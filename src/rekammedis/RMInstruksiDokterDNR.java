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
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.DlgCariDokter;
import simrskhanza.frmUtama;

/**
 *
 * @author dosen
 */
public class RMInstruksiDokterDNR extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String nipDpjp = "", cekPasien = "", cekNakes = "", cekWali = "", cekAnggota = "", cekInstruksi = "", cekKeputusan = "";
    private frmUtama formUtama;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMInstruksiDokterDNR(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Jns. Kelamin", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Menyatakan", "Nama DPJP",
            "cek_pasien", "cek_nakes", "cek_wali", "cek_anggota_klg", "cek_instruksi", "cek_keputusan", "nip_dpjp", "no_telp_dpjp",
            "tgl_menyatakan", "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbInstruksi.setModel(tabMode);
        tbInstruksi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbInstruksi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 18; i++) {
            TableColumn column = tbInstruksi.getColumnModel().getColumn(i);
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
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(90);
            } else if (i == 7) {
                column.setPreferredWidth(250);
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
            }
        }
        tbInstruksi.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbInstruksi.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbInstruksi.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbInstruksi.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbInstruksi.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        
        TnoTelp.setDocument(new batasInput((byte) 13).getOnlyAngka(TnoTelp));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));        
        
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMInstruksiDokterDNR")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        nipDpjp = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                        TnmDpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDpjp.requestFocus();
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
        FormInput = new widget.PanelBiasa();
        jLabel10 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel12 = new widget.Label();
        TtglMenyatakan = new widget.Tanggal();
        jLabel69 = new widget.Label();
        TnmDpjp = new widget.TextBox();
        BtnDpjp = new widget.Button();
        jLabel70 = new widget.Label();
        TrgRawat = new widget.TextBox();
        chkPasien = new widget.CekBox();
        chkNakes = new widget.CekBox();
        chkWali = new widget.CekBox();
        chkAnggota = new widget.CekBox();
        jLabel13 = new widget.Label();
        chkInstruksi = new widget.CekBox();
        chkKeputusan = new widget.CekBox();
        jLabel72 = new widget.Label();
        TnoTelp = new widget.TextBox();
        jLabel73 = new widget.Label();
        Scroll = new widget.ScrollPane();
        tbInstruksi = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        jLabel71 = new widget.Label();
        cmbPilihCetak = new widget.ComboBox();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel9 = new widget.Label();
        LCount = new widget.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "<html>::[ Instruksi Dokter Untuk DNR (<i>Do Not Resucitate</i>) ]::</html>", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 325));
        FormInput.setLayout(null);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 110, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(114, 10, 131, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(247, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(319, 10, 410, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("<html><div style=\"text-align: left;\">Saya dokter yang bertanda tangan di bawah ini menyatakan bahwa keputusan DNR diatas diambil setelah pasien diberikan penjelsan dan <i>informed consent</i> diperoleh dari salah satu :</div></html>");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(115, 66, 630, 30);

        TtglMenyatakan.setEditable(false);
        TtglMenyatakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-08-2026" }));
        TtglMenyatakan.setDisplayFormat("dd-MM-yyyy");
        TtglMenyatakan.setName("TtglMenyatakan"); // NOI18N
        TtglMenyatakan.setOpaque(false);
        TtglMenyatakan.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglMenyatakan);
        TtglMenyatakan.setBounds(390, 283, 90, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Nama DPJP :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 255, 110, 23);

        TnmDpjp.setEditable(false);
        TnmDpjp.setForeground(new java.awt.Color(0, 0, 0));
        TnmDpjp.setToolTipText("Alt+C");
        TnmDpjp.setName("TnmDpjp"); // NOI18N
        TnmDpjp.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(TnmDpjp);
        TnmDpjp.setBounds(115, 255, 430, 23);

        BtnDpjp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDpjp.setMnemonic('2');
        BtnDpjp.setToolTipText("Alt+2");
        BtnDpjp.setName("BtnDpjp"); // NOI18N
        BtnDpjp.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDpjp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDpjpActionPerformed(evt);
            }
        });
        FormInput.add(BtnDpjp);
        BtnDpjp.setBounds(550, 255, 28, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Ruang Rawat : ");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 38, 110, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        TrgRawat.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(115, 38, 430, 23);

        chkPasien.setBackground(new java.awt.Color(242, 242, 242));
        chkPasien.setForeground(new java.awt.Color(0, 0, 0));
        chkPasien.setText("Pasien");
        chkPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPasien.setName("chkPasien"); // NOI18N
        chkPasien.setOpaque(false);
        chkPasien.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkPasien);
        chkPasien.setBounds(115, 101, 70, 23);

        chkNakes.setBackground(new java.awt.Color(242, 242, 242));
        chkNakes.setForeground(new java.awt.Color(0, 0, 0));
        chkNakes.setText("Tenaga kesehatan yang ditunjuk pasien");
        chkNakes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNakes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNakes.setName("chkNakes"); // NOI18N
        chkNakes.setOpaque(false);
        chkNakes.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkNakes);
        chkNakes.setBounds(115, 129, 225, 23);

        chkWali.setBackground(new java.awt.Color(242, 242, 242));
        chkWali.setForeground(new java.awt.Color(0, 0, 0));
        chkWali.setText("Wali yang sah atas pasien (termasuk yang ditunjuk oleh pengadilan)");
        chkWali.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkWali.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkWali.setName("chkWali"); // NOI18N
        chkWali.setOpaque(false);
        chkWali.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkWali);
        chkWali.setBounds(350, 101, 360, 23);

        chkAnggota.setBackground(new java.awt.Color(242, 242, 242));
        chkAnggota.setForeground(new java.awt.Color(0, 0, 0));
        chkAnggota.setText("Anggota keluarga pasien");
        chkAnggota.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAnggota.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAnggota.setName("chkAnggota"); // NOI18N
        chkAnggota.setOpaque(false);
        chkAnggota.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkAnggota);
        chkAnggota.setBounds(350, 129, 160, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("<html><div style=\"text-align: left;\">Jika yang diatas tidak dimungkinkan maka dokter yang bertanda tangan di bawah ini memberikan perintah DNR berdasarkan pada :</div></html>");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(115, 157, 540, 30);

        chkInstruksi.setBackground(new java.awt.Color(242, 242, 242));
        chkInstruksi.setForeground(new java.awt.Color(0, 0, 0));
        chkInstruksi.setText("Instruksi pasien sebelumnya atau");
        chkInstruksi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkInstruksi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkInstruksi.setName("chkInstruksi"); // NOI18N
        chkInstruksi.setOpaque(false);
        chkInstruksi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkInstruksi);
        chkInstruksi.setBounds(115, 192, 200, 23);

        chkKeputusan.setBackground(new java.awt.Color(242, 242, 242));
        chkKeputusan.setForeground(new java.awt.Color(0, 0, 0));
        chkKeputusan.setText("<html>Keputusan dua orang dokter yang menyatakan bahwa Resusitasi Jantung Paru (RJP) akan mendatangkan hasil yang kurang efektif karena kondisi</html>");
        chkKeputusan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKeputusan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKeputusan.setName("chkKeputusan"); // NOI18N
        chkKeputusan.setOpaque(false);
        chkKeputusan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkKeputusan);
        chkKeputusan.setBounds(115, 220, 540, 30);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("No. Telpn./HP :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 283, 110, 23);

        TnoTelp.setForeground(new java.awt.Color(0, 0, 0));
        TnoTelp.setName("TnoTelp"); // NOI18N
        TnoTelp.setPreferredSize(new java.awt.Dimension(140, 23));
        TnoTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoTelpKeyPressed(evt);
            }
        });
        FormInput.add(TnoTelp);
        TnoTelp.setBounds(115, 283, 160, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Tgl. Menyatakan :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(275, 283, 110, 23);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbInstruksi.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki atau dihapus");
        tbInstruksi.setName("tbInstruksi"); // NOI18N
        tbInstruksi.getTableHeader().setReorderingAllowed(false);
        tbInstruksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbInstruksiMouseClicked(evt);
            }
        });
        tbInstruksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbInstruksiKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbInstruksi);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
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

        BtnGanti.setForeground(new java.awt.Color(0, 0, 0));
        BtnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti.setMnemonic('G');
        BtnGanti.setText("Ganti");
        BtnGanti.setToolTipText("Alt+G");
        BtnGanti.setName("BtnGanti"); // NOI18N
        BtnGanti.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiActionPerformed(evt);
            }
        });
        BtnGanti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGantiKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnGanti);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Cetak Dalam Bentuk :");
        jLabel71.setName("jLabel71"); // NOI18N
        jLabel71.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass8.add(jLabel71);

        cmbPilihCetak.setForeground(new java.awt.Color(0, 0, 0));
        cmbPilihCetak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TTE (QR Code)", "TTD Basah" }));
        cmbPilihCetak.setName("cmbPilihCetak"); // NOI18N
        cmbPilihCetak.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass8.add(cmbPilihCetak);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass10.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-08-2026" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-08-2026" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari2);

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
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
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

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Record :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel9);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            cekData();
            if (Sequel.menyimpantf("instruksi_dokter_untuk_dnr", "?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 12, new String[]{
                TNoRw.getText(), TrgRawat.getText(), cekPasien, cekNakes, cekWali, cekAnggota, cekInstruksi, cekKeputusan,
                nipDpjp, TnoTelp.getText(), Valid.SetTgl(TtglMenyatakan.getSelectedItem() + ""), Sequel.cariIsi("select now()")
            }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Instruksi Dokter Untuk DNR (Do Not Resucitate)", "Simpan");
                TCari.setText(TNoRw.getText());
                emptTeks();
                tampil();
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

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (tbInstruksi.getSelectedRow() > -1) {
                cekData();
                if (Sequel.mengedittf("instruksi_dokter_untuk_dnr", "waktu_simpan=?", "cek_pasien=?, cek_nakes=?, cek_wali=?, cek_anggota_klg=?, "
                        + "cek_instruksi=?, cek_keputusan=?, nip_dpjp=?, no_telp_dpjp=?, tgl_menyatakan=?", 10, new String[]{
                            cekPasien, cekNakes, cekWali, cekAnggota, cekInstruksi, cekKeputusan,
                            nipDpjp, TnoTelp.getText(), Valid.SetTgl(TtglMenyatakan.getSelectedItem() + ""),
                            tbInstruksi.getValueAt(tbInstruksi.getSelectedRow(), 17).toString()
                        }) == true) {

                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Instruksi Dokter Untuk DNR (Do Not Resucitate)", "Ganti");
                    TCari.setText(TNoRw.getText());
                    tampil();
                    emptTeks();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            }
        }
}//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnGantiActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
}//GEN-LAST:event_BtnGantiKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        frmUtama.getInstance().tutupDialogDiPanelUtama(this);
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            frmUtama.getInstance().tutupDialogDiPanelUtama(this);
        } else {
            Valid.pindah(evt, BtnBatal, TCari);
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
        BtnCariActionPerformed(null);
        emptTeks();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
            TCari.setText("");
        } 
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbInstruksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbInstruksiMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbInstruksiMouseClicked

    private void tbInstruksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbInstruksiKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbInstruksiKeyPressed

    private void BtnDpjpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDpjpActionPerformed
        akses.setform("RMInstruksiDokterDNR");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDpjpActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbInstruksi.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from instruksi_dokter_untuk_dnr where waktu_simpan=?", 1, new String[]{
                    tbInstruksi.getValueAt(tbInstruksi.getSelectedRow(), 17).toString()
                }) == true) {
                    TCari.setText(TNoRw.getText());
                    emptTeks();
                    tampil();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                TCari.setText(TNoRw.getText());
                emptTeks();
                tampil();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tbInstruksi.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnGanti);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void TnoTelpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoTelpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglMenyatakan.requestFocus();
        }
    }//GEN-LAST:event_TnoTelpKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbInstruksi.getSelectedRow() > -1) {
            String tglLahir = "";
            tglLahir = Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis='" + TNoRM.getText() + "'");

            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format('" + tglLahir + "','%d-%m-%Y')"));
            
            param.put("ttlPasien", Sequel.cariIsi("select tmp_lahir from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + ", " + Valid.SetTglINDONESIA(tglLahir));
            param.put("jenkel", tbInstruksi.getValueAt(tbInstruksi.getSelectedRow(), 3).toString());
            param.put("umur", Sequel.cariIsi("select concat(umurdaftar,' ',sttsumur,'.') from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
            
            if (chkPasien.isSelected() == true) {
                param.put("pasien", "V");
            } else {
                param.put("pasien", "");
            }
            
            if (chkNakes.isSelected() == true) {
                param.put("nakes", "V");
            } else {
                param.put("nakes", "");
            }
            
            if (chkWali.isSelected() == true) {
                param.put("wali", "V");
            } else {
                param.put("wali", "");
            }
            
            if (chkAnggota.isSelected() == true) {
                param.put("anggota", "V");
            } else {
                param.put("anggota", "");
            }
            
            if (chkInstruksi.isSelected() == true) {
                param.put("instruksi", "V");
            } else {
                param.put("instruksi", "");
            }
            
            if (chkKeputusan.isSelected() == true) {
                param.put("keputusan", "V");
            } else {
                param.put("keputusan", "");
            }
            
            param.put("nmDpjp", TnmDpjp.getText());
            
            if (TnoTelp.getText().equals("")) {
                param.put("noTelpn", "...............");
            } else {
                param.put("noTelpn", TnoTelp.getText());
            }

            param.put("tglMenyatakan", Valid.SetTglINDONESIA(Valid.SetTgl(TtglMenyatakan.getSelectedItem() + "")));            

            if (cmbPilihCetak.getSelectedIndex() == 0) {
                String isi = "";
                if (nipDpjp.equals("") || nipDpjp.equals("-") || nipDpjp.equals("--")) {
                    JOptionPane.showMessageDialog(rootPane, "Nama DPJP harus diisi/dipilih dulu dengan benar,..");
                } else {
                    isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                        + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                            "Instruksi Dokter Untuk DNR (Do Not Resucitate)", TnmDpjp.getText(),
                            Sequel.cariIsi("select date_format('" + tbInstruksi.getValueAt(tbInstruksi.getSelectedRow(), 17).toString() + "','%d/%m/%Y')"),
                            Sequel.cariIsi("select time('" + tbInstruksi.getValueAt(tbInstruksi.getSelectedRow(), 17).toString() + "')")) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                    Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                    Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Instruksi Dokter Untuk DNR", Sequel.cariFolderPrintTte());
                    param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                    param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='001'"));

                    Valid.MyReport("rptInstruksiDokterUntukDNRQr.jasper", "report", "::[ Instruksi Dokter Untuk DNR (Do Not Resucitate) ]::",
                        "SELECT date(now()) tgl", param);
                    Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                }
            } else {
                Valid.MyReport("rptInstruksiDokterUntukDNR.jasper", "report", "::[ Instruksi Dokter Untuk DNR (Do Not Resucitate) ]::",
                    "SELECT date(now()) tgl", param);
            }

            BtnBatalActionPerformed(null);
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih/klik dulu salah satu datanya pada tabel..!!!");
            tbInstruksi.requestFocus();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnGanti, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMInstruksiDokterDNR dialog = new RMInstruksiDokterDNR(new javax.swing.JFrame(), true);
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
    private widget.Button BtnDpjp;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TnmDpjp;
    private widget.TextBox TnoTelp;
    private widget.TextBox TrgRawat;
    private widget.Tanggal TtglMenyatakan;
    private widget.CekBox chkAnggota;
    private widget.CekBox chkInstruksi;
    private widget.CekBox chkKeputusan;
    private widget.CekBox chkNakes;
    private widget.CekBox chkPasien;
    private widget.CekBox chkWali;
    private widget.ComboBox cmbPilihCetak;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel6;
    private widget.Label jLabel69;
    private widget.Label jLabel70;
    private widget.Label jLabel71;
    private widget.Label jLabel72;
    private widget.Label jLabel73;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.Table tbInstruksi;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select id.*, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jenkel, date_format(p.tgl_lahir,'%d-%m-%Y') tglLahir, "
                    + "date_format(id.tgl_menyatakan,'%d-%m-%Y') tglMenya, pg.nama nmDpjp from instruksi_dokter_untuk_dnr id "
                    + "inner join reg_periksa rp on rp.no_rawat=id.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg on pg.nik=id.nip_dpjp where "
                    + "id.tgl_menyatakan between ? and ? and id.no_rawat like ? or "
                    + "id.tgl_menyatakan between ? and ? and p.no_rkm_medis like ? or "
                    + "id.tgl_menyatakan between ? and ? and p.nm_pasien like ? or "
                    + "id.tgl_menyatakan between ? and ? and pg.nama like ? order by id.waktu_simpan desc");
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
                rs = ps.executeQuery();                
                while (rs.next()) {
                    tabMode.addRow(new String[]{                        
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("jenkel"),
                        rs.getString("tglLahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tglMenya"),
                        rs.getString("nmDpjp"),
                        rs.getString("cek_pasien"),
                        rs.getString("cek_nakes"),
                        rs.getString("cek_wali"),
                        rs.getString("cek_anggota_klg"),
                        rs.getString("cek_instruksi"),
                        rs.getString("cek_keputusan"),
                        rs.getString("nip_dpjp"),
                        rs.getString("no_telp_dpjp"),
                        rs.getString("tgl_menyatakan"),
                        rs.getString("waktu_simpan")
                    });
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
        LCount.setText("" + tabMode.getRowCount());
    }
    
    public void emptTeks() {  
        chkPasien.setSelected(false);
        chkNakes.setSelected(false);
        chkWali.setSelected(false);
        chkAnggota.setSelected(false);
        chkInstruksi.setSelected(false);
        chkKeputusan.setSelected(false);
        nipDpjp = "-";
        TnmDpjp.setText("-");
        TnoTelp.setText("");
        TtglMenyatakan.setDate(new Date());
    }

    private void getData() {        
        nipDpjp = "";
        cekPasien = "";
        cekNakes = "";
        cekWali = "";
        cekAnggota = "";
        cekInstruksi = "";
        cekKeputusan = "";
        
        if (tbInstruksi.getSelectedRow() != -1) {
            TNoRw.setText(tbInstruksi.getValueAt(tbInstruksi.getSelectedRow(), 0).toString());
            TNoRM.setText(tbInstruksi.getValueAt(tbInstruksi.getSelectedRow(), 1).toString());
            TPasien.setText(tbInstruksi.getValueAt(tbInstruksi.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbInstruksi.getValueAt(tbInstruksi.getSelectedRow(), 5).toString());
            cekPasien = tbInstruksi.getValueAt(tbInstruksi.getSelectedRow(), 8).toString();
            cekNakes = tbInstruksi.getValueAt(tbInstruksi.getSelectedRow(), 9).toString();
            cekWali = tbInstruksi.getValueAt(tbInstruksi.getSelectedRow(), 10).toString();
            cekAnggota = tbInstruksi.getValueAt(tbInstruksi.getSelectedRow(), 11).toString();
            cekInstruksi = tbInstruksi.getValueAt(tbInstruksi.getSelectedRow(), 12).toString();
            cekKeputusan = tbInstruksi.getValueAt(tbInstruksi.getSelectedRow(), 13).toString();
            nipDpjp = tbInstruksi.getValueAt(tbInstruksi.getSelectedRow(), 14).toString();
            TnmDpjp.setText(tbInstruksi.getValueAt(tbInstruksi.getSelectedRow(), 7).toString());
            TnoTelp.setText(tbInstruksi.getValueAt(tbInstruksi.getSelectedRow(), 15).toString());            
            Valid.SetTgl(TtglMenyatakan, tbInstruksi.getValueAt(tbInstruksi.getSelectedRow(), 16).toString());
            dataCek();
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getresep_dokter());
        BtnGanti.setEnabled(akses.getresep_dokter());
        BtnHapus.setEnabled(akses.getresep_dokter());

        if (akses.getjml2() >= 1) {
            nipDpjp = akses.getkode();
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmDpjp, nipDpjp);
            if (TnmDpjp.getText().equals("")) {
                TnmDpjp.setText("-");
            }
        }
    }
    
    public void setData(String norw, String norm, String nmpasien, String ruangan) {
        TNoRw.setText(norw);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norw + "'"));
        TrgRawat.setText(ruangan);
        TCari.setText(norw);
    }
    
    public void awalData() {
        tampil();
    }

    private void cekData() {
        if (chkPasien.isSelected() == true) {
            cekPasien = "ya";
        } else {
            cekPasien = "tidak";
        }
        
        if (chkNakes.isSelected() == true) {
            cekNakes = "ya";
        } else {
            cekNakes = "tidak";
        }
        
        if (chkWali.isSelected() == true) {
            cekWali = "ya";
        } else {
            cekWali = "tidak";
        }
        
        if (chkAnggota.isSelected() == true) {
            cekAnggota = "ya";
        } else {
            cekAnggota = "tidak";
        }
        
        if (chkInstruksi.isSelected() == true) {
            cekInstruksi = "ya";
        } else {
            cekInstruksi = "tidak";
        }
        
        if (chkKeputusan.isSelected() == true) {
            cekKeputusan = "ya";
        } else {
            cekKeputusan = "tidak";
        }
    }
    
    private void dataCek() {
        if (cekPasien.equals("ya")) {
            chkPasien.setSelected(true);
        } else {
            chkPasien.setSelected(false);
        }
        
        if (cekNakes.equals("ya")) {
            chkNakes.setSelected(true);
        } else {
            chkNakes.setSelected(false);
        }
        
        if (cekWali.equals("ya")) {
            chkWali.setSelected(true);
        } else {
            chkWali.setSelected(false);
        }
        
        if (cekAnggota.equals("ya")) {
            chkAnggota.setSelected(true);
        } else {
            chkAnggota.setSelected(false);
        }
        
        if (cekInstruksi.equals("ya")) {
            chkInstruksi.setSelected(true);
        } else {
            chkInstruksi.setSelected(false);
        }
        
        if (cekKeputusan.equals("ya")) {
            chkKeputusan.setSelected(true);
        } else {
            chkKeputusan.setSelected(false);
        }
    }
}
