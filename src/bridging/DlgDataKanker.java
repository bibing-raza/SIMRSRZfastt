package bridging;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.DlgCariPenyakit;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import simrskhanza.DlgKelurahan;

/**
 *
 * @author dosen
 */
public final class DlgDataKanker extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, pilihan = 0, x = 0;
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private RestTemplate rest;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    private ApiKemenkesKANKER api = new ApiKemenkesKANKER();
    private KankerReferensiJenisKelamin jk = new KankerReferensiJenisKelamin(null, false);
    private KankerReferensiKelurahan kel = new KankerReferensiKelurahan(null, false);
    private KankerReferensiKecamatan kec = new KankerReferensiKecamatan(null, false);
    private KankerReferensiKabupaten kab = new KankerReferensiKabupaten(null, false);
    private KankerReferensiPropinsi prop = new KankerReferensiPropinsi(null, false);
    private KankerReferensiCaraMasuk caraMsk = new KankerReferensiCaraMasuk(null, false);
    private KankerReferensiAsalRujukan asalRujuk = new KankerReferensiAsalRujukan(null, false);
    private DlgCariPenyakit penyakit = new DlgCariPenyakit(null, false);
    private KankerReferensiSubInstalasi subInst = new KankerReferensiSubInstalasi(null, false);
    private KankerReferensiCaraKeluar carak = new KankerReferensiCaraKeluar(null, false);
    private KankerReferensiKeadaanKeluar kelar = new KankerReferensiKeadaanKeluar(null, false);
    private KankerReferensiCaraBayar cabar = new KankerReferensiCaraBayar(null, false);
    private String requestJson = "", stts_lanjt = "", url = "", status = "", pesan = "", pesanGagal = "", 
            id_simpan = "";

    /** Creates new form DlgJnsPerawatan
     * @param parent
     * @param modal */
    public DlgDataKanker(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(628,674);

        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "ID Simpan", "No.RM", "Nama Pasien", "Tgl.Lahir", "ID JK", "Jns. Kelamin", "Umur", "No.JKN", "NIK", "Alamat", "ID Kel.", "Kelurahan",
            "ID Kec.", "Kecamatan", "ID Kab.", "Kabupaten/Kota", "ID. Prop.", "Propinsi", "Alamat Domisili", "ID Kel. Dom.", "Kelurahan Dom.", "ID Kec. Dom.", "Kecamatan Dom.",
            "ID Kab. Dom.", "Kabupaten/Kota Dom.", "ID Prop. Dom.", "Propinsi Dom.", "No. HP/Telp.", "Tgl. Masuk", "ID Cr. Msk.", "Cara Masuk", "ID Asal Rjkn.", "Asal Rujukan",
            "Asal Rujukan Fasyankes Lainnya", "ID Diag. Msk.", "ID Sub. Inst.", "Sub Instalasi Unit", "ID Diag. Utama", "ID Diag. Sekunder 1", "ID Diag. Sekunder 2", "ID Diag. Sekunder 3",
            "Tgl. Diagnosa", "Tgl. Keluar", "ID Cr. Klr.", "Cara Keluar", "ID Keadaan", "Keadaan Keluar", "ID Sbb. Mati Lgs. 1A", "ID Sbb. Mati Ant. 1B", "ID Sbb. Mati Ant. 1C",
            "ID Sbb. Mati Ant. 1D", "ID Kond. Kontr. Mati", "Sebab Dsr. Kematian", "ID Cr. Byr", "Cara Bayar"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbKanker.setModel(tabMode);
        tbKanker.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbKanker.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 56; i++) {
            TableColumn column = tbKanker.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(70);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(65);
            } else if (i == 5) {
                column.setPreferredWidth(40);
            } else if (i == 6) {
                column.setPreferredWidth(110);
            } else if (i == 7) {
                column.setPreferredWidth(60);
            } else if (i == 8) {
                column.setPreferredWidth(90);
            } else if (i == 9) {
                column.setPreferredWidth(120);
            } else if (i == 10) {
                column.setPreferredWidth(290);
            } else if (i == 11) {
                column.setPreferredWidth(55);
            } else if (i == 12) {
                column.setPreferredWidth(110);
            } else if (i == 13) {
                column.setPreferredWidth(55);
            } else if (i == 14) {
                column.setPreferredWidth(110);
            } else if (i == 15) {
                column.setPreferredWidth(55);
            } else if (i == 16) {
                column.setPreferredWidth(110);
            } else if (i == 17) {
                column.setPreferredWidth(55);
            } else if (i == 18) {
                column.setPreferredWidth(160);
            } else if (i == 19) {
                column.setPreferredWidth(250);
            } else if (i == 20) {
                column.setPreferredWidth(80);
            } else if (i == 21) {
                column.setPreferredWidth(160);
            } else if (i == 22) {
                column.setPreferredWidth(80);
            } else if (i == 23) {
                column.setPreferredWidth(160);
            } else if (i == 24) {
                column.setPreferredWidth(80);
            } else if (i == 25) {
                column.setPreferredWidth(160);
            } else if (i == 26) {
                column.setPreferredWidth(80);
            } else if (i == 27) {
                column.setPreferredWidth(160);
            } else if (i == 28) {
                column.setPreferredWidth(100);
            } else if (i == 29) {
                column.setPreferredWidth(75);
            } else if (i == 30) {
                column.setPreferredWidth(60);
            } else if (i == 31) {
                column.setPreferredWidth(260);
            } else if (i == 32) {
                column.setPreferredWidth(80);
            } else if (i == 33) {
                column.setPreferredWidth(165);
            } else if (i == 34) {
                column.setPreferredWidth(250);
            } else if (i == 35) {
                column.setPreferredWidth(80);
            } else if (i == 36) {
                column.setPreferredWidth(80);
            } else if (i == 37) {
                column.setPreferredWidth(160);
            } else if (i == 38) {
                column.setPreferredWidth(100);
            } else if (i == 39) {
                column.setPreferredWidth(120);
            } else if (i == 40) {
                column.setPreferredWidth(120);
            } else if (i == 41) {
                column.setPreferredWidth(120);
            } else if (i == 42) {
                column.setPreferredWidth(90);
            } else if (i == 43) {
                column.setPreferredWidth(75);
            } else if (i == 44) {
                column.setPreferredWidth(60);
            } else if (i == 45) {
                column.setPreferredWidth(170);
            } else if (i == 46) {
                column.setPreferredWidth(90);
            } else if (i == 47) {
                column.setPreferredWidth(160);
            } else if (i == 48) {
                column.setPreferredWidth(120);
            } else if (i == 49) {
                column.setPreferredWidth(120);
            } else if (i == 50) {
                column.setPreferredWidth(120);
            } else if (i == 51) {
                column.setPreferredWidth(120);
            } else if (i == 52) {
                column.setPreferredWidth(120);
            } else if (i == 53) {
                column.setPreferredWidth(250);
            } else if (i == 54) {
                column.setPreferredWidth(60);
            } else if (i == 55) {
                column.setPreferredWidth(200);
            }
        }
        tbKanker.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        NIK.setDocument(new batasInput((byte)17).getOnlyAngka(NIK));
        TNoTelp.setDocument(new batasInput((byte) 13).getOnlyAngka(TNoTelp));
        
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
        
        jk.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgDataKanker")) {
                    if (jk.getTable().getSelectedRow() != -1) {
                        kdjk.setText(jk.getTable().getValueAt(jk.getTable().getSelectedRow(), 0).toString());
                        nmjk.setText(jk.getTable().getValueAt(jk.getTable().getSelectedRow(), 1).toString());
                        BtnJK.requestFocus();
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
        
        jk.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    jk.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        }); 
        
        prop.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgDataKanker")) {
                    if (prop.getTable().getSelectedRow() != -1) {
                        if (pilihan == 1) {
                            KdProp.setText(prop.getTable().getValueAt(prop.getTable().getSelectedRow(), 0).toString());
                            Propinsi.setText(prop.getTable().getValueAt(prop.getTable().getSelectedRow(), 1).toString());
                            BtnPropinsi.requestFocus();
                        } else if (pilihan == 2) {
                            KdPropDom.setText(prop.getTable().getValueAt(prop.getTable().getSelectedRow(), 0).toString());
                            PropinsiDom.setText(prop.getTable().getValueAt(prop.getTable().getSelectedRow(), 1).toString());
                            BtnPropinsiDom.requestFocus();
                        }
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
        
        prop.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    prop.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        kab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgDataKanker")) {
                    if (kab.getTable().getSelectedRow() != -1) {
                        if (pilihan == 1) {
                            KdKab.setText(kab.getTable().getValueAt(kab.getTable().getSelectedRow(), 0).toString());
                            Kabupaten.setText(kab.getTable().getValueAt(kab.getTable().getSelectedRow(), 1).toString());
                            BtnKabupaten.requestFocus();
                        } else if (pilihan == 2) {
                            KdKabDom.setText(kab.getTable().getValueAt(kab.getTable().getSelectedRow(), 0).toString());
                            KabupatenDom.setText(kab.getTable().getValueAt(kab.getTable().getSelectedRow(), 1).toString());
                            BtnKabupatenDom.requestFocus();
                        }
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
        
        kab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    kab.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        }); 
        
        kec.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgDataKanker")) {
                    if (kec.getTable().getSelectedRow() != -1) {
                        if (pilihan == 1) {
                            KdKec.setText(kec.getTable().getValueAt(kec.getTable().getSelectedRow(), 0).toString());
                            Kecamatan.setText(kec.getTable().getValueAt(kec.getTable().getSelectedRow(), 1).toString());
                            BtnKecamatan.requestFocus();
                        } else if (pilihan == 2) {
                            KdKecDom.setText(kec.getTable().getValueAt(kec.getTable().getSelectedRow(), 0).toString());
                            KecamatanDom.setText(kec.getTable().getValueAt(kec.getTable().getSelectedRow(), 1).toString());
                            BtnKecamatanDom.requestFocus();
                        }
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
        
        kec.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    kec.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        }); 
        
        kel.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgDataKanker")) {
                    if (kel.getTable().getSelectedRow() != -1) {
                        if (pilihan == 1) {
                            KdKel.setText(kel.getTable().getValueAt(kel.getTable().getSelectedRow(), 0).toString());
                            Kelurahan.setText(kel.getTable().getValueAt(kel.getTable().getSelectedRow(), 1).toString());
                            BtnKelurahan.requestFocus();
                        } else if (pilihan == 2) {
                            KdKelDom.setText(kel.getTable().getValueAt(kel.getTable().getSelectedRow(), 0).toString());
                            KelurahanDom.setText(kel.getTable().getValueAt(kel.getTable().getSelectedRow(), 1).toString());
                            BtnKelurahanDom.requestFocus();
                        }
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
        
        kel.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    kel.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        caraMsk.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgDataKanker")) {
                    if (caraMsk.getTable().getSelectedRow() != -1) {
                        Kdcm.setText(caraMsk.getTable().getValueAt(caraMsk.getTable().getSelectedRow(), 0).toString());
                        cara_masuk.setText(caraMsk.getTable().getValueAt(caraMsk.getTable().getSelectedRow(), 1).toString());
                        BtnCaraMasuk.requestFocus();
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
        
        caraMsk.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    caraMsk.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        asalRujuk.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgDataKanker")) {
                    if (asalRujuk.getTable().getSelectedRow() != -1) {
                        Kdrujukan.setText(asalRujuk.getTable().getValueAt(asalRujuk.getTable().getSelectedRow(), 0).toString());
                        asalRujukan.setText(asalRujuk.getTable().getValueAt(asalRujuk.getTable().getSelectedRow(), 1).toString());
                        BtnAsalRujukan.requestFocus();
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
        
        asalRujuk.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    asalRujuk.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        penyakit.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (penyakit.getTable().getSelectedRow() != -1) {
                    if (pilihan == 1) {
                        KdicdDM.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 0).toString());
                        diagnosaDM.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 1).toString());
                        BtnICDdm.requestFocus();
                    } else if (pilihan == 2) {
                        KddiagUtama.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 0).toString());
                        diagnosaUtama.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 1).toString());
                        BtnDiagUtam.requestFocus();
                    } else if (pilihan == 3) {
                        KddiagSekun1.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 0).toString());
                        diagnosaSekunder1.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 1).toString());
                        BtnDiagSekun1.requestFocus();
                    } else if (pilihan == 4) {
                        KddiagSekun2.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 0).toString());
                        diagnosaSekunder2.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 1).toString());
                        BtnDiagSekun2.requestFocus();
                    } else if (pilihan == 5) {
                        KddiagSekun3.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 0).toString());
                        diagnosaSekunder3.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 1).toString());
                        BtnDiagSekun3.requestFocus();
                    } else if (pilihan == 6) {
                        KdsbbMati1A.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 0).toString());
                        diagnosaSbbMati1A.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 1).toString());
                        BtnDiagSbbMati1A.requestFocus();
                    } else if (pilihan == 7) {
                        KdsbbMati1B.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 0).toString());
                        diagnosaSbbMati1B.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 1).toString());
                        BtnDiagSbbMati1B.requestFocus();
                    } else if (pilihan == 8) {
                        KdsbbMati1C.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 0).toString());
                        diagnosaSbbMati1C.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 1).toString());
                        BtnDiagSbbMati1C.requestFocus();
                    } else if (pilihan == 9) {
                        KdsbbMati1D.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 0).toString());
                        diagnosaSbbMati1D.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 1).toString());
                        BtnDiagSbbMati1D.requestFocus();
                    } else if (pilihan == 10) {
                        Kdkondisi.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 0).toString());
                        diagnosaKondisi.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 1).toString());
                        BtnDiagKondisi.requestFocus();
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
        
        subInst.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgDataKanker")) {
                    if (subInst.getTable().getSelectedRow() != -1) {
                        Kdsub.setText(subInst.getTable().getValueAt(subInst.getTable().getSelectedRow(), 0).toString());
                        SubInstalasi.setText(subInst.getTable().getValueAt(subInst.getTable().getSelectedRow(), 1).toString());
                        BtnSub.requestFocus();
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
        
        subInst.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    subInst.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        carak.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgDataKanker")) {
                    if (carak.getTable().getSelectedRow() != -1) {
                        KdcaraKlr.setText(carak.getTable().getValueAt(carak.getTable().getSelectedRow(), 0).toString());
                        CaraKeluar.setText(carak.getTable().getValueAt(carak.getTable().getSelectedRow(), 1).toString());
                        BtnCaraKeluar.requestFocus();
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
        
        carak.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    carak.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        kelar.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgDataKanker")) {
                    if (kelar.getTable().getSelectedRow() != -1) {
                        KdkeadaanKlr.setText(kelar.getTable().getValueAt(kelar.getTable().getSelectedRow(), 0).toString());
                        KeadaanKeluar.setText(kelar.getTable().getValueAt(kelar.getTable().getSelectedRow(), 1).toString());
                        BtnKeadaanKeluar.requestFocus();
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
        
        kelar.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    kelar.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        cabar.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgDataKanker")) {
                    if (cabar.getTable().getSelectedRow() != -1) {
                        KdcaraByr.setText(cabar.getTable().getValueAt(cabar.getTable().getSelectedRow(), 0).toString());
                        CaraBayar.setText(cabar.getTable().getValueAt(cabar.getTable().getSelectedRow(), 1).toString());
                        BtnCaraBayar.requestFocus();
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
        
        cabar.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    cabar.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
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
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel58 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel59 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput1 = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TNmPasien = new widget.TextBox();
        jLabel4 = new widget.Label();
        tglLahr = new widget.TextBox();
        jLabel5 = new widget.Label();
        Umur = new widget.TextBox();
        jLabel9 = new widget.Label();
        NoKartu = new widget.TextBox();
        jLabel10 = new widget.Label();
        NIK = new widget.TextBox();
        jLabel13 = new widget.Label();
        Alamat = new widget.TextBox();
        Kelurahan = new widget.TextBox();
        BtnKelurahan = new widget.Button();
        Kecamatan = new widget.TextBox();
        BtnKecamatan = new widget.Button();
        BtnKabupaten = new widget.Button();
        Kabupaten = new widget.TextBox();
        KdKel = new widget.TextBox();
        KdKec = new widget.TextBox();
        KdKab = new widget.TextBox();
        KdProp = new widget.TextBox();
        Propinsi = new widget.TextBox();
        BtnPropinsi = new widget.Button();
        ChkDomisili = new widget.CekBox();
        jLabel23 = new widget.Label();
        kdjk = new widget.TextBox();
        nmjk = new widget.TextBox();
        BtnJK = new widget.Button();
        TNoTelp = new widget.TextBox();
        jLabel14 = new widget.Label();
        jLabel15 = new widget.Label();
        TAlamatDom = new widget.TextBox();
        KdPropDom = new widget.TextBox();
        PropinsiDom = new widget.TextBox();
        BtnPropinsiDom = new widget.Button();
        KdKabDom = new widget.TextBox();
        KabupatenDom = new widget.TextBox();
        BtnKabupatenDom = new widget.Button();
        KdKecDom = new widget.TextBox();
        KecamatanDom = new widget.TextBox();
        BtnKecamatanDom = new widget.Button();
        KdKelDom = new widget.TextBox();
        KelurahanDom = new widget.TextBox();
        BtnKelurahanDom = new widget.Button();
        jLabel16 = new widget.Label();
        tgl_masuk = new widget.TextBox();
        jLabel17 = new widget.Label();
        Kdcm = new widget.TextBox();
        cara_masuk = new widget.TextBox();
        BtnCaraMasuk = new widget.Button();
        jLabel18 = new widget.Label();
        Kdrujukan = new widget.TextBox();
        asalRujukan = new widget.TextBox();
        BtnAsalRujukan = new widget.Button();
        jLabel19 = new widget.Label();
        asalRujukanFasyankes = new widget.TextBox();
        jLabel20 = new widget.Label();
        Scroll6 = new widget.ScrollPane();
        TDiagnosa = new widget.TextArea();
        KdicdDM = new widget.TextBox();
        diagnosaDM = new widget.TextBox();
        BtnICDdm = new widget.Button();
        jLabel21 = new widget.Label();
        Kdsub = new widget.TextBox();
        SubInstalasi = new widget.TextBox();
        BtnSub = new widget.Button();
        jLabel22 = new widget.Label();
        jLabel24 = new widget.Label();
        KddiagUtama = new widget.TextBox();
        diagnosaUtama = new widget.TextBox();
        BtnDiagUtam = new widget.Button();
        jLabel25 = new widget.Label();
        KddiagSekun1 = new widget.TextBox();
        diagnosaSekunder1 = new widget.TextBox();
        BtnDiagSekun1 = new widget.Button();
        jLabel26 = new widget.Label();
        KddiagSekun2 = new widget.TextBox();
        diagnosaSekunder2 = new widget.TextBox();
        BtnDiagSekun2 = new widget.Button();
        jLabel27 = new widget.Label();
        KddiagSekun3 = new widget.TextBox();
        diagnosaSekunder3 = new widget.TextBox();
        BtnDiagSekun3 = new widget.Button();
        jLabel28 = new widget.Label();
        tglDiagnosa = new widget.Tanggal();
        jLabel29 = new widget.Label();
        tglKeluar = new widget.TextBox();
        jLabel30 = new widget.Label();
        KdcaraKlr = new widget.TextBox();
        CaraKeluar = new widget.TextBox();
        BtnCaraKeluar = new widget.Button();
        jLabel31 = new widget.Label();
        KdkeadaanKlr = new widget.TextBox();
        KeadaanKeluar = new widget.TextBox();
        BtnKeadaanKeluar = new widget.Button();
        jLabel32 = new widget.Label();
        KdcaraByr = new widget.TextBox();
        CaraBayar = new widget.TextBox();
        BtnCaraBayar = new widget.Button();
        jLabel33 = new widget.Label();
        KdsbbMati1A = new widget.TextBox();
        diagnosaSbbMati1A = new widget.TextBox();
        BtnDiagSbbMati1A = new widget.Button();
        jLabel34 = new widget.Label();
        KdsbbMati1B = new widget.TextBox();
        diagnosaSbbMati1B = new widget.TextBox();
        BtnDiagSbbMati1B = new widget.Button();
        jLabel35 = new widget.Label();
        KdsbbMati1C = new widget.TextBox();
        diagnosaSbbMati1C = new widget.TextBox();
        BtnDiagSbbMati1C = new widget.Button();
        jLabel36 = new widget.Label();
        KdsbbMati1D = new widget.TextBox();
        diagnosaSbbMati1D = new widget.TextBox();
        BtnDiagSbbMati1D = new widget.Button();
        jLabel37 = new widget.Label();
        Kdkondisi = new widget.TextBox();
        diagnosaKondisi = new widget.TextBox();
        BtnDiagKondisi = new widget.Button();
        jLabel38 = new widget.Label();
        sebabDasarMati = new widget.TextBox();
        jLabel39 = new widget.Label();
        jLabel40 = new widget.Label();
        sttsPulang = new widget.TextBox();
        jnsBayar = new widget.TextBox();
        jLabel41 = new widget.Label();
        nmRuangPoli = new widget.TextBox();
        BtnDelAsalRujukan = new widget.Button();
        BtnDelDiagSekun1 = new widget.Button();
        BtnDelDiagSekun2 = new widget.Button();
        BtnDelDiagSekun3 = new widget.Button();
        BtnDelSbbMati1A = new widget.Button();
        BtnDelSbbMati1B = new widget.Button();
        BtnDelSbbMati1C = new widget.Button();
        BtnDelSbbMati1D = new widget.Button();
        BtnDelKondisi = new widget.Button();
        internalFrame3 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbKanker = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Pasien Penyakit Kanker ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Tgl. Simpan : ");
        jLabel58.setName("jLabel58"); // NOI18N
        jLabel58.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass9.add(jLabel58);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-07-2022" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("s.d");
        jLabel59.setName("jLabel59"); // NOI18N
        jLabel59.setPreferredSize(new java.awt.Dimension(24, 23));
        panelGlass9.add(jLabel59);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-07-2022" }));
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
        TCari.setPreferredSize(new java.awt.Dimension(210, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('T');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+T");
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

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass9.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(LCount);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setForeground(new java.awt.Color(0, 0, 0));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 1100));

        FormInput1.setBackground(new java.awt.Color(255, 255, 255));
        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(100, 865));
        FormInput1.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Pasien :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput1.add(jLabel3);
        jLabel3.setBounds(0, 10, 101, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput1.add(TNoRw);
        TNoRw.setBounds(104, 10, 140, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput1.add(TNoRM);
        TNoRM.setBounds(246, 10, 80, 23);

        TNmPasien.setEditable(false);
        TNmPasien.setForeground(new java.awt.Color(0, 0, 0));
        TNmPasien.setName("TNmPasien"); // NOI18N
        FormInput1.add(TNmPasien);
        TNmPasien.setBounds(328, 10, 397, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tgl. Lahir :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput1.add(jLabel4);
        jLabel4.setBounds(0, 40, 101, 23);

        tglLahr.setEditable(false);
        tglLahr.setForeground(new java.awt.Color(0, 0, 0));
        tglLahr.setName("tglLahr"); // NOI18N
        FormInput1.add(tglLahr);
        tglLahr.setBounds(104, 40, 100, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Umur :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput1.add(jLabel5);
        jLabel5.setBounds(204, 40, 45, 23);

        Umur.setEditable(false);
        Umur.setForeground(new java.awt.Color(0, 0, 0));
        Umur.setName("Umur"); // NOI18N
        FormInput1.add(Umur);
        Umur.setBounds(255, 40, 65, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("No. JKN :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput1.add(jLabel9);
        jLabel9.setBounds(322, 40, 50, 23);

        NoKartu.setEditable(false);
        NoKartu.setForeground(new java.awt.Color(0, 0, 0));
        NoKartu.setName("NoKartu"); // NOI18N
        FormInput1.add(NoKartu);
        NoKartu.setBounds(375, 40, 120, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("NIK :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput1.add(jLabel10);
        jLabel10.setBounds(495, 40, 40, 23);

        NIK.setEditable(false);
        NIK.setForeground(new java.awt.Color(0, 0, 0));
        NIK.setName("NIK"); // NOI18N
        FormInput1.add(NIK);
        NIK.setBounds(540, 40, 185, 23);

        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("Jenis Kelamin :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput1.add(jLabel13);
        jLabel13.setBounds(0, 70, 101, 23);

        Alamat.setForeground(new java.awt.Color(0, 0, 0));
        Alamat.setText("ALAMAT");
        Alamat.setName("Alamat"); // NOI18N
        Alamat.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                AlamatMouseMoved(evt);
            }
        });
        Alamat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AlamatMouseExited(evt);
            }
        });
        Alamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatKeyPressed(evt);
            }
        });
        FormInput1.add(Alamat);
        Alamat.setBounds(435, 70, 290, 23);

        Kelurahan.setEditable(false);
        Kelurahan.setForeground(new java.awt.Color(0, 0, 0));
        Kelurahan.setText("KELURAHAN");
        Kelurahan.setName("Kelurahan"); // NOI18N
        Kelurahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KelurahanKeyPressed(evt);
            }
        });
        FormInput1.add(Kelurahan);
        Kelurahan.setBounds(483, 130, 210, 23);

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
        FormInput1.add(BtnKelurahan);
        BtnKelurahan.setBounds(697, 130, 28, 23);

        Kecamatan.setEditable(false);
        Kecamatan.setForeground(new java.awt.Color(0, 0, 0));
        Kecamatan.setText("KECAMATAN");
        Kecamatan.setName("Kecamatan"); // NOI18N
        Kecamatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KecamatanKeyPressed(evt);
            }
        });
        FormInput1.add(Kecamatan);
        Kecamatan.setBounds(167, 130, 210, 23);

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
        FormInput1.add(BtnKecamatan);
        BtnKecamatan.setBounds(380, 130, 28, 23);

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
        FormInput1.add(BtnKabupaten);
        BtnKabupaten.setBounds(697, 100, 28, 23);

        Kabupaten.setEditable(false);
        Kabupaten.setForeground(new java.awt.Color(0, 0, 0));
        Kabupaten.setText("KABUPATEN");
        Kabupaten.setName("Kabupaten"); // NOI18N
        Kabupaten.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KabupatenKeyPressed(evt);
            }
        });
        FormInput1.add(Kabupaten);
        Kabupaten.setBounds(483, 100, 210, 23);

        KdKel.setEditable(false);
        KdKel.setForeground(new java.awt.Color(0, 0, 0));
        KdKel.setName("KdKel"); // NOI18N
        KdKel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdKelKeyPressed(evt);
            }
        });
        FormInput1.add(KdKel);
        KdKel.setBounds(421, 130, 60, 23);

        KdKec.setEditable(false);
        KdKec.setForeground(new java.awt.Color(0, 0, 0));
        KdKec.setName("KdKec"); // NOI18N
        KdKec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdKecKeyPressed(evt);
            }
        });
        FormInput1.add(KdKec);
        KdKec.setBounds(104, 130, 60, 23);

        KdKab.setEditable(false);
        KdKab.setForeground(new java.awt.Color(0, 0, 0));
        KdKab.setName("KdKab"); // NOI18N
        FormInput1.add(KdKab);
        KdKab.setBounds(421, 100, 60, 23);

        KdProp.setEditable(false);
        KdProp.setForeground(new java.awt.Color(0, 0, 0));
        KdProp.setName("KdProp"); // NOI18N
        FormInput1.add(KdProp);
        KdProp.setBounds(104, 100, 60, 23);

        Propinsi.setEditable(false);
        Propinsi.setForeground(new java.awt.Color(0, 0, 0));
        Propinsi.setText("PROPINSI");
        Propinsi.setName("Propinsi"); // NOI18N
        Propinsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PropinsiKeyPressed(evt);
            }
        });
        FormInput1.add(Propinsi);
        Propinsi.setBounds(167, 100, 210, 23);

        BtnPropinsi.setForeground(new java.awt.Color(0, 0, 0));
        BtnPropinsi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPropinsi.setMnemonic('3');
        BtnPropinsi.setToolTipText("ALt+3");
        BtnPropinsi.setName("BtnPropinsi"); // NOI18N
        BtnPropinsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPropinsiActionPerformed(evt);
            }
        });
        FormInput1.add(BtnPropinsi);
        BtnPropinsi.setBounds(380, 100, 28, 23);

        ChkDomisili.setBackground(new java.awt.Color(255, 255, 250));
        ChkDomisili.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDomisili.setForeground(new java.awt.Color(255, 0, 0));
        ChkDomisili.setText("Domisili Disamakan dg. Alamat KTP/KK");
        ChkDomisili.setBorderPainted(true);
        ChkDomisili.setBorderPaintedFlat(true);
        ChkDomisili.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkDomisili.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkDomisili.setName("ChkDomisili"); // NOI18N
        ChkDomisili.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkDomisili.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkDomisiliActionPerformed(evt);
            }
        });
        FormInput1.add(ChkDomisili);
        ChkDomisili.setBounds(275, 160, 230, 23);

        jLabel23.setForeground(new java.awt.Color(255, 0, 0));
        jLabel23.setText("Alamat :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput1.add(jLabel23);
        jLabel23.setBounds(370, 70, 60, 23);

        kdjk.setEditable(false);
        kdjk.setForeground(new java.awt.Color(0, 0, 0));
        kdjk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        kdjk.setName("kdjk"); // NOI18N
        FormInput1.add(kdjk);
        kdjk.setBounds(104, 70, 30, 23);

        nmjk.setEditable(false);
        nmjk.setForeground(new java.awt.Color(0, 0, 0));
        nmjk.setName("nmjk"); // NOI18N
        FormInput1.add(nmjk);
        nmjk.setBounds(138, 70, 200, 23);

        BtnJK.setForeground(new java.awt.Color(0, 0, 0));
        BtnJK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnJK.setMnemonic('3');
        BtnJK.setToolTipText("ALt+3");
        BtnJK.setName("BtnJK"); // NOI18N
        BtnJK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnJKActionPerformed(evt);
            }
        });
        FormInput1.add(BtnJK);
        BtnJK.setBounds(340, 70, 28, 23);

        TNoTelp.setForeground(new java.awt.Color(0, 0, 0));
        TNoTelp.setName("TNoTelp"); // NOI18N
        TNoTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoTelpKeyPressed(evt);
            }
        });
        FormInput1.add(TNoTelp);
        TNoTelp.setBounds(104, 160, 170, 23);

        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("No. HP/Telp. : ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput1.add(jLabel14);
        jLabel14.setBounds(0, 160, 101, 23);

        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("Alamat Domisili : ");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput1.add(jLabel15);
        jLabel15.setBounds(0, 190, 101, 23);

        TAlamatDom.setForeground(new java.awt.Color(0, 0, 0));
        TAlamatDom.setText("ALAMAT DOMISILI");
        TAlamatDom.setName("TAlamatDom"); // NOI18N
        TAlamatDom.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                TAlamatDomMouseMoved(evt);
            }
        });
        TAlamatDom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TAlamatDomMouseExited(evt);
            }
        });
        TAlamatDom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlamatDomKeyPressed(evt);
            }
        });
        FormInput1.add(TAlamatDom);
        TAlamatDom.setBounds(104, 190, 310, 23);

        KdPropDom.setEditable(false);
        KdPropDom.setForeground(new java.awt.Color(0, 0, 0));
        KdPropDom.setName("KdPropDom"); // NOI18N
        FormInput1.add(KdPropDom);
        KdPropDom.setBounds(421, 190, 60, 23);

        PropinsiDom.setEditable(false);
        PropinsiDom.setForeground(new java.awt.Color(0, 0, 0));
        PropinsiDom.setText("PROPINSI DOMISILI");
        PropinsiDom.setName("PropinsiDom"); // NOI18N
        FormInput1.add(PropinsiDom);
        PropinsiDom.setBounds(483, 190, 210, 23);

        BtnPropinsiDom.setForeground(new java.awt.Color(0, 0, 0));
        BtnPropinsiDom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPropinsiDom.setMnemonic('2');
        BtnPropinsiDom.setToolTipText("ALt+2");
        BtnPropinsiDom.setName("BtnPropinsiDom"); // NOI18N
        BtnPropinsiDom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPropinsiDomActionPerformed(evt);
            }
        });
        FormInput1.add(BtnPropinsiDom);
        BtnPropinsiDom.setBounds(697, 190, 28, 23);

        KdKabDom.setEditable(false);
        KdKabDom.setForeground(new java.awt.Color(0, 0, 0));
        KdKabDom.setName("KdKabDom"); // NOI18N
        KdKabDom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdKabDomKeyPressed(evt);
            }
        });
        FormInput1.add(KdKabDom);
        KdKabDom.setBounds(104, 220, 60, 23);

        KabupatenDom.setEditable(false);
        KabupatenDom.setForeground(new java.awt.Color(0, 0, 0));
        KabupatenDom.setText("KABUPATEN DOMISILI");
        KabupatenDom.setName("KabupatenDom"); // NOI18N
        KabupatenDom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KabupatenDomKeyPressed(evt);
            }
        });
        FormInput1.add(KabupatenDom);
        KabupatenDom.setBounds(167, 220, 210, 23);

        BtnKabupatenDom.setForeground(new java.awt.Color(0, 0, 0));
        BtnKabupatenDom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKabupatenDom.setMnemonic('3');
        BtnKabupatenDom.setToolTipText("ALt+3");
        BtnKabupatenDom.setName("BtnKabupatenDom"); // NOI18N
        BtnKabupatenDom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKabupatenDomActionPerformed(evt);
            }
        });
        FormInput1.add(BtnKabupatenDom);
        BtnKabupatenDom.setBounds(380, 220, 28, 23);

        KdKecDom.setEditable(false);
        KdKecDom.setForeground(new java.awt.Color(0, 0, 0));
        KdKecDom.setName("KdKecDom"); // NOI18N
        FormInput1.add(KdKecDom);
        KdKecDom.setBounds(421, 220, 60, 23);

        KecamatanDom.setEditable(false);
        KecamatanDom.setForeground(new java.awt.Color(0, 0, 0));
        KecamatanDom.setText("KECAMATAN DOMISILI");
        KecamatanDom.setName("KecamatanDom"); // NOI18N
        KecamatanDom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KecamatanDomKeyPressed(evt);
            }
        });
        FormInput1.add(KecamatanDom);
        KecamatanDom.setBounds(483, 220, 210, 23);

        BtnKecamatanDom.setForeground(new java.awt.Color(0, 0, 0));
        BtnKecamatanDom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKecamatanDom.setMnemonic('2');
        BtnKecamatanDom.setToolTipText("ALt+2");
        BtnKecamatanDom.setName("BtnKecamatanDom"); // NOI18N
        BtnKecamatanDom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKecamatanDomActionPerformed(evt);
            }
        });
        FormInput1.add(BtnKecamatanDom);
        BtnKecamatanDom.setBounds(697, 220, 28, 23);

        KdKelDom.setEditable(false);
        KdKelDom.setForeground(new java.awt.Color(0, 0, 0));
        KdKelDom.setName("KdKelDom"); // NOI18N
        KdKelDom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdKelDomKeyPressed(evt);
            }
        });
        FormInput1.add(KdKelDom);
        KdKelDom.setBounds(104, 250, 60, 23);

        KelurahanDom.setEditable(false);
        KelurahanDom.setForeground(new java.awt.Color(0, 0, 0));
        KelurahanDom.setText("KELURAHAN DOMISILI");
        KelurahanDom.setName("KelurahanDom"); // NOI18N
        KelurahanDom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KelurahanDomKeyPressed(evt);
            }
        });
        FormInput1.add(KelurahanDom);
        KelurahanDom.setBounds(167, 250, 210, 23);

        BtnKelurahanDom.setForeground(new java.awt.Color(0, 0, 0));
        BtnKelurahanDom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKelurahanDom.setMnemonic('3');
        BtnKelurahanDom.setToolTipText("ALt+3");
        BtnKelurahanDom.setName("BtnKelurahanDom"); // NOI18N
        BtnKelurahanDom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKelurahanDomActionPerformed(evt);
            }
        });
        FormInput1.add(BtnKelurahanDom);
        BtnKelurahanDom.setBounds(380, 250, 28, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Tgl. Masuk/Reg. : ");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput1.add(jLabel16);
        jLabel16.setBounds(515, 160, 110, 23);

        tgl_masuk.setEditable(false);
        tgl_masuk.setForeground(new java.awt.Color(0, 0, 0));
        tgl_masuk.setName("tgl_masuk"); // NOI18N
        FormInput1.add(tgl_masuk);
        tgl_masuk.setBounds(625, 160, 100, 23);

        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("Cara Masuk : ");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput1.add(jLabel17);
        jLabel17.setBounds(415, 250, 70, 23);

        Kdcm.setEditable(false);
        Kdcm.setForeground(new java.awt.Color(0, 0, 0));
        Kdcm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Kdcm.setName("Kdcm"); // NOI18N
        FormInput1.add(Kdcm);
        Kdcm.setBounds(485, 250, 30, 23);

        cara_masuk.setEditable(false);
        cara_masuk.setForeground(new java.awt.Color(0, 0, 0));
        cara_masuk.setName("cara_masuk"); // NOI18N
        FormInput1.add(cara_masuk);
        cara_masuk.setBounds(518, 250, 175, 23);

        BtnCaraMasuk.setForeground(new java.awt.Color(0, 0, 0));
        BtnCaraMasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnCaraMasuk.setMnemonic('2');
        BtnCaraMasuk.setToolTipText("ALt+2");
        BtnCaraMasuk.setName("BtnCaraMasuk"); // NOI18N
        BtnCaraMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCaraMasukActionPerformed(evt);
            }
        });
        FormInput1.add(BtnCaraMasuk);
        BtnCaraMasuk.setBounds(697, 250, 28, 23);

        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("Asal Rujukan : ");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput1.add(jLabel18);
        jLabel18.setBounds(0, 280, 101, 23);

        Kdrujukan.setEditable(false);
        Kdrujukan.setForeground(new java.awt.Color(0, 0, 0));
        Kdrujukan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Kdrujukan.setName("Kdrujukan"); // NOI18N
        FormInput1.add(Kdrujukan);
        Kdrujukan.setBounds(104, 280, 30, 23);

        asalRujukan.setEditable(false);
        asalRujukan.setForeground(new java.awt.Color(0, 0, 0));
        asalRujukan.setName("asalRujukan"); // NOI18N
        FormInput1.add(asalRujukan);
        asalRujukan.setBounds(137, 280, 556, 23);

        BtnAsalRujukan.setForeground(new java.awt.Color(0, 0, 0));
        BtnAsalRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAsalRujukan.setMnemonic('3');
        BtnAsalRujukan.setToolTipText("ALt+3");
        BtnAsalRujukan.setName("BtnAsalRujukan"); // NOI18N
        BtnAsalRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsalRujukanActionPerformed(evt);
            }
        });
        FormInput1.add(BtnAsalRujukan);
        BtnAsalRujukan.setBounds(697, 280, 28, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Asal Rujukan Fasyankes Lainnya : ");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput1.add(jLabel19);
        jLabel19.setBounds(0, 310, 190, 23);

        asalRujukanFasyankes.setForeground(new java.awt.Color(0, 0, 0));
        asalRujukanFasyankes.setName("asalRujukanFasyankes"); // NOI18N
        FormInput1.add(asalRujukanFasyankes);
        asalRujukanFasyankes.setBounds(195, 310, 530, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Diagnosa Masuk : ");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput1.add(jLabel20);
        jLabel20.setBounds(0, 340, 120, 23);

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        TDiagnosa.setEditable(false);
        TDiagnosa.setColumns(20);
        TDiagnosa.setRows(5);
        TDiagnosa.setName("TDiagnosa"); // NOI18N
        TDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagnosaKeyPressed(evt);
            }
        });
        Scroll6.setViewportView(TDiagnosa);

        FormInput1.add(Scroll6);
        Scroll6.setBounds(120, 340, 230, 55);

        KdicdDM.setEditable(false);
        KdicdDM.setForeground(new java.awt.Color(0, 0, 0));
        KdicdDM.setName("KdicdDM"); // NOI18N
        FormInput1.add(KdicdDM);
        KdicdDM.setBounds(355, 370, 60, 23);

        diagnosaDM.setEditable(false);
        diagnosaDM.setForeground(new java.awt.Color(0, 0, 0));
        diagnosaDM.setName("diagnosaDM"); // NOI18N
        FormInput1.add(diagnosaDM);
        diagnosaDM.setBounds(419, 370, 275, 23);

        BtnICDdm.setForeground(new java.awt.Color(0, 0, 0));
        BtnICDdm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnICDdm.setMnemonic('3');
        BtnICDdm.setToolTipText("ALt+3");
        BtnICDdm.setName("BtnICDdm"); // NOI18N
        BtnICDdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnICDdmActionPerformed(evt);
            }
        });
        FormInput1.add(BtnICDdm);
        BtnICDdm.setBounds(697, 370, 28, 23);

        jLabel21.setForeground(new java.awt.Color(255, 0, 0));
        jLabel21.setText("Sub Instalasi Unit : ");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput1.add(jLabel21);
        jLabel21.setBounds(0, 430, 120, 23);

        Kdsub.setEditable(false);
        Kdsub.setForeground(new java.awt.Color(0, 0, 0));
        Kdsub.setName("Kdsub"); // NOI18N
        FormInput1.add(Kdsub);
        Kdsub.setBounds(120, 430, 50, 23);

        SubInstalasi.setEditable(false);
        SubInstalasi.setForeground(new java.awt.Color(0, 0, 0));
        SubInstalasi.setName("SubInstalasi"); // NOI18N
        FormInput1.add(SubInstalasi);
        SubInstalasi.setBounds(175, 430, 520, 23);

        BtnSub.setForeground(new java.awt.Color(0, 0, 0));
        BtnSub.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSub.setMnemonic('3');
        BtnSub.setToolTipText("ALt+3");
        BtnSub.setName("BtnSub"); // NOI18N
        BtnSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSubActionPerformed(evt);
            }
        });
        FormInput1.add(BtnSub);
        BtnSub.setBounds(697, 430, 28, 23);

        jLabel22.setForeground(new java.awt.Color(255, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("Diagnosa Masuk (ICD 10) : ");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput1.add(jLabel22);
        jLabel22.setBounds(355, 340, 330, 23);

        jLabel24.setForeground(new java.awt.Color(255, 0, 0));
        jLabel24.setText("Diagnosa Utama : ");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput1.add(jLabel24);
        jLabel24.setBounds(0, 460, 120, 23);

        KddiagUtama.setEditable(false);
        KddiagUtama.setForeground(new java.awt.Color(0, 0, 0));
        KddiagUtama.setName("KddiagUtama"); // NOI18N
        FormInput1.add(KddiagUtama);
        KddiagUtama.setBounds(120, 460, 60, 23);

        diagnosaUtama.setEditable(false);
        diagnosaUtama.setForeground(new java.awt.Color(0, 0, 0));
        diagnosaUtama.setName("diagnosaUtama"); // NOI18N
        FormInput1.add(diagnosaUtama);
        diagnosaUtama.setBounds(185, 460, 510, 23);

        BtnDiagUtam.setForeground(new java.awt.Color(0, 0, 0));
        BtnDiagUtam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDiagUtam.setMnemonic('3');
        BtnDiagUtam.setToolTipText("ALt+3");
        BtnDiagUtam.setName("BtnDiagUtam"); // NOI18N
        BtnDiagUtam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDiagUtamActionPerformed(evt);
            }
        });
        FormInput1.add(BtnDiagUtam);
        BtnDiagUtam.setBounds(697, 460, 28, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Diag. Sekunder 1 : ");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput1.add(jLabel25);
        jLabel25.setBounds(0, 490, 120, 23);

        KddiagSekun1.setEditable(false);
        KddiagSekun1.setForeground(new java.awt.Color(0, 0, 0));
        KddiagSekun1.setName("KddiagSekun1"); // NOI18N
        FormInput1.add(KddiagSekun1);
        KddiagSekun1.setBounds(120, 490, 60, 23);

        diagnosaSekunder1.setEditable(false);
        diagnosaSekunder1.setForeground(new java.awt.Color(0, 0, 0));
        diagnosaSekunder1.setName("diagnosaSekunder1"); // NOI18N
        FormInput1.add(diagnosaSekunder1);
        diagnosaSekunder1.setBounds(185, 490, 510, 23);

        BtnDiagSekun1.setForeground(new java.awt.Color(0, 0, 0));
        BtnDiagSekun1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDiagSekun1.setMnemonic('3');
        BtnDiagSekun1.setToolTipText("ALt+3");
        BtnDiagSekun1.setName("BtnDiagSekun1"); // NOI18N
        BtnDiagSekun1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDiagSekun1ActionPerformed(evt);
            }
        });
        FormInput1.add(BtnDiagSekun1);
        BtnDiagSekun1.setBounds(697, 490, 28, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Diag. Sekunder 2 : ");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput1.add(jLabel26);
        jLabel26.setBounds(0, 520, 120, 23);

        KddiagSekun2.setEditable(false);
        KddiagSekun2.setForeground(new java.awt.Color(0, 0, 0));
        KddiagSekun2.setName("KddiagSekun2"); // NOI18N
        FormInput1.add(KddiagSekun2);
        KddiagSekun2.setBounds(120, 520, 60, 23);

        diagnosaSekunder2.setEditable(false);
        diagnosaSekunder2.setForeground(new java.awt.Color(0, 0, 0));
        diagnosaSekunder2.setName("diagnosaSekunder2"); // NOI18N
        FormInput1.add(diagnosaSekunder2);
        diagnosaSekunder2.setBounds(185, 520, 510, 23);

        BtnDiagSekun2.setForeground(new java.awt.Color(0, 0, 0));
        BtnDiagSekun2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDiagSekun2.setMnemonic('3');
        BtnDiagSekun2.setToolTipText("ALt+3");
        BtnDiagSekun2.setName("BtnDiagSekun2"); // NOI18N
        BtnDiagSekun2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDiagSekun2ActionPerformed(evt);
            }
        });
        FormInput1.add(BtnDiagSekun2);
        BtnDiagSekun2.setBounds(697, 520, 28, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Diag. Sekunder 3 : ");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput1.add(jLabel27);
        jLabel27.setBounds(0, 550, 120, 23);

        KddiagSekun3.setEditable(false);
        KddiagSekun3.setForeground(new java.awt.Color(0, 0, 0));
        KddiagSekun3.setName("KddiagSekun3"); // NOI18N
        FormInput1.add(KddiagSekun3);
        KddiagSekun3.setBounds(120, 550, 60, 23);

        diagnosaSekunder3.setEditable(false);
        diagnosaSekunder3.setForeground(new java.awt.Color(0, 0, 0));
        diagnosaSekunder3.setName("diagnosaSekunder3"); // NOI18N
        FormInput1.add(diagnosaSekunder3);
        diagnosaSekunder3.setBounds(185, 550, 510, 23);

        BtnDiagSekun3.setForeground(new java.awt.Color(0, 0, 0));
        BtnDiagSekun3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDiagSekun3.setMnemonic('3');
        BtnDiagSekun3.setToolTipText("ALt+3");
        BtnDiagSekun3.setName("BtnDiagSekun3"); // NOI18N
        BtnDiagSekun3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDiagSekun3ActionPerformed(evt);
            }
        });
        FormInput1.add(BtnDiagSekun3);
        BtnDiagSekun3.setBounds(697, 550, 28, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Tgl. Diagnosa : ");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput1.add(jLabel28);
        jLabel28.setBounds(0, 580, 120, 23);

        tglDiagnosa.setEditable(false);
        tglDiagnosa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-07-2022" }));
        tglDiagnosa.setDisplayFormat("dd-MM-yyyy");
        tglDiagnosa.setName("tglDiagnosa"); // NOI18N
        tglDiagnosa.setOpaque(false);
        FormInput1.add(tglDiagnosa);
        tglDiagnosa.setBounds(120, 580, 90, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Tgl. Keluar : ");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput1.add(jLabel29);
        jLabel29.setBounds(210, 580, 70, 23);

        tglKeluar.setEditable(false);
        tglKeluar.setForeground(new java.awt.Color(0, 0, 0));
        tglKeluar.setName("tglKeluar"); // NOI18N
        FormInput1.add(tglKeluar);
        tglKeluar.setBounds(280, 580, 90, 23);

        jLabel30.setForeground(new java.awt.Color(255, 0, 0));
        jLabel30.setText("Cara Keluar : ");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput1.add(jLabel30);
        jLabel30.setBounds(370, 580, 78, 23);

        KdcaraKlr.setEditable(false);
        KdcaraKlr.setForeground(new java.awt.Color(0, 0, 0));
        KdcaraKlr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        KdcaraKlr.setName("KdcaraKlr"); // NOI18N
        FormInput1.add(KdcaraKlr);
        KdcaraKlr.setBounds(450, 580, 30, 23);

        CaraKeluar.setEditable(false);
        CaraKeluar.setForeground(new java.awt.Color(0, 0, 0));
        CaraKeluar.setName("CaraKeluar"); // NOI18N
        FormInput1.add(CaraKeluar);
        CaraKeluar.setBounds(485, 580, 210, 23);

        BtnCaraKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnCaraKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnCaraKeluar.setMnemonic('3');
        BtnCaraKeluar.setToolTipText("ALt+3");
        BtnCaraKeluar.setName("BtnCaraKeluar"); // NOI18N
        BtnCaraKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCaraKeluarActionPerformed(evt);
            }
        });
        FormInput1.add(BtnCaraKeluar);
        BtnCaraKeluar.setBounds(697, 580, 28, 23);

        jLabel31.setForeground(new java.awt.Color(255, 0, 0));
        jLabel31.setText("Keadaan Keluar : ");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput1.add(jLabel31);
        jLabel31.setBounds(0, 640, 120, 23);

        KdkeadaanKlr.setEditable(false);
        KdkeadaanKlr.setForeground(new java.awt.Color(0, 0, 0));
        KdkeadaanKlr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        KdkeadaanKlr.setName("KdkeadaanKlr"); // NOI18N
        FormInput1.add(KdkeadaanKlr);
        KdkeadaanKlr.setBounds(120, 640, 40, 23);

        KeadaanKeluar.setEditable(false);
        KeadaanKeluar.setForeground(new java.awt.Color(0, 0, 0));
        KeadaanKeluar.setName("KeadaanKeluar"); // NOI18N
        FormInput1.add(KeadaanKeluar);
        KeadaanKeluar.setBounds(165, 640, 200, 23);

        BtnKeadaanKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeadaanKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKeadaanKeluar.setMnemonic('3');
        BtnKeadaanKeluar.setToolTipText("ALt+3");
        BtnKeadaanKeluar.setName("BtnKeadaanKeluar"); // NOI18N
        BtnKeadaanKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeadaanKeluarActionPerformed(evt);
            }
        });
        FormInput1.add(BtnKeadaanKeluar);
        BtnKeadaanKeluar.setBounds(367, 640, 28, 23);

        jLabel32.setForeground(new java.awt.Color(255, 0, 0));
        jLabel32.setText("Cara Bayar : ");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput1.add(jLabel32);
        jLabel32.setBounds(403, 640, 70, 23);

        KdcaraByr.setEditable(false);
        KdcaraByr.setForeground(new java.awt.Color(0, 0, 0));
        KdcaraByr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        KdcaraByr.setName("KdcaraByr"); // NOI18N
        FormInput1.add(KdcaraByr);
        KdcaraByr.setBounds(475, 640, 30, 23);

        CaraBayar.setEditable(false);
        CaraBayar.setForeground(new java.awt.Color(0, 0, 0));
        CaraBayar.setName("CaraBayar"); // NOI18N
        FormInput1.add(CaraBayar);
        CaraBayar.setBounds(510, 640, 185, 23);

        BtnCaraBayar.setForeground(new java.awt.Color(0, 0, 0));
        BtnCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnCaraBayar.setMnemonic('3');
        BtnCaraBayar.setToolTipText("ALt+3");
        BtnCaraBayar.setName("BtnCaraBayar"); // NOI18N
        BtnCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCaraBayarActionPerformed(evt);
            }
        });
        FormInput1.add(BtnCaraBayar);
        BtnCaraBayar.setBounds(697, 640, 28, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Sebab Kematian Langsung 1A : ");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput1.add(jLabel33);
        jLabel33.setBounds(0, 670, 200, 23);

        KdsbbMati1A.setEditable(false);
        KdsbbMati1A.setForeground(new java.awt.Color(0, 0, 0));
        KdsbbMati1A.setName("KdsbbMati1A"); // NOI18N
        FormInput1.add(KdsbbMati1A);
        KdsbbMati1A.setBounds(201, 670, 60, 23);

        diagnosaSbbMati1A.setEditable(false);
        diagnosaSbbMati1A.setForeground(new java.awt.Color(0, 0, 0));
        diagnosaSbbMati1A.setName("diagnosaSbbMati1A"); // NOI18N
        FormInput1.add(diagnosaSbbMati1A);
        diagnosaSbbMati1A.setBounds(265, 670, 430, 23);

        BtnDiagSbbMati1A.setForeground(new java.awt.Color(0, 0, 0));
        BtnDiagSbbMati1A.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDiagSbbMati1A.setMnemonic('3');
        BtnDiagSbbMati1A.setToolTipText("ALt+3");
        BtnDiagSbbMati1A.setName("BtnDiagSbbMati1A"); // NOI18N
        BtnDiagSbbMati1A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDiagSbbMati1AActionPerformed(evt);
            }
        });
        FormInput1.add(BtnDiagSbbMati1A);
        BtnDiagSbbMati1A.setBounds(697, 670, 28, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Sebab Kematian Antara 1B : ");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput1.add(jLabel34);
        jLabel34.setBounds(0, 700, 200, 23);

        KdsbbMati1B.setEditable(false);
        KdsbbMati1B.setForeground(new java.awt.Color(0, 0, 0));
        KdsbbMati1B.setName("KdsbbMati1B"); // NOI18N
        FormInput1.add(KdsbbMati1B);
        KdsbbMati1B.setBounds(201, 700, 60, 23);

        diagnosaSbbMati1B.setEditable(false);
        diagnosaSbbMati1B.setForeground(new java.awt.Color(0, 0, 0));
        diagnosaSbbMati1B.setName("diagnosaSbbMati1B"); // NOI18N
        FormInput1.add(diagnosaSbbMati1B);
        diagnosaSbbMati1B.setBounds(265, 700, 430, 23);

        BtnDiagSbbMati1B.setForeground(new java.awt.Color(0, 0, 0));
        BtnDiagSbbMati1B.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDiagSbbMati1B.setMnemonic('3');
        BtnDiagSbbMati1B.setToolTipText("ALt+3");
        BtnDiagSbbMati1B.setName("BtnDiagSbbMati1B"); // NOI18N
        BtnDiagSbbMati1B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDiagSbbMati1BActionPerformed(evt);
            }
        });
        FormInput1.add(BtnDiagSbbMati1B);
        BtnDiagSbbMati1B.setBounds(697, 700, 28, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Sebab Kematian Antara 1C : ");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput1.add(jLabel35);
        jLabel35.setBounds(0, 730, 200, 23);

        KdsbbMati1C.setEditable(false);
        KdsbbMati1C.setForeground(new java.awt.Color(0, 0, 0));
        KdsbbMati1C.setName("KdsbbMati1C"); // NOI18N
        FormInput1.add(KdsbbMati1C);
        KdsbbMati1C.setBounds(201, 730, 60, 23);

        diagnosaSbbMati1C.setEditable(false);
        diagnosaSbbMati1C.setForeground(new java.awt.Color(0, 0, 0));
        diagnosaSbbMati1C.setName("diagnosaSbbMati1C"); // NOI18N
        FormInput1.add(diagnosaSbbMati1C);
        diagnosaSbbMati1C.setBounds(265, 730, 430, 23);

        BtnDiagSbbMati1C.setForeground(new java.awt.Color(0, 0, 0));
        BtnDiagSbbMati1C.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDiagSbbMati1C.setMnemonic('3');
        BtnDiagSbbMati1C.setToolTipText("ALt+3");
        BtnDiagSbbMati1C.setName("BtnDiagSbbMati1C"); // NOI18N
        BtnDiagSbbMati1C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDiagSbbMati1CActionPerformed(evt);
            }
        });
        FormInput1.add(BtnDiagSbbMati1C);
        BtnDiagSbbMati1C.setBounds(697, 730, 28, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Sebab Kematian Dasar 1D : ");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput1.add(jLabel36);
        jLabel36.setBounds(0, 760, 200, 23);

        KdsbbMati1D.setEditable(false);
        KdsbbMati1D.setForeground(new java.awt.Color(0, 0, 0));
        KdsbbMati1D.setName("KdsbbMati1D"); // NOI18N
        FormInput1.add(KdsbbMati1D);
        KdsbbMati1D.setBounds(201, 760, 60, 23);

        diagnosaSbbMati1D.setEditable(false);
        diagnosaSbbMati1D.setForeground(new java.awt.Color(0, 0, 0));
        diagnosaSbbMati1D.setName("diagnosaSbbMati1D"); // NOI18N
        FormInput1.add(diagnosaSbbMati1D);
        diagnosaSbbMati1D.setBounds(265, 760, 430, 23);

        BtnDiagSbbMati1D.setForeground(new java.awt.Color(0, 0, 0));
        BtnDiagSbbMati1D.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDiagSbbMati1D.setMnemonic('3');
        BtnDiagSbbMati1D.setToolTipText("ALt+3");
        BtnDiagSbbMati1D.setName("BtnDiagSbbMati1D"); // NOI18N
        BtnDiagSbbMati1D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDiagSbbMati1DActionPerformed(evt);
            }
        });
        FormInput1.add(BtnDiagSbbMati1D);
        BtnDiagSbbMati1D.setBounds(697, 760, 28, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Kondisi Yg. Kontribusi Thd. Kematian: ");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput1.add(jLabel37);
        jLabel37.setBounds(0, 790, 200, 23);

        Kdkondisi.setEditable(false);
        Kdkondisi.setForeground(new java.awt.Color(0, 0, 0));
        Kdkondisi.setName("Kdkondisi"); // NOI18N
        FormInput1.add(Kdkondisi);
        Kdkondisi.setBounds(201, 790, 60, 23);

        diagnosaKondisi.setEditable(false);
        diagnosaKondisi.setForeground(new java.awt.Color(0, 0, 0));
        diagnosaKondisi.setName("diagnosaKondisi"); // NOI18N
        FormInput1.add(diagnosaKondisi);
        diagnosaKondisi.setBounds(265, 790, 430, 23);

        BtnDiagKondisi.setForeground(new java.awt.Color(0, 0, 0));
        BtnDiagKondisi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDiagKondisi.setMnemonic('3');
        BtnDiagKondisi.setToolTipText("ALt+3");
        BtnDiagKondisi.setName("BtnDiagKondisi"); // NOI18N
        BtnDiagKondisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDiagKondisiActionPerformed(evt);
            }
        });
        FormInput1.add(BtnDiagKondisi);
        BtnDiagKondisi.setBounds(697, 790, 28, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Sebab Dasar Kematian : ");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput1.add(jLabel38);
        jLabel38.setBounds(0, 820, 200, 23);

        sebabDasarMati.setForeground(new java.awt.Color(0, 0, 0));
        sebabDasarMati.setName("sebabDasarMati"); // NOI18N
        FormInput1.add(sebabDasarMati);
        sebabDasarMati.setBounds(201, 820, 524, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Status Pulang : ");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput1.add(jLabel39);
        jLabel39.setBounds(0, 610, 120, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Jenis Bayar : ");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput1.add(jLabel40);
        jLabel40.setBounds(370, 610, 78, 23);

        sttsPulang.setEditable(false);
        sttsPulang.setForeground(new java.awt.Color(0, 0, 0));
        sttsPulang.setName("sttsPulang"); // NOI18N
        FormInput1.add(sttsPulang);
        sttsPulang.setBounds(120, 610, 250, 23);

        jnsBayar.setEditable(false);
        jnsBayar.setForeground(new java.awt.Color(0, 0, 0));
        jnsBayar.setName("jnsBayar"); // NOI18N
        FormInput1.add(jnsBayar);
        jnsBayar.setBounds(450, 610, 275, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Rg. / Poliklinik / Inst : ");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput1.add(jLabel41);
        jLabel41.setBounds(0, 400, 120, 23);

        nmRuangPoli.setEditable(false);
        nmRuangPoli.setForeground(new java.awt.Color(0, 0, 0));
        nmRuangPoli.setName("nmRuangPoli"); // NOI18N
        FormInput1.add(nmRuangPoli);
        nmRuangPoli.setBounds(120, 400, 605, 23);

        BtnDelAsalRujukan.setForeground(new java.awt.Color(0, 0, 0));
        BtnDelAsalRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cancel.png"))); // NOI18N
        BtnDelAsalRujukan.setMnemonic('3');
        BtnDelAsalRujukan.setToolTipText("ALt+3");
        BtnDelAsalRujukan.setName("BtnDelAsalRujukan"); // NOI18N
        BtnDelAsalRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDelAsalRujukanActionPerformed(evt);
            }
        });
        FormInput1.add(BtnDelAsalRujukan);
        BtnDelAsalRujukan.setBounds(732, 280, 28, 23);

        BtnDelDiagSekun1.setForeground(new java.awt.Color(0, 0, 0));
        BtnDelDiagSekun1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cancel.png"))); // NOI18N
        BtnDelDiagSekun1.setMnemonic('3');
        BtnDelDiagSekun1.setToolTipText("ALt+3");
        BtnDelDiagSekun1.setName("BtnDelDiagSekun1"); // NOI18N
        BtnDelDiagSekun1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDelDiagSekun1ActionPerformed(evt);
            }
        });
        FormInput1.add(BtnDelDiagSekun1);
        BtnDelDiagSekun1.setBounds(732, 490, 28, 23);

        BtnDelDiagSekun2.setForeground(new java.awt.Color(0, 0, 0));
        BtnDelDiagSekun2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cancel.png"))); // NOI18N
        BtnDelDiagSekun2.setMnemonic('3');
        BtnDelDiagSekun2.setToolTipText("ALt+3");
        BtnDelDiagSekun2.setName("BtnDelDiagSekun2"); // NOI18N
        BtnDelDiagSekun2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDelDiagSekun2ActionPerformed(evt);
            }
        });
        FormInput1.add(BtnDelDiagSekun2);
        BtnDelDiagSekun2.setBounds(732, 520, 28, 23);

        BtnDelDiagSekun3.setForeground(new java.awt.Color(0, 0, 0));
        BtnDelDiagSekun3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cancel.png"))); // NOI18N
        BtnDelDiagSekun3.setMnemonic('3');
        BtnDelDiagSekun3.setToolTipText("ALt+3");
        BtnDelDiagSekun3.setName("BtnDelDiagSekun3"); // NOI18N
        BtnDelDiagSekun3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDelDiagSekun3ActionPerformed(evt);
            }
        });
        FormInput1.add(BtnDelDiagSekun3);
        BtnDelDiagSekun3.setBounds(732, 550, 28, 23);

        BtnDelSbbMati1A.setForeground(new java.awt.Color(0, 0, 0));
        BtnDelSbbMati1A.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cancel.png"))); // NOI18N
        BtnDelSbbMati1A.setMnemonic('3');
        BtnDelSbbMati1A.setToolTipText("ALt+3");
        BtnDelSbbMati1A.setName("BtnDelSbbMati1A"); // NOI18N
        BtnDelSbbMati1A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDelSbbMati1AActionPerformed(evt);
            }
        });
        FormInput1.add(BtnDelSbbMati1A);
        BtnDelSbbMati1A.setBounds(732, 670, 28, 23);

        BtnDelSbbMati1B.setForeground(new java.awt.Color(0, 0, 0));
        BtnDelSbbMati1B.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cancel.png"))); // NOI18N
        BtnDelSbbMati1B.setMnemonic('3');
        BtnDelSbbMati1B.setToolTipText("ALt+3");
        BtnDelSbbMati1B.setName("BtnDelSbbMati1B"); // NOI18N
        BtnDelSbbMati1B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDelSbbMati1BActionPerformed(evt);
            }
        });
        FormInput1.add(BtnDelSbbMati1B);
        BtnDelSbbMati1B.setBounds(732, 700, 28, 23);

        BtnDelSbbMati1C.setForeground(new java.awt.Color(0, 0, 0));
        BtnDelSbbMati1C.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cancel.png"))); // NOI18N
        BtnDelSbbMati1C.setMnemonic('3');
        BtnDelSbbMati1C.setToolTipText("ALt+3");
        BtnDelSbbMati1C.setName("BtnDelSbbMati1C"); // NOI18N
        BtnDelSbbMati1C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDelSbbMati1CActionPerformed(evt);
            }
        });
        FormInput1.add(BtnDelSbbMati1C);
        BtnDelSbbMati1C.setBounds(732, 730, 28, 23);

        BtnDelSbbMati1D.setForeground(new java.awt.Color(0, 0, 0));
        BtnDelSbbMati1D.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cancel.png"))); // NOI18N
        BtnDelSbbMati1D.setMnemonic('3');
        BtnDelSbbMati1D.setToolTipText("ALt+3");
        BtnDelSbbMati1D.setName("BtnDelSbbMati1D"); // NOI18N
        BtnDelSbbMati1D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDelSbbMati1DActionPerformed(evt);
            }
        });
        FormInput1.add(BtnDelSbbMati1D);
        BtnDelSbbMati1D.setBounds(732, 760, 28, 23);

        BtnDelKondisi.setForeground(new java.awt.Color(0, 0, 0));
        BtnDelKondisi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cancel.png"))); // NOI18N
        BtnDelKondisi.setMnemonic('3');
        BtnDelKondisi.setToolTipText("ALt+3");
        BtnDelKondisi.setName("BtnDelKondisi"); // NOI18N
        BtnDelKondisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDelKondisiActionPerformed(evt);
            }
        });
        FormInput1.add(BtnDelKondisi);
        BtnDelKondisi.setBounds(732, 790, 28, 23);

        scrollInput.setViewportView(FormInput1);
        FormInput1.getAccessibleContext().setAccessibleName("");

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Data Pasien Kanker", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbKanker.setAutoCreateRowSorter(true);
        tbKanker.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbKanker.setName("tbKanker"); // NOI18N
        tbKanker.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKankerMouseClicked(evt);
            }
        });
        tbKanker.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbKankerKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbKanker);

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Daftar Pasien Kanker", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("") || TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (Sequel.cariInteger("select count(-1) from setting_bridging where kd_bridging='1' and status_aktif='Ya'") == 1) {
                simpanData();
            } else {
                JOptionPane.showMessageDialog(null, "Bridging data penyakit kanker dinonaktifkan, permasalahanya masih dalam pembahasan...");
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, sebabDasarMati, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();       
        TabRawat.setSelectedIndex(0); 
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (TNoRw.getText().trim().equals("") || TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (id_simpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu datanya pada tabel terlebih dahulu..!!");
        } else {
            if (tbKanker.getSelectedRow() > -1) {
                if (Sequel.cariInteger("select count(-1) from setting_bridging where kd_bridging='1' and status_aktif='Ya'") == 1) {
                    x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        try {
                            if (api.MintaToken().equals("")) {
                                JOptionPane.showMessageDialog(rootPane, "Token tidak diterima....!");
                            } else {
                                url = koneksiDB.URLAPIKANKER() + "/t_register_penyakit/delete";
                                headers = new HttpHeaders();
                                headers.add("X-Api-Key", "sirskemkes");
                                headers.add("X-Token", api.MintaToken());
                                headers.add("Content-Type", "application/json");

                                requestJson = "{\"id\" : \"" + id_simpan + "\"}";

                                System.out.println("JSON dikirim : " + requestJson);
                                requestEntity = new HttpEntity(requestJson, headers);
                                requestJson = api.getRest().exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
                                root = mapper.readTree(requestJson);
                                status = root.path("status").asText();
                                pesan = root.path("message").asText();
                                System.out.println("Status : " + status + ", Pesan : " + pesan);

                                if (status.equals("true")) {
                                    Sequel.meghapus("data_kanker_bridging", "id_simpan", tbKanker.getValueAt(tbKanker.getSelectedRow(), 1).toString());
                                    emptTeks();
                                    tampil();
                                    JOptionPane.showMessageDialog(null, "Info dari Kemenkes Bridging Penyakit Kanker : " + pesan);
                                } else {
                                    System.out.println("Status : " + status + ", Pesan : " + pesan);
                                    JOptionPane.showMessageDialog(null, "Proses hapus data gagal..!!");
                                }
                            }

                        } catch (Exception ex) {
                            System.out.println("Notifikasi : " + ex);
                            JOptionPane.showMessageDialog(null, "Proses hapus data gagal..!!");
                            if (ex.toString().contains("UnknownHostException")) {
                                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server Kemenkes terputus....!");
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Bridging data penyakit kanker dinonaktifkan, permasalahanya masih dalam pembahasan...");
                }
            }
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRw.getText().trim().equals("") || TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (id_simpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu datanya pada tabel terlebih dahulu..!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from setting_bridging where kd_bridging='1' and status_aktif='Ya'") == 1) {
                editData();
            } else {
                JOptionPane.showMessageDialog(null, "Bridging data penyakit kanker dinonaktifkan, permasalahanya masih dalam pembahasan...");
            }
        }       
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnAll,TCari);}
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
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
            TCari.setText("");
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void AlamatMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlamatMouseMoved
        if(Alamat.getText().equals("ALAMAT")){
            Alamat.setText("");
        }
    }//GEN-LAST:event_AlamatMouseMoved

    private void AlamatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlamatMouseExited
        if(Alamat.getText().equals("")){
            Alamat.setText("ALAMAT");
        }
    }//GEN-LAST:event_AlamatMouseExited

    private void AlamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Alamat.getText().equals("")) {
                Alamat.setText("ALAMAT");
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            if (Alamat.getText().equals("")) {
                Alamat.setText("ALAMAT");
            }
        }
    }//GEN-LAST:event_AlamatKeyPressed

    private void BtnKelurahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKelurahanActionPerformed
        if (KdKec.getText().equals("") || Kecamatan.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih Kecamatan terlebih dahulu..!!");
            BtnKecamatan.requestFocus();
        } else {
            pilihan = 1;
            akses.setform("DlgDataKanker");
            kel.TKdKec.setText(KdKec.getText());
            kel.setSize(810, 384);
            kel.setLocationRelativeTo(internalFrame1);
            kel.setVisible(true);
            kel.TCari.requestFocus();
            kel.tampil();
        }
    }//GEN-LAST:event_BtnKelurahanActionPerformed

    private void KecamatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KecamatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Kecamatan.getText().equals("")) {
                Kecamatan.setText("KECAMATAN");
            }
            if (Kabupaten.getText().equals("KABUPATEN")) {
                Kabupaten.setText("");
            }
            Kabupaten.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            if (Kecamatan.getText().equals("")) {
                Kecamatan.setText("KECAMATAN");
            }
            if (Kelurahan.getText().equals("KELURAHAN")) {
                Kelurahan.setText("");
            }
            Kelurahan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnKecamatanActionPerformed(null);
        }
    }//GEN-LAST:event_KecamatanKeyPressed

    private void BtnKecamatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKecamatanActionPerformed
        if (KdKab.getText().equals("") || Kabupaten.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih kabupaten terlebih dahulu..!!");
            BtnKabupaten.requestFocus();
        } else {
            pilihan = 1;
            akses.setform("DlgDataKanker");
            kec.TKdKab.setText(KdKab.getText());
            kec.setSize(795, 270);
            kec.setLocationRelativeTo(internalFrame1);
            kec.setVisible(true);
            kec.TCari.requestFocus();
            kec.tampil();
        }
    }//GEN-LAST:event_BtnKecamatanActionPerformed

    private void BtnKabupatenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKabupatenActionPerformed
        if (KdProp.getText().equals("") || Propinsi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih propinsi terlebih dahulu..!!");
            BtnPropinsi.requestFocus();
        } else {
            pilihan = 1;
            akses.setform("DlgDataKanker");
            kab.TKode.setText(KdProp.getText());
            kab.setSize(795, 270);
            kab.setLocationRelativeTo(internalFrame1);
            kab.setVisible(true);
            kab.TCari.requestFocus();
            kab.tampil();
        }
    }//GEN-LAST:event_BtnKabupatenActionPerformed

    private void KabupatenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KabupatenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Kabupaten.getText().equals("")) {
                Kabupaten.setText("KABUPATEN");
            }
//            PeriodeLaporan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            if (Kabupaten.getText().equals("")) {
                Kabupaten.setText("KABUPATEN");
            }
            if (Kecamatan.getText().equals("KECAMATAN")) {
                Kecamatan.setText("");
            }
            Kecamatan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnKabupatenActionPerformed(null);
        }
    }//GEN-LAST:event_KabupatenKeyPressed

    private void KdKecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdKecKeyPressed
        Valid.pindah(evt, KdKel,KdKab);
    }//GEN-LAST:event_KdKecKeyPressed

    private void KelurahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KelurahanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Kelurahan.getText().equals("")) {
                Kelurahan.setText("KELURAHAN");
            }
            if (Kecamatan.getText().equals("KECAMATAN")) {
                Kecamatan.setText("");
            }
            Kecamatan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            if (Kelurahan.getText().equals("")) {
                Kelurahan.setText("KELURAHAN");
            }
            if (Alamat.getText().equals("ALAMAT")) {
                Alamat.setText("");
            }
            Alamat.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnKelurahanActionPerformed(null);
        }
    }//GEN-LAST:event_KelurahanKeyPressed

    private void KdKelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdKelKeyPressed
        Valid.pindah(evt, TCari,KdKec);
    }//GEN-LAST:event_KdKelKeyPressed

    private void PropinsiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PropinsiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PropinsiKeyPressed

    private void BtnPropinsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPropinsiActionPerformed
        pilihan = 1;
        akses.setform("DlgDataKanker");
        prop.setSize(654, 342);
        prop.setLocationRelativeTo(internalFrame1);
        prop.setVisible(true);
        prop.TCari.requestFocus();
        prop.TCari.setText("Kalimantan");
        prop.tampil();
    }//GEN-LAST:event_BtnPropinsiActionPerformed

    private void tbKankerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKankerKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                try {
                    getData();
                    TabRawat.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_A) {
                for (i = 0; i < tbKanker.getRowCount(); i++) {
                    tbKanker.setValueAt(true, i, 0);
                }
            }
        }
    }//GEN-LAST:event_tbKankerKeyPressed

    private void tbKankerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKankerMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
                if (evt.getClickCount() == 2) {
                    TabRawat.setSelectedIndex(0);

                }
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbKankerMouseClicked

    private void ChkDomisiliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkDomisiliActionPerformed
        if (ChkDomisili.isSelected() == true) {
            if (Alamat.getText().equals("")) {
                TAlamatDom.setText("ALAMAT DOMISILI");
            } else {
                TAlamatDom.setText(Alamat.getText());
            }
          
            if (KdProp.getText().equals("")) {
                KdPropDom.setText("");
                PropinsiDom.setText("PROPINSI DOMISILI");
            } else {
                KdPropDom.setText(KdProp.getText());
                PropinsiDom.setText(Propinsi.getText());
            }
            
            if (KdKab.getText().equals("")) {
                KdKabDom.setText("");
                KabupatenDom.setText("KABUPATEN DOMISILI");
            } else {
                KdKabDom.setText(KdKab.getText());
                KabupatenDom.setText(Kabupaten.getText());
            }
            
            if (KdKec.getText().equals("")) {
                KdKecDom.setText("");
                KecamatanDom.setText("KECAMATAN DOMISILI");
            } else {
                KdKecDom.setText(KdKec.getText());
                KecamatanDom.setText(Kecamatan.getText());
            }
            
            if (KdKel.getText().equals("")) {
                KdKelDom.setText("");
                KelurahanDom.setText("KELURAHAN DOMISILI");
            } else {
                KdKelDom.setText(KdKel.getText());
                KelurahanDom.setText(Kelurahan.getText());
            }
            
        } else {
            TAlamatDom.setText("ALAMAT DOMISILI");
            KdPropDom.setText("");
            PropinsiDom.setText("PROPINSI DOMISILI");
            KdKabDom.setText("");
            KabupatenDom.setText("KABUPATEN DOMISILI");
            KdKecDom.setText("");
            KecamatanDom.setText("KECAMATAN DOMISILI");
            KdKelDom.setText("");
            KelurahanDom.setText("KELURAHAN DOMISILI");
        }
    }//GEN-LAST:event_ChkDomisiliActionPerformed

    private void BtnJKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnJKActionPerformed
        akses.setform("DlgDataKanker");
        jk.setSize(393, 266);
        jk.setLocationRelativeTo(internalFrame1);
        jk.setVisible(true);
        jk.tampil();
    }//GEN-LAST:event_BtnJKActionPerformed

    private void TNoTelpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoTelpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoTelpKeyPressed

    private void TAlamatDomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlamatDomKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (TAlamatDom.getText().equals("")) {
                TAlamatDom.setText("ALAMAT DOMISILI");
            }            
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            if (TAlamatDom.getText().equals("")) {
                TAlamatDom.setText("ALAMAT DOMISILI");
            }
        }
    }//GEN-LAST:event_TAlamatDomKeyPressed

    private void BtnPropinsiDomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPropinsiDomActionPerformed
        pilihan = 2;
        akses.setform("DlgDataKanker");
        prop.setSize(654, 342);
        prop.setLocationRelativeTo(internalFrame1);
        prop.setVisible(true);
        prop.TCari.requestFocus();
        prop.TCari.setText("Kalimantan");
        prop.tampil();
    }//GEN-LAST:event_BtnPropinsiDomActionPerformed

    private void TAlamatDomMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TAlamatDomMouseExited
        if (TAlamatDom.getText().equals("")) {
            TAlamatDom.setText("ALAMAT DOMISILI");
        }
    }//GEN-LAST:event_TAlamatDomMouseExited

    private void TAlamatDomMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TAlamatDomMouseMoved
        if (TAlamatDom.getText().equals("ALAMAT DOMISILI")) {
            TAlamatDom.setText("");
        }
    }//GEN-LAST:event_TAlamatDomMouseMoved

    private void KdKabDomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdKabDomKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdKabDomKeyPressed

    private void KabupatenDomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KabupatenDomKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (KabupatenDom.getText().equals("")) {
                KabupatenDom.setText("KABUPATEN DOMISILI");
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            if (KabupatenDom.getText().equals("")) {
                KabupatenDom.setText("KABUPATEN DOMISILI");
            }
            if (KecamatanDom.getText().equals("KECAMATAN DOMISILI")) {
                KecamatanDom.setText("");
            }
            KecamatanDom.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnKabupatenActionPerformed(null);
        }
    }//GEN-LAST:event_KabupatenDomKeyPressed

    private void BtnKabupatenDomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKabupatenDomActionPerformed
        if (KdPropDom.getText().equals("") || PropinsiDom.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih propinsi domisili terlebih dahulu..!!");
            BtnPropinsiDom.requestFocus();
        } else {
            pilihan = 2;
            akses.setform("DlgDataKanker");
            kab.TKode.setText(KdPropDom.getText());
            kab.setSize(795, 270);
            kab.setLocationRelativeTo(internalFrame1);
            kab.setVisible(true);
            kab.TCari.requestFocus();
            kab.tampil();
        }
    }//GEN-LAST:event_BtnKabupatenDomActionPerformed

    private void KecamatanDomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KecamatanDomKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (KecamatanDom.getText().equals("")) {
                KecamatanDom.setText("KECAMATAN DOMISILI");
            }
            if (KabupatenDom.getText().equals("KABUPATEN DOMISILI")) {
                KabupatenDom.setText("");
            }
            KabupatenDom.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            if (KecamatanDom.getText().equals("")) {
                KecamatanDom.setText("KECAMATAN DOMISILI");
            }
            if (KelurahanDom.getText().equals("KELURAHAN DOMISILI")) {
                KelurahanDom.setText("");
            }
            KelurahanDom.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnKecamatanActionPerformed(null);
        }
    }//GEN-LAST:event_KecamatanDomKeyPressed

    private void BtnKecamatanDomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKecamatanDomActionPerformed
        if (KdKabDom.getText().equals("") || KabupatenDom.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih kabupaten domisili terlebih dahulu..!!");
            BtnKabupatenDom.requestFocus();
        } else {
            pilihan = 2;
            akses.setform("DlgDataKanker");
            kec.TKdKab.setText(KdKabDom.getText());
            kec.setSize(795, 270);
            kec.setLocationRelativeTo(internalFrame1);
            kec.setVisible(true);
            kec.TCari.requestFocus();
            kec.tampil();
        }
    }//GEN-LAST:event_BtnKecamatanDomActionPerformed

    private void KdKelDomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdKelDomKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdKelDomKeyPressed

    private void KelurahanDomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KelurahanDomKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (KelurahanDom.getText().equals("")) {
                KelurahanDom.setText("KELURAHAN DOMISILI");
            }
            if (KecamatanDom.getText().equals("KECAMATAN DOMISILI")) {
                KecamatanDom.setText("");
            }
            KecamatanDom.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            if (KelurahanDom.getText().equals("")) {
                KelurahanDom.setText("KELURAHAN DOMISILI");
            }
            if (TAlamatDom.getText().equals("ALAMAT DOMISILI")) {
                TAlamatDom.setText("");
            }
            Alamat.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnKelurahanActionPerformed(null);
        }
    }//GEN-LAST:event_KelurahanDomKeyPressed

    private void BtnKelurahanDomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKelurahanDomActionPerformed
        if (KdKecDom.getText().equals("") || KecamatanDom.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih Kecamatan domisili terlebih dahulu..!!");
            BtnKecamatanDom.requestFocus();
        } else {
            pilihan = 2;
            akses.setform("DlgDataKanker");
            kel.TKdKec.setText(KdKecDom.getText());
            kel.setSize(810, 384);
            kel.setLocationRelativeTo(internalFrame1);
            kel.setVisible(true);
            kel.TCari.requestFocus();
            kel.tampil();
        }
    }//GEN-LAST:event_BtnKelurahanDomActionPerformed

    private void BtnCaraMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCaraMasukActionPerformed
        akses.setform("DlgDataKanker");
        caraMsk.setSize(465, 200);
        caraMsk.setLocationRelativeTo(internalFrame1);
        caraMsk.setVisible(true);
        caraMsk.tampil();
    }//GEN-LAST:event_BtnCaraMasukActionPerformed

    private void BtnAsalRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsalRujukanActionPerformed
        akses.setform("DlgDataKanker");
        asalRujuk.setSize(654, 342);
        asalRujuk.setLocationRelativeTo(internalFrame1);
        asalRujuk.setVisible(true);
        asalRujuk.TCari.requestFocus();
        asalRujuk.tampil();
    }//GEN-LAST:event_BtnAsalRujukanActionPerformed

    private void TDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiagnosaKeyPressed

    }//GEN-LAST:event_TDiagnosaKeyPressed

    private void BtnICDdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnICDdmActionPerformed
        pilihan = 1;
        penyakit.isCek();
        penyakit.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
    }//GEN-LAST:event_BtnICDdmActionPerformed

    private void BtnSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSubActionPerformed
        akses.setform("DlgDataKanker");
        subInst.setSize(654, 342);
        subInst.setLocationRelativeTo(internalFrame1);
        subInst.setVisible(true);
        subInst.TCari.requestFocus();
        subInst.tampil();
    }//GEN-LAST:event_BtnSubActionPerformed

    private void BtnDiagUtamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDiagUtamActionPerformed
        pilihan = 2;
        penyakit.isCek();
        penyakit.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
    }//GEN-LAST:event_BtnDiagUtamActionPerformed

    private void BtnDiagSekun1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDiagSekun1ActionPerformed
        pilihan = 3;
        penyakit.isCek();
        penyakit.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
    }//GEN-LAST:event_BtnDiagSekun1ActionPerformed

    private void BtnDiagSekun2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDiagSekun2ActionPerformed
        pilihan = 4;
        penyakit.isCek();
        penyakit.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
    }//GEN-LAST:event_BtnDiagSekun2ActionPerformed

    private void BtnDiagSekun3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDiagSekun3ActionPerformed
        pilihan = 5;
        penyakit.isCek();
        penyakit.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
    }//GEN-LAST:event_BtnDiagSekun3ActionPerformed

    private void BtnCaraKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCaraKeluarActionPerformed
        akses.setform("DlgDataKanker");
        carak.setSize(654, 342);
        carak.setLocationRelativeTo(internalFrame1);
        carak.setVisible(true);
        carak.TCari.requestFocus();
        carak.tampil();
    }//GEN-LAST:event_BtnCaraKeluarActionPerformed

    private void BtnKeadaanKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeadaanKeluarActionPerformed
        akses.setform("DlgDataKanker");
        kelar.setSize(654, 342);
        kelar.setLocationRelativeTo(internalFrame1);
        kelar.setVisible(true);
        kelar.TCari.requestFocus();
        kelar.tampil();
    }//GEN-LAST:event_BtnKeadaanKeluarActionPerformed

    private void BtnCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCaraBayarActionPerformed
        akses.setform("DlgDataKanker");
        cabar.setSize(654, 342);
        cabar.setLocationRelativeTo(internalFrame1);
        cabar.setVisible(true);
        cabar.TCari.requestFocus();
        cabar.tampil();
    }//GEN-LAST:event_BtnCaraBayarActionPerformed

    private void BtnDiagSbbMati1AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDiagSbbMati1AActionPerformed
        pilihan = 6;
        penyakit.isCek();
        penyakit.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
    }//GEN-LAST:event_BtnDiagSbbMati1AActionPerformed

    private void BtnDiagSbbMati1BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDiagSbbMati1BActionPerformed
        pilihan = 7;
        penyakit.isCek();
        penyakit.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
    }//GEN-LAST:event_BtnDiagSbbMati1BActionPerformed

    private void BtnDiagSbbMati1CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDiagSbbMati1CActionPerformed
        pilihan = 8;
        penyakit.isCek();
        penyakit.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
    }//GEN-LAST:event_BtnDiagSbbMati1CActionPerformed

    private void BtnDiagSbbMati1DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDiagSbbMati1DActionPerformed
        pilihan = 9;
        penyakit.isCek();
        penyakit.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
    }//GEN-LAST:event_BtnDiagSbbMati1DActionPerformed

    private void BtnDiagKondisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDiagKondisiActionPerformed
        pilihan = 10;
        penyakit.isCek();
        penyakit.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
    }//GEN-LAST:event_BtnDiagKondisiActionPerformed

    private void BtnDelAsalRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDelAsalRujukanActionPerformed
        Kdrujukan.setText("");
        asalRujukan.setText("");
    }//GEN-LAST:event_BtnDelAsalRujukanActionPerformed

    private void BtnDelDiagSekun1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDelDiagSekun1ActionPerformed
        KddiagSekun1.setText("");
        diagnosaSekunder1.setText("");
    }//GEN-LAST:event_BtnDelDiagSekun1ActionPerformed

    private void BtnDelDiagSekun2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDelDiagSekun2ActionPerformed
        KddiagSekun2.setText("");
        diagnosaSekunder2.setText("");
    }//GEN-LAST:event_BtnDelDiagSekun2ActionPerformed

    private void BtnDelDiagSekun3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDelDiagSekun3ActionPerformed
        KddiagSekun3.setText("");
        diagnosaSekunder3.setText("");
    }//GEN-LAST:event_BtnDelDiagSekun3ActionPerformed

    private void BtnDelSbbMati1AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDelSbbMati1AActionPerformed
        KdsbbMati1A.setText("");
        diagnosaSbbMati1A.setText("");
    }//GEN-LAST:event_BtnDelSbbMati1AActionPerformed

    private void BtnDelSbbMati1BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDelSbbMati1BActionPerformed
        KdsbbMati1B.setText("");
        diagnosaSbbMati1B.setText("");
    }//GEN-LAST:event_BtnDelSbbMati1BActionPerformed

    private void BtnDelSbbMati1CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDelSbbMati1CActionPerformed
        KdsbbMati1C.setText("");
        diagnosaSbbMati1C.setText("");
    }//GEN-LAST:event_BtnDelSbbMati1CActionPerformed

    private void BtnDelSbbMati1DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDelSbbMati1DActionPerformed
        KdsbbMati1D.setText("");
        diagnosaSbbMati1D.setText("");
    }//GEN-LAST:event_BtnDelSbbMati1DActionPerformed

    private void BtnDelKondisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDelKondisiActionPerformed
        Kdkondisi.setText("");
        diagnosaKondisi.setText("");
    }//GEN-LAST:event_BtnDelKondisiActionPerformed

    private void PindahROKeyPressed(java.awt.event.KeyEvent evt) {                                    
//        Valid.pindah(evt,TerapiDM,Status);
    }                                   

    private void StatusKeyPressed(java.awt.event.KeyEvent evt) {                                  
//        Valid.pindah(evt,PindahRO,FotoTorak);
    }                                 

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgDataKanker dialog = new DlgDataKanker(new javax.swing.JFrame(), true);
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
    private widget.TextBox Alamat;
    private widget.Button BtnAll;
    private widget.Button BtnAsalRujukan;
    private widget.Button BtnBatal;
    private widget.Button BtnCaraBayar;
    private widget.Button BtnCaraKeluar;
    private widget.Button BtnCaraMasuk;
    private widget.Button BtnCari;
    private widget.Button BtnDelAsalRujukan;
    private widget.Button BtnDelDiagSekun1;
    private widget.Button BtnDelDiagSekun2;
    private widget.Button BtnDelDiagSekun3;
    private widget.Button BtnDelKondisi;
    private widget.Button BtnDelSbbMati1A;
    private widget.Button BtnDelSbbMati1B;
    private widget.Button BtnDelSbbMati1C;
    private widget.Button BtnDelSbbMati1D;
    private widget.Button BtnDiagKondisi;
    private widget.Button BtnDiagSbbMati1A;
    private widget.Button BtnDiagSbbMati1B;
    private widget.Button BtnDiagSbbMati1C;
    private widget.Button BtnDiagSbbMati1D;
    private widget.Button BtnDiagSekun1;
    private widget.Button BtnDiagSekun2;
    private widget.Button BtnDiagSekun3;
    private widget.Button BtnDiagUtam;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnICDdm;
    private widget.Button BtnJK;
    private widget.Button BtnKabupaten;
    private widget.Button BtnKabupatenDom;
    private widget.Button BtnKeadaanKeluar;
    private widget.Button BtnKecamatan;
    private widget.Button BtnKecamatanDom;
    private widget.Button BtnKeluar;
    private widget.Button BtnKelurahan;
    private widget.Button BtnKelurahanDom;
    private widget.Button BtnPropinsi;
    private widget.Button BtnPropinsiDom;
    private widget.Button BtnSimpan;
    private widget.Button BtnSub;
    private widget.TextBox CaraBayar;
    private widget.TextBox CaraKeluar;
    public widget.CekBox ChkDomisili;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput1;
    private widget.TextBox Kabupaten;
    private widget.TextBox KabupatenDom;
    private widget.TextBox KdKab;
    private widget.TextBox KdKabDom;
    private widget.TextBox KdKec;
    private widget.TextBox KdKecDom;
    private widget.TextBox KdKel;
    private widget.TextBox KdKelDom;
    private widget.TextBox KdProp;
    private widget.TextBox KdPropDom;
    private widget.TextBox KdcaraByr;
    private widget.TextBox KdcaraKlr;
    private widget.TextBox Kdcm;
    private widget.TextBox KddiagSekun1;
    private widget.TextBox KddiagSekun2;
    private widget.TextBox KddiagSekun3;
    private widget.TextBox KddiagUtama;
    private widget.TextBox KdicdDM;
    private widget.TextBox KdkeadaanKlr;
    private widget.TextBox Kdkondisi;
    private widget.TextBox Kdrujukan;
    private widget.TextBox KdsbbMati1A;
    private widget.TextBox KdsbbMati1B;
    private widget.TextBox KdsbbMati1C;
    private widget.TextBox KdsbbMati1D;
    private widget.TextBox Kdsub;
    private widget.TextBox KeadaanKeluar;
    private widget.TextBox Kecamatan;
    private widget.TextBox KecamatanDom;
    private widget.TextBox Kelurahan;
    private widget.TextBox KelurahanDom;
    private widget.Label LCount;
    private widget.TextBox NIK;
    private widget.TextBox NoKartu;
    private widget.TextBox Propinsi;
    private widget.TextBox PropinsiDom;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll6;
    private widget.TextBox SubInstalasi;
    private widget.TextBox TAlamatDom;
    private widget.TextBox TCari;
    private widget.TextArea TDiagnosa;
    private widget.TextBox TNmPasien;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TNoTelp;
    public javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Umur;
    private widget.TextBox asalRujukan;
    private widget.TextBox asalRujukanFasyankes;
    private widget.TextBox cara_masuk;
    private widget.TextBox diagnosaDM;
    private widget.TextBox diagnosaKondisi;
    private widget.TextBox diagnosaSbbMati1A;
    private widget.TextBox diagnosaSbbMati1B;
    private widget.TextBox diagnosaSbbMati1C;
    private widget.TextBox diagnosaSbbMati1D;
    private widget.TextBox diagnosaSekunder1;
    private widget.TextBox diagnosaSekunder2;
    private widget.TextBox diagnosaSekunder3;
    private widget.TextBox diagnosaUtama;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
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
    private widget.Label jLabel5;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox jnsBayar;
    private widget.TextBox kdjk;
    private widget.TextBox nmRuangPoli;
    private widget.TextBox nmjk;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.TextBox sebabDasarMati;
    private widget.TextBox sttsPulang;
    private widget.Table tbKanker;
    private widget.Tanggal tglDiagnosa;
    private widget.TextBox tglKeluar;
    private widget.TextBox tglLahr;
    private widget.TextBox tgl_masuk;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{    
            ps = koneksi.prepareStatement(
                    "SELECT dk.no_rawat, dk.id_simpan, p.no_rkm_medis, p.nm_pasien, dk.tanggal_lahir, dk.id_jenis_kelamin, dk.jenis_kelamin, CONCAT(rp.umurdaftar,' ',rp.sttsumur) umur,"
                    + "dk.nomor_bpjs, dk.nik, dk.alamat, dk.id_kelurahan, dk.kelurahan, dk.id_kecamatan, dk.kecamatan, dk.id_kab_kota, dk.kab_kota, dk.id_provinsi, dk.provinsi,"
                    + "dk.alamat_tinggal, dk.id_kelurahan_tinggal, dk.kelurahan_tinggal, dk.id_kecamatan_tinggal, dk.kecamatan_tinggal, dk.id_kab_kota_tinggal, dk.kab_kota_tinggal,"
                    + "dk.id_provinsi_tinggal, dk.provinsi_tinggal, dk.kontak_pasien, dk.tanggal_masuk, dk.id_cara_masuk_pasien, dk.cara_masuk_pasien, dk.id_asal_rujukan_pasien,"
                    + "dk.asal_rujukan_pasien, dk.asal_rujukan_pasien_fasyankes_lainnya, dk.id_diagnosa_masuk, dk.id_sub_instalasi_unit, dk.sub_instalasi_unit, dk.id_diagnosa_utama,"
                    + "dk.id_diagnosa_sekunder1, dk.id_diagnosa_sekunder2, dk.id_diagnosa_sekunder3, dk.tanggal_diagnosa, dk.tanggal_keluar, dk.id_cara_keluar, dk.cara_keluar, dk.id_keadaan_keluar,"
                    + "dk.keadaan_keluar, dk.id_sebab_kematian_langsung_1a, dk.id_sebab_kematian_antara_1b, dk.id_sebab_kematian_antara_1c, dk.id_sebab_kematian_dasar_1d, dk.id_kondisi_yg_berkontribusi_thdp_kematian,"
                    + "dk.sebab_dasar_kematian, dk.id_cara_bayar, dk.cara_bayar, dk.waktu_simpan FROM data_kanker_bridging dk INNER JOIN reg_periksa rp ON rp.no_rawat=dk.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                    + "date_format(dk.waktu_simpan,'%Y-%m-%d') between ? and ? and dk.no_rawat like ? or "
                    + "date_format(dk.waktu_simpan,'%Y-%m-%d') between ? and ? and dk.id_simpan like ? or "
                    + "date_format(dk.waktu_simpan,'%Y-%m-%d') between ? and ? and p.no_rkm_medis like ? or "
                    + "date_format(dk.waktu_simpan,'%Y-%m-%d') between ? and ? and p.nm_pasien like ? or "
                    + "date_format(dk.waktu_simpan,'%Y-%m-%d') between ? and ? and dk.jenis_kelamin like ? or "
                    + "date_format(dk.waktu_simpan,'%Y-%m-%d') between ? and ? and dk.nomor_bpjs like ? or "
                    + "date_format(dk.waktu_simpan,'%Y-%m-%d') between ? and ? and dk.nik like ? or "
                    + "date_format(dk.waktu_simpan,'%Y-%m-%d') between ? and ? and dk.alamat like ? or "
                    + "date_format(dk.waktu_simpan,'%Y-%m-%d') between ? and ? and dk.kelurahan like ? or "
                    + "date_format(dk.waktu_simpan,'%Y-%m-%d') between ? and ? and dk.kecamatan like ? or "
                    + "date_format(dk.waktu_simpan,'%Y-%m-%d') between ? and ? and dk.kab_kota like ? or "
                    + "date_format(dk.waktu_simpan,'%Y-%m-%d') between ? and ? and dk.provinsi like ? or "
                    + "date_format(dk.waktu_simpan,'%Y-%m-%d') between ? and ? and dk.kontak_pasien like ? or "
                    + "date_format(dk.waktu_simpan,'%Y-%m-%d') between ? and ? and dk.cara_masuk_pasien like ? or "
                    + "date_format(dk.waktu_simpan,'%Y-%m-%d') between ? and ? and dk.cara_keluar like ? or "
                    + "date_format(dk.waktu_simpan,'%Y-%m-%d') between ? and ? and dk.asal_rujukan_pasien like ? or "
                    + "date_format(dk.waktu_simpan,'%Y-%m-%d') between ? and ? and dk.asal_rujukan_pasien_fasyankes_lainnya like ? or "
                    + "date_format(dk.waktu_simpan,'%Y-%m-%d') between ? and ? and dk.cara_keluar like ? or "
                    + "date_format(dk.waktu_simpan,'%Y-%m-%d') between ? and ? and dk.keadaan_keluar like ? or "
                    + "date_format(dk.waktu_simpan,'%Y-%m-%d') between ? and ? and dk.sebab_dasar_kematian like ? or "
                    + "date_format(dk.waktu_simpan,'%Y-%m-%d') between ? and ? and dk.cara_bayar like ? ORDER BY date_format(dk.waktu_simpan,'%Y-%m-%d') desc");
            try{
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
                ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText().trim() + "%");                
                ps.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(24, "%" + TCari.getText().trim() + "%");                
                ps.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(27, "%" + TCari.getText().trim() + "%");                
                ps.setString(28, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(29, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(30, "%" + TCari.getText().trim() + "%");                
                ps.setString(31, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(32, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(33, "%" + TCari.getText().trim() + "%");                
                ps.setString(34, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(35, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(36, "%" + TCari.getText().trim() + "%");                
                ps.setString(37, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(38, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(39, "%" + TCari.getText().trim() + "%");                
                ps.setString(40, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(41, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(42, "%" + TCari.getText().trim() + "%");                
                ps.setString(43, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(44, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(45, "%" + TCari.getText().trim() + "%");                
                ps.setString(46, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(47, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(48, "%" + TCari.getText().trim() + "%");                
                ps.setString(49, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(50, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(51, "%" + TCari.getText().trim() + "%");                
                ps.setString(52, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(53, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(54, "%" + TCari.getText().trim() + "%");                
                ps.setString(55, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(56, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(57, "%" + TCari.getText().trim() + "%");                
                ps.setString(58, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(59, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(60, "%" + TCari.getText().trim() + "%");                
                ps.setString(61, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(62, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(63, "%" + TCari.getText().trim() + "%");
                rs=ps.executeQuery();
                while(rs.next()){   
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"), 
                        rs.getString("id_simpan"), 
                        rs.getString("no_rkm_medis"), 
                        rs.getString("nm_pasien"), 
                        rs.getString("tanggal_lahir"),
                        rs.getString("id_jenis_kelamin"), 
                        rs.getString("jenis_kelamin"), 
                        rs.getString("umur"), 
                        rs.getString("nomor_bpjs"), 
                        rs.getString("nik"),
                        rs.getString("alamat"), 
                        rs.getString("id_kelurahan"), 
                        rs.getString("kelurahan"), 
                        rs.getString("id_kecamatan"), 
                        rs.getString("kecamatan"),
                        rs.getString("id_kab_kota"), 
                        rs.getString("kab_kota"), 
                        rs.getString("id_provinsi"), 
                        rs.getString("provinsi"),
                        rs.getString("alamat_tinggal"), 
                        rs.getString("id_kelurahan_tinggal"), 
                        rs.getString("kelurahan_tinggal"), 
                        rs.getString("id_kecamatan_tinggal"),
                        rs.getString("kecamatan_tinggal"), 
                        rs.getString("id_kab_kota_tinggal"), 
                        rs.getString("kab_kota_tinggal"), 
                        rs.getString("id_provinsi_tinggal"),
                        rs.getString("provinsi_tinggal"), 
                        rs.getString("kontak_pasien"), 
                        rs.getString("tanggal_masuk"), 
                        rs.getString("id_cara_masuk_pasien"),
                        rs.getString("cara_masuk_pasien"), 
                        rs.getString("id_asal_rujukan_pasien"), 
                        rs.getString("asal_rujukan_pasien"),
                        rs.getString("asal_rujukan_pasien_fasyankes_lainnya"), 
                        rs.getString("id_diagnosa_masuk"), 
                        rs.getString("id_sub_instalasi_unit"),
                        rs.getString("sub_instalasi_unit"), 
                        rs.getString("id_diagnosa_utama"), 
                        rs.getString("id_diagnosa_sekunder1"),
                        rs.getString("id_diagnosa_sekunder2"), 
                        rs.getString("id_diagnosa_sekunder3"), 
                        rs.getString("tanggal_diagnosa"),
                        rs.getString("tanggal_keluar"), 
                        rs.getString("id_cara_keluar"), 
                        rs.getString("cara_keluar"),
                        rs.getString("id_keadaan_keluar"), 
                        rs.getString("keadaan_keluar"), 
                        rs.getString("id_sebab_kematian_langsung_1a"), 
                        rs.getString("id_sebab_kematian_antara_1b"), 
                        rs.getString("id_sebab_kematian_antara_1c"),
                        rs.getString("id_sebab_kematian_dasar_1d"), 
                        rs.getString("id_kondisi_yg_berkontribusi_thdp_kematian"), 
                        rs.getString("sebab_dasar_kematian"), 
                        rs.getString("id_cara_bayar"),
                        rs.getString("cara_bayar")
                    });
                }  
            } catch(Exception e){
                System.out.println(e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        TNoRw.setText("");
        TNoRM.setText("");
        TNmPasien.setText("");
        kdjk.setText("");
        nmjk.setText("");
        tglLahr.setText("");
        Umur.setText("");
        NoKartu.setText("");
        NIK.setText("");
        Alamat.setText("ALAMAT");
        KdKel.setText("");
        Kelurahan.setText("KELURAHAN");
        KdKec.setText("");
        Kecamatan.setText("KECAMATAN");
        KdKab.setText("");
        Kabupaten.setText("KABUPATEN");
        KdProp.setText("");
        Propinsi.setText("PROPINSI"); 
        TNoTelp.setText("");        
        ChkDomisili.setSelected(false);
        tgl_masuk.setText("");        
        TAlamatDom.setText("ALAMAT DOMISILI");
        KdKelDom.setText("");
        KelurahanDom.setText("KELURAHAN DOMISILI");
        KdKecDom.setText("");
        KecamatanDom.setText("KECAMATAN DOMISILI");
        KdKabDom.setText("");
        KabupatenDom.setText("KABUPATEN DOMISILI");
        KdPropDom.setText("");
        PropinsiDom.setText("PROPINSI DOMISILI"); 
        Kdcm.setText("");
        cara_masuk.setText("");
        Kdrujukan.setText("");
        asalRujukan.setText("");
        asalRujukanFasyankes.setText("");
        TDiagnosa.setText("");
        KdicdDM.setText("");
        diagnosaDM.setText("");
        Kdsub.setText("");
        SubInstalasi.setText("");
        KddiagUtama.setText("");
        diagnosaUtama.setText("");
        KddiagSekun1.setText("");
        diagnosaSekunder1.setText("");
        KddiagSekun2.setText("");
        diagnosaSekunder2.setText("");
        KddiagSekun3.setText("");
        diagnosaSekunder3.setText("");
        tglDiagnosa.setDate(new Date());
        tglKeluar.setText("");
        KdcaraKlr.setText("");
        CaraKeluar.setText("");
        KdkeadaanKlr.setText("");
        KeadaanKeluar.setText("");
        KdcaraByr.setText("");
        CaraBayar.setText("");
        KdsbbMati1A.setText("");
        diagnosaSbbMati1A.setText("");        
        KdsbbMati1B.setText("");
        diagnosaSbbMati1B.setText("");        
        KdsbbMati1C.setText("");
        diagnosaSbbMati1C.setText("");        
        KdsbbMati1D.setText("");
        diagnosaSbbMati1D.setText("");        
        Kdkondisi.setText("");
        diagnosaKondisi.setText("");
        sebabDasarMati.setText("");
        
        sttsPulang.setText("");
        jnsBayar.setText("");
        nmRuangPoli.setText("");
    }

    private void getData() {
        emptTeks();
        id_simpan = "";
        if(tbKanker.getSelectedRow()!= -1){
            TNoRw.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 0).toString());
            id_simpan = tbKanker.getValueAt(tbKanker.getSelectedRow(), 1).toString();
            TNoRM.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 2).toString());
            TNmPasien.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 3).toString());
            tglLahr.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 4).toString());
            Umur.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 7).toString());
            NoKartu.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 8).toString());
            NIK.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 9).toString());
            kdjk.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 5).toString());
            nmjk.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 6).toString());
            Alamat.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 10).toString());
            KdKel.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 11).toString());
            Kelurahan.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 12).toString());
            KdKec.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 13).toString());
            Kecamatan.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 14).toString());
            KdKab.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 15).toString());
            Kabupaten.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 16).toString());
            KdProp.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 17).toString());
            Propinsi.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 18).toString());
            TNoTelp.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 28).toString());
            tgl_masuk.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 29).toString());
            TAlamatDom.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 19).toString());
            KdKelDom.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 20).toString());
            KelurahanDom.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 21).toString());
            KdKecDom.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 22).toString());
            KecamatanDom.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 23).toString());
            KdKabDom.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 24).toString());
            KabupatenDom.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 25).toString());
            KdPropDom.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 26).toString());
            PropinsiDom.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 27).toString());
            Kdcm.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 30).toString());
            cara_masuk.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(),31).toString());
            Kdrujukan.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(),32).toString());
            asalRujukan.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(),33).toString());
            asalRujukanFasyankes.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(),34).toString());
            KdicdDM.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(),35).toString());
            diagnosaDM.setText(Sequel.cariIsi("select ifnull(nm_penyakit,'') from penyakit where kd_penyakit='" + KdicdDM.getText() + "'"));
            Kdsub.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(),36).toString());
            SubInstalasi.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(),37).toString());
            KddiagUtama.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(),38).toString());
            diagnosaUtama.setText(Sequel.cariIsi("select ifnull(nm_penyakit,'') from penyakit where kd_penyakit='" + KddiagUtama.getText() + "'"));
            KddiagSekun1.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(),39).toString());
            diagnosaSekunder1.setText(Sequel.cariIsi("select ifnull(nm_penyakit,'') from penyakit where kd_penyakit='" + KddiagSekun1.getText() + "'"));            
            KddiagSekun2.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(),40).toString());
            diagnosaSekunder2.setText(Sequel.cariIsi("select ifnull(nm_penyakit,'') from penyakit where kd_penyakit='" + KddiagSekun2.getText() + "'"));            
            KddiagSekun3.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(),41).toString());
            diagnosaSekunder3.setText(Sequel.cariIsi("select ifnull(nm_penyakit,'') from penyakit where kd_penyakit='" + KddiagSekun3.getText() + "'"));
            Valid.SetTgl(tglDiagnosa, tbKanker.getValueAt(tbKanker.getSelectedRow(), 42).toString());
            tglKeluar.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 43).toString());
            KdcaraKlr.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 44).toString());
            CaraKeluar.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 45).toString());
            jnsBayar.setText(Sequel.cariIsi("select if(r.kd_pj='B01',concat(p.png_jawab,' - ',bs.peserta),p.png_jawab) from reg_periksa r inner join penjab p on p.kd_pj=r.kd_pj "
                    + "left join bridging_sep bs on bs.no_rawat=r.no_rawat where r.no_rawat='" + TNoRw.getText() + "' order by bs.urutan_sep desc limit 1"));
            KdkeadaanKlr.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 46).toString());
            KeadaanKeluar.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 47).toString());
            KdcaraByr.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 54).toString());
            CaraBayar.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 55).toString());
            KdsbbMati1A.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 48).toString());
            diagnosaSbbMati1A.setText(Sequel.cariIsi("select ifnull(nm_penyakit,'') from penyakit where kd_penyakit='" + KdsbbMati1A.getText() + "'"));
            KdsbbMati1B.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 49).toString());
            diagnosaSbbMati1B.setText(Sequel.cariIsi("select ifnull(nm_penyakit,'') from penyakit where kd_penyakit='" + KdsbbMati1B.getText() + "'"));
            KdsbbMati1C.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 50).toString());
            diagnosaSbbMati1C.setText(Sequel.cariIsi("select ifnull(nm_penyakit,'') from penyakit where kd_penyakit='" + KdsbbMati1C.getText() + "'"));
            KdsbbMati1D.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 51).toString());
            diagnosaSbbMati1D.setText(Sequel.cariIsi("select ifnull(nm_penyakit,'') from penyakit where kd_penyakit='" + KdsbbMati1D.getText() + "'"));
            Kdkondisi.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 52).toString());
            diagnosaKondisi.setText(Sequel.cariIsi("select ifnull(nm_penyakit,'') from penyakit where kd_penyakit='" + Kdkondisi.getText() + "'"));
            sebabDasarMati.setText(tbKanker.getValueAt(tbKanker.getSelectedRow(), 53).toString());
            
            if (!KdPropDom.getText().equals("")) {
                ChkDomisili.setSelected(true);
            } else {
                ChkDomisili.setSelected(false);
            }

            if (Sequel.cariIsi("select status_lanjut from reg_periksa where no_rawat='" + TNoRw.getText() + "'").equals("Ralan")) {
                TDiagnosa.setText(Sequel.cariIsi("select ifnull(diagnosa,'') from pemeriksaan_ralan where no_rawat='" + TNoRw.getText() + "'"));
                nmRuangPoli.setText(Sequel.cariIsi("select nm_poli from reg_periksa rp inner join poliklinik pl on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + TNoRw.getText() + "'"));
                sttsPulang.setText("-");
            } else if (Sequel.cariIsi("select status_lanjut from reg_periksa where no_rawat='" + TNoRw.getText() + "'").equals("Ranap")) {
                TDiagnosa.setText(Sequel.cariIsi("select ifnull(diagnosa_awal,'') from kamar_inap where no_rawat='" + TNoRw.getText() + "' and stts_pulang not in ('-','Pindah Kamar')"));
                nmRuangPoli.setText(Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                        + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' and ki.stts_pulang not in ('-','Pindah Kamar')"));
                sttsPulang.setText(Sequel.cariIsi("select ki.stts_pulang from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                                + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' and ki.stts_pulang not in ('-','Pindah Kamar')"));
            }
        }
    }
    
    public void isCek(){
        TabRawat.setSelectedIndex(1);
        tampil();
        BtnSimpan.setEnabled(akses.getkemenkes_kanker());
        BtnHapus.setEnabled(akses.getkemenkes_kanker());
        BtnEdit.setEnabled(akses.getkemenkes_kanker());
    }
    
    public void setData(String norawat){
        TabRawat.setSelectedIndex(0);
        TNoRw.setText(norawat);
        stts_lanjt = "";
        try {
            ps=koneksi.prepareStatement("select p.no_rkm_medis, p.nm_pasien, p.tgl_lahir, rp.umurdaftar, "
                    + "rp.sttsumur, IF(LENGTH(p.no_peserta)<5,'000000',p.no_peserta) no_peserta, IF(LENGTH(p.no_ktp)<5,'000000',p.no_ktp) no_ktp,"
                    + "p.alamat, rp.status_lanjut, rp.tgl_registrasi, kb.nm_kab, kc.nm_kec, kl.nm_kel, if(p.jk='L','Laki-laki','Perempuan') jk, "
                    + "ifnull(dp.kd_penyakit,'') kd_penyakit, ifnull(py.nm_penyakit,'') nm_penyakit, if(rp.kd_pj='B01',concat(pj.png_jawab,' - ',bs.peserta),pj.png_jawab) png_jawab, "
                    + "ifnull(rm.perujuk,'') asalrujukan, IF(LENGTH(p.no_tlp)<5,'000000',p.no_tlp) no_tlp from pasien p "
                    + "inner join reg_periksa rp on rp.no_rkm_medis=p.no_rkm_medis "
                    + "inner join kelurahan kl ON kl.kd_kel=p.kd_kel "
                    + "inner join kecamatan kc ON kc.kd_kec=p.kd_kec "
                    + "inner join kabupaten kb ON kb.kd_kab=p.kd_kab "
                    + "inner join penjab pj on pj.kd_pj=rp.kd_pj "
                    + "left join diagnosa_pasien dp on dp.no_rawat=rp.no_rawat and dp.prioritas='1' "
                    + "left join penyakit py on py.kd_penyakit=dp.kd_penyakit "
                    + "left join rujuk_masuk rm on rm.no_rawat=rp.no_rawat "
                    + "left join bridging_sep bs on bs.no_rawat=rp.no_rawat where rp.no_rawat = '" + norawat + "' order by bs.urutan_sep desc limit 1");
            try {
                rs=ps.executeQuery();
                if(rs.next()){
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    TNmPasien.setText(rs.getString("nm_pasien"));
                    Umur.setText(rs.getString("umurdaftar") + " " + rs.getString("sttsumur"));
                    tglLahr.setText(rs.getString("tgl_lahir"));
                    NoKartu.setText(rs.getString("no_peserta"));
                    NIK.setText(rs.getString("no_ktp"));
                    Alamat.setText(rs.getString("alamat"));
                    Kabupaten.setText(rs.getString("nm_kab"));
                    Kecamatan.setText(rs.getString("nm_kec"));
                    Kelurahan.setText(rs.getString("nm_kel"));
                    tgl_masuk.setText(rs.getString("tgl_registrasi"));                      
                    jnsBayar.setText(rs.getString("png_jawab"));
                    nmjk.setText(rs.getString("jk"));
                    asalRujukan.setText(rs.getString("asalrujukan"));
                    TNoTelp.setText(rs.getString("no_tlp"));
                    stts_lanjt = rs.getString("status_lanjut"); 
                    
                    if (stts_lanjt.equals("Ralan")) {
                        TDiagnosa.setText(Sequel.cariIsi("select ifnull(diagnosa,'') from pemeriksaan_ralan where no_rawat='" + norawat + "'"));
                        tglKeluar.setText(rs.getString("tgl_registrasi"));
                        nmRuangPoli.setText(Sequel.cariIsi("select nm_poli from reg_periksa rp inner join poliklinik pl on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + norawat + "'"));
                        KddiagUtama.setText(rs.getString("kd_penyakit"));
                        diagnosaUtama.setText(rs.getString("nm_penyakit"));
                        KddiagSekun1.setText(Sequel.cariIsi("select ifnull(kd_penyakit,'') from diagnosa_pasien where no_rawat='" + norawat + "' and prioritas='2' and status='Ralan'"));
                        diagnosaSekunder1.setText(Sequel.cariIsi("select ifnull(nm_penyakit,'') from penyakit where kd_penyakit='" + KddiagSekun1.getText() + "'"));
                        KddiagSekun2.setText(Sequel.cariIsi("select ifnull(kd_penyakit,'') from diagnosa_pasien where no_rawat='" + norawat + "' and prioritas='3' and status='Ralan'"));
                        diagnosaSekunder2.setText(Sequel.cariIsi("select ifnull(nm_penyakit,'') from penyakit where kd_penyakit='" + KddiagSekun2.getText() + "'"));
                        KddiagSekun3.setText(Sequel.cariIsi("select ifnull(kd_penyakit,'') from diagnosa_pasien where no_rawat='" + norawat + "' and prioritas='4' and status='Ralan'"));
                        diagnosaSekunder3.setText(Sequel.cariIsi("select ifnull(nm_penyakit,'') from penyakit where kd_penyakit='" + KddiagSekun3.getText() + "'"));
                        sttsPulang.setText("-");
                    } else {
                        TDiagnosa.setText(Sequel.cariIsi("select ifnull(diagnosa_awal,'') from kamar_inap where no_rawat='" + norawat + "' and stts_pulang not in ('-','Pindah Kamar')"));
                        tglKeluar.setText(Sequel.cariIsi("select tgl_keluar from kamar_inap where no_rawat='" + norawat + "' and stts_pulang not in ('-','Pindah Kamar')"));
                        nmRuangPoli.setText(Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                                + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + norawat + "' and ki.stts_pulang not in ('-','Pindah Kamar')"));
                        KddiagUtama.setText(rs.getString("kd_penyakit"));
                        diagnosaUtama.setText(rs.getString("nm_penyakit"));
                        KddiagSekun1.setText(Sequel.cariIsi("select ifnull(kd_penyakit,'') from diagnosa_pasien where no_rawat='" + norawat + "' and prioritas='2' and status='Ranap'"));
                        diagnosaSekunder1.setText(Sequel.cariIsi("select ifnull(nm_penyakit,'') from penyakit where kd_penyakit='" + KddiagSekun1.getText() + "'"));
                        KddiagSekun2.setText(Sequel.cariIsi("select ifnull(kd_penyakit,'') from diagnosa_pasien where no_rawat='" + norawat + "' and prioritas='3' and status='Ranap'"));
                        diagnosaSekunder2.setText(Sequel.cariIsi("select ifnull(nm_penyakit,'') from penyakit where kd_penyakit='" + KddiagSekun2.getText() + "'"));
                        KddiagSekun3.setText(Sequel.cariIsi("select ifnull(kd_penyakit,'') from diagnosa_pasien where no_rawat='" + norawat + "' and prioritas='4' and status='Ranap'"));
                        diagnosaSekunder3.setText(Sequel.cariIsi("select ifnull(nm_penyakit,'') from penyakit where kd_penyakit='" + KddiagSekun3.getText() + "'"));
                        sttsPulang.setText(Sequel.cariIsi("select ki.stts_pulang from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                                + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + norawat + "' and ki.stts_pulang not in ('-','Pindah Kamar')"));
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }
    }
    
    private void editData() {
        pesanGagal = "";
        status = "";
        pesan = "";
        try {
            if (api.MintaToken().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Token tidak diterima....!");
            } else {
                url = koneksiDB.URLAPIKANKER() + "/t_register_penyakit/update";
                headers = new HttpHeaders();
                headers.add("X-Api-Key", "sirskemkes");
                headers.add("X-Token", api.MintaToken());
                headers.add("Content-Type", "application/json");

                requestJson = "{\"id\" : \"" + id_simpan + "\","
                        + "\"id_jenis_pelaporan\" : \"2\","
                        + "\"id_jenis_kasus\" : \"2.5\","
                        + "\"nik\" : \"" + NIK.getText() + "\","
                        + "\"nama_pasien\" : \"" + TNmPasien.getText() + "\","
                        + "\"id_jenis_kelamin\" : \"" + kdjk.getText() + "\","
                        + "\"tanggal_lahir\" : \"" + tglLahr.getText() + "\","
                        + "\"alamat\" : \"" + Alamat.getText() + "\","
                        + "\"id_kelurahan\" : \"" + KdKel.getText() + "\","
                        + "\"id_kecamatan\" : \"" + KdKec.getText() + "\","
                        + "\"id_kab_kota\" : \"" + KdKab.getText() + "\","
                        + "\"id_provinsi\" : \"" + KdProp.getText() + "\","
                        + "\"alamat_tinggal\" : \"" + TAlamatDom.getText() + "\","
                        + "\"id_kelurahan_tinggal\" : \"" + KdKelDom.getText() + "\","
                        + "\"id_kecamatan_tinggal\" : \"" + KdKecDom.getText() + "\","
                        + "\"id_kab_kota_tinggal\" : \"" + KdKabDom.getText() + "\","
                        + "\"id_provinsi_tinggal\" : \"" + KdPropDom.getText() + "\","
                        + "\"kontak_pasien\" : \"" + TNoTelp.getText() + "\","
                        + "\"tanggal_masuk\" : \"" + tgl_masuk.getText() + "\","
                        + "\"id_cara_masuk_pasien\" : \"" + Kdcm.getText() + "\","
                        + "\"id_asal_rujukan_pasien\" : \"" + Kdrujukan.getText() + "\","
                        + "\"asal_rujukan_pasien_fasyankes_lainnya\" : \"" + asalRujukanFasyankes.getText() + "\","
                        + "\"id_diagnosa_masuk\" : \"" + KdicdDM.getText() + "\","
                        + "\"id_sub_instalasi_unit\" : \"" + Kdsub.getText() + "\","
                        + "\"id_diagnosa_utama\" : \"" + KddiagUtama.getText() + "\","
                        + "\"id_diagnosa_sekunder1\" : \"" + KddiagSekun1.getText() + "\","
                        + "\"id_diagnosa_sekunder2\" : \"" + KddiagSekun2.getText() + "\","
                        + "\"id_diagnosa_sekunder3\" : \"" + KddiagSekun3.getText() + "\","
                        + "\"tanggal_diagnosa\" : \"" + Valid.SetTgl(tglDiagnosa.getSelectedItem() + "") + "\","
                        + "\"tanggal_keluar\" : \"" + tglKeluar.getText() + "\","
                        + "\"id_cara_keluar\" : \"" + KdcaraKlr.getText() + "\","
                        + "\"id_keadaan_keluar\" : \"" + KdkeadaanKlr.getText() + "\","
                        + "\"id_sebab_kematian_langsung_1a\" : \"" + KdsbbMati1A.getText() + "\","
                        + "\"id_sebab_kematian_antara_1b\" : \"" + KdsbbMati1B.getText() + "\","
                        + "\"id_sebab_kematian_antara_1c\" : \"" + KdsbbMati1C.getText() + "\","
                        + "\"id_sebab_kematian_dasar_1d\" : \"" + KdsbbMati1D.getText() + "\","
                        + "\"id_kondisi_yg_berkontribusi_thdp_kematian\" : \"" + Kdkondisi.getText() + "\","
                        + "\"sebab_dasar_kematian\" : \"" + sebabDasarMati.getText() + "\","
                        + "\"id_cara_bayar\" : \"" + KdcaraByr.getText() + "\","
                        + "\"nomor_bpjs\" : \"" + NoKartu.getText() + "\" }";

                System.out.println("JSON dikirim : " + requestJson);
                requestEntity = new HttpEntity(requestJson, headers);
                requestJson = api.getRest().exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
                root = mapper.readTree(requestJson);
                status = root.path("status").asText();
                pesan = root.path("message").asText();
                System.out.println("Status : " + status + ", Pesan : " + pesan + ", ID Simpan : " + root.path("id_simpan").asText());

                if (status.equals("true")) {
                    if (Sequel.mengedittf("data_kanker_bridging", "id_simpan=?", "nik=?,nama_pasien=?,id_jenis_kelamin=?,jenis_kelamin=?,tanggal_lahir=?,alamat=?,"
                            + "id_kelurahan=?,kelurahan=?,id_kecamatan=?,kecamatan=?,id_kab_kota=?,kab_kota=?,id_provinsi=?,provinsi=?,alamat_tinggal=?,id_kelurahan_tinggal=?,"
                            + "kelurahan_tinggal=?,id_kecamatan_tinggal=?,kecamatan_tinggal=?,id_kab_kota_tinggal=?,kab_kota_tinggal=?,id_provinsi_tinggal=?,"
                            + "provinsi_tinggal=?,kontak_pasien=?,tanggal_masuk=?,id_cara_masuk_pasien=?,cara_masuk_pasien=?,id_asal_rujukan_pasien=?,asal_rujukan_pasien=?,"
                            + "asal_rujukan_pasien_fasyankes_lainnya=?,id_diagnosa_masuk=?,id_sub_instalasi_unit=?,sub_instalasi_unit=?,id_diagnosa_utama=?,id_diagnosa_sekunder1=?,"
                            + "id_diagnosa_sekunder2=?,id_diagnosa_sekunder3=?,tanggal_diagnosa=?,tanggal_keluar=?,id_cara_keluar=?,cara_keluar=?,id_keadaan_keluar=?,"
                            + "keadaan_keluar=?,id_sebab_kematian_langsung_1a=?,id_sebab_kematian_antara_1b=?,id_sebab_kematian_antara_1c=?,id_sebab_kematian_dasar_1d=?,"
                            + "id_kondisi_yg_berkontribusi_thdp_kematian=?,sebab_dasar_kematian=?,id_cara_bayar=?,cara_bayar=?,nomor_bpjs=?", 53, new String[]{
                                NIK.getText(), TNmPasien.getText(), kdjk.getText(), nmjk.getText(), tglLahr.getText(), Alamat.getText(), KdKel.getText(), Kelurahan.getText(),
                                KdKec.getText(), Kecamatan.getText(), KdKab.getText(), Kabupaten.getText(), KdProp.getText(), Propinsi.getText(), TAlamatDom.getText(),
                                KdKelDom.getText(), KelurahanDom.getText(), KdKecDom.getText(), KecamatanDom.getText(), KdKabDom.getText(), KabupatenDom.getText(),
                                KdPropDom.getText(), PropinsiDom.getText(), TNoTelp.getText(), tgl_masuk.getText(), Kdcm.getText(), cara_masuk.getText(), Kdrujukan.getText(),
                                asalRujukan.getText(), asalRujukanFasyankes.getText(), KdicdDM.getText(), Kdsub.getText(), SubInstalasi.getText(), KddiagUtama.getText(), 
                                KddiagSekun1.getText(), KddiagSekun2.getText(), KddiagSekun3.getText(), Valid.SetTgl(tglDiagnosa.getSelectedItem() + ""),
                                tglKeluar.getText(), KdcaraKlr.getText(), CaraKeluar.getText(), KdkeadaanKlr.getText(), KeadaanKeluar.getText(), KdsbbMati1A.getText(),
                                KdsbbMati1B.getText(), KdsbbMati1C.getText(), KdsbbMati1D.getText(), Kdkondisi.getText(), sebabDasarMati.getText(), KdcaraByr.getText(),
                                CaraBayar.getText(), NoKartu.getText(), tbKanker.getValueAt(tbKanker.getSelectedRow(), 1).toString()
                            }) == true) {
                        emptTeks();
                        tampil();
                        JOptionPane.showMessageDialog(null, "Info dari Kemenkes Bridging Penyakit Kanker : " + pesan);
                        TabRawat.setSelectedIndex(1);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Periksa kembali data yang akan diperbaiki..!!");
                }
            }

        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            JOptionPane.showMessageDialog(null, "Periksa kembali data yang akan diperbaiki..!!");
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server Kemenkes terputus....!");
            }
        }
    }

    private void simpanData() {
        pesanGagal = "";
        status = "";
        pesan = "";
        try {
            if (api.MintaToken().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Token tidak diterima....!");
            } else {
                url = koneksiDB.URLAPIKANKER() + "/t_register_penyakit/add";
                headers = new HttpHeaders();
                headers.add("X-Api-Key", "sirskemkes");
                headers.add("X-Token", api.MintaToken());   
                headers.add("Content-Type", "application/json");
                
                requestJson = "{\"id_jenis_pelaporan\" : \"2\","
                        + "\"id_jenis_kasus\" : \"2.5\","
                        + "\"nik\" : \"" + NIK.getText() + "\","
                        + "\"nama_pasien\" : \"" + TNmPasien.getText() + "\","
                        + "\"id_jenis_kelamin\" : \"" + kdjk.getText() + "\","
                        + "\"tanggal_lahir\" : \"" + tglLahr.getText() + "\","
                        + "\"alamat\" : \"" + Alamat.getText() + "\","
                        + "\"id_kelurahan\" : \"" + KdKel.getText() + "\","
                        + "\"id_kecamatan\" : \"" + KdKec.getText() + "\","
                        + "\"id_kab_kota\" : \"" + KdKab.getText() + "\","
                        + "\"id_provinsi\" : \"" + KdProp.getText() + "\","
                        + "\"alamat_tinggal\" : \"" + TAlamatDom.getText() + "\","
                        + "\"id_kelurahan_tinggal\" : \"" + KdKelDom.getText() + "\","
                        + "\"id_kecamatan_tinggal\" : \"" + KdKecDom.getText() + "\","
                        + "\"id_kab_kota_tinggal\" : \"" + KdKabDom.getText() + "\","
                        + "\"id_provinsi_tinggal\" : \"" + KdPropDom.getText() + "\","
                        + "\"kontak_pasien\" : \"" + TNoTelp.getText() + "\","
                        + "\"tanggal_masuk\" : \"" + tgl_masuk.getText() + "\","
                        + "\"id_cara_masuk_pasien\" : \"" + Kdcm.getText() + "\","
                        + "\"id_asal_rujukan_pasien\" : \"" + Kdrujukan.getText() + "\","
                        + "\"asal_rujukan_pasien_fasyankes_lainnya\" : \"" + asalRujukanFasyankes.getText() + "\","
                        + "\"id_diagnosa_masuk\" : \"" + KdicdDM.getText() + "\","
                        + "\"id_sub_instalasi_unit\" : \"" + Kdsub.getText() + "\","
                        + "\"id_diagnosa_utama\" : \"" + KddiagUtama.getText() + "\","
                        + "\"id_diagnosa_sekunder1\" : \"" + KddiagSekun1.getText() + "\","
                        + "\"id_diagnosa_sekunder2\" : \"" + KddiagSekun2.getText() + "\","
                        + "\"id_diagnosa_sekunder3\" : \"" + KddiagSekun3.getText() + "\","
                        + "\"tanggal_diagnosa\" : \"" + Valid.SetTgl(tglDiagnosa.getSelectedItem() + "") + "\","
                        + "\"tanggal_keluar\" : \"" + tglKeluar.getText() + "\","
                        + "\"id_cara_keluar\" : \"" + KdcaraKlr.getText() + "\","
                        + "\"id_keadaan_keluar\" : \"" + KdkeadaanKlr.getText() + "\","
                        + "\"id_sebab_kematian_langsung_1a\" : \"" + KdsbbMati1A.getText() + "\","
                        + "\"id_sebab_kematian_antara_1b\" : \"" + KdsbbMati1B.getText() + "\","
                        + "\"id_sebab_kematian_antara_1c\" : \"" + KdsbbMati1C.getText() + "\","
                        + "\"id_sebab_kematian_dasar_1d\" : \"" + KdsbbMati1D.getText() + "\","
                        + "\"id_kondisi_yg_berkontribusi_thdp_kematian\" : \"" + Kdkondisi.getText() + "\","
                        + "\"sebab_dasar_kematian\" : \"" + sebabDasarMati.getText() + "\","
                        + "\"id_cara_bayar\" : \"" + KdcaraByr.getText() + "\","
                        + "\"nomor_bpjs\" : \"" + NoKartu.getText() + "\" }";
                
                System.out.println("JSON dikirim : " + requestJson);
                requestEntity = new HttpEntity(requestJson, headers);
                requestJson = api.getRest().exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
                root = mapper.readTree(requestJson);
                status = root.path("status").asText();                
                pesan = root.path("message").asText();
                System.out.println("Status : " + status + ", Pesan : " + pesan + ", ID Simpan : " + root.path("id_simpan").asText());
                
                if (status.equals("true")) {                    
                    response = root.path("id_simpan");
                    if (Sequel.menyimpantf("data_kanker_bridging", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 57, new String[]{
                        TNoRw.getText(), response.asText(), "2", "2.5", NIK.getText(), TNmPasien.getText(), kdjk.getText(), nmjk.getText(), tglLahr.getText(), Alamat.getText(),
                        KdKel.getText(), Kelurahan.getText(), KdKec.getText(), Kecamatan.getText(), KdKab.getText(), Kabupaten.getText(), KdProp.getText(), Propinsi.getText(),
                        TAlamatDom.getText(), KdKelDom.getText(), KelurahanDom.getText(), KdKecDom.getText(), KecamatanDom.getText(), KdKabDom.getText(), KabupatenDom.getText(),
                        KdPropDom.getText(), PropinsiDom.getText(), TNoTelp.getText(), tgl_masuk.getText(), Kdcm.getText(), cara_masuk.getText(), Kdrujukan.getText(), asalRujukan.getText(),
                        asalRujukanFasyankes.getText(), KdicdDM.getText(), Kdsub.getText(), SubInstalasi.getText(), KddiagUtama.getText(), KddiagSekun1.getText(), KddiagSekun2.getText(),
                        KddiagSekun3.getText(), Valid.SetTgl(tglDiagnosa.getSelectedItem() + ""), tglKeluar.getText(), KdcaraKlr.getText(), CaraKeluar.getText(), KdkeadaanKlr.getText(),
                        KeadaanKeluar.getText(), KdsbbMati1A.getText(), KdsbbMati1B.getText(), KdsbbMati1C.getText(), KdsbbMati1D.getText(), Kdkondisi.getText(), sebabDasarMati.getText(),
                        KdcaraByr.getText(), CaraBayar.getText(), NoKartu.getText(), Sequel.cariIsi("select now()")
                    }) == true) {
                        emptTeks();
                        tampil();
                        JOptionPane.showMessageDialog(null, "Info dari Kemenkes Bridging Penyakit Kanker : " + pesan);
                    }
                } else {                    
//                    if (root.path("message").isArray()) {
//                        for (JsonNode list : root.path("message")) {
//                            if (pesanGagal.equals("")) {
//                                pesanGagal = root.path("message").asText();
//                            } else {
//                                pesanGagal = pesanGagal + ", " + root.path("message").asText();
//                            }                            
//                        }
//                    }
                    JOptionPane.showMessageDialog(null, "Data masih kurang lengkap, silahkan koreksi kembali..!!");
                }
            }

        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            JOptionPane.showMessageDialog(null, "Data masih kurang lengkap, silahkan koreksi kembali..!!");
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server Kemenkes terputus....!");
            }
        }
    }
}
