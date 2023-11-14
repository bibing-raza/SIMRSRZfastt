/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.koneksiDB;
import fungsi.sekuel;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author khanzasoft
 */
public class ApiLIS {
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps, ps2;
    private ResultSet rs, rs2;
    private String URL = "", requestJson = "", requestJson2 = "", stringbalik = "";
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private JsonNode root;
    private sekuel Sequel = new sekuel();
    private JsonNode response;
    private ObjectMapper mapper = new ObjectMapper();
    
    public ApiLIS(){
        super();
        try {
            URL = koneksiDB.HOSTWSADAM();
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }
    }
    
    public void kirim(String noRawatLIS, String Tglperiksa, String jamPeriksa, 
            String diagnosa, String ketKlinis, String kdDokter, String kdUnit, String kdPenjab,
            String nmDokter, String nmUnit, String nmPenjab, String noTelpDokter, String kdFasilitas, String nmFasilitas,
            String alamtFasilitas, String noTelpFasilitas) {
        try {
//            ps = koneksi.prepareStatement(
//                    "select rp.no_rkm_medis, p.nm_pasien, p.jk, CONCAT(rp.umurdaftar,' ',rp.sttsumur) umur, p.alamat, "
//                    + "if(p.no_tlp='','0',p.no_tlp) notel, p.tgl_lahir, pl.no_rawat, ifnull(p.no_ktp,'-') noKTP from pasien p "
//                    + "INNER JOIN reg_periksa rp on rp.no_rkm_medis=p.no_rkm_medis INNER JOIN periksa_lab pl on pl.no_rawat=rp.no_rawat "
//                    + "where pl.no_rawat='" + noRawatAdam + "' GROUP BY pl.no_rawat");
            
            ps = koneksi.prepareStatement(
                    "select rp.no_rkm_medis, p.nm_pasien, p.jk, CONCAT(rp.umurdaftar,' ',rp.sttsumur) umur, p.alamat, "
                    + "if(p.no_tlp='','0',p.no_tlp) notel, p.tgl_lahir, pl.no_rawat from pasien p "
                    + "INNER JOIN reg_periksa rp on rp.no_rkm_medis=p.no_rkm_medis INNER JOIN periksa_lab pl on pl.no_rawat=rp.no_rawat "
                    + "where pl.no_rawat='" + noRawatLIS + "' GROUP BY pl.no_rawat");
             try {
                rs=ps.executeQuery();
                while(rs.next()){
                    headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.add("Content-Type","application/json;charset=UTF-8");
                    ps2 = koneksi.prepareStatement(
                            "SELECT d.id_template, tl.Pemeriksaan, pl.no_rawat, d.tgl_periksa, d.jam FROM template_laboratorium tl "
                            + "INNER JOIN detail_periksa_lab d on d.id_template=tl.id_template INNER JOIN periksa_lab pl on pl.kd_jenis_prw=d.kd_jenis_prw "
                            + "and pl.tgl_periksa = d.tgl_periksa and d.jam = pl.jam WHERE pl.no_rawat='" + noRawatLIS + "' and "
                            + "d.tgl_periksa='" + Tglperiksa + "' and d.jam='" + jamPeriksa + "' and tl.Pemeriksaan not like '%sampling%'");
                    try {
                        rs2 = ps2.executeQuery();
                        requestJson2 = "";
                        while (rs2.next()) {
                            requestJson2 = 
                                    "{"
                                    + "\"kode_tindakan\": \"" + rs2.getString("id_template") + "\","
                                    + "\"nama_tindakan\": \"" + rs2.getString("Pemeriksaan") + "\""
                                    + "}," 
                                    + requestJson2;
                        }
                        if (requestJson2.endsWith(",")) {
                            requestJson2 = requestJson2.substring(0, requestJson2.length() - 1);
                        }
                    } catch (Exception e) {
                        System.out.println("Notif 3 : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (ps2 != null) {
                            ps2.close();
                        }
                    }

                    requestJson=
//                            "{"
//                                + "\"registrasi\": {"
//                                + "\"no_reg_rs\": \"" + noRawatAdam + "\","
//                                + "\"diagnosa_awal\": \"" + diagnosa + "\","
//                                + "\"keterangan_klinis\": \"" + ketKlinis + "\""
//                            + "},"
//                                    + "\"pasien\": {"
//                                        + "\"nama\": \"" + rs.getString("nm_pasien") + "\","
//                                        + "\"no_rm\": \"" + rs.getString("no_rkm_medis") + "\","
//                                        + "\"jenis_kelamin\": \"" + rs.getString("jk") + "\","
//                                        + "\"alamat\": \"" + rs.getString("alamat") + "\","
//                                        + "\"no_telphone\": \"" + rs.getString("notel") + "\","
//                                        + "\"tanggal_lahir\": \"" + rs.getString("tgl_lahir") + "\","
//                                        + "\"nik\": \"" + rs.getString("noKTP") + "\""
//                            + "},"
//                                + "\"kode_dokter_pengirim\": \"" + kdDokter + "\","
//                                + "\"kode_unit_asal\": \"" + kdUnit + "\","
//                                + "\"kode_penjamin\": \"" + kdPenjab + "\","
//                            + "\"tindakan\": ["
//                                + requestJson2
//                                    + "]"
//                            + "}";
                    
                            "{"
                                + "\"registrasi\": {"
                                + "\"no_reg_rs\": \"" + noRawatLIS + "\","
                                + "\"Tglperiksa\": \"" + Tglperiksa + "\","
                                + "\"jamPeriksa\": \"" + jamPeriksa + "\","
                                + "\"diagnosa_awal\": \"" + diagnosa + "\","
                                + "\"keterangan_klinis\": \"" + ketKlinis + "\""
                            + "},"
                                    + "\"pasien\": {"
                                        + "\"nama\": \"" + rs.getString("nm_pasien") + "\","
                                        + "\"no_rm\": \"" + rs.getString("no_rkm_medis") + "\","
                                        + "\"jenis_kelamin\": \"" + rs.getString("jk") + "\","
                                        + "\"alamat\": \"" + rs.getString("alamat") + "\","
                                        + "\"no_telphone\": \"" + rs.getString("notel") + "\","
                                        + "\"tanggal_lahir\": \"" + rs.getString("tgl_lahir") + "\""
                            + "},"
                                + "\"kode_dokter_pengirim\": \"" + kdDokter + "\","
                                + "\"nama_dokter_pengirim\": \"" + nmDokter + "\","
                                + "\"phone_dokter\": \"" + noTelpDokter + "\","
                                + "\"kode_unit_asal\": \"" + kdUnit + "\","
                                + "\"nama_unit_asal\": \"" + nmUnit + "\","
                                + "\"kode_penjamin\": \"" + kdPenjab + "\","
                                + "\"nama_penjamin\": \"" + nmPenjab + "\","
                                + "\"id_fasilitas\": \"" + kdFasilitas + "\","
                                + "\"fasilitas\": \"" + nmFasilitas + "\","
                                + "\"alamat\": \"" + alamtFasilitas + "\","
                                + "\"no_telp_perujuk\": \"" + noTelpFasilitas + "\","
                            + "\"tindakan\": ["
                                + requestJson2
                                    + "]"
                            + "}";
                    
                    System.out.println("JSON : " + requestJson);
                    requestEntity = new HttpEntity(requestJson, headers);
                    stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
//                    System.out.println("Output : " + stringbalik);

                    root = mapper.readTree(stringbalik);                    
                    if (root.path("success").asText().equals("false")) {
                        Sequel.menyimpan("lis_error_log", "'0','" + noRawatLIS + "','SIMRS ke LIS',"
                                + "'" + root.path("message").asText() + "','" + stringbalik + "',Now() ", "Error LIS");
                        JOptionPane.showMessageDialog(null, "Data gagal terkirim ke alat Laboratorium " + "(" + root.path("message").asText() + ")");
                    } else {
                        Sequel.menyimpanIgnore("lis_reg", "'" + root.path("payload").path("no_lab").asText() + "','" + noRawatLIS + "','" + Tglperiksa + "',"
                                + "'" + jamPeriksa + "','" + kdDokter + "','" + kdUnit + "','" + diagnosa + "','" + ketKlinis + "',Now(), '" + requestJson + "','1'", "Berhasil LIS");
                        JOptionPane.showMessageDialog(null,"Data berhasil terkirim ke alat Laboratorium...!!!");
                    }

                }
             } catch (Exception e) {
                 System.out.println("Notif : "+e);
                 if (e.toString().contains("UnknownHostException") || e.toString().contains("false")) {
                     JOptionPane.showMessageDialog(null, "Koneksi ke server ADAM LIS terputus...!");
                 }
             } finally{
                 if(rs!=null){
                     rs.close();
                 }
                 if(ps!=null){
                     ps.close();
                 }
             }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException") || ex.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, "Koneksi ke server ADAM LIS terputus...!");
            }
        }
    }
    
    public RestTemplate getRest() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("SSL");
        javax.net.ssl.TrustManager[] trustManagers= {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {return null;}
                public void checkServerTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
                public void checkClientTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
            }
        };
        sslContext.init(null,trustManagers , new SecureRandom());
        SSLSocketFactory sslFactory=new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme scheme=new Scheme("https",443,sslFactory);
        HttpComponentsClientHttpRequestFactory factory=new HttpComponentsClientHttpRequestFactory();
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        return new RestTemplate(factory);
    }
    
}