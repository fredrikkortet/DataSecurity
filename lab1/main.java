import java.io.IOException;
import java.io.InputStream;
public class main {

public static void main(String[] arg) throws IOException {
	char[] text = {'L','A','T','E','R'};
	char[] key = {'T','R','T','S','H'};
	int temp;
	 OtpInputStream input = new OtpInputStream(text,key,3);
	 do {
		 temp = input.read();
		 if(temp==-1) {}else {
		 System.out.print((char)temp);

		 }
	 }while(temp != -1 );
	 input.reset();
	 text=input.getNewtext();
	 input.setText(text);
	 input.transform(4);
	 System.out.println("decrypt");
	 do {
		 temp = input.read();
		 if(temp==-1) {}else {
		 System.out.print((char)temp);

		 }
	 }while(temp != -1 );
}
}
