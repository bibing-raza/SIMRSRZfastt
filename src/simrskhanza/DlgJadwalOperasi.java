/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgJadwal.java
 *
 * Created on May 22, 2010, 10:25:16 PM
 */

package simrskhanza;
import fungsi.WarnaTable;
import fungsi.batasInput;
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
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import keuangan.DlgKamar;

/**
 *
 * @author dosen
 */
public class DlgJadwalOperasi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private DlgCariPoli poli = new DlgCariPoli(null, false);
    private DlgKamar kamar = new DlgKamar(null, false);
    private DlgPasien pasien = new DlgPasien(null, false);
    private String terlaksana = "", kdoperasi = "", rawat = "", kdunit = "", nmunit = "";
    private int x = 0;

    /** Creates new form DlgJadwal
     * @param parent
     * @param modal */
    public DlgJadwalOperasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);        

        Object[] row = {"No. Booking", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Operasi", "Jns. Tindakan Operasi",
            "Poliklinik/Rg. Rawat", "Terlaksana", "No. Peserta", "Last Update", "kode_paket", "spesialisasi_operasi",
            "tanggaloperasi", "kodepoli", "terlaksana", "status", "namapoli", "jenistindakan"
        };
        
        tabMode=new DefaultTableModel(null,row){
             @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbJadwal.setModel(tabMode);
        tbJadwal.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbJadwal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 18; i++) {
            TableColumn column = tbJadwal.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(100);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(350);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(140);
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
            }
        }
        tbJadwal.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Kode Operasi", "Jenis Operasi"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbJenis.setModel(tabMode1);
        tbJenis.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbJenis.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 2; i++) {
            TableColumn column = tbJenis.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(100);
            } else if (i == 1) {
                column.setPreferredWidth(270);
            }
        }
        tbJenis.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TNoPeserta.setDocument(new batasInput((int) 25).getKata(TNoPeserta));
        
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
        
        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(poli.getTable().getSelectedRow()!= -1){
                    KdPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),0).toString());
                    TPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),1).toString());
                } 
                KdPoli.requestFocus();
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
        
        kamar.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgJadwalOperasi")) {
                    if (kamar.getTable().getSelectedRow() != -1) {
                        KdKamar.setText(kamar.getTable().getValueAt(kamar.getTable().getSelectedRow(), 1).toString());
                        TnmKamar.setText(Sequel.cariIsi("SELECT b.nm_bangsal FROM bangsal b INNER JOIN kamar k ON k.kd_bangsal=b.kd_bangsal WHERE k.kd_kamar='" + KdKamar.getText() + "'"));
                        BtnKamar.requestFocus();
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
        
        kamar.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgJadwalOperasi")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        kamar.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgJadwalOperasi")) {
                    if (pasien.getTable().getSelectedRow() != -1) {
                        TNoRw.setText("-");
                        TNoRm.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 1).toString());
                        Tpasien.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 2).toString());
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

        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgJadwalOperasi")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pasien.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
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

        WindowJenisOperasi = new javax.swing.JDialog();
        internalFrame7 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        tbJenis = new widget.Table();
        internalFrame8 = new widget.InternalFrame();
        BtnKeluar1 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbJadwal = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
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
        panelBiasa1 = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        Tpasien = new widget.TextBox();
        jLabel10 = new widget.Label();
        TPoli = new widget.TextBox();
        btnPasien = new widget.Button();
        TNoRw = new widget.TextBox();
        KdPoli = new widget.TextBox();
        BtnPoli = new widget.Button();
        TNoRm = new widget.TextBox();
        jLabel5 = new widget.Label();
        TNoBoking = new widget.TextBox();
        jLabel8 = new widget.Label();
        tglOperasi = new widget.Tanggal();
        jLabel12 = new widget.Label();
        cmbSpesialis = new widget.ComboBox();
        jLabel13 = new widget.Label();
        TJnsOperasi = new widget.TextBox();
        btnJenis = new widget.Button();
        jLabel14 = new widget.Label();
        cmbRawat = new widget.ComboBox();
        jLabel11 = new widget.Label();
        KdKamar = new widget.TextBox();
        TnmKamar = new widget.TextBox();
        BtnKamar = new widget.Button();
        jLabel15 = new widget.Label();
        cmbTerlaksana = new widget.ComboBox();
        jLabel9 = new widget.Label();
        TNoPeserta = new widget.TextBox();

        WindowJenisOperasi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowJenisOperasi.setName("WindowJenisOperasi"); // NOI18N
        WindowJenisOperasi.setUndecorated(true);
        WindowJenisOperasi.setResizable(false);

        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Jenis Tindakan Operasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame7.setLayout(new java.awt.BorderLayout());

        Scroll1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        Scroll1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbJenis.setToolTipText("Silahkan klik untuk memilih data yang ataupun dihapus");
        tbJenis.setName("tbJenis"); // NOI18N
        tbJenis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbJenisMouseClicked(evt);
            }
        });
        tbJenis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbJenisKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbJenis);

        internalFrame7.add(Scroll1, java.awt.BorderLayout.CENTER);

        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setPreferredSize(new java.awt.Dimension(2, 44));
        internalFrame8.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 9, 9));

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 26));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        internalFrame8.add(BtnKeluar1);

        internalFrame7.add(internalFrame8, java.awt.BorderLayout.PAGE_END);

        WindowJenisOperasi.getContentPane().add(internalFrame7, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Jadwal Operasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbJadwal.setAutoCreateRowSorter(true);
        tbJadwal.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbJadwal.setName("tbJadwal"); // NOI18N
        tbJadwal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbJadwalMouseClicked(evt);
            }
        });
        tbJadwal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbJadwalKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbJadwal);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 9, 9));

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(90, 26));
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
        BtnBatal.setPreferredSize(new java.awt.Dimension(80, 26));
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
        BtnHapus.setPreferredSize(new java.awt.Dimension(90, 26));
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
        BtnEdit.setPreferredSize(new java.awt.Dimension(90, 26));
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

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(90, 26));
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
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Operasi :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-11-2023" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-11-2023" }));
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
        TCari.setPreferredSize(new java.awt.Dimension(240, 23));
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

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('4');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+4");
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

        panelBiasa1.setName("panelBiasa1"); // NOI18N
        panelBiasa1.setPreferredSize(new java.awt.Dimension(1023, 190));
        panelBiasa1.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Pasien :");
        jLabel3.setName("jLabel3"); // NOI18N
        panelBiasa1.add(jLabel3);
        jLabel3.setBounds(0, 10, 100, 23);

        Tpasien.setEditable(false);
        Tpasien.setForeground(new java.awt.Color(0, 0, 0));
        Tpasien.setName("Tpasien"); // NOI18N
        panelBiasa1.add(Tpasien);
        Tpasien.setBounds(305, 10, 325, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Poliklinik :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelBiasa1.add(jLabel10);
        jLabel10.setBounds(0, 94, 100, 23);

        TPoli.setEditable(false);
        TPoli.setForeground(new java.awt.Color(0, 0, 0));
        TPoli.setName("TPoli"); // NOI18N
        panelBiasa1.add(TPoli);
        TPoli.setBounds(158, 94, 324, 23);

        btnPasien.setForeground(new java.awt.Color(0, 0, 0));
        btnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPasien.setMnemonic('1');
        btnPasien.setToolTipText("ALt+1");
        btnPasien.setName("btnPasien"); // NOI18N
        btnPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasienActionPerformed(evt);
            }
        });
        panelBiasa1.add(btnPasien);
        btnPasien.setBounds(635, 10, 28, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        panelBiasa1.add(TNoRw);
        TNoRw.setBounds(104, 10, 122, 23);

        KdPoli.setEditable(false);
        KdPoli.setForeground(new java.awt.Color(0, 0, 0));
        KdPoli.setName("KdPoli"); // NOI18N
        panelBiasa1.add(KdPoli);
        KdPoli.setBounds(104, 94, 50, 23);

        BtnPoli.setForeground(new java.awt.Color(0, 0, 0));
        BtnPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPoli.setMnemonic('2');
        BtnPoli.setToolTipText("ALt+2");
        BtnPoli.setName("BtnPoli"); // NOI18N
        BtnPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPoliActionPerformed(evt);
            }
        });
        panelBiasa1.add(BtnPoli);
        BtnPoli.setBounds(485, 94, 28, 23);

        TNoRm.setEditable(false);
        TNoRm.setForeground(new java.awt.Color(0, 0, 0));
        TNoRm.setName("TNoRm"); // NOI18N
        panelBiasa1.add(TNoRm);
        TNoRm.setBounds(230, 10, 70, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("No. Booking :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelBiasa1.add(jLabel5);
        jLabel5.setBounds(0, 38, 100, 23);

        TNoBoking.setEditable(false);
        TNoBoking.setForeground(new java.awt.Color(0, 0, 0));
        TNoBoking.setName("TNoBoking"); // NOI18N
        panelBiasa1.add(TNoBoking);
        TNoBoking.setBounds(104, 38, 150, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Operasi :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelBiasa1.add(jLabel8);
        jLabel8.setBounds(170, 150, 80, 23);

        tglOperasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-11-2023" }));
        tglOperasi.setDisplayFormat("dd-MM-yyyy");
        tglOperasi.setName("tglOperasi"); // NOI18N
        tglOperasi.setOpaque(false);
        tglOperasi.setPreferredSize(new java.awt.Dimension(90, 23));
        panelBiasa1.add(tglOperasi);
        tglOperasi.setBounds(255, 150, 90, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Spesialisasi : ");
        jLabel12.setName("jLabel12"); // NOI18N
        panelBiasa1.add(jLabel12);
        jLabel12.setBounds(435, 66, 70, 23);

        cmbSpesialis.setForeground(new java.awt.Color(0, 0, 0));
        cmbSpesialis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Bedah", "Obstetrik & Ginekologi", "Bedah Saraf", "THT", "Mata", "Kulit & Kelamin", "Gigi & Mulut", "Bedah Anak", "Kardiovaskuler", "Bedah Orthopedi", "Thorak", "Digestive", "Urologi", "Onkologi", "Bedah Vaskuler", "Lain-lain" }));
        cmbSpesialis.setName("cmbSpesialis"); // NOI18N
        panelBiasa1.add(cmbSpesialis);
        cmbSpesialis.setBounds(507, 66, 150, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Jenis Operasi :");
        jLabel13.setName("jLabel13"); // NOI18N
        panelBiasa1.add(jLabel13);
        jLabel13.setBounds(0, 66, 100, 23);

        TJnsOperasi.setEditable(false);
        TJnsOperasi.setForeground(new java.awt.Color(0, 0, 0));
        TJnsOperasi.setName("TJnsOperasi"); // NOI18N
        panelBiasa1.add(TJnsOperasi);
        TJnsOperasi.setBounds(104, 66, 294, 23);

        btnJenis.setForeground(new java.awt.Color(0, 0, 0));
        btnJenis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnJenis.setMnemonic('1');
        btnJenis.setToolTipText("ALt+1");
        btnJenis.setName("btnJenis"); // NOI18N
        btnJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJenisActionPerformed(evt);
            }
        });
        panelBiasa1.add(btnJenis);
        btnJenis.setBounds(400, 66, 28, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Jns. Rawat : ");
        jLabel14.setName("jLabel14"); // NOI18N
        panelBiasa1.add(jLabel14);
        jLabel14.setBounds(490, 38, 70, 23);

        cmbRawat.setForeground(new java.awt.Color(0, 0, 0));
        cmbRawat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rawat Jalan", "Rawat Inap" }));
        cmbRawat.setName("cmbRawat"); // NOI18N
        cmbRawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRawatActionPerformed(evt);
            }
        });
        panelBiasa1.add(cmbRawat);
        cmbRawat.setBounds(561, 38, 95, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Rg. Rawat :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelBiasa1.add(jLabel11);
        jLabel11.setBounds(0, 122, 100, 23);

        KdKamar.setEditable(false);
        KdKamar.setForeground(new java.awt.Color(0, 0, 0));
        KdKamar.setName("KdKamar"); // NOI18N
        panelBiasa1.add(KdKamar);
        KdKamar.setBounds(104, 122, 100, 23);

        TnmKamar.setEditable(false);
        TnmKamar.setForeground(new java.awt.Color(0, 0, 0));
        TnmKamar.setName("TnmKamar"); // NOI18N
        panelBiasa1.add(TnmKamar);
        TnmKamar.setBounds(208, 122, 370, 23);

        BtnKamar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKamar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKamar.setMnemonic('2');
        BtnKamar.setToolTipText("ALt+2");
        BtnKamar.setName("BtnKamar"); // NOI18N
        BtnKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKamarActionPerformed(evt);
            }
        });
        panelBiasa1.add(BtnKamar);
        BtnKamar.setBounds(580, 122, 28, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Terlaksana :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelBiasa1.add(jLabel15);
        jLabel15.setBounds(0, 150, 100, 23);

        cmbTerlaksana.setForeground(new java.awt.Color(0, 0, 0));
        cmbTerlaksana.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum", "Sudah" }));
        cmbTerlaksana.setName("cmbTerlaksana"); // NOI18N
        panelBiasa1.add(cmbTerlaksana);
        cmbTerlaksana.setBounds(104, 150, 65, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("No. Peserta :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelBiasa1.add(jLabel9);
        jLabel9.setBounds(255, 38, 80, 23);

        TNoPeserta.setForeground(new java.awt.Color(0, 0, 0));
        TNoPeserta.setName("TNoPeserta"); // NOI18N
        panelBiasa1.add(TNoPeserta);
        TNoPeserta.setBounds(339, 38, 150, 23);

        internalFrame1.add(panelBiasa1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            terlaksana = "";
            rawat = "";
            kdunit = "";
            nmunit = "";
            autoNomorBooking();
            
            if (cmbTerlaksana.getSelectedIndex() == 0) {
                terlaksana = "0";
            } else {
                terlaksana = "1";
            }
            
            if (cmbRawat.getSelectedIndex() == 0) {
                rawat = "Ralan";
                kdunit = KdPoli.getText();
                nmunit = TPoli.getText();
            } else {
                rawat = "Ranap";
                kdunit = KdKamar.getText();
                nmunit = TnmKamar.getText();
            }
            
            Sequel.menyimpan("jadwal_operasi", "'" + TNoRw.getText() + "','" + TNoRm.getText() + "','" + TNoBoking.getText() + "',"
                    + "'" + Valid.SetTgl(tglOperasi.getSelectedItem() + "") + "','" + TJnsOperasi.getText() + "',"
                    + "'" + kdunit + "','" + nmunit + "','" + terlaksana + "','" + TNoPeserta.getText() + "','" + Sequel.cariIsi("select now()") + "',"
                    + "'" + kdoperasi + "','" + cmbSpesialis.getSelectedItem().toString() + "','" + rawat + "'", "Jadwal Operasi");
            
            tampil();
            emptTeks();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,KdPoli,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from jadwal_operasi where kd_booking=?", 1, new String[]{TNoBoking.getText()
            }) == true) {
                tampil();
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (Sequel.cariInteger("select count(-1) from jadwal_operasi where kd_booking='" + TNoBoking.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tbJadwal.requestFocus();
        } else {
            terlaksana = "";
            rawat = "";
            kdunit = "";
            nmunit = "";
            
            if (cmbTerlaksana.getSelectedIndex() == 0) {
                terlaksana = "0";
            } else {
                terlaksana = "1";
            }
            
            if (cmbRawat.getSelectedIndex() == 0) {
                rawat = "Ralan";
                kdunit = KdPoli.getText();
                nmunit = TPoli.getText();
            } else {
                rawat = "Ranap";
                kdunit = KdKamar.getText();
                nmunit = TnmKamar.getText();
            }

            Sequel.mengedit("jadwal_operasi", "kd_booking='" + TNoBoking.getText() + "'",
                    "no_rawat='" + TNoRw.getText() + "', "
                    + "nomr='" + TNoRm.getText() + "', "
                    + "tanggaloperasi='" + Valid.SetTgl(tglOperasi.getSelectedItem() + "") + "', "
                    + "jenistindakan='" + TJnsOperasi.getText() + "', "
                    + "kodepoli='" + kdunit + "', "
                    + "namapoli='" + nmunit + "', "
                    + "terlaksana='" + terlaksana + "', "
                    + "no_peserta='" + TNoPeserta.getText() + "', "
                    + "last_update='" + Sequel.cariIsi("select now()") + "', "
                    + "kode_paket='" + kdoperasi + "', "
                    + "spesialisasi_operasi='" + cmbSpesialis.getSelectedItem().toString() + "', "
                    + "status='" + rawat + "'");

            tampil();
            emptTeks();
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
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
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
            TCari.setText("");
        }else{
            Valid.pindah(evt, BtnCari,TNoRw);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbJadwalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbJadwalMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbJadwalMouseClicked

    private void tbJadwalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbJadwalKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbJadwalKeyPressed

private void btnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasienActionPerformed
    akses.setform("DlgJadwalOperasi");
    pasien.emptTeks();
    pasien.isCek();
    pasien.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    pasien.setLocationRelativeTo(internalFrame1);
    pasien.setVisible(true);
}//GEN-LAST:event_btnPasienActionPerformed

private void BtnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPoliActionPerformed
    akses.setform("DlgJadwalOperasi");
    poli.isCek();
    poli.setSize(1055, internalFrame1.getHeight() - 40);
    poli.setLocationRelativeTo(internalFrame1);
    poli.setVisible(true);
}//GEN-LAST:event_BtnPoliActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void btnJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJenisActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            tbJadwal.requestFocus();
        } else {
            WindowJenisOperasi.setSize(396, internalFrame1.getHeight() - 40);
            WindowJenisOperasi.setLocationRelativeTo(internalFrame1);
            WindowJenisOperasi.setVisible(true);
            tampilJenis();
        }
    }//GEN-LAST:event_btnJenisActionPerformed

    private void BtnKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKamarActionPerformed
        akses.setform("DlgJadwalOperasi");
        kamar.load();
        kamar.isCek();
        kamar.emptTeks();
        kamar.tampil();
        kamar.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        kamar.setLocationRelativeTo(internalFrame1);
        kamar.setVisible(true);
    }//GEN-LAST:event_BtnKamarActionPerformed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        WindowJenisOperasi.dispose();        
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void tbJenisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbJenisMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                if (evt.getClickCount() == 2) {
                    getDataJenis();
                    BtnKeluar1ActionPerformed(null);
                }
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbJenisMouseClicked

    private void tbJenisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbJenisKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_SPACE)) {
                try {
                    getDataJenis();
                    BtnKeluar1ActionPerformed(null);
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbJenisKeyPressed

    private void cmbRawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRawatActionPerformed
        if (cmbRawat.getSelectedIndex() == 0) {
            BtnPoli.setEnabled(true);
            BtnKamar.setEnabled(false);
            KdKamar.setText("-");
            TnmKamar.setText("-");
        } else {
            BtnPoli.setEnabled(false);
            BtnKamar.setEnabled(true);
            KdPoli.setText("-");
            TPoli.setText("-");
        }
    }//GEN-LAST:event_cmbRawatActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgJadwalOperasi dialog = new DlgJadwalOperasi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKamar;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnPoli;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextBox KdKamar;
    private widget.TextBox KdPoli;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    public widget.TextBox TCari;
    private widget.TextBox TJnsOperasi;
    private widget.TextBox TNoBoking;
    private widget.TextBox TNoPeserta;
    private widget.TextBox TNoRm;
    private widget.TextBox TNoRw;
    private widget.TextBox TPoli;
    private widget.TextBox TnmKamar;
    private widget.TextBox Tpasien;
    private javax.swing.JDialog WindowJenisOperasi;
    private widget.Button btnJenis;
    private widget.Button btnPasien;
    private widget.ComboBox cmbRawat;
    private widget.ComboBox cmbSpesialis;
    private widget.ComboBox cmbTerlaksana;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel3;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.PanelBiasa panelBiasa1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbJadwal;
    private widget.Table tbJenis;
    private widget.Tanggal tglOperasi;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select jo.kd_booking, jo.no_rawat, p.no_rkm_medis, p.nm_pasien, date_format(jo.tanggaloperasi,'%d-%m-%Y') tgloperasi, "
                    + "jo.jenistindakan, jo.namapoli, if(jo.terlaksana='0','Belum','Sudah') status, jo.no_peserta, jo.last_update, jo.kode_paket, "
                    + "jo.spesialisasi_operasi, jo.tanggaloperasi, jo.kodepoli, jo.terlaksana, jo.status rawat, "
                    + "jo.namapoli from jadwal_operasi jo inner join pasien p on p.no_rkm_medis=jo.nomr where "
                    + "jo.tanggaloperasi between ? and ? and jo.kd_booking like ? or "
                    + "jo.tanggaloperasi between ? and ? and jo.no_rawat like ? or "
                    + "jo.tanggaloperasi between ? and ? and p.no_rkm_medis like ? or "
                    + "jo.tanggaloperasi between ? and ? and p.nm_pasien like ? or "
                    + "jo.tanggaloperasi between ? and ? and jo.jenistindakan like ? or "
                    + "jo.tanggaloperasi between ? and ? and jo.namapoli like ? or "
                    + "jo.tanggaloperasi between ? and ? and if(jo.terlaksana='0','Belum','Sudah') like ? or "
                    + "jo.tanggaloperasi between ? and ? and jo.no_peserta like ? order by jo.kd_booking desc, jo.tanggaloperasi desc");
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("kd_booking"),
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgloperasi"),
                        "(" + rs.getString("spesialisasi_operasi") + ") " + rs.getString("jenistindakan"),
                        rs.getString("namapoli"),
                        rs.getString("status"),
                        rs.getString("no_peserta"),
                        rs.getString("last_update"),
                        rs.getString("kode_paket"),
                        rs.getString("spesialisasi_operasi"),
                        rs.getString("tanggaloperasi"),
                        rs.getString("kodepoli"),
                        rs.getString("terlaksana"),
                        rs.getString("rawat"),
                        rs.getString("namapoli"),
                        rs.getString("jenistindakan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
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
        TNoRw.setText("-");
        TNoRm.setText("");
        Tpasien.setText("");
        TNoPeserta.setText("");
        cmbRawat.setSelectedIndex(0);
        TJnsOperasi.setText("");
        cmbSpesialis.setSelectedIndex(0);        
        KdPoli.setText("-");
        TPoli.setText("-");
        KdKamar.setText("-");
        TnmKamar.setText("-");
        cmbTerlaksana.setSelectedIndex(0);
        tglOperasi.setDate(new Date());
        DTPCari1.setDate(new Date());
        DTPCari2.setDate(new Date());
        kdoperasi = "-";
        rawat = "-";
        kdunit = "";
        nmunit = "";
        autoNomorBooking();
    }

    private void getData() {
        terlaksana = "";
        kdoperasi = "";
        rawat = "";
        kdunit = "";
        nmunit = "";
        
        if (tbJadwal.getSelectedRow() != -1) {
            TNoBoking.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 0).toString());
            TNoRw.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 1).toString());
            TNoRm.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 2).toString());
            Tpasien.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 3).toString());
            Valid.SetTgl(tglOperasi, tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 12).toString());
            TJnsOperasi.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 17).toString());
            terlaksana = tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 14).toString();            
            TNoPeserta.setText(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 8).toString());
            kdoperasi = tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 10).toString();
            cmbSpesialis.setSelectedItem(tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 11).toString());
            rawat = tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 15).toString();
            kdunit = tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 13).toString();
            nmunit = tbJadwal.getValueAt(tbJadwal.getSelectedRow(), 16).toString();

            if (terlaksana.equals("0")) {
                cmbTerlaksana.setSelectedIndex(0);
            } else {
                cmbTerlaksana.setSelectedIndex(1);
            }
            
            if (rawat.equals("Ralan")) {
                cmbRawat.setSelectedIndex(0);
                KdPoli.setText(kdunit);
                TPoli.setText(nmunit);
                KdKamar.setText("-");
                TnmKamar.setText("-");
            } else {
                cmbRawat.setSelectedIndex(1);
                KdPoli.setText("-");
                TPoli.setText("-");
                KdKamar.setText(kdunit);
                TnmKamar.setText(nmunit);
            }
        }
    }
    
    private void getDataJenis() {
        kdoperasi = "";
        if (tbJenis.getSelectedRow() != -1) {
            kdoperasi = tbJenis.getValueAt(tbJenis.getSelectedRow(), 0).toString();
            TJnsOperasi.setText(tbJenis.getValueAt(tbJenis.getSelectedRow(), 1).toString());            
        }
    }
    
    public void autoNomorBooking() {
        Valid.autoNomer7("select ifnull(MAX(CONVERT(LEFT(kd_booking,4),signed)),0) from jadwal_operasi where "
                + "tanggaloperasi like '%" + Valid.SetTgl(tglOperasi.getSelectedItem() + "").substring(0, 7) + "%' ", "/OP/" + Valid.SetTgl(tglOperasi.getSelectedItem() + "").substring(5, 7)
                + "/" + Valid.SetTgl(tglOperasi.getSelectedItem() + "").substring(0, 4), 4, TNoBoking);
    }

    private void tampilJenis() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("select kode_paket, nm_perawatan FROM paket_operasi WHERE STATUS='1' ORDER BY kode_paket");
            try {                
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{
                        rs1.getString("kode_paket"),
                        rs1.getString("nm_perawatan")
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
    
    public void setData(String norw) {
        TNoRw.setText(norw);
        TNoRm.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + norw + "'"));
        Tpasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRm.getText() + "'"));        
        autoNomorBooking();
        
        if (Sequel.cariIsi("select status_lanjut from reg_periksa where no_rawat='" + norw + "'").equals("Ralan")) {
            cmbRawat.setSelectedIndex(0);
            BtnPoli.setEnabled(true);
            BtnKamar.setEnabled(false);
            KdPoli.setText(Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + norw + "'"));
            TPoli.setText(Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + KdPoli.getText() + "'"));
            KdKamar.setText("-");
            TnmKamar.setText("-");
        } else {
            cmbRawat.setSelectedIndex(1);
            BtnPoli.setEnabled(false);
            BtnKamar.setEnabled(true);
            KdPoli.setText("-");
            TPoli.setText("-");
            KdKamar.setText(Sequel.cariIsi("select ki.kd_kamar from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar where "
                    + "ki.no_rawat='" + norw + "' order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1"));
            TnmKamar.setText(Sequel.cariIsi("SELECT b.nm_bangsal FROM kamar k INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE "
                    + "k.kd_kamar='" + KdKamar.getText() + "'"));
        }
        
        if (Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat='" + norw + "'").equals("B01")
                || Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat='" + norw + "'").equals("A03")) {
            TNoPeserta.setText(Sequel.cariIsi("select no_peserta from pasien where no_rkm_medis='" + TNoRm.getText() + "'"));
        } else {
            TNoPeserta.setText("");
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getoperasi());
        BtnHapus.setEnabled(akses.getoperasi());
        BtnEdit.setEnabled(akses.getoperasi());
    }
}

