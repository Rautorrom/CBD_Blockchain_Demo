import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSA {
	
	private PrivateKey privateKey;
	private PublicKey publicKey;

	
	public RSA () {
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(2048);
			KeyPair pair = generator.generateKeyPair();
			privateKey = pair.getPrivate();
			publicKey = pair.getPublic();
		} catch (Exception e) {
			
		}
	}
	
	public String cifra(String message) throws Exception {
		byte[] messageToBytes = message.getBytes();
		Cipher publicCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		publicCipher.init(Cipher.ENCRYPT_MODE,publicKey);
		byte[] encryptedBytes = publicCipher.doFinal(messageToBytes);
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}

	public String descifra(String encryptedBytes) throws Exception {
		byte[] encryptedMessage = Base64.getDecoder().decode(encryptedBytes);
		Cipher privateCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		privateCipher.init(Cipher.DECRYPT_MODE,privateKey);
		byte[] decryptedMessage = privateCipher.doFinal(encryptedMessage);
		return new String(decryptedMessage, "UTF8");
	}
	
	public String firma(String message) throws Exception {
		byte[] messageToBytes = message.getBytes();
		Cipher publicCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		publicCipher.init(Cipher.ENCRYPT_MODE,privateKey);
		byte[] encryptedBytes = publicCipher.doFinal(messageToBytes);
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}
	
	public String resuelveFirma(String encryptedBytes, PublicKey clave) throws Exception {
		byte[] encryptedMessage = Base64.getDecoder().decode(encryptedBytes);
		Cipher privateCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		privateCipher.init(Cipher.DECRYPT_MODE,clave);
		byte[] decryptedMessage = privateCipher.doFinal(encryptedMessage);
		return new String(decryptedMessage, "UTF8");
	}
	
	public static void main(String[] args) {

		try {
			
			RSA rsa = new RSA();
			
			String s = "Alicia paga 10 BTC a Bob";
			String sCifrada = rsa.cifra(s);
			String sDescifrada = rsa.descifra(sCifrada);
			String sFirmada = rsa.firma(s);
			
			//La clave sería recibida por una conexión segura,
			//es la clave pública del firmante
			PublicKey clave = rsa.publicKey;
			String sFirmaResuelta = rsa.resuelveFirma(sFirmada, clave);
			
			System.out.println("La String s: "+s);
			System.out.println("La String sCifrada: "+sCifrada);
			System.out.println("La String sDescifrada: "+sDescifrada);
			System.out.println("La String sFirmada: "+sFirmada);
			System.out.println("La String sFirmaResuelta: "+sFirmaResuelta);
			Boolean b = (sDescifrada.equals(sFirmaResuelta));
			System.out.println("¿La firma coincide con el contenido? "+b);
			
		} catch (Exception e) {
			
		}

	}

}
