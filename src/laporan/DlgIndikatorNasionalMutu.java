package laporan;

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
import java.io.FileInputStream;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import rekammedis.DlgMasterIndikatorMutu;

/**
 *
 * @author dosen
 */
public class DlgIndikatorNasionalMutu extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private String kdIndikator = "", gedungDIpilih = "", tglDipilih = "", angkaBulan = "", cekBulan = "", 
            tgl1 = "", tgl2 = "", tgl3 = "", tgl4 = "", tgl5 = "", tgl6 = "", tgl7 = "", tgl8 = "", tgl9 = "", tgl10 = "",
            tgl11 = "", tgl12 = "", tgl13 = "", tgl14 = "", tgl15 = "", tgl16 = "", tgl17 = "", tgl18 = "", tgl19 = "", tgl20 = "",
            tgl21 = "", tgl22 = "", tgl23 = "", tgl24 = "", tgl25 = "", tgl26 = "", tgl27 = "", tgl28 = "", tgl29 = "", tgl30 = "",
            tgl31 = "", total = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgIndikatorNasionalMutu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "Ruangan", "No.", "Indikator", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "Total",
            "kdindikator", "tglcatat"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbIndikator.setModel(tabMode);
        tbIndikator.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbIndikator.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 37; i++) {
            TableColumn column = tbIndikator.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(40);
            } else if (i == 2) {
                column.setPreferredWidth(400);
            } else if (i == 3) {
                column.setPreferredWidth(40);
            } else if (i == 4) {
                column.setPreferredWidth(40);
            } else if (i == 5) {
                column.setPreferredWidth(40);
            } else if (i == 6) {
                column.setPreferredWidth(40);
            } else if (i == 7) {
                column.setPreferredWidth(40);
            } else if (i == 8) {
                column.setPreferredWidth(40);
            } else if (i == 9) {
                column.setPreferredWidth(40);
            } else if (i == 10) {
                column.setPreferredWidth(40);
            } else if (i == 11) {
                column.setPreferredWidth(40);
            } else if (i == 12) {
                column.setPreferredWidth(40);
            } else if (i == 13) {
                column.setPreferredWidth(40);
            } else if (i == 14) {
                column.setPreferredWidth(40);
            } else if (i == 15) {
                column.setPreferredWidth(40);
            } else if (i == 16) {
                column.setPreferredWidth(40);
            } else if (i == 17) {
                column.setPreferredWidth(40);
            } else if (i == 18) {
                column.setPreferredWidth(40);
            } else if (i == 19) {
                column.setPreferredWidth(40);
            } else if (i == 20) {
                column.setPreferredWidth(40);
            } else if (i == 21) {
                column.setPreferredWidth(40);
            } else if (i == 22) {
                column.setPreferredWidth(40);
            } else if (i == 23) {
                column.setPreferredWidth(40);
            } else if (i == 24) {
                column.setPreferredWidth(40);
            } else if (i == 25) {
                column.setPreferredWidth(40);
            } else if (i == 26) {
                column.setPreferredWidth(40);
            } else if (i == 27) {
                column.setPreferredWidth(40);
            } else if (i == 28) {
                column.setPreferredWidth(40);
            } else if (i == 29) {
                column.setPreferredWidth(40);
            } else if (i == 30) {
                column.setPreferredWidth(40);
            } else if (i == 31) {
                column.setPreferredWidth(40);
            } else if (i == 32) {
                column.setPreferredWidth(40);
            } else if (i == 33) {
                column.setPreferredWidth(40);
            } else if (i == 34) {
                column.setPreferredWidth(70);
            } else if (i == 35) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 36) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbIndikator.setDefaultRenderer(Object.class, new WarnaTable());

        Ttahun.setDocument(new batasInput((byte) 4).getOnlyAngka(Ttahun));
        Tjumlah.setDocument(new batasInput((byte) 3).getOnlyAngka(Tjumlah));
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
        MnMasterIndikator = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel29 = new widget.Label();
        cmbBulan = new widget.ComboBox();
        jLabel35 = new widget.Label();
        Ttahun = new widget.TextBox();
        jLabel6 = new widget.Label();
        cmbGedung1 = new widget.ComboBox();
        BtnCari = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        jLabel4 = new widget.Label();
        cmbGedung = new widget.ComboBox();
        jLabel5 = new widget.Label();
        cmbIndikator = new widget.ComboBox();
        jLabel8 = new widget.Label();
        TtglCatat = new widget.Tanggal();
        jLabel9 = new widget.Label();
        Tjumlah = new widget.TextBox();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbIndikator = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnMasterIndikator.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnMasterIndikator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnMasterIndikator.setText("Master Indikator NM");
        MnMasterIndikator.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnMasterIndikator.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnMasterIndikator.setIconTextGap(5);
        MnMasterIndikator.setName("MnMasterIndikator"); // NOI18N
        MnMasterIndikator.setPreferredSize(new java.awt.Dimension(150, 26));
        MnMasterIndikator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnMasterIndikatorActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnMasterIndikator);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Indikator Nasional Mutu ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Bulan :");
        jLabel29.setName("jLabel29"); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(jLabel29);

        cmbBulan.setForeground(new java.awt.Color(0, 0, 0));
        cmbBulan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "Nopember", "Desember" }));
        cmbBulan.setName("cmbBulan"); // NOI18N
        cmbBulan.setPreferredSize(new java.awt.Dimension(85, 23));
        cmbBulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBulanActionPerformed(evt);
            }
        });
        panelGlass10.add(cmbBulan);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Tahun :");
        jLabel35.setName("jLabel35"); // NOI18N
        jLabel35.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(jLabel35);

        Ttahun.setForeground(new java.awt.Color(0, 0, 0));
        Ttahun.setName("Ttahun"); // NOI18N
        Ttahun.setPreferredSize(new java.awt.Dimension(60, 23));
        Ttahun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtahunKeyPressed(evt);
            }
        });
        panelGlass10.add(Ttahun);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Ruang Perawatan :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass10.add(jLabel6);

        cmbGedung1.setForeground(new java.awt.Color(0, 0, 0));
        cmbGedung1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "VK BERSALIN", "RAWAT JALAN", "IBS", "AR RAUDAH" }));
        cmbGedung1.setName("cmbGedung1"); // NOI18N
        cmbGedung1.setPreferredSize(new java.awt.Dimension(190, 23));
        cmbGedung1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbGedung1MouseReleased(evt);
            }
        });
        panelGlass10.add(cmbGedung1);

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

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 107));
        PanelInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Ruang Perawatan :");
        jLabel4.setName("jLabel4"); // NOI18N
        PanelInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 130, 23);

        cmbGedung.setForeground(new java.awt.Color(0, 0, 0));
        cmbGedung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "VK BERSALIN", "RAWAT JALAN", "IBS", "AR RAUDAH" }));
        cmbGedung.setName("cmbGedung"); // NOI18N
        cmbGedung.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbGedung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbGedungMouseReleased(evt);
            }
        });
        cmbGedung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGedungActionPerformed(evt);
            }
        });
        PanelInput.add(cmbGedung);
        cmbGedung.setBounds(135, 10, 190, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Jenis Indikator :");
        jLabel5.setName("jLabel5"); // NOI18N
        PanelInput.add(jLabel5);
        jLabel5.setBounds(0, 38, 130, 23);

        cmbIndikator.setForeground(new java.awt.Color(0, 0, 0));
        cmbIndikator.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbIndikator.setName("cmbIndikator"); // NOI18N
        cmbIndikator.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelInput.add(cmbIndikator);
        cmbIndikator.setBounds(135, 38, 490, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Dilaporkan :");
        jLabel8.setName("jLabel8"); // NOI18N
        PanelInput.add(jLabel8);
        jLabel8.setBounds(0, 66, 130, 23);

        TtglCatat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-09-2024" }));
        TtglCatat.setDisplayFormat("dd-MM-yyyy");
        TtglCatat.setName("TtglCatat"); // NOI18N
        TtglCatat.setOpaque(false);
        TtglCatat.setPreferredSize(new java.awt.Dimension(90, 23));
        PanelInput.add(TtglCatat);
        TtglCatat.setBounds(135, 66, 90, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Jumlah Dilaporkan :");
        jLabel9.setName("jLabel9"); // NOI18N
        PanelInput.add(jLabel9);
        jLabel9.setBounds(230, 66, 110, 23);

        Tjumlah.setForeground(new java.awt.Color(0, 0, 0));
        Tjumlah.setName("Tjumlah"); // NOI18N
        Tjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjumlahKeyPressed(evt);
            }
        });
        PanelInput.add(Tjumlah);
        Tjumlah.setBounds(345, 66, 50, 23);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbIndikator.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbIndikator.setComponentPopupMenu(jPopupMenu1);
        tbIndikator.setName("tbIndikator"); // NOI18N
        tbIndikator.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbIndikatorMouseClicked(evt);
            }
        });
        tbIndikator.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbIndikatorKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbIndikator);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (cmbGedung.getSelectedItem().toString().equals("-")) {
            Valid.textKosong(cmbGedung, "ruang perawatan");
            cmbGedung.requestFocus();
        } else if (cmbIndikator.getSelectedItem().toString().equals("-")) {
            Valid.textKosong(cmbIndikator, "jenis indikator");
            cmbIndikator.requestFocus();
        } else if (Tjumlah.getText().equals("")) {
            Valid.textKosong(Tjumlah, "jumlah dilaporkan");
            Tjumlah.requestFocus();
        } else {
            kdIndikator = Sequel.cariIsi("select kd_indikator from master_indikator_nasional_mutu "
                + "where gedung='" + cmbGedung.getSelectedItem().toString() + "' and "
                + "nm_indikator='" + cmbIndikator.getSelectedItem().toString() + "'");
            
            Sequel.menyimpan("indikator_nasional_mutu", "'" + kdIndikator + "','" + cmbGedung.getSelectedItem().toString() + "',"
                    + "'" + Valid.SetTgl(TtglCatat.getSelectedItem() + "") + "','" + Tjumlah.getText() + "'", "Indikator & Tgl. Dilaporkan");
            
            cekBulanTahun();
            BtnCariActionPerformed(null);
            emptTeks();
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
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (cmbGedung.getSelectedItem().toString().equals("-")) {
            Valid.textKosong(cmbGedung, "ruang perawatan");
            cmbGedung.requestFocus();
        } else if (cmbIndikator.getSelectedItem().toString().equals("-")) {
            Valid.textKosong(cmbIndikator, "jenis indikator");
            cmbIndikator.requestFocus();
        } else if (Tjumlah.getText().equals("")) {
            Valid.textKosong(Tjumlah, "jumlah dilaporkan");
            Tjumlah.requestFocus();
        } else {
            kdIndikator = Sequel.cariIsi("select kd_indikator from master_indikator_nasional_mutu "
                + "where gedung='" + cmbGedung.getSelectedItem().toString() + "' and "
                + "nm_indikator='" + cmbIndikator.getSelectedItem().toString() + "'");
            
            Sequel.mengedit("indikator_nasional_mutu", "kd_indikator='" + kdIndikator + "' and gedung='" + gedungDIpilih + "' and tgl_catat='" + tglDipilih + "'",
                    "kd_indikator='" + kdIndikator + "',gedung='" + cmbGedung.getSelectedItem().toString() + "', "
                    + "tgl_catat='" + Valid.SetTgl(TtglCatat.getSelectedItem() + "") + "', jumlah_pertanggal='" + Tjumlah.getText() + "'");
            
            cekBulanTahun();
            BtnCariActionPerformed(null);
            emptTeks();
        }
}//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnGantiActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
}//GEN-LAST:event_BtnGantiKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        BtnCariActionPerformed(null);
        emptTeks();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbIndikatorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbIndikatorMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbIndikatorMouseClicked

    private void tbIndikatorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbIndikatorKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbIndikatorKeyPressed

    private void TjumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjumlahKeyPressed
        Valid.pindah(evt, TtglCatat, BtnSimpan);
    }//GEN-LAST:event_TjumlahKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Sequel.cariIsiComboDB("SELECT nm_gedung FROM bangsal WHERE nm_gedung<>'-' and nm_gedung not like '%ar-rau%' and status='1' GROUP BY nm_gedung ORDER BY nm_gedung", cmbGedung);
        Sequel.cariIsiComboDB("SELECT nm_gedung FROM bangsal WHERE nm_gedung<>'-' and nm_gedung not like '%ar-rau%' and status='1' GROUP BY nm_gedung ORDER BY nm_gedung", cmbGedung1);
        
        cmbBulan.setSelectedItem(Sequel.bulanINDONESIA("select month(now())"));
        angkaBulan = Sequel.cariIsi("select month(now())");
        Ttahun.setText(Sequel.cariIsi("select year(now())"));
        tampil();
        MnMasterIndikator.setEnabled(akses.getadmin());
    }//GEN-LAST:event_formWindowOpened

    private void cmbGedungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGedungActionPerformed
        cmbIndikator.removeAllItems();
        Sequel.cariIsiComboDB("SELECT nm_indikator from master_indikator_nasional_mutu "
                + "WHERE gedung='" + cmbGedung.getSelectedItem().toString() + "' and status_data='aktif' order by no_urut", cmbIndikator);
        
        cmbGedung1.setSelectedItem(cmbGedung.getSelectedItem().toString());
    }//GEN-LAST:event_cmbGedungActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbIndikator.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from indikator_nasional_mutu where gedung='" + gedungDIpilih + "' and "
                        + "tgl_catat='" + tglDipilih + "' and kd_indikator=?", 1, new String[]{
                    tbIndikator.getValueAt(tbIndikator.getSelectedRow(), 84).toString()
                }) == true) {
                    cekBulanTahun();
                    tampil();
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tbIndikator.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void cmbBulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBulanActionPerformed
        angkaBulan = "";
        if (cmbBulan.getSelectedIndex() == 0) {
            angkaBulan = "1";
        } else if (cmbBulan.getSelectedIndex() == 1) {
            angkaBulan = "2";
        } else if (cmbBulan.getSelectedIndex() == 2) {
            angkaBulan = "3";
        } else if (cmbBulan.getSelectedIndex() == 3) {
            angkaBulan = "4";
        } else if (cmbBulan.getSelectedIndex() == 4) {
            angkaBulan = "5";
        } else if (cmbBulan.getSelectedIndex() == 5) {
            angkaBulan = "6";
        } else if (cmbBulan.getSelectedIndex() == 6) {
            angkaBulan = "7";
        } else if (cmbBulan.getSelectedIndex() == 7) {
            angkaBulan = "8";
        } else if (cmbBulan.getSelectedIndex() == 8) {
            angkaBulan = "9";
        } else if (cmbBulan.getSelectedIndex() == 9) {
            angkaBulan = "10";
        } else if (cmbBulan.getSelectedIndex() == 10) {
            angkaBulan = "11";
        } else if (cmbBulan.getSelectedIndex() == 11) {
            angkaBulan = "12";
        }
        BtnCariActionPerformed(null);
    }//GEN-LAST:event_cmbBulanActionPerformed

    private void TtahunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtahunKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        }
    }//GEN-LAST:event_TtahunKeyPressed

    private void cmbGedungMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbGedungMouseReleased
        AutoCompleteDecorator.decorate(cmbGedung);
    }//GEN-LAST:event_cmbGedungMouseReleased

    private void cmbGedung1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbGedung1MouseReleased
        AutoCompleteDecorator.decorate(cmbGedung1);
    }//GEN-LAST:event_cmbGedung1MouseReleased

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbIndikator.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            //report rptAsesmenPraSedasi1
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
//            param.put("norm", TNoRM.getText());
//            param.put("nmpasien", TPasien.getText());
//            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
//
//            param.put("riwAlergiObat", TriwAlergiObat.getText() + "\n");
//            param.put("obatSaatIni", TobatSaatIni.getText() + "\n");
//            param.put("riwAnastesi", TriwAnastesi.getText() + "\n");
//
//            param.put("keadaan", "Kepala : " + Tkepala.getText() + ", Sklera : " + Tsklera.getText() + ", Conjungtiva : " + Tconjung.getText() + ", Leher : " + Tleher.getText() + "\n"
//                + "Paru-paru : " + Tparu.getText() + "\n"
//                + "Jantung : " + Tjantung.getText() + "\n"
//                + "Abdomen : " + Tabdomen.getText() + "\n"
//                + "Ekstremitas : " + Tekstremitas.getText() + "\n");
//
//            param.put("pemeriksaan", "GCS : " + Tgcs.getText() + ", Tekanan Darah : " + Ttd.getText() + " mmHg, RR : " + Trr.getText() + " x/menit, Nadi : " + Tnadi.getText() + " x/menit\n"
//                + "Suhu : " + Tsuhu.getText() + " °C, Tinggi Badan : " + Ttb.getText() + " Cm, Berat Badan : " + Tbb.getText() + " Kg, VAS : " + Tvas.getText() + "\n");
//
//            param.put("kajian1", ": " + cmbHilang.getSelectedItem().toString() + "\n"
//                + ": " + cmbMasalah.getSelectedItem().toString() + "\n"
//                + ": " + cmbLeher.getSelectedItem().toString() + "\n"
//                + ": " + cmbStroke.getSelectedItem().toString() + "\n"
//                + ": " + cmbSesak.getSelectedItem().toString() + "\n"
//                + ": " + cmbBuka.getSelectedItem().toString() + "\n"
//                + ": " + cmbJarak.getSelectedItem().toString());
//
//            param.put("kajian2", ": " + cmbSakit.getSelectedItem().toString() + "\n"
//                + ": " + cmbDenyut.getSelectedItem().toString() + "\n"
//                + ": " + cmbSedang.getSelectedItem().toString() + "\n"
//                + ": " + cmbKejang.getSelectedItem().toString() + "\n"
//                + ": " + cmbObes.getSelectedItem().toString() + "\n"
//                + ": " + cmbGigi.getSelectedItem().toString() + "\n"
//                + ": " + cmbGerakan.getSelectedItem().toString());
//
//            if (ChkSatu.isSelected() == true) {
//                param.put("mal1", "V");
//            } else {
//                param.put("mal1", "");
//            }
//
//            if (ChkDua.isSelected() == true) {
//                param.put("mal2", "V");
//            } else {
//                param.put("mal2", "");
//            }
//
//            if (ChkTiga.isSelected() == true) {
//                param.put("mal3", "V");
//            } else {
//                param.put("mal3", "");
//            }
//
//            if (ChkEmpat.isSelected() == true) {
//                param.put("mal4", "V");
//            } else {
//                param.put("mal4", "");
//            }
//
//            param.put("penunjang", Tpemeriksaan.getText() + "\n");
//            param.put("diagnosa", Tdiagnosa.getText() + "\n");
//
//            //report rptAsesmenPraSedasi2
//            if (ChkSedasi.isSelected() == true) {
//                param.put("sedasi", "V");
//                param.put("obat1", Tobat1.getText());
//                param.put("obat2", Tobat2.getText());
//                param.put("obat3", Tobat3.getText());
//            } else {
//                param.put("sedasi", "");
//                param.put("obat1", "");
//                param.put("obat2", "");
//                param.put("obat3", "");
//            }
//
//            if (ChkGA.isSelected() == true) {
//                param.put("ga", "V");
//                param.put("ketGa", Tga.getText());
//            } else {
//                param.put("ga", "");
//                param.put("ketGa", "");
//            }
//
//            if (ChkRegional.isSelected() == true) {
//                param.put("regional", "V");
//            } else {
//                param.put("regional", "");
//            }
//
//            if (ChkSpinal.isSelected() == true) {
//                param.put("spinal", "V");
//            } else {
//                param.put("spinal", "");
//            }
//
//            if (ChkEpidural.isSelected() == true) {
//                param.put("epidural", "V");
//            } else {
//                param.put("epidural", "");
//            }
//
//            if (ChkKaudal.isSelected() == true) {
//                param.put("kaudal", "V");
//            } else {
//                param.put("kaudal", "");
//            }
//
//            if (ChkBlok.isSelected() == true) {
//                param.put("blok", "V");
//            } else {
//                param.put("blok", "");
//            }
//
//            if (ChkEkg.isSelected() == true) {
//                param.put("ekg", "V");
//            } else {
//                param.put("ekg", "");
//            }
//
//            if (ChkSpo.isSelected() == true) {
//                param.put("spo2", "V");
//            } else {
//                param.put("spo2", "");
//            }
//
//            if (ChkNibp.isSelected() == true) {
//                param.put("nibp", "V");
//            } else {
//                param.put("nibp", "");
//            }
//
//            if (ChkTemp.isSelected() == true) {
//                param.put("temp", "V");
//            } else {
//                param.put("temp", "");
//            }
//
//            if (ChkLain.isSelected() == true) {
//                param.put("lain", "V");
//                param.put("ketLain", Tlain.getText());
//            } else {
//                param.put("lain", "");
//                param.put("ketLain", "");
//            }
//
//            if (ChkRanap.isSelected() == true) {
//                param.put("ranap", "V");
//            } else {
//                param.put("ranap", "");
//            }
//
//            if (ChkRalan.isSelected() == true) {
//                param.put("ralan", "V");
//            } else {
//                param.put("ralan", "");
//            }
//
//            if (ChkRakhus.isSelected() == true) {
//                param.put("rakhus", "V");
//            } else {
//                param.put("rakhus", "");
//            }
//
//            if (ChkIcu.isSelected() == true) {
//                param.put("icu", "V");
//            } else {
//                param.put("icu", "");
//            }
//
//            if (ChkHcu.isSelected() == true) {
//                param.put("hcu", "V");
//            } else {
//                param.put("hcu", "");
//            }
//
//            param.put("catatan", Tcatatan.getText() + "\n");
//
//            if (ChkAsa1.isSelected() == true) {
//                param.put("asa1", "V");
//            } else {
//                param.put("asa1", "");
//            }
//
//            if (ChkAsa2.isSelected() == true) {
//                param.put("asa2", "V");
//            } else {
//                param.put("asa2", "");
//            }
//
//            if (ChkAsa3.isSelected() == true) {
//                param.put("asa3", "V");
//            } else {
//                param.put("asa3", "");
//            }
//
//            if (ChkAsa4.isSelected() == true) {
//                param.put("asa4", "V");
//            } else {
//                param.put("asa4", "");
//            }
//
//            if (ChkEmergency.isSelected() == true) {
//                param.put("emer", "V");
//            } else {
//                param.put("emer", "");
//            }
//
//            param.put("penyulit", Tpenyulit.getText() + "\n");
//            param.put("puasaMulai", cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString() + " Wita, Tanggal : " + TtglPuasa.getSelectedItem().toString());
//            param.put("rencana", cmbJam1.getSelectedItem().toString() + ":" + cmbMnt1.getSelectedItem().toString() + " Wita, Tanggal : " + TtglRencana.getSelectedItem().toString());
//            param.put("dokter", "(" + TnmDokter.getText() + ")");
//
//            Valid.MyReport("rptAsesmenPraSedasi2.jasper", "report", "::[ Laporan Asesmen Pra Sedasi hal. 2 ]::",
//                "SELECT now() tanggal", param);
//            Valid.MyReport("rptAsesmenPraSedasi1.jasper", "report", "::[ Laporan Asesmen Pra Sedasi hal. 1 ]::",
//                "SELECT now() tanggal", param);

            emptTeks();
            tampil();
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik/pilih datanya pada tabel terlebih dahulu..!!!!");
            tbIndikator.requestFocus();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void MnMasterIndikatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnMasterIndikatorActionPerformed
        akses.setform("DlgIndikatorNasionalMutu");
        DlgMasterIndikatorMutu mutu = new DlgMasterIndikatorMutu(null, false);
        mutu.isCek();
        mutu.emptTeks();
        mutu.setSize(782, internalFrame1.getHeight() - 40);
        mutu.setLocationRelativeTo(internalFrame1);
        mutu.setVisible(true);
    }//GEN-LAST:event_MnMasterIndikatorActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgIndikatorNasionalMutu dialog = new DlgIndikatorNasionalMutu(new javax.swing.JFrame(), true);
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
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private javax.swing.JMenuItem MnMasterIndikator;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.TextBox Tjumlah;
    private widget.TextBox Ttahun;
    private widget.Tanggal TtglCatat;
    private widget.ComboBox cmbBulan;
    private widget.ComboBox cmbGedung;
    private widget.ComboBox cmbGedung1;
    private widget.ComboBox cmbIndikator;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel29;
    private widget.Label jLabel35;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.Table tbIndikator;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        if (Ttahun.getText().equals("")) {
            Ttahun.setText(Sequel.cariIsi("select year(now())"));
        } else {
            Ttahun.setText(Ttahun.getText());
        }
        
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT inm.*, m.no_urut, m.nm_indikator from indikator_nasional_mutu inm "
                    + "INNER JOIN master_indikator_nasional_mutu m on m.kd_indikator=inm.kd_indikator where "
                    + "MONTH(inm.tgl_catat)='" + angkaBulan + "' and YEAR(inm.tgl_catat)='" + Ttahun.getText() + "' "
                    + "and inm.gedung='" + cmbGedung1.getSelectedItem().toString() + "' order by m.no_urut");
            try {
                rs = ps.executeQuery();
                x = 1;
                while (rs.next()) {
                    cekJumlahPertanggal(rs.getString("tgl_catat"), rs.getString("gedung"));
                    tabMode.addRow(new String[]{                        
                        rs.getString("gedung"),
                        x + ".",
                        rs.getString("nm_indikator"),
                        tgl1,
                        tgl2,
                        tgl3,
                        tgl4,
                        tgl5,
                        tgl6,
                        tgl7,
                        tgl8,
                        tgl9,
                        tgl10,
                        tgl11,
                        tgl12,
                        tgl13,
                        tgl14,
                        tgl15,
                        tgl16,
                        tgl17,
                        tgl18,
                        tgl19,
                        tgl20,
                        tgl21,
                        tgl22,
                        tgl23,
                        tgl24,
                        tgl25,
                        tgl26,
                        tgl27,
                        tgl28,
                        tgl29,
                        tgl30,
                        tgl31,
                        total,
                        rs.getString("kd_indikator"),
                        rs.getString("tgl_catat")
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("laporan.DlgIndikatorNasionalMutu.tampil() : " + e);
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
    }
    
    public void emptTeks() {  
        cmbIndikator.setSelectedIndex(0);
        TtglCatat.setDate(new Date());
        Tjumlah.setText("0");
    }

    private void getData() {
        kdIndikator = "";
        gedungDIpilih = "";
        tglDipilih = "";

        if (tbIndikator.getSelectedRow() != -1) {
//            kdkomite.setText(tbIndikator.getValueAt(tbIndikator.getSelectedRow(), 0).toString());
//            nmkomite.setText(tbIndikator.getValueAt(tbIndikator.getSelectedRow(), 1).toString());        
        }
    }
    
    private void cekBulanTahun() {
        cekBulan = Sequel.cariIsi("select MONTH('" + Valid.SetTgl(TtglCatat.getSelectedItem() + "") + "')");
        Ttahun.setText(Sequel.cariIsi("select YEAR('" + Valid.SetTgl(TtglCatat.getSelectedItem() + "") + "')"));

        if (cekBulan.equals("1")) {
            cmbBulan.setSelectedIndex(0);
        } else if (cekBulan.equals("2")) {
            cmbBulan.setSelectedIndex(1);
        } else if (cekBulan.equals("3")) {
            cmbBulan.setSelectedIndex(2);
        } else if (cekBulan.equals("4")) {
            cmbBulan.setSelectedIndex(3);
        } else if (cekBulan.equals("5")) {
            cmbBulan.setSelectedIndex(4);
        } else if (cekBulan.equals("6")) {
            cmbBulan.setSelectedIndex(5);
        } else if (cekBulan.equals("7")) {
            cmbBulan.setSelectedIndex(6);
        } else if (cekBulan.equals("8")) {
            cmbBulan.setSelectedIndex(7);
        } else if (cekBulan.equals("9")) {
            cmbBulan.setSelectedIndex(8);
        } else if (cekBulan.equals("10")) {
            cmbBulan.setSelectedIndex(9);
        } else if (cekBulan.equals("11")) {
            cmbBulan.setSelectedIndex(10);
        } else if (cekBulan.equals("12")) {
            cmbBulan.setSelectedIndex(11);
        }
    }
    
    private void cekJumlahPertanggal(String bln, String nmGedung) {
        
    }
}
