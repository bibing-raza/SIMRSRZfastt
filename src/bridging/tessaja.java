/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridging;

import static bridging.tessaja2.api;

/**
 *
 * @author khanzasoft
 */
public class tessaja {
    public static BPJSApi api = new BPJSApi();
    public static void tessaja() {
        String utc = "";
        utc = String.valueOf(api.GetUTCdatetimeAsString());
        System.out.println("X-Timestamp:" + utc);
        System.out.println("X-Signature:" + api.getHmac(utc));
    }

    public static void main(String[] args) {
        tessaja();
    }    
}
