package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.koneksiDB;
import fungsi.sekuel;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class ApiKemenkesKANKER {        
    private String url = "", koders = "", pass = "", hasilToken = "", requestJson = "", status = "", pesan = "";
    private HttpHeaders headers ;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode response;
    private boolean x;
    private sekuel Sequel = new sekuel();
    
    public ApiKemenkesKANKER(){
        try {
            koders = koneksiDB.KODERSKANKER();
            pass = koneksiDB.PASSKANKER();
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }
    }
    
    public long GetUTCdatetimeAsString(){    
        long millis = System.currentTimeMillis();   
        return millis/1000;
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
    
    public String MintaToken() {
        hasilToken = "";
        try {
            url = koneksiDB.URLAPIKANKER() + "/user/request_token";
            headers = new HttpHeaders();
            headers.add("X-Api-Key", "sirskemkes");
            requestEntity = new HttpEntity(headers);

            requestJson = "{"
                    + "\"username\":\"" + koders + "\","
                    + "\"password\":\"" + pass + "\""
                    + "}";

//            System.out.println("Json yang dikirim : " + requestJson);
            requestEntity = new HttpEntity(requestJson, headers);
            requestJson = getRest().exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
            root = mapper.readTree(requestJson);
            status = root.path("status").asText();
            pesan = root.path("message").asText();
            System.out.println("Status : " + status + ", Pesan : " + pesan);

            if (status.equals("true")) {
                hasilToken = root.path("data").path("token").asText();
//                System.out.println("Ini Tokennya : " + hasilToken);                
            } else {
                System.out.println("Pesan : " + pesan);
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(null, "Koneksi ke server Kemenkes terputus....!");
            }
        }
        return hasilToken;
    }
}
