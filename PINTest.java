import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PINTest {




		public int initpin = 4321;
		public PIN testpin;
		public Field ff ;
		
		@BeforeEach 
		void SetUP() throws NoSuchFieldException, SecurityException {
			testpin = new PIN(initpin);
			ff = testpin.getClass().getDeclaredField("access");
			ff.setAccessible(true);
		}
	

	@Test
	void test1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		testpin.checkPin(4321);
		assertEquals(true,ff.getBoolean(testpin));
	}

}
