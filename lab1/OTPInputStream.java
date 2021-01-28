import java.io.*;
import java.util.Scanner;

public class OTPInputStream extends java.io.InputStream {
	final int first_letter = 65; // 65 = A
	char[] text;
	char[] newtext;
	int method = 1;
	char[] key;// key to encrypt and decrypt
	int pos = 0;
	
	/**
	 * This method will encryption/decryption 
	 * your messages in OTP or XOR encryption/decryption
	 * 
	 * @param text send in a text to decrypt or encrypt
	 * @param key send in the key to decrypt or encrypt
	 * @param method select the encryption/decryption method
	 */
	public OTPInputStream (char[] text, char[] key, int method) {
		this.text=text;
		this.key=key;
		this.method=method;
		transform(this.method);
		
	}
	
	@Override
	/**
	 * Reads byte of data from this Input stream
	 * @return the next byte of data, or -1 if end of the line.
	 */
	public int read() throws IOException {
		if(pos<newtext.length) {
			return newtext[pos++];
		}else {
			return -1;
		}		
	}
	
	public char[] getText() {
		return text;
	}

	public void setText(char[] text) {
		this.text = text;
	}

	public char[] getNewtext() {
		return newtext;
	}

	public void setNewtext(char[] newtext) {
		this.newtext = newtext;
	}

	public char[] getKey() {
		return key;
	}

	public void setKey(char[] key) {
		this.key = key;
	}
	public void reset() {
		pos=0;
	}
	/**
	 * This method take the text to choose to encryption/decryption
	 *  with XOR or OTP
	 * @param method select the method to encryption/decryption
	 */
	public void transform(int method) {
		switch (method) {
		case 1:
			newtext = Encrypt_char(text, key);
			break;
		case 2:
			newtext = Decrypt_char(text, key);
			break;
		default:
			newtext = Encr_Decr_xor(text, key);
			break;
		}
	}
	/**
	 * 
	 * @param n is a number of character that should 
	 * be encrypted/decryption
	 * @return this program going to return random 
	 * character which we use for encryption and decryption
	 */

	public char[] random_char(int n) {
		char[] character = new char[n];
		for (int i = 0; i < n; i++) {
			character[i] = (char) ((int) (Math.random() * 25) + first_letter);// 65-90

		}
		return character;
	}

	/**
	 * 
	 * @param text it is plain text that we want to encrypt it
	 * @param key  is the random character key value
	 * @return it is going to return a cipher text
	 */
	public char[] Encrypt_char(char[] text, char[] key) {
		char[] cipher = new char[text.length]; // cipher text at the end

		for (int i = 0; i < cipher.length; i++) {
			cipher[i] = (char) (((text[i] + key[i]) % 26) + first_letter);
		}

		return cipher;
	}

	/**
	 * 
	 * @param cipher it is an encrypted value from encryption function
	 * @param key    is the same key we used when we decrypted the plain text.
	 * @return value is going to the message or the plain text.
	 */

	public char[] Decrypt_char(char[] cipher, char[] key) {

		char[] text = new char[cipher.length]; // cipher text at the end
		for (int i = 0; i < cipher.length; i++) {
			int num1 = cipher[i];
			int num2 = key[i];
			num2 = num1 - num2;
			if (num2 < 0) {
				num2 = num2 + 26;
			}
			text[i] = (char) (((num2) % 26) + first_letter);
		}

		return text;
	}

	/**
	 * This function change char to binary
	 * 
	 * @param text is an array of chars that can be plain text or cipher text and
	 *             even a key if user want to see the key
	 * @return a string which show 1 and 0
	 */
	public String[] char_to_binary(char[] text) {
		String[] binary = new String[text.length];

		for (int i = 0; i < binary.length; i++) {
			binary[i] = String.format("%8s", Integer.toBinaryString(text[i])).replace(" ", "0");
		}
		return binary;
	}

	/**
	 * This function does xor operation by taking to char and does xor byte wise.
	 * 
	 * @param value is the plain text or cipher text that we want to do the xor
	 *              operation on them
	 * @param key   is the key value
	 * @return an array of character which can be a cipher text or a plaint text.
	 */
	public char[] Encr_Decr_xor(char[] value, char[] key) {
		char[] result = new char[value.length];

		for (int i = 0; i < result.length; i++) {
			result[i] = (char) (value[i] ^ key[i]);
		}

		return result;
	}

}
