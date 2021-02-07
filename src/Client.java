import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.management.openmbean.CompositeDataInvocationHandler;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Client {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, ClassNotFoundException {
        Socket client = new Socket("localhost",6666);

        DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
        String encodedKey = dataInputStream.readUTF();
        System.out.println("Received Key = " + encodedKey);
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);

        DataInputStream dataInputStream1= new DataInputStream(client.getInputStream());
        String message = dataInputStream1.readUTF();

        ObjectInputStream objectinputStream = new ObjectInputStream(client.getInputStream());
        byte[] descode = (byte[]) objectinputStream.readObject();





        Cipher ciper = Cipher.getInstance("DES/ECB/PKCS5Padding");
        SecretKey Mykey = new SecretKeySpec(decodedKey,0,decodedKey.length,"DES/ECB/PKCS5Padding");
        ciper.init(Mykey);









    }
}
