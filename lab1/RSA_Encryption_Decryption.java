import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class RSA_Encryption_Decryption {
	private  BigInteger n, e,d;
	 RSAPrivateKey privatekey;
	 RSAPublicKey publickey;
	/**
	 * 
	 * @throws NoSuchAlgorithmException takes care of algorithm 
	 * 
	 */
	public RSA_Encryption_Decryption() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		privatekey = (RSAPrivateKey) keyPair.getPrivate();
		publickey = (RSAPublicKey) keyPair.getPublic();
		n = privatekey.getModulus();
		d = privatekey.getPrivateExponent();
		e = publickey.getPublicExponent();
	}
	
	public BigInteger encrypt(BigInteger m) { return m.modPow(e, n); }
	
	/**
	 * 
	 * @param plaintext is the message that we want to encrypt by protection java API 
	 * @return A the cipher text as a big integer 
	 * @throws InvalidKeyException This is the exception for invalid Keys (invalid encoding, wrong length, uninitialized, etc). 
	 * @throws NoSuchPaddingException  takes care of long padding
	 * @throws NoSuchAlgorithmException  This exception is thrown when a particular cryptographic algorithm is requested but is not available in the 
	 * @throws BadPaddingException This is the exception for invalid Keys (invalid encoding, wrong length, uninitialized, etc).
	 * @throws IllegalBlockSizeException  
	 *
	 */
	public  BigInteger encryptAPI(BigInteger plaintext) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException  {//API
		Cipher cipher=Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE,publickey);
        byte[] ciphertext = cipher.doFinal(plaintext.toByteArray());
        return new BigInteger(ciphertext);
        
	}
	
	public BigInteger decrypt(BigInteger c) { return c.modPow(d, n); }
	
	/**
	 * 
	 * @param ciphertext is the cipher text that we want to decrypt by protection java API 
	 * @return A the plain text text as a big integer 
	 * @throws InvalidKeyException This is the exception for invalid Keys (invalid encoding, wrong length, uninitialized, etc). 
	 * @throws NoSuchPaddingException  takes care of long padding
	 * @throws NoSuchAlgorithmException  This exception is thrown when a particular cryptographic algorithm is requested but is not available in the 
	 * @throws BadPaddingException This is the exception for invalid Keys (invalid encoding, wrong length, uninitialized, etc).
	 * @throws IllegalBlockSizeException  
	 */
	public  BigInteger decryptAPI(BigInteger ciphertext) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException  {//API
		Cipher cipher=Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE,privatekey);
        byte[] plaintext = cipher.doFinal(ciphertext.toByteArray());
        return new BigInteger(plaintext);
	}

	public String toString() {
		return "PrivateKey=["+d+", "+n+"], PublicKey=["+e+", "+n+"]";
	}
	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException  {
		RSA_Encryption_Decryption rsa = new RSA_Encryption_Decryption();
		BigInteger ciphertext = rsa.encryptAPI(new BigInteger("65"));
		System.out.println("the Orignal: " + "65");
		System.out.println("encrypt " + ciphertext);
		BigInteger plaintext = rsa.decrypt(ciphertext);
		System.out.println("new orginal: " + plaintext);
		
		ciphertext = rsa.encrypt(new BigInteger("100342321421"));
		System.out.println("the Orignal: " + "100342321421");
		System.out.println("encrypt " + ciphertext);
		plaintext = rsa.decryptAPI(ciphertext);
		System.out.println("new orginal: " + plaintext);
		
		
	}
}
