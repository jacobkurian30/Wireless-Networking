//Jacob kurian
//09/12/2017
//Wireless Networking Lab 1

package lab1_jacob_kurian;

import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author tue54888
 */
public class EncrytionDes {

    public static Scanner input;

    private SecretKey keyGeneration(String mySecretKey) throws Exception {
        DESKeySpec deskKeySpec = new DESKeySpec(mySecretKey.getBytes());
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = secretKeyFactory.generateSecret(deskKeySpec);
        //  System.out.println("The key " + key.getAlgorithm());
        return key;
    }

    private String encrption(String message, SecretKey key) throws Exception {
        // make the cipher object
        Cipher c = Cipher.getInstance("DES");
        c.init(Cipher.ENCRYPT_MODE, key);

        /*Getting the bytes of the message*/
        byte[] stringBytes = message.getBytes("UTF8");

        /*Encrypt the message*/
        byte[] encryptMsg = c.doFinal(stringBytes);

        System.out.println("Encrypted  Cipher Text: " + encryptMsg);

        /*Encoding the message to base 64*/
        BASE64Encoder encoder = new BASE64Encoder();
        String encordedEncrptMsg = encoder.encode(encryptMsg);
        System.out.println("\n\n#######################################################################");
        System.out.println("*************************Encoded Cipher Text*******************************");
        System.out.println("#######################################################################");
        System.out.println(encordedEncrptMsg);
        System.out.println("***************************************************************************");

        return encordedEncrptMsg;
    }

    private void decryptMsg() {
        boolean success = true;
        do {
            try {
                Cipher c = Cipher.getInstance("DES");

                System.out.println("Enter Encoded  Cipher Text: ");
                String encryptMsg = input.next();

                System.out.println("Enter your secret passcode to decode: ");
                String decryptKey = input.next();

                SecretKey key = keyGeneration(decryptKey);

                c.init(Cipher.DECRYPT_MODE, key);

                BASE64Decoder decoder = new BASE64Decoder();

                byte[] decodeMsg = decoder.decodeBuffer(encryptMsg);

                byte[] msgByte = c.doFinal(decodeMsg);

                String finalMsg = new String(msgByte, "UTF8");

                System.out.println("Decry Msg: " + finalMsg);
                success = true;
                

            } catch (Exception e) {
                System.out.println("Wrong Key !!" + e);
                success = false;
                System.out.println("Staus " + success);
            }
        } while (!success);
    }

    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        input = new Scanner(System.in);
        String myMessage = "This is my secret";

        EncrytionDes des = new EncrytionDes();
        String mySecretKey;
        do {
            System.out.print("Create your secret passcode : ");
            mySecretKey = input.next();

            if (mySecretKey.length() < 8) {
                System.out.println("Length of the key should be => 8");
                System.out.println("Please Re-enter the passcode");
            } else {
                System.out.println("Passcode Successfully created!!!");
            }
        } while (mySecretKey.length() < 8);
        SecretKey key = des.keyGeneration(mySecretKey);        
        des.encrption(myMessage, key);
        
        
        System.out.println("\n\n#################################################################");
        System.out.println("***************************Decrytion*****************************");
        System.out.println("#################################################################");
        des.decryptMsg();
    }

}
