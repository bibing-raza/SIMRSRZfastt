package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariPetugas;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgKabupaten;
import simrskhanza.DlgKecamatan;
import simrskhanza.DlgKelurahan;
import simrskhanza.DlgPasien;
import simrskhanza.frmUtama;

/**
 *
 * @author dosen
 */
public class RMSuratPenyataanBayarDenda extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2;
    private ResultSet rs, rs1, rs2;
    public DlgKabupaten kab = new DlgKabupaten(null, false);
    public DlgKecamatan kec = new DlgKecamatan(null, false);
    public DlgKelurahan kel = new DlgKelurahan(null, false);
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgPasien pasien = new DlgPasien(null, false);
    private int i = 0, x = 0, cekSama = 0;
    private String kdkel = "", kdkec = "", kdkab = "", nipPtgs = "", norawat = "", idFileNmBerttd = "", cekSatu = "", cekTiga = "",
            tglreg = "", norm = "";
    private frmUtama formUtama;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMSuratPenyataanBayarDenda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();        
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "Nama BerTTD", "No. RM", "Nama Pasien", "Jns. Kelamin", "Tgl. Lahir", "Tgl. Daftar/Reg.", "Tgl. Surat", "Nama Petugas",
            "nm_berttd", "alamat", "kd_kel", "kd_kec", "kd_kab", "no_tlp", "email", "satu_kali", "tiga_kali", "bank", "no_rekening", 
            "atas_nama", "tanggal", "nip_petugas", "id_file_nm_berttd", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbSurat.setModel(tabMode);
        tbSurat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbSurat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 25; i++) {
            TableColumn column = tbSurat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(220);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(220);
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
            }
        }
        tbSurat.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbSurat.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbSurat.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbSurat.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbSurat.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        
        tabMode1 = new DefaultTableModel(null, new String[]{
            "No.", "Nama", "No. Kartu", "Jabatan/Pekerjaan", "Status", "Tanggungan", "Keterangan", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPeserta.setModel(tabMode1);
        tbPeserta.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPeserta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (i = 0; i < 8; i++) {
            TableColumn column = tbPeserta.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(40);
            } else if (i == 1) {
                column.setPreferredWidth(220);
            } else if (i == 2) {
                column.setPreferredWidth(105);
            } else if (i == 3) {
                column.setPreferredWidth(230);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(220);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPeserta.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPeserta.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPeserta.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        Tnm.setDocument(new batasInput((int) 200).getKata(Tnm));
        Talamat.setDocument(new batasInput((int) 200).getKata(Talamat));        
        TnoTelp.setDocument(new batasInput((byte) 13).getOnlyAngka(TnoTelp));
        Temail.setDocument(new batasInput((int) 150).getKata(Temail));
        TnmBank.setDocument(new batasInput((int) 150).getKata(TnmBank));
        TnoRek.setDocument(new batasInput((int) 150).getKata(TnoRek));
        TatasNm.setDocument(new batasInput((int) 150).getKata(TatasNm));        
        TnmPasien.setDocument(new batasInput((int) 200).getKata(TnmPasien));
        TnoKartu.setDocument(new batasInput((byte) 25).getOnlyAngka(TnoKartu));
        Tpekerjaan.setDocument(new batasInput((int) 200).getKata(Tpekerjaan));
        TnmTanggungan.setDocument(new batasInput((int) 200).getKata(TnmTanggungan));
        Tket.setDocument(new batasInput((int) 200).getKata(Tket));
        
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
        
        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMSuratPenyataanBayarDenda")) {
                    if (pasien.getTable().getSelectedRow() != -1) {
                        TnmPasien.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 2).toString());
                        TnoKartu.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 21).toString());
                        Tpekerjaan.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 11).toString());
                        cmbStatus.setSelectedIndex(0);
                        TnmTanggungan.setText("");
                        Tket.setText("");
                        cmbStatus.requestFocus();
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
                if (akses.getform().equals("RMSuratPenyataanBayarDenda")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pasien.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        kel.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMSuratPenyataanBayarDenda")) {
                    if (kel.getTable().getSelectedRow() != -1) {
                        TnmKel.setText(kel.getTable().getValueAt(kel.getTable().getSelectedRow(), 0).toString());
                        kdkel = kel.getTable().getValueAt(kel.getTable().getSelectedRow(), 1).toString();
                        BtnKelurahan.requestFocus();
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
                if (akses.getform().equals("RMSuratPenyataanBayarDenda")) {
                    if (kec.getTable().getSelectedRow() != -1) {
                        TnmKec.setText(kec.getTable().getValueAt(kec.getTable().getSelectedRow(), 0).toString());
                        kdkec = kec.getTable().getValueAt(kec.getTable().getSelectedRow(), 1).toString();
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
                if (akses.getform().equals("RMSuratPenyataanBayarDenda")) {
                    if (kab.getTable().getSelectedRow() != -1) {
                        TnmKab.setText(kab.getTable().getValueAt(kab.getTable().getSelectedRow(), 0).toString());
                        kdkab = kab.getTable().getValueAt(kab.getTable().getSelectedRow(), 1).toString();
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
                        nipPtgs = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPetugas.requestFocus();
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
        
        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(".isi td{border-right: 1px solid #edf2e8;font: 10px tahoma;height:12px;border-bottom: 1px solid #edf2e8;background: 0000000;color:0000000;}");
        Document doc = kit.createDefaultDocument();
        
        LoadHTML1.setEditable(true);
        LoadHTML1.setEditorKit(kit);
        LoadHTML1.setDocument(doc);
        LoadHTML1.setEditable(false);
        LoadHTML1.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnHapusTtd = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        Tnm = new widget.TextBox();
        jLabel9 = new widget.Label();
        Temail = new widget.TextBox();
        jLabel8 = new widget.Label();
        Talamat = new widget.TextBox();
        jLabel10 = new widget.Label();
        TnmKel = new widget.TextBox();
        BtnKelurahan = new widget.Button();
        chkSamaAlamat = new widget.CekBox();
        jLabel11 = new widget.Label();
        TnmKec = new widget.TextBox();
        BtnKecamatan = new widget.Button();
        jLabel12 = new widget.Label();
        TnmKab = new widget.TextBox();
        BtnKabupaten = new widget.Button();
        jLabel13 = new widget.Label();
        TnoTelp = new widget.TextBox();
        jLabel14 = new widget.Label();
        jLabel19 = new widget.Label();
        Ttgl = new widget.Tanggal();
        jLabel21 = new widget.Label();
        TnmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        jLabel23 = new widget.Label();
        TtglReg = new widget.TextBox();
        Scroll1 = new widget.ScrollPane();
        tbPeserta = new widget.Table();
        BtnPasien = new widget.Button();
        jLabel5 = new widget.Label();
        TnmPasien = new widget.TextBox();
        jLabel15 = new widget.Label();
        TnoKartu = new widget.TextBox();
        jLabel16 = new widget.Label();
        Tpekerjaan = new widget.TextBox();
        jLabel17 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        jLabel18 = new widget.Label();
        TnmTanggungan = new widget.TextBox();
        jLabel20 = new widget.Label();
        Tket = new widget.TextBox();
        BtnTambahPasien = new widget.Button();
        BtnSimpanPasien = new widget.Button();
        BtnHapusPasien = new widget.Button();
        BtnGantiPasien = new widget.Button();
        jLabel22 = new widget.Label();
        chkSatu = new widget.CekBox();
        chkTiga = new widget.CekBox();
        jLabel26 = new widget.Label();
        jLabel27 = new widget.Label();
        TnmBank = new widget.TextBox();
        jLabel28 = new widget.Label();
        TnoRek = new widget.TextBox();
        jLabel29 = new widget.Label();
        TatasNm = new widget.TextBox();
        Scroll5 = new widget.ScrollPane();
        LoadHTML1 = new widget.editorpane();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        jLabel63 = new widget.Label();
        cmbPilihCetak = new widget.ComboBox();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel24 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel25 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbSurat = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N
        jPopupMenu1.setPreferredSize(new java.awt.Dimension(172, 30));

        MnHapusTtd.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusTtd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusTtd.setText("Hapus Tanda Tangan");
        MnHapusTtd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusTtd.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusTtd.setIconTextGap(5);
        MnHapusTtd.setName("MnHapusTtd"); // NOI18N
        MnHapusTtd.setPreferredSize(new java.awt.Dimension(150, 26));
        MnHapusTtd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusTtdActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapusTtd);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Surat Pernyataan Pembayaran Denda Pelayanan & Penggunaan Selisih Denda Pelayanan Kesehatan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 565));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nama : ");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 110, 23);

        Tnm.setForeground(new java.awt.Color(0, 0, 0));
        Tnm.setName("Tnm"); // NOI18N
        Tnm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmKeyPressed(evt);
            }
        });
        FormInput.add(Tnm);
        Tnm.setBounds(114, 10, 300, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Email : ");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(245, 150, 60, 23);

        Temail.setForeground(new java.awt.Color(0, 0, 0));
        Temail.setName("Temail"); // NOI18N
        Temail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TemailKeyPressed(evt);
            }
        });
        FormInput.add(Temail);
        Temail.setBounds(307, 150, 300, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Alamat : ");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 38, 110, 23);

        Talamat.setForeground(new java.awt.Color(0, 0, 0));
        Talamat.setName("Talamat"); // NOI18N
        Talamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalamatKeyPressed(evt);
            }
        });
        FormInput.add(Talamat);
        Talamat.setBounds(114, 38, 630, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Kelurahan : ");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 66, 110, 23);

        TnmKel.setEditable(false);
        TnmKel.setForeground(new java.awt.Color(0, 0, 0));
        TnmKel.setName("TnmKel"); // NOI18N
        FormInput.add(TnmKel);
        TnmKel.setBounds(114, 66, 300, 23);

        BtnKelurahan.setForeground(new java.awt.Color(0, 0, 0));
        BtnKelurahan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKelurahan.setMnemonic('2');
        BtnKelurahan.setToolTipText("ALt+2");
        BtnKelurahan.setName("BtnKelurahan"); // NOI18N
        BtnKelurahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKelurahanActionPerformed(evt);
            }
        });
        FormInput.add(BtnKelurahan);
        BtnKelurahan.setBounds(420, 66, 28, 23);

        chkSamaAlamat.setBorder(null);
        chkSamaAlamat.setForeground(new java.awt.Color(0, 0, 0));
        chkSamaAlamat.setText("Data Sama Dengan Persetujuan Rawat Inap");
        chkSamaAlamat.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkSamaAlamat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSamaAlamat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSamaAlamat.setName("chkSamaAlamat"); // NOI18N
        chkSamaAlamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSamaAlamatActionPerformed(evt);
            }
        });
        FormInput.add(chkSamaAlamat);
        chkSamaAlamat.setBounds(455, 66, 300, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Kecamatan : ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 94, 110, 23);

        TnmKec.setEditable(false);
        TnmKec.setForeground(new java.awt.Color(0, 0, 0));
        TnmKec.setName("TnmKec"); // NOI18N
        FormInput.add(TnmKec);
        TnmKec.setBounds(114, 94, 300, 23);

        BtnKecamatan.setForeground(new java.awt.Color(0, 0, 0));
        BtnKecamatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKecamatan.setMnemonic('3');
        BtnKecamatan.setToolTipText("ALt+3");
        BtnKecamatan.setName("BtnKecamatan"); // NOI18N
        BtnKecamatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKecamatanActionPerformed(evt);
            }
        });
        FormInput.add(BtnKecamatan);
        BtnKecamatan.setBounds(420, 94, 28, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Kabupaten : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 122, 110, 23);

        TnmKab.setEditable(false);
        TnmKab.setForeground(new java.awt.Color(0, 0, 0));
        TnmKab.setName("TnmKab"); // NOI18N
        FormInput.add(TnmKab);
        TnmKab.setBounds(114, 122, 400, 23);

        BtnKabupaten.setForeground(new java.awt.Color(0, 0, 0));
        BtnKabupaten.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKabupaten.setMnemonic('4');
        BtnKabupaten.setToolTipText("ALt+4");
        BtnKabupaten.setName("BtnKabupaten"); // NOI18N
        BtnKabupaten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKabupatenActionPerformed(evt);
            }
        });
        FormInput.add(BtnKabupaten);
        BtnKabupaten.setBounds(517, 122, 28, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("No. Telp./HP : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 150, 110, 23);

        TnoTelp.setBackground(new java.awt.Color(245, 250, 240));
        TnoTelp.setForeground(new java.awt.Color(0, 0, 0));
        TnoTelp.setName("TnoTelp"); // NOI18N
        TnoTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoTelpKeyPressed(evt);
            }
        });
        FormInput.add(TnoTelp);
        TnoTelp.setBounds(114, 150, 130, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Akan Menanggung Seluruh Denda Pelayanan Awal & Akhir Atas Nama Tersebut Dibawah Ini :");
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(114, 178, 540, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tanggal : ");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(550, 527, 60, 23);

        Ttgl.setEditable(false);
        Ttgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-02-2026" }));
        Ttgl.setDisplayFormat("dd-MM-yyyy");
        Ttgl.setName("Ttgl"); // NOI18N
        Ttgl.setOpaque(false);
        Ttgl.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(Ttgl);
        Ttgl.setBounds(612, 527, 90, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Nama Petugas : ");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(0, 527, 110, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        FormInput.add(TnmPetugas);
        TnmPetugas.setBounds(114, 527, 400, 23);

        BtnPetugas.setForeground(new java.awt.Color(0, 0, 0));
        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setMnemonic('4');
        BtnPetugas.setToolTipText("ALt+4");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        FormInput.add(BtnPetugas);
        BtnPetugas.setBounds(515, 527, 28, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Tgl. Daftar/Reg. : ");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(370, 499, 100, 23);

        TtglReg.setEditable(false);
        TtglReg.setForeground(new java.awt.Color(0, 0, 0));
        TtglReg.setName("TtglReg"); // NOI18N
        FormInput.add(TtglReg);
        TtglReg.setBounds(473, 499, 150, 23);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbPeserta.setAutoCreateRowSorter(true);
        tbPeserta.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPeserta.setName("tbPeserta"); // NOI18N
        tbPeserta.getTableHeader().setReorderingAllowed(false);
        tbPeserta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPesertaMouseClicked(evt);
            }
        });
        tbPeserta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPesertaKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbPeserta);

        FormInput.add(Scroll1);
        Scroll1.setBounds(114, 290, 630, 120);

        BtnPasien.setForeground(new java.awt.Color(0, 0, 0));
        BtnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/users.png"))); // NOI18N
        BtnPasien.setMnemonic('2');
        BtnPasien.setText("Cari Pasien");
        BtnPasien.setName("BtnPasien"); // NOI18N
        BtnPasien.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasienActionPerformed(evt);
            }
        });
        FormInput.add(BtnPasien);
        BtnPasien.setBounds(610, 206, 120, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Nama Pasien : ");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 206, 110, 23);

        TnmPasien.setForeground(new java.awt.Color(0, 0, 0));
        TnmPasien.setName("TnmPasien"); // NOI18N
        TnmPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmPasienKeyPressed(evt);
            }
        });
        FormInput.add(TnmPasien);
        TnmPasien.setBounds(114, 206, 310, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("No. Kartu : ");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 234, 110, 23);

        TnoKartu.setForeground(new java.awt.Color(0, 0, 0));
        TnoKartu.setName("TnoKartu"); // NOI18N
        TnoKartu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoKartuKeyPressed(evt);
            }
        });
        FormInput.add(TnoKartu);
        TnoKartu.setBounds(114, 234, 110, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Jab./Pekerjaan : ");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 262, 110, 23);

        Tpekerjaan.setForeground(new java.awt.Color(0, 0, 0));
        Tpekerjaan.setName("Tpekerjaan"); // NOI18N
        Tpekerjaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpekerjaanKeyPressed(evt);
            }
        });
        FormInput.add(Tpekerjaan);
        Tpekerjaan.setBounds(114, 262, 310, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Status :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(425, 206, 85, 23);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Peserta", "Istri", "Suami", "Anak", "Tambahan" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbStatus);
        cmbStatus.setBounds(515, 206, 85, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Tanggungan :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(425, 234, 85, 23);

        TnmTanggungan.setForeground(new java.awt.Color(0, 0, 0));
        TnmTanggungan.setName("TnmTanggungan"); // NOI18N
        TnmTanggungan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmTanggunganKeyPressed(evt);
            }
        });
        FormInput.add(TnmTanggungan);
        TnmTanggungan.setBounds(515, 234, 230, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Keterangan :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(425, 262, 85, 23);

        Tket.setForeground(new java.awt.Color(0, 0, 0));
        Tket.setName("Tket"); // NOI18N
        Tket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketKeyPressed(evt);
            }
        });
        FormInput.add(Tket);
        Tket.setBounds(515, 262, 230, 23);

        BtnTambahPasien.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahPasien.setText("Tambah");
        BtnTambahPasien.setToolTipText("Tambah Pasien");
        BtnTambahPasien.setName("BtnTambahPasien"); // NOI18N
        BtnTambahPasien.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnTambahPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahPasienActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambahPasien);
        BtnTambahPasien.setBounds(750, 290, 90, 26);

        BtnSimpanPasien.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanPasien.setText("Simpan");
        BtnSimpanPasien.setToolTipText("Simpan Pasien");
        BtnSimpanPasien.setName("BtnSimpanPasien"); // NOI18N
        BtnSimpanPasien.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnSimpanPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanPasienActionPerformed(evt);
            }
        });
        FormInput.add(BtnSimpanPasien);
        BtnSimpanPasien.setBounds(750, 320, 90, 26);

        BtnHapusPasien.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusPasien.setText("Hapus");
        BtnHapusPasien.setToolTipText("Hapus Pasien");
        BtnHapusPasien.setName("BtnHapusPasien"); // NOI18N
        BtnHapusPasien.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnHapusPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusPasienActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusPasien);
        BtnHapusPasien.setBounds(750, 350, 90, 26);

        BtnGantiPasien.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiPasien.setText("Ganti");
        BtnGantiPasien.setToolTipText("Ganti Pasien");
        BtnGantiPasien.setName("BtnGantiPasien"); // NOI18N
        BtnGantiPasien.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnGantiPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiPasienActionPerformed(evt);
            }
        });
        FormInput.add(BtnGantiPasien);
        BtnGantiPasien.setBounds(750, 380, 90, 26);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("Akan Melakukan Pembayaran Melalui :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(114, 415, 190, 23);

        chkSatu.setBackground(new java.awt.Color(255, 255, 250));
        chkSatu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkSatu);
        chkSatu.setForeground(new java.awt.Color(0, 0, 0));
        chkSatu.setText("1 (satu) kali tahap pembayaran");
        chkSatu.setBorderPainted(true);
        chkSatu.setBorderPaintedFlat(true);
        chkSatu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSatu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSatu.setName("chkSatu"); // NOI18N
        chkSatu.setOpaque(false);
        chkSatu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSatu);
        chkSatu.setBounds(309, 415, 190, 23);

        chkTiga.setBackground(new java.awt.Color(255, 255, 250));
        chkTiga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkTiga);
        chkTiga.setForeground(new java.awt.Color(0, 0, 0));
        chkTiga.setText("3 (tiga) kali tahap pembayaran");
        chkTiga.setBorderPainted(true);
        chkTiga.setBorderPaintedFlat(true);
        chkTiga.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTiga.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTiga.setName("chkTiga"); // NOI18N
        chkTiga.setOpaque(false);
        chkTiga.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTiga);
        chkTiga.setBounds(510, 415, 190, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("Bersedia Menerima Pengembalian Dana Yang Telah Dibayarkan (Refund) Ke :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(114, 443, 380, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Nama Bank : ");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(0, 471, 110, 23);

        TnmBank.setForeground(new java.awt.Color(0, 0, 0));
        TnmBank.setName("TnmBank"); // NOI18N
        TnmBank.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmBankKeyPressed(evt);
            }
        });
        FormInput.add(TnmBank);
        TnmBank.setBounds(114, 471, 250, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("No. Rekening : ");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(0, 499, 110, 23);

        TnoRek.setForeground(new java.awt.Color(0, 0, 0));
        TnoRek.setName("TnoRek"); // NOI18N
        TnoRek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoRekKeyPressed(evt);
            }
        });
        FormInput.add(TnoRek);
        TnoRek.setBounds(114, 499, 250, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Atas Nama : ");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(365, 471, 80, 23);

        TatasNm.setForeground(new java.awt.Color(0, 0, 0));
        TatasNm.setName("TatasNm"); // NOI18N
        TatasNm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TatasNmKeyPressed(evt);
            }
        });
        FormInput.add(TatasNm);
        TatasNm.setBounds(447, 471, 250, 23);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: TANDA TANGAN :.", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        LoadHTML1.setBorder(null);
        LoadHTML1.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML1.setName("LoadHTML1"); // NOI18N
        Scroll5.setViewportView(LoadHTML1);

        FormInput.add(Scroll5);
        Scroll5.setBounds(860, 300, 260, 240);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

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

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Cetak Dalam Bentuk :");
        jLabel63.setName("jLabel63"); // NOI18N
        jLabel63.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass8.add(jLabel63);

        cmbPilihCetak.setForeground(new java.awt.Color(0, 0, 0));
        cmbPilihCetak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TTD (Tablet/Tab)", "TTD Basah" }));
        cmbPilihCetak.setName("cmbPilihCetak"); // NOI18N
        cmbPilihCetak.setPreferredSize(new java.awt.Dimension(115, 23));
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

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Tgl. Surat :");
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel24);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-02-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari1);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("s.d.");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel25);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-02-2026" }));
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

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbSurat.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki/dihapus");
        tbSurat.setComponentPopupMenu(jPopupMenu1);
        tbSurat.setName("tbSurat"); // NOI18N
        tbSurat.getTableHeader().setReorderingAllowed(false);
        tbSurat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSuratMouseClicked(evt);
            }
        });
        tbSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSuratKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbSurat);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (norawat.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya..!!");
        } else if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Nama peserta BPJS yang akan membayar denda biaya perawatan harus dipilih dulu..!!");
            BtnPasien.requestFocus();
        } else {
            if (chkSatu.isSelected() == true) {
                cekSatu = "ya";
            } else {
                cekSatu = "tidak";
            }
            
            if (chkTiga.isSelected() == true) {
                cekTiga = "ya";
            } else {
                cekTiga = "tidak";
            }
            
            if (Sequel.menyimpantf("surat_pernyataan_bayar_denda", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 17, new String[]{
                norawat, Tnm.getText(), Talamat.getText(), kdkel, kdkec, kdkab, TnoTelp.getText(), Temail.getText(), cekSatu, cekTiga,
                TnmBank.getText(), TnoRek.getText(), TatasNm.getText(), Valid.SetTgl(Ttgl.getSelectedItem() + ""), nipPtgs, "", 
                Sequel.cariIsi("select now()")
            }) == true) {

                if (tbPeserta.getRowCount() != 0) {
                    for (i = 0; i < tbPeserta.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("peserta_bpjs_bayar_denda",
                                "'" + norawat + "',"
                                + "'" + tbPeserta.getValueAt(i, 1).toString() + "',"
                                + "'" + tbPeserta.getValueAt(i, 2).toString() + "',"
                                + "'" + tbPeserta.getValueAt(i, 3).toString() + "',"
                                + "'" + tbPeserta.getValueAt(i, 4).toString() + "',"
                                + "'" + tbPeserta.getValueAt(i, 5).toString() + "',"
                                + "'" + tbPeserta.getValueAt(i, 6).toString() + "',"
                                + "'" + tbPeserta.getValueAt(i, 7).toString() + "'", "Data Pembayar Denda");
                    }
                }
                
                Sequel.SimpanHistoriRekamMedis(norawat, "Surat Pernyataan Pembayaran Denda", "Simpan");
                TCari.setText(norawat);
                tampil();
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, Tnm, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        tampil();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (tbSurat.getSelectedRow() > -1) {
            if (chkSatu.isSelected() == true) {
                cekSatu = "ya";
            } else {
                cekSatu = "tidak";
            }
            
            if (chkTiga.isSelected() == true) {
                cekTiga = "ya";
            } else {
                cekTiga = "tidak";
            }

            if (Sequel.mengedittf("surat_pernyataan_bayar_denda", "no_rawat=?", "nm_berttd=?, alamat=?, kd_kel=?, kd_kec=?, kd_kab=?, "
                    + "no_tlp=?, email=?, satu_kali=?, tiga_kali=?, bank=?, no_rekening=?, atas_nama=?, tanggal=?, nip_petugas=?", 15, new String[]{
                        Tnm.getText(), Talamat.getText(), kdkel, kdkec, kdkab, TnoTelp.getText(), Temail.getText(), cekSatu, cekTiga,
                        TnmBank.getText(), TnoRek.getText(), TatasNm.getText(), Valid.SetTgl(Ttgl.getSelectedItem() + ""), nipPtgs,
                        tbSurat.getValueAt(tbSurat.getSelectedRow(), 0).toString()
                    }) == true) {

                if (tbPeserta.getRowCount() != 0) {
                    if (Sequel.queryu2tf("delete from peserta_bpjs_bayar_denda where no_rawat=?", 1, new String[]{
                        tbSurat.getValueAt(tbSurat.getSelectedRow(), 0).toString()
                    }) == true) {
                        for (i = 0; i < tbPeserta.getRowCount(); i++) {
                            Sequel.menyimpanIgnore("peserta_bpjs_bayar_denda",
                                    "'" + norawat + "',"
                                    + "'" + tbPeserta.getValueAt(i, 1).toString() + "',"
                                    + "'" + tbPeserta.getValueAt(i, 2).toString() + "',"
                                    + "'" + tbPeserta.getValueAt(i, 3).toString() + "',"
                                    + "'" + tbPeserta.getValueAt(i, 4).toString() + "',"
                                    + "'" + tbPeserta.getValueAt(i, 5).toString() + "',"
                                    + "'" + tbPeserta.getValueAt(i, 6).toString() + "',"
                                    + "'" + tbPeserta.getValueAt(i, 7).toString() + "'", "Data Pembayar Denda");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                    }
                }

                Sequel.SimpanHistoriRekamMedis(tbSurat.getValueAt(tbSurat.getSelectedRow(), 0).toString(), "Surat Pernyataan Pembayaran Denda", "Ganti");
                TCari.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 0).toString());
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
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
        } else {
            Valid.pindah(evt, BtnCari, Tnm);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbSuratMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSuratMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbSuratMouseClicked

    private void tbSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSuratKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbSuratKeyPressed

    private void TemailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TemailKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnPasien.requestFocus();
        }
    }//GEN-LAST:event_TemailKeyPressed

    private void TalamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalamatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnKelurahan.requestFocus();
        }
    }//GEN-LAST:event_TalamatKeyPressed

    private void BtnKelurahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKelurahanActionPerformed
        akses.setform("RMSuratPenyataanBayarDenda");
        kel.setSize(703, 384);
        kel.setLocationRelativeTo(internalFrame1);
        kel.setVisible(true);
        kel.TCari.requestFocus();
        kel.isCek();
    }//GEN-LAST:event_BtnKelurahanActionPerformed

    private void chkSamaAlamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSamaAlamatActionPerformed
        if (chkSamaAlamat.isSelected() == true) {
            try {
                ps1 = koneksi.prepareStatement("select p.*, kl.nm_kel, kc.nm_kec, kb.nm_kab from persetujuan_ranap p "
                    + "inner join kelurahan kl on kl.kd_kel=p.kd_kel "
                    + "inner join kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "inner join kabupaten kb on kb.kd_kab=p.kd_kab where p.no_rawat='" + norawat + "'");
                try {
                    rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        Tnm.setText(rs1.getString("nm_berttd"));
                        Talamat.setText(rs1.getString("alamat"));
                        kdkel = rs1.getString("kd_kel");
                        kdkec = rs1.getString("kd_kec");
                        kdkab = rs1.getString("kd_kab");
                        TnmKel.setText(rs1.getString("nm_kel"));
                        TnmKec.setText(rs1.getString("nm_kec"));
                        TnmKab.setText(rs1.getString("nm_kab"));                        
                        TnoTelp.setText(rs1.getString("no_tlp"));
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
        } else {
            Tnm.setText("");
            Talamat.setText("");
            kdkel = "0";
            kdkec = "0";
            kdkab = "0";
            TnmKel.setText("-");
            TnmKec.setText("-");
            TnmKab.setText("-");
            TnoTelp.setText("");
        }
    }//GEN-LAST:event_chkSamaAlamatActionPerformed

    private void BtnKecamatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKecamatanActionPerformed
        akses.setform("RMSuratPenyataanBayarDenda");
        kec.setSize(703, 384);
        kec.setLocationRelativeTo(internalFrame1);
        kec.setVisible(true);
        kec.TCari.requestFocus();
        kec.isCek();
    }//GEN-LAST:event_BtnKecamatanActionPerformed

    private void BtnKabupatenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKabupatenActionPerformed
        akses.setform("RMSuratPenyataanBayarDenda");
        kab.setSize(703, 384);
        kab.setLocationRelativeTo(internalFrame1);
        kab.setVisible(true);
        kab.TCari.requestFocus();
        kab.isCek();
    }//GEN-LAST:event_BtnKabupatenActionPerformed

    private void TnoTelpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoTelpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Temail.requestFocus();
        }
    }//GEN-LAST:event_TnoTelpKeyPressed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        akses.setform("RMSuratPenyataanBayarDenda");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbSurat.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from surat_pernyataan_bayar_denda where no_rawat=?", 1, new String[]{
                    tbSurat.getValueAt(tbSurat.getSelectedRow(), 0).toString()
                }) == true) {
                    Sequel.meghapus("peserta_bpjs_bayar_denda", "no_rawat", tbSurat.getValueAt(tbSurat.getSelectedRow(), 0).toString());
                    if (!idFileNmBerttd.equals("")) {
                        Sequel.hapusSemuaTtd(idFileNmBerttd);
                    }
                    tampil();
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void TnmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Talamat.requestFocus();
        }
    }//GEN-LAST:event_TnmKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbSurat.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("nmBerttd", Tnm.getText());
            
            if (TnoTelp.getText().equals("")) {
                param.put("notelp", "..........................");
            } else {
                param.put("notelp", TnoTelp.getText());
            }
            
            param.put("alamat", Talamat.getText() + ", Kel. " + TnmKel.getText() + ", Kec. " + TnmKec.getText() + ", Kab. " + TnmKab.getText());            

            if (Temail.getText().equals("")) {
                param.put("email", "..........................");
            } else {
                param.put("email", Temail.getText());
            }
            
            if (chkSatu.isSelected() == true) {
                param.put("satuKali", "V");
            } else {
                param.put("satuKali", "");
            }
            
            if (chkTiga.isSelected() == true) {
                param.put("tigaKali", "V");
            } else {
                param.put("tigaKali", "");
            }
            
            param.put("tgl", "Martapura, " + Valid.SetTglINDONESIA(Valid.SetTgl(Ttgl.getSelectedItem() + "")));
            
            if (cmbPilihCetak.getSelectedIndex() == 0) {
                if (nipPtgs.equals("") || nipPtgs.equals("-") || nipPtgs.equals("--")) {
                    JOptionPane.showMessageDialog(rootPane, "Nama petugas TPPRI harus diisi dulu,..");
                } else {
                    try {
                        String gambar = "", ipGambar = "";
                        try {
                            //cek atau ping ip addres
                            ipGambar = "192.168.0.230";
                            InetAddress inet = InetAddress.getByName(ipGambar);

                            //ping sukses timeout 100 ms (0.1 detik)
                            if (inet.isReachable(100)) {
                                if (idFileNmBerttd.equals("")) {
                                    gambar = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                                } else {
                                    gambar = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFileNmBerttd;
                                }
                                //ping gagal
                            } else {
                                gambar = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                            }
                        } catch (Exception e) {
                            System.out.println("Notif : " + e);
                            gambar = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                        }

                        param.put("gambarTtd", gambar);
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }

                    Valid.MyReport("rptSuratPernyataanBayarDendaTablet.jasper", "report", "::[ Surat Pernyataan Pembayaran Denda Pelayanan & Penggunaan Selisih Denda Pelayanan Kesehatan ]::",
                            "select p.*, sp.bank, sp.no_rekening, sp.atas_nama from peserta_bpjs_bayar_denda p "
                            + "inner join surat_pernyataan_bayar_denda sp on sp.no_rawat=p.no_rawat where "
                            + "p.no_rawat='" + norawat + "' order by p.waktu_simpan", param);
                }
            } else {
                Valid.MyReport("rptSuratPernyataanBayarDenda.jasper", "report", "::[ Surat Pernyataan Pembayaran Denda Pelayanan & Penggunaan Selisih Denda Pelayanan Kesehatan ]::",
                        "select p.*, sp.bank, sp.no_rekening, sp.atas_nama from peserta_bpjs_bayar_denda p "
                        + "inner join surat_pernyataan_bayar_denda sp on sp.no_rawat=p.no_rawat where "
                        + "p.no_rawat='" + norawat + "' order by p.waktu_simpan", param);
            }

            BtnBatalActionPerformed(null);
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih/klik dulu salah satu datanya pada tabel..!!!");
            tbSurat.requestFocus();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnGanti, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void tbPesertaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPesertaMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataPeserta();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPesertaMouseClicked

    private void tbPesertaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPesertaKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataPeserta();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPesertaKeyPressed

    private void BtnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasienActionPerformed
        akses.setform("RMSuratPenyataanBayarDenda");
        pasien.emptTeks();
        pasien.isCek();
        pasien.TCari.setText(norm);
        pasien.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setAlwaysOnTop(false);
        pasien.setVisible(true);
    }//GEN-LAST:event_BtnPasienActionPerformed

    private void BtnTambahPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahPasienActionPerformed
        TnmPasien.setText("");
        TnoKartu.setText("");
        Tpekerjaan.setText("");
        cmbStatus.setSelectedIndex(0);
        TnmTanggungan.setText("");
        Tket.setText("");
    }//GEN-LAST:event_BtnTambahPasienActionPerformed

    private void BtnSimpanPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanPasienActionPerformed
        if (norawat.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya..!!");
        } else if (TnmPasien.getText().equals("")) {
            Valid.textKosong(TnmPasien, "Nama Pasien");
        } else {
            x = 1;
            cekSama = 0;
            
            for (i = 0; i < tbPeserta.getRowCount(); i++) {
                if (!tbPeserta.getValueAt(i, 1).toString().equals("")) {
                    x++;
                }
                
                if (tbPeserta.getValueAt(i, 1).toString().equals(TnmPasien.getText()) && tbPeserta.getValueAt(i, 2).toString().equals(TnoKartu.getText())) {
                    cekSama++;
                }
            }

            if (cekSama > 0) {
                JOptionPane.showMessageDialog(rootPane, "Pasien BPJS dg. no. kartu tersebut sudah ada tersimpan..!!");
                BtnTambahPasienActionPerformed(null);
            } else {
                tabMode1.addRow(new String[]{x + ".", TnmPasien.getText(), TnoKartu.getText(),
                    Tpekerjaan.getText(), cmbStatus.getSelectedItem().toString(), TnmTanggungan.getText(), Tket.getText(),
                    Sequel.cariIsi("select now()")
                });
                BtnTambahPasienActionPerformed(null);
            }
        }
    }//GEN-LAST:event_BtnSimpanPasienActionPerformed

    private void BtnHapusPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusPasienActionPerformed
        if (tbPeserta.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data pembayar denda peserta BPJS yang bisa dihapus..!!");
        } else {
            if (tbPeserta.getSelectedRow() > -1) {
                tabMode1.removeRow(tbPeserta.getSelectedRow());
                BtnTambahPasienActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel peserta BPJS yang akan membayar denda..!!");
                tbPeserta.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusPasienActionPerformed

    private void BtnGantiPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiPasienActionPerformed
        if (tbPeserta.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data pembayar denda peserta BPJS yang bisa diganti..!!");
        } else {
            if (tbPeserta.getSelectedRow() > -1) {
                if (norawat.equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya..!!");
                } else if (TnmPasien.getText().equals("")) {
                    Valid.textKosong(TnmPasien, "Nama Pasien");
                } else {
                    tabMode1.addRow(new String[]{tbPeserta.getValueAt(tbPeserta.getSelectedRow(), 0).toString(),
                        TnmPasien.getText(), TnoKartu.getText(), Tpekerjaan.getText(), cmbStatus.getSelectedItem().toString(),
                        TnmTanggungan.getText(), Tket.getText(), tbPeserta.getValueAt(tbPeserta.getSelectedRow(), 7).toString()
                    });

                    tabMode1.removeRow(tbPeserta.getSelectedRow());
                    BtnTambahPasienActionPerformed(null);
                }
            } else {                
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel peserta BPJS yang akan membayar denda..!!");
                tbPeserta.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiPasienActionPerformed

    private void TpekerjaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpekerjaanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbStatus.requestFocus();
        }
    }//GEN-LAST:event_TpekerjaanKeyPressed

    private void TnmTanggunganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmTanggunganKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tket.requestFocus();
        }
    }//GEN-LAST:event_TnmTanggunganKeyPressed

    private void TketKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpanPasien.requestFocus();
        }
    }//GEN-LAST:event_TketKeyPressed

    private void TnmBankKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmBankKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnoRek.requestFocus();
        }
    }//GEN-LAST:event_TnmBankKeyPressed

    private void TnoRekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoRekKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TatasNm.requestFocus();
        }
    }//GEN-LAST:event_TnoRekKeyPressed

    private void TatasNmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TatasNmKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttgl.requestFocus();
        }
    }//GEN-LAST:event_TatasNmKeyPressed

    private void TnmPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmPasienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnoKartu.requestFocus();
        }
    }//GEN-LAST:event_TnmPasienKeyPressed

    private void TnoKartuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoKartuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tpekerjaan.requestFocus();
        }
    }//GEN-LAST:event_TnoKartuKeyPressed

    private void MnHapusTtdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusTtdActionPerformed
        if (tbSurat.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin tanda tangan yang memberikan pernyataan mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                String ipGambar = "";
                try {
                    //cek atau ping ip addres
                    ipGambar = "192.168.0.230";
                    InetAddress inet = InetAddress.getByName(ipGambar);

                    //ping sukses timeout 100 ms (0.1 detik)
                    if (inet.isReachable(100)) {
                        if (idFileNmBerttd.equals("")) {
                            JOptionPane.showMessageDialog(null, "Yang memberikan pernyataan ini belum melakukan tanda tangan...!!!!");
                        } else {
                            if (Sequel.hapusFileTTD(idFileNmBerttd) == true) {
                                Sequel.mengedit("surat_pernyataan_bayar_denda", "no_rawat='" + norawat + "'", "id_file_nm_berttd=''");
                                tampil();
                                emptTeks();
                            }
                        }
                        //ping gagal
                    } else {
                        JOptionPane.showMessageDialog(null, "Koneksi ke server terputus...!!!!");
                    }
                } catch (Exception e) {
                    System.out.println("Notif : " + e);
                }
            } else {
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }//GEN-LAST:event_MnHapusTtdActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMSuratPenyataanBayarDenda dialog = new RMSuratPenyataanBayarDenda(new javax.swing.JFrame(), true);
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
    private widget.Button BtnGantiPasien;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusPasien;
    private widget.Button BtnKabupaten;
    private widget.Button BtnKecamatan;
    private widget.Button BtnKeluar;
    private widget.Button BtnKelurahan;
    private widget.Button BtnPasien;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpanPasien;
    private widget.Button BtnTambahPasien;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private widget.editorpane LoadHTML1;
    private javax.swing.JMenuItem MnHapusTtd;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll5;
    public widget.TextBox TCari;
    private widget.TextBox Talamat;
    private widget.TextBox TatasNm;
    private widget.TextBox Temail;
    private widget.TextBox Tket;
    private widget.TextBox Tnm;
    private widget.TextBox TnmBank;
    private widget.TextBox TnmKab;
    private widget.TextBox TnmKec;
    private widget.TextBox TnmKel;
    private widget.TextBox TnmPasien;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TnmTanggungan;
    private widget.TextBox TnoKartu;
    private widget.TextBox TnoRek;
    private widget.TextBox TnoTelp;
    private widget.TextBox Tpekerjaan;
    private widget.Tanggal Ttgl;
    private widget.TextBox TtglReg;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.CekBox chkSamaAlamat;
    public widget.CekBox chkSatu;
    public widget.CekBox chkTiga;
    private widget.ComboBox cmbPilihCetak;
    private widget.ComboBox cmbStatus;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
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
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel63;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.Table tbPeserta;
    private widget.Table tbSurat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select sp.*, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jenkel, date_format(p.tgl_lahir,'%d-%m-%Y') tglLahir, "
                    + "rp.tgl_registrasi, date_format(rp.tgl_registrasi,'%d-%m-%Y') tglReg, date_format(sp.tanggal,'%d-%m-%Y') tglSurat, "
                    + "pg.nama nmPtgs from surat_pernyataan_bayar_denda sp inner join reg_periksa rp on rp.no_rawat=sp.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg on pg.nik=sp.nip_petugas WHERE "
                    + "sp.tanggal between ? and ? and sp.no_rawat LIKE ? or "
                    + "sp.tanggal between ? and ? and p.no_rkm_medis LIKE ? or "
                    + "sp.tanggal between ? and ? and p.nm_pasien LIKE ? or "
                    + "sp.tanggal between ? and ? and pg.nama LIKE ? ORDER BY sp.waktu_simpan desc");
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
                        rs.getString("nm_berttd"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("jenkel"),
                        rs.getString("tglLahir"),
                        rs.getString("tglReg"),
                        rs.getString("tglSurat"),
                        rs.getString("nmPtgs"),
                        rs.getString("nm_berttd"),
                        rs.getString("alamat"),
                        rs.getString("kd_kel"),
                        rs.getString("kd_kec"),
                        rs.getString("kd_kab"),
                        rs.getString("no_tlp"),
                        rs.getString("email"),
                        rs.getString("satu_kali"),
                        rs.getString("tiga_kali"),
                        rs.getString("bank"),
                        rs.getString("no_rekening"),
                        rs.getString("atas_nama"),
                        rs.getString("tanggal"),
                        rs.getString("nip_petugas"),
                        rs.getString("id_file_nm_berttd"),
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
        Tnm.setText("");
        Tnm.requestFocus();        
        Talamat.setText("");
        kdkel = "0";
        kdkec = "0";
        kdkab = "0";
        TnmKel.setText("-");
        TnmKec.setText("-");
        TnmKab.setText("-");
        TnoTelp.setText("");
        Temail.setText("");
        chkSamaAlamat.setSelected(false);
        TnmPasien.setText("");
        TnoKartu.setText("");
        Tpekerjaan.setText("");        
        cmbStatus.setSelectedIndex(0);
        TnmTanggungan.setText("");
        Tket.setText("");
        Valid.tabelKosong(tabMode1);        
        chkSatu.setSelected(false);
        chkTiga.setSelected(false);
        buttonGroup1.clearSelection();
        TnmBank.setText("");
        TnoRek.setText("");
        TatasNm.setText("");
        Ttgl.setDate(new Date());
        LoadHTML1.setText("");
        
        if (akses.getadmin() == true) {
            nipPtgs = "-";
        } else {
            nipPtgs = akses.getkode();
        }
    }

    private void getData() {
        kdkel = "";
        kdkec = "";
        kdkab = "";
        nipPtgs = "";
        norawat = "";
        idFileNmBerttd = "";
        cekSatu = "";
        cekTiga = "";

        if (tbSurat.getSelectedRow() != -1) {
            norawat = tbSurat.getValueAt(tbSurat.getSelectedRow(), 0).toString();
            Tnm.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 1).toString());            
            Talamat.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 10).toString());            
            kdkel = tbSurat.getValueAt(tbSurat.getSelectedRow(), 11).toString();
            kdkec = tbSurat.getValueAt(tbSurat.getSelectedRow(), 12).toString();
            kdkab = tbSurat.getValueAt(tbSurat.getSelectedRow(), 13).toString();
            TnmKel.setText(Sequel.cariIsi("select nm_kel from kelurahan where kd_kel='" + kdkel + "'"));
            TnmKec.setText(Sequel.cariIsi("select nm_kec from kecamatan where kd_kec='" + kdkec + "'"));
            TnmKab.setText(Sequel.cariIsi("select nm_kab from kabupaten where kd_kab='" + kdkab + "'"));
            TnoTelp.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 14).toString());            
            Temail.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 15).toString());
            isPeserta();            
            cekSatu = tbSurat.getValueAt(tbSurat.getSelectedRow(), 16).toString();
            cekTiga = tbSurat.getValueAt(tbSurat.getSelectedRow(), 17).toString();            
            TnmBank.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 18).toString());
            TnoRek.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 19).toString());
            TatasNm.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 20).toString());            
            Valid.SetTgl(Ttgl, tbSurat.getValueAt(tbSurat.getSelectedRow(), 21).toString());            
            nipPtgs = tbSurat.getValueAt(tbSurat.getSelectedRow(), 22).toString();
            TnmPetugas.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 8).toString());
            idFileNmBerttd = tbSurat.getValueAt(tbSurat.getSelectedRow(), 23).toString();
            TtglReg.setText(Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norawat + "'")));
            tampilTTD();
            
            if (cekSatu.equals("ya")) {
                chkSatu.setSelected(true);
            } else {
                chkSatu.setSelected(false);
            }
            
            if (cekTiga.equals("ya")) {
                chkTiga.setSelected(true);
            } else {
                chkTiga.setSelected(false);
            }
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getbpjs_sep());
        BtnHapus.setEnabled(akses.getbpjs_sep());
        BtnGanti.setEnabled(akses.getbpjs_sep());
        MnHapusTtd.setEnabled(akses.getbpjs_sep());
        BtnPetugas.setEnabled(akses.getadmin());
        
        if (akses.getjml2() >= 1) {
            nipPtgs = akses.getkode();
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmPetugas, nipPtgs);
            if (TnmPetugas.getText().equals("")) {
                TnmPetugas.setText("-");
            }
        }
    }

    public void setData(String norw, String noRM) {
        norawat = norw;
        norm = noRM;
        tglreg = Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norw + "'");
        TtglReg.setText(Valid.SetTglINDONESIA(tglreg));
        Valid.SetTgl(DTPCari1, tglreg);
        DTPCari2.setDate(new Date());
        TCari.setText(norw);
    }

    private void isPeserta() {
        try {
            ps2 = koneksi.prepareStatement("select * from peserta_bpjs_bayar_denda where no_rawat='" + norawat + "' order by waktu_simpan");
            try {
                rs2 = ps2.executeQuery();
                x = 1;
                while (rs2.next()) {
                    boolean sudahAda = false;
                    for (int i = 0; i < tabMode1.getRowCount(); i++) {
                        if (tabMode1.getValueAt(i, 1).toString().equals(rs2.getString("nama"))
                                && tabMode1.getValueAt(i, 2).toString().equals(rs2.getString("no_kartu"))) {
                            sudahAda = true;
                            break;
                        }
                    }

                    if (!sudahAda) {
                        tabMode1.addRow(new String[]{
                            x + ".",
                            rs2.getString("nama"),
                            rs2.getString("no_kartu"),
                            rs2.getString("pekerjaan"),
                            rs2.getString("status"),
                            rs2.getString("nm_penanggung"),
                            rs2.getString("keterangan"),
                            rs2.getString("waktu_simpan")
                        });
                        x++;
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
    
    private void getDataPeserta() {
        if (tbPeserta.getSelectedRow() != -1) {
            TnmPasien.setText(tbPeserta.getValueAt(tbPeserta.getSelectedRow(), 1).toString());
            TnoKartu.setText(tbPeserta.getValueAt(tbPeserta.getSelectedRow(), 2).toString());
            Tpekerjaan.setText(tbPeserta.getValueAt(tbPeserta.getSelectedRow(), 3).toString());
            cmbStatus.setSelectedItem(tbPeserta.getValueAt(tbPeserta.getSelectedRow(), 4).toString());
            TnmTanggungan.setText(tbPeserta.getValueAt(tbPeserta.getSelectedRow(), 5).toString());
            Tket.setText(tbPeserta.getValueAt(tbPeserta.getSelectedRow(), 6).toString());
        }
    }
    
    private void tampilTTD() {
        try {
            StringBuilder htmlContent = new StringBuilder();
            String gambar = "", ipGambar = "";
            try {
                //cek atau ping ip addres
                ipGambar = "192.168.0.230";
                InetAddress inet = InetAddress.getByName(ipGambar);
                
                //ping sukses timeout 100 ms (0.1 detik)
                if (inet.isReachable(100)) {
                    if (idFileNmBerttd.equals("")) {
                        gambar = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                    } else {
                        gambar = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFileNmBerttd;
                    }
                    //ping gagal
                } else {
                    gambar = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
                gambar = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
            }
            
            htmlContent.append(
                    "<table width='100%' class='isi'>"
                    + "<thead>"
                    + "<tr class='isi'>"
                    + "<td align='center' bgcolor='#f8fdf3'><b>Yang Memberikan Pernyataan</b></td>"
                    + "</tr>"
                    + "</thead>"
                    + "<tbody>"
            );

            htmlContent.append(
                    "<tr class='isi'>"
                    + "<td valign='middle' align='center'><img src='" + gambar + "' width='160' height='160' alt='TTD Pemberi Pernyataan'><br>(" + Tnm.getText() + ")<br></td>"
                    + "</tr>"
            );

            htmlContent.append("</tbody>"
                    + "</table>");

            LoadHTML1.setText(
                    "<html>"
                    + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                    + htmlContent.toString()
                    + "</table>"
                    + "</html>");
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void awalData() {
        tampil();
    }
}
