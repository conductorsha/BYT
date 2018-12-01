package b_Money;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		Assert.assertEquals("SEK", SEK.getName());
	}
	
	@Test
	public void testGetRate() {

		Assert.assertEquals((long) 0.20,Math.round(DKK.getRate()));
	}
	
	@Test
	public void testSetRate() {
		SEK.setRate(0.3);
		Assert.assertEquals((long) 0.3, Math.round(SEK.getRate()));
	}
	
	@Test
	public void testGlobalValue() {
		Money SEK1 = new Money(200, SEK);
		Assert.assertEquals(30,(long) SEK1.universalValue());
	}
	
	@Test
	public void testValueInThisCurrency() {
		Money EUR20 = new Money(20, EUR);
		Assert.assertEquals(200, (long)SEK.valueInThisCurrency(EUR20.getAmount(), EUR20.getCurrency() ));

	}

}
