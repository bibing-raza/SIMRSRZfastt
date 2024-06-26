/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * DlgPasienMati.java
 *
 * Created on Aug 30, 2010, 7:46:01 AM
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen3
 */
public class DlgPasienMati extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private DlgPasien pasien = new DlgPasien(null, false);
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat dateformatNORW = new SimpleDateFormat("yyyy/MM/dd");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private Date date = new Date();
    private DlgCariPenyakit penyakit = new DlgCariPenyakit(null, false);
    private DlgCariPenyakit penyakit1 = new DlgCariPenyakit(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private PreparedStatement ps, ps3;
    private ResultSet rs;
    private int x = 0;
    private String sql = " pasien_mati.no_rkm_medis=pasien.no_rkm_medis  ", umur = "0", nipDokter = "",
            sttsumur = "Th", a, b, noSurat = "", regBulan = "", thmati = "", blmati = "";
    private String now = dateformat.format(date), timeIn = timeFormat.format(date);
    private double cek = 0, pasienIGD = 0;
    private Date date2 = new Date(), timeOut, dateIn, dateOut, timeIn2;
    private Calendar date3 = Calendar.getInstance();

    /**
     * Creates new form DlgPasienMati
     *
     * @param parent
     * @param modal
     */
    public DlgPasienMati(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10, 2);
        setSize(628, 674);

        Object[] row = {"Tanggal", "Jam", "No.R.Medik", "Nama Pasien", "J.K.", "Tmp.Lahir",
            "Tgl.Lahir", "G.D.", "Stts.Nikah", "Agama", "Keterangan", "Tempat Meninggal",
            "ICD-X", "Final UCod", "Antara 1", "Antara 2", "Langsung", "Unit Asal", "No. Surat", "Reg. Bulan", 
            "Dokter Yang Menerangkan", "nipdokter"};

        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbMati.setModel(tabMode);
        tbMati.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbMati.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 22; i++) {
            TableColumn column = tbMati.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } else if (i == 4) {
                column.setPreferredWidth(30);
            } else if (i == 5) {
                column.setPreferredWidth(120);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(30);
            } else if (i == 8) {
                column.setPreferredWidth(90);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(170);
            } else if (i == 11) {
                column.setPreferredWidth(120);
            } else if (i == 12) {
                column.setPreferredWidth(65);
            } else if (i == 13) {
                column.setPreferredWidth(65);
            } else if (i == 14) {
                column.setPreferredWidth(65);
            } else if (i == 15) {
                column.setPreferredWidth(65);
            } else if (i == 16) {
                column.setPreferredWidth(65);
            } else if (i == 17) {
                column.setPreferredWidth(90);
            } else if (i == 18) {
                column.setPreferredWidth(100);
            } else if (i == 19) {
                column.setPreferredWidth(70);
            } else if (i == 20) {
                column.setPreferredWidth(250);
            } else if (i == 21) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbMati.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRM.setDocument(new batasInput((byte) 15).getKata(TNoRM));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TKtg.setDocument(new batasInput((byte) 100).getKata(TKtg));
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

        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPasienMati")) {
                    if (pasien.getTable().getSelectedRow() != -1) {
                        TNoRM.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 1).toString());
                        TPasien.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 2).toString());
                    }
                    //TNoRM.requestFocus();
                    tampil();
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
                if (akses.getform().equals("DlgPasienMati")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pasien.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
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
                if (akses.getform().equals("DlgCariPeriksaRadiologi")) {
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
                if (akses.getform().equals("DlgCariPeriksaRadiologi")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pasien.penjab.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        penyakit.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (penyakit.getTable().getSelectedRow() != -1) {
                    icd1.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 0).toString());
                    //nmpenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),1).toString());
                }
                TKtg.requestFocus();
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

        penyakit1.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (penyakit1.getTable().getSelectedRow() != -1) {
                    icd5.setText(penyakit1.getTable().getValueAt(penyakit1.getTable().getSelectedRow(), 0).toString());
                    //nmpenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),1).toString());
                }
                TKtg.requestFocus();
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPasienMati")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        nipDokter = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                        Tdokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter.requestFocus();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnCetakSuratMati = new javax.swing.JMenuItem();
        MnFormulirKematian = new javax.swing.JMenuItem();
        MnTindakan = new javax.swing.JMenuItem();
        MnNomorSurat = new javax.swing.JMenuItem();
        MnAngkutJenazah = new javax.swing.JMenuItem();
        TNoReg = new widget.TextBox();
        TNoRw = new widget.TextBox();
        StatusReg = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbMati = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelBiasa1 = new widget.PanelBiasa();
        jLabel8 = new widget.Label();
        jLabel4 = new widget.Label();
        jLabel9 = new widget.Label();
        TKtg = new widget.TextBox();
        TPasien = new widget.TextBox();
        DTPTgl = new widget.Tanggal();
        TNoRM = new widget.TextBox();
        BtnSeek = new widget.Button();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel10 = new widget.Label();
        jLabel5 = new widget.Label();
        jLabel11 = new widget.Label();
        jLabel12 = new widget.Label();
        jLabel13 = new widget.Label();
        jLabel14 = new widget.Label();
        jLabel15 = new widget.Label();
        icd1 = new widget.TextBox();
        icd2 = new widget.TextBox();
        icd3 = new widget.TextBox();
        icd4 = new widget.TextBox();
        icd5 = new widget.TextBox();
        tmptmeninggal = new widget.ComboBox();
        jLabel16 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        jLabel17 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        jLabel18 = new widget.Label();
        unitAsal = new widget.ComboBox();
        jLabel19 = new widget.Label();
        kdpnj = new widget.TextBox();
        nmpnj = new widget.TextBox();
        btnPenjab = new widget.Button();
        cekLaporan = new widget.CekBox();
        jLabel20 = new widget.Label();
        jnsLap = new widget.ComboBox();
        BtnPrint = new widget.Button();
        BtnKode = new widget.Button();
        jLabel21 = new widget.Label();
        BtnKode1 = new widget.Button();
        jLabel22 = new widget.Label();
        TNoSurat = new widget.TextBox();
        jLabel23 = new widget.Label();
        TNoRegBulan = new widget.TextBox();
        label15 = new widget.Label();
        Tdokter = new widget.TextBox();
        BtnDokter = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnCetakSuratMati.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakSuratMati.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakSuratMati.setText("Surat Kematian");
        MnCetakSuratMati.setName("MnCetakSuratMati"); // NOI18N
        MnCetakSuratMati.setPreferredSize(new java.awt.Dimension(250, 28));
        MnCetakSuratMati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakSuratMatiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetakSuratMati);

        MnFormulirKematian.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnFormulirKematian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnFormulirKematian.setText("Formulir Keterangan Penyebab Kematian");
        MnFormulirKematian.setName("MnFormulirKematian"); // NOI18N
        MnFormulirKematian.setPreferredSize(new java.awt.Dimension(250, 28));
        MnFormulirKematian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnFormulirKematianActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnFormulirKematian);

        MnTindakan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTindakan.setText("Tindakan & Pemeriksaan Jenazah");
        MnTindakan.setName("MnTindakan"); // NOI18N
        MnTindakan.setPreferredSize(new java.awt.Dimension(250, 28));
        MnTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTindakanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnTindakan);

        MnNomorSurat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnNomorSurat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnNomorSurat.setText("Penomoran Surat Kematian");
        MnNomorSurat.setEnabled(false);
        MnNomorSurat.setName("MnNomorSurat"); // NOI18N
        MnNomorSurat.setPreferredSize(new java.awt.Dimension(250, 28));
        MnNomorSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnNomorSuratActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnNomorSurat);

        MnAngkutJenazah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAngkutJenazah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAngkutJenazah.setText("Surat Angkut Jenazah");
        MnAngkutJenazah.setEnabled(false);
        MnAngkutJenazah.setName("MnAngkutJenazah"); // NOI18N
        MnAngkutJenazah.setPreferredSize(new java.awt.Dimension(250, 28));
        MnAngkutJenazah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAngkutJenazahActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnAngkutJenazah);

        TNoReg.setForeground(new java.awt.Color(0, 0, 0));
        TNoReg.setHighlighter(null);
        TNoReg.setName("TNoReg"); // NOI18N
        TNoReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRegKeyPressed(evt);
            }
        });

        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });

        StatusReg.setForeground(new java.awt.Color(0, 0, 0));
        StatusReg.setHighlighter(null);
        StatusReg.setName("StatusReg"); // NOI18N
        StatusReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatusRegKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pasien Meninggal ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbMati.setAutoCreateRowSorter(true);
        tbMati.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbMati.setComponentPopupMenu(jPopupMenu1);
        tbMati.setName("tbMati"); // NOI18N
        tbMati.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMatiMouseClicked(evt);
            }
        });
        tbMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMatiKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbMati);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(350, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+2");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(150, 23));
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

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(80, 23));
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
        panelBiasa1.setPreferredSize(new java.awt.Dimension(610, 200));
        panelBiasa1.setLayout(null);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Jam :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelBiasa1.add(jLabel8);
        jLabel8.setBounds(209, 10, 40, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rekam Medik :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelBiasa1.add(jLabel4);
        jLabel4.setBounds(0, 40, 115, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Keterangan :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelBiasa1.add(jLabel9);
        jLabel9.setBounds(400, 130, 70, 23);

        TKtg.setForeground(new java.awt.Color(0, 0, 0));
        TKtg.setName("TKtg"); // NOI18N
        TKtg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKtgKeyPressed(evt);
            }
        });
        panelBiasa1.add(TKtg);
        TKtg.setBounds(474, 130, 554, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        panelBiasa1.add(TPasien);
        TPasien.setBounds(203, 40, 390, 23);

        DTPTgl.setEditable(false);
        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-06-2024" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        panelBiasa1.add(DTPTgl);
        DTPTgl.setBounds(118, 10, 90, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        panelBiasa1.add(TNoRM);
        TNoRM.setBounds(118, 40, 80, 23);

        BtnSeek.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek.setMnemonic('1');
        BtnSeek.setToolTipText("Alt+1");
        BtnSeek.setName("BtnSeek"); // NOI18N
        BtnSeek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeekActionPerformed(evt);
            }
        });
        BtnSeek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeekKeyPressed(evt);
            }
        });
        panelBiasa1.add(BtnSeek);
        BtnSeek.setBounds(596, 40, 28, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        panelBiasa1.add(cmbJam);
        cmbJam.setBounds(251, 10, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        panelBiasa1.add(cmbMnt);
        cmbMnt.setBounds(298, 10, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        panelBiasa1.add(cmbDtk);
        cmbDtk.setBounds(345, 10, 45, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Tgl. Meninggal :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelBiasa1.add(jLabel10);
        jLabel10.setBounds(0, 10, 115, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tmp. Meninggal :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelBiasa1.add(jLabel5);
        jLabel5.setBounds(391, 10, 90, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("ICD-X ( Langsung ) :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelBiasa1.add(jLabel11);
        jLabel11.setBounds(360, 100, 110, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Penyebab Kematian :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelBiasa1.add(jLabel12);
        jLabel12.setBounds(0, 70, 115, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("ICD-X ( Dasar/Utama ) :");
        jLabel13.setName("jLabel13"); // NOI18N
        panelBiasa1.add(jLabel13);
        jLabel13.setBounds(117, 70, 130, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("ICD-X ( Antara #1 ) :");
        jLabel14.setName("jLabel14"); // NOI18N
        panelBiasa1.add(jLabel14);
        jLabel14.setBounds(117, 129, 130, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("ICD-X ( Antara #2 ) :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelBiasa1.add(jLabel15);
        jLabel15.setBounds(360, 70, 110, 23);

        icd1.setEditable(false);
        icd1.setForeground(new java.awt.Color(0, 0, 0));
        icd1.setName("icd1"); // NOI18N
        icd1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                icd1KeyPressed(evt);
            }
        });
        panelBiasa1.add(icd1);
        icd1.setBounds(252, 70, 70, 23);

        icd2.setForeground(new java.awt.Color(0, 0, 0));
        icd2.setName("icd2"); // NOI18N
        icd2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                icd2KeyPressed(evt);
            }
        });
        panelBiasa1.add(icd2);
        icd2.setBounds(252, 129, 140, 23);

        icd3.setForeground(new java.awt.Color(0, 0, 0));
        icd3.setName("icd3"); // NOI18N
        icd3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                icd3KeyPressed(evt);
            }
        });
        panelBiasa1.add(icd3);
        icd3.setBounds(474, 70, 124, 23);

        icd4.setForeground(new java.awt.Color(0, 0, 0));
        icd4.setName("icd4"); // NOI18N
        icd4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                icd4KeyPressed(evt);
            }
        });
        panelBiasa1.add(icd4);
        icd4.setBounds(474, 100, 124, 23);

        icd5.setEditable(false);
        icd5.setForeground(new java.awt.Color(0, 0, 0));
        icd5.setName("icd5"); // NOI18N
        icd5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                icd5KeyPressed(evt);
            }
        });
        panelBiasa1.add(icd5);
        icd5.setBounds(252, 100, 70, 23);

        tmptmeninggal.setForeground(new java.awt.Color(0, 0, 0));
        tmptmeninggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Rumah Sakit", "Puskesmas", "Rumah Bersalin", "Rumah Tempat Tinggal", "Lain-lain (Termasuk Doa)", "Tidak tahu", "Dari luar Rumah Sakit" }));
        tmptmeninggal.setName("tmptmeninggal"); // NOI18N
        tmptmeninggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tmptmeninggalKeyPressed(evt);
            }
        });
        panelBiasa1.add(tmptmeninggal);
        tmptmeninggal.setBounds(484, 10, 160, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Tgl. Laporan :");
        jLabel16.setName("jLabel16"); // NOI18N
        panelBiasa1.add(jLabel16);
        jLabel16.setBounds(650, 10, 80, 23);

        Tgl1.setEditable(false);
        Tgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-06-2024" }));
        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setOpaque(false);
        Tgl1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl1KeyPressed(evt);
            }
        });
        panelBiasa1.add(Tgl1);
        Tgl1.setBounds(733, 10, 90, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("s.d");
        jLabel17.setName("jLabel17"); // NOI18N
        panelBiasa1.add(jLabel17);
        jLabel17.setBounds(827, 10, 30, 23);

        Tgl2.setEditable(false);
        Tgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-06-2024" }));
        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setOpaque(false);
        Tgl2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl2KeyPressed(evt);
            }
        });
        panelBiasa1.add(Tgl2);
        Tgl2.setBounds(860, 10, 90, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Unit Asal :");
        jLabel18.setName("jLabel18"); // NOI18N
        panelBiasa1.add(jLabel18);
        jLabel18.setBounds(640, 40, 90, 23);

        unitAsal.setForeground(new java.awt.Color(0, 0, 0));
        unitAsal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua Unit", "Ruangan Inap", "Kamar Jenazah", "IGD" }));
        unitAsal.setName("unitAsal"); // NOI18N
        unitAsal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                unitAsalKeyPressed(evt);
            }
        });
        panelBiasa1.add(unitAsal);
        unitAsal.setBounds(733, 40, 130, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Cara Bayar :");
        jLabel19.setName("jLabel19"); // NOI18N
        panelBiasa1.add(jLabel19);
        jLabel19.setBounds(640, 100, 90, 23);

        kdpnj.setEditable(false);
        kdpnj.setForeground(new java.awt.Color(0, 0, 0));
        kdpnj.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kdpnj.setName("kdpnj"); // NOI18N
        kdpnj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpnjKeyPressed(evt);
            }
        });
        panelBiasa1.add(kdpnj);
        kdpnj.setBounds(733, 100, 60, 23);

        nmpnj.setEditable(false);
        nmpnj.setForeground(new java.awt.Color(0, 0, 0));
        nmpnj.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        nmpnj.setName("nmpnj"); // NOI18N
        panelBiasa1.add(nmpnj);
        nmpnj.setBounds(797, 100, 230, 23);

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
        btnPenjab.setBounds(1030, 100, 28, 23);

        cekLaporan.setForeground(new java.awt.Color(0, 0, 0));
        cekLaporan.setText("Filter Data Laporan");
        cekLaporan.setName("cekLaporan"); // NOI18N
        cekLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekLaporanActionPerformed(evt);
            }
        });
        panelBiasa1.add(cekLaporan);
        cekLaporan.setBounds(955, 10, 120, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Jenis Laporan :");
        jLabel20.setName("jLabel20"); // NOI18N
        panelBiasa1.add(jLabel20);
        jLabel20.setBounds(640, 70, 90, 23);

        jnsLap.setForeground(new java.awt.Color(0, 0, 0));
        jnsLap.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Rekap Semua Unit", "Rekap Per Unit", "Dinkes Kab. Semua Unit", "Dinkes Kab. Per Unit" }));
        jnsLap.setName("jnsLap"); // NOI18N
        jnsLap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jnsLapKeyPressed(evt);
            }
        });
        panelBiasa1.add(jnsLap);
        jnsLap.setBounds(733, 70, 160, 23);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Print Preview");
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
        panelBiasa1.add(BtnPrint);
        BtnPrint.setBounds(900, 70, 120, 23);

        BtnKode.setForeground(new java.awt.Color(0, 0, 0));
        BtnKode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKode.setMnemonic('1');
        BtnKode.setToolTipText("Alt+1");
        BtnKode.setName("BtnKode"); // NOI18N
        BtnKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKodeActionPerformed(evt);
            }
        });
        BtnKode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKodeKeyPressed(evt);
            }
        });
        panelBiasa1.add(BtnKode);
        BtnKode.setBounds(328, 70, 28, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("ICD-X ( Final UCod ) :");
        jLabel21.setName("jLabel21"); // NOI18N
        panelBiasa1.add(jLabel21);
        jLabel21.setBounds(117, 100, 130, 23);

        BtnKode1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKode1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKode1.setMnemonic('1');
        BtnKode1.setToolTipText("Alt+1");
        BtnKode1.setName("BtnKode1"); // NOI18N
        BtnKode1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKode1ActionPerformed(evt);
            }
        });
        BtnKode1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKode1KeyPressed(evt);
            }
        });
        panelBiasa1.add(BtnKode1);
        BtnKode1.setBounds(328, 100, 28, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("No. Surat : 474.3/");
        jLabel22.setName("jLabel22"); // NOI18N
        panelBiasa1.add(jLabel22);
        jLabel22.setBounds(0, 158, 150, 23);

        TNoSurat.setEditable(false);
        TNoSurat.setForeground(new java.awt.Color(0, 0, 0));
        TNoSurat.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TNoSurat.setName("TNoSurat"); // NOI18N
        panelBiasa1.add(TNoSurat);
        TNoSurat.setBounds(153, 158, 220, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("No. Reg. Bulan :");
        jLabel23.setName("jLabel23"); // NOI18N
        panelBiasa1.add(jLabel23);
        jLabel23.setBounds(380, 158, 90, 23);

        TNoRegBulan.setEditable(false);
        TNoRegBulan.setForeground(new java.awt.Color(0, 0, 0));
        TNoRegBulan.setName("TNoRegBulan"); // NOI18N
        panelBiasa1.add(TNoRegBulan);
        TNoRegBulan.setBounds(474, 158, 50, 23);

        label15.setForeground(new java.awt.Color(0, 0, 0));
        label15.setText("Dokter Yang Menerangkan :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        panelBiasa1.add(label15);
        label15.setBounds(560, 158, 170, 23);

        Tdokter.setEditable(false);
        Tdokter.setForeground(new java.awt.Color(0, 0, 0));
        Tdokter.setName("Tdokter"); // NOI18N
        Tdokter.setPreferredSize(new java.awt.Dimension(207, 23));
        panelBiasa1.add(Tdokter);
        Tdokter.setBounds(733, 158, 295, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('2');
        BtnDokter.setToolTipText("Alt+2");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        panelBiasa1.add(BtnDokter);
        BtnDokter.setBounds(1030, 158, 28, 23);

        internalFrame1.add(panelBiasa1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
        Valid.pindah(evt, TCari, cmbJam);
}//GEN-LAST:event_DTPTglKeyPressed

    private void BtnSeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeekActionPerformed
        akses.setform("DlgPasienMati");
        pasien.isiKeterangan("Daftar Pasien Meninggal");
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(true);
}//GEN-LAST:event_BtnSeekActionPerformed

    private void BtnSeekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeekKeyPressed
        Valid.pindah(evt, TNoRM, TKtg);
}//GEN-LAST:event_BtnSeekKeyPressed

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        Valid.pindah(evt, DTPTgl, cmbMnt);
}//GEN-LAST:event_cmbJamKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
        Valid.pindah(evt, cmbJam, cmbDtk);
}//GEN-LAST:event_cmbMntKeyPressed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
        Valid.pindah(evt, cmbMnt, tmptmeninggal);
}//GEN-LAST:event_cmbDtkKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        try {
            now = dateformat.format(date);
            dateIn = dateformat.parse(now);
            dateOut = dateformat.parse(Valid.SetTgl(DTPTgl.getSelectedItem() + ""));
            timeOut = new SimpleDateFormat("HH:mm").parse(cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem());
            date3.add(Calendar.MINUTE, 1);
            timeIn = timeFormat.format(date3.getTime());
            timeIn2 = timeFormat.parse(timeIn);
            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(now);
            
            if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                Valid.textKosong(TNoRM, "pasien");
            } else if (TKtg.getText().trim().equals("")) {
                Valid.textKosong(TKtg, "keterangan");
            } else if ((dateOut.equals(dateIn) && timeOut.after(timeIn2))) {
                JOptionPane.showMessageDialog(null, "Waktu Salah ....");
                cmbJam.requestFocus();
            } else if (dateOut.after(dateIn)) {
                JOptionPane.showMessageDialog(null, "Tanggal Salah ....");
                DTPTgl.requestFocus();
            } else {
                cekDaftar();
                if (cek > 0) {
                    JOptionPane.showMessageDialog(null, "Pasien sudah didaftarkan....");
                } else {
                    isCekPasien();
                    Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_poli='KJH' and tgl_registrasi='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "'", "", 3, TNoReg);
                    Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_rawat,6),signed)),0) from reg_periksa where tgl_registrasi='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "' ", dateformatNORW.format(DTPTgl.getDate()) + "/", 6, TNoRw);

                    Sequel.menyimpantf2("reg_periksa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 18,
                            new String[]{TNoReg.getText(), TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                                "-", TNoRM.getText(), "KJH", "-", "-", "-", 0 + "", "Belum",
                                "Baru", "Ralan", "U01", umur, sttsumur, akses.getkode()});

                    Sequel.menyimpan("pasien_mati", "'" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','"
                            + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "','"
                            + TNoRM.getText() + "','"
                            + TKtg.getText() + "','"
                            + tmptmeninggal.getSelectedItem() + "','"
                            + icd1.getText() + "','"
                            + icd2.getText() + "','"
                            + icd3.getText() + "','"
                            + icd4.getText() + "','Kamar Jenazah','"
                            + icd5.getText() + "','" 
                            + TNoSurat.getText() + "','"
                            + TNoRegBulan.getText() + "','"
                            + nipDokter + "'", "pasien");

                    tampil();
                    emptTeks();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, TKtg, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        unitAsal.setVisible(false);
        jLabel18.setVisible(false);
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        cekDaftar();
        if (cek > 0) {
            JOptionPane.showMessageDialog(null, "Data transaksi telah terverifikasi, cek dikasir rawat jalan....");
            tampil();
            emptTeks();
        } else if (!tbMati.getValueAt(tbMati.getSelectedRow(), 18).toString().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien ini sudah diterbitkan no. suratnya, data tdk. bisa dihapus....");
            tampil();
            emptTeks();
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (pasienIGD > 0) {
                    Sequel.mengedit("data_igd", "(SELECT di.tindakan_lanjut FROM data_igd di "
                            + "INNER JOIN reg_periksa rp ON rp.no_rawat=di.no_rawat "
                            + "WHERE rp.tgl_registrasi='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "' AND rp.no_rkm_medis = '" + TNoRM.getText() + "' "
                            + "AND rp.kd_poli = 'IGDK' and date_format(rp.jam_reg,'%H:%i')='" + Sequel.cariIsi("select date_format(jam,'%H:%i') from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "'") + "') IN ('MENINGGAL DI IGD','D.O.A')",
                            "tindakan_lanjut='-' ");
                    Valid.hapusTable(tabMode, TNoRM, "pasien_mati", "pasien_mati.no_rkm_medis");
                    tampil();
                    emptTeks();
                } else {
                    Valid.hapusTable(tabMode, TNoRM, "pasien_mati", "pasien_mati.no_rkm_medis");
                    tampil();
                    emptTeks();
                }
            }
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnPrint, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        //laporan yang benar
        if ((unitAsal.getSelectedItem().equals("Semua Unit")) && (jnsLap.getSelectedItem().equals("Rekap Semua Unit"))) {
            ctkSemua();
        } else if ((!unitAsal.getSelectedItem().equals("Semua Unit")) && (jnsLap.getSelectedItem().equals("Rekap Per Unit"))) {
            ctkPerUnit();
        } else if ((unitAsal.getSelectedItem().equals("Semua Unit")) && (jnsLap.getSelectedItem().equals("Dinkes Kab. Semua Unit"))) {
            ctkDinkesSemuaUnit();
        } else if ((!unitAsal.getSelectedItem().equals("Semua Unit")) && (jnsLap.getSelectedItem().equals("Dinkes Kab. Per Unit"))) {
            ctkDinkesPerUnit();

            //yang semua unit yang salah
        } else if ((unitAsal.getSelectedItem().equals("Semua Unit")) && (jnsLap.getSelectedItem().equals("Rekap Per Unit"))) {
            JOptionPane.showMessageDialog(null, "Jenis laporan yang akan dicetak salah....");
            jnsLap.requestFocus();
        } else if ((unitAsal.getSelectedItem().equals("Semua Unit")) && (jnsLap.getSelectedItem().equals("Dinkes Kab. Per Unit"))) {
            JOptionPane.showMessageDialog(null, "Jenis laporan yang akan dicetak salah....");
            jnsLap.requestFocus();

            //yang per unit yang salah
        } else if ((!unitAsal.getSelectedItem().equals("Semua Unit")) && (jnsLap.getSelectedItem().equals("Rekap Semua Unit"))) {
            JOptionPane.showMessageDialog(null, "Jenis laporan yang akan dicetak salah....");
            jnsLap.requestFocus();
        } else if ((!unitAsal.getSelectedItem().equals("Semua Unit")) && (jnsLap.getSelectedItem().equals("Dinkes Kab. Semua Unit"))) {
            JOptionPane.showMessageDialog(null, "Jenis laporan yang akan dicetak salah....");
            jnsLap.requestFocus();
        } else if (jnsLap.getSelectedItem().equals("-")) {
            JOptionPane.showMessageDialog(null, "Silakan tentukan pilihan jenis laporan yang akan dicetak....");
            jnsLap.requestFocus();
        }
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

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
        if (cekLaporan.isSelected() == true) {
            tampilLaporan();

            TNoRM.setText("");
            TPasien.setText("");
            TKtg.setText("");
            tmptmeninggal.setSelectedItem("-");
            icd1.setText("");
            icd2.setText("");
            icd3.setText("");
            icd4.setText("");
            icd5.setText("");
            DTPTgl.setDate(new Date());

            TNoRw.setText("");
            TNoReg.setText("");
            StatusReg.setText("");
            unitAsal.setSelectedIndex(0);
            kdpnj.setText("-");
            nmpnj.setText("-");
            jnsLap.setSelectedIndex(0);

            jLabel19.setVisible(false);
            kdpnj.setVisible(false);
            nmpnj.setVisible(false);
            btnPenjab.setVisible(false);

            if (tabMode.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Data sesuai tgl. laporan yang dimaksud tidak ditemukan....");
                Tgl1.requestFocus();
            }

        } else if (cekLaporan.isSelected() == false) {
            tampil();
            emptTeks();
        }
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        if (cekLaporan.isSelected() == true) {
            tampilLaporan();

            TNoRM.setText("");
            TPasien.setText("");
            TKtg.setText("");
            tmptmeninggal.setSelectedItem("-");
            icd1.setText("");
            icd2.setText("");
            icd3.setText("");
            icd4.setText("");
            icd5.setText("");
            DTPTgl.setDate(new Date());

            TNoRw.setText("");
            TNoReg.setText("");
            StatusReg.setText("");
            unitAsal.setSelectedIndex(0);
            kdpnj.setText("-");
            nmpnj.setText("-");
            jnsLap.setSelectedIndex(0);

            jLabel19.setVisible(false);
            kdpnj.setVisible(false);
            nmpnj.setVisible(false);
            btnPenjab.setVisible(false);

            if (tabMode.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Data sesuai tgl. laporan yang dimaksud tidak ditemukan....");
                Tgl1.requestFocus();
            }

        } else if (cekLaporan.isSelected() == false) {
            TCari.setText("");
            tampil();
            emptTeks();
        }
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari.setText("");
            tampil();
        } else {
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbMatiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMatiMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbMatiMouseClicked

    private void tbMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMatiKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbMatiKeyPressed

private void MnCetakSuratMatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakSuratMatiActionPerformed
    if (TPasien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
    } else {
        if (tbMati.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tgl_surat", Valid.SetTglINDONESIA(Sequel.cariIsi("select date(now())")));
            param.put("tgl_lahir", Sequel.cariIsi("select tmp_lahir from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + ", "
                    + Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis='" + TNoRM.getText() + "'")));
            
            if (nipDokter.equals("-")) {
                param.put("nmdokter", "");
                param.put("nipdokter", "");
            } else {
                param.put("nmdokter", Tdokter.getText());
                param.put("nipdokter", "NIP/NR. " + nipDokter);
            }
            
            Valid.MyReport("rptSuratKematian.jasper", "report", "::[ Surat Keterangan Kematian ]::",
                    " SELECT pasien_mati.tanggal, pasien_mati.jam, pasien_mati.no_rkm_medis, "
                    + " pasien.nm_pasien, TIMESTAMPDIFF(Year,pasien.tgl_lahir,CURDATE()) as umur_thn, "
                    + " TIMESTAMPDIFF(MONTH,pasien.tgl_lahir,CURDATE()) % 12 as umur_bln, "
                    + " FLOOR( TIMESTAMPDIFF( DAY, pasien.tgl_lahir, CURDATE() ) % 30.4375 ) as umur_hari, "
                    + " pasien.alamat, pasien.no_ktp, "
                    + " pasien.no_tlp, if(jk = 'L','Laki-laki','Perempuan') AS Kelamin, "
                    + " pasien.gol_darah, pasien.stts_nikah, pasien.agama, pasien_mati.keterangan, "
                    + " kelurahan.nm_kel, kecamatan.nm_kec, kabupaten.nm_kab, "
                    + " ifnull(pasien_mati.no_surat,'.....') nosurat, ifnull(pasien_mati.reg_bulan,'.....') noregbulan "
                    + " FROM pasien_mati, pasien "
                    + " INNER JOIN kabupaten ON kabupaten.kd_kab = pasien.kd_kab "
                    + " INNER JOIN kecamatan ON kecamatan.kd_kec = pasien.kd_kec "
                    + " INNER JOIN kelurahan ON kelurahan.kd_kel = pasien.kd_kel "
                    + " WHERE pasien_mati.no_rkm_medis = pasien.no_rkm_medis "
                    + " and pasien_mati.no_rkm_medis='" + TNoRM.getText() + "' ", param);

            tampil();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik/pilih datanya pada tabel terlebih dahulu..!!!!");
            tbMati.requestFocus();
        }
    }
}//GEN-LAST:event_MnCetakSuratMatiActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void MnAngkutJenazahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAngkutJenazahActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
        } else {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptAngkutJenazah.jasper", "report", "::[ Surat Angkut Jenazah ]::",
                    "select tanggal,jam,pasien_mati.no_rkm_medis,pasien.nm_pasien,pasien.pekerjaan, "
                    + "pasien.umur,pasien.alamat,jk,tmp_lahir,tgl_lahir,gol_darah,stts_nikah, "
                    + "agama,keterangan from pasien_mati,pasien "
                    + "where pasien_mati.no_rkm_medis=pasien.no_rkm_medis "
                    + "and pasien_mati.no_rkm_medis='" + TNoRM.getText() + "' ", param);
        }
    }//GEN-LAST:event_MnAngkutJenazahActionPerformed

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ", TPasien, TNoRM.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnSeekActionPerformed(null);
        } else {
            Valid.pindah(evt, tmptmeninggal, icd1);
        }
    }//GEN-LAST:event_TNoRMKeyPressed

    private void icd1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_icd1KeyPressed
        Valid.pindah(evt, TNoRM, icd2);
    }//GEN-LAST:event_icd1KeyPressed

    private void icd2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_icd2KeyPressed
        Valid.pindah(evt, icd1, icd3);
    }//GEN-LAST:event_icd2KeyPressed

    private void icd3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_icd3KeyPressed
        Valid.pindah(evt, icd2, icd4);
    }//GEN-LAST:event_icd3KeyPressed

    private void icd4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_icd4KeyPressed
        Valid.pindah(evt, icd3, TKtg);
    }//GEN-LAST:event_icd4KeyPressed

    private void tmptmeninggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tmptmeninggalKeyPressed
        Valid.pindah(evt, cmbDtk, TNoRM);
    }//GEN-LAST:event_tmptmeninggalKeyPressed

    private void MnFormulirKematianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnFormulirKematianActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
        } else {
            if (tbMati.getSelectedRow() > -1) {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                param.put("tgl_surat", Valid.SetTglINDONESIA(Sequel.cariIsi("select date(now())")));
                
                if (nipDokter.equals("-")) {
                    param.put("nmdokter", "");
                    param.put("nipdokter", "");
                } else {
                    param.put("nmdokter", Tdokter.getText());
                    param.put("nipdokter", "NIP/NR. " + nipDokter);
                }

                Valid.MyReport("rptFormulirKematianRM.jasper", "report", "::[ Surat Kematian Untuk KELUARGA PASIEN ]::",
                        " SELECT pasien_mati.tanggal, pasien_mati.jam, pasien_mati.no_rkm_medis, "
                        + " pasien.nm_pasien, TIMESTAMPDIFF(Year,pasien.tgl_lahir,CURDATE()) as umur_thn, "
                        + " TIMESTAMPDIFF(MONTH,pasien.tgl_lahir,CURDATE()) % 12 as umur_bln, "
                        + " FLOOR( TIMESTAMPDIFF( DAY, pasien.tgl_lahir, CURDATE() ) % 30.4375 ) as umur_hari, "
                        + " pasien.alamat, pasien.no_ktp, pasien.no_tlp, "
                        + " if(jk = 'L','Laki-laki','Perempuan') AS Kelamin, pasien.tmp_lahir, "
                        + " DATE_FORMAT(pasien.tgl_lahir,'%d') AS tgl_lahir, "
                        + " DATE_FORMAT(pasien.tgl_lahir,'%m') AS bln_lahir, "
                        + " DATE_FORMAT(pasien.tgl_lahir,'%Y') AS thn_lahir, "
                        + " pasien.gol_darah, pasien.stts_nikah, pasien.agama, pasien_mati.keterangan, "
                        + " kelurahan.nm_kel, kecamatan.nm_kec, kabupaten.nm_kab, "
                        + " ifnull(pasien_mati.no_surat,'.....') nosurat, ifnull(pasien_mati.reg_bulan,'.....') noregbulan "
                        + " FROM pasien_mati, pasien "
                        + " INNER JOIN kabupaten ON kabupaten.kd_kab = pasien.kd_kab "
                        + " INNER JOIN kecamatan ON kecamatan.kd_kec = pasien.kd_kec "
                        + " INNER JOIN kelurahan ON kelurahan.kd_kel = pasien.kd_kel "
                        + " WHERE pasien_mati.no_rkm_medis = pasien.no_rkm_medis "
                        + " and pasien_mati.no_rkm_medis='" + TNoRM.getText() + "' ", param);

                tampil();
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan klik/pilih datanya pada tabel terlebih dahulu..!!!!");
                tbMati.requestFocus();
            }
        }
    }//GEN-LAST:event_MnFormulirKematianActionPerformed

    private void Tgl1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tgl1KeyPressed

    private void Tgl2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tgl2KeyPressed

    private void TKtgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKtgKeyPressed
        Valid.pindah(evt, icd4, BtnSimpan);
    }//GEN-LAST:event_TKtgKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoRwKeyPressed

    private void MnTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTindakanActionPerformed
        if (tbMati.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else {
            if (StatusReg.getText().equals("Ralan")) {
                DlgRawatJalan dlgrwjl2 = new DlgRawatJalan(null, false);
                dlgrwjl2.isCek();
                dlgrwjl2.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                dlgrwjl2.setLocationRelativeTo(internalFrame1);
                dlgrwjl2.setNoRm(TNoRw.getText(), DTPTgl.getDate(), DTPTgl.getDate());
                dlgrwjl2.tampilDrPr();
                dlgrwjl2.TotalNominal();
                dlgrwjl2.setVisible(true);
                dlgrwjl2.fokus();
            } else {
                JOptionPane.showMessageDialog(null, "Data tindakan & pemeriksaan tidak dapat dimasukkan, jenazah bukan berasal dari IGD/Kamar Jenazah..!!!!");
                emptTeks();
            }
        }
    }//GEN-LAST:event_MnTindakanActionPerformed

    private void StatusRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatusRegKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatusRegKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih & klik dulu nama pasiennya pada tabel..!!!!");
            tbMati.requestFocus();

        } else if (!TNoRM.getText().equals("")) {
            Sequel.mengedit("pasien_mati", "no_rkm_medis='" + TNoRM.getText() + "'", "tanggal='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "', jam='"
                    + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                    + "no_rkm_medis='" + TNoRM.getText() + "', keterangan='" + TKtg.getText() + "', "
                    + "temp_meninggal='" + tmptmeninggal.getSelectedItem() + "', icd1='" + icd1.getText() + "', "
                    + " icd2='" + icd2.getText() + "', icd3='" + icd3.getText() + "', icd4='" + icd4.getText() + "', "
                    + "icd5='" + icd5.getText() + "',nip_dokter='" + nipDokter + "' ");

            tampil();
            emptTeks();
        }
    }//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGantiKeyPressed

    private void unitAsalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unitAsalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_unitAsalKeyPressed

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
        akses.setform("DlgCariPeriksaRadiologi");
        pasien.penjab.onCari();
        pasien.penjab.isCek();
        pasien.penjab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        pasien.penjab.setLocationRelativeTo(internalFrame1);
        pasien.penjab.setVisible(true);
    }//GEN-LAST:event_btnPenjabActionPerformed

    private void cekLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekLaporanActionPerformed
        if (cekLaporan.isSelected() == true) {
            jLabel18.setVisible(true);
            unitAsal.setVisible(true);
            //jLabel19.setVisible(true);
            //kdpnj.setVisible(true);
            //nmpnj.setVisible(true);
            //btnPenjab.setVisible(true);
            jLabel20.setVisible(true);
            jnsLap.setVisible(true);
            BtnPrint.setVisible(true);

            unitAsal.setSelectedIndex(0);
            kdpnj.setText("-");
            nmpnj.setText("-");
            jnsLap.setSelectedIndex(0);

        } else if (cekLaporan.isSelected() == false) {
            jLabel18.setVisible(false);
            unitAsal.setVisible(false);
            jLabel19.setVisible(false);
            kdpnj.setVisible(false);
            nmpnj.setVisible(false);
            btnPenjab.setVisible(false);
            jLabel20.setVisible(false);
            jnsLap.setVisible(false);
            BtnPrint.setVisible(false);

            unitAsal.setSelectedIndex(0);
            kdpnj.setText("-");
            nmpnj.setText("-");
            jnsLap.setSelectedIndex(0);
        }
    }//GEN-LAST:event_cekLaporanActionPerformed

    private void jnsLapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jnsLapKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jnsLapKeyPressed

    private void BtnKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKodeActionPerformed
        penyakit.isCek();
        penyakit.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
        penyakit.emptTeks();
    }//GEN-LAST:event_BtnKodeActionPerformed

    private void BtnKodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKodeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnKodeKeyPressed

    private void icd5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_icd5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_icd5KeyPressed

    private void BtnKode1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKode1ActionPerformed
        penyakit1.isCek();
        penyakit1.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penyakit1.setLocationRelativeTo(internalFrame1);
        penyakit1.setVisible(true);
        penyakit1.emptTeks();
    }//GEN-LAST:event_BtnKode1ActionPerformed

    private void BtnKode1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKode1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnKode1KeyPressed

    private void MnNomorSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnNomorSuratActionPerformed
        if (TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel....");
            tbMati.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "'") < 1) {
            JOptionPane.showMessageDialog(null, "Data pasien tersebut belum pernah tersimpan di database....");
        } else if (!Sequel.cariIsi("select ifnull(no_surat,'') from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "'").equals("")) {
            JOptionPane.showMessageDialog(null, "Surat keterangan kematian pasien ini sudah ada nomornya...");
            tampil();
            emptTeks();
        } else {
            autoNomorSurat();
        }
    }//GEN-LAST:event_MnNomorSuratActionPerformed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        akses.setform("DlgPasienMati");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void TNoRegKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPasienMati dialog = new DlgPasienMati(new javax.swing.JFrame(), true);
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
    private widget.Button BtnDokter;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKode;
    private widget.Button BtnKode1;
    private widget.Button BtnPrint;
    private widget.Button BtnSeek;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPTgl;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnAngkutJenazah;
    private javax.swing.JMenuItem MnCetakSuratMati;
    private javax.swing.JMenuItem MnFormulirKematian;
    private javax.swing.JMenuItem MnNomorSurat;
    private javax.swing.JMenuItem MnTindakan;
    private widget.ScrollPane Scroll;
    private widget.TextBox StatusReg;
    private widget.TextBox TCari;
    private widget.TextBox TKtg;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoReg;
    private widget.TextBox TNoRegBulan;
    private widget.TextBox TNoRw;
    private widget.TextBox TNoSurat;
    private widget.TextBox TPasien;
    private widget.TextBox Tdokter;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.Button btnPenjab;
    private widget.CekBox cekLaporan;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.TextBox icd1;
    private widget.TextBox icd2;
    private widget.TextBox icd3;
    private widget.TextBox icd4;
    private widget.TextBox icd5;
    private widget.InternalFrame internalFrame1;
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
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.ComboBox jnsLap;
    private widget.TextBox kdpnj;
    private widget.Label label15;
    private widget.TextBox nmpnj;
    private widget.PanelBiasa panelBiasa1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbMati;
    private widget.ComboBox tmptmeninggal;
    private widget.ComboBox unitAsal;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select tanggal,jam,pasien_mati.no_rkm_medis,nm_pasien, "
                    + "jk,tmp_lahir,tgl_lahir,gol_darah,stts_nikah, "
                    + "agama,keterangan,temp_meninggal,icd1,icd5,icd2,icd3,icd4,unit_asal, "
                    + "ifnull(no_surat,'') nosurat, ifnull(reg_bulan,'') regBln, nip_dokter from pasien_mati,pasien where "
                    + sql + "and tanggal like '%" + TCari.getText().trim() + "%' or "
                    + sql + "and pasien_mati.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                    + sql + "and nm_pasien like '%" + TCari.getText().trim() + "%' or "
                    + sql + "and jk like '%" + TCari.getText().trim() + "%' or "
                    + sql + "and tmp_lahir like '%" + TCari.getText().trim() + "%' or "
                    + sql + "and gol_darah like '%" + TCari.getText().trim() + "%' or "
                    + sql + "and stts_nikah like '%" + TCari.getText().trim() + "%' or "
                    + sql + "and agama like '%" + TCari.getText().trim() + "%' or "
                    + sql + "and unit_asal like '%" + TCari.getText().trim() + "%' or "
                    + sql + "and keterangan like '%" + TCari.getText().trim() + "%' "
                    + " order by tanggal desc limit 100");
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16),
                        rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20),
                        Sequel.cariIsi("select nama from pegawai where nik='" + rs.getString("nip_dokter") + "'"),
                        rs.getString("nip_dokter")
                    });
                }
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgPasienMati.tampil() : " + e);
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
        int b = tabMode.getRowCount();
        LCount.setText("" + b);
    }

    public void emptTeks() {
        TNoRM.setText("");
        TPasien.setText("");
        TKtg.setText("-");
        tmptmeninggal.setSelectedItem("-");
        icd1.setText("-");
        icd2.setText("-");
        icd3.setText("-");
        icd4.setText("-");
        icd5.setText("-");
        TNoSurat.setText("");
        TNoRegBulan.setText("");
        DTPTgl.setDate(new Date());
        DTPTgl.requestFocus();

        TNoRw.setText("");
        TNoReg.setText("");
        StatusReg.setText("");
        unitAsal.setSelectedIndex(0);
        kdpnj.setText("-");
        nmpnj.setText("-");
        jnsLap.setSelectedIndex(0);

        cekLaporan.setSelected(false);
        jLabel18.setVisible(false);
        unitAsal.setVisible(false);
        jLabel19.setVisible(false);
        kdpnj.setVisible(false);
        nmpnj.setVisible(false);
        btnPenjab.setVisible(false);
        jLabel20.setVisible(false);
        jnsLap.setVisible(false);
        BtnPrint.setVisible(false);

        DTPTgl.setEditable(false);
        cmbJam.setEditable(false);
        cmbMnt.setEditable(false);
        cmbDtk.setEditable(false);
        tmptmeninggal.setEditable(false);
        Tgl1.setEditable(false);
        Tgl2.setEditable(false);
        unitAsal.setEditable(false);
        jnsLap.setEditable(false);
        nipDokter = "-";
        Tdokter.setText("-");
    }

    private void getData() {
        nipDokter = "";
        if (tbMati.getSelectedRow() != -1) {
            cmbJam.setSelectedItem(tbMati.getValueAt(tbMati.getSelectedRow(), 1).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbMati.getValueAt(tbMati.getSelectedRow(), 1).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbMati.getValueAt(tbMati.getSelectedRow(), 1).toString().substring(6, 8));
            TNoRM.setText(tbMati.getValueAt(tbMati.getSelectedRow(), 2).toString());
            TPasien.setText(tbMati.getValueAt(tbMati.getSelectedRow(), 3).toString());
            TKtg.setText(tbMati.getValueAt(tbMati.getSelectedRow(), 10).toString());
            Valid.SetTgl(DTPTgl, tbMati.getValueAt(tbMati.getSelectedRow(), 0).toString());
            tmptmeninggal.setSelectedItem(tbMati.getValueAt(tbMati.getSelectedRow(), 11).toString());
            icd1.setText(tbMati.getValueAt(tbMati.getSelectedRow(), 12).toString());
            icd5.setText(tbMati.getValueAt(tbMati.getSelectedRow(), 13).toString());
            icd2.setText(tbMati.getValueAt(tbMati.getSelectedRow(), 14).toString());
            icd3.setText(tbMati.getValueAt(tbMati.getSelectedRow(), 15).toString());
            icd4.setText(tbMati.getValueAt(tbMati.getSelectedRow(), 16).toString());
            TNoSurat.setText(tbMati.getValueAt(tbMati.getSelectedRow(), 18).toString());
            TNoRegBulan.setText(tbMati.getValueAt(tbMati.getSelectedRow(), 19).toString());
            Tdokter.setText(tbMati.getValueAt(tbMati.getSelectedRow(), 20).toString());
            nipDokter = tbMati.getValueAt(tbMati.getSelectedRow(), 21).toString();
            //unitAsal.setSelectedItem(tbMati.getValueAt(tbMati.getSelectedRow(), 17).toString());

            a = Sequel.cariIsi("SELECT no_rawat FROM reg_periksa WHERE no_rkm_medis='" + TNoRM.getText() + "' and tgl_registrasi='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "' ");
            TNoRw.setText(a);

            b = Sequel.cariIsi("SELECT status_lanjut FROM reg_periksa WHERE no_rkm_medis='" + TNoRM.getText() + "' and tgl_registrasi='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "' ");
            StatusReg.setText(b);
        }
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.getpasien_meninggal());
        BtnHapus.setEnabled(akses.getpasien_meninggal());
        BtnPrint.setEnabled(akses.getpasien_meninggal());
        MnNomorSurat.setEnabled(akses.getedit_data_kematian());
    }

    public void setNoRm(String norm) {
        TNoRM.setText(norm);
        isPsien();
    }

    private void isPsien() {
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ", TPasien, TNoRM.getText());
    }

    private void isCekPasien() {
        try {
            ps3 = koneksi.prepareStatement("select TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) as tahun, "
                    + "(TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12)) as bulan, "
                    + "TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()) as hari from pasien "
                    + "where pasien.no_rkm_medis=?");
            try {
                ps3.setString(1, TNoRM.getText());
                rs = ps3.executeQuery();
                while (rs.next()) {
                    umur = "0";
                    sttsumur = "Th";
                    if (rs.getInt("tahun") > 0) {
                        umur = rs.getString("tahun");
                        sttsumur = "Th";
                    } else if (rs.getInt("tahun") == 0) {
                        if (rs.getInt("bulan") > 0) {
                            umur = rs.getString("bulan");
                            sttsumur = "Bl";
                        } else if (rs.getInt("bulan") == 0) {
                            umur = rs.getString("hari");
                            sttsumur = "Hr";
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (ps3 != null) {
                    ps3.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cekDaftar() {
        cek = Sequel.cekIGD("select count(1) from reg_periksa where tgl_registrasi = ? and kd_dokter = ? and no_rkm_medis = ? "
                + "and kd_poli = ? and jam_reg = ?", Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "-", TNoRM.getText(), "KJH",
                Sequel.cariIsi("select jam from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "'"));
        pasienIGD = Sequel.cekIGD("select count(1) from reg_periksa where tgl_registrasi = ? and kd_dokter like ? and "
                + "no_rkm_medis = ? and kd_poli = ? and date_format(jam_reg,'%H:%i') = ?", Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "%%", TNoRM.getText(), "IGDK",
                Sequel.cariIsi("select date_format(jam,'%H:%i') from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "'"));
    }

    public void ctkSemua() {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
        Valid.MyReport("rptPasienMati.jasper", "report", "::[ Data Pasien Meninggal Semua Unit ]::",
                " select pasien_mati.tanggal,pasien_mati.jam,pasien_mati.no_rkm_medis,pasien.nm_pasien, "
                + " pasien.jk,pasien.tmp_lahir,pasien.tgl_lahir,pasien.gol_darah,pasien.stts_nikah, "
                + " pasien.agama,pasien_mati.keterangan,ifnull(pasien_mati.temp_meninggal,'-') as temp_meninggal,ifnull(pasien_mati.icd1,'-') as icd1, "
                + " ifnull(pasien_mati.icd2,'-') as icd2,ifnull(pasien_mati.icd3,'-') as icd3,ifnull(pasien_mati.icd4,'-') as icd4, pasien_mati.unit_asal "
                + " FROM pasien_mati INNER JOIN pasien ON pasien_mati.no_rkm_medis = pasien.no_rkm_medis "
                + " where pasien_mati.tanggal between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " order by pasien_mati.tanggal", param);
        this.setCursor(Cursor.getDefaultCursor());
        tampil();
        emptTeks();
    }

    public void ctkPerUnit() {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
        Valid.MyReport("rptPasienMatiUnit.jasper", "report", "::[ Data Pasien Meninggal PerAsal Unit ]::",
                " select pasien_mati.tanggal,pasien_mati.jam,pasien_mati.no_rkm_medis,pasien.nm_pasien, "
                + " pasien.jk,pasien.tmp_lahir,pasien.tgl_lahir,pasien.gol_darah,pasien.stts_nikah, "
                + " pasien.agama,pasien_mati.keterangan,ifnull(pasien_mati.temp_meninggal,'-') as temp_meninggal,ifnull(pasien_mati.icd1,'-') as icd1, "
                + " ifnull(pasien_mati.icd2,'-') as icd2,ifnull(pasien_mati.icd3,'-') as icd3,ifnull(pasien_mati.icd4,'-') as icd4, pasien_mati.unit_asal "
                + " FROM pasien_mati INNER JOIN pasien ON pasien_mati.no_rkm_medis = pasien.no_rkm_medis "
                + " where pasien_mati.tanggal between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " and pasien_mati.unit_asal='" + unitAsal.getSelectedItem() + "' "
                + " order by pasien_mati.tanggal", param);
        this.setCursor(Cursor.getDefaultCursor());
        tampil();
        emptTeks();
    }

    public void ctkDinkesSemuaUnit() {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
        Valid.MyReport("rptDinkesMatiAll.jasper", "report", "::[ Rekap Laporan Kematian Dinkes Kab. Semua Unit ]::",
                " SELECT p.nm_pasien, CONCAT(p.no_ktp,' / ',p.no_rkm_medis) nik_rm, CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                + " IF(p.jk='L','Laki-laki','Perempuan') jns_kelamin, DATE_FORMAT(p.tgl_lahir,'%d/%m/%Y') tgl_lahir, DATE_FORMAT(pm.tanggal,'%d/%m/%Y') tgl_mati, "
                + " IF(py.nm_penyakit IS NULL,'-',py.nm_penyakit) sebab_mati, IF(r.kd_pj in ('B01','D03'),'Ya','Tidak') peserta_jkn, IF(pm.icd5 IS NULL,'-',pm.icd5) ket, pm.unit_asal "
                + " FROM pasien_mati pm INNER JOIN pasien p ON pm.no_rkm_medis = p.no_rkm_medis "
                + " INNER JOIN kelurahan kl ON kl.kd_kel = p.kd_kel "
                + " INNER JOIN kecamatan kc ON kc.kd_kec = p.kd_kec "
                + " INNER JOIN kabupaten kb ON kb.kd_kab = p.kd_kab "
                + " LEFT JOIN reg_periksa r ON r.no_rkm_medis = pm.no_rkm_medis "
                + " INNER JOIN penjab pj ON pj.kd_pj = r.kd_pj "
                + " LEFT JOIN penyakit py ON py.kd_penyakit = pm.icd5 "
                + " WHERE pm.tanggal BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " GROUP BY pm.no_rkm_medis ORDER BY pm.tanggal", param);
        this.setCursor(Cursor.getDefaultCursor());
        tampil();
        emptTeks();
    }

    public void ctkDinkesPerUnit() {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
        Valid.MyReport("rptDinkesMatiPerUnit.jasper", "report", "::[ Rekap Laporan Kematian Dinkes Kab. Per Unit ]::",
                " SELECT p.nm_pasien, CONCAT(p.no_ktp,' / ',p.no_rkm_medis) nik_rm, CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                + " IF(p.jk='L','Laki-laki','Perempuan') jns_kelamin, DATE_FORMAT(p.tgl_lahir,'%d/%m/%Y') tgl_lahir, DATE_FORMAT(pm.tanggal,'%d/%m/%Y') tgl_mati, "
                + " IF(py.nm_penyakit IS NULL,'-',py.nm_penyakit) sebab_mati, IF(r.kd_pj in ('B01','D03'),'Ya','Tidak') peserta_jkn, IF(pm.icd5 IS NULL,'-',pm.icd5) ket, pm.unit_asal "
                + " FROM pasien_mati pm INNER JOIN pasien p ON pm.no_rkm_medis = p.no_rkm_medis "
                + " INNER JOIN kelurahan kl ON kl.kd_kel = p.kd_kel "
                + " INNER JOIN kecamatan kc ON kc.kd_kec = p.kd_kec "
                + " INNER JOIN kabupaten kb ON kb.kd_kab = p.kd_kab "
                + " LEFT JOIN reg_periksa r ON r.no_rkm_medis = pm.no_rkm_medis "
                + " INNER JOIN penjab pj ON pj.kd_pj = r.kd_pj "
                + " LEFT JOIN penyakit py ON py.kd_penyakit = pm.icd5 "
                + " WHERE pm.tanggal BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " AND pm.unit_asal='" + unitAsal.getSelectedItem() + "' "
                + " GROUP BY pm.no_rkm_medis ORDER BY pm.tanggal", param);
        this.setCursor(Cursor.getDefaultCursor());
        tampil();
        emptTeks();
    }

    private void tampilLaporan() {
        Valid.tabelKosong(tabMode);
        try {
            if (unitAsal.getSelectedItem().equals("Semua Unit")) {
                ps = koneksi.prepareStatement("select tanggal,jam,pasien_mati.no_rkm_medis,nm_pasien, "
                        + "jk,tmp_lahir,tgl_lahir,gol_darah,stts_nikah, "
                        + "agama,keterangan,temp_meninggal,icd1,icd5,icd2,icd3,icd4,unit_asal, "
                        + "ifnull(no_surat,'') nosurat, ifnull(reg_bulan,'') regBln, nip_dokter from pasien_mati,pasien where "
                        + sql + "and tanggal between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " order by tanggal ");

            } else if (!unitAsal.getSelectedItem().equals("Semua Unit")) {
                ps = koneksi.prepareStatement("select tanggal,jam,pasien_mati.no_rkm_medis,nm_pasien, "
                        + "jk,tmp_lahir,tgl_lahir,gol_darah,stts_nikah, "
                        + "agama,keterangan,temp_meninggal,icd1,icd5,icd2,icd3,icd4,unit_asal, "
                        + "ifnull(no_surat,'') nosurat, ifnull(reg_bulan,'') regBln, nip_dokter from pasien_mati,pasien where "
                        + sql + "and tanggal between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and unit_asal='" + unitAsal.getSelectedItem() + "' "
                        + " order by tanggal ");
            }

            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16),
                        rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20),
                        Sequel.cariIsi("select nama from pegawai where nik='" + rs.getString("nip_dokter") + "'"),
                        rs.getString("nip_dokter")
                    });
                }
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgPasienMati.tampil() : " + e);
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
        int b = tabMode.getRowCount();
        LCount.setText("" + b);
    }
    
    private void autoNomorSurat() {
        thmati = "";
        blmati = "";

        thmati = Sequel.cariIsi("select date_format(tanggal,'/%Y') from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "'");
        blmati = Sequel.cariIsi("select date_format(tanggal,\"%m/%Y\") from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "'");

        Valid.autoNomer7("select ifnull(MAX(CONVERT(LEFT(no_surat,5),signed)),0) from pasien_mati where "
                + "no_surat like '%" + thmati + "%' ", "/MR/" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(5, 7)
                + "/" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 4), 5, TNoSurat);

        Valid.autoNomer7("select ifnull(MAX(CONVERT(LEFT(reg_bulan,3),signed)),0) from pasien_mati where "
                + "no_surat like '%" + blmati + "%' ", "", 3, TNoRegBulan);

        Sequel.mengedit("pasien_mati", "no_rkm_medis='" + TNoRM.getText() + "'",
                "no_surat='" + TNoSurat.getText() + "', "
                + "reg_bulan='" + TNoRegBulan.getText() + "'");

        JOptionPane.showMessageDialog(null, "Penomoran surat kematian berhasil tersimpan....");
        tampil();
        emptTeks();
    }
}
