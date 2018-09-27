import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Base64;

//uses MD5 with salt
class Hasher {
    static String hash(String plaintextPass, byte[] salt){
        String hashedPass = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(salt);
            byte[] bytes = md.digest(plaintextPass.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<bytes.length; i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashedPass = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPass;
    }


    static String genSalt() {
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstance("SHA1PRNG","SUN");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        byte[] salt = new byte[16];
        String strSalt = new String();
        sr.nextBytes(salt);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        strSalt = encoder.encodeToString(salt);
        return strSalt;
    }
}
