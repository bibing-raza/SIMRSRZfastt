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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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

/**
 *
 * @author dosen
 */
public class RMObservasiKala1 extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private int i = 0, x = 0;
    private String nipPtgs = "", tglreg = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMObservasiKala1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang Rawat", "Tgl. Observasi", "Jam Obs.",
            "Frek.", "Durasi", "Kekuatan", "DJJ", "TD", "Nadi", "Saturasi", "Respirasi", "Dosis", "Oral", "Cairan + Dosis",
            "TPM", "Periksa Dalam", "Nama Petugas", "tgl_observasi", "jam_observasi", "nip_petugas", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbObservasi.setModel(tabMode);
        tbObservasi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObservasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 25; i++) {
            TableColumn column = tbObservasi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(150);
            } else if (i == 9) {
                column.setPreferredWidth(150);
            } else if (i == 10) {
                column.setPreferredWidth(150);
            } else if (i == 11) {
                column.setPreferredWidth(70);
            } else if (i == 12) {
                column.setPreferredWidth(70);
            } else if (i == 13) {
                column.setPreferredWidth(70);
            } else if (i == 14) {
                column.setPreferredWidth(70);
            } else if (i == 15) {
                column.setPreferredWidth(200);
            } else if (i == 16) {
                column.setPreferredWidth(200);
            } else if (i == 17) {
                column.setPreferredWidth(200);
            } else if (i == 18) {
                column.setPreferredWidth(200);
            } else if (i == 19) {
                column.setPreferredWidth(300);
            } else if (i == 20) {
                column.setPreferredWidth(220);
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
        tbObservasi.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbObservasi.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbObservasi.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbObservasi.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbObservasi.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbObservasi.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        Tfrek.setDocument(new batasInput((int) 50).getKata(Tfrek));
        Tdurasi.setDocument(new batasInput((int) 20).getKata(Tdurasi));
        Tkekuatan.setDocument(new batasInput((int) 20).getKata(Tkekuatan));
        Tdjj.setDocument(new batasInput((int) 50).getKata(Tdjj));
        Ttd.setDocument(new batasInput((int) 7).getKata(Ttd));
        Tnadi.setDocument(new batasInput((int) 7).getKata(Tnadi));
        Tsaturasi.setDocument(new batasInput((int) 7).getKata(Tsaturasi));
        Trespi.setDocument(new batasInput((int) 7).getKata(Trespi));
        Tdosis.setDocument(new batasInput((int) 150).getKata(Tdosis));
        Toral.setDocument(new batasInput((int) 150).getKata(Toral));
        Tcairan.setDocument(new batasInput((int) 150).getKata(Tcairan));
        Ttpm.setDocument(new batasInput((int) 150).getKata(Ttpm));
        
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
        jLabel19 = new widget.Label();
        Ttgl = new widget.Tanggal();
        jLabel20 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel21 = new widget.Label();
        TnmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        jLabel10 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel205 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel206 = new widget.Label();
        jLabel207 = new widget.Label();
        Tfrek = new widget.TextBox();
        jLabel208 = new widget.Label();
        Tdurasi = new widget.TextBox();
        jLabel209 = new widget.Label();
        Tkekuatan = new widget.TextBox();
        jLabel210 = new widget.Label();
        Tdjj = new widget.TextBox();
        jLabel211 = new widget.Label();
        jLabel212 = new widget.Label();
        Ttd = new widget.TextBox();
        jLabel213 = new widget.Label();
        Tnadi = new widget.TextBox();
        jLabel214 = new widget.Label();
        jLabel215 = new widget.Label();
        Tsaturasi = new widget.TextBox();
        jLabel216 = new widget.Label();
        Trespi = new widget.TextBox();
        jLabel217 = new widget.Label();
        jLabel218 = new widget.Label();
        jLabel219 = new widget.Label();
        Tdosis = new widget.TextBox();
        jLabel220 = new widget.Label();
        Toral = new widget.TextBox();
        jLabel221 = new widget.Label();
        Tcairan = new widget.TextBox();
        jLabel222 = new widget.Label();
        Ttpm = new widget.TextBox();
        jLabel223 = new widget.Label();
        Tperiksa = new widget.TextBox();
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
        tbObservasi = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Observasi Kala 1 (Kebidanan) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 388));
        FormInput.setLayout(null);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tanggal : ");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(445, 38, 70, 23);

        Ttgl.setEditable(false);
        Ttgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-02-2026" }));
        Ttgl.setDisplayFormat("dd-MM-yyyy");
        Ttgl.setName("Ttgl"); // NOI18N
        Ttgl.setOpaque(false);
        Ttgl.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(Ttgl);
        Ttgl.setBounds(520, 38, 90, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Jam : ");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(610, 38, 50, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(665, 38, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(715, 38, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(765, 38, 45, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Nama Petugas : ");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(0, 346, 100, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        FormInput.add(TnmPetugas);
        TnmPetugas.setBounds(105, 346, 400, 23);

        BtnPetugas.setForeground(new java.awt.Color(0, 0, 0));
        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setToolTipText("ALt+4");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        FormInput.add(BtnPetugas);
        BtnPetugas.setBounds(507, 346, 28, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 100, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(105, 10, 131, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(239, 10, 100, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(344, 10, 380, 23);

        jLabel205.setForeground(new java.awt.Color(0, 0, 0));
        jLabel205.setText("Ruang Rawat :");
        jLabel205.setName("jLabel205"); // NOI18N
        FormInput.add(jLabel205);
        jLabel205.setBounds(0, 38, 100, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(105, 38, 340, 23);

        jLabel206.setForeground(new java.awt.Color(0, 0, 0));
        jLabel206.setText("HIS :");
        jLabel206.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel206.setName("jLabel206"); // NOI18N
        FormInput.add(jLabel206);
        jLabel206.setBounds(0, 66, 100, 23);

        jLabel207.setForeground(new java.awt.Color(0, 0, 0));
        jLabel207.setText("Frek :");
        jLabel207.setName("jLabel207"); // NOI18N
        FormInput.add(jLabel207);
        jLabel207.setBounds(105, 66, 65, 23);

        Tfrek.setForeground(new java.awt.Color(0, 0, 0));
        Tfrek.setName("Tfrek"); // NOI18N
        Tfrek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TfrekKeyPressed(evt);
            }
        });
        FormInput.add(Tfrek);
        Tfrek.setBounds(175, 66, 270, 23);

        jLabel208.setForeground(new java.awt.Color(0, 0, 0));
        jLabel208.setText("Durasi :");
        jLabel208.setName("jLabel208"); // NOI18N
        FormInput.add(jLabel208);
        jLabel208.setBounds(105, 94, 65, 23);

        Tdurasi.setForeground(new java.awt.Color(0, 0, 0));
        Tdurasi.setName("Tdurasi"); // NOI18N
        Tdurasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdurasiKeyPressed(evt);
            }
        });
        FormInput.add(Tdurasi);
        Tdurasi.setBounds(175, 94, 270, 23);

        jLabel209.setForeground(new java.awt.Color(0, 0, 0));
        jLabel209.setText("Kekuatan :");
        jLabel209.setName("jLabel209"); // NOI18N
        FormInput.add(jLabel209);
        jLabel209.setBounds(105, 122, 65, 23);

        Tkekuatan.setForeground(new java.awt.Color(0, 0, 0));
        Tkekuatan.setName("Tkekuatan"); // NOI18N
        Tkekuatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkekuatanKeyPressed(evt);
            }
        });
        FormInput.add(Tkekuatan);
        Tkekuatan.setBounds(175, 122, 270, 23);

        jLabel210.setForeground(new java.awt.Color(0, 0, 0));
        jLabel210.setText("DJJ : ");
        jLabel210.setName("jLabel210"); // NOI18N
        FormInput.add(jLabel210);
        jLabel210.setBounds(445, 66, 70, 23);

        Tdjj.setForeground(new java.awt.Color(0, 0, 0));
        Tdjj.setName("Tdjj"); // NOI18N
        Tdjj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdjjKeyPressed(evt);
            }
        });
        FormInput.add(Tdjj);
        Tdjj.setBounds(520, 66, 200, 23);

        jLabel211.setForeground(new java.awt.Color(0, 0, 0));
        jLabel211.setText("Vital Sign : ");
        jLabel211.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel211.setName("jLabel211"); // NOI18N
        FormInput.add(jLabel211);
        jLabel211.setBounds(0, 150, 100, 23);

        jLabel212.setForeground(new java.awt.Color(0, 0, 0));
        jLabel212.setText("T. Darah :");
        jLabel212.setName("jLabel212"); // NOI18N
        FormInput.add(jLabel212);
        jLabel212.setBounds(105, 150, 65, 23);

        Ttd.setForeground(new java.awt.Color(0, 0, 0));
        Ttd.setName("Ttd"); // NOI18N
        Ttd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtdKeyPressed(evt);
            }
        });
        FormInput.add(Ttd);
        Ttd.setBounds(175, 150, 70, 23);

        jLabel213.setForeground(new java.awt.Color(0, 0, 0));
        jLabel213.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel213.setText("mmHg      Nadi :");
        jLabel213.setName("jLabel213"); // NOI18N
        FormInput.add(jLabel213);
        jLabel213.setBounds(250, 150, 80, 23);

        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        FormInput.add(Tnadi);
        Tnadi.setBounds(330, 150, 70, 23);

        jLabel214.setForeground(new java.awt.Color(0, 0, 0));
        jLabel214.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel214.setText("x/menit");
        jLabel214.setName("jLabel214"); // NOI18N
        FormInput.add(jLabel214);
        jLabel214.setBounds(405, 150, 50, 23);

        jLabel215.setForeground(new java.awt.Color(0, 0, 0));
        jLabel215.setText("Saturasi :");
        jLabel215.setName("jLabel215"); // NOI18N
        FormInput.add(jLabel215);
        jLabel215.setBounds(105, 178, 65, 23);

        Tsaturasi.setForeground(new java.awt.Color(0, 0, 0));
        Tsaturasi.setName("Tsaturasi"); // NOI18N
        Tsaturasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsaturasiKeyPressed(evt);
            }
        });
        FormInput.add(Tsaturasi);
        Tsaturasi.setBounds(175, 178, 70, 23);

        jLabel216.setForeground(new java.awt.Color(0, 0, 0));
        jLabel216.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel216.setText("%     Respirasi :");
        jLabel216.setName("jLabel216"); // NOI18N
        FormInput.add(jLabel216);
        jLabel216.setBounds(250, 178, 80, 23);

        Trespi.setForeground(new java.awt.Color(0, 0, 0));
        Trespi.setName("Trespi"); // NOI18N
        Trespi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrespiKeyPressed(evt);
            }
        });
        FormInput.add(Trespi);
        Trespi.setBounds(330, 178, 70, 23);

        jLabel217.setForeground(new java.awt.Color(0, 0, 0));
        jLabel217.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel217.setText("x/menit");
        jLabel217.setName("jLabel217"); // NOI18N
        FormInput.add(jLabel217);
        jLabel217.setBounds(405, 178, 50, 23);

        jLabel218.setForeground(new java.awt.Color(0, 0, 0));
        jLabel218.setText("Induksi : ");
        jLabel218.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel218.setName("jLabel218"); // NOI18N
        FormInput.add(jLabel218);
        jLabel218.setBounds(0, 206, 100, 23);

        jLabel219.setForeground(new java.awt.Color(0, 0, 0));
        jLabel219.setText("Misoprostol (Dosis) :");
        jLabel219.setName("jLabel219"); // NOI18N
        FormInput.add(jLabel219);
        jLabel219.setBounds(105, 206, 120, 23);

        Tdosis.setForeground(new java.awt.Color(0, 0, 0));
        Tdosis.setName("Tdosis"); // NOI18N
        Tdosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdosisKeyPressed(evt);
            }
        });
        FormInput.add(Tdosis);
        Tdosis.setBounds(230, 206, 360, 23);

        jLabel220.setForeground(new java.awt.Color(0, 0, 0));
        jLabel220.setText("Misoprostol (Oral/Vag) :");
        jLabel220.setName("jLabel220"); // NOI18N
        FormInput.add(jLabel220);
        jLabel220.setBounds(85, 234, 140, 23);

        Toral.setForeground(new java.awt.Color(0, 0, 0));
        Toral.setName("Toral"); // NOI18N
        Toral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ToralKeyPressed(evt);
            }
        });
        FormInput.add(Toral);
        Toral.setBounds(230, 234, 360, 23);

        jLabel221.setForeground(new java.awt.Color(0, 0, 0));
        jLabel221.setText("Oxytocine (Cairan + Dosis) :");
        jLabel221.setName("jLabel221"); // NOI18N
        FormInput.add(jLabel221);
        jLabel221.setBounds(75, 262, 150, 23);

        Tcairan.setForeground(new java.awt.Color(0, 0, 0));
        Tcairan.setName("Tcairan"); // NOI18N
        Tcairan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcairanKeyPressed(evt);
            }
        });
        FormInput.add(Tcairan);
        Tcairan.setBounds(230, 262, 360, 23);

        jLabel222.setForeground(new java.awt.Color(0, 0, 0));
        jLabel222.setText("Oxytocine (TPM) :");
        jLabel222.setName("jLabel222"); // NOI18N
        FormInput.add(jLabel222);
        jLabel222.setBounds(75, 290, 150, 23);

        Ttpm.setForeground(new java.awt.Color(0, 0, 0));
        Ttpm.setName("Ttpm"); // NOI18N
        Ttpm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtpmKeyPressed(evt);
            }
        });
        FormInput.add(Ttpm);
        Ttpm.setBounds(230, 290, 360, 23);

        jLabel223.setForeground(new java.awt.Color(0, 0, 0));
        jLabel223.setText("Periksa Dalam :");
        jLabel223.setName("jLabel223"); // NOI18N
        FormInput.add(jLabel223);
        jLabel223.setBounds(0, 318, 100, 23);

        Tperiksa.setForeground(new java.awt.Color(0, 0, 0));
        Tperiksa.setName("Tperiksa"); // NOI18N
        Tperiksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TperiksaKeyPressed(evt);
            }
        });
        FormInput.add(Tperiksa);
        Tperiksa.setBounds(105, 318, 630, 23);

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
        jLabel24.setText("Tgl. Observasi :");
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass10.add(jLabel24);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-02-2026" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-02-2026" }));
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

        tbObservasi.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki/dihapus");
        tbObservasi.setName("tbObservasi"); // NOI18N
        tbObservasi.getTableHeader().setReorderingAllowed(false);
        tbObservasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObservasiMouseClicked(evt);
            }
        });
        tbObservasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObservasiKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObservasi);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (Sequel.menyimpantf("observasi_kala1_kebidanan", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 19, new String[]{
                TNoRw.getText(), TrgRawat.getText(), Valid.SetTgl(Ttgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                Tfrek.getText(), Tdurasi.getText(), Tkekuatan.getText(), Tdjj.getText(), Ttd.getText(), Tnadi.getText(), Tsaturasi.getText(), Trespi.getText(),
                Tdosis.getText(), Toral.getText(), Tcairan.getText(), Ttpm.getText(), Tperiksa.getText(), nipPtgs,
                Sequel.cariIsi("select now()"), ""
            }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Observasi Kala 1", "Simpan");
                TCari.setText(TNoRw.getText());
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
        } else {
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (tbObservasi.getSelectedRow() > -1) {
            if (Sequel.mengedittf("observasi_kala1_kebidanan", "waktu_simpan=?", "tgl_observasi=?, jam_observasi=?, frek=?, durasi=?, kekuatan=?, "
                    + "djj=?, td=?, nadi=?, saturasi=?, respirasi=?, dosis=?, oral=?, cairan_dosis=?, tpm=?, periksa_dalam=?, nip_petugas=?", 17, new String[]{
                        Valid.SetTgl(Ttgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        Tfrek.getText(), Tdurasi.getText(), Tkekuatan.getText(), Tdjj.getText(), Ttd.getText(), Tnadi.getText(), Tsaturasi.getText(), Trespi.getText(),
                        Tdosis.getText(), Toral.getText(), Tcairan.getText(), Ttpm.getText(), Tperiksa.getText(), nipPtgs,
                        tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 24).toString()
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Observasi Kala 1", "Ganti");
                TCari.setText(TNoRw.getText());
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
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnBatal,TCari);}
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

    private void tbObservasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObservasiMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbObservasiMouseClicked

    private void tbObservasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObservasiKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObservasiKeyPressed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        akses.setform("RMObservasiKala1");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbObservasi.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from observasi_kala1_kebidanan where waktu_simpan=?", 1, new String[]{
                    tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 24).toString()
                }) == true) {
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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbObservasi.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));

            Valid.MyReport("rptObservasiKala1.jasper", "report", "::[ Observasi Kala 1 (Kebidanan) ]::",
                    "select okk.*, date_format(okk.tgl_observasi ,'%d-%m-%Y') tglObs, time_format(okk.jam_observasi,'%H:%i Wita') jamObs, "
                    + "pg.nama nmPtgs from observasi_kala1_kebidanan okk inner join pegawai pg on pg.nik=okk.nip_petugas "
                    + "WHERE okk.no_rawat ='" + TNoRw.getText() + "' ORDER BY okk.waktu_simpan", param);            

            BtnBatalActionPerformed(null);
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih/klik dulu salah satu datanya pada tabel..!!!");
            tbObservasi.requestFocus();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnGanti, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void TfrekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfrekKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdurasi.requestFocus();
        }
    }//GEN-LAST:event_TfrekKeyPressed

    private void TdurasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdurasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkekuatan.requestFocus();
        }
    }//GEN-LAST:event_TdurasiKeyPressed

    private void TkekuatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkekuatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdjj.requestFocus();
        }
    }//GEN-LAST:event_TkekuatanKeyPressed

    private void TdjjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdjjKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttd.requestFocus();
        }
    }//GEN-LAST:event_TdjjKeyPressed

    private void TtdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tnadi.requestFocus();
        }
    }//GEN-LAST:event_TtdKeyPressed

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsaturasi.requestFocus();
        }
    }//GEN-LAST:event_TnadiKeyPressed

    private void TsaturasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsaturasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trespi.requestFocus();
        }
    }//GEN-LAST:event_TsaturasiKeyPressed

    private void TrespiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrespiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdosis.requestFocus();
        }
    }//GEN-LAST:event_TrespiKeyPressed

    private void TdosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdosisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Toral.requestFocus();
        }
    }//GEN-LAST:event_TdosisKeyPressed

    private void ToralKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ToralKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tcairan.requestFocus();
        }
    }//GEN-LAST:event_ToralKeyPressed

    private void TcairanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcairanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttpm.requestFocus();
        }
    }//GEN-LAST:event_TcairanKeyPressed

    private void TtpmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtpmKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tperiksa.requestFocus();
        }
    }//GEN-LAST:event_TtpmKeyPressed

    private void TperiksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TperiksaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnPetugas.requestFocus();
        }
    }//GEN-LAST:event_TperiksaKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMObservasiKala1 dialog = new RMObservasiKala1(new javax.swing.JFrame(), true);
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
    private widget.Button BtnPetugas;
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
    private widget.TextBox Tcairan;
    private widget.TextBox Tdjj;
    private widget.TextBox Tdosis;
    private widget.TextBox Tdurasi;
    private widget.TextBox Tfrek;
    private widget.TextBox Tkekuatan;
    private widget.TextBox Tnadi;
    private widget.TextBox TnmPetugas;
    private widget.TextBox Toral;
    private widget.TextBox Tperiksa;
    private widget.TextBox Trespi;
    private widget.TextBox TrgRawat;
    private widget.TextBox Tsaturasi;
    private widget.TextBox Ttd;
    private widget.Tanggal Ttgl;
    private widget.TextBox Ttpm;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel10;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel205;
    private widget.Label jLabel206;
    private widget.Label jLabel207;
    private widget.Label jLabel208;
    private widget.Label jLabel209;
    private widget.Label jLabel21;
    private widget.Label jLabel210;
    private widget.Label jLabel211;
    private widget.Label jLabel212;
    private widget.Label jLabel213;
    private widget.Label jLabel214;
    private widget.Label jLabel215;
    private widget.Label jLabel216;
    private widget.Label jLabel217;
    private widget.Label jLabel218;
    private widget.Label jLabel219;
    private widget.Label jLabel220;
    private widget.Label jLabel221;
    private widget.Label jLabel222;
    private widget.Label jLabel223;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.Table tbObservasi;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select okk.*, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tglLahir, date_format(okk.tgl_observasi ,'%d-%m-%Y') tglObs, "
                    + "time_format(okk.jam_observasi,'%H:%i Wita') jamObs, pg.nama nmPtgs from observasi_kala1_kebidanan okk "
                    + "inner join reg_periksa rp on rp.no_rawat=okk.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg on pg.nik=okk.nip_petugas WHERE "
                    + "okk.tgl_observasi between ? and ? and okk.no_rawat LIKE ? or "
                    + "okk.tgl_observasi between ? and ? and p.no_rkm_medis LIKE ? or "
                    + "okk.tgl_observasi between ? and ? and p.nm_pasien LIKE ? or "
                    + "okk.tgl_observasi between ? and ? and pg.nama LIKE ? or "
                    + "okk.tgl_observasi between ? and ? and okk.ruang_rawat LIKE ? ORDER BY okk.waktu_simpan desc");
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tglLahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tglObs"),
                        rs.getString("jamObs"),                        
                        rs.getString("frek"),
                        rs.getString("durasi"),
                        rs.getString("kekuatan"),
                        rs.getString("djj"),
                        rs.getString("td"),
                        rs.getString("nadi"),
                        rs.getString("saturasi"),
                        rs.getString("respirasi"),
                        rs.getString("dosis"),
                        rs.getString("oral"),
                        rs.getString("cairan_dosis"),
                        rs.getString("tpm"),
                        rs.getString("periksa_dalam"),                        
                        rs.getString("nmPtgs"),
                        rs.getString("tgl_observasi"),
                        rs.getString("jam_observasi"),
                        rs.getString("nip_petugas"),
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
        Ttgl.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        
        Tfrek.setText("");
        Tdurasi.setText("");
        Tkekuatan.setText("");
        Tdjj.setText("");
        
        Ttd.setText("");
        Tnadi.setText("");
        Tsaturasi.setText("");
        Trespi.setText("");
        Tdosis.setText("");
        Toral.setText("");
        Tcairan.setText("");
        Ttpm.setText("");
        Tperiksa.setText("");
        
        if (akses.getadmin() == true) {
            nipPtgs = "-";
        } else {
            nipPtgs = akses.getkode();
        }
    }

    private void getData() {
        nipPtgs = "";        

        if (tbObservasi.getSelectedRow() != -1) {
            TNoRw.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 0).toString());            
            TNoRM.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 1).toString());
            TPasien.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 4).toString());
            Valid.SetTgl(Ttgl, tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 21).toString());
            cmbJam.setSelectedItem(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 22).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 22).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 22).toString().substring(6, 8));            
            Tfrek.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 7).toString());
            Tdurasi.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 8).toString());
            Tkekuatan.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 9).toString());
            Tdjj.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 10).toString());            
            Ttd.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 11).toString());
            Tnadi.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 12).toString());
            Tsaturasi.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 13).toString());
            Trespi.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 14).toString());
            Tdosis.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 15).toString());
            Toral.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 16).toString());
            Tcairan.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 17).toString());
            Ttpm.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 18).toString());
            Tperiksa.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 19).toString());            
            nipPtgs = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 23).toString();
            TnmPetugas.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 20).toString());
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        BtnGanti.setEnabled(akses.getcppt());
        BtnPetugas.setEnabled(akses.getadmin());
        
        if (akses.getjml2() >= 1) {            
            BtnPetugas.setEnabled(false);
            nipPtgs = akses.getkode();
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmPetugas, nipPtgs);
            if (TnmPetugas.getText().equals("")) {
                nipPtgs = "";
            }
        }
    }

    public void setData(String norw, String norm, String nmpasien, String ruangrw) {
        TNoRw.setText(norw);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        TrgRawat.setText(ruangrw);
        tglreg = Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norw + "'");
        Valid.SetTgl(DTPCari1, tglreg);
        DTPCari2.setDate(new Date());
        TCari.setText(norw);
    }
}
