import java.io.*;

public class  OtpInputStream extends java.io.InputStream{
	final static int first_letter = 65;  // 65  = A
	
	
	
	
	public static void main(String [] args) {
		char [] text = {'L','A','T','E','R'};
		char[] rand_key = random_char(text.length); // random character of the text input
		char [] key= {'T','R','T','S','H'};// key to encrypt and decrypt
		
			
			char [] cipher = Encrypt_char(text, rand_key);
			char [] plantext = Decrypt_char(cipher, rand_key);
			char [] xor_encryption = Encr_Decr_xor(text, key);
			char [] xor_decryption = Encr_Decr_xor(xor_encryption, key);
			String[] encrypt_to_binary = char_to_binary(xor_encryption);
			String[] decrypt_to_binary = char_to_binary(xor_decryption);
			String[] key_to_binary = char_to_binary(key);
			
			System.out.println("PlainText: ");
			for(int i = 0; i<text.length; i++) {
				System.out.print(text[i] + " ");
				
			}
			System.out.println("\nCipher text: ");
			for(int i = 0; i<text.length; i++) {
				System.out.print(cipher[i] + " ");
				
			}
			System.out.println("\nPlainText: ");
			for(int i = 0; i<text.length; i++) {
				System.out.print(plantext[i] + " ");
			}
			
	
			System.out.println("\nxor encryption:");
			for(int i = 0; i<xor_encryption.length; i++) {
				System.out.print(xor_encryption[i] + " ");
			}
			
			System.out.println("\nxor decryption: ");
			for(int i = 0; i<xor_decryption.length; i++) {
				System.out.print(xor_decryption[i] + " ");
			}
			
			
			System.out.println("\nbinary encryption: ");
			for(int i = 0; i<encrypt_to_binary.length; i++) {
				System.out.print(encrypt_to_binary[i] + " ");
			}
			System.out.println("\nbinary key: ");
			for(int i = 0; i<key_to_binary.length; i++) {
				System.out.print(key_to_binary[i] + " ");
			}
			System.out.println("\nbinary decryption: ");
			for(int i = 0; i<decrypt_to_binary.length; i++) {
				System.out.print(decrypt_to_binary[i] + " ");
			}
			
			
	}

	@Override
	public int read() throws IOException {
		
		return 0;
	}
	
	
	/**
	 * 
	 * @param n is a number of character that should be encrypted/decryption
	 * @return this program going to return random character which we use for encryption and decryption
	 */
	
	public static char[] random_char(int n) {
		char[] character = new char[n];
		for(int i = 0; i<n; i++) {
			character[i] = (char)((int)(Math.random()*25)+first_letter);// 65-90
			
			
		}
		return character;
	}
	
	
	/**
	 * 
	 * @param text it is plain text that we want to encrypt it
	 * @param key is the random character key value
	 * @return it is going to return a cipher text
	 */
	public static char[] Encrypt_char(char[] text, char[] key) {
		char[] cipher = new char[text.length];   // cipher text at the end
		 
		for(int  i = 0; i< cipher.length; i++) {
			cipher[i] = (char) (((text[i]+key[i])%26)+first_letter);
		}
		
		return cipher;
	}
	
	/**
	 * 
	 * @param cipher it is an encrypted value from encryption function
	 * @param key is the same key we used when we decrypted the plain text.
	 * @return value is going to the message or the plain text.
	 */
	
	public static char[] Decrypt_char(char[] cipher, char[] key) {
		
		char[] text = new char[cipher.length];   // cipher text at the end
		for(int  i = 0; i< cipher.length; i++) {
			int num1 = cipher[i];
		    int num2 = key[i];
		    num2=num1-num2;
		    if(num2 < 0) {
		    	num2=num2+26;
		    }
		    text[i]=(char)(((num2)%26)+first_letter);
		}
		
		return text;
	}
	
	/**
	 * This function change char to binary
	 * @param text is an array of chars that can be plain text or cipher text and even a key if user want to see the key
	 * @return a string which show 1 and 0
	 */
	public static String[] char_to_binary(char[] text) {
		String[] binary = new String[text.length];
		
		for(int i = 0; i<binary.length; i++) {
			binary[i] = String.format("%8s", Integer.toBinaryString(text[i])).replace(" ", "0");
		}
		return binary;
	}
	
	/**
	 * This function does xor operation by taking to char and does xor byte wise.
	 * @param value is the plain text or cipher text that we want to do the xor operation on them
	 * @param key is the key value
	 * @return an array of character which can be a cipher text or a plaint text. 
	 */
	public static char[] Encr_Decr_xor(char[] value, char[] key) {
		char[] result = new char[value.length];
		
		for(int i = 0; i<result.length; i++) {
			result[i] = (char) (value[i] ^ key[i]);
		}
		
		return result;
	}
	
	 
}
