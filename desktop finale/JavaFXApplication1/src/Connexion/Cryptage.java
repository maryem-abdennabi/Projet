/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Vector;
 
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

 
/**
 * @author xavier
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Cryptage 
{
 public SecretKey mykey() throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException
 {
    DESKeySpec keySpec = new DESKeySpec("YourSecr".getBytes("UTF8")); 
SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
SecretKey key = keyFactory.generateSecret(keySpec); 
 return key;
 }
 
public String encrypt(String password,String key){
try
{
Key clef = new SecretKeySpec(key.getBytes(),"DES");

    Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
// Initialize the cipher for encryption
desCipher.init(Cipher.ENCRYPT_MODE,clef);
byte[] textEncrypted = desCipher.doFinal(password.getBytes());
return new sun.misc.BASE64Encoder().encode(textEncrypted);
}
catch (Exception e)
{
return null;
}
}
 public String decrypt(String password,String key){
try
{
byte[] textencrypted = new sun.misc.BASE64Decoder().decodeBuffer(password);
Key clef = new SecretKeySpec(key.getBytes(),"DES");
    Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
   desCipher.init(Cipher.DECRYPT_MODE, clef);
byte[] textDecrypted = desCipher.doFinal(textencrypted);

return new String(textDecrypted,"UTF-8");
}
catch (Exception e)
{
System.out.println(e);
return null;
}
 
}
}