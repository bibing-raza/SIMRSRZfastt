package rekammedis;

import bridging.INACBGDaftarKlaim;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.DlgCariPoli;
import simrskhanza.DlgPenanggungJawab;

/**
 *
 * @author dosen
 */
public class DlgInputKodeICD extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabModeDiagnosa, tabModeDiagnosaSekunder, 
            tabModeProsedur, TabModeDiagnosaPasien, TabModeTindakanPasien, tabMode1, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private DlgCariPoli poli = new DlgCariPoli(null, false);
    public DlgPenanggungJawab penjab = new DlgPenanggungJawab(null, false);
    private Properties prop = new Properties();
    private PreparedStatement ps, pspenyakit, psdiagnosapasien, psprosedur, pstindakanpasien, 
            pspenyakitsekunder, psinadrg, psTINinadrg;
    private ResultSet rs, rs1;
    private int i = 0, jml = 0, index = 0, jml1 = 0, s = 0, index1 = 0, cek = 0, 
            cekPremier = 0, cekINADRG = 0, cekPremierINADRG = 0;
    private boolean[] pilih, pilih2, pilih3;
    private String kdpoli = "", kdpenjab = "", norw = "", norm = "", status = "", cekKlaim = "", jlhTindakan = "";
    private String[] kode, nama, ciripny, keterangan, kategori, cirium, kode2, panjang, pendek,
            kode1, nama1, ciripny1, keterangan1, kategori1, cirium1;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgInputKodeICD(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null,new Object[]{
            "noRawat", "Tgl. Kunjungan", "Jam Reg.", "No. RM", "Nama Pasien", "Poliklinik/Inst.", "Cara Bayar", "Status Kode ICD-10",
            "Diag. Resume Dokter", "Diag. Resume Perawat/Bidan", "tgl_reg", "kd_pj", "kd_poli", "Keluhan Catatan Dokter","Keluhan Catatan Perawat/Bidan",
            "Rincian Tindakan Catatan Dokter", "Rincian Tindakan Catatan Perawat/Bidan"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                  boolean a = false;
                  if (colIndex == 0) {
                      a = false;
                  }
                  return a;
             }
             Class[] types = new Class[] {
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class,
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbPasien.setModel(tabMode);
        tbPasien.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 17; i++) {
            TableColumn column = tbPasien.getColumnModel().getColumn(i);
            if (i == 0) {
//                column.setPreferredWidth(35);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(130);
            } else if (i == 7) {
                column.setPreferredWidth(120);
            } else if (i == 8) {
                column.setPreferredWidth(250);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
//                column.setPreferredWidth(350);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
//                column.setPreferredWidth(350);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 12) {
//                column.setPreferredWidth(350);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 13) {
                column.setPreferredWidth(250);
            } else if (i == 14) {
                column.setPreferredWidth(250);
            } else if (i == 15) {
                column.setPreferredWidth(250);
            } else if (i == 16) {
                column.setPreferredWidth(250);
            } 
        }
        tbPasien.setDefaultRenderer(Object.class, new WarnaTable());
        
        TabModeDiagnosaPasien = new DefaultTableModel(null, new Object[]{
            "#", "Tgl.Rawat", "No.Rawat", "No.R.M.", "Nama Pasien", "Kode", "Nama Penyakit", "Status", "Jenis Diagnosa", "Petugas Coding RM"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbDiagnosaPasien.setModel(TabModeDiagnosaPasien);
        tbDiagnosaPasien.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosaPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbDiagnosaPasien.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
//                column.setPreferredWidth(110);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(290);
            } else if (i == 7) {
//                column.setPreferredWidth(100);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setPreferredWidth(85);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            }
        }
        tbDiagnosaPasien.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "P", "Tgl.Rawat", "No.Rawat", "No.R.M.", "Nama Pasien", "Kode", "Nama Penyakit", "Status", "Jenis Diagnosa", "Petugas Coding RM"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbDiagnosaPasien1.setModel(tabMode1);
        tbDiagnosaPasien1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosaPasien1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbDiagnosaPasien1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(110);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            }
        }
        tbDiagnosaPasien1.setDefaultRenderer(Object.class, new WarnaTable());
        
        TabModeTindakanPasien = new DefaultTableModel(null, new Object[]{
            "#", "Tgl.Rawat", "No.Rawat", "No.R.M.", "Nama Pasien", "Kode", "Nama Prosedur", "Status", "Petugas Coding RM"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbTindakanPasien.setModel(TabModeTindakanPasien);
        tbTindakanPasien.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTindakanPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbTindakanPasien.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
//                column.setPreferredWidth(110);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(355);
            } else if (i == 7) {
//                column.setPreferredWidth(100);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setPreferredWidth(250);
            }
        }
        tbTindakanPasien.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "P", "Tgl.Rawat", "No.Rawat", "No.R.M.", "Nama Pasien", "Kode", "Nama Prosedur", "Status","Jlh.", "Petugas Coding RM"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 0) || (colIndex == 8)) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbTindakanPasien1.setModel(tabMode2);
        tbTindakanPasien1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTindakanPasien1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbTindakanPasien1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(110);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(40);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            }
        }
        tbTindakanPasien1.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeDiagnosa = new DefaultTableModel(null, new Object[]{
            "#", "Kode", "Nama Penyakit", "Ciri-ciri Penyakit", "Keterangan", "Ktg.Penyakit", "Ciri-ciri Umum"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbDiagnosa.setModel(tabModeDiagnosa);
        tbDiagnosa.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 7; i++) {
            TableColumn column = tbDiagnosa.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(50);
            } else if (i == 2) {
                column.setPreferredWidth(355);
            } else if (i == 3) {
                column.setPreferredWidth(355);
            } else if (i == 4) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbDiagnosa.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeDiagnosaSekunder = new DefaultTableModel(null, new Object[]{
            "#", "Kode", "Nama Penyakit", "Ciri-ciri Penyakit", "Keterangan", "Ktg.Penyakit", "Ciri-ciri Umum"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbDiagnosa1.setModel(tabModeDiagnosaSekunder);
        tbDiagnosa1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosa1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 7; i++) {
            TableColumn column = tbDiagnosa1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(50);
            } else if (i == 2) {
                column.setPreferredWidth(355);
            } else if (i == 3) {
                column.setPreferredWidth(355);
            } else if (i == 4) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbDiagnosa1.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeProsedur = new DefaultTableModel(null, new Object[]{
            "#", "Kode", "Deskripsi Panjang", "Deskripsi Pendek"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbProsedur.setModel(tabModeProsedur);
        tbProsedur.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbProsedur.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbProsedur.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(50);
            } else if (i == 2) {
                column.setPreferredWidth(355);
            } else if (i == 3) {
                column.setPreferredWidth(355);
            }
        }
        tbProsedur.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
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
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgInputKodeICD")) {
                    if (poli.getTable().getSelectedRow() != -1) {
                        kdpoli = poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString();
                        TPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                        BtnUnit.requestFocus();
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
        
        poli.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgInputKodeICD")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        poli.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgInputKodeICD")) {
                    if (penjab.getTable().getSelectedRow() != -1) {
                        kdpenjab = penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 1).toString();
                        nmpnj.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 2).toString());
                    }
                    btnPenjab.requestFocus();
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

        penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgInputKodeICD")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        penjab.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        ChkInput.setSelected(false);
        isForm();        
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
        MnSimpanQTYinadrg = new javax.swing.JMenuItem();
        PopupPengajuanKlaim = new javax.swing.JPopupMenu();
        MnPengajuanKlaim = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        jLabel15 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel17 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel19 = new widget.Label();
        TPoli = new widget.TextBox();
        BtnUnit = new widget.Button();
        label16 = new widget.Label();
        nmpnj = new widget.TextBox();
        btnPenjab = new widget.Button();
        BtnBatal = new widget.Button();
        BtnSimpan = new widget.Button();
        BtnHapus = new widget.Button();
        BtnKeluar = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        jLabel9 = new widget.Label();
        jLabel11 = new widget.Label();
        jLabel12 = new widget.Label();
        jLabel13 = new widget.Label();
        Scroll7 = new widget.ScrollPane();
        TDiagDokter = new widget.TextArea();
        Scroll8 = new widget.ScrollPane();
        TDiagPerawat = new widget.TextArea();
        labelPasien = new widget.Label();
        labelPoliklinik = new widget.Label();
        tglKunjungan = new widget.Label();
        jLabel20 = new widget.Label();
        Scroll9 = new widget.ScrollPane();
        TKeluhan1 = new widget.TextArea();
        jLabel21 = new widget.Label();
        Scroll10 = new widget.ScrollPane();
        TRincianTindakan1 = new widget.TextArea();
        jLabel22 = new widget.Label();
        Scroll11 = new widget.ScrollPane();
        TKeluhan2 = new widget.TextArea();
        jLabel23 = new widget.Label();
        Scroll12 = new widget.ScrollPane();
        TRincianTindakan2 = new widget.TextArea();
        jLabel10 = new widget.Label();
        labelJnsPasien = new widget.Label();
        jLabel24 = new widget.Label();
        labelStatusKoding = new widget.Label();
        jLabel25 = new widget.Label();
        cmbDiagPro = new widget.ComboBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        Scroll = new widget.ScrollPane();
        tbPasien = new widget.Table();
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        jPanel2 = new javax.swing.JPanel();
        Scroll6 = new widget.ScrollPane();
        FormInput2 = new widget.PanelBiasa();
        jLabel14 = new widget.Label();
        DiagnosaP = new widget.TextBox();
        BtnCariPenyakit = new widget.Button();
        Scroll1 = new widget.ScrollPane();
        tbDiagnosa = new widget.Table();
        jLabel18 = new widget.Label();
        DiagnosaS = new widget.TextBox();
        BtnCariPenyakit1 = new widget.Button();
        Scroll4 = new widget.ScrollPane();
        tbDiagnosa1 = new widget.Table();
        jLabel16 = new widget.Label();
        Prosedur = new widget.TextBox();
        BtnCariProsedur = new widget.Button();
        Scroll2 = new widget.ScrollPane();
        tbProsedur = new widget.Table();
        TabICD = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll3 = new widget.ScrollPane();
        tbDiagnosaPasien = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        Scroll5 = new widget.ScrollPane();
        tbTindakanPasien = new widget.Table();
        internalFrame4 = new widget.InternalFrame();
        Scroll13 = new widget.ScrollPane();
        tbDiagnosaPasien1 = new widget.Table();
        internalFrame5 = new widget.InternalFrame();
        Scroll14 = new widget.ScrollPane();
        tbTindakanPasien1 = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSimpanQTYinadrg.setBackground(new java.awt.Color(255, 255, 255));
        MnSimpanQTYinadrg.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSimpanQTYinadrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSimpanQTYinadrg.setText("Simpan Jumlah Prosedur INADRG");
        MnSimpanQTYinadrg.setName("MnSimpanQTYinadrg"); // NOI18N
        MnSimpanQTYinadrg.setPreferredSize(new java.awt.Dimension(240, 26));
        MnSimpanQTYinadrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSimpanQTYinadrgActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSimpanQTYinadrg);

        PopupPengajuanKlaim.setName("PopupPengajuanKlaim"); // NOI18N

        MnPengajuanKlaim.setBackground(new java.awt.Color(242, 242, 242));
        MnPengajuanKlaim.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPengajuanKlaim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPengajuanKlaim.setText("Proses Pengajuan Klaim JKN");
        MnPengajuanKlaim.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPengajuanKlaim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPengajuanKlaim.setIconTextGap(8);
        MnPengajuanKlaim.setName("MnPengajuanKlaim"); // NOI18N
        MnPengajuanKlaim.setPreferredSize(new java.awt.Dimension(210, 25));
        MnPengajuanKlaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPengajuanKlaimBtnPrintActionPerformed(evt);
            }
        });
        PopupPengajuanKlaim.add(MnPengajuanKlaim);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Input Kode ICD-10 & ICD-9-CM Rawat Jalan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 44));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 15));

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Tanggal : ");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass8.add(jLabel15);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-06-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass8.add(DTPCari1);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("s.d");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(24, 23));
        panelGlass8.add(jLabel17);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-06-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass8.add(DTPCari2);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Poliklinik/Inst. : ");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass8.add(jLabel19);

        TPoli.setEditable(false);
        TPoli.setForeground(new java.awt.Color(0, 0, 0));
        TPoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TPoli.setEnabled(false);
        TPoli.setName("TPoli"); // NOI18N
        TPoli.setPreferredSize(new java.awt.Dimension(200, 23));
        panelGlass8.add(TPoli);

        BtnUnit.setForeground(new java.awt.Color(0, 0, 0));
        BtnUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnUnit.setMnemonic('4');
        BtnUnit.setToolTipText("ALt+4");
        BtnUnit.setName("BtnUnit"); // NOI18N
        BtnUnit.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUnitActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnUnit);

        label16.setForeground(new java.awt.Color(0, 0, 0));
        label16.setText("Cara Bayar : ");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass8.add(label16);

        nmpnj.setEditable(false);
        nmpnj.setForeground(new java.awt.Color(0, 0, 0));
        nmpnj.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        nmpnj.setEnabled(false);
        nmpnj.setName("nmpnj"); // NOI18N
        nmpnj.setPreferredSize(new java.awt.Dimension(200, 23));
        panelGlass8.add(nmpnj);

        btnPenjab.setForeground(new java.awt.Color(0, 0, 0));
        btnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenjab.setMnemonic('2');
        btnPenjab.setToolTipText("ALt+2");
        btnPenjab.setName("btnPenjab"); // NOI18N
        btnPenjab.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenjabActionPerformed(evt);
            }
        });
        panelGlass8.add(btnPenjab);

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

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 285));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('M');
        ChkInput.setText(".: Input Data");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 107));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Pasien : ");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 100, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Poliklinik/Inst. : ");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 37, 100, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Tgl. Kunjungan : ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 91, 100, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Diagnosa Resume dari Dokter : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(530, 10, 210, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Diagnosa Resume dari Perawat/Bidan : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(970, 10, 240, 23);

        Scroll7.setName("Scroll7"); // NOI18N

        TDiagDokter.setEditable(false);
        TDiagDokter.setColumns(20);
        TDiagDokter.setRows(5);
        TDiagDokter.setName("TDiagDokter"); // NOI18N
        Scroll7.setViewportView(TDiagDokter);

        FormInput.add(Scroll7);
        Scroll7.setBounds(530, 35, 430, 55);

        Scroll8.setName("Scroll8"); // NOI18N

        TDiagPerawat.setEditable(false);
        TDiagPerawat.setColumns(20);
        TDiagPerawat.setRows(5);
        TDiagPerawat.setName("TDiagPerawat"); // NOI18N
        Scroll8.setViewportView(TDiagPerawat);

        FormInput.add(Scroll8);
        Scroll8.setBounds(970, 35, 430, 55);

        labelPasien.setForeground(new java.awt.Color(0, 0, 0));
        labelPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelPasien.setText("labelPasien");
        labelPasien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelPasien.setName("labelPasien"); // NOI18N
        FormInput.add(labelPasien);
        labelPasien.setBounds(102, 10, 420, 23);

        labelPoliklinik.setForeground(new java.awt.Color(0, 0, 0));
        labelPoliklinik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelPoliklinik.setText("labelPoliklinik");
        labelPoliklinik.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelPoliklinik.setName("labelPoliklinik"); // NOI18N
        FormInput.add(labelPoliklinik);
        labelPoliklinik.setBounds(102, 37, 420, 23);

        tglKunjungan.setForeground(new java.awt.Color(0, 0, 0));
        tglKunjungan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tglKunjungan.setText("tglKunjungan");
        tglKunjungan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tglKunjungan.setName("tglKunjungan"); // NOI18N
        FormInput.add(tglKunjungan);
        tglKunjungan.setBounds(102, 91, 360, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Catatan Keluhan Dari Dokter : ");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(530, 93, 210, 23);

        Scroll9.setName("Scroll9"); // NOI18N

        TKeluhan1.setEditable(false);
        TKeluhan1.setColumns(20);
        TKeluhan1.setRows(5);
        TKeluhan1.setName("TKeluhan1"); // NOI18N
        Scroll9.setViewportView(TKeluhan1);

        FormInput.add(Scroll9);
        Scroll9.setBounds(530, 118, 430, 55);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Catatan Rincian Tindakan Dari Dokter :");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(530, 177, 210, 23);

        Scroll10.setName("Scroll10"); // NOI18N

        TRincianTindakan1.setEditable(false);
        TRincianTindakan1.setColumns(20);
        TRincianTindakan1.setRows(5);
        TRincianTindakan1.setName("TRincianTindakan1"); // NOI18N
        Scroll10.setViewportView(TRincianTindakan1);

        FormInput.add(Scroll10);
        Scroll10.setBounds(530, 203, 430, 55);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("Catatan Keluhan Dari Perawat/Bidan : ");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(970, 93, 240, 23);

        Scroll11.setName("Scroll11"); // NOI18N

        TKeluhan2.setEditable(false);
        TKeluhan2.setColumns(20);
        TKeluhan2.setRows(5);
        TKeluhan2.setName("TKeluhan2"); // NOI18N
        Scroll11.setViewportView(TKeluhan2);

        FormInput.add(Scroll11);
        Scroll11.setBounds(970, 118, 430, 55);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("Catatan Rincian Tindakan Dari Perawat/Bidan : ");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(970, 177, 240, 23);

        Scroll12.setName("Scroll12"); // NOI18N

        TRincianTindakan2.setEditable(false);
        TRincianTindakan2.setColumns(20);
        TRincianTindakan2.setRows(5);
        TRincianTindakan2.setName("TRincianTindakan2"); // NOI18N
        Scroll12.setViewportView(TRincianTindakan2);

        FormInput.add(Scroll12);
        Scroll12.setBounds(970, 203, 430, 55);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Jns. Pasien : ");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 64, 100, 23);

        labelJnsPasien.setForeground(new java.awt.Color(0, 0, 0));
        labelJnsPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelJnsPasien.setText("labelJnsPasien");
        labelJnsPasien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelJnsPasien.setName("labelJnsPasien"); // NOI18N
        FormInput.add(labelJnsPasien);
        labelJnsPasien.setBounds(102, 64, 380, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Status Koding : ");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(0, 118, 100, 23);

        labelStatusKoding.setForeground(new java.awt.Color(0, 0, 0));
        labelStatusKoding.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelStatusKoding.setText("labelStatusKoding");
        labelStatusKoding.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelStatusKoding.setName("labelStatusKoding"); // NOI18N
        FormInput.add(labelStatusKoding);
        labelStatusKoding.setBounds(102, 118, 360, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Jns. Diagnosa & Prosedur : ");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(0, 145, 150, 23);

        cmbDiagPro.setForeground(new java.awt.Color(0, 0, 0));
        cmbDiagPro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "STATISTIK", "INADRG" }));
        cmbDiagPro.setSelectedIndex(1);
        cmbDiagPro.setName("cmbDiagPro"); // NOI18N
        cmbDiagPro.setOpaque(false);
        cmbDiagPro.setPreferredSize(new java.awt.Dimension(308, 23));
        FormInput.add(cmbDiagPro);
        cmbDiagPro.setBounds(152, 145, 85, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Data Kunjungan Pasien Rawat Jalan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(250, 102));
        jPanel4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPasien.setAutoCreateRowSorter(true);
        tbPasien.setToolTipText("Silahkan klik untuk memilih data yang akan diperbaiki data ICD nya");
        tbPasien.setComponentPopupMenu(PopupPengajuanKlaim);
        tbPasien.setName("tbPasien"); // NOI18N
        tbPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPasienMouseClicked(evt);
            }
        });
        tbPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPasienKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPasien);

        jPanel4.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(210, 23));
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

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 23));
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
        panelGlass10.add(BtnAll);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount);

        jPanel4.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel4);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Diagnosa & Prosedur Tindakan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 102));
        jPanel2.setLayout(new java.awt.BorderLayout());

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        FormInput2.setBackground(new java.awt.Color(255, 255, 255));
        FormInput2.setBorder(null);
        FormInput2.setName("FormInput2"); // NOI18N
        FormInput2.setPreferredSize(new java.awt.Dimension(100, 630));
        FormInput2.setLayout(null);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Diagnosa Primer : ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput2.add(jLabel14);
        jLabel14.setBounds(0, 10, 130, 23);

        DiagnosaP.setForeground(new java.awt.Color(0, 0, 0));
        DiagnosaP.setHighlighter(null);
        DiagnosaP.setName("DiagnosaP"); // NOI18N
        DiagnosaP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaPKeyPressed(evt);
            }
        });
        FormInput2.add(DiagnosaP);
        DiagnosaP.setBounds(131, 10, 240, 23);

        BtnCariPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariPenyakit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariPenyakit.setMnemonic('1');
        BtnCariPenyakit.setToolTipText("Alt+1");
        BtnCariPenyakit.setName("BtnCariPenyakit"); // NOI18N
        BtnCariPenyakit.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariPenyakitActionPerformed(evt);
            }
        });
        FormInput2.add(BtnCariPenyakit);
        BtnCariPenyakit.setBounds(373, 10, 28, 23);

        Scroll1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbDiagnosa.setToolTipText("");
        tbDiagnosa.setName("tbDiagnosa"); // NOI18N
        tbDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagnosaKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbDiagnosa);

        FormInput2.add(Scroll1);
        Scroll1.setBounds(11, 39, 815, 90);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Diagnosa Sekunder : ");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput2.add(jLabel18);
        jLabel18.setBounds(0, 135, 130, 23);

        DiagnosaS.setForeground(new java.awt.Color(0, 0, 0));
        DiagnosaS.setHighlighter(null);
        DiagnosaS.setName("DiagnosaS"); // NOI18N
        DiagnosaS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaSKeyPressed(evt);
            }
        });
        FormInput2.add(DiagnosaS);
        DiagnosaS.setBounds(131, 135, 240, 23);

        BtnCariPenyakit1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariPenyakit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariPenyakit1.setMnemonic('1');
        BtnCariPenyakit1.setToolTipText("Alt+1");
        BtnCariPenyakit1.setName("BtnCariPenyakit1"); // NOI18N
        BtnCariPenyakit1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariPenyakit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariPenyakit1ActionPerformed(evt);
            }
        });
        FormInput2.add(BtnCariPenyakit1);
        BtnCariPenyakit1.setBounds(373, 135, 28, 23);

        Scroll4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbDiagnosa1.setToolTipText("");
        tbDiagnosa1.setName("tbDiagnosa1"); // NOI18N
        tbDiagnosa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagnosa1KeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbDiagnosa1);

        FormInput2.add(Scroll4);
        Scroll4.setBounds(11, 163, 815, 110);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Prosedur/Tindakan : ");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput2.add(jLabel16);
        jLabel16.setBounds(0, 278, 130, 23);

        Prosedur.setForeground(new java.awt.Color(0, 0, 0));
        Prosedur.setHighlighter(null);
        Prosedur.setName("Prosedur"); // NOI18N
        Prosedur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsedurKeyPressed(evt);
            }
        });
        FormInput2.add(Prosedur);
        Prosedur.setBounds(131, 278, 240, 23);

        BtnCariProsedur.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariProsedur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariProsedur.setMnemonic('1');
        BtnCariProsedur.setToolTipText("Alt+1");
        BtnCariProsedur.setName("BtnCariProsedur"); // NOI18N
        BtnCariProsedur.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariProsedur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariProsedurActionPerformed(evt);
            }
        });
        FormInput2.add(BtnCariProsedur);
        BtnCariProsedur.setBounds(373, 278, 28, 23);

        Scroll2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbProsedur.setToolTipText("");
        tbProsedur.setName("tbProsedur"); // NOI18N
        Scroll2.setViewportView(tbProsedur);

        FormInput2.add(Scroll2);
        Scroll2.setBounds(11, 307, 815, 110);

        TabICD.setBackground(new java.awt.Color(250, 255, 245));
        TabICD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabICD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabICD.setName("TabICD"); // NOI18N
        TabICD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabICDMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbDiagnosaPasien.setToolTipText("Silahkan klik untuk memilih data yang mau dihapus");
        tbDiagnosaPasien.setName("tbDiagnosaPasien"); // NOI18N
        tbDiagnosaPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDiagnosaPasienMouseClicked(evt);
            }
        });
        tbDiagnosaPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagnosaPasienKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbDiagnosaPasien);

        internalFrame2.add(Scroll3, java.awt.BorderLayout.CENTER);

        TabICD.addTab("Diagnosa Statistik", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbTindakanPasien.setToolTipText("Silahkan klik untuk memilih data yang mau dihapus");
        tbTindakanPasien.setName("tbTindakanPasien"); // NOI18N
        tbTindakanPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTindakanPasienMouseClicked(evt);
            }
        });
        tbTindakanPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTindakanPasienKeyPressed(evt);
            }
        });
        Scroll5.setViewportView(tbTindakanPasien);

        internalFrame3.add(Scroll5, java.awt.BorderLayout.CENTER);

        TabICD.addTab("Prosedur/Tindakan Statistik", internalFrame3);

        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll13.setName("Scroll13"); // NOI18N
        Scroll13.setOpaque(true);

        tbDiagnosaPasien1.setToolTipText("Silahkan klik untuk memilih data yang mau dihapus");
        tbDiagnosaPasien1.setName("tbDiagnosaPasien1"); // NOI18N
        tbDiagnosaPasien1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDiagnosaPasien1MouseClicked(evt);
            }
        });
        tbDiagnosaPasien1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagnosaPasien1KeyPressed(evt);
            }
        });
        Scroll13.setViewportView(tbDiagnosaPasien1);

        internalFrame4.add(Scroll13, java.awt.BorderLayout.CENTER);

        TabICD.addTab("Diagnosa INADRG", internalFrame4);

        internalFrame5.setBorder(null);
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll14.setName("Scroll14"); // NOI18N
        Scroll14.setOpaque(true);

        tbTindakanPasien1.setToolTipText("Silahkan klik untuk memilih data yang mau dihapus");
        tbTindakanPasien1.setComponentPopupMenu(jPopupMenu1);
        tbTindakanPasien1.setName("tbTindakanPasien1"); // NOI18N
        tbTindakanPasien1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTindakanPasien1MouseClicked(evt);
            }
        });
        tbTindakanPasien1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTindakanPasien1KeyPressed(evt);
            }
        });
        Scroll14.setViewportView(tbTindakanPasien1);

        internalFrame5.add(Scroll14, java.awt.BorderLayout.CENTER);

        TabICD.addTab("Prosedur/Tindakan INADRG", internalFrame5);

        FormInput2.add(TabICD);
        TabICD.setBounds(11, 424, 815, 190);

        Scroll6.setViewportView(FormInput2);

        jPanel2.add(Scroll6, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();

        for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
            tbDiagnosa.setValueAt(false, i, 0);
        }
        for (i = 0; i < tbDiagnosa1.getRowCount(); i++) {
            tbDiagnosa1.setValueAt(false, i, 0);
        }
        for (i = 0; i < tbProsedur.getRowCount(); i++) {
            tbProsedur.setValueAt(false, i, 0);
        }
        ChkInput.setSelected(true);
        isForm();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnBatal,TCari);}
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
        kdpoli = "";
        kdpenjab = "";
        tampil();
        emptTeks();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari.setText("");
            kdpoli = "";
            kdpenjab = "";
            tampil();
            emptTeks();
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPasienMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPasienMouseClicked

    private void tbPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPasienKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbPasienKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void BtnUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUnitActionPerformed
        TPoli.setText("");
        kdpoli = "";
        akses.setform("DlgInputKodeICD");
        poli.isCek();
        poli.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
    }//GEN-LAST:event_BtnUnitActionPerformed

    private void btnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenjabActionPerformed
        kdpenjab = "";
        nmpnj.setText("");
        akses.setform("DlgInputKodeICD");
        penjab.onCari();
        penjab.isCek();
        penjab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penjab.setLocationRelativeTo(internalFrame1);
        penjab.setVisible(true);
    }//GEN-LAST:event_btnPenjabActionPerformed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        cekKlaim = "";
      
        if (norw.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasien pada tabel...!!");
        } else {
            cekKlaim = Sequel.cariIsi("SELECT klaim_final FROM eklaim_new_claim WHERE no_rawat='" + norw + "'");
            
            if (cekKlaim.equals("Final")) {
                JOptionPane.showMessageDialog(null, "Proses pengajuan klaim selesai dilakukan, data ICD-10 atau ICD-9-CM pasien ini tdk. dapat disimpan...!!");
            } else {
                if (cmbDiagPro.getSelectedIndex() == 0) {
                    simpan_diagproStatistik();
                    simpan_diagproINADRG();
                } else if (cmbDiagPro.getSelectedIndex() == 1) {
                    simpan_diagproStatistik();
                } else if (cmbDiagPro.getSelectedIndex() == 2) {
                    simpan_diagproINADRG();
                }

                TabICDMouseClicked(null);
                for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                    tbDiagnosa.setValueAt(false, i, 0);
                }
                for (i = 0; i < tbDiagnosa1.getRowCount(); i++) {
                    tbDiagnosa1.setValueAt(false, i, 0);
                }
                for (i = 0; i < tbProsedur.getRowCount(); i++) {
                    tbProsedur.setValueAt(false, i, 0);
                }
                tampildiagnosa();
                tampildiagnosaSekunder();
                tampilprosedure();
                tampil();
            }
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, DiagnosaP, BtnBatal);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        cekKlaim = "";

        if (norw.equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
        } else if (!(norw.equals(""))) {
            cekKlaim = Sequel.cariIsi("SELECT klaim_final FROM eklaim_new_claim WHERE no_rawat='" + norw + "'");

            if (cekKlaim.equals("Final")) {
                JOptionPane.showMessageDialog(null, "Proses pengajuan klaim selesai dilakukan, data ICD-10 atau ICD-9-CM pasien ini tdk. dapat dihapus...!!");
            } else {
                if (cmbDiagPro.getSelectedIndex() == 0) {
                    hapus_diagproStatistik();
                    hapus_diagproINADRG();
                } else if (cmbDiagPro.getSelectedIndex() == 1) {
                    hapus_diagproStatistik();
                } else if (cmbDiagPro.getSelectedIndex() == 2) {
                    hapus_diagproINADRG();
                }
            }

            TabICDMouseClicked(null);
            for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                tbDiagnosa.setValueAt(false, i, 0);
            }
            for (i = 0; i < tbDiagnosa1.getRowCount(); i++) {
                tbDiagnosa1.setValueAt(false, i, 0);
            }
            for (i = 0; i < tbProsedur.getRowCount(); i++) {
                tbProsedur.setValueAt(false, i, 0);
            }
            tampildiagnosa();
            tampildiagnosaSekunder();
            tampilprosedure();
            tampil();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void DiagnosaPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaPKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (norw.equals("")) {
                JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasien pada tabel...!!");
                DiagnosaP.setText("");
                tbPasien.requestFocus();
            } else {
                tampildiagnosa();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbDiagnosa.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_DiagnosaPKeyPressed

    private void BtnCariPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariPenyakitActionPerformed
        if (norw.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasien pada tabel...!!");
            DiagnosaP.setText("");
            tbPasien.requestFocus();
        } else {
            tampildiagnosa();
        }        
    }//GEN-LAST:event_BtnCariPenyakitActionPerformed

    private void tbDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosaKeyPressed
        if (tbDiagnosa.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    i = tbDiagnosa.getSelectedColumn();
                    if (i == 1) {
                        if (tbDiagnosa.getSelectedRow() > -1) {
                            tbDiagnosa.setValueAt(true, tbDiagnosa.getSelectedRow(), 0);
                        }
                        DiagnosaP.setText("");
                        DiagnosaP.requestFocus();
                    }
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                DiagnosaP.setText("");
                DiagnosaP.requestFocus();
            }
        }
    }//GEN-LAST:event_tbDiagnosaKeyPressed

    private void DiagnosaSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaSKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (norw.equals("")) {
                JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasien pada tabel...!!");
                DiagnosaS.setText("");
                tbPasien.requestFocus();
            } else {
                tampildiagnosaSekunder();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbDiagnosa1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_DiagnosaSKeyPressed

    private void BtnCariPenyakit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariPenyakit1ActionPerformed
        if (norw.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasien pada tabel...!!");
            DiagnosaS.setText("");
            tbPasien.requestFocus();
        } else {
            tampildiagnosaSekunder();
        }
    }//GEN-LAST:event_BtnCariPenyakit1ActionPerformed

    private void tbDiagnosa1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosa1KeyPressed
        if (tbDiagnosa1.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    i = tbDiagnosa1.getSelectedColumn();
                    if (i == 1) {
                        if (tbDiagnosa1.getSelectedRow() > -1) {
                            tbDiagnosa1.setValueAt(true, tbDiagnosa1.getSelectedRow(), 0);
                        }
                        DiagnosaS.setText("");
                        DiagnosaS.requestFocus();
                    }
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                DiagnosaS.setText("");
                DiagnosaS.requestFocus();
            }
        }
    }//GEN-LAST:event_tbDiagnosa1KeyPressed

    private void ProsedurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsedurKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (norw.equals("")) {
                JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasien pada tabel...!!");
                Prosedur.setText("");
                tbPasien.requestFocus();
            } else {
                tampilprosedure();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbProsedur.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_ProsedurKeyPressed

    private void BtnCariProsedurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariProsedurActionPerformed
        if (norw.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasien pada tabel...!!");
            Prosedur.setText("");
            tbPasien.requestFocus();
        } else {
            tampilprosedure();
        }
    }//GEN-LAST:event_BtnCariProsedurActionPerformed

    private void tbDiagnosaPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagnosaPasienMouseClicked
        if (TabModeDiagnosaPasien.getRowCount() != 0) {
            try {
                getDataICD10();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbDiagnosaPasienMouseClicked

    private void tbDiagnosaPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosaPasienKeyPressed
        if (TabModeDiagnosaPasien.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataICD10();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbDiagnosaPasienKeyPressed

    private void tbTindakanPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTindakanPasienMouseClicked
        if (TabModeTindakanPasien.getRowCount() != 0) {
            try {
                getDataICD9();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTindakanPasienMouseClicked

    private void tbTindakanPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTindakanPasienKeyPressed
        if (TabModeTindakanPasien.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataICD9();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbTindakanPasienKeyPressed

    private void TabICDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabICDMouseClicked
        if (TabICD.isEnabled() == false) {
            tbPasien.requestFocus();
        } else {
            if (TabICD.getSelectedIndex() == 0) {
                tampilRiwayatDiag();
            } else if (TabICD.getSelectedIndex() == 1) {
                tampilProsedurTindakan();
            } else if (TabICD.getSelectedIndex() == 2) {
                tampilDiagINADRG();
            } else if (TabICD.getSelectedIndex() == 3) {
                tampilProsINADRG();
            }
        }
    }//GEN-LAST:event_TabICDMouseClicked

    private void tbDiagnosaPasien1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagnosaPasien1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDiagnosaPasien1MouseClicked

    private void tbDiagnosaPasien1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosaPasien1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDiagnosaPasien1KeyPressed

    private void tbTindakanPasien1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTindakanPasien1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbTindakanPasien1MouseClicked

    private void tbTindakanPasien1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTindakanPasien1KeyPressed
        if (tabMode2.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                for (i = 0; i < tbTindakanPasien1.getRowCount(); i++) {
                    if (tbTindakanPasien1.getValueAt(i, 8).toString().equals("") || Integer.parseInt(tbTindakanPasien1.getValueAt(i, 8).toString()) == 0) {
                        tabMode2.setValueAt("1", i, 8);
                    }
                }
            }
        }
    }//GEN-LAST:event_tbTindakanPasien1KeyPressed

    private void MnSimpanQTYinadrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSimpanQTYinadrgActionPerformed
        simpanQTYprosedurINADRG();
        for (i = 0; i < tbTindakanPasien1.getRowCount(); i++) {
            tbTindakanPasien1.setValueAt(false, i, 0);
        }
        tampilProsINADRG();
    }//GEN-LAST:event_MnSimpanQTYinadrgActionPerformed

    private void MnPengajuanKlaimBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPengajuanKlaimBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (norw.equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbPasien.requestFocus();
        } else if (!tbPasien.getValueAt(tbPasien.getSelectedRow(), 11).equals("B01")) {
            JOptionPane.showMessageDialog(null, "Fitur/menu ini hanya untuk pasien BPJS saja...!!!!");
            tbPasien.requestFocus();
        } else {
            INACBGDaftarKlaim diklaim = new INACBGDaftarKlaim(null, false);
            diklaim.isCek();
            diklaim.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            diklaim.setLocationRelativeTo(internalFrame1);
            diklaim.verifData();
            diklaim.KlaimRAZA(norw, Sequel.cariIsi("select no_sep from bridging_sep where no_rawat='" + norw + "' and urutan_sep=1"),
                    "JKN", "3", Sequel.cariIsi("select tglsep from bridging_sep where no_rawat='" + norw + "' and urutan_sep=1"));
            diklaim.setVisible(true);
        }
    }//GEN-LAST:event_MnPengajuanKlaimBtnPrintActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgInputKodeICD dialog = new DlgInputKodeICD(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCariPenyakit;
    private widget.Button BtnCariPenyakit1;
    private widget.Button BtnCariProsedur;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.Button BtnUnit;
    public widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextBox DiagnosaP;
    private widget.TextBox DiagnosaS;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput2;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnPengajuanKlaim;
    private javax.swing.JMenuItem MnSimpanQTYinadrg;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPopupMenu PopupPengajuanKlaim;
    private widget.TextBox Prosedur;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll11;
    private widget.ScrollPane Scroll12;
    private widget.ScrollPane Scroll13;
    private widget.ScrollPane Scroll14;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    public widget.TextBox TCari;
    private widget.TextArea TDiagDokter;
    private widget.TextArea TDiagPerawat;
    private widget.TextArea TKeluhan1;
    private widget.TextArea TKeluhan2;
    private widget.TextBox TPoli;
    private widget.TextArea TRincianTindakan1;
    private widget.TextArea TRincianTindakan2;
    private javax.swing.JTabbedPane TabICD;
    private widget.Button btnPenjab;
    private widget.ComboBox cmbDiagPro;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
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
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label16;
    private widget.Label labelJnsPasien;
    private widget.Label labelPasien;
    private widget.Label labelPoliklinik;
    private widget.Label labelStatusKoding;
    private widget.TextBox nmpnj;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.Table tbDiagnosa;
    private widget.Table tbDiagnosa1;
    private widget.Table tbDiagnosaPasien;
    private widget.Table tbDiagnosaPasien1;
    private widget.Table tbPasien;
    private widget.Table tbProsedur;
    private widget.Table tbTindakanPasien;
    private widget.Table tbTindakanPasien1;
    private widget.Label tglKunjungan;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT rp.no_rawat, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_kun, rp.jam_reg, rp.no_rkm_medis, concat(p.nm_pasien,' (Usia : ',CONCAT(rp.umurdaftar,' ',rp.sttsumur),')') nm_pasien, "
                    + "pl.nm_poli, pj.png_jawab, IF(dp.no_rawat IS NULL,'Belum Ada','Ada') diag_icd_10, IFNULL(pr1.diagnosa,'-') diag_resum_dr, IFNULL(pr2.diagnosa,'-') diag_resum_pr, rp.tgl_registrasi, rp.kd_pj, rp.kd_poli, "
                    + "IFNULL(pr1.keluhan,'-') keluhan_dr, IFNULL(pr2.keluhan,'-') keluhan_pr, IFNULL(pr1.rincian_tindakan,'-') tindakan_dr, IFNULL(pr2.rincian_tindakan,'-') tindakan_pr "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli "
                    + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj LEFT JOIN pemeriksaan_ralan pr1 ON pr1.no_rawat=rp.no_rawat "
                    + "LEFT JOIN pemeriksaan_ralan_petugas pr2 ON pr2.no_rawat=rp.no_rawat LEFT JOIN diagnosa_pasien dp ON dp.no_rawat=rp.no_rawat WHERE "
                    + "rp.status_lanjut = 'Ralan' AND rp.kd_poli not in ('laa','lab','rad','-') and (dp.prioritas=1 OR IFNULL(dp.prioritas,'-')='-') AND rp.tgl_registrasi BETWEEN ? AND ? and rp.kd_pj like ? and rp.kd_poli like ? and rp.no_rawat like ? or "
                    + "rp.status_lanjut = 'Ralan' AND rp.kd_poli not in ('laa','lab','rad','-') and (dp.prioritas=1 OR IFNULL(dp.prioritas,'-')='-') AND rp.tgl_registrasi BETWEEN ? AND ? and rp.kd_pj like ? and rp.kd_poli like ? and rp.no_rkm_medis like ? or "
                    + "rp.status_lanjut = 'Ralan' AND rp.kd_poli not in ('laa','lab','rad','-') and (dp.prioritas=1 OR IFNULL(dp.prioritas,'-')='-') AND rp.tgl_registrasi BETWEEN ? AND ? and rp.kd_pj like ? and rp.kd_poli like ? and p.nm_pasien like ? or "
                    + "rp.status_lanjut = 'Ralan' AND rp.kd_poli not in ('laa','lab','rad','-') and (dp.prioritas=1 OR IFNULL(dp.prioritas,'-')='-') AND rp.tgl_registrasi BETWEEN ? AND ? and rp.kd_pj like ? and rp.kd_poli like ? and IF(dp.no_rawat IS NULL,'Belum Ada','Ada') like ? "
                    + "ORDER BY rp.tgl_registrasi DESC, rp.jam_reg DESC");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(3, "%" + kdpenjab + "%");
                ps.setString(4, "%" + kdpoli + "%");
                ps.setString(5, "%" + TCari.getText().trim() + "%");                
                ps.setString(6, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(7, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(8, "%" + kdpenjab + "%");
                ps.setString(9, "%" + kdpoli + "%");
                ps.setString(10, "%" + TCari.getText().trim() + "%");                
                ps.setString(11, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(12, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(13, "%" + kdpenjab + "%");
                ps.setString(14, "%" + kdpoli + "%");
                ps.setString(15, "%" + TCari.getText().trim() + "%");                
                ps.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(18, "%" + kdpenjab + "%");
                ps.setString(19, "%" + kdpoli + "%");
                ps.setString(20, "%" + TCari.getText().trim() + "%");                
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"),
                        rs.getString("tgl_kun"),
                        rs.getString("jam_reg"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("nm_poli"),
                        rs.getString("png_jawab"),
                        rs.getString("diag_icd_10"),
                        rs.getString("diag_resum_dr"),
                        rs.getString("diag_resum_pr"),
                        rs.getString("tgl_registrasi"),
                        rs.getString("kd_pj"),
                        rs.getString("kd_poli"),                        
                        rs.getString("keluhan_dr"),
                        rs.getString("keluhan_pr"),
                        rs.getString("tindakan_dr"),
                        rs.getString("tindakan_pr")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("rekammedis.DlgInputKodeICD.tampil() : " + e);
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
        cmbDiagPro.setSelectedIndex(1);
        labelPasien.setText("-");
        labelPoliklinik.setText("-");
        labelJnsPasien.setText("-");
        labelStatusKoding.setText("-");
        tglKunjungan.setText("-");
        TDiagDokter.setText("");
        TKeluhan1.setText("");
        TRincianTindakan1.setText("");
        TDiagPerawat.setText("");
        TKeluhan2.setText("");
        TRincianTindakan2.setText("");
        kdpoli = "";
        kdpenjab = "";
        TPoli.setText("");
        nmpnj.setText("");
        norw  = "";
        norm = "";
        DiagnosaP.setText("");
        DiagnosaS.setText("");
        Prosedur.setText("");
        Valid.tabelKosong(TabModeDiagnosaPasien);
        Valid.tabelKosong(TabModeTindakanPasien);
        Valid.tabelKosong(tabMode1);
        Valid.tabelKosong(tabMode2);
        TabICD.setEnabled(false);
        TabICD.setSelectedIndex(0);
        tampildiagnosa();
        tampildiagnosaSekunder();
        tampilprosedure();
    }

    private void getData() {
        norw = "";
        norm = "";
        
        if(tbPasien.getSelectedRow()!= -1){            
            norw = tbPasien.getValueAt(tbPasien.getSelectedRow(), 0).toString();
            norm = tbPasien.getValueAt(tbPasien.getSelectedRow(), 3).toString();
            labelPasien.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 3).toString()+" - "+tbPasien.getValueAt(tbPasien.getSelectedRow(), 4).toString());
            labelPoliklinik.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 5).toString());
            labelJnsPasien.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 6).toString());
            tglKunjungan.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 1).toString() + ", Jam : " + tbPasien.getValueAt(tbPasien.getSelectedRow(), 2).toString());
            labelStatusKoding.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 7).toString());
            TDiagDokter.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 8).toString());
            TDiagPerawat.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 9).toString());
            TKeluhan1.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 13).toString());
            TKeluhan2.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 14).toString());
            TRincianTindakan1.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 15).toString());
            TRincianTindakan2.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 16).toString());
            
            TabICD.setEnabled(true);
            TabICD.setSelectedIndex(0);
            if (TabICD.getSelectedIndex() == 0) {
                tampilRiwayatDiag();
            } else if (TabICD.getSelectedIndex() == 1) {
                tampilProsedurTindakan();
            } else if (TabICD.getSelectedIndex() == 2) {
                tampilDiagINADRG();
            } else if (TabICD.getSelectedIndex() == 3) {
                tampilProsINADRG();
            }
        }
    }
    
    public void isForm(){
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 285));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getdiagnosa_pasien());
        BtnHapus.setEnabled(akses.getdiagnosa_pasien());
    }

    private void tampildiagnosa() {
        try {
            jml = 0;
            for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                    jml++;
                }
            }

            pilih = null;
            pilih = new boolean[jml];
            kode = null;
            kode = new String[jml];
            nama = null;
            nama = new String[jml];
            ciripny = null;
            ciripny = new String[jml];
            keterangan = null;
            keterangan = new String[jml];
            kategori = null;
            kategori = new String[jml];
            cirium = null;
            cirium = new String[jml];

            index = 0;
            for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                    pilih[index] = true;
                    kode[index] = tbDiagnosa.getValueAt(i, 1).toString();
                    nama[index] = tbDiagnosa.getValueAt(i, 2).toString();
                    ciripny[index] = tbDiagnosa.getValueAt(i, 3).toString();
                    keterangan[index] = tbDiagnosa.getValueAt(i, 4).toString();
                    kategori[index] = tbDiagnosa.getValueAt(i, 5).toString();
                    cirium[index] = tbDiagnosa.getValueAt(i, 6).toString();
                    index++;
                }
            }

            Valid.tabelKosong(tabModeDiagnosa);
            for (i = 0; i < jml; i++) {
                tabModeDiagnosa.addRow(new Object[]{pilih[i], kode[i], nama[i], ciripny[i], keterangan[i], kategori[i], cirium[i]});
            }

            pspenyakit = koneksi.prepareStatement("select penyakit.kd_penyakit,penyakit.nm_penyakit,penyakit.ciri_ciri,penyakit.keterangan, "
                    + "kategori_penyakit.nm_kategori,kategori_penyakit.ciri_umum "
                    + "from kategori_penyakit inner join penyakit "
                    + "on penyakit.kd_ktg=kategori_penyakit.kd_ktg where  "
                    + " (penyakit.kd_penyakit like ? or "
                    + " penyakit.nm_penyakit like ? or "
                    + " penyakit.ciri_ciri like ? or "
                    + " penyakit.keterangan like ? or "
                    + " kategori_penyakit.nm_kategori like ? or "
                    + " kategori_penyakit.ciri_umum like ? ) and penyakit.kd_penyakit <> '-' "
                    + "order by penyakit.kd_penyakit  LIMIT 100");
            try {
                pspenyakit.setString(1, "%" + DiagnosaP.getText().trim() + "%");
                pspenyakit.setString(2, "%" + DiagnosaP.getText().trim() + "%");
                pspenyakit.setString(3, "%" + DiagnosaP.getText().trim() + "%");
                pspenyakit.setString(4, "%" + DiagnosaP.getText().trim() + "%");
                pspenyakit.setString(5, "%" + DiagnosaP.getText().trim() + "%");
                pspenyakit.setString(6, "%" + DiagnosaP.getText().trim() + "%");
                rs = pspenyakit.executeQuery();
                while (rs.next()) {
                    tabModeDiagnosa.addRow(new Object[]{
                        false, 
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (pspenyakit != null) {
                    pspenyakit.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampildiagnosaSekunder() {
        try {
            jml1 = 0;
            for (s = 0; s < tbDiagnosa1.getRowCount(); s++) {
                if (tbDiagnosa1.getValueAt(s, 0).toString().equals("true")) {
                    jml1++;
                }
            }

            pilih3 = null;
            pilih3 = new boolean[jml1];
            kode1 = null;
            kode1 = new String[jml1];
            nama1 = null;
            nama1 = new String[jml1];
            ciripny1 = null;
            ciripny1 = new String[jml1];
            keterangan1 = null;
            keterangan1 = new String[jml1];
            kategori1 = null;
            kategori1 = new String[jml1];
            cirium1 = null;
            cirium1 = new String[jml1];

            index1 = 0;
            for (s = 0; s < tbDiagnosa1.getRowCount(); s++) {
                if (tbDiagnosa1.getValueAt(s, 0).toString().equals("true")) {
                    pilih3[index1] = true;
                    kode1[index1] = tbDiagnosa1.getValueAt(s, 1).toString();
                    nama1[index1] = tbDiagnosa1.getValueAt(s, 2).toString();
                    ciripny1[index1] = tbDiagnosa1.getValueAt(s, 3).toString();
                    keterangan1[index1] = tbDiagnosa1.getValueAt(s, 4).toString();
                    kategori1[index1] = tbDiagnosa1.getValueAt(s, 5).toString();
                    cirium1[index1] = tbDiagnosa1.getValueAt(s, 6).toString();
                    index1++;
                }
            }

            Valid.tabelKosong(tabModeDiagnosaSekunder);
            for (s = 0; s < jml1; s++) {
                tabModeDiagnosaSekunder.addRow(new Object[]{pilih3[s], kode1[s], nama1[s], ciripny1[s], keterangan1[s], kategori1[s], cirium1[s]});
            }

            pspenyakitsekunder = koneksi.prepareStatement("select penyakit.kd_penyakit,penyakit.nm_penyakit,penyakit.ciri_ciri,penyakit.keterangan, "
                    + "kategori_penyakit.nm_kategori,kategori_penyakit.ciri_umum "
                    + "from kategori_penyakit inner join penyakit "
                    + "on penyakit.kd_ktg=kategori_penyakit.kd_ktg where  "
                    + " (penyakit.kd_penyakit like ? or "
                    + " penyakit.nm_penyakit like ? or "
                    + " penyakit.ciri_ciri like ? or "
                    + " penyakit.keterangan like ? or "
                    + " kategori_penyakit.nm_kategori like ? or "
                    + " kategori_penyakit.ciri_umum like ? ) and penyakit.kd_penyakit <> '-' "
                    + "order by penyakit.kd_penyakit LIMIT 100");
            try {
                pspenyakitsekunder.setString(1, "%" + DiagnosaS.getText().trim() + "%");
                pspenyakitsekunder.setString(2, "%" + DiagnosaS.getText().trim() + "%");
                pspenyakitsekunder.setString(3, "%" + DiagnosaS.getText().trim() + "%");
                pspenyakitsekunder.setString(4, "%" + DiagnosaS.getText().trim() + "%");
                pspenyakitsekunder.setString(5, "%" + DiagnosaS.getText().trim() + "%");
                pspenyakitsekunder.setString(6, "%" + DiagnosaS.getText().trim() + "%");
                rs1 = pspenyakitsekunder.executeQuery();
                while (rs1.next()) {
                    tabModeDiagnosaSekunder.addRow(new Object[]{
                        false, 
                        rs1.getString(1),
                        rs1.getString(2),
                        rs1.getString(3),
                        rs1.getString(4),
                        rs1.getString(5),
                        rs1.getString(6)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }
                if (pspenyakitsekunder != null) {
                    pspenyakitsekunder.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilprosedure() {
        try {
            jml = 0;
            for (i = 0; i < tbProsedur.getRowCount(); i++) {
                if (tbProsedur.getValueAt(i, 0).toString().equals("true")) {
                    jml++;
                }
            }

            pilih = null;
            pilih = new boolean[jml];
            kode2 = null;
            kode2 = new String[jml];
            panjang = null;
            panjang = new String[jml];
            pendek = null;
            pendek = new String[jml];

            index = 0;
            for (i = 0; i < tbProsedur.getRowCount(); i++) {
                if (tbProsedur.getValueAt(i, 0).toString().equals("true")) {
                    pilih[index] = true;
                    kode2[index] = tbProsedur.getValueAt(i, 1).toString();
                    panjang[index] = tbProsedur.getValueAt(i, 2).toString();
                    pendek[index] = tbProsedur.getValueAt(i, 3).toString();
                    index++;
                }
            }

            Valid.tabelKosong(tabModeProsedur);
            for (i = 0; i < jml; i++) {
                tabModeProsedur.addRow(new Object[]{pilih[i], kode2[i], panjang[i], pendek[i]});
            }

            psprosedur = koneksi.prepareStatement("select * from icd9 where kode like ? or "
                    + " deskripsi_panjang like ? or  deskripsi_pendek like ? order by kode");
            try {
                psprosedur.setString(1, "%" + Prosedur.getText().trim() + "%");
                psprosedur.setString(2, "%" + Prosedur.getText().trim() + "%");
                psprosedur.setString(3, "%" + Prosedur.getText().trim() + "%");
                rs = psprosedur.executeQuery();
                while (rs.next()) {
                    tabModeProsedur.addRow(new Object[]{
                        false, rs.getString(1), rs.getString(2), rs.getString(3)});
                }
            } catch (Exception ex) {
                System.out.println(ex);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (psprosedur != null) {
                    psprosedur.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void tampilRiwayatDiag() {
        Valid.tabelKosong(TabModeDiagnosaPasien);
        try {
            psdiagnosapasien = koneksi.prepareStatement("select reg_periksa.tgl_registrasi,diagnosa_pasien.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit, diagnosa_pasien.status, if(diagnosa_pasien.prioritas='1','Primer','Sekunder') prior, "
                    + "if(diagnosa_pasien.nip_petugas='Admin Utama','Admin Utama',ifnull(pg.nama,'-')) nmPetugas from diagnosa_pasien "
                    + "inner join reg_periksa inner join pasien inner join penyakit "
                    + "on diagnosa_pasien.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit left join pegawai pg on pg.nik = diagnosa_pasien.nip_petugas where "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rawat like ? "
                    + "order by reg_periksa.tgl_registrasi,diagnosa_pasien.prioritas ");
            try {
                psdiagnosapasien.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(3, "%" + norw + "%");
                rs = psdiagnosapasien.executeQuery();
                while (rs.next()) {
                    TabModeDiagnosaPasien.addRow(new Object[]{
                        false, 
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (psdiagnosapasien != null) {
                    psdiagnosapasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void tampilProsedurTindakan() {
        Valid.tabelKosong(TabModeTindakanPasien);
        try {
            pstindakanpasien = koneksi.prepareStatement("select reg_periksa.tgl_registrasi,prosedur_pasien.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "prosedur_pasien.kode,icd9.deskripsi_panjang, prosedur_pasien.status, if(prosedur_pasien.nip_petugas='Admin Utama','Admin Utama',ifnull(pg.nama,'-')) nmPetugas "
                    + "from prosedur_pasien inner join reg_periksa inner join pasien inner join icd9 "
                    + "on prosedur_pasien.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and prosedur_pasien.kode=icd9.kode left join pegawai pg on pg.nik = prosedur_pasien.nip_petugas where "                    
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rawat like ? "
                    + "order by reg_periksa.tgl_registrasi,prosedur_pasien.prioritas ");
            try {
                pstindakanpasien.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(3, "%" + norw + "%");
                rs = pstindakanpasien.executeQuery();
                while (rs.next()) {
                    TabModeTindakanPasien.addRow(new Object[]{
                        false, 
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (pstindakanpasien != null) {
                    pstindakanpasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataICD10() {
        if (tbDiagnosaPasien.getSelectedRow() != -1) {
            norw = tbDiagnosaPasien.getValueAt(tbDiagnosaPasien.getSelectedRow(), 2).toString();
            Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", norm, norw);
            status = tbDiagnosaPasien.getValueAt(tbDiagnosaPasien.getSelectedRow(), 7).toString();
        }
    }
    
    private void getDataICD9() {
        if (tbTindakanPasien.getSelectedRow() != -1) {
            norw = tbTindakanPasien.getValueAt(tbTindakanPasien.getSelectedRow(), 2).toString();
            Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", norm, norw);
            status = tbTindakanPasien.getValueAt(tbTindakanPasien.getSelectedRow(), 7).toString();
        }
    }
    
    public void tampilDiagINADRG() {
        Valid.tabelKosong(tabMode1);
        try {
            psinadrg = koneksi.prepareStatement("select reg_periksa.tgl_registrasi,diagnosa_pasien_inadrg.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien, "
                    + "diagnosa_pasien_inadrg.kd_penyakit,penyakit.nm_penyakit, diagnosa_pasien_inadrg.status, if(diagnosa_pasien_inadrg.prioritas='1','Primer','Sekunder') prior, "
                    + "if(diagnosa_pasien_inadrg.nip_petugas='Admin Utama','Admin Utama',ifnull(pg.nama,'-')) nmPetugas from diagnosa_pasien_inadrg "
                    + "inner join reg_periksa inner join pasien inner join penyakit "
                    + "on diagnosa_pasien_inadrg.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and diagnosa_pasien_inadrg.kd_penyakit=penyakit.kd_penyakit left join pegawai pg on pg.nik = diagnosa_pasien_inadrg.nip_petugas where "
                    + "reg_periksa.tgl_registrasi between ? and ? and diagnosa_pasien_inadrg.no_rawat like ? "
                    + "order by reg_periksa.tgl_registrasi,diagnosa_pasien_inadrg.prioritas");
            try {
                psinadrg.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(3, "%" + norw + "%");
                rs = psinadrg.executeQuery();
                while (rs.next()) {
                    tabMode1.addRow(new Object[]{false, rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)
                    });
                }
                
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (psinadrg != null) {
                    psinadrg.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void tampilProsINADRG() {
        Valid.tabelKosong(tabMode2);
        try {
            psTINinadrg = koneksi.prepareStatement("SELECT reg_periksa.tgl_registrasi,prosedur_pasien_inadrg.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien, "
                    + "prosedur_pasien_inadrg.kode,icd9.deskripsi_panjang, prosedur_pasien_inadrg.status, prosedur_pasien_inadrg.qty, "
                    + "if(prosedur_pasien_inadrg.nip_petugas='Admin Utama','Admin Utama',ifnull(pg.nama,'-')) nmPetugas "
                    + "FROM prosedur_pasien_inadrg INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN icd9 "
                    + "ON prosedur_pasien_inadrg.no_rawat=reg_periksa.no_rawat AND reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "AND prosedur_pasien_inadrg.kode=icd9.kode left join pegawai pg on pg.nik = prosedur_pasien_inadrg.nip_petugas WHERE "
                    + "reg_periksa.tgl_registrasi BETWEEN ? AND ? AND prosedur_pasien_inadrg.no_rawat LIKE ? "
                    + "ORDER BY reg_periksa.tgl_registrasi,prosedur_pasien_inadrg.prioritas");
            try {
                psTINinadrg.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(3, "%" + norw + "%");
                rs = psTINinadrg.executeQuery();
                while (rs.next()) {
                    tabMode2.addRow(new Object[]{false, rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)
                    });
                }
                
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (psTINinadrg != null) {
                    psTINinadrg.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void simpan_diagproStatistik() {
        try {
            cek = 0;
            cekPremier = 0;
            koneksi.setAutoCommit(false);
            cek = Sequel.cariInteger("select count(-1) from diagnosa_pasien where no_rawat='" + norw + "' and prioritas=1");
            if (cek > 0) {
                for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                    if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                        JOptionPane.showMessageDialog(null, "Diagnosa primer sudah tersimpan sebelumnya...");
                    }
                }
                for (i = 0; i < tbDiagnosa1.getRowCount(); i++) {
                    if (tbDiagnosa1.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.menyimpan("diagnosa_pasien", "?,?,?,?,?", "Penyakit", 5, new String[]{
                            norw, tbDiagnosa1.getValueAt(i, 1).toString(), "Ralan", Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from diagnosa_pasien where no_rawat=? and status='Ralan'", norw),
                            akses.getkode()
                        });
                    }
                }
            } else {
                for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                    if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                        cekPremier++;
                    }
                }

                if (cekPremier > 1) {
                    JOptionPane.showMessageDialog(null, "Diagnosa primer hanya boleh ada 1 saja,...");
                } else {
                    for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                        if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.menyimpan("diagnosa_pasien", "?,?,?,?,?", "Penyakit", 5, new String[]{
                                norw, tbDiagnosa.getValueAt(i, 1).toString(), "Ralan", "1", akses.getkode()
                            });
                        }
                    }
                }

                if (cekPremier == 1) {
                    for (i = 0; i < tbDiagnosa1.getRowCount(); i++) {
                        if (tbDiagnosa1.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.menyimpan("diagnosa_pasien", "?,?,?,?,?", "Penyakit", 5, new String[]{
                                norw, tbDiagnosa1.getValueAt(i, 1).toString(), "Ralan", Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from diagnosa_pasien where no_rawat=? and status='Ralan'", norw),
                                akses.getkode()
                            });
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Silakan input diagnosa primer terlebih dulu...");
                }
            }

            koneksi.setAutoCommit(true);            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data diagnosa yang sama dimasukkan sebelumnya...!");
        }

        //---------------------------------
        try {
            koneksi.setAutoCommit(false);
            for (i = 0; i < tbProsedur.getRowCount(); i++) {
                if (tbProsedur.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan("prosedur_pasien", "?,?,?,?,?", "ICD 9", 5, new String[]{
                        norw, tbProsedur.getValueAt(i, 1).toString(), "Ralan", Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from prosedur_pasien where no_rawat=? and status='Ralan'", norw),
                        akses.getkode()
                    });
                }
            }
            koneksi.setAutoCommit(true);            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data prosedur/ICD9 yang sama dimasukkan sebelumnya...!");
        }
    }
    
    private void simpan_diagproINADRG() {
        try {
            cekINADRG = 0;
            cekPremierINADRG = 0;
            koneksi.setAutoCommit(false);
            cekINADRG = Sequel.cariInteger("select count(-1) from diagnosa_pasien_inadrg where no_rawat='" + norw + "' and prioritas=1");
            if (cekINADRG > 0) {
                for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                    if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                        JOptionPane.showMessageDialog(null, "Diagnosa primer INADRG sudah tersimpan sebelumnya...");
                    }
                }
                for (i = 0; i < tbDiagnosa1.getRowCount(); i++) {
                    if (tbDiagnosa1.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.menyimpan("diagnosa_pasien_inadrg", "?,?,?,?,?", "Penyakit", 5, new String[]{
                            norw, tbDiagnosa1.getValueAt(i, 1).toString(), "Ralan", 
                            Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from diagnosa_pasien_inadrg where no_rawat=? and status='Ralan'", norw),
                            akses.getkode()
                        });
                    }
                }
            } else {
                for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                    if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                        cekPremierINADRG++;
                    }
                }

                if (cekPremierINADRG > 1) {
                    JOptionPane.showMessageDialog(null, "Diagnosa primer INADRG hanya boleh ada 1 saja,...");
                } else {
                    for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                        if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.menyimpan("diagnosa_pasien_inadrg", "?,?,?,?,?", "Penyakit", 5, new String[]{
                                norw, tbDiagnosa.getValueAt(i, 1).toString(), "Ralan", "1", akses.getkode()
                            });
                        }
                    }
                }

                if (cekPremierINADRG == 1) {
                    for (i = 0; i < tbDiagnosa1.getRowCount(); i++) {
                        if (tbDiagnosa1.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.menyimpan("diagnosa_pasien_inadrg", "?,?,?,?,?", "Penyakit", 5, new String[]{
                                norw, tbDiagnosa1.getValueAt(i, 1).toString(), "Ralan", 
                                Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from diagnosa_pasien_inadrg where no_rawat=? and status='Ralan'", norw),
                                akses.getkode()
                            });
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Silakan input diagnosa primer INADRG terlebih dulu...");
                }
            }

            koneksi.setAutoCommit(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data diagnosa INADRG yang sama dimasukkan sebelumnya...!");
        }

        //---------------------------------
        try {
            koneksi.setAutoCommit(false);
            for (i = 0; i < tbProsedur.getRowCount(); i++) {
                if (tbProsedur.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan("prosedur_pasien_inadrg", "?,?,?,?,?,?", "ICD 9", 6, new String[]{
                        norw, tbProsedur.getValueAt(i, 1).toString(), "Ralan", 
                        Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from prosedur_pasien_inadrg where no_rawat=? and status='Ralan'", norw), "1", akses.getkode()
                    });
                }
            }
            koneksi.setAutoCommit(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data prosedur/ICD9 INADRG yang sama dimasukkan sebelumnya...!");
        }
    }
    
    private void hapus_diagproStatistik() {
        if (TabICD.getSelectedIndex() == 0) {
            if (TabModeDiagnosaPasien.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            } else {
                for (i = 0; i < tbDiagnosaPasien.getRowCount(); i++) {
                    if (tbDiagnosaPasien.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.queryu2("delete from diagnosa_pasien where no_rawat=? and kd_penyakit=?", 2, new String[]{
                            tbDiagnosaPasien.getValueAt(i, 2).toString(), tbDiagnosaPasien.getValueAt(i, 5).toString()
                        });
                    }
                }
            }
        } else if (TabICD.getSelectedIndex() == 1) {
            if (TabModeTindakanPasien.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            } else {
                for (i = 0; i < tbTindakanPasien.getRowCount(); i++) {
                    if (tbTindakanPasien.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.queryu2("delete from prosedur_pasien where no_rawat=? and kode=?", 2, new String[]{
                            tbTindakanPasien.getValueAt(i, 2).toString(), tbTindakanPasien.getValueAt(i, 5).toString()
                        });
                    }
                }
            }
        }
    }
    
    private void hapus_diagproINADRG() {
        if (TabICD.getSelectedIndex() == 2) {
            if (tabMode1.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data diagnosa INADRG sudah habis...!!!!");
            } else {
                for (i = 0; i < tbDiagnosaPasien1.getRowCount(); i++) {
                    if (tbDiagnosaPasien1.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.queryu2("delete from diagnosa_pasien_inadrg where no_rawat=? and kd_penyakit=?", 2, new String[]{
                            tbDiagnosaPasien1.getValueAt(i, 2).toString(), tbDiagnosaPasien1.getValueAt(i, 5).toString()
                        });
                    }
                }
            }
        } else if (TabICD.getSelectedIndex() == 3) {
            if (tabMode2.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data prosedur/tindakan/ICD 9 INADRG sudah habis...!!!!");
            } else {
                for (i = 0; i < tbTindakanPasien1.getRowCount(); i++) {
                    if (tbTindakanPasien1.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.queryu2("delete from prosedur_pasien_inadrg where no_rawat=? and kode=?", 2, new String[]{
                            tbTindakanPasien1.getValueAt(i, 2).toString(), tbTindakanPasien1.getValueAt(i, 5).toString()
                        });
                    }
                }
            }
        }
    }
    
    private void simpanQTYprosedurINADRG() {
        jlhTindakan = ""; 

        for (i = 0; i < tbTindakanPasien1.getRowCount(); i++) {
            if (tbTindakanPasien1.getValueAt(i, 0).toString().equals("true")) {                
                if (!tbTindakanPasien1.getValueAt(i, 8).toString().equals("") && Integer.parseInt(tbTindakanPasien1.getValueAt(i, 8).toString()) > 1) {
                    jlhTindakan = tbTindakanPasien1.getValueAt(i, 8).toString();
                    Sequel.mengedit("prosedur_pasien_inadrg",
                            "no_rawat='" + norw + "' and kode='" + tbTindakanPasien1.getValueAt(i, 5).toString() + "' and status='" + tbTindakanPasien1.getValueAt(i, 7).toString() + "'",
                            "qty='" + jlhTindakan + "'");
                } else if (Integer.parseInt(tbTindakanPasien1.getValueAt(i, 8).toString()) == 1) {
                    jlhTindakan = tbTindakanPasien1.getValueAt(i, 8).toString();
                    Sequel.mengedit("prosedur_pasien_inadrg",
                            "no_rawat='" + norw + "' and kode='" + tbTindakanPasien1.getValueAt(i, 5).toString() + "' and status='" + tbTindakanPasien1.getValueAt(i, 7).toString() + "'",
                            "qty='" + jlhTindakan + "'");
                }          
            }
        }
    }
}
