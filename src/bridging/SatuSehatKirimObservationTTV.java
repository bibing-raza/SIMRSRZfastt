/*
  By Mas Elkhanza
 */

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
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.event.DocumentEvent;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 *
 * @author dosen
 */
public final class SatuSehatKirimObservationTTV extends javax.swing.JDialog {
    private final DefaultTableModel tabModeSuhu,tabModeRespirasi,tabModeNadi,tabModeSpO2,tabModeGCS,tabModeKesadaran,
                  tabModeTensi,tabModeTB,tabModeBB,tabModeLP;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private PreparedStatement ps;
    private ResultSet rs;   
    private int i=0;
    private String link="",json="",iddokter="",idpasien="",sistole="0",diastole="0";
    private ApiSatuSehat api=new ApiSatuSehat();
    private HttpHeaders headers ;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode response;
    private String[] arrSplit;
    private SatuSehatCekNIK cekViaSatuSehat=new SatuSehatCekNIK();  
    private StringBuilder htmlContent;  
    
    /** Creates new form DlgKamar
     * @param parent
     * @param modal */
    public SatuSehatKirimObservationTTV(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10,2);
        setSize(628,674);

        tabModeSuhu=new DefaultTableModel(null,new String[]{
                "P","Tgl. Registrasi","No. Rawat","No. RM","Nama Pasien","No.KTP Pasien","Stts Rawat","Stts Lanjut",
                "Tanggal Pulang","ID Encounter","Suhu (°C)","Petugas/Dokter/Praktisi","No.KTP Praktisi","Tanggal","Jam",
                "ID Observation Suhu"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbSuhu.setModel(tabModeSuhu);
        tbSuhu.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbSuhu.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbSuhu.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(105);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(120);
            } else if (i == 7) {
                column.setPreferredWidth(63);
            } else if (i == 8) {
                column.setPreferredWidth(120);
            } else if (i == 9) {
                column.setPreferredWidth(215);
            } else if (i == 10) {
                column.setPreferredWidth(60);
            } else if (i == 11) {
                column.setPreferredWidth(220);
            } else if (i == 12) {
                column.setPreferredWidth(110);
            } else if (i == 13) {
                column.setPreferredWidth(70);
            } else if (i == 14) {
                column.setPreferredWidth(65);
            } else if (i == 15) {
                column.setPreferredWidth(225);
            }
        }
        tbSuhu.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeRespirasi=new DefaultTableModel(null,new String[]{
                "P","Tanggal Registrasi","No.Rawat","No.RM","Nama Pasien","No.KTP Pasien","Stts Rawat","Stts Lanjut",
                "Tanggal Pulang","ID Encounter","Resp(/menit)","Petugas/Dokter/Praktisi","No.KTP Praktisi","Tanggal","Jam",
                "ID Observation Respirasi"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbRespirasi.setModel(tabModeRespirasi);
        tbRespirasi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRespirasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbRespirasi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(105);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(120);
            } else if (i == 7) {
                column.setPreferredWidth(63);
            } else if (i == 8) {
                column.setPreferredWidth(120);
            } else if (i == 9) {
                column.setPreferredWidth(215);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(220);
            } else if (i == 12) {
                column.setPreferredWidth(110);
            } else if (i == 13) {
                column.setPreferredWidth(70);
            } else if (i == 14) {
                column.setPreferredWidth(65);
            } else if (i == 15) {
                column.setPreferredWidth(225);
            }
        }
        tbRespirasi.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeNadi=new DefaultTableModel(null,new String[]{
                "P","Tanggal Registrasi","No.Rawat","No.RM","Nama Pasien","No.KTP Pasien","Stts Rawat","Stts Lanjut",
                "Tanggal Pulang","ID Encounter","Nadi(/menit)","Petugas/Dokter/Praktisi","No.KTP Praktisi","Tanggal","Jam",
                "ID Observation Nadi"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbNadi.setModel(tabModeNadi);
        tbNadi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbNadi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbNadi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(105);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(120);
            } else if (i == 7) {
                column.setPreferredWidth(63);
            } else if (i == 8) {
                column.setPreferredWidth(120);
            } else if (i == 9) {
                column.setPreferredWidth(215);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(220);
            } else if (i == 12) {
                column.setPreferredWidth(110);
            } else if (i == 13) {
                column.setPreferredWidth(70);
            } else if (i == 14) {
                column.setPreferredWidth(65);
            } else if (i == 15) {
                column.setPreferredWidth(225);
            }
        }
        tbNadi.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeSpO2=new DefaultTableModel(null,new String[]{
                "P","Tanggal Registrasi","No.Rawat","No.RM","Nama Pasien","No.KTP Pasien","Stts Rawat","Stts Lanjut",
                "Tanggal Pulang","ID Encounter","SpO2(%)","Petugas/Dokter/Praktisi","No.KTP Praktisi","Tanggal","Jam",
                "ID Observation SpO2"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbSpO2.setModel(tabModeSpO2);
        tbSpO2.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbSpO2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbSpO2.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(105);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(120);
            } else if (i == 7) {
                column.setPreferredWidth(63);
            } else if (i == 8) {
                column.setPreferredWidth(120);
            } else if (i == 9) {
                column.setPreferredWidth(215);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(220);
            } else if (i == 12) {
                column.setPreferredWidth(110);
            } else if (i == 13) {
                column.setPreferredWidth(70);
            } else if (i == 14) {
                column.setPreferredWidth(65);
            } else if (i == 15) {
                column.setPreferredWidth(225);
            }
        }
        tbSpO2.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeGCS=new DefaultTableModel(null,new String[]{
                "P","Tanggal Registrasi","No.Rawat","No.RM","Nama Pasien","No.KTP Pasien","Stts Rawat","Stts Lanjut",
                "Tanggal Pulang","ID Encounter","GCS","Petugas/Dokter/Praktisi","No.KTP Praktisi","Tanggal","Jam",
                "ID Observation GCS"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbGCS.setModel(tabModeGCS);
        tbGCS.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbGCS.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbGCS.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(105);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(120);
            } else if (i == 7) {
                column.setPreferredWidth(63);
            } else if (i == 8) {
                column.setPreferredWidth(120);
            } else if (i == 9) {
                column.setPreferredWidth(215);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(220);
            } else if (i == 12) {
                column.setPreferredWidth(110);
            } else if (i == 13) {
                column.setPreferredWidth(70);
            } else if (i == 14) {
                column.setPreferredWidth(65);
            } else if (i == 15) {
                column.setPreferredWidth(225);
            }
        }
        tbGCS.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeKesadaran=new DefaultTableModel(null,new String[]{
                "P","Tanggal Registrasi","No.Rawat","No.RM","Nama Pasien","No.KTP Pasien","Stts Rawat","Stts Lanjut",
                "Tanggal Pulang","ID Encounter","Kesadaran","Petugas/Dokter/Praktisi","No.KTP Praktisi","Tanggal","Jam",
                "ID Observation Kesadaran"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbKesadaran.setModel(tabModeKesadaran);
        tbKesadaran.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbKesadaran.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbKesadaran.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(105);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(120);
            } else if (i == 7) {
                column.setPreferredWidth(63);
            } else if (i == 8) {
                column.setPreferredWidth(120);
            } else if (i == 9) {
                column.setPreferredWidth(215);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(220);
            } else if (i == 12) {
                column.setPreferredWidth(110);
            } else if (i == 13) {
                column.setPreferredWidth(70);
            } else if (i == 14) {
                column.setPreferredWidth(65);
            } else if (i == 15) {
                column.setPreferredWidth(225);
            }
        }
        tbKesadaran.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeTensi=new DefaultTableModel(null,new String[]{
                "P","Tanggal Registrasi","No.Rawat","No.RM","Nama Pasien","No.KTP Pasien","Stts Rawat","Stts Lanjut",
                "Tanggal Pulang","ID Encounter","Tensi(mmHg)","Petugas/Dokter/Praktisi","No.KTP Praktisi","Tanggal","Jam",
                "ID Observation Tensi"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbTensi.setModel(tabModeTensi);
        tbTensi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbTensi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbTensi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(105);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(120);
            } else if (i == 7) {
                column.setPreferredWidth(63);
            } else if (i == 8) {
                column.setPreferredWidth(120);
            } else if (i == 9) {
                column.setPreferredWidth(215);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(220);
            } else if (i == 12) {
                column.setPreferredWidth(110);
            } else if (i == 13) {
                column.setPreferredWidth(70);
            } else if (i == 14) {
                column.setPreferredWidth(65);
            } else if (i == 15) {
                column.setPreferredWidth(225);
            }
        }
        tbTensi.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeTB=new DefaultTableModel(null,new String[]{
                "P","Tanggal Registrasi","No.Rawat","No.RM","Nama Pasien","No.KTP Pasien","Stts Rawat","Stts Lanjut",
                "Tanggal Pulang","ID Encounter","TB(Cm)","Petugas/Dokter/Praktisi","No.KTP Praktisi","Tanggal","Jam",
                "ID Observation TB"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbTB.setModel(tabModeTB);
        tbTB.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbTB.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbTB.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(105);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(120);
            } else if (i == 7) {
                column.setPreferredWidth(63);
            } else if (i == 8) {
                column.setPreferredWidth(120);
            } else if (i == 9) {
                column.setPreferredWidth(215);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(220);
            } else if (i == 12) {
                column.setPreferredWidth(110);
            } else if (i == 13) {
                column.setPreferredWidth(70);
            } else if (i == 14) {
                column.setPreferredWidth(65);
            } else if (i == 15) {
                column.setPreferredWidth(225);
            }
        }
        tbTB.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeBB=new DefaultTableModel(null,new String[]{
                "P","Tanggal Registrasi","No.Rawat","No.RM","Nama Pasien","No.KTP Pasien","Stts Rawat","Stts Lanjut",
                "Tanggal Pulang","ID Encounter","BB(Kg)","Petugas/Dokter/Praktisi","No.KTP Praktisi","Tanggal","Jam",
                "ID Observation BB"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbBB.setModel(tabModeBB);
        tbBB.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbBB.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbBB.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(105);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(120);
            } else if (i == 7) {
                column.setPreferredWidth(63);
            } else if (i == 8) {
                column.setPreferredWidth(120);
            } else if (i == 9) {
                column.setPreferredWidth(215);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(220);
            } else if (i == 12) {
                column.setPreferredWidth(110);
            } else if (i == 13) {
                column.setPreferredWidth(70);
            } else if (i == 14) {
                column.setPreferredWidth(65);
            } else if (i == 15) {
                column.setPreferredWidth(225);
            }
        }
        tbBB.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeLP=new DefaultTableModel(null,new String[]{
                "P","Tanggal Registrasi","No.Rawat","No.RM","Nama Pasien","No.KTP Pasien","Stts Rawat","Stts Lanjut",
                "Tanggal Pulang","ID Encounter","LP(Cm)","Petugas/Dokter/Praktisi","No.KTP Praktisi","Tanggal","Jam",
                "ID Observation LP"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbLP.setModel(tabModeLP);
        tbLP.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbLP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbLP.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(105);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(120);
            } else if (i == 7) {
                column.setPreferredWidth(63);
            } else if (i == 8) {
                column.setPreferredWidth(120);
            } else if (i == 9) {
                column.setPreferredWidth(215);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(220);
            } else if (i == 12) {
                column.setPreferredWidth(110);
            } else if (i == 13) {
                column.setPreferredWidth(70);
            } else if (i == 14) {
                column.setPreferredWidth(65);
            } else if (i == 15) {
                column.setPreferredWidth(225);
            }
        }
        tbLP.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampilsuhu();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampilsuhu();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampilsuhu();
                    }
                }
            });
        } 
        
        try {
            link=koneksiDB.URLFHIRSATUSEHAT();
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }    
        
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
        );
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
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
        ppPilihSemua = new javax.swing.JMenuItem();
        ppPilihIdKosong = new javax.swing.JMenuItem();
        ppBersihkan = new javax.swing.JMenuItem();
        LoadHTML = new widget.editorpane();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnAll1 = new widget.Button();
        BtnKirim = new widget.Button();
        BtnUpdate = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel15 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel17 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel16 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        Scroll = new widget.ScrollPane();
        tbSuhu = new widget.Table();
        Scroll1 = new widget.ScrollPane();
        tbRespirasi = new widget.Table();
        Scroll2 = new widget.ScrollPane();
        tbNadi = new widget.Table();
        Scroll3 = new widget.ScrollPane();
        tbSpO2 = new widget.Table();
        Scroll4 = new widget.ScrollPane();
        tbGCS = new widget.Table();
        Scroll5 = new widget.ScrollPane();
        tbKesadaran = new widget.Table();
        Scroll6 = new widget.ScrollPane();
        tbTensi = new widget.Table();
        Scroll7 = new widget.ScrollPane();
        tbTB = new widget.Table();
        Scroll8 = new widget.ScrollPane();
        tbBB = new widget.Table();
        Scroll9 = new widget.ScrollPane();
        tbLP = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        ppPilihSemua.setBackground(new java.awt.Color(255, 255, 254));
        ppPilihSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPilihSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPilihSemua.setText("Pilih Semua");
        ppPilihSemua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPilihSemua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPilihSemua.setName("ppPilihSemua"); // NOI18N
        ppPilihSemua.setPreferredSize(new java.awt.Dimension(190, 26));
        ppPilihSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPilihSemuaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppPilihSemua);

        ppPilihIdKosong.setBackground(new java.awt.Color(255, 255, 254));
        ppPilihIdKosong.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPilihIdKosong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPilihIdKosong.setText("Pilih ID Observasi-TTV Kosong");
        ppPilihIdKosong.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPilihIdKosong.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPilihIdKosong.setName("ppPilihIdKosong"); // NOI18N
        ppPilihIdKosong.setPreferredSize(new java.awt.Dimension(190, 26));
        ppPilihIdKosong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPilihIdKosongActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppPilihIdKosong);

        ppBersihkan.setBackground(new java.awt.Color(255, 255, 254));
        ppBersihkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBersihkan.setText("Hilangkan Pilihan");
        ppBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan.setName("ppBersihkan"); // NOI18N
        ppBersihkan.setPreferredSize(new java.awt.Dimension(190, 26));
        ppBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBersihkanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppBersihkan);

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pengiriman Data Observation-TTV Satu Sehat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(53, 23));
        panelGlass8.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass8.add(LCount);

        BtnAll1.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('M');
        BtnAll1.setText("Semua");
        BtnAll1.setToolTipText("Alt+M");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll1ActionPerformed(evt);
            }
        });
        BtnAll1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll1KeyPressed(evt);
            }
        });
        panelGlass8.add(BtnAll1);

        BtnKirim.setForeground(new java.awt.Color(0, 0, 0));
        BtnKirim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnKirim.setMnemonic('K');
        BtnKirim.setText("Kirim");
        BtnKirim.setToolTipText("Alt+K");
        BtnKirim.setName("BtnKirim"); // NOI18N
        BtnKirim.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKirimActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnKirim);

        BtnUpdate.setForeground(new java.awt.Color(0, 0, 0));
        BtnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/edit_f2.png"))); // NOI18N
        BtnUpdate.setMnemonic('U');
        BtnUpdate.setText("Update");
        BtnUpdate.setToolTipText("Alt+U");
        BtnUpdate.setName("BtnUpdate"); // NOI18N
        BtnUpdate.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUpdateActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnUpdate);

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
        panelGlass8.add(BtnPrint);

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

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Tgl. Registrasi :");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(85, 23));
        panelGlass9.add(jLabel15);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-01-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("s.d.");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(24, 23));
        panelGlass9.add(jLabel17);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-01-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Key Word :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel16);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(210, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('6');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+6");
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

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(255, 255, 254));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbSuhu.setAutoCreateRowSorter(true);
        tbSuhu.setComponentPopupMenu(jPopupMenu1);
        tbSuhu.setName("tbSuhu"); // NOI18N
        Scroll.setViewportView(tbSuhu);

        TabRawat.addTab("Suhu", Scroll);

        Scroll1.setComponentPopupMenu(jPopupMenu1);
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbRespirasi.setAutoCreateRowSorter(true);
        tbRespirasi.setComponentPopupMenu(jPopupMenu1);
        tbRespirasi.setName("tbRespirasi"); // NOI18N
        Scroll1.setViewportView(tbRespirasi);

        TabRawat.addTab("Respirasi", Scroll1);

        Scroll2.setComponentPopupMenu(jPopupMenu1);
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbNadi.setAutoCreateRowSorter(true);
        tbNadi.setComponentPopupMenu(jPopupMenu1);
        tbNadi.setName("tbNadi"); // NOI18N
        Scroll2.setViewportView(tbNadi);

        TabRawat.addTab("Nadi", Scroll2);

        Scroll3.setComponentPopupMenu(jPopupMenu1);
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbSpO2.setAutoCreateRowSorter(true);
        tbSpO2.setComponentPopupMenu(jPopupMenu1);
        tbSpO2.setName("tbSpO2"); // NOI18N
        Scroll3.setViewportView(tbSpO2);

        TabRawat.addTab("SpO2", Scroll3);

        Scroll4.setComponentPopupMenu(jPopupMenu1);
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbGCS.setAutoCreateRowSorter(true);
        tbGCS.setComponentPopupMenu(jPopupMenu1);
        tbGCS.setName("tbGCS"); // NOI18N
        Scroll4.setViewportView(tbGCS);

        TabRawat.addTab("GCS", Scroll4);

        Scroll5.setComponentPopupMenu(jPopupMenu1);
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbKesadaran.setAutoCreateRowSorter(true);
        tbKesadaran.setComponentPopupMenu(jPopupMenu1);
        tbKesadaran.setName("tbKesadaran"); // NOI18N
        Scroll5.setViewportView(tbKesadaran);

        TabRawat.addTab("Kesadaran", Scroll5);

        Scroll6.setComponentPopupMenu(jPopupMenu1);
        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbTensi.setAutoCreateRowSorter(true);
        tbTensi.setComponentPopupMenu(jPopupMenu1);
        tbTensi.setName("tbTensi"); // NOI18N
        Scroll6.setViewportView(tbTensi);

        TabRawat.addTab("Tensi", Scroll6);

        Scroll7.setComponentPopupMenu(jPopupMenu1);
        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        tbTB.setAutoCreateRowSorter(true);
        tbTB.setComponentPopupMenu(jPopupMenu1);
        tbTB.setName("tbTB"); // NOI18N
        Scroll7.setViewportView(tbTB);

        TabRawat.addTab("Tingi Badan", Scroll7);

        Scroll8.setComponentPopupMenu(jPopupMenu1);
        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        tbBB.setAutoCreateRowSorter(true);
        tbBB.setComponentPopupMenu(jPopupMenu1);
        tbBB.setName("tbBB"); // NOI18N
        Scroll8.setViewportView(tbBB);

        TabRawat.addTab("Berat Badan", Scroll8);

        Scroll9.setComponentPopupMenu(jPopupMenu1);
        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);

        tbLP.setAutoCreateRowSorter(true);
        tbLP.setComponentPopupMenu(jPopupMenu1);
        tbLP.setName("tbLP"); // NOI18N
        Scroll9.setViewportView(tbLP);

        TabRawat.addTab("Lingkar Perut", Scroll9);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,BtnKeluar);}
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                try{
                    htmlContent = new StringBuilder();
                    htmlContent.append(                             
                        "<tr class='isi'>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Registrasi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.RM</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nama Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Stts Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Stts Lanjut</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Pulang</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Encounter</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Suhu (°C)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Petugas/Dokter/Praktisi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Praktisi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Jam</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Observation Suhu</b></td>"+
                        "</tr>"
                    );
                    for (i = 0; i < tabModeSuhu.getRowCount(); i++) {
                        htmlContent.append(
                            "<tr class='isi'>"+
                                "<td valign='top'>"+tbSuhu.getValueAt(i,1).toString()+"</td>"+
                                "<td valign='top'>"+tbSuhu.getValueAt(i,2).toString()+"</td>"+
                                "<td valign='top'>"+tbSuhu.getValueAt(i,3).toString()+"</td>"+
                                "<td valign='top'>"+tbSuhu.getValueAt(i,4).toString()+"</td>"+
                                "<td valign='top'>"+tbSuhu.getValueAt(i,5).toString()+"</td>"+
                                "<td valign='top'>"+tbSuhu.getValueAt(i,6).toString()+"</td>"+
                                "<td valign='top'>"+tbSuhu.getValueAt(i,7).toString()+"</td>"+
                                "<td valign='top'>"+tbSuhu.getValueAt(i,8).toString()+"</td>"+
                                "<td valign='top'>"+tbSuhu.getValueAt(i,9).toString()+"</td>"+
                                "<td valign='top'>"+tbSuhu.getValueAt(i,10).toString()+"</td>"+
                                "<td valign='top'>"+tbSuhu.getValueAt(i,11).toString()+"</td>"+
                                "<td valign='top'>"+tbSuhu.getValueAt(i,12).toString()+"</td>"+
                                "<td valign='top'>"+tbSuhu.getValueAt(i,13).toString()+"</td>"+
                                "<td valign='top'>"+tbSuhu.getValueAt(i,14).toString()+"</td>"+
                                "<td valign='top'>"+tbSuhu.getValueAt(i,15).toString()+"</td>"+
                            "</tr>");
                    }
                    LoadHTML.setText(
                        "<html>"+
                          "<table width='1500px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
                           htmlContent.toString()+
                          "</table>"+
                        "</html>"
                    );

                    File g = new File("file2.css");            
                    BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                    bg.write(
                        ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                        ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                        ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                        ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                        ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                        ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
                    );
                    bg.close();

                    File f = new File("DataSatuSehatObservationTTVSuhu.html");            
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                    bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                                "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                                "<table width='1500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                    "<tr class='isi2'>"+
                                        "<td valign='top' align='center'>"+
                                            "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                            akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                            akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                            "<font size='2' face='Tahoma'>DATA PENGIRIMAN SATU SEHAT OBSERVATION-TTV SUHU TUBUH<br><br></font>"+        
                                        "</td>"+
                                   "</tr>"+
                                "</table>")
                    );
                    bw.close();                         
                    Desktop.getDesktop().browse(f.toURI());
                }catch(Exception e){
                    System.out.println("Notifikasi : "+e);
                }
                break;
            case 1:
                try{
                    htmlContent = new StringBuilder();
                    htmlContent.append(                             
                        "<tr class='isi'>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Registrasi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.RM</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nama Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Stts Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Stts Lanjut</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Pulang</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Encounter</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Resp(/menit)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Petugas/Dokter/Praktisi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Praktisi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Jam</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Observation Suhu</b></td>"+
                        "</tr>"
                    );
                    for (i = 0; i < tabModeRespirasi.getRowCount(); i++) {
                        htmlContent.append(
                            "<tr class='isi'>"+
                                "<td valign='top'>"+tbRespirasi.getValueAt(i,1).toString()+"</td>"+
                                "<td valign='top'>"+tbRespirasi.getValueAt(i,2).toString()+"</td>"+
                                "<td valign='top'>"+tbRespirasi.getValueAt(i,3).toString()+"</td>"+
                                "<td valign='top'>"+tbRespirasi.getValueAt(i,4).toString()+"</td>"+
                                "<td valign='top'>"+tbRespirasi.getValueAt(i,5).toString()+"</td>"+
                                "<td valign='top'>"+tbRespirasi.getValueAt(i,6).toString()+"</td>"+
                                "<td valign='top'>"+tbRespirasi.getValueAt(i,7).toString()+"</td>"+
                                "<td valign='top'>"+tbRespirasi.getValueAt(i,8).toString()+"</td>"+
                                "<td valign='top'>"+tbRespirasi.getValueAt(i,9).toString()+"</td>"+
                                "<td valign='top'>"+tbRespirasi.getValueAt(i,10).toString()+"</td>"+
                                "<td valign='top'>"+tbRespirasi.getValueAt(i,11).toString()+"</td>"+
                                "<td valign='top'>"+tbRespirasi.getValueAt(i,12).toString()+"</td>"+
                                "<td valign='top'>"+tbRespirasi.getValueAt(i,13).toString()+"</td>"+
                                "<td valign='top'>"+tbRespirasi.getValueAt(i,14).toString()+"</td>"+
                                "<td valign='top'>"+tbRespirasi.getValueAt(i,15).toString()+"</td>"+
                            "</tr>");
                    }
                    LoadHTML.setText(
                        "<html>"+
                          "<table width='1500px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
                           htmlContent.toString()+
                          "</table>"+
                        "</html>"
                    );

                    File g = new File("file2.css");            
                    BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                    bg.write(
                        ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                        ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                        ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                        ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                        ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                        ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
                    );
                    bg.close();

                    File f = new File("DataSatuSehatObservationTTVRespirasi.html");            
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                    bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                                "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                                "<table width='1500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                    "<tr class='isi2'>"+
                                        "<td valign='top' align='center'>"+
                                            "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                            akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                            akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                            "<font size='2' face='Tahoma'>DATA PENGIRIMAN SATU SEHAT OBSERVATION-TTV RESPIRASI<br><br></font>"+        
                                        "</td>"+
                                   "</tr>"+
                                "</table>")
                    );
                    bw.close();                         
                    Desktop.getDesktop().browse(f.toURI());
                }catch(Exception e){
                    System.out.println("Notifikasi : "+e);
                }
                break;
            case 2:
                try{
                    htmlContent = new StringBuilder();
                    htmlContent.append(                             
                        "<tr class='isi'>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Registrasi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.RM</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nama Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Stts Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Stts Lanjut</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Pulang</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Encounter</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nadi(/menit)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Petugas/Dokter/Praktisi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Praktisi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Jam</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Observation Suhu</b></td>"+
                        "</tr>"
                    );
                    for (i = 0; i < tabModeNadi.getRowCount(); i++) {
                        htmlContent.append(
                            "<tr class='isi'>"+
                                "<td valign='top'>"+tbNadi.getValueAt(i,1).toString()+"</td>"+
                                "<td valign='top'>"+tbNadi.getValueAt(i,2).toString()+"</td>"+
                                "<td valign='top'>"+tbNadi.getValueAt(i,3).toString()+"</td>"+
                                "<td valign='top'>"+tbNadi.getValueAt(i,4).toString()+"</td>"+
                                "<td valign='top'>"+tbNadi.getValueAt(i,5).toString()+"</td>"+
                                "<td valign='top'>"+tbNadi.getValueAt(i,6).toString()+"</td>"+
                                "<td valign='top'>"+tbNadi.getValueAt(i,7).toString()+"</td>"+
                                "<td valign='top'>"+tbNadi.getValueAt(i,8).toString()+"</td>"+
                                "<td valign='top'>"+tbNadi.getValueAt(i,9).toString()+"</td>"+
                                "<td valign='top'>"+tbNadi.getValueAt(i,10).toString()+"</td>"+
                                "<td valign='top'>"+tbNadi.getValueAt(i,11).toString()+"</td>"+
                                "<td valign='top'>"+tbNadi.getValueAt(i,12).toString()+"</td>"+
                                "<td valign='top'>"+tbNadi.getValueAt(i,13).toString()+"</td>"+
                                "<td valign='top'>"+tbNadi.getValueAt(i,14).toString()+"</td>"+
                                "<td valign='top'>"+tbNadi.getValueAt(i,15).toString()+"</td>"+
                            "</tr>");
                    }
                    
                    LoadHTML.setText(
                        "<html>"+
                          "<table width='1500px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
                           htmlContent.toString()+
                          "</table>"+
                        "</html>"
                    );

                    File g = new File("file2.css");            
                    BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                    bg.write(
                        ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                        ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                        ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                        ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                        ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                        ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
                    );
                    bg.close();

                    File f = new File("DataSatuSehatObservationTTVNadi.html");            
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                    bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                                "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                                "<table width='1500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                    "<tr class='isi2'>"+
                                        "<td valign='top' align='center'>"+
                                            "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                            akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                            akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                            "<font size='2' face='Tahoma'>DATA PENGIRIMAN SATU SEHAT OBSERVATION-TTV NADI<br><br></font>"+        
                                        "</td>"+
                                   "</tr>"+
                                "</table>")
                    );
                    bw.close();                         
                    Desktop.getDesktop().browse(f.toURI());
                }catch(Exception e){
                    System.out.println("Notifikasi : "+e);
                }
                break;
            case 3:
                try{
                    htmlContent = new StringBuilder();
                    htmlContent.append(                             
                        "<tr class='isi'>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Registrasi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.RM</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nama Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Stts Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Stts Lanjut</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Pulang</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Encounter</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>SpO2(%)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Petugas/Dokter/Praktisi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Praktisi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Jam</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Observation Suhu</b></td>"+
                        "</tr>"
                    );
                    for (i = 0; i < tabModeSpO2.getRowCount(); i++) {
                        htmlContent.append(
                            "<tr class='isi'>"+
                                "<td valign='top'>"+tbSpO2.getValueAt(i,1).toString()+"</td>"+
                                "<td valign='top'>"+tbSpO2.getValueAt(i,2).toString()+"</td>"+
                                "<td valign='top'>"+tbSpO2.getValueAt(i,3).toString()+"</td>"+
                                "<td valign='top'>"+tbSpO2.getValueAt(i,4).toString()+"</td>"+
                                "<td valign='top'>"+tbSpO2.getValueAt(i,5).toString()+"</td>"+
                                "<td valign='top'>"+tbSpO2.getValueAt(i,6).toString()+"</td>"+
                                "<td valign='top'>"+tbSpO2.getValueAt(i,7).toString()+"</td>"+
                                "<td valign='top'>"+tbSpO2.getValueAt(i,8).toString()+"</td>"+
                                "<td valign='top'>"+tbSpO2.getValueAt(i,9).toString()+"</td>"+
                                "<td valign='top'>"+tbSpO2.getValueAt(i,10).toString()+"</td>"+
                                "<td valign='top'>"+tbSpO2.getValueAt(i,11).toString()+"</td>"+
                                "<td valign='top'>"+tbSpO2.getValueAt(i,12).toString()+"</td>"+
                                "<td valign='top'>"+tbSpO2.getValueAt(i,13).toString()+"</td>"+
                                "<td valign='top'>"+tbSpO2.getValueAt(i,14).toString()+"</td>"+
                                "<td valign='top'>"+tbSpO2.getValueAt(i,15).toString()+"</td>"+
                            "</tr>");
                    }
                    
                    LoadHTML.setText(
                        "<html>"+
                          "<table width='1500px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
                           htmlContent.toString()+
                          "</table>"+
                        "</html>"
                    );

                    File g = new File("file2.css");            
                    BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                    bg.write(
                        ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                        ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                        ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                        ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                        ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                        ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
                    );
                    bg.close();

                    File f = new File("DataSatuSehatObservationTTVSpO2.html");            
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                    bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                                "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                                "<table width='1500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                    "<tr class='isi2'>"+
                                        "<td valign='top' align='center'>"+
                                            "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                            akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                            akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                            "<font size='2' face='Tahoma'>DATA PENGIRIMAN SATU SEHAT OBSERVATION-TTV SPO2<br><br></font>"+        
                                        "</td>"+
                                   "</tr>"+
                                "</table>")
                    );
                    bw.close();                         
                    Desktop.getDesktop().browse(f.toURI());
                }catch(Exception e){
                    System.out.println("Notifikasi : "+e);
                }
                break;
            case 4:
                try{
                    htmlContent = new StringBuilder();
                    htmlContent.append(                             
                        "<tr class='isi'>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Registrasi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.RM</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nama Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Stts Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Stts Lanjut</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Pulang</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Encounter</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>GCS</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Petugas/Dokter/Praktisi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Praktisi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Jam</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Observation Suhu</b></td>"+
                        "</tr>"
                    );
                    for (i = 0; i < tabModeGCS.getRowCount(); i++) {
                        htmlContent.append(
                            "<tr class='isi'>"+
                                "<td valign='top'>"+tbGCS.getValueAt(i,1).toString()+"</td>"+
                                "<td valign='top'>"+tbGCS.getValueAt(i,2).toString()+"</td>"+
                                "<td valign='top'>"+tbGCS.getValueAt(i,3).toString()+"</td>"+
                                "<td valign='top'>"+tbGCS.getValueAt(i,4).toString()+"</td>"+
                                "<td valign='top'>"+tbGCS.getValueAt(i,5).toString()+"</td>"+
                                "<td valign='top'>"+tbGCS.getValueAt(i,6).toString()+"</td>"+
                                "<td valign='top'>"+tbGCS.getValueAt(i,7).toString()+"</td>"+
                                "<td valign='top'>"+tbGCS.getValueAt(i,8).toString()+"</td>"+
                                "<td valign='top'>"+tbGCS.getValueAt(i,9).toString()+"</td>"+
                                "<td valign='top'>"+tbGCS.getValueAt(i,10).toString()+"</td>"+
                                "<td valign='top'>"+tbGCS.getValueAt(i,11).toString()+"</td>"+
                                "<td valign='top'>"+tbGCS.getValueAt(i,12).toString()+"</td>"+
                                "<td valign='top'>"+tbGCS.getValueAt(i,13).toString()+"</td>"+
                                "<td valign='top'>"+tbGCS.getValueAt(i,14).toString()+"</td>"+
                                "<td valign='top'>"+tbGCS.getValueAt(i,15).toString()+"</td>"+
                            "</tr>");
                    }
                    
                    LoadHTML.setText(
                        "<html>"+
                          "<table width='1500px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
                           htmlContent.toString()+
                          "</table>"+
                        "</html>"
                    );

                    File g = new File("file2.css");            
                    BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                    bg.write(
                        ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                        ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                        ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                        ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                        ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                        ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
                    );
                    bg.close();

                    File f = new File("DataSatuSehatObservationTTVGcs.html");            
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                    bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                                "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                                "<table width='1500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                    "<tr class='isi2'>"+
                                        "<td valign='top' align='center'>"+
                                            "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                            akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                            akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                            "<font size='2' face='Tahoma'>DATA PENGIRIMAN SATU SEHAT OBSERVATION-TTV GCS<br><br></font>"+        
                                        "</td>"+
                                   "</tr>"+
                                "</table>")
                    );
                    bw.close();                         
                    Desktop.getDesktop().browse(f.toURI());
                }catch(Exception e){
                    System.out.println("Notifikasi : "+e);
                }
                break;
            case 5:
                try{
                    htmlContent = new StringBuilder();
                    htmlContent.append(                             
                        "<tr class='isi'>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Registrasi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.RM</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nama Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Stts Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Stts Lanjut</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Pulang</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Encounter</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Kesadaran</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Petugas/Dokter/Praktisi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Praktisi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Jam</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Observation Suhu</b></td>"+
                        "</tr>"
                    );
                    for (i = 0; i < tabModeKesadaran.getRowCount(); i++) {
                        htmlContent.append(
                            "<tr class='isi'>"+
                                "<td valign='top'>"+tbKesadaran.getValueAt(i,1).toString()+"</td>"+
                                "<td valign='top'>"+tbKesadaran.getValueAt(i,2).toString()+"</td>"+
                                "<td valign='top'>"+tbKesadaran.getValueAt(i,3).toString()+"</td>"+
                                "<td valign='top'>"+tbKesadaran.getValueAt(i,4).toString()+"</td>"+
                                "<td valign='top'>"+tbKesadaran.getValueAt(i,5).toString()+"</td>"+
                                "<td valign='top'>"+tbKesadaran.getValueAt(i,6).toString()+"</td>"+
                                "<td valign='top'>"+tbKesadaran.getValueAt(i,7).toString()+"</td>"+
                                "<td valign='top'>"+tbKesadaran.getValueAt(i,8).toString()+"</td>"+
                                "<td valign='top'>"+tbKesadaran.getValueAt(i,9).toString()+"</td>"+
                                "<td valign='top'>"+tbKesadaran.getValueAt(i,10).toString()+"</td>"+
                                "<td valign='top'>"+tbKesadaran.getValueAt(i,11).toString()+"</td>"+
                                "<td valign='top'>"+tbKesadaran.getValueAt(i,12).toString()+"</td>"+
                                "<td valign='top'>"+tbKesadaran.getValueAt(i,13).toString()+"</td>"+
                                "<td valign='top'>"+tbKesadaran.getValueAt(i,14).toString()+"</td>"+
                                "<td valign='top'>"+tbKesadaran.getValueAt(i,15).toString()+"</td>"+
                            "</tr>");
                    }
                    
                    LoadHTML.setText(
                        "<html>"+
                          "<table width='1500px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
                           htmlContent.toString()+
                          "</table>"+
                        "</html>"
                    );

                    File g = new File("file2.css");            
                    BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                    bg.write(
                        ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                        ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                        ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                        ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                        ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                        ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
                    );
                    bg.close();

                    File f = new File("DataSatuSehatObservationTTVKesadaran.html");            
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                    bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                                "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                                "<table width='1500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                    "<tr class='isi2'>"+
                                        "<td valign='top' align='center'>"+
                                            "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                            akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                            akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                            "<font size='2' face='Tahoma'>DATA PENGIRIMAN SATU SEHAT OBSERVATION-TTV KESADARAN<br><br></font>"+        
                                        "</td>"+
                                   "</tr>"+
                                "</table>")
                    );
                    bw.close();                         
                    Desktop.getDesktop().browse(f.toURI());
                }catch(Exception e){
                    System.out.println("Notifikasi : "+e);
                }
                break;
            case 6:
                try{
                    htmlContent = new StringBuilder();
                    htmlContent.append(                             
                        "<tr class='isi'>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Registrasi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.RM</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nama Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Stts Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Stts Lanjut</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Pulang</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Encounter</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tensi(mmHg)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Petugas/Dokter/Praktisi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Praktisi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Jam</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Observation Suhu</b></td>"+
                        "</tr>"
                    );
                    for (i = 0; i < tabModeTensi.getRowCount(); i++) {
                        htmlContent.append(
                            "<tr class='isi'>"+
                                "<td valign='top'>"+tbTensi.getValueAt(i,1).toString()+"</td>"+
                                "<td valign='top'>"+tbTensi.getValueAt(i,2).toString()+"</td>"+
                                "<td valign='top'>"+tbTensi.getValueAt(i,3).toString()+"</td>"+
                                "<td valign='top'>"+tbTensi.getValueAt(i,4).toString()+"</td>"+
                                "<td valign='top'>"+tbTensi.getValueAt(i,5).toString()+"</td>"+
                                "<td valign='top'>"+tbTensi.getValueAt(i,6).toString()+"</td>"+
                                "<td valign='top'>"+tbTensi.getValueAt(i,7).toString()+"</td>"+
                                "<td valign='top'>"+tbTensi.getValueAt(i,8).toString()+"</td>"+
                                "<td valign='top'>"+tbTensi.getValueAt(i,9).toString()+"</td>"+
                                "<td valign='top'>"+tbTensi.getValueAt(i,10).toString()+"</td>"+
                                "<td valign='top'>"+tbTensi.getValueAt(i,11).toString()+"</td>"+
                                "<td valign='top'>"+tbTensi.getValueAt(i,12).toString()+"</td>"+
                                "<td valign='top'>"+tbTensi.getValueAt(i,13).toString()+"</td>"+
                                "<td valign='top'>"+tbTensi.getValueAt(i,14).toString()+"</td>"+
                                "<td valign='top'>"+tbTensi.getValueAt(i,15).toString()+"</td>"+
                            "</tr>");
                    }
                    
                    LoadHTML.setText(
                        "<html>"+
                          "<table width='1500px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
                           htmlContent.toString()+
                          "</table>"+
                        "</html>"
                    );

                    File g = new File("file2.css");            
                    BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                    bg.write(
                        ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                        ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                        ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                        ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                        ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                        ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
                    );
                    bg.close();

                    File f = new File("DataSatuSehatObservationTTVTensi.html");            
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                    bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                                "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                                "<table width='1500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                    "<tr class='isi2'>"+
                                        "<td valign='top' align='center'>"+
                                            "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                            akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                            akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                            "<font size='2' face='Tahoma'>DATA PENGIRIMAN SATU SEHAT OBSERVATION-TTV TENSI<br><br></font>"+        
                                        "</td>"+
                                   "</tr>"+
                                "</table>")
                    );
                    bw.close();                         
                    Desktop.getDesktop().browse(f.toURI());
                }catch(Exception e){
                    System.out.println("Notifikasi : "+e);
                }
                break;
            case 7:
                try{
                    htmlContent = new StringBuilder();
                    htmlContent.append(                             
                        "<tr class='isi'>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Registrasi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.RM</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nama Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Stts Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Stts Lanjut</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Pulang</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Encounter</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>TB(Cm)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Petugas/Dokter/Praktisi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Praktisi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Jam</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Observation Suhu</b></td>"+
                        "</tr>"
                    );
                    for (i = 0; i < tabModeTB.getRowCount(); i++) {
                        htmlContent.append(
                            "<tr class='isi'>"+
                                "<td valign='top'>"+tbTB.getValueAt(i,1).toString()+"</td>"+
                                "<td valign='top'>"+tbTB.getValueAt(i,2).toString()+"</td>"+
                                "<td valign='top'>"+tbTB.getValueAt(i,3).toString()+"</td>"+
                                "<td valign='top'>"+tbTB.getValueAt(i,4).toString()+"</td>"+
                                "<td valign='top'>"+tbTB.getValueAt(i,5).toString()+"</td>"+
                                "<td valign='top'>"+tbTB.getValueAt(i,6).toString()+"</td>"+
                                "<td valign='top'>"+tbTB.getValueAt(i,7).toString()+"</td>"+
                                "<td valign='top'>"+tbTB.getValueAt(i,8).toString()+"</td>"+
                                "<td valign='top'>"+tbTB.getValueAt(i,9).toString()+"</td>"+
                                "<td valign='top'>"+tbTB.getValueAt(i,10).toString()+"</td>"+
                                "<td valign='top'>"+tbTB.getValueAt(i,11).toString()+"</td>"+
                                "<td valign='top'>"+tbTB.getValueAt(i,12).toString()+"</td>"+
                                "<td valign='top'>"+tbTB.getValueAt(i,13).toString()+"</td>"+
                                "<td valign='top'>"+tbTB.getValueAt(i,14).toString()+"</td>"+
                                "<td valign='top'>"+tbTB.getValueAt(i,15).toString()+"</td>"+
                            "</tr>");
                    }
                    
                    LoadHTML.setText(
                        "<html>"+
                          "<table width='1500px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
                           htmlContent.toString()+
                          "</table>"+
                        "</html>"
                    );

                    File g = new File("file2.css");            
                    BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                    bg.write(
                        ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                        ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                        ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                        ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                        ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                        ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
                    );
                    bg.close();

                    File f = new File("DataSatuSehatObservationTTVTinggiBadan.html");            
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                    bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                                "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                                "<table width='1500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                    "<tr class='isi2'>"+
                                        "<td valign='top' align='center'>"+
                                            "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                            akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                            akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                            "<font size='2' face='Tahoma'>DATA PENGIRIMAN SATU SEHAT OBSERVATION-TTV TINGGI BADAN<br><br></font>"+        
                                        "</td>"+
                                   "</tr>"+
                                "</table>")
                    );
                    bw.close();                         
                    Desktop.getDesktop().browse(f.toURI());
                }catch(Exception e){
                    System.out.println("Notifikasi : "+e);
                }
                break;
            case 8:
                try{
                    htmlContent = new StringBuilder();
                    htmlContent.append(                             
                        "<tr class='isi'>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Registrasi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.RM</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nama Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Stts Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Stts Lanjut</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Pulang</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Encounter</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>BB(Kg)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Petugas/Dokter/Praktisi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Praktisi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Jam</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Observation Suhu</b></td>"+
                        "</tr>"
                    );
                    for (i = 0; i < tabModeBB.getRowCount(); i++) {
                        htmlContent.append(
                            "<tr class='isi'>"+
                                "<td valign='top'>"+tbBB.getValueAt(i,1).toString()+"</td>"+
                                "<td valign='top'>"+tbBB.getValueAt(i,2).toString()+"</td>"+
                                "<td valign='top'>"+tbBB.getValueAt(i,3).toString()+"</td>"+
                                "<td valign='top'>"+tbBB.getValueAt(i,4).toString()+"</td>"+
                                "<td valign='top'>"+tbBB.getValueAt(i,5).toString()+"</td>"+
                                "<td valign='top'>"+tbBB.getValueAt(i,6).toString()+"</td>"+
                                "<td valign='top'>"+tbBB.getValueAt(i,7).toString()+"</td>"+
                                "<td valign='top'>"+tbBB.getValueAt(i,8).toString()+"</td>"+
                                "<td valign='top'>"+tbBB.getValueAt(i,9).toString()+"</td>"+
                                "<td valign='top'>"+tbBB.getValueAt(i,10).toString()+"</td>"+
                                "<td valign='top'>"+tbBB.getValueAt(i,11).toString()+"</td>"+
                                "<td valign='top'>"+tbBB.getValueAt(i,12).toString()+"</td>"+
                                "<td valign='top'>"+tbBB.getValueAt(i,13).toString()+"</td>"+
                                "<td valign='top'>"+tbBB.getValueAt(i,14).toString()+"</td>"+
                                "<td valign='top'>"+tbBB.getValueAt(i,15).toString()+"</td>"+
                            "</tr>");
                    }
                    
                    LoadHTML.setText(
                        "<html>"+
                          "<table width='1500px' border='0' align='center' cellpadding='1px' cellspacing='0' class='bbl_form'>"+
                           htmlContent.toString()+
                          "</table>"+
                        "</html>"
                    );

                    File g = new File("file2.css");            
                    BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                    bg.write(
                        ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                        ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                        ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                        ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                        ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                        ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
                    );
                    bg.close();

                    File f = new File("DataSatuSehatObservationTTVBeratBadan.html");            
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                    bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                                "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                                "<table width='1500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='bbl_form'>"+
                                    "<tr class='isi2'>"+
                                        "<td valign='top' align='center'>"+
                                            "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                            akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                            akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                            "<font size='2' face='Tahoma'>DATA PENGIRIMAN SATU SEHAT OBSERVATION-TTV BERAT BADAN<br><br></font>"+        
                                        "</td>"+
                                   "</tr>"+
                                "</table>")
                    );
                    bw.close();                         
                    Desktop.getDesktop().browse(f.toURI());
                }catch(Exception e){
                    System.out.println("Notifikasi : "+e);
                }
                break;
            case 9:
                try{
                    htmlContent = new StringBuilder();
                    htmlContent.append(                             
                        "<tr class='isi'>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Registrasi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.RM</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nama Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Stts Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Stts Lanjut</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Pulang</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Encounter</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>LP(Cm)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Petugas/Dokter/Praktisi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Praktisi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Jam</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Observation Suhu</b></td>"+
                        "</tr>"
                    );
                    for (i = 0; i < tabModeLP.getRowCount(); i++) {
                        htmlContent.append(
                            "<tr class='isi'>"+
                                "<td valign='top'>"+tbLP.getValueAt(i,1).toString()+"</td>"+
                                "<td valign='top'>"+tbLP.getValueAt(i,2).toString()+"</td>"+
                                "<td valign='top'>"+tbLP.getValueAt(i,3).toString()+"</td>"+
                                "<td valign='top'>"+tbLP.getValueAt(i,4).toString()+"</td>"+
                                "<td valign='top'>"+tbLP.getValueAt(i,5).toString()+"</td>"+
                                "<td valign='top'>"+tbLP.getValueAt(i,6).toString()+"</td>"+
                                "<td valign='top'>"+tbLP.getValueAt(i,7).toString()+"</td>"+
                                "<td valign='top'>"+tbLP.getValueAt(i,8).toString()+"</td>"+
                                "<td valign='top'>"+tbLP.getValueAt(i,9).toString()+"</td>"+
                                "<td valign='top'>"+tbLP.getValueAt(i,10).toString()+"</td>"+
                                "<td valign='top'>"+tbLP.getValueAt(i,11).toString()+"</td>"+
                                "<td valign='top'>"+tbLP.getValueAt(i,12).toString()+"</td>"+
                                "<td valign='top'>"+tbLP.getValueAt(i,13).toString()+"</td>"+
                                "<td valign='top'>"+tbLP.getValueAt(i,14).toString()+"</td>"+
                                "<td valign='top'>"+tbLP.getValueAt(i,15).toString()+"</td>"+
                            "</tr>");
                    }
                    
                    LoadHTML.setText(
                        "<html>"+
                          "<table width='1500px' border='0' align='center' cellpadding='1px' cellspacing='0' class='lpl_form'>"+
                           htmlContent.toString()+
                          "</table>"+
                        "</html>"
                    );

                    File g = new File("file2.css");            
                    BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                    bg.write(
                        ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                        ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                        ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                        ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                        ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                        ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
                    );
                    bg.close();

                    File f = new File("DataSatuSehatObservationTTVLingkarPerut.html");            
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                    bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                                "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                                "<table width='1500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='lpl_form'>"+
                                    "<tr class='isi2'>"+
                                        "<td valign='top' align='center'>"+
                                            "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                            akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                            akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                            "<font size='2' face='Tahoma'>DATA PENGIRIMAN SATU SEHAT OBSERVATION-TTV LINGKAR PERUT<br><br></font>"+        
                                        "</td>"+
                                   "</tr>"+
                                "</table>")
                    );
                    bw.close();                         
                    Desktop.getDesktop().browse(f.toURI());
                }catch(Exception e){
                    System.out.println("Notifikasi : "+e);
                }
                break;
            default:
                break;
        }
        this.setCursor(Cursor.getDefaultCursor());    
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbSuhu.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        TabRawatMouseClicked(null);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt,TCari,BtnPrint);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void BtnKirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKirimActionPerformed
        if (TabRawat.getSelectedIndex() == 0) {
            for (i = 0; i < tbSuhu.getRowCount(); i++) {
                if (tbSuhu.getValueAt(i, 0).toString().equals("true") 
                        && (!tbSuhu.getValueAt(i, 5).toString().equals("")) 
                        && (!tbSuhu.getValueAt(i, 12).toString().equals("")) 
                        && tbSuhu.getValueAt(i, 15).toString().equals("")) {
                    try {
                        iddokter = cekViaSatuSehat.tampilIDParktisi(tbSuhu.getValueAt(i, 12).toString());
                        idpasien = cekViaSatuSehat.tampilIDPasien(tbSuhu.getValueAt(i, 5).toString());
                        try {
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                            json = "{" +
                                        "\"resourceType\": \"Observation\"," +
                                        "\"status\": \"final\"," +
                                        "\"category\": [" +
                                            "{" +
                                                "\"coding\": [" +
                                                    "{" +
                                                        "\"system\": \"http://terminology.hl7.org/CodeSystem/observation-category\"," +
                                                        "\"code\": \"vital-signs\"," +
                                                        "\"display\": \"Vital Signs\"" +
                                                    "}" +
                                                "]" +
                                            "}" +
                                        "]," +
                                        "\"code\": {" +
                                            "\"coding\": [" +
                                                "{" +
                                                    "\"system\": \"http://loinc.org\"," +
                                                    "\"code\": \"8310-5\"," +
                                                    "\"display\": \"Body temperature\"" +
                                                "}" +
                                            "]" +
                                        "}," +
                                        "\"subject\": {" +
                                            "\"reference\": \"Patient/"+idpasien+"\"" +
                                        "}," +
                                        "\"performer\": [" +
                                            "{" +
                                                "\"reference\": \"Practitioner/"+iddokter+"\"" +
                                            "}" +
                                        "]," +
                                        "\"encounter\": {" +
                                            "\"reference\": \"Encounter/"+tbSuhu.getValueAt(i,9).toString()+"\"," +
                                            "\"display\": \"Pemeriksaan Fisik Suhu Badan di "+tbSuhu.getValueAt(i,7).toString().replaceAll("Ralan","Rawat Jalan/IGD").replaceAll("Ranap","Rawat Inap")+", Pasien "+tbSuhu.getValueAt(i,4).toString()+" Pada Tanggal "+tbSuhu.getValueAt(i,13).toString()+" Jam "+tbSuhu.getValueAt(i,14).toString()+"\"" +
                                        "}," +
                                        "\"effectiveDateTime\": \""+tbSuhu.getValueAt(i,13).toString()+"T"+tbSuhu.getValueAt(i,14).toString()+"+07:00\"," +
                                        "\"valueQuantity\": {" +
                                            "\"value\": "+tbSuhu.getValueAt(i,10).toString().replaceAll(",",".")+"," +
                                            "\"unit\": \"degree Celsius\"," +
                                            "\"system\": \"http://unitsofmeasure.org\"," +
                                            "\"code\": \"Cel\"" +
                                        "}" +
                                   "}";
                            System.out.println("URL : " + link + "/Observation");
                            System.out.println("Request JSON : " + json);
                            requestEntity = new HttpEntity(json, headers);
                            json = api.getRest().exchange(link + "/Observation", HttpMethod.POST, requestEntity, String.class).getBody();
                            System.out.println("Result JSON : " + json);
                            root = mapper.readTree(json);
                            response = root.path("id");
                            if (!response.asText().equals("")) {
                                Sequel.menyimpan("satu_sehat_observationttvsuhu", "?,?,?,?,?", "Observation Suhu", 5, new String[]{
                                    tbSuhu.getValueAt(i, 2).toString(), tbSuhu.getValueAt(i, 13).toString(), tbSuhu.getValueAt(i, 14).toString(), tbSuhu.getValueAt(i, 7).toString(), response.asText()
                                });
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi Bridging : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    }
                }
            }
            tampilsuhu();
        } else if (TabRawat.getSelectedIndex() == 1) {
            for (i = 0; i < tbRespirasi.getRowCount(); i++) {
                if (tbRespirasi.getValueAt(i, 0).toString().equals("true") 
                        && (!tbRespirasi.getValueAt(i, 5).toString().equals("")) 
                        && (!tbRespirasi.getValueAt(i, 12).toString().equals("")) 
                        && tbRespirasi.getValueAt(i, 15).toString().equals("")) {
                    try {
                        iddokter = cekViaSatuSehat.tampilIDParktisi(tbRespirasi.getValueAt(i, 12).toString());
                        idpasien = cekViaSatuSehat.tampilIDPasien(tbRespirasi.getValueAt(i, 5).toString());
                        try {
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                            json = "{" +
                                        "\"resourceType\": \"Observation\"," +
                                        "\"status\": \"final\"," +
                                        "\"category\": [" +
                                            "{" +
                                                "\"coding\": [" +
                                                    "{" +
                                                        "\"system\": \"http://terminology.hl7.org/CodeSystem/observation-category\"," +
                                                        "\"code\": \"vital-signs\"," +
                                                        "\"display\": \"Vital Signs\"" +
                                                    "}" +
                                                "]" +
                                            "}" +
                                        "]," +
                                        "\"code\": {" +
                                            "\"coding\": [" +
                                                "{" +
                                                    "\"system\": \"http://loinc.org\"," +
                                                    "\"code\": \"9279-1\"," +
                                                    "\"display\": \"Respiratory rate\"" +
                                                "}" +
                                            "]" +
                                        "}," +
                                        "\"subject\": {" +
                                            "\"reference\": \"Patient/"+idpasien+"\"" +
                                        "}," +
                                        "\"performer\": [" +
                                            "{" +
                                                "\"reference\": \"Practitioner/"+iddokter+"\"" +
                                            "}" +
                                        "]," +
                                        "\"encounter\": {" +
                                            "\"reference\": \"Encounter/"+tbRespirasi.getValueAt(i,9).toString()+"\"," +
                                            "\"display\": \"Pemeriksaan Fisik Respirasi di "+tbRespirasi.getValueAt(i,7).toString().replaceAll("Ralan","Rawat Jalan/IGD").replaceAll("Ranap","Rawat Inap")+", Pasien "+tbRespirasi.getValueAt(i,4).toString()+" Pada Tanggal "+tbRespirasi.getValueAt(i,13).toString()+" Jam "+tbRespirasi.getValueAt(i,14).toString()+"\"" +
                                        "}," +
                                        "\"effectiveDateTime\": \""+tbRespirasi.getValueAt(i,13).toString()+"T"+tbRespirasi.getValueAt(i,14).toString()+"+07:00\"," +
                                        "\"valueQuantity\": {" +
                                            "\"value\": "+tbRespirasi.getValueAt(i,10).toString().replaceAll(",",".")+"," +
                                            "\"unit\": \"breaths/minute\"," +
                                            "\"system\": \"http://unitsofmeasure.org\"," +
                                            "\"code\": \"/min\"" +
                                        "}" +
                                   "}";
                            System.out.println("URL : " + link + "/Observation");
                            System.out.println("Request JSON : " + json);
                            requestEntity = new HttpEntity(json, headers);
                            json = api.getRest().exchange(link + "/Observation", HttpMethod.POST, requestEntity, String.class).getBody();
                            System.out.println("Result JSON : " + json);
                            root = mapper.readTree(json);
                            response = root.path("id");
                            if (!response.asText().equals("")) {
                                Sequel.menyimpan("satu_sehat_observationttvrespirasi", "?,?,?,?,?", "Observation Respirasi", 5, new String[]{
                                    tbRespirasi.getValueAt(i, 2).toString(), tbRespirasi.getValueAt(i, 13).toString(), tbRespirasi.getValueAt(i, 14).toString(), tbRespirasi.getValueAt(i, 7).toString(), response.asText()
                                });
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi Bridging : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
            tampilrespirasi();
        } else if (TabRawat.getSelectedIndex() == 2) {
            for (i = 0; i < tbNadi.getRowCount(); i++) {
                if (tbNadi.getValueAt(i, 0).toString().equals("true") 
                        && (!tbNadi.getValueAt(i, 5).toString().equals("")) 
                        && (!tbNadi.getValueAt(i, 12).toString().equals("")) 
                        && tbNadi.getValueAt(i, 15).toString().equals("")) {
                    try {
                        iddokter = cekViaSatuSehat.tampilIDParktisi(tbNadi.getValueAt(i, 12).toString());
                        idpasien = cekViaSatuSehat.tampilIDPasien(tbNadi.getValueAt(i, 5).toString());
                        try {
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                            json = "{" +
                                        "\"resourceType\": \"Observation\"," +
                                        "\"status\": \"final\"," +
                                        "\"category\": [" +
                                            "{" +
                                                "\"coding\": [" +
                                                    "{" +
                                                        "\"system\": \"http://terminology.hl7.org/CodeSystem/observation-category\"," +
                                                        "\"code\": \"vital-signs\"," +
                                                        "\"display\": \"Vital Signs\"" +
                                                    "}" +
                                                "]" +
                                            "}" +
                                        "]," +
                                        "\"code\": {" +
                                            "\"coding\": [" +
                                                "{" +
                                                    "\"system\": \"http://loinc.org\"," +
                                                    "\"code\": \"8867-4\"," +
                                                    "\"display\": \"Heart rate\"" +
                                                "}" +
                                            "]" +
                                        "}," +
                                        "\"subject\": {" +
                                            "\"reference\": \"Patient/"+idpasien+"\"" +
                                        "}," +
                                        "\"performer\": [" +
                                            "{" +
                                                "\"reference\": \"Practitioner/"+iddokter+"\"" +
                                            "}" +
                                        "]," +
                                        "\"encounter\": {" +
                                            "\"reference\": \"Encounter/"+tbNadi.getValueAt(i,9).toString()+"\"," +
                                            "\"display\": \"Pemeriksaan Fisik Nadi di "+tbNadi.getValueAt(i,7).toString().replaceAll("Ralan","Rawat Jalan/IGD").replaceAll("Ranap","Rawat Inap")+", Pasien "+tbNadi.getValueAt(i,4).toString()+" Pada Tanggal "+tbNadi.getValueAt(i,13).toString()+" Jam "+tbNadi.getValueAt(i,14).toString()+"\"" +
                                        "}," +
                                        "\"effectiveDateTime\": \""+tbNadi.getValueAt(i,13).toString()+"T"+tbNadi.getValueAt(i,14).toString()+"+07:00\"," +
                                        "\"valueQuantity\": {" +
                                            "\"value\": "+tbNadi.getValueAt(i,10).toString().replaceAll(",",".")+"," +
                                            "\"unit\": \"breaths/minute\"," +
                                            "\"system\": \"http://unitsofmeasure.org\"," +
                                            "\"code\": \"/min\"" +
                                        "}" +
                                   "}";
                            System.out.println("URL : " + link + "/Observation");
                            System.out.println("Request JSON : " + json);
                            requestEntity = new HttpEntity(json, headers);
                            json = api.getRest().exchange(link + "/Observation", HttpMethod.POST, requestEntity, String.class).getBody();
                            System.out.println("Result JSON : " + json);
                            root = mapper.readTree(json);
                            response = root.path("id");
                            if (!response.asText().equals("")) {
                                Sequel.menyimpan("satu_sehat_observationttvnadi", "?,?,?,?,?", "Observation Nadi", 5, new String[]{
                                    tbNadi.getValueAt(i, 2).toString(), tbNadi.getValueAt(i, 13).toString(), tbNadi.getValueAt(i, 14).toString(), tbNadi.getValueAt(i, 7).toString(), response.asText()
                                });
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi Bridging : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
            tampilnadi();
        } else if (TabRawat.getSelectedIndex() == 3) {
            for (i = 0; i < tbSpO2.getRowCount(); i++) {
                if (tbSpO2.getValueAt(i, 0).toString().equals("true") 
                        && (!tbSpO2.getValueAt(i, 5).toString().equals("")) 
                        && (!tbSpO2.getValueAt(i, 12).toString().equals("")) 
                        && tbSpO2.getValueAt(i, 15).toString().equals("")) {
                    try {
                        iddokter = cekViaSatuSehat.tampilIDParktisi(tbSpO2.getValueAt(i, 12).toString());
                        idpasien = cekViaSatuSehat.tampilIDPasien(tbSpO2.getValueAt(i, 5).toString());
                        try {
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                            json = "{" +
                                        "\"resourceType\": \"Observation\"," +
                                        "\"status\": \"final\"," +
                                        "\"category\": [" +
                                            "{" +
                                                "\"coding\": [" +
                                                    "{" +
                                                        "\"system\": \"http://terminology.hl7.org/CodeSystem/observation-category\"," +
                                                        "\"code\": \"vital-signs\"," +
                                                        "\"display\": \"Vital Signs\"" +
                                                    "}" +
                                                "]" +
                                            "}" +
                                        "]," +
                                        "\"code\": {" +
                                            "\"coding\": [" +
                                                "{" +
                                                    "\"system\": \"http://loinc.org\"," +
                                                    "\"code\": \"59408-5\"," +
                                                    "\"display\": \"Oxygen saturation\"" +
                                                "}" +
                                            "]" +
                                        "}," +
                                        "\"subject\": {" +
                                            "\"reference\": \"Patient/"+idpasien+"\"" +
                                        "}," +
                                        "\"performer\": [" +
                                            "{" +
                                                "\"reference\": \"Practitioner/"+iddokter+"\"" +
                                            "}" +
                                        "]," +
                                        "\"encounter\": {" +
                                            "\"reference\": \"Encounter/"+tbSpO2.getValueAt(i,9).toString()+"\"," +
                                            "\"display\": \"Pemeriksaan Fisik SpO2  di "+tbSpO2.getValueAt(i,7).toString().replaceAll("Ralan","Rawat Jalan/IGD").replaceAll("Ranap","Rawat Inap")+", Pasien"+tbSpO2.getValueAt(i,4).toString()+" Pada Tanggal "+tbSpO2.getValueAt(i,13).toString()+" Jam "+tbSpO2.getValueAt(i,14).toString()+"\"" +
                                        "}," +
                                        "\"effectiveDateTime\": \""+tbSpO2.getValueAt(i,13).toString()+"T"+tbSpO2.getValueAt(i,14).toString()+"+07:00\"," +
                                        "\"valueQuantity\": {" +
                                            "\"value\": "+tbSpO2.getValueAt(i,10).toString().replaceAll(",",".")+"," +
                                            "\"unit\": \"percent saturation\"," +
                                            "\"system\": \"http://unitsofmeasure.org\"," +
                                            "\"code\": \"%\"" +
                                        "}" +
                                   "}";
                            System.out.println("URL : " + link + "/Observation");
                            System.out.println("Request JSON : " + json);
                            requestEntity = new HttpEntity(json, headers);
                            json = api.getRest().exchange(link + "/Observation", HttpMethod.POST, requestEntity, String.class).getBody();
                            System.out.println("Result JSON : " + json);
                            root = mapper.readTree(json);
                            response = root.path("id");
                            if (!response.asText().equals("")) {
                                Sequel.menyimpan("satu_sehat_observationttvspo2", "?,?,?,?,?", "Observation SpO2", 5, new String[]{
                                    tbSpO2.getValueAt(i, 2).toString(), tbSpO2.getValueAt(i, 13).toString(), tbSpO2.getValueAt(i, 14).toString(), tbSpO2.getValueAt(i, 7).toString(), response.asText()
                                });
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi Bridging : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
            tampilspo2();
        } else if (TabRawat.getSelectedIndex() == 4) {
            for (i = 0; i < tbGCS.getRowCount(); i++) {
                if (tbGCS.getValueAt(i, 0).toString().equals("true") 
                        && (!tbGCS.getValueAt(i, 5).toString().equals("")) 
                        && (!tbGCS.getValueAt(i, 12).toString().equals("")) 
                        && tbGCS.getValueAt(i, 15).toString().equals("")) {
                    try {
                        iddokter = cekViaSatuSehat.tampilIDParktisi(tbGCS.getValueAt(i, 12).toString());
                        idpasien = cekViaSatuSehat.tampilIDPasien(tbGCS.getValueAt(i, 5).toString());
                        try {
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                            json = "{" +
                                        "\"resourceType\": \"Observation\"," +
                                        "\"status\": \"final\"," +
                                        "\"category\": [" +
                                            "{" +
                                                "\"coding\": [" +
                                                    "{" +
                                                        "\"system\": \"http://terminology.hl7.org/CodeSystem/observation-category\"," +
                                                        "\"code\": \"vital-signs\"," +
                                                        "\"display\": \"Vital Signs\"" +
                                                    "}" +
                                                "]" +
                                            "}" +
                                        "]," +
                                        "\"code\": {" +
                                            "\"coding\": [" +
                                                "{" +
                                                    "\"system\": \"http://loinc.org\"," +
                                                    "\"code\": \"9269-2\"," +
                                                    "\"display\": \"Glasgow coma score total\"" +
                                                "}" +
                                            "]" +
                                        "}," +
                                        "\"subject\": {" +
                                            "\"reference\": \"Patient/"+idpasien+"\"" +
                                        "}," +
                                        "\"performer\": [" +
                                            "{" +
                                                "\"reference\": \"Practitioner/"+iddokter+"\"" +
                                            "}" +
                                        "]," +
                                        "\"encounter\": {" +
                                            "\"reference\": \"Encounter/"+tbGCS.getValueAt(i,9).toString()+"\"," +
                                            "\"display\": \"Pemeriksaan Fisik GCS di "+tbGCS.getValueAt(i,7).toString().replaceAll("Ralan","Rawat Jalan/IGD").replaceAll("Ranap","Rawat Inap")+", Pasien "+tbGCS.getValueAt(i,4).toString()+" Pada Tanggal "+tbGCS.getValueAt(i,13).toString()+" Jam "+tbGCS.getValueAt(i,14).toString()+"\"" +
                                        "}," +
                                        "\"effectiveDateTime\": \""+tbGCS.getValueAt(i,13).toString()+"T"+tbGCS.getValueAt(i,14).toString()+"+07:00\"," +
                                        "\"valueQuantity\": {" +
                                            "\"value\": "+tbGCS.getValueAt(i,10).toString().replaceAll(",",".")+"," +
                                            "\"system\": \"http://unitsofmeasure.org\"," +
                                            "\"code\": \"{score}\"" +
                                        "}" +
                                   "}";
                            System.out.println("URL : " + link + "/Observation");
                            System.out.println("Request JSON : " + json);
                            requestEntity = new HttpEntity(json, headers);
                            json = api.getRest().exchange(link + "/Observation", HttpMethod.POST, requestEntity, String.class).getBody();
                            System.out.println("Result JSON : " + json);
                            root = mapper.readTree(json);
                            response = root.path("id");
                            if (!response.asText().equals("")) {
                                Sequel.menyimpan("satu_sehat_observationttvgcs", "?,?,?,?,?", "Observation GCS", 5, new String[]{
                                    tbGCS.getValueAt(i, 2).toString(), tbGCS.getValueAt(i, 13).toString(), tbGCS.getValueAt(i, 14).toString(), tbGCS.getValueAt(i, 7).toString(), response.asText()
                                });
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi Bridging : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
            tampilgcs();
        } else if (TabRawat.getSelectedIndex() == 5) {
            for (i = 0; i < tbKesadaran.getRowCount(); i++) {
                if (tbKesadaran.getValueAt(i, 0).toString().equals("true") 
                        && (!tbKesadaran.getValueAt(i, 5).toString().equals("")) 
                        && (!tbKesadaran.getValueAt(i, 12).toString().equals("")) 
                        && tbKesadaran.getValueAt(i, 15).toString().equals("")) {
                    try {
                        iddokter = cekViaSatuSehat.tampilIDParktisi(tbKesadaran.getValueAt(i, 12).toString());
                        idpasien = cekViaSatuSehat.tampilIDPasien(tbKesadaran.getValueAt(i, 5).toString());
                        try {
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                            json = "{" +
                                        "\"resourceType\": \"Observation\"," +
                                        "\"status\": \"final\"," +
                                        "\"category\": [" +
                                            "{" +
                                                "\"coding\": [" +
                                                    "{" +
                                                        "\"system\": \"http://terminology.hl7.org/CodeSystem/observation-category\"," +
                                                        "\"code\": \"exam\"," +
                                                        "\"display\": \"Exam\"" +
                                                    "}" +
                                                "]" +
                                            "}" +
                                        "]," +
                                        "\"code\": {" +
                                            "\"coding\": [" +
                                                "{" +
                                                    "\"system\": \"http://snomed.info/sct\"," +
                                                    "\"code\": \"1104441000000107\"," +
                                                    "\"display\": \"ACVPU (Alert Confusion Voice Pain Unresponsive) scale score\"" +
                                                "}" +
                                            "]" +
                                        "}," +
                                        "\"subject\": {" +
                                            "\"reference\": \"Patient/"+idpasien+"\"" +
                                        "}," +
                                        "\"performer\": [" +
                                            "{" +
                                                "\"reference\": \"Practitioner/"+iddokter+"\"" +
                                            "}" +
                                        "]," +
                                        "\"encounter\": {" +
                                            "\"reference\": \"Encounter/"+tbKesadaran.getValueAt(i,9).toString()+"\"," +
                                            "\"display\": \"Pemeriksaan Fisik Kesadaran di "+tbKesadaran.getValueAt(i,7).toString().replaceAll("Ralan","Rawat Jalan/IGD").replaceAll("Ranap","Rawat Inap")+", Pasien "+tbKesadaran.getValueAt(i,4).toString()+" Pada Tanggal "+tbKesadaran.getValueAt(i,13).toString()+" Jam "+tbKesadaran.getValueAt(i,14).toString()+"\"" +
                                        "}," +
                                        "\"effectiveDateTime\": \""+tbKesadaran.getValueAt(i,13).toString()+"T"+tbKesadaran.getValueAt(i,14).toString()+"+07:00\"," +
                                        "\"valueCodeableConcept\": {" +
                                            "\"text\": \""+tbKesadaran.getValueAt(i,10).toString().replaceAll("Compos Mentis","Alert").replaceAll("Somnolence","Voice").replaceAll("Sopor","Pain").replaceAll("Coma","Unresponsive")+"\"" +
                                        "}" +
                                   "}";
                            System.out.println("URL : " + link + "/Observation");
                            System.out.println("Request JSON : " + json);
                            requestEntity = new HttpEntity(json, headers);
                            json = api.getRest().exchange(link + "/Observation", HttpMethod.POST, requestEntity, String.class).getBody();
                            System.out.println("Result JSON : " + json);
                            root = mapper.readTree(json);
                            response = root.path("id");
                            if (!response.asText().equals("")) {
                                Sequel.menyimpan("satu_sehat_observationttvkesadaran", "?,?,?,?,?", "Observation Kesadaran", 5, new String[]{
                                    tbKesadaran.getValueAt(i, 2).toString(), tbKesadaran.getValueAt(i, 13).toString(), tbKesadaran.getValueAt(i, 14).toString(), tbKesadaran.getValueAt(i, 7).toString(), response.asText()
                                });
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi Bridging : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
            tampilkesadaran();
        } else if (TabRawat.getSelectedIndex() == 6) {
            for (i = 0; i < tbTensi.getRowCount(); i++) {
                if (tbTensi.getValueAt(i, 0).toString().equals("true") 
                        && (!tbTensi.getValueAt(i, 5).toString().equals("")) 
                        && (!tbTensi.getValueAt(i, 12).toString().equals("")) 
                        && tbTensi.getValueAt(i, 15).toString().equals("")) {
                    try {
                        iddokter = cekViaSatuSehat.tampilIDParktisi(tbTensi.getValueAt(i, 12).toString());
                        idpasien = cekViaSatuSehat.tampilIDPasien(tbTensi.getValueAt(i, 5).toString());
                        arrSplit = tbTensi.getValueAt(i, 10).toString().split("/");
                        sistole = "0";
                        try {
                            if (!arrSplit[0].equals("")) {
                                sistole = arrSplit[0];
                            }
                        } catch (Exception e) {
                            sistole = "0";
                        }
                        diastole = "0";
                        try {
                            if (!arrSplit[1].equals("")) {
                                diastole = arrSplit[1];
                            }
                        } catch (Exception e) {
                            diastole = "0";
                        }
                        try {
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                            json = "{" +
                                        "\"resourceType\": \"Observation\"," +
                                        "\"status\": \"final\"," +
                                        "\"category\": [" +
                                            "{" +
                                                "\"coding\": [" +
                                                    "{" +
                                                        "\"system\": \"http://terminology.hl7.org/CodeSystem/observation-category\"," +
                                                        "\"code\": \"vital-signs\"," +
                                                        "\"display\": \"Vital Signs\"" +
                                                    "}" +
                                                "]" +
                                            "}" +
                                        "]," +
                                        "\"code\": {" +
                                            "\"coding\": [" +
                                                "{" +
                                                    "\"system\": \"http://loinc.org\"," +
                                                    "\"code\": \"35094-2\"," +
                                                    "\"display\": \"Blood pressure panel\"" +
                                                "}" +
                                            "]," +
                                            "\"text\": \"Blood pressure systolic & diastolic\"" +
                                        "}," +
                                        "\"subject\": {" +
                                            "\"reference\": \"Patient/"+idpasien+"\"" +
                                        "}," +
                                        "\"performer\": [" +
                                            "{" +
                                                "\"reference\": \"Practitioner/"+iddokter+"\"" +
                                            "}" +
                                        "]," +
                                        "\"encounter\": {" +
                                            "\"reference\": \"Encounter/"+tbTensi.getValueAt(i,9).toString()+"\"," +
                                            "\"display\": \"Pemeriksaan Fisik Tensi di "+tbTensi.getValueAt(i,7).toString().replaceAll("Ralan","Rawat Jalan/IGD").replaceAll("Ranap","Rawat Inap")+", Pasien "+tbTensi.getValueAt(i,4).toString()+" Pada Tanggal "+tbTensi.getValueAt(i,13).toString()+" Jam "+tbTensi.getValueAt(i,14).toString()+"\"" +
                                        "}," +
                                        "\"effectiveDateTime\": \""+tbTensi.getValueAt(i,13).toString()+"T"+tbTensi.getValueAt(i,14).toString()+"+07:00\"," +
                                        "\"component\" : ["+
                                            "{" +
                                                "\"code\" : {" +
                                                    "\"coding\" : ["+
                                                        "{" +
                                                            "\"system\" : \"http://loinc.org\"," +
                                                            "\"code\" : \"8480-6\"," +
                                                            "\"display\" : \"Systolic blood pressure\"" +
                                                        "}" +
                                                    "]" +
                                                "}," +
                                                "\"valueQuantity\" : {" +
                                                    "\"value\" : "+sistole+"," +
                                                    "\"unit\" : \"mmHg\"," +
                                                    "\"system\" : \"http://unitsofmeasure.org\"," +
                                                    "\"code\" : \"mm[Hg]\"" +
                                                "}" +
                                            "}," +
                                            "{" +
                                                "\"code\" : {" +
                                                    "\"coding\" : ["+
                                                        "{" +
                                                            "\"system\" : \"http://loinc.org\"," +
                                                            "\"code\" : \"8462-4\"," +
                                                            "\"display\" : \"Diastolic blood pressure\"" +
                                                        "}"+
                                                    "]" +
                                                "}," +
                                                "\"valueQuantity\" : {" +
                                                    "\"value\" : "+diastole+"," +
                                                    "\"unit\" : \"mmHg\"," +
                                                    "\"system\" : \"http://unitsofmeasure.org\"," +
                                                    "\"code\" : \"mm[Hg]\"" +
                                                "}" +
                                            "}"+
                                        "]" +
                                   "}";
                            System.out.println("URL : " + link + "/Observation");
                            System.out.println("Request JSON : " + json);
                            requestEntity = new HttpEntity(json, headers);
                            json = api.getRest().exchange(link + "/Observation", HttpMethod.POST, requestEntity, String.class).getBody();
                            System.out.println("Result JSON : " + json);
                            root = mapper.readTree(json);
                            response = root.path("id");
                            if (!response.asText().equals("")) {
                                Sequel.menyimpan("satu_sehat_observationttvtensi", "?,?,?,?,?", "Observation Tensi", 5, new String[]{
                                    tbTensi.getValueAt(i, 2).toString(), tbTensi.getValueAt(i, 13).toString(), tbTensi.getValueAt(i, 14).toString(), tbTensi.getValueAt(i, 7).toString(), response.asText()
                                });
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi Bridging : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
            tampiltensi();
        } else if (TabRawat.getSelectedIndex() == 7) {
            for (i = 0; i < tbTB.getRowCount(); i++) {
                if (tbTB.getValueAt(i, 0).toString().equals("true") 
                        && (!tbTB.getValueAt(i, 5).toString().equals("")) 
                        && (!tbTB.getValueAt(i, 12).toString().equals("")) 
                        && tbTB.getValueAt(i, 15).toString().equals("")) {
                    try {
                        iddokter = cekViaSatuSehat.tampilIDParktisi(tbTB.getValueAt(i, 12).toString());
                        idpasien = cekViaSatuSehat.tampilIDPasien(tbTB.getValueAt(i, 5).toString());
                        try {
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                            json = "{" +
                                        "\"resourceType\": \"Observation\"," +
                                        "\"status\": \"final\"," +
                                        "\"category\": [" +
                                            "{" +
                                                "\"coding\": [" +
                                                    "{" +
                                                        "\"system\": \"http://terminology.hl7.org/CodeSystem/observation-category\"," +
                                                        "\"code\": \"vital-signs\"," +
                                                        "\"display\": \"Vital Signs\"" +
                                                    "}" +
                                                "]" +
                                            "}" +
                                        "]," +
                                        "\"code\": {" +
                                            "\"coding\": [" +
                                                "{" +
                                                    "\"system\": \"http://loinc.org\"," +
                                                    "\"code\": \"8302-2\"," +
                                                    "\"display\": \"Body height\"" +
                                                "}" +
                                            "]" +
                                        "}," +
                                        "\"subject\": {" +
                                            "\"reference\": \"Patient/"+idpasien+"\"" +
                                        "}," +
                                        "\"performer\": [" +
                                            "{" +
                                                "\"reference\": \"Practitioner/"+iddokter+"\"" +
                                            "}" +
                                        "]," +
                                        "\"encounter\": {" +
                                            "\"reference\": \"Encounter/"+tbTB.getValueAt(i,9).toString()+"\"," +
                                            "\"display\": \"Pemeriksaan Fisik Tinggi Badan di "+tbTB.getValueAt(i,7).toString().replaceAll("Ralan","Rawat Jalan/IGD").replaceAll("Ranap","Rawat Inap")+", Pasien "+tbTB.getValueAt(i,4).toString()+" Pada Tanggal "+tbTB.getValueAt(i,13).toString()+" Jam "+tbTB.getValueAt(i,14).toString()+"\"" +
                                        "}," +
                                        "\"effectiveDateTime\": \""+tbTB.getValueAt(i,13).toString()+"T"+tbTB.getValueAt(i,14).toString()+"+07:00\"," +
                                        "\"valueQuantity\": {" +
                                            "\"value\": "+tbTB.getValueAt(i,10).toString().replaceAll(",",".")+"," +
                                            "\"unit\": \"centimeter\"," +
                                            "\"system\": \"http://unitsofmeasure.org\"," +
                                            "\"code\": \"cm\"" +
                                        "}" +
                                   "}";
                            System.out.println("URL : " + link + "/Observation");
                            System.out.println("Request JSON : " + json);
                            requestEntity = new HttpEntity(json, headers);
                            json = api.getRest().exchange(link + "/Observation", HttpMethod.POST, requestEntity, String.class).getBody();
                            System.out.println("Result JSON : " + json);
                            root = mapper.readTree(json);
                            response = root.path("id");
                            if (!response.asText().equals("")) {
                                Sequel.menyimpan("satu_sehat_observationttvtb", "?,?,?,?,?", "Observation TB", 5, new String[]{
                                    tbTB.getValueAt(i, 2).toString(), tbTB.getValueAt(i, 13).toString(), tbTB.getValueAt(i, 14).toString(), tbTB.getValueAt(i, 7).toString(), response.asText()
                                });
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi Bridging : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
            tampiltb();
        } else if (TabRawat.getSelectedIndex() == 8) {
            for (i = 0; i < tbBB.getRowCount(); i++) {
                if (tbBB.getValueAt(i, 0).toString().equals("true") 
                        && (!tbBB.getValueAt(i, 5).toString().equals("")) 
                        && (!tbBB.getValueAt(i, 12).toString().equals("")) 
                        && tbBB.getValueAt(i, 15).toString().equals("")) {
                    try {
                        iddokter = cekViaSatuSehat.tampilIDParktisi(tbBB.getValueAt(i, 12).toString());
                        idpasien = cekViaSatuSehat.tampilIDPasien(tbBB.getValueAt(i, 5).toString());
                        try {
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                            json = "{" +
                                        "\"resourceType\": \"Observation\"," +
                                        "\"status\": \"final\"," +
                                        "\"category\": [" +
                                            "{" +
                                                "\"coding\": [" +
                                                    "{" +
                                                        "\"system\": \"http://terminology.hl7.org/CodeSystem/observation-category\"," +
                                                        "\"code\": \"vital-signs\"," +
                                                        "\"display\": \"Vital Signs\"" +
                                                    "}" +
                                                "]" +
                                            "}" +
                                        "]," +
                                        "\"code\": {" +
                                            "\"coding\": [" +
                                                "{" +
                                                    "\"system\": \"http://loinc.org\"," +
                                                    "\"code\": \"29463-7\"," +
                                                    "\"display\": \"Body Weight\"" +
                                                "}" +
                                            "]" +
                                        "}," +
                                        "\"subject\": {" +
                                            "\"reference\": \"Patient/"+idpasien+"\"" +
                                        "}," +
                                        "\"performer\": [" +
                                            "{" +
                                                "\"reference\": \"Practitioner/"+iddokter+"\"" +
                                            "}" +
                                        "]," +
                                        "\"encounter\": {" +
                                            "\"reference\": \"Encounter/"+tbBB.getValueAt(i,9).toString()+"\"," +
                                            "\"display\": \"Pemeriksaan Fisik Berat Badan di "+tbBB.getValueAt(i,7).toString().replaceAll("Ralan","Rawat Jalan/IGD").replaceAll("Ranap","Rawat Inap")+", Pasien "+tbBB.getValueAt(i,4).toString()+" Pada Tanggal "+tbBB.getValueAt(i,13).toString()+" Jam "+tbBB.getValueAt(i,14).toString()+"\"" +
                                        "}," +
                                        "\"effectiveDateTime\": \""+tbBB.getValueAt(i,13).toString()+"T"+tbBB.getValueAt(i,14).toString()+"+07:00\"," +
                                        "\"valueQuantity\": {" +
                                            "\"value\": "+tbBB.getValueAt(i,10).toString().replaceAll(",",".")+"," +
                                            "\"unit\": \"kilogram\"," +
                                            "\"system\": \"http://unitsofmeasure.org\"," +
                                            "\"code\": \"kg\"" +
                                        "}" +
                                   "}";
                            System.out.println("URL : " + link + "/Observation");
                            System.out.println("Request JSON : " + json);
                            requestEntity = new HttpEntity(json, headers);
                            json = api.getRest().exchange(link + "/Observation", HttpMethod.POST, requestEntity, String.class).getBody();
                            System.out.println("Result JSON : " + json);
                            root = mapper.readTree(json);
                            response = root.path("id");
                            if (!response.asText().equals("")) {
                                Sequel.menyimpan("satu_sehat_observationttvbb", "?,?,?,?,?", "Observation BB", 5, new String[]{
                                    tbBB.getValueAt(i, 2).toString(), tbBB.getValueAt(i, 13).toString(), tbBB.getValueAt(i, 14).toString(), tbBB.getValueAt(i, 7).toString(), response.asText()
                                });
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi Bridging : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
            tampilbb();
        } else if (TabRawat.getSelectedIndex() == 9) {
            for (i = 0; i < tbLP.getRowCount(); i++) {
                if (tbLP.getValueAt(i, 0).toString().equals("true") 
                        && (!tbLP.getValueAt(i, 5).toString().equals("")) 
                        && (!tbLP.getValueAt(i, 12).toString().equals("")) 
                        && tbLP.getValueAt(i, 15).toString().equals("")) {
                    try {
                        iddokter = cekViaSatuSehat.tampilIDParktisi(tbLP.getValueAt(i, 12).toString());
                        idpasien = cekViaSatuSehat.tampilIDPasien(tbLP.getValueAt(i, 5).toString());
                        try {
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                            json = "{" +
                                        "\"resourceType\": \"Observation\"," +
                                        "\"status\": \"final\"," +
                                        "\"category\": [" +
                                            "{" +
                                                "\"coding\": [" +
                                                    "{" +
                                                        "\"system\": \"http://terminology.hl7.org/CodeSystem/observation-category\"," +
                                                        "\"code\": \"vital-signs\"," +
                                                        "\"display\": \"Vital Signs\"" +
                                                    "}" +
                                                "]" +
                                            "}" +
                                        "]," +
                                        "\"code\": {" +
                                            "\"coding\": [" +
                                                "{" +
                                                    "\"system\": \"http://loinc.org\"," +
                                                    "\"code\": \"8280-0\"," +
                                                    "\"display\": \"Waist Circumference at umbilicus by Tape measure\"" +
                                                "}" +
                                            "]" +
                                        "}," +
                                        "\"subject\": {" +
                                            "\"reference\": \"Patient/"+idpasien+"\"" +
                                        "}," +
                                        "\"performer\": [" +
                                            "{" +
                                                "\"reference\": \"Practitioner/"+iddokter+"\"" +
                                            "}" +
                                        "]," +
                                        "\"encounter\": {" +
                                            "\"reference\": \"Encounter/"+tbLP.getValueAt(i,9).toString()+"\"," +
                                            "\"display\": \"Pemeriksaan Fisik Lingkar Perut di "+tbLP.getValueAt(i,7).toString().replaceAll("Ralan","Rawat Jalan/IGD").replaceAll("Ranap","Rawat Inap")+", Pasien "+tbLP.getValueAt(i,4).toString()+" Pada Tanggal "+tbLP.getValueAt(i,13).toString()+" Jam "+tbLP.getValueAt(i,14).toString()+"\"" +
                                        "}," +
                                        "\"effectiveDateTime\": \""+tbLP.getValueAt(i,13).toString()+"T"+tbLP.getValueAt(i,14).toString()+"+07:00\"," +
                                        "\"valueQuantity\": {" +
                                            "\"value\": "+tbLP.getValueAt(i,10).toString().replaceAll(",",".")+"," +
                                            "\"unit\": \"centimeter\"," +
                                            "\"system\": \"http://unitsofmeasure.org\"," +
                                            "\"code\": \"cm\"" +
                                        "}" +
                                   "}";
                            System.out.println("URL : " + link + "/Observation");
                            System.out.println("Request JSON : " + json);
                            requestEntity = new HttpEntity(json, headers);
                            json = api.getRest().exchange(link + "/Observation", HttpMethod.POST, requestEntity, String.class).getBody();
                            System.out.println("Result JSON : " + json);
                            root = mapper.readTree(json);
                            response = root.path("id");
                            if (!response.asText().equals("")) {
                                Sequel.menyimpan("satu_sehat_observationttvlp", "?,?,?,?,?", "Observation LP", 5, new String[]{
                                    tbLP.getValueAt(i, 2).toString(), tbLP.getValueAt(i, 13).toString(), tbLP.getValueAt(i, 14).toString(), tbLP.getValueAt(i, 7).toString(), response.asText()
                                });
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi Bridging : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
            tampillp();
        }
    }//GEN-LAST:event_BtnKirimActionPerformed

    private void ppPilihSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPilihSemuaActionPerformed
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                for (i = 0; i < tbSuhu.getRowCount(); i++) {                    
                    tbSuhu.setValueAt(true, i, 0);
                }
                break;
            case 1:
                for (i = 0; i < tbRespirasi.getRowCount(); i++) {
                    tbRespirasi.setValueAt(true, i, 0);
                }
                break;
            case 2:
                for (i = 0; i < tbNadi.getRowCount(); i++) {
                    tbNadi.setValueAt(true, i, 0);
                }
                break;
            case 3:
                for (i = 0; i < tbSpO2.getRowCount(); i++) {
                    tbSpO2.setValueAt(true, i, 0);
                }
                break;
            case 4:
                for (i = 0; i < tbGCS.getRowCount(); i++) {
                    tbGCS.setValueAt(true, i, 0);
                }
                break;
            case 5:
                for (i = 0; i < tbKesadaran.getRowCount(); i++) {
                    tbKesadaran.setValueAt(true, i, 0);
                }
                break;
            case 6:
                for (i = 0; i < tbTensi.getRowCount(); i++) {
                    tbTensi.setValueAt(true, i, 0);
                }
                break;
            case 7:
                for (i = 0; i < tbTB.getRowCount(); i++) {
                    tbTB.setValueAt(true, i, 0);
                }
                break;
            case 8:
                for (i = 0; i < tbBB.getRowCount(); i++) {
                    tbBB.setValueAt(true, i, 0);
                }
                break;
            case 9:
                for (i = 0; i < tbLP.getRowCount(); i++) {
                    tbLP.setValueAt(true, i, 0);
                }
                break;
            default:
                break;
        }
    }//GEN-LAST:event_ppPilihSemuaActionPerformed

    private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                for (i = 0; i < tbSuhu.getRowCount(); i++) {
                    tbSuhu.setValueAt(false, i, 0);
                }
                break;
            case 1:
                for (i = 0; i < tbRespirasi.getRowCount(); i++) {
                    tbRespirasi.setValueAt(false, i, 0);
                }
                break;
            case 2:
                for (i = 0; i < tbNadi.getRowCount(); i++) {
                    tbNadi.setValueAt(false, i, 0);
                }
                break;
            case 3:
                for (i = 0; i < tbSpO2.getRowCount(); i++) {
                    tbSpO2.setValueAt(false, i, 0);
                }
                break;
            case 4:
                for (i = 0; i < tbGCS.getRowCount(); i++) {
                    tbGCS.setValueAt(false, i, 0);
                }
                break;
            case 5:
                for (i = 0; i < tbKesadaran.getRowCount(); i++) {
                    tbKesadaran.setValueAt(false, i, 0);
                }
                break;
            case 6:
                for (i = 0; i < tbTensi.getRowCount(); i++) {
                    tbTensi.setValueAt(false, i, 0);
                }
                break;
            case 7:
                for (i = 0; i < tbTB.getRowCount(); i++) {
                    tbTB.setValueAt(false, i, 0);
                }
                break;
            case 8:
                for (i = 0; i < tbBB.getRowCount(); i++) {
                    tbBB.setValueAt(false, i, 0);
                }
                break;
            case 9:
                for (i = 0; i < tbLP.getRowCount(); i++) {
                    tbLP.setValueAt(false, i, 0);
                }
                break;
            default:
                break;
        }
    }//GEN-LAST:event_ppBersihkanActionPerformed

    private void BtnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUpdateActionPerformed
        if (TabRawat.getSelectedIndex() == 0) {
            for (i = 0; i < tbSuhu.getRowCount(); i++) {
                if (tbSuhu.getValueAt(i, 0).toString().equals("true") 
                        && (!tbSuhu.getValueAt(i, 5).toString().equals("")) 
                        && (!tbSuhu.getValueAt(i, 12).toString().equals("")) 
                        && (!tbSuhu.getValueAt(i, 15).toString().equals(""))) {
                    try {
                        iddokter = cekViaSatuSehat.tampilIDParktisi(tbSuhu.getValueAt(i, 12).toString());
                        idpasien = cekViaSatuSehat.tampilIDPasien(tbSuhu.getValueAt(i, 5).toString());
                        try {
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                            json = "{" +
                                        "\"resourceType\": \"Observation\"," +
                                        "\"id\": \""+tbSuhu.getValueAt(i,15).toString()+"\"," +
                                        "\"status\": \"final\"," +
                                        "\"category\": [" +
                                            "{" +
                                                "\"coding\": [" +
                                                    "{" +
                                                        "\"system\": \"http://terminology.hl7.org/CodeSystem/observation-category\"," +
                                                        "\"code\": \"vital-signs\"," +
                                                        "\"display\": \"Vital Signs\"" +
                                                    "}" +
                                                "]" +
                                            "}" +
                                        "]," +
                                        "\"code\": {" +
                                            "\"coding\": [" +
                                                "{" +
                                                    "\"system\": \"http://loinc.org\"," +
                                                    "\"code\": \"8310-5\"," +
                                                    "\"display\": \"Body temperature\"" +
                                                "}" +
                                            "]" +
                                        "}," +
                                        "\"subject\": {" +
                                            "\"reference\": \"Patient/"+idpasien+"\"" +
                                        "}," +
                                        "\"performer\": [" +
                                            "{" +
                                                "\"reference\": \"Practitioner/"+iddokter+"\"" +
                                            "}" +
                                        "]," +
                                        "\"encounter\": {" +
                                            "\"reference\": \"Encounter/"+tbSuhu.getValueAt(i,9).toString()+"\"," +
                                            "\"display\": \"Pemeriksaan Fisik Suhu Badan di "+tbSuhu.getValueAt(i,7).toString().replaceAll("Ralan","Rawat Jalan/IGD").replaceAll("Ranap","Rawat Inap")+", Pasien "+tbSuhu.getValueAt(i,4).toString()+" Pada Tanggal "+tbSuhu.getValueAt(i,13).toString()+" Jam "+tbSuhu.getValueAt(i,14).toString()+"\"" +
                                        "}," +
                                        "\"effectiveDateTime\": \""+tbSuhu.getValueAt(i,13).toString()+"T"+tbSuhu.getValueAt(i,14).toString()+"+07:00\"," +
                                        "\"valueQuantity\": {" +
                                            "\"value\": "+tbSuhu.getValueAt(i,10).toString().replaceAll(",",".")+"," +
                                            "\"unit\": \"degree Celsius\"," +
                                            "\"system\": \"http://unitsofmeasure.org\"," +
                                            "\"code\": \"Cel\"" +
                                        "}" +
                                   "}";
                            System.out.println("URL : " + link + "/Observation/" + tbSuhu.getValueAt(i, 15).toString());
                            System.out.println("Request JSON : " + json);
                            requestEntity = new HttpEntity(json, headers);
                            json = api.getRest().exchange(link + "/Observation/" + tbSuhu.getValueAt(i, 15).toString(), HttpMethod.PUT, requestEntity, String.class).getBody();
                            System.out.println("Result JSON : " + json);
                        } catch (Exception e) {
                            System.out.println("Notifikasi Bridging : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
            tampilsuhu();
        } else if (TabRawat.getSelectedIndex() == 1) {
            for (i = 0; i < tbRespirasi.getRowCount(); i++) {
                if (tbRespirasi.getValueAt(i, 0).toString().equals("true") 
                        && (!tbRespirasi.getValueAt(i, 5).toString().equals("")) 
                        && (!tbRespirasi.getValueAt(i, 12).toString().equals("")) 
                        && (!tbRespirasi.getValueAt(i, 15).toString().equals(""))) {
                    try {
                        iddokter = cekViaSatuSehat.tampilIDParktisi(tbRespirasi.getValueAt(i, 12).toString());
                        idpasien = cekViaSatuSehat.tampilIDPasien(tbRespirasi.getValueAt(i, 5).toString());
                        try {
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                            json = "{" +
                                        "\"resourceType\": \"Observation\"," +
                                        "\"id\": \""+tbRespirasi.getValueAt(i,15).toString()+"\"," +
                                        "\"status\": \"final\"," +
                                        "\"category\": [" +
                                            "{" +
                                                "\"coding\": [" +
                                                    "{" +
                                                        "\"system\": \"http://terminology.hl7.org/CodeSystem/observation-category\"," +
                                                        "\"code\": \"vital-signs\"," +
                                                        "\"display\": \"Vital Signs\"" +
                                                    "}" +
                                                "]" +
                                            "}" +
                                        "]," +
                                        "\"code\": {" +
                                            "\"coding\": [" +
                                                "{" +
                                                    "\"system\": \"http://loinc.org\"," +
                                                    "\"code\": \"9279-1\"," +
                                                    "\"display\": \"Respiratory rate\"" +
                                                "}" +
                                            "]" +
                                        "}," +
                                        "\"subject\": {" +
                                            "\"reference\": \"Patient/"+idpasien+"\"" +
                                        "}," +
                                        "\"performer\": [" +
                                            "{" +
                                                "\"reference\": \"Practitioner/"+iddokter+"\"" +
                                            "}" +
                                        "]," +
                                        "\"encounter\": {" +
                                            "\"reference\": \"Encounter/"+tbRespirasi.getValueAt(i,9).toString()+"\"," +
                                            "\"display\": \"Pemeriksaan Fisik Respirasi di "+tbRespirasi.getValueAt(i,7).toString().replaceAll("Ralan","Rawat Jalan/IGD").replaceAll("Ranap","Rawat Inap")+", Pasien "+tbRespirasi.getValueAt(i,4).toString()+" Pada Tanggal "+tbRespirasi.getValueAt(i,13).toString()+" Jam "+tbRespirasi.getValueAt(i,14).toString()+"\"" +
                                        "}," +
                                        "\"effectiveDateTime\": \""+tbRespirasi.getValueAt(i,13).toString()+"T"+tbRespirasi.getValueAt(i,14).toString()+"+07:00\"," +
                                        "\"valueQuantity\": {" +
                                            "\"value\": "+tbRespirasi.getValueAt(i,10).toString().replaceAll(",",".")+"," +
                                            "\"unit\": \"breaths/minute\"," +
                                            "\"system\": \"http://unitsofmeasure.org\"," +
                                            "\"code\": \"/min\"" +
                                        "}" +
                                   "}";
                            System.out.println("URL : " + link + "/Observation/" + tbRespirasi.getValueAt(i, 15).toString());
                            System.out.println("Request JSON : " + json);
                            requestEntity = new HttpEntity(json, headers);
                            json = api.getRest().exchange(link + "/Observation/" + tbRespirasi.getValueAt(i, 15).toString(), HttpMethod.PUT, requestEntity, String.class).getBody();
                            System.out.println("Result JSON : " + json);
                        } catch (Exception e) {
                            System.out.println("Notifikasi Bridging : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
            tampilrespirasi();
        } else if (TabRawat.getSelectedIndex() == 2) {
            for (i = 0; i < tbNadi.getRowCount(); i++) {
                if (tbNadi.getValueAt(i, 0).toString().equals("true") 
                        && (!tbNadi.getValueAt(i, 5).toString().equals("")) 
                        && (!tbNadi.getValueAt(i, 12).toString().equals("")) 
                        && (!tbNadi.getValueAt(i, 15).toString().equals(""))) {
                    try {
                        iddokter = cekViaSatuSehat.tampilIDParktisi(tbNadi.getValueAt(i, 12).toString());
                        idpasien = cekViaSatuSehat.tampilIDPasien(tbNadi.getValueAt(i, 5).toString());
                        try {
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                            json = "{" +
                                        "\"resourceType\": \"Observation\"," +
                                        "\"id\": \""+tbNadi.getValueAt(i,15).toString()+"\"," +
                                        "\"status\": \"final\"," +
                                        "\"category\": [" +
                                            "{" +
                                                "\"coding\": [" +
                                                    "{" +
                                                        "\"system\": \"http://terminology.hl7.org/CodeSystem/observation-category\"," +
                                                        "\"code\": \"vital-signs\"," +
                                                        "\"display\": \"Vital Signs\"" +
                                                    "}" +
                                                "]" +
                                            "}" +
                                        "]," +
                                        "\"code\": {" +
                                            "\"coding\": [" +
                                                "{" +
                                                    "\"system\": \"http://loinc.org\"," +
                                                    "\"code\": \"8867-4\"," +
                                                    "\"display\": \"Heart rate\"" +
                                                "}" +
                                            "]" +
                                        "}," +
                                        "\"subject\": {" +
                                            "\"reference\": \"Patient/"+idpasien+"\"" +
                                        "}," +
                                        "\"performer\": [" +
                                            "{" +
                                                "\"reference\": \"Practitioner/"+iddokter+"\"" +
                                            "}" +
                                        "]," +
                                        "\"encounter\": {" +
                                            "\"reference\": \"Encounter/"+tbNadi.getValueAt(i,9).toString()+"\"," +
                                            "\"display\": \"Pemeriksaan Fisik Nadi di "+tbNadi.getValueAt(i,7).toString().replaceAll("Ralan","Rawat Jalan/IGD").replaceAll("Ranap","Rawat Inap")+", Pasien "+tbNadi.getValueAt(i,4).toString()+" Pada Tanggal "+tbNadi.getValueAt(i,13).toString()+" Jam "+tbNadi.getValueAt(i,14).toString()+"\"" +
                                        "}," +
                                        "\"effectiveDateTime\": \""+tbNadi.getValueAt(i,13).toString()+"T"+tbNadi.getValueAt(i,14).toString()+"+07:00\"," +
                                        "\"valueQuantity\": {" +
                                            "\"value\": "+tbNadi.getValueAt(i,10).toString().replaceAll(",",".")+"," +
                                            "\"unit\": \"breaths/minute\"," +
                                            "\"system\": \"http://unitsofmeasure.org\"," +
                                            "\"code\": \"/min\"" +
                                        "}" +
                                   "}";
                            System.out.println("URL : " + link + "/Observation/" + tbNadi.getValueAt(i, 15).toString());
                            System.out.println("Request JSON : " + json);
                            requestEntity = new HttpEntity(json, headers);
                            json = api.getRest().exchange(link + "/Observation/" + tbNadi.getValueAt(i, 15).toString(), HttpMethod.PUT, requestEntity, String.class).getBody();
                            System.out.println("Result JSON : " + json);
                        } catch (Exception e) {
                            System.out.println("Notifikasi Bridging : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
            tampilnadi();
        } else if (TabRawat.getSelectedIndex() == 3) {
            for (i = 0; i < tbSpO2.getRowCount(); i++) {
                if (tbSpO2.getValueAt(i, 0).toString().equals("true") 
                        && (!tbSpO2.getValueAt(i, 5).toString().equals("")) 
                        && (!tbSpO2.getValueAt(i, 12).toString().equals("")) 
                        && (!tbSpO2.getValueAt(i, 15).toString().equals(""))) {
                    try {
                        iddokter = cekViaSatuSehat.tampilIDParktisi(tbSpO2.getValueAt(i, 12).toString());
                        idpasien = cekViaSatuSehat.tampilIDPasien(tbSpO2.getValueAt(i, 5).toString());
                        try {
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                            json = "{" +
                                        "\"resourceType\": \"Observation\"," +
                                        "\"id\": \""+tbSpO2.getValueAt(i,15).toString()+"\"," +
                                        "\"status\": \"final\"," +
                                        "\"category\": [" +
                                            "{" +
                                                "\"coding\": [" +
                                                    "{" +
                                                        "\"system\": \"http://terminology.hl7.org/CodeSystem/observation-category\"," +
                                                        "\"code\": \"vital-signs\"," +
                                                        "\"display\": \"Vital Signs\"" +
                                                    "}" +
                                                "]" +
                                            "}" +
                                        "]," +
                                        "\"code\": {" +
                                            "\"coding\": [" +
                                                "{" +
                                                    "\"system\": \"http://loinc.org\"," +
                                                    "\"code\": \"59408-5\"," +
                                                    "\"display\": \"Oxygen saturation\"" +
                                                "}" +
                                            "]" +
                                        "}," +
                                        "\"subject\": {" +
                                            "\"reference\": \"Patient/"+idpasien+"\"" +
                                        "}," +
                                        "\"performer\": [" +
                                            "{" +
                                                "\"reference\": \"Practitioner/"+iddokter+"\"" +
                                            "}" +
                                        "]," +
                                        "\"encounter\": {" +
                                            "\"reference\": \"Encounter/"+tbSpO2.getValueAt(i,9).toString()+"\"," +
                                            "\"display\": \"Pemeriksaan Fisik SpO2 di "+tbSpO2.getValueAt(i,7).toString().replaceAll("Ralan","Rawat Jalan/IGD").replaceAll("Ranap","Rawat Inap")+", Pasien "+tbSpO2.getValueAt(i,4).toString()+" Pada Tanggal "+tbSpO2.getValueAt(i,13).toString()+" Jam "+tbSpO2.getValueAt(i,14).toString()+"\"" +
                                        "}," +
                                        "\"effectiveDateTime\": \""+tbSpO2.getValueAt(i,13).toString()+"T"+tbSpO2.getValueAt(i,14).toString()+"+07:00\"," +
                                        "\"valueQuantity\": {" +
                                            "\"value\": "+tbSpO2.getValueAt(i,10).toString().replaceAll(",",".")+"," +
                                            "\"unit\": \"percent saturation\"," +
                                            "\"system\": \"http://unitsofmeasure.org\"," +
                                            "\"code\": \"%\"" +
                                        "}" +
                                   "}";
                            System.out.println("URL : " + link + "/Observation/" + tbSpO2.getValueAt(i, 15).toString());
                            System.out.println("Request JSON : " + json);
                            requestEntity = new HttpEntity(json, headers);
                            json = api.getRest().exchange(link + "/Observation/" + tbSpO2.getValueAt(i, 15).toString(), HttpMethod.PUT, requestEntity, String.class).getBody();
                            System.out.println("Result JSON : " + json);
                        } catch (Exception e) {
                            System.out.println("Notifikasi Bridging : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
            tampilspo2();
        } else if (TabRawat.getSelectedIndex() == 4) {
            for (i = 0; i < tbGCS.getRowCount(); i++) {
                if (tbGCS.getValueAt(i, 0).toString().equals("true") 
                        && (!tbGCS.getValueAt(i, 5).toString().equals("")) 
                        && (!tbGCS.getValueAt(i, 12).toString().equals("")) 
                        && (!tbGCS.getValueAt(i, 15).toString().equals(""))) {
                    try {
                        iddokter = cekViaSatuSehat.tampilIDParktisi(tbGCS.getValueAt(i, 12).toString());
                        idpasien = cekViaSatuSehat.tampilIDPasien(tbGCS.getValueAt(i, 5).toString());
                        try {
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                            json = "{" +
                                        "\"resourceType\": \"Observation\"," +
                                        "\"id\": \""+tbGCS.getValueAt(i,15).toString()+"\"," +
                                        "\"status\": \"final\"," +
                                        "\"category\": [" +
                                            "{" +
                                                "\"coding\": [" +
                                                    "{" +
                                                        "\"system\": \"http://terminology.hl7.org/CodeSystem/observation-category\"," +
                                                        "\"code\": \"vital-signs\"," +
                                                        "\"display\": \"Vital Signs\"" +
                                                    "}" +
                                                "]" +
                                            "}" +
                                        "]," +
                                        "\"code\": {" +
                                            "\"coding\": [" +
                                                "{" +
                                                    "\"system\": \"http://loinc.org\"," +
                                                    "\"code\": \"9269-2\"," +
                                                    "\"display\": \"Glasgow coma score total\"" +
                                                "}" +
                                            "]" +
                                        "}," +
                                        "\"subject\": {" +
                                            "\"reference\": \"Patient/"+idpasien+"\"" +
                                        "}," +
                                        "\"performer\": [" +
                                            "{" +
                                                "\"reference\": \"Practitioner/"+iddokter+"\"" +
                                            "}" +
                                        "]," +
                                        "\"encounter\": {" +
                                            "\"reference\": \"Encounter/"+tbGCS.getValueAt(i,9).toString()+"\"," +
                                            "\"display\": \"Pemeriksaan Fisik GCS di "+tbGCS.getValueAt(i,7).toString().replaceAll("Ralan","Rawat Jalan/IGD").replaceAll("Ranap","Rawat Inap")+", Pasien "+tbGCS.getValueAt(i,4).toString()+" Pada Tanggal "+tbGCS.getValueAt(i,13).toString()+" Jam "+tbGCS.getValueAt(i,14).toString()+"\"" +
                                        "}," +
                                        "\"effectiveDateTime\": \""+tbGCS.getValueAt(i,13).toString()+"T"+tbGCS.getValueAt(i,14).toString()+"+07:00\"," +
                                        "\"valueQuantity\": {" +
                                            "\"value\": "+tbGCS.getValueAt(i,10).toString().replaceAll(",",".")+"," +
                                            "\"system\": \"http://unitsofmeasure.org\"," +
                                            "\"code\": \"{score}\"" +
                                        "}" +
                                   "}";
                            System.out.println("URL : " + link + "/Observation/" + tbGCS.getValueAt(i, 15).toString());
                            System.out.println("Request JSON : " + json);
                            requestEntity = new HttpEntity(json, headers);
                            json = api.getRest().exchange(link + "/Observation/" + tbGCS.getValueAt(i, 15).toString(), HttpMethod.PUT, requestEntity, String.class).getBody();
                            System.out.println("Result JSON : " + json);
                        } catch (Exception e) {
                            System.out.println("Notifikasi Bridging : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
            tampilgcs();
        } else if (TabRawat.getSelectedIndex() == 5) {
            for (i = 0; i < tbKesadaran.getRowCount(); i++) {
                if (tbKesadaran.getValueAt(i, 0).toString().equals("true") 
                        && (!tbKesadaran.getValueAt(i, 5).toString().equals("")) 
                        && (!tbKesadaran.getValueAt(i, 12).toString().equals("")) 
                        && (!tbKesadaran.getValueAt(i, 15).toString().equals(""))) {
                    try {
                        iddokter = cekViaSatuSehat.tampilIDParktisi(tbKesadaran.getValueAt(i, 12).toString());
                        idpasien = cekViaSatuSehat.tampilIDPasien(tbKesadaran.getValueAt(i, 5).toString());
                        try {
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                            json = "{" +
                                        "\"resourceType\": \"Observation\"," +
                                        "\"id\": \""+tbKesadaran.getValueAt(i,15).toString()+"\"," +
                                        "\"status\": \"final\"," +
                                        "\"category\": [" +
                                            "{" +
                                                "\"coding\": [" +
                                                    "{" +
                                                        "\"system\": \"http://terminology.hl7.org/CodeSystem/observation-category\"," +
                                                        "\"code\": \"exam\"," +
                                                        "\"display\": \"Exam\"" +
                                                    "}" +
                                                "]" +
                                            "}" +
                                        "]," +
                                        "\"code\": {" +
                                            "\"coding\": [" +
                                                "{" +
                                                    "\"system\": \"http://snomed.info/sct\"," +
                                                    "\"code\": \"1104441000000107\"," +
                                                    "\"display\": \"ACVPU (Alert Confusion Voice Pain Unresponsive) scale score\"" +
                                                "}" +
                                            "]" +
                                        "}," +
                                        "\"subject\": {" +
                                            "\"reference\": \"Patient/"+idpasien+"\"" +
                                        "}," +
                                        "\"performer\": [" +
                                            "{" +
                                                "\"reference\": \"Practitioner/"+iddokter+"\"" +
                                            "}" +
                                        "]," +
                                        "\"encounter\": {" +
                                            "\"reference\": \"Encounter/"+tbKesadaran.getValueAt(i,9).toString()+"\"," +
                                            "\"display\": \"Pemeriksaan Fisik Kesadaran di "+tbKesadaran.getValueAt(i,7).toString().replaceAll("Ralan","Rawat Jalan/IGD").replaceAll("Ranap","Rawat Inap")+", Pasien "+tbKesadaran.getValueAt(i,4).toString()+" Pada Tanggal "+tbKesadaran.getValueAt(i,13).toString()+" Jam "+tbKesadaran.getValueAt(i,14).toString()+"\"" +
                                        "}," +
                                        "\"effectiveDateTime\": \""+tbKesadaran.getValueAt(i,13).toString()+"T"+tbKesadaran.getValueAt(i,14).toString()+"+07:00\"," +
                                        "\"valueCodeableConcept\": {" +
                                            "\"text\": \""+tbKesadaran.getValueAt(i,10).toString().replaceAll("Compos Mentis","Alert").replaceAll("Somnolence","Voice").replaceAll("Sopor","Pain").replaceAll("Coma","Unresponsive")+"\"" +
                                        "}" +
                                   "}";
                            System.out.println("URL : " + link + "/Observation/" + tbKesadaran.getValueAt(i, 15).toString());
                            System.out.println("Request JSON : " + json);
                            requestEntity = new HttpEntity(json, headers);
                            json = api.getRest().exchange(link + "/Observation/" + tbKesadaran.getValueAt(i, 15).toString(), HttpMethod.PUT, requestEntity, String.class).getBody();
                            System.out.println("Result JSON : " + json);
                        } catch (Exception e) {
                            System.out.println("Notifikasi Bridging : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
            tampilkesadaran();
        } else if (TabRawat.getSelectedIndex() == 6) {
            for (i = 0; i < tbTensi.getRowCount(); i++) {
                if (tbTensi.getValueAt(i, 0).toString().equals("true") 
                        && (!tbTensi.getValueAt(i, 5).toString().equals("")) 
                        && (!tbTensi.getValueAt(i, 12).toString().equals("")) 
                        && (!tbTensi.getValueAt(i, 15).toString().equals(""))) {
                    try {
                        iddokter = cekViaSatuSehat.tampilIDParktisi(tbTensi.getValueAt(i, 12).toString());
                        idpasien = cekViaSatuSehat.tampilIDPasien(tbTensi.getValueAt(i, 5).toString());
                        try {
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                            json = "{" +
                                        "\"resourceType\": \"Observation\"," +
                                        "\"id\": \""+tbTensi.getValueAt(i,15).toString()+"\"," +
                                        "\"status\": \"final\"," +
                                        "\"category\": [" +
                                            "{" +
                                                "\"coding\": [" +
                                                    "{" +
                                                        "\"system\": \"http://terminology.hl7.org/CodeSystem/observation-category\"," +
                                                        "\"code\": \"vital-signs\"," +
                                                        "\"display\": \"Vital Signs\"" +
                                                    "}" +
                                                "]" +
                                            "}" +
                                        "]," +
                                        "\"code\": {" +
                                            "\"coding\": [" +
                                                "{" +
                                                    "\"system\": \"http://loinc.org\"," +
                                                    "\"code\": \"35094-2\"," +
                                                    "\"display\": \"Blood pressure panel\"" +
                                                "}" +
                                            "]," +
                                            "\"text\": \"Blood pressure systolic & diastolic\"" +
                                        "}," +
                                        "\"subject\": {" +
                                            "\"reference\": \"Patient/"+idpasien+"\"" +
                                        "}," +
                                        "\"performer\": [" +
                                            "{" +
                                                "\"reference\": \"Practitioner/"+iddokter+"\"" +
                                            "}" +
                                        "]," +
                                        "\"encounter\": {" +
                                            "\"reference\": \"Encounter/"+tbTensi.getValueAt(i,9).toString()+"\"," +
                                            "\"display\": \"Pemeriksaan Fisik Tensi di "+tbTensi.getValueAt(i,7).toString().replaceAll("Ralan","Rawat Jalan/IGD").replaceAll("Ranap","Rawat Inap")+", Pasien "+tbTensi.getValueAt(i,4).toString()+" Pada Tanggal "+tbTensi.getValueAt(i,13).toString()+" Jam "+tbTensi.getValueAt(i,14).toString()+"\"" +
                                        "}," +
                                        "\"effectiveDateTime\": \""+tbTensi.getValueAt(i,13).toString()+"T"+tbTensi.getValueAt(i,14).toString()+"+07:00\"," +
                                        "\"component\" : ["+
                                            "{" +
                                                "\"code\" : {" +
                                                    "\"coding\" : ["+
                                                        "{" +
                                                            "\"system\" : \"http://loinc.org\"," +
                                                            "\"code\" : \"8480-6\"," +
                                                            "\"display\" : \"Systolic blood pressure\"" +
                                                        "}" +
                                                    "]" +
                                                "}," +
                                                "\"valueQuantity\" : {" +
                                                    "\"value\" : "+sistole+"," +
                                                    "\"unit\" : \"mmHg\"," +
                                                    "\"system\" : \"http://unitsofmeasure.org\"," +
                                                    "\"code\" : \"mm[Hg]\"" +
                                                "}" +
                                            "}," +
                                            "{" +
                                                "\"code\" : {" +
                                                    "\"coding\" : ["+
                                                        "{" +
                                                            "\"system\" : \"http://loinc.org\"," +
                                                            "\"code\" : \"8462-4\"," +
                                                            "\"display\" : \"Diastolic blood pressure\"" +
                                                        "}"+
                                                    "]" +
                                                "}," +
                                                "\"valueQuantity\" : {" +
                                                    "\"value\" : "+diastole+"," +
                                                    "\"unit\" : \"mmHg\"," +
                                                    "\"system\" : \"http://unitsofmeasure.org\"," +
                                                    "\"code\" : \"mm[Hg]\"" +
                                                "}" +
                                            "}"+
                                        "]" +
                                   "}";
                            System.out.println("URL : " + link + "/Observation/" + tbTensi.getValueAt(i, 15).toString());
                            System.out.println("Request JSON : " + json);
                            requestEntity = new HttpEntity(json, headers);
                            json = api.getRest().exchange(link + "/Observation/" + tbTensi.getValueAt(i, 15).toString(), HttpMethod.PUT, requestEntity, String.class).getBody();
                            System.out.println("Result JSON : " + json);
                        } catch (Exception e) {
                            System.out.println("Notifikasi Bridging : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
            tampiltensi();
        } else if (TabRawat.getSelectedIndex() == 7) {
            for (i = 0; i < tbTB.getRowCount(); i++) {
                if (tbTB.getValueAt(i, 0).toString().equals("true") 
                        && (!tbTB.getValueAt(i, 5).toString().equals("")) 
                        && (!tbTB.getValueAt(i, 12).toString().equals("")) 
                        && (!tbTB.getValueAt(i, 15).toString().equals(""))) {
                    try {
                        iddokter = cekViaSatuSehat.tampilIDParktisi(tbTB.getValueAt(i, 12).toString());
                        idpasien = cekViaSatuSehat.tampilIDPasien(tbTB.getValueAt(i, 5).toString());
                        try {
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                            json = "{" +
                                        "\"resourceType\": \"Observation\"," +
                                        "\"id\": \""+tbTB.getValueAt(i,15).toString()+"\"," +
                                        "\"status\": \"final\"," +
                                        "\"category\": [" +
                                            "{" +
                                                "\"coding\": [" +
                                                    "{" +
                                                        "\"system\": \"http://terminology.hl7.org/CodeSystem/observation-category\"," +
                                                        "\"code\": \"vital-signs\"," +
                                                        "\"display\": \"Vital Signs\"" +
                                                    "}" +
                                                "]" +
                                            "}" +
                                        "]," +
                                        "\"code\": {" +
                                            "\"coding\": [" +
                                                "{" +
                                                    "\"system\": \"http://loinc.org\"," +
                                                    "\"code\": \"8302-2\"," +
                                                    "\"display\": \"Body height\"" +
                                                "}" +
                                            "]" +
                                        "}," +
                                        "\"subject\": {" +
                                            "\"reference\": \"Patient/"+idpasien+"\"" +
                                        "}," +
                                        "\"performer\": [" +
                                            "{" +
                                                "\"reference\": \"Practitioner/"+iddokter+"\"" +
                                            "}" +
                                        "]," +
                                        "\"encounter\": {" +
                                            "\"reference\": \"Encounter/"+tbTB.getValueAt(i,9).toString()+"\"," +
                                            "\"display\": \"Pemeriksaan Fisik Tinggi Badan di "+tbTB.getValueAt(i,7).toString().replaceAll("Ralan","Rawat Jalan/IGD").replaceAll("Ranap","Rawat Inap")+", Pasien "+tbTB.getValueAt(i,4).toString()+" Pada Tanggal "+tbTB.getValueAt(i,13).toString()+" Jam "+tbTB.getValueAt(i,14).toString()+"\"" +
                                        "}," +
                                        "\"effectiveDateTime\": \""+tbTB.getValueAt(i,13).toString()+"T"+tbTB.getValueAt(i,14).toString()+"+07:00\"," +
                                        "\"valueQuantity\": {" +
                                            "\"value\": "+tbTB.getValueAt(i,10).toString().replaceAll(",",".")+"," +
                                            "\"unit\": \"centimeter\"," +
                                            "\"system\": \"http://unitsofmeasure.org\"," +
                                            "\"code\": \"cm\"" +
                                        "}" +
                                   "}";
                            System.out.println("URL : " + link + "/Observation/" + tbTB.getValueAt(i, 15).toString());
                            System.out.println("Request JSON : " + json);
                            requestEntity = new HttpEntity(json, headers);
                            json = api.getRest().exchange(link + "/Observation/" + tbTB.getValueAt(i, 15).toString(), HttpMethod.PUT, requestEntity, String.class).getBody();
                            System.out.println("Result JSON : " + json);
                        } catch (Exception e) {
                            System.out.println("Notifikasi Bridging : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
            tampiltb();
        } else if (TabRawat.getSelectedIndex() == 8) {
            for (i = 0; i < tbBB.getRowCount(); i++) {
                if (tbBB.getValueAt(i, 0).toString().equals("true") 
                        && (!tbBB.getValueAt(i, 5).toString().equals("")) 
                        && (!tbBB.getValueAt(i, 12).toString().equals("")) 
                        && (!tbBB.getValueAt(i, 15).toString().equals(""))) {
                    try {
                        iddokter = cekViaSatuSehat.tampilIDParktisi(tbBB.getValueAt(i, 12).toString());
                        idpasien = cekViaSatuSehat.tampilIDPasien(tbBB.getValueAt(i, 5).toString());
                        try {
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                            json = "{" +
                                        "\"resourceType\": \"Observation\"," +
                                        "\"id\": \""+tbBB.getValueAt(i,15).toString()+"\"," +
                                        "\"status\": \"final\"," +
                                        "\"category\": [" +
                                            "{" +
                                                "\"coding\": [" +
                                                    "{" +
                                                        "\"system\": \"http://terminology.hl7.org/CodeSystem/observation-category\"," +
                                                        "\"code\": \"vital-signs\"," +
                                                        "\"display\": \"Vital Signs\"" +
                                                    "}" +
                                                "]" +
                                            "}" +
                                        "]," +
                                        "\"code\": {" +
                                            "\"coding\": [" +
                                                "{" +
                                                    "\"system\": \"http://loinc.org\"," +
                                                    "\"code\": \"29463-7\"," +
                                                    "\"display\": \"Body Weight\"" +
                                                "}" +
                                            "]" +
                                        "}," +
                                        "\"subject\": {" +
                                            "\"reference\": \"Patient/"+idpasien+"\"" +
                                        "}," +
                                        "\"performer\": [" +
                                            "{" +
                                                "\"reference\": \"Practitioner/"+iddokter+"\"" +
                                            "}" +
                                        "]," +
                                        "\"encounter\": {" +
                                            "\"reference\": \"Encounter/"+tbBB.getValueAt(i,9).toString()+"\"," +
                                            "\"display\": \"Pemeriksaan Fisik Berat Badan di "+tbBB.getValueAt(i,7).toString().replaceAll("Ralan","Rawat Jalan/IGD").replaceAll("Ranap","Rawat Inap")+", Pasien "+tbBB.getValueAt(i,4).toString()+" Pada Tanggal "+tbBB.getValueAt(i,13).toString()+" Jam "+tbBB.getValueAt(i,14).toString()+"\"" +
                                        "}," +
                                        "\"effectiveDateTime\": \""+tbBB.getValueAt(i,13).toString()+"T"+tbBB.getValueAt(i,14).toString()+"+07:00\"," +
                                        "\"valueQuantity\": {" +
                                            "\"value\": "+tbBB.getValueAt(i,10).toString().replaceAll(",",".")+"," +
                                            "\"unit\": \"kilogram\"," +
                                            "\"system\": \"http://unitsofmeasure.org\"," +
                                            "\"code\": \"kg\"" +
                                        "}" +
                                   "}";
                            System.out.println("URL : " + link + "/Observation/" + tbBB.getValueAt(i, 15).toString());
                            System.out.println("Request JSON : " + json);
                            requestEntity = new HttpEntity(json, headers);
                            json = api.getRest().exchange(link + "/Observation/" + tbBB.getValueAt(i, 15).toString(), HttpMethod.PUT, requestEntity, String.class).getBody();
                            System.out.println("Result JSON : " + json);
                        } catch (Exception e) {
                            System.out.println("Notifikasi Bridging : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
            tampilbb();
        } else if (TabRawat.getSelectedIndex() == 9) {
            for (i = 0; i < tbLP.getRowCount(); i++) {
                if (tbLP.getValueAt(i, 0).toString().equals("true") 
                        && (!tbLP.getValueAt(i, 5).toString().equals("")) 
                        && (!tbLP.getValueAt(i, 12).toString().equals("")) 
                        && (!tbLP.getValueAt(i, 15).toString().equals(""))) {
                    try {
                        iddokter = cekViaSatuSehat.tampilIDParktisi(tbLP.getValueAt(i, 12).toString());
                        idpasien = cekViaSatuSehat.tampilIDPasien(tbLP.getValueAt(i, 5).toString());
                        try {
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                            json = "{" +
                                        "\"resourceType\": \"Observation\"," +
                                        "\"id\": \""+tbLP.getValueAt(i,15).toString()+"\"," +
                                        "\"status\": \"final\"," +
                                        "\"category\": [" +
                                            "{" +
                                                "\"coding\": [" +
                                                    "{" +
                                                        "\"system\": \"http://terminology.hl7.org/CodeSystem/observation-category\"," +
                                                        "\"code\": \"vital-signs\"," +
                                                        "\"display\": \"Vital Signs\"" +
                                                    "}" +
                                                "]" +
                                            "}" +
                                        "]," +
                                        "\"code\": {" +
                                            "\"coding\": [" +
                                                "{" +
                                                    "\"system\": \"http://loinc.org\"," +
                                                    "\"code\": \"8280-0\"," +
                                                    "\"display\": \"Waist Circumference at umbilicus by Tape measure\"" +
                                                "}" +
                                            "]" +
                                        "}," +
                                        "\"subject\": {" +
                                            "\"reference\": \"Patient/"+idpasien+"\"" +
                                        "}," +
                                        "\"performer\": [" +
                                            "{" +
                                                "\"reference\": \"Practitioner/"+iddokter+"\"" +
                                            "}" +
                                        "]," +
                                        "\"encounter\": {" +
                                            "\"reference\": \"Encounter/"+tbLP.getValueAt(i,9).toString()+"\"," +
                                            "\"display\": \"Pemeriksaan Fisik Lingkar Perut di "+tbLP.getValueAt(i,7).toString().replaceAll("Ralan","Rawat Jalan/IGD").replaceAll("Ranap","Rawat Inap")+", Pasien "+tbLP.getValueAt(i,4).toString()+" Pada Tanggal "+tbLP.getValueAt(i,13).toString()+" Jam "+tbLP.getValueAt(i,14).toString()+"\"" +
                                        "}," +
                                        "\"effectiveDateTime\": \""+tbLP.getValueAt(i,13).toString()+"T"+tbLP.getValueAt(i,14).toString()+"+07:00\"," +
                                        "\"valueQuantity\": {" +
                                            "\"value\": "+tbLP.getValueAt(i,10).toString().replaceAll(",",".")+"," +
                                            "\"unit\": \"centimeter\"," +
                                            "\"system\": \"http://unitsofmeasure.org\"," +
                                            "\"code\": \"cm\"" +
                                        "}" +
                                   "}";
                            System.out.println("URL : " + link + "/Observation/" + tbLP.getValueAt(i, 15).toString());
                            System.out.println("Request JSON : " + json);
                            requestEntity = new HttpEntity(json, headers);
                            json = api.getRest().exchange(link + "/Observation/" + tbLP.getValueAt(i, 15).toString(), HttpMethod.PUT, requestEntity, String.class).getBody();
                            System.out.println("Result JSON : " + json);
                        } catch (Exception e) {
                            System.out.println("Notifikasi Bridging : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
            tampillp();
        }
    }//GEN-LAST:event_BtnUpdateActionPerformed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                tampilsuhu();
                break;
            case 1:
                tampilrespirasi();
                break;
            case 2:
                tampilnadi();
                break;
            case 3:
                tampilspo2();
                break;
            case 4:
                tampilgcs();
                break;
            case 5:
                tampilkesadaran();
                break;
            case 6:
                tampiltensi();
                break;
            case 7:
                tampiltb();
                break;
            case 8:
                tampilbb();
                break;
            case 9:
                tampillp();
                break;
            default:
                break;
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        TCari.setText("");
        TabRawatMouseClicked(null);
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari.setText("");
            tampilsuhu();
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAll1KeyPressed

    private void ppPilihIdKosongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPilihIdKosongActionPerformed
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                for (i = 0; i < tbSuhu.getRowCount(); i++) {
                    if (tbSuhu.getValueAt(i, 15).equals("")) {
                        tbSuhu.setValueAt(true, i, 0);
                    }
                }
                break;
            case 1:
                for (i = 0; i < tbRespirasi.getRowCount(); i++) {
                    if (tbRespirasi.getValueAt(i, 15).equals("")) {
                        tbRespirasi.setValueAt(true, i, 0);
                    }
                }
                break;
            case 2:
                for (i = 0; i < tbNadi.getRowCount(); i++) {
                    if (tbNadi.getValueAt(i, 15).equals("")) {
                        tbNadi.setValueAt(true, i, 0);
                    }
                }
                break;
            case 3:
                for (i = 0; i < tbSpO2.getRowCount(); i++) {
                    if (tbSpO2.getValueAt(i, 15).equals("")) {
                        tbSpO2.setValueAt(true, i, 0);
                    }
                }
                break;
            case 4:
                for (i = 0; i < tbGCS.getRowCount(); i++) {
                    if (tbGCS.getValueAt(i, 15).equals("")) {
                        tbGCS.setValueAt(true, i, 0);
                    }
                }
                break;
            case 5:
                for (i = 0; i < tbKesadaran.getRowCount(); i++) {
                    if (tbKesadaran.getValueAt(i, 15).equals("")) {
                        tbKesadaran.setValueAt(true, i, 0);
                    }
                }
                break;
            case 6:
                for (i = 0; i < tbTensi.getRowCount(); i++) {
                    if (tbTensi.getValueAt(i, 15).equals("")) {
                        tbTensi.setValueAt(true, i, 0);
                    }
                }
                break;
            case 7:
                for (i = 0; i < tbTB.getRowCount(); i++) {
                    if (tbTB.getValueAt(i, 15).equals("")) {
                        tbTB.setValueAt(true, i, 0);
                    }
                }
                break;
            case 8:
                for (i = 0; i < tbBB.getRowCount(); i++) {
                    if (tbBB.getValueAt(i, 15).equals("")) {
                        tbBB.setValueAt(true, i, 0);
                    }
                }
                break;
            case 9:
                for (i = 0; i < tbLP.getRowCount(); i++) {
                    if (tbLP.getValueAt(i, 15).equals("")) {
                        tbLP.setValueAt(true, i, 0);
                    }
                }
                break;
            default:
                break;
        }
    }//GEN-LAST:event_ppPilihIdKosongActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            SatuSehatKirimObservationTTV dialog = new SatuSehatKirimObservationTTV(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll1;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnKirim;
    private widget.Button BtnPrint;
    private widget.Button BtnUpdate;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    private widget.TextBox TCari;
    private javax.swing.JTabbedPane TabRawat;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private javax.swing.JMenuItem ppBersihkan;
    private javax.swing.JMenuItem ppPilihIdKosong;
    private javax.swing.JMenuItem ppPilihSemua;
    private widget.Table tbBB;
    private widget.Table tbGCS;
    private widget.Table tbKesadaran;
    private widget.Table tbLP;
    private widget.Table tbNadi;
    private widget.Table tbRespirasi;
    private widget.Table tbSpO2;
    private widget.Table tbSuhu;
    private widget.Table tbTB;
    private widget.Table tbTensi;
    // End of variables declaration//GEN-END:variables
    
    private void tampilsuhu() {
        Valid.tabelKosong(tabModeSuhu);
        try {
            ps = koneksi.prepareStatement(
                    "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, rp.stts, concat(rp.tgl_registrasi,' ',ADDTIME(rp.jam_reg,'02:00:00')) pulang, "
                    + "se.id_encounter, pg.nama, pg.no_ktp ktppraktisi, pr.tgl_perawatan, pr.jam_rawat, if(rp.kd_poli='igdk',ti.temperatur,pr.suhu_tubuh) suhu_tubuh, "
                    + "ifnull(ss.id_observation,'') satu_sehat_observationttvsuhu FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                    + "INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat INNER JOIN pemeriksaan_ralan_petugas pr ON pr.no_rawat = rp.no_rawat "
                    + "INNER JOIN pegawai pg ON pg.nik=rp.kd_dokter LEFT JOIN triase_igd ti on ti.no_rawat=rp.no_rawat "
                    + "LEFT JOIN satu_sehat_observationttvsuhu ss ON ss.no_rawat = pr.no_rawat AND ss.tgl_perawatan = pr.tgl_perawatan AND ss.jam_rawat = pr.jam_rawat AND ss.STATUS = 'Ralan' "
                    + "where (ti.temperatur<>'' or pr.suhu_tubuh<>'') and rp.tgl_registrasi between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ? or "
                    + "rp.stts like ?)") + " order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat,pr.tgl_perawatan,pr.jam_rawat");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeSuhu.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("stts"), "Ralan", rs.getString("pulang"), rs.getString("id_encounter"), rs.getString("suhu_tubuh"),
                        rs.getString("nama"), rs.getString("ktppraktisi"), rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("satu_sehat_observationttvsuhu")
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

            ps = koneksi.prepareStatement(
                    "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, rp.stts, concat(ki.tgl_keluar,' ',ki.jam_keluar) pulang, "
                    + "se.id_encounter, pg.nama, pg.no_ktp ktppraktisi, pr.tgl_perawatan, pr.jam_rawat, pr.suhu_tubuh, ifnull(ss.id_observation,'') satu_sehat_observationttvsuhu "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                    + "INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat INNER JOIN pemeriksaan_ranap pr ON pr.no_rawat = rp.no_rawat "
                    + "INNER JOIN pegawai pg ON pr.nip = pg.nik LEFT JOIN satu_sehat_observationttvsuhu ss ON ss.no_rawat = pr.no_rawat AND ss.tgl_perawatan = pr.tgl_perawatan "
                    + "AND ss.jam_rawat = pr.jam_rawat AND ss.STATUS = 'Ranap' where ki.stts_pulang not in ('-','Pindah Kamar') and pr.suhu_tubuh<>'' and rp.tgl_registrasi between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ? or "
                    + "rp.stts like ?)") + " order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat,pr.tgl_perawatan,pr.jam_rawat");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeSuhu.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("stts"), "Ralan", rs.getString("pulang"), rs.getString("id_encounter"), rs.getString("suhu_tubuh"),
                        rs.getString("nama"), rs.getString("ktppraktisi"), rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("satu_sehat_observationttvsuhu")
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
        LCount.setText("" + tabModeSuhu.getRowCount());
    }

    private void tampilrespirasi() {
        Valid.tabelKosong(tabModeRespirasi);
        try {
            ps = koneksi.prepareStatement(
                    "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, rp.stts, concat(rp.tgl_registrasi,' ',ADDTIME(rp.jam_reg, '02:00:00')) pulang, "
                    + "se.id_encounter, pg.nama, pg.no_ktp ktppraktisi, pr.tgl_perawatan, pr.jam_rawat, if(rp.kd_poli='igdk',ti.napas,pr.respirasi) respirasi, ifnull(ss.id_observation,'') satu_sehat_observationttvrespirasi "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat "
                    + "INNER JOIN pemeriksaan_ralan_petugas pr ON pr.no_rawat = rp.no_rawat INNER JOIN pegawai pg ON pg.nik=rp.kd_dokter LEFT JOIN triase_igd ti on ti.no_rawat=rp.no_rawat "
                    + "LEFT JOIN satu_sehat_observationttvrespirasi ss ON ss.no_rawat = pr.no_rawat AND ss.tgl_perawatan = pr.tgl_perawatan AND ss.jam_rawat = pr.jam_rawat AND ss.STATUS = 'Ralan' "
                    + "where (ti.napas<>'' or pr.respirasi<>'') and rp.tgl_registrasi between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ? or "
                    + "rp.stts like ?)") + " order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat,pr.tgl_perawatan,pr.jam_rawat");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeRespirasi.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("stts"), "Ralan", rs.getString("pulang"), rs.getString("id_encounter"), rs.getString("respirasi"),
                        rs.getString("nama"), rs.getString("ktppraktisi"), rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("satu_sehat_observationttvrespirasi")
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

            ps = koneksi.prepareStatement(
                    "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, rp.stts, concat(ki.tgl_keluar,' ',ki.jam_keluar) pulang, "
                    + "se.id_encounter, pg.nama, pg.no_ktp ktppraktisi, pr.tgl_perawatan, pr.jam_rawat, pr.respirasi, ifnull(ss.id_observation,'') satu_sehat_observationttvrespirasi "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                    + "INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat INNER JOIN pemeriksaan_ranap pr ON pr.no_rawat = rp.no_rawat "
                    + "INNER JOIN pegawai pg ON pr.nip = pg.nik LEFT JOIN satu_sehat_observationttvrespirasi ss ON ss.no_rawat = pr.no_rawat AND ss.tgl_perawatan = pr.tgl_perawatan "
                    + "AND ss.jam_rawat = pr.jam_rawat AND ss.STATUS = 'Ranap' where ki.stts_pulang not in ('-','Pindah Kamar') and pr.respirasi<>'' and rp.tgl_registrasi between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ? or "
                    + "rp.stts like ?)") + " order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat,pr.tgl_perawatan,pr.jam_rawat");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeRespirasi.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("stts"), "Ralan", rs.getString("pulang"), rs.getString("id_encounter"), rs.getString("respirasi"),
                        rs.getString("nama"), rs.getString("ktppraktisi"), rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("satu_sehat_observationttvrespirasi")
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
        LCount.setText("" + tabModeRespirasi.getRowCount());
    }
    
    private void tampilnadi() {
        Valid.tabelKosong(tabModeNadi);
        try {
            ps = koneksi.prepareStatement(
                    "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, rp.stts, concat(rp.tgl_registrasi,' ',ADDTIME(rp.jam_reg,'02:00:00')) pulang, "
                    + "se.id_encounter, pg.nama, pg.no_ktp ktppraktisi, pr.tgl_perawatan, pr.jam_rawat, if(rp.kd_poli='igdk',ti.nadi,pr.nadi) nadi, ifnull(ss.id_observation,'') satu_sehat_observationttvnadi "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat "
                    + "INNER JOIN pemeriksaan_ralan_petugas pr ON pr.no_rawat = rp.no_rawat INNER JOIN pegawai pg ON pg.nik=rp.kd_dokter LEFT JOIN triase_igd ti on ti.no_rawat=rp.no_rawat "
                    + "LEFT JOIN satu_sehat_observationttvnadi ss ON ss.no_rawat = pr.no_rawat AND ss.tgl_perawatan = pr.tgl_perawatan AND ss.jam_rawat = pr.jam_rawat AND ss.STATUS = 'Ralan' "
                    + "where (ti.nadi<>'' or pr.nadi<>'') and rp.tgl_registrasi between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or nj.tanggal like ? or "
                    + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ? or "
                    + "rp.stts like ?)") + " order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat,pr.tgl_perawatan,pr.jam_rawat");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeNadi.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("stts"), "Ralan", rs.getString("pulang"), rs.getString("id_encounter"), rs.getString("nadi"),
                        rs.getString("nama"), rs.getString("ktppraktisi"), rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("satu_sehat_observationttvnadi")
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

            ps = koneksi.prepareStatement(
                    "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, rp.stts, concat(ki.tgl_keluar,' ',ki.jam_keluar) pulang, "
                    + "se.id_encounter, pg.nama, pg.no_ktp ktppraktisi, pr.tgl_perawatan, pr.jam_rawat, pr.nadi, ifnull(ss.id_observation,'') satu_sehat_observationttvnadi "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                    + "INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat INNER JOIN pemeriksaan_ranap pr ON pr.no_rawat = rp.no_rawat "
                    + "INNER JOIN pegawai pg ON pr.nip = pg.nik LEFT JOIN satu_sehat_observationttvnadi ss ON ss.no_rawat = pr.no_rawat AND ss.tgl_perawatan = pr.tgl_perawatan "
                    + "AND ss.jam_rawat = pr.jam_rawat AND ss.STATUS = 'Ranap' where ki.stts_pulang not in ('-','Pindah Kamar') and pr.nadi<>'' and rp.tgl_registrasi between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ? or "
                    + "rp.stts like ?)") + " order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat,pr.tgl_perawatan,pr.jam_rawat");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeNadi.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("stts"), "Ralan", rs.getString("pulang"), rs.getString("id_encounter"), rs.getString("nadi"),
                        rs.getString("nama"), rs.getString("ktppraktisi"), rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("satu_sehat_observationttvnadi")
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
        LCount.setText("" + tabModeNadi.getRowCount());
    }
    
    private void tampilspo2() {
        Valid.tabelKosong(tabModeSpO2);
        try {
            ps = koneksi.prepareStatement(
                    "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, rp.stts, concat(rp.tgl_registrasi,' ',ADDTIME(rp.jam_reg, '02:00:00')) pulang, "
                    + "se.id_encounter, pg.nama, pg.no_ktp ktppraktisi, pr.tgl_perawatan, pr.jam_rawat, if(rp.kd_poli='igdk',ti.saturasi,pr.spo2) spo2, ifnull(ss.id_observation,'') satu_sehat_observationttvspo2 "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat "
                    + "INNER JOIN pemeriksaan_ralan_petugas pr ON pr.no_rawat = rp.no_rawat INNER JOIN pegawai pg ON pg.nik=rp.kd_dokter LEFT JOIN triase_igd ti on ti.no_rawat=rp.no_rawat "
                    + "LEFT JOIN satu_sehat_observationttvspo2 ss ON ss.no_rawat = pr.no_rawat AND ss.tgl_perawatan = pr.tgl_perawatan AND ss.jam_rawat = pr.jam_rawat AND ss.STATUS = 'Ralan' "
                    + "where (ti.saturasi<>'' or pr.spo2<>'') and rp.tgl_registrasi between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ? or "
                    + "rp.stts like ?)") + " order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat,pr.tgl_perawatan,pr.jam_rawat");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeSpO2.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("stts"), "Ralan", rs.getString("pulang"), rs.getString("id_encounter"), rs.getString("spo2"),
                        rs.getString("nama"), rs.getString("ktppraktisi"), rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("satu_sehat_observationttvspo2")
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

            ps = koneksi.prepareStatement(
                    "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, rp.stts, concat(ki.tgl_keluar,' ',ki.jam_keluar) pulang, "
                    + "se.id_encounter, pg.nama, pg.no_ktp ktppraktisi, pr.tgl_perawatan, pr.jam_rawat, pr.spo2, ifnull(ss.id_observation,'') satu_sehat_observationttvspo2 "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                    + "INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat INNER JOIN pemeriksaan_ranap pr ON pr.no_rawat = rp.no_rawat "
                    + "INNER JOIN pegawai pg ON pr.nip = pg.nik LEFT JOIN satu_sehat_observationttvspo2 ss ON ss.no_rawat = pr.no_rawat AND ss.tgl_perawatan = pr.tgl_perawatan "
                    + "AND ss.jam_rawat = pr.jam_rawat AND ss.STATUS = 'Ranap' where ki.stts_pulang not in ('-','Pindah Kamar') and pr.spo2<>'' and rp.tgl_registrasi between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ? or "
                    + "rp.stts like ?)") + " order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat,pr.tgl_perawatan,pr.jam_rawat");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeSpO2.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("stts"), "Ralan", rs.getString("pulang"), rs.getString("id_encounter"), rs.getString("spo2"),
                        rs.getString("nama"), rs.getString("ktppraktisi"), rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("satu_sehat_observationttvspo2")
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
        LCount.setText("" + tabModeSpO2.getRowCount());
    }
    
    private void tampilgcs() {
        Valid.tabelKosong(tabModeGCS);
        try {
            ps = koneksi.prepareStatement(
                    "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, rp.stts, concat(rp.tgl_registrasi,' ',ADDTIME(rp.jam_reg, '02:00:00')) pulang, "
                    + "se.id_encounter, pg.nama, pg.no_ktp ktppraktisi, pr.tgl_perawatan, pr.jam_rawat, if(rp.kd_poli='igdk',concat(pa.gcs_e,',',pa.gcs_v,',',pa.gcs_m),pr.gcs) gcs, "
                    + "ifnull(ss.id_observation,'') satu_sehat_observationttvgcs FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                    + "INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat INNER JOIN pemeriksaan_ralan_petugas pr ON pr.no_rawat = rp.no_rawat "
                    + "INNER JOIN pegawai pg ON pg.nik=rp.kd_dokter LEFT JOIN penilaian_awal_medis_igd pa on pa.no_rawat=rp.no_rawat "
                    + "LEFT JOIN satu_sehat_observationttvgcs ss ON ss.no_rawat = pr.no_rawat AND ss.tgl_perawatan = pr.tgl_perawatan AND ss.jam_rawat = pr.jam_rawat AND ss.STATUS = 'Ralan' "
                    + "where (pa.gcs_e<>'' or pr.gcs<>'') and rp.tgl_registrasi between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ? or "
                    + "rp.stts like ?)") + " order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat,pr.tgl_perawatan,pr.jam_rawat");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeGCS.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("stts"), "Ralan", rs.getString("pulang"), rs.getString("id_encounter"), rs.getString("gcs"),
                        rs.getString("nama"), rs.getString("ktppraktisi"), rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("satu_sehat_observationttvgcs")
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

            ps = koneksi.prepareStatement(
                    "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, rp.stts, concat(ki.tgl_keluar,' ',ki.jam_keluar) pulang, "
                    + "se.id_encounter, pg.nama, pg.no_ktp ktppraktisi, pr.tgl_perawatan, pr.jam_rawat, pr.gcs, ifnull(ss.id_observation,'') satu_sehat_observationttvgcs "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                    + "INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat INNER JOIN pemeriksaan_ranap pr ON pr.no_rawat = rp.no_rawat "
                    + "INNER JOIN pegawai pg ON pr.nip = pg.nik LEFT JOIN satu_sehat_observationttvgcs ss ON ss.no_rawat = pr.no_rawat AND ss.tgl_perawatan = pr.tgl_perawatan "
                    + "AND ss.jam_rawat = pr.jam_rawat AND ss.STATUS = 'Ranap' where ki.stts_pulang not in ('-','Pindah Kamar') and pr.gcs<>'' and rp.tgl_registrasi between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ? or "
                    + "rp.stts like ?)") + " order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat,pr.tgl_perawatan,pr.jam_rawat");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeGCS.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("stts"), "Ralan", rs.getString("pulang"), rs.getString("id_encounter"), rs.getString("gcs"),
                        rs.getString("nama"), rs.getString("ktppraktisi"), rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("satu_sehat_observationttvgcs")
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
        LCount.setText("" + tabModeGCS.getRowCount());
    }
    
    private void tampilkesadaran() {
        Valid.tabelKosong(tabModeKesadaran);
        try {
            ps = koneksi.prepareStatement(
                    "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, rp.stts, concat(rp.tgl_registrasi,' ',ADDTIME(rp.jam_reg, '02:00:00')) pulang, "
                    + "se.id_encounter, pg.nama, pg.no_ktp ktppraktisi, pr.tgl_perawatan, pr.jam_rawat, if(rp.kd_poli='igdk',ti.kesadaran,pr.kesadaran) kesadaran, ifnull(ss.id_observation,'') satu_sehat_observationttvkesadaran "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat "
                    + "INNER JOIN pemeriksaan_ralan_petugas pr ON pr.no_rawat = rp.no_rawat INNER JOIN pegawai pg ON pg.nik=rp.kd_dokter LEFT JOIN triase_igd ti on ti.no_rawat=rp.no_rawat "
                    + "LEFT JOIN satu_sehat_observationttvkesadaran ss ON ss.no_rawat = pr.no_rawat AND ss.tgl_perawatan = pr.tgl_perawatan AND ss.jam_rawat = pr.jam_rawat AND ss.STATUS = 'Ralan' "
                    + "where (ti.kesadaran<>'' or pr.kesadaran<>'') and rp.tgl_registrasi between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ? or "
                    + "rp.stts like ?)") + " order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat,pr.tgl_perawatan,pr.jam_rawat");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeKesadaran.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("stts"), "Ralan", rs.getString("pulang"), rs.getString("id_encounter"), rs.getString("kesadaran"),
                        rs.getString("nama"), rs.getString("ktppraktisi"), rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("satu_sehat_observationttvkesadaran")
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

            ps = koneksi.prepareStatement(
                    "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, rp.stts, concat(ki.tgl_keluar,' ',ki.jam_keluar) pulang, "
                    + "se.id_encounter, pg.nama, pg.no_ktp ktppraktisi, pr.tgl_perawatan, pr.jam_rawat, pr.kesadaran, ifnull(ss.id_observation,'') satu_sehat_observationttvkesadaran "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                    + "INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat INNER JOIN pemeriksaan_ranap pr ON pr.no_rawat = rp.no_rawat "
                    + "INNER JOIN pegawai pg ON pr.nip = pg.nik LEFT JOIN satu_sehat_observationttvkesadaran ss ON ss.no_rawat = pr.no_rawat AND ss.tgl_perawatan = pr.tgl_perawatan "
                    + "AND ss.jam_rawat = pr.jam_rawat AND ss.STATUS = 'Ranap' where ki.stts_pulang not in ('-','Pindah Kamar') and pr.kesadaran<>'' and rp.tgl_registrasi between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ? or "
                    + "rp.stts like ?)") + " order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat,pr.tgl_perawatan,pr.jam_rawat");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeKesadaran.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("stts"), "Ralan", rs.getString("pulang"), rs.getString("id_encounter"), rs.getString("kesadaran"),
                        rs.getString("nama"), rs.getString("ktppraktisi"), rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("satu_sehat_observationttvkesadaran")
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
        LCount.setText("" + tabModeKesadaran.getRowCount());
    }
    
    private void tampiltensi() {
        Valid.tabelKosong(tabModeTensi);
        try {
            ps = koneksi.prepareStatement(
                    "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, rp.stts, concat(rp.tgl_registrasi,' ',ADDTIME(rp.jam_reg, '02:00:00')) pulang, "
                    + "se.id_encounter, pg.nama, pg.no_ktp ktppraktisi, pr.tgl_perawatan, pr.jam_rawat, if(rp.kd_poli='igdk',ti.td,pr.tensi) tensi, ifnull(ss.id_observation,'') satu_sehat_observationttvtensi "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat "
                    + "INNER JOIN pemeriksaan_ralan_petugas pr ON pr.no_rawat = rp.no_rawat INNER JOIN pegawai pg ON pg.nik=rp.kd_dokter LEFT JOIN triase_igd ti on ti.no_rawat=rp.no_rawat "
                    + "LEFT JOIN satu_sehat_observationttvtensi ss ON ss.no_rawat = pr.no_rawat AND ss.tgl_perawatan = pr.tgl_perawatan AND ss.jam_rawat = pr.jam_rawat AND ss.STATUS = 'Ralan' "
                    + "where (ti.td<>'' or pr.tensi<>'') and rp.tgl_registrasi between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ? or "
                    + "rp.stts like ?)") + " order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat,pr.tgl_perawatan,pr.jam_rawat");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeTensi.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("stts"), "Ralan", rs.getString("pulang"), rs.getString("id_encounter"), rs.getString("tensi"),
                        rs.getString("nama"), rs.getString("ktppraktisi"), rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("satu_sehat_observationttvtensi")
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

            ps = koneksi.prepareStatement(
                    "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, rp.stts, concat(ki.tgl_keluar,' ',ki.jam_keluar) pulang, "
                    + "se.id_encounter, pg.nama, pg.no_ktp ktppraktisi, pr.tgl_perawatan, pr.jam_rawat, pr.tensi, ifnull(ss.id_observation,'') satu_sehat_observationttvtensi "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                    + "INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat INNER JOIN pemeriksaan_ranap pr ON pr.no_rawat = rp.no_rawat "
                    + "INNER JOIN pegawai pg ON pr.nip = pg.nik LEFT JOIN satu_sehat_observationttvtensi ss ON ss.no_rawat = pr.no_rawat AND ss.tgl_perawatan = pr.tgl_perawatan "
                    + "AND ss.jam_rawat = pr.jam_rawat AND ss.STATUS = 'Ranap' where ki.stts_pulang not in ('-','Pindah Kamar') and pr.tensi<>'' and rp.tgl_registrasi between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ? or "
                    + "rp.stts like ?)") + " order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat,pr.tgl_perawatan,pr.jam_rawat");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeTensi.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("stts"), "Ralan", rs.getString("pulang"), rs.getString("id_encounter"), rs.getString("tensi"),
                        rs.getString("nama"), rs.getString("ktppraktisi"), rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("satu_sehat_observationttvtensi")
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
        LCount.setText("" + tabModeTensi.getRowCount());
    }
    
    private void tampiltb() {
        Valid.tabelKosong(tabModeTB);
        try {
            ps = koneksi.prepareStatement(
                    "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, rp.stts, concat(rp.tgl_registrasi,' ',ADDTIME(rp.jam_reg, '02:00:00')) pulang, "
                    + "se.id_encounter, pg.nama, pg.no_ktp ktppraktisi, pr.tgl_perawatan, pr.jam_rawat, if(rp.kd_poli='igdk',ti.tb,pr.tinggi) tinggi, ifnull(ss.id_observation,'') satu_sehat_observationttvtb "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat "
                    + "INNER JOIN pemeriksaan_ralan_petugas pr ON pr.no_rawat = rp.no_rawat INNER JOIN pegawai pg ON pg.nik=rp.kd_dokter LEFT JOIN triase_igd ti on ti.no_rawat=rp.no_rawat "
                    + "LEFT JOIN satu_sehat_observationttvtb ss ON ss.no_rawat = pr.no_rawat AND ss.tgl_perawatan = pr.tgl_perawatan AND ss.jam_rawat = pr.jam_rawat AND ss.STATUS = 'Ralan' "
                    + "where (ti.tb<>'' or pr.tinggi<>'') and rp.tgl_registrasi between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ? or "
                    + "rp.stts like ?)") + " order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat,pr.tgl_perawatan,pr.jam_rawat");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeTB.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("stts"), "Ralan", rs.getString("pulang"), rs.getString("id_encounter"), rs.getString("tinggi"),
                        rs.getString("nama"), rs.getString("ktppraktisi"), rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("satu_sehat_observationttvtb")
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

            ps = koneksi.prepareStatement(
                    "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, rp.stts, concat(ki.tgl_keluar,' ',ki.jam_keluar) pulang, "
                    + "se.id_encounter, pg.nama, pg.no_ktp ktppraktisi, pr.tgl_perawatan, pr.jam_rawat, pr.tinggi, ifnull(ss.id_observation,'') satu_sehat_observationttvtb "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                    + "INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat INNER JOIN pemeriksaan_ranap pr ON pr.no_rawat = rp.no_rawat "
                    + "INNER JOIN pegawai pg ON pr.nip = pg.nik LEFT JOIN satu_sehat_observationttvtb ss ON ss.no_rawat = pr.no_rawat AND ss.tgl_perawatan = pr.tgl_perawatan "
                    + "AND ss.jam_rawat = pr.jam_rawat AND ss.STATUS = 'Ranap' where ki.stts_pulang not in ('-','Pindah Kamar') and pr.tinggi<>'' and rp.tgl_registrasi between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ? or "
                    + "rp.stts like ?)") + " order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat,pr.tgl_perawatan,pr.jam_rawat");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeTB.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("stts"), "Ralan", rs.getString("pulang"), rs.getString("id_encounter"), rs.getString("tinggi"),
                        rs.getString("nama"), rs.getString("ktppraktisi"), rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("satu_sehat_observationttvtb")
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
        LCount.setText("" + tabModeTB.getRowCount());
    }
    
    private void tampilbb() {
        Valid.tabelKosong(tabModeBB);
        try {
            ps = koneksi.prepareStatement(
                    "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, rp.stts, concat(rp.tgl_registrasi,' ',ADDTIME(rp.jam_reg, '02:00:00')) pulang, "
                    + "se.id_encounter, pg.nama, pg.no_ktp ktppraktisi, pr.tgl_perawatan, pr.jam_rawat, if(rp.kd_poli='igdk',ti.bb,pr.berat) berat, ifnull(ss.id_observation,'') satu_sehat_observationttvbb "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat "
                    + "INNER JOIN pemeriksaan_ralan_petugas pr ON pr.no_rawat = rp.no_rawat INNER JOIN pegawai pg ON pg.nik=rp.kd_dokter LEFT JOIN triase_igd ti on ti.no_rawat=rp.no_rawat "
                    + "LEFT JOIN satu_sehat_observationttvbb ss ON ss.no_rawat = pr.no_rawat AND ss.tgl_perawatan = pr.tgl_perawatan AND ss.jam_rawat = pr.jam_rawat AND ss.STATUS = 'Ralan' "
                    + "where (ti.bb<>'' or pr.berat<>'') and rp.tgl_registrasi between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ? or "
                    + "rp.stts like ?)") + " order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat,pr.tgl_perawatan,pr.jam_rawat");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeBB.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("stts"), "Ralan", rs.getString("pulang"), rs.getString("id_encounter"), rs.getString("berat"),
                        rs.getString("nama"), rs.getString("ktppraktisi"), rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("satu_sehat_observationttvbb")
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

            ps = koneksi.prepareStatement(
                    "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, rp.stts, concat(ki.tgl_keluar,' ',ki.jam_keluar) pulang, "
                    + "se.id_encounter, pg.nama, pg.no_ktp ktppraktisi, pr.tgl_perawatan, pr.jam_rawat, pr.berat, ifnull(ss.id_observation,'') satu_sehat_observationttvbb "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                    + "INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat INNER JOIN pemeriksaan_ranap pr ON pr.no_rawat = rp.no_rawat "
                    + "INNER JOIN pegawai pg ON pr.nip = pg.nik LEFT JOIN satu_sehat_observationttvbb ss ON ss.no_rawat = pr.no_rawat AND ss.tgl_perawatan = pr.tgl_perawatan "
                    + "AND ss.jam_rawat = pr.jam_rawat AND ss.STATUS = 'Ranap' where ki.stts_pulang not in ('-','Pindah Kamar') and pr.berat<>'' and rp.tgl_registrasi between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ? or "
                    + "rp.stts like ?)") + " order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat,pr.tgl_perawatan,pr.jam_rawat");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeBB.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("stts"), "Ralan", rs.getString("pulang"), rs.getString("id_encounter"), rs.getString("berat"),
                        rs.getString("nama"), rs.getString("ktppraktisi"), rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("satu_sehat_observationttvbb")
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
        LCount.setText("" + tabModeBB.getRowCount());
    }
    
    private void tampillp() {
        Valid.tabelKosong(tabModeLP);
        try {
            ps = koneksi.prepareStatement(
                    "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, rp.stts, concat(rp.tgl_registrasi,' ',ADDTIME(rp.jam_reg, '02:00:00')) pulang, "
                    + "se.id_encounter, pg.nama, pg.no_ktp ktppraktisi, pr.tgl_perawatan, pr.jam_rawat, pr.lingkar_perut, ifnull(ss.id_observation,'') satu_sehat_observationttvlp "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat "
                    + "INNER JOIN pemeriksaan_ralan_petugas pr ON pr.no_rawat = rp.no_rawat INNER JOIN pegawai pg ON pg.nik=rp.kd_dokter "
                    + "LEFT JOIN satu_sehat_observationttvlp ss ON ss.no_rawat = pr.no_rawat AND ss.tgl_perawatan = pr.tgl_perawatan AND ss.jam_rawat = pr.jam_rawat AND ss.STATUS = 'Ralan' "
                    + "where pr.lingkar_perut<>'' and rp.tgl_registrasi between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ? or "
                    + "rp.stts like ?)") + " order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat,pr.tgl_perawatan,pr.jam_rawat");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeLP.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("stts"), "Ralan", rs.getString("pulang"), rs.getString("id_encounter"), rs.getString("lingkar_perut"),
                        rs.getString("nama"), rs.getString("ktppraktisi"), rs.getString("tgl_perawatan"), rs.getString("jam_rawat"), rs.getString("satu_sehat_observationttvlp")
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
        LCount.setText("" + tabModeLP.getRowCount());
    }
    
    public void isCek(){
        BtnKirim.setEnabled(akses.getsatu_sehat());
        BtnPrint.setEnabled(akses.getsatu_sehat());
    }
}
