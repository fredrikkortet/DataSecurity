import java.util.ArrayList;
import java.util.Collection;
public class Main {

	public static void main(String[] args) {
		Secret s = new Secret();
		String first = "";
        String test = "0";
		String last = "000000000000000";
		Long time = Long.MIN_VALUE;
		int index = 0;	
		for(int j = 0;j<16;j++){
			for( int i = 0; i<10;i++){
				
				String pin = first+test+last;
				Long starttime = System.currentTimeMillis();
				s.verifyPassword(pin);
				Long endtime=System.currentTimeMillis()-starttime;
			if(endtime>time){	
				time = endtime;
				index = i;	
			}
			if(s.getSecret() != null){
				break;
			}		
				test=""+(i+1);	
			}
			time =Long.MIN_VALUE;

				first =first+ ""+index;
				test = "0";
				last = (""+last).replaceFirst("0", "");
		}
		System.out.println(s.getSecret());
	}

}
