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
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgNotepad;

/**
 *
 * @author dosen
 */
public class DlgCatatanTindakanKeperawatan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabMode4, tabMode5, tabMode6, tabModeCppt;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, psPagi, psSore, psMalam, pscppt;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rsPagi, rsSore, rsMalam, rscppt;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String nip = "", nipCopy = "", nipCopy1 = "", wktSimpan = "", kodeTindakan = "", dataKonfirmasi = "", nmgedung = "",
            wktSimpanPagi = "", wktSimpanSore = "", wktSimpanMalam = "", evaluasi = "", evaluasiPG = "",
            evaluasiSR = "", evaluasiML = "";
    private int x = 0, i = 0, pilihan = 0;
    private Date date = new Date(), jamSekarang, jamSift1, jamSift2, jamSift3,
            jamSekarangCopy, jamSift1Copy, jamSift2Copy, jamSift3Copy,
            jamSekarangCopy1, jamSift1Copy1, jamSift2Copy1, jamSift3Copy1;

    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public DlgCatatanTindakanKeperawatan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"Cek", "No. Rawat", "No. RM", "Nama Pasien", "Tanggal", "Sift", "Jam",
            "Nama Tindakan", "Petugas", "tgl", "jam", "nip", "waktu_simpan"
        };
        tabMode=new DefaultTableModel(null,row){
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbCatatan.setModel(tabMode);
        tbCatatan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbCatatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 13; i++) {
            TableColumn column = tbCatatan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(60);
            } else if (i == 6) {
                column.setPreferredWidth(60);
            } else if (i == 7) {
                column.setPreferredWidth(250);
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
            } 
        }

        tbCatatan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Nama Tindakan", "kode"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTindakan.setModel(tabMode1);
        tbTindakan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTindakan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 2; i++) {
            TableColumn column = tbTindakan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(550);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbTindakan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "Cek", "Tanggal", "Evaluasi Nyeri", "Keterangan Pagi", "tanggal", "waktu_simpan", "Sift", "no_rawat"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbPagi.setModel(tabMode2);
        tbPagi.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPagi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 8; i++) {
            TableColumn column = tbPagi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setPreferredWidth(60);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPagi.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3 = new DefaultTableModel(null, new Object[]{
            "Cek", "Tanggal", "Evaluasi Nyeri", "Keterangan Sore", "tanggal", "waktu_simpan", "Sift", "no_rawat"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbSore.setModel(tabMode3);
        tbSore.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbSore.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 8; i++) {
            TableColumn column = tbSore.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setPreferredWidth(60);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbSore.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode4 = new DefaultTableModel(null, new Object[]{
            "Cek", "Tanggal", "Evaluasi Nyeri", "Keterangan Malam", "tanggal", "waktu_simpan", "Sift", "no_rawat"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbMalam.setModel(tabMode4);
        tbMalam.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbMalam.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 8; i++) {
            TableColumn column = tbMalam.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setPreferredWidth(60);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbMalam.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode5 = new DefaultTableModel(null, new Object[]{
            "Manajemen Nyeri", "No. Rawat", "Tanggal", "tanggal"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbManajemen.setModel(tabMode5);
        tbManajemen.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbManajemen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbManajemen.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(500);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbManajemen.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeCppt=new DefaultTableModel(null, new Object[]{
            "Tgl. CPPT", "Jam CPPT", "Jenis Bagian", "DPJP Konsulen", "Jenis PPA",
            "Nama PPA", "Shift", "hasil", "instruksi", "no_rawat", "tgl_cppt", "jam_cppt"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbCPPT.setModel(tabModeCppt);
        tbCPPT.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbCPPT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbCPPT.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(70);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(40);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
            } 
        }
        tbCPPT.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode6 = new DefaultTableModel(null, new Object[]{
            "No. RM", "Nama Pasien", "Tanggal", "Sift", "Jam", "Nama Tindakan", "norawat", "evalusi_nyeri", 
            "ket_pagi", "ket_sore", "ket_malam", "manajemen"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbData.setModel(tabMode6);
        tbData.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 12; i++) {
            TableColumn column = tbData.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(50);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(50);
            } else if (i == 4) {
                column.setPreferredWidth(50);            
            } else if (i == 5) {
                column.setPreferredWidth(248);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
            }
        }
        tbData.setDefaultRenderer(Object.class, new WarnaTable());

        TnmTindakan.setDocument(new batasInput((int) 200).getKata(TnmTindakan));
        Tpagi.setDocument(new batasInput((int) 150).getKata(Tpagi));
        Tsore.setDocument(new batasInput((int) 150).getKata(Tsore));
        Tmalam.setDocument(new batasInput((int) 150).getKata(Tmalam));
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        
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
                if (petugas.getTable().getSelectedRow() != -1) {
                    if (pilihan == 1) {
                        nip = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        chkSaya.setSelected(false);
                        BtnPetugas.requestFocus();
                    } else if (pilihan == 2) {
                        nipCopy = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPetugasCopy.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        chkSayaCopy.setSelected(false);
                        BtnPetugasCopy.requestFocus();
                    } else if (pilihan == 3) {
                        nipCopy1 = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPetugasCopy1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        chkSayaCopy1.setSelected(false);
                        BtnPetugasCopy1.requestFocus();
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
        
        ChkAccor.setSelected(false);
        isMenu();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu = new javax.swing.JPopupMenu();
        MnUrutanJam = new javax.swing.JMenuItem();
        MnContengSemua = new javax.swing.JMenuItem();
        MnHapusConteng = new javax.swing.JMenuItem();
        MnCopyTindakan = new javax.swing.JMenuItem();
        MnCopyData = new javax.swing.JMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnHapus = new javax.swing.JMenuItem();
        jPopupMenuPagi = new javax.swing.JPopupMenu();
        MnHapusPagi = new javax.swing.JMenuItem();
        MnGantiPagi = new javax.swing.JMenuItem();
        MnCopyPagi = new javax.swing.JMenuItem();
        MnContengSemuaPagi = new javax.swing.JMenuItem();
        MnHapusContengPagi = new javax.swing.JMenuItem();
        jPopupMenuSore = new javax.swing.JPopupMenu();
        MnHapusSore = new javax.swing.JMenuItem();
        MnGantiSore = new javax.swing.JMenuItem();
        MnCopySore = new javax.swing.JMenuItem();
        MnContengSemuaSore = new javax.swing.JMenuItem();
        MnHapusContengSore = new javax.swing.JMenuItem();
        jPopupMenuMalam = new javax.swing.JPopupMenu();
        MnHapusMalam = new javax.swing.JMenuItem();
        MnGantiMalam = new javax.swing.JMenuItem();
        MnCopyMalam = new javax.swing.JMenuItem();
        MnContengSemuaMalam = new javax.swing.JMenuItem();
        MnHapusContengMalam = new javax.swing.JMenuItem();
        jPopupMenuManajemen = new javax.swing.JPopupMenu();
        MnHapusManajemen = new javax.swing.JMenuItem();
        MnGantiManajemen = new javax.swing.JMenuItem();
        MnCopyManajemen = new javax.swing.JMenuItem();
        WindowTindakan = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        tbTindakan = new widget.Table();
        panelisi5 = new widget.panelisi();
        jLabel48 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        WindowEvaluasi = new javax.swing.JDialog();
        internalFrame7 = new widget.InternalFrame();
        panelisi6 = new widget.panelisi();
        BtnSimpan1 = new widget.Button();
        BtnEdit1 = new widget.Button();
        BtnCloseIn2 = new widget.Button();
        panelisi7 = new widget.panelisi();
        jLabel50 = new widget.Label();
        cmbEvaluasi = new widget.ComboBox();
        jLabel54 = new widget.Label();
        Tpagi = new widget.TextBox();
        jLabel55 = new widget.Label();
        Tsore = new widget.TextBox();
        jLabel56 = new widget.Label();
        Tmalam = new widget.TextBox();
        WindowManajemen = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        Scroll7 = new widget.ScrollPane();
        TManajemen = new widget.TextArea();
        panelisi8 = new widget.panelisi();
        BtnSimpan2 = new widget.Button();
        BtnEdit2 = new widget.Button();
        BtnCloseIn3 = new widget.Button();
        WindowCetak = new javax.swing.JDialog();
        internalFrame9 = new widget.InternalFrame();
        panelisi10 = new widget.panelisi();
        jLabel51 = new widget.Label();
        TtglCetak = new widget.Tanggal();
        BtnPrint1 = new widget.Button();
        BtnCloseIn4 = new widget.Button();
        jLabel52 = new widget.Label();
        cmbSiftCetak = new widget.ComboBox();
        WindowCopyTindakan = new javax.swing.JDialog();
        internalFrame10 = new widget.InternalFrame();
        panelisi11 = new widget.panelisi();
        jLabel53 = new widget.Label();
        TtglCopy = new widget.Tanggal();
        jLabel11 = new widget.Label();
        cmbSiftCopy = new widget.ComboBox();
        jLabel12 = new widget.Label();
        cmbJamCopy = new widget.ComboBox();
        cmbMntCopy = new widget.ComboBox();
        cmbDtkCopy = new widget.ComboBox();
        jLabel13 = new widget.Label();
        TnmPetugasCopy = new widget.TextBox();
        BtnPetugasCopy = new widget.Button();
        chkSayaCopy = new widget.CekBox();
        panelisi12 = new widget.panelisi();
        BtnPaste = new widget.Button();
        BtnCloseIn5 = new widget.Button();
        WindowCopyData = new javax.swing.JDialog();
        internalFrame11 = new widget.InternalFrame();
        Scroll8 = new widget.ScrollPane();
        tbData = new widget.Table();
        panelisi15 = new widget.panelisi();
        panelisi14 = new widget.panelisi();
        ChkTglCTK = new widget.CekBox();
        TtglCTK = new widget.Tanggal();
        jLabel14 = new widget.Label();
        TCari2 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        panelisi16 = new widget.panelisi();
        jLabel16 = new widget.Label();
        cmbDicopy = new widget.ComboBox();
        BtnPasteData = new widget.Button();
        BtnCloseIn6 = new widget.Button();
        jLabel57 = new widget.Label();
        TtglCopy1 = new widget.Tanggal();
        jLabel15 = new widget.Label();
        cmbSiftCopy1 = new widget.ComboBox();
        jLabel17 = new widget.Label();
        cmbJamCopy1 = new widget.ComboBox();
        cmbMntCopy1 = new widget.ComboBox();
        cmbDtkCopy1 = new widget.ComboBox();
        jLabel18 = new widget.Label();
        TnmPetugasCopy1 = new widget.TextBox();
        BtnPetugasCopy1 = new widget.Button();
        chkSayaCopy1 = new widget.CekBox();
        internalFrame1 = new widget.InternalFrame();
        panelGlass10 = new widget.panelisi();
        jLabel3 = new widget.Label();
        TNoRW = new widget.TextBox();
        TNmPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel4 = new widget.Label();
        Ttgl = new widget.Tanggal();
        jLabel5 = new widget.Label();
        cmbSift = new widget.ComboBox();
        jLabel8 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel9 = new widget.Label();
        jLabel10 = new widget.Label();
        TnmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        TnmTindakan = new widget.TextBox();
        BtnTindakan = new widget.Button();
        chkSaya = new widget.CekBox();
        panelGlass13 = new widget.panelisi();
        Scroll = new widget.ScrollPane();
        tbCatatan = new widget.Table();
        panelGlass12 = new widget.panelisi();
        Scroll6 = new widget.ScrollPane();
        tbManajemen = new widget.Table();
        panelGlass11 = new widget.panelisi();
        Scroll2 = new widget.ScrollPane();
        tbPagi = new widget.Table();
        Scroll4 = new widget.ScrollPane();
        tbSore = new widget.Table();
        Scroll5 = new widget.ScrollPane();
        tbMalam = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnEvaluasi = new widget.Button();
        BtnManajemen = new widget.Button();
        BtnPrint = new widget.Button();
        BtnNotepad = new widget.Button();
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
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormMenu = new widget.PanelBiasa();
        Scroll3 = new widget.ScrollPane();
        tbCPPT = new widget.Table();
        panelGlass14 = new widget.panelisi();
        scrollPane5 = new widget.ScrollPane();
        Thasil = new widget.TextArea();
        scrollPane4 = new widget.ScrollPane();
        Tinstruksi = new widget.TextArea();

        jPopupMenu.setName("jPopupMenu"); // NOI18N

        MnUrutanJam.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutanJam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutanJam.setText("Urutkan Sesuai Jam");
        MnUrutanJam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutanJam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutanJam.setIconTextGap(5);
        MnUrutanJam.setName("MnUrutanJam"); // NOI18N
        MnUrutanJam.setPreferredSize(new java.awt.Dimension(155, 26));
        MnUrutanJam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutanJamActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnUrutanJam);

        MnContengSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnContengSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnContengSemua.setText("Conteng Semua");
        MnContengSemua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnContengSemua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnContengSemua.setIconTextGap(5);
        MnContengSemua.setName("MnContengSemua"); // NOI18N
        MnContengSemua.setPreferredSize(new java.awt.Dimension(155, 26));
        MnContengSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnContengSemuaActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnContengSemua);

        MnHapusConteng.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusConteng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusConteng.setText("Hapus Conteng");
        MnHapusConteng.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusConteng.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusConteng.setIconTextGap(5);
        MnHapusConteng.setName("MnHapusConteng"); // NOI18N
        MnHapusConteng.setPreferredSize(new java.awt.Dimension(155, 26));
        MnHapusConteng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusContengActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnHapusConteng);

        MnCopyTindakan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCopyTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCopyTindakan.setText("Copy Tindakan");
        MnCopyTindakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCopyTindakan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCopyTindakan.setIconTextGap(5);
        MnCopyTindakan.setName("MnCopyTindakan"); // NOI18N
        MnCopyTindakan.setPreferredSize(new java.awt.Dimension(155, 26));
        MnCopyTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCopyTindakanActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnCopyTindakan);

        MnCopyData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCopyData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCopyData.setText("Copy Data Pasien Lain");
        MnCopyData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCopyData.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCopyData.setIconTextGap(5);
        MnCopyData.setName("MnCopyData"); // NOI18N
        MnCopyData.setPreferredSize(new java.awt.Dimension(155, 26));
        MnCopyData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCopyDataActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnCopyData);

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnHapus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        MnHapus.setText("Hapus");
        MnHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapus.setIconTextGap(5);
        MnHapus.setName("MnHapus"); // NOI18N
        MnHapus.setPreferredSize(new java.awt.Dimension(90, 26));
        MnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapus);

        jPopupMenuPagi.setName("jPopupMenuPagi"); // NOI18N

        MnHapusPagi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusPagi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        MnHapusPagi.setText("Hapus Data");
        MnHapusPagi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusPagi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusPagi.setIconTextGap(5);
        MnHapusPagi.setName("MnHapusPagi"); // NOI18N
        MnHapusPagi.setPreferredSize(new java.awt.Dimension(130, 26));
        MnHapusPagi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusPagiActionPerformed(evt);
            }
        });
        jPopupMenuPagi.add(MnHapusPagi);

        MnGantiPagi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGantiPagi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGantiPagi.setText("Ganti Data");
        MnGantiPagi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGantiPagi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGantiPagi.setIconTextGap(5);
        MnGantiPagi.setName("MnGantiPagi"); // NOI18N
        MnGantiPagi.setPreferredSize(new java.awt.Dimension(130, 26));
        MnGantiPagi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGantiPagiActionPerformed(evt);
            }
        });
        jPopupMenuPagi.add(MnGantiPagi);

        MnCopyPagi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCopyPagi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCopyPagi.setText("Copy Evaluasi");
        MnCopyPagi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCopyPagi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCopyPagi.setIconTextGap(5);
        MnCopyPagi.setName("MnCopyPagi"); // NOI18N
        MnCopyPagi.setPreferredSize(new java.awt.Dimension(130, 26));
        MnCopyPagi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCopyPagiActionPerformed(evt);
            }
        });
        jPopupMenuPagi.add(MnCopyPagi);

        MnContengSemuaPagi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnContengSemuaPagi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnContengSemuaPagi.setText("Conteng Semua");
        MnContengSemuaPagi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnContengSemuaPagi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnContengSemuaPagi.setIconTextGap(5);
        MnContengSemuaPagi.setName("MnContengSemuaPagi"); // NOI18N
        MnContengSemuaPagi.setPreferredSize(new java.awt.Dimension(130, 26));
        MnContengSemuaPagi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnContengSemuaPagiActionPerformed(evt);
            }
        });
        jPopupMenuPagi.add(MnContengSemuaPagi);

        MnHapusContengPagi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusContengPagi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusContengPagi.setText("Hapus Conteng");
        MnHapusContengPagi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusContengPagi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusContengPagi.setIconTextGap(5);
        MnHapusContengPagi.setName("MnHapusContengPagi"); // NOI18N
        MnHapusContengPagi.setPreferredSize(new java.awt.Dimension(130, 26));
        MnHapusContengPagi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusContengPagiActionPerformed(evt);
            }
        });
        jPopupMenuPagi.add(MnHapusContengPagi);

        jPopupMenuSore.setName("jPopupMenuSore"); // NOI18N

        MnHapusSore.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusSore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        MnHapusSore.setText("Hapus Data");
        MnHapusSore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusSore.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusSore.setIconTextGap(5);
        MnHapusSore.setName("MnHapusSore"); // NOI18N
        MnHapusSore.setPreferredSize(new java.awt.Dimension(130, 26));
        MnHapusSore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusSoreActionPerformed(evt);
            }
        });
        jPopupMenuSore.add(MnHapusSore);

        MnGantiSore.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGantiSore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGantiSore.setText("Ganti Data");
        MnGantiSore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGantiSore.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGantiSore.setIconTextGap(5);
        MnGantiSore.setName("MnGantiSore"); // NOI18N
        MnGantiSore.setPreferredSize(new java.awt.Dimension(130, 26));
        MnGantiSore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGantiSoreActionPerformed(evt);
            }
        });
        jPopupMenuSore.add(MnGantiSore);

        MnCopySore.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCopySore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCopySore.setText("Copy Evaluasi");
        MnCopySore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCopySore.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCopySore.setIconTextGap(5);
        MnCopySore.setName("MnCopySore"); // NOI18N
        MnCopySore.setPreferredSize(new java.awt.Dimension(130, 26));
        MnCopySore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCopySoreActionPerformed(evt);
            }
        });
        jPopupMenuSore.add(MnCopySore);

        MnContengSemuaSore.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnContengSemuaSore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnContengSemuaSore.setText("Conteng Semua");
        MnContengSemuaSore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnContengSemuaSore.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnContengSemuaSore.setIconTextGap(5);
        MnContengSemuaSore.setName("MnContengSemuaSore"); // NOI18N
        MnContengSemuaSore.setPreferredSize(new java.awt.Dimension(130, 26));
        MnContengSemuaSore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnContengSemuaSoreActionPerformed(evt);
            }
        });
        jPopupMenuSore.add(MnContengSemuaSore);

        MnHapusContengSore.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusContengSore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusContengSore.setText("Hapus Conteng");
        MnHapusContengSore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusContengSore.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusContengSore.setIconTextGap(5);
        MnHapusContengSore.setName("MnHapusContengSore"); // NOI18N
        MnHapusContengSore.setPreferredSize(new java.awt.Dimension(130, 26));
        MnHapusContengSore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusContengSoreActionPerformed(evt);
            }
        });
        jPopupMenuSore.add(MnHapusContengSore);

        jPopupMenuMalam.setName("jPopupMenuMalam"); // NOI18N

        MnHapusMalam.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusMalam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        MnHapusMalam.setText("Hapus Data");
        MnHapusMalam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusMalam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusMalam.setIconTextGap(5);
        MnHapusMalam.setName("MnHapusMalam"); // NOI18N
        MnHapusMalam.setPreferredSize(new java.awt.Dimension(130, 26));
        MnHapusMalam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusMalamActionPerformed(evt);
            }
        });
        jPopupMenuMalam.add(MnHapusMalam);

        MnGantiMalam.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGantiMalam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGantiMalam.setText("Ganti Data");
        MnGantiMalam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGantiMalam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGantiMalam.setIconTextGap(5);
        MnGantiMalam.setName("MnGantiMalam"); // NOI18N
        MnGantiMalam.setPreferredSize(new java.awt.Dimension(130, 26));
        MnGantiMalam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGantiMalamActionPerformed(evt);
            }
        });
        jPopupMenuMalam.add(MnGantiMalam);

        MnCopyMalam.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCopyMalam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCopyMalam.setText("Copy Evaluasi");
        MnCopyMalam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCopyMalam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCopyMalam.setIconTextGap(5);
        MnCopyMalam.setName("MnCopyMalam"); // NOI18N
        MnCopyMalam.setPreferredSize(new java.awt.Dimension(130, 26));
        MnCopyMalam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCopyMalamActionPerformed(evt);
            }
        });
        jPopupMenuMalam.add(MnCopyMalam);

        MnContengSemuaMalam.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnContengSemuaMalam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnContengSemuaMalam.setText("Conteng Semua");
        MnContengSemuaMalam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnContengSemuaMalam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnContengSemuaMalam.setIconTextGap(5);
        MnContengSemuaMalam.setName("MnContengSemuaMalam"); // NOI18N
        MnContengSemuaMalam.setPreferredSize(new java.awt.Dimension(130, 26));
        MnContengSemuaMalam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnContengSemuaMalamActionPerformed(evt);
            }
        });
        jPopupMenuMalam.add(MnContengSemuaMalam);

        MnHapusContengMalam.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusContengMalam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusContengMalam.setText("Hapus Conteng");
        MnHapusContengMalam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusContengMalam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusContengMalam.setIconTextGap(5);
        MnHapusContengMalam.setName("MnHapusContengMalam"); // NOI18N
        MnHapusContengMalam.setPreferredSize(new java.awt.Dimension(130, 26));
        MnHapusContengMalam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusContengMalamActionPerformed(evt);
            }
        });
        jPopupMenuMalam.add(MnHapusContengMalam);

        jPopupMenuManajemen.setName("jPopupMenuManajemen"); // NOI18N

        MnHapusManajemen.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusManajemen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        MnHapusManajemen.setText("Hapus Data");
        MnHapusManajemen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusManajemen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusManajemen.setIconTextGap(5);
        MnHapusManajemen.setName("MnHapusManajemen"); // NOI18N
        MnHapusManajemen.setPreferredSize(new java.awt.Dimension(130, 26));
        MnHapusManajemen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusManajemenActionPerformed(evt);
            }
        });
        jPopupMenuManajemen.add(MnHapusManajemen);

        MnGantiManajemen.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGantiManajemen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGantiManajemen.setText("Ganti Data");
        MnGantiManajemen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGantiManajemen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGantiManajemen.setIconTextGap(5);
        MnGantiManajemen.setName("MnGantiManajemen"); // NOI18N
        MnGantiManajemen.setPreferredSize(new java.awt.Dimension(130, 26));
        MnGantiManajemen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGantiManajemenActionPerformed(evt);
            }
        });
        jPopupMenuManajemen.add(MnGantiManajemen);

        MnCopyManajemen.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCopyManajemen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCopyManajemen.setText("Copy Manajemen");
        MnCopyManajemen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCopyManajemen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCopyManajemen.setIconTextGap(5);
        MnCopyManajemen.setName("MnCopyManajemen"); // NOI18N
        MnCopyManajemen.setPreferredSize(new java.awt.Dimension(130, 26));
        MnCopyManajemen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCopyManajemenActionPerformed(evt);
            }
        });
        jPopupMenuManajemen.add(MnCopyManajemen);

        WindowTindakan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowTindakan.setName("WindowTindakan"); // NOI18N
        WindowTindakan.setUndecorated(true);
        WindowTindakan.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Daftar Nama Tindakan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame6.setLayout(new java.awt.BorderLayout());

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 250));

        tbTindakan.setToolTipText("Silahkan klik salah satu data yang akan dipakai");
        tbTindakan.setComponentPopupMenu(jPopupMenu1);
        tbTindakan.setName("tbTindakan"); // NOI18N
        tbTindakan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTindakanMouseClicked(evt);
            }
        });
        tbTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTindakanKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbTindakan);

        internalFrame6.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 7));

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Key Word :");
        jLabel48.setName("jLabel48"); // NOI18N
        jLabel48.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel48.setRequestFocusEnabled(false);
        panelisi5.add(jLabel48);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelisi5.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('1');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+1");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        BtnCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari1KeyPressed(evt);
            }
        });
        panelisi5.add(BtnCari1);

        BtnCloseIn1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn1.setMnemonic('U');
        BtnCloseIn1.setText("Tutup");
        BtnCloseIn1.setToolTipText("Alt+U");
        BtnCloseIn1.setName("BtnCloseIn1"); // NOI18N
        BtnCloseIn1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn1ActionPerformed(evt);
            }
        });
        panelisi5.add(BtnCloseIn1);

        internalFrame6.add(panelisi5, java.awt.BorderLayout.PAGE_END);

        WindowTindakan.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        WindowEvaluasi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowEvaluasi.setName("WindowEvaluasi"); // NOI18N
        WindowEvaluasi.setUndecorated(true);
        WindowEvaluasi.setResizable(false);

        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Evaluasi Nyeri ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame7.setLayout(new java.awt.BorderLayout());

        panelisi6.setName("panelisi6"); // NOI18N
        panelisi6.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 7));

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
        BtnSimpan1.setText("Simpan");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        panelisi6.add(BtnSimpan1);

        BtnEdit1.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit1.setMnemonic('G');
        BtnEdit1.setText("Ganti");
        BtnEdit1.setToolTipText("Alt+G");
        BtnEdit1.setName("BtnEdit1"); // NOI18N
        BtnEdit1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEdit1ActionPerformed(evt);
            }
        });
        panelisi6.add(BtnEdit1);

        BtnCloseIn2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn2.setMnemonic('U');
        BtnCloseIn2.setText("Tutup");
        BtnCloseIn2.setToolTipText("Alt+U");
        BtnCloseIn2.setName("BtnCloseIn2"); // NOI18N
        BtnCloseIn2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn2ActionPerformed(evt);
            }
        });
        panelisi6.add(BtnCloseIn2);

        internalFrame7.add(panelisi6, java.awt.BorderLayout.PAGE_END);

        panelisi7.setName("panelisi7"); // NOI18N
        panelisi7.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi7.setLayout(null);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Evaluasi Nyeri : ");
        jLabel50.setName("jLabel50"); // NOI18N
        jLabel50.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel50.setRequestFocusEnabled(false);
        panelisi7.add(jLabel50);
        jLabel50.setBounds(0, 8, 130, 23);

        cmbEvaluasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbEvaluasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Skala", "2. Lokasi", "3. Sifat", "4. Tindakan" }));
        cmbEvaluasi.setName("cmbEvaluasi"); // NOI18N
        cmbEvaluasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEvaluasiActionPerformed(evt);
            }
        });
        panelisi7.add(cmbEvaluasi);
        cmbEvaluasi.setBounds(132, 8, 100, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Keterangan Pagi : ");
        jLabel54.setName("jLabel54"); // NOI18N
        jLabel54.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel54.setRequestFocusEnabled(false);
        panelisi7.add(jLabel54);
        jLabel54.setBounds(0, 36, 130, 23);

        Tpagi.setForeground(new java.awt.Color(0, 0, 0));
        Tpagi.setName("Tpagi"); // NOI18N
        panelisi7.add(Tpagi);
        Tpagi.setBounds(132, 36, 430, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Keterangan Sore : ");
        jLabel55.setName("jLabel55"); // NOI18N
        jLabel55.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel55.setRequestFocusEnabled(false);
        panelisi7.add(jLabel55);
        jLabel55.setBounds(0, 64, 130, 23);

        Tsore.setForeground(new java.awt.Color(0, 0, 0));
        Tsore.setName("Tsore"); // NOI18N
        panelisi7.add(Tsore);
        Tsore.setBounds(132, 64, 430, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Keterangan Malam : ");
        jLabel56.setName("jLabel56"); // NOI18N
        jLabel56.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel56.setRequestFocusEnabled(false);
        panelisi7.add(jLabel56);
        jLabel56.setBounds(0, 92, 130, 23);

        Tmalam.setForeground(new java.awt.Color(0, 0, 0));
        Tmalam.setName("Tmalam"); // NOI18N
        panelisi7.add(Tmalam);
        Tmalam.setBounds(132, 92, 430, 23);

        internalFrame7.add(panelisi7, java.awt.BorderLayout.CENTER);

        WindowEvaluasi.getContentPane().add(internalFrame7, java.awt.BorderLayout.CENTER);

        WindowManajemen.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowManajemen.setName("WindowManajemen"); // NOI18N
        WindowManajemen.setUndecorated(true);
        WindowManajemen.setResizable(false);

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Manajemen Nyeri ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame8.setLayout(new java.awt.BorderLayout());

        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        TManajemen.setColumns(20);
        TManajemen.setRows(5);
        TManajemen.setName("TManajemen"); // NOI18N
        TManajemen.setPreferredSize(new java.awt.Dimension(170, 1000));
        Scroll7.setViewportView(TManajemen);

        internalFrame8.add(Scroll7, java.awt.BorderLayout.CENTER);

        panelisi8.setName("panelisi8"); // NOI18N
        panelisi8.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 7));

        BtnSimpan2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan2.setMnemonic('S');
        BtnSimpan2.setText("Simpan");
        BtnSimpan2.setToolTipText("Alt+S");
        BtnSimpan2.setName("BtnSimpan2"); // NOI18N
        BtnSimpan2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan2ActionPerformed(evt);
            }
        });
        panelisi8.add(BtnSimpan2);

        BtnEdit2.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit2.setMnemonic('G');
        BtnEdit2.setText("Ganti");
        BtnEdit2.setToolTipText("Alt+G");
        BtnEdit2.setName("BtnEdit2"); // NOI18N
        BtnEdit2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEdit2ActionPerformed(evt);
            }
        });
        panelisi8.add(BtnEdit2);

        BtnCloseIn3.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn3.setMnemonic('U');
        BtnCloseIn3.setText("Tutup");
        BtnCloseIn3.setToolTipText("Alt+U");
        BtnCloseIn3.setName("BtnCloseIn3"); // NOI18N
        BtnCloseIn3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn3ActionPerformed(evt);
            }
        });
        panelisi8.add(BtnCloseIn3);

        internalFrame8.add(panelisi8, java.awt.BorderLayout.PAGE_END);

        WindowManajemen.getContentPane().add(internalFrame8, java.awt.BorderLayout.CENTER);

        WindowCetak.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCetak.setName("WindowCetak"); // NOI18N
        WindowCetak.setUndecorated(true);
        WindowCetak.setResizable(false);

        internalFrame9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Cetak Catatan Tindakan Keperawatan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame9.setLayout(new java.awt.BorderLayout());

        panelisi10.setName("panelisi10"); // NOI18N
        panelisi10.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi10.setLayout(null);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Tanggal : ");
        jLabel51.setName("jLabel51"); // NOI18N
        jLabel51.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel51.setRequestFocusEnabled(false);
        panelisi10.add(jLabel51);
        jLabel51.setBounds(0, 8, 80, 23);

        TtglCetak.setEditable(false);
        TtglCetak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-02-2024" }));
        TtglCetak.setDisplayFormat("dd-MM-yyyy");
        TtglCetak.setName("TtglCetak"); // NOI18N
        TtglCetak.setOpaque(false);
        TtglCetak.setPreferredSize(new java.awt.Dimension(95, 23));
        panelisi10.add(TtglCetak);
        TtglCetak.setBounds(82, 8, 95, 23);

        BtnPrint1.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnPrint1.setMnemonic('T');
        BtnPrint1.setText("Cetak");
        BtnPrint1.setToolTipText("Alt+T");
        BtnPrint1.setName("BtnPrint1"); // NOI18N
        BtnPrint1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint1ActionPerformed(evt);
            }
        });
        panelisi10.add(BtnPrint1);
        BtnPrint1.setBounds(305, 8, 85, 23);

        BtnCloseIn4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setMnemonic('U');
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+U");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        panelisi10.add(BtnCloseIn4);
        BtnCloseIn4.setBounds(400, 8, 90, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Sift : ");
        jLabel52.setName("jLabel52"); // NOI18N
        jLabel52.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel52.setRequestFocusEnabled(false);
        panelisi10.add(jLabel52);
        jLabel52.setBounds(178, 8, 45, 23);

        cmbSiftCetak.setForeground(new java.awt.Color(0, 0, 0));
        cmbSiftCetak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "Pagi", "Sore", "Malam" }));
        cmbSiftCetak.setName("cmbSiftCetak"); // NOI18N
        panelisi10.add(cmbSiftCetak);
        cmbSiftCetak.setBounds(225, 8, 70, 23);

        internalFrame9.add(panelisi10, java.awt.BorderLayout.CENTER);

        WindowCetak.getContentPane().add(internalFrame9, java.awt.BorderLayout.CENTER);

        WindowCopyTindakan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCopyTindakan.setName("WindowCopyTindakan"); // NOI18N
        WindowCopyTindakan.setUndecorated(true);
        WindowCopyTindakan.setResizable(false);

        internalFrame10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Copy Catatan Tindakan Keperawatan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame10.setLayout(new java.awt.BorderLayout());

        panelisi11.setName("panelisi11"); // NOI18N
        panelisi11.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi11.setLayout(null);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Dicopy Ke Tgl. : ");
        jLabel53.setName("jLabel53"); // NOI18N
        jLabel53.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel53.setRequestFocusEnabled(false);
        panelisi11.add(jLabel53);
        jLabel53.setBounds(0, 8, 100, 23);

        TtglCopy.setEditable(false);
        TtglCopy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-02-2024" }));
        TtglCopy.setDisplayFormat("dd-MM-yyyy");
        TtglCopy.setName("TtglCopy"); // NOI18N
        TtglCopy.setOpaque(false);
        TtglCopy.setPreferredSize(new java.awt.Dimension(95, 23));
        panelisi11.add(TtglCopy);
        TtglCopy.setBounds(102, 8, 95, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Sift Petugas :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelisi11.add(jLabel11);
        jLabel11.setBounds(200, 10, 80, 23);

        cmbSiftCopy.setForeground(new java.awt.Color(0, 0, 0));
        cmbSiftCopy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pagi", "Sore", "Malam" }));
        cmbSiftCopy.setName("cmbSiftCopy"); // NOI18N
        panelisi11.add(cmbSiftCopy);
        cmbSiftCopy.setBounds(285, 10, 70, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Jam Tindakan :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelisi11.add(jLabel12);
        jLabel12.setBounds(356, 10, 90, 23);

        cmbJamCopy.setForeground(new java.awt.Color(0, 0, 0));
        cmbJamCopy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJamCopy.setName("cmbJamCopy"); // NOI18N
        cmbJamCopy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamCopyMouseReleased(evt);
            }
        });
        panelisi11.add(cmbJamCopy);
        cmbJamCopy.setBounds(453, 10, 45, 23);

        cmbMntCopy.setForeground(new java.awt.Color(0, 0, 0));
        cmbMntCopy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMntCopy.setName("cmbMntCopy"); // NOI18N
        cmbMntCopy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntCopyMouseReleased(evt);
            }
        });
        panelisi11.add(cmbMntCopy);
        cmbMntCopy.setBounds(504, 10, 45, 23);

        cmbDtkCopy.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtkCopy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtkCopy.setName("cmbDtkCopy"); // NOI18N
        cmbDtkCopy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkCopyMouseReleased(evt);
            }
        });
        panelisi11.add(cmbDtkCopy);
        cmbDtkCopy.setBounds(555, 10, 45, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Nama Petugas : ");
        jLabel13.setName("jLabel13"); // NOI18N
        panelisi11.add(jLabel13);
        jLabel13.setBounds(0, 38, 100, 23);

        TnmPetugasCopy.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugasCopy.setName("TnmPetugasCopy"); // NOI18N
        panelisi11.add(TnmPetugasCopy);
        TnmPetugasCopy.setBounds(102, 38, 360, 23);

        BtnPetugasCopy.setForeground(new java.awt.Color(0, 0, 0));
        BtnPetugasCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugasCopy.setMnemonic('1');
        BtnPetugasCopy.setToolTipText("Alt+1");
        BtnPetugasCopy.setName("BtnPetugasCopy"); // NOI18N
        BtnPetugasCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasCopyActionPerformed(evt);
            }
        });
        panelisi11.add(BtnPetugasCopy);
        BtnPetugasCopy.setBounds(462, 38, 28, 23);

        chkSayaCopy.setBackground(new java.awt.Color(242, 242, 242));
        chkSayaCopy.setForeground(new java.awt.Color(0, 0, 0));
        chkSayaCopy.setText("Saya Sendiri");
        chkSayaCopy.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSayaCopy.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSayaCopy.setName("chkSayaCopy"); // NOI18N
        chkSayaCopy.setOpaque(false);
        chkSayaCopy.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSayaCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSayaCopyActionPerformed(evt);
            }
        });
        panelisi11.add(chkSayaCopy);
        chkSayaCopy.setBounds(505, 38, 90, 23);

        internalFrame10.add(panelisi11, java.awt.BorderLayout.CENTER);

        panelisi12.setName("panelisi12"); // NOI18N
        panelisi12.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 9, 9));

        BtnPaste.setForeground(new java.awt.Color(0, 0, 0));
        BtnPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnPaste.setMnemonic('C');
        BtnPaste.setText("Paste Tindakan");
        BtnPaste.setToolTipText("Alt+C");
        BtnPaste.setName("BtnPaste"); // NOI18N
        BtnPaste.setPreferredSize(new java.awt.Dimension(150, 30));
        BtnPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasteActionPerformed(evt);
            }
        });
        panelisi12.add(BtnPaste);

        BtnCloseIn5.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn5.setMnemonic('U');
        BtnCloseIn5.setText("Tutup");
        BtnCloseIn5.setToolTipText("Alt+U");
        BtnCloseIn5.setName("BtnCloseIn5"); // NOI18N
        BtnCloseIn5.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn5ActionPerformed(evt);
            }
        });
        panelisi12.add(BtnCloseIn5);

        internalFrame10.add(panelisi12, java.awt.BorderLayout.PAGE_END);

        WindowCopyTindakan.getContentPane().add(internalFrame10, java.awt.BorderLayout.CENTER);

        WindowCopyData.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCopyData.setName("WindowCopyData"); // NOI18N
        WindowCopyData.setUndecorated(true);
        WindowCopyData.setResizable(false);

        internalFrame11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Copy Catatan Tindakan Keperawatan Pasien Lain ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame11.setLayout(new java.awt.BorderLayout());

        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        tbData.setToolTipText("Silahkan klik untuk memilih data yang dipilih");
        tbData.setName("tbData"); // NOI18N
        tbData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDataMouseClicked(evt);
            }
        });
        tbData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDataKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbDataKeyReleased(evt);
            }
        });
        Scroll8.setViewportView(tbData);

        internalFrame11.add(Scroll8, java.awt.BorderLayout.CENTER);

        panelisi15.setName("panelisi15"); // NOI18N
        panelisi15.setPreferredSize(new java.awt.Dimension(100, 157));
        panelisi15.setLayout(new java.awt.BorderLayout());

        panelisi14.setName("panelisi14"); // NOI18N
        panelisi14.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 8));

        ChkTglCTK.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTglCTK.setForeground(new java.awt.Color(0, 0, 0));
        ChkTglCTK.setText("Tgl. CTK :");
        ChkTglCTK.setBorderPainted(true);
        ChkTglCTK.setBorderPaintedFlat(true);
        ChkTglCTK.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkTglCTK.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTglCTK.setName("ChkTglCTK"); // NOI18N
        ChkTglCTK.setPreferredSize(new java.awt.Dimension(80, 23));
        ChkTglCTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkTglCTKActionPerformed(evt);
            }
        });
        panelisi14.add(ChkTglCTK);

        TtglCTK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-02-2024" }));
        TtglCTK.setDisplayFormat("dd-MM-yyyy");
        TtglCTK.setName("TtglCTK"); // NOI18N
        TtglCTK.setOpaque(false);
        TtglCTK.setPreferredSize(new java.awt.Dimension(95, 23));
        panelisi14.add(TtglCTK);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Key Word :");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi14.add(jLabel14);

        TCari2.setForeground(new java.awt.Color(0, 0, 0));
        TCari2.setName("TCari2"); // NOI18N
        TCari2.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari2KeyPressed(evt);
            }
        });
        panelisi14.add(TCari2);

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
        panelisi14.add(BtnCari2);

        panelisi15.add(panelisi14, java.awt.BorderLayout.PAGE_START);

        panelisi16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Tujuan Copy Data ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        panelisi16.setName("panelisi16"); // NOI18N
        panelisi16.setPreferredSize(new java.awt.Dimension(100, 110));
        panelisi16.setLayout(null);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Yang Dicopy : ");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi16.add(jLabel16);
        jLabel16.setBounds(0, 76, 100, 23);

        cmbDicopy.setForeground(new java.awt.Color(0, 0, 0));
        cmbDicopy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Per Nama Tindakan Sesuai Data Dipilih", "Semua Tindakan Pada Tanggal & Sift Dipilih" }));
        cmbDicopy.setName("cmbDicopy"); // NOI18N
        cmbDicopy.setPreferredSize(new java.awt.Dimension(245, 23));
        panelisi16.add(cmbDicopy);
        cmbDicopy.setBounds(102, 76, 240, 23);

        BtnPasteData.setForeground(new java.awt.Color(0, 0, 0));
        BtnPasteData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnPasteData.setMnemonic('P');
        BtnPasteData.setText("Paste Data");
        BtnPasteData.setToolTipText("Alt+P");
        BtnPasteData.setName("BtnPasteData"); // NOI18N
        BtnPasteData.setPreferredSize(new java.awt.Dimension(120, 30));
        BtnPasteData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasteDataActionPerformed(evt);
            }
        });
        panelisi16.add(BtnPasteData);
        BtnPasteData.setBounds(350, 76, 110, 23);

        BtnCloseIn6.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn6.setMnemonic('U');
        BtnCloseIn6.setText("Tutup");
        BtnCloseIn6.setToolTipText("Alt+U");
        BtnCloseIn6.setName("BtnCloseIn6"); // NOI18N
        BtnCloseIn6.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn6ActionPerformed(evt);
            }
        });
        panelisi16.add(BtnCloseIn6);
        BtnCloseIn6.setBounds(480, 76, 90, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Dicopy Ke Tgl. : ");
        jLabel57.setName("jLabel57"); // NOI18N
        jLabel57.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel57.setRequestFocusEnabled(false);
        panelisi16.add(jLabel57);
        jLabel57.setBounds(0, 20, 100, 23);

        TtglCopy1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-02-2024" }));
        TtglCopy1.setDisplayFormat("dd-MM-yyyy");
        TtglCopy1.setName("TtglCopy1"); // NOI18N
        TtglCopy1.setOpaque(false);
        TtglCopy1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelisi16.add(TtglCopy1);
        TtglCopy1.setBounds(102, 20, 95, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Sift Petugas :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelisi16.add(jLabel15);
        jLabel15.setBounds(200, 20, 80, 23);

        cmbSiftCopy1.setForeground(new java.awt.Color(0, 0, 0));
        cmbSiftCopy1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pagi", "Sore", "Malam" }));
        cmbSiftCopy1.setName("cmbSiftCopy1"); // NOI18N
        panelisi16.add(cmbSiftCopy1);
        cmbSiftCopy1.setBounds(285, 20, 70, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Jam Tindakan :");
        jLabel17.setName("jLabel17"); // NOI18N
        panelisi16.add(jLabel17);
        jLabel17.setBounds(356, 20, 90, 23);

        cmbJamCopy1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJamCopy1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJamCopy1.setName("cmbJamCopy1"); // NOI18N
        cmbJamCopy1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamCopy1MouseReleased(evt);
            }
        });
        panelisi16.add(cmbJamCopy1);
        cmbJamCopy1.setBounds(453, 20, 45, 23);

        cmbMntCopy1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMntCopy1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMntCopy1.setName("cmbMntCopy1"); // NOI18N
        cmbMntCopy1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntCopy1MouseReleased(evt);
            }
        });
        panelisi16.add(cmbMntCopy1);
        cmbMntCopy1.setBounds(504, 20, 45, 23);

        cmbDtkCopy1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtkCopy1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtkCopy1.setName("cmbDtkCopy1"); // NOI18N
        cmbDtkCopy1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkCopy1MouseReleased(evt);
            }
        });
        panelisi16.add(cmbDtkCopy1);
        cmbDtkCopy1.setBounds(555, 20, 45, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Nama Petugas : ");
        jLabel18.setName("jLabel18"); // NOI18N
        panelisi16.add(jLabel18);
        jLabel18.setBounds(0, 48, 100, 23);

        TnmPetugasCopy1.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugasCopy1.setName("TnmPetugasCopy1"); // NOI18N
        panelisi16.add(TnmPetugasCopy1);
        TnmPetugasCopy1.setBounds(102, 48, 360, 23);

        BtnPetugasCopy1.setForeground(new java.awt.Color(0, 0, 0));
        BtnPetugasCopy1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugasCopy1.setMnemonic('1');
        BtnPetugasCopy1.setToolTipText("Alt+1");
        BtnPetugasCopy1.setName("BtnPetugasCopy1"); // NOI18N
        BtnPetugasCopy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasCopy1ActionPerformed(evt);
            }
        });
        panelisi16.add(BtnPetugasCopy1);
        BtnPetugasCopy1.setBounds(462, 48, 28, 23);

        chkSayaCopy1.setBackground(new java.awt.Color(242, 242, 242));
        chkSayaCopy1.setForeground(new java.awt.Color(0, 0, 0));
        chkSayaCopy1.setText("Saya Sendiri");
        chkSayaCopy1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSayaCopy1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSayaCopy1.setName("chkSayaCopy1"); // NOI18N
        chkSayaCopy1.setOpaque(false);
        chkSayaCopy1.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSayaCopy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSayaCopy1ActionPerformed(evt);
            }
        });
        panelisi16.add(chkSayaCopy1);
        chkSayaCopy1.setBounds(505, 48, 90, 23);

        panelisi15.add(panelisi16, java.awt.BorderLayout.PAGE_END);

        internalFrame11.add(panelisi15, java.awt.BorderLayout.PAGE_END);

        WindowCopyData.getContentPane().add(internalFrame11, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Catatan Tindakan Keperawatan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 130));
        panelGlass10.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Pasien :");
        jLabel3.setName("jLabel3"); // NOI18N
        panelGlass10.add(jLabel3);
        jLabel3.setBounds(2, 10, 100, 23);

        TNoRW.setEditable(false);
        TNoRW.setForeground(new java.awt.Color(0, 0, 0));
        TNoRW.setName("TNoRW"); // NOI18N
        panelGlass10.add(TNoRW);
        TNoRW.setBounds(107, 10, 122, 23);

        TNmPasien.setEditable(false);
        TNmPasien.setForeground(new java.awt.Color(0, 0, 0));
        TNmPasien.setName("TNmPasien"); // NOI18N
        panelGlass10.add(TNmPasien);
        TNmPasien.setBounds(308, 10, 291, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        panelGlass10.add(TNoRM);
        TNoRM.setBounds(233, 10, 70, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tanggal :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass10.add(jLabel4);
        jLabel4.setBounds(2, 38, 100, 23);

        Ttgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-02-2024" }));
        Ttgl.setDisplayFormat("dd-MM-yyyy");
        Ttgl.setName("Ttgl"); // NOI18N
        Ttgl.setOpaque(false);
        Ttgl.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(Ttgl);
        Ttgl.setBounds(107, 38, 90, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Sift Petugas :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass10.add(jLabel5);
        jLabel5.setBounds(200, 38, 80, 23);

        cmbSift.setForeground(new java.awt.Color(0, 0, 0));
        cmbSift.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pagi", "Sore", "Malam" }));
        cmbSift.setName("cmbSift"); // NOI18N
        panelGlass10.add(cmbSift);
        cmbSift.setBounds(285, 38, 70, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Jam Tindakan :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass10.add(jLabel8);
        jLabel8.setBounds(356, 38, 90, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        panelGlass10.add(cmbJam);
        cmbJam.setBounds(453, 38, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        panelGlass10.add(cmbMnt);
        cmbMnt.setBounds(504, 38, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        panelGlass10.add(cmbDtk);
        cmbDtk.setBounds(555, 38, 45, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Nama Tindakan :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass10.add(jLabel9);
        jLabel9.setBounds(2, 66, 100, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Nama Petugas :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass10.add(jLabel10);
        jLabel10.setBounds(2, 94, 100, 23);

        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        panelGlass10.add(TnmPetugas);
        TnmPetugas.setBounds(107, 94, 360, 23);

        BtnPetugas.setForeground(new java.awt.Color(0, 0, 0));
        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setMnemonic('1');
        BtnPetugas.setToolTipText("Alt+1");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnPetugas);
        BtnPetugas.setBounds(470, 94, 28, 23);

        TnmTindakan.setForeground(new java.awt.Color(0, 0, 0));
        TnmTindakan.setName("TnmTindakan"); // NOI18N
        panelGlass10.add(TnmTindakan);
        TnmTindakan.setBounds(107, 66, 491, 23);

        BtnTindakan.setForeground(new java.awt.Color(0, 0, 0));
        BtnTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnTindakan.setMnemonic('1');
        BtnTindakan.setToolTipText("Alt+1");
        BtnTindakan.setName("BtnTindakan"); // NOI18N
        BtnTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTindakanActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnTindakan);
        BtnTindakan.setBounds(603, 66, 28, 23);

        chkSaya.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya.setText("Saya Sendiri");
        chkSaya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya.setName("chkSaya"); // NOI18N
        chkSaya.setOpaque(false);
        chkSaya.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSayaActionPerformed(evt);
            }
        });
        panelGlass10.add(chkSaya);
        chkSaya.setBounds(505, 94, 90, 23);

        internalFrame1.add(panelGlass10, java.awt.BorderLayout.PAGE_START);

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass13.setLayout(new java.awt.GridLayout(1, 2));

        Scroll.setComponentPopupMenu(jPopupMenu);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbCatatan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbCatatan.setComponentPopupMenu(jPopupMenu);
        tbCatatan.setName("tbCatatan"); // NOI18N
        tbCatatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCatatanMouseClicked(evt);
            }
        });
        tbCatatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCatatanKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbCatatanKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbCatatan);

        panelGlass13.add(Scroll);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 130));
        panelGlass12.setLayout(new java.awt.BorderLayout());

        Scroll6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Manajemen Nyeri ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);
        Scroll6.setPreferredSize(new java.awt.Dimension(460, 200));

        tbManajemen.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbManajemen.setComponentPopupMenu(jPopupMenuManajemen);
        tbManajemen.setName("tbManajemen"); // NOI18N
        tbManajemen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbManajemenMouseClicked(evt);
            }
        });
        tbManajemen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbManajemenKeyReleased(evt);
            }
        });
        Scroll6.setViewportView(tbManajemen);

        panelGlass12.add(Scroll6, java.awt.BorderLayout.PAGE_END);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 130));
        panelGlass11.setLayout(new java.awt.GridLayout(1, 3));

        Scroll2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Evaluasi Nyeri (PAGI) ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbPagi.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPagi.setComponentPopupMenu(jPopupMenuPagi);
        tbPagi.setName("tbPagi"); // NOI18N
        tbPagi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPagiMouseClicked(evt);
            }
        });
        tbPagi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPagiKeyReleased(evt);
            }
        });
        Scroll2.setViewportView(tbPagi);

        panelGlass11.add(Scroll2);

        Scroll4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Evaluasi Nyeri (SORE) ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbSore.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbSore.setComponentPopupMenu(jPopupMenuSore);
        tbSore.setName("tbSore"); // NOI18N
        tbSore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSoreMouseClicked(evt);
            }
        });
        tbSore.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbSoreKeyReleased(evt);
            }
        });
        Scroll4.setViewportView(tbSore);

        panelGlass11.add(Scroll4);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Evaluasi Nyeri (MALAM) ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbMalam.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbMalam.setComponentPopupMenu(jPopupMenuMalam);
        tbMalam.setName("tbMalam"); // NOI18N
        tbMalam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMalamMouseClicked(evt);
            }
        });
        tbMalam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbMalamKeyReleased(evt);
            }
        });
        Scroll5.setViewportView(tbMalam);

        panelGlass11.add(Scroll5);

        panelGlass12.add(panelGlass11, java.awt.BorderLayout.CENTER);

        panelGlass13.add(panelGlass12);

        internalFrame1.add(panelGlass13, java.awt.BorderLayout.CENTER);

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

        BtnEvaluasi.setForeground(new java.awt.Color(0, 0, 0));
        BtnEvaluasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnEvaluasi.setMnemonic('P');
        BtnEvaluasi.setText("Evaluasi Nyeri");
        BtnEvaluasi.setToolTipText("Alt+P");
        BtnEvaluasi.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnEvaluasi.setName("BtnEvaluasi"); // NOI18N
        BtnEvaluasi.setPreferredSize(new java.awt.Dimension(140, 30));
        BtnEvaluasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEvaluasiActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnEvaluasi);

        BtnManajemen.setForeground(new java.awt.Color(0, 0, 0));
        BtnManajemen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnManajemen.setMnemonic('P');
        BtnManajemen.setText("Manajemen Nyeri");
        BtnManajemen.setToolTipText("Alt+P");
        BtnManajemen.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnManajemen.setName("BtnManajemen"); // NOI18N
        BtnManajemen.setPreferredSize(new java.awt.Dimension(150, 30));
        BtnManajemen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnManajemenActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnManajemen);

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

        BtnNotepad.setForeground(new java.awt.Color(0, 0, 0));
        BtnNotepad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnNotepad.setMnemonic('N');
        BtnNotepad.setText("Notepad");
        BtnNotepad.setToolTipText("Alt+N");
        BtnNotepad.setName("BtnNotepad"); // NOI18N
        BtnNotepad.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNotepad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNotepadActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnNotepad);

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
        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-02-2024" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-02-2024" }));
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

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(900, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout());

        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
        ChkAccor.setToolTipText("Silahkan Klik Untuk Membaca CPPT");
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(22, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2leftarrow.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2leftarrow.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.WEST);

        FormMenu.setBackground(new java.awt.Color(250, 250, 245));
        FormMenu.setName("FormMenu"); // NOI18N
        FormMenu.setPreferredSize(new java.awt.Dimension(150, 483));
        FormMenu.setLayout(new java.awt.GridLayout(1, 2));

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " CPPT ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbCPPT.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbCPPT.setName("tbCPPT"); // NOI18N
        tbCPPT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCPPTMouseClicked(evt);
            }
        });
        tbCPPT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCPPTKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbCPPT);

        FormMenu.add(Scroll3);

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 300));
        panelGlass14.setLayout(new java.awt.BorderLayout());

        scrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Hasil Pemeriksaan, Analisa, Rencana, Penatalaksanaan Pasien ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane5.setName("scrollPane5"); // NOI18N
        scrollPane5.setPreferredSize(new java.awt.Dimension(212, 350));

        Thasil.setEditable(false);
        Thasil.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Thasil.setColumns(20);
        Thasil.setRows(5);
        Thasil.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Thasil.setName("Thasil"); // NOI18N
        Thasil.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane5.setViewportView(Thasil);

        panelGlass14.add(scrollPane5, java.awt.BorderLayout.PAGE_START);

        scrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Instruksi Tenaga Kesehatan Termasuk Pasca Bedah/Prosedur ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane4.setName("scrollPane4"); // NOI18N
        scrollPane4.setPreferredSize(new java.awt.Dimension(212, 150));

        Tinstruksi.setEditable(false);
        Tinstruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tinstruksi.setColumns(20);
        Tinstruksi.setRows(5);
        Tinstruksi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Tinstruksi.setName("Tinstruksi"); // NOI18N
        Tinstruksi.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane4.setViewportView(Tinstruksi);

        panelGlass14.add(scrollPane4, java.awt.BorderLayout.CENTER);

        FormMenu.add(panelGlass14);

        PanelAccor.add(FormMenu, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelAccor, java.awt.BorderLayout.EAST);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            Sequel.menyimpan("catatan_tindakan_keperawatan",
                    "'" + TNoRW.getText() + "','" + cmbSift.getSelectedItem().toString() + "',"
                    + "'" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "',"
                    + "'" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                    + "'" + TnmTindakan.getText() + "','" + nip + "','" + Sequel.cariIsi("select now()") + "'", "Catatan Tindakan Keperawatan");
            
            Sequel.simpanReplaceInto("master_catatan_tindakan_keperawatan", "'0','" + TnmTindakan.getText() + "'", "Data Master Catatan");
            tampil();
            emptTeks();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, TNmPasien, BtnBatal);
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
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                //cek conteng
                x = 0;
                for (i = 0; i < tbCatatan.getRowCount(); i++) {
                    if (tbCatatan.getValueAt(i, 0).toString().equals("true")) {
                        x++;
                    }
                }

                //jika tidak diconteng
                if (x == 0) {
                    if (tbCatatan.getSelectedRow() > -1) {
                        if (Sequel.queryu2tf("delete from catatan_tindakan_keperawatan where waktu_simpan=?", 1, new String[]{
                            tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 12).toString()
                        }) == true) {
                            tampil();
                            emptTeks();
                        } else {
                            JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu atau conteng dulu utk. menghapus data..!!");
                    }
                //jika diconteng
                } else {
                    try {
                        for (i = 0; i < tbCatatan.getRowCount(); i++) {
                            if (tbCatatan.getValueAt(i, 0).toString().equals("true")) {
                                Sequel.meghapus("catatan_tindakan_keperawatan", "waktu_simpan",
                                        tbCatatan.getValueAt(i, 12).toString());
                            }
                        }
                        tampil();
                        emptTeks();
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
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
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!!");
            tbCatatan.requestFocus();
        } else {
            if (tbCatatan.getSelectedRow() > -1) {
                Sequel.mengedit("catatan_tindakan_keperawatan", "waktu_simpan=?", "sift=?, tanggal=?, jam_tindakan=?, "
                        + "nm_tindakan=?, nip_petugas=?", 6, new String[]{
                            cmbSift.getSelectedItem().toString(), Valid.SetTgl(Ttgl.getSelectedItem() + ""),
                            cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                            TnmTindakan.getText(), nip,
                            wktSimpan
                });
                
                Sequel.simpanReplaceInto("master_catatan_tindakan_keperawatan", "'0','" + TnmTindakan.getText() + "'", "Data Master Catatan");
                tampil();
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        WindowTindakan.dispose();
        WindowEvaluasi.dispose();
        WindowManajemen.dispose();
        WindowCetak.dispose();
        WindowCopyTindakan.dispose();
        WindowCopyData.dispose();
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
            tbCatatan.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
        tampilPagi();
        tampilSore();
        tampilMalam();
        tampilManajemen();
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
       tampilPagi();
       tampilSore();
       tampilMalam();
       tampilManajemen();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari, TNoRW);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbCatatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCatatanMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbCatatanMouseClicked

    private void tbCatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCatatanKeyPressed
        if (tabMode.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCari.setText("");
                TCari.requestFocus();
            }
        }
}//GEN-LAST:event_tbCatatanKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void tbCatatanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCatatanKeyReleased
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbCatatanKeyReleased

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
        pilihan = 1;
        akses.setform("DlgCatatanTindakanKeperawatan");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void BtnTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTindakanActionPerformed
        WindowTindakan.setSize(603, internalFrame1.getHeight() - 40);
        WindowTindakan.setLocationRelativeTo(internalFrame1);
        WindowTindakan.setAlwaysOnTop(false);
        WindowTindakan.setVisible(true);
        tampilTindakan();
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnTindakanActionPerformed

    private void tbTindakanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTindakanMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                kodeTindakan = tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 1).toString();
            } catch (java.lang.NullPointerException e) {
            }
            
            if (evt.getClickCount() == 2) {
                if (tbTindakan.getSelectedRow() != -1) {
                    TnmTindakan.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 0).toString());
                    kodeTindakan = tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 1).toString();
                    WindowTindakan.dispose();
                }
            }
        }
    }//GEN-LAST:event_tbTindakanMouseClicked

    private void tbTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTindakanKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SPACE || evt.getKeyCode() == KeyEvent.VK_ENTER) {
                if (tbTindakan.getSelectedRow() != -1) {
                    TnmTindakan.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 0).toString());
                    kodeTindakan = tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 1).toString();
                    WindowTindakan.dispose();
                }
            }
        }
    }//GEN-LAST:event_tbTindakanKeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilTindakan();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        WindowTindakan.dispose();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (TNoRW.getText().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            if (Sequel.cariInteger("select count(-1) from catatan_tindakan_keperawatan where no_rawat='" + TNoRW.getText() + "'") > 0) {
                WindowCetak.setSize(519, 73);
                WindowCetak.setLocationRelativeTo(internalFrame1);
                WindowCetak.setAlwaysOnTop(false);
                WindowCetak.setVisible(true);
                TtglCetak.setDate(new Date());
                cmbSiftCetak.setSelectedIndex(0);
                TtglCetak.requestFocus();
            } else {
                if (tbCatatan.getSelectedRow() > -1) {
                    WindowCetak.setSize(519, 73);
                    WindowCetak.setLocationRelativeTo(internalFrame1);
                    WindowCetak.setAlwaysOnTop(false);
                    WindowCetak.setVisible(true);
                    TtglCetak.setDate(new Date());
                    cmbSiftCetak.setSelectedIndex(0);
                    TtglCetak.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih salah satu datanya terlebih dulu pada tabel catatan tindakan keperawatan..!!");
                    tbCatatan.requestFocus();
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

    private void MnContengSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnContengSemuaActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data catatan tindakan keperawatan belum ada...!!!!");
        } else {
            tampil();
            for (i = 0; i < tbCatatan.getRowCount(); i++) {
                tbCatatan.setValueAt(Boolean.TRUE, i, 0);
            }
        }
    }//GEN-LAST:event_MnContengSemuaActionPerformed

    private void MnHapusContengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusContengActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data catatan tindakan keperawatan belum ada...!!!!");
        } else {
            wktSimpan = "";
            tampil();
            for (i = 0; i < tbCatatan.getRowCount(); i++) {
                tbCatatan.setValueAt(Boolean.FALSE, i, 0);
            }
        }
    }//GEN-LAST:event_MnHapusContengActionPerformed

    private void MnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusActionPerformed
        if (kodeTindakan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu data nama tindakan yang akan dihapus..!!!!");
            tbTindakan.requestFocus();
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from master_catatan_tindakan_keperawatan where kode=?", 1, new String[]{
                    kodeTindakan
                }) == true) {
                    tampilTindakan();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            }
        }
    }//GEN-LAST:event_MnHapusActionPerformed

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        isMenu();
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void tbCPPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCPPTMouseClicked
        if (tabModeCppt.getRowCount() != 0) {
            try {
                getDataCppt();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbCPPTMouseClicked

    private void tbCPPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCPPTKeyPressed
        if (tabModeCppt.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataCppt();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbCPPTKeyPressed

    private void chkSayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaActionPerformed
        if (chkSaya.isSelected() == true) {
            if (akses.getadmin() == true) {
                nip = "-";
                TnmPetugas.setText("-");
            } else {
                nip = akses.getkode();
                TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nip + "'"));
            }
        } else {
            nip = "-";
            TnmPetugas.setText("-");
        }
    }//GEN-LAST:event_chkSayaActionPerformed

    private void tbPagiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPagiMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDataPagi();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPagiMouseClicked

    private void tbPagiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPagiKeyReleased
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataPagi();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPagiKeyReleased

    private void tbSoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSoreMouseClicked
        if (tabMode3.getRowCount() != 0) {
            try {
                getDataSore();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbSoreMouseClicked

    private void tbSoreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSoreKeyReleased
        if (tabMode3.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataSore();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbSoreKeyReleased

    private void tbMalamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMalamMouseClicked
        if (tabMode4.getRowCount() != 0) {
            try {
                getDataMalam();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbMalamMouseClicked

    private void tbMalamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMalamKeyReleased
        if (tabMode4.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataMalam();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbMalamKeyReleased

    private void BtnCloseIn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn2ActionPerformed
        WindowEvaluasi.dispose();
        emptTeksEvaluasi();
    }//GEN-LAST:event_BtnCloseIn2ActionPerformed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            if (cmbSift.getSelectedIndex() == 0) {
                if (Sequel.cariInteger("select count(-1) from evaluasi_catatan_tindakan_keperawatan where no_rawat='" + TNoRW.getText() + "' "
                        + "and sift='" + cmbSift.getSelectedItem().toString() + "' and evaluasi_nyeri='" + cmbEvaluasi.getSelectedItem() + "' "
                        + "and tanggal='" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "' and ket_pagi<>''") > 0) {
                    JOptionPane.showMessageDialog(null, "Data evaluasi nyeri " + cmbEvaluasi.getSelectedItem() + " sift " + cmbSift.getSelectedItem() + " tgl. " + Ttgl.getSelectedItem() + " sudah tersimpan,..!!");
                } else {
                    Sequel.menyimpan("evaluasi_catatan_tindakan_keperawatan", "'" + TNoRW.getText() + "','" + cmbSift.getSelectedItem().toString() + "',"
                            + "'" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "','" + cmbEvaluasi.getSelectedItem().toString() + "',"
                            + "'" + Tpagi.getText() + "','" + Tsore.getText() + "','" + Tmalam.getText() + "','" + Sequel.cariIsi("select now()") + "'", "Evaluasi Nyeri");
                }
            } else if (cmbSift.getSelectedIndex() == 1) {
                if (Sequel.cariInteger("select count(-1) from evaluasi_catatan_tindakan_keperawatan where no_rawat='" + TNoRW.getText() + "' "
                        + "and sift='" + cmbSift.getSelectedItem().toString() + "' and evaluasi_nyeri='" + cmbEvaluasi.getSelectedItem() + "' "
                        + "and tanggal='" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "' and ket_sore<>''") > 0) {
                    JOptionPane.showMessageDialog(null, "Data evaluasi nyeri " + cmbEvaluasi.getSelectedItem() + " sift " + cmbSift.getSelectedItem() + " tgl. " + Ttgl.getSelectedItem() + " sudah tersimpan,..!!");
                } else {
                    Sequel.menyimpan("evaluasi_catatan_tindakan_keperawatan", "'" + TNoRW.getText() + "','" + cmbSift.getSelectedItem().toString() + "',"
                            + "'" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "','" + cmbEvaluasi.getSelectedItem().toString() + "',"
                            + "'" + Tpagi.getText() + "','" + Tsore.getText() + "','" + Tmalam.getText() + "','" + Sequel.cariIsi("select now()") + "'", "Evaluasi Nyeri");
                }
            } else if (cmbSift.getSelectedIndex() == 2) {
                if (Sequel.cariInteger("select count(-1) from evaluasi_catatan_tindakan_keperawatan where no_rawat='" + TNoRW.getText() + "' "
                        + "and sift='" + cmbSift.getSelectedItem().toString() + "' and evaluasi_nyeri='" + cmbEvaluasi.getSelectedItem() + "' "
                        + "and tanggal='" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "' and ket_malam<>''") > 0) {
                    JOptionPane.showMessageDialog(null, "Data evaluasi nyeri " + cmbEvaluasi.getSelectedItem() + " sift " + cmbSift.getSelectedItem() + " tgl. " + Ttgl.getSelectedItem() + " sudah tersimpan,..!!");
                } else {
                    Sequel.menyimpan("evaluasi_catatan_tindakan_keperawatan", "'" + TNoRW.getText() + "','" + cmbSift.getSelectedItem().toString() + "',"
                            + "'" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "','" + cmbEvaluasi.getSelectedItem().toString() + "',"
                            + "'" + Tpagi.getText() + "','" + Tsore.getText() + "','" + Tmalam.getText() + "','" + Sequel.cariIsi("select now()") + "'", "Evaluasi Nyeri");
                }
            }
            
            tampilPagi();
            tampilSore();
            tampilMalam();
            tampilManajemen();
            emptTeksEvaluasi();
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEdit1ActionPerformed
        if (!wktSimpanPagi.equals("")) {
            Sequel.mengedit("evaluasi_catatan_tindakan_keperawatan", "waktu_simpan=?", "sift=?, tanggal=?, evaluasi_nyeri=?, "
                    + "ket_pagi=?, ket_sore=?, ket_malam=?", 7, new String[]{
                        cmbSift.getSelectedItem().toString(), Valid.SetTgl(Ttgl.getSelectedItem() + ""), cmbEvaluasi.getSelectedItem().toString(),
                        Tpagi.getText(), Tsore.getText(), Tmalam.getText(),
                        wktSimpanPagi
                    });
            tampilPagi();
            emptTeksEvaluasi();
            WindowEvaluasi.dispose();
        } else if (!wktSimpanSore.equals("")) {
            Sequel.mengedit("evaluasi_catatan_tindakan_keperawatan", "waktu_simpan=?", "sift=?, tanggal=?, evaluasi_nyeri=?, "
                    + "ket_pagi=?, ket_sore=?, ket_malam=?", 7, new String[]{
                        cmbSift.getSelectedItem().toString(), Valid.SetTgl(Ttgl.getSelectedItem() + ""), cmbEvaluasi.getSelectedItem().toString(),
                        Tpagi.getText(), Tsore.getText(), Tmalam.getText(),
                        wktSimpanSore
                    });
            tampilSore();
            emptTeksEvaluasi();
            WindowEvaluasi.dispose();
        } else if (!wktSimpanMalam.equals("")) {
            Sequel.mengedit("evaluasi_catatan_tindakan_keperawatan", "waktu_simpan=?", "sift=?, tanggal=?, evaluasi_nyeri=?, "
                    + "ket_pagi=?, ket_sore=?, ket_malam=?", 7, new String[]{
                        cmbSift.getSelectedItem().toString(), Valid.SetTgl(Ttgl.getSelectedItem() + ""), cmbEvaluasi.getSelectedItem().toString(),
                        Tpagi.getText(), Tsore.getText(), Tmalam.getText(),
                        wktSimpanMalam
                    });
            tampilMalam();
            emptTeksEvaluasi();
            WindowEvaluasi.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel,..!!");
        }
    }//GEN-LAST:event_BtnEdit1ActionPerformed

    private void BtnEvaluasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEvaluasiActionPerformed
        WindowEvaluasi.setSize(599, 197);
        WindowEvaluasi.setLocationRelativeTo(internalFrame1);
        WindowEvaluasi.setAlwaysOnTop(false);
        WindowEvaluasi.setVisible(true);
        emptTeksEvaluasi();
    }//GEN-LAST:event_BtnEvaluasiActionPerformed

    private void MnHapusPagiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusPagiActionPerformed
        if (TNoRW.getText().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                //cek conteng
                x = 0;
                for (i = 0; i < tbPagi.getRowCount(); i++) {
                    if (tbPagi.getValueAt(i, 0).toString().equals("true")) {
                        x++;
                    }
                }

                //jika tidak diconteng
                if (x == 0) {
                    if (tbPagi.getSelectedRow() > -1) {
                        if (Sequel.queryu2tf("delete from evaluasi_catatan_tindakan_keperawatan where waktu_simpan=?", 1, new String[]{
                            tbPagi.getValueAt(tbPagi.getSelectedRow(), 5).toString()
                        }) == true) {
                            tampilPagi();
                            emptTeksEvaluasi();
                        } else {
                            JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu atau conteng dulu utk. menghapus data..!!");
                    }
                //jika diconteng
                } else {
                    try {
                        for (i = 0; i < tbPagi.getRowCount(); i++) {
                            if (tbPagi.getValueAt(i, 0).toString().equals("true")) {
                                Sequel.meghapus("evaluasi_catatan_tindakan_keperawatan", "waktu_simpan",
                                        tbPagi.getValueAt(i, 5).toString());
                            }
                        }
                        tampilPagi();
                        emptTeksEvaluasi();
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
                
            } else {
                tampilPagi();
                emptTeksEvaluasi();
            }
        }
    }//GEN-LAST:event_MnHapusPagiActionPerformed

    private void MnHapusSoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusSoreActionPerformed
        if (TNoRW.getText().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                //cek conteng
                x = 0;
                for (i = 0; i < tbSore.getRowCount(); i++) {
                    if (tbSore.getValueAt(i, 0).toString().equals("true")) {
                        x++;
                    }
                }

                //jika tidak diconteng
                if (x == 0) {
                    if (tbSore.getSelectedRow() > -1) {
                        if (Sequel.queryu2tf("delete from evaluasi_catatan_tindakan_keperawatan where waktu_simpan=?", 1, new String[]{
                            tbSore.getValueAt(tbSore.getSelectedRow(), 5).toString()
                        }) == true) {
                            tampilSore();
                            emptTeksEvaluasi();
                        } else {
                            JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu atau conteng dulu utk. menghapus data..!!");
                    }
                //jika diconteng
                } else {
                    try {
                        for (i = 0; i < tbSore.getRowCount(); i++) {
                            if (tbSore.getValueAt(i, 0).toString().equals("true")) {
                                Sequel.meghapus("evaluasi_catatan_tindakan_keperawatan", "waktu_simpan",
                                        tbSore.getValueAt(i, 5).toString());
                            }
                        }
                        tampilSore();
                        emptTeksEvaluasi();
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
                
            } else {
                tampilSore();
                emptTeksEvaluasi();
            }
        }
    }//GEN-LAST:event_MnHapusSoreActionPerformed

    private void MnHapusMalamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusMalamActionPerformed
        if (TNoRW.getText().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                //cek conteng
                x = 0;
                for (i = 0; i < tbMalam.getRowCount(); i++) {
                    if (tbMalam.getValueAt(i, 0).toString().equals("true")) {
                        x++;
                    }
                }

                //jika tidak diconteng
                if (x == 0) {
                    if (tbMalam.getSelectedRow() > -1) {
                        if (Sequel.queryu2tf("delete from evaluasi_catatan_tindakan_keperawatan where waktu_simpan=?", 1, new String[]{
                            tbMalam.getValueAt(tbMalam.getSelectedRow(), 5).toString()
                        }) == true) {
                            tampilMalam();
                            emptTeksEvaluasi();
                        } else {
                            JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu atau conteng dulu utk. menghapus data..!!");
                    }
                //jika diconteng
                } else {
                    try {
                        for (i = 0; i < tbMalam.getRowCount(); i++) {
                            if (tbMalam.getValueAt(i, 0).toString().equals("true")) {
                                Sequel.meghapus("evaluasi_catatan_tindakan_keperawatan", "waktu_simpan",
                                        tbMalam.getValueAt(i, 5).toString());
                            }
                        }
                        tampilMalam();
                        emptTeksEvaluasi();
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
                
            } else {
                tampilMalam();
                emptTeksEvaluasi();
            }
        }
    }//GEN-LAST:event_MnHapusMalamActionPerformed

    private void MnGantiPagiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGantiPagiActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (wktSimpanPagi.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel evaluasi nyeri (Pagi),..!!");
            tbPagi.requestFocus();
        } else {
            WindowEvaluasi.setSize(599, 197);
            WindowEvaluasi.setLocationRelativeTo(internalFrame1);
            WindowEvaluasi.setAlwaysOnTop(false);
            WindowEvaluasi.setVisible(true);
        }
    }//GEN-LAST:event_MnGantiPagiActionPerformed

    private void MnGantiMalamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGantiMalamActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (wktSimpanMalam.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel evaluasi nyeri (Malam),..!!");
            tbMalam.requestFocus();
        } else {
            WindowEvaluasi.setSize(599, 197);
            WindowEvaluasi.setLocationRelativeTo(internalFrame1);
            WindowEvaluasi.setAlwaysOnTop(false);
            WindowEvaluasi.setVisible(true);
        }
    }//GEN-LAST:event_MnGantiMalamActionPerformed

    private void MnGantiSoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGantiSoreActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (wktSimpanSore.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel evaluasi nyeri (Sore),..!!");
            tbSore.requestFocus();
        } else {
            WindowEvaluasi.setSize(599, 197);
            WindowEvaluasi.setLocationRelativeTo(internalFrame1);
            WindowEvaluasi.setAlwaysOnTop(false);
            WindowEvaluasi.setVisible(true);
        }
    }//GEN-LAST:event_MnGantiSoreActionPerformed

    private void cmbEvaluasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEvaluasiActionPerformed
        Tpagi.setText("");
        Tsore.setText("");
        Tmalam.setText("");
        
        if (cmbSift.getSelectedIndex() == 0) {
            Tpagi.setEnabled(true);
            Tsore.setEnabled(false);
            Tmalam.setEnabled(false);
            Tpagi.requestFocus();
        } else if (cmbSift.getSelectedIndex() == 1) {
            Tpagi.setEnabled(false);
            Tsore.setEnabled(true);
            Tmalam.setEnabled(false);
            Tsore.requestFocus();
        } else if (cmbSift.getSelectedIndex() == 2) {
            Tpagi.setEnabled(false);
            Tsore.setEnabled(false);
            Tmalam.setEnabled(true);
            Tmalam.requestFocus();
        }
    }//GEN-LAST:event_cmbEvaluasiActionPerformed

    private void tbManajemenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbManajemenMouseClicked
        if (tabMode5.getRowCount() != 0) {
            try {
                getDataManajemen();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbManajemenMouseClicked

    private void tbManajemenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbManajemenKeyReleased
        if (tabMode5.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataManajemen();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbManajemenKeyReleased

    private void BtnManajemenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManajemenActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (Sequel.cariInteger("select count(-1) from manajemen_catatan_tindakan_keperawatan where no_rawat='" + TNoRW.getText() + "' "
                + "and tanggal='" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "'") > 0) {
            JOptionPane.showMessageDialog(null, "Data catatan tindakan keperawatan manajemen nyeri utk. tgl. " + Ttgl.getSelectedItem() + " sudah tersimpan,..!!");
        } else {
            WindowManajemen.setSize(664, 294);
            WindowManajemen.setLocationRelativeTo(internalFrame1);
            WindowManajemen.setAlwaysOnTop(false);
            WindowManajemen.setVisible(true);
            TManajemen.setText("");
            TManajemen.requestFocus();
        }
    }//GEN-LAST:event_BtnManajemenActionPerformed

    private void BtnSimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan2ActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            Sequel.menyimpan("manajemen_catatan_tindakan_keperawatan", "'" + TNoRW.getText() + "',"
                    + "'" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "','" + TManajemen.getText() + "'", "Manajemen Nyeri");

            tampilPagi();
            tampilSore();
            tampilMalam();
            tampilManajemen();
            TManajemen.setText("");
            WindowManajemen.dispose();
        }
    }//GEN-LAST:event_BtnSimpan2ActionPerformed

    private void BtnEdit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEdit2ActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            if (tbManajemen.getSelectedRow() > -1) {
                Sequel.mengedit("manajemen_catatan_tindakan_keperawatan", "no_rawat='" + tbManajemen.getValueAt(tbManajemen.getSelectedRow(), 1).toString() + "' "
                        + "and tanggal=?", "tanggal=?, manajemen_nyeri=?", 3, new String[]{
                            Valid.SetTgl(Ttgl.getSelectedItem() + ""), TManajemen.getText(),
                            tbManajemen.getValueAt(tbManajemen.getSelectedRow(), 3).toString()
                        });
                tampilManajemen();
                WindowManajemen.dispose();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dulu utk. perbaikan data..!!");
            }
        }
    }//GEN-LAST:event_BtnEdit2ActionPerformed

    private void BtnCloseIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn3ActionPerformed
        WindowManajemen.dispose();
        Ttgl.setDate(new Date());
    }//GEN-LAST:event_BtnCloseIn3ActionPerformed

    private void MnHapusManajemenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusManajemenActionPerformed
        if (TNoRW.getText().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            if (tbManajemen.getSelectedRow() > -1) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from manajemen_catatan_tindakan_keperawatan where "
                            + "no_rawat='" + tbManajemen.getValueAt(tbManajemen.getSelectedRow(), 1).toString() + "' and tanggal=?", 1, new String[]{
                        tbManajemen.getValueAt(tbManajemen.getSelectedRow(), 3).toString()
                    }) == true) {
                        tampilManajemen();
                        TManajemen.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                    }
                } else {
                    tampilManajemen();
                    TManajemen.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
            }
        }
    }//GEN-LAST:event_MnHapusManajemenActionPerformed

    private void MnGantiManajemenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGantiManajemenActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            if (tbManajemen.getSelectedRow() > -1) {
                WindowManajemen.setSize(664, 294);
                WindowManajemen.setLocationRelativeTo(internalFrame1);
                WindowManajemen.setAlwaysOnTop(false);
                WindowManajemen.setVisible(true);
                TManajemen.requestFocus();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
            }
        }
    }//GEN-LAST:event_MnGantiManajemenActionPerformed

    private void MnCopyTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCopyTindakanActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data catatan tindakan keperawatan masih kosong...!!!!");
        } else {
            //cek conteng
            x = 0;
            for (i = 0; i < tbCatatan.getRowCount(); i++) {
                if (tbCatatan.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng dulu data catatan tindakan keperawatan yang dipilih utk. di copy..!!!!");
                tbCatatan.requestFocus();
            } else {
                TtglCopy.setDate(new Date());
                cmbJamCopy.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
                cmbMntCopy.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
                cmbDtkCopy.setSelectedIndex(0);

                try {
                    jamSekarangCopy = new SimpleDateFormat("HH:mm").parse(Sequel.cariIsi("SELECT TIME_FORMAT(NOW(),'%H:%i')"));
                    jamSift1Copy = new SimpleDateFormat("HH:mm").parse("08:00");
                    jamSift2Copy = new SimpleDateFormat("HH:mm").parse("14:00");
                    jamSift3Copy = new SimpleDateFormat("HH:mm").parse("20:00");
                } catch (Exception e) {
                    System.out.println("Tanggal error, cek lagi..!!");
                }

                if (jamSift1Copy.before(jamSekarangCopy)) {
                    cmbSiftCopy.setSelectedIndex(0);
                }

                if (jamSift2Copy.before(jamSekarangCopy)) {
                    cmbSiftCopy.setSelectedIndex(1);
                }

                if (jamSift3Copy.before(jamSekarangCopy)) {
                    cmbSiftCopy.setSelectedIndex(2);
                }

                WindowCopyTindakan.setSize(642, 146);
                WindowCopyTindakan.setLocationRelativeTo(internalFrame1);
                WindowCopyTindakan.setAlwaysOnTop(false);
                WindowCopyTindakan.setVisible(true);
            }
        }
    }//GEN-LAST:event_MnCopyTindakanActionPerformed

    private void MnCopyPagiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCopyPagiActionPerformed
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data evaluasi nyeri sift pagi masih kosong...!!!!");
        } else {
            //cek conteng
            x = 0;
            for (i = 0; i < tbPagi.getRowCount(); i++) {
                if (tbPagi.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng dulu data evaluasi nyeri sift pagi yang dipilih utk. di copy..!!!!");
                tbPagi.requestFocus();
            } else {
                try {
                    for (i = 0; i < tbPagi.getRowCount(); i++) {
                        if (tbPagi.getValueAt(i, 0).toString().equals("true")) {
                            if (Sequel.cariInteger("select count(-1) from evaluasi_catatan_tindakan_keperawatan where "
                                    + "no_rawat='" + tbPagi.getValueAt(i, 7).toString() + "' "
                                    + "and sift='" + cmbSift.getSelectedItem().toString() + "' "
                                    + "and evaluasi_nyeri='" + tbPagi.getValueAt(i, 2).toString() + "' "
                                    + "and tanggal='" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "'") > 0) {
                                
                                System.out.println("Data Sudah Tersimpan : " + tbPagi.getValueAt(i, 2).toString() + " sift "
                                        + tbPagi.getValueAt(i, 6).toString() + " tgl. " + tbPagi.getValueAt(i, 4).toString());
                            } else {
                                if (cmbSift.getSelectedIndex() == 0) {
                                    Sequel.menyimpan("evaluasi_catatan_tindakan_keperawatan",
                                            "'" + tbPagi.getValueAt(i, 7).toString() + "',"
                                            + "'" + cmbSift.getSelectedItem().toString() + "',"
                                            + "'" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "',"
                                            + "'" + tbPagi.getValueAt(i, 2).toString() + "',"
                                            + "'" + tbPagi.getValueAt(i, 3).toString() + "',"
                                            + "'','','" + Sequel.cariIsi("select now()") + "'", "Evaluasi Nyeri (Pagi)");
                                } else if (cmbSift.getSelectedIndex() == 1) {
                                    Sequel.menyimpan("evaluasi_catatan_tindakan_keperawatan",
                                            "'" + tbPagi.getValueAt(i, 7).toString() + "',"
                                            + "'" + cmbSift.getSelectedItem().toString() + "',"
                                            + "'" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "',"
                                            + "'" + tbPagi.getValueAt(i, 2).toString() + "','',"
                                            + "'" + tbPagi.getValueAt(i, 3).toString() + "','',"
                                            + "'" + Sequel.cariIsi("select now()") + "'", "Evaluasi Nyeri (Pagi)");
                                } else if (cmbSift.getSelectedIndex() == 2) {
                                    Sequel.menyimpan("evaluasi_catatan_tindakan_keperawatan",
                                            "'" + tbPagi.getValueAt(i, 7).toString() + "',"
                                            + "'" + cmbSift.getSelectedItem().toString() + "',"
                                            + "'" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "',"
                                            + "'" + tbPagi.getValueAt(i, 2).toString() + "','','',"
                                            + "'" + tbPagi.getValueAt(i, 3).toString() + "',"
                                            + "'" + Sequel.cariIsi("select now()") + "'", "Evaluasi Nyeri (Pagi)");
                                }

                                //jeda 1 detik
                                Thread.sleep(1000);
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }

                DTPCari1.setDate(Ttgl.getDate());                
                tampilPagi();
                tampilSore();
                tampilMalam();
                tampilManajemen();
                emptTeksEvaluasi();
            }
        }
    }//GEN-LAST:event_MnCopyPagiActionPerformed

    private void MnCopySoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCopySoreActionPerformed
        if (tabMode3.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data evaluasi nyeri sift sore masih kosong...!!!!");
        } else {
            //cek conteng
            x = 0;
            for (i = 0; i < tbSore.getRowCount(); i++) {
                if (tbSore.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng dulu data evaluasi nyeri sift sore yang dipilih utk. di copy..!!!!");
                tbSore.requestFocus();
            } else {
                try {
                    for (i = 0; i < tbSore.getRowCount(); i++) {
                        if (tbSore.getValueAt(i, 0).toString().equals("true")) {
                            if (Sequel.cariInteger("select count(-1) from evaluasi_catatan_tindakan_keperawatan where "
                                    + "no_rawat='" + tbSore.getValueAt(i, 7).toString() + "' "
                                    + "and sift='" + cmbSift.getSelectedItem().toString() + "' "
                                    + "and evaluasi_nyeri='" + tbSore.getValueAt(i, 2).toString() + "' "
                                    + "and tanggal='" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "'") > 0) {
                                
                                System.out.println("Data Sudah Tersimpan : " + tbSore.getValueAt(i, 2).toString() + " sift "
                                        + tbSore.getValueAt(i, 6).toString() + " tgl. " + tbSore.getValueAt(i, 4).toString());
                            } else {
                                if (cmbSift.getSelectedIndex() == 0) {
                                    Sequel.menyimpan("evaluasi_catatan_tindakan_keperawatan",
                                            "'" + tbSore.getValueAt(i, 7).toString() + "',"
                                            + "'" + cmbSift.getSelectedItem().toString() + "',"
                                            + "'" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "',"
                                            + "'" + tbSore.getValueAt(i, 2).toString() + "',"
                                            + "'" + tbSore.getValueAt(i, 3).toString() + "','','',"
                                            + "'" + Sequel.cariIsi("select now()") + "'", "Evaluasi Nyeri (Sore)");
                                } else if (cmbSift.getSelectedIndex() == 1) {
                                    Sequel.menyimpan("evaluasi_catatan_tindakan_keperawatan",
                                            "'" + tbSore.getValueAt(i, 7).toString() + "',"
                                            + "'" + cmbSift.getSelectedItem().toString() + "',"
                                            + "'" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "',"
                                            + "'" + tbSore.getValueAt(i, 2).toString() + "','',"
                                            + "'" + tbSore.getValueAt(i, 3).toString() + "','',"
                                            + "'" + Sequel.cariIsi("select now()") + "'", "Evaluasi Nyeri (Sore)");
                                } else if (cmbSift.getSelectedIndex() == 2) {
                                    Sequel.menyimpan("evaluasi_catatan_tindakan_keperawatan",
                                            "'" + tbSore.getValueAt(i, 7).toString() + "',"
                                            + "'" + cmbSift.getSelectedItem().toString() + "',"
                                            + "'" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "',"
                                            + "'" + tbSore.getValueAt(i, 2).toString() + "','','',"
                                            + "'" + tbSore.getValueAt(i, 3).toString() + "',"
                                            + "'" + Sequel.cariIsi("select now()") + "'", "Evaluasi Nyeri (Sore)");
                                }

                                //jeda 1 detik
                                Thread.sleep(1000);
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }

                DTPCari1.setDate(Ttgl.getDate());
                tampilPagi();
                tampilSore();
                tampilMalam();
                tampilManajemen();
                emptTeksEvaluasi();
            }
        }
    }//GEN-LAST:event_MnCopySoreActionPerformed

    private void MnCopyMalamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCopyMalamActionPerformed
        if (tabMode4.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data evaluasi nyeri sift malam masih kosong...!!!!");
        } else {
            //cek conteng
            x = 0;
            for (i = 0; i < tbMalam.getRowCount(); i++) {
                if (tbMalam.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng dulu data evaluasi nyeri sift malam yang dipilih utk. di copy..!!!!");
                tbMalam.requestFocus();
            } else {
                try {
                    for (i = 0; i < tbMalam.getRowCount(); i++) {
                        if (tbMalam.getValueAt(i, 0).toString().equals("true")) {
                            if (Sequel.cariInteger("select count(-1) from evaluasi_catatan_tindakan_keperawatan where "
                                    + "no_rawat='" + tbMalam.getValueAt(i, 7).toString() + "' "
                                    + "and sift='" + cmbSift.getSelectedItem().toString() + "' "
                                    + "and evaluasi_nyeri='" + tbMalam.getValueAt(i, 2).toString() + "' "
                                    + "and tanggal='" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "'") > 0) {
                                
                                System.out.println("Data Sudah Tersimpan : " + tbMalam.getValueAt(i, 2).toString() + " sift "
                                        + tbMalam.getValueAt(i, 6).toString() + " tgl. " + tbMalam.getValueAt(i, 4).toString());
                            } else {
                                if (cmbSift.getSelectedIndex() == 0) {
                                    Sequel.menyimpan("evaluasi_catatan_tindakan_keperawatan",
                                            "'" + tbMalam.getValueAt(i, 7).toString() + "',"
                                            + "'" + cmbSift.getSelectedItem().toString() + "',"
                                            + "'" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "',"
                                            + "'" + tbMalam.getValueAt(i, 2).toString() + "',"
                                            + "'" + tbMalam.getValueAt(i, 3).toString() + "','','',"
                                            + "'" + Sequel.cariIsi("select now()") + "'", "Evaluasi Nyeri (Malam)");
                                } else if (cmbSift.getSelectedIndex() == 1) {
                                    Sequel.menyimpan("evaluasi_catatan_tindakan_keperawatan",
                                            "'" + tbMalam.getValueAt(i, 7).toString() + "',"
                                            + "'" + cmbSift.getSelectedItem().toString() + "',"
                                            + "'" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "',"
                                            + "'" + tbMalam.getValueAt(i, 2).toString() + "','',"
                                            + "'" + tbMalam.getValueAt(i, 3).toString() + "','',"
                                            + "'" + Sequel.cariIsi("select now()") + "'", "Evaluasi Nyeri (Malam)");
                                } else if (cmbSift.getSelectedIndex() == 2) {
                                    Sequel.menyimpan("evaluasi_catatan_tindakan_keperawatan",
                                            "'" + tbMalam.getValueAt(i, 7).toString() + "',"
                                            + "'" + cmbSift.getSelectedItem().toString() + "',"
                                            + "'" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "',"
                                            + "'" + tbMalam.getValueAt(i, 2).toString() + "','','',"
                                            + "'" + tbMalam.getValueAt(i, 3).toString() + "',"
                                            + "'" + Sequel.cariIsi("select now()") + "'", "Evaluasi Nyeri (Malam)");
                                }                                

                                //jeda 1 detik
                                Thread.sleep(1000);
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }

                DTPCari1.setDate(Ttgl.getDate());
                tampilPagi();
                tampilSore();
                tampilMalam();
                tampilManajemen();
                emptTeksEvaluasi();
            }
        }
    }//GEN-LAST:event_MnCopyMalamActionPerformed

    private void MnCopyManajemenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCopyManajemenActionPerformed
        if (TNoRW.getText().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            if (tbManajemen.getSelectedRow() > -1) {
                Sequel.menyimpan("manajemen_catatan_tindakan_keperawatan", "'" + TNoRW.getText() + "',"
                        + "'" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "','" + TManajemen.getText() + "'", "Manajemen Nyeri");

                DTPCari1.setDate(Ttgl.getDate());
                tampilPagi();
                tampilSore();
                tampilMalam();
                tampilManajemen();
                TManajemen.setText("");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
            }
        }
    }//GEN-LAST:event_MnCopyManajemenActionPerformed

    private void MnContengSemuaPagiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnContengSemuaPagiActionPerformed
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data evaluasi nyeri sift pagi belum ada...!!!!");
        } else {
            tampilPagi();
            for (i = 0; i < tbPagi.getRowCount(); i++) {
                tbPagi.setValueAt(Boolean.TRUE, i, 0);
            }
        }
    }//GEN-LAST:event_MnContengSemuaPagiActionPerformed

    private void MnHapusContengPagiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusContengPagiActionPerformed
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data evaluasi nyeri sift pagi belum ada...!!!!");
        } else {
            wktSimpanPagi = "";
            tampilPagi();
            for (i = 0; i < tbPagi.getRowCount(); i++) {
                tbPagi.setValueAt(Boolean.FALSE, i, 0);
            }
        }
    }//GEN-LAST:event_MnHapusContengPagiActionPerformed

    private void MnContengSemuaSoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnContengSemuaSoreActionPerformed
        if (tabMode3.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data evaluasi nyeri sift sore belum ada...!!!!");
        } else {
            tampilSore();
            for (i = 0; i < tbSore.getRowCount(); i++) {
                tbSore.setValueAt(Boolean.TRUE, i, 0);
            }
        }
    }//GEN-LAST:event_MnContengSemuaSoreActionPerformed

    private void MnHapusContengSoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusContengSoreActionPerformed
        if (tabMode3.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data evaluasi nyeri sift sore belum ada...!!!!");
        } else {
            wktSimpanSore = "";
            tampilSore();
            for (i = 0; i < tbSore.getRowCount(); i++) {
                tbSore.setValueAt(Boolean.FALSE, i, 0);
            }
        }
    }//GEN-LAST:event_MnHapusContengSoreActionPerformed

    private void MnContengSemuaMalamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnContengSemuaMalamActionPerformed
        if (tabMode4.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data evaluasi nyeri sift malam belum ada...!!!!");
        } else {
            tampilMalam();
            for (i = 0; i < tbMalam.getRowCount(); i++) {
                tbMalam.setValueAt(Boolean.TRUE, i, 0);
            }
        }
    }//GEN-LAST:event_MnContengSemuaMalamActionPerformed

    private void MnHapusContengMalamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusContengMalamActionPerformed
        if (tabMode4.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data evaluasi nyeri sift malam belum ada...!!!!");
        } else {
            wktSimpanMalam = "";
            tampilMalam();
            for (i = 0; i < tbMalam.getRowCount(); i++) {
                tbMalam.setValueAt(Boolean.FALSE, i, 0);
            }
        }
    }//GEN-LAST:event_MnHapusContengMalamActionPerformed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        WindowCetak.dispose();
        BtnCariActionPerformed(null);
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
        if (Sequel.cariInteger("select count(-1) from catatan_tindakan_keperawatan where no_rawat='" + TNoRW.getText() + "' and "
                + "tanggal='" + Valid.SetTgl(TtglCetak.getSelectedItem() + "") + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data catatan tindakan keperawatan pada tgl. " + TtglCetak.getSelectedItem() + " tidak ditemukan...!!!!");
            TtglCetak.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));

            if (cmbSiftCetak.getSelectedIndex() == 0) {
                if (Sequel.cariInteger("SELECT count(-1) from catatan_tindakan_keperawatan where "
                        + "no_rawat='" + TNoRW.getText() + "' and tanggal='" + Valid.SetTgl(TtglCetak.getSelectedItem() + "") + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Data catatan tindakan keperawatan tgl. " + TtglCetak.getSelectedItem() + " utk. semua sift tidak ditemukan...!!!!");
                } else {
                    dataEvaluasiSiftPagi();
                    dataEvaluasiSiftSore();
                    dataEvaluasiSiftMalam();
                    param.put("evaluasiPagi", evaluasiPG);
                    param.put("evaluasiSore", evaluasiSR);
                    param.put("evaluasiMalam", evaluasiML);
                    param.put("manajemen", "Manajemen Nyeri : \n" + Sequel.cariIsi("SELECT ifnull(manajemen_nyeri,'-') from manajemen_catatan_tindakan_keperawatan where "
                            + "no_rawat='" + TNoRW.getText() + "' and tanggal='" + Valid.SetTgl(TtglCetak.getSelectedItem() + "") + "'"));
                    
                    Valid.MyReport("rptCatatanTindakanPerawatAll.jasper", "report", "::[ Cetak Lembar Catatan Tindakan Keperawatan ]::",
                            "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, ct.sift, date_format(ct.tanggal,'%d-%m-%Y') tglctk, "
                            + "time_format(ct.jam_tindakan,'%H:%i') jam, ct.nm_tindakan, ct.waktu_simpan from catatan_tindakan_keperawatan ct "
                            + "inner join reg_periksa rp on rp.no_rawat=ct.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                            + "ct.no_rawat='" + TNoRW.getText() + "' and ct.tanggal='" + Valid.SetTgl(TtglCetak.getSelectedItem() + "") + "' ORDER BY ct.waktu_simpan", param);
                    BtnCloseIn4ActionPerformed(null);
                }
            } else {
                if (Sequel.cariInteger("SELECT count(-1) from catatan_tindakan_keperawatan where no_rawat='" + TNoRW.getText() + "' "
                        + "and tanggal='" + Valid.SetTgl(TtglCetak.getSelectedItem() + "") + "' and sift='" + cmbSiftCetak.getSelectedItem().toString() + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Data catatan tindakan keperawatan tgl. " + TtglCetak.getSelectedItem() + " sift " + cmbSiftCetak.getSelectedItem().toString() + " tidak ditemukan...!!!!");
                } else {
                    dataEvaluasiPerSift();
                    param.put("evaluasi", evaluasi + "\n\nManajemen Nyeri : \n" + Sequel.cariIsi("SELECT ifnull(manajemen_nyeri,'-') from manajemen_catatan_tindakan_keperawatan where "
                            + "no_rawat='" + TNoRW.getText() + "' and tanggal='" + Valid.SetTgl(TtglCetak.getSelectedItem() + "") + "'"));
                    Valid.MyReport("rptCatatanTindakanPerawat.jasper", "report", "::[ Cetak Lembar Catatan Tindakan Keperawatan ]::",
                            "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, ct.sift, date_format(ct.tanggal,'%d-%m-%Y') tglctk, "
                            + "time_format(ct.jam_tindakan,'%H:%i') jam, ct.nm_tindakan, ct.waktu_simpan from catatan_tindakan_keperawatan ct "
                            + "inner join reg_periksa rp on rp.no_rawat=ct.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                            + "ct.no_rawat='" + TNoRW.getText() + "' and ct.tanggal='" + Valid.SetTgl(TtglCetak.getSelectedItem() + "") + "' "
                            + "and ct.sift='" + cmbSiftCetak.getSelectedItem().toString() + "' ORDER BY ct.waktu_simpan", param);
                    BtnCloseIn4ActionPerformed(null);
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        }        
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void MnUrutanJamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutanJamActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data catatan tindakan keperawatan belum ada...!!!!");
        } else {
            tampilUrutanJam();
        }
    }//GEN-LAST:event_MnUrutanJamActionPerformed

    private void BtnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotepadActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("DlgCatatanTindakanKeperawatan");
        DlgNotepad form = new DlgNotepad(null, false);
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setData(akses.getkode());
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnNotepadActionPerformed

    private void BtnPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasteActionPerformed
        if (nipCopy.equals("") || TnmPetugasCopy.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu nama petugasnya...!!!!");
            BtnPetugasCopy.requestFocus();
        } else {
            try {
                for (i = 0; i < tbCatatan.getRowCount(); i++) {
                    if (tbCatatan.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.menyimpan("catatan_tindakan_keperawatan",
                                "'" + tbCatatan.getValueAt(i, 1).toString() + "',"
                                + "'" + cmbSiftCopy.getSelectedItem().toString() + "',"
                                + "'" + Valid.SetTgl(TtglCopy.getSelectedItem() + "") + "',"
                                + "'" + tbCatatan.getValueAt(i, 10).toString() + "',"
                                + "'" + tbCatatan.getValueAt(i, 7).toString() + "',"
                                + "'" + nipCopy + "',"
                                + "'" + Sequel.cariIsi("select now()") + "'", "Catatan Tindakan Keperawatan");

                        //jeda 1 detik
                        Thread.sleep(1000);
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }

            DTPCari1.setDate(Ttgl.getDate());
            tampil();
            emptTeks();
            MnHapusContengActionPerformed(null);
            BtnCloseIn5ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnPasteActionPerformed

    private void BtnCloseIn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn5ActionPerformed
        WindowCopyTindakan.dispose();
        TtglCopy.setDate(new Date());
        cmbSiftCopy.setSelectedIndex(0);
        cmbJamCopy.setSelectedIndex(0);
        cmbMntCopy.setSelectedIndex(0);
        cmbDtkCopy.setSelectedIndex(0);
        nipCopy = "";
        chkSayaCopy.setSelected(false);
        TnmPetugasCopy.setText("");
    }//GEN-LAST:event_BtnCloseIn5ActionPerformed

    private void cmbJamCopyMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamCopyMouseReleased
        AutoCompleteDecorator.decorate(cmbJamCopy);
    }//GEN-LAST:event_cmbJamCopyMouseReleased

    private void cmbMntCopyMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntCopyMouseReleased
        AutoCompleteDecorator.decorate(cmbMntCopy);
    }//GEN-LAST:event_cmbMntCopyMouseReleased

    private void cmbDtkCopyMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkCopyMouseReleased
        AutoCompleteDecorator.decorate(cmbDtkCopy);
    }//GEN-LAST:event_cmbDtkCopyMouseReleased

    private void BtnPetugasCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasCopyActionPerformed
        pilihan = 2;
        akses.setform("DlgCatatanTindakanKeperawatan");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasCopyActionPerformed

    private void chkSayaCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaCopyActionPerformed
        if (chkSayaCopy.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipCopy = "-";
                TnmPetugasCopy.setText("-");
            } else {
                nipCopy = akses.getkode();
                TnmPetugasCopy.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipCopy + "'"));
            }
        } else {
            nipCopy = "-";
            TnmPetugasCopy.setText("-");
        }
    }//GEN-LAST:event_chkSayaCopyActionPerformed

    private void MnCopyDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCopyDataActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasiennya...!!!!");
        } else {
            TtglCopy1.setDate(new Date());
            cmbJamCopy1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
            cmbMntCopy1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
            cmbDtkCopy1.setSelectedIndex(0);

            try {
                jamSekarangCopy1 = new SimpleDateFormat("HH:mm").parse(Sequel.cariIsi("SELECT TIME_FORMAT(NOW(),'%H:%i')"));
                jamSift1Copy1 = new SimpleDateFormat("HH:mm").parse("08:00");
                jamSift2Copy1 = new SimpleDateFormat("HH:mm").parse("14:00");
                jamSift3Copy1 = new SimpleDateFormat("HH:mm").parse("20:00");
            } catch (Exception e) {
                System.out.println("Tanggal error, cek lagi..!!");
            }

            if (jamSift1Copy1.before(jamSekarangCopy1)) {
                cmbSiftCopy1.setSelectedIndex(0);
            }

            if (jamSift2Copy1.before(jamSekarangCopy1)) {
                cmbSiftCopy1.setSelectedIndex(1);
            }

            if (jamSift3Copy1.before(jamSekarangCopy1)) {
                cmbSiftCopy1.setSelectedIndex(2);
            }
            
            WindowCopyData.setSize(704, internalFrame1.getHeight() - 40);
            WindowCopyData.setLocationRelativeTo(internalFrame1);
            WindowCopyData.setAlwaysOnTop(false);
            WindowCopyData.setVisible(true);
            
            TtglCTK.setDate(new Date());
            ChkTglCTK.setSelected(false);
            TtglCTK.setEnabled(false);
            nipCopy1 = "";
            TnmPetugasCopy1.setText("");
            TCari2.setText("");
            TCari2.requestFocus();
            cmbDicopy.setSelectedIndex(0);
            tampilPasienLain();            
        }
    }//GEN-LAST:event_MnCopyDataActionPerformed

    private void BtnPasteDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasteDataActionPerformed
        if (tbData.getSelectedRow() > -1) {
            if (nipCopy1.equals("") || TnmPetugasCopy1.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu nama petugasnya...!!!!");
                BtnPetugasCopy1.requestFocus();
            } else if (cmbDicopy.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu pilihan yang akan dicopy datanya...!!!!");
                cmbDicopy.requestFocus();
            } else {
                if (cmbDicopy.getSelectedIndex() == 1) {
                    //catatan_tindakan_keperawatan
                    if (Sequel.menyimpantf("catatan_tindakan_keperawatan", "'" + TNoRW.getText() + "',"
                            + "'" + cmbSiftCopy1.getSelectedItem().toString() + "',"
                            + "'" + Valid.SetTgl(TtglCopy1.getSelectedItem() + "") + "',"
                            + "'" + cmbJamCopy1.getSelectedItem() + ":" + cmbMntCopy1.getSelectedItem() + ":" + cmbDtkCopy1.getSelectedItem() + "',"
                            + "'" + Sequel.cariIsi("select nm_tindakan from catatan_tindakan_keperawatan where no_rawat='" + tbData.getValueAt(tbData.getSelectedRow(), 6).toString() + "'") + "',"
                            + "'" + nipCopy1 + "',"
                            + "'" + Sequel.cariIsi("select now()") + "'", "Catatan Tindakan Keperawatan") == true) {

                        //evaluasi_catatan_tindakan_keperawatan
                        if (cmbSiftCopy1.getSelectedIndex() == 0) {
                            if (Sequel.cariInteger("select count(-1) from evaluasi_catatan_tindakan_keperawatan where no_rawat='" + TNoRW.getText() + "' "
                                    + "and sift='" + cmbSiftCopy1.getSelectedItem().toString() + "' and evaluasi_nyeri='" + tbData.getValueAt(tbData.getSelectedRow(), 7).toString() + "' "
                                    + "and tanggal='" + Valid.SetTgl(TtglCopy1.getSelectedItem() + "") + "' and ket_pagi<>''") > 0) {
                                System.out.println("Data evaluasi nyeri " + tbData.getValueAt(tbData.getSelectedRow(), 7).toString()
                                        + " sift " + cmbSiftCopy1.getSelectedItem() + " tgl. " + TtglCopy1.getSelectedItem() + " sudah tersimpan,..!!");
                            } else {
                                if (tbData.getValueAt(tbData.getSelectedRow(), 7).toString().equals("")) {
                                    System.out.println("Data evaluasi nyeri waktu pagi tidak ditemukan..!!");
                                } else {
                                    Sequel.menyimpanPesanGagalnyaDiTerminal("evaluasi_catatan_tindakan_keperawatan", "?,?,?,?,?,?,?,?", "Evaluasi Nyeri Pagi", 8, new String[]{
                                        TNoRW.getText(), cmbSiftCopy1.getSelectedItem().toString(), Valid.SetTgl(TtglCopy1.getSelectedItem() + ""),
                                        tbData.getValueAt(tbData.getSelectedRow(), 7).toString(), tbData.getValueAt(tbData.getSelectedRow(), 8).toString(),
                                        tbData.getValueAt(tbData.getSelectedRow(), 9).toString(), tbData.getValueAt(tbData.getSelectedRow(), 10).toString(),
                                        Sequel.cariIsi("select now()")
                                    });
                                }
                            }
                        } else if (cmbSiftCopy1.getSelectedIndex() == 1) {
                            if (Sequel.cariInteger("select count(-1) from evaluasi_catatan_tindakan_keperawatan where no_rawat='" + TNoRW.getText() + "' "
                                    + "and sift='" + cmbSiftCopy1.getSelectedItem().toString() + "' and evaluasi_nyeri='" + tbData.getValueAt(tbData.getSelectedRow(), 7).toString() + "' "
                                    + "and tanggal='" + Valid.SetTgl(TtglCopy1.getSelectedItem() + "") + "' and ket_sore<>''") > 0) {
                                System.out.println("Data evaluasi nyeri " + tbData.getValueAt(tbData.getSelectedRow(), 7).toString()
                                        + " sift " + cmbSiftCopy1.getSelectedItem() + " tgl. " + TtglCopy1.getSelectedItem() + " sudah tersimpan,..!!");
                            } else {
                                if (tbData.getValueAt(tbData.getSelectedRow(), 7).toString().equals("")) {
                                    System.out.println("Data evaluasi nyeri waktu sore tidak ditemukan..!!");
                                } else {
                                    Sequel.menyimpanPesanGagalnyaDiTerminal("evaluasi_catatan_tindakan_keperawatan", "?,?,?,?,?,?,?,?", "Evaluasi Nyeri Sore", 8, new String[]{
                                        TNoRW.getText(), cmbSiftCopy1.getSelectedItem().toString(), Valid.SetTgl(TtglCopy1.getSelectedItem() + ""),
                                        tbData.getValueAt(tbData.getSelectedRow(), 7).toString(), tbData.getValueAt(tbData.getSelectedRow(), 8).toString(),
                                        tbData.getValueAt(tbData.getSelectedRow(), 9).toString(), tbData.getValueAt(tbData.getSelectedRow(), 10).toString(),
                                        Sequel.cariIsi("select now()")
                                    });
                                }
                            }
                        } else if (cmbSiftCopy1.getSelectedIndex() == 2) {
                            if (Sequel.cariInteger("select count(-1) from evaluasi_catatan_tindakan_keperawatan where no_rawat='" + TNoRW.getText() + "' "
                                    + "and sift='" + cmbSiftCopy1.getSelectedItem().toString() + "' and evaluasi_nyeri='" + tbData.getValueAt(tbData.getSelectedRow(), 7).toString() + "' "
                                    + "and tanggal='" + Valid.SetTgl(TtglCopy1.getSelectedItem() + "") + "' and ket_malam<>''") > 0) {
                                System.out.println("Data evaluasi nyeri " + tbData.getValueAt(tbData.getSelectedRow(), 7).toString()
                                        + " sift " + cmbSiftCopy1.getSelectedItem() + " tgl. " + TtglCopy1.getSelectedItem() + " sudah tersimpan,..!!");
                            } else {
                                if (tbData.getValueAt(tbData.getSelectedRow(), 7).toString().equals("")) {
                                    System.out.println("Data evaluasi nyeri waktu malam tidak ditemukan..!!");
                                } else {
                                    Sequel.menyimpanPesanGagalnyaDiTerminal("evaluasi_catatan_tindakan_keperawatan", "?,?,?,?,?,?,?,?", "Evaluasi Nyeri Malam", 8, new String[]{
                                        TNoRW.getText(), cmbSiftCopy1.getSelectedItem().toString(), Valid.SetTgl(TtglCopy1.getSelectedItem() + ""),
                                        tbData.getValueAt(tbData.getSelectedRow(), 7).toString(), tbData.getValueAt(tbData.getSelectedRow(), 8).toString(),
                                        tbData.getValueAt(tbData.getSelectedRow(), 9).toString(), tbData.getValueAt(tbData.getSelectedRow(), 10).toString(),
                                        Sequel.cariIsi("select now()")
                                    });
                                }
                            }
                        }

                        //manajemen_catatan_tindakan_keperawatan
                        if (tbData.getValueAt(tbData.getSelectedRow(), 11).toString().equals("")) {
                            System.out.println("Tidak ada data manajemen nyeri yang bisa disimpan..!!");
                        } else {
                            Sequel.menyimpan("manajemen_catatan_tindakan_keperawatan", "'" + TNoRW.getText() + "',"
                                    + "'" + Valid.SetTgl(TtglCopy1.getSelectedItem() + "") + "',"
                                    + "'" + tbData.getValueAt(tbData.getSelectedRow(), 11).toString() + "'", "Manajemen Nyeri");
                        }
                        
                        BtnCloseIn6ActionPerformed(null);
                        BtnCariActionPerformed(null);
                    } else {
                        BtnCloseIn6ActionPerformed(null);
                        BtnCariActionPerformed(null);
                    }
                    
                } else if (cmbDicopy.getSelectedIndex() == 2) {
                    JOptionPane.showMessageDialog(null, "Untuk pilihan " + cmbDicopy.getSelectedItem() + " masih dalam proses dikerjakan...!!!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel...!!!!");
            tbData.requestFocus();
        }
    }//GEN-LAST:event_BtnPasteDataActionPerformed

    private void BtnCloseIn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn6ActionPerformed
        WindowCopyData.dispose();
        TCari2.setText("");
        cmbDicopy.setSelectedIndex(0);
        
        wktSimpan = "";
        tampil();
        for (i = 0; i < tbCatatan.getRowCount(); i++) {
            tbCatatan.setValueAt(Boolean.FALSE, i, 0);
        }
    }//GEN-LAST:event_BtnCloseIn6ActionPerformed

    private void tbDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDataMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDataMouseClicked

    private void tbDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDataKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDataKeyPressed

    private void tbDataKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDataKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDataKeyReleased

    private void TCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari2ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari2KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampilPasienLain();
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void ChkTglCTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkTglCTKActionPerformed
        TtglCTK.setDate(new Date());
        if (ChkTglCTK.isSelected() == true) {            
            TtglCTK.setEnabled(true);
            TtglCTK.requestFocus();
        } else {
            TtglCTK.setEnabled(false);
        }
    }//GEN-LAST:event_ChkTglCTKActionPerformed

    private void cmbJamCopy1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamCopy1MouseReleased
        AutoCompleteDecorator.decorate(cmbJamCopy1);
    }//GEN-LAST:event_cmbJamCopy1MouseReleased

    private void cmbMntCopy1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntCopy1MouseReleased
        AutoCompleteDecorator.decorate(cmbMntCopy1);
    }//GEN-LAST:event_cmbMntCopy1MouseReleased

    private void cmbDtkCopy1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkCopy1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtkCopy1);
    }//GEN-LAST:event_cmbDtkCopy1MouseReleased

    private void BtnPetugasCopy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasCopy1ActionPerformed
        pilihan = 3;
        akses.setform("DlgCatatanTindakanKeperawatan");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasCopy1ActionPerformed

    private void chkSayaCopy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaCopy1ActionPerformed
        if (chkSayaCopy1.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipCopy1 = "-";
                TnmPetugasCopy1.setText("-");
            } else {
                nipCopy1 = akses.getkode();
                TnmPetugasCopy1.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipCopy1 + "'"));
            }
        } else {
            nipCopy1 = "-";
            TnmPetugasCopy1.setText("-");
        }
    }//GEN-LAST:event_chkSayaCopy1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCatatanTindakanKeperawatan dialog = new DlgCatatanTindakanKeperawatan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn2;
    private widget.Button BtnCloseIn3;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnCloseIn5;
    private widget.Button BtnCloseIn6;
    private widget.Button BtnEdit;
    private widget.Button BtnEdit1;
    private widget.Button BtnEdit2;
    private widget.Button BtnEvaluasi;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnManajemen;
    private widget.Button BtnNotepad;
    private widget.Button BtnPaste;
    private widget.Button BtnPasteData;
    private widget.Button BtnPetugas;
    private widget.Button BtnPetugasCopy;
    private widget.Button BtnPetugasCopy1;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Button BtnSimpan2;
    private widget.Button BtnTindakan;
    public widget.CekBox ChkAccor;
    private widget.CekBox ChkTglCTK;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormMenu;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnContengSemua;
    private javax.swing.JMenuItem MnContengSemuaMalam;
    private javax.swing.JMenuItem MnContengSemuaPagi;
    private javax.swing.JMenuItem MnContengSemuaSore;
    private javax.swing.JMenuItem MnCopyData;
    private javax.swing.JMenuItem MnCopyMalam;
    private javax.swing.JMenuItem MnCopyManajemen;
    private javax.swing.JMenuItem MnCopyPagi;
    private javax.swing.JMenuItem MnCopySore;
    private javax.swing.JMenuItem MnCopyTindakan;
    private javax.swing.JMenuItem MnGantiMalam;
    private javax.swing.JMenuItem MnGantiManajemen;
    private javax.swing.JMenuItem MnGantiPagi;
    private javax.swing.JMenuItem MnGantiSore;
    private javax.swing.JMenuItem MnHapus;
    private javax.swing.JMenuItem MnHapusConteng;
    private javax.swing.JMenuItem MnHapusContengMalam;
    private javax.swing.JMenuItem MnHapusContengPagi;
    private javax.swing.JMenuItem MnHapusContengSore;
    private javax.swing.JMenuItem MnHapusMalam;
    private javax.swing.JMenuItem MnHapusManajemen;
    private javax.swing.JMenuItem MnHapusPagi;
    private javax.swing.JMenuItem MnHapusSore;
    private javax.swing.JMenuItem MnUrutanJam;
    private widget.PanelBiasa PanelAccor;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TCari2;
    private widget.TextArea TManajemen;
    private widget.TextBox TNmPasien;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private widget.TextArea Thasil;
    private widget.TextArea Tinstruksi;
    private widget.TextBox Tmalam;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TnmPetugasCopy;
    private widget.TextBox TnmPetugasCopy1;
    private widget.TextBox TnmTindakan;
    private widget.TextBox Tpagi;
    private widget.TextBox Tsore;
    private widget.Tanggal Ttgl;
    private widget.Tanggal TtglCTK;
    private widget.Tanggal TtglCetak;
    private widget.Tanggal TtglCopy;
    private widget.Tanggal TtglCopy1;
    private javax.swing.JDialog WindowCetak;
    private javax.swing.JDialog WindowCopyData;
    private javax.swing.JDialog WindowCopyTindakan;
    private javax.swing.JDialog WindowEvaluasi;
    private javax.swing.JDialog WindowManajemen;
    private javax.swing.JDialog WindowTindakan;
    private widget.CekBox chkSaya;
    private widget.CekBox chkSayaCopy;
    private widget.CekBox chkSayaCopy1;
    private widget.ComboBox cmbDicopy;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtkCopy;
    private widget.ComboBox cmbDtkCopy1;
    private widget.ComboBox cmbEvaluasi;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJamCopy;
    private widget.ComboBox cmbJamCopy1;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMntCopy;
    private widget.ComboBox cmbMntCopy1;
    private widget.ComboBox cmbSift;
    private widget.ComboBox cmbSiftCetak;
    private widget.ComboBox cmbSiftCopy;
    private widget.ComboBox cmbSiftCopy1;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame11;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
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
    private widget.Label jLabel21;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel48;
    private widget.Label jLabel5;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenuMalam;
    private javax.swing.JPopupMenu jPopupMenuManajemen;
    private javax.swing.JPopupMenu jPopupMenuPagi;
    private javax.swing.JPopupMenu jPopupMenuSore;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi10;
    private widget.panelisi panelisi11;
    private widget.panelisi panelisi12;
    private widget.panelisi panelisi14;
    private widget.panelisi panelisi15;
    private widget.panelisi panelisi16;
    private widget.panelisi panelisi5;
    private widget.panelisi panelisi6;
    private widget.panelisi panelisi7;
    private widget.panelisi panelisi8;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.Table tbCPPT;
    private widget.Table tbCatatan;
    private widget.Table tbData;
    private widget.Table tbMalam;
    private widget.Table tbManajemen;
    private widget.Table tbPagi;
    private widget.Table tbSore;
    private widget.Table tbTindakan;
    // End of variables declaration//GEN-END:variables

    private void tampilUrutanJam() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select c.no_rawat, p.no_rkm_medis, p.nm_pasien, date_format(c.tanggal,'%d-%m-%Y') tgl, "
                    + "c.sift, if(c.jam_tindakan='00:00:00','',time_format(c.jam_tindakan,'%H:%i')) jamTindkaan, "
                    + "c.nm_tindakan, pg.nama ptgs, c.tanggal, c.jam_tindakan, c.nip_petugas, c.waktu_simpan from catatan_tindakan_keperawatan c "
                    + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg on pg.nik=c.nip_petugas where "
                    + "c.tanggal between ? and ? and c.no_rawat like ? or "
                    + "c.tanggal between ? and ? and p.no_rkm_medis like ? or "
                    + "c.tanggal between ? and ? and p.nm_pasien like ? or "
                    + "c.tanggal between ? and ? and c.sift like ? or "
                    + "c.tanggal between ? and ? and c.nm_tindakan like ? or "
                    + "c.tanggal between ? and ? and pg.nama like ? order by c.tanggal, c.jam_tindakan");
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
                    tabMode.addRow(new Object[]{
                        false,
                        rs.getString("no_rawat"), 
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgl"),
                        rs.getString("sift"),
                        rs.getString("jamTindkaan"),
                        rs.getString("nm_tindakan"),
                        rs.getString("ptgs"),
                        rs.getString("tanggal"),
                        rs.getString("jam_tindakan"),
                        rs.getString("nip_petugas"),
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
    
    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select c.no_rawat, p.no_rkm_medis, p.nm_pasien, date_format(c.tanggal,'%d-%m-%Y') tgl, "
                    + "c.sift, if(c.jam_tindakan='00:00:00','',time_format(c.jam_tindakan,'%H:%i')) jamTindkaan, "
                    + "c.nm_tindakan, pg.nama ptgs, c.tanggal, c.jam_tindakan, c.nip_petugas, c.waktu_simpan from catatan_tindakan_keperawatan c "
                    + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg on pg.nik=c.nip_petugas where "
                    + "c.tanggal between ? and ? and c.no_rawat like ? or "
                    + "c.tanggal between ? and ? and p.no_rkm_medis like ? or "
                    + "c.tanggal between ? and ? and p.nm_pasien like ? or "
                    + "c.tanggal between ? and ? and c.sift like ? or "
                    + "c.tanggal between ? and ? and c.nm_tindakan like ? or "
                    + "c.tanggal between ? and ? and pg.nama like ? order by c.sift, c.waktu_simpan");
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
                    tabMode.addRow(new Object[]{
                        false,
                        rs.getString("no_rawat"), 
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgl"),
                        rs.getString("sift"),
                        rs.getString("jamTindkaan"),
                        rs.getString("nm_tindakan"),
                        rs.getString("ptgs"),
                        rs.getString("tanggal"),
                        rs.getString("jam_tindakan"),
                        rs.getString("nip_petugas"),
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
        TnmTindakan.setText("");
        TManajemen.setText("");
        wktSimpan = "";
        chkSaya.setSelected(false);
        Valid.SetTgl(DTPCari2, Sequel.cariIsi("select DATE_ADD(now(),interval 30 day)"));
        Ttgl.requestFocus();
        KetentuanSift();
    }

    private void getData() {
        nip = "";
        wktSimpan = "";

        if (tbCatatan.getSelectedRow() != -1) {
            TNoRW.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 1).toString());
            TNoRM.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 2).toString());
            TNmPasien.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 3).toString());
            Valid.SetTgl(Ttgl, tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 9).toString());
            cmbSift.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 5).toString());
            cmbJam.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 10).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 10).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 10).toString().substring(6, 8));
            TnmTindakan.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 7).toString());
            nip = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 11).toString();
            TnmPetugas.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 8).toString());
            wktSimpan = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 12).toString();
        }
    }
    
    private void getDataPagi() {
        wktSimpanPagi = "";
        if (tbPagi.getSelectedRow() != -1) {
            cmbEvaluasi.setSelectedItem(tbPagi.getValueAt(tbPagi.getSelectedRow(), 2).toString());
            Tpagi.setText(tbPagi.getValueAt(tbPagi.getSelectedRow(), 3).toString());
            Valid.SetTgl(Ttgl, tbPagi.getValueAt(tbPagi.getSelectedRow(), 4).toString());
            wktSimpanPagi = tbPagi.getValueAt(tbPagi.getSelectedRow(), 5).toString();
            cmbSift.setSelectedItem(tbPagi.getValueAt(tbPagi.getSelectedRow(), 6).toString());
  
            if (cmbSift.getSelectedIndex() == 0) {
                Tpagi.setEnabled(true);
                Tsore.setEnabled(false);
                Tmalam.setEnabled(false);
                Tpagi.requestFocus();
            } else if (cmbSift.getSelectedIndex() == 1) {
                Tpagi.setEnabled(false);
                Tsore.setEnabled(true);
                Tmalam.setEnabled(false);
                Tsore.requestFocus();
            } else if (cmbSift.getSelectedIndex() == 2) {
                Tpagi.setEnabled(false);
                Tsore.setEnabled(false);
                Tmalam.setEnabled(true);
                Tmalam.requestFocus();
            }
        }
    }
    
    private void getDataSore() {
        wktSimpanSore = "";
        if (tbSore.getSelectedRow() != -1) {
            cmbEvaluasi.setSelectedItem(tbSore.getValueAt(tbSore.getSelectedRow(), 2).toString());
            Tsore.setText(tbSore.getValueAt(tbSore.getSelectedRow(), 3).toString());
            Valid.SetTgl(Ttgl, tbSore.getValueAt(tbSore.getSelectedRow(), 4).toString());
            wktSimpanSore = tbSore.getValueAt(tbSore.getSelectedRow(), 5).toString();
            cmbSift.setSelectedItem(tbSore.getValueAt(tbSore.getSelectedRow(), 6).toString());
            
            if (cmbSift.getSelectedIndex() == 0) {
                Tpagi.setEnabled(true);
                Tsore.setEnabled(false);
                Tmalam.setEnabled(false);
                Tpagi.requestFocus();
            } else if (cmbSift.getSelectedIndex() == 1) {
                Tpagi.setEnabled(false);
                Tsore.setEnabled(true);
                Tmalam.setEnabled(false);
                Tsore.requestFocus();
            } else if (cmbSift.getSelectedIndex() == 2) {
                Tpagi.setEnabled(false);
                Tsore.setEnabled(false);
                Tmalam.setEnabled(true);
                Tmalam.requestFocus();
            }
        }
    }
    
    private void getDataMalam() {
        wktSimpanMalam = "";
        if (tbMalam.getSelectedRow() != -1) {
            cmbEvaluasi.setSelectedItem(tbMalam.getValueAt(tbMalam.getSelectedRow(), 2).toString());
            Tmalam.setText(tbMalam.getValueAt(tbMalam.getSelectedRow(), 3).toString());
            Valid.SetTgl(Ttgl, tbMalam.getValueAt(tbMalam.getSelectedRow(), 4).toString());
            wktSimpanMalam = tbMalam.getValueAt(tbMalam.getSelectedRow(), 5).toString();
            cmbSift.setSelectedItem(tbMalam.getValueAt(tbMalam.getSelectedRow(), 6).toString());
            
            if (cmbSift.getSelectedIndex() == 0) {
                Tpagi.setEnabled(true);
                Tsore.setEnabled(false);
                Tmalam.setEnabled(false);
                Tpagi.requestFocus();
            } else if (cmbSift.getSelectedIndex() == 1) {
                Tpagi.setEnabled(false);
                Tsore.setEnabled(true);
                Tmalam.setEnabled(false);
                Tsore.requestFocus();
            } else if (cmbSift.getSelectedIndex() == 2) {
                Tpagi.setEnabled(false);
                Tsore.setEnabled(false);
                Tmalam.setEnabled(true);
                Tmalam.requestFocus();
            }
        }
    }
    
    private void getDataManajemen() {
        if (tbManajemen.getSelectedRow() != -1) {
            TManajemen.setText(tbManajemen.getValueAt(tbManajemen.getSelectedRow(), 0).toString());
            Valid.SetTgl(Ttgl, tbManajemen.getValueAt(tbManajemen.getSelectedRow(), 3).toString());            
        }
    }
    
    public void isCek(){
       BtnSimpan.setEnabled(akses.getcppt());
       BtnHapus.setEnabled(akses.getcppt());
       BtnEdit.setEnabled(akses.getcppt());
       MnHapus.setEnabled(akses.getadmin());
       BtnEvaluasi.setEnabled(akses.getcppt());
       MnHapusPagi.setEnabled(akses.getcppt());
       MnHapusSore.setEnabled(akses.getcppt());
       MnHapusMalam.setEnabled(akses.getcppt());
       MnGantiPagi.setEnabled(akses.getcppt());
       MnGantiSore.setEnabled(akses.getcppt());
       MnGantiMalam.setEnabled(akses.getcppt());
    }
    
    private void tampilTindakan() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT nm_tindakan, kode FROM master_catatan_tindakan_keperawatan where "
                    + "nm_tindakan like '%" + TCari1.getText() + "%' group by nm_tindakan order by nm_tindakan");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{
                        rs1.getString("nm_tindakan"),
                        rs1.getString("kode")
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
    
    public void setData(String norw, String norm, String nmPasien, String nmunit) {
        TNoRW.setText(norw);
        TNoRM.setText(norm);
        TNmPasien.setText(nmPasien);
        Valid.SetTgl(DTPCari2, Sequel.cariIsi("select DATE_ADD(now(),interval 30 day)"));
        nmgedung = Sequel.cariIsi("select nm_gedung from bangsal where nm_bangsal='" + nmunit + "'");
        
        if (Sequel.cariInteger("select count(-1) from catatan_tindakan_keperawatan where no_rawat='" + norw + "'") > 0) {
            Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tanggal from catatan_tindakan_keperawatan where "
                    + "no_rawat='" + norw + "' order by tanggal desc limit 1"));
        } else {
            DTPCari1.setDate(new Date());
        }
        
        TCari.setText(norw);
        tampilPagi();
        tampilSore();
        tampilMalam();
        tampilManajemen();
        KetentuanSift();
        
        if (akses.getadmin() == true) {
            nip = "-";
            TnmPetugas.setText("-");
        } else {
            nip = akses.getkode();
            TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nip + "'"));
        }
    }
    
    public void isMenu() {
        if (ChkAccor.isSelected() == true) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(900, HEIGHT));
            FormMenu.setVisible(true);
            ChkAccor.setVisible(true);
            Thasil.setText("");
            Tinstruksi.setText("");
            tampilCppt();
        } else if (ChkAccor.isSelected() == false) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(22, HEIGHT));
            FormMenu.setVisible(false);
            ChkAccor.setVisible(true);
        }
    }
    
    private void tampilCppt() {
        Valid.tabelKosong(tabModeCppt);
        try {
            pscppt = koneksi.prepareStatement("SELECT c.verifikasi, DATE_FORMAT(c.tgl_cppt,'%d-%m-%Y') tgl, if(c.cek_jam='ya',TIME_FORMAT(c.jam_cppt,'%H:%i'),'-') jam, "
                    + "c.jenis_bagian, pg1.nama nmdpjp, c.jenis_ppa, pg2.nama nmppa, c.cppt_shift, c.hasil_pemeriksaan, "
                    + "c.instruksi_nakes, c.waktu_simpan, c.no_rawat, c.tgl_cppt, c.jam_cppt from cppt c "
                    + "inner join pegawai pg1 on pg1.nik=c.nip_konsulen "
                    + "inner join pegawai pg2 on pg2.nik=c.nip_ppa where "
                    + "c.flag_hapus='tidak' and c.status='ranap' and c.no_rawat='" + TNoRW.getText() + "' order by c.tgl_cppt, c.jam_cppt");
            try {
                rscppt = pscppt.executeQuery();                
                while (rscppt.next()) {
                    tabModeCppt.addRow(new String[]{
                        rscppt.getString("tgl"),
                        rscppt.getString("jam"),
                        rscppt.getString("jenis_bagian"),
                        rscppt.getString("nmdpjp"),
                        rscppt.getString("jenis_ppa"),
                        rscppt.getString("nmppa"),
                        rscppt.getString("cppt_shift"),
                        rscppt.getString("hasil_pemeriksaan"),
                        rscppt.getString("instruksi_nakes"),
                        rscppt.getString("no_rawat"),
                        rscppt.getString("tgl_cppt"),
                        rscppt.getString("jam_cppt")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscppt != null) {
                    rscppt.close();
                }
                if (pscppt != null) {
                    pscppt.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataCppt() {
        dataKonfirmasi = "";
        
        if (tbCPPT.getSelectedRow() != -1) {
            Thasil.setText("Tgl. CPPT : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 0).toString() + ", Jam : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 1).toString() + " WITA\n\n"
                    + "" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 7).toString());
            
            //konfirmasi terapi
            if (Sequel.cariInteger("select count(-1) from cppt_konfirmasi_terapi where "
                    + "no_rawat='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 9).toString() + "' "
                    + "and tgl_cppt='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 10).toString() + "' "
                    + "and cppt_shift='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 6).toString() + "' "
                    + "and jam_cppt='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 11).toString() + "'") > 0) {

                tampilKonfirmasi(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 9).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 10).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 6).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 11).toString());
                
                if (tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString().equals("-")) {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "KONFIRMASI TERAPI VIA TELP. :\n\n" + dataKonfirmasi);
                } else {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "(" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString() + ")\n\n"
                            + "KONFIRMASI TERAPI VIA TELP. :\n\n" + dataKonfirmasi);
                }
            } else {
                if (tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString().equals("-")) {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString());
                } else {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "(" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString() + ")");
                }
            }
        }
    }
    
    private void tampilKonfirmasi(String norwt, String tglcppt, String sift, String jamcppt) {
        try {
            ps2 = koneksi.prepareStatement("select pg1.nama ptgs, date_format(ck.tgl_lapor,'%d-%m-%Y') tgllapor, time_format(ck.jam_lapor,'%H:%i') jamlapor, "
                    + "pg2.nama dpjp, date_format(ck.tgl_verifikasi,'%d-%m-%Y') tglverif, time_format(ck.jam_verifikasi,'%H:%i') jamverif from cppt_konfirmasi_terapi ck "
                    + "inner join pegawai pg1 on pg1.nik=ck.nip_petugas_konfir inner join pegawai pg2 on pg2.nik=ck.nip_dpjp_konfir where "
                    + "ck.no_rawat = '" + norwt + "' and ck.tgl_cppt='" + tglcppt + "' and ck.cppt_shift='" + sift + "' "
                    + "and ck.jam_cppt='" + jamcppt + "' order by ck.waktu_simpan");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    if (dataKonfirmasi.equals("")) {
                        dataKonfirmasi = "Tgl. Lapor : " + rs2.getString("tgllapor") + ", Jam : " + rs2.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs2.getString("tglverif") + ", Jam : " + rs2.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs2.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs2.getString("dpjp");
                    } else {
                        dataKonfirmasi = dataKonfirmasi + "\n\nTgl. Lapor : " + rs2.getString("tgllapor") + ", Jam : " + rs2.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs2.getString("tglverif") + ", Jam : " + rs2.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs2.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs2.getString("dpjp");
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
    
    private void tampilPagi() {
        Valid.tabelKosong(tabMode2);
        try {
            psPagi = koneksi.prepareStatement("SELECT *, date_format(tanggal,'%d-%m-%Y') tgl from evaluasi_catatan_tindakan_keperawatan where "
                    + "no_rawat='" + TNoRW.getText() + "' and tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' "
                    + "and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and sift='Pagi' and ket_pagi<>'' order by tanggal, evaluasi_nyeri");
            try {
                rsPagi = psPagi.executeQuery();                
                while (rsPagi.next()) {
                    tabMode2.addRow(new Object[]{
                        false,
                        rsPagi.getString("tgl"),
                        rsPagi.getString("evaluasi_nyeri"),
                        rsPagi.getString("ket_pagi"),
                        rsPagi.getString("tanggal"),
                        rsPagi.getString("waktu_simpan"),
                        rsPagi.getString("sift"),
                        rsPagi.getString("no_rawat")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsPagi != null) {
                    rsPagi.close();
                }
                if (psPagi != null) {
                    psPagi.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilSore() {
        Valid.tabelKosong(tabMode3);
        try {
            psSore = koneksi.prepareStatement("SELECT *, date_format(tanggal,'%d-%m-%Y') tgl from evaluasi_catatan_tindakan_keperawatan where "
                    + "no_rawat='" + TNoRW.getText() + "' and tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' "
                    + "and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and sift='Sore' and ket_sore<>'' order by tanggal, evaluasi_nyeri");
            try {
                rsSore = psSore.executeQuery();                
                while (rsSore.next()) {
                    tabMode3.addRow(new Object[]{
                        false,
                        rsSore.getString("tgl"),
                        rsSore.getString("evaluasi_nyeri"),
                        rsSore.getString("ket_sore"),
                        rsSore.getString("tanggal"),
                        rsSore.getString("waktu_simpan"),
                        rsSore.getString("sift"),
                        rsSore.getString("no_rawat")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsSore != null) {
                    rsSore.close();
                }
                if (psSore != null) {
                    psSore.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilMalam() {
        Valid.tabelKosong(tabMode4);
        try {
            psMalam = koneksi.prepareStatement("SELECT *, date_format(tanggal,'%d-%m-%Y') tgl from evaluasi_catatan_tindakan_keperawatan where "
                    + "no_rawat='" + TNoRW.getText() + "' and tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' "
                    + "and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and sift='Malam' and ket_malam<>'' order by tanggal, evaluasi_nyeri");
            try {
                rsMalam = psMalam.executeQuery();                
                while (rsMalam.next()) {
                    tabMode4.addRow(new Object[]{
                        false,
                        rsMalam.getString("tgl"),
                        rsMalam.getString("evaluasi_nyeri"),
                        rsMalam.getString("ket_malam"),
                        rsMalam.getString("tanggal"),
                        rsMalam.getString("waktu_simpan"),
                        rsMalam.getString("sift"),
                        rsMalam.getString("no_rawat")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsMalam != null) {
                    rsMalam.close();
                }
                if (psMalam != null) {
                    psMalam.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilManajemen() {
        Valid.tabelKosong(tabMode5);
        try {
            ps3 = koneksi.prepareStatement("SELECT *, date_format(tanggal,'%d-%m-%Y') tgl from manajemen_catatan_tindakan_keperawatan where "
                    + "no_rawat='" + TNoRW.getText() + "' and tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' "
                    + "and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' order by tanggal");
            try {
                rs3 = ps3.executeQuery();                
                while (rs3.next()) {
                    tabMode5.addRow(new String[]{
                        rs3.getString("manajemen_nyeri"),
                        rs3.getString("no_rawat"),
                        rs3.getString("tgl"),
                        rs3.getString("tanggal")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs3 != null) {
                    rs3.close();
                }
                if (ps3 != null) {
                    ps3.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void emptTeksEvaluasi() {
        Tpagi.setText("");
        Tsore.setText("");
        Tmalam.setText("");
        wktSimpanPagi = "";
        wktSimpanSore = "";
        wktSimpanMalam = "";

        if (cmbSift.getSelectedIndex() == 0) {
            Tpagi.setEnabled(true);
            Tsore.setEnabled(false);
            Tmalam.setEnabled(false);
            Tpagi.requestFocus();
        } else if (cmbSift.getSelectedIndex() == 1) {
            Tpagi.setEnabled(false);
            Tsore.setEnabled(true);
            Tmalam.setEnabled(false);
            Tsore.requestFocus();
        } else if (cmbSift.getSelectedIndex() == 2) {
            Tpagi.setEnabled(false);
            Tsore.setEnabled(false);
            Tmalam.setEnabled(true);
            Tmalam.requestFocus();
        }
    }
    
    private void KetentuanSift() {
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);

        try {
            jamSekarang = new SimpleDateFormat("HH:mm").parse(Sequel.cariIsi("SELECT TIME_FORMAT(NOW(),'%H:%i')"));
            jamSift1 = new SimpleDateFormat("HH:mm").parse("08:00");
            jamSift2 = new SimpleDateFormat("HH:mm").parse("14:00");
            jamSift3 = new SimpleDateFormat("HH:mm").parse("20:00");
        } catch (Exception e) {
            System.out.println("Tanggal error, cek lagi..!!");
        }

        if (jamSift1.before(jamSekarang)) {
            cmbSift.setSelectedIndex(0);
        }

        if (jamSift2.before(jamSekarang)) {
            cmbSift.setSelectedIndex(1);
        }

        if (jamSift3.before(jamSekarang)) {
            cmbSift.setSelectedIndex(2);
        }
    }
    
    private void dataEvaluasiPerSift() {
        evaluasi = "";
        try {
            if (cmbSiftCetak.getSelectedIndex() == 1) {
                ps4 = koneksi.prepareStatement("SELECT * from evaluasi_catatan_tindakan_keperawatan where "
                        + "no_rawat='" + TNoRW.getText() + "' and tanggal='" + Valid.SetTgl(TtglCetak.getSelectedItem() + "") + "' "
                        + "and sift='" + cmbSiftCetak.getSelectedItem().toString() + "' and ket_pagi<>'' ORDER BY evaluasi_nyeri");
            } else if (cmbSiftCetak.getSelectedIndex() == 2) {
                ps4 = koneksi.prepareStatement("SELECT * from evaluasi_catatan_tindakan_keperawatan where "
                        + "no_rawat='" + TNoRW.getText() + "' and tanggal='" + Valid.SetTgl(TtglCetak.getSelectedItem() + "") + "' "
                        + "and sift='" + cmbSiftCetak.getSelectedItem().toString() + "' and ket_sore<>'' ORDER BY evaluasi_nyeri");
            } else if (cmbSiftCetak.getSelectedIndex() == 3) {
                ps4 = koneksi.prepareStatement("SELECT * from evaluasi_catatan_tindakan_keperawatan where "
                        + "no_rawat='" + TNoRW.getText() + "' and tanggal='" + Valid.SetTgl(TtglCetak.getSelectedItem() + "") + "' "
                        + "and sift='" + cmbSiftCetak.getSelectedItem().toString() + "' and ket_malam<>'' ORDER BY evaluasi_nyeri");
            } 

            try {
                rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    if (cmbSiftCetak.getSelectedIndex() == 1) {
                        if (evaluasi.equals("")) {
                            evaluasi = rs4.getString("evaluasi_nyeri") + " : " + rs4.getString("ket_pagi");
                        } else {
                            evaluasi = evaluasi + "\n" + rs4.getString("evaluasi_nyeri") + " : " + rs4.getString("ket_pagi");
                        }
                    } else if (cmbSiftCetak.getSelectedIndex() == 2) {
                        if (evaluasi.equals("")) {
                            evaluasi = rs4.getString("evaluasi_nyeri") + " : " + rs4.getString("ket_sore");
                        } else {
                            evaluasi = evaluasi + "\n" + rs4.getString("evaluasi_nyeri") + " : " + rs4.getString("ket_sore");
                        }
                    } else if (cmbSiftCetak.getSelectedIndex() == 3) {
                        if (evaluasi.equals("")) {
                            evaluasi = rs4.getString("evaluasi_nyeri") + " : " + rs4.getString("ket_malam");
                        } else {
                            evaluasi = evaluasi + "\n" + rs4.getString("evaluasi_nyeri") + " : " + rs4.getString("ket_malam");
                        }
                    } 
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs4 != null) {
                    rs4.close();
                }
                if (ps4 != null) {
                    ps4.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void dataEvaluasiSiftPagi() {
        evaluasiPG = "";
        try {
            ps5 = koneksi.prepareStatement("SELECT * from evaluasi_catatan_tindakan_keperawatan where "
                    + "no_rawat='" + TNoRW.getText() + "' and tanggal='" + Valid.SetTgl(TtglCetak.getSelectedItem() + "") + "' "
                    + "and ket_pagi<>'' ORDER BY evaluasi_nyeri");
            try {
                rs5 = ps5.executeQuery();
                while (rs5.next()) {
                    if (evaluasiPG.equals("")) {
                        evaluasiPG = rs5.getString("evaluasi_nyeri") + " : " + rs5.getString("ket_pagi");
                    } else {
                        evaluasiPG = evaluasiPG + "\n" + rs5.getString("evaluasi_nyeri") + " : " + rs5.getString("ket_pagi");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs5 != null) {
                    rs5.close();
                }
                if (ps5 != null) {
                    ps5.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void dataEvaluasiSiftSore() {
        evaluasiSR = "";
        try {
            ps6 = koneksi.prepareStatement("SELECT * from evaluasi_catatan_tindakan_keperawatan where "
                    + "no_rawat='" + TNoRW.getText() + "' and tanggal='" + Valid.SetTgl(TtglCetak.getSelectedItem() + "") + "' "
                    + "and ket_sore<>'' ORDER BY evaluasi_nyeri");
            try {
                rs6 = ps6.executeQuery();
                while (rs6.next()) {
                    if (evaluasiSR.equals("")) {
                        evaluasiSR = rs6.getString("evaluasi_nyeri") + " : " + rs6.getString("ket_sore");
                    } else {
                        evaluasiSR = evaluasiSR + "\n" + rs6.getString("evaluasi_nyeri") + " : " + rs6.getString("ket_sore");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs6 != null) {
                    rs6.close();
                }
                if (ps6 != null) {
                    ps6.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void dataEvaluasiSiftMalam() {
        evaluasiML = "";
        try {
            ps7 = koneksi.prepareStatement("SELECT * from evaluasi_catatan_tindakan_keperawatan where "
                    + "no_rawat='" + TNoRW.getText() + "' and tanggal='" + Valid.SetTgl(TtglCetak.getSelectedItem() + "") + "' "
                    + "and ket_malam<>'' ORDER BY evaluasi_nyeri");
            try {
                rs7 = ps7.executeQuery();
                while (rs7.next()) {
                    if (evaluasiML.equals("")) {
                        evaluasiML = rs7.getString("evaluasi_nyeri") + " : " + rs7.getString("ket_malam");
                    } else {
                        evaluasiML = evaluasiML + "\n" + rs7.getString("evaluasi_nyeri") + " : " + rs7.getString("ket_malam");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs7 != null) {
                    rs7.close();
                }
                if (ps7 != null) {
                    ps7.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilPasienLain() {
        Valid.tabelKosong(tabMode6);
        try {
            if (ChkTglCTK.isSelected() == true) {
                ps8 = koneksi.prepareStatement("SELECT c.*, p.no_rkm_medis, p.nm_pasien, date_format(c.tanggal,'%d-%m-%Y') tgl, "
                        + "time_format(c.jam_tindakan,'%H:%i') jam FROM catatan_tindakan_keperawatan c INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis WHERE "
                        + "c.tanggal='" + Valid.SetTgl(TtglCTK.getSelectedItem() + "") + "' and p.no_rkm_medis like ? or "
                        + "c.tanggal='" + Valid.SetTgl(TtglCTK.getSelectedItem() + "") + "' and p.nm_pasien like ? or "
                        + "c.tanggal='" + Valid.SetTgl(TtglCTK.getSelectedItem() + "") + "' and c.nm_tindakan like ? order by c.tanggal desc limit 50");
            } else {
                ps8 = koneksi.prepareStatement("SELECT c.*, p.no_rkm_medis, p.nm_pasien, date_format(c.tanggal,'%d-%m-%Y') tgl, "
                        + "time_format(c.jam_tindakan,'%H:%i') jam FROM catatan_tindakan_keperawatan c INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis WHERE "
                        + "p.no_rkm_medis like ? or "
                        + "p.nm_pasien like ? or "
                        + "c.nm_tindakan like ? order by c.tanggal desc limit 50");
            }
            try {
                ps8.setString(1, "%" + TCari2.getText().trim() + "%");
                ps8.setString(2, "%" + TCari2.getText().trim() + "%");
                ps8.setString(3, "%" + TCari2.getText().trim() + "%");
                rs8 = ps8.executeQuery();
                while (rs8.next()) {
                    tabMode6.addRow(new String[]{
                        rs8.getString("no_rkm_medis"),
                        rs8.getString("nm_pasien"),
                        rs8.getString("tgl"),
                        rs8.getString("sift"),
                        rs8.getString("jam"),                        
                        rs8.getString("nm_tindakan"),
                        rs8.getString("no_rawat"),
                        Sequel.cariIsi("SELECT ifnull(evaluasi_nyeri,'') from evaluasi_catatan_tindakan_keperawatan where "
                        + "no_rawat='" + rs8.getString("no_rawat") + "' and tanggal ='" + rs8.getString("tanggal") + "' "
                        + "and sift='" + rs8.getString("sift") + "'"),
                        Sequel.cariIsi("SELECT ifnull(ket_pagi,'') from evaluasi_catatan_tindakan_keperawatan where "
                        + "no_rawat='" + rs8.getString("no_rawat") + "' and tanggal ='" + rs8.getString("tanggal") + "' "
                        + "and sift='" + rs8.getString("sift") + "'"),
                        Sequel.cariIsi("SELECT ifnull(ket_sore,'') from evaluasi_catatan_tindakan_keperawatan where "
                        + "no_rawat='" + rs8.getString("no_rawat") + "' and tanggal ='" + rs8.getString("tanggal") + "' "
                        + "and sift='" + rs8.getString("sift") + "'"),
                        Sequel.cariIsi("SELECT ifnull(ket_malam,'') from evaluasi_catatan_tindakan_keperawatan where "
                        + "no_rawat='" + rs8.getString("no_rawat") + "' and tanggal ='" + rs8.getString("tanggal") + "' "
                        + "and sift='" + rs8.getString("sift") + "'"),
                        Sequel.cariIsi("SELECT ifnull(manajemen_nyeri,'') from manajemen_catatan_tindakan_keperawatan where no_rawat='" + rs8.getString("no_rawat") + "' "
                        + "and tanggal ='" + rs8.getString("tanggal") + "'")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs8 != null) {
                    rs8.close();
                }
                if (ps8 != null) {
                    ps8.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}

