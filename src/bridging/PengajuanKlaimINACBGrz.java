package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import laporan.DlgDiagnosaPenyakit;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 *
 * @author perpustakaan
 */
public final class PengajuanKlaimINACBGrz extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabMode4,
            tabMode5, tabMode6, tabMode7, tabMode8, tabMode9, tabMode10, tabMode11, tabMode12, 
            tabMode13, tabMode14, tabMode15, tabMode16, tabMode17;
    private final Properties prop = new Properties();
    private validasi Valid = new validasi();
    private sekuel Sequel = new sekuel();
    private int i = 0, j = 0, x = 0, diag = 0, cekKanker = 0, kankerRanap = 0, kankerRalan = 0;
    private Connection koneksi = koneksiDB.condb();
    private String jknya = "", tgllhrnya = "", jpel = "", norawat = "", sts_umur = "", sts_umur_ok = "", konversiKD = "",
            cekstsPulang = "", kdPulang = "", kls = "", nilaiKP = "", cekBB = "", tglmsk = "", tglplg = "", icuindikator = "",
            naikTurunkls = "", persenNaikKls = "", diagnosaKlaim = "", prosedurKlaim = "", trfPoliEx = "", kdEpisod = "",
            kodeTopUPnya = "", cekFinal = "", datanya = "", naikKLS = "", cekklsLAGI = "", cekCOB = "", hasilVerifDiag1 = "",
            tgllhrnyaCOVID = "", jknyaCOVID = "", cekID = "", cekCI = "", cekKOM = "", cekSTATUS = "", cekRS = "", ibunya = "",
            cekPEMU = "", cekPLAS = "", cekKAN = "", cekDESJEN = "", cekDESMOB = "", cekPET = "", cekTRA = "", kirimEPISOD = "",
            cekASAM = "", cekKUL = "", cekAPTT = "", cekANAL = "", cekPRO = "", cekDIM = "", nilaiUnggah = "", nilaiIsolasi = "",
            cekWAK = "", cekALBU = "", cekCRP = "", cekPT = "", cekANT = "", cekTHO = "", file = "", directory = "", nilaiKriteria = "",
            trfPlasma = "", kodePayor = "", nilaiKelainan = "", prosedurINADRG1 = "", prosedurINADRG2 = "", tglREG = "", NrawatTARIF = "",
            diagnosaKlaimINADRG = "", hasilVerifDiag2 = "", hasilVerifDiag3 = "", hasilVerifDiag4 = "", caramasuk = "", nilaiPembiayaan = "",
            nilaiVenti = "", tglIntu = "", tglEkstu = "", dializer = "", onset = "", caraLhr = "", wktlahir = "", ltkjnn = "", kndisi = "",
            dgBantuanManual = "", forcep = "", vacum = "", cekDeliveri = "", mnt1APP = "", mnt1PUL = "", mnt1GRI = "", mnt1ACT = "", mnt1RES = "",
            mnt5APP = "", mnt5PUL = "", mnt5GRI = "", mnt5ACT = "", mnt5RES = "", requestJson = "";
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, ps9, ps10, ps11, ps12, ps13, ps14, ps15, ps16, ps17, ps18;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rs9, rs10, rs11, rs12, rs13, rs14, rs15, rs16, rs17, rs18;
    private ApiEKLAIM_inacbg mbak_eka = new ApiEKLAIM_inacbg();
    private double nilaiPNB = 0, nilaiRAD = 0, nilaiREH = 0, nilaiOBAT = 0, nilaiPB = 0, 
            nilaiKEP = 0, nilaiLAB = 0, nilaiKAM = 0, nilaiKON = 0, nilaiRI = 0, nilaiTOTAL = 0;
    private double pnb1 = 0, pnb2 = 0, pnb3 = 0, pnb4 = 0, pnb5 = 0, pnb6 = 0,
            pnb8 = 0, pnb9 = 0, pnb10 = 0, pnb11 = 0, pnb12 = 0, pnb13 = 0,
            kmr1 = 0, kmr2 = 0, kmr3 = 0, kmr4 = 0, kmr5 = 0, kmr6 = 0, kmr7 = 0,
            kmr8 = 0, kmr9 = 0, kmr10 = 0, kmr11 = 0, kmr12 = 0, kmr13 = 0, kmr14 = 0,
            kon1 = 0, kon2 = 0, kon3 = 0, kon4 = 0, kon5 = 0, kon6 = 0,
            kon8 = 0, kon9 = 0, kon10 = 0, kon11 = 0, kon12 = 0, kon13 = 0,
            kep1 = 0, kep2 = 0, kep3 = 0, kep4 = 0, kep5 = 0, kep6 = 0,
            kep8 = 0, kep9 = 0, kep10 = 0, kep11 = 0, kep12 = 0, kep13 = 0,
            reh1 = 0, reh2 = 0, reh3 = 0, reh4 = 0, reh5 = 0, reh6 = 0,
            reh8 = 0, reh9 = 0, reh10 = 0, reh11 = 0, reh12 = 0, reh13 = 0,
            obt1 = 0, obt2 = 0, obt3 = 0,
            obt4 = 0, obt5 = 0, obt6 = 0;
    private DlgDiagnosaPenyakit diagnosa = new DlgDiagnosaPenyakit(null, false);

    /**
     * Creates new form DlgRujuk
     *
     * @param parent
     * @param modal
     */
    public PengajuanKlaimINACBGrz(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{"Kode", "Deskripsi Diagnosa ICD-10", "Status Diag.", "Verifikasi"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbDiagnosaPasien1.setModel(tabMode);
        tbDiagnosaPasien1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosaPasien1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbDiagnosaPasien1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(70);
            } else if (i == 1) {
                column.setPreferredWidth(340);
            } else if (i == 2) {
                column.setPreferredWidth(95);
            } else if (i == 3) {
                column.setPreferredWidth(95);
            }
        }
        tbDiagnosaPasien1.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode13 = new DefaultTableModel(null, new Object[]{"Kode", "Deskripsi Diagnosa ICD-10", "Status Diag.", "Verifikasi"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbDiagnosaPasien2.setModel(tabMode13);
        tbDiagnosaPasien2.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosaPasien2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbDiagnosaPasien2.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(70);
            } else if (i == 1) {
                column.setPreferredWidth(340);
            } else if (i == 2) {
                column.setPreferredWidth(95);
            } else if (i == 3) {
                column.setPreferredWidth(95);
            }
        }
        tbDiagnosaPasien2.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode5 = new DefaultTableModel(null, new Object[]{"Kode", "Deskripsi Diagnosa ICD-10", "Status Diag.", "Verifikasi"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbDiagnosaPasie3.setModel(tabMode5);
        tbDiagnosaPasie3.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosaPasie3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbDiagnosaPasie3.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(70);
            } else if (i == 1) {
                column.setPreferredWidth(340);
            } else if (i == 2) {
                column.setPreferredWidth(95);
            } else if (i == 3) {
                column.setPreferredWidth(95);
            }
        }
        tbDiagnosaPasie3.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode14 = new DefaultTableModel(null, new Object[]{"Kode", "Deskripsi Diagnosa ICD-10", "Status Diag.", "Verifikasi"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbDiagnosaPasie4.setModel(tabMode14);
        tbDiagnosaPasie4.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosaPasie4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbDiagnosaPasie4.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(70);
            } else if (i == 1) {
                column.setPreferredWidth(340);
            } else if (i == 2) {
                column.setPreferredWidth(95);
            } else if (i == 3) {
                column.setPreferredWidth(95);
            }
        }
        tbDiagnosaPasie4.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{"Kode", "Deskripsi Prosedur ICD-9-CM"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbTindakanPasien1.setModel(tabMode1);
        tbTindakanPasien1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTindakanPasien1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 2; i++) {
            TableColumn column = tbTindakanPasien1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(70);
            } else if (i == 1) {
                column.setPreferredWidth(350);
            }
        }
        tbTindakanPasien1.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode15 = new DefaultTableModel(null, new Object[]{"Kode", "Deskripsi Prosedur ICD-9-CM","Jlh."}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 2) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbTindakanPasien2.setModel(tabMode15);
        tbTindakanPasien2.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTindakanPasien2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = tbTindakanPasien2.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(70);
            } else if (i == 1) {
                column.setPreferredWidth(350);
            } else if (i == 2) {
                column.setPreferredWidth(40);
            }
        }
        tbTindakanPasien2.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode6 = new DefaultTableModel(null, new Object[]{"Kode", "Deskripsi Prosedur ICD-9-CM"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 2) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbTindakanPasien3.setModel(tabMode6);
        tbTindakanPasien3.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTindakanPasien3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 2; i++) {
            TableColumn column = tbTindakanPasien3.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(70);
            } else if (i == 1) {
                column.setPreferredWidth(350);
            }
        }
        tbTindakanPasien3.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode16 = new DefaultTableModel(null, new Object[]{"Kode", "Deskripsi Prosedur ICD-9-CM","Jlh."}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 2) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbTindakanPasien4.setModel(tabMode16);
        tbTindakanPasien4.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTindakanPasien4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = tbTindakanPasien4.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(70);
            } else if (i == 1) {
                column.setPreferredWidth(350);
            } else if (i == 2) {
                column.setPreferredWidth(40);
            }
        }
        tbTindakanPasien4.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode17 = new DefaultTableModel(null, new Object[]{
            "No.", "Waktu Kelahiran", "Cara Kelahiran", "Letak Janin", "Kondisi", "Bantuan Manual", "Forcep", "Vacum",
            "wk", "ck", "lj", "kon", "bm", "for", "vac"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbPersalinan.setModel(tabMode17);
        tbPersalinan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPersalinan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 15; i++) {
            TableColumn column = tbPersalinan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(135);
            } else if (i == 2) {
                column.setPreferredWidth(110);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(100);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(60);
            } else if (i == 7) {
                column.setPreferredWidth(60);
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
            }
        }
        tbPersalinan.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode2 = new DefaultTableModel(null, new Object[]{"Komponen", "Deskripsi", "Kode INACBG", "Nominal"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbHasil1.setModel(tabMode2);
        tbHasil1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbHasil1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbHasil1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(300);
            } else if (i == 2) {
                column.setPreferredWidth(85);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            }
        }
        tbHasil1.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode7 = new DefaultTableModel(null, new Object[]{"Komponen", "Deskripsi", "Kode INACBG", "Nominal"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbHasil3.setModel(tabMode7);
        tbHasil3.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbHasil3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbHasil3.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(300);
            } else if (i == 2) {
                column.setPreferredWidth(85);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            }
        }
        tbHasil3.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode3 = new DefaultTableModel(null, new Object[]{"#", "Tipe", "Kode", "Deskripsi"}) {
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
        tbHasil2.setModel(tabMode3);
        tbHasil2.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbHasil2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbHasil2.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(125);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(400);
            }
        }
        tbHasil2.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode8 = new DefaultTableModel(null, new Object[]{"#", "Tipe", "Kode", "Deskripsi"}) {
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
        tbHasil4.setModel(tabMode8);
        tbHasil4.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbHasil4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbHasil4.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(125);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(400);
            }
        }
        tbHasil4.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode4 = new DefaultTableModel(null, new Object[]{"kode", "#", "Jenis Episode Ruang Rawat", "Hari"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 1) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbEpisod.setModel(tabMode4);
        tbEpisod.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbEpisod.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbEpisod.getColumnModel().getColumn(i);
            if (i == 0) {
//                column.setPreferredWidth(70);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(25);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(40);
            }
        }
        tbEpisod.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode9 = new DefaultTableModel(null, new Object[]{
            "#", "Jenis Berkas Pendukung", "Nama File", "Status Kirim", "basecode64_file", "file_id", "kode", "file_class"}) {
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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbUnggah.setModel(tabMode9);
        tbUnggah.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbUnggah.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 8; i++) {
            TableColumn column = tbUnggah.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(25);
            } else if (i == 1) {
                column.setPreferredWidth(145);
            } else if (i == 2) {
                column.setPreferredWidth(130);
            } else if (i == 3) {
                column.setPreferredWidth(300);
            } else if (i == 4) {
//                column.setPreferredWidth(350);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
//                column.setPreferredWidth(350);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
//                column.setPreferredWidth(350);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
//                column.setPreferredWidth(350);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbUnggah.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode10 = new DefaultTableModel(null, new Object[]{
            "#", "Paramater Berkas", "Nama File", "Lokasi File"}) {
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
        tbUnggahPilihan.setModel(tabMode10);
        tbUnggahPilihan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbUnggahPilihan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbUnggahPilihan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(25);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(135);
            } else if (i == 3) {
                column.setPreferredWidth(350);
            } 
        }
        tbUnggahPilihan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode11 = new DefaultTableModel(null, new Object[]{"Komponen","Kode","Deskripsi"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbRespon1.setModel(tabMode11);
        tbRespon1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRespon1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = tbRespon1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(420);
            }
        }
        tbRespon1.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode12 = new DefaultTableModel(null, new Object[]{"Komponen","Kode","Deskripsi"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbRespon2.setModel(tabMode12);
        tbRespon2.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRespon2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = tbRespon2.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(420);
            }
        }
        tbRespon2.setDefaultRenderer(Object.class, new WarnaTable());

        losIntensif.setDocument(new batasInput((byte) 2).getOnlyAngka(losIntensif));
        losNaikKls.setDocument(new batasInput((byte) 2).getOnlyAngka(losNaikKls));
        ventilator.setDocument(new batasInput((byte) 4).getOnlyAngka(ventilator));
        subakut.setDocument(new batasInput((byte) 3).getOnlyAngka(subakut));
        kronik.setDocument(new batasInput((byte) 3).getOnlyAngka(kronik));
        subakut1.setDocument(new batasInput((byte) 3).getOnlyAngka(subakut1));
        kronik1.setDocument(new batasInput((byte) 3).getOnlyAngka(kronik1));
        tarifPoliExe.setDocument(new batasInput((byte) 10).getOnlyAngka(tarifPoliExe));
        hari.setDocument(new batasInput((byte) 2).getOnlyAngka(hari));
        brtlhr1.setDocument(new batasInput((byte) 4).getOnlyAngka(brtlhr1));
        brtlhr.setDocument(new batasInput((byte) 4).getOnlyAngka(brtlhr));
        sistole.setDocument(new batasInput((byte) 3).getOnlyAngka(sistole));
        diastole.setDocument(new batasInput((byte) 3).getOnlyAngka(diastole));
        TkntngDrh.setDocument(new batasInput((byte) 2).getOnlyAngka(TkntngDrh));        
        TUsiaHml.setDocument(new batasInput((byte) 2).getOnlyAngka(TUsiaHml));
        Tgravida.setDocument(new batasInput((byte) 2).getOnlyAngka(Tgravida));
        Tpartus.setDocument(new batasInput((byte) 2).getOnlyAngka(Tpartus));
        Tabortus.setDocument(new batasInput((byte) 2).getOnlyAngka(Tabortus));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Popup1 = new javax.swing.JPopupMenu();
        ppDiagnosa = new javax.swing.JMenuItem();
        Popup2 = new javax.swing.JPopupMenu();
        ppHapusDelivery = new javax.swing.JMenuItem();
        tglDiagnosa = new widget.Tanggal();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnKirimOnline = new widget.Button();
        BtnEditKlaim = new widget.Button();
        BtnHapusKlaim = new widget.Button();
        BtnFinal = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        jLabel10 = new widget.Label();
        jLabel39 = new widget.Label();
        jLabel6 = new widget.Label();
        cmbCOB = new widget.ComboBox();
        ChkKelasEksekutif = new widget.CekBox();
        ChkNaikTurun = new widget.CekBox();
        ChkRawatIntensif = new widget.CekBox();
        labelklsHAK = new widget.Label();
        jLabel12 = new widget.Label();
        jLabel13 = new widget.Label();
        jLabel14 = new widget.Label();
        jLabel15 = new widget.Label();
        labelklspel = new widget.Label();
        labelLM = new widget.Label();
        losNaikKls = new widget.TextBox();
        labelHRlm = new widget.Label();
        labelRI = new widget.Label();
        losIntensif = new widget.TextBox();
        labelHRinten = new widget.Label();
        labelventi = new widget.Label();
        ventilator = new widget.TextBox();
        labeljam = new widget.Label();
        jLabel26 = new widget.Label();
        jLabel27 = new widget.Label();
        jLabel28 = new widget.Label();
        jLabel29 = new widget.Label();
        jLabel30 = new widget.Label();
        jLabel31 = new widget.Label();
        subakut = new widget.TextBox();
        jLabel32 = new widget.Label();
        kronik = new widget.TextBox();
        jLabel33 = new widget.Label();
        jLabel34 = new widget.Label();
        labelTarifEx = new widget.Label();
        tarifPoliExe = new widget.TextBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel59 = new widget.Label();
        jLabel60 = new widget.Label();
        labelLOS = new widget.Label();
        BtnGruper = new widget.Button();
        separatHasil = new javax.swing.JSeparator();
        labelhasilG = new widget.Label();
        ScrollHasil1 = new widget.ScrollPane();
        tbHasil1 = new widget.Table();
        ScrollHasil2 = new widget.ScrollPane();
        tbHasil2 = new widget.Table();
        labelRP = new widget.Label();
        labeltarif = new widget.Label();
        umur = new widget.Label();
        cmbKP = new widget.ComboBox();
        labelrwt = new widget.Label();
        dpjp = new widget.Label();
        wktMasuk = new widget.Label();
        wktPulang = new widget.Label();
        noPeserta = new widget.Label();
        jLabel3 = new widget.Label();
        norm = new widget.Label();
        jLabel20 = new widget.Label();
        nmPasien = new widget.Label();
        jLabel43 = new widget.Label();
        jk = new widget.Label();
        jLabel44 = new widget.Label();
        tglLhr = new widget.Label();
        labelhasilG1 = new widget.Label();
        labeltambahan = new widget.Label();
        BtnGruperStage = new widget.Button();
        BtnRefres = new widget.Button();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        nmPetugas = new widget.Label();
        nikPetugas = new widget.Label();
        noSEP = new widget.TextBox();
        brtlhr = new widget.TextBox();
        cmbcrPulang = new widget.ComboBox();
        cmbKH = new widget.ComboBox();
        labelrespon = new widget.Label();
        ScrollHasil5 = new widget.ScrollPane();
        tbRespon1 = new widget.Table();
        pros_inadrg1 = new widget.Label();
        jLabel65 = new widget.Label();
        labelklspel4 = new widget.Label();
        tglSEP = new widget.Label();
        TabDiagnosa1 = new javax.swing.JTabbedPane();
        internalFrame4 = new widget.InternalFrame();
        Scroll7 = new widget.ScrollPane();
        tbDiagnosaPasien1 = new widget.Table();
        internalFrame5 = new widget.InternalFrame();
        Scroll8 = new widget.ScrollPane();
        tbDiagnosaPasien2 = new widget.Table();
        TabProsedur1 = new javax.swing.JTabbedPane();
        internalFrame8 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbTindakanPasien1 = new widget.Table();
        internalFrame9 = new widget.InternalFrame();
        Scroll10 = new widget.ScrollPane();
        tbTindakanPasien2 = new widget.Table();
        noregTB = new widget.TextBox();
        BtnValidasiTB = new widget.Button();
        cmbvalidTB = new widget.ComboBox();
        jLabel106 = new widget.Label();
        ChkpasienTB = new widget.CekBox();
        stts_noreg = new widget.Label();
        labelklspel5 = new widget.Label();
        cmbCrMasuk = new widget.ComboBox();
        labelHRlm1 = new widget.Label();
        cmbPembiayaan = new widget.ComboBox();
        labelHRlm3 = new widget.Label();
        labelHRlm2 = new widget.Label();
        sistole = new widget.TextBox();
        diastole = new widget.TextBox();
        jLabel107 = new widget.Label();
        jLabel108 = new widget.Label();
        labelHRlm4 = new widget.Label();
        cmbVentilator = new widget.ComboBox();
        labelHRlm5 = new widget.Label();
        tglIntubasi = new widget.Tanggal();
        labelHRlm6 = new widget.Label();
        tglEkstubasi = new widget.Tanggal();
        labelHRlm7 = new widget.Label();
        cmbDializer = new widget.ComboBox();
        labelHRlm8 = new widget.Label();
        TkntngDrh = new widget.TextBox();
        TabJaminanJKN = new javax.swing.JTabbedPane();
        internalFrame12 = new widget.InternalFrame();
        FormInput1 = new widget.PanelBiasa();
        labelTarifRS = new widget.Label();
        jLabel37 = new widget.Label();
        jLabel36 = new widget.Label();
        jLabel38 = new widget.Label();
        pnb = new widget.Label();
        jLabel40 = new widget.Label();
        ta = new widget.Label();
        jLabel41 = new widget.Label();
        rad = new widget.Label();
        jLabel42 = new widget.Label();
        reh = new widget.Label();
        jLabel45 = new widget.Label();
        obat = new widget.Label();
        jLabel46 = new widget.Label();
        alkes = new widget.Label();
        jLabel47 = new widget.Label();
        pb = new widget.Label();
        jLabel48 = new widget.Label();
        kep = new widget.Label();
        jLabel49 = new widget.Label();
        lab = new widget.Label();
        jLabel50 = new widget.Label();
        kam = new widget.Label();
        jLabel51 = new widget.Label();
        okr = new widget.Label();
        jLabel52 = new widget.Label();
        bmhp = new widget.Label();
        jLabel53 = new widget.Label();
        kon = new widget.Label();
        jLabel54 = new widget.Label();
        pen = new widget.Label();
        jLabel55 = new widget.Label();
        pd = new widget.Label();
        jLabel56 = new widget.Label();
        ri = new widget.Label();
        jLabel57 = new widget.Label();
        oke = new widget.Label();
        jLabel58 = new widget.Label();
        sa = new widget.Label();
        internalFrame13 = new widget.InternalFrame();
        FormInput3 = new widget.PanelBiasa();
        ChkApgar = new widget.CekBox();
        labelHRlm16 = new widget.Label();
        labelHRlm11 = new widget.Label();
        labelHRlm12 = new widget.Label();
        labelHRlm13 = new widget.Label();
        labelHRlm14 = new widget.Label();
        labelHRlm15 = new widget.Label();
        cmbMnt1App = new widget.ComboBox();
        cmbMnt1Pul = new widget.ComboBox();
        cmbMnt1Gri = new widget.ComboBox();
        cmbMnt1Act = new widget.ComboBox();
        cmbMnt1Res = new widget.ComboBox();
        labelHRlm23 = new widget.Label();
        labelHRlm24 = new widget.Label();
        labelHRlm25 = new widget.Label();
        labelHRlm26 = new widget.Label();
        labelHRlm27 = new widget.Label();
        cmbMnt5App = new widget.ComboBox();
        cmbMnt5Pul = new widget.ComboBox();
        cmbMnt5Gri = new widget.ComboBox();
        cmbMnt5Act = new widget.ComboBox();
        cmbMnt5Res = new widget.ComboBox();
        labelHRlm28 = new widget.Label();
        internalFrame14 = new widget.InternalFrame();
        FormInput4 = new widget.PanelBiasa();
        labelHRlm17 = new widget.Label();
        labelHRlm18 = new widget.Label();
        labelHRlm19 = new widget.Label();
        labelHRlm20 = new widget.Label();
        labelHRlm21 = new widget.Label();
        TUsiaHml = new widget.TextBox();
        labelHRinten2 = new widget.Label();
        Tgravida = new widget.TextBox();
        Tpartus = new widget.TextBox();
        Tabortus = new widget.TextBox();
        cmbOnset = new widget.ComboBox();
        labelHRlm22 = new widget.Label();
        wktkelahiran = new widget.Tanggal();
        labelHRlm29 = new widget.Label();
        cmbCaraLhr = new widget.ComboBox();
        labelHRlm30 = new widget.Label();
        cmbLtkJanin = new widget.ComboBox();
        labelHRlm31 = new widget.Label();
        cmbKondisi = new widget.ComboBox();
        labelHRlm32 = new widget.Label();
        cmbBantuan = new widget.ComboBox();
        labelHRlm33 = new widget.Label();
        cmbForcep = new widget.ComboBox();
        labelHRlm34 = new widget.Label();
        cmbVacum = new widget.ComboBox();
        BtnBaru = new widget.Button();
        BtnTambah = new widget.Button();
        internalFrame15 = new widget.InternalFrame();
        Scroll12 = new widget.ScrollPane();
        tbPersalinan = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        scrollInput1 = new widget.ScrollPane();
        FormInput2 = new widget.PanelBiasa();
        jLabel5 = new widget.Label();
        jLabel11 = new widget.Label();
        jLabel63 = new widget.Label();
        jLabel7 = new widget.Label();
        labelklsHAK1 = new widget.Label();
        jLabel16 = new widget.Label();
        jLabel17 = new widget.Label();
        jLabel18 = new widget.Label();
        jLabel19 = new widget.Label();
        labelklspel1 = new widget.Label();
        labelLM1 = new widget.Label();
        labelRI1 = new widget.Label();
        jLabel35 = new widget.Label();
        jLabel64 = new widget.Label();
        jLabel66 = new widget.Label();
        jLabel67 = new widget.Label();
        jLabel68 = new widget.Label();
        subakut1 = new widget.TextBox();
        jLabel69 = new widget.Label();
        kronik1 = new widget.TextBox();
        jLabel71 = new widget.Label();
        jLabel72 = new widget.Label();
        jLabel73 = new widget.Label();
        labelTarifRS1 = new widget.Label();
        jLabel74 = new widget.Label();
        jLabel75 = new widget.Label();
        jLabel76 = new widget.Label();
        jLabel77 = new widget.Label();
        jLabel78 = new widget.Label();
        jLabel79 = new widget.Label();
        jLabel80 = new widget.Label();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        jLabel83 = new widget.Label();
        jLabel84 = new widget.Label();
        jLabel85 = new widget.Label();
        jLabel86 = new widget.Label();
        jLabel87 = new widget.Label();
        jLabel88 = new widget.Label();
        jLabel89 = new widget.Label();
        jLabel90 = new widget.Label();
        jLabel91 = new widget.Label();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel92 = new widget.Label();
        jLabel93 = new widget.Label();
        labelLOS1 = new widget.Label();
        BtnGruper2 = new widget.Button();
        separatHasil1 = new javax.swing.JSeparator();
        labelhasilG2 = new widget.Label();
        ScrollHasil3 = new widget.ScrollPane();
        tbHasil3 = new widget.Table();
        ScrollHasil4 = new widget.ScrollPane();
        tbHasil4 = new widget.Table();
        labelhak1 = new widget.Label();
        umur1 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        labelrwt1 = new widget.Label();
        dpjp1 = new widget.Label();
        wktMasuk1 = new widget.Label();
        wktPulang1 = new widget.Label();
        jLabel8 = new widget.Label();
        norm1 = new widget.Label();
        jLabel21 = new widget.Label();
        nmPasien1 = new widget.Label();
        jLabel94 = new widget.Label();
        jk1 = new widget.Label();
        jLabel95 = new widget.Label();
        tglLhr1 = new widget.Label();
        pnb7 = new widget.Label();
        ta1 = new widget.Label();
        rad1 = new widget.Label();
        reh7 = new widget.Label();
        obat1 = new widget.Label();
        alkes1 = new widget.Label();
        pb1 = new widget.Label();
        kep7 = new widget.Label();
        lab1 = new widget.Label();
        kam1 = new widget.Label();
        okr1 = new widget.Label();
        bmhp1 = new widget.Label();
        kon7 = new widget.Label();
        pen1 = new widget.Label();
        pd1 = new widget.Label();
        ri1 = new widget.Label();
        oke1 = new widget.Label();
        sa1 = new widget.Label();
        labelhasilG3 = new widget.Label();
        BtnGruperStage1 = new widget.Button();
        BtnRefres1 = new widget.Button();
        jLabel96 = new widget.Label();
        jLabel97 = new widget.Label();
        nmPetugas1 = new widget.Label();
        nikPetugas1 = new widget.Label();
        noID = new widget.TextBox();
        cmbID = new widget.ComboBox();
        cmbKomor = new widget.ComboBox();
        cmbEpisod = new widget.ComboBox();
        labelRI2 = new widget.Label();
        cmbRS = new widget.ComboBox();
        cmbCO = new widget.ComboBox();
        hari = new widget.TextBox();
        labelklspel2 = new widget.Label();
        labelLM2 = new widget.Label();
        jLabel99 = new widget.Label();
        Scroll4 = new widget.ScrollPane();
        tbEpisod = new widget.Table();
        BtnAddEpisod = new widget.Button();
        BtnDelEpisod = new widget.Button();
        jLabel100 = new widget.Label();
        labelTarifEx2 = new widget.Label();
        jLabel101 = new widget.Label();
        ChkAsam = new widget.CekBox();
        ChkKultur = new widget.CekBox();
        ChkAPTT = new widget.CekBox();
        ChkAnalisa = new widget.CekBox();
        ChkProcal = new widget.CekBox();
        ChkDimer = new widget.CekBox();
        ChkWaktu = new widget.CekBox();
        ChkAlbumin = new widget.CekBox();
        ChkCRP = new widget.CekBox();
        ChkPT = new widget.CekBox();
        ChkAnti = new widget.CekBox();
        ChkThorax = new widget.CekBox();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel102 = new widget.Label();
        ChkPemulasaran = new widget.CekBox();
        ChkPlastik = new widget.CekBox();
        ChkKantong = new widget.CekBox();
        ChkDesinfekJen = new widget.CekBox();
        ChkDesinfekMob = new widget.CekBox();
        ChkPeti = new widget.CekBox();
        ChkTranspot = new widget.CekBox();
        jSeparator6 = new javax.swing.JSeparator();
        noKlaim = new widget.TextBox();
        labelklspel3 = new widget.Label();
        cmbUnggah = new widget.ComboBox();
        lokasiFile = new widget.TextBox();
        Scroll5 = new widget.ScrollPane();
        tbUnggah = new widget.Table();
        BtnAddUnggah = new widget.Button();
        BtnDelUnggah = new widget.Button();
        BtnUploadFile = new widget.Button();
        brtlhr1 = new widget.TextBox();
        ChkSemuaFaktor = new widget.CekBox();
        ChkSemuaPelengkap = new widget.CekBox();
        cmbcrPulang1 = new widget.ComboBox();
        jLabel103 = new widget.Label();
        cmbKriteria = new widget.ComboBox();
        jLabel104 = new widget.Label();
        cmbIsolasi = new widget.ComboBox();
        tarifPlasma = new widget.TextBox();
        labelRP2 = new widget.Label();
        labeltarifPlasma = new widget.Label();
        jLabel110 = new widget.Label();
        nmJaminan = new widget.Label();
        jLabel70 = new widget.Label();
        cmbKelainan = new widget.ComboBox();
        labelnmIbu = new widget.Label();
        ChkRawatIntensif1 = new widget.CekBox();
        labelRI3 = new widget.Label();
        losIntensif1 = new widget.TextBox();
        labelHRinten1 = new widget.Label();
        labelventi1 = new widget.Label();
        ventilator1 = new widget.TextBox();
        labeljam2 = new widget.Label();
        Scroll6 = new widget.ScrollPane();
        tbUnggahPilihan = new widget.Table();
        BtnDelUnggahPilihan = new widget.Button();
        BtnUnggahDipilih = new widget.Button();
        jSeparator7 = new javax.swing.JSeparator();
        labelrespon1 = new widget.Label();
        ScrollHasil6 = new widget.ScrollPane();
        tbRespon2 = new widget.Table();
        pros_inadrg2 = new widget.Label();
        jLabel98 = new widget.Label();
        TabDiagnosa2 = new javax.swing.JTabbedPane();
        internalFrame6 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbDiagnosaPasie3 = new widget.Table();
        internalFrame7 = new widget.InternalFrame();
        Scroll9 = new widget.ScrollPane();
        tbDiagnosaPasie4 = new widget.Table();
        TabProsedur2 = new javax.swing.JTabbedPane();
        internalFrame10 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        tbTindakanPasien3 = new widget.Table();
        internalFrame11 = new widget.InternalFrame();
        Scroll11 = new widget.ScrollPane();
        tbTindakanPasien4 = new widget.Table();
        jLabel105 = new widget.Label();
        cmbCrMasuk1 = new widget.ComboBox();
        labelventi2 = new widget.Label();
        cmbVentilator1 = new widget.ComboBox();
        labeljam3 = new widget.Label();
        tglIntubasi1 = new widget.Tanggal();
        labeljam4 = new widget.Label();
        tglEkstubasi1 = new widget.Tanggal();

        Popup1.setName("Popup1"); // NOI18N

        ppDiagnosa.setBackground(new java.awt.Color(242, 242, 242));
        ppDiagnosa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppDiagnosa.setText("Diagnosa ICD-10");
        ppDiagnosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppDiagnosa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppDiagnosa.setIconTextGap(8);
        ppDiagnosa.setName("ppDiagnosa"); // NOI18N
        ppDiagnosa.setPreferredSize(new java.awt.Dimension(180, 25));
        ppDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppDiagnosaBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(ppDiagnosa);

        Popup2.setName("Popup2"); // NOI18N

        ppHapusDelivery.setBackground(new java.awt.Color(242, 242, 242));
        ppHapusDelivery.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppHapusDelivery.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cancel.png"))); // NOI18N
        ppHapusDelivery.setText("Hapus Semua Data");
        ppHapusDelivery.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppHapusDelivery.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppHapusDelivery.setIconTextGap(8);
        ppHapusDelivery.setName("ppHapusDelivery"); // NOI18N
        ppHapusDelivery.setPreferredSize(new java.awt.Dimension(180, 25));
        ppHapusDelivery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppHapusDeliveryBtnPrintActionPerformed(evt);
            }
        });
        Popup2.add(ppHapusDelivery);

        tglDiagnosa.setEditable(false);
        tglDiagnosa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-02-2023" }));
        tglDiagnosa.setDisplayFormat("dd-MM-yyyy");
        tglDiagnosa.setName("tglDiagnosa"); // NOI18N
        tglDiagnosa.setOpaque(false);
        tglDiagnosa.setPreferredSize(new java.awt.Dimension(90, 23));
        tglDiagnosa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tglDiagnosaDTPCari1ItemStateChanged(evt);
            }
        });
        tglDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglDiagnosaKeyPressed(evt);
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Proses Pengajuan Klaim INACBG ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setPreferredSize(new java.awt.Dimension(133, 1170));
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 9, 9));

        BtnKirimOnline.setForeground(new java.awt.Color(0, 0, 0));
        BtnKirimOnline.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnKirimOnline.setMnemonic('K');
        BtnKirimOnline.setText("Kirim Klaim Online");
        BtnKirimOnline.setToolTipText("Alt+K");
        BtnKirimOnline.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnKirimOnline.setName("BtnKirimOnline"); // NOI18N
        BtnKirimOnline.setPreferredSize(new java.awt.Dimension(150, 30));
        BtnKirimOnline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKirimOnlineActionPerformed(evt);
            }
        });
        BtnKirimOnline.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKirimOnlineKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKirimOnline);

        BtnEditKlaim.setForeground(new java.awt.Color(0, 0, 0));
        BtnEditKlaim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/addressbook-edit24.png"))); // NOI18N
        BtnEditKlaim.setMnemonic('E');
        BtnEditKlaim.setText("Edit Ulang Klaim");
        BtnEditKlaim.setToolTipText("Alt+E");
        BtnEditKlaim.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnEditKlaim.setName("BtnEditKlaim"); // NOI18N
        BtnEditKlaim.setPreferredSize(new java.awt.Dimension(140, 30));
        BtnEditKlaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditKlaimActionPerformed(evt);
            }
        });
        BtnEditKlaim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKlaimKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnEditKlaim);

        BtnHapusKlaim.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusKlaim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/addressbook-remove24.png"))); // NOI18N
        BtnHapusKlaim.setMnemonic('H');
        BtnHapusKlaim.setText("Hapus Klaim");
        BtnHapusKlaim.setToolTipText("Alt+H");
        BtnHapusKlaim.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnHapusKlaim.setName("BtnHapusKlaim"); // NOI18N
        BtnHapusKlaim.setPreferredSize(new java.awt.Dimension(120, 30));
        BtnHapusKlaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusKlaimActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnHapusKlaim);

        BtnFinal.setForeground(new java.awt.Color(0, 0, 0));
        BtnFinal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Export.png"))); // NOI18N
        BtnFinal.setMnemonic('F');
        BtnFinal.setText("Final Klaim");
        BtnFinal.setToolTipText("Alt+F");
        BtnFinal.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnFinal.setName("BtnFinal"); // NOI18N
        BtnFinal.setPreferredSize(new java.awt.Dimension(120, 30));
        BtnFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFinalActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnFinal);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setGlassColor(new java.awt.Color(0, 153, 153));
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

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.setPreferredSize(new java.awt.Dimension(140, 1270));
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(102, 1100));
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 1100));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "::[ Data Klaim ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 0, 0))); // NOI18N
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 1080));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Peserta : ");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(3, 53, 120, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Jenis Rawat : ");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(3, 81, 120, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("COB : ");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(330, 221, 42, 23);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("No. SEP : ");
        jLabel6.setName("jLabel6"); // NOI18N
        FormInput.add(jLabel6);
        jLabel6.setBounds(220, 53, 60, 23);

        cmbCOB.setForeground(new java.awt.Color(0, 0, 0));
        cmbCOB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbCOB.setName("cmbCOB"); // NOI18N
        cmbCOB.setOpaque(false);
        cmbCOB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbCOBMouseClicked(evt);
            }
        });
        cmbCOB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbCOBKeyPressed(evt);
            }
        });
        FormInput.add(cmbCOB);
        cmbCOB.setBounds(375, 221, 170, 23);

        ChkKelasEksekutif.setBackground(new java.awt.Color(255, 255, 250));
        ChkKelasEksekutif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKelasEksekutif.setForeground(new java.awt.Color(0, 0, 0));
        ChkKelasEksekutif.setText("Kelas Eksekutif");
        ChkKelasEksekutif.setBorderPainted(true);
        ChkKelasEksekutif.setBorderPaintedFlat(true);
        ChkKelasEksekutif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKelasEksekutif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKelasEksekutif.setName("ChkKelasEksekutif"); // NOI18N
        ChkKelasEksekutif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkKelasEksekutifActionPerformed(evt);
            }
        });
        FormInput.add(ChkKelasEksekutif);
        ChkKelasEksekutif.setBounds(177, 81, 95, 23);

        ChkNaikTurun.setBackground(new java.awt.Color(255, 255, 250));
        ChkNaikTurun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkNaikTurun.setForeground(new java.awt.Color(0, 0, 0));
        ChkNaikTurun.setText("Naik/Turun Kelas");
        ChkNaikTurun.setBorderPainted(true);
        ChkNaikTurun.setBorderPaintedFlat(true);
        ChkNaikTurun.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkNaikTurun.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkNaikTurun.setName("ChkNaikTurun"); // NOI18N
        ChkNaikTurun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkNaikTurunActionPerformed(evt);
            }
        });
        FormInput.add(ChkNaikTurun);
        ChkNaikTurun.setBounds(279, 81, 110, 23);

        ChkRawatIntensif.setBackground(new java.awt.Color(255, 255, 250));
        ChkRawatIntensif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkRawatIntensif.setForeground(new java.awt.Color(0, 0, 0));
        ChkRawatIntensif.setText("Ada Rawat Intensif");
        ChkRawatIntensif.setBorderPainted(true);
        ChkRawatIntensif.setBorderPaintedFlat(true);
        ChkRawatIntensif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkRawatIntensif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkRawatIntensif.setName("ChkRawatIntensif"); // NOI18N
        ChkRawatIntensif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkRawatIntensifActionPerformed(evt);
            }
        });
        FormInput.add(ChkRawatIntensif);
        ChkRawatIntensif.setBounds(390, 81, 130, 23);

        labelklsHAK.setForeground(new java.awt.Color(0, 0, 0));
        labelklsHAK.setText("Kelas Hak : ");
        labelklsHAK.setName("labelklsHAK"); // NOI18N
        FormInput.add(labelklsHAK);
        labelklsHAK.setBounds(565, 81, 90, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tgl.  Rawat : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(3, 109, 120, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Masuk : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(125, 109, 40, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Pulang : ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(305, 109, 60, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Umur : ");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(605, 109, 50, 23);

        labelklspel.setForeground(new java.awt.Color(0, 0, 0));
        labelklspel.setText("Kelas Pelayanan : ");
        labelklspel.setName("labelklspel"); // NOI18N
        FormInput.add(labelklspel);
        labelklspel.setBounds(3, 137, 120, 23);

        labelLM.setForeground(new java.awt.Color(0, 0, 0));
        labelLM.setText("LOS Naik Kelas : ");
        labelLM.setName("labelLM"); // NOI18N
        FormInput.add(labelLM);
        labelLM.setBounds(565, 137, 90, 23);

        losNaikKls.setForeground(new java.awt.Color(0, 0, 0));
        losNaikKls.setName("losNaikKls"); // NOI18N
        losNaikKls.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                losNaikKlsKeyPressed(evt);
            }
        });
        FormInput.add(losNaikKls);
        losNaikKls.setBounds(655, 137, 40, 23);

        labelHRlm.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelHRlm.setText("hari");
        labelHRlm.setName("labelHRlm"); // NOI18N
        FormInput.add(labelHRlm);
        labelHRlm.setBounds(700, 137, 30, 23);

        labelRI.setForeground(new java.awt.Color(0, 0, 0));
        labelRI.setText("LOS Intensif : ");
        labelRI.setName("labelRI"); // NOI18N
        FormInput.add(labelRI);
        labelRI.setBounds(3, 165, 120, 23);

        losIntensif.setForeground(new java.awt.Color(0, 0, 0));
        losIntensif.setName("losIntensif"); // NOI18N
        FormInput.add(losIntensif);
        losIntensif.setBounds(125, 165, 40, 23);

        labelHRinten.setForeground(new java.awt.Color(0, 0, 0));
        labelHRinten.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelHRinten.setText("hari");
        labelHRinten.setName("labelHRinten"); // NOI18N
        FormInput.add(labelHRinten);
        labelHRinten.setBounds(170, 165, 30, 23);

        labelventi.setForeground(new java.awt.Color(0, 0, 0));
        labelventi.setText("Ventilator : ");
        labelventi.setName("labelventi"); // NOI18N
        FormInput.add(labelventi);
        labelventi.setBounds(565, 165, 90, 23);

        ventilator.setForeground(new java.awt.Color(0, 0, 0));
        ventilator.setName("ventilator"); // NOI18N
        ventilator.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ventilatorKeyPressed(evt);
            }
        });
        FormInput.add(ventilator);
        ventilator.setBounds(655, 165, 48, 23);

        labeljam.setForeground(new java.awt.Color(0, 0, 0));
        labeljam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labeljam.setText("jam");
        labeljam.setName("labeljam"); // NOI18N
        FormInput.add(labeljam);
        labeljam.setBounds(709, 165, 30, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("LOS : ");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(3, 193, 120, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("hari");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(160, 193, 30, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Berat Lahir : ");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(565, 193, 90, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("gram");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(709, 193, 30, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("ADL Score : ");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(3, 221, 120, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Sub Acute : ");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(125, 221, 60, 23);

        subakut.setForeground(new java.awt.Color(0, 0, 0));
        subakut.setName("subakut"); // NOI18N
        subakut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                subakutKeyPressed(evt);
            }
        });
        FormInput.add(subakut);
        subakut.setBounds(188, 221, 40, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Chronic : ");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(228, 221, 55, 23);

        kronik.setForeground(new java.awt.Color(0, 0, 0));
        kronik.setName("kronik"); // NOI18N
        kronik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kronikKeyPressed(evt);
            }
        });
        FormInput.add(kronik);
        kronik.setBounds(285, 221, 40, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Cara Pulang : ");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(565, 221, 90, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("DPJP : ");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(3, 249, 120, 23);

        labelTarifEx.setForeground(new java.awt.Color(0, 0, 0));
        labelTarifEx.setText("Tarif Poliklinik Eks. : ");
        labelTarifEx.setName("labelTarifEx"); // NOI18N
        FormInput.add(labelTarifEx);
        labelTarifEx.setBounds(535, 249, 120, 23);

        tarifPoliExe.setForeground(new java.awt.Color(0, 0, 0));
        tarifPoliExe.setName("tarifPoliExe"); // NOI18N
        tarifPoliExe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tarifPoliExeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tarifPoliExeKeyReleased(evt);
            }
        });
        FormInput.add(tarifPoliExe);
        tarifPoliExe.setBounds(655, 249, 100, 23);

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 528, 1260, 5);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel59.setText("Diagnosa (ICD-10) : ");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(45, 530, 140, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel60.setText("Prosedur (ICD-9-CM) : ");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(690, 530, 120, 23);

        labelLOS.setForeground(new java.awt.Color(0, 0, 0));
        labelLOS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLOS.setText("1");
        labelLOS.setName("labelLOS"); // NOI18N
        FormInput.add(labelLOS);
        labelLOS.setBounds(125, 193, 30, 23);

        BtnGruper.setForeground(new java.awt.Color(0, 0, 0));
        BtnGruper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/SeratusRibu.png"))); // NOI18N
        BtnGruper.setMnemonic('G');
        BtnGruper.setText("Simpan & Grouper");
        BtnGruper.setToolTipText("Alt+G");
        BtnGruper.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnGruper.setName("BtnGruper"); // NOI18N
        BtnGruper.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnGruper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGruperActionPerformed(evt);
            }
        });
        FormInput.add(BtnGruper);
        BtnGruper.setBounds(760, 490, 160, 30);

        separatHasil.setForeground(new java.awt.Color(0, 0, 0));
        separatHasil.setName("separatHasil"); // NOI18N
        FormInput.add(separatHasil);
        separatHasil.setBounds(0, 708, 1260, 5);

        labelhasilG.setForeground(new java.awt.Color(0, 0, 204));
        labelhasilG.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelhasilG.setText("Hasil Grouper : ");
        labelhasilG.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelhasilG.setName("labelhasilG"); // NOI18N
        FormInput.add(labelhasilG);
        labelhasilG.setBounds(45, 713, 576, 23);

        ScrollHasil1.setName("ScrollHasil1"); // NOI18N
        ScrollHasil1.setOpaque(true);

        tbHasil1.setToolTipText("");
        tbHasil1.setName("tbHasil1"); // NOI18N
        ScrollHasil1.setViewportView(tbHasil1);

        FormInput.add(ScrollHasil1);
        ScrollHasil1.setBounds(45, 736, 576, 170);

        ScrollHasil2.setName("ScrollHasil2"); // NOI18N
        ScrollHasil2.setOpaque(true);

        tbHasil2.setToolTipText("");
        tbHasil2.setName("tbHasil2"); // NOI18N
        ScrollHasil2.setViewportView(tbHasil2);

        FormInput.add(ScrollHasil2);
        ScrollHasil2.setBounds(45, 932, 1155, 97);

        labelRP.setForeground(new java.awt.Color(0, 0, 0));
        labelRP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelRP.setText("Rp. ");
        labelRP.setName("labelRP"); // NOI18N
        FormInput.add(labelRP);
        labelRP.setBounds(760, 249, 20, 23);

        labeltarif.setForeground(new java.awt.Color(0, 0, 0));
        labeltarif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labeltarif.setText("0");
        labeltarif.setName("labeltarif"); // NOI18N
        FormInput.add(labeltarif);
        labeltarif.setBounds(785, 249, 190, 23);

        umur.setForeground(new java.awt.Color(0, 0, 0));
        umur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        umur.setText("umur");
        umur.setName("umur"); // NOI18N
        FormInput.add(umur);
        umur.setBounds(655, 109, 80, 23);

        cmbKP.setForeground(new java.awt.Color(0, 0, 0));
        cmbKP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kelas 3", "Kelas 2", "Kelas 1", "Kelas VIP", "Kelas VVIP" }));
        cmbKP.setName("cmbKP"); // NOI18N
        cmbKP.setOpaque(false);
        FormInput.add(cmbKP);
        cmbKP.setBounds(125, 137, 80, 23);

        labelrwt.setForeground(new java.awt.Color(0, 0, 0));
        labelrwt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelrwt.setText("labelrwt");
        labelrwt.setName("labelrwt"); // NOI18N
        FormInput.add(labelrwt);
        labelrwt.setBounds(125, 81, 50, 23);

        dpjp.setForeground(new java.awt.Color(0, 0, 0));
        dpjp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dpjp.setText("labeldpjp");
        dpjp.setName("dpjp"); // NOI18N
        FormInput.add(dpjp);
        dpjp.setBounds(125, 249, 390, 23);

        wktMasuk.setForeground(new java.awt.Color(0, 0, 0));
        wktMasuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        wktMasuk.setText("labelwktMasuk");
        wktMasuk.setName("wktMasuk"); // NOI18N
        FormInput.add(wktMasuk);
        wktMasuk.setBounds(167, 109, 130, 23);

        wktPulang.setForeground(new java.awt.Color(0, 0, 0));
        wktPulang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        wktPulang.setText("labelwktPulang");
        wktPulang.setName("wktPulang"); // NOI18N
        FormInput.add(wktPulang);
        wktPulang.setBounds(367, 109, 170, 23);

        noPeserta.setForeground(new java.awt.Color(0, 0, 0));
        noPeserta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        noPeserta.setText("labelnoPeserta");
        noPeserta.setName("noPeserta"); // NOI18N
        FormInput.add(noPeserta);
        noPeserta.setBounds(125, 53, 95, 23);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("No. RM : ");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(3, 25, 120, 23);

        norm.setForeground(new java.awt.Color(0, 0, 0));
        norm.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        norm.setText("labelnorm");
        norm.setName("norm"); // NOI18N
        FormInput.add(norm);
        norm.setBounds(125, 25, 70, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Nama Pasien : ");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(200, 25, 80, 23);

        nmPasien.setForeground(new java.awt.Color(0, 0, 0));
        nmPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nmPasien.setText("labelnmPasien");
        nmPasien.setName("nmPasien"); // NOI18N
        FormInput.add(nmPasien);
        nmPasien.setBounds(282, 25, 290, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Jns. Kelamin : ");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(574, 25, 80, 23);

        jk.setForeground(new java.awt.Color(0, 0, 0));
        jk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jk.setText("labeljk");
        jk.setName("jk"); // NOI18N
        FormInput.add(jk);
        jk.setBounds(655, 25, 80, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Tgl. Lahir : ");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(595, 53, 60, 23);

        tglLhr.setForeground(new java.awt.Color(0, 0, 0));
        tglLhr.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tglLhr.setText("labeltglLhr");
        tglLhr.setName("tglLhr"); // NOI18N
        FormInput.add(tglLhr);
        tglLhr.setBounds(655, 53, 80, 23);

        labelhasilG1.setForeground(new java.awt.Color(0, 0, 0));
        labelhasilG1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelhasilG1.setText("Special CMG Option : ");
        labelhasilG1.setName("labelhasilG1"); // NOI18N
        FormInput.add(labelhasilG1);
        labelhasilG1.setBounds(45, 907, 120, 23);

        labeltambahan.setForeground(new java.awt.Color(0, 0, 0));
        labeltambahan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labeltambahan.setText("tambahan biaya");
        labeltambahan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labeltambahan.setName("labeltambahan"); // NOI18N
        FormInput.add(labeltambahan);
        labeltambahan.setBounds(45, 1030, 960, 23);

        BtnGruperStage.setForeground(new java.awt.Color(0, 0, 0));
        BtnGruperStage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/SeratusRibu.png"))); // NOI18N
        BtnGruperStage.setMnemonic('G');
        BtnGruperStage.setText("Grouper Stage 2");
        BtnGruperStage.setToolTipText("Alt+G");
        BtnGruperStage.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnGruperStage.setName("BtnGruperStage"); // NOI18N
        BtnGruperStage.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnGruperStage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGruperStageActionPerformed(evt);
            }
        });
        BtnGruperStage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGruperStageKeyPressed(evt);
            }
        });
        FormInput.add(BtnGruperStage);
        BtnGruperStage.setBounds(1055, 1035, 140, 30);

        BtnRefres.setForeground(new java.awt.Color(0, 0, 0));
        BtnRefres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnRefres.setMnemonic('R');
        BtnRefres.setText("Refresh Diagn. & Pros.");
        BtnRefres.setToolTipText("Alt+R");
        BtnRefres.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnRefres.setName("BtnRefres"); // NOI18N
        BtnRefres.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnRefres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRefresActionPerformed(evt);
            }
        });
        BtnRefres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnRefresKeyPressed(evt);
            }
        });
        FormInput.add(BtnRefres);
        BtnRefres.setBounds(930, 490, 180, 30);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("Nama Petugas : ");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(725, 25, 90, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("NIK Petugas : ");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(735, 53, 80, 23);

        nmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        nmPetugas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nmPetugas.setText("labelnmPetugas");
        nmPetugas.setName("nmPetugas"); // NOI18N
        FormInput.add(nmPetugas);
        nmPetugas.setBounds(817, 25, 430, 23);

        nikPetugas.setForeground(new java.awt.Color(0, 0, 0));
        nikPetugas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nikPetugas.setText("labelnikPetugas");
        nikPetugas.setName("nikPetugas"); // NOI18N
        FormInput.add(nikPetugas);
        nikPetugas.setBounds(817, 53, 190, 23);

        noSEP.setEditable(false);
        noSEP.setForeground(new java.awt.Color(0, 0, 0));
        noSEP.setName("noSEP"); // NOI18N
        FormInput.add(noSEP);
        noSEP.setBounds(282, 53, 150, 23);

        brtlhr.setForeground(new java.awt.Color(0, 0, 0));
        brtlhr.setName("brtlhr"); // NOI18N
        brtlhr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                brtlhrKeyPressed(evt);
            }
        });
        FormInput.add(brtlhr);
        brtlhr.setBounds(655, 193, 48, 23);

        cmbcrPulang.setForeground(new java.awt.Color(0, 0, 0));
        cmbcrPulang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Dirujuk", "Atas permintaan sendiri", "Meninggal", "Atas persetujuan dokter", "Lain-lain" }));
        cmbcrPulang.setName("cmbcrPulang"); // NOI18N
        cmbcrPulang.setOpaque(false);
        cmbcrPulang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbcrPulangMouseClicked(evt);
            }
        });
        cmbcrPulang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbcrPulangKeyPressed(evt);
            }
        });
        FormInput.add(cmbcrPulang);
        cmbcrPulang.setBounds(655, 221, 157, 23);

        cmbKH.setForeground(new java.awt.Color(0, 0, 0));
        cmbKH.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kelas 3", "Kelas 2", "Kelas 1" }));
        cmbKH.setName("cmbKH"); // NOI18N
        cmbKH.setOpaque(false);
        FormInput.add(cmbKH);
        cmbKH.setBounds(655, 81, 70, 23);

        labelrespon.setForeground(new java.awt.Color(0, 0, 204));
        labelrespon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelrespon.setText("Response INAGROUPER : ");
        labelrespon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelrespon.setName("labelrespon"); // NOI18N
        FormInput.add(labelrespon);
        labelrespon.setBounds(630, 713, 576, 23);

        ScrollHasil5.setName("ScrollHasil5"); // NOI18N
        ScrollHasil5.setOpaque(true);

        tbRespon1.setToolTipText("");
        tbRespon1.setName("tbRespon1"); // NOI18N
        ScrollHasil5.setViewportView(tbRespon1);

        FormInput.add(ScrollHasil5);
        ScrollHasil5.setBounds(630, 736, 576, 170);

        pros_inadrg1.setForeground(new java.awt.Color(0, 0, 204));
        pros_inadrg1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pros_inadrg1.setText("pros_inadrg1");
        pros_inadrg1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pros_inadrg1.setName("pros_inadrg1"); // NOI18N
        FormInput.add(pros_inadrg1);
        pros_inadrg1.setBounds(870, 680, 430, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel65.setText("Prosedur INADRG (V6) yang dikirim : ");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(690, 680, 180, 23);

        labelklspel4.setForeground(new java.awt.Color(0, 0, 0));
        labelklspel4.setText("Tgl. SEP : ");
        labelklspel4.setName("labelklspel4"); // NOI18N
        FormInput.add(labelklspel4);
        labelklspel4.setBounds(430, 53, 60, 23);

        tglSEP.setForeground(new java.awt.Color(0, 0, 0));
        tglSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tglSEP.setText("tglSEP");
        tglSEP.setName("tglSEP"); // NOI18N
        FormInput.add(tglSEP);
        tglSEP.setBounds(490, 53, 90, 23);

        TabDiagnosa1.setBackground(new java.awt.Color(250, 255, 245));
        TabDiagnosa1.setForeground(new java.awt.Color(0, 0, 0));
        TabDiagnosa1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabDiagnosa1.setName("TabDiagnosa1"); // NOI18N
        TabDiagnosa1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabDiagnosa1MouseClicked(evt);
            }
        });

        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll7.setComponentPopupMenu(Popup1);
        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        tbDiagnosaPasien1.setAutoCreateRowSorter(true);
        tbDiagnosaPasien1.setToolTipText("Silahkan klik kanan untuk memperbaiki datanya");
        tbDiagnosaPasien1.setComponentPopupMenu(Popup1);
        tbDiagnosaPasien1.setName("tbDiagnosaPasien1"); // NOI18N
        Scroll7.setViewportView(tbDiagnosaPasien1);

        internalFrame4.add(Scroll7, java.awt.BorderLayout.CENTER);

        TabDiagnosa1.addTab("Diagnosa Statistik (V5)", internalFrame4);

        internalFrame5.setBorder(null);
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll8.setComponentPopupMenu(Popup1);
        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        tbDiagnosaPasien2.setAutoCreateRowSorter(true);
        tbDiagnosaPasien2.setToolTipText("Silahkan klik kanan untuk memperbaiki datanya");
        tbDiagnosaPasien2.setComponentPopupMenu(Popup1);
        tbDiagnosaPasien2.setName("tbDiagnosaPasien2"); // NOI18N
        Scroll8.setViewportView(tbDiagnosaPasien2);

        internalFrame5.add(Scroll8, java.awt.BorderLayout.CENTER);

        TabDiagnosa1.addTab("Diagnosa INADRG (V6)", internalFrame5);

        FormInput.add(TabDiagnosa1);
        TabDiagnosa1.setBounds(45, 550, 630, 145);

        TabProsedur1.setBackground(new java.awt.Color(250, 255, 245));
        TabProsedur1.setForeground(new java.awt.Color(0, 0, 0));
        TabProsedur1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabProsedur1.setName("TabProsedur1"); // NOI18N
        TabProsedur1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabProsedur1MouseClicked(evt);
            }
        });

        internalFrame8.setBorder(null);
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll2.setComponentPopupMenu(Popup1);
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbTindakanPasien1.setAutoCreateRowSorter(true);
        tbTindakanPasien1.setToolTipText("Silahkan klik kanan untuk memperbaiki datanya");
        tbTindakanPasien1.setComponentPopupMenu(Popup1);
        tbTindakanPasien1.setName("tbTindakanPasien1"); // NOI18N
        Scroll2.setViewportView(tbTindakanPasien1);

        internalFrame8.add(Scroll2, java.awt.BorderLayout.CENTER);

        TabProsedur1.addTab("Prosedur Statistik (V5)", internalFrame8);

        internalFrame9.setBorder(null);
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll10.setComponentPopupMenu(Popup1);
        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);

        tbTindakanPasien2.setAutoCreateRowSorter(true);
        tbTindakanPasien2.setToolTipText("Silahkan klik kanan untuk memperbaiki datanya");
        tbTindakanPasien2.setComponentPopupMenu(Popup1);
        tbTindakanPasien2.setName("tbTindakanPasien2"); // NOI18N
        Scroll10.setViewportView(tbTindakanPasien2);

        internalFrame9.add(Scroll10, java.awt.BorderLayout.CENTER);

        TabProsedur1.addTab("Prosedur INADRG (V6)", internalFrame9);

        FormInput.add(TabProsedur1);
        TabProsedur1.setBounds(690, 550, 510, 131);

        noregTB.setForeground(new java.awt.Color(0, 0, 0));
        noregTB.setName("noregTB"); // NOI18N
        FormInput.add(noregTB);
        noregTB.setBounds(285, 165, 260, 23);

        BtnValidasiTB.setForeground(new java.awt.Color(0, 0, 0));
        BtnValidasiTB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnValidasiTB.setMnemonic('O');
        BtnValidasiTB.setText("Di Proses");
        BtnValidasiTB.setToolTipText("Alt+O");
        BtnValidasiTB.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnValidasiTB.setName("BtnValidasiTB"); // NOI18N
        BtnValidasiTB.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnValidasiTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnValidasiTBActionPerformed(evt);
            }
        });
        FormInput.add(BtnValidasiTB);
        BtnValidasiTB.setBounds(390, 193, 97, 23);

        cmbvalidTB.setForeground(new java.awt.Color(0, 0, 0));
        cmbvalidTB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Validasi", "Batal Validasi" }));
        cmbvalidTB.setName("cmbvalidTB"); // NOI18N
        cmbvalidTB.setOpaque(false);
        FormInput.add(cmbvalidTB);
        cmbvalidTB.setBounds(285, 193, 100, 23);

        jLabel106.setForeground(new java.awt.Color(255, 0, 0));
        jLabel106.setText("No. Reg. TB : ");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(200, 193, 83, 23);

        ChkpasienTB.setBackground(new java.awt.Color(255, 255, 250));
        ChkpasienTB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkpasienTB.setForeground(new java.awt.Color(255, 0, 0));
        ChkpasienTB.setText("Pasien TB : ");
        ChkpasienTB.setBorderPainted(true);
        ChkpasienTB.setBorderPaintedFlat(true);
        ChkpasienTB.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkpasienTB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkpasienTB.setName("ChkpasienTB"); // NOI18N
        ChkpasienTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkpasienTBActionPerformed(evt);
            }
        });
        FormInput.add(ChkpasienTB);
        ChkpasienTB.setBounds(200, 165, 83, 23);

        stts_noreg.setForeground(new java.awt.Color(255, 0, 0));
        stts_noreg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        stts_noreg.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        stts_noreg.setName("stts_noreg"); // NOI18N
        FormInput.add(stts_noreg);
        stts_noreg.setBounds(492, 193, 50, 23);

        labelklspel5.setForeground(new java.awt.Color(255, 0, 0));
        labelklspel5.setText("Cara Masuk : ");
        labelklspel5.setName("labelklspel5"); // NOI18N
        FormInput.add(labelklspel5);
        labelklspel5.setBounds(207, 137, 75, 23);

        cmbCrMasuk.setForeground(new java.awt.Color(0, 0, 0));
        cmbCrMasuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rujukan FKTP", "Rujukan FKRTL", "Rujukan Spesialis", "Dari Rawat Jalan", "Dari Rawat Inap", "Dari Rawat Darurat", "Lahir di RS", "Rujukan Panti Jompo", "Rujukan dari RS Jiwa", "Rujukan Fasilitas", "Lain-lain" }));
        cmbCrMasuk.setName("cmbCrMasuk"); // NOI18N
        cmbCrMasuk.setOpaque(false);
        FormInput.add(cmbCrMasuk);
        cmbCrMasuk.setBounds(285, 137, 140, 23);

        labelHRlm1.setForeground(new java.awt.Color(255, 0, 0));
        labelHRlm1.setText("Pembiayaan : ");
        labelHRlm1.setName("labelHRlm1"); // NOI18N
        FormInput.add(labelHRlm1);
        labelHRlm1.setBounds(735, 81, 80, 23);

        cmbPembiayaan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPembiayaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Peserta", "Pemberi Kerja", "Asuransi Tambahan" }));
        cmbPembiayaan.setName("cmbPembiayaan"); // NOI18N
        cmbPembiayaan.setOpaque(false);
        FormInput.add(cmbPembiayaan);
        cmbPembiayaan.setBounds(817, 81, 130, 23);

        labelHRlm3.setForeground(new java.awt.Color(255, 0, 0));
        labelHRlm3.setText("Diastole : ");
        labelHRlm3.setName("labelHRlm3"); // NOI18N
        FormInput.add(labelHRlm3);
        labelHRlm3.setBounds(735, 137, 80, 23);

        labelHRlm2.setForeground(new java.awt.Color(255, 0, 0));
        labelHRlm2.setText("Sistole : ");
        labelHRlm2.setName("labelHRlm2"); // NOI18N
        FormInput.add(labelHRlm2);
        labelHRlm2.setBounds(735, 109, 80, 23);

        sistole.setForeground(new java.awt.Color(0, 0, 0));
        sistole.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        sistole.setName("sistole"); // NOI18N
        sistole.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sistoleKeyPressed(evt);
            }
        });
        FormInput.add(sistole);
        sistole.setBounds(817, 109, 40, 23);

        diastole.setForeground(new java.awt.Color(0, 0, 0));
        diastole.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        diastole.setName("diastole"); // NOI18N
        diastole.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diastoleKeyPressed(evt);
            }
        });
        FormInput.add(diastole);
        diastole.setBounds(817, 137, 40, 23);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel107.setText("mmHg.");
        jLabel107.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(861, 109, 40, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel108.setText("mmHg.");
        jLabel108.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(861, 137, 40, 23);

        labelHRlm4.setForeground(new java.awt.Color(255, 0, 0));
        labelHRlm4.setText("Ventilator : ");
        labelHRlm4.setName("labelHRlm4"); // NOI18N
        FormInput.add(labelHRlm4);
        labelHRlm4.setBounds(735, 165, 80, 23);

        cmbVentilator.setForeground(new java.awt.Color(0, 0, 0));
        cmbVentilator.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        cmbVentilator.setSelectedIndex(1);
        cmbVentilator.setName("cmbVentilator"); // NOI18N
        cmbVentilator.setOpaque(false);
        cmbVentilator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVentilatorActionPerformed(evt);
            }
        });
        FormInput.add(cmbVentilator);
        cmbVentilator.setBounds(817, 165, 60, 23);

        labelHRlm5.setForeground(new java.awt.Color(255, 0, 0));
        labelHRlm5.setText("Intubasi : ");
        labelHRlm5.setName("labelHRlm5"); // NOI18N
        FormInput.add(labelHRlm5);
        labelHRlm5.setBounds(735, 193, 80, 23);

        tglIntubasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-02-2023 09:02:20" }));
        tglIntubasi.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        tglIntubasi.setName("tglIntubasi"); // NOI18N
        tglIntubasi.setOpaque(false);
        tglIntubasi.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(tglIntubasi);
        tglIntubasi.setBounds(817, 193, 140, 23);

        labelHRlm6.setForeground(new java.awt.Color(255, 0, 0));
        labelHRlm6.setText("Ekstubasi : ");
        labelHRlm6.setName("labelHRlm6"); // NOI18N
        FormInput.add(labelHRlm6);
        labelHRlm6.setBounds(960, 193, 65, 23);

        tglEkstubasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-02-2023 09:02:20" }));
        tglEkstubasi.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        tglEkstubasi.setName("tglEkstubasi"); // NOI18N
        tglEkstubasi.setOpaque(false);
        tglEkstubasi.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(tglEkstubasi);
        tglEkstubasi.setBounds(1026, 193, 140, 23);

        labelHRlm7.setForeground(new java.awt.Color(255, 0, 0));
        labelHRlm7.setText("Penggunaan Dializer : ");
        labelHRlm7.setName("labelHRlm7"); // NOI18N
        FormInput.add(labelHRlm7);
        labelHRlm7.setBounds(915, 109, 110, 23);

        cmbDializer.setForeground(new java.awt.Color(0, 0, 0));
        cmbDializer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Multiple Use (reuse)", "Single Use" }));
        cmbDializer.setSelectedIndex(1);
        cmbDializer.setName("cmbDializer"); // NOI18N
        cmbDializer.setOpaque(false);
        FormInput.add(cmbDializer);
        cmbDializer.setBounds(1026, 109, 130, 23);

        labelHRlm8.setForeground(new java.awt.Color(255, 0, 0));
        labelHRlm8.setText("Kantong Darah : ");
        labelHRlm8.setName("labelHRlm8"); // NOI18N
        FormInput.add(labelHRlm8);
        labelHRlm8.setBounds(915, 137, 110, 23);

        TkntngDrh.setForeground(new java.awt.Color(0, 0, 0));
        TkntngDrh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TkntngDrh.setName("TkntngDrh"); // NOI18N
        TkntngDrh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkntngDrhKeyPressed(evt);
            }
        });
        FormInput.add(TkntngDrh);
        TkntngDrh.setBounds(1026, 137, 40, 23);

        TabJaminanJKN.setBackground(new java.awt.Color(250, 255, 245));
        TabJaminanJKN.setForeground(new java.awt.Color(0, 0, 0));
        TabJaminanJKN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabJaminanJKN.setName("TabJaminanJKN"); // NOI18N
        TabJaminanJKN.setOpaque(true);
        TabJaminanJKN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabJaminanJKNMouseClicked(evt);
            }
        });

        internalFrame12.setBorder(null);
        internalFrame12.setName("internalFrame12"); // NOI18N
        internalFrame12.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(560, 208));
        FormInput1.setLayout(null);

        labelTarifRS.setForeground(new java.awt.Color(0, 0, 0));
        labelTarifRS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelTarifRS.setText("0");
        labelTarifRS.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        labelTarifRS.setName("labelTarifRS"); // NOI18N
        FormInput1.add(labelTarifRS);
        labelTarifRS.setBounds(152, 8, 400, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("Rp. ");
        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput1.add(jLabel37);
        jLabel37.setBounds(122, 8, 25, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Tarif Rumah Sakit : ");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput1.add(jLabel36);
        jLabel36.setBounds(0, 8, 120, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Prosedur Non Bedah : ");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput1.add(jLabel38);
        jLabel38.setBounds(0, 36, 120, 23);

        pnb.setForeground(new java.awt.Color(0, 0, 0));
        pnb.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnb.setText("labelpnb");
        pnb.setName("pnb"); // NOI18N
        FormInput1.add(pnb);
        pnb.setBounds(122, 36, 110, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Tenaga Ahli : ");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput1.add(jLabel40);
        jLabel40.setBounds(0, 64, 120, 23);

        ta.setForeground(new java.awt.Color(0, 0, 0));
        ta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ta.setText("labelta");
        ta.setName("ta"); // NOI18N
        FormInput1.add(ta);
        ta.setBounds(122, 64, 110, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Radiologi : ");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput1.add(jLabel41);
        jLabel41.setBounds(0, 92, 120, 23);

        rad.setForeground(new java.awt.Color(0, 0, 0));
        rad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rad.setText("labelrad");
        rad.setName("rad"); // NOI18N
        FormInput1.add(rad);
        rad.setBounds(122, 92, 110, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Rehabilitasi : ");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput1.add(jLabel42);
        jLabel42.setBounds(0, 120, 120, 23);

        reh.setForeground(new java.awt.Color(0, 0, 0));
        reh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        reh.setText("labelreh");
        reh.setName("reh"); // NOI18N
        FormInput1.add(reh);
        reh.setBounds(122, 120, 110, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Obat : ");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput1.add(jLabel45);
        jLabel45.setBounds(0, 148, 120, 23);

        obat.setForeground(new java.awt.Color(0, 0, 0));
        obat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        obat.setText("labelobat");
        obat.setName("obat"); // NOI18N
        FormInput1.add(obat);
        obat.setBounds(122, 148, 110, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Alkes : ");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput1.add(jLabel46);
        jLabel46.setBounds(0, 176, 120, 23);

        alkes.setForeground(new java.awt.Color(0, 0, 0));
        alkes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        alkes.setText("labelalkes");
        alkes.setName("alkes"); // NOI18N
        FormInput1.add(alkes);
        alkes.setBounds(122, 176, 110, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Prosedur Bedah : ");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput1.add(jLabel47);
        jLabel47.setBounds(242, 36, 110, 23);

        pb.setForeground(new java.awt.Color(0, 0, 0));
        pb.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pb.setText("labelpb");
        pb.setName("pb"); // NOI18N
        FormInput1.add(pb);
        pb.setBounds(354, 36, 110, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Keperawatan : ");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput1.add(jLabel48);
        jLabel48.setBounds(242, 64, 110, 23);

        kep.setForeground(new java.awt.Color(0, 0, 0));
        kep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        kep.setText("labelkep");
        kep.setName("kep"); // NOI18N
        FormInput1.add(kep);
        kep.setBounds(354, 64, 110, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Laboratorium : ");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput1.add(jLabel49);
        jLabel49.setBounds(242, 92, 110, 23);

        lab.setForeground(new java.awt.Color(0, 0, 0));
        lab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lab.setText("labellab");
        lab.setName("lab"); // NOI18N
        FormInput1.add(lab);
        lab.setBounds(354, 92, 110, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Kamar / Akomodasi : ");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput1.add(jLabel50);
        jLabel50.setBounds(242, 120, 110, 23);

        kam.setForeground(new java.awt.Color(0, 0, 0));
        kam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        kam.setText("labelkam");
        kam.setName("kam"); // NOI18N
        FormInput1.add(kam);
        kam.setBounds(354, 120, 110, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Obat Kronis : ");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput1.add(jLabel51);
        jLabel51.setBounds(242, 148, 110, 23);

        okr.setForeground(new java.awt.Color(0, 0, 0));
        okr.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        okr.setText("labelokr");
        okr.setName("okr"); // NOI18N
        FormInput1.add(okr);
        okr.setBounds(354, 148, 110, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("BMHP : ");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput1.add(jLabel52);
        jLabel52.setBounds(242, 176, 110, 23);

        bmhp.setForeground(new java.awt.Color(0, 0, 0));
        bmhp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bmhp.setText("labelbmhp");
        bmhp.setName("bmhp"); // NOI18N
        FormInput1.add(bmhp);
        bmhp.setBounds(354, 176, 110, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Konsultasi : ");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput1.add(jLabel53);
        jLabel53.setBounds(472, 36, 100, 23);

        kon.setForeground(new java.awt.Color(0, 0, 0));
        kon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        kon.setText("labelkon");
        kon.setName("kon"); // NOI18N
        FormInput1.add(kon);
        kon.setBounds(575, 36, 110, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Penunjang : ");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput1.add(jLabel54);
        jLabel54.setBounds(472, 64, 100, 23);

        pen.setForeground(new java.awt.Color(0, 0, 0));
        pen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pen.setText("labelpen");
        pen.setName("pen"); // NOI18N
        FormInput1.add(pen);
        pen.setBounds(575, 64, 110, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Pelayanan Darah : ");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput1.add(jLabel55);
        jLabel55.setBounds(472, 92, 100, 23);

        pd.setForeground(new java.awt.Color(0, 0, 0));
        pd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pd.setText("labelpd");
        pd.setName("pd"); // NOI18N
        FormInput1.add(pd);
        pd.setBounds(575, 92, 110, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Rawat Intensif : ");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput1.add(jLabel56);
        jLabel56.setBounds(472, 120, 100, 23);

        ri.setForeground(new java.awt.Color(0, 0, 0));
        ri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ri.setText("labelri");
        ri.setName("ri"); // NOI18N
        FormInput1.add(ri);
        ri.setBounds(575, 120, 110, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Obat Kemoterapi : ");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput1.add(jLabel57);
        jLabel57.setBounds(472, 148, 100, 23);

        oke.setForeground(new java.awt.Color(0, 0, 0));
        oke.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        oke.setText("labeloke");
        oke.setName("oke"); // NOI18N
        FormInput1.add(oke);
        oke.setBounds(575, 148, 110, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Sewa Alat : ");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput1.add(jLabel58);
        jLabel58.setBounds(472, 176, 100, 23);

        sa.setForeground(new java.awt.Color(0, 0, 0));
        sa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sa.setText("labelsa");
        sa.setName("sa"); // NOI18N
        FormInput1.add(sa);
        sa.setBounds(575, 176, 110, 23);

        internalFrame12.add(FormInput1, java.awt.BorderLayout.CENTER);

        TabJaminanJKN.addTab("Tarif Real Cost RS", internalFrame12);

        internalFrame13.setBorder(null);
        internalFrame13.setName("internalFrame13"); // NOI18N
        internalFrame13.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput3.setForeground(new java.awt.Color(255, 0, 0));
        FormInput3.setName("FormInput3"); // NOI18N
        FormInput3.setPreferredSize(new java.awt.Dimension(560, 208));
        FormInput3.setLayout(null);

        ChkApgar.setBackground(new java.awt.Color(255, 255, 250));
        ChkApgar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkApgar.setForeground(new java.awt.Color(0, 0, 0));
        ChkApgar.setText("Apgar Score >>");
        ChkApgar.setBorderPainted(true);
        ChkApgar.setBorderPaintedFlat(true);
        ChkApgar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkApgar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkApgar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkApgar.setName("ChkApgar"); // NOI18N
        ChkApgar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkApgarActionPerformed(evt);
            }
        });
        FormInput3.add(ChkApgar);
        ChkApgar.setBounds(0, 8, 120, 23);

        labelHRlm16.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm16.setText("Menit 1 : ");
        labelHRlm16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelHRlm16.setName("labelHRlm16"); // NOI18N
        FormInput3.add(labelHRlm16);
        labelHRlm16.setBounds(130, 8, 65, 23);

        labelHRlm11.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm11.setText("Appearance : ");
        labelHRlm11.setName("labelHRlm11"); // NOI18N
        FormInput3.add(labelHRlm11);
        labelHRlm11.setBounds(120, 36, 75, 23);

        labelHRlm12.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm12.setText("Pulse : ");
        labelHRlm12.setName("labelHRlm12"); // NOI18N
        FormInput3.add(labelHRlm12);
        labelHRlm12.setBounds(120, 64, 75, 23);

        labelHRlm13.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm13.setText("Grimace : ");
        labelHRlm13.setName("labelHRlm13"); // NOI18N
        FormInput3.add(labelHRlm13);
        labelHRlm13.setBounds(120, 92, 75, 23);

        labelHRlm14.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm14.setText("Activity : ");
        labelHRlm14.setName("labelHRlm14"); // NOI18N
        FormInput3.add(labelHRlm14);
        labelHRlm14.setBounds(120, 120, 75, 23);

        labelHRlm15.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm15.setText("Respiration : ");
        labelHRlm15.setName("labelHRlm15"); // NOI18N
        FormInput3.add(labelHRlm15);
        labelHRlm15.setBounds(120, 148, 75, 23);

        cmbMnt1App.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1App.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0", "1", "2" }));
        cmbMnt1App.setName("cmbMnt1App"); // NOI18N
        cmbMnt1App.setOpaque(false);
        FormInput3.add(cmbMnt1App);
        cmbMnt1App.setBounds(197, 36, 40, 23);

        cmbMnt1Pul.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1Pul.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0", "1", "2" }));
        cmbMnt1Pul.setName("cmbMnt1Pul"); // NOI18N
        cmbMnt1Pul.setOpaque(false);
        FormInput3.add(cmbMnt1Pul);
        cmbMnt1Pul.setBounds(197, 64, 40, 23);

        cmbMnt1Gri.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1Gri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0", "1", "2" }));
        cmbMnt1Gri.setName("cmbMnt1Gri"); // NOI18N
        cmbMnt1Gri.setOpaque(false);
        FormInput3.add(cmbMnt1Gri);
        cmbMnt1Gri.setBounds(197, 92, 40, 23);

        cmbMnt1Act.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1Act.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0", "1", "2" }));
        cmbMnt1Act.setName("cmbMnt1Act"); // NOI18N
        cmbMnt1Act.setOpaque(false);
        FormInput3.add(cmbMnt1Act);
        cmbMnt1Act.setBounds(197, 120, 40, 23);

        cmbMnt1Res.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1Res.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0", "1", "2" }));
        cmbMnt1Res.setName("cmbMnt1Res"); // NOI18N
        cmbMnt1Res.setOpaque(false);
        FormInput3.add(cmbMnt1Res);
        cmbMnt1Res.setBounds(197, 148, 40, 23);

        labelHRlm23.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm23.setText("Appearance : ");
        labelHRlm23.setName("labelHRlm23"); // NOI18N
        FormInput3.add(labelHRlm23);
        labelHRlm23.setBounds(255, 36, 75, 23);

        labelHRlm24.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm24.setText("Pulse : ");
        labelHRlm24.setName("labelHRlm24"); // NOI18N
        FormInput3.add(labelHRlm24);
        labelHRlm24.setBounds(255, 64, 75, 23);

        labelHRlm25.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm25.setText("Grimace : ");
        labelHRlm25.setName("labelHRlm25"); // NOI18N
        FormInput3.add(labelHRlm25);
        labelHRlm25.setBounds(255, 92, 75, 23);

        labelHRlm26.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm26.setText("Activity : ");
        labelHRlm26.setName("labelHRlm26"); // NOI18N
        FormInput3.add(labelHRlm26);
        labelHRlm26.setBounds(255, 120, 75, 23);

        labelHRlm27.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm27.setText("Respiration : ");
        labelHRlm27.setName("labelHRlm27"); // NOI18N
        FormInput3.add(labelHRlm27);
        labelHRlm27.setBounds(255, 148, 75, 23);

        cmbMnt5App.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt5App.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0", "1", "2" }));
        cmbMnt5App.setName("cmbMnt5App"); // NOI18N
        cmbMnt5App.setOpaque(false);
        FormInput3.add(cmbMnt5App);
        cmbMnt5App.setBounds(332, 36, 40, 23);

        cmbMnt5Pul.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt5Pul.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0", "1", "2" }));
        cmbMnt5Pul.setName("cmbMnt5Pul"); // NOI18N
        cmbMnt5Pul.setOpaque(false);
        FormInput3.add(cmbMnt5Pul);
        cmbMnt5Pul.setBounds(332, 64, 40, 23);

        cmbMnt5Gri.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt5Gri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0", "1", "2" }));
        cmbMnt5Gri.setName("cmbMnt5Gri"); // NOI18N
        cmbMnt5Gri.setOpaque(false);
        FormInput3.add(cmbMnt5Gri);
        cmbMnt5Gri.setBounds(332, 92, 40, 23);

        cmbMnt5Act.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt5Act.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0", "1", "2" }));
        cmbMnt5Act.setName("cmbMnt5Act"); // NOI18N
        cmbMnt5Act.setOpaque(false);
        FormInput3.add(cmbMnt5Act);
        cmbMnt5Act.setBounds(332, 120, 40, 23);

        cmbMnt5Res.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt5Res.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0", "1", "2" }));
        cmbMnt5Res.setName("cmbMnt5Res"); // NOI18N
        cmbMnt5Res.setOpaque(false);
        FormInput3.add(cmbMnt5Res);
        cmbMnt5Res.setBounds(332, 148, 40, 23);

        labelHRlm28.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm28.setText("Menit 5 : ");
        labelHRlm28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelHRlm28.setName("labelHRlm28"); // NOI18N
        FormInput3.add(labelHRlm28);
        labelHRlm28.setBounds(265, 8, 65, 23);

        internalFrame13.add(FormInput3, java.awt.BorderLayout.CENTER);

        TabJaminanJKN.addTab("Apgar Score (Bayi Baru Lahir)", internalFrame13);

        internalFrame14.setBorder(null);
        internalFrame14.setName("internalFrame14"); // NOI18N
        internalFrame14.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput4.setName("FormInput4"); // NOI18N
        FormInput4.setPreferredSize(new java.awt.Dimension(560, 208));
        FormInput4.setLayout(null);

        labelHRlm17.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm17.setText("Usia Kehamilan : ");
        labelHRlm17.setName("labelHRlm17"); // NOI18N
        FormInput4.add(labelHRlm17);
        labelHRlm17.setBounds(0, 8, 112, 23);

        labelHRlm18.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm18.setText("Gravida : ");
        labelHRlm18.setName("labelHRlm18"); // NOI18N
        FormInput4.add(labelHRlm18);
        labelHRlm18.setBounds(0, 36, 112, 23);

        labelHRlm19.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm19.setText("Partus : ");
        labelHRlm19.setName("labelHRlm19"); // NOI18N
        FormInput4.add(labelHRlm19);
        labelHRlm19.setBounds(0, 64, 112, 23);

        labelHRlm20.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm20.setText("Abortus : ");
        labelHRlm20.setName("labelHRlm20"); // NOI18N
        FormInput4.add(labelHRlm20);
        labelHRlm20.setBounds(0, 92, 112, 23);

        labelHRlm21.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm21.setText("Onset Kontraksi : ");
        labelHRlm21.setName("labelHRlm21"); // NOI18N
        FormInput4.add(labelHRlm21);
        labelHRlm21.setBounds(0, 120, 112, 23);

        TUsiaHml.setForeground(new java.awt.Color(0, 0, 0));
        TUsiaHml.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TUsiaHml.setName("TUsiaHml"); // NOI18N
        TUsiaHml.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TUsiaHmlKeyPressed(evt);
            }
        });
        FormInput4.add(TUsiaHml);
        TUsiaHml.setBounds(115, 8, 40, 23);

        labelHRinten2.setForeground(new java.awt.Color(0, 0, 0));
        labelHRinten2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelHRinten2.setText("minggu");
        labelHRinten2.setName("labelHRinten2"); // NOI18N
        FormInput4.add(labelHRinten2);
        labelHRinten2.setBounds(160, 8, 50, 23);

        Tgravida.setForeground(new java.awt.Color(0, 0, 0));
        Tgravida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tgravida.setName("Tgravida"); // NOI18N
        Tgravida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgravidaKeyPressed(evt);
            }
        });
        FormInput4.add(Tgravida);
        Tgravida.setBounds(115, 36, 40, 23);

        Tpartus.setForeground(new java.awt.Color(0, 0, 0));
        Tpartus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tpartus.setName("Tpartus"); // NOI18N
        Tpartus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpartusKeyPressed(evt);
            }
        });
        FormInput4.add(Tpartus);
        Tpartus.setBounds(115, 64, 40, 23);

        Tabortus.setForeground(new java.awt.Color(0, 0, 0));
        Tabortus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tabortus.setName("Tabortus"); // NOI18N
        Tabortus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TabortusKeyPressed(evt);
            }
        });
        FormInput4.add(Tabortus);
        Tabortus.setBounds(115, 92, 40, 23);

        cmbOnset.setForeground(new java.awt.Color(0, 0, 0));
        cmbOnset.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Timbul Spontan", "Dengan Induksi", "SC Tanpa Kontraksi/Induksi" }));
        cmbOnset.setName("cmbOnset"); // NOI18N
        cmbOnset.setOpaque(false);
        FormInput4.add(cmbOnset);
        cmbOnset.setBounds(115, 120, 170, 23);

        labelHRlm22.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm22.setText("Waktu Kelahiran : ");
        labelHRlm22.setName("labelHRlm22"); // NOI18N
        FormInput4.add(labelHRlm22);
        labelHRlm22.setBounds(290, 8, 140, 23);

        wktkelahiran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-02-2023 09:02:20" }));
        wktkelahiran.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        wktkelahiran.setName("wktkelahiran"); // NOI18N
        wktkelahiran.setOpaque(false);
        wktkelahiran.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput4.add(wktkelahiran);
        wktkelahiran.setBounds(433, 8, 140, 23);

        labelHRlm29.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm29.setText("Cara Kelahiran : ");
        labelHRlm29.setName("labelHRlm29"); // NOI18N
        FormInput4.add(labelHRlm29);
        labelHRlm29.setBounds(290, 36, 140, 23);

        cmbCaraLhr.setForeground(new java.awt.Color(0, 0, 0));
        cmbCaraLhr.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Vaginal", "SC" }));
        cmbCaraLhr.setName("cmbCaraLhr"); // NOI18N
        cmbCaraLhr.setOpaque(false);
        FormInput4.add(cmbCaraLhr);
        cmbCaraLhr.setBounds(433, 36, 70, 23);

        labelHRlm30.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm30.setText("Letak / Presentasi : ");
        labelHRlm30.setName("labelHRlm30"); // NOI18N
        FormInput4.add(labelHRlm30);
        labelHRlm30.setBounds(290, 64, 140, 23);

        cmbLtkJanin.setForeground(new java.awt.Color(0, 0, 0));
        cmbLtkJanin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Kepala", "Sungsang", "Lintang" }));
        cmbLtkJanin.setName("cmbLtkJanin"); // NOI18N
        cmbLtkJanin.setOpaque(false);
        FormInput4.add(cmbLtkJanin);
        cmbLtkJanin.setBounds(433, 64, 80, 23);

        labelHRlm31.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm31.setText("Kondisi : ");
        labelHRlm31.setName("labelHRlm31"); // NOI18N
        FormInput4.add(labelHRlm31);
        labelHRlm31.setBounds(290, 92, 140, 23);

        cmbKondisi.setForeground(new java.awt.Color(0, 0, 0));
        cmbKondisi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Lahir Hidup", "Lahir Meninggal" }));
        cmbKondisi.setName("cmbKondisi"); // NOI18N
        cmbKondisi.setOpaque(false);
        FormInput4.add(cmbKondisi);
        cmbKondisi.setBounds(433, 92, 110, 23);

        labelHRlm32.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm32.setText("Dengan Bantuan Manual : ");
        labelHRlm32.setName("labelHRlm32"); // NOI18N
        FormInput4.add(labelHRlm32);
        labelHRlm32.setBounds(290, 120, 140, 23);

        cmbBantuan.setForeground(new java.awt.Color(0, 0, 0));
        cmbBantuan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak", "Ya" }));
        cmbBantuan.setName("cmbBantuan"); // NOI18N
        cmbBantuan.setOpaque(false);
        FormInput4.add(cmbBantuan);
        cmbBantuan.setBounds(433, 120, 60, 23);

        labelHRlm33.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm33.setText("Penggunaan Forcep : ");
        labelHRlm33.setName("labelHRlm33"); // NOI18N
        FormInput4.add(labelHRlm33);
        labelHRlm33.setBounds(290, 148, 140, 23);

        cmbForcep.setForeground(new java.awt.Color(0, 0, 0));
        cmbForcep.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak", "Ya" }));
        cmbForcep.setName("cmbForcep"); // NOI18N
        cmbForcep.setOpaque(false);
        FormInput4.add(cmbForcep);
        cmbForcep.setBounds(433, 148, 60, 23);

        labelHRlm34.setForeground(new java.awt.Color(0, 0, 0));
        labelHRlm34.setText("Penggunaan Vacum : ");
        labelHRlm34.setName("labelHRlm34"); // NOI18N
        FormInput4.add(labelHRlm34);
        labelHRlm34.setBounds(290, 176, 140, 23);

        cmbVacum.setForeground(new java.awt.Color(0, 0, 0));
        cmbVacum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak", "Ya" }));
        cmbVacum.setName("cmbVacum"); // NOI18N
        cmbVacum.setOpaque(false);
        FormInput4.add(cmbVacum);
        cmbVacum.setBounds(433, 176, 60, 23);

        BtnBaru.setForeground(new java.awt.Color(0, 0, 0));
        BtnBaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBaru.setMnemonic('B');
        BtnBaru.setText("Baru");
        BtnBaru.setToolTipText("Alt+B");
        BtnBaru.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnBaru.setName("BtnBaru"); // NOI18N
        BtnBaru.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBaruActionPerformed(evt);
            }
        });
        FormInput4.add(BtnBaru);
        BtnBaru.setBounds(540, 142, 80, 26);

        BtnTambah.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambah.setMnemonic('T');
        BtnTambah.setText("Tambahkan Data");
        BtnTambah.setToolTipText("Alt+T");
        BtnTambah.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnTambah.setName("BtnTambah"); // NOI18N
        BtnTambah.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahActionPerformed(evt);
            }
        });
        FormInput4.add(BtnTambah);
        BtnTambah.setBounds(540, 176, 140, 26);

        internalFrame14.add(FormInput4, java.awt.BorderLayout.CENTER);

        TabJaminanJKN.addTab("Kasus Persalinan", internalFrame14);

        internalFrame15.setBorder(null);
        internalFrame15.setName("internalFrame15"); // NOI18N
        internalFrame15.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll12.setName("Scroll12"); // NOI18N
        Scroll12.setOpaque(true);

        tbPersalinan.setToolTipText("Silahkan klik kanan untuk menghapus data");
        tbPersalinan.setComponentPopupMenu(Popup2);
        tbPersalinan.setName("tbPersalinan"); // NOI18N
        Scroll12.setViewportView(tbPersalinan);

        internalFrame15.add(Scroll12, java.awt.BorderLayout.CENTER);

        TabJaminanJKN.addTab("Delivery Persalinan", internalFrame15);

        FormInput.add(TabJaminanJKN);
        TabJaminanJKN.setBounds(45, 280, 700, 240);
        TabJaminanJKN.getAccessibleContext().setAccessibleName("");
        TabJaminanJKN.getAccessibleContext().setAccessibleDescription("");

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Jaminan JKN", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setPreferredSize(new java.awt.Dimension(102, 1200));
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput1.setName("scrollInput1"); // NOI18N
        scrollInput1.setPreferredSize(new java.awt.Dimension(102, 1200));

        FormInput2.setBackground(new java.awt.Color(255, 255, 255));
        FormInput2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "::[ Data Klaim ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 0, 0))); // NOI18N
        FormInput2.setName("FormInput2"); // NOI18N
        FormInput2.setPreferredSize(new java.awt.Dimension(100, 1278));
        FormInput2.setLayout(null);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Jenis Identitas : ");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput2.add(jLabel5);
        jLabel5.setBounds(3, 53, 120, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Jenis Rawat : ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput2.add(jLabel11);
        jLabel11.setBounds(3, 81, 120, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("No. Klaim : ");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput2.add(jLabel63);
        jLabel63.setBounds(587, 53, 100, 23);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("No. ID. : ");
        jLabel7.setName("jLabel7"); // NOI18N
        FormInput2.add(jLabel7);
        jLabel7.setBounds(346, 53, 60, 23);

        labelklsHAK1.setForeground(new java.awt.Color(0, 0, 0));
        labelklsHAK1.setText("Kelas Hak : ");
        labelklsHAK1.setName("labelklsHAK1"); // NOI18N
        FormInput2.add(labelklsHAK1);
        labelklsHAK1.setBounds(595, 81, 60, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Tgl.  Rawat : ");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput2.add(jLabel16);
        jLabel16.setBounds(3, 109, 120, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Masuk : ");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput2.add(jLabel17);
        jLabel17.setBounds(125, 109, 40, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Pulang : ");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput2.add(jLabel18);
        jLabel18.setBounds(305, 109, 60, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Umur : ");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput2.add(jLabel19);
        jLabel19.setBounds(605, 109, 50, 23);

        labelklspel1.setForeground(new java.awt.Color(0, 0, 0));
        labelklspel1.setText("Episode Rg. Rawat : ");
        labelklspel1.setName("labelklspel1"); // NOI18N
        FormInput2.add(labelklspel1);
        labelklspel1.setBounds(545, 137, 110, 23);

        labelLM1.setForeground(new java.awt.Color(0, 0, 0));
        labelLM1.setText("Co-Insidens : ");
        labelLM1.setName("labelLM1"); // NOI18N
        FormInput2.add(labelLM1);
        labelLM1.setBounds(3, 137, 120, 23);

        labelRI1.setForeground(new java.awt.Color(0, 0, 0));
        labelRI1.setText("Status COVID-19 : ");
        labelRI1.setName("labelRI1"); // NOI18N
        FormInput2.add(labelRI1);
        labelRI1.setBounds(535, 277, 120, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("LOS : ");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput2.add(jLabel35);
        jLabel35.setBounds(3, 165, 120, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel64.setText("hari");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput2.add(jLabel64);
        jLabel64.setBounds(160, 165, 40, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText("gram");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput2.add(jLabel66);
        jLabel66.setBounds(180, 277, 30, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("ADL Score : ");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput2.add(jLabel67);
        jLabel67.setBounds(3, 193, 120, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel68.setText("Sub Acute : ");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput2.add(jLabel68);
        jLabel68.setBounds(125, 193, 60, 23);

        subakut1.setForeground(new java.awt.Color(0, 0, 0));
        subakut1.setName("subakut1"); // NOI18N
        subakut1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                subakut1KeyPressed(evt);
            }
        });
        FormInput2.add(subakut1);
        subakut1.setBounds(185, 193, 40, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Chronic : ");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput2.add(jLabel69);
        jLabel69.setBounds(225, 193, 55, 23);

        kronik1.setForeground(new java.awt.Color(0, 0, 0));
        kronik1.setName("kronik1"); // NOI18N
        kronik1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kronik1KeyPressed(evt);
            }
        });
        FormInput2.add(kronik1);
        kronik1.setBounds(282, 193, 40, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("DPJP : ");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput2.add(jLabel71);
        jLabel71.setBounds(3, 221, 120, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Tarif Rumah Sakit : ");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput2.add(jLabel72);
        jLabel72.setBounds(3, 340, 120, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel73.setText("Rp. ");
        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput2.add(jLabel73);
        jLabel73.setBounds(125, 340, 25, 23);

        labelTarifRS1.setForeground(new java.awt.Color(0, 0, 0));
        labelTarifRS1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelTarifRS1.setText("0");
        labelTarifRS1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        labelTarifRS1.setName("labelTarifRS1"); // NOI18N
        FormInput2.add(labelTarifRS1);
        labelTarifRS1.setBounds(155, 340, 400, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Prosedur Non Bedah : ");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput2.add(jLabel74);
        jLabel74.setBounds(3, 368, 120, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Tenaga Ahli : ");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput2.add(jLabel75);
        jLabel75.setBounds(3, 396, 120, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Radiologi : ");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput2.add(jLabel76);
        jLabel76.setBounds(3, 424, 120, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Rehabilitasi : ");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput2.add(jLabel77);
        jLabel77.setBounds(3, 452, 120, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Obat : ");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput2.add(jLabel78);
        jLabel78.setBounds(3, 480, 120, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Alkes : ");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput2.add(jLabel79);
        jLabel79.setBounds(3, 508, 120, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Prosedur Bedah : ");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput2.add(jLabel80);
        jLabel80.setBounds(240, 368, 120, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Keperawatan : ");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput2.add(jLabel81);
        jLabel81.setBounds(240, 396, 120, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Laboratorium : ");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput2.add(jLabel82);
        jLabel82.setBounds(240, 424, 120, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Kamar / Akomodasi : ");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput2.add(jLabel83);
        jLabel83.setBounds(240, 452, 120, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Obat Kronis : ");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput2.add(jLabel84);
        jLabel84.setBounds(240, 480, 120, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("BMHP : ");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput2.add(jLabel85);
        jLabel85.setBounds(240, 508, 120, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Konsultasi : ");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput2.add(jLabel86);
        jLabel86.setBounds(475, 368, 120, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Penunjang : ");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput2.add(jLabel87);
        jLabel87.setBounds(475, 396, 120, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("Pelayanan Darah : ");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput2.add(jLabel88);
        jLabel88.setBounds(475, 424, 120, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Rawat Intensif : ");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput2.add(jLabel89);
        jLabel89.setBounds(475, 452, 120, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Obat Kemoterapi : ");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput2.add(jLabel90);
        jLabel90.setBounds(475, 480, 120, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Sewa Alat : ");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput2.add(jLabel91);
        jLabel91.setBounds(475, 508, 120, 23);

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput2.add(jSeparator3);
        jSeparator3.setBounds(0, 334, 1260, 5);

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput2.add(jSeparator4);
        jSeparator4.setBounds(0, 540, 715, 5);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel92.setText("Diagnosa (ICD-10) : ");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput2.add(jLabel92);
        jLabel92.setBounds(45, 730, 140, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel93.setText("Prosedur (ICD-9-CM) : ");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput2.add(jLabel93);
        jLabel93.setBounds(690, 730, 120, 23);

        labelLOS1.setForeground(new java.awt.Color(0, 0, 0));
        labelLOS1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLOS1.setText("1");
        labelLOS1.setName("labelLOS1"); // NOI18N
        FormInput2.add(labelLOS1);
        labelLOS1.setBounds(125, 165, 30, 23);

        BtnGruper2.setForeground(new java.awt.Color(0, 0, 0));
        BtnGruper2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/SeratusRibu.png"))); // NOI18N
        BtnGruper2.setMnemonic('G');
        BtnGruper2.setText("Simpan & Grouper");
        BtnGruper2.setToolTipText("Alt+G");
        BtnGruper2.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnGruper2.setName("BtnGruper2"); // NOI18N
        BtnGruper2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnGruper2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGruper2ActionPerformed(evt);
            }
        });
        FormInput2.add(BtnGruper2);
        BtnGruper2.setBounds(725, 694, 160, 30);

        separatHasil1.setForeground(new java.awt.Color(0, 0, 0));
        separatHasil1.setName("separatHasil1"); // NOI18N
        FormInput2.add(separatHasil1);
        separatHasil1.setBounds(0, 903, 1260, 5);

        labelhasilG2.setForeground(new java.awt.Color(0, 0, 204));
        labelhasilG2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelhasilG2.setText("Hasil Grouper : ");
        labelhasilG2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelhasilG2.setName("labelhasilG2"); // NOI18N
        FormInput2.add(labelhasilG2);
        labelhasilG2.setBounds(45, 907, 576, 23);

        ScrollHasil3.setName("ScrollHasil3"); // NOI18N
        ScrollHasil3.setOpaque(true);

        tbHasil3.setToolTipText("");
        tbHasil3.setName("tbHasil3"); // NOI18N
        ScrollHasil3.setViewportView(tbHasil3);

        FormInput2.add(ScrollHasil3);
        ScrollHasil3.setBounds(45, 931, 576, 170);

        ScrollHasil4.setName("ScrollHasil4"); // NOI18N
        ScrollHasil4.setOpaque(true);

        tbHasil4.setToolTipText("");
        tbHasil4.setName("tbHasil4"); // NOI18N
        ScrollHasil4.setViewportView(tbHasil4);

        FormInput2.add(ScrollHasil4);
        ScrollHasil4.setBounds(45, 1127, 1155, 97);

        labelhak1.setForeground(new java.awt.Color(0, 0, 0));
        labelhak1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelhak1.setText("labelhak");
        labelhak1.setName("labelhak1"); // NOI18N
        FormInput2.add(labelhak1);
        labelhak1.setBounds(655, 81, 70, 23);

        umur1.setForeground(new java.awt.Color(0, 0, 0));
        umur1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        umur1.setText("umur");
        umur1.setName("umur1"); // NOI18N
        FormInput2.add(umur1);
        umur1.setBounds(655, 109, 100, 23);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Suspek", "Probabel", "Terkonfirmasi", "ODP", "PDP" }));
        cmbStatus.setSelectedIndex(2);
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.setOpaque(false);
        cmbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusActionPerformed(evt);
            }
        });
        FormInput2.add(cmbStatus);
        cmbStatus.setBounds(655, 277, 110, 23);

        labelrwt1.setForeground(new java.awt.Color(0, 0, 0));
        labelrwt1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelrwt1.setText("labelrwt");
        labelrwt1.setName("labelrwt1"); // NOI18N
        FormInput2.add(labelrwt1);
        labelrwt1.setBounds(125, 81, 50, 23);

        dpjp1.setForeground(new java.awt.Color(0, 0, 0));
        dpjp1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dpjp1.setText("labeldpjp");
        dpjp1.setName("dpjp1"); // NOI18N
        FormInput2.add(dpjp1);
        dpjp1.setBounds(125, 221, 345, 23);

        wktMasuk1.setForeground(new java.awt.Color(0, 0, 0));
        wktMasuk1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        wktMasuk1.setText("labelwktMasuk");
        wktMasuk1.setName("wktMasuk1"); // NOI18N
        FormInput2.add(wktMasuk1);
        wktMasuk1.setBounds(167, 109, 130, 23);

        wktPulang1.setForeground(new java.awt.Color(0, 0, 0));
        wktPulang1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        wktPulang1.setText("labelwktPulang");
        wktPulang1.setName("wktPulang1"); // NOI18N
        FormInput2.add(wktPulang1);
        wktPulang1.setBounds(367, 109, 170, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("No. RM : ");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput2.add(jLabel8);
        jLabel8.setBounds(3, 25, 120, 23);

        norm1.setForeground(new java.awt.Color(0, 0, 0));
        norm1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        norm1.setText("labelnorm");
        norm1.setName("norm1"); // NOI18N
        FormInput2.add(norm1);
        norm1.setBounds(125, 25, 70, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Nama Pasien : ");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput2.add(jLabel21);
        jLabel21.setBounds(200, 25, 90, 23);

        nmPasien1.setForeground(new java.awt.Color(0, 0, 0));
        nmPasien1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nmPasien1.setText("labelnmPasien");
        nmPasien1.setName("nmPasien1"); // NOI18N
        FormInput2.add(nmPasien1);
        nmPasien1.setBounds(292, 25, 280, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Jns. Kelamin : ");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput2.add(jLabel94);
        jLabel94.setBounds(574, 25, 80, 23);

        jk1.setForeground(new java.awt.Color(0, 0, 0));
        jk1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jk1.setText("labeljk");
        jk1.setName("jk1"); // NOI18N
        FormInput2.add(jk1);
        jk1.setBounds(655, 25, 90, 23);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Tgl. Lahir : ");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput2.add(jLabel95);
        jLabel95.setBounds(745, 25, 70, 23);

        tglLhr1.setForeground(new java.awt.Color(0, 0, 0));
        tglLhr1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tglLhr1.setText("labeltglLhr");
        tglLhr1.setName("tglLhr1"); // NOI18N
        FormInput2.add(tglLhr1);
        tglLhr1.setBounds(817, 25, 110, 23);

        pnb7.setForeground(new java.awt.Color(0, 0, 0));
        pnb7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnb7.setText("labelpnb");
        pnb7.setName("pnb7"); // NOI18N
        FormInput2.add(pnb7);
        pnb7.setBounds(125, 368, 120, 23);

        ta1.setForeground(new java.awt.Color(0, 0, 0));
        ta1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ta1.setText("labelta");
        ta1.setName("ta1"); // NOI18N
        FormInput2.add(ta1);
        ta1.setBounds(125, 396, 120, 23);

        rad1.setForeground(new java.awt.Color(0, 0, 0));
        rad1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rad1.setText("labelrad");
        rad1.setName("rad1"); // NOI18N
        FormInput2.add(rad1);
        rad1.setBounds(125, 424, 120, 23);

        reh7.setForeground(new java.awt.Color(0, 0, 0));
        reh7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        reh7.setText("labelreh");
        reh7.setName("reh7"); // NOI18N
        FormInput2.add(reh7);
        reh7.setBounds(125, 452, 120, 23);

        obat1.setForeground(new java.awt.Color(0, 0, 0));
        obat1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        obat1.setText("labelobat");
        obat1.setName("obat1"); // NOI18N
        FormInput2.add(obat1);
        obat1.setBounds(125, 480, 120, 23);

        alkes1.setForeground(new java.awt.Color(0, 0, 0));
        alkes1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        alkes1.setText("labelalkes");
        alkes1.setName("alkes1"); // NOI18N
        FormInput2.add(alkes1);
        alkes1.setBounds(125, 508, 120, 23);

        pb1.setForeground(new java.awt.Color(0, 0, 0));
        pb1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pb1.setText("labelpb");
        pb1.setName("pb1"); // NOI18N
        FormInput2.add(pb1);
        pb1.setBounds(362, 368, 120, 23);

        kep7.setForeground(new java.awt.Color(0, 0, 0));
        kep7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        kep7.setText("labelkep");
        kep7.setName("kep7"); // NOI18N
        FormInput2.add(kep7);
        kep7.setBounds(362, 396, 120, 23);

        lab1.setForeground(new java.awt.Color(0, 0, 0));
        lab1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lab1.setText("labellab");
        lab1.setName("lab1"); // NOI18N
        FormInput2.add(lab1);
        lab1.setBounds(362, 424, 120, 23);

        kam1.setForeground(new java.awt.Color(0, 0, 0));
        kam1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        kam1.setText("labelkam");
        kam1.setName("kam1"); // NOI18N
        FormInput2.add(kam1);
        kam1.setBounds(362, 452, 120, 23);

        okr1.setForeground(new java.awt.Color(0, 0, 0));
        okr1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        okr1.setText("labelokr");
        okr1.setName("okr1"); // NOI18N
        FormInput2.add(okr1);
        okr1.setBounds(362, 480, 120, 23);

        bmhp1.setForeground(new java.awt.Color(0, 0, 0));
        bmhp1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bmhp1.setText("labelbmhp");
        bmhp1.setName("bmhp1"); // NOI18N
        FormInput2.add(bmhp1);
        bmhp1.setBounds(362, 508, 120, 23);

        kon7.setForeground(new java.awt.Color(0, 0, 0));
        kon7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        kon7.setText("labelkon");
        kon7.setName("kon7"); // NOI18N
        FormInput2.add(kon7);
        kon7.setBounds(596, 368, 120, 23);

        pen1.setForeground(new java.awt.Color(0, 0, 0));
        pen1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pen1.setText("labelpen");
        pen1.setName("pen1"); // NOI18N
        FormInput2.add(pen1);
        pen1.setBounds(596, 396, 120, 23);

        pd1.setForeground(new java.awt.Color(0, 0, 0));
        pd1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pd1.setText("labelpd");
        pd1.setName("pd1"); // NOI18N
        FormInput2.add(pd1);
        pd1.setBounds(596, 424, 120, 23);

        ri1.setForeground(new java.awt.Color(0, 0, 0));
        ri1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ri1.setText("labelri");
        ri1.setName("ri1"); // NOI18N
        FormInput2.add(ri1);
        ri1.setBounds(596, 452, 120, 23);

        oke1.setForeground(new java.awt.Color(0, 0, 0));
        oke1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        oke1.setText("labeloke");
        oke1.setName("oke1"); // NOI18N
        FormInput2.add(oke1);
        oke1.setBounds(596, 480, 120, 23);

        sa1.setForeground(new java.awt.Color(0, 0, 0));
        sa1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sa1.setText("labelsa");
        sa1.setName("sa1"); // NOI18N
        FormInput2.add(sa1);
        sa1.setBounds(596, 508, 120, 23);

        labelhasilG3.setForeground(new java.awt.Color(0, 0, 0));
        labelhasilG3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelhasilG3.setText("Special CMG Option : ");
        labelhasilG3.setName("labelhasilG3"); // NOI18N
        FormInput2.add(labelhasilG3);
        labelhasilG3.setBounds(45, 1102, 120, 23);

        BtnGruperStage1.setForeground(new java.awt.Color(0, 0, 0));
        BtnGruperStage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/SeratusRibu.png"))); // NOI18N
        BtnGruperStage1.setMnemonic('G');
        BtnGruperStage1.setText("Grouper Stage 2");
        BtnGruperStage1.setToolTipText("Alt+G");
        BtnGruperStage1.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnGruperStage1.setName("BtnGruperStage1"); // NOI18N
        BtnGruperStage1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnGruperStage1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGruperStage1ActionPerformed(evt);
            }
        });
        FormInput2.add(BtnGruperStage1);
        BtnGruperStage1.setBounds(1055, 1229, 140, 30);

        BtnRefres1.setForeground(new java.awt.Color(0, 0, 0));
        BtnRefres1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnRefres1.setMnemonic('R');
        BtnRefres1.setText("Refresh Diagn. & Pros.");
        BtnRefres1.setToolTipText("Alt+R");
        BtnRefres1.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnRefres1.setName("BtnRefres1"); // NOI18N
        BtnRefres1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnRefres1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRefres1ActionPerformed(evt);
            }
        });
        BtnRefres1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnRefres1KeyPressed(evt);
            }
        });
        FormInput2.add(BtnRefres1);
        BtnRefres1.setBounds(900, 694, 180, 30);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Nama Petugas : ");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput2.add(jLabel96);
        jLabel96.setBounds(875, 53, 90, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("NIK Petugas : ");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput2.add(jLabel97);
        jLabel97.setBounds(875, 81, 90, 23);

        nmPetugas1.setForeground(new java.awt.Color(0, 0, 0));
        nmPetugas1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nmPetugas1.setText("labelnmPetugas");
        nmPetugas1.setName("nmPetugas1"); // NOI18N
        FormInput2.add(nmPetugas1);
        nmPetugas1.setBounds(967, 53, 280, 23);

        nikPetugas1.setForeground(new java.awt.Color(0, 0, 0));
        nikPetugas1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nikPetugas1.setText("labelnikPetugas");
        nikPetugas1.setName("nikPetugas1"); // NOI18N
        FormInput2.add(nikPetugas1);
        nikPetugas1.setBounds(967, 81, 300, 23);

        noID.setForeground(new java.awt.Color(0, 0, 0));
        noID.setName("noID"); // NOI18N
        noID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noIDKeyPressed(evt);
            }
        });
        FormInput2.add(noID);
        noID.setBounds(407, 53, 180, 23);

        cmbID.setForeground(new java.awt.Color(0, 0, 0));
        cmbID.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NIK", "Kartu Izin Tinggal Terbatas (Kitas)", "Paspor", "Kartu JKN", "Kartu Keluarga (KK)", "Surat UNHCR", "Surat Kelurahan", "Surat Dinas Sosial", "Surat Dinas Kesehatan", "Surat Jaminan Pelayanan", "Lainnya", "Nomor Klaim Ibu" }));
        cmbID.setName("cmbID"); // NOI18N
        cmbID.setOpaque(false);
        FormInput2.add(cmbID);
        cmbID.setBounds(125, 53, 220, 23);

        cmbKomor.setForeground(new java.awt.Color(0, 0, 0));
        cmbKomor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        cmbKomor.setName("cmbKomor"); // NOI18N
        cmbKomor.setOpaque(false);
        FormInput2.add(cmbKomor);
        cmbKomor.setBounds(318, 137, 80, 23);

        cmbEpisod.setForeground(new java.awt.Color(0, 0, 0));
        cmbEpisod.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "ICU tekanan negatif dengan ventilator", "ICU tekanan negatif tanpa ventilator", "ICU tanpa tekanan negatif dengan ventilator", "ICU tanpa tekanan negatif tanpa ventilator", "Isolasi tekanan negatif", "Isolasi tanpa tekanan negatif", "ICU dengan ventilator", "ICU tanpa ventilator", "Isolasi tekanan negatif dengan ventilator", "Isolasi tekanan negatif tanpa ventilator", "Isolasi non tekanan negatif dengan ventilator", "Isolasi non tekanan negatif tanpa ventilator" }));
        cmbEpisod.setName("cmbEpisod"); // NOI18N
        cmbEpisod.setOpaque(false);
        FormInput2.add(cmbEpisod);
        cmbEpisod.setBounds(655, 137, 280, 23);

        labelRI2.setForeground(new java.awt.Color(0, 0, 0));
        labelRI2.setText("RS Darurat / Lapangan : ");
        labelRI2.setName("labelRI2"); // NOI18N
        FormInput2.add(labelRI2);
        labelRI2.setBounds(775, 277, 130, 23);

        cmbRS.setForeground(new java.awt.Color(0, 0, 0));
        cmbRS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        cmbRS.setSelectedIndex(1);
        cmbRS.setName("cmbRS"); // NOI18N
        cmbRS.setOpaque(false);
        FormInput2.add(cmbRS);
        cmbRS.setBounds(905, 277, 70, 23);

        cmbCO.setForeground(new java.awt.Color(0, 0, 0));
        cmbCO.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        cmbCO.setSelectedIndex(1);
        cmbCO.setName("cmbCO"); // NOI18N
        cmbCO.setOpaque(false);
        FormInput2.add(cmbCO);
        cmbCO.setBounds(125, 137, 70, 23);

        hari.setForeground(new java.awt.Color(0, 0, 0));
        hari.setName("hari"); // NOI18N
        hari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hariKeyPressed(evt);
            }
        });
        FormInput2.add(hari);
        hari.setBounds(975, 137, 40, 23);

        labelklspel2.setForeground(new java.awt.Color(0, 0, 0));
        labelklspel2.setText("hari : ");
        labelklspel2.setName("labelklspel2"); // NOI18N
        FormInput2.add(labelklspel2);
        labelklspel2.setBounds(935, 137, 40, 23);

        labelLM2.setForeground(new java.awt.Color(0, 0, 0));
        labelLM2.setText("Komorbid / Komplikasi : ");
        labelLM2.setName("labelLM2"); // NOI18N
        FormInput2.add(labelLM2);
        labelLM2.setBounds(195, 137, 120, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Cara Pulang : ");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput2.add(jLabel99);
        jLabel99.setBounds(3, 249, 120, 23);

        Scroll4.setComponentPopupMenu(Popup1);
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbEpisod.setToolTipText("");
        tbEpisod.setComponentPopupMenu(Popup1);
        tbEpisod.setName("tbEpisod"); // NOI18N
        tbEpisod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbEpisodMouseClicked(evt);
            }
        });
        tbEpisod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbEpisodKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbEpisod);

        FormInput2.add(Scroll4);
        Scroll4.setBounds(685, 166, 340, 105);

        BtnAddEpisod.setForeground(new java.awt.Color(0, 0, 0));
        BtnAddEpisod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnAddEpisod.setMnemonic('S');
        BtnAddEpisod.setToolTipText("Alt+S");
        BtnAddEpisod.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnAddEpisod.setName("BtnAddEpisod"); // NOI18N
        BtnAddEpisod.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnAddEpisod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddEpisodActionPerformed(evt);
            }
        });
        BtnAddEpisod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAddEpisodKeyPressed(evt);
            }
        });
        FormInput2.add(BtnAddEpisod);
        BtnAddEpisod.setBounds(1030, 165, 28, 28);

        BtnDelEpisod.setForeground(new java.awt.Color(0, 0, 0));
        BtnDelEpisod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnDelEpisod.setMnemonic('H');
        BtnDelEpisod.setToolTipText("Alt+H");
        BtnDelEpisod.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnDelEpisod.setName("BtnDelEpisod"); // NOI18N
        BtnDelEpisod.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnDelEpisod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDelEpisodActionPerformed(evt);
            }
        });
        BtnDelEpisod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDelEpisodKeyPressed(evt);
            }
        });
        FormInput2.add(BtnDelEpisod);
        BtnDelEpisod.setBounds(1030, 200, 28, 28);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Berat Lahir : ");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput2.add(jLabel100);
        jLabel100.setBounds(3, 277, 120, 23);

        labelTarifEx2.setForeground(new java.awt.Color(0, 0, 0));
        labelTarifEx2.setText("Tambahan Terapi Plasma Konvalesen : ");
        labelTarifEx2.setName("labelTarifEx2"); // NOI18N
        FormInput2.add(labelTarifEx2);
        labelTarifEx2.setBounds(425, 305, 230, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel101.setText("Faktor pengurang, pilih checkbox pemeriksaan penunjang berikut yang tidak dilakukan : ");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput2.add(jLabel101);
        jLabel101.setBounds(45, 541, 430, 23);

        ChkAsam.setBackground(new java.awt.Color(255, 255, 250));
        ChkAsam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAsam.setForeground(new java.awt.Color(0, 0, 0));
        ChkAsam.setText("Asam Laktat");
        ChkAsam.setBorderPainted(true);
        ChkAsam.setBorderPaintedFlat(true);
        ChkAsam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAsam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAsam.setName("ChkAsam"); // NOI18N
        FormInput2.add(ChkAsam);
        ChkAsam.setBounds(45, 565, 95, 23);

        ChkKultur.setBackground(new java.awt.Color(255, 255, 250));
        ChkKultur.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKultur.setForeground(new java.awt.Color(0, 0, 0));
        ChkKultur.setText("Kultur MO (aerob) dengan resistansi");
        ChkKultur.setBorderPainted(true);
        ChkKultur.setBorderPaintedFlat(true);
        ChkKultur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKultur.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKultur.setName("ChkKultur"); // NOI18N
        FormInput2.add(ChkKultur);
        ChkKultur.setBounds(45, 592, 200, 23);

        ChkAPTT.setBackground(new java.awt.Color(255, 255, 250));
        ChkAPTT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAPTT.setForeground(new java.awt.Color(0, 0, 0));
        ChkAPTT.setText("APTT");
        ChkAPTT.setBorderPainted(true);
        ChkAPTT.setBorderPaintedFlat(true);
        ChkAPTT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAPTT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAPTT.setName("ChkAPTT"); // NOI18N
        FormInput2.add(ChkAPTT);
        ChkAPTT.setBounds(45, 619, 200, 23);

        ChkAnalisa.setBackground(new java.awt.Color(255, 255, 250));
        ChkAnalisa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAnalisa.setForeground(new java.awt.Color(0, 0, 0));
        ChkAnalisa.setText("Analisa Gas");
        ChkAnalisa.setBorderPainted(true);
        ChkAnalisa.setBorderPaintedFlat(true);
        ChkAnalisa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAnalisa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAnalisa.setName("ChkAnalisa"); // NOI18N
        FormInput2.add(ChkAnalisa);
        ChkAnalisa.setBounds(250, 565, 95, 23);

        ChkProcal.setBackground(new java.awt.Color(255, 255, 250));
        ChkProcal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkProcal.setForeground(new java.awt.Color(0, 0, 0));
        ChkProcal.setText("Procalcitonin");
        ChkProcal.setBorderPainted(true);
        ChkProcal.setBorderPaintedFlat(true);
        ChkProcal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkProcal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkProcal.setName("ChkProcal"); // NOI18N
        FormInput2.add(ChkProcal);
        ChkProcal.setBounds(250, 592, 95, 23);

        ChkDimer.setBackground(new java.awt.Color(255, 255, 250));
        ChkDimer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDimer.setForeground(new java.awt.Color(0, 0, 0));
        ChkDimer.setText("D Dimer");
        ChkDimer.setBorderPainted(true);
        ChkDimer.setBorderPaintedFlat(true);
        ChkDimer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDimer.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDimer.setName("ChkDimer"); // NOI18N
        FormInput2.add(ChkDimer);
        ChkDimer.setBounds(250, 619, 95, 23);

        ChkWaktu.setBackground(new java.awt.Color(255, 255, 250));
        ChkWaktu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkWaktu.setForeground(new java.awt.Color(0, 0, 0));
        ChkWaktu.setText("Waktu Pendarahan");
        ChkWaktu.setBorderPainted(true);
        ChkWaktu.setBorderPaintedFlat(true);
        ChkWaktu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkWaktu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkWaktu.setName("ChkWaktu"); // NOI18N
        FormInput2.add(ChkWaktu);
        ChkWaktu.setBounds(350, 565, 120, 23);

        ChkAlbumin.setBackground(new java.awt.Color(255, 255, 250));
        ChkAlbumin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAlbumin.setForeground(new java.awt.Color(0, 0, 0));
        ChkAlbumin.setText("Albumin");
        ChkAlbumin.setBorderPainted(true);
        ChkAlbumin.setBorderPaintedFlat(true);
        ChkAlbumin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAlbumin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAlbumin.setName("ChkAlbumin"); // NOI18N
        FormInput2.add(ChkAlbumin);
        ChkAlbumin.setBounds(350, 592, 120, 23);

        ChkCRP.setBackground(new java.awt.Color(255, 255, 250));
        ChkCRP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkCRP.setForeground(new java.awt.Color(0, 0, 0));
        ChkCRP.setText("CRP");
        ChkCRP.setBorderPainted(true);
        ChkCRP.setBorderPaintedFlat(true);
        ChkCRP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkCRP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkCRP.setName("ChkCRP"); // NOI18N
        FormInput2.add(ChkCRP);
        ChkCRP.setBounds(350, 619, 120, 23);

        ChkPT.setBackground(new java.awt.Color(255, 255, 250));
        ChkPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPT.setForeground(new java.awt.Color(0, 0, 0));
        ChkPT.setText("PT");
        ChkPT.setBorderPainted(true);
        ChkPT.setBorderPaintedFlat(true);
        ChkPT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPT.setName("ChkPT"); // NOI18N
        FormInput2.add(ChkPT);
        ChkPT.setBounds(480, 565, 100, 23);

        ChkAnti.setBackground(new java.awt.Color(255, 255, 250));
        ChkAnti.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAnti.setForeground(new java.awt.Color(0, 0, 0));
        ChkAnti.setText("Anti HIV");
        ChkAnti.setBorderPainted(true);
        ChkAnti.setBorderPaintedFlat(true);
        ChkAnti.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAnti.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAnti.setName("ChkAnti"); // NOI18N
        FormInput2.add(ChkAnti);
        ChkAnti.setBounds(480, 592, 100, 23);

        ChkThorax.setBackground(new java.awt.Color(255, 255, 250));
        ChkThorax.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkThorax.setForeground(new java.awt.Color(0, 0, 0));
        ChkThorax.setText("Thorax AP / PA");
        ChkThorax.setBorderPainted(true);
        ChkThorax.setBorderPaintedFlat(true);
        ChkThorax.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkThorax.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkThorax.setName("ChkThorax"); // NOI18N
        FormInput2.add(ChkThorax);
        ChkThorax.setBounds(480, 619, 100, 23);

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator5.setName("jSeparator5"); // NOI18N
        FormInput2.add(jSeparator5);
        jSeparator5.setBounds(0, 647, 1260, 5);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel102.setText("Khusus pasien COVID-19 yang meninggal dunia : ");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput2.add(jLabel102);
        jLabel102.setBounds(45, 648, 260, 23);

        ChkPemulasaran.setBackground(new java.awt.Color(255, 255, 250));
        ChkPemulasaran.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPemulasaran.setForeground(new java.awt.Color(0, 0, 0));
        ChkPemulasaran.setText("Pemulasaraan Jenazah");
        ChkPemulasaran.setBorderPainted(true);
        ChkPemulasaran.setBorderPaintedFlat(true);
        ChkPemulasaran.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPemulasaran.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPemulasaran.setName("ChkPemulasaran"); // NOI18N
        ChkPemulasaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPemulasaranActionPerformed(evt);
            }
        });
        FormInput2.add(ChkPemulasaran);
        ChkPemulasaran.setBounds(45, 672, 140, 23);

        ChkPlastik.setBackground(new java.awt.Color(255, 255, 250));
        ChkPlastik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPlastik.setForeground(new java.awt.Color(0, 0, 0));
        ChkPlastik.setText("Plastik Erat");
        ChkPlastik.setBorderPainted(true);
        ChkPlastik.setBorderPaintedFlat(true);
        ChkPlastik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPlastik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPlastik.setName("ChkPlastik"); // NOI18N
        ChkPlastik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPlastikActionPerformed(evt);
            }
        });
        FormInput2.add(ChkPlastik);
        ChkPlastik.setBounds(45, 700, 140, 23);

        ChkKantong.setBackground(new java.awt.Color(255, 255, 250));
        ChkKantong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKantong.setForeground(new java.awt.Color(0, 0, 0));
        ChkKantong.setText("Kantong Jenazah");
        ChkKantong.setBorderPainted(true);
        ChkKantong.setBorderPaintedFlat(true);
        ChkKantong.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKantong.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKantong.setName("ChkKantong"); // NOI18N
        ChkKantong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkKantongActionPerformed(evt);
            }
        });
        FormInput2.add(ChkKantong);
        ChkKantong.setBounds(190, 672, 125, 23);

        ChkDesinfekJen.setBackground(new java.awt.Color(255, 255, 250));
        ChkDesinfekJen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDesinfekJen.setForeground(new java.awt.Color(0, 0, 0));
        ChkDesinfekJen.setText("Desinfektan Jenazah");
        ChkDesinfekJen.setBorderPainted(true);
        ChkDesinfekJen.setBorderPaintedFlat(true);
        ChkDesinfekJen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDesinfekJen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDesinfekJen.setName("ChkDesinfekJen"); // NOI18N
        ChkDesinfekJen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkDesinfekJenActionPerformed(evt);
            }
        });
        FormInput2.add(ChkDesinfekJen);
        ChkDesinfekJen.setBounds(190, 700, 125, 23);

        ChkDesinfekMob.setBackground(new java.awt.Color(255, 255, 250));
        ChkDesinfekMob.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDesinfekMob.setForeground(new java.awt.Color(0, 0, 0));
        ChkDesinfekMob.setText("Desinfektan Mobil");
        ChkDesinfekMob.setBorderPainted(true);
        ChkDesinfekMob.setBorderPaintedFlat(true);
        ChkDesinfekMob.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDesinfekMob.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDesinfekMob.setName("ChkDesinfekMob"); // NOI18N
        ChkDesinfekMob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkDesinfekMobActionPerformed(evt);
            }
        });
        FormInput2.add(ChkDesinfekMob);
        ChkDesinfekMob.setBounds(325, 672, 125, 23);

        ChkPeti.setBackground(new java.awt.Color(255, 255, 250));
        ChkPeti.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPeti.setForeground(new java.awt.Color(0, 0, 0));
        ChkPeti.setText("Peti Jenazah");
        ChkPeti.setBorderPainted(true);
        ChkPeti.setBorderPaintedFlat(true);
        ChkPeti.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPeti.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPeti.setName("ChkPeti"); // NOI18N
        ChkPeti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPetiActionPerformed(evt);
            }
        });
        FormInput2.add(ChkPeti);
        ChkPeti.setBounds(325, 700, 125, 23);

        ChkTranspot.setBackground(new java.awt.Color(255, 255, 250));
        ChkTranspot.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTranspot.setForeground(new java.awt.Color(0, 0, 0));
        ChkTranspot.setText("Transport Mobil");
        ChkTranspot.setBorderPainted(true);
        ChkTranspot.setBorderPaintedFlat(true);
        ChkTranspot.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTranspot.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTranspot.setName("ChkTranspot"); // NOI18N
        ChkTranspot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkTranspotActionPerformed(evt);
            }
        });
        FormInput2.add(ChkTranspot);
        ChkTranspot.setBounds(455, 672, 125, 23);

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator6.setName("jSeparator6"); // NOI18N
        FormInput2.add(jSeparator6);
        jSeparator6.setBounds(0, 728, 1260, 5);

        noKlaim.setEditable(false);
        noKlaim.setForeground(new java.awt.Color(0, 0, 0));
        noKlaim.setName("noKlaim"); // NOI18N
        FormInput2.add(noKlaim);
        noKlaim.setBounds(690, 53, 160, 23);

        labelklspel3.setForeground(new java.awt.Color(0, 0, 0));
        labelklspel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelklspel3.setText("Unggah Berkas Pendukung Klaim : ");
        labelklspel3.setName("labelklspel3"); // NOI18N
        FormInput2.add(labelklspel3);
        labelklspel3.setBounds(725, 340, 180, 23);

        cmbUnggah.setForeground(new java.awt.Color(0, 0, 0));
        cmbUnggah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Resume Medis", "Ruang Perawatan", "Hasil Laboratorium", "Hasil Radiologi", "Hasil Penunjang Lainnya", "Resep Obat / Alkes", "Tagihan (Billing)", "Kartu Identitas", "Dokumen KIPI", "Lain-lain" }));
        cmbUnggah.setName("cmbUnggah"); // NOI18N
        cmbUnggah.setOpaque(false);
        FormInput2.add(cmbUnggah);
        cmbUnggah.setBounds(725, 364, 160, 23);

        lokasiFile.setEditable(false);
        lokasiFile.setForeground(new java.awt.Color(0, 0, 0));
        lokasiFile.setName("lokasiFile"); // NOI18N
        FormInput2.add(lokasiFile);
        lokasiFile.setBounds(890, 364, 320, 23);

        Scroll5.setComponentPopupMenu(Popup1);
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbUnggah.setToolTipText("");
        tbUnggah.setComponentPopupMenu(Popup1);
        tbUnggah.setName("tbUnggah"); // NOI18N
        tbUnggah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUnggahMouseClicked(evt);
            }
        });
        tbUnggah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbUnggahKeyPressed(evt);
            }
        });
        Scroll5.setViewportView(tbUnggah);

        FormInput2.add(Scroll5);
        Scroll5.setBounds(725, 518, 490, 120);

        BtnAddUnggah.setForeground(new java.awt.Color(0, 0, 0));
        BtnAddUnggah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAddUnggah.setMnemonic('F');
        BtnAddUnggah.setText("Cari File");
        BtnAddUnggah.setToolTipText("Alt+F");
        BtnAddUnggah.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnAddUnggah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnAddUnggah.setName("BtnAddUnggah"); // NOI18N
        BtnAddUnggah.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnAddUnggah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddUnggahActionPerformed(evt);
            }
        });
        FormInput2.add(BtnAddUnggah);
        BtnAddUnggah.setBounds(1220, 364, 100, 28);

        BtnDelUnggah.setForeground(new java.awt.Color(0, 0, 0));
        BtnDelUnggah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnDelUnggah.setMnemonic('Y');
        BtnDelUnggah.setText("Hapus Filenya");
        BtnDelUnggah.setToolTipText("Alt+Y");
        BtnDelUnggah.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnDelUnggah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnDelUnggah.setName("BtnDelUnggah"); // NOI18N
        BtnDelUnggah.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnDelUnggah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDelUnggahActionPerformed(evt);
            }
        });
        FormInput2.add(BtnDelUnggah);
        BtnDelUnggah.setBounds(1220, 552, 120, 28);

        BtnUploadFile.setForeground(new java.awt.Color(0, 0, 0));
        BtnUploadFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/upload24.png"))); // NOI18N
        BtnUploadFile.setMnemonic('U');
        BtnUploadFile.setText("Upload File");
        BtnUploadFile.setToolTipText("Alt+U");
        BtnUploadFile.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnUploadFile.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnUploadFile.setName("BtnUploadFile"); // NOI18N
        BtnUploadFile.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnUploadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUploadFileActionPerformed(evt);
            }
        });
        FormInput2.add(BtnUploadFile);
        BtnUploadFile.setBounds(1220, 518, 120, 28);

        brtlhr1.setForeground(new java.awt.Color(0, 0, 0));
        brtlhr1.setName("brtlhr1"); // NOI18N
        brtlhr1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                brtlhr1KeyPressed(evt);
            }
        });
        FormInput2.add(brtlhr1);
        brtlhr1.setBounds(125, 277, 50, 23);

        ChkSemuaFaktor.setBackground(new java.awt.Color(255, 255, 250));
        ChkSemuaFaktor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSemuaFaktor.setForeground(new java.awt.Color(0, 0, 0));
        ChkSemuaFaktor.setText("Semua Faktor Pengurang");
        ChkSemuaFaktor.setBorderPainted(true);
        ChkSemuaFaktor.setBorderPaintedFlat(true);
        ChkSemuaFaktor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkSemuaFaktor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSemuaFaktor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSemuaFaktor.setName("ChkSemuaFaktor"); // NOI18N
        ChkSemuaFaktor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkSemuaFaktorActionPerformed(evt);
            }
        });
        FormInput2.add(ChkSemuaFaktor);
        ChkSemuaFaktor.setBounds(480, 541, 190, 23);

        ChkSemuaPelengkap.setBackground(new java.awt.Color(255, 255, 250));
        ChkSemuaPelengkap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSemuaPelengkap.setForeground(new java.awt.Color(0, 0, 0));
        ChkSemuaPelengkap.setText("Semua Pelengkap Pasien Meninggal");
        ChkSemuaPelengkap.setBorderPainted(true);
        ChkSemuaPelengkap.setBorderPaintedFlat(true);
        ChkSemuaPelengkap.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkSemuaPelengkap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSemuaPelengkap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSemuaPelengkap.setName("ChkSemuaPelengkap"); // NOI18N
        ChkSemuaPelengkap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkSemuaPelengkapActionPerformed(evt);
            }
        });
        FormInput2.add(ChkSemuaPelengkap);
        ChkSemuaPelengkap.setBounds(455, 700, 240, 23);

        cmbcrPulang1.setForeground(new java.awt.Color(0, 0, 0));
        cmbcrPulang1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Dirujuk", "Atas permintaan sendiri", "Meninggal", "Atas persetujuan dokter", "Lain-lain" }));
        cmbcrPulang1.setName("cmbcrPulang1"); // NOI18N
        cmbcrPulang1.setOpaque(false);
        cmbcrPulang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbcrPulang1ActionPerformed(evt);
            }
        });
        FormInput2.add(cmbcrPulang1);
        cmbcrPulang1.setBounds(125, 249, 157, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Kriteria Akses NAAT : ");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput2.add(jLabel103);
        jLabel103.setBounds(3, 305, 120, 23);

        cmbKriteria.setForeground(new java.awt.Color(0, 0, 0));
        cmbKriteria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kriteria A", "Kriteria B", "Kriteria C" }));
        cmbKriteria.setSelectedIndex(1);
        cmbKriteria.setName("cmbKriteria"); // NOI18N
        cmbKriteria.setOpaque(false);
        FormInput2.add(cmbKriteria);
        cmbKriteria.setBounds(125, 305, 80, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("Isolasi Mandiri : ");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput2.add(jLabel104);
        jLabel104.setBounds(210, 305, 90, 23);

        cmbIsolasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbIsolasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbIsolasi.setName("cmbIsolasi"); // NOI18N
        cmbIsolasi.setOpaque(false);
        FormInput2.add(cmbIsolasi);
        cmbIsolasi.setBounds(303, 305, 70, 23);

        tarifPlasma.setForeground(new java.awt.Color(0, 0, 0));
        tarifPlasma.setName("tarifPlasma"); // NOI18N
        tarifPlasma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tarifPlasmaActionPerformed(evt);
            }
        });
        tarifPlasma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tarifPlasmaKeyReleased(evt);
            }
        });
        FormInput2.add(tarifPlasma);
        tarifPlasma.setBounds(655, 305, 100, 23);

        labelRP2.setForeground(new java.awt.Color(0, 0, 0));
        labelRP2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelRP2.setText("Rp. ");
        labelRP2.setName("labelRP2"); // NOI18N
        FormInput2.add(labelRP2);
        labelRP2.setBounds(758, 305, 20, 23);

        labeltarifPlasma.setForeground(new java.awt.Color(0, 0, 0));
        labeltarifPlasma.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labeltarifPlasma.setText("0");
        labeltarifPlasma.setName("labeltarifPlasma"); // NOI18N
        FormInput2.add(labeltarifPlasma);
        labeltarifPlasma.setBounds(780, 305, 160, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("Jenis : ");
        jLabel110.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput2.add(jLabel110);
        jLabel110.setBounds(932, 25, 50, 23);

        nmJaminan.setForeground(new java.awt.Color(0, 0, 0));
        nmJaminan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nmJaminan.setText("nmJaminan");
        nmJaminan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nmJaminan.setName("nmJaminan"); // NOI18N
        FormInput2.add(nmJaminan);
        nmJaminan.setBounds(986, 25, 420, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Status Kelainan : ");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput2.add(jLabel70);
        jLabel70.setBounds(210, 277, 90, 23);

        cmbKelainan.setForeground(new java.awt.Color(0, 0, 0));
        cmbKelainan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tanpa Kelainan", "Dengan Kelainan" }));
        cmbKelainan.setName("cmbKelainan"); // NOI18N
        cmbKelainan.setOpaque(false);
        FormInput2.add(cmbKelainan);
        cmbKelainan.setBounds(303, 277, 110, 23);

        labelnmIbu.setForeground(new java.awt.Color(0, 0, 0));
        labelnmIbu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelnmIbu.setText("labelnmIbu");
        labelnmIbu.setName("labelnmIbu"); // NOI18N
        FormInput2.add(labelnmIbu);
        labelnmIbu.setBounds(177, 81, 390, 23);

        ChkRawatIntensif1.setBackground(new java.awt.Color(255, 255, 250));
        ChkRawatIntensif1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkRawatIntensif1.setForeground(new java.awt.Color(0, 0, 0));
        ChkRawatIntensif1.setText("Ada Rawat Intensif");
        ChkRawatIntensif1.setBorderPainted(true);
        ChkRawatIntensif1.setBorderPaintedFlat(true);
        ChkRawatIntensif1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkRawatIntensif1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkRawatIntensif1.setName("ChkRawatIntensif1"); // NOI18N
        ChkRawatIntensif1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkRawatIntensif1ActionPerformed(evt);
            }
        });
        FormInput2.add(ChkRawatIntensif1);
        ChkRawatIntensif1.setBounds(400, 137, 120, 23);

        labelRI3.setForeground(new java.awt.Color(0, 0, 0));
        labelRI3.setText("LOS Intensif : ");
        labelRI3.setName("labelRI3"); // NOI18N
        FormInput2.add(labelRI3);
        labelRI3.setBounds(195, 165, 85, 23);

        losIntensif1.setForeground(new java.awt.Color(0, 0, 0));
        losIntensif1.setName("losIntensif1"); // NOI18N
        FormInput2.add(losIntensif1);
        losIntensif1.setBounds(282, 165, 40, 23);

        labelHRinten1.setForeground(new java.awt.Color(0, 0, 0));
        labelHRinten1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelHRinten1.setText("hari");
        labelHRinten1.setName("labelHRinten1"); // NOI18N
        FormInput2.add(labelHRinten1);
        labelHRinten1.setBounds(327, 165, 30, 23);

        labelventi1.setForeground(new java.awt.Color(0, 0, 0));
        labelventi1.setText("Ventilator : ");
        labelventi1.setName("labelventi1"); // NOI18N
        FormInput2.add(labelventi1);
        labelventi1.setBounds(355, 165, 60, 23);

        ventilator1.setForeground(new java.awt.Color(0, 0, 0));
        ventilator1.setName("ventilator1"); // NOI18N
        FormInput2.add(ventilator1);
        ventilator1.setBounds(415, 165, 48, 23);

        labeljam2.setForeground(new java.awt.Color(0, 0, 0));
        labeljam2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labeljam2.setText("jam");
        labeljam2.setName("labeljam2"); // NOI18N
        FormInput2.add(labeljam2);
        labeljam2.setBounds(469, 165, 30, 23);

        Scroll6.setComponentPopupMenu(Popup1);
        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbUnggahPilihan.setToolTipText("");
        tbUnggahPilihan.setComponentPopupMenu(Popup1);
        tbUnggahPilihan.setName("tbUnggahPilihan"); // NOI18N
        Scroll6.setViewportView(tbUnggahPilihan);

        FormInput2.add(Scroll6);
        Scroll6.setBounds(725, 393, 490, 120);

        BtnDelUnggahPilihan.setForeground(new java.awt.Color(0, 0, 0));
        BtnDelUnggahPilihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnDelUnggahPilihan.setMnemonic('H');
        BtnDelUnggahPilihan.setText("Hapus File");
        BtnDelUnggahPilihan.setToolTipText("Alt+H");
        BtnDelUnggahPilihan.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnDelUnggahPilihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnDelUnggahPilihan.setName("BtnDelUnggahPilihan"); // NOI18N
        BtnDelUnggahPilihan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnDelUnggahPilihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDelUnggahPilihanActionPerformed(evt);
            }
        });
        FormInput2.add(BtnDelUnggahPilihan);
        BtnDelUnggahPilihan.setBounds(1220, 432, 100, 28);

        BtnUnggahDipilih.setForeground(new java.awt.Color(0, 0, 0));
        BtnUnggahDipilih.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnUnggahDipilih.setMnemonic('D');
        BtnUnggahDipilih.setText("File Dipilih");
        BtnUnggahDipilih.setToolTipText("Alt+D");
        BtnUnggahDipilih.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnUnggahDipilih.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnUnggahDipilih.setName("BtnUnggahDipilih"); // NOI18N
        BtnUnggahDipilih.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnUnggahDipilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUnggahDipilihActionPerformed(evt);
            }
        });
        FormInput2.add(BtnUnggahDipilih);
        BtnUnggahDipilih.setBounds(1220, 398, 100, 28);

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator7.setName("jSeparator7"); // NOI18N
        FormInput2.add(jSeparator7);
        jSeparator7.setBounds(715, 334, 5, 315);

        labelrespon1.setForeground(new java.awt.Color(0, 0, 204));
        labelrespon1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelrespon1.setText("Response INAGROUPER : ");
        labelrespon1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelrespon1.setName("labelrespon1"); // NOI18N
        FormInput2.add(labelrespon1);
        labelrespon1.setBounds(630, 907, 576, 23);

        ScrollHasil6.setName("ScrollHasil6"); // NOI18N
        ScrollHasil6.setOpaque(true);

        tbRespon2.setToolTipText("");
        tbRespon2.setName("tbRespon2"); // NOI18N
        ScrollHasil6.setViewportView(tbRespon2);

        FormInput2.add(ScrollHasil6);
        ScrollHasil6.setBounds(630, 931, 576, 170);

        pros_inadrg2.setForeground(new java.awt.Color(0, 0, 204));
        pros_inadrg2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pros_inadrg2.setText("pros_inadrg2");
        pros_inadrg2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pros_inadrg2.setName("pros_inadrg2"); // NOI18N
        FormInput2.add(pros_inadrg2);
        pros_inadrg2.setBounds(870, 880, 500, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel98.setText("Prosedur INADRG (V6) yang dikirim : ");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput2.add(jLabel98);
        jLabel98.setBounds(690, 880, 180, 23);

        TabDiagnosa2.setBackground(new java.awt.Color(250, 255, 245));
        TabDiagnosa2.setForeground(new java.awt.Color(0, 0, 0));
        TabDiagnosa2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabDiagnosa2.setName("TabDiagnosa2"); // NOI18N
        TabDiagnosa2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabDiagnosa2MouseClicked(evt);
            }
        });

        internalFrame6.setBorder(null);
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(Popup1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbDiagnosaPasie3.setAutoCreateRowSorter(true);
        tbDiagnosaPasie3.setToolTipText("Silahkan klik kanan untuk memperbaiki datanya");
        tbDiagnosaPasie3.setComponentPopupMenu(Popup1);
        tbDiagnosaPasie3.setName("tbDiagnosaPasie3"); // NOI18N
        Scroll.setViewportView(tbDiagnosaPasie3);

        internalFrame6.add(Scroll, java.awt.BorderLayout.CENTER);

        TabDiagnosa2.addTab("Diagnosa Statistik (V5)", internalFrame6);

        internalFrame7.setBorder(null);
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll9.setComponentPopupMenu(Popup1);
        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);

        tbDiagnosaPasie4.setAutoCreateRowSorter(true);
        tbDiagnosaPasie4.setToolTipText("Silahkan klik kanan untuk memperbaiki datanya");
        tbDiagnosaPasie4.setComponentPopupMenu(Popup1);
        tbDiagnosaPasie4.setName("tbDiagnosaPasie4"); // NOI18N
        Scroll9.setViewportView(tbDiagnosaPasie4);

        internalFrame7.add(Scroll9, java.awt.BorderLayout.CENTER);

        TabDiagnosa2.addTab("Diagnosa INADRG (V6)", internalFrame7);

        FormInput2.add(TabDiagnosa2);
        TabDiagnosa2.setBounds(45, 750, 630, 145);

        TabProsedur2.setBackground(new java.awt.Color(250, 255, 245));
        TabProsedur2.setForeground(new java.awt.Color(0, 0, 0));
        TabProsedur2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabProsedur2.setName("TabProsedur2"); // NOI18N
        TabProsedur2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabProsedur2MouseClicked(evt);
            }
        });

        internalFrame10.setBorder(null);
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setComponentPopupMenu(Popup1);
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbTindakanPasien3.setAutoCreateRowSorter(true);
        tbTindakanPasien3.setToolTipText("Silahkan klik kanan untuk memperbaiki datanya");
        tbTindakanPasien3.setComponentPopupMenu(Popup1);
        tbTindakanPasien3.setName("tbTindakanPasien3"); // NOI18N
        Scroll1.setViewportView(tbTindakanPasien3);

        internalFrame10.add(Scroll1, java.awt.BorderLayout.CENTER);

        TabProsedur2.addTab("Prosedur Statistik (V5)", internalFrame10);

        internalFrame11.setBorder(null);
        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll11.setComponentPopupMenu(Popup1);
        Scroll11.setName("Scroll11"); // NOI18N
        Scroll11.setOpaque(true);

        tbTindakanPasien4.setAutoCreateRowSorter(true);
        tbTindakanPasien4.setToolTipText("Silahkan klik kanan untuk memperbaiki datanya");
        tbTindakanPasien4.setComponentPopupMenu(Popup1);
        tbTindakanPasien4.setName("tbTindakanPasien4"); // NOI18N
        Scroll11.setViewportView(tbTindakanPasien4);

        internalFrame11.add(Scroll11, java.awt.BorderLayout.CENTER);

        TabProsedur2.addTab("Prosedur INADRG (V6)", internalFrame11);

        FormInput2.add(TabProsedur2);
        TabProsedur2.setBounds(690, 750, 510, 131);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("Cara Masuk : ");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput2.add(jLabel105);
        jLabel105.setBounds(283, 249, 80, 23);

        cmbCrMasuk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbCrMasuk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rujukan FKTP", "Rujukan FKRTL", "Rujukan Spesialis", "Dari Rawat Jalan", "Dari Rawat Inap", "Dari Rawat Darurat", "Lahir di RS", "Rujukan Panti Jompo", "Rujukan dari RS Jiwa", "Rujukan Fasilitas", "Lain-lain" }));
        cmbCrMasuk1.setName("cmbCrMasuk1"); // NOI18N
        cmbCrMasuk1.setOpaque(false);
        FormInput2.add(cmbCrMasuk1);
        cmbCrMasuk1.setBounds(365, 249, 140, 23);

        labelventi2.setForeground(new java.awt.Color(255, 0, 0));
        labelventi2.setText("Ventilator : ");
        labelventi2.setName("labelventi2"); // NOI18N
        FormInput2.add(labelventi2);
        labelventi2.setBounds(355, 193, 60, 23);

        cmbVentilator1.setForeground(new java.awt.Color(0, 0, 0));
        cmbVentilator1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        cmbVentilator1.setSelectedIndex(1);
        cmbVentilator1.setName("cmbVentilator1"); // NOI18N
        cmbVentilator1.setOpaque(false);
        cmbVentilator1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVentilator1ActionPerformed(evt);
            }
        });
        FormInput2.add(cmbVentilator1);
        cmbVentilator1.setBounds(415, 193, 60, 23);

        labeljam3.setForeground(new java.awt.Color(255, 0, 0));
        labeljam3.setText("Intubasi : ");
        labeljam3.setName("labeljam3"); // NOI18N
        FormInput2.add(labeljam3);
        labeljam3.setBounds(475, 193, 60, 23);

        tglIntubasi1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-02-2023 09:02:20" }));
        tglIntubasi1.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        tglIntubasi1.setName("tglIntubasi1"); // NOI18N
        tglIntubasi1.setOpaque(false);
        tglIntubasi1.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput2.add(tglIntubasi1);
        tglIntubasi1.setBounds(536, 193, 140, 23);

        labeljam4.setForeground(new java.awt.Color(255, 0, 0));
        labeljam4.setText("Ekstubasi : ");
        labeljam4.setName("labeljam4"); // NOI18N
        FormInput2.add(labeljam4);
        labeljam4.setBounds(475, 221, 60, 23);

        tglEkstubasi1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-02-2023 09:02:20" }));
        tglEkstubasi1.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        tglEkstubasi1.setName("tglEkstubasi1"); // NOI18N
        tglEkstubasi1.setOpaque(false);
        tglEkstubasi1.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput2.add(tglEkstubasi1);
        tglEkstubasi1.setBounds(536, 221, 140, 23);

        scrollInput1.setViewportView(FormInput2);

        internalFrame3.add(scrollInput1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Jaminan LAINNYA", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 0) {
            TabDiagnosa1.setSelectedIndex(0);
            TabProsedur1.setSelectedIndex(0);
        } else if (TabRawat.getSelectedIndex() == 1) {
            TabDiagnosa2.setSelectedIndex(0);
            TabProsedur2.setSelectedIndex(0);
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        TabJaminanJKN.setBackgroundAt(1, Color.PINK);
        TabJaminanJKN.setBackgroundAt(2, Color.PINK);
        TabJaminanJKN.setBackgroundAt(3, Color.PINK);
        
        emptnotb();
        if (Sequel.cariIsi("select stts_validasi from eklaim_sitb_validasi where no_sep='" + noSEP.getText() + "'").equals("VALID")) {
            ChkpasienTB.setSelected(true);
            noregTB.setEnabled(true);
            noregTB.setText(Sequel.cariIsi("select id_tb_03 from nomor_reg_tb where no_rkm_medis='" + norm.getText() + "'"));
            stts_noreg.setText(Sequel.cariIsi("select stts_validasi from eklaim_sitb_validasi where no_sep='" + noSEP.getText() + "'"));
            cmbvalidTB.setSelectedIndex(0); 
            cmbvalidTB.setEnabled(true);
            BtnValidasiTB.setEnabled(true);
        } else if (Sequel.cariIsi("select stts_validasi from eklaim_sitb_validasi where no_sep='" + noSEP.getText() + "'").equals("INVALID")) {
            ChkpasienTB.setSelected(true);
            noregTB.setEnabled(true);
            noregTB.setText(Sequel.cariIsi("select id_tb_03 from nomor_reg_tb where no_rkm_medis='" + norm.getText() + "'"));
            stts_noreg.setText(Sequel.cariIsi("select stts_validasi from eklaim_sitb_validasi where no_sep='" + noSEP.getText() + "'"));
            cmbvalidTB.setSelectedIndex(1);
            cmbvalidTB.setEnabled(true);
            BtnValidasiTB.setEnabled(true);
        } else {
            emptnotb();
        }
    }//GEN-LAST:event_formWindowOpened

    private void cmbCOBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbCOBMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCOBMouseClicked

    private void cmbCOBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbCOBKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCOBKeyPressed

    private void ChkKelasEksekutifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkKelasEksekutifActionPerformed
        if (ChkKelasEksekutif.isSelected() == true) {
            tarifPoliExe.setEnabled(true);
            tarifPoliExe.setText("0");
            labeltarif.setText("0");
            cmbKH.setEnabled(false);
            cmbKH.setSelectedIndex(2);
        } else {
            tarifPoliExe.setEnabled(false);
            tarifPoliExe.setText("0");
            labeltarif.setText("0");
            cmbKH.setEnabled(true);
            cmbKH.setSelectedIndex(0);
        }
    }//GEN-LAST:event_ChkKelasEksekutifActionPerformed

    private void ChkNaikTurunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkNaikTurunActionPerformed
        if (ChkNaikTurun.isSelected() == true) {
            cmbKP.setEnabled(true);
            losNaikKls.setEnabled(true);
            cmbPembiayaan.setEnabled(true);
        } else {
            cmbKP.setEnabled(false);
            cmbKP.setSelectedIndex(0);
            losNaikKls.setEnabled(false);
            losNaikKls.setText("0");
            cmbPembiayaan.setEnabled(false);
            cmbPembiayaan.setSelectedIndex(0);
        }
    }//GEN-LAST:event_ChkNaikTurunActionPerformed

    private void ChkRawatIntensifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkRawatIntensifActionPerformed
        tglIntubasi.setEnabled(false);
        tglEkstubasi.setEnabled(false);

        if (ChkRawatIntensif.isSelected() == true) {
            losIntensif.setEnabled(true);
            ventilator.setEnabled(true);
            cmbVentilator.setEnabled(true);
            cmbVentilator.setSelectedIndex(1);
        } else {
            losIntensif.setEnabled(false);
            losIntensif.setText("0");
            ventilator.setEnabled(false);
            ventilator.setText("0");
            cmbVentilator.setEnabled(false);
            cmbVentilator.setSelectedIndex(1);            
        }
    }//GEN-LAST:event_ChkRawatIntensifActionPerformed

    private void losNaikKlsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_losNaikKlsKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_losNaikKlsKeyPressed

    private void ventilatorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ventilatorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ventilatorKeyPressed

    private void subakutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_subakutKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_subakutKeyPressed

    private void kronikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kronikKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kronikKeyPressed

    private void tarifPoliExeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarifPoliExeKeyPressed

    }//GEN-LAST:event_tarifPoliExeKeyPressed

    private void tarifPoliExeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarifPoliExeKeyReleased
        if (tarifPoliExe.getText().trim().equals("")) {
            labeltarif.setText("0");
        } else {
            labeltarif.setText(Valid.SetAngka(Double.parseDouble(tarifPoliExe.getText())));
        }
    }//GEN-LAST:event_tarifPoliExeKeyReleased

    private void BtnGruperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGruperActionPerformed
        if (norawat.equals("") || noSEP.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu No. SEP...!!!!");
        } 
//        else if (!tglREG.equals(tglSEP.getText())) {
//            JOptionPane.showMessageDialog(null, "Tgl. SEP & Tgl. Registrasi berbeda, perbaikilah terlebih dulu...!!!!");
//        } 
        else if (wktMasuk.getText().trim().equals("") || dpjp.getText().trim().equals("")) {
            setKlaim(norawat, noSEP.getText(), "JKN", "3","-", tglSEP.getText());
        } else if (cmbcrPulang.getSelectedItem().equals("-")) {
            JOptionPane.showMessageDialog(null, "Pilihlah cara pulang pasien dengan benar...!!!!");        
        } else {
            if (jpel.equals("2")) {
                if (tabMode.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Diagnosa ICD-10 masih kosong...!!!!");
                } else {                    
                    simpanGroupingJKN();
                }
            } else {
                if (tabMode.getRowCount() == 0 || tabMode13.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Untuk kasus rawat inap Diagnosa ICD-10 V5 & V6 harus terisi...!!!!");
                } else {
                    simpanGroupingJKN();
                }
            }
        }
    }//GEN-LAST:event_BtnGruperActionPerformed

    private void BtnKirimOnlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKirimOnlineActionPerformed
        //kalau kode payor 3 adalah JKN
        cekKanker = 0;
        kankerRalan = 0;
        kankerRanap = 0;
        
        if (kodePayor.equals("3")) {
            if (tabMode2.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Hasil grouper belum ada, lakukan proses simpan & grouper klaim dulu,...!!");
                BtnGruper.requestFocus();
            } else {
                if (Sequel.cariInteger("select count(-1) from setting_bridging where kd_bridging='1' and status_aktif='Ya'") == 1) {
                    kankerRanap = Sequel.cariInteger("select count(-1) from diagnosa_pasien dp inner join penyakit p on p.kd_penyakit=dp.kd_penyakit "
                            + "inner join kategori_penyakit kp on kp.kd_ktg=p.kd_ktg where "
                            + "dp.no_rawat='" + Sequel.cariIsi("select no_rawat from bridging_sep where no_sep='" + noSEP.getText() + "'") + "' "
                            + "and dp.status='Ranap' and dp.prioritas='1' and kp.nm_kategori='Kanker'");

                    kankerRalan = Sequel.cariInteger("select count(-1) from diagnosa_pasien dp inner join penyakit p on p.kd_penyakit=dp.kd_penyakit "
                            + "inner join kategori_penyakit kp on kp.kd_ktg=p.kd_ktg where "
                            + "dp.no_rawat='" + Sequel.cariIsi("select no_rawat from bridging_sep where no_sep='" + noSEP.getText() + "'") + "' "
                            + "and dp.status='Ralan' and dp.prioritas='1' and kp.nm_kategori='Kanker'");

                    cekKanker = Sequel.cariInteger("select count(-1) from data_kanker_bridging dk inner join bridging_sep bs on bs.no_rawat=dk.no_rawat where bs.no_sep='" + noSEP.getText() + "'");

                    if ((kankerRanap > 0 && cekKanker < 1) || (kankerRalan > 0 && cekKanker < 1)) {
                        i = JOptionPane.showConfirmDialog(null, "Diagnosa utama pasien ini terindikasi kanker, lakukan proses pengiriman data ke Kemenkes terlebih dulu...", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                        if (i == JOptionPane.YES_OPTION) {
                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            DlgDataKanker kanker = new DlgDataKanker(null, false);
                            kanker.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            kanker.setLocationRelativeTo(internalFrame1);
                            kanker.isCek();
                            kanker.emptTeks();
                            kanker.setData(Sequel.cariIsi("select no_rawat from bridging_sep where no_sep='" + noSEP.getText() + "'"));
                            kanker.setVisible(true);
                            this.setCursor(Cursor.getDefaultCursor());
                        }

                    } else {
                        tampilDiagnosa();
                        tampilProsedur();
                        mbak_eka.mengirimOnline(noSEP.getText());
                        labelhasilG.setText("Hasil Grouper : " + Sequel.cariIsi("select if(klaim_final='Belum','',klaim_final) from eklaim_new_claim where no_sep='" + noSEP.getText() + "'"));
                        labeltambahan.setText("Tambahan biaya yang dibayar pasien naik kelas : Rp. "
                                + Sequel.cariIsi("SELECT format(ifnull(add_payment_amt,'0'),0) tambhn_biaya FROM eklaim_grouping WHERE no_sep='" + noSEP.getText() + "'"));
                        tampilHG1();
                        tampilHG2();
                    }
                } else {
                    tampilDiagnosa();
                    tampilProsedur();
                    mbak_eka.mengirimOnline(noSEP.getText());
                    labelhasilG.setText("Hasil Grouper : " + Sequel.cariIsi("select if(klaim_final='Belum','',klaim_final) from eklaim_new_claim where no_sep='" + noSEP.getText() + "'"));
                    labeltambahan.setText("Tambahan biaya yang dibayar pasien naik kelas : Rp. "
                            + Sequel.cariIsi("SELECT format(ifnull(add_payment_amt,'0'),0) tambhn_biaya FROM eklaim_grouping WHERE no_sep='" + noSEP.getText() + "'"));
                    tampilHG1();
                    tampilHG2();
                }               
            }

        } else {
            if (tabMode7.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Hasil grouper belum ada, lakukan proses simpan & grouper klaim dulu,...!!");
                BtnGruper2.requestFocus();
            } else {
                tampilDiagnosa();
                tampilProsedur();
                mbak_eka.mengirimOnline(noKlaim.getText());
                labelhasilG2.setText("Hasil Grouper : " + Sequel.cariIsi("select if(klaim_final='Belum','',klaim_final) from eklaim_new_claim where no_sep='" + noKlaim.getText() + "'"));
                tampilHG1();
                tampilHG2();
            }
        }
    }//GEN-LAST:event_BtnKirimOnlineActionPerformed

    private void BtnKirimOnlineKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKirimOnlineKeyPressed

    }//GEN-LAST:event_BtnKirimOnlineKeyPressed

    private void BtnEditKlaimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditKlaimActionPerformed
        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            if (tabMode2.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Hasil grouper belum ada, lakukan proses simpan & grouper klaim dulu,...!!");
                BtnGruper.requestFocus();
            } else {
                tampilDiagnosa();
                tampilProsedur();
                mbak_eka.mengedit(noSEP.getText());
                labelhasilG.setText("Hasil Grouper : " + Sequel.cariIsi("select if(klaim_final='Belum','',klaim_final) from eklaim_new_claim where no_sep='" + noSEP.getText() + "'"));
                labeltambahan.setText("Tambahan biaya yang dibayar pasien naik kelas : Rp. "
                        + Sequel.cariIsi("SELECT format(ifnull(add_payment_amt,'0'),0) tambhn_biaya FROM eklaim_grouping WHERE no_sep='" + noSEP.getText() + "'"));
                tampilHG1();
                tampilHG2();
            }

        } else {
            if (tabMode7.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Hasil grouper belum ada, lakukan proses simpan & grouper klaim dulu,...!!");
                BtnGruper2.requestFocus();
            } else {
                tampilDiagnosa();
                tampilProsedur();
                mbak_eka.mengedit(noKlaim.getText());
                labelhasilG2.setText("Hasil Grouper : " + Sequel.cariIsi("select if(klaim_final='Belum','',klaim_final) from eklaim_new_claim where no_sep='" + noKlaim.getText() + "'"));
                tampilHG1();
                tampilHG2();
            }
        }
    }//GEN-LAST:event_BtnEditKlaimActionPerformed

    private void BtnEditKlaimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKlaimKeyPressed

    }//GEN-LAST:event_BtnEditKlaimKeyPressed

    private void BtnHapusKlaimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusKlaimActionPerformed
        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            if (tabMode2.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Hasil grouper belum ada, lakukan proses simpan & grouper klaim dulu,...!!");
                BtnGruper.requestFocus();
            } else {
                tampilDiagnosa();
                tampilProsedur();
                mbak_eka.menghapus(noSEP.getText(), nikPetugas.getText());
                dispose();
            }

        } else {
            if (tabMode7.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Hasil grouper belum ada, lakukan proses simpan & grouper klaim dulu,...!!");
                BtnGruper2.requestFocus();
            } else {
                tampilDiagnosa();
                tampilProsedur();
                mbak_eka.menghapus(noKlaim.getText(), nikPetugas1.getText());
                dispose();
            }
        }
    }//GEN-LAST:event_BtnHapusKlaimActionPerformed

    private void BtnFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFinalActionPerformed
        //kalau kode payor 3 adalah JKN        
        if (kodePayor.equals("3")) {
            if (tabMode2.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Lakukan proses simpan & grouper klaim dulu,...!!");
                BtnGruper.requestFocus();
            } else {
                i = JOptionPane.showConfirmDialog(null, "Apakah proses final klaim akan tetap dilanjutkan..??!!", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    tampilDiagnosa();
                    tampilProsedur();
                    mbak_eka.mempinal(noSEP.getText(), nikPetugas.getText());
                    labelhasilG.setText("Hasil Grouper : " + Sequel.cariIsi("select if(klaim_final='Belum','',klaim_final) from eklaim_new_claim where no_sep='" + noSEP.getText() + "'"));
                    labeltambahan.setText("Tambahan biaya yang dibayar pasien naik kelas : Rp. "
                            + Sequel.cariIsi("SELECT format(ifnull(add_payment_amt,'0'),0) tambhn_biaya FROM eklaim_grouping WHERE no_sep='" + noSEP.getText() + "'"));
                    tampilHG1();
                    tampilHG2();
                }
            }

        } else {
            if (tabMode7.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Lakukan proses simpan & grouper klaim dulu,...!!");
                BtnGruper2.requestFocus();
            } else {
                i = JOptionPane.showConfirmDialog(null, "Apakah proses final klaim akan tetap dilanjutkan..??!!", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    tampilDiagnosa();
                    tampilProsedur();
                    mbak_eka.mempinal(noKlaim.getText(), nikPetugas1.getText());
                    labelhasilG2.setText("Hasil Grouper : " + Sequel.cariIsi("select if(klaim_final='Belum','',klaim_final) from eklaim_new_claim where no_sep='" + noKlaim.getText() + "'"));
                    tampilHG1();
                    tampilHG2();
                }
            }
        }
    }//GEN-LAST:event_BtnFinalActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        emptTeksJKN();
        emptTeksLAINNYA();
        emptnotb();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnFinal, BtnKeluar);
        }
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void ppDiagnosaBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppDiagnosaBtnPrintActionPerformed
        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            if (norm.getText().equals("") || norawat.equals("")) {
                JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasiennya,...!!");
            } else {
                cekFinal = "";
                cekFinal = Sequel.cariIsi("select klaim_final from eklaim_new_claim where no_sep='" + noSEP.getText() + "'");

                if (cekFinal.equals("Final")) {
                    JOptionPane.showMessageDialog(null, "Data klaim pasien ini sudah difinal, proses tidak dapat dilanjutkan...!!");
                    tampilDiagnosa();
                    tampilProsedur();
                } else {
                    akses.setform("PengajuanKlaimINACBGraza");
                    diagnosa.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    diagnosa.setLocationRelativeTo(internalFrame1);
                    diagnosa.isCek();
                    //rawat inap
                    if (jpel.equals("1")) {
                        diagnosa.setNoRm(norawat, tglDiagnosa.getDate(), "Ranap");
                    //rawat jalan
                    } else {
                        diagnosa.setNoRm(norawat, tglDiagnosa.getDate(), "Ralan");
                    }
                    diagnosa.tampilDiagStatistik();
                    diagnosa.tampilDiagInadrg();
                    diagnosa.setVisible(true);
                }
            }

        } else {
            if (norm1.getText().equals("") || norawat.equals("")) {
                JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasiennya,...!!");
            } else {
                cekFinal = "";
                cekFinal = Sequel.cariIsi("select klaim_final from eklaim_new_claim where no_sep='" + noKlaim.getText() + "'");

                if (cekFinal.equals("Final")) {
                    JOptionPane.showMessageDialog(null, "Data klaim pasien ini sudah difinal, proses tidak dapat dilanjutkan...!!");
                    tampilDiagnosa();
                    tampilProsedur();
                } else {
                    akses.setform("PengajuanKlaimINACBGraza");
                    diagnosa.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    diagnosa.setLocationRelativeTo(internalFrame1);
                    diagnosa.isCek();
                    //rawat inap
                    if (jpel.equals("1")) {
                        diagnosa.setNoRm(norawat, tglDiagnosa.getDate(), "Ranap");
                    //rawat jalan
                    } else {
                        diagnosa.setNoRm(norawat, tglDiagnosa.getDate(), "Ralan");
                    }
                    diagnosa.tampilDiagStatistik();
                    diagnosa.tampilDiagInadrg();
                    diagnosa.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_ppDiagnosaBtnPrintActionPerformed

    private void tglDiagnosaDTPCari1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tglDiagnosaDTPCari1ItemStateChanged

    }//GEN-LAST:event_tglDiagnosaDTPCari1ItemStateChanged

    private void tglDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglDiagnosaKeyPressed

    }//GEN-LAST:event_tglDiagnosaKeyPressed

    private void BtnGruperStageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGruperStageActionPerformed
        kodeTopUPnya = "";
        tampilDiagnosa();
        tampilProsedur();

        try {
            j = 0;
            for (i = 0; i < tbHasil2.getRowCount(); i++) {
                if (tbHasil2.getValueAt(i, 0).toString().equals("true")) {
                    j++;
                }
            }

            if (j == 0) {
                JOptionPane.showMessageDialog(null, "Belum ada special CMG yang dipilih,.!!!");
                tampilHG2();
            } else {
                for (i = 0; i < tbHasil2.getRowCount(); i++) {
                    if (tbHasil2.getValueAt(i, 0).toString().equals("true")) {
                        kodeTopUPnya = kodeTopUPnya + tbHasil2.getValueAt(i, 2).toString() + "#";
                    }
                }
                mbak_eka.menggrouperKedua(noSEP.getText(), kodeTopUPnya);
                tampilHG1();
                tampilHG2();
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }//GEN-LAST:event_BtnGruperStageActionPerformed

    private void BtnGruperStageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGruperStageKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGruperStageKeyPressed

    private void BtnRefresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRefresActionPerformed
        tampilDiagnosa();
        tampilProsedur();
    }//GEN-LAST:event_BtnRefresActionPerformed

    private void BtnRefresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnRefresKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRefresKeyPressed

    private void subakut1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_subakut1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_subakut1KeyPressed

    private void kronik1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kronik1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kronik1KeyPressed

    private void BtnGruper2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGruper2ActionPerformed
        if (norawat.equals("") || noKlaim.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu data pasiennya...!!!!");
        } else if (wktMasuk1.getText().trim().equals("") || dpjp1.getText().trim().equals("")) {
            setKlaim(norawat, noKlaim.getText(), nmJaminan.getText(), kodePayor, ibunya, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norawat + "'"));
        } else if (cmbcrPulang1.getSelectedItem().equals("-")) {
            JOptionPane.showMessageDialog(null, "Pilihlah cara pulang pasien dengan benar...!!!!");
        } else {
            if (jpel.equals("2")) {
                if (tabMode5.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Diagnosa ICD-10 masih kosong...!!!!");
                } else {
                    simpanGrouperLAINNYA();
                }
            } else {
                if (tabMode5.getRowCount() == 0 || tabMode14.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Untuk kasus rawat inap Diagnosa ICD-10 V5 & V6 harus terisi...!!!!");
                } else {
                    simpanGrouperLAINNYA();
                }
            }
        }
    }//GEN-LAST:event_BtnGruper2ActionPerformed

    private void cmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusActionPerformed
        if (cmbStatus.getSelectedIndex() == 2) {
            if (kodePayor.equals("73") || kodePayor.equals("72") || kodePayor.equals("75")) {
                cmbIsolasi.setEnabled(false);
                tarifPlasma.setEnabled(false);
                tarifPlasma.setText("0");
            } else {
                cmbIsolasi.setEnabled(true);
                tarifPlasma.setEnabled(true);
                tarifPlasma.setText("0");
            }
        } else {
            cmbIsolasi.setEnabled(false);
            cmbIsolasi.setSelectedIndex(0);
            tarifPlasma.setEnabled(false);
            tarifPlasma.setText("0");
        }
    }//GEN-LAST:event_cmbStatusActionPerformed

    private void BtnGruperStage1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGruperStage1ActionPerformed
        kodeTopUPnya = "";
        tampilDiagnosa();
        tampilProsedur();

        try {
            j = 0;
            for (i = 0; i < tbHasil4.getRowCount(); i++) {
                if (tbHasil4.getValueAt(i, 0).toString().equals("true")) {
                    j++;
                }
            }

            if (j == 0) {
                JOptionPane.showMessageDialog(null, "Belum ada special CMG yang dipilih,.!!!");
                tampilHG2();
            } else {
                for (i = 0; i < tbHasil4.getRowCount(); i++) {
                    if (tbHasil4.getValueAt(i, 0).toString().equals("true")) {
                        kodeTopUPnya = kodeTopUPnya + tbHasil4.getValueAt(i, 2).toString() + "#";
                    }
                }
                mbak_eka.menggrouperKedua(noKlaim.getText(), kodeTopUPnya);
                tampilHG1();
                tampilHG2();
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }//GEN-LAST:event_BtnGruperStage1ActionPerformed

    private void BtnRefres1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRefres1ActionPerformed
        tampilDiagnosa();
        tampilProsedur();
    }//GEN-LAST:event_BtnRefres1ActionPerformed

    private void BtnRefres1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnRefres1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRefres1KeyPressed

    private void noIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noIDKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_noIDKeyPressed

    private void hariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnAddEpisodActionPerformed(null);
        }
    }//GEN-LAST:event_hariKeyPressed

    private void BtnAddEpisodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddEpisodActionPerformed
        if (hari.getText().trim().equals("") || hari.getText().equals("0")) {
            JOptionPane.showMessageDialog(null, "Jumlah lama hari tidak boleh kosong..!!");
        } else if (cmbEpisod.getSelectedItem().equals("-")) {
            JOptionPane.showMessageDialog(null, "Pilihlah satu jenis episode ruang rawat dengan benar..!!");
        } else {
            tambahEpisod();
            hari.setText("0");
            episodSIAP();
            cmbEpisod.setSelectedIndex(0);
            cmbEpisod.requestFocus();
        }
    }//GEN-LAST:event_BtnAddEpisodActionPerformed

    private void BtnAddEpisodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAddEpisodKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAddEpisodKeyPressed

    private void BtnDelEpisodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDelEpisodActionPerformed
        for (i = 0; i < tbEpisod.getRowCount(); i++) {
            if (tbEpisod.getValueAt(i, 1).toString().equals("true")) {
                tabMode4.removeRow(i);
            }
        }
        episodSIAP();
    }//GEN-LAST:event_BtnDelEpisodActionPerformed

    private void BtnDelEpisodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDelEpisodKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDelEpisodKeyPressed

    private void tbEpisodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbEpisodKeyPressed
        if (tabMode4.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataEpisod();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbEpisodKeyPressed

    private void tbEpisodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEpisodMouseClicked
        if (tabMode4.getRowCount() != 0) {
            try {
                getDataEpisod();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbEpisodMouseClicked

    private void ChkPemulasaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPemulasaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkPemulasaranActionPerformed

    private void ChkPlastikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPlastikActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkPlastikActionPerformed

    private void ChkKantongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkKantongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkKantongActionPerformed

    private void ChkDesinfekJenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkDesinfekJenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkDesinfekJenActionPerformed

    private void ChkDesinfekMobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkDesinfekMobActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkDesinfekMobActionPerformed

    private void ChkPetiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPetiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkPetiActionPerformed

    private void ChkTranspotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkTranspotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkTranspotActionPerformed

    private void tbUnggahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUnggahMouseClicked
//        if (tabMode9.getRowCount() != 0) {
//            try {
//                getDataUnggah();
//            } catch (java.lang.NullPointerException e) {
//            }
//        }
    }//GEN-LAST:event_tbUnggahMouseClicked

    private void tbUnggahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbUnggahKeyPressed
//        if (tabMode9.getRowCount() != 0) {
//            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
//                try {
//                    getDataUnggah();
//                } catch (java.lang.NullPointerException e) {
//                }
//            }
//        }
    }//GEN-LAST:event_tbUnggahKeyPressed

    private void BtnAddUnggahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddUnggahActionPerformed
        if (cmbUnggah.getSelectedItem().equals("-")) {
            JOptionPane.showMessageDialog(null, "Pilih salah satu berkas pendukung klaim dengan benar,...!!");
        } else {
            FileDialog dialog = new FileDialog((Frame) null, "Pilihlah salah satu file yang akan dikirim dalam bentuk format pdf");
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            if (dialog.getDirectory().equals("null") || dialog.getFile().equals("null")) {
                lokasiFile.setText("");
            } else {
                directory = dialog.getDirectory();
                file = dialog.getFile();
                lokasiFile.setText(directory + file);
            }            
        }
    }//GEN-LAST:event_BtnAddUnggahActionPerformed

    private void BtnDelUnggahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDelUnggahActionPerformed
        for (i = 0; i < tbUnggah.getRowCount(); i++) {
            if (tbUnggah.getValueAt(i, 0).toString().equals("true")) {
                mbak_eka.menghapusFileUpload(noKlaim.getText(), tbUnggah.getValueAt(i, 5).toString());
            }
        }
        tampilTerupload();
    }//GEN-LAST:event_BtnDelUnggahActionPerformed

    private void BtnUploadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUploadFileActionPerformed
        if (tabMode10.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "File berkas yang diunggah belum ada yang dipilih...!!!!");
            cmbUnggah.setSelectedIndex(0);
            lokasiFile.setText("");
        } else {
            try {
                for (i = 0; i < tbUnggahPilihan.getRowCount(); i++) {
                    byte[] input_file = Files.readAllBytes(Paths.get(tbUnggahPilihan.getValueAt(i, 3).toString()));
                    byte[] encodedBytes = Base64.getEncoder().encode(input_file);
                    String encodedString = new String(encodedBytes);
                    mbak_eka.mengunggahFile(noKlaim.getText(), tbUnggahPilihan.getValueAt(i, 1).toString(), tbUnggahPilihan.getValueAt(i, 2).toString(), encodedString);
                    tampilTerupload();
                }
                
                cmbUnggah.setSelectedIndex(0);
                lokasiFile.setText("");
                Valid.tabelKosong(tabMode10);
                JOptionPane.showMessageDialog(null, "Proses upload berkas pendukung klaim yang dipilih telah selesai...!!!!");                
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_BtnUploadFileActionPerformed

    private void brtlhr1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_brtlhr1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_brtlhr1KeyPressed

    private void ChkSemuaFaktorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkSemuaFaktorActionPerformed
        if (ChkSemuaFaktor.isSelected() == true) {
            ChkAsam.setSelected(true);
            ChkKultur.setSelected(true);
            ChkAPTT.setSelected(true);
            ChkAnalisa.setSelected(true);
            ChkProcal.setSelected(true);
            ChkDimer.setSelected(true);
            ChkWaktu.setSelected(true);
            ChkAlbumin.setSelected(true);
            ChkCRP.setSelected(true);
            ChkPT.setSelected(true);
            ChkAnti.setSelected(true);
            ChkThorax.setSelected(true);
        } else if (ChkSemuaFaktor.isSelected() == false) {
            ChkAsam.setSelected(false);
            ChkKultur.setSelected(false);
            ChkAPTT.setSelected(false);
            ChkAnalisa.setSelected(false);
            ChkProcal.setSelected(false);
            ChkDimer.setSelected(false);
            ChkWaktu.setSelected(false);
            ChkAlbumin.setSelected(false);
            ChkCRP.setSelected(false);
            ChkPT.setSelected(false);
            ChkAnti.setSelected(false);
            ChkThorax.setSelected(false);
        }
    }//GEN-LAST:event_ChkSemuaFaktorActionPerformed

    private void ChkSemuaPelengkapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkSemuaPelengkapActionPerformed
        if (ChkSemuaPelengkap.isSelected() == true) {
            ChkPemulasaran.setSelected(true);
            ChkPlastik.setSelected(true);
            ChkKantong.setSelected(true);
            ChkDesinfekJen.setSelected(true);
            ChkDesinfekMob.setSelected(true);
            ChkPeti.setSelected(true);
            ChkTranspot.setSelected(true);
        } else if (ChkSemuaPelengkap.isSelected() == false) {
            ChkPemulasaran.setSelected(false);
            ChkPlastik.setSelected(false);
            ChkKantong.setSelected(false);
            ChkDesinfekJen.setSelected(false);
            ChkDesinfekMob.setSelected(false);
            ChkPeti.setSelected(false);
            ChkTranspot.setSelected(false);
        }
    }//GEN-LAST:event_ChkSemuaPelengkapActionPerformed

    private void brtlhrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_brtlhrKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_brtlhrKeyPressed

    private void cmbcrPulangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbcrPulangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbcrPulangMouseClicked

    private void cmbcrPulangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbcrPulangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbcrPulangKeyPressed

    private void tarifPlasmaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarifPlasmaKeyReleased
        if (tarifPlasma.getText().trim().equals("")) {
            labeltarifPlasma.setText("0");
        } else {
            labeltarifPlasma.setText(Valid.SetAngka(Double.parseDouble(tarifPlasma.getText())));
        }
    }//GEN-LAST:event_tarifPlasmaKeyReleased

    private void cmbcrPulang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbcrPulang1ActionPerformed
        if (cmbcrPulang1.getSelectedIndex() == 3) {
            if (kodePayor.equals("73") || kodePayor.equals("72") || kodePayor.equals("75")) {
                ChkSemuaPelengkap.setEnabled(false);
                ChkPemulasaran.setEnabled(false);
                ChkPlastik.setEnabled(false);
                ChkKantong.setEnabled(false);
                ChkDesinfekJen.setEnabled(false);
                ChkDesinfekMob.setEnabled(false);
                ChkPeti.setEnabled(false);
                ChkTranspot.setEnabled(false);
                
                ChkSemuaPelengkap.setSelected(false);
                ChkPemulasaran.setSelected(false);
                ChkPlastik.setSelected(false);
                ChkKantong.setSelected(false);
                ChkDesinfekJen.setSelected(false);
                ChkDesinfekMob.setSelected(false);
                ChkPeti.setSelected(false);
                ChkTranspot.setSelected(false);
            } else {
                ChkSemuaPelengkap.setEnabled(true);
                ChkPemulasaran.setEnabled(true);
                ChkPlastik.setEnabled(true);
                ChkKantong.setEnabled(true);
                ChkDesinfekJen.setEnabled(true);
                ChkDesinfekMob.setEnabled(true);
                ChkPeti.setEnabled(true);
                ChkTranspot.setEnabled(true);
                
                ChkSemuaPelengkap.setSelected(false);
                ChkPemulasaran.setSelected(false);
                ChkPlastik.setSelected(false);
                ChkKantong.setSelected(false);
                ChkDesinfekJen.setSelected(false);
                ChkDesinfekMob.setSelected(false);
                ChkPeti.setSelected(false);
                ChkTranspot.setSelected(false);
            }
        } else {
            ChkSemuaPelengkap.setEnabled(false);
            ChkPemulasaran.setEnabled(false);
            ChkPlastik.setEnabled(false);
            ChkKantong.setEnabled(false);
            ChkDesinfekJen.setEnabled(false);
            ChkDesinfekMob.setEnabled(false);
            ChkPeti.setEnabled(false);
            ChkTranspot.setEnabled(false);
          
            ChkSemuaPelengkap.setSelected(false);
            ChkPemulasaran.setSelected(false);
            ChkPlastik.setSelected(false);
            ChkKantong.setSelected(false);
            ChkDesinfekJen.setSelected(false);
            ChkDesinfekMob.setSelected(false);
            ChkPeti.setSelected(false);
            ChkTranspot.setSelected(false);
        }
    }//GEN-LAST:event_cmbcrPulang1ActionPerformed

    private void tarifPlasmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tarifPlasmaActionPerformed
        if (tarifPlasma.getText().trim().equals("")) {
            labeltarifPlasma.setText("0");
        } else {
            labeltarifPlasma.setText(Valid.SetAngka(Double.parseDouble(tarifPlasma.getText())));
        }
    }//GEN-LAST:event_tarifPlasmaActionPerformed

    private void ChkRawatIntensif1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkRawatIntensif1ActionPerformed
        tglIntubasi1.setEnabled(false);
        tglEkstubasi1.setEnabled(false);
        
        if (ChkRawatIntensif1.isSelected() == true) {
            losIntensif1.setEnabled(true);
            ventilator1.setEnabled(true);
            cmbVentilator1.setEnabled(true);
            cmbVentilator1.setSelectedIndex(1);
        } else {
            losIntensif1.setEnabled(false);
            losIntensif1.setText("0");
            ventilator1.setEnabled(false);
            ventilator1.setText("0");
            cmbVentilator1.setEnabled(false);
            cmbVentilator1.setSelectedIndex(1);
        }
    }//GEN-LAST:event_ChkRawatIntensif1ActionPerformed

    private void BtnDelUnggahPilihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDelUnggahPilihanActionPerformed
        for (i = 0; i < tbUnggahPilihan.getRowCount(); i++) {
            if (tbUnggahPilihan.getValueAt(i, 0).toString().equals("true")) {
                tabMode10.removeRow(i);
            }
        }
    }//GEN-LAST:event_BtnDelUnggahPilihanActionPerformed

    private void BtnUnggahDipilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUnggahDipilihActionPerformed
        nilaiUnggah = "";
        if (cmbUnggah.getSelectedItem().equals("Resume Medis")) {
            nilaiUnggah = "resume_medis";
        } else if (cmbUnggah.getSelectedItem().equals("Ruang Perawatan")) {
            nilaiUnggah = "ruang_rawat";
        } else if (cmbUnggah.getSelectedItem().equals("Hasil Laboratorium")) {
            nilaiUnggah = "laboratorium";
        } else if (cmbUnggah.getSelectedItem().equals("Hasil Radiologi")) {
            nilaiUnggah = "radiologi";
        } else if (cmbUnggah.getSelectedItem().equals("Hasil Penunjang Lainnya")) {
            nilaiUnggah = "penunjang_lain";
        } else if (cmbUnggah.getSelectedItem().equals("Resep Obat / Alkes")) {
            nilaiUnggah = "resep_obat";
        } else if (cmbUnggah.getSelectedItem().equals("Tagihan (Billing)")) {
            nilaiUnggah = "tagihan";
        } else if (cmbUnggah.getSelectedItem().equals("Kartu Identitas")) {
            nilaiUnggah = "kartu_identitas";
        } else if (cmbUnggah.getSelectedItem().equals("Dokumen KIPI")) {
            nilaiUnggah = "dokumen_kipi";
        } else if (cmbUnggah.getSelectedItem().equals("Lain-lain")) {
            nilaiUnggah = "lain_lain";
        } else {
            nilaiUnggah = "";
        }

        if (nilaiUnggah.equals("") || lokasiFile.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih salah satu jenis berkas & nama file pendukung klaim dengan benar...!!!!");
        } else {
            tabMode10.addRow(new Object[]{
                false,
                nilaiUnggah,
                file,
                lokasiFile.getText()
            });
            
            cmbUnggah.setSelectedIndex(0);
            lokasiFile.setText("");
        }
    }//GEN-LAST:event_BtnUnggahDipilihActionPerformed

    private void TabDiagnosa1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabDiagnosa1MouseClicked
        tampilDiagnosa();
    }//GEN-LAST:event_TabDiagnosa1MouseClicked

    private void TabDiagnosa2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabDiagnosa2MouseClicked
        tampilDiagnosa();
    }//GEN-LAST:event_TabDiagnosa2MouseClicked

    private void TabProsedur1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabProsedur1MouseClicked
        tampilProsedur();
    }//GEN-LAST:event_TabProsedur1MouseClicked

    private void TabProsedur2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabProsedur2MouseClicked
        tampilProsedur();
    }//GEN-LAST:event_TabProsedur2MouseClicked

    private void BtnValidasiTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnValidasiTBActionPerformed
        if (norawat.equals("") || noSEP.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu No. SEP...!!!!");
        } else if (noregTB.getText().equals("") || noregTB.getText().length() < 3) {
            JOptionPane.showMessageDialog(rootPane, "Isilah No. Registrasi Pasien TB dg. benar sesuai ketentuan...!!!!");
            noregTB.requestFocus();
        } else {
            if (cmbvalidTB.getSelectedIndex() == 0) {
                mbak_eka.validasiNomorTB(noSEP.getText(), noregTB.getText());
                stts_noreg.setText(Sequel.cariIsi("select ifnull(stts_validasi,'') from eklaim_sitb_validasi where no_sep='" + noSEP.getText() + "'"));
            } else if (cmbvalidTB.getSelectedIndex() == 1) {
                mbak_eka.BatalvalidasiNomorTB(noSEP.getText(), noregTB.getText());
                stts_noreg.setText(Sequel.cariIsi("select ifnull(stts_validasi,'') from eklaim_sitb_validasi where no_sep='" + noSEP.getText() + "'"));
            }
        }
    }//GEN-LAST:event_BtnValidasiTBActionPerformed

    private void ChkpasienTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkpasienTBActionPerformed
        stts_noreg.setText(Sequel.cariIsi("select ifnull(stts_validasi,'') from eklaim_sitb_validasi where no_sep='" + noSEP.getText() + "'"));
        
        if (ChkpasienTB.isSelected() == true) {
            noregTB.setEnabled(true);
            cmbvalidTB.setEnabled(true);
            noregTB.setText(Sequel.cariIsi("select ifnull(id_tb_03,'') from nomor_reg_tb where no_rkm_medis='" + norm.getText() + "'"));
            cmbvalidTB.setSelectedIndex(0);
            BtnValidasiTB.setEnabled(true);
        } else if (ChkpasienTB.isSelected() == false) {
            noregTB.setEnabled(false);
            cmbvalidTB.setEnabled(false);
            noregTB.setText("");
            cmbvalidTB.setSelectedIndex(0);
            BtnValidasiTB.setEnabled(false);
        }
    }//GEN-LAST:event_ChkpasienTBActionPerformed

    private void sistoleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sistoleKeyPressed
        Valid.pindah(evt, cmbPembiayaan, diastole);
    }//GEN-LAST:event_sistoleKeyPressed

    private void diastoleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diastoleKeyPressed
        Valid.pindah(evt, sistole, cmbcrPulang);
    }//GEN-LAST:event_diastoleKeyPressed

    private void cmbVentilatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVentilatorActionPerformed
        if (cmbVentilator.getSelectedIndex() == 1) {
            tglIntubasi.setEnabled(false);
            tglEkstubasi.setEnabled(false);
        } else if (cmbVentilator.getSelectedIndex() == 0) {
            tglIntubasi.setEnabled(true);
            tglIntubasi.requestFocus();
            tglEkstubasi.setEnabled(true);
        }
    }//GEN-LAST:event_cmbVentilatorActionPerformed

    private void cmbVentilator1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVentilator1ActionPerformed
        if (cmbVentilator1.getSelectedIndex() == 1) {
            tglIntubasi1.setEnabled(false);
            tglEkstubasi1.setEnabled(false);
        } else if (cmbVentilator1.getSelectedIndex() == 0) {
            tglIntubasi1.setEnabled(true);
            tglIntubasi1.requestFocus();
            tglEkstubasi1.setEnabled(true);
        }
    }//GEN-LAST:event_cmbVentilator1ActionPerformed

    private void TkntngDrhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkntngDrhKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TkntngDrhKeyPressed

    private void ChkApgarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkApgarActionPerformed
        if (ChkApgar.isSelected() == true) {
            cekApgar();
        } else if (ChkApgar.isSelected() == false) {
            cmbMnt1App.setSelectedIndex(0);
            cmbMnt1Pul.setSelectedIndex(0);
            cmbMnt1Gri.setSelectedIndex(0);
            cmbMnt1Act.setSelectedIndex(0);
            cmbMnt1Res.setSelectedIndex(0);
            cmbMnt5App.setSelectedIndex(0);
            cmbMnt5Pul.setSelectedIndex(0);
            cmbMnt5Gri.setSelectedIndex(0);
            cmbMnt5Act.setSelectedIndex(0);
            cmbMnt5Res.setSelectedIndex(0);
        }
    }//GEN-LAST:event_ChkApgarActionPerformed

    private void BtnBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaruActionPerformed
        wktkelahiran.setDate(new Date());
        cmbCaraLhr.setSelectedIndex(0);
        cmbLtkJanin.setSelectedIndex(0);
        cmbKondisi.setSelectedIndex(0);
        cmbBantuan.setSelectedIndex(0);
        cmbForcep.setSelectedIndex(0);
        cmbVacum.setSelectedIndex(0);
    }//GEN-LAST:event_BtnBaruActionPerformed

    private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahActionPerformed
        if (TUsiaHml.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data usia kehamilan belum terisi...!!!!");
            TUsiaHml.requestFocus();
        } else {
            if (TUsiaHml.getText().equals("")) {
                wktlahir = "0000-00-00 00:00:00";
            } else {
                wktlahir = Valid.SetTgl(wktkelahiran.getSelectedItem() + "") + " " + wktkelahiran.getSelectedItem().toString().substring(11, 19);
            }
            
            if (cmbCaraLhr.getSelectedIndex() == 0) {
                caraLhr = "";
            } else if (cmbCaraLhr.getSelectedIndex() == 1) {
                caraLhr = "vaginal";
            } else if (cmbCaraLhr.getSelectedIndex() == 2) {
                caraLhr = "sc";
            }
            
            if (cmbLtkJanin.getSelectedIndex() == 0) {
                ltkjnn = "";
            } else if (cmbLtkJanin.getSelectedIndex() == 1) {
                ltkjnn = "kepala";
            } else if (cmbLtkJanin.getSelectedIndex() == 2) {
                ltkjnn = "sungsang";
            } else if (cmbLtkJanin.getSelectedIndex() == 3) {
                ltkjnn = "lintang";
            }
            
            if (cmbKondisi.getSelectedIndex() == 0) {
                kndisi = "";
            } else if (cmbKondisi.getSelectedIndex() == 1) {
                kndisi = "livebirth";
            } else if (cmbKondisi.getSelectedIndex() == 2) {
                kndisi = "stillbirth";
            }
            
            if (cmbBantuan.getSelectedIndex() == 0) {
                dgBantuanManual = "";
            } else if (cmbBantuan.getSelectedIndex() == 1) {
                dgBantuanManual = "0";
            } else if (cmbBantuan.getSelectedIndex() == 2) {
                dgBantuanManual = "1";
            }
            
            if (cmbForcep.getSelectedIndex() == 0) {
                forcep = "";
            } else if (cmbForcep.getSelectedIndex() == 1) {
                forcep = "0";
            } else if (cmbForcep.getSelectedIndex() == 2) {
                forcep = "1";
            }
            
            if (cmbVacum.getSelectedIndex() == 0) {
                vacum = "";
            } else if (cmbVacum.getSelectedIndex() == 1) {
                vacum = "0";
            } else if (cmbVacum.getSelectedIndex() == 2) {
                vacum = "1";
            }
            
            tabMode17.addRow(new Object[]{
                tbPersalinan.getRowCount() + 1, 
                wktkelahiran.getSelectedItem(),
                cmbCaraLhr.getSelectedItem().toString(), 
                cmbLtkJanin.getSelectedItem().toString(),
                cmbKondisi.getSelectedItem().toString(), 
                cmbBantuan.getSelectedItem().toString(),
                cmbForcep.getSelectedItem().toString(), 
                cmbVacum.getSelectedItem().toString(),
                wktlahir, caraLhr, ltkjnn, kndisi, dgBantuanManual, forcep, vacum
            });
            
            BtnBaruActionPerformed(null);
        }
    }//GEN-LAST:event_BtnTambahActionPerformed

    private void TUsiaHmlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TUsiaHmlKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tgravida.requestFocus();
        }
    }//GEN-LAST:event_TUsiaHmlKeyPressed

    private void TgravidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgravidaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tpartus.requestFocus();
        }
    }//GEN-LAST:event_TgravidaKeyPressed

    private void TpartusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpartusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tabortus.requestFocus();
        }
    }//GEN-LAST:event_TpartusKeyPressed

    private void TabortusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TabortusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbOnset.requestFocus();
        }
    }//GEN-LAST:event_TabortusKeyPressed

    private void TabJaminanJKNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabJaminanJKNMouseClicked
        if (TabJaminanJKN.getSelectedIndex() == 1) {
            ChkApgar.requestFocus();
        } else if (TabJaminanJKN.getSelectedIndex() == 2) {
            TUsiaHml.requestFocus();
        } else if (TabJaminanJKN.getSelectedIndex() == 3) {            
            tbPersalinan.requestFocus();
        }
    }//GEN-LAST:event_TabJaminanJKNMouseClicked

    private void ppHapusDeliveryBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppHapusDeliveryBtnPrintActionPerformed
        if (tbPersalinan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Tabel masih kosong, tidak ada data yang bisa dihapus...!!!!");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus semua..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                Valid.tabelKosong(tabMode17);
                BtnBaruActionPerformed(null);
            }
        }        
    }//GEN-LAST:event_ppHapusDeliveryBtnPrintActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            PengajuanKlaimINACBGrz dialog = new PengajuanKlaimINACBGrz(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAddEpisod;
    private widget.Button BtnAddUnggah;
    private widget.Button BtnBaru;
    private widget.Button BtnDelEpisod;
    private widget.Button BtnDelUnggah;
    private widget.Button BtnDelUnggahPilihan;
    private widget.Button BtnEditKlaim;
    private widget.Button BtnFinal;
    private widget.Button BtnGruper;
    private widget.Button BtnGruper2;
    private widget.Button BtnGruperStage;
    private widget.Button BtnGruperStage1;
    private widget.Button BtnHapusKlaim;
    private widget.Button BtnKeluar;
    private widget.Button BtnKirimOnline;
    private widget.Button BtnRefres;
    private widget.Button BtnRefres1;
    private widget.Button BtnTambah;
    private widget.Button BtnUnggahDipilih;
    private widget.Button BtnUploadFile;
    private widget.Button BtnValidasiTB;
    private widget.CekBox ChkAPTT;
    private widget.CekBox ChkAlbumin;
    private widget.CekBox ChkAnalisa;
    private widget.CekBox ChkAnti;
    private widget.CekBox ChkApgar;
    private widget.CekBox ChkAsam;
    private widget.CekBox ChkCRP;
    private widget.CekBox ChkDesinfekJen;
    private widget.CekBox ChkDesinfekMob;
    private widget.CekBox ChkDimer;
    private widget.CekBox ChkKantong;
    private widget.CekBox ChkKelasEksekutif;
    private widget.CekBox ChkKultur;
    private widget.CekBox ChkNaikTurun;
    private widget.CekBox ChkPT;
    private widget.CekBox ChkPemulasaran;
    private widget.CekBox ChkPeti;
    private widget.CekBox ChkPlastik;
    private widget.CekBox ChkProcal;
    private widget.CekBox ChkRawatIntensif;
    private widget.CekBox ChkRawatIntensif1;
    private widget.CekBox ChkSemuaFaktor;
    private widget.CekBox ChkSemuaPelengkap;
    private widget.CekBox ChkThorax;
    private widget.CekBox ChkTranspot;
    private widget.CekBox ChkWaktu;
    private widget.CekBox ChkpasienTB;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.PanelBiasa FormInput2;
    private widget.PanelBiasa FormInput3;
    private widget.PanelBiasa FormInput4;
    private javax.swing.JPopupMenu Popup1;
    private javax.swing.JPopupMenu Popup2;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll11;
    private widget.ScrollPane Scroll12;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    private widget.ScrollPane ScrollHasil1;
    private widget.ScrollPane ScrollHasil2;
    private widget.ScrollPane ScrollHasil3;
    private widget.ScrollPane ScrollHasil4;
    private widget.ScrollPane ScrollHasil5;
    private widget.ScrollPane ScrollHasil6;
    private widget.TextBox TUsiaHml;
    public javax.swing.JTabbedPane TabDiagnosa1;
    public javax.swing.JTabbedPane TabDiagnosa2;
    public javax.swing.JTabbedPane TabJaminanJKN;
    public javax.swing.JTabbedPane TabProsedur1;
    public javax.swing.JTabbedPane TabProsedur2;
    public javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Tabortus;
    private widget.TextBox Tgravida;
    private widget.TextBox TkntngDrh;
    private widget.TextBox Tpartus;
    private widget.Label alkes;
    private widget.Label alkes1;
    private widget.Label bmhp;
    private widget.Label bmhp1;
    private widget.TextBox brtlhr;
    private widget.TextBox brtlhr1;
    private widget.ComboBox cmbBantuan;
    private widget.ComboBox cmbCO;
    private widget.ComboBox cmbCOB;
    private widget.ComboBox cmbCaraLhr;
    private widget.ComboBox cmbCrMasuk;
    private widget.ComboBox cmbCrMasuk1;
    private widget.ComboBox cmbDializer;
    private widget.ComboBox cmbEpisod;
    private widget.ComboBox cmbForcep;
    private widget.ComboBox cmbID;
    private widget.ComboBox cmbIsolasi;
    private widget.ComboBox cmbKH;
    private widget.ComboBox cmbKP;
    private widget.ComboBox cmbKelainan;
    private widget.ComboBox cmbKomor;
    private widget.ComboBox cmbKondisi;
    private widget.ComboBox cmbKriteria;
    private widget.ComboBox cmbLtkJanin;
    private widget.ComboBox cmbMnt1Act;
    private widget.ComboBox cmbMnt1App;
    private widget.ComboBox cmbMnt1Gri;
    private widget.ComboBox cmbMnt1Pul;
    private widget.ComboBox cmbMnt1Res;
    private widget.ComboBox cmbMnt5Act;
    private widget.ComboBox cmbMnt5App;
    private widget.ComboBox cmbMnt5Gri;
    private widget.ComboBox cmbMnt5Pul;
    private widget.ComboBox cmbMnt5Res;
    private widget.ComboBox cmbOnset;
    private widget.ComboBox cmbPembiayaan;
    private widget.ComboBox cmbRS;
    private widget.ComboBox cmbStatus;
    private widget.ComboBox cmbUnggah;
    private widget.ComboBox cmbVacum;
    private widget.ComboBox cmbVentilator;
    private widget.ComboBox cmbVentilator1;
    private widget.ComboBox cmbcrPulang;
    private widget.ComboBox cmbcrPulang1;
    private widget.ComboBox cmbvalidTB;
    private widget.TextBox diastole;
    private widget.Label dpjp;
    private widget.Label dpjp1;
    private widget.TextBox hari;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame11;
    private widget.InternalFrame internalFrame12;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame14;
    private widget.InternalFrame internalFrame15;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel11;
    private widget.Label jLabel110;
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
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
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
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel67;
    private widget.Label jLabel68;
    private widget.Label jLabel69;
    private widget.Label jLabel7;
    private widget.Label jLabel70;
    private widget.Label jLabel71;
    private widget.Label jLabel72;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel88;
    private widget.Label jLabel89;
    private widget.Label jLabel90;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private widget.Label jk;
    private widget.Label jk1;
    private widget.Label kam;
    private widget.Label kam1;
    private widget.Label kep;
    private widget.Label kep7;
    private widget.Label kon;
    private widget.Label kon7;
    private widget.TextBox kronik;
    private widget.TextBox kronik1;
    private widget.Label lab;
    private widget.Label lab1;
    private widget.Label labelHRinten;
    private widget.Label labelHRinten1;
    private widget.Label labelHRinten2;
    private widget.Label labelHRlm;
    private widget.Label labelHRlm1;
    private widget.Label labelHRlm11;
    private widget.Label labelHRlm12;
    private widget.Label labelHRlm13;
    private widget.Label labelHRlm14;
    private widget.Label labelHRlm15;
    private widget.Label labelHRlm16;
    private widget.Label labelHRlm17;
    private widget.Label labelHRlm18;
    private widget.Label labelHRlm19;
    private widget.Label labelHRlm2;
    private widget.Label labelHRlm20;
    private widget.Label labelHRlm21;
    private widget.Label labelHRlm22;
    private widget.Label labelHRlm23;
    private widget.Label labelHRlm24;
    private widget.Label labelHRlm25;
    private widget.Label labelHRlm26;
    private widget.Label labelHRlm27;
    private widget.Label labelHRlm28;
    private widget.Label labelHRlm29;
    private widget.Label labelHRlm3;
    private widget.Label labelHRlm30;
    private widget.Label labelHRlm31;
    private widget.Label labelHRlm32;
    private widget.Label labelHRlm33;
    private widget.Label labelHRlm34;
    private widget.Label labelHRlm4;
    private widget.Label labelHRlm5;
    private widget.Label labelHRlm6;
    private widget.Label labelHRlm7;
    private widget.Label labelHRlm8;
    private widget.Label labelLM;
    private widget.Label labelLM1;
    private widget.Label labelLM2;
    private widget.Label labelLOS;
    private widget.Label labelLOS1;
    private widget.Label labelRI;
    private widget.Label labelRI1;
    private widget.Label labelRI2;
    private widget.Label labelRI3;
    private widget.Label labelRP;
    private widget.Label labelRP2;
    private widget.Label labelTarifEx;
    private widget.Label labelTarifEx2;
    private widget.Label labelTarifRS;
    private widget.Label labelTarifRS1;
    private widget.Label labelhak1;
    private widget.Label labelhasilG;
    private widget.Label labelhasilG1;
    private widget.Label labelhasilG2;
    private widget.Label labelhasilG3;
    private widget.Label labeljam;
    private widget.Label labeljam2;
    private widget.Label labeljam3;
    private widget.Label labeljam4;
    private widget.Label labelklsHAK;
    private widget.Label labelklsHAK1;
    private widget.Label labelklspel;
    private widget.Label labelklspel1;
    private widget.Label labelklspel2;
    private widget.Label labelklspel3;
    private widget.Label labelklspel4;
    private widget.Label labelklspel5;
    private widget.Label labelnmIbu;
    private widget.Label labelrespon;
    private widget.Label labelrespon1;
    private widget.Label labelrwt;
    private widget.Label labelrwt1;
    private widget.Label labeltambahan;
    private widget.Label labeltarif;
    private widget.Label labeltarifPlasma;
    private widget.Label labelventi;
    private widget.Label labelventi1;
    private widget.Label labelventi2;
    private widget.TextBox lokasiFile;
    private widget.TextBox losIntensif;
    private widget.TextBox losIntensif1;
    private widget.TextBox losNaikKls;
    private widget.Label nikPetugas;
    private widget.Label nikPetugas1;
    private widget.Label nmJaminan;
    private widget.Label nmPasien;
    private widget.Label nmPasien1;
    private widget.Label nmPetugas;
    private widget.Label nmPetugas1;
    private widget.TextBox noID;
    private widget.TextBox noKlaim;
    private widget.Label noPeserta;
    private widget.TextBox noSEP;
    private widget.TextBox noregTB;
    private widget.Label norm;
    private widget.Label norm1;
    private widget.Label obat;
    private widget.Label obat1;
    private widget.Label oke;
    private widget.Label oke1;
    private widget.Label okr;
    private widget.Label okr1;
    private widget.panelisi panelGlass8;
    private widget.Label pb;
    private widget.Label pb1;
    private widget.Label pd;
    private widget.Label pd1;
    private widget.Label pen;
    private widget.Label pen1;
    private widget.Label pnb;
    private widget.Label pnb7;
    private javax.swing.JMenuItem ppDiagnosa;
    private javax.swing.JMenuItem ppHapusDelivery;
    private widget.Label pros_inadrg1;
    private widget.Label pros_inadrg2;
    private widget.Label rad;
    private widget.Label rad1;
    private widget.Label reh;
    private widget.Label reh7;
    private widget.Label ri;
    private widget.Label ri1;
    private widget.Label sa;
    private widget.Label sa1;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollInput1;
    private javax.swing.JSeparator separatHasil;
    private javax.swing.JSeparator separatHasil1;
    private widget.TextBox sistole;
    private widget.Label stts_noreg;
    private widget.TextBox subakut;
    private widget.TextBox subakut1;
    private widget.Label ta;
    private widget.Label ta1;
    private widget.TextBox tarifPlasma;
    private widget.TextBox tarifPoliExe;
    private widget.Table tbDiagnosaPasie3;
    private widget.Table tbDiagnosaPasie4;
    private widget.Table tbDiagnosaPasien1;
    private widget.Table tbDiagnosaPasien2;
    private widget.Table tbEpisod;
    private widget.Table tbHasil1;
    private widget.Table tbHasil2;
    private widget.Table tbHasil3;
    private widget.Table tbHasil4;
    private widget.Table tbPersalinan;
    private widget.Table tbRespon1;
    private widget.Table tbRespon2;
    private widget.Table tbTindakanPasien1;
    private widget.Table tbTindakanPasien2;
    private widget.Table tbTindakanPasien3;
    private widget.Table tbTindakanPasien4;
    private widget.Table tbUnggah;
    private widget.Table tbUnggahPilihan;
    private widget.Tanggal tglDiagnosa;
    private widget.Tanggal tglEkstubasi;
    private widget.Tanggal tglEkstubasi1;
    private widget.Tanggal tglIntubasi;
    private widget.Tanggal tglIntubasi1;
    private widget.Label tglLhr;
    private widget.Label tglLhr1;
    private widget.Label tglSEP;
    private widget.Label umur;
    private widget.Label umur1;
    private widget.TextBox ventilator;
    private widget.TextBox ventilator1;
    private widget.Label wktMasuk;
    private widget.Label wktMasuk1;
    private widget.Label wktPulang;
    private widget.Label wktPulang1;
    private widget.Tanggal wktkelahiran;
    // End of variables declaration//GEN-END:variables

    private void cekDATA() {
        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            if (cmbMnt1App.getSelectedIndex() == 0) {
                mnt1APP = "";
            } else {
                mnt1APP = cmbMnt1App.getSelectedItem().toString();
            }
            
            if (cmbMnt1Pul.getSelectedIndex() == 0) {
                mnt1PUL = "";
            } else {
                mnt1PUL = cmbMnt1Pul.getSelectedItem().toString();
            }
            
            if (cmbMnt1Gri.getSelectedIndex() == 0) {
                mnt1GRI = "";
            } else {
                mnt1GRI = cmbMnt1Gri.getSelectedItem().toString();
            }
            
            if (cmbMnt1Act.getSelectedIndex() == 0) {
                mnt1ACT = "";
            } else {
                mnt1ACT = cmbMnt1Act.getSelectedItem().toString();
            }
            
            if (cmbMnt1Res.getSelectedIndex() == 0) {
                mnt1RES = "";
            } else {
                mnt1RES = cmbMnt1Res.getSelectedItem().toString();
            }            
            
            if (cmbMnt5App.getSelectedIndex() == 0) {
                mnt5APP = "";
            } else {
                mnt5APP = cmbMnt5App.getSelectedItem().toString();
            }
            
            if (cmbMnt5Pul.getSelectedIndex() == 0) {
                mnt5PUL = "";
            } else {
                mnt5PUL = cmbMnt5Pul.getSelectedItem().toString();
            }
            
            if (cmbMnt5Gri.getSelectedIndex() == 0) {
                mnt5GRI = "";
            } else {
                mnt5GRI = cmbMnt5Gri.getSelectedItem().toString();
            }
            
            if (cmbMnt5Act.getSelectedIndex() == 0) {
                mnt5ACT = "";
            } else {
                mnt5ACT = cmbMnt5Act.getSelectedItem().toString();
            }
            
            if (cmbMnt5Res.getSelectedIndex() == 0) {
                mnt5RES = "";
            } else {
                mnt5RES = cmbMnt5Res.getSelectedItem().toString();
            }            
            
            if (tbPersalinan.getRowCount() == 0) {
                cekDeliveri = "tabel_kosong";            
            } else {
                cekDeliveri = "ada_datanya";                
            }
            
            if (cmbOnset.getSelectedIndex() == 0) {
                onset = "";
            } else if (cmbOnset.getSelectedIndex() == 1) {
                onset = "spontan";
            } else if (cmbOnset.getSelectedIndex() == 2) {
                onset = "induksi";
            } else if (cmbOnset.getSelectedIndex() == 3) {
                onset = "non_spontan_non_induksi";
            }
            
            if (cmbVentilator.getSelectedIndex() == 1) {
                nilaiVenti = "0";
                tglIntu = "0000-00-00 00:00:00";
                tglEkstu = "0000-00-00 00:00:00";
            } else if (cmbVentilator.getSelectedIndex() == 0) {
                nilaiVenti = "1";
                tglIntu = Valid.SetTgl(tglIntubasi.getSelectedItem() + "") + " " + tglIntubasi.getSelectedItem().toString().substring(11, 19);
                tglEkstu = Valid.SetTgl(tglEkstubasi.getSelectedItem() + "") + " " + tglEkstubasi.getSelectedItem().toString().substring(11, 19);
            }
            
            if (cmbDializer.getSelectedIndex() == 0) {
                dializer = "0";
            } else if (cmbDializer.getSelectedIndex() == 1) {
                dializer = "1";
            }
            
            if (TkntngDrh.getText().equals("")) {
                TkntngDrh.setText("0");
            } else {
                TkntngDrh.setText(TkntngDrh.getText());
            }
            
            if (cmbKH.getSelectedIndex() == 0) {
                kls = "3";
            } else if (cmbKH.getSelectedIndex() == 1) {
                kls = "2";
            } else if (cmbKH.getSelectedIndex() == 2) {
                kls = "1";
            }
            
            if (subakut.getText().equals("")) {
                subakut.setText("0");
            }
            
            if (kronik.getText().equals("")) {
                kronik.setText("0");
            }
            
            //tekanan darah
            if (sistole.getText().equals("")) {
                sistole.setText("0");
            }            
            if (diastole.getText().equals("")) {
                diastole.setText("0");
            }
            
            if (cmbcrPulang.getSelectedItem().equals("Dirujuk")) {
                kdPulang = "2";
            } else if (cmbcrPulang.getSelectedItem().equals("Atas permintaan sendiri")) {
                kdPulang = "3";
            } else if (cmbcrPulang.getSelectedItem().equals("Meninggal")) {
                kdPulang = "4";
            } else if (cmbcrPulang.getSelectedItem().equals("Atas persetujuan dokter")) {
                kdPulang = "1";
            } else if (cmbcrPulang.getSelectedItem().equals("Lain-lain")) {
                kdPulang = "5";
            } 

            if (ChkRawatIntensif.isSelected() == true) {
                icuindikator = "1";
            } else {
                icuindikator = "0";
            }

            if (ChkNaikTurun.isSelected() == true) {
                naikTurunkls = "1";
            } else {
                naikTurunkls = "0";
            }

            if (cmbKP.getSelectedItem().equals("Kelas 3")) {
                nilaiKP = "kelas_3";
            } else if (cmbKP.getSelectedItem().equals("Kelas 2")) {
                nilaiKP = "kelas_2";
            } else if (cmbKP.getSelectedItem().equals("Kelas 1")) {
                nilaiKP = "kelas_1";
            } else if (cmbKP.getSelectedItem().equals("Kelas VIP")) {
                nilaiKP = "vip";
            } else if (cmbKP.getSelectedItem().equals("Kelas VVIP")) {
                nilaiKP = "vvip";
            }
            
            //pembiayaan
            if (cmbPembiayaan.getSelectedIndex() == 0) {
                nilaiPembiayaan = "";
            } else if (cmbPembiayaan.getSelectedIndex() == 1) {
                nilaiPembiayaan = "peserta";
            } else if (cmbPembiayaan.getSelectedIndex() == 2) {
                nilaiPembiayaan = "pemberi_kerja";
            } else if (cmbPembiayaan.getSelectedIndex() == 3) {
                nilaiPembiayaan = "asuransi_tambahan";
            }

            if ((Double.parseDouble(losNaikKls.getText())) <= 3) {
                persenNaikKls = Sequel.cariIsi("select selisih_tarif_bpjs1 from set_tarif");
            } else if ((Double.parseDouble(losNaikKls.getText())) >= 4) {
                persenNaikKls = Sequel.cariIsi("select selisih_tarif_bpjs2 from set_tarif");
            } else {
                persenNaikKls = "0";
            }

            if (ChkKelasEksekutif.isSelected() == true) {
                cmbKH.setSelectedIndex(2);
                kls = "1";
            }

            if (tarifPoliExe.getText().trim().equals("")) {
                trfPoliEx = "0";
            } else {
                trfPoliEx = tarifPoliExe.getText();
            }
            
            //cara masuk
            if (cmbCrMasuk.getSelectedIndex() == 0) {
                caramasuk = "gp";
            } else if (cmbCrMasuk.getSelectedIndex() == 1) {
                caramasuk = "hosp-trans";
            } else if (cmbCrMasuk.getSelectedIndex() == 2) {
                caramasuk = "mp";
            } else if (cmbCrMasuk.getSelectedIndex() == 3) {
                caramasuk = "outp";
            } else if (cmbCrMasuk.getSelectedIndex() == 4) {
                caramasuk = "inp";
            } else if (cmbCrMasuk.getSelectedIndex() == 5) {
                caramasuk = "emd";
            } else if (cmbCrMasuk.getSelectedIndex() == 6) {
                caramasuk = "born";
            } else if (cmbCrMasuk.getSelectedIndex() == 7) {
                caramasuk = "nursing";
            } else if (cmbCrMasuk.getSelectedIndex() == 8) {
                caramasuk = "psych";
            } else if (cmbCrMasuk.getSelectedIndex() == 9) {
                caramasuk = "rehab";
            } else if (cmbCrMasuk.getSelectedIndex() == 10) {
                caramasuk = "other";
            }

        } else {
            if (cmbVentilator1.getSelectedIndex() == 1) {
                nilaiVenti = "0";
                tglIntu = "0000-00-00 00:00:00";
                tglEkstu = "0000-00-00 00:00:00";
            } else if (cmbVentilator1.getSelectedIndex() == 0) {
                nilaiVenti = "1";
                tglIntu = Valid.SetTgl(tglIntubasi1.getSelectedItem() + "") + " " + tglIntubasi1.getSelectedItem().toString().substring(11, 19);
                tglEkstu = Valid.SetTgl(tglEkstubasi1.getSelectedItem() + "") + " " + tglEkstubasi1.getSelectedItem().toString().substring(11, 19);
            }
            
            if (subakut1.getText().equals("")) {
                subakut1.setText("0");
            }
            
            if (kronik1.getText().equals("")) {
                kronik1.setText("0");
            }
            
            if (ChkRawatIntensif1.isSelected() == true) {
                icuindikator = "1";
            } else {
                icuindikator = "0";
            }
            
            if (cmbIsolasi.getSelectedItem().equals("Tidak")) {
                nilaiIsolasi = "0";
            } else if (cmbIsolasi.getSelectedItem().equals("Ya")) {
                nilaiIsolasi = "1";
            }
                        
            if (cmbcrPulang1.getSelectedItem().equals("Dirujuk")) {
                kdPulang = "2";
            } else if (cmbcrPulang1.getSelectedItem().equals("Atas permintaan sendiri")) {
                kdPulang = "3";
            } else if (cmbcrPulang1.getSelectedItem().equals("Meninggal")) {
                kdPulang = "4";
            } else if (cmbcrPulang1.getSelectedItem().equals("Atas persetujuan dokter")) {
                kdPulang = "1";
            } else if (cmbcrPulang1.getSelectedItem().equals("Lain-lain")) {
                kdPulang = "5";
            }
            
            if (cmbCrMasuk1.getSelectedIndex() == 0) {
                caramasuk = "gp";
            } else if (cmbCrMasuk1.getSelectedIndex() == 1) {
                caramasuk = "hosp-trans";
            } else if (cmbCrMasuk1.getSelectedIndex() == 2) {
                caramasuk = "mp";
            } else if (cmbCrMasuk1.getSelectedIndex() == 3) {
                caramasuk = "outp";
            } else if (cmbCrMasuk1.getSelectedIndex() == 4) {
                caramasuk = "inp";
            } else if (cmbCrMasuk1.getSelectedIndex() == 5) {
                caramasuk = "emd";
            } else if (cmbCrMasuk1.getSelectedIndex() == 6) {
                caramasuk = "born";
            } else if (cmbCrMasuk1.getSelectedIndex() == 7) {
                caramasuk = "nursing";
            } else if (cmbCrMasuk1.getSelectedIndex() == 8) {
                caramasuk = "psych";
            } else if (cmbCrMasuk1.getSelectedIndex() == 9) {
                caramasuk = "rehab";
            } else if (cmbCrMasuk1.getSelectedIndex() == 10) {
                caramasuk = "other";
            }

            if (cmbCO.getSelectedItem().equals("Ya")) {
                cekCI = "1";
            } else {
                cekCI = "0";
            }

            if (cmbRS.getSelectedItem().equals("Ya")) {
                cekRS = "1";
            } else {
                cekRS = "0";
            }
            
            if (tarifPlasma.getText().trim().equals("")) {
                trfPlasma = "0";
            } else {
                trfPlasma = tarifPlasma.getText();
            }

            if (cmbKomor.getSelectedItem().equals("Tidak Ada")) {
                cekKOM = "0";
            } else {
                cekKOM = "1";
            }

            if (kodePayor.equals("73")) {
                cekSTATUS = "-";
                nilaiKriteria = "#";
                if (cmbKelainan.getSelectedItem().equals("Tanpa Kelainan")) {
                    nilaiKelainan = "1";
                } else if (cmbKelainan.getSelectedItem().equals("Dengan Kelainan")) {
                    nilaiKelainan = "2";
                }
            } else if (kodePayor.equals("72") || kodePayor.equals("75")) {
                cekSTATUS = "-";
                nilaiKriteria = "#";
                nilaiKelainan = "0";
            } else {
                nilaiKelainan = "0";                
                if (cmbKriteria.getSelectedItem().equals("Kriteria A")) {
                    nilaiKriteria = "A";
                } else if (cmbKriteria.getSelectedItem().equals("Kriteria B")) {
                    nilaiKriteria = "B";
                } else if (cmbKriteria.getSelectedItem().equals("Kriteria C")) {
                    nilaiKriteria = "C";
                }
                
                if (cmbStatus.getSelectedItem().equals("Suspek")) {
                    cekSTATUS = "4";
                } else if (cmbStatus.getSelectedItem().equals("Probabel")) {
                    cekSTATUS = "5";
                } else if (cmbStatus.getSelectedItem().equals("Terkonfirmasi")) {
                    cekSTATUS = "3";
                } else if (cmbStatus.getSelectedItem().equals("ODP")) {
                    cekSTATUS = "1";
                } else if (cmbStatus.getSelectedItem().equals("PDP")) {
                    cekSTATUS = "2";
                }
            }

            if (cmbID.getSelectedItem().equals("NIK")) {
                cekID = "nik";
            } else if (cmbID.getSelectedItem().equals("Kartu Izin Tinggal Terbatas (Kitas)")) {
                cekID = "kitas";
            } else if (cmbID.getSelectedItem().equals("Paspor")) {
                cekID = "paspor";
            } else if (cmbID.getSelectedItem().equals("Kartu JKN")) {
                cekID = "kartu_jkn";
            } else if (cmbID.getSelectedItem().equals("Kartu Keluarga (KK)")) {
                cekID = "kk";
            } else if (cmbID.getSelectedItem().equals("Surat UNHCR")) {
                cekID = "unhcr";
            } else if (cmbID.getSelectedItem().equals("Surat Kelurahan")) {
                cekID = "kelurahan";
            } else if (cmbID.getSelectedItem().equals("Surat Dinas Sosial")) {
                cekID = "dinsos";
            } else if (cmbID.getSelectedItem().equals("Surat Dinas Kesehatan")) {
                cekID = "dinkes";
            } else if (cmbID.getSelectedItem().equals("Surat Jaminan Pelayanan")) {
                cekID = "sjp";
            } else if (cmbID.getSelectedItem().equals("Lainnya")) {
                cekID = "lainnya";
            } else if (cmbID.getSelectedItem().equals("Nomor Klaim Ibu")) {
                cekID = "klaim_ibu";
            }

            //khusus pasien meninggal
            if (ChkPemulasaran.isSelected() == true) {
                cekPEMU = "1";
            } else {
                cekPEMU = "0";
            }

            if (ChkPlastik.isSelected() == true) {
                cekPLAS = "1";
            } else {
                cekPLAS = "0";
            }

            if (ChkKantong.isSelected() == true) {
                cekKAN = "1";
            } else {
                cekKAN = "0";
            }

            if (ChkDesinfekJen.isSelected() == true) {
                cekDESJEN = "1";
            } else {
                cekDESJEN = "0";
            }

            if (ChkDesinfekMob.isSelected() == true) {
                cekDESMOB = "1";
            } else {
                cekDESMOB = "0";
            }

            if (ChkPeti.isSelected() == true) {
                cekPET = "1";
            } else {
                cekPET = "0";
            }

            if (ChkTranspot.isSelected() == true) {
                cekTRA = "1";
            } else {
                cekTRA = "0";
            }
            
            //faktor pengurang
            if (ChkAsam.isSelected() == true) {
                cekASAM = "1";
            } else {
                cekASAM = "0";
            }

            if (ChkKultur.isSelected() == true) {
                cekKUL = "1";
            } else {
                cekKUL = "0";
            }

            if (ChkAPTT.isSelected() == true) {
                cekAPTT = "1";
            } else {
                cekAPTT = "0";
            }

            if (ChkAnalisa.isSelected() == true) {
                cekANAL = "1";
            } else {
                cekANAL = "0";
            }

            if (ChkProcal.isSelected() == true) {
                cekPRO = "1";
            } else {
                cekPRO = "0";
            }

            if (ChkDimer.isSelected() == true) {
                cekDIM = "1";
            } else {
                cekDIM = "0";
            }

            if (ChkWaktu.isSelected() == true) {
                cekWAK = "1";
            } else {
                cekWAK = "0";
            }

            if (ChkAlbumin.isSelected() == true) {
                cekALBU = "1";
            } else {
                cekALBU = "0";
            }

            if (ChkCRP.isSelected() == true) {
                cekCRP = "1";
            } else {
                cekCRP = "0";
            }

            if (ChkPT.isSelected() == true) {
                cekPT = "1";
            } else {
                cekPT = "0";
            }

            if (ChkAnti.isSelected() == true) {
                cekANT = "1";
            } else {
                cekANT = "0";
            }

            if (ChkThorax.isSelected() == true) {
                cekTHO = "1";
            } else {
                cekTHO = "0";
            }
        }
    }

    public void setKlaim(String norw, String nosep, String jnsKlaim, String payor, String nmIbu, String tglsep) {
        norawat = norw;
        noSEP.setText(nosep);
        jknya = "";
        tgllhrnya = "";
        jpel = "";
        kls = "";
        datanya = "";
        kodePayor = payor;
        nmJaminan.setText(jnsKlaim);
        ibunya = nmIbu;
        tglREG = "";
        tglSEP.setText("");

        try {
            //kalau kode payor 3 adalah JKN
            if (kodePayor.equals("3")) {
                ps = koneksi.prepareStatement("select bs.nomr, REPLACE(REPLACE(bs.nama_pasien,'\\\\',''),'''','') nama_pasien, "
                        + "p.jk jkel, bs.tanggal_lahir, UPPER(DATE_FORMAT(bs.tanggal_lahir,'%d %b %Y')) tgllhr, bs.tglsep, "
                        + "bs.no_kartu, bs.jnspelayanan, bs.klsrawat from bridging_sep bs "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=bs.nomr where bs.no_sep='" + nosep + "' and bs.tglsep='" + tglsep + "'");

            } else {
                ps = koneksi.prepareStatement("SELECT rp.no_rawat, rp.tgl_registrasi, p.no_ktp, p.no_peserta, p.no_rkm_medis, "
                        + "p.nm_pasien, if(p.jk='L','1','2') jkel, IF(rp.status_lanjut='Ralan','2','1') jRawat, "
                        + "p.tgl_lahir, UPPER(DATE_FORMAT(p.tgl_lahir,'%d %b %Y')) tgllhir FROM reg_periksa rp "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis WHERE rp.no_rawat='" + norw + "'");
            }
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    //kalau kode payor 3 adalah JKN
                    if (kodePayor.equals("3")) {
                        norm.setText(rs.getString("nomr"));
                        nmPasien.setText(rs.getString("nama_pasien"));
                        jknya = rs.getString("jkel");
                        tgllhrnya = rs.getString("tanggal_lahir");
                        tglLhr.setText(rs.getString("tgllhr"));
                        noPeserta.setText(rs.getString("no_kartu"));
                        jpel = rs.getString("jnspelayanan");
//                        tglREG = Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norw + "'");
                        tglREG = rs.getString("tglsep");
                        tglSEP.setText(rs.getString("tglsep"));
                      
                        if (jpel.equals("2")) {
                            kls = "3";
                        } else {
                            kls = rs.getString("klsrawat");
                        }

                    } else {
                        norm1.setText(rs.getString("no_rkm_medis"));
                        nmPasien1.setText(rs.getString("nm_pasien"));
                        jknyaCOVID = rs.getString("jkel");
                        tgllhrnyaCOVID = rs.getString("tgl_lahir");
                        tglLhr1.setText(rs.getString("tgllhir"));
                        jpel = rs.getString("jRawat");
                        
                        if (nmJaminan.getText().equals("JAMINAN BAYI BARU LAHIR")) {
                            cmbID.setSelectedIndex(11);                            
                            noID.setText(nosep);
                            labelnmIbu.setText("Nama Ibu : " + nmIbu);
                        } else {
                            cmbID.setSelectedIndex(0);
                            noID.setText(rs.getString("no_ktp"));
                        }                        
                    }
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

        sts_umur = Sequel.cariIsi("select sttsumur from reg_periksa where no_rawat='" + norw + "'");
        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            if (akses.getkode().equals("Admin Utama")) {
                nmPetugas.setText("Admin Utama");
                nikPetugas.setText("1");
            } else {
                nmPetugas.setText(Sequel.cariIsi("select nama from petugas WHERE nip='" + akses.getkode() + "'"));
                nikPetugas.setText(Sequel.cariIsi("SELECT no_ik FROM inacbg_coder_nik WHERE nik='" + akses.getkode() + "'"));
            }

            labelhasilG.setText("Hasil Grouper : " + Sequel.cariIsi("select if(klaim_final='Belum','',klaim_final) from eklaim_new_claim where no_sep='" + nosep + "'"));
            labeltambahan.setText("Tambahan biaya yang dibayar pasien naik kelas : Rp. "
                    + Sequel.cariIsi("SELECT format(ifnull(add_payment_amt,'0'),0) tambhn_biaya FROM eklaim_grouping WHERE no_sep='" + nosep + "'"));
            ta.setText("0");
            alkes.setText("0");
            okr.setText("0");
            bmhp.setText("0");
            pen.setText("0");
            pd.setText("0");
            oke.setText("0");
            sa.setText("0");

            if (tabMode3.getRowCount() == 0) {
                BtnGruperStage.setEnabled(false);
            } else {
                BtnGruperStage.setEnabled(true);
            }

        } else {
            if (akses.getkode().equals("Admin Utama")) {
                nmPetugas1.setText("Admin Utama");
                nikPetugas1.setText("1");
            } else {
                nmPetugas1.setText(Sequel.cariIsi("select nama from petugas WHERE nip='" + akses.getkode() + "'"));
                nikPetugas1.setText(Sequel.cariIsi("SELECT no_ik FROM inacbg_coder_nik WHERE nik='" + akses.getkode() + "'"));
            }

            labelhasilG2.setText("Hasil Grouper : " + Sequel.cariIsi("select if(klaim_final='Belum','',klaim_final) from eklaim_new_claim "
                    + "where no_sep='" + Sequel.cariIsi("SELECT claim_number FROM eklaim_generate_claim where no_rawat='" + norw + "'") + "'"));
            ta1.setText("0");
            alkes1.setText("0");
            okr1.setText("0");
            bmhp1.setText("0");
            pen1.setText("0");
            pd1.setText("0");
            oke1.setText("0");
            sa1.setText("0");

            if (tabMode8.getRowCount() == 0) {
                BtnGruperStage1.setEnabled(false);
            } else {
                BtnGruperStage1.setEnabled(true);
            }
        }
        SetDataKlaim("baru", tglsep);
        tampilDiagnosa();
        tampilProsedur();
        BtnGruperStage.setEnabled(false);
        BtnGruperStage1.setEnabled(false);
        tampilHG1();
        tampilHG2();
        tampilRespon();
    }

    private void SetDataKlaim(String status, String tglsep) {
        datanya = status;
        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            if (jknya.equals("L")) {
                jk.setText("LAKI-LAKI");
            } else {
                jk.setText("PEREMPUAN");
            }
        } else {
            if (jknyaCOVID.equals("1")) {
                jk1.setText("LAKI-LAKI");
            } else {
                jk1.setText("PEREMPUAN");
            }
        }

        if (sts_umur.equals("Th")) {
            sts_umur_ok = "tahun";
        } else if (sts_umur.equals("Bl")) {
            sts_umur_ok = "bulan";
        } else if (sts_umur.equals("Hr")) {
            sts_umur_ok = "hari";
        }

        if (datanya.equals("baru")) {
            //kalau kode payor 3 adalah JKN
            if (kodePayor.equals("3")) {
                if (kls.equals("1")) {
                    cmbKH.setSelectedIndex(2);
                } else if (kls.equals("2")) {
                    cmbKH.setSelectedIndex(1);
                } else if (kls.equals("3")) {
                    cmbKH.setSelectedIndex(0);
                }
                dataKlaimBaru();
            } else {
                kls = "3";
                labelhak1.setText("Kelas 3");
                dataKlaimBaru();
            }

        } else if (datanya.equals("lama")) {
            dataKlaimLama(tglsep);
        }
    }

    private void tampilDiagnosa() {
        diagnosaKlaim = "";
        diagnosaKlaimINADRG = "";
        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            //diagnosa v5 -----------------------------------------
            Valid.tabelKosong(tabMode);
            try {
                ps1 = koneksi.prepareStatement("SELECT dp.kd_penyakit, p.ciri_ciri, IF (dp.prioritas = '1','Primer','Sekunder') status "
                        + "FROM diagnosa_pasien dp INNER JOIN penyakit p ON p.kd_penyakit=dp.kd_penyakit "
                        + "WHERE dp.no_rawat='" + norawat + "' ORDER BY dp.prioritas");
                try {
                    rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        diagnosaKlaim = diagnosaKlaim + rs1.getString("kd_penyakit") + "#";
                        if (mbak_eka.ngecekDiagnosa(rs1.getString("kd_penyakit")) == true) {
                            hasilVerifDiag1 = "Sesuai";
                        } else {
                            hasilVerifDiag1 = "Belum Sesuai";
                        }
                        tabMode.addRow(new Object[]{
                            rs1.getString("kd_penyakit"),
                            rs1.getString("ciri_ciri"),
                            rs1.getString("status"),
                            hasilVerifDiag1
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
            } catch (SQLException e) {
                System.out.println("Notifikasi : " + e);
            }
            
            //diagnosa v6 -----------------------------------------
            Valid.tabelKosong(tabMode13);
            try {
                ps13 = koneksi.prepareStatement("SELECT dp.kd_penyakit, p.ciri_ciri, IF (dp.prioritas = '1','Primer','Sekunder') status "
                        + "FROM diagnosa_pasien_inadrg dp INNER JOIN penyakit p ON p.kd_penyakit=dp.kd_penyakit "
                        + "WHERE dp.no_rawat='" + norawat + "' ORDER BY dp.prioritas");
                try {
                    rs13 = ps13.executeQuery();
                    while (rs13.next()) {
                        diagnosaKlaimINADRG = diagnosaKlaimINADRG + rs13.getString("kd_penyakit") + "#";
                        if (mbak_eka.ngecekDiagnosaINADRG(rs13.getString("kd_penyakit")) == true) {
                            hasilVerifDiag2 = "Sesuai";
                        } else {
                            hasilVerifDiag2 = "Belum Sesuai";
                        }
                        tabMode13.addRow(new Object[]{
                            rs13.getString("kd_penyakit"),
                            rs13.getString("ciri_ciri"),
                            rs13.getString("status"),
                            hasilVerifDiag2
                        });
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rs13 != null) {
                        rs13.close();
                    }
                    if (ps13 != null) {
                        ps13.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Notifikasi : " + e);
            }

        } else {
            //diagnosa v5 -----------------------------------------
            Valid.tabelKosong(tabMode5);
            try {
                ps1 = koneksi.prepareStatement("SELECT dp.kd_penyakit, p.ciri_ciri, IF (dp.prioritas = '1','Primer','Sekunder') status "
                        + "FROM diagnosa_pasien dp INNER JOIN penyakit p ON p.kd_penyakit=dp.kd_penyakit "
                        + "WHERE dp.no_rawat='" + norawat + "' ORDER BY dp.prioritas");
                try {
                    rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        diagnosaKlaim = diagnosaKlaim + rs1.getString("kd_penyakit") + "#";
                        if (mbak_eka.ngecekDiagnosa(rs1.getString("kd_penyakit")) == true) {
                            hasilVerifDiag3 = "Sesuai";
                        } else {
                            hasilVerifDiag3 = "Belum Sesuai";
                        }
                        tabMode5.addRow(new Object[]{
                            rs1.getString("kd_penyakit"),
                            rs1.getString("ciri_ciri"),
                            rs1.getString("status"),
                            hasilVerifDiag3
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
            } catch (SQLException e) {
                System.out.println("Notifikasi : " + e);
            }
            
            //diagnosa v6 -----------------------------------------
            Valid.tabelKosong(tabMode14);
            try {
                ps14 = koneksi.prepareStatement("SELECT dp.kd_penyakit, p.ciri_ciri, IF (dp.prioritas = '1','Primer','Sekunder') status "
                        + "FROM diagnosa_pasien_inadrg dp INNER JOIN penyakit p ON p.kd_penyakit=dp.kd_penyakit "
                        + "WHERE dp.no_rawat='" + norawat + "' ORDER BY dp.prioritas");
                try {
                    rs14 = ps14.executeQuery();
                    while (rs14.next()) {
                        diagnosaKlaimINADRG = diagnosaKlaimINADRG + rs14.getString("kd_penyakit") + "#";
                        if (mbak_eka.ngecekDiagnosaINADRG(rs14.getString("kd_penyakit")) == true) {
                            hasilVerifDiag4 = "Sesuai";
                        } else {
                            hasilVerifDiag4 = "Belum Sesuai";
                        }
                        tabMode14.addRow(new Object[]{
                            rs14.getString("kd_penyakit"),
                            rs14.getString("ciri_ciri"),
                            rs14.getString("status"),
                            hasilVerifDiag4
                        });
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rs14 != null) {
                        rs14.close();
                    }
                    if (ps14 != null) {
                        ps14.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }

    private void tampilProsedur() {
        prosedurKlaim = "";
        
        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            //prosedur v5 ---------------------------------------
            Valid.tabelKosong(tabMode1);
            try {
                ps2 = koneksi.prepareStatement("SELECT pp.kode, i.deskripsi_panjang FROM prosedur_pasien pp "
                        + "INNER JOIN icd9 i ON i.kode=pp.kode WHERE pp.no_rawat='" + norawat + "' ORDER BY pp.prioritas");
                try {
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        if (prosedurKlaim.equals("")) {
                            prosedurKlaim = rs2.getString("kode");
                        } else {
                            prosedurKlaim = prosedurKlaim + "#" + rs2.getString("kode");
                        }
                        
                        tabMode1.addRow(new Object[]{
                            rs2.getString("kode"),
                            rs2.getString("deskripsi_panjang")
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
            } catch (SQLException e) {
                System.out.println("Notifikasi : " + e);
            }            
            
            //prosedur v6 ---------------------------------------
            Valid.tabelKosong(tabMode15);
            try {
                ps15 = koneksi.prepareStatement("SELECT pp.kode, i.deskripsi_panjang, pp.qty FROM prosedur_pasien_inadrg pp "
                        + "INNER JOIN icd9 i ON i.kode=pp.kode WHERE pp.no_rawat='" + norawat + "' ORDER BY pp.prioritas");
                try {
                    rs15 = ps15.executeQuery();
                    while (rs15.next()) {
                        tabMode15.addRow(new Object[]{
                            rs15.getString("kode"),
                            rs15.getString("deskripsi_panjang"),
                            rs15.getString("qty")
                        });
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rs15 != null) {
                        rs15.close();
                    }
                    if (ps15 != null) {
                        ps15.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Notifikasi : " + e);
            }            
            hasilJLHProsedur();

        } else {
            //prosedur v5 ---------------------------------------
            Valid.tabelKosong(tabMode6);
            try {
                ps2 = koneksi.prepareStatement("SELECT pp.kode, i.deskripsi_panjang FROM prosedur_pasien pp "
                        + "INNER JOIN icd9 i ON i.kode=pp.kode WHERE pp.no_rawat='" + norawat + "' ORDER BY pp.prioritas");
                try {
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        if (prosedurKlaim.equals("")) {
                            prosedurKlaim = rs2.getString("kode");
                        } else {
                            prosedurKlaim = prosedurKlaim + "#" + rs2.getString("kode");
                        }
                        
                        tabMode6.addRow(new Object[]{
                            rs2.getString("kode"),
                            rs2.getString("deskripsi_panjang")
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
            } catch (SQLException e) {
                System.out.println("Notifikasi : " + e);
            } 
            
            //prosedur v6 ---------------------------------------
            Valid.tabelKosong(tabMode16);
            try {
                ps16 = koneksi.prepareStatement("SELECT pp.kode, i.deskripsi_panjang, pp.qty FROM prosedur_pasien_inadrg pp "
                        + "INNER JOIN icd9 i ON i.kode=pp.kode WHERE pp.no_rawat='" + norawat + "' ORDER BY pp.prioritas");
                try {
                    rs16 = ps16.executeQuery();
                    while (rs16.next()) {
                        tabMode16.addRow(new Object[]{
                            rs16.getString("kode"),
                            rs16.getString("deskripsi_panjang"),
                            rs16.getString("qty")
                        });
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rs16 != null) {
                        rs16.close();
                    }
                    if (ps16 != null) {
                        ps16.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Notifikasi : " + e);
            } 
            hasilJLHProsedur();
        }
    }
    
    private void tampilTerupload() {
        nilaiUnggah = "";
        Valid.tabelKosong(tabMode9);
        try {
            ps10 = koneksi.prepareStatement("SELECT efu.file_name,efu.message,efut.basecode64_file, efu.file_id,efu.kode,"
                    + "efu.file_class FROM eklaim_file_upload efu LEFT join eklaim_file_upload_temp efut ON efut.no_sep=efu.no_sep "
                    + "AND efut.file_id=efu.file_id WHERE efu.no_sep='" + noKlaim.getText() + "'");
            try {
                rs10 = ps10.executeQuery();
                while (rs10.next()) {
                    if (rs10.getString("file_class").equals("resume_medis")) {
                        nilaiUnggah = "Resume Medis";
                    } else if (rs10.getString("file_class").equals("ruang_rawat")) {
                        nilaiUnggah = "Ruang Perawatan";
                    } else if (rs10.getString("file_class").equals("laboratorium")) {
                        nilaiUnggah = "Hasil Laboratorium";
                    } else if (rs10.getString("file_class").equals("radiologi")) {
                        nilaiUnggah = "Hasil Radiologi";
                    } else if (rs10.getString("file_class").equals("penunjang_lain")) {
                        nilaiUnggah = "Hasil Penunjang Lainnya";
                    } else if (rs10.getString("file_class").equals("resep_obat")) {
                        nilaiUnggah = "Resep Obat / Alkes";
                    } else if (rs10.getString("file_class").equals("tagihan")) {
                        nilaiUnggah = "Tagihan (Billing)";
                    } else if (rs10.getString("file_class").equals("kartu_identitas")) {
                        nilaiUnggah = "Kartu Identitas";
                    } else if (rs10.getString("file_class").equals("dokumen_kipi")) {
                        nilaiUnggah = "Dokumen KIPI";
                    } else if (rs10.getString("file_class").equals("lain_lain")) {
                        nilaiUnggah = "Lain-lain";
                    }
                    tabMode9.addRow(new Object[]{
                        false,
                        nilaiUnggah,
                        rs10.getString("file_name"),
                        rs10.getString("message"),
                        rs10.getString("basecode64_file"),
                        rs10.getString("file_id"),
                        rs10.getString("kode"),
                        rs10.getString("file_class")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs10 != null) {
                    rs10.close();
                }
                if (ps10 != null) {
                    ps10.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void emptTeksJKN() {
        norm.setText("");
        nmPasien.setText("");
        jk.setText("");
        tglLhr.setText("");
        noPeserta.setText("");
        noSEP.setText("");
        cmbCOB.setSelectedItem(0);
        nmPetugas.setText("");
        nikPetugas.setText("");
        labelrwt.setText("");
        ChkKelasEksekutif.setEnabled(false);
        ChkKelasEksekutif.setSelected(false);
        ChkNaikTurun.setEnabled(false);
        ChkNaikTurun.setSelected(false);
        ChkRawatIntensif.setEnabled(false);
        ChkRawatIntensif.setSelected(false);
        cmbKH.setSelectedIndex(0);
        cmbKH.setEnabled(true);
        wktMasuk.setText("");
        wktPulang.setText("");
        umur.setText("");
        cmbKP.setEnabled(false);
        cmbKP.setSelectedIndex(0);
        losNaikKls.setEnabled(false);
        losNaikKls.setText("");
        losIntensif.setEnabled(false);
        losIntensif.setText("0");
        ventilator.setEnabled(false);
        ventilator.setText("");
        labelLOS.setText("");
        brtlhr.setText("");
        subakut.setText("");
        kronik.setText("");
        cmbcrPulang.setSelectedIndex(0);
        dpjp.setText("");
        tarifPoliExe.setEnabled(false);
        tarifPoliExe.setText("");
        labeltarif.setText("");
        pnb.setText("");
        ta.setText("");
        rad.setText("");
        reh.setText("");
        obat.setText("");
        alkes.setText("");
        pb.setText("");
        kep.setText("");
        lab.setText("");
        kam.setText("");
        okr.setText("");
        bmhp.setText("");
        kon.setText("");
        pen.setText("");
        pd.setText("");
        ri.setText("");
        oke.setText("");
        sa.setText("");
        labelTarifRS.setText("");
        labelhasilG.setText("Hasil Grouper : ");
        labeltambahan.setText("Tambahan biaya yang dibayar pasien naik kelas : Rp. 0");
        cmbCrMasuk.setSelectedIndex(0);
        cmbPembiayaan.setSelectedIndex(0);
        cmbPembiayaan.setEnabled(false);
        sistole.setText("0");
        diastole.setText("0");
        Valid.tabelKosong(tabMode);
        Valid.tabelKosong(tabMode1);
        Valid.tabelKosong(tabMode2);
        Valid.tabelKosong(tabMode3);
        
        cmbVentilator.setEnabled(false);
        cmbVentilator.setSelectedIndex(1);
        tglIntubasi.setDate(new Date());
        tglEkstubasi.setDate(new Date());
        tglIntubasi.setEnabled(false);
        tglEkstubasi.setEnabled(false);
        cmbDializer.setSelectedIndex(1);
        TkntngDrh.setText("0");
        
        ChkApgar.setSelected(false);
        cmbMnt1App.setSelectedIndex(0);
        cmbMnt1Pul.setSelectedIndex(0);
        cmbMnt1Gri.setSelectedIndex(0);
        cmbMnt1Act.setSelectedIndex(0);
        cmbMnt1Res.setSelectedIndex(0);
        cmbMnt5App.setSelectedIndex(0);
        cmbMnt5Pul.setSelectedIndex(0);
        cmbMnt5Gri.setSelectedIndex(0);
        cmbMnt5Act.setSelectedIndex(0);
        cmbMnt5Res.setSelectedIndex(0);
        
        TUsiaHml.setText("");
        Tgravida.setText("");
        Tpartus.setText("");
        Tabortus.setText("");
        cmbOnset.setSelectedIndex(0);
        wktkelahiran.setDate(new Date());
        cmbCaraLhr.setSelectedIndex(0);
        cmbLtkJanin.setSelectedIndex(0);
        cmbKondisi.setSelectedIndex(0);
        cmbBantuan.setSelectedIndex(0);
        cmbForcep.setSelectedIndex(0);
        cmbVacum.setSelectedIndex(0);
        Valid.tabelKosong(tabMode17);
    }

    public void emptTeksLAINNYA() {
        norm1.setText("");
        nmPasien1.setText("");
        jk1.setText("");
        tglLhr1.setText("");
        cmbID.setSelectedIndex(0);
        noID.setText("");
        noKlaim.setText("");
        nmPetugas1.setText("");
        nikPetugas1.setText("");
        labelrwt1.setText("");
        labelhak1.setText("");
        wktMasuk1.setText("");
        wktPulang1.setText("");
        umur1.setText("");
        cmbCO.setSelectedIndex(1);
        cmbKomor.setSelectedIndex(0);
        cmbEpisod.setSelectedIndex(0);
        hari.setText("");
        labelLOS1.setText("");
        BtnAddEpisod.setEnabled(false);
        BtnDelEpisod.setEnabled(false);
        subakut1.setText("");
        kronik1.setText("");
        dpjp1.setText("");
        cmbcrPulang1.setSelectedIndex(0);
        brtlhr1.setText("");
        cmbStatus.setSelectedIndex(2);
        cmbRS.setSelectedIndex(1);
        labelTarifRS1.setText("");
        pnb7.setText("");
        ta1.setText("");
        rad1.setText("");
        reh7.setText("");
        obat1.setText("");
        alkes1.setText("");
        pb1.setText("");
        kep7.setText("");
        lab1.setText("");
        kam1.setText("");
        okr1.setText("");
        bmhp1.setText("");
        kon7.setText("");
        pen1.setText("");
        pd1.setText("");
        ri1.setText("");
        oke1.setText("");
        sa1.setText("");
        ChkSemuaFaktor.setSelected(false);
        ChkSemuaPelengkap.setSelected(false);
        ChkAsam.setSelected(false);
        ChkKultur.setSelected(false);
        ChkAPTT.setSelected(false);
        ChkAnalisa.setSelected(false);
        ChkProcal.setSelected(false);
        ChkDimer.setSelected(false);
        ChkWaktu.setSelected(false);
        ChkAlbumin.setSelected(false);
        ChkCRP.setSelected(false);
        ChkPT.setSelected(false);
        ChkAnti.setSelected(false);
        ChkThorax.setSelected(false);
        ChkPemulasaran.setSelected(false);
        ChkPlastik.setSelected(false);
        ChkKantong.setSelected(false);
        ChkDesinfekJen.setSelected(false);
        ChkDesinfekMob.setSelected(false);
        ChkPeti.setSelected(false);
        ChkTranspot.setSelected(false);
        Valid.tabelKosong(tabMode4);
        Valid.tabelKosong(tabMode5);
        Valid.tabelKosong(tabMode6);
        Valid.tabelKosong(tabMode7);
        Valid.tabelKosong(tabMode8);
        Valid.tabelKosong(tabMode9);
        Valid.tabelKosong(tabMode10);
        labelhasilG2.setText("Hasil Grouper : ");
        cmbKriteria.setSelectedIndex(1);
        cmbIsolasi.setSelectedIndex(0);
        cmbKelainan.setSelectedIndex(0);
        tarifPlasma.setText("");
        labeltarifPlasma.setText("");
        nmJaminan.setText("-");
        labelnmIbu.setText("Nama Ibu : -");
        labelnmIbu.setVisible(false);
        ChkRawatIntensif1.setEnabled(false);
        ChkRawatIntensif1.setSelected(false);
        losIntensif1.setEnabled(false);
        losIntensif1.setText("0");
        ventilator1.setEnabled(false);
        ventilator1.setText("0");
        cmbCrMasuk1.setSelectedIndex(0);
        
        cmbVentilator1.setEnabled(false);
        cmbVentilator1.setSelectedIndex(1);
        tglIntubasi1.setDate(new Date());
        tglEkstubasi1.setDate(new Date());
        tglIntubasi1.setEnabled(false);
        tglEkstubasi1.setEnabled(false);
    }

    public void isCek() {
        BtnGruper.setEnabled(akses.getpengajuan_klaim_raza());
        BtnGruper2.setEnabled(akses.getpengajuan_klaim_raza());
        BtnKirimOnline.setEnabled(akses.getpengajuan_klaim_raza());
        BtnEditKlaim.setEnabled(akses.getpengajuan_klaim_raza());
        BtnHapusKlaim.setEnabled(akses.getpengajuan_klaim_raza());
        BtnFinal.setEnabled(akses.getpengajuan_klaim_raza());
        ppDiagnosa.setEnabled(akses.getpengajuan_klaim_raza());
        BtnAddEpisod.setEnabled(akses.getpengajuan_klaim_raza());
        BtnDelEpisod.setEnabled(akses.getpengajuan_klaim_raza());
        BtnGruperStage.setEnabled(akses.getpengajuan_klaim_raza());
        BtnGruperStage1.setEnabled(akses.getpengajuan_klaim_raza());
    }

    private void hitungPNB() {
        pnb1 = 0;
        pnb2 = 0;
        pnb3 = 0;
        pnb4 = 0;
        pnb5 = 0;
        pnb6 = 0;
        pnb8 = 0;
        pnb9 = 0;
        pnb10 = 0;
        pnb11 = 0;
        pnb12 = 0;
        pnb13 = 0;
        nilaiPNB = 0;

        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            pnb1 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_dr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='TMINV' OR kp.kd_kategori='TMNIN' OR kp.kd_kategori='GIGI')");

            pnb2 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_pr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='TMINV' OR kp.kd_kategori='TMNIN' OR kp.kd_kategori='GIGI')");

            pnb3 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_drpr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='TMINV' OR kp.kd_kategori='TMNIN' OR kp.kd_kategori='GIGI')");

            pnb4 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_dr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='TMINV' OR kp.kd_kategori='TMNIN' OR kp.kd_kategori='GIGI')");

            pnb5 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_drpr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='TMINV' OR kp.kd_kategori='TMNIN' OR kp.kd_kategori='GIGI')");

            pnb6 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_pr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='TMINV' OR kp.kd_kategori='TMNIN' OR kp.kd_kategori='GIGI')");

            nilaiPNB = pnb1 + pnb2 + pnb3 + pnb4 + pnb5 + pnb6;
            pnb.setText(Valid.SetAngka(nilaiPNB));

        } else {
            pnb8 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_dr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='TMINV' OR kp.kd_kategori='TMNIN' OR kp.kd_kategori='GIGI')");

            pnb9 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_pr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='TMINV' OR kp.kd_kategori='TMNIN' OR kp.kd_kategori='GIGI')");

            pnb10 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_drpr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='TMINV' OR kp.kd_kategori='TMNIN' OR kp.kd_kategori='GIGI')");

            pnb11 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_dr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='TMINV' OR kp.kd_kategori='TMNIN' OR kp.kd_kategori='GIGI')");

            pnb12 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_drpr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='TMINV' OR kp.kd_kategori='TMNIN' OR kp.kd_kategori='GIGI')");

            pnb13 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_pr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='TMINV' OR kp.kd_kategori='TMNIN' OR kp.kd_kategori='GIGI')");

            nilaiPNB = pnb8 + pnb9 + pnb10 + pnb11 + pnb12 + pnb13;
            pnb7.setText(Valid.SetAngka(nilaiPNB));
        }
    }

    private void hitungRI() {
        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            nilaiRI = Sequel.cariIsiAngka("SELECT SUM(ttl_biaya) biaya FROM kamar_inap WHERE (kd_kamar LIKE 'IC/%' OR kd_kamar LIKE '%NICU%') AND no_rawat='" + NrawatTARIF + "'");
            ri.setText(Valid.SetAngka(nilaiRI));

        } else {
            nilaiRI = Sequel.cariIsiAngka("SELECT SUM(ttl_biaya) biaya FROM kamar_inap WHERE (kd_kamar LIKE 'IC/%' OR kd_kamar LIKE '%NICU%') AND no_rawat='" + NrawatTARIF + "'");
            ri1.setText(Valid.SetAngka(nilaiRI));
        }
    }

    private void hitungKAMAR() {
        kmr1 = 0;
        kmr2 = 0;
        kmr3 = 0;
        kmr4 = 0;
        kmr5 = 0;
        kmr6 = 0;
        kmr7 = 0;
        kmr8 = 0;
        kmr9 = 0;
        kmr10 = 0;
        kmr11 = 0;
        kmr12 = 0;
        kmr13 = 0;
        kmr14 = 0;
        nilaiKAM = 0;

        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            kmr1 = Sequel.cariIsiAngka("SELECT SUM(ttl_biaya) biaya FROM kamar_inap WHERE (kd_kamar not LIKE 'IC/%' OR kd_kamar not LIKE '%NICU%') AND no_rawat='" + NrawatTARIF + "'");
            kmr2 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_dr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='GIZI' OR kp.kd_kategori='PKSK')");
            kmr3 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_pr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='GIZI' OR kp.kd_kategori='PKSK')");
            kmr4 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_drpr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='GIZI' OR kp.kd_kategori='PKSK')");
            kmr5 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_dr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='GIZI' OR kp.kd_kategori='PKSK')");
            kmr6 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_drpr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='GIZI' OR kp.kd_kategori='PKSK')");
            kmr7 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_pr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='GIZI' OR kp.kd_kategori='PKSK')");

            nilaiKAM = kmr1 + kmr2 + kmr3 + kmr4 + kmr5 + kmr6 + kmr7;
            kam.setText(Valid.SetAngka(nilaiKAM));

        } else {
            kmr8 = Sequel.cariIsiAngka("SELECT SUM(ttl_biaya) biaya FROM kamar_inap WHERE (kd_kamar not LIKE 'IC/%' OR kd_kamar not LIKE '%NICU%') AND no_rawat='" + NrawatTARIF + "'");
            kmr9 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_dr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='GIZI' OR kp.kd_kategori='PKSK')");
            kmr10 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_pr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='GIZI' OR kp.kd_kategori='PKSK')");
            kmr11 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_drpr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='GIZI' OR kp.kd_kategori='PKSK')");
            kmr12 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_dr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='GIZI' OR kp.kd_kategori='PKSK')");
            kmr13 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_drpr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='GIZI' OR kp.kd_kategori='PKSK')");
            kmr14 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_pr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='GIZI' OR kp.kd_kategori='PKSK')");

            nilaiKAM = kmr8 + kmr9 + kmr10 + kmr11 + kmr12 + kmr13 + kmr14;
            kam1.setText(Valid.SetAngka(nilaiKAM));
        }
    }

    private void hitungTOTAL() {
        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            nilaiTOTAL = nilaiPNB + nilaiRI + nilaiKAM + nilaiKON + nilaiKEP + nilaiREH
                    + nilaiRAD + nilaiLAB + nilaiOBAT + nilaiPB;
            labelTarifRS.setText(Valid.SetAngka(nilaiTOTAL));

        } else {
            nilaiTOTAL = nilaiPNB + nilaiRI + nilaiKAM + nilaiKON + nilaiKEP + nilaiREH
                    + nilaiRAD + nilaiLAB + nilaiOBAT + nilaiPB;
            labelTarifRS1.setText(Valid.SetAngka(nilaiTOTAL));
        }
    }

    private void hitungKON() {
        kon1 = 0;
        kon2 = 0;
        kon3 = 0;
        kon4 = 0;
        kon5 = 0;
        kon6 = 0;
        kon8 = 0;
        kon9 = 0;
        kon10 = 0;
        kon11 = 0;
        kon12 = 0;
        kon13 = 0;
        nilaiKON = 0;

        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            kon1 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_dr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' AND "
                    + "(kp.kd_kategori='RANAP' OR kp.kd_kategori='ICARE' OR kp.kd_kategori='IGDA' OR kp.kd_kategori='POLI' OR kp.kd_kategori='JIWA' OR kp.kd_kategori='PSIK' OR kp.kd_kategori='FRMS')");
            kon2 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_pr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' AND "
                    + "(kp.kd_kategori='RANAP' OR kp.kd_kategori='ICARE' OR kp.kd_kategori='IGDA' OR kp.kd_kategori='POLI' OR kp.kd_kategori='JIWA' OR kp.kd_kategori='PSIK' OR kp.kd_kategori='FRMS')");
            kon3 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_drpr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' AND "
                    + "(kp.kd_kategori='RANAP' OR kp.kd_kategori='ICARE' OR kp.kd_kategori='IGDA' OR kp.kd_kategori='POLI' OR kp.kd_kategori='JIWA' OR kp.kd_kategori='PSIK' OR kp.kd_kategori='FRMS')");
            kon4 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_dr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' AND "
                    + "(kp.kd_kategori='RANAP' OR kp.kd_kategori='ICARE' OR kp.kd_kategori='IGDA' OR kp.kd_kategori='POLI' OR kp.kd_kategori='JIWA' OR kp.kd_kategori='PSIK' OR kp.kd_kategori='FRMS')");
            kon5 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_drpr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' AND "
                    + "(kp.kd_kategori='RANAP' OR kp.kd_kategori='ICARE' OR kp.kd_kategori='IGDA' OR kp.kd_kategori='POLI' OR kp.kd_kategori='JIWA' OR kp.kd_kategori='PSIK' OR kp.kd_kategori='FRMS')");
            kon6 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_pr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' AND "
                    + "(kp.kd_kategori='RANAP' OR kp.kd_kategori='ICARE' OR kp.kd_kategori='IGDA' OR kp.kd_kategori='POLI' OR kp.kd_kategori='JIWA' OR kp.kd_kategori='PSIK' OR kp.kd_kategori='FRMS')");

            nilaiKON = kon1 + kon2 + kon3 + kon4 + kon5 + kon6;
            kon.setText(Valid.SetAngka(nilaiKON));

        } else {
            kon8 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_dr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' AND "
                    + "(kp.kd_kategori='RANAP' OR kp.kd_kategori='ICARE' OR kp.kd_kategori='IGDA' OR kp.kd_kategori='POLI' OR kp.kd_kategori='JIWA' OR kp.kd_kategori='PSIK' OR kp.kd_kategori='FRMS')");
            kon9 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_pr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' AND "
                    + "(kp.kd_kategori='RANAP' OR kp.kd_kategori='ICARE' OR kp.kd_kategori='IGDA' OR kp.kd_kategori='POLI' OR kp.kd_kategori='JIWA' OR kp.kd_kategori='PSIK' OR kp.kd_kategori='FRMS')");
            kon10 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_drpr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' AND "
                    + "(kp.kd_kategori='RANAP' OR kp.kd_kategori='ICARE' OR kp.kd_kategori='IGDA' OR kp.kd_kategori='POLI' OR kp.kd_kategori='JIWA' OR kp.kd_kategori='PSIK' OR kp.kd_kategori='FRMS')");
            kon11 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_dr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' AND "
                    + "(kp.kd_kategori='RANAP' OR kp.kd_kategori='ICARE' OR kp.kd_kategori='IGDA' OR kp.kd_kategori='POLI' OR kp.kd_kategori='JIWA' OR kp.kd_kategori='PSIK' OR kp.kd_kategori='FRMS')");
            kon12 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_drpr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' AND "
                    + "(kp.kd_kategori='RANAP' OR kp.kd_kategori='ICARE' OR kp.kd_kategori='IGDA' OR kp.kd_kategori='POLI' OR kp.kd_kategori='JIWA' OR kp.kd_kategori='PSIK' OR kp.kd_kategori='FRMS')");
            kon13 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_pr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' AND "
                    + "(kp.kd_kategori='RANAP' OR kp.kd_kategori='ICARE' OR kp.kd_kategori='IGDA' OR kp.kd_kategori='POLI' OR kp.kd_kategori='JIWA' OR kp.kd_kategori='PSIK' OR kp.kd_kategori='FRMS')");

            nilaiKON = kon8 + kon9 + kon10 + kon11 + kon12 + kon13;
            kon7.setText(Valid.SetAngka(nilaiKON));
        }
    }

    private void hitungKEP() {
        kep1 = 0;
        kep2 = 0;
        kep3 = 0;
        kep4 = 0;
        kep5 = 0;
        kep6 = 0;
        kep8 = 0;
        kep9 = 0;
        kep10 = 0;
        kep11 = 0;
        kep12 = 0;
        kep13 = 0;
        nilaiKEP = 0;

        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            kep1 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_dr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='IGDB' OR kp.kd_kategori='NURSE' OR kp.kd_kategori='TCARE' OR kp.kd_kategori='JNZH')");
            kep2 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_pr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='IGDB' OR kp.kd_kategori='NURSE' OR kp.kd_kategori='TCARE' OR kp.kd_kategori='JNZH')");
            kep3 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_drpr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='IGDB' OR kp.kd_kategori='NURSE' OR kp.kd_kategori='TCARE' OR kp.kd_kategori='JNZH')");
            kep4 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_dr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='IGDB' OR kp.kd_kategori='NURSE' OR kp.kd_kategori='TCARE' OR kp.kd_kategori='JNZH')");
            kep5 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_drpr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='IGDB' OR kp.kd_kategori='NURSE' OR kp.kd_kategori='TCARE' OR kp.kd_kategori='JNZH')");
            kep6 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_pr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='IGDB' OR kp.kd_kategori='NURSE' OR kp.kd_kategori='TCARE' OR kp.kd_kategori='JNZH')");

            nilaiKEP = kep1 + kep2 + kep3 + kep4 + kep5 + kep6;
            kep.setText(Valid.SetAngka(nilaiKEP));

        } else {
            kep8 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_dr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='IGDB' OR kp.kd_kategori='NURSE' OR kp.kd_kategori='TCARE' OR kp.kd_kategori='JNZH')");
            kep9 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_pr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='IGDB' OR kp.kd_kategori='NURSE' OR kp.kd_kategori='TCARE' OR kp.kd_kategori='JNZH')");
            kep10 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_drpr ridr INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='IGDB' OR kp.kd_kategori='NURSE' OR kp.kd_kategori='TCARE' OR kp.kd_kategori='JNZH')");
            kep11 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_dr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='IGDB' OR kp.kd_kategori='NURSE' OR kp.kd_kategori='TCARE' OR kp.kd_kategori='JNZH')");
            kep12 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_drpr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='IGDB' OR kp.kd_kategori='NURSE' OR kp.kd_kategori='TCARE' OR kp.kd_kategori='JNZH')");
            kep13 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_pr ridr INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori WHERE ridr.no_rawat='" + NrawatTARIF + "' "
                    + "AND (kp.kd_kategori='IGDB' OR kp.kd_kategori='NURSE' OR kp.kd_kategori='TCARE' OR kp.kd_kategori='JNZH')");

            nilaiKEP = kep8 + kep9 + kep10 + kep11 + kep12 + kep13;
            kep7.setText(Valid.SetAngka(nilaiKEP));
        }
    }

    private void hitungREH() {
        reh1 = 0;
        reh2 = 0;
        reh3 = 0;
        reh4 = 0;
        reh5 = 0;
        reh6 = 0;
        reh8 = 0;
        reh9 = 0;
        reh10 = 0;
        reh11 = 0;
        reh12 = 0;
        reh13 = 0;
        nilaiREH = 0;

        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            reh1 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_dr ridr "
                    + "INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori "
                    + "WHERE ridr.no_rawat='" + NrawatTARIF + "' AND (kp.kd_kategori='FISIO')");
            reh2 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_pr ridr "
                    + "INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori "
                    + "WHERE ridr.no_rawat='" + NrawatTARIF + "' AND (kp.kd_kategori='FISIO')");
            reh3 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_drpr ridr "
                    + "INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori "
                    + "WHERE ridr.no_rawat='" + NrawatTARIF + "' AND (kp.kd_kategori='FISIO')");
            reh4 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_dr ridr "
                    + "INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori "
                    + "WHERE ridr.no_rawat='" + NrawatTARIF + "' AND (kp.kd_kategori='FISIO')");
            reh5 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_drpr ridr "
                    + "INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori "
                    + "WHERE ridr.no_rawat='" + NrawatTARIF + "' AND (kp.kd_kategori='FISIO')");
            reh6 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_pr ridr "
                    + "INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori "
                    + "WHERE ridr.no_rawat='" + NrawatTARIF + "' AND (kp.kd_kategori='FISIO')");

            nilaiREH = reh1 + reh2 + reh3 + reh4 + reh5 + reh6;
            reh.setText(Valid.SetAngka(nilaiREH));

        } else {
            reh8 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_dr ridr "
                    + "INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori "
                    + "WHERE ridr.no_rawat='" + NrawatTARIF + "' AND (kp.kd_kategori='FISIO')");
            reh9 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_pr ridr "
                    + "INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori "
                    + "WHERE ridr.no_rawat='" + NrawatTARIF + "' AND (kp.kd_kategori='FISIO')");
            reh10 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_inap_drpr ridr "
                    + "INNER JOIN jns_perawatan_inap jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori "
                    + "WHERE ridr.no_rawat='" + NrawatTARIF + "' AND (kp.kd_kategori='FISIO')");
            reh11 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_dr ridr "
                    + "INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori "
                    + "WHERE ridr.no_rawat='" + NrawatTARIF + "' AND (kp.kd_kategori='FISIO')");
            reh12 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_drpr ridr "
                    + "INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori "
                    + "WHERE ridr.no_rawat='" + NrawatTARIF + "' AND (kp.kd_kategori='FISIO')");
            reh13 = Sequel.cariIsiAngka("SELECT sum(ridr.biaya_rawat) biaya FROM rawat_jl_pr ridr "
                    + "INNER JOIN jns_perawatan jpi ON jpi.kd_jenis_prw=ridr.kd_jenis_prw "
                    + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jpi.kd_kategori "
                    + "WHERE ridr.no_rawat='" + NrawatTARIF + "' AND (kp.kd_kategori='FISIO')");

            nilaiREH = reh8 + reh9 + reh10 + reh11 + reh12 + reh13;
            reh7.setText(Valid.SetAngka(nilaiREH));
        }
    }

    private void hitungRAD() {
        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            nilaiRAD = Sequel.cariIsiAngka("SELECT SUM(pr.biaya) biaya FROM periksa_radiologi pr WHERE pr.no_rawat='" + NrawatTARIF + "'");
            rad.setText(Valid.SetAngka(nilaiRAD));

        } else {
            nilaiRAD = Sequel.cariIsiAngka("SELECT SUM(pr.biaya) biaya FROM periksa_radiologi pr WHERE pr.no_rawat='" + NrawatTARIF + "'");
            rad1.setText(Valid.SetAngka(nilaiRAD));
        }
    }

    private void hitungLAB() {
        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            nilaiLAB = Sequel.cariIsiAngka("SELECT SUM(dpl.biaya_item) biaya FROM detail_periksa_lab dpl WHERE dpl.no_rawat='" + NrawatTARIF + "'");
            lab.setText(Valid.SetAngka(nilaiLAB));

        } else {
            nilaiLAB = Sequel.cariIsiAngka("SELECT SUM(dpl.biaya_item) biaya FROM detail_periksa_lab dpl WHERE dpl.no_rawat='" + NrawatTARIF + "'");
            lab1.setText(Valid.SetAngka(nilaiLAB));
        }
    }

    private void hitungOBAT() {
        obt1 = 0;
        obt2 = 0;
        obt3 = 0;
        obt4 = 0;
        obt5 = 0;
        obt6 = 0;
        nilaiOBAT = 0;

        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            obt1 = Sequel.cariIsiAngka("SELECT SUM(dpo.total) biaya FROM detail_pemberian_obat dpo WHERE dpo.no_rawat='" + NrawatTARIF + "'");
            obt2 = Sequel.cariIsiAngka("SELECT SUM(total) biaya FROM resep_pulang WHERE no_rawat='" + NrawatTARIF + "'");
            obt3 = Sequel.cariIsiAngka("SELECT SUM(subtotal) biaya FROM detreturjual de WHERE de.no_retur_jual='" + NrawatTARIF + "'");

            nilaiOBAT = obt1 + obt2 - obt3;
            obat.setText(Valid.SetAngka(nilaiOBAT));

        } else {
            obt4 = Sequel.cariIsiAngka("SELECT SUM(dpo.total) biaya FROM detail_pemberian_obat dpo WHERE dpo.no_rawat='" + NrawatTARIF + "'");
            obt5 = Sequel.cariIsiAngka("SELECT SUM(total) biaya FROM resep_pulang WHERE no_rawat='" + NrawatTARIF + "'");
            obt6 = Sequel.cariIsiAngka("SELECT SUM(subtotal) biaya FROM detreturjual de WHERE de.no_retur_jual='" + NrawatTARIF + "'");

            nilaiOBAT = obt4 + obt5 - obt6;
            obat1.setText(Valid.SetAngka(nilaiOBAT));
        }
    }

    private void hitungPB() {
        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            nilaiPB = Sequel.cariIsiAngka("SELECT SUM(biayaoperator1+biayaoperator2+biayaoperator3+biayaasisten_operator1+biayaasisten_operator2+biayaasisten_operator3+ "
                    + "biayainstrumen+biayadokter_anak+biayaperawaat_resusitas+biayadokter_anestesi+biayaasisten_anestesi+biayaasisten_anestesi2+biayabidan+ "
                    + "biayabidan2+biayabidan3+biayaperawat_luar+biayaalat+biayasewaok+akomodasi+bagian_rs+biaya_omloop+biaya_omloop2+biaya_omloop3+biaya_omloop4+ "
                    + "biaya_omloop5+biayasarpras+biaya_dokter_pjanak+biaya_dokter_umum) biaya FROM operasi WHERE no_rawat='" + NrawatTARIF + "'");
            pb.setText(Valid.SetAngka(nilaiPB));

        } else {
            nilaiPB = Sequel.cariIsiAngka("SELECT SUM(biayaoperator1+biayaoperator2+biayaoperator3+biayaasisten_operator1+biayaasisten_operator2+biayaasisten_operator3+ "
                    + "biayainstrumen+biayadokter_anak+biayaperawaat_resusitas+biayadokter_anestesi+biayaasisten_anestesi+biayaasisten_anestesi2+biayabidan+ "
                    + "biayabidan2+biayabidan3+biayaperawat_luar+biayaalat+biayasewaok+akomodasi+bagian_rs+biaya_omloop+biaya_omloop2+biaya_omloop3+biaya_omloop4+ "
                    + "biaya_omloop5+biayasarpras+biaya_dokter_pjanak+biaya_dokter_umum) biaya FROM operasi WHERE no_rawat='" + NrawatTARIF + "'");
            pb1.setText(Valid.SetAngka(nilaiPB));
        }
    }

    private void tampilHG1() {
        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            Valid.tabelKosong(tabMode2);
            try {
                ps3 = koneksi.prepareStatement("SELECT 'CBG' komponen, eg.cbg_desc deskrip, eg.cbg_code kode, concat('Rp. ',format(eg.cbg_tarif,0)) nominal  FROM eklaim_grouping eg "
                        + "WHERE eg.no_sep='" + noSEP.getText() + "' union ALL "
                        + "SELECT 'Sub Acute',eg.sub_acute_desc, eg.sub_acute_code, concat('Rp. ',format(eg.sub_acute_tarif,0)) nominal FROM eklaim_grouping eg "
                        + "WHERE eg.no_sep='" + noSEP.getText() + "' union ALL "
                        + "SELECT 'Chronic',eg.chronic_desc, eg.chronic_code, concat('Rp. ',format(eg.chronic_tarif,0)) nominal FROM eklaim_grouping eg "
                        + "WHERE eg.no_sep='" + noSEP.getText() + "' UNION ALL "
                        + "SELECT egsc.type,egsc.desc, egsc.code, concat('Rp. ',format(egsc.tarif,0)) nominal FROM eklaim_grouping_spc_cmg egsc "
                        + "WHERE egsc.no_sep='" + noSEP.getText() + "'");
                try {
                    rs3 = ps3.executeQuery();
                    while (rs3.next()) {
                        tabMode2.addRow(new Object[]{
                            rs3.getString("komponen"),
                            rs3.getString("deskrip"),
                            rs3.getString("kode"),
                            rs3.getString("nominal")
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

        } else {
            Valid.tabelKosong(tabMode7);
            try {
                ps3 = koneksi.prepareStatement("SELECT 'CBG' komponen, eg.cbg_desc deskrip, eg.cbg_code kode, concat('Rp. ',format(eg.cbg_tarif,0)) nominal  FROM eklaim_grouping eg "
                        + "WHERE eg.no_sep='" + noKlaim.getText() + "' union ALL "
                        + "SELECT 'Sub Acute',eg.sub_acute_desc, eg.sub_acute_code, concat('Rp. ',format(eg.sub_acute_tarif,0)) nominal FROM eklaim_grouping eg "
                        + "WHERE eg.no_sep='" + noKlaim.getText() + "' union ALL "
                        + "SELECT 'Chronic',eg.chronic_desc, eg.chronic_code, concat('Rp. ',format(eg.chronic_tarif,0)) nominal FROM eklaim_grouping eg "
                        + "WHERE eg.no_sep='" + noKlaim.getText() + "' UNION ALL "
                        + "SELECT egsc.type,egsc.desc, egsc.code, concat('Rp. ',format(egsc.tarif,0)) nominal FROM eklaim_grouping_spc_cmg egsc "
                        + "WHERE egsc.no_sep='" + noKlaim.getText() + "' UNION ALL "
                        + "SELECT 'Perawatan Covid-19',CONCAT(ecd.covid19_status_nm,' ',if(ecd.cc_ind='0','Tanpa Komorbid/Komplikasi','Dengan Komorbid/Komplikasi')) desk, '', concat('Rp. ',format(ecd.top_up_rawat,0)) nominal FROM eklaim_covid19_data ecd "
                        + "WHERE ecd.no_sep='" + noKlaim.getText() + "' UNION ALL "
                        + "SELECT 'Pemulasaran Jenazah','', '', concat('Rp. ',format(ecd.top_up_jenazah,0)) nominal FROM eklaim_covid19_data ecd "
                        + "WHERE ecd.no_sep='" + noKlaim.getText() + "' UNION ALL "
                        + "SELECT '','TOTAL BIAYA PERAWATAN COVID-19', '', concat('Rp. ',format(ecd.top_up_rawat+ecd.top_up_jenazah,0)) nominal FROM eklaim_covid19_data ecd "
                        + "WHERE ecd.no_sep='" + noKlaim.getText() + "'");
                try {
                    rs3 = ps3.executeQuery();
                    while (rs3.next()) {
                        tabMode7.addRow(new Object[]{
                            rs3.getString("komponen"),
                            rs3.getString("deskrip"),
                            rs3.getString("kode"),
                            rs3.getString("nominal")
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
    }

    private void tampilHG2() {
        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            Valid.tabelKosong(tabMode3);
            try {
                ps4 = koneksi.prepareStatement("SELECT egco.type,egco.code,egco.desc FROM eklaim_grouping_cmg_opt egco WHERE egco.no_sep='" + noSEP.getText() + "'");
                try {
                    rs4 = ps4.executeQuery();
                    while (rs4.next()) {
                        tabMode3.addRow(new Object[]{false,
                            rs4.getString("type"),
                            rs4.getString("code"),
                            rs4.getString("desc")
                        });
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

        } else {
            Valid.tabelKosong(tabMode8);
            try {
                ps4 = koneksi.prepareStatement("SELECT egco.type,egco.code,egco.desc FROM eklaim_grouping_cmg_opt egco WHERE egco.no_sep='" + noKlaim.getText() + "'");
                try {
                    rs4 = ps4.executeQuery();
                    while (rs4.next()) {
                        tabMode8.addRow(new Object[]{false,
                            rs4.getString("type"),
                            rs4.getString("code"),
                            rs4.getString("desc")
                        });
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
    }

    public void setKlaimAda(String norW, String noseP, String jnsKlaim, String payor, String nmIbu, String tglsep) {
        norawat = norW;
        noSEP.setText(noseP);
        jknya = "";
        tgllhrnya = "";
        jpel = "";
        kls = "";
        datanya = "";
        kodePayor = payor;
        nmJaminan.setText(jnsKlaim);
        tglREG = "";
        tglSEP.setText("");

        try {
            if (kodePayor.equals("3")) {
                ps5 = koneksi.prepareStatement("select nomr, REPLACE(REPLACE(nama_pasien,'\\\\',''),'''','') nama_pasien, jkel, tanggal_lahir, tglsep, "
                        + "UPPER(DATE_FORMAT(tanggal_lahir,'%d %b %Y')) tgllhr from bridging_sep where no_sep='" + noseP + "' and tglsep='" + tglsep + "'");

            } else {
                ps5 = koneksi.prepareStatement("SELECT rp.tgl_registrasi, p.no_ktp, p.no_peserta, p.no_rkm_medis, "
                        + "p.nm_pasien, if(p.jk='L','1','2') jkel, IF(rp.status_lanjut='Ralan','2','1') jRawat, "
                        + "p.tgl_lahir, UPPER(DATE_FORMAT(p.tgl_lahir,'%d %b %Y')) tgllhir FROM reg_periksa rp "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis WHERE rp.no_rawat='" + norW + "'");
            }

            try {
                rs5 = ps5.executeQuery();
                while (rs5.next()) {
                    //kalau kode payor 3 adalah JKN
                    if (kodePayor.equals("3")) {
                        norm.setText(rs5.getString("nomr"));
                        nmPasien.setText(rs5.getString("nama_pasien"));
                        jknya = rs5.getString("jkel");
                        tgllhrnya = rs5.getString("tanggal_lahir");
                        tglLhr.setText(rs5.getString("tgllhr"));
//                        tglREG = Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norW + "'");
                        tglREG = rs5.getString("tglsep");
                        tglSEP.setText(rs5.getString("tglsep"));

                    } else {
                        norm1.setText(rs5.getString("no_rkm_medis"));
                        nmPasien1.setText(rs5.getString("nm_pasien"));
                        jknyaCOVID = rs5.getString("jkel");
                        tgllhrnyaCOVID = rs5.getString("tgl_lahir");
                        tglLhr1.setText(rs5.getString("tgllhir"));
                        jpel = rs5.getString("jRawat");
                        
                        if (nmJaminan.getText().equals("JAMINAN BAYI BARU LAHIR")) {
                            cmbID.setSelectedIndex(11);
                            noID.setText(noseP);
                            labelnmIbu.setText("Nama Ibu : " + nmIbu);
                        } else {
                            cmbID.setSelectedIndex(0);
                            noID.setText(rs5.getString("no_ktp"));
                        }
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
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        sts_umur = Sequel.cariIsi("select sttsumur from reg_periksa where no_rawat='" + norW + "'");
        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            if (akses.getkode().equals("Admin Utama")) {
                nmPetugas.setText("Admin Utama");
                nikPetugas.setText("1");
            } else {
                nmPetugas.setText(Sequel.cariIsi("select nama from petugas WHERE nip='" + akses.getkode() + "'"));
                nikPetugas.setText(Sequel.cariIsi("SELECT no_ik FROM inacbg_coder_nik WHERE nik='" + akses.getkode() + "'"));
            }

            if (tabMode3.getRowCount() == 0) {
                BtnGruperStage.setEnabled(false);
            } else {
                BtnGruperStage.setEnabled(true);
            }
            labelhasilG.setText("Hasil Grouper : " + Sequel.cariIsi("select if(klaim_final='Belum','',klaim_final) from eklaim_new_claim where no_sep='" + noseP + "'"));
            labeltambahan.setText("Tambahan biaya yang dibayar pasien naik kelas : Rp. "
                    + Sequel.cariIsi("SELECT format(ifnull(add_payment_amt,'0'),0) tambhn_biaya FROM eklaim_grouping WHERE no_sep='" + noseP + "'"));
            ta.setText("0");
            alkes.setText("0");
            okr.setText("0");
            bmhp.setText("0");
            pen.setText("0");
            pd.setText("0");
            oke.setText("0");
            sa.setText("0");

        } else {
            if (akses.getkode().equals("Admin Utama")) {
                nmPetugas1.setText("Admin Utama");
                nikPetugas1.setText("1");
            } else {
                nmPetugas1.setText(Sequel.cariIsi("select nama from petugas WHERE nip='" + akses.getkode() + "'"));
                nikPetugas1.setText(Sequel.cariIsi("SELECT no_ik FROM inacbg_coder_nik WHERE nik='" + akses.getkode() + "'"));
            }

            labelhasilG2.setText("Hasil Grouper : " + Sequel.cariIsi("select if(klaim_final='Belum','',klaim_final) from eklaim_new_claim "
                    + "where no_sep='" + Sequel.cariIsi("SELECT claim_number FROM eklaim_generate_claim where no_rawat='" + norW + "'") + "'"));

            if (tabMode8.getRowCount() == 0) {
                BtnGruperStage1.setEnabled(false);
            } else {
                BtnGruperStage1.setEnabled(true);
            }
            ta1.setText("0");
            alkes1.setText("0");
            okr1.setText("0");
            bmhp1.setText("0");
            pen1.setText("0");
            pd1.setText("0");
            oke1.setText("0");
            sa1.setText("0");
        }

        tampilDiagnosa();
        tampilProsedur();
        SetDataKlaim("lama", tglsep);
        BtnGruperStage.setEnabled(false);
        BtnGruperStage1.setEnabled(false);
        tampilHG1();
        tampilHG2();
        tampilRespon();
    }

    private void dataKlaimBaru() {
        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            tarifPoliExe.setText("0");
            labeltarif.setText("0");
            losNaikKls.setText("0");
            losIntensif.setText("0");
            ventilator.setText("0");
            ChkKelasEksekutif.setSelected(false);
            ChkNaikTurun.setSelected(false);
            ChkRawatIntensif.setSelected(false);
            cmbKP.setSelectedIndex(0);
            cmbCrMasuk.setSelectedIndex(0);   
            nilaiPembiayaan = Sequel.cariIsi("select ifnull(pembiayaan,'') from bridging_sep where no_sep='" + noSEP.getText() + "'");
            umur.setText(Sequel.cariIsi("select concat(umurdaftar,' ','" + sts_umur_ok + "') from reg_periksa where no_rawat='" + norawat + "'"));
            cekBB = Sequel.cariIsi("select berat_badan_benar from pasien_bayi where no_rkm_medis='" + norm.getText() + "'");
            cmbVentilator.setEnabled(false);
            cmbVentilator.setSelectedIndex(1);
            tglIntubasi.setDate(new Date());
            tglEkstubasi.setDate(new Date());
            tglIntubasi.setEnabled(false);
            tglEkstubasi.setEnabled(false);

            ChkApgar.setSelected(false);
            cmbMnt1App.setSelectedIndex(0);
            cmbMnt1Pul.setSelectedIndex(0);
            cmbMnt1Gri.setSelectedIndex(0);
            cmbMnt1Act.setSelectedIndex(0);
            cmbMnt1Res.setSelectedIndex(0);
            cmbMnt5App.setSelectedIndex(0);
            cmbMnt5Pul.setSelectedIndex(0);
            cmbMnt5Gri.setSelectedIndex(0);
            cmbMnt5Act.setSelectedIndex(0);
            cmbMnt5Res.setSelectedIndex(0);
            
            cmbDializer.setSelectedIndex(1);
            TkntngDrh.setText("0");
            
            if (cekBB.equals("")) {
                brtlhr.setText("0");
            } else if (!cekBB.equals("")) {
                brtlhr.setText(cekBB);
            }

            //jns. pelayanan 1 (rawat inap)
            if (jpel.equals("1")) {
                labelrwt.setText("INAP");
                ChkKelasEksekutif.setEnabled(false);
                tarifPoliExe.setEnabled(false );
                ChkNaikTurun.setEnabled(true);
                cmbKP.setEnabled(false);
                cmbPembiayaan.setEnabled(false);
                losNaikKls.setEnabled(false);
                ChkRawatIntensif.setEnabled(true);
                losIntensif.setEnabled(false);
                ventilator.setEnabled(false);
                wktMasuk.setText(Sequel.cariIsi("select ifnull(concat(date_format(tgl_registrasi,'%d %b %Y'),' ',jam_reg),'') from reg_periksa where no_rawat='" + norawat + "'"));
                wktPulang.setText(Sequel.cariIsi("select concat(date_format(ki.tgl_keluar,'%d %b %Y'),' ',ki.jam_keluar) from kamar_inap ki "
                        + "inner join reg_periksa rp on rp.no_rawat=ki.no_rawat where ki.no_rawat='" + norawat + "' and ki.stts_pulang not in ('-','pindah kamar')"));
                tglmsk = Sequel.cariIsi("SELECT CONCAT(bs.tglsep,' ',rp.jam_reg) FROM reg_periksa rp "
                        + "INNER JOIN bridging_sep bs ON bs.no_rawat=rp.no_rawat WHERE bs.no_rawat='" + norawat + "'");
                tglplg = Sequel.cariIsi("SELECT if(bs.tglsep=ki.tgl_keluar,CONCAT(ki.tgl_keluar,' ',rp.jam_reg),CONCAT(ki.tgl_keluar,' ',ki.jam_keluar)) tgl_pulang "
                        + "from kamar_inap ki inner join reg_periksa rp on rp.no_rawat=ki.no_rawat LEFT JOIN bridging_sep bs ON bs.no_rawat=rp.no_rawat WHERE "
                        + "ki.no_rawat='" + norawat + "' and ki.stts_pulang not in ('-','pindah kamar')");
                dpjp.setText(Sequel.cariIsi("select d.nm_dokter from dpjp_ranap dr inner join dokter d on d.kd_dokter=dr.kd_dokter where dr.no_rawat='" + norawat + "'"));                
                labelLOS.setText(Sequel.cariIsi("select DATEDIFF(k.tgl_keluar,r.tgl_registrasi)+1 from kamar_inap k "
                        + "inner join reg_periksa r on r.no_rawat = k.no_rawat where k.stts_pulang not in ('-','Pindah Kamar') and k.no_rawat = '" + norawat + "'"));

                cekstsPulang = Sequel.cariIsi("select stts_pulang from kamar_inap where no_rawat='" + norawat + "' and stts_pulang not in ('-','pindah kamar')");
                if (cekstsPulang.equals("Dirujuk")) {
                    kdPulang = "2";
                    cmbcrPulang.setSelectedIndex(1);
                } else if (cekstsPulang.equals("APS")) {
                    kdPulang = "3";
                    cmbcrPulang.setSelectedIndex(2);
                } else if (cekstsPulang.equals("Meninggal >= 48 Jam") || cekstsPulang.equals("Meninggal < 48 Jam")) {
                    kdPulang = "4";
                    cmbcrPulang.setSelectedIndex(3);
                } else if (cekstsPulang.equals("Sembuh/BLPL")) {
                    kdPulang = "1";
                    cmbcrPulang.setSelectedIndex(4);
                } else if (cekstsPulang.equals("Kabur")) {
                    kdPulang = "5";
                    cmbcrPulang.setSelectedIndex(5);
                }

                //pembiayaan
                if (nilaiPembiayaan.equals("")) {
                    cmbPembiayaan.setSelectedIndex(0);
                } else if (nilaiPembiayaan.equals("1")) {
                    cmbPembiayaan.setSelectedIndex(1);
                } else if (nilaiPembiayaan.equals("2")) {
                    cmbPembiayaan.setSelectedIndex(2);
                } else if (nilaiPembiayaan.equals("3")) {
                    cmbPembiayaan.setSelectedIndex(3);
                }
                
                //tekanan darah
                if (Sequel.cariInteger("select count(-1) from pemeriksaan_ranap where no_rawat = '" + norawat + "'") > 0) {
                    if (Sequel.cariIsi("select tensi from pemeriksaan_ranap where no_rawat = '" + norawat + "'").equals("")) {
                        sistole.setText("0");
                        diastole.setText("0");
                    } else {
                        sistole.setText(Sequel.cariIsi("select SUBSTRING_INDEX(tensi,'/',1) sistole from pemeriksaan_ranap where no_rawat = '" + norawat + "'"));
                        diastole.setText(Sequel.cariIsi("select SUBSTRING_INDEX(tensi,'/',-1) diastole from pemeriksaan_ranap where no_rawat = '" + norawat + "'"));
                    }

                } else {
                    sistole.setText("0");
                    diastole.setText("0");
                }

            //jns. pelayanan 2 (rawat jalan)  
            } else if (jpel.equals("2")) {
                labelrwt.setText("JALAN");
                ChkKelasEksekutif.setEnabled(true);
                tarifPoliExe.setEnabled(false);
                ChkNaikTurun.setEnabled(false);
                cmbKP.setEnabled(false);
                cmbPembiayaan.setEnabled(false);
                losNaikKls.setEnabled(false);
                ChkRawatIntensif.setEnabled(false);
                losIntensif.setEnabled(false);
                ventilator.setEnabled(false);
                wktMasuk.setText(Sequel.cariIsi("select ifnull(concat(date_format(tgl_registrasi,'%d %b %Y'),' ',jam_reg),'') from reg_periksa where no_rawat='" + norawat + "'"));
                wktPulang.setText(wktMasuk.getText());
                tglmsk = Sequel.cariIsi("SELECT CONCAT(bs.tglsep,' ',rp.jam_reg) FROM reg_periksa rp "
                        + "INNER JOIN bridging_sep bs ON bs.no_rawat=rp.no_rawat WHERE bs.no_rawat='" + norawat + "'");
                tglplg = tglmsk;
                kdPulang = "1";
                cmbcrPulang.setSelectedIndex(4);
                dpjp.setText(Sequel.cariIsi("select d.nm_dokter from reg_periksa rp inner join dokter d on d.kd_dokter=rp.kd_dokter where rp.no_rawat='" + norawat + "'"));
                labelLOS.setText("1");
                
                if (Sequel.cariInteger("select count(-1) from pemeriksaan_ralan_petugas where no_rawat = '" + norawat + "'") > 0) {
                    if (Sequel.cariIsi("select tensi from pemeriksaan_ralan_petugas where no_rawat = '" + norawat + "'").equals("")) {
                        sistole.setText("0");
                        diastole.setText("0");
                    } else {
                        sistole.setText(Sequel.cariIsi("select SUBSTRING_INDEX(tensi,'/',1) sistole from pemeriksaan_ralan_petugas where no_rawat = '" + norawat + "'"));
                        diastole.setText(Sequel.cariIsi("select SUBSTRING_INDEX(tensi,'/',-1) diastole from pemeriksaan_ralan_petugas where no_rawat = '" + norawat + "'"));
                    }

                } else {
                    sistole.setText("0");
                    diastole.setText("0");
                }
            }

        } else {
            cmbVentilator1.setEnabled(false);
            cmbVentilator1.setSelectedIndex(1);
            tglIntubasi1.setDate(new Date());
            tglEkstubasi1.setDate(new Date());
            tglIntubasi1.setEnabled(false);
            tglEkstubasi1.setEnabled(false);
            
            //kelengkapan meninggal
            ChkSemuaPelengkap.setSelected(false);
            ChkPemulasaran.setSelected(false);
            ChkPlastik.setSelected(false);
            ChkKantong.setSelected(false);
            ChkDesinfekJen.setSelected(false);
            ChkDesinfekMob.setSelected(false);
            ChkPeti.setSelected(false);
            ChkTranspot.setSelected(false);

            ChkSemuaPelengkap.setEnabled(false);
            ChkPemulasaran.setEnabled(false);
            ChkPlastik.setEnabled(false);
            ChkKantong.setEnabled(false);
            ChkDesinfekJen.setEnabled(false);
            ChkDesinfekMob.setEnabled(false);
            ChkPeti.setEnabled(false);
            ChkTranspot.setEnabled(false);

            //faktor pengurang
            ChkSemuaFaktor.setSelected(false);
            ChkAsam.setSelected(false);
            ChkKultur.setSelected(false);
            ChkAPTT.setSelected(false);
            ChkAnalisa.setSelected(false);
            ChkProcal.setSelected(false);
            ChkDimer.setSelected(false);
            ChkWaktu.setSelected(false);
            ChkAlbumin.setSelected(false);
            ChkCRP.setSelected(false);
            ChkPT.setSelected(false);
            ChkAnti.setSelected(false);
            ChkThorax.setSelected(false);
            
            ChkSemuaFaktor.setEnabled(true);
            ChkAsam.setEnabled(true);
            ChkKultur.setEnabled(true);
            ChkAPTT.setEnabled(true);
            ChkAnalisa.setEnabled(true);
            ChkProcal.setEnabled(true);
            ChkDimer.setEnabled(true);
            ChkWaktu.setEnabled(true);
            ChkAlbumin.setEnabled(true);
            ChkCRP.setEnabled(true);
            ChkPT.setEnabled(true);
            ChkAnti.setEnabled(true);
            ChkThorax.setEnabled(true);

            //---------------------------------------------------
            Valid.tabelKosong(tabMode4);
            Valid.tabelKosong(tabMode9);
            Valid.tabelKosong(tabMode10);
            cmbUnggah.setSelectedIndex(0);
            lokasiFile.setText("");
            kirimEPISOD = "";
            hari.setText("0");
            cmbCO.setSelectedIndex(1);
            cmbKomor.setSelectedIndex(0);
            cmbStatus.setSelectedIndex(2);
            cmbEpisod.setSelectedIndex(0);
            cmbCrMasuk1.setSelectedIndex(0);
            umur1.setText(Sequel.cariIsi("select concat(umurdaftar,' ','" + sts_umur_ok + "') from reg_periksa where no_rawat='" + norawat + "'"));
            cekBB = Sequel.cariIsi("select berat_badan_benar from pasien_bayi where no_rkm_medis='" + norm.getText() + "'");
            
            ChkRawatIntensif1.setEnabled(false);
            ChkRawatIntensif1.setSelected(false);
            losIntensif1.setEnabled(false);
            losIntensif1.setText("0");
            ventilator1.setEnabled(false);
            ventilator1.setText("0");
            cmbKelainan.setEnabled(false);
            cmbKelainan.setSelectedIndex(0);
            labelnmIbu.setVisible(false);
            cmbID.setEnabled(true);
            cmbID.setSelectedIndex(0);
            noID.setEditable(true);
            cmbIsolasi.setEnabled(true);
            cmbIsolasi.setSelectedIndex(0);

            cmbEpisod.setEnabled(true);
            hari.setEnabled(true);
            BtnAddEpisod.setEnabled(true);
            BtnDelEpisod.setEnabled(true);
            cmbStatus.setEnabled(true);
            cmbStatus.setSelectedIndex(2);
            cmbRS.setEnabled(true);
            cmbRS.setSelectedIndex(1);
            cmbKriteria.setEnabled(true);
            cmbKriteria.setSelectedIndex(1);
            cmbCO.setEnabled(true);
            cmbKomor.setEnabled(true);

            if (cekBB.equals("")) {
                brtlhr1.setText("0");
            } else if (!cekBB.equals("")) {
                brtlhr1.setText(cekBB);
            }

            //jns. pelayanan 1 (rawat inap)
            if (jpel.equals("1")) {
                labelrwt1.setText("INAP");
                noKlaim.setText(Sequel.cariIsi("SELECT claim_number FROM eklaim_generate_claim where no_rawat='" + norawat + "'"));
                tampilTerupload();
                cmbCO.setEnabled(true);
                cmbRS.setEnabled(true);
                cmbRS.setSelectedItem(1);                
                tbEpisod.setEnabled(true);
                wktMasuk1.setText(Sequel.cariIsi("select ifnull(concat(date_format(tgl_registrasi,'%d %b %Y'),' ',jam_reg),'') from reg_periksa where no_rawat='" + norawat + "'"));
                wktPulang1.setText(Sequel.cariIsi("select concat(date_format(ki.tgl_keluar,'%d %b %Y'),' ',ki.jam_keluar) from kamar_inap ki "
                        + "inner join reg_periksa rp on rp.no_rawat=ki.no_rawat where ki.no_rawat='" + norawat + "' and ki.stts_pulang not in ('-','pindah kamar')"));
                tglmsk = Sequel.cariIsi("select concat(tgl_registrasi,' ',jam_reg) from reg_periksa where no_rawat='" + norawat + "'");
                tglplg = Sequel.cariIsi("select concat(ki.tgl_keluar,' ',ki.jam_keluar) from kamar_inap ki inner join reg_periksa rp on rp.no_rawat=ki.no_rawat where "
                        + "ki.no_rawat='" + norawat + "' and ki.stts_pulang not in ('-','pindah kamar')");
                cekstsPulang = Sequel.cariIsi("select stts_pulang from kamar_inap where no_rawat='" + norawat + "' and stts_pulang not in ('-','pindah kamar')");
                dpjp1.setText(Sequel.cariIsi("select d.nm_dokter from dpjp_ranap dr inner join dokter d on d.kd_dokter=dr.kd_dokter where dr.no_rawat='" + norawat + "'"));
                losNaikKls.setText("0");
                labelLOS1.setText(Sequel.cariIsi("select DATEDIFF(k.tgl_keluar,r.tgl_registrasi)+1 from kamar_inap k "
                        + "inner join reg_periksa r on r.no_rawat = k.no_rawat where k.stts_pulang not in ('-','Pindah Kamar') and k.no_rawat = '" + norawat + "'"));

                if (kodePayor.equals("73")) {
                    ChkRawatIntensif1.setEnabled(true);
                    ChkRawatIntensif1.setSelected(false);
                    cmbKelainan.setEnabled(true);
                    cmbKelainan.setSelectedIndex(0);
                    labelnmIbu.setVisible(true);
                    cmbID.setEnabled(false);
                    cmbID.setSelectedIndex(11);
                    noID.setEditable(false);
                    BtnAddEpisod.setEnabled(false);
                    BtnDelEpisod.setEnabled(false);
                    cmbStatus.setEnabled(false);
                    cmbRS.setEnabled(false);
                    tarifPlasma.setEnabled(false);
                    cmbIsolasi.setEnabled(false);
                    cmbKriteria.setEnabled(false);
                    cmbCO.setEnabled(false);
                    cmbKomor.setEnabled(false);

                    ChkSemuaFaktor.setEnabled(false);
                    ChkAsam.setEnabled(false);
                    ChkKultur.setEnabled(false);
                    ChkAPTT.setEnabled(false);
                    ChkAnalisa.setEnabled(false);
                    ChkProcal.setEnabled(false);
                    ChkDimer.setEnabled(false);
                    ChkWaktu.setEnabled(false);
                    ChkAlbumin.setEnabled(false);
                    ChkCRP.setEnabled(false);
                    ChkPT.setEnabled(false);
                    ChkAnti.setEnabled(false);
                    ChkThorax.setEnabled(false);

                    ChkSemuaFaktor.setSelected(false);
                    ChkAsam.setSelected(false);
                    ChkKultur.setSelected(false);
                    ChkAPTT.setSelected(false);
                    ChkAnalisa.setSelected(false);
                    ChkProcal.setSelected(false);
                    ChkDimer.setSelected(false);
                    ChkWaktu.setSelected(false);
                    ChkAlbumin.setSelected(false);
                    ChkCRP.setSelected(false);
                    ChkPT.setSelected(false);
                    ChkAnti.setSelected(false);
                    ChkThorax.setSelected(false);
                }
                
                if (kodePayor.equals("72") || kodePayor.equals("75")) {
                    cmbCO.setEnabled(false);
                    cmbKomor.setEnabled(false);
                    ChkRawatIntensif1.setEnabled(true);
                    ChkRawatIntensif1.setSelected(false);
                    cmbKriteria.setEnabled(false);
                    cmbIsolasi.setEnabled(false);
                    cmbStatus.setEnabled(false);
                    cmbRS.setEnabled(false);
                    tarifPlasma.setEnabled(false); 
                    BtnAddEpisod.setEnabled(false);
                    BtnDelEpisod.setEnabled(false);
                    
                    ChkSemuaFaktor.setEnabled(false);
                    ChkAsam.setEnabled(false);
                    ChkKultur.setEnabled(false);
                    ChkAPTT.setEnabled(false);
                    ChkAnalisa.setEnabled(false);
                    ChkProcal.setEnabled(false);
                    ChkDimer.setEnabled(false);
                    ChkWaktu.setEnabled(false);
                    ChkAlbumin.setEnabled(false);
                    ChkCRP.setEnabled(false);
                    ChkPT.setEnabled(false);
                    ChkAnti.setEnabled(false);
                    ChkThorax.setEnabled(false);

                    ChkSemuaFaktor.setSelected(false);
                    ChkAsam.setSelected(false);
                    ChkKultur.setSelected(false);
                    ChkAPTT.setSelected(false);
                    ChkAnalisa.setSelected(false);
                    ChkProcal.setSelected(false);
                    ChkDimer.setSelected(false);
                    ChkWaktu.setSelected(false);
                    ChkAlbumin.setSelected(false);
                    ChkCRP.setSelected(false);
                    ChkPT.setSelected(false);
                    ChkAnti.setSelected(false);
                    ChkThorax.setSelected(false);
                }

                if (cmbStatus.getSelectedIndex() == 2) {
                    if (kodePayor.equals("73") || kodePayor.equals("72") || kodePayor.equals("75")) {
                        tarifPlasma.setEnabled(false);
                        tarifPlasma.setText("0");
                    } else {
                        tarifPlasma.setEnabled(true);
                        tarifPlasma.setText("0");
                    }
                } else {
                    tarifPlasma.setEnabled(false);
                    tarifPlasma.setText("0");
                }

                if (cekstsPulang.equals("Dirujuk")) {
                    kdPulang = "2";
                    cmbcrPulang1.setSelectedIndex(1);
                } else if (cekstsPulang.equals("APS")) {
                    kdPulang = "3";
                    cmbcrPulang1.setSelectedIndex(2);
                } else if (cekstsPulang.equals("Meninggal >= 48 Jam") || cekstsPulang.equals("Meninggal < 48 Jam")) {
                    kdPulang = "4";
                    cmbcrPulang1.setSelectedIndex(3);

                    if (kodePayor.equals("73") || kodePayor.equals("72") || kodePayor.equals("75")) {
                        ChkSemuaPelengkap.setEnabled(false);
                        ChkPemulasaran.setEnabled(false);
                        ChkPlastik.setEnabled(false);
                        ChkKantong.setEnabled(false);
                        ChkDesinfekJen.setEnabled(false);
                        ChkDesinfekMob.setEnabled(false);
                        ChkPeti.setEnabled(false);
                        ChkTranspot.setEnabled(false);

                        ChkSemuaPelengkap.setSelected(false);
                        ChkPemulasaran.setSelected(false);
                        ChkPlastik.setSelected(false);
                        ChkKantong.setSelected(false);
                        ChkDesinfekJen.setSelected(false);
                        ChkDesinfekMob.setSelected(false);
                        ChkPeti.setSelected(false);
                        ChkTranspot.setSelected(false);
                    } else {
                        ChkSemuaPelengkap.setEnabled(true);
                        ChkPemulasaran.setEnabled(true);
                        ChkPlastik.setEnabled(true);
                        ChkKantong.setEnabled(true);
                        ChkDesinfekJen.setEnabled(true);
                        ChkDesinfekMob.setEnabled(true);
                        ChkPeti.setEnabled(true);
                        ChkTranspot.setEnabled(true);
                    }
                } else if (cekstsPulang.equals("Sembuh/BLPL")) {
                    kdPulang = "1";
                    cmbcrPulang1.setSelectedIndex(4);
                } else if (cekstsPulang.equals("Kabur")) {
                    kdPulang = "5";
                    cmbcrPulang1.setSelectedIndex(5);
                }

                //jns. pelayanan 2 (rawat jalan)  
            } else if (jpel.equals("2")) {
                labelrwt1.setText("JALAN");
                noKlaim.setText(Sequel.cariIsi("SELECT claim_number FROM eklaim_generate_claim where no_rawat='" + norawat + "'"));
                tampilTerupload();
                tbEpisod.setEnabled(false);
                wktMasuk1.setText(Sequel.cariIsi("select ifnull(concat(date_format(tgl_registrasi,'%d %b %Y'),' ',jam_reg),'') from reg_periksa where no_rawat='" + norawat + "'"));
                wktPulang1.setText(wktMasuk1.getText());
                tglmsk = Sequel.cariIsi("select concat(tgl_registrasi,' ',jam_reg) from reg_periksa where no_rawat='" + norawat + "'");
                tglplg = tglmsk;
                kdPulang = "1";
                cmbcrPulang1.setSelectedIndex(4);
                dpjp1.setText(Sequel.cariIsi("select d.nm_dokter from reg_periksa rp inner join dokter d on d.kd_dokter=rp.kd_dokter where rp.no_rawat='" + norawat + "'"));
                labelLOS1.setText("1");

                losIntensif1.setEnabled(false);
                losIntensif1.setText("0");
                ventilator1.setEnabled(false);
                ventilator1.setText("0");
                cmbKelainan.setEnabled(false);
                cmbKelainan.setSelectedIndex(0);
                labelnmIbu.setVisible(false);
                cmbID.setEnabled(true);
                noID.setEditable(true);
                tarifPlasma.setEnabled(false);
                tarifPlasma.setText("0");
                BtnAddEpisod.setEnabled(false);
                BtnDelEpisod.setEnabled(false);
                
                if (kodePayor.equals("72")) {
                    cmbCO.setEnabled(false);
                    cmbKomor.setEnabled(false);
                    cmbKriteria.setEnabled(false);
                    cmbIsolasi.setEnabled(false);
                    cmbStatus.setEnabled(false);
                    cmbRS.setEnabled(false);
                    tarifPlasma.setEnabled(false); 
                    BtnAddEpisod.setEnabled(false);
                    BtnDelEpisod.setEnabled(false);
                    
                    ChkSemuaFaktor.setEnabled(false);
                    ChkAsam.setEnabled(false);
                    ChkKultur.setEnabled(false);
                    ChkAPTT.setEnabled(false);
                    ChkAnalisa.setEnabled(false);
                    ChkProcal.setEnabled(false);
                    ChkDimer.setEnabled(false);
                    ChkWaktu.setEnabled(false);
                    ChkAlbumin.setEnabled(false);
                    ChkCRP.setEnabled(false);
                    ChkPT.setEnabled(false);
                    ChkAnti.setEnabled(false);
                    ChkThorax.setEnabled(false);

                    ChkSemuaFaktor.setSelected(false);
                    ChkAsam.setSelected(false);
                    ChkKultur.setSelected(false);
                    ChkAPTT.setSelected(false);
                    ChkAnalisa.setSelected(false);
                    ChkProcal.setSelected(false);
                    ChkDimer.setSelected(false);
                    ChkWaktu.setSelected(false);
                    ChkAlbumin.setSelected(false);
                    ChkCRP.setSelected(false);
                    ChkPT.setSelected(false);
                    ChkAnti.setSelected(false);
                    ChkThorax.setSelected(false);
             
                    ChkSemuaPelengkap.setEnabled(false);
                    ChkPemulasaran.setEnabled(false);
                    ChkPlastik.setEnabled(false);
                    ChkKantong.setEnabled(false);
                    ChkDesinfekJen.setEnabled(false);
                    ChkDesinfekMob.setEnabled(false);
                    ChkPeti.setEnabled(false);
                    ChkTranspot.setEnabled(false);

                    ChkSemuaPelengkap.setSelected(false);
                    ChkPemulasaran.setSelected(false);
                    ChkPlastik.setSelected(false);
                    ChkKantong.setSelected(false);
                    ChkDesinfekJen.setSelected(false);
                    ChkDesinfekMob.setSelected(false);
                    ChkPeti.setSelected(false);
                    ChkTranspot.setSelected(false);
                }                
            }
        }
    }

    private void dataKlaimLama(String tglsep) {
        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            try {
                ps6 = koneksi.prepareStatement("SELECT enc.no_rawat, ifnull(DATE_FORMAT(esc.tgl_masuk,'%d %b %Y %H:%i:%S'),'') tglMasuk, DATE_FORMAT(esc.tgl_pulang,'%d %b %Y %H:%i:%S') tglPulang, "
                        + "DATEDIFF(esc.tgl_pulang,esc.tgl_masuk)+1 los, ifnull(esa.menit_1_appearance,'') menit_1_appearance, ifnull(esa.menit_1_pulse,'') menit_1_pulse, "
                        + "ifnull(esa.menit_1_grimace,'') menit_1_grimace, ifnull(esa.menit_1_activity,'') menit_1_activity, ifnull(esa.menit_1_respiration,'') menit_1_respiration, "
                        + "ifnull(esa.menit_5_appearance,'') menit_5_appearance, ifnull(esa.menit_5_pulse,'') menit_5_pulse, ifnull(esa.menit_5_grimace,'') menit_5_grimace, "
                        + "ifnull(esa.menit_5_activity,'') menit_5_activity, ifnull(esa.menit_5_respiration,'') menit_5_respiration, ifnull(esp.usia_kehamilan,'') usia_kehamilan, "
                        + "ifnull(esp.gravida,'') gravida, ifnull(esp.partus,'') partus, ifnull(esp.abortus,'') abortus, ifnull(esp.onset_kontraksi,'') onset_kontraksi, "
                        + "ifnull(esc.ventilator_start_dttm,now()) tglintu, ifnull(esc.ventilator_stop_dttm,now()) tglextu, ifnull(esc.kantong_darah,'') kantong_darah, esc.* "
                        + "FROM eklaim_set_claim esc INNER JOIN eklaim_new_claim enc ON enc.no_sep = esc.no_sep left join eklaim_set_apgar esa on esa.no_sep=esc.no_sep "
                        + "left join eklaim_set_persalinan esp on esp.no_sep=esc.no_sep where "
                        + "esc.no_sep='" + noSEP.getText() + "' and date(esc.tgl_masuk)='" + tglsep + "'");
                try {
                    rs6 = ps6.executeQuery();
                    while (rs6.next()) {
                        noPeserta.setText(rs6.getString("no_kartu"));
                        cekCOB = rs6.getString("cob_cd");
                        jpel = rs6.getString("jenis_rawat");
                        naikTurunkls = rs6.getString("upgrade_class_ind");
                        naikKLS = rs6.getString("upgrade_class_class");
                        icuindikator = rs6.getString("icu_indikator");
                        wktMasuk.setText(rs6.getString("tglMasuk"));
                        wktPulang.setText(rs6.getString("tglPulang"));
                        tglmsk = rs6.getString("tgl_masuk");
                        tglplg = rs6.getString("tgl_pulang");
                        kdPulang = rs6.getString("discharge_status");
                        dpjp.setText(rs6.getString("nama_dokter"));
                        subakut.setText(rs6.getString("adl_sub_acute"));
                        kronik.setText(rs6.getString("adl_chronic"));
                        brtlhr.setText(rs6.getString("birth_weight"));
                        cekklsLAGI = rs6.getString("kelas_rawat");
                        umur.setText(Sequel.cariIsi("select concat(umurdaftar,' ','" + sts_umur_ok + "') from reg_periksa where no_rawat='" + norawat + "'"));
                        pros_inadrg1.setText(rs6.getString("procedure_inagrouper"));
                        caramasuk = rs6.getString("cara_masuk");
                        nilaiPembiayaan = rs6.getString("upgrade_class_payor");
                        sistole.setText(rs6.getString("sistole"));
                        diastole.setText(rs6.getString("diastole"));
                        nilaiVenti = rs6.getString("ventilator_use_ind").replaceAll("", "");
                        tglIntu = rs6.getString("tglintu");
                        tglEkstu = rs6.getString("tglextu");
                        dializer = rs6.getString("dializer_single_use");
                        TkntngDrh.setText(rs6.getString("kantong_darah").replaceAll("", "0"));
                        
                        TUsiaHml.setText(rs6.getString("usia_kehamilan"));
                        Tgravida.setText(rs6.getString("gravida"));
                        Tpartus.setText(rs6.getString("partus"));
                        Tabortus.setText(rs6.getString("abortus"));                        
                        if (rs6.getString("onset_kontraksi").equals("")) {
                            cmbOnset.setSelectedIndex(0);
                        } else if (rs6.getString("onset_kontraksi").equals("spontan")) {
                            cmbOnset.setSelectedIndex(1);
                        } else if (rs6.getString("onset_kontraksi").equals("induksi")) {
                            cmbOnset.setSelectedIndex(2);
                        } else if (rs6.getString("onset_kontraksi").equals("non_spontan_non_induksi")) {
                            cmbOnset.setSelectedIndex(3);
                        }                        
                        tampilDelivery();
                        
                        nilaiPNB = rs6.getDouble("tarif_prosedur_non_bedah");
                        pnb.setText(Valid.SetAngka(nilaiPNB));
                        nilaiRI = rs6.getDouble("tarif_rawat_intensif");
                        ri.setText(Valid.SetAngka(nilaiRI));
                        nilaiKAM = rs6.getDouble("tarif_kamar");
                        kam.setText(Valid.SetAngka(nilaiKAM));
                        nilaiKON = rs6.getDouble("tarif_konsultasi");
                        kon.setText(Valid.SetAngka(nilaiKON));
                        nilaiKEP = rs6.getDouble("tarif_keperawatan");
                        kep.setText(Valid.SetAngka(nilaiKEP));
                        nilaiREH = rs6.getDouble("tarif_rehabilitasi");
                        reh.setText(Valid.SetAngka(nilaiREH));
                        nilaiRAD = rs6.getDouble("tarif_radiologi");
                        rad.setText(Valid.SetAngka(nilaiRAD));
                        nilaiLAB = rs6.getDouble("tarif_laboratorium");
                        lab.setText(Valid.SetAngka(nilaiLAB));
                        nilaiOBAT = rs6.getDouble("tarif_obat");
                        obat.setText(Valid.SetAngka(nilaiOBAT));
                        nilaiPB = rs6.getDouble("tarif_prosedur_bedah");
                        pb.setText(Valid.SetAngka(nilaiPB));                        
                        
                        //apgar menit 1
                        if (rs6.getString("menit_1_appearance").equals("")) {
                            cmbMnt1App.setSelectedIndex(0);
                        } else {
                            cmbMnt1App.setSelectedItem(rs6.getString("menit_1_appearance"));
                        }
                        
                        if (rs6.getString("menit_1_pulse").equals("")) {
                            cmbMnt1Pul.setSelectedIndex(0);
                        } else {
                            cmbMnt1Pul.setSelectedItem(rs6.getString("menit_1_pulse"));
                        }
                        
                        if (rs6.getString("menit_1_grimace").equals("")) {
                            cmbMnt1Gri.setSelectedIndex(0);
                        } else {
                            cmbMnt1Gri.setSelectedItem(rs6.getString("menit_1_grimace"));
                        }
                        
                        if (rs6.getString("menit_1_activity").equals("")) {
                            cmbMnt1Act.setSelectedIndex(0);
                        } else {
                            cmbMnt1Act.setSelectedItem(rs6.getString("menit_1_activity"));
                        }
                        
                        if (rs6.getString("menit_1_respiration").equals("")) {
                            cmbMnt1Res.setSelectedIndex(0);
                        } else {
                            cmbMnt1Res.setSelectedItem(rs6.getString("menit_1_respiration"));
                        }
                        
                        //apgar menit 5
                        if (rs6.getString("menit_5_appearance").equals("")) {
                            cmbMnt5App.setSelectedIndex(0);
                        } else {
                            cmbMnt5App.setSelectedItem(rs6.getString("menit_5_appearance"));
                        }
                        
                        if (rs6.getString("menit_5_pulse").equals("")) {
                            cmbMnt5Pul.setSelectedIndex(0);
                        } else {
                            cmbMnt5Pul.setSelectedItem(rs6.getString("menit_5_pulse"));
                        }
                        
                        if (rs6.getString("menit_5_grimace").equals("")) {
                            cmbMnt5Gri.setSelectedIndex(0);
                        } else {
                            cmbMnt5Gri.setSelectedItem(rs6.getString("menit_5_grimace"));
                        }
                        
                        if (rs6.getString("menit_5_activity").equals("")) {
                            cmbMnt5Act.setSelectedIndex(0);
                        } else {
                            cmbMnt5Act.setSelectedItem(rs6.getString("menit_5_activity"));
                        }
                        
                        if (rs6.getString("menit_5_respiration").equals("")) {
                            cmbMnt5Res.setSelectedIndex(0);
                        } else {
                            cmbMnt5Res.setSelectedItem(rs6.getString("menit_5_respiration"));
                        }
                        
                        //waktu ventilator
                        if (nilaiVenti.equals("1")) {                            
                            cmbVentilator.setSelectedIndex(0);
                            Valid.SetTgl2(tglIntubasi, tglIntu);
                            Valid.SetTgl2(tglEkstubasi, tglEkstu);
                            cmbVentilator.setEnabled(true);
                            tglIntubasi.setEnabled(true);
                            tglEkstubasi.setEnabled(true);
                            
                        } else if (nilaiVenti.equals("0")) {
                            cmbVentilator.setSelectedIndex(1);
                            tglIntubasi.setDate(new Date());
                            tglEkstubasi.setDate(new Date());
                            cmbVentilator.setEnabled(false);
                            tglIntubasi.setEnabled(false);
                            tglEkstubasi.setEnabled(false);
                            
                        } else {
                            cmbVentilator.setSelectedIndex(1);
                            tglIntubasi.setDate(new Date());
                            tglEkstubasi.setDate(new Date());
                            cmbVentilator.setEnabled(false);
                            tglIntubasi.setEnabled(false);
                            tglEkstubasi.setEnabled(false);
                        }
                        
                        //penggunaan dializer
                        if (dializer.equals("0")) {
                            cmbDializer.setSelectedIndex(0);
                        } else if (dializer.equals("1")) {
                            cmbDializer.setSelectedIndex(1);
                        }
                    
                        //tekanan darah
                        if (sistole.getText().equals("")) {
                            sistole.setText("0");
                        }

                        if (diastole.getText().equals("")) {
                            diastole.setText("0");
                        }

                        if (kdPulang.equals("2")) {
                            cmbcrPulang.setSelectedIndex(1);
                        } else if (kdPulang.equals("3")) {
                            cmbcrPulang.setSelectedIndex(2);
                        } else if (kdPulang.equals("4")) {
                            cmbcrPulang.setSelectedIndex(3);
                        } else if (kdPulang.equals("1")) {
                            cmbcrPulang.setSelectedIndex(4);
                        } else if (kdPulang.equals("5")) {
                            cmbcrPulang.setSelectedIndex(5);
                        }

                        if (cekCOB.equals("-")) {
                            cmbCOB.setSelectedIndex(0);
                        } else {
                            cmbCOB.setSelectedIndex(0);
                        }
                        
                        if (caramasuk.equals("gp") || caramasuk.equals("")) {
                            cmbCrMasuk.setSelectedIndex(0);
                        } else if (caramasuk.equals("hosp-trans")) {
                            cmbCrMasuk.setSelectedIndex(1);
                        } else if (caramasuk.equals("mp")) {
                            cmbCrMasuk.setSelectedIndex(2);
                        } else if (caramasuk.equals("outp")) {
                            cmbCrMasuk.setSelectedIndex(3);
                        } else if (caramasuk.equals("inp")) {
                            cmbCrMasuk.setSelectedIndex(4);
                        } else if (caramasuk.equals("emd")) {
                            cmbCrMasuk.setSelectedIndex(5);
                        } else if (caramasuk.equals("born")) {
                            cmbCrMasuk.setSelectedIndex(6);
                        } else if (caramasuk.equals("nursing")) {
                            cmbCrMasuk.setSelectedIndex(7);
                        } else if (caramasuk.equals("psych")) {
                            cmbCrMasuk.setSelectedIndex(8);
                        } else if (caramasuk.equals("rehab")) {
                            cmbCrMasuk.setSelectedIndex(9);
                        } else if (caramasuk.equals("other")) {
                            cmbCrMasuk.setSelectedIndex(10);
                        }

                        //jns. pelayanan 1 (rawat inap)
                        if (jpel.equals("1")) {
                            labelrwt.setText("INAP");
                            ChkKelasEksekutif.setEnabled(false);
                            ChkKelasEksekutif.setSelected(false);
                            tarifPoliExe.setEnabled(false );
                            tarifPoliExe.setText("0");
                            labeltarif.setText("0");
                            labelLOS.setText(rs6.getString("los"));

                            if (naikTurunkls.equals("0")) {
                                ChkNaikTurun.setEnabled(true);
                                ChkNaikTurun.setSelected(false);
                                cmbKP.setEnabled(false);
                                cmbKP.setSelectedIndex(0);                                
                                losNaikKls.setEnabled(false);
                                losNaikKls.setText("0");
                                cmbPembiayaan.setEnabled(false);
                                cmbPembiayaan.setSelectedIndex(0);
                            } else {
                                ChkNaikTurun.setEnabled(true);
                                ChkNaikTurun.setSelected(true);
                                
                                cmbKP.setEnabled(true);
                                if (naikKLS.equals("kelas_1")) {
                                    cmbKP.setSelectedIndex(2);
                                } else if (naikKLS.equals("kelas_2")) {
                                    cmbKP.setSelectedIndex(1);
                                } else if (naikKLS.equals("vip")) {
                                    cmbKP.setSelectedIndex(3);
                                } else if (naikKLS.equals("vvip")) {
                                    cmbKP.setSelectedIndex(4);
                                }
                                
                                //pembiayaan
                                cmbPembiayaan.setEnabled(true);
                                if (nilaiPembiayaan.equals("")) {
                                    cmbPembiayaan.setSelectedIndex(0);
                                } else if (nilaiPembiayaan.equals("peserta")) {
                                    cmbPembiayaan.setSelectedIndex(1);
                                } else if (nilaiPembiayaan.equals("pemberi_kerja")) {
                                    cmbPembiayaan.setSelectedIndex(2);
                                } else if (nilaiPembiayaan.equals("asuransi_tambahan")) {
                                    cmbPembiayaan.setSelectedIndex(3);
                                }
                                
                                losNaikKls.setEnabled(true);
                                losNaikKls.setText(rs6.getString("upgrade_class_los"));
                            }

                            if (icuindikator.equals("0")) {
                                ChkRawatIntensif.setEnabled(true);
                                ChkRawatIntensif.setSelected(false);
                                losIntensif.setEnabled(false);
                                losIntensif.setText("0");
                                ventilator.setEnabled(false);
                                ventilator.setText("0");
                            } else {
                                ChkRawatIntensif.setEnabled(true);
                                ChkRawatIntensif.setSelected(true);
                                losIntensif.setEnabled(true);
                                losIntensif.setText(rs6.getString("icu_los"));
                                ventilator.setEnabled(true);
                                ventilator.setText(rs6.getString("ventilator_hour"));
                            }

                            if (cekklsLAGI.equals("1")) {
                                cmbKH.setSelectedIndex(2);
                                kls = "1";
                            } else if (cekklsLAGI.equals("2")) {
                                cmbKH.setSelectedIndex(1);                                
                                kls = "2";
                            } else if (cekklsLAGI.equals("3")) {
                                cmbKH.setSelectedIndex(0);
                                kls = "3";
                            }

                        //jns. pelayanan 2 (rawat jalan)  
                        } else if (jpel.equals("2")) {
                            labelrwt.setText("JALAN");
                            labelLOS.setText("1");
                            if (cekklsLAGI.equals("1")) {
                                ChkKelasEksekutif.setEnabled(true);
                                ChkKelasEksekutif.setSelected(true);
                                cmbKH.setSelectedIndex(2);
                                kls = "1";
                                tarifPoliExe.setEnabled(true);
                                tarifPoliExe.setText(rs6.getString("tarif_poli_eks"));
                            } else if (cekklsLAGI.equals("3")) {
                                ChkKelasEksekutif.setEnabled(true);
                                ChkKelasEksekutif.setSelected(false);
                                cmbKH.setSelectedIndex(0);
                                kls = "3";
                                tarifPoliExe.setEnabled(true);
                                tarifPoliExe.setText(rs6.getString("tarif_poli_eks"));
                            }

                            if (naikTurunkls.equals("0")) {
                                ChkNaikTurun.setEnabled(false);
                                ChkNaikTurun.setSelected(false);
                                cmbKP.setEnabled(false);
                                cmbKP.setSelectedIndex(0);
                                losNaikKls.setEnabled(false);
                                losNaikKls.setText("0");
                                cmbPembiayaan.setEnabled(false);
                                cmbPembiayaan.setSelectedIndex(0);
                                if (naikKLS.equals("")) {
                                    cmbKP.setSelectedIndex(0);
                                    cmbPembiayaan.setSelectedIndex(0);
                                }
                            }

                            if (icuindikator.equals("0")) {
                                ChkRawatIntensif.setEnabled(false);
                                ChkRawatIntensif.setSelected(false);
                                losIntensif.setEnabled(false);
                                losIntensif.setText("0");
                                ventilator.setEnabled(false);
                                ventilator.setText("0");
                            }
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
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }

        } else {
            try {
                ps6 = koneksi.prepareStatement("SELECT enc.no_rawat, ifnull(DATE_FORMAT(esc.tgl_masuk,'%d %b %Y %H:%i:%S'),'') tglMasuk, "
                        + "DATE_FORMAT(esc.tgl_pulang,'%d %b %Y %H:%i:%S') tglPulang, DATEDIFF(esc.tgl_pulang,esc.tgl_masuk)+1 los, esc.* "
                        + "FROM eklaim_set_claim esc INNER JOIN eklaim_new_claim enc ON enc.no_sep = esc.no_sep where "
                        + "esc.no_sep='" + Sequel.cariIsi("SELECT claim_number FROM eklaim_generate_claim where no_rawat='" + norawat + "'") + "'");
                try {
                    rs6 = ps6.executeQuery();
                    while (rs6.next()) {
                        noID.setText(rs6.getString("no_kartu"));
                        jpel = rs6.getString("jenis_rawat");
                        cekID = rs6.getString("nomor_kartu_t");
                        noKlaim.setText(rs6.getString("no_sep"));
                        tampilTerupload();
                        cmbUnggah.setSelectedIndex(0);
                        lokasiFile.setText("");
                        labelhak1.setText("Kelas 3");
                        kls = "3";
                        nilaiKriteria = rs6.getString("akses_naat");
                        nilaiIsolasi = rs6.getString("isoman_ind");  
                        nilaiKelainan = rs6.getString("bayi_lahir_status_cd");  
                        icuindikator = rs6.getString("icu_indikator");
                        wktMasuk1.setText(rs6.getString("tglMasuk"));
                        wktPulang1.setText(rs6.getString("tglPulang"));
                        caramasuk = rs6.getString("cara_masuk");
                        tglmsk = rs6.getString("tgl_masuk");
                        tglplg = rs6.getString("tgl_pulang");
                        umur1.setText(Sequel.cariIsi("select concat(umurdaftar,' ','" + sts_umur_ok + "') from reg_periksa where no_rawat='" + norawat + "'"));
                        cekCI = rs6.getString("covid19_co_insidense_ind");
                        cekKOM = rs6.getString("covid19_cc_ind");
                        subakut1.setText(rs6.getString("adl_sub_acute"));
                        kronik1.setText(rs6.getString("adl_chronic"));
                        dpjp1.setText(rs6.getString("nama_dokter"));
                        kdPulang = rs6.getString("discharge_status");
                        brtlhr1.setText(rs6.getString("birth_weight"));
                        cekSTATUS = rs6.getString("covid19_status_cd");
                        cekRS = rs6.getString("covid19_rs_darurat_ind");
                        pros_inadrg2.setText(rs6.getString("procedure_inagrouper"));
                        nilaiVenti = rs6.getString("ventilator_use_ind");
                        tglIntu = rs6.getString("ventilator_start_dttm");
                        tglEkstu = rs6.getString("ventilator_stop_dttm");

                        cekPEMU = rs6.getString("pemulasaraan_jenazah");
                        cekPLAS = rs6.getString("plastik_erat");
                        cekKAN = rs6.getString("kantong_jenazah");
                        cekDESJEN = rs6.getString("desinfektan_jenazah");
                        cekDESMOB = rs6.getString("desinfektan_mobil_jenazah");
                        cekPET = rs6.getString("peti_jenazah");
                        cekTRA = rs6.getString("mobil_jenazah");

                        cekASAM = rs6.getString("lab_asam_laktat");
                        cekKUL = rs6.getString("lab_kultur");
                        cekAPTT = rs6.getString("lab_aptt");
                        cekANAL = rs6.getString("lab_analisa_gas");
                        cekPRO = rs6.getString("lab_procalcitonin");
                        cekDIM = rs6.getString("lab_d_dimer");
                        cekWAK = rs6.getString("lab_waktu_pendarahan");
                        cekALBU = rs6.getString("lab_albumin");
                        cekCRP = rs6.getString("lab_crp");
                        cekPT = rs6.getString("lab_pt");
                        cekANT = rs6.getString("lab_anti_hiv");
                        cekTHO = rs6.getString("rad_thorax_ap_pa");

                        nilaiPNB = rs6.getDouble("tarif_prosedur_non_bedah");
                        pnb7.setText(Valid.SetAngka(nilaiPNB));
                        nilaiRI = rs6.getDouble("tarif_rawat_intensif");
                        ri1.setText(Valid.SetAngka(nilaiRI));
                        nilaiKAM = rs6.getDouble("tarif_kamar");
                        kam1.setText(Valid.SetAngka(nilaiKAM));
                        nilaiKON = rs6.getDouble("tarif_konsultasi");
                        kon7.setText(Valid.SetAngka(nilaiKON));
                        nilaiKEP = rs6.getDouble("tarif_keperawatan");
                        kep7.setText(Valid.SetAngka(nilaiKEP));
                        nilaiREH = rs6.getDouble("tarif_rehabilitasi");
                        reh7.setText(Valid.SetAngka(nilaiREH));
                        nilaiRAD = rs6.getDouble("tarif_radiologi");
                        rad1.setText(Valid.SetAngka(nilaiRAD));
                        nilaiLAB = rs6.getDouble("tarif_laboratorium");
                        lab1.setText(Valid.SetAngka(nilaiLAB));
                        nilaiOBAT = rs6.getDouble("tarif_obat");
                        obat1.setText(Valid.SetAngka(nilaiOBAT));
                        nilaiPB = rs6.getDouble("tarif_prosedur_bedah");
                        pb1.setText(Valid.SetAngka(nilaiPB));
                        
                        if (nilaiVenti.equals("1")) {
                            cmbVentilator1.setSelectedIndex(0);
                            Valid.SetTgl2(tglIntubasi1, tglIntu);
                            Valid.SetTgl2(tglEkstubasi1, tglEkstu);
                            cmbVentilator1.setEnabled(true);
                            tglIntubasi1.setEnabled(true);
                            tglEkstubasi1.setEnabled(true);
                            
                        } else if (nilaiVenti.equals("0")) {
                            cmbVentilator1.setSelectedIndex(1);
                            tglIntubasi1.setDate(new Date());
                            tglEkstubasi1.setDate(new Date());
                            cmbVentilator1.setEnabled(false);
                            tglIntubasi1.setEnabled(false);
                            tglEkstubasi1.setEnabled(false);
                            
                        } else {
                            cmbVentilator1.setSelectedIndex(1);
                            tglIntubasi1.setDate(new Date());
                            tglEkstubasi1.setDate(new Date());
                            cmbVentilator1.setEnabled(false);
                            tglIntubasi1.setEnabled(false);
                            tglEkstubasi1.setEnabled(false);
                        }
                        
                        if (nilaiKriteria.equals("A")) {
                            cmbKriteria.setSelectedIndex(0);
                        } else if (nilaiKriteria.equals("B")) {
                            cmbKriteria.setSelectedIndex(1);
                        } else if (nilaiKriteria.equals("C")) {
                            cmbKriteria.setSelectedIndex(2);
                        }
                        
                        if (nilaiIsolasi.equals("0")) {
                            cmbIsolasi.setSelectedIndex(0);
                        } else if (nilaiIsolasi.equals("1")) {
                            cmbIsolasi.setSelectedIndex(1);
                        }
                        
                        if (nilaiKelainan.equals("0")) {
                            cmbKelainan.setSelectedIndex(0);
                        } else if (nilaiKelainan.equals("1")) {
                            cmbKelainan.setSelectedIndex(1);
                        }
                        
                        //cara masuk
                        if (caramasuk.equals("gp")) {
                            cmbCrMasuk1.setSelectedIndex(0);
                        } else if (caramasuk.equals("hosp-trans")) {
                            cmbCrMasuk1.setSelectedIndex(1);
                        } else if (caramasuk.equals("mp")) {
                            cmbCrMasuk1.setSelectedIndex(2);
                        } else if (caramasuk.equals("outp")) {
                            cmbCrMasuk1.setSelectedIndex(3);
                        } else if (caramasuk.equals("inp")) {
                            cmbCrMasuk1.setSelectedIndex(4);
                        } else if (caramasuk.equals("emd")) {
                            cmbCrMasuk1.setSelectedIndex(5);
                        } else if (caramasuk.equals("born")) {
                            cmbCrMasuk1.setSelectedIndex(6);
                        } else if (caramasuk.equals("nursing")) {
                            cmbCrMasuk1.setSelectedIndex(7);
                        } else if (caramasuk.equals("psych")) {
                            cmbCrMasuk1.setSelectedIndex(8);
                        } else if (caramasuk.equals("rehab")) {
                            cmbCrMasuk1.setSelectedIndex(9);
                        } else if (caramasuk.equals("other")) {
                            cmbCrMasuk1.setSelectedIndex(10);
                        } 
                        
                        //faktor pengurang
                        if (cekASAM.equals("1")) {
                            ChkAsam.setSelected(true);                            
                        } else {
                            ChkAsam.setSelected(false);
                        }

                        if (cekKUL.equals("1")) {
                            ChkKultur.setSelected(true);                            
                        } else {
                            ChkKultur.setSelected(false);
                        }

                        if (cekAPTT.equals("1")) {
                            ChkAPTT.setSelected(true);                            
                        } else {
                            ChkAPTT.setSelected(false);
                        }

                        if (cekANAL.equals("1")) {
                            ChkAnalisa.setSelected(true);                            
                        } else {
                            ChkAnalisa.setSelected(false);
                        }

                        if (cekPRO.equals("1")) {
                            ChkProcal.setSelected(true);
                        } else {
                            ChkProcal.setSelected(false);
                        }

                        if (cekDIM.equals("1")) {
                            ChkDimer.setSelected(true);
                        } else {
                            ChkDimer.setSelected(false);
                        }

                        if (cekWAK.equals("1")) {
                            ChkWaktu.setSelected(true);
                        } else {
                            ChkWaktu.setSelected(false);
                        }

                        if (cekALBU.equals("1")) {
                            ChkAlbumin.setSelected(true);
                        } else {
                            ChkAlbumin.setSelected(false);
                        }

                        if (cekCRP.equals("1")) {
                            ChkCRP.setSelected(true);
                        } else {
                            ChkCRP.setSelected(false);
                        }

                        if (cekPT.equals("1")) {
                            ChkPT.setSelected(true);
                        } else {
                            ChkPT.setSelected(false);
                        }

                        if (cekANT.equals("1")) {
                            ChkAnti.setSelected(true);
                        } else {
                            ChkAnti.setSelected(false);
                        }

                        if (cekTHO.equals("1")) {
                            ChkThorax.setSelected(true);
                        } else {
                            ChkThorax.setSelected(false);
                        }

                        ChkSemuaPelengkap.setEnabled(false);
                        ChkPemulasaran.setEnabled(false);
                        ChkPlastik.setEnabled(false);
                        ChkKantong.setEnabled(false);
                        ChkDesinfekJen.setEnabled(false);
                        ChkDesinfekMob.setEnabled(false);
                        ChkPeti.setEnabled(false);
                        ChkTranspot.setEnabled(false);

                        ChkSemuaFaktor.setSelected(false);
                        ChkSemuaPelengkap.setSelected(false);
                        ChkPemulasaran.setSelected(false);
                        ChkPlastik.setSelected(false);
                        ChkKantong.setSelected(false);
                        ChkDesinfekJen.setSelected(false);
                        ChkDesinfekMob.setSelected(false);
                        ChkPeti.setSelected(false);
                        ChkTranspot.setSelected(false);

                        ChkSemuaFaktor.setEnabled(true);
                        ChkAsam.setEnabled(true);
                        ChkKultur.setEnabled(true);
                        ChkAPTT.setEnabled(true);
                        ChkAnalisa.setEnabled(true);
                        ChkProcal.setEnabled(true);
                        ChkDimer.setEnabled(true);
                        ChkWaktu.setEnabled(true);
                        ChkAlbumin.setEnabled(true);
                        ChkCRP.setEnabled(true);
                        ChkPT.setEnabled(true);
                        ChkAnti.setEnabled(true);
                        ChkThorax.setEnabled(true);

                        if (kdPulang.equals("2")) {
                            cmbcrPulang1.setSelectedIndex(1);
                        } else if (kdPulang.equals("3")) {
                            cmbcrPulang1.setSelectedIndex(2);
                        } else if (kdPulang.equals("4")) {
                            cmbcrPulang1.setSelectedIndex(3);
                            if (kodePayor.equals("73") || kodePayor.equals("72") || kodePayor.equals("75")) {                                
                                ChkSemuaPelengkap.setEnabled(false);
                                ChkPemulasaran.setEnabled(false);
                                ChkPlastik.setEnabled(false);
                                ChkKantong.setEnabled(false);
                                ChkDesinfekJen.setEnabled(false);
                                ChkDesinfekMob.setEnabled(false);
                                ChkPeti.setEnabled(false);
                                ChkTranspot.setEnabled(false);
                                                                
                                ChkPemulasaran.setSelected(false);
                                ChkPemulasaran.setSelected(false);
                                ChkPlastik.setSelected(false);
                                ChkKantong.setSelected(false);
                                ChkDesinfekJen.setSelected(false);
                                ChkDesinfekMob.setSelected(false);
                                ChkPeti.setSelected(false);
                                ChkTranspot.setSelected(false);
                            } else {
                                ChkSemuaPelengkap.setEnabled(true);
                                ChkPemulasaran.setEnabled(true);
                                ChkPlastik.setEnabled(true);
                                ChkKantong.setEnabled(true);
                                ChkDesinfekJen.setEnabled(true);
                                ChkDesinfekMob.setEnabled(true);
                                ChkPeti.setEnabled(true);
                                ChkTranspot.setEnabled(true);

                                if (cekPEMU.equals("1")) {
                                    ChkPemulasaran.setSelected(true);
                                } else {
                                    ChkPemulasaran.setSelected(false);
                                }

                                if (cekPLAS.equals("1")) {
                                    ChkPlastik.setSelected(true);
                                } else {
                                    ChkPlastik.setSelected(false);
                                }

                                if (cekKAN.equals("1")) {
                                    ChkKantong.setSelected(true);
                                } else {
                                    ChkKantong.setSelected(false);
                                }

                                if (cekDESJEN.equals("1")) {
                                    ChkDesinfekJen.setSelected(true);
                                } else {
                                    ChkDesinfekJen.setSelected(false);
                                }

                                if (cekDESMOB.equals("1")) {
                                    ChkDesinfekMob.setSelected(true);
                                } else {
                                    ChkDesinfekMob.setSelected(false);
                                }

                                if (cekPET.equals("1")) {
                                    ChkPeti.setSelected(true);
                                } else {
                                    ChkPeti.setSelected(false);
                                }

                                if (cekTRA.equals("1")) {
                                    ChkTranspot.setSelected(true);
                                } else {
                                    ChkTranspot.setSelected(false);
                                }
                            }
                        } else if (kdPulang.equals("1")) {
                            cmbcrPulang1.setSelectedIndex(4);
                        } else if (kdPulang.equals("5")) {
                            cmbcrPulang1.setSelectedIndex(5);
                        }

                        if (cekKOM.equals("1")) {
                            cmbKomor.setSelectedIndex(1);
                        } else if (cekKOM.equals("0")) {
                            cmbKomor.setSelectedIndex(0);
                        }

                        if (cekID.equals("nik")) {
                            cmbID.setSelectedIndex(0);
                        } else if (cekID.equals("kitas")) {
                            cmbID.setSelectedIndex(1);
                        } else if (cekID.equals("paspor")) {
                            cmbID.setSelectedIndex(2);
                        } else if (cekID.equals("kartu_jkn")) {
                            cmbID.setSelectedIndex(3);
                        } else if (cekID.equals("kk")) {
                            cmbID.setSelectedIndex(4);
                        } else if (cekID.equals("unhcr")) {
                            cmbID.setSelectedIndex(5);
                        } else if (cekID.equals("kelurahan")) {
                            cmbID.setSelectedIndex(6);
                        } else if (cekID.equals("dinsos")) {
                            cmbID.setSelectedIndex(7);
                        } else if (cekID.equals("dinkes")) {
                            cmbID.setSelectedIndex(8);
                        } else if (cekID.equals("sjp")) {
                            cmbID.setSelectedIndex(9);
                        } else if (cekID.equals("lainnya")) {
                            cmbID.setSelectedIndex(10);
                        } else if (cekID.equals("klaim_ibu")) {
                            cmbID.setSelectedIndex(11);
                        }

                        if (cekSTATUS.equals("4")) {
                            cmbStatus.setSelectedIndex(0);
                        } else if (cekSTATUS.equals("5")) {
                            cmbStatus.setSelectedIndex(1);
                        } else if (cekSTATUS.equals("3")) {
                            cmbStatus.setSelectedIndex(2);                            
                        } else if (cekSTATUS.equals("2")) {
                            cmbStatus.setSelectedIndex(4);
                        } else if (cekSTATUS.equals("1")) {
                            cmbStatus.setSelectedIndex(3);
                        }

                        ChkRawatIntensif1.setEnabled(false);
                        ChkRawatIntensif1.setSelected(false);
                        losIntensif1.setEnabled(false);
                        losIntensif1.setText("0");
                        ventilator1.setEnabled(false);
                        ventilator1.setText("0");
                        cmbKelainan.setEnabled(false);
                        labelnmIbu.setVisible(false);
                        cmbID.setEnabled(true);
                        noID.setEditable(true);
                        cmbEpisod.setEnabled(true);
                        cmbEpisod.setSelectedIndex(0);
                        hari.setEnabled(true);
                        hari.setText("0");

                        BtnAddEpisod.setEnabled(true);
                        BtnDelEpisod.setEnabled(true);
                        cmbStatus.setEnabled(true);
                        cmbRS.setEnabled(true);
                        cmbKriteria.setEnabled(true);
                        cmbIsolasi.setEnabled(false);
                        cmbCO.setEnabled(true);
                        cmbKomor.setEnabled(true);
                                    
                        //jns. pelayanan 1 (rawat inap)
                        if (jpel.equals("1")) {
                            labelrwt1.setText("INAP");
                            labelLOS1.setText(rs6.getString("los"));
                            tarifPlasma.setText(rs6.getString("terapi_konvalesen"));
                            labeltarifPlasma.setText(Valid.SetAngka(Double.parseDouble(tarifPlasma.getText())));
                            tampilEpisod();
                            episodSIAP();
                          
                            if (kodePayor.equals("73")) {
                                cmbKelainan.setEnabled(true);
                                ChkRawatIntensif1.setEnabled(true);
                                labelnmIbu.setVisible(true);
                                cmbID.setEnabled(false);
                                noID.setEditable(false);
                                BtnAddEpisod.setEnabled(false);
                                BtnDelEpisod.setEnabled(false);
                                cmbStatus.setEnabled(false);
                                cmbRS.setEnabled(false);
                                tarifPlasma.setEnabled(false);
                                cmbKriteria.setEnabled(false);
                                cmbIsolasi.setEnabled(false);
                                cmbCO.setEnabled(false);
                                cmbKomor.setEnabled(false);

                                ChkSemuaFaktor.setEnabled(false);
                                ChkAsam.setEnabled(false);
                                ChkKultur.setEnabled(false);
                                ChkAPTT.setEnabled(false);
                                ChkAnalisa.setEnabled(false);
                                ChkProcal.setEnabled(false);
                                ChkDimer.setEnabled(false);
                                ChkWaktu.setEnabled(false);
                                ChkAlbumin.setEnabled(false);
                                ChkCRP.setEnabled(false);
                                ChkPT.setEnabled(false);
                                ChkAnti.setEnabled(false);
                                ChkThorax.setEnabled(false);

                                if (icuindikator.equals("1")) {
                                    ChkRawatIntensif1.setSelected(true);
                                    losIntensif1.setEnabled(true);
                                    losIntensif1.setText(rs6.getString("icu_los"));
                                    ventilator1.setEnabled(true);
                                    ventilator1.setText(rs6.getString("ventilator_hour"));
                                }
                            }
                            
                            if (kodePayor.equals("72") || kodePayor.equals("75")) {
                                ChkRawatIntensif1.setEnabled(true);
                                labelnmIbu.setVisible(false);
                                BtnAddEpisod.setEnabled(false);
                                BtnDelEpisod.setEnabled(false);
                                cmbStatus.setEnabled(false);
                                cmbRS.setEnabled(false);
                                tarifPlasma.setEnabled(false);
                                cmbKriteria.setEnabled(false);
                                cmbIsolasi.setEnabled(false);
                                cmbCO.setEnabled(false);
                                cmbKomor.setEnabled(false);
                                
                                ChkSemuaFaktor.setEnabled(false);
                                ChkAsam.setEnabled(false);
                                ChkKultur.setEnabled(false);
                                ChkAPTT.setEnabled(false);
                                ChkAnalisa.setEnabled(false);
                                ChkProcal.setEnabled(false);
                                ChkDimer.setEnabled(false);
                                ChkWaktu.setEnabled(false);
                                ChkAlbumin.setEnabled(false);
                                ChkCRP.setEnabled(false);
                                ChkPT.setEnabled(false);
                                ChkAnti.setEnabled(false);
                                ChkThorax.setEnabled(false);
                                
                                if (icuindikator.equals("1")) {
                                    ChkRawatIntensif1.setSelected(true);
                                    losIntensif1.setEnabled(true);
                                    losIntensif1.setText(rs6.getString("icu_los"));
                                    ventilator1.setEnabled(true);
                                    ventilator1.setText(rs6.getString("ventilator_hour"));
                                }
                            }

                            if (cmbStatus.getSelectedIndex() == 2) {
                                if (kodePayor.equals("73") || kodePayor.equals("72") || kodePayor.equals("75")) {
                                    cmbIsolasi.setEnabled(false);
                                    tarifPlasma.setEnabled(false);
                                } else {
                                    cmbIsolasi.setEnabled(true);
                                    tarifPlasma.setEnabled(true);
                                }
                            } else {
                                cmbIsolasi.setEnabled(false);
                                tarifPlasma.setEnabled(false);
                            }

                            if (cekCI.equals("0")) {
                                cmbCO.setSelectedIndex(1);
                            } else if (cekCI.equals("1")) {
                                cmbCO.setSelectedIndex(0);
                            }

                            if (cekRS.equals("0")) {
                                cmbRS.setSelectedIndex(1);
                            } else if (cekRS.equals("1")) {
                                cmbRS.setSelectedIndex(0);
                            }

                            //jns. pelayanan 2 (rawat jalan)  
                        } else if (jpel.equals("2")) {
                            labelrwt1.setText("JALAN");
                            labelLOS1.setText("1");
                            kirimEPISOD = "";
                            cmbStatus.setEnabled(true);
                            cmbRS.setEnabled(false);
                            BtnAddEpisod.setEnabled(false);
                            BtnDelEpisod.setEnabled(false);
                            Valid.tabelKosong(tabMode4);
                            tarifPlasma.setEnabled(false);
                            tarifPlasma.setText("0");
                            labeltarifPlasma.setText(Valid.SetAngka(Double.parseDouble(tarifPlasma.getText())));
                            cmbCO.setEnabled(false);
                            cmbCO.setSelectedIndex(0);
                            cmbRS.setEnabled(false);
                            cmbRS.setSelectedIndex(0);
                            cmbIsolasi.setEnabled(false);
                            cmbKriteria.setEnabled(false);

                            ChkSemuaFaktor.setEnabled(false);
                            ChkAsam.setEnabled(false);
                            ChkKultur.setEnabled(false);
                            ChkAPTT.setEnabled(false);
                            ChkAnalisa.setEnabled(false);
                            ChkProcal.setEnabled(false);
                            ChkDimer.setEnabled(false);
                            ChkWaktu.setEnabled(false);
                            ChkAlbumin.setEnabled(false);
                            ChkCRP.setEnabled(false);
                            ChkPT.setEnabled(false);
                            ChkAnti.setEnabled(false);
                            ChkThorax.setEnabled(false);                            
                            
                            if (kodePayor.equals("72")) {
                                BtnAddEpisod.setEnabled(false);
                                BtnDelEpisod.setEnabled(false);
                                cmbStatus.setEnabled(false);
                                cmbRS.setEnabled(false);
                                tarifPlasma.setEnabled(false);
                                cmbKriteria.setEnabled(false);
                                cmbIsolasi.setEnabled(false);
                                cmbCO.setEnabled(false);
                                cmbKomor.setEnabled(false);
                                
                                ChkSemuaFaktor.setEnabled(false);
                                ChkAsam.setEnabled(false);
                                ChkKultur.setEnabled(false);
                                ChkAPTT.setEnabled(false);
                                ChkAnalisa.setEnabled(false);
                                ChkProcal.setEnabled(false);
                                ChkDimer.setEnabled(false);
                                ChkWaktu.setEnabled(false);
                                ChkAlbumin.setEnabled(false);
                                ChkCRP.setEnabled(false);
                                ChkPT.setEnabled(false);
                                ChkAnti.setEnabled(false);
                                ChkThorax.setEnabled(false);
                            }
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
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }

    private void tambahEpisod() {
        kdEpisod = "";
        if (cmbEpisod.getSelectedItem().equals("ICU dengan ventilator")) {
            kdEpisod = "1";
        } else if (cmbEpisod.getSelectedItem().equals("ICU tanpa ventilator")) {
            kdEpisod = "2";
        } else if (cmbEpisod.getSelectedItem().equals("Isolasi tekanan negatif dengan ventilator")) {
            kdEpisod = "3";
        } else if (cmbEpisod.getSelectedItem().equals("Isolasi tekanan negatif tanpa ventilator")) {
            kdEpisod = "4";
        } else if (cmbEpisod.getSelectedItem().equals("Isolasi non tekanan negatif dengan ventilator")) {
            kdEpisod = "5";
        } else if (cmbEpisod.getSelectedItem().equals("Isolasi non tekanan negatif tanpa ventilator")) {
            kdEpisod = "6";
        } else if (cmbEpisod.getSelectedItem().equals("ICU tekanan negatif dengan ventilator")) {
            kdEpisod = "7";
        } else if (cmbEpisod.getSelectedItem().equals("ICU tekanan negatif tanpa ventilator")) {
            kdEpisod = "8";
        } else if (cmbEpisod.getSelectedItem().equals("ICU tanpa tekanan negatif dengan ventilator")) {
            kdEpisod = "9";
        } else if (cmbEpisod.getSelectedItem().equals("ICU tanpa tekanan negatif tanpa ventilator")) {
            kdEpisod = "10";
        } else if (cmbEpisod.getSelectedItem().equals("Isolasi tekanan negatif")) {
            kdEpisod = "11";
        } else if (cmbEpisod.getSelectedItem().equals("Isolasi tanpa tekanan negatif")) {
            kdEpisod = "12";
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu jenis episode ruang rawat dengan benar..!!");
        }

        if (!cmbEpisod.getSelectedItem().equals("-")) {
            tabMode4.addRow(new Object[]{
                kdEpisod,
                false,
                cmbEpisod.getSelectedItem().toString(),
                hari.getText()
            });
        }
    }

    private void getDataEpisod() {
        kdEpisod = "";
        if (tbEpisod.getSelectedRow() != -1) {
            kdEpisod = tbEpisod.getValueAt(tbEpisod.getSelectedRow(), 0).toString();
            cmbEpisod.setSelectedItem(tbEpisod.getValueAt(tbEpisod.getSelectedRow(), 2).toString());
            hari.setText(tbEpisod.getValueAt(tbEpisod.getSelectedRow(), 3).toString());
        }
    }

    private void tampilEpisod() {
        konversiKD = "";
        Valid.tabelKosong(tabMode4);
        try {
            ps8 = koneksi.prepareStatement("SELECT episodes_class_cd kode, los hari FROM eklaim_covid19_episodes "
                    + "WHERE no_sep='" + noKlaim.getText() + "' ORDER BY episodes_id DESC");
            try {
                rs8 = ps8.executeQuery();
                while (rs8.next()) {
                    if (rs8.getString("kode").equals("1")) {
                        konversiKD = "ICU dengan ventilator";
                    } else if (rs8.getString("kode").equals("2")) {
                        konversiKD = "ICU tanpa ventilator";
                    } else if (rs8.getString("kode").equals("3")) {
                        konversiKD = "Isolasi tekanan negatif dengan ventilator";
                    } else if (rs8.getString("kode").equals("4")) {
                        konversiKD = "Isolasi tekanan negatif tanpa ventilator";
                    } else if (rs8.getString("kode").equals("5")) {
                        konversiKD = "Isolasi non tekanan negatif dengan ventilator";
                    } else if (rs8.getString("kode").equals("6")) {
                        konversiKD = "Isolasi non tekanan negatif tanpa ventilator";
                    } else if (rs8.getString("kode").equals("7")) {
                        konversiKD = "ICU tekanan negatif dengan ventilator";
                    } else if (rs8.getString("kode").equals("8")) {
                        konversiKD = "ICU tekanan negatif tanpa ventilator";
                    } else if (rs8.getString("kode").equals("9")) {
                        konversiKD = "ICU tanpa tekanan negatif dengan ventilator";
                    } else if (rs8.getString("kode").equals("10")) {
                        konversiKD = "ICU tanpa tekanan negatif tanpa ventilator";
                    } else if (rs8.getString("kode").equals("11")) {
                        konversiKD = "Isolasi tekanan negatif";
                    } else if (rs8.getString("kode").equals("12")) {
                        konversiKD = "Isolasi tanpa tekanan negatif";
                    }
                    
                    tabMode4.addRow(new Object[]{
                        rs8.getString("kode"),
                        false,
                        konversiKD,
                        rs8.getString("hari")
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

    private void episodSIAP() {
        kirimEPISOD = "";
        for (i = 0; i < tbEpisod.getRowCount(); i++) {
            kirimEPISOD = kirimEPISOD + tbEpisod.getValueAt(i, 0).toString() + ";" + tbEpisod.getValueAt(i, 3).toString() + "#";
        }
    }
    
    private void tampilRespon() {
        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            Valid.tabelKosong(tabMode11);
            try {
                ps11 = koneksi.prepareStatement("SELECT 'MDC' komponen, mdc_number kode, mdc_description deskripsi FROM eklaim_response_inagrouper "
                        + "WHERE no_sep='" + noSEP.getText() + "' UNION ALL "
                        + "SELECT 'DRG', drg_code, drg_description FROM eklaim_response_inagrouper "
                        + "WHERE no_sep='" + noSEP.getText() + "'");
                try {
                    rs11 = ps11.executeQuery();
                    while (rs11.next()) {
                        tabMode11.addRow(new Object[]{
                            rs11.getString("komponen"),
                            rs11.getString("kode"),
                            rs11.getString("deskripsi")
                        });
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rs11 != null) {
                        rs11.close();
                    }
                    if (ps11 != null) {
                        ps11.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Notifikasi : " + e);
            }
            
        } else {
            Valid.tabelKosong(tabMode12);
            try {
                ps12 = koneksi.prepareStatement("SELECT 'MDC' komponen, mdc_number kode, mdc_description deskripsi FROM eklaim_response_inagrouper "
                        + "WHERE no_sep='" + noSEP.getText() + "' UNION ALL "
                        + "SELECT 'DRG', drg_code, drg_description FROM eklaim_response_inagrouper "
                        + "WHERE no_sep='" + noSEP.getText() + "'");
                try {
                    rs12 = ps12.executeQuery();
                    while (rs2.next()) {
                        tabMode12.addRow(new Object[]{
                            rs12.getString("komponen"),
                            rs12.getString("deskrip"),
                            rs12.getString("kode"),
                            rs12.getString("nominal")
                        });
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rs12 != null) {
                        rs12.close();
                    }
                    if (ps12 != null) {
                        ps12.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }
  
    private void hasilJLHProsedur() {
        prosedurINADRG1 = "";
        prosedurINADRG2 = "";
        pros_inadrg1.setText("");
        pros_inadrg2.setText("");        

        //kalau kode payor 3 adalah JKN
        if (kodePayor.equals("3")) {
            for (i = 0; i < tbTindakanPasien2.getRowCount(); i++) {
                if (i == 0) {
                    if (!tbTindakanPasien2.getValueAt(i, 2).toString().equals("") && Integer.parseInt(tbTindakanPasien2.getValueAt(i, 2).toString()) > 1) {
                        prosedurINADRG1 = tbTindakanPasien2.getValueAt(i, 0).toString() + "+" + tbTindakanPasien2.getValueAt(i, 2).toString();
                    } else {
                        prosedurINADRG1 = tbTindakanPasien2.getValueAt(i, 0).toString();
                    }
                } else {
                    if (!tbTindakanPasien2.getValueAt(i, 2).toString().equals("") && Integer.parseInt(tbTindakanPasien2.getValueAt(i, 2).toString()) > 1) {
                        prosedurINADRG1 = prosedurINADRG1 + "#" + tbTindakanPasien2.getValueAt(i, 0).toString() + "+" + tbTindakanPasien2.getValueAt(i, 2).toString();
                    } else {
                        prosedurINADRG1 = prosedurINADRG1 + "#" + tbTindakanPasien2.getValueAt(i, 0).toString();
                    }
                }
            }
            pros_inadrg1.setText(prosedurINADRG1);

        } else {
            for (i = 0; i < tbTindakanPasien4.getRowCount(); i++) {
                if (i == 0) {
                    if (!tbTindakanPasien4.getValueAt(i, 2).toString().equals("") && Integer.parseInt(tbTindakanPasien4.getValueAt(i, 2).toString()) > 1) {
                        prosedurINADRG2 = tbTindakanPasien4.getValueAt(i, 0).toString() + "+" + tbTindakanPasien4.getValueAt(i, 2).toString();
                    } else {
                        prosedurINADRG2 = tbTindakanPasien4.getValueAt(i, 0).toString();
                    }
                } else {
                    if (!tbTindakanPasien4.getValueAt(i, 2).toString().equals("") && Integer.parseInt(tbTindakanPasien4.getValueAt(i, 2).toString()) > 1) {
                        prosedurINADRG2 = prosedurINADRG2 + "#" + tbTindakanPasien4.getValueAt(i, 0).toString() + "+" + tbTindakanPasien4.getValueAt(i, 2).toString();
                    } else {
                        prosedurINADRG2 = prosedurINADRG2 + "#" + tbTindakanPasien4.getValueAt(i, 0).toString();
                    }
                }
            }
            pros_inadrg2.setText(prosedurINADRG2);
        }
    }
    
    public void tarifRS(String NORW) {
        NrawatTARIF = NORW;
        hitungPNB();
        hitungRI();
        hitungKAMAR();
        hitungKON();
        hitungKEP();
        hitungREH();
        hitungRAD();
        hitungLAB();
        hitungOBAT();
        hitungPB();
        hitungTOTAL();
    }
    
    private void simpanGroupingJKN() {
        cekDATA();
        tampilDiagnosa();
        tampilProsedur();
        
        for (i = 0; i < tbPersalinan.getRowCount(); i++) {
            requestJson = "{"
                    + "\"delivery_sequence\": \"" + tbPersalinan.getValueAt(i, 0).toString() + "\","
                    + "\"delivery_method\": \"" + tbPersalinan.getValueAt(i, 9).toString() + "\","
                    + "\"delivery_dttm\": \"" + tbPersalinan.getValueAt(i, 8).toString() + "\","
                    + "\"letak_janin\": \"" + tbPersalinan.getValueAt(i, 10).toString() + "\","
                    + "\"kondisi\": \"" + tbPersalinan.getValueAt(i, 11).toString() + "\","
                    + "\"use_manual\": \"" + tbPersalinan.getValueAt(i, 12).toString() + "\","
                    + "\"use_forcep\": \"" + tbPersalinan.getValueAt(i, 13).toString() + "\","
                    + "\"use_vacuum\": \"" + tbPersalinan.getValueAt(i, 14).toString() + "\""
                    + "},"
                    + requestJson;
        }

        if (requestJson.endsWith(",")) {
            requestJson = requestJson.substring(0, requestJson.length() - 1) + "]";
        }

        diag = 0;
        if (jpel.equals("2")) {
            for (i = 0; i < tbDiagnosaPasien1.getRowCount(); i++) {
                if (tbDiagnosaPasien1.getValueAt(i, 3).toString().equals("Belum Sesuai")) {
                    diag++;
                }
            }
        } else {
            for (i = 0; i < tbDiagnosaPasien1.getRowCount(); i++) {
                if (tbDiagnosaPasien1.getValueAt(i, 3).toString().equals("Belum Sesuai")) {
                    diag++;
                }
            }

            for (i = 0; i < tbDiagnosaPasien2.getRowCount(); i++) {
                if (tbDiagnosaPasien2.getValueAt(i, 3).toString().equals("Belum Sesuai")) {
                    diag++;
                }
            }
        }

        if (diag > 0) {
            JOptionPane.showMessageDialog(null, "Masih ada diagnosa yg. blm. sesuai utk. diklaimkan, Silahkan perbaiki dulu.!!!");
            tbDiagnosaPasien1.requestFocus();
        } else {
            if (mbak_eka.menggrouper(noSEP.getText(), noPeserta.getText(), tglmsk, tglplg, jpel, kls, subakut.getText(), kronik.getText(), icuindikator,
                    losIntensif.getText(), ventilator.getText(), naikTurunkls, nilaiKP, losNaikKls.getText(), persenNaikKls, brtlhr.getText(),
                    kdPulang, diagnosaKlaim, prosedurKlaim, nilaiPNB, nilaiPB, nilaiKON, ta.getText(), nilaiKEP, pen.getText(), nilaiRAD, nilaiLAB,
                    pd.getText(), nilaiREH, nilaiKAM, nilaiRI, nilaiOBAT, okr.getText(), oke.getText(), alkes.getText(), bmhp.getText(), sa.getText(),
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", trfPoliEx, dpjp.getText(),
                    kodePayor, "JKN", cmbCOB.getSelectedItem().toString(), nikPetugas.getText(), "0", "#", "0", "0", pros_inadrg1.getText(), 
                    diagnosaKlaimINADRG, caramasuk, nilaiPembiayaan, sistole.getText(), diastole.getText(), nilaiVenti, tglIntu, tglEkstu, dializer, 
                    TkntngDrh.getText(), mnt1APP, mnt1PUL, mnt1GRI, mnt1ACT, mnt1RES, mnt5APP, mnt5PUL, mnt5GRI, mnt5ACT, mnt5RES, TUsiaHml.getText(), 
                    Tgravida.getText(), Tpartus.getText(), Tabortus.getText(), onset, cekDeliveri, requestJson) == true) {

                labelhasilG.setText("Hasil Grouper : " + Sequel.cariIsi("select if(klaim_final='Belum','',klaim_final) from eklaim_new_claim where no_sep='" + noSEP.getText() + "'"));
                labeltambahan.setText("Tambahan biaya yang dibayar pasien naik kelas : Rp. "
                        + Sequel.cariIsi("SELECT format(ifnull(add_payment_amt,'0'),0) tambhn_biaya FROM eklaim_grouping WHERE no_sep='" + noSEP.getText() + "'"));
                tampilHG1();
                tampilHG2();
                tampilRespon();
                if (tabMode3.getRowCount() == 0) {
                    BtnGruperStage.setEnabled(false);
                } else {
                    BtnGruperStage.setEnabled(true);
                }
            } else {
                labelhasilG.setText("Hasil Grouper : " + Sequel.cariIsi("select if(klaim_final='Belum','',klaim_final) from eklaim_new_claim where no_sep='" + noSEP.getText() + "'"));
                labeltambahan.setText("Tambahan biaya yang dibayar pasien naik kelas : Rp. "
                        + Sequel.cariIsi("SELECT format(ifnull(add_payment_amt,'0'),0) tambhn_biaya FROM eklaim_grouping WHERE no_sep='" + noSEP.getText() + "'"));
                tampilHG1();
                tampilHG2();
                tampilRespon();
                if (tabMode3.getRowCount() == 0) {
                    BtnGruperStage.setEnabled(false);
                } else {
                    BtnGruperStage.setEnabled(true);
                }
            }
        }
    }
    
    private void simpanGrouperLAINNYA() {
        cekDATA();
        tampilDiagnosa();
        tampilProsedur();

        diag = 0;
        if (jpel.equals("2")) {
            for (i = 0; i < tbDiagnosaPasie3.getRowCount(); i++) {
                if (tbDiagnosaPasie3.getValueAt(i, 3).toString().equals("Belum Sesuai")) {
                    diag++;
                }
            }
        } else {
            for (i = 0; i < tbDiagnosaPasie3.getRowCount(); i++) {
                if (tbDiagnosaPasie3.getValueAt(i, 3).toString().equals("Belum Sesuai")) {
                    diag++;
                }
            }

            for (i = 0; i < tbDiagnosaPasie4.getRowCount(); i++) {
                if (tbDiagnosaPasie4.getValueAt(i, 3).toString().equals("Belum Sesuai")) {
                    diag++;
                }
            }
        }

        if (diag > 0) {
            JOptionPane.showMessageDialog(null, "Masih ada diagnosa yg. blm. sesuai utk. diklaimkan, Silahkan perbaiki dulu.!!!");
            tbDiagnosaPasie3.requestFocus();
        } else {
            if (mbak_eka.menggrouper(noKlaim.getText(), noID.getText(), tglmsk, tglplg, jpel, kls, subakut1.getText(), kronik1.getText(), icuindikator,
                    losIntensif1.getText(), ventilator1.getText(), "0", "", "0", "0", brtlhr1.getText(), kdPulang, diagnosaKlaim, prosedurKlaim, nilaiPNB,
                    nilaiPB, nilaiKON, ta1.getText(), nilaiKEP, pen1.getText(), nilaiRAD, nilaiLAB, pd1.getText(), nilaiREH, nilaiKAM, nilaiRI, nilaiOBAT,
                    okr1.getText(), oke1.getText(), alkes1.getText(), bmhp1.getText(), sa1.getText(), cekPEMU, cekKAN, cekPET, cekPLAS, cekDESJEN, cekTRA,
                    cekDESMOB, cekSTATUS, cekID, kirimEPISOD, cekKOM, cekRS, cekCI, cekASAM, cekPRO, cekCRP, cekKUL, cekDIM, cekPT, cekAPTT, cekWAK,
                    cekANT, cekANAL, cekALBU, cekTHO, "0", dpjp1.getText(), kodePayor, nmJaminan.getText(), "#", nikPetugas1.getText(), trfPlasma,
                    nilaiKriteria, nilaiIsolasi, nilaiKelainan, pros_inadrg2.getText(), diagnosaKlaimINADRG, caramasuk, "", "0", "0", nilaiVenti, tglIntu,
                    tglEkstu, "1", "0", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "") == true) {

                labelhasilG2.setText("Hasil Grouper : " + Sequel.cariIsi("select if(klaim_final='Belum','',klaim_final) from eklaim_new_claim "
                        + "where no_sep='" + Sequel.cariIsi("SELECT claim_number FROM eklaim_generate_claim where no_rawat='" + norawat + "'") + "'"));
                tampilHG1();
                tampilHG2();
                tampilRespon();
                if (tabMode8.getRowCount() == 0) {
                    BtnGruperStage1.setEnabled(false);
                } else {
                    BtnGruperStage1.setEnabled(true);
                }
            } else {
                labelhasilG2.setText("Hasil Grouper : " + Sequel.cariIsi("select if(klaim_final='Belum','',klaim_final) from eklaim_new_claim "
                        + "where no_sep='" + Sequel.cariIsi("SELECT claim_number FROM eklaim_generate_claim where no_rawat='" + norawat + "'") + "'"));
                tampilHG1();
                tampilHG2();
                tampilRespon();
                if (tabMode8.getRowCount() == 0) {
                    BtnGruperStage1.setEnabled(false);
                } else {
                    BtnGruperStage1.setEnabled(true);
                }
            }
        }
    }
    
    private void emptnotb() {
        ChkpasienTB.setSelected(false);
        noregTB.setEnabled(false);
        noregTB.setText("");
        stts_noreg.setText("");
        cmbvalidTB.setSelectedIndex(0);
        cmbvalidTB.setEnabled(false);
        BtnValidasiTB.setEnabled(false);
    }
    
    private void cekApgar() {
        String klaimApgar = "";
           //jika klaim baru diajukan ambil data dari tabel pasien_bayi
        if (Sequel.cariInteger("select count(-1) from eklaim_set_claim where no_sep='" + noSEP.getText() + "'") == 0) {
            klaimApgar = "baru";

            //jika klaim sudah diajukan ambil data dari tabel eklaim_set_apgar
        } else if (Sequel.cariInteger("select count(-1) from eklaim_set_claim where no_sep='" + noSEP.getText() + "'") > 0) {
            klaimApgar = "lama";
        }
        
        try {            
            if (klaimApgar.equals("baru")) {
                ps17 = koneksi.prepareStatement("select ifnull(menit_1_appearance,' ') m1app, ifnull(menit_1_pulse,' ') m1pul, "
                        + "ifnull(menit_1_grimace,' ') m1gri, ifnull(menit_1_activity,' ') m1act, ifnull(menit_1_respiration,' ') m1res, "
                        + "ifnull(menit_5_appearance,' ') m5app, ifnull(menit_5_pulse,' ') m5pul, ifnull(menit_5_grimace,' ') m5gri, "
                        + "ifnull(menit_5_activity,' ') m5act, ifnull(menit_5_respiration,' ') m5res from pasien_bayi where "
                        + "no_rkm_medis='" + norm.getText() + "'");
                
            } else if (klaimApgar.equals("lama")) {
                ps17 = koneksi.prepareStatement("select ifnull(menit_1_appearance,'') m1app, ifnull(menit_1_pulse,'') m1pul, ifnull(menit_1_grimace,'') m1gri, "
                        + "ifnull(menit_1_activity,'') m1act, ifnull(menit_1_respiration,'') m1res, ifnull(menit_5_appearance,'') m5app, "
                        + "ifnull(menit_5_pulse,'') m5pul, ifnull(menit_5_grimace,'') m5gri, ifnull(menit_5_activity,'') m5act, "
                        + "ifnull(menit_5_respiration,'') m5res from eklaim_set_apgar where no_sep='" + noSEP.getText() + "'");
            }            
            try {
                rs17 = ps17.executeQuery();
                while (rs17.next()) {
                    if (klaimApgar.equals("baru")) {
                        cmbMnt1App.setSelectedItem(rs17.getString("m1app"));
                        cmbMnt1Pul.setSelectedItem(rs17.getString("m1pul"));
                        cmbMnt1Gri.setSelectedItem(rs17.getString("m1gri"));
                        cmbMnt1Act.setSelectedItem(rs17.getString("m1act"));
                        cmbMnt1Res.setSelectedItem(rs17.getString("m1res"));

                        cmbMnt5App.setSelectedItem(rs17.getString("m5app"));
                        cmbMnt5Pul.setSelectedItem(rs17.getString("m5pul"));
                        cmbMnt5Gri.setSelectedItem(rs17.getString("m5gri"));
                        cmbMnt5Act.setSelectedItem(rs17.getString("m5act"));
                        cmbMnt5Res.setSelectedItem(rs17.getString("m5res"));
                    } else if (klaimApgar.equals("lama")) {
                        //apgar menit 1
                        if (rs17.getString("m1app").equals("")) {
                            cmbMnt1App.setSelectedIndex(0);
                        } else {
                            cmbMnt1App.setSelectedItem(rs17.getString("m1app"));
                        }
                       
                        if (rs17.getString("m1pul").equals("")) {
                            cmbMnt1Pul.setSelectedIndex(0);
                        } else {
                            cmbMnt1Pul.setSelectedItem(rs17.getString("m1pul"));
                        }
                        
                        if (rs17.getString("m1gri").equals("")) {
                            cmbMnt1Gri.setSelectedIndex(0);
                        } else {
                            cmbMnt1Gri.setSelectedItem(rs17.getString("m1gri"));
                        }
                        
                        if (rs17.getString("m1act").equals("")) {
                            cmbMnt1Act.setSelectedIndex(0);
                        } else {
                            cmbMnt1Act.setSelectedItem(rs17.getString("m1act"));
                        }
                        
                        if (rs17.getString("m1res").equals("")) {
                            cmbMnt1Res.setSelectedIndex(0);
                        } else {
                            cmbMnt1Res.setSelectedItem(rs17.getString("m1res"));
                        }
                        
                        //apgar menit 5
                        if (rs17.getString("m5app").equals("")) {
                            cmbMnt5App.setSelectedIndex(0);
                        } else {
                            cmbMnt5App.setSelectedItem(rs17.getString("m5app"));
                        }
                       
                        if (rs17.getString("m5pul").equals("")) {
                            cmbMnt5Pul.setSelectedIndex(0);
                        } else {
                            cmbMnt5Pul.setSelectedItem(rs17.getString("m5pul"));
                        }
                        
                        if (rs17.getString("m5gri").equals("")) {
                            cmbMnt5Gri.setSelectedIndex(0);
                        } else {
                            cmbMnt5Gri.setSelectedItem(rs17.getString("m5gri"));
                        }
                        
                        if (rs17.getString("m5act").equals("")) {
                            cmbMnt5Act.setSelectedIndex(0);
                        } else {
                            cmbMnt5Act.setSelectedItem(rs17.getString("m5act"));
                        }
                        
                        if (rs17.getString("m5res").equals("")) {
                            cmbMnt5Res.setSelectedIndex(0);
                        } else {
                            cmbMnt5Res.setSelectedItem(rs17.getString("m5res"));
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs17 != null) {
                    rs17.close();
                }
                if (ps17 != null) {
                    ps17.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilDelivery() {
        Valid.tabelKosong(tabMode17);
        try {
            ps18 = koneksi.prepareStatement("select *, DATE_FORMAT(delivery_dttm,'%d-%m-%Y %H:%i:%s') wktlhr from eklaim_set_persalinan_delivery where "
                    + "no_sep='" + noSEP.getText() + "' order by delivery_sequence");
            try {
                rs18 = ps18.executeQuery();
                while (rs18.next()) {
                    tabMode17.addRow(new Object[]{
                        rs18.getString("delivery_sequence"), 
                        rs18.getString("wktlhr"),
                        rs18.getString("delivery_method").replaceAll("", " ").replaceAll("vaginal", "Vaginal").replaceAll("sc", "SC"),
                        rs18.getString("letak_janin").replaceAll("", " ").replaceAll("kepala", "Kepala").replaceAll("sungsang", "Sungsang").replaceAll("lintang", "Lintang"),
                        rs18.getString("kondisi").replaceAll("", " ").replaceAll("livebirth", "Lahir Hidup").replaceAll("stillbirth", "Lahir Meninggal"),
                        rs18.getString("use_manual").replaceAll("", " ").replaceAll("0", "Tidak").replaceAll("1", "Ya"),
                        rs18.getString("use_forcep").replaceAll("", " ").replaceAll("0", "Tidak").replaceAll("1", "Ya"),
                        rs18.getString("use_vacuum").replaceAll("", " ").replaceAll("0", "Tidak").replaceAll("1", "Ya"),                        
                        rs18.getString("delivery_dttm"),
                        rs18.getString("delivery_method"),
                        rs18.getString("letak_janin"),
                        rs18.getString("kondisi"),
                        rs18.getString("use_manual"),
                        rs18.getString("use_forcep"),
                        rs18.getString("use_vacuum")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs18 != null) {
                    rs18.close();
                }
                if (ps18 != null) {
                    ps18.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }
}
