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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author dosen
 */
public class RMPemantauanHarian24Jam extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2;
    private ResultSet rs, rs1, rs2;
    private String urutanJam = "", dataParental = "", wktSimpanPantau = "";
    private int i = 0, x = 0, totParental = 0;

    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public RMPemantauanHarian24Jam(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {
            "No. Rawat", "Kode Pantau", "No. RM", "Nama Pasien", "Tgl. Pantau", "Jam", "Nadi", "Suhu",
            "GCS", "Kesadaran", "Tensi", "Frek. Nafas", "SPO2", "Makan Minum", "NGT", "Jml. Parental", "Tot. Intake",
            "Urine", "NGT/Darah", "Drain", "Muntah", "BAB", "IWL", "Tot. Output", "Balance",
            "gcse", "gcsm", "gcsv", "tgl_pantau", "urutan_jam", "wktu_simpan"
        };
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbPantau.setModel(tabMode);
        tbPantau.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPantau.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 31; i++) {
            TableColumn column = tbPantau.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(115);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(45);
            } else if (i == 6) {
                column.setPreferredWidth(50);
            } else if (i == 7) {
                column.setPreferredWidth(50);
            } else if (i == 8) {
                column.setPreferredWidth(105);
            } else if (i == 9) {
                column.setPreferredWidth(100);
            } else if (i == 10) {
                column.setPreferredWidth(55);
            } else if (i == 11) {
                column.setPreferredWidth(75);
            } else if (i == 12) {
                column.setPreferredWidth(50);
            } else if (i == 13) {
                column.setPreferredWidth(75);
            } else if (i == 14) {
                column.setPreferredWidth(45);
            } else if (i == 15) {
                column.setPreferredWidth(75);
            } else if (i == 16) {
                column.setPreferredWidth(75);
            } else if (i == 17) {
                column.setPreferredWidth(40);
            } else if (i == 18) {
                column.setPreferredWidth(75);
            } else if (i == 19) {
                column.setPreferredWidth(45);
            } else if (i == 20) {
                column.setPreferredWidth(65);
            } else if (i == 21) {
                column.setPreferredWidth(35);
            } else if (i == 22) {
                column.setPreferredWidth(45);
            } else if (i == 23) {
                column.setPreferredWidth(80);
            } else if (i == 24) {
                column.setPreferredWidth(55);
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
            } 
        }
        tbPantau.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Tanggal", "Nama Obat", "Jml. Beri (cc)", "Kode Pemantauan", "tgl"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbParental.setModel(tabMode1);
        tbParental.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbParental.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbParental.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(350);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(123);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbParental.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "Tgl. Beri", "Nama Obat", "Jns. Obat", "Jumlah", "Dosis", "Cara Pemberian/Rute"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbObat.setModel(tabMode2);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 6; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(300);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            } else if (i == 5) {
                column.setPreferredWidth(140);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((int)100).getKata(TCari));
        Tnadi.setDocument(new batasInput((byte) 3).getOnlyAngka(Tnadi));
        Tsuhu.setDocument(new batasInput((int) 4).getKata(Tsuhu));
        Tbb.setDocument(new batasInput((byte) 4).getKata(Tbb));
        Tgcse.setDocument(new batasInput((byte) 2).getOnlyAngka(Tgcse));
        Tgcsm.setDocument(new batasInput((byte) 2).getOnlyAngka(Tgcsm));
        Tgcsv.setDocument(new batasInput((byte) 2).getOnlyAngka(Tgcsv));
        Ttensi.setDocument(new batasInput((byte) 7).getKata(Ttensi));
        Trr.setDocument(new batasInput((byte) 7).getKata(Trr));
        Tspo.setDocument(new batasInput((byte) 7).getKata(Tspo));
        Tmm.setDocument(new batasInput((byte) 3).getOnlyAngka(Tmm));
        Tngt.setDocument(new batasInput((byte) 3).getOnlyAngka(Tngt));
        Tline.setDocument(new batasInput((byte) 3).getOnlyAngka(Tline));
        Turin.setDocument(new batasInput((byte) 3).getOnlyAngka(Turin));
        TngtDarah.setDocument(new batasInput((byte) 3).getOnlyAngka(TngtDarah));
        Tdrain.setDocument(new batasInput((byte) 3).getOnlyAngka(Tdrain));
        Tmuntah.setDocument(new batasInput((byte) 3).getOnlyAngka(Tmuntah));
        Tbab.setDocument(new batasInput((byte) 3).getOnlyAngka(Tbab));
        TjmlBeri.setDocument(new batasInput((byte) 4).getOnlyAngka(TjmlBeri));

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
        MnHapus = new javax.swing.JMenuItem();
        MnGanti = new javax.swing.JMenuItem();
        WindowParental = new javax.swing.JDialog();
        internalFrame10 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbObat = new widget.Table();
        internalFrame11 = new widget.InternalFrame();
        internalFrame12 = new widget.InternalFrame();
        jLabel3 = new widget.Label();
        TnmObat = new widget.TextBox();
        jLabel4 = new widget.Label();
        TjmlBeri = new widget.TextBox();
        jLabel21 = new widget.Label();
        internalFrame13 = new widget.InternalFrame();
        jLabel23 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel25 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        BtnCari1 = new widget.Button();
        BtnSimpan1 = new widget.Button();
        BtnBatal1 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPantau = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnParental = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel28 = new widget.Label();
        DTPCariA = new widget.Tanggal();
        jLabel29 = new widget.Label();
        DTPCariB = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelGlass10 = new widget.panelisi();
        panelGlass7 = new widget.panelisi();
        jLabel5 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRm = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel8 = new widget.Label();
        tglPantau = new widget.Tanggal();
        jLabel9 = new widget.Label();
        Tnadi = new widget.TextBox();
        jLabel10 = new widget.Label();
        Tsuhu = new widget.TextBox();
        jLabel11 = new widget.Label();
        cmbJam = new widget.ComboBox();
        jLabel12 = new widget.Label();
        jLabel13 = new widget.Label();
        Tgcse = new widget.TextBox();
        jLabel14 = new widget.Label();
        Tgcsm = new widget.TextBox();
        jLabel15 = new widget.Label();
        Tgcsv = new widget.TextBox();
        jLabel16 = new widget.Label();
        Tkesadaran = new widget.TextBox();
        jLabel17 = new widget.Label();
        Ttensi = new widget.TextBox();
        jLabel36 = new widget.Label();
        jLabel18 = new widget.Label();
        Trr = new widget.TextBox();
        jLabel40 = new widget.Label();
        jLabel19 = new widget.Label();
        Tspo = new widget.TextBox();
        jLabel41 = new widget.Label();
        jLabel20 = new widget.Label();
        Tmm = new widget.TextBox();
        jLabel37 = new widget.Label();
        jLabel38 = new widget.Label();
        jLabel39 = new widget.Label();
        Tngt = new widget.TextBox();
        jLabel42 = new widget.Label();
        Tline = new widget.TextBox();
        jLabel44 = new widget.Label();
        Ttotintake = new widget.TextBox();
        jLabel45 = new widget.Label();
        jLabel22 = new widget.Label();
        jLabel46 = new widget.Label();
        Turin = new widget.TextBox();
        jLabel47 = new widget.Label();
        jLabel48 = new widget.Label();
        TngtDarah = new widget.TextBox();
        jLabel49 = new widget.Label();
        jLabel50 = new widget.Label();
        Tdrain = new widget.TextBox();
        jLabel51 = new widget.Label();
        jLabel52 = new widget.Label();
        Tmuntah = new widget.TextBox();
        jLabel53 = new widget.Label();
        jLabel54 = new widget.Label();
        Tbab = new widget.TextBox();
        jLabel55 = new widget.Label();
        Tiwl = new widget.TextBox();
        jLabel57 = new widget.Label();
        Ttotouput = new widget.TextBox();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        Tbalance = new widget.TextBox();
        jLabel60 = new widget.Label();
        jLabel24 = new widget.Label();
        Tbb = new widget.TextBox();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        TkdPantau = new widget.TextBox();
        BtnTotOutput = new widget.Button();
        jLabel26 = new widget.Label();
        jLabel56 = new widget.Label();
        jLabel27 = new widget.Label();
        Scroll1 = new widget.ScrollPane();
        tbParental = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnHapus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        MnHapus.setText("Hapus");
        MnHapus.setName("MnHapus"); // NOI18N
        MnHapus.setPreferredSize(new java.awt.Dimension(90, 26));
        MnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapus);

        MnGanti.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGanti.setText("Ganti");
        MnGanti.setName("MnGanti"); // NOI18N
        MnGanti.setPreferredSize(new java.awt.Dimension(90, 26));
        MnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGantiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnGanti);

        WindowParental.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowParental.setName("WindowParental"); // NOI18N
        WindowParental.setUndecorated(true);
        WindowParental.setResizable(false);

        internalFrame10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Parental / Line / Obat-obatan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame10.setLayout(new java.awt.BorderLayout());

        Scroll2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " [ Pemberian Obat Terjadwal ] ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang akan disimpan");
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbObatKeyReleased(evt);
            }
        });
        Scroll2.setViewportView(tbObat);

        internalFrame10.add(Scroll2, java.awt.BorderLayout.CENTER);

        internalFrame11.setBorder(null);
        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setPreferredSize(new java.awt.Dimension(0, 90));
        internalFrame11.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame11.setLayout(new java.awt.BorderLayout());

        internalFrame12.setBorder(null);
        internalFrame12.setName("internalFrame12"); // NOI18N
        internalFrame12.setPreferredSize(new java.awt.Dimension(0, 44));
        internalFrame12.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame12.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nama Obat Dipilih : ");
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(120, 23));
        internalFrame12.add(jLabel3);
        jLabel3.setBounds(5, 9, 120, 23);

        TnmObat.setForeground(new java.awt.Color(0, 0, 0));
        TnmObat.setName("TnmObat"); // NOI18N
        TnmObat.setPreferredSize(new java.awt.Dimension(450, 23));
        TnmObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmObatKeyPressed(evt);
            }
        });
        internalFrame12.add(TnmObat);
        TnmObat.setBounds(130, 9, 450, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Jml. Pemberian :");
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(100, 23));
        internalFrame12.add(jLabel4);
        jLabel4.setBounds(585, 9, 100, 23);

        TjmlBeri.setForeground(new java.awt.Color(0, 0, 0));
        TjmlBeri.setName("TjmlBeri"); // NOI18N
        TjmlBeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjmlBeriKeyPressed(evt);
            }
        });
        internalFrame12.add(TjmlBeri);
        TjmlBeri.setBounds(690, 9, 64, 24);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("cc.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(30, 23));
        internalFrame12.add(jLabel21);
        jLabel21.setBounds(759, 9, 30, 23);

        internalFrame11.add(internalFrame12, java.awt.BorderLayout.PAGE_START);

        internalFrame13.setBorder(null);
        internalFrame13.setName("internalFrame13"); // NOI18N
        internalFrame13.setPreferredSize(new java.awt.Dimension(0, 44));
        internalFrame13.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 9, 9));

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Tgl. Pemberian :");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(99, 23));
        internalFrame13.add(jLabel23);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-01-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 26));
        internalFrame13.add(DTPCari1);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("s.d.");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(23, 23));
        internalFrame13.add(jLabel25);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-01-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 26));
        internalFrame13.add(DTPCari2);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('1');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+1");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 26));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        internalFrame13.add(BtnCari1);

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
        BtnSimpan1.setText("Simpan");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(100, 26));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        internalFrame13.add(BtnSimpan1);

        BtnBatal1.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal1.setMnemonic('B');
        BtnBatal1.setText("Baru");
        BtnBatal1.setToolTipText("Alt+B");
        BtnBatal1.setName("BtnBatal1"); // NOI18N
        BtnBatal1.setPreferredSize(new java.awt.Dimension(90, 26));
        BtnBatal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatal1ActionPerformed(evt);
            }
        });
        internalFrame13.add(BtnBatal1);

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
        internalFrame13.add(BtnKeluar1);

        internalFrame11.add(internalFrame13, java.awt.BorderLayout.PAGE_END);

        internalFrame10.add(internalFrame11, java.awt.BorderLayout.PAGE_END);

        WindowParental.getContentPane().add(internalFrame10, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pemantauan Harian Pasien 24 Jam ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPantau.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPantau.setName("tbPantau"); // NOI18N
        tbPantau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPantauMouseClicked(evt);
            }
        });
        tbPantau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPantauKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPantauKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbPantau);

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

        BtnParental.setForeground(new java.awt.Color(0, 0, 0));
        BtnParental.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnParental.setMnemonic('P');
        BtnParental.setText("Parental/Line/Obat");
        BtnParental.setToolTipText("Alt+P");
        BtnParental.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnParental.setName("BtnParental"); // NOI18N
        BtnParental.setPreferredSize(new java.awt.Dimension(155, 30));
        BtnParental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnParentalActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnParental);

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

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Tanggal :");
        jLabel28.setName("jLabel28"); // NOI18N
        jLabel28.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel28);

        DTPCariA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-01-2024" }));
        DTPCariA.setDisplayFormat("dd-MM-yyyy");
        DTPCariA.setName("DTPCariA"); // NOI18N
        DTPCariA.setOpaque(false);
        DTPCariA.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCariA);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("s.d.");
        jLabel29.setName("jLabel29"); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel29);

        DTPCariB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-01-2024" }));
        DTPCariB.setDisplayFormat("dd-MM-yyyy");
        DTPCariB.setName("DTPCariB"); // NOI18N
        DTPCariB.setOpaque(false);
        DTPCariB.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCariB);

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
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 298));
        panelGlass10.setLayout(new java.awt.GridLayout(1, 2));

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 270));
        panelGlass7.setLayout(null);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Pasien :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass7.add(jLabel5);
        jLabel5.setBounds(0, 10, 110, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        panelGlass7.add(TNoRw);
        TNoRw.setBounds(115, 10, 122, 23);

        TNoRm.setEditable(false);
        TNoRm.setForeground(new java.awt.Color(0, 0, 0));
        TNoRm.setName("TNoRm"); // NOI18N
        panelGlass7.add(TNoRm);
        TNoRm.setBounds(240, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        panelGlass7.add(TPasien);
        TPasien.setBounds(314, 10, 301, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Pemantauan :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass7.add(jLabel8);
        jLabel8.setBounds(0, 38, 110, 23);

        tglPantau.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-01-2024" }));
        tglPantau.setDisplayFormat("dd-MM-yyyy");
        tglPantau.setName("tglPantau"); // NOI18N
        tglPantau.setOpaque(false);
        tglPantau.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass7.add(tglPantau);
        tglPantau.setBounds(115, 38, 90, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Nadi :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass7.add(jLabel9);
        jLabel9.setBounds(0, 66, 110, 23);

        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        panelGlass7.add(Tnadi);
        Tnadi.setBounds(115, 66, 50, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Suhu :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass7.add(jLabel10);
        jLabel10.setBounds(170, 66, 40, 23);

        Tsuhu.setForeground(new java.awt.Color(0, 0, 0));
        Tsuhu.setToolTipText("Jika pakai koma ganti dengan titik, sebagai gantinya koma");
        Tsuhu.setName("Tsuhu"); // NOI18N
        Tsuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuKeyPressed(evt);
            }
        });
        panelGlass7.add(Tsuhu);
        Tsuhu.setBounds(216, 66, 50, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Jam :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelGlass7.add(jLabel11);
        jLabel11.setBounds(270, 66, 40, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "01", "02", "03", "04", "05" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        panelGlass7.add(cmbJam);
        cmbJam.setBounds(316, 66, 45, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("GCS :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass7.add(jLabel12);
        jLabel12.setBounds(0, 94, 110, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("E :");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass7.add(jLabel13);
        jLabel13.setBounds(115, 94, 15, 23);

        Tgcse.setForeground(new java.awt.Color(0, 0, 0));
        Tgcse.setName("Tgcse"); // NOI18N
        Tgcse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcseKeyPressed(evt);
            }
        });
        panelGlass7.add(Tgcse);
        Tgcse.setBounds(135, 94, 45, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("M :");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass7.add(jLabel14);
        jLabel14.setBounds(186, 94, 15, 23);

        Tgcsm.setForeground(new java.awt.Color(0, 0, 0));
        Tgcsm.setName("Tgcsm"); // NOI18N
        Tgcsm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsmKeyPressed(evt);
            }
        });
        panelGlass7.add(Tgcsm);
        Tgcsm.setBounds(206, 94, 45, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("V :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass7.add(jLabel15);
        jLabel15.setBounds(255, 94, 15, 23);

        Tgcsv.setForeground(new java.awt.Color(0, 0, 0));
        Tgcsv.setName("Tgcsv"); // NOI18N
        Tgcsv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsvKeyPressed(evt);
            }
        });
        panelGlass7.add(Tgcsv);
        Tgcsv.setBounds(274, 94, 45, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Kesadaran :");
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass7.add(jLabel16);
        jLabel16.setBounds(320, 94, 70, 23);

        Tkesadaran.setForeground(new java.awt.Color(0, 0, 0));
        Tkesadaran.setName("Tkesadaran"); // NOI18N
        Tkesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkesadaranKeyPressed(evt);
            }
        });
        panelGlass7.add(Tkesadaran);
        Tkesadaran.setBounds(395, 94, 190, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Tekanan Darah :");
        jLabel17.setName("jLabel17"); // NOI18N
        panelGlass7.add(jLabel17);
        jLabel17.setBounds(0, 122, 110, 23);

        Ttensi.setForeground(new java.awt.Color(0, 0, 0));
        Ttensi.setName("Ttensi"); // NOI18N
        Ttensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtensiKeyPressed(evt);
            }
        });
        panelGlass7.add(Ttensi);
        Ttensi.setBounds(115, 122, 70, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("mmHg");
        jLabel36.setName("jLabel36"); // NOI18N
        panelGlass7.add(jLabel36);
        jLabel36.setBounds(190, 122, 40, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Frek. Nafas / RR :");
        jLabel18.setName("jLabel18"); // NOI18N
        panelGlass7.add(jLabel18);
        jLabel18.setBounds(230, 122, 100, 23);

        Trr.setForeground(new java.awt.Color(0, 0, 0));
        Trr.setName("Trr"); // NOI18N
        Trr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrrKeyPressed(evt);
            }
        });
        panelGlass7.add(Trr);
        Trr.setBounds(335, 122, 70, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel40.setText("x/menit");
        jLabel40.setName("jLabel40"); // NOI18N
        panelGlass7.add(jLabel40);
        jLabel40.setBounds(410, 122, 50, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("SPO2 :");
        jLabel19.setName("jLabel19"); // NOI18N
        panelGlass7.add(jLabel19);
        jLabel19.setBounds(460, 122, 40, 23);

        Tspo.setForeground(new java.awt.Color(0, 0, 0));
        Tspo.setName("Tspo"); // NOI18N
        Tspo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TspoKeyPressed(evt);
            }
        });
        panelGlass7.add(Tspo);
        Tspo.setBounds(505, 122, 50, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("%");
        jLabel41.setName("jLabel41"); // NOI18N
        panelGlass7.add(jLabel41);
        jLabel41.setBounds(560, 122, 20, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("INTAKE :");
        jLabel20.setName("jLabel20"); // NOI18N
        panelGlass7.add(jLabel20);
        jLabel20.setBounds(0, 150, 110, 23);

        Tmm.setForeground(new java.awt.Color(0, 0, 0));
        Tmm.setName("Tmm"); // NOI18N
        Tmm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmmKeyPressed(evt);
            }
        });
        panelGlass7.add(Tmm);
        Tmm.setBounds(200, 150, 45, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Makan Minum :");
        jLabel37.setName("jLabel37"); // NOI18N
        panelGlass7.add(jLabel37);
        jLabel37.setBounds(115, 150, 80, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setText("cc.");
        jLabel38.setName("jLabel38"); // NOI18N
        panelGlass7.add(jLabel38);
        jLabel38.setBounds(250, 150, 20, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("NGT :");
        jLabel39.setName("jLabel39"); // NOI18N
        panelGlass7.add(jLabel39);
        jLabel39.setBounds(270, 150, 45, 23);

        Tngt.setForeground(new java.awt.Color(0, 0, 0));
        Tngt.setName("Tngt"); // NOI18N
        Tngt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TngtKeyPressed(evt);
            }
        });
        panelGlass7.add(Tngt);
        Tngt.setBounds(320, 150, 45, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("cc.");
        jLabel42.setName("jLabel42"); // NOI18N
        panelGlass7.add(jLabel42);
        jLabel42.setBounds(370, 150, 20, 23);

        Tline.setEditable(false);
        Tline.setForeground(new java.awt.Color(0, 0, 0));
        Tline.setName("Tline"); // NOI18N
        panelGlass7.add(Tline);
        Tline.setBounds(505, 150, 75, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("cc.");
        jLabel44.setName("jLabel44"); // NOI18N
        panelGlass7.add(jLabel44);
        jLabel44.setBounds(585, 150, 20, 23);

        Ttotintake.setEditable(false);
        Ttotintake.setForeground(new java.awt.Color(0, 0, 0));
        Ttotintake.setName("Ttotintake"); // NOI18N
        panelGlass7.add(Ttotintake);
        Ttotintake.setBounds(115, 178, 80, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("cc.");
        jLabel45.setName("jLabel45"); // NOI18N
        panelGlass7.add(jLabel45);
        jLabel45.setBounds(200, 178, 20, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("OUTPUT :");
        jLabel22.setName("jLabel22"); // NOI18N
        panelGlass7.add(jLabel22);
        jLabel22.setBounds(0, 206, 110, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Urine :");
        jLabel46.setName("jLabel46"); // NOI18N
        panelGlass7.add(jLabel46);
        jLabel46.setBounds(115, 206, 40, 23);

        Turin.setForeground(new java.awt.Color(0, 0, 0));
        Turin.setName("Turin"); // NOI18N
        Turin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TurinKeyPressed(evt);
            }
        });
        panelGlass7.add(Turin);
        Turin.setBounds(160, 206, 45, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("cc.");
        jLabel47.setName("jLabel47"); // NOI18N
        panelGlass7.add(jLabel47);
        jLabel47.setBounds(210, 206, 20, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("NGT / Darah :");
        jLabel48.setName("jLabel48"); // NOI18N
        panelGlass7.add(jLabel48);
        jLabel48.setBounds(235, 206, 80, 23);

        TngtDarah.setForeground(new java.awt.Color(0, 0, 0));
        TngtDarah.setName("TngtDarah"); // NOI18N
        TngtDarah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TngtDarahKeyPressed(evt);
            }
        });
        panelGlass7.add(TngtDarah);
        TngtDarah.setBounds(320, 206, 45, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel49.setText("cc.");
        jLabel49.setName("jLabel49"); // NOI18N
        panelGlass7.add(jLabel49);
        jLabel49.setBounds(370, 206, 20, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Drain :");
        jLabel50.setName("jLabel50"); // NOI18N
        panelGlass7.add(jLabel50);
        jLabel50.setBounds(390, 206, 62, 23);

        Tdrain.setForeground(new java.awt.Color(0, 0, 0));
        Tdrain.setName("Tdrain"); // NOI18N
        Tdrain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdrainKeyPressed(evt);
            }
        });
        panelGlass7.add(Tdrain);
        Tdrain.setBounds(456, 206, 45, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("cc.");
        jLabel51.setName("jLabel51"); // NOI18N
        panelGlass7.add(jLabel51);
        jLabel51.setBounds(508, 206, 20, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Muntah :");
        jLabel52.setName("jLabel52"); // NOI18N
        panelGlass7.add(jLabel52);
        jLabel52.setBounds(95, 234, 60, 23);

        Tmuntah.setForeground(new java.awt.Color(0, 0, 0));
        Tmuntah.setName("Tmuntah"); // NOI18N
        Tmuntah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmuntahKeyPressed(evt);
            }
        });
        panelGlass7.add(Tmuntah);
        Tmuntah.setBounds(160, 234, 45, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("cc.");
        jLabel53.setName("jLabel53"); // NOI18N
        panelGlass7.add(jLabel53);
        jLabel53.setBounds(210, 234, 20, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("BAB :");
        jLabel54.setName("jLabel54"); // NOI18N
        panelGlass7.add(jLabel54);
        jLabel54.setBounds(235, 234, 80, 23);

        Tbab.setForeground(new java.awt.Color(0, 0, 0));
        Tbab.setName("Tbab"); // NOI18N
        Tbab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbabKeyPressed(evt);
            }
        });
        panelGlass7.add(Tbab);
        Tbab.setBounds(320, 234, 45, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel55.setText("cc.");
        jLabel55.setName("jLabel55"); // NOI18N
        panelGlass7.add(jLabel55);
        jLabel55.setBounds(370, 234, 20, 23);

        Tiwl.setEditable(false);
        Tiwl.setForeground(new java.awt.Color(0, 0, 0));
        Tiwl.setName("Tiwl"); // NOI18N
        panelGlass7.add(Tiwl);
        Tiwl.setBounds(456, 234, 45, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("cc.");
        jLabel57.setName("jLabel57"); // NOI18N
        panelGlass7.add(jLabel57);
        jLabel57.setBounds(508, 234, 20, 23);

        Ttotouput.setEditable(false);
        Ttotouput.setForeground(new java.awt.Color(0, 0, 0));
        Ttotouput.setName("Ttotouput"); // NOI18N
        panelGlass7.add(Ttotouput);
        Ttotouput.setBounds(160, 262, 80, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel58.setText("cc.");
        jLabel58.setName("jLabel58"); // NOI18N
        panelGlass7.add(jLabel58);
        jLabel58.setBounds(245, 262, 20, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("BALANCE :");
        jLabel59.setName("jLabel59"); // NOI18N
        panelGlass7.add(jLabel59);
        jLabel59.setBounds(390, 262, 62, 23);

        Tbalance.setEditable(false);
        Tbalance.setForeground(new java.awt.Color(0, 0, 0));
        Tbalance.setName("Tbalance"); // NOI18N
        panelGlass7.add(Tbalance);
        Tbalance.setBounds(456, 262, 60, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel60.setText("cc.");
        jLabel60.setName("jLabel60"); // NOI18N
        panelGlass7.add(jLabel60);
        jLabel60.setBounds(522, 262, 20, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("BB Masuk RS :");
        jLabel24.setName("jLabel24"); // NOI18N
        panelGlass7.add(jLabel24);
        jLabel24.setBounds(360, 66, 90, 23);

        Tbb.setForeground(new java.awt.Color(0, 0, 0));
        Tbb.setToolTipText("Jika pakai koma ganti dengan titik, sebagai gantinya koma");
        Tbb.setName("Tbb"); // NOI18N
        Tbb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbKeyPressed(evt);
            }
        });
        panelGlass7.add(Tbb);
        Tbb.setBounds(456, 66, 50, 23);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel61.setText("Kg.");
        jLabel61.setName("jLabel61"); // NOI18N
        panelGlass7.add(jLabel61);
        jLabel61.setBounds(512, 66, 30, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Kode Pemantauan :");
        jLabel62.setName("jLabel62"); // NOI18N
        panelGlass7.add(jLabel62);
        jLabel62.setBounds(200, 38, 110, 23);

        TkdPantau.setEditable(false);
        TkdPantau.setForeground(new java.awt.Color(0, 0, 0));
        TkdPantau.setName("TkdPantau"); // NOI18N
        panelGlass7.add(TkdPantau);
        TkdPantau.setBounds(314, 38, 140, 23);

        BtnTotOutput.setForeground(new java.awt.Color(0, 0, 0));
        BtnTotOutput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnTotOutput.setText("TOTAL OUTPUT :");
        BtnTotOutput.setToolTipText("");
        BtnTotOutput.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnTotOutput.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BtnTotOutput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnTotOutput.setName("BtnTotOutput"); // NOI18N
        BtnTotOutput.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnTotOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTotOutputActionPerformed(evt);
            }
        });
        BtnTotOutput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnTotOutputKeyPressed(evt);
            }
        });
        panelGlass7.add(BtnTotOutput);
        BtnTotOutput.setBounds(25, 262, 130, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Jml. Parental / Line :");
        jLabel26.setName("jLabel26"); // NOI18N
        panelGlass7.add(jLabel26);
        jLabel26.setBounds(390, 150, 110, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("IWL :");
        jLabel56.setName("jLabel56"); // NOI18N
        panelGlass7.add(jLabel56);
        jLabel56.setBounds(390, 234, 62, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("TOTAL INTAKE :");
        jLabel27.setName("jLabel27"); // NOI18N
        panelGlass7.add(jLabel27);
        jLabel27.setBounds(0, 178, 110, 23);

        panelGlass10.add(panelGlass7);

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " [ Parental / Line / Obat-obatan ] ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbParental.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbParental.setComponentPopupMenu(jPopupMenu1);
        tbParental.setName("tbParental"); // NOI18N
        tbParental.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbParentalMouseClicked(evt);
            }
        });
        Scroll1.setViewportView(tbParental);

        panelGlass10.add(Scroll1);

        internalFrame1.add(panelGlass10, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (Tnadi.getText().trim().equals("")) {
            Valid.textKosong(Tnadi, "Nadi");
            Tnadi.requestFocus();
        } else if (Tsuhu.getText().trim().equals("")) {
            Valid.textKosong(Tsuhu, "Suhu");
            Tsuhu.requestFocus();
        } else {
            if (Tsuhu.getText().contains(",") == true) {
                Tsuhu.setText(Tsuhu.getText().replaceAll(",", "."));
            }

            autoNomorPerJam();
            jamDiurutkan();
            BtnTotOutputActionPerformed(null);
            if (Sequel.menyimpantf("pemantauan_harian_24jam", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat, Tgl. Pantau & Jam", 27, new String[]{
                TNoRw.getText(), TkdPantau.getText(), Valid.SetTgl(tglPantau.getSelectedItem() + ""), cmbJam.getSelectedItem().toString(),
                urutanJam, Tnadi.getText(), Tsuhu.getText(), Tgcse.getText(), Tgcsm.getText(), Tgcsv.getText(), Tkesadaran.getText(), Ttensi.getText(),
                Trr.getText(), Tspo.getText(), Tmm.getText(), Tngt.getText(), Tline.getText(), Ttotintake.getText(), Turin.getText(), TngtDarah.getText(),
                Tdrain.getText(), Tmuntah.getText(), Tbab.getText(), Tiwl.getText(), Ttotouput.getText(), Tbalance.getText(), Sequel.cariIsi("select now()")
            }) == true) {
              
                //simpan Parental / Line / Obat-obatan
                if (tbParental.getRowCount() != 0) {
                    for (i = 0; i < tbParental.getRowCount(); i++) {
                        Sequel.menyimpan2("pemantauan_harian_parental", "?,?,?,?,?,?,?", 7, new String[]{
                            TNoRw.getText(), TkdPantau.getText(), Valid.SetTgl(tglPantau.getSelectedItem() + ""), cmbJam.getSelectedItem().toString(),
                            tbParental.getValueAt(i, 1).toString(), tbParental.getValueAt(i, 2).toString(), "24 Jam"
                        });
                    }
                }

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
            tampil();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnEdit);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
//        if (kode.getText().trim().equals("")) {
//            Valid.textKosong(kode, "Kode");
//        } else if (nmkasus.getText().trim().equals("")) {
//            Valid.textKosong(nmkasus, "Nama Kasus Persalinan");
//            nmkasus.requestFocus();
//        } else {
//            if (tbPantau.getSelectedRow() > -1) {
//                Sequel.mengedit("master_kasus_persalinan_dinkes", "kode=?", "kode=?,nama_kasus=?", 3, new String[]{
//                    kode.getText(), nmkasus.getText(), tbPantau.getValueAt(tbPantau.getSelectedRow(), 0).toString()
//                });
//                if (tabMode.getRowCount() != 0) {
//                    tampil();
//                }
//                emptTeks();
//            }
//        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        emptTeks();
        dispose();
        WindowParental.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbPantau.requestFocus();
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
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbPantauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPantauMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPantauMouseClicked

    private void tbPantauKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPantauKeyPressed
        if (tabMode.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCari.setText("");
                TCari.requestFocus();
            }
        }
}//GEN-LAST:event_tbPantauKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        emptTeks();
    }//GEN-LAST:event_formWindowOpened

    private void tbPantauKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPantauKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }          
        }
    }//GEN-LAST:event_tbPantauKeyReleased

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        Valid.pindah(evt, tglPantau, Tsuhu);
    }//GEN-LAST:event_TnadiKeyPressed

    private void TsuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuhuKeyPressed
        Valid.pindah(evt, Tnadi, cmbJam);
    }//GEN-LAST:event_TsuhuKeyPressed

    private void TbbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbKeyPressed
        Valid.pindah(evt, cmbJam, Tgcse);
    }//GEN-LAST:event_TbbKeyPressed

    private void TgcseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcseKeyPressed
        Valid.pindah(evt, Tbb, Tgcsm);
    }//GEN-LAST:event_TgcseKeyPressed

    private void TgcsmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsmKeyPressed
        Valid.pindah(evt, Tgcse, Tgcsv);
    }//GEN-LAST:event_TgcsmKeyPressed

    private void TgcsvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsvKeyPressed
        Valid.pindah(evt, Tgcsm, Tkesadaran);
    }//GEN-LAST:event_TgcsvKeyPressed

    private void TkesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkesadaranKeyPressed
        Valid.pindah(evt, Tgcsv, Ttensi);
    }//GEN-LAST:event_TkesadaranKeyPressed

    private void TtensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtensiKeyPressed
        Valid.pindah(evt, Tkesadaran, Trr);
    }//GEN-LAST:event_TtensiKeyPressed

    private void TrrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrrKeyPressed
        Valid.pindah(evt, Ttensi, Tspo);
    }//GEN-LAST:event_TrrKeyPressed

    private void TspoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TspoKeyPressed
        Valid.pindah(evt, Trr, Tmm);
    }//GEN-LAST:event_TspoKeyPressed

    private void TmmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmmKeyPressed
        Valid.pindah(evt, Tspo, Tngt);
    }//GEN-LAST:event_TmmKeyPressed

    private void TngtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TngtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            hitungParental();
            BtnTotOutputActionPerformed(null);
            Turin.requestFocus();
        }
    }//GEN-LAST:event_TngtKeyPressed

    private void TurinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TurinKeyPressed
        Valid.pindah(evt, Tngt, TngtDarah);
    }//GEN-LAST:event_TurinKeyPressed

    private void TngtDarahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TngtDarahKeyPressed
        Valid.pindah(evt, Turin, Tdrain);
    }//GEN-LAST:event_TngtDarahKeyPressed

    private void TdrainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdrainKeyPressed
        Valid.pindah(evt, TngtDarah, Tmuntah);
    }//GEN-LAST:event_TdrainKeyPressed

    private void TmuntahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmuntahKeyPressed
        Valid.pindah(evt, Tdrain, Tbab);
    }//GEN-LAST:event_TmuntahKeyPressed

    private void TbabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbabKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            hitungIWL();
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_TbabKeyPressed

    private void tbParentalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbParentalMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                dataParental = "";
                dataParental = tbParental.getValueAt(tbParental.getSelectedRow(),3).toString();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbParentalMouseClicked

    private void BtnTotOutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTotOutputActionPerformed
        hitungParental();
        hitungIntake();
        hitungIWL();
        hitungOutput();
        hitungBalance();
    }//GEN-LAST:event_BtnTotOutputActionPerformed

    private void BtnTotOutputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnTotOutputKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnTotOutputActionPerformed(null);
        }
    }//GEN-LAST:event_BtnTotOutputKeyPressed

    private void BtnParentalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnParentalActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasienya...!!!!");
        } else {
            WindowParental.setSize(820, internalFrame1.getHeight() - 40);
            WindowParental.setLocationRelativeTo(internalFrame1);
            WindowParental.setAlwaysOnTop(false);
            WindowParental.setVisible(true);
            
            BtnBatal1ActionPerformed(null);
            DTPCari1.setDate(new Date());
            DTPCari2.setDate(new Date());
            tampilObatTerjadwal();
        }
    }//GEN-LAST:event_BtnParentalActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbPantau.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from pemantauan_harian_24jam where waktu_simpan=?", 1, new String[]{
                    tbPantau.getValueAt(tbPantau.getSelectedRow(), 30).toString()
                }) == true) {
                    Sequel.meghapus("pemantauan_harian_parental", "kd_pantau",
                            tbPantau.getValueAt(tbPantau.getSelectedRow(), 1).toString());

                    tampil();
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                TnmObat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
                TjmlBeri.requestFocus();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbObatKeyPressed

    private void tbObatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbObatKeyReleased

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilObatTerjadwal();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        if (TnmObat.getText().trim().equals("")) {
            Valid.textKosong(TnmObat, "Nama Obat Dipilih");
        } else if (TjmlBeri.getText().trim().equals("")) {
            Valid.textKosong(TjmlBeri, "Jml. Pemberian");
        } else {
            tabMode1.addRow(new String[]{
                tglPantau.getSelectedItem().toString(), TnmObat.getText(), TjmlBeri.getText(), TkdPantau.getText(),
                Valid.SetTgl(tglPantau.getSelectedItem() + "")});
            hitungParental();
            BtnBatal1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnBatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatal1ActionPerformed
        TnmObat.setText("");
        TjmlBeri.setText("");
        TnmObat.requestFocus();
    }//GEN-LAST:event_BtnBatal1ActionPerformed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        WindowParental.dispose();
        BtnBatal1ActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void TnmObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmObatKeyPressed
        Valid.pindah(evt,TnmObat,TjmlBeri);
    }//GEN-LAST:event_TnmObatKeyPressed

    private void TjmlBeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjmlBeriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpan1ActionPerformed(null);
            BtnSimpan1.requestFocus();
        }
    }//GEN-LAST:event_TjmlBeriKeyPressed

    private void MnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data Parental/Line/Obat-obatan belum ada...!!");
            BtnParental.requestFocus();
        } else if (tbParental.getSelectedRowCount() == 0 || dataParental.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel...!!!!");
            tbParental.requestFocus();
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tabMode1.removeRow(tbParental.getSelectedRow());
                dataParental = "";
                hitungParental();
            } else {
                dataParental = "";
            }
        }
    }//GEN-LAST:event_MnHapusActionPerformed

    private void MnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGantiActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data Parental/Line/Obat-obatan belum ada...!!");
            BtnParental.requestFocus();
        } else if (tbParental.getSelectedRowCount() == 0 || dataParental.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel...!!!!");
            tbParental.requestFocus();
        } else {
            tabMode1.removeRow(tbParental.getSelectedRow());
            dataParental = "";
            WindowParental.setSize(820, internalFrame1.getHeight() - 40);
            WindowParental.setLocationRelativeTo(internalFrame1);
            WindowParental.setAlwaysOnTop(false);
            WindowParental.setVisible(true);
            
            BtnBatal1ActionPerformed(null);
            DTPCari1.setDate(new Date());
            DTPCari2.setDate(new Date());
            tampilObatTerjadwal();
        }
    }//GEN-LAST:event_MnGantiActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPemantauanHarian24Jam dialog = new RMPemantauanHarian24Jam(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal1;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnParental;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Button BtnTotOutput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCariA;
    private widget.Tanggal DTPCariB;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnGanti;
    private javax.swing.JMenuItem MnHapus;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    public widget.TextBox TCari;
    private widget.TextBox TNoRm;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Tbab;
    private widget.TextBox Tbalance;
    private widget.TextBox Tbb;
    private widget.TextBox Tdrain;
    private widget.TextBox Tgcse;
    private widget.TextBox Tgcsm;
    private widget.TextBox Tgcsv;
    private widget.TextBox Tiwl;
    private widget.TextBox TjmlBeri;
    private widget.TextBox TkdPantau;
    private widget.TextBox Tkesadaran;
    private widget.TextBox Tline;
    private widget.TextBox Tmm;
    private widget.TextBox Tmuntah;
    private widget.TextBox Tnadi;
    private widget.TextBox Tngt;
    private widget.TextBox TngtDarah;
    private widget.TextBox TnmObat;
    private widget.TextBox Trr;
    private widget.TextBox Tspo;
    private widget.TextBox Tsuhu;
    private widget.TextBox Ttensi;
    private widget.TextBox Ttotintake;
    private widget.TextBox Ttotouput;
    private widget.TextBox Turin;
    private javax.swing.JDialog WindowParental;
    private widget.ComboBox cmbJam;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame11;
    private widget.InternalFrame internalFrame12;
    private widget.InternalFrame internalFrame13;
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
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel5;
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
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbObat;
    private widget.Table tbPantau;
    private widget.Table tbParental;
    private widget.Tanggal tglPantau;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select ph.no_rawat, ph.kd_pantau, p.no_rkm_medis, p.nm_pasien, date_format(ph.tgl_pantau,'%d-%m-%Y') tglPantau, "
                    + "ph.jam, ph.nadi, ph.suhu, concat('E : ',ph.gcs_e,', M : ',ph.gcs_m,', V : ',ph.gcs_v) gcs, ph.kesadaran, ph.td, ph.nafas, ph.spo2, "
                    + "ph.makan_minum, ph.ngt, ph.total_parental, ph.total_intake, ph.urine, ph.ngt_darah, ph.drain, ph.muntah, ph.bab, ph.iwl, ph.total_output, "
                    + "ph.balance, ph.gcs_e, ph.gcs_m, ph.gcs_v, ph.tgl_pantau, ph.urutan_jam, ph.waktu_simpan from pemantauan_harian_24jam ph "
                    + "inner join reg_periksa rp on rp.no_rawat=ph.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                    + "ph.tgl_pantau between ? and ? and ph.no_rawat like ? or "
                    + "ph.tgl_pantau between ? and ? and ph.kd_pantau like ? or "
                    + "ph.tgl_pantau between ? and ? and p.no_rkm_medis like ? or "
                    + "ph.tgl_pantau between ? and ? and p.nm_pasien like ? order by ph.tgl_pantau, ph.urutan_jam");
            try {
                ps.setString(1, Valid.SetTgl(DTPCariA.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCariB.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, Valid.SetTgl(DTPCariA.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(DTPCariB.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, Valid.SetTgl(DTPCariA.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(DTPCariB.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, Valid.SetTgl(DTPCariA.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(DTPCariB.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("kd_pantau"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tglPantau"),
                        rs.getString("jam"),
                        rs.getString("nadi"),
                        rs.getString("suhu"),
                        rs.getString("gcs"),
                        rs.getString("kesadaran"),
                        rs.getString("td"),
                        rs.getString("nafas"),
                        rs.getString("spo2"),
                        rs.getString("makan_minum"),
                        rs.getString("ngt"),
                        rs.getString("total_parental"),
                        rs.getString("total_intake"),
                        rs.getString("urine"),
                        rs.getString("ngt_darah"),
                        rs.getString("drain"),
                        rs.getString("muntah"),
                        rs.getString("bab"),
                        rs.getString("iwl"),
                        rs.getString("total_output"),
                        rs.getString("balance"),
                        rs.getString("gcs_e"),
                        rs.getString("gcs_m"),
                        rs.getString("gcs_v"),
                        rs.getString("tgl_pantau"),
                        rs.getString("urutan_jam"),
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
        tglPantau.setDate(new Date());
        tglPantau.requestFocus();
        Tnadi.setText("");        
        Tsuhu.setText("");
        cmbJam.setSelectedIndex(0);
        Tgcse.setText("");
        Tgcsm.setText("");
        Tgcsv.setText("");
        Tkesadaran.setText("");
        Ttensi.setText("");
        Trr.setText("");
        Tspo.setText("");
        Tmm.setText("");
        Tngt.setText("");
        Tline.setText("");
        Ttotintake.setText("");
        Turin.setText("");
        TngtDarah.setText("");
        Tdrain.setText("");
        Tmuntah.setText("");
        Tbab.setText("");
        Tiwl.setText("");
        Ttotouput.setText("");
        Tbalance.setText("");
        urutanJam = "";
        dataParental = "";
        wktSimpanPantau = "";
        Valid.tabelKosong(tabMode1);
        autoNomorPerJam();
    }

    private void getData() {
        wktSimpanPantau = "";
        if (tbPantau.getSelectedRow() != -1) {
            TNoRw.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),0).toString());
            TkdPantau.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),1).toString());
            TNoRm.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),2).toString());
            TPasien.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),3).toString());
            Valid.SetTgl(tglPantau, tbPantau.getValueAt(tbPantau.getSelectedRow(),28).toString());
            cmbJam.setSelectedItem(tbPantau.getValueAt(tbPantau.getSelectedRow(),5).toString());
            Tnadi.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),6).toString());
            Tsuhu.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),7).toString());
            Tbb.setText(Sequel.cariIsi("select ifnull(bb_msk_rs,'0') from penilaian_awal_keperawatan_dewasa_ranap where no_rawat='" + TNoRw.getText() + "'"));
            Tgcse.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),25).toString());
            Tgcsm.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),26).toString());
            Tgcsv.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),27).toString());
            Tkesadaran.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),9).toString());
            Ttensi.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),10).toString());
            Trr.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),11).toString());
            Tspo.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),12).toString());
            Tmm.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),13).toString());
            Tngt.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),14).toString());
            Tline.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),15).toString());
            Ttotintake.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),16).toString());
            Turin.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),17).toString());
            TngtDarah.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),18).toString());
            Tdrain.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),19).toString());
            Tmuntah.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),20).toString());
            Tbab.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),21).toString());
            Tiwl.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),22).toString());
            Ttotouput.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),23).toString());
            Tbalance.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),24).toString());
            wktSimpanPantau = tbPantau.getValueAt(tbPantau.getSelectedRow(),30).toString();            
            tampilParental();
        }
    }
    
    public void isCek(){
       BtnSimpan.setEnabled(akses.getcppt());       
       BtnEdit.setEnabled(akses.getcppt());
       BtnHapus.setEnabled(akses.getcppt());
    }
    
    private void hitungIWL() {
        try {
            double bb, Total_kali, Total_iwl;
            Total_kali = 0;
            Total_iwl = 0;
            if (Tbb.getText().equals("")) {
                Tbb.setText("0");
            }
            
            if (Tbb.getText().contains(",") == true) {
                Tbb.setText(Tbb.getText().replaceAll(",", "."));
            }

            bb = Double.parseDouble(Tbb.getText());
            Total_kali = bb * 15;
            Total_iwl = Total_kali / 24;

            if (Valid.SetAngka2(Total_iwl).equals("NaN")) {
                Tiwl.setText("0");
            } else {
                Tiwl.setText(Valid.SetAngka2(Total_iwl));
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(rootPane, "Silahkan koreksi lagi angka BB masuk RS,    \n"
                    + "jika menggunakan koma, gantilah tanda koma dengan titik sebagai komanya !!");
        }
    }
    
    private void hitungIntake() {
        try {
            double A, B, C, Total;
            Total = 0;
            A = 0;
            B = 0;
            C = 0;
            if (Tmm.getText().equals("")) {
                Tmm.setText("0");
            }
            
            if (Tngt.getText().equals("")) {
                Tngt.setText("0");
            }
            
            if (Tline.getText().equals("")) {
                Tline.setText("0");
            }

            A = Double.parseDouble(Tmm.getText());
            B = Double.parseDouble(Tngt.getText());
            C = Double.parseDouble(Tline.getText());
            Total = A + B + C;

            if (Valid.SetAngka2(Total).equals("NaN")) {
                Ttotintake.setText("0");
            } else {
                Ttotintake.setText(Valid.SetAngka2(Total));
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void hitungOutput() {
        try {
            double A, B, C, D, E, F, Total;
            Total = 0;
            A = 0;
            B = 0;
            C = 0;
            D = 0;
            E = 0;
            F = 0;
            if (Turin.getText().equals("")) {
                Turin.setText("0");
            }
            
            if (TngtDarah.getText().equals("")) {
                TngtDarah.setText("0");
            }
            
            if (Tdrain.getText().equals("")) {
                Tdrain.setText("0");
            }
            
            if (Tmuntah.getText().equals("")) {
                Tmuntah.setText("0");
            }
            
            if (Tbab.getText().equals("")) {
                Tbab.setText("0");
            }
            
            if (Tiwl.getText().equals("")) {
                Tiwl.setText("0");
            }

            A = Double.parseDouble(Turin.getText());
            B = Double.parseDouble(TngtDarah.getText());
            C = Double.parseDouble(Tdrain.getText());            
            D = Double.parseDouble(Tmuntah.getText());
            E = Double.parseDouble(Tbab.getText());
            F = Double.parseDouble(Tiwl.getText());
            Total = A + B + C + D + E + F;

            if (Valid.SetAngka2(Total).equals("NaN")) {
                Ttotouput.setText("0");
            } else {
                Ttotouput.setText(Valid.SetAngka2(Total));
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void hitungBalance() {
        try {
            double A, B, Total;
            Total = 0;
            A = 0;
            B = 0;
            if (Ttotintake.getText().equals("")) {
                Ttotintake.setText("0");
            }
            
            if (Ttotouput.getText().equals("")) {
                Ttotouput.setText("0");
            }

            A = Double.parseDouble(Ttotintake.getText());
            B = Double.parseDouble(Ttotouput.getText());
            Total = A - B;

            if (Valid.SetAngka2(Total).equals("NaN")) {
                Tbalance.setText("0");
            } else {
                Tbalance.setText(Valid.SetAngka2(Total));
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void autoNomorPerJam() {
        Valid.autoNomer7("select ifnull(MAX(CONVERT(LEFT(kd_pantau,4),signed)),0) from pemantauan_harian_24jam where "
                + "tgl_pantau like '%" + Valid.SetTgl(tglPantau.getSelectedItem() + "").substring(0, 7) + "%' ", "/24Jam/" + Valid.SetTgl(tglPantau.getSelectedItem() + "").substring(5, 7)
                + "/" + Valid.SetTgl(tglPantau.getSelectedItem() + "").substring(0, 4), 4, TkdPantau);
    }
    
    private void jamDiurutkan() {
        urutanJam = "";
        if (cmbJam.getSelectedIndex() == 0) {
            urutanJam = "1";
        } else if (cmbJam.getSelectedIndex() == 1) {
            urutanJam = "2";
        } else if (cmbJam.getSelectedIndex() == 2) {
            urutanJam = "3";
        } else if (cmbJam.getSelectedIndex() == 3) {
            urutanJam = "4";
        } else if (cmbJam.getSelectedIndex() == 4) {
            urutanJam = "5";
        } else if (cmbJam.getSelectedIndex() == 5) {
            urutanJam = "6";
        } else if (cmbJam.getSelectedIndex() == 6) {
            urutanJam = "7";
        } else if (cmbJam.getSelectedIndex() == 7) {
            urutanJam = "8";
        } else if (cmbJam.getSelectedIndex() == 8) {
            urutanJam = "9";
        } else if (cmbJam.getSelectedIndex() == 9) {
            urutanJam = "10";
        } else if (cmbJam.getSelectedIndex() == 10) {
            urutanJam = "11";
        } else if (cmbJam.getSelectedIndex() == 11) {
            urutanJam = "12";
        } else if (cmbJam.getSelectedIndex() == 12) {
            urutanJam = "13";
        } else if (cmbJam.getSelectedIndex() == 13) {
            urutanJam = "14";
        } else if (cmbJam.getSelectedIndex() == 14) {
            urutanJam = "15";
        } else if (cmbJam.getSelectedIndex() == 15) {
            urutanJam = "16";
        } else if (cmbJam.getSelectedIndex() == 16) {
            urutanJam = "17";
        } else if (cmbJam.getSelectedIndex() == 17) {
            urutanJam = "18";
        } else if (cmbJam.getSelectedIndex() == 18) {
            urutanJam = "19";
        } else if (cmbJam.getSelectedIndex() == 19) {
            urutanJam = "20";
        } else if (cmbJam.getSelectedIndex() == 20) {
            urutanJam = "21";
        } else if (cmbJam.getSelectedIndex() == 21) {
            urutanJam = "22";
        } else if (cmbJam.getSelectedIndex() == 22) {
            urutanJam = "23";
        } else if (cmbJam.getSelectedIndex() == 23) {
            urutanJam = "24";
        } 
    }
    
    private void tampilObatTerjadwal() {
        Valid.tabelKosong(tabMode2);
        try {
            ps1 = koneksi.prepareStatement("SELECT *, date_format(tgl_pemberian,'%d-%m-%Y') tgl_beri FROM pemberian_obat where "
                    + "tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "and no_rawat='" + TNoRw.getText() + "' and nama_obat<>'-' order by waktu_simpan");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode2.addRow(new String[]{
                        rs1.getString("tgl_beri"),
                        rs1.getString("nama_obat"),
                        rs1.getString("jenis_obat"),
                        rs1.getString("jlh_obat"),
                        rs1.getString("dosis"),
                        rs1.getString("cara_pemberian")
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
    
    private void tampilParental() {
        Valid.tabelKosong(tabMode1);
        try {
            ps2 = koneksi.prepareStatement("select date_format(p.tgl_pantau,'%d-%m-%Y') tglPantau, "
                    + "p.nm_obat, p.jml_pemberian, p.kd_pantau, p.tgl_pantau from pemantauan_harian_parental p "
                    + "inner join pemantauan_harian_24jam ph on ph.kd_pantau=p.kd_pantau where "
                    + "p.tgl_pantau='" + Valid.SetTgl(tglPantau.getSelectedItem() + "") + "' and p.kd_pantau='" + TkdPantau.getText() + "'");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode1.addRow(new String[]{
                        rs2.getString("tglPantau"),
                        rs2.getString("nm_obat"),
                        rs2.getString("jml_pemberian"),
                        rs2.getString("kd_pantau"),
                        rs2.getString("tgl_pantau")
                    });
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
    
    public void setData(String norw) {
        TNoRw.setText(norw);
        TNoRm.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + norw + "'"));
        TPasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRm.getText() + "'"));
        Tbb.setText(Sequel.cariIsi("select ifnull(bb_msk_rs,'0') from penilaian_awal_keperawatan_dewasa_ranap where no_rawat='" + norw + "'"));
    }
    
    private void hitungParental() {
        totParental = 0;
        for (i = 0; i < tbParental.getRowCount(); i++) {
            totParental = totParental + Integer.parseInt(tbParental.getValueAt(i, 2).toString());
        }
        
        Tline.setText(Valid.SetAngka2(totParental));
    }
}
