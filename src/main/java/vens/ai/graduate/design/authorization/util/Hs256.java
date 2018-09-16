package vens.ai.graduate.design.authorization.util;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
/**
 * @author vens
 * @date 2018-05-09 21:59
 **/
public class Hs256 {
        private static String secret = "weiwei";
        public static String returnSign(String message) {
            String hash = "";
            try {
                Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
                SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(),"HmacSHA256");
                sha256_HMAC.init(secret_key);
                hash = Base64Helper.encode(sha256_HMAC.doFinal(message.getBytes()));
            } catch (Exception e) {
                System.out.println("Error");
            }
            return hash;
        }

}
