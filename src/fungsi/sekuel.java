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
package fungsi;

import fungsi.koneksiDB;
import fungsi.akses;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.net.InetAddress;
//import org.apache.poi.hssf.record.formula.functions.Len;
//import org.apache.poi.hssf.record.formula.functions.Mid;
import uz.ncipro.calendar.JDateTimePicker;

/**
 *
 * @author Owner
 */
public final class sekuel {

    private javax.swing.ImageIcon icon = null;
    private javax.swing.ImageIcon iconThumbnail = null;
    private String folder, AKTIFKANTRACKSQL = koneksiDB.AKTIFKANTRACKSQL();
    private final Connection connect = koneksiDB.condb();
    private PreparedStatement ps, ps1;
    private ResultSet rs;
    private int angka = 0;
    private static int angka3 = 0;
    private double angka2 = 0;
    private String dicari = "", output = "", inputan = "", bulan = "", hari = "", romawi = "", ipAddresKomputer = "", user = "", 
            umur = "", sttsumur = "", umurOK = "";
    private static String dicari2 = "", output2 = "", inputan2 = "";
    private char enkrip;
    private static char enkrip2;
    private Integer Panjang_Input, panjangKey, cekData = 0;
    private static Integer Panjang_Input2, panjangKey2;
    private Date tanggal = new Date();
    private boolean bool = false;
    private DecimalFormat df2 = new DecimalFormat("####");
    private static final Properties prop = new Properties();
    private ResultSet rs1;
    String[] nominal = {"", "Satu", "Dua", "Tiga", "Empat", "Lima", "Enam",
        "Tujuh", "Delapan", "Sembilan", "Sepuluh", "Sebelas"};

    public sekuel() {
        super();
    }

    public void menyimpan(String table, String value, String sama) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void simpanReplaceInto(String table, String value, String sama) {
        try {
            ps = connect.prepareStatement("replace into " + table + " values(" + value + ")");
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void menyimpan2(String table, String value, String sama) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public boolean menyimpantf(String table, String value, String sama) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            ps.executeUpdate();
            if (ps != null) {
                ps.close();
            }
            return true;
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
            return false;
        }
    }

    public boolean menyimpantf2(String table, String value, String sama) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            ps.executeUpdate();
            if (ps != null) {
                ps.close();
            }
            return true;
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            return false;
        }
    }

    public void menyimpan(String table, String value, String sama, int i, String[] a) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void menyimpanPesanGagalnyaDiTerminal(String table, String value, String sama, int i, String[] a) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                System.out.println("Gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void menyimpan2(String table, String value, String sama, int i, String[] a) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public boolean menyimpantf(String table, String value, String sama, int i, String[] a) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            for (angka = 1; angka <= i; angka++) {
                ps.setString(angka, a[angka - 1]);
            }
            ps.executeUpdate();

            if (ps != null) {
                ps.close();
            }
            return true;
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            if (e.toString().contains("Duplicate")) {
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Ada kesalahan Query...!");
            }
            return false;
        }
    }

    public boolean menyimpantf3(String table, String value, String pesan, int i, String[] a) {
        bool = true;
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
                bool = true;
            } catch (Exception e) {
                bool = false;
                System.out.println("Notifikasi : " + e);
                if (e.toString().contains("Duplicate")) {
                    JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + pesan + " yang sama dimasukkan sebelumnya...!");
                } else {
                    JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Ada kesalahan Query...!");
                }
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            bool = false;
            System.out.println("Notifikasi : " + e);
            if (e.toString().contains("Duplicate")) {
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + pesan + " yang sama dimasukkan sebelumnya...!");
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Ada kesalahan Query...!");
            }
        }
        return bool;
    }

    public boolean menyimpantf2(String table, String value, String sama, int i, String[] a) {
        bool = true;
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
                bool = true;
            } catch (Exception e) {
                bool = false;
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            bool = false;
            System.out.println("Notifikasi : " + e);
        }
        return bool;
    }

    public void menyimpan(String table, String value, int i, String[] a) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void menyimpan2(String table, String value, int i, String[] a) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi " + table + " : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
            if (AKTIFKANTRACKSQL.equals("yes")) {
                dicari = "";
                for (angka = 1; angka <= i; angka++) {
                    dicari = dicari + "|" + a[angka - 1];
                }
            }
            SimpanTrack("insert into " + table + " values(" + dicari + ")");
        } catch (Exception e) {
        }
    }

    private void SimpanTrack(String sql) {
        if (AKTIFKANTRACKSQL.equals("yes")) {
            try {
                ps = connect.prepareStatement("insert into trackersql values(now(),?,?)");
                try {
                    ps.setString(1, sql);
                    ps.setString(2, akses.getkode());
                    ps.executeUpdate();
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (ps != null) {
                        ps.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }
    
    public void SimpanHistoriRekamMedis(String norawat, String dokumen, String proses) {
        ipAddresKomputer = "";
        user = "";
        try {
            InetAddress ip = InetAddress.getLocalHost();
            ipAddresKomputer = ip.getHostAddress();
            
            if (akses.getadmin() == true) {
                user = "-";
            } else {
                user = akses.getkode();
            }
            
            try {
                ps = connect.prepareStatement("insert into histori_petugas_erm values(?,?,?,?,?,now())");
                try {
                    ps.setString(1, norawat);
                    ps.setString(2, dokumen);
                    ps.setString(3, proses);
                    ps.setString(4, user);
                    ps.setString(5, ipAddresKomputer);
                    ps.executeUpdate();
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (ps != null) {
                        ps.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        } catch (Exception e) {
            System.out.println("Gagal mendapatkan alamat IP host : " + e.getMessage());
        }
    }

    public void Commit() {
        try {
            connect.commit();
        } catch (Exception e) {
        }
    }

    public void menyimpan(String table, String value, int i, String[] a, String acuan_field, String update, int j, String[] b) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            for (angka = 1; angka <= i; angka++) {
                ps.setString(angka, a[angka - 1]);
            }
            ps.executeUpdate();

            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            try {
                ps = connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);
                for (angka = 1; angka <= j; angka++) {
                    ps.setString(angka, b[angka - 1]);
                }
                ps.executeUpdate();

                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e2) {
                System.out.println("Notifikasi : " + e2);
            }
        }
    }

    public void menyimpan2(String table, String value, int i, String[] a, String acuan_field, String update, int j, String[] b) {
        try {
            dicari = "";
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            for (angka = 1; angka <= i; angka++) {
                dicari = dicari + ", " + a[angka - 1];
                ps.setString(angka, a[angka - 1]);
            }
            ps.executeUpdate();
            ps = connect.prepareStatement("insert into trackersql values( now(),'insert into " + table + " values(" + dicari + ")','','jadwal pegawai " + akses.getuser() + "','simpan')");
            ps.executeUpdate();

            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            try {
                dicari = "";
                ps = connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);
                for (angka = 1; angka <= j; angka++) {
                    dicari = dicari + ", " + a[angka - 1];
                    ps.setString(angka, b[angka - 1]);
                }
                ps.executeUpdate();
                ps = connect.prepareStatement("insert into trackersql values( now(),'update " + table + " set " + update.replaceAll("'", "") + " where " + acuan_field.replaceAll("'", "") + " " + dicari + "','" + e.toString().replaceAll("'", "") + "','jadwal pegawai " + akses.getuser() + "','update')");
                ps.executeUpdate();

                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e2) {
                System.out.println("Notifikasi : " + e2);
                dicari = "";
                for (angka = 1; angka <= j; angka++) {
                    dicari = dicari + ", " + a[angka - 1];
                }
                menyimpan("trackersql", "now(),'update " + table + " set " + update.replaceAll("'", "") + " where " + acuan_field.replaceAll("'", "") + " " + dicari + "','" + e.toString().replaceAll("'", "") + "','jadwal pegawai " + akses.getuser() + "','update'");
            }

        }
    }

    public void menyimpan3(String table, String value, int i, String[] a, String acuan_field, String update, int j, String[] b) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            for (angka = 1; angka <= i; angka++) {
                ps.setString(angka, a[angka - 1]);
            }
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Proses simpan berhasil..!!");
            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            try {
                ps = connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);
                for (angka = 1; angka <= j; angka++) {
                    ps.setString(angka, b[angka - 1]);
                }
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Proses simpan berhasil..!!");
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e2) {
                System.out.println("Notifikasi : " + e2);
            }
        }
    }

    public void menyimpan(String table, String value) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void menyimpan(String table, String isisimpan, String isiedit, String acuan_field) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + isisimpan + ")");
            ps.executeUpdate();
            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            try {
                ps = connect.prepareStatement("update " + table + " set " + isiedit + " where " + acuan_field);
                ps.executeUpdate();
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Edit : " + ex);
            }
        }
    }

    public void menyimpan(String table, String value, String sama, JTextField AlmGb) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ",?)");
            try {
                ps.setBinaryStream(1, new FileInputStream(AlmGb.getText()), new File(AlmGb.getText()).length());
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

    }

    public void menyimpan(String table, String value, String sama, JTextField AlmGb, JTextField AlmPhoto) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ",?,?)");
            try {
                ps.setBinaryStream(1, new FileInputStream(AlmGb.getText()), new File(AlmGb.getText()).length());
                ps.setBinaryStream(2, new FileInputStream(AlmPhoto.getText()), new File(AlmPhoto.getText()).length());
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void menyimpan2logo(String table, String value, String sama, JTextField AlmGb,
            JTextField AlmPhoto, JTextField AlmPhoto1, JTextField AlmPhoto2, JTextField AlmPhoto3) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ",?,?,?,?)");
            try {
                ps.setBinaryStream(1, new FileInputStream(AlmGb.getText()), new File(AlmGb.getText()).length());
                ps.setBinaryStream(2, new FileInputStream(AlmPhoto.getText()), new File(AlmPhoto.getText()).length());
                ps.setBinaryStream(3, new FileInputStream(AlmPhoto1.getText()), new File(AlmPhoto1.getText()).length());
                ps.setBinaryStream(4, new FileInputStream(AlmPhoto2.getText()), new File(AlmPhoto2.getText()).length());
                ps.setBinaryStream(5, new FileInputStream(AlmPhoto3.getText()), new File(AlmPhoto3.getText()).length());
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void meghapus(String table, String field, String nilai_field) {
        try {
            ps = connect.prepareStatement("delete from " + table + " where " + field + "=?");
            try {
                ps.setString(1, nilai_field);
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, data gagal dihapus. Kemungkinan data tersebut masih dipakai di table lain...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void mengedit(String table, String acuan_field, String update) {
        try {
            ps = connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Gagal Mengedit. Mungkin kode sudah digunakan sebelumnya...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void meghapus2(String table, String field, String nilai_field) {
        try {
            ps = connect.prepareStatement("delete from " + table + " where " + field + "=?");
            try {
                ps.setString(1, nilai_field);
                ps.executeUpdate();
                System.out.println("Proses hapus data pada tabel " + table + " berhasil...!!!!");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, data gagal dihapus. Kemungkinan data tersebut masih dipakai di table lain...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public boolean mengedittf(String table, String acuan_field, String update) {
        bool = true;
        try {
            ps = connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);
            try {
                ps.executeUpdate();
                bool = true;
            } catch (Exception e) {
                bool = false;
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Gagal Mengedit. Mungkin kode sudah digunakan sebelumnya...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            bool = false;
            System.out.println("Notifikasi : " + e);
        }
        return bool;
    }

    public void mengedit(String table, String acuan_field, String update, int i, String[] a) {
        try {
            ps = connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Gagal Mengedit. Periksa kembali data...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void mengedit2(String table, String acuan_field, String update, int i, String[] a) {
        try {
            ps = connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Proses edit berhasil...!!!!");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Gagal mengedit. Periksa kembali data...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void mengedit3(String table, String acuan_field, String update, int i, String[] a) {
        try {
            ps = connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public boolean mengedittf(String table, String acuan_field, String update, int i, String[] a) {
        bool = true;
        try {
            ps = connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
                bool = true;
            } catch (Exception e) {
                bool = false;
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Gagal Mengedit. Periksa kembali data...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            bool = false;
            System.out.println("Notifikasi : " + e);
        }
        return bool;
    }

    public void mengedit(String table, String acuan_field, String update, JTextField AlmGb) {
        try {
            ps = connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);
            try {
                ps.setBinaryStream(1, new FileInputStream(AlmGb.getText()), new File(AlmGb.getText()).length());
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Pilih dulu data yang mau anda edit...\n Klik data pada table untuk memilih...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void query(String qry) {
        try {
            ps = connect.prepareStatement(qry);
            try {
                ps.executeQuery();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Query tidak bisa dijalankan...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void queryu(String qry) {
        try {
            ps = connect.prepareStatement(qry);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Query tidak bisa dijalankan...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public boolean queryutf(String qry) {
        bool = false;
        try {
            ps = connect.prepareStatement(qry);
            try {
                ps.executeUpdate();
                bool = true;
            } catch (Exception e) {
                bool = false;
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Query tidak bisa dijalankan...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            bool = false;
            System.out.println("Notifikasi : " + e);
        }
        return bool;
    }

    public void queryu(String qry, String parameter) {
        try {
            ps = connect.prepareStatement(qry);
            try {
                ps.setString(1, parameter);
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Query tidak bisa dijalankan...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void queryu2(String qry) {
        try {
            ps = connect.prepareStatement(qry);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void queryu2(String qry, int i, String[] a) {
        try {
            try {
                ps = connect.prepareStatement(qry);
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public boolean queryu2tf(String qry, int i, String[] a) {
        bool = false;
        try {
            try {
                ps = connect.prepareStatement(qry);
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
                bool = true;
            } catch (Exception e) {
                bool = false;
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        return bool;
    }

    public void queryu3(String qry, int i, String[] a) {
        try {
            try {
                ps = connect.prepareStatement(qry);
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void AutoComitFalse() {
        try {
            connect.setAutoCommit(false);
        } catch (Exception e) {
        }
    }

    public void AutoComitTrue() {
        try {
            connect.setAutoCommit(true);
        } catch (Exception e) {
        }
    }

    public void cariIsi(String sql, JComboBox cmb) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    String dicari = rs.getString(1);
                    cmb.setSelectedItem(dicari);
                } else {
                    cmb.setSelectedItem("");
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
    }

    public void cariIsi(String sql, JDateTimePicker dtp) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    try {
                        dtp.setDisplayFormat("yyyy-MM-dd");
                        dtp.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(1)));
                        dtp.setDisplayFormat("dd-MM-yyyy");
                    } catch (Exception ex) {
                        System.out.println(ex);
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
    }

    public void cariIsi(String sql, JTextField txt) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    txt.setText(rs.getString(1));
                } else {
                    txt.setText("");
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
    }

    public int cekRekamMedisIGD(String norawat) {
        angka = 0;
        int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0, h = 0, i = 0, j = 0, k = 0, l = 0, m = 0, n = 0, o = 0, p = 0, q = 0, r = 0, s = 0, t = 0;
        a = cariInteger("select count(-1) from triase_igd where no_rawat='" + norawat + "'");
        b = cariInteger("select count(-1) from penilaian_awal_medis_igd where no_rawat='" + norawat + "'");
        c = cariInteger("select count(-1) from penilaian_awal_keperawatan_igdrz where no_rawat='" + norawat + "'");
        d = cariInteger("select count(-1) from penilaian_awal_keperawatan_igd_resiko where no_rawat='" + norawat + "'");
        e = cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat='" + norawat + "'");
        f = cariInteger("select count(-1) from pemberian_obat where no_rawat='" + norawat + "'");
        g = cariInteger("select count(-1) from pelaksana_pemberian_obat where no_rawat='" + norawat + "'");
        h = cariInteger("select count(-1) from penilaian_awal_medis_obstetri_ralan where no_rawat='" + norawat + "'");
        i = cariInteger("select count(-1) from cppt where no_rawat='" + norawat + "' and flag_hapus='tidak'");
        j = cariInteger("select count(-1) from cppt_konfirmasi_terapi where no_rawat='" + norawat + "'");
        k = cariInteger("select count(-1) from surat_tindakan_kedokteran where no_rawat='" + norawat + "'");
        l = cariInteger("select count(-1) from permintaan_lab_raza where no_rawat='" + norawat + "'");
        m = cariInteger("select count(-1) from permintaan_radiologi where no_rawat='" + norawat + "'");
        n = cariInteger("select count(-1) from surat_istirahat_sakit where no_rawat='" + norawat + "'");
        o = cariInteger("select count(-1) from surat_keterangan_sakit where no_rawat='" + norawat + "'");
        p = cariInteger("select count(-1) from catatan_resep where no_rawat='" + norawat + "'");
        q = cariInteger("select count(-1) from lembar_observasi where no_rawat='" + norawat + "'");
        r = cariInteger("select count(-1) from detail_lembar_observasi where no_rawat='" + norawat + "'");
        s = cariInteger("select count(-1) from asesmen_pra_sedasi where no_rawat='" + norawat + "'");
        t = cariInteger("select count(-1) from triase_pediatrik where no_rawat='" + norawat + "'");

        System.out.println("Notifikasi : " + cariIsi("select if(count(-1)=0,'tabel triase_igd KOSONG','tabel triase_igd ADA DATANYA (" + norawat + ")') from triase_igd where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel penilaian_awal_medis_igd KOSONG','tabel penilaian_awal_medis_igd ADA DATANYA (" + norawat + ")') from penilaian_awal_medis_igd where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel penilaian_awal_keperawatan_igdrz KOSONG','tabel penilaian_awal_keperawatan_igdrz ADA DATANYA (" + norawat + ")') from penilaian_awal_keperawatan_igdrz where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel penilaian_awal_keperawatan_igd_resiko KOSONG','tabel penilaian_awal_keperawatan_igd_resiko ADA DATANYA (" + norawat + ")') from penilaian_awal_keperawatan_igd_resiko where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel transfer_serah_terima_pasien_igd KOSONG','tabel transfer_serah_terima_pasien_igd ADA DATANYA (" + norawat + ")') from transfer_serah_terima_pasien_igd where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel pemberian_obat KOSONG','tabel pemberian_obat ADA DATANYA (" + norawat + ")') from pemberian_obat where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel pelaksana_pemberian_obat KOSONG','tabel pelaksana_pemberian_obat ADA DATANYA (" + norawat + ")') from pelaksana_pemberian_obat where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel penilaian_awal_medis_obstetri_ralan KOSONG','tabel penilaian_awal_medis_obstetri_ralan ADA DATANYA (" + norawat + ")') from penilaian_awal_medis_obstetri_ralan where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel cppt KOSONG','tabel cppt ADA DATANYA (" + norawat + ")') from cppt where no_rawat='" + norawat + "' and flag_hapus='tidak'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel cppt_konfirmasi_terapi KOSONG','tabel cppt_konfirmasi_terapi ADA DATANYA (" + norawat + ")') from cppt_konfirmasi_terapi where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel surat_tindakan_kedokteran KOSONG','tabel surat_tindakan_kedokteran ADA DATANYA (" + norawat + ")') from surat_tindakan_kedokteran where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel permintaan_lab_raza KOSONG','tabel permintaan_lab_raza ADA DATANYA (" + norawat + ")') from permintaan_lab_raza where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel permintaan_radiologi KOSONG','tabel permintaan_radiologi ADA DATANYA (" + norawat + ")') from permintaan_radiologi where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel surat_istirahat_sakit KOSONG','tabel surat_istirahat_sakit ADA DATANYA (" + norawat + ")') from surat_istirahat_sakit where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel surat_keterangan_sakit KOSONG','tabel surat_keterangan_sakit ADA DATANYA (" + norawat + ")') from surat_keterangan_sakit where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel catatan_resep KOSONG','tabel catatan_resep ADA DATANYA (" + norawat + ")') from catatan_resep where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel lembar_observasi KOSONG','tabel lembar_observasi ADA DATANYA (" + norawat + ")') from lembar_observasi where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel detail_lembar_observasi KOSONG','tabel detail_lembar_observasi ADA DATANYA (" + norawat + ")') from detail_lembar_observasi where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel asesmen_pra_sedasi KOSONG','tabel asesmen_pra_sedasi ADA DATANYA (" + norawat + ")') from asesmen_pra_sedasi where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel triase_pediatrik KOSONG','tabel triase_pediatrik ADA DATANYA (" + norawat + ")') from triase_pediatrik where no_rawat='" + norawat + "'") + "\n"
        );

        angka = a + b + c + d + e + f + g + h + i + j + k + l + m + n + o + p + q + r + s + t;
        return angka;
    }
    
    public int cekRekamMedisRanap(String norawat) {
        angka = 0;
        int rm1 = 0, rm2 = 0, rm3 = 0, rm4 = 0, rm5 = 0, rm6 = 0, rm7 = 0, rm8 = 0, rm9 = 0, rm10 = 0, rm11 = 0, rm12 = 0, rm13 = 0,
                rm14 = 0, rm15 = 0, rm16 = 0, rm17 = 0, rm18 = 0, rm19 = 0, rm20 = 0, rm21 = 0, rm22 = 0, rm23 = 0, rm24 = 0, rm25 = 0, rm26 = 0,
                rm27 = 0, rm28 = 0, rm29 = 0, rm30 = 0, rm31 = 0, rm32 = 0, rm33 = 0, rm34 = 0, rm35 = 0, rm36 = 0, rm37 = 0, rm38 = 0, rm39 = 0,
                rm40 = 0, rm41 = 0, rm42 = 0, rm43 = 0, rm44 = 0, rm45 = 0, rm46 = 0, rm47 = 0, rm48 = 0, rm49 = 0, rm50 = 0, rm51 = 0, rm52 = 0,
                rm53 = 0, rm54 = 0, rm55 = 0, rm56 = 0, rm57 = 0, rm58 = 0, rm59 = 0, rm60 = 0, rm61 = 0, rm62 = 0, rm63 = 0, rm64 = 0, rm65 = 0;

        rm1 = cariInteger("select count(-1) from catatan_tindakan_keperawatan where no_rawat='" + norawat + "'");
        rm2 = cariInteger("select count(-1) from evaluasi_catatan_tindakan_keperawatan where no_rawat='" + norawat + "'");
        rm3 = cariInteger("select count(-1) from manajemen_catatan_tindakan_keperawatan where no_rawat='" + norawat + "'");
        rm4 = cariInteger("select count(-1) from surat_konsul_unit_ranap where no_rawat='" + norawat + "'");
        rm5 = cariInteger("select count(-1) from asesmen_medik_dewasa_ranap where no_rawat='" + norawat + "'");
        rm6 = cariInteger("select count(-1) from ringkasan_pulang_ranap where no_rawat='" + norawat + "'");
        rm7 = cariInteger("select count(-1) from cppt where no_rawat='" + norawat + "' and status='Ranap' and flag_hapus='tidak'");
        rm8 = cariInteger("select count(-1) from cppt_konfirmasi_terapi where no_rawat='" + norawat + "'");
        rm9 = cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat='" + norawat + "' and status='Ranap'");
        rm10 = cariInteger("select count(-1) from pemberian_obat where no_rawat='" + norawat + "' and nm_unit not like '%igd%'");
        rm11 = cariInteger("select count(-1) from pelaksana_pemberian_obat where no_rawat='" + norawat + "' and nm_unit not like '%igd%'");
        rm12 = cariInteger("select count(-1) from surat_tindakan_kedokteran where no_rawat='" + norawat + "'");
        rm13 = cariInteger("select count(-1) from spirometri where no_rawat='" + norawat + "'");
        rm14 = cariInteger("select count(-1) from data_persalinan where no_rawat='" + norawat + "'");
        rm15 = cariInteger("select count(-1) from permintaan_lab_raza where no_rawat='" + norawat + "' and status_rawat='Ranap'");
        rm16 = cariInteger("select count(-1) from permintaan_radiologi where no_rawat='" + norawat + "' and dari_unit not like '%igd%'");
        rm17 = cariInteger("select count(-1) from surat_istirahat_sakit where no_rawat='" + norawat + "'");
        rm18 = cariInteger("select count(-1) from surat_keterangan_sakit where no_rawat='" + norawat + "'");
        rm19 = cariInteger("select count(-1) from catatan_resep_ranap where no_rawat='" + norawat + "'");
        rm20 = cariInteger("select count(-1) from penilaian_awal_keperawatan_dewasa_ranap where no_rawat='" + norawat + "'");
        rm21 = cariInteger("select count(-1) from penilaian_awal_keperawatan_dewasa_ranap_resiko where no_rawat='" + norawat + "'");
        rm22 = cariInteger("select count(-1) from penilaian_awal_keperawatan_dewasa_ranap_decubitus where no_rawat='" + norawat + "'");
        rm23 = cariInteger("select count(-1) from pemantauan_harian_parental where no_rawat='" + norawat + "'");
        rm24 = cariInteger("select count(-1) from pemantauan_harian_24jam where no_rawat='" + norawat + "'");
        rm25 = cariInteger("select count(-1) from protokol_kemoterapi where no_rawat='" + norawat + "'");
        rm26 = cariInteger("select count(-1) from asesmen_ulang_resiko_jatuh where no_rawat='" + norawat + "'");
        rm27 = cariInteger("select count(-1) from detail_asesmen_ulang_resiko_jatuh where no_rawat='" + norawat + "'");
        rm28 = cariInteger("select count(-1) from pengelolaan_transfusi_darah where no_rawat='" + norawat + "'");
        rm29 = cariInteger("select count(-1) from monitoring_ews_dewasa where no_rawat='" + norawat + "'");
        rm30 = cariInteger("select count(-1) from penilaian_awal_keperawatan_anak_ranap where no_rawat='" + norawat + "'");
        rm31 = cariInteger("select count(-1) from penilaian_awal_keperawatan_anak_ranap_resiko where no_rawat='" + norawat + "'");
        rm32 = cariInteger("select count(-1) from asesmen_medik_anak_ranap where no_rawat='" + norawat + "'");
        rm33 = cariInteger("select count(-1) from asesmen_ulang_resiko_jatuh_anak where no_rawat='" + norawat + "'");
        rm34 = cariInteger("select count(-1) from detail_asesmen_ulang_resiko_jatuh_anak where no_rawat='" + norawat + "'");
        rm35 = cariInteger("select count(-1) from monitoring_pews_anak where no_rawat='" + norawat + "'");
        rm36 = cariInteger("select count(-1) from asesmen_restrain where no_rawat='" + norawat + "'");
        rm37 = cariInteger("select count(-1) from observasi_restrain where no_rawat='" + norawat + "'");
        rm38 = cariInteger("select count(-1) from skrining_gizi_ulang where no_rawat='" + norawat + "'");
        rm39 = cariInteger("select count(-1) from monev_asuhan_gizi where no_rawat='" + norawat + "'");
        rm40 = cariInteger("select count(-1) from assesmen_gizi_ulang where no_rawat='" + norawat + "'");
        rm41 = cariInteger("select count(-1) from lembar_observasi where no_rawat='" + norawat + "' and ruang_rawat not like '%igd%'");
        rm42 = cariInteger("select count(-1) from detail_lembar_observasi where no_rawat='" + norawat + "' and ruang_rawat not like '%igd%'");
        rm43 = cariInteger("select count(-1) from transfer_sebelum_tindakan where no_rawat='" + norawat + "'");
        rm44 = cariInteger("select count(-1) from transfer_sesudah_tindakan where no_rawat='" + norawat + "'");
        rm45 = cariInteger("select count(-1) from asesmen_pra_sedasi where no_rawat='" + norawat + "' and ruang_rawat not like '%igd%'");
        rm46 = cariInteger("select count(-1) from ceklis_pra_operasi where no_rawat='" + norawat + "'");
        rm47 = cariInteger("select count(-1) from ceklis_kesiapan_anestesi where no_rawat='" + norawat + "'");
        rm48 = cariInteger("select count(-1) from asesmen_pre_induksi where no_rawat='" + norawat + "'");
        rm49 = cariInteger("select count(-1) from asesmen_keperawatan_perioperatif where no_rawat='" + norawat + "'");
        rm50 = cariInteger("select count(-1) from hitungan_asesmen_keperawatan_perioperatif where no_rawat='" + norawat + "'");
        rm51 = cariInteger("select count(-1) from perencanaan_pulang_ranap where no_rawat='" + norawat + "'");
        rm52 = cariInteger("select count(-1) from ceklis_keselamatan_operasi1 where no_rawat='" + norawat + "'");
        rm53 = cariInteger("select count(-1) from ceklis_keselamatan_operasi2 where no_rawat='" + norawat + "'");
        rm54 = cariInteger("select count(-1) from ceklis_keselamatan_operasi3 where no_rawat='" + norawat + "'");
        rm55 = cariInteger("select count(-1) from catatan_material_operasi where no_rawat='" + norawat + "'");
        rm56 = cariInteger("select count(-1) from asesmen_medik_bedah_ranap where no_rawat='" + norawat + "'");
        rm57 = cariInteger("select count(-1) from asesmen_medik_perinatologi where no_rawat='" + norawat + "'");
        rm58 = cariInteger("select count(-1) from asesmen_keperawatan_perinatologi where no_rawat='" + norawat + "'");
        rm59 = cariInteger("select count(-1) from skor_apgar_downe_cap_jari_perinatologi where no_rawat='" + norawat + "'");
        rm60 = cariInteger("select count(-1) from pengamatan_menyusui_perinatologi where no_rawat='" + norawat + "'");        
        rm61 = cariInteger("select count(-1) from rekonsiliasi_obat where no_rawat='" + norawat + "'");
        rm62 = cariInteger("select count(-1) from rekonsiliasi_obat_igd where no_rawat='" + norawat + "'");
        rm63 = cariInteger("select count(-1) from serah_terima_bayi_pulang_perinatologi where no_rawat='" + norawat + "'");
        rm64 = cariInteger("select count(-1) from pemberian_informasi_edukasi where no_rawat='" + norawat + "'");
        rm65 = cariInteger("select count(-1) from penilaian_informasi_edukasi where no_rawat='" + norawat + "'");

        System.out.println("Notifikasi : " 
                + cariIsi("select if(count(-1)=0,'tabel catatan_tindakan_keperawatan KOSONG','tabel catatan_tindakan_keperawatan ADA DATANYA (" + norawat + ")') from catatan_tindakan_keperawatan where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel evaluasi_catatan_tindakan_keperawatan KOSONG','tabel evaluasi_catatan_tindakan_keperawatan ADA DATANYA (" + norawat + ")') from evaluasi_catatan_tindakan_keperawatan where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel manajemen_catatan_tindakan_keperawatan KOSONG','tabel manajemen_catatan_tindakan_keperawatan ADA DATANYA (" + norawat + ")') from manajemen_catatan_tindakan_keperawatan where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel surat_konsul_unit_ranap KOSONG','tabel surat_konsul_unit_ranap ADA DATANYA (" + norawat + ")') from surat_konsul_unit_ranap where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel asesmen_medik_dewasa_ranap KOSONG','tabel asesmen_medik_dewasa_ranap ADA DATANYA (" + norawat + ")') from asesmen_medik_dewasa_ranap where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel ringkasan_pulang_ranap KOSONG','tabel ringkasan_pulang_ranap ADA DATANYA (" + norawat + ")') from ringkasan_pulang_ranap where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel cppt KOSONG','tabel cppt ADA DATANYA (" + norawat + ")') from cppt where no_rawat='" + norawat + "' and status='Ranap' and flag_hapus='tidak'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel cppt_konfirmasi_terapi KOSONG','tabel cppt_konfirmasi_terapi ADA DATANYA (" + norawat + ")') from cppt_konfirmasi_terapi where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel transfer_serah_terima_pasien_igd KOSONG','tabel transfer_serah_terima_pasien_igd ADA DATANYA (" + norawat + ")') from transfer_serah_terima_pasien_igd where no_rawat='" + norawat + "' and status='Ranap'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel pemberian_obat KOSONG','tabel pemberian_obat ADA DATANYA (" + norawat + ")') from pemberian_obat where no_rawat='" + norawat + "' and nm_unit not like '%igd%'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel pelaksana_pemberian_obat KOSONG','tabel pelaksana_pemberian_obat ADA DATANYA (" + norawat + ")') from pelaksana_pemberian_obat where no_rawat='" + norawat + "' and nm_unit not like '%igd%'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel surat_tindakan_kedokteran KOSONG','tabel surat_tindakan_kedokteran ADA DATANYA (" + norawat + ")') from surat_tindakan_kedokteran where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel spirometri KOSONG','tabel spirometri ADA DATANYA (" + norawat + ")') from spirometri where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel data_persalinan KOSONG','tabel data_persalinan ADA DATANYA (" + norawat + ")') from data_persalinan where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel permintaan_lab_raza KOSONG','tabel permintaan_lab_raza ADA DATANYA (" + norawat + ")') from permintaan_lab_raza where no_rawat='" + norawat + "' and status_rawat='Ranap'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel permintaan_radiologi KOSONG','tabel permintaan_radiologi ADA DATANYA (" + norawat + ")') from permintaan_radiologi where no_rawat='" + norawat + "' and dari_unit not like '%igd%'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel surat_istirahat_sakit KOSONG','tabel surat_istirahat_sakit ADA DATANYA (" + norawat + ")') from surat_istirahat_sakit where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel surat_keterangan_sakit KOSONG','tabel surat_keterangan_sakit ADA DATANYA (" + norawat + ")') from surat_keterangan_sakit where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel catatan_resep_ranap KOSONG','tabel catatan_resep_ranap ADA DATANYA (" + norawat + ")') from catatan_resep_ranap where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel penilaian_awal_keperawatan_dewasa_ranap KOSONG','tabel penilaian_awal_keperawatan_dewasa_ranap ADA DATANYA (" + norawat + ")') from penilaian_awal_keperawatan_dewasa_ranap where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel penilaian_awal_keperawatan_dewasa_ranap_resiko KOSONG','tabel penilaian_awal_keperawatan_dewasa_ranap_resiko ADA DATANYA (" + norawat + ")') from penilaian_awal_keperawatan_dewasa_ranap_resiko where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel penilaian_awal_keperawatan_dewasa_ranap_decubitus KOSONG','tabel penilaian_awal_keperawatan_dewasa_ranap_decubitus ADA DATANYA (" + norawat + ")') from penilaian_awal_keperawatan_dewasa_ranap_decubitus where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel pemantauan_harian_parental KOSONG','tabel pemantauan_harian_parental ADA DATANYA (" + norawat + ")') from pemantauan_harian_parental where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel pemantauan_harian_24jam KOSONG','tabel pemantauan_harian_24jam ADA DATANYA (" + norawat + ")') from pemantauan_harian_24jam where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel protokol_kemoterapi KOSONG','tabel protokol_kemoterapi ADA DATANYA (" + norawat + ")') from protokol_kemoterapi where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel asesmen_ulang_resiko_jatuh KOSONG','tabel asesmen_ulang_resiko_jatuh ADA DATANYA (" + norawat + ")') from asesmen_ulang_resiko_jatuh where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel detail_asesmen_ulang_resiko_jatuh KOSONG','tabel detail_asesmen_ulang_resiko_jatuh ADA DATANYA (" + norawat + ")') from detail_asesmen_ulang_resiko_jatuh where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel pengelolaan_transfusi_darah KOSONG','tabel pengelolaan_transfusi_darah ADA DATANYA (" + norawat + ")') from pengelolaan_transfusi_darah where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel monitoring_ews_dewasa KOSONG','tabel monitoring_ews_dewasa ADA DATANYA (" + norawat + ")') from monitoring_ews_dewasa where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel penilaian_awal_keperawatan_anak_ranap KOSONG','tabel penilaian_awal_keperawatan_anak_ranap ADA DATANYA (" + norawat + ")') from penilaian_awal_keperawatan_anak_ranap where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel penilaian_awal_keperawatan_anak_ranap_resiko KOSONG','tabel penilaian_awal_keperawatan_anak_ranap_resiko ADA DATANYA (" + norawat + ")') from penilaian_awal_keperawatan_anak_ranap_resiko where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel asesmen_medik_anak_ranap KOSONG','tabel asesmen_medik_anak_ranap ADA DATANYA (" + norawat + ")') from asesmen_medik_anak_ranap where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel asesmen_ulang_resiko_jatuh_anak KOSONG','tabel asesmen_ulang_resiko_jatuh_anak ADA DATANYA (" + norawat + ")') from asesmen_ulang_resiko_jatuh_anak where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel detail_asesmen_ulang_resiko_jatuh_anak KOSONG','tabel detail_asesmen_ulang_resiko_jatuh_anak ADA DATANYA (" + norawat + ")') from detail_asesmen_ulang_resiko_jatuh_anak where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel monitoring_pews_anak KOSONG','tabel monitoring_pews_anak ADA DATANYA (" + norawat + ")') from monitoring_pews_anak where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel asesmen_restrain KOSONG','tabel asesmen_restrain ADA DATANYA (" + norawat + ")') from asesmen_restrain where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel observasi_restrain KOSONG','tabel observasi_restrain ADA DATANYA (" + norawat + ")') from observasi_restrain where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel skrining_gizi_ulang KOSONG','tabel skrining_gizi_ulang ADA DATANYA (" + norawat + ")') from skrining_gizi_ulang where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel monev_asuhan_gizi KOSONG','tabel monev_asuhan_gizi ADA DATANYA (" + norawat + ")') from monev_asuhan_gizi where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel assesmen_gizi_ulang KOSONG','tabel assesmen_gizi_ulang ADA DATANYA (" + norawat + ")') from assesmen_gizi_ulang where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel lembar_observasi KOSONG','tabel lembar_observasi ADA DATANYA (" + norawat + ")') from lembar_observasi where no_rawat='" + norawat + "' and ruang_rawat not like '%igd%'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel detail_lembar_observasi KOSONG','tabel detail_lembar_observasi ADA DATANYA (" + norawat + ")') from detail_lembar_observasi where no_rawat='" + norawat + "' and ruang_rawat not like '%igd%'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel transfer_sebelum_tindakan KOSONG','tabel transfer_sebelum_tindakan ADA DATANYA (" + norawat + ")') from transfer_sebelum_tindakan where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel transfer_sesudah_tindakan KOSONG','tabel transfer_sesudah_tindakan ADA DATANYA (" + norawat + ")') from transfer_sesudah_tindakan where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel asesmen_pra_sedasi KOSONG','tabel asesmen_pra_sedasi ADA DATANYA (" + norawat + ")') from asesmen_pra_sedasi where no_rawat='" + norawat + "' and ruang_rawat not like '%igd%'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel ceklis_pra_operasi KOSONG','tabel ceklis_pra_operasi ADA DATANYA (" + norawat + ")') from ceklis_pra_operasi where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel ceklis_kesiapan_anestesi KOSONG','tabel ceklis_kesiapan_anestesi ADA DATANYA (" + norawat + ")') from ceklis_kesiapan_anestesi where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel asesmen_pre_induksi KOSONG','tabel asesmen_pre_induksi ADA DATANYA (" + norawat + ")') from asesmen_pre_induksi where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel asesmen_keperawatan_perioperatif KOSONG','tabel asesmen_keperawatan_perioperatif ADA DATANYA (" + norawat + ")') from asesmen_keperawatan_perioperatif where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel hitungan_asesmen_keperawatan_perioperatif KOSONG','tabel hitungan_asesmen_keperawatan_perioperatif ADA DATANYA (" + norawat + ")') from hitungan_asesmen_keperawatan_perioperatif where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel perencanaan_pulang_ranap KOSONG','tabel perencanaan_pulang_ranap ADA DATANYA (" + norawat + ")') from perencanaan_pulang_ranap where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel ceklis_keselamatan_operasi1 KOSONG','tabel ceklis_keselamatan_operasi1 ADA DATANYA (" + norawat + ")') from ceklis_keselamatan_operasi1 where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel ceklis_keselamatan_operasi2 KOSONG','tabel ceklis_keselamatan_operasi2 ADA DATANYA (" + norawat + ")') from ceklis_keselamatan_operasi2 where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel ceklis_keselamatan_operasi3 KOSONG','tabel ceklis_keselamatan_operasi3 ADA DATANYA (" + norawat + ")') from ceklis_keselamatan_operasi3 where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel catatan_material_operasi KOSONG','tabel catatan_material_operasi ADA DATANYA (" + norawat + ")') from catatan_material_operasi where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel asesmen_medik_bedah_ranap KOSONG','tabel asesmen_medik_bedah_ranap ADA DATANYA (" + norawat + ")') from asesmen_medik_bedah_ranap where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel asesmen_medik_perinatologi KOSONG','tabel asesmen_medik_perinatologi ADA DATANYA (" + norawat + ")') from asesmen_medik_perinatologi where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel asesmen_keperawatan_perinatologi KOSONG','tabel asesmen_keperawatan_perinatologi ADA DATANYA (" + norawat + ")') from asesmen_keperawatan_perinatologi where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel skor_apgar_downe_cap_jari_perinatologi KOSONG','tabel skor_apgar_downe_cap_jari_perinatologi ADA DATANYA (" + norawat + ")') from skor_apgar_downe_cap_jari_perinatologi where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel pengamatan_menyusui_perinatologi KOSONG','tabel pengamatan_menyusui_perinatologi ADA DATANYA (" + norawat + ")') from pengamatan_menyusui_perinatologi where no_rawat='" + norawat + "'") + "\n"                
                + cariIsi("select if(count(-1)=0,'tabel rekonsiliasi_obat KOSONG','tabel rekonsiliasi_obat ADA DATANYA (" + norawat + ")') from rekonsiliasi_obat where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel rekonsiliasi_obat_igd KOSONG','tabel rekonsiliasi_obat_igd ADA DATANYA (" + norawat + ")') from rekonsiliasi_obat_igd where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel serah_terima_bayi_pulang_perinatologi KOSONG','tabel serah_terima_bayi_pulang_perinatologi ADA DATANYA (" + norawat + ")') from serah_terima_bayi_pulang_perinatologi where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel pemberian_informasi_edukasi KOSONG','tabel pemberian_informasi_edukasi ADA DATANYA (" + norawat + ")') from pemberian_informasi_edukasi where no_rawat='" + norawat + "'") + "\n"
                + cariIsi("select if(count(-1)=0,'tabel penilaian_informasi_edukasi KOSONG','tabel penilaian_informasi_edukasi ADA DATANYA (" + norawat + ")') from penilaian_informasi_edukasi where no_rawat='" + norawat + "'") + "\n"
        );
        
        angka = rm1 + rm2 + rm3 + rm4 + rm5 + rm6 + rm7 + rm8 + rm9 + rm10 + rm11 + rm12 + rm13 + rm14 + rm15 + rm16 + rm17 + rm18 + rm19
                + rm20 + rm21 + rm22 + rm23 + rm24 + rm25 + rm26 + rm27 + rm28 + rm29 + rm30 + rm31 + rm32 + rm33 + rm34 + rm35 + rm36 + rm37
                + rm38 + rm39 + rm40 + rm41 + rm42 + rm43 + rm44 + rm45 + rm46 + rm47 + rm48 + rm49 + rm50 + rm51 + rm52 + rm53 + rm54 + rm55 + rm56
                + rm57 + rm58 + rm59 + rm60 + rm61 + rm62 + rm63 + rm64 + rm65;

        return angka;
    }
    
    public int cariRegistrasi(String norawat) {
        angka = 0;
        try {
            ps = connect.prepareStatement(
                    "select count(billing.no_rawat) from billing where billing.no_rawat=?");
            try {
                ps.setString(1, norawat);
                rs = ps.executeQuery();
                if (rs.next()) {
                    angka = rs.getInt(1);
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
            System.out.println(e);
        }
        return angka;
    }
    
    public void cariIsi(String sql, JTextField txt, String kunci) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                ps.setString(1, kunci);
                rs = ps.executeQuery();
                if (rs.next()) {
                    txt.setText(rs.getString(1));
                } else {
                    txt.setText("");
                }
            } catch (SQLException e) {
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
    }

    public void cariIsi(String sql, JTextArea txt, String kunci) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                ps.setString(1, kunci);
                rs = ps.executeQuery();
                if (rs.next()) {
                    txt.setText(rs.getString(1));
                } else {
                    txt.setText("");
                }
            } catch (SQLException e) {
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
    }

    public void cariIsi(String sql, JLabel txt) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    txt.setText(rs.getString(1));
                } else {
                    txt.setText("");
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
    }
    
    public String cekUmurPasien(String norm) {
        umurOK = "";
        umur = "0";
        sttsumur = "Th";
        try {
            ps = connect.prepareStatement("select TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) as tahun, "
                    + "(TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12)) as bulan, "
                    + "TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()) as hari "
                    + "FROM pasien WHERE no_rkm_medis ='" + norm + "'");
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
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
                umurOK = umur + " " + sttsumur;
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
        
        return umurOK;
    }
    
    public String eksekusiQuery(String sql) {
        dicari = "";
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {                    
                    System.out.println("Query Sql yang diterapkan sudah benar..!!");
                    dicari = "ok";
                } else {
                    System.out.println("Query Sql yang diterapkan masih salah, periksa lagi penulisanya..!!");
                    dicari = "gagal";
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Sql yang diterapkan masih salah, periksa lagi penulisanya berikut erornya : \n" + e);
                System.out.println("Error Query Sql Salah : " + e);
                dicari = "gagal";
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Query Sql yang diterapkan masih salah, periksa lagi penulisanya berikut erornya : \n" + e);
            System.out.println("Error Query Sql Salah : " + e);
            dicari = "gagal";
        }
        return dicari;
    }

    public String cariIsi(String sql) {
        dicari = "";
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    dicari = rs.getString(1);
                } else {
                    dicari = "";
                }
            } catch (Exception e) {
                dicari = "";
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

        return dicari;
    }

    public ByteArrayInputStream cariGambar(String sql) {
        ByteArrayInputStream inputStream = null;
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    inputStream = new ByteArrayInputStream(rs.getBytes(1));
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

        return inputStream;
    }

    public String cariIsi(String sql, String data) {
        dicari = "";
        try {
            ps = connect.prepareStatement(sql);
            try {
                ps.setString(1, data);
                rs = ps.executeQuery();
                if (rs.next()) {
                    dicari = rs.getString(1);
                } else {
                    dicari = "";
                }
            } catch (Exception e) {
                dicari = "";
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

        return dicari;
    }

    public Date cariIsi2(String sql) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    tanggal = rs.getDate(1);
                } else {
                    tanggal = new Date();
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
        return tanggal;
    }

    public Integer cariInteger(String sql) {
        angka = 0;
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    angka = rs.getInt(1);
                } else {
                    angka = 0;
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

        return angka;
    }

    public Integer cariIntegerCount(String sql) {
        angka = 0;
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    angka = angka + rs.getInt(1);
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

        return angka;
    }

    public Integer cariInteger(String sql, String data) {
        angka = 0;
        try {
            ps = connect.prepareStatement(sql);
            try {
                ps.setString(1, data);
                rs = ps.executeQuery();
                if (rs.next()) {
                    angka = rs.getInt(1);
                } else {
                    angka = 0;
                }
            } catch (Exception e) {
                angka = 0;
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

        return angka;
    }

    public Integer cariInteger2(String sql) {
        angka = 0;
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                rs.last();
                angka = rs.getRow();
                if (angka < 1) {
                    angka = 0;
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

        return angka;
    }

    public void cariIsiAngka(String sql, JTextField txt) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    txt.setText(df2.format(rs.getDouble(1)));
                } else {
                    txt.setText("0");
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
    }

    public void cariIsiAngka(String sql, JLabel txt) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    txt.setText(df2.format(rs.getDouble(1)));
                } else {
                    txt.setText("0");
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
    }

    public double cariIsiAngka(String sql) {
        angka2 = 0;
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    angka2 = rs.getDouble(1);
                } else {
                    angka2 = 0;
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

        return angka2;
    }

    public double cariIsiAngka(String sql, String data) {
        angka2 = 0;
        try {
            ps = connect.prepareStatement(sql);
            try {
                ps.setString(1, data);
                rs = ps.executeQuery();
                if (rs.next()) {
                    angka2 = rs.getDouble(1);
                } else {
                    angka2 = 0;
                }
                //rs.close();
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

        return angka2;
    }

    public void cariGambar(String sql, JLabel txt) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    icon = new javax.swing.ImageIcon(rs.getBlob(1).getBytes(1L, (int) rs.getBlob(1).length()));
                    createThumbnail();
                    txt.setIcon(icon);
                } else {
                    txt.setText(null);
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
    }

    public void cariGambar(String sql, java.awt.Canvas txt, String text) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                for (int I = 0; rs.next(); I++) {
                    ((Painter) txt).setImage(gambar(text));
                    Blob blob = rs.getBlob(5);
                    ((Painter) txt).setImageIcon(new javax.swing.ImageIcon(
                            blob.getBytes(1, (int) (blob.length()))));
                }
            } catch (Exception ex) {
                cetak(ex.toString());
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

    }

    public String cariString(String sql) {
        dicari = "";
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    dicari = rs.getString(1);
                } else {
                    dicari = "";
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

        return dicari;
    }

    private String gambar(String id) {
        return folder + File.separator + id.trim() + ".jpg";
    }

    public void Tabel(javax.swing.JTable tb, int lebar[]) {
        tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        angka = tb.getColumnCount();
        for (int i = 0; i < angka; i++) {
            javax.swing.table.TableColumn tbc = tb.getColumnModel().getColumn(i);
            tbc.setPreferredWidth(lebar[i]);
            //tb.setRowHeight(17);
        }
    }

    private void createThumbnail() {
        int maxDim = 150;
        try {
            Image inImage = icon.getImage();

            double scale = (double) maxDim / (double) inImage.getHeight(null);
            if (inImage.getWidth(null) > inImage.getHeight(null)) {
                scale = (double) maxDim / (double) inImage.getWidth(null);
            }

            int scaledW = (int) (scale * inImage.getWidth(null));
            int scaledH = (int) (scale * inImage.getHeight(null));

            BufferedImage outImage = new BufferedImage(scaledW, scaledH,
                    BufferedImage.TYPE_INT_RGB);

            AffineTransform tx = new AffineTransform();

            if (scale < 1.0d) {
                tx.scale(scale, scale);
            }

            Graphics2D g2d = outImage.createGraphics();
            g2d.drawImage(inImage, tx, null);
            g2d.dispose();

            iconThumbnail = new javax.swing.ImageIcon(outImage);
        } catch (Exception e) {
        }
    }

    private void cetak(String str) {
        System.out.println(str);
    }

    public void mengedit(String rujuk_masuk, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public class Painter extends Canvas {

        Image image;

        private void setImage(String file) {
            URL url = null;
            try {
                url = new File(file).toURI().toURL();
            } catch (MalformedURLException ex) {
                cetak(ex.toString());
            }
            image = getToolkit().getImage(url);
            repaint();
        }

        private void setImageIcon(ImageIcon file) {
            image = file.getImage();
            repaint();
        }

        @Override
        public void paint(Graphics g) {
            double d = image.getHeight(this) / this.getHeight();
            double w = image.getWidth(this) / d;
            double x = this.getWidth() / 2 - w / 2;
            g.drawImage(image, (int) x, 0, (int) (w), this.getHeight(), this);
        }

        private void cetak(String str) {
            System.out.println(str);
        }
    }

    public class NIOCopier {

        public NIOCopier(String asal, String tujuan) throws IOException {
            FileOutputStream outFile;
            try (FileInputStream inFile = new FileInputStream(asal)) {
                outFile = new FileOutputStream(tujuan);
                FileChannel outChannel;
                try (FileChannel inChannel = inFile.getChannel()) {
                    outChannel = outFile.getChannel();
                    for (ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
                            inChannel.read(buffer) != -1;
                            buffer.clear()) {
                        buffer.flip();
                        while (buffer.hasRemaining()) {
                            outChannel.write(buffer);
                        }
                    }
                }
                outChannel.close();
            }
            outFile.close();
        }
    }

    public void cariIsi(String sql, JTextField txt, String kunci, String kunci2) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                ps.setString(1, kunci);
                ps.setString(2, kunci2);
                rs = ps.executeQuery();
                if (rs.next()) {
                    txt.setText(rs.getString(1));
                } else {
                    txt.setText("");
                }
            } catch (SQLException e) {
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
    }

    public void cariIsi(String sql, String txt, String kunci) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                ps.setString(1, kunci);
                rs = ps.executeQuery();
                if (rs.next()) {
                    txt = rs.getString(1);
                } else {
                    txt = "";
                }
            } catch (SQLException e) {
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
    }

    public String cariApotek() {
        dicari = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            dicari = prop.getProperty("APOTEK").toString();

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return dicari;
    }

    public String decXML(String input, String k) {
        dicari = "";
        try {
            //prop.loadFromXML(new FileInputStream("setting/database.xml"));
            inputan = input;
            Panjang_Input = inputan.length();
            panjangKey = k.length();
            for (int i = 0; i < Panjang_Input; i++) {
//                    enkrip = inputan.substring(i, 1);
                angka = inputan.charAt(i);
                angka = (angka - panjangKey) + 10;
                enkrip = (char) angka;
                dicari = dicari + enkrip;
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        return dicari;
    }

    public static String decXML2(String input, String k) {
        dicari2 = "";
        try {
            //prop.loadFromXML(new FileInputStream("setting/database.xml"));
            inputan2 = input;
            Panjang_Input2 = inputan2.length();
            panjangKey2 = k.length();
            for (int i = 0; i < Panjang_Input2; i++) {
//                    enkrip = inputan.substring(i, 1);
                angka3 = inputan2.charAt(i);
                angka3 = (angka3 - panjangKey2) + 10;
                enkrip2 = (char) angka3;
                dicari2 = dicari2 + enkrip2;
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        return dicari2;
    }

    public String cariApotek2() {
        dicari = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            dicari = prop.getProperty("APOTEK_IGD").toString();

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return dicari;
    }

    public void cariIsiResep(String sql, JTextField txt, String kunci, String kunci2, String kunci3) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                ps.setString(1, kunci);
                ps.setString(2, kunci2);
                ps.setString(3, kunci3);
                ps.setString(4, kunci);
                ps.setString(5, kunci2);
                ps.setString(6, kunci3);
                rs = ps.executeQuery();
                if (rs.next()) {
                    txt.setText(rs.getString(1));
                } else {
                    txt.setText("");
                }
            } catch (SQLException e) {
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
    }

    public double cekIGD(String sql, String kunci, String kunci2, String kunci3, String kunci4, String kunci5) {
        angka2 = 0;
        try {
            ps = connect.prepareStatement(sql);
            try {
                ps.setString(1, kunci);
                ps.setString(2, kunci2);
                ps.setString(3, kunci3);
                ps.setString(4, kunci4);
                ps.setString(5, kunci5);
                rs = ps.executeQuery();
                if (rs.next()) {
                    angka2 = rs.getDouble(1);
                } else {
                    angka2 = 0;
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

        return angka2;
    }

    public int cariObat(String norawat) {
        angka = 0;
        try {
            ps = connect.prepareStatement(
                    "select count(detail_pemberian_obat.no_rawat) from detail_pemberian_obat where detail_pemberian_obat.no_rawat=?");
            try {
                ps.setString(1, norawat);
                rs = ps.executeQuery();
                if (rs.next()) {
                    angka = rs.getInt(1);
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
            System.out.println(e);
        }
        return angka;
    }

    public Integer cekForm(String s) {
        angka = 0;
        try {
            prop.loadFromXML(new FileInputStream("setting/noname.xml"));
            angka = Integer.parseInt(prop.getProperty(s));

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return angka;
    }

    public Integer cariInteger(String sql, String data, String data2) {
        angka = 0;
        try {
            ps = connect.prepareStatement(sql);
            try {
                ps.setString(1, data);
                ps.setString(2, data2);
                rs = ps.executeQuery();
                if (rs.next()) {
                    angka = rs.getInt(1);
                } else {
                    angka = 0;
                }
            } catch (Exception e) {
                angka = 0;
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

        return angka;
    }

    public void meghapus3(String table, String field, String nilai_field) {
        try {
            ps = connect.prepareStatement("delete from " + table + " where " + field + "=?");
            try {
                ps.setString(1, nilai_field);
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public String FolderQRresep() {
        dicari = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            dicari = prop.getProperty("FOLDERQRRESEP").toString();

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        return dicari;
    }
    
    public String FolderQRresepRanap() {
        dicari = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            dicari = prop.getProperty("FOLDERQRRESEPRANAP").toString();

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        return dicari;
    }

    public String cariFolder() {
        dicari = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            dicari = prop.getProperty("FOLDER").toString();

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return dicari;
    }

    public String cariFolderPrint() {
        dicari = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            dicari = prop.getProperty("FOLDERPRINT").toString();

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return dicari;
    }

    public String cariFolderPrintResep() {
        dicari = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            dicari = prop.getProperty("FOLDERPRINTQRRESEP").toString();

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return dicari;
    }
    
    public String cariFolderPrintResepRanap() {
        dicari = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            dicari = prop.getProperty("FOLDERPRINTQRRESEPRANAP").toString();

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return dicari;
    }

    public void menyimpanQr(String table, String value, String sama, String AlmGb) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ",?)");
            try {
                ps.setBinaryStream(1, new FileInputStream(AlmGb), new File(AlmGb).length());
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan lokasi folder utk. meletakkan " + sama + " belum ditentukan..!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void insertClosingStok() {
        cekData = cariInteger("select count(-1) from stok_bulanan where periode = DATE_FORMAT(now(),'%Y-%m')");
        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Closing Stok Bulanan Belum dilakukan, proses closing stok, klik OK");
            try {
                ps = connect.prepareStatement("select kode_brng,DATE_FORMAT(SUBDATE(now(), INTERVAL 1 MONTH),'%Y-%m') 'periode',kd_bangsal,stok as 'stok_awal',DATE_FORMAT(now(),'%Y-%m-%d') 'tgl_input',0 as 'stok_akhir','0000-00-00' as 'tgl_akhir' from gudangbarang");
                rs1 = ps.executeQuery();
                while (rs1.next()) {
                    menyimpan("stok_bulanan", "'" + rs1.getString("kode_brng") + "','" + rs1.getString("periode") + "','" + rs1.getString("kd_bangsal") + "',0,"
                            + "'" + rs1.getString("tgl_input") + "','" + rs1.getString("stok_awal") + "','" + rs1.getString("tgl_input") + "'", "stok_akhir = '" + rs1.getString("stok_awal") + "',tgl_input_akhir = DATE_FORMAT(now(),'%Y-%m-%d')", "kode_brng = '" + rs1.getString("kode_brng") + "' and kd_bangsal = '" + rs1.getString("kd_bangsal") + "' and periode = '" + rs1.getString("periode") + "'");
                }

                ps1 = connect.prepareStatement("insert into stok_bulanan (select kode_brng,DATE_FORMAT(now(),'%Y-%m'),kd_bangsal,stok,DATE_FORMAT(now(),'%Y-%m-%d'),0,'0000-00-00' from gudangbarang)");
                try {
                    ps1.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Berhasil menyimpan data closing bulanan");
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                    JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data closing bulanan. Hubungi ADMIN");
                } finally {
                    if (ps != null) {
                        ps.close();
                    }
                }

            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }

        }
        insertClosingStokHarian();
    }

    public void updateClosingStok() {
        try {
            ps = connect.prepareStatement("UPDATE stok_bulanan s,gudangbarang g SET s.stok_akhir = g.stok,tgl_input_akhir = DATE_FORMAT(now(),'%Y-%m-%d') WHERE g.kode_brng = s.kode_brng and g.kd_bangsal = s.kd_bangsal and s.periode = DATE_FORMAT(SUBDATE(now(), INTERVAL 1 MONTH),'%Y-%m')");
            try {
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Berhasil mengupdate data closing bulanan");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, gagal mengupdate data closing bulanan. Hubungi ADMIN");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void menyimpanInsertIgnore(String table, String value, String sama) {
        try {
            ps = connect.prepareStatement("insert ignore into " + table + " values(" + value + ")");
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void cariIsiComboDB(String sql, JComboBox cmb) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    String dicari = rs.getString(1);
                    cmb.addItem(dicari);
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
    }

    public String Terbilang(double angka) {
        if (angka < 12) {
            return nominal[(int) angka];
        }

        if (angka >= 12 && angka <= 19) {
            return nominal[(int) angka % 10] + " Belas ";
        }

        if (angka >= 20 && angka <= 99) {
            return nominal[(int) angka / 10] + " Puluh " + nominal[(int) angka % 10];
        }

        if (angka >= 100 && angka <= 199) {
            return "Seratus " + Terbilang(angka % 100);
        }

        if (angka >= 200 && angka <= 999) {
            return nominal[(int) angka / 100] + " Ratus " + Terbilang(angka % 100);
        }

        if (angka >= 1000 && angka <= 1999) {
            return "Seribu " + Terbilang(angka % 1000);
        }

        if (angka >= 2000 && angka <= 999999) {
            return Terbilang((int) angka / 1000) + " Ribu " + Terbilang(angka % 1000);
        }

        if (angka >= 1000000 && angka <= 999999999) {
            return Terbilang((int) angka / 1000000) + " Juta " + Terbilang(angka % 1000000);
        }

        if (angka > 999999999) {
            return Terbilang((Double) angka / 1000000000) + " Milyar " + Terbilang(angka % 1000000000);
        }
        return "";
    }

    public String bulanINDONESIA(String sql) {
        bulan = "";
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    bulan = rs.getString(1);
                    if (bulan.equals("January") || bulan.equals("1") || bulan.equals("01")) {
                        bulan = "Januari";
                    }

                    if (bulan.equals("February") || bulan.equals("2") || bulan.equals("02")) {
                        bulan = "Februari";
                    }

                    if (bulan.equals("March") || bulan.equals("3") || bulan.equals("03")) {
                        bulan = "Maret";
                    }

                    if (bulan.equals("April") || bulan.equals("4") || bulan.equals("04")) {
                        bulan = "April";
                    }

                    if (bulan.equals("May") || bulan.equals("5") || bulan.equals("05")) {
                        bulan = "Mei";
                    }

                    if (bulan.equals("June") || bulan.equals("6") || bulan.equals("06")) {
                        bulan = "Juni";
                    }

                    if (bulan.equals("July") || bulan.equals("7") || bulan.equals("07")) {
                        bulan = "Juli";
                    }

                    if (bulan.equals("August") || bulan.equals("8") || bulan.equals("08")) {
                        bulan = "Agustus";
                    }

                    if (bulan.equals("September") || bulan.equals("9") || bulan.equals("09")) {
                        bulan = "September";
                    }

                    if (bulan.equals("October") || bulan.equals("10")) {
                        bulan = "Oktober";
                    }

                    if (bulan.equals("November") || bulan.equals("11")) {
                        bulan = "Nopember";
                    }

                    if (bulan.equals("December") || bulan.equals("12")) {
                        bulan = "Desember";
                    }
                } else {
                    bulan = "";
                }
            } catch (Exception e) {
                bulan = "";
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
        return bulan;
    }

    public String bulanINDONESIAkata(String darikata) {
        bulan = darikata;
        if (bulan.equals("January") || bulan.equals("1")) {
            bulan = "Januari";
        }

        if (bulan.equals("February") || bulan.equals("2")) {
            bulan = "Februari";
        }

        if (bulan.equals("March") || bulan.equals("3")) {
            bulan = "Maret";
        }

        if (bulan.equals("April") || bulan.equals("4")) {
            bulan = "April";
        }

        if (bulan.equals("May") || bulan.equals("5")) {
            bulan = "Mei";
        }

        if (bulan.equals("June") || bulan.equals("6")) {
            bulan = "Juni";
        }

        if (bulan.equals("July") || bulan.equals("7")) {
            bulan = "Juli";
        }

        if (bulan.equals("August") || bulan.equals("8")) {
            bulan = "Agustus";
        }

        if (bulan.equals("September") || bulan.equals("9")) {
            bulan = "September";
        }

        if (bulan.equals("October") || bulan.equals("10")) {
            bulan = "Oktober";
        }

        if (bulan.equals("November") || bulan.equals("11")) {
            bulan = "Nopember";
        }

        if (bulan.equals("December") || bulan.equals("12")) {
            bulan = "Desember";
        }
        return bulan = "";
    }

    public String hariINDONESIA(String sql) {
        hari = "";
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    hari = rs.getString(1);
                    if (hari.equals("Monday")) {
                        hari = "Senin";
                    }

                    if (hari.equals("Tuesday")) {
                        hari = "Selasa";
                    }

                    if (hari.equals("Wednesday")) {
                        hari = "Rabu";
                    }

                    if (hari.equals("Thursday")) {
                        hari = "Kamis";
                    }

                    if (hari.equals("Friday")) {
                        hari = "Jum'at";
                    }

                    if (hari.equals("Saturday")) {
                        hari = "Sabtu";
                    }

                    if (hari.equals("Sunday")) {
                        hari = "Minggu";
                    }
                } else {
                    hari = "";
                }
            } catch (Exception e) {
                hari = "";
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
        return hari;
    }

    public String bulanRomawi(String sql) {
        romawi = "";
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    romawi = rs.getString(1);
                    if (romawi.equals("January") || romawi.equals("1") || romawi.equals("01")) {
                        romawi = "I";
                    }

                    if (romawi.equals("February") || romawi.equals("2") || romawi.equals("02")) {
                        romawi = "II";
                    }

                    if (romawi.equals("March") || romawi.equals("3") || romawi.equals("03")) {
                        romawi = "III";
                    }

                    if (romawi.equals("April") || romawi.equals("4") || romawi.equals("04")) {
                        romawi = "IV";
                    }

                    if (romawi.equals("May") || romawi.equals("5") || romawi.equals("05")) {
                        romawi = "V";
                    }

                    if (romawi.equals("June") || romawi.equals("6") || romawi.equals("06")) {
                        romawi = "VI";
                    }

                    if (romawi.equals("July") || romawi.equals("7") || romawi.equals("07")) {
                        romawi = "VII";
                    }

                    if (romawi.equals("August") || romawi.equals("8") || romawi.equals("08")) {
                        romawi = "VIII";
                    }

                    if (romawi.equals("September") || romawi.equals("9") || romawi.equals("09")) {
                        romawi = "IX";
                    }

                    if (romawi.equals("October") || romawi.equals("10")) {
                        romawi = "X";
                    }

                    if (romawi.equals("November") || romawi.equals("11")) {
                        romawi = "XI";
                    }

                    if (romawi.equals("December") || romawi.equals("12")) {
                        romawi = "XII";
                    }
                } else {
                    romawi = "";
                }
            } catch (Exception e) {
                romawi = "";
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
        return romawi;
    }

    public void menghapus(String table, String field, String field2, String nilai_field, String nilai_field2) {
        try {
            ps = connect.prepareStatement("delete from " + table + " where " + field + "=? and " + field2 + "=?");
            try {
                ps.setString(1, nilai_field);
                ps.setString(2, nilai_field2);
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, data gagal dihapus. Kemungkinan data tersebut masih dipakai di table lain...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
            SimpanTrack("delete from " + table + " where " + field + "='" + nilai_field + "' and " + field2 + "='" + nilai_field2 + "'");
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void meghapus(String table, String field, String field2, String nilai_field, String nilai_field2) {
        try {
            ps = connect.prepareStatement("delete from " + table + " where " + field + "=? and " + field2 + "=?");
            try {
                ps.setString(1, nilai_field);
                ps.setString(2, nilai_field2);
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, data gagal dihapus. Kemungkinan data tersebut masih dipakai di table lain...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
            SimpanTrack("delete from " + table + " where " + field + "='" + nilai_field + "' and " + field2 + "='" + nilai_field2 + "'");
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void insertClosingStokHarian() {
        cekData = cariInteger("select count(-1) from stok_harian where tanggal = DATE_FORMAT(now(),'%Y-%m-%d')");
        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Closing Stok Harian Belum dilakukan, proses closing stok, klik OK");
            try {
                ps = connect.prepareStatement("select kode_brng,DATE_FORMAT(SUBDATE(now(), INTERVAL 1 DAY),'%Y-%m-%d') 'tanggal',kd_bangsal,stok as 'stok_awal',DATE_FORMAT(now(),'%Y-%m-%d') 'tgl_input',0 as 'stok_akhir','0000-00-00' as 'tgl_akhir' from gudangbarang");
                rs1 = ps.executeQuery();
                while (rs1.next()) {
                    menyimpan("stok_harian", "'" + rs1.getString("kode_brng") + "','" + rs1.getString("tanggal") + "','" + rs1.getString("kd_bangsal") + "',0,"
                            + "'" + rs1.getString("tgl_input") + "','" + rs1.getString("stok_awal") + "','" + rs1.getString("tgl_input") + "'", "stok_akhir = '" + rs1.getString("stok_awal") + "',tgl_input_akhir = DATE_FORMAT(now(),'%Y-%m-%d')", "kode_brng = '" + rs1.getString("kode_brng") + "' and kd_bangsal = '" + rs1.getString("kd_bangsal") + "' and tanggal = '" + rs1.getString("tanggal") + "'");
                }

                ps1 = connect.prepareStatement("insert into stok_harian (select kode_brng,DATE_FORMAT(now(),'%Y-%m-%d'),kd_bangsal,stok,DATE_FORMAT(now(),'%Y-%m-%d'),0,'0000-00-00' from gudangbarang)");
                try {
                    ps1.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Berhasil menyimpan data closing harian");
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                    JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data closing harian. Hubungi ADMIN");
                } finally {
                    if (ps != null) {
                        ps.close();
                    }
                }
//                updateClosingStok();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }

    public void menyimpanIgnore(String table, String value, String sama) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                ps.executeUpdate();
            } catch (Exception e) {
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public boolean meghapustf(String table,String field,String nilai_field) {
        bool=true;
        try {
            ps=connect.prepareStatement("delete from "+table+" where "+field+"=?");
            try{       
                ps.setString(1,nilai_field);
                ps.executeUpdate();
                bool=true;
             }catch(Exception e){
                bool=false;
                System.out.println("Notifikasi : "+e);
                JOptionPane.showMessageDialog(null,"Maaf, data gagal dihapus. Kemungkinan data tersebut masih dipakai di table lain...!!!!");
             }finally{
                if(ps != null){
                    ps.close();
                }
            }
            SimpanTrack("delete from "+table+" where "+field+"='"+nilai_field+"'");
        } catch (Exception e) {
            bool=false;
            System.out.println("Notifikasi : "+e);
        }
        return bool;
    }
	
	public String cariFolderRad() {
        dicari = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            dicari = prop.getProperty("FOLDERQRRAD").toString();

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return dicari;
    }
    
    public String cariFolderPrintRad() {
        dicari = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            dicari = prop.getProperty("FOLDERPRINTQRRAD").toString();

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return dicari;
    }
}
