import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PINTest {

	public int initpin = 4321;
	public PIN testpin;
	public Field ff ,counter;
	
	@BeforeEach
	void SetUP() throws NoSuchFieldException, SecurityException {
		testpin = new PIN(initpin);
		ff = testpin.getClass().getDeclaredField("access");
		ff.setAccessible(true);
		counter = testpin.getClass().getDeclaredField("tryCounter");
		counter.setAccessible(true);
	}
	@Test
	void test() throws IllegalArgumentException, IllegalAccessException {
		assertEquals(false,ff.getBoolean(testpin));
		testpin.checkPin(2222);
		assertEquals(false,ff.getBoolean(testpin));
		testpin.checkPin(0000);
		assertEquals(false,ff.getBoolean(testpin));
		testpin.checkPin(1111);
		assertEquals(false, ff.getBoolean(testpin));
		assertEquals(0,counter.get(testpin));
		testpin.checkPin(4321);
		assertEquals(false, ff.getBoolean(testpin));
		assertEquals(-1,counter.get(testpin));
	}
	
	@Test
	void test1() throws IllegalArgumentException, IllegalAccessException{
		testpin.checkPin(0000);
		assertEquals(false,ff.getBoolean(testpin));
		testpin.checkPin(1111);
		assertEquals(false,ff.getBoolean(testpin));
		testpin.checkPin(4321);
		assertEquals(true,ff.getBoolean(testpin));
	}
	@Test
	void test2() throws IllegalArgumentException, IllegalAccessException{
		testpin.checkPin(0);
		assertEquals(false,ff.getBoolean(testpin));
		testpin.checkPin(11110);
		assertEquals(false,ff.getBoolean(testpin));
		
	}

}
