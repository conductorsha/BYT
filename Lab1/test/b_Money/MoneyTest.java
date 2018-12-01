package b_Money;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
        Assert.assertEquals((long)10000, (long)SEK100.getAmount());
        Assert.assertEquals((long) 20000, (long)SEK200.getAmount());
        Assert.assertEquals((long) 2000, (long)EUR20.getAmount());
        Assert.assertEquals((long) -10000, (long)SEKn100.getAmount());
	}

	@Test
	public void testGetCurrency() {
        Assert.assertEquals(SEK, SEK100.getCurrency());
        Assert.assertEquals(EUR, EUR20.getCurrency());
	}

	@Test
	public void testToString() {
        Assert.assertEquals("10000 SEK", SEK100.toString());
	}

	@Test
	public void testGlobalValue() {
        Assert.assertEquals((long)1500, (long)SEK100.universalValue());
	}

	@Test
	public void testEqualsMoney() {
        Assert.assertEquals(false, SEK100.equals(EUR20));
	}

	@Test
	public void testAdd() {
        Assert.assertEquals((long)(30000),(long) SEK100.add(EUR20).getAmount());
        Assert.assertEquals((long)(40000),(long) SEK200.add(EUR20).getAmount());
        Assert.assertEquals((long)(10000),(long) SEKn100.add(EUR20).getAmount());
	}

	@Test
	public void testSub() {
        Assert.assertEquals((long)(-10000),(long) SEK100.sub(EUR20).getAmount());
        Assert.assertEquals((long)(0),(long) SEK200.sub(EUR20).getAmount());
        Assert.assertEquals((long)(-30000),(long) SEKn100.sub(EUR20).getAmount());
	}

	@Test
	public void testIsZero() {
        Assert.assertEquals(true , SEK200.sub(EUR20).isZero());
        Assert.assertEquals(false, SEK100.isZero());

	}

	@Test
	public void testNegate() {

        Assert.assertEquals((long)-10000, (long) SEK100.negate().getAmount());
        Assert.assertEquals((long)-2000, (long)EUR20.negate().getAmount());
        Assert.assertEquals((long)10000, (long)SEKn100.negate().getAmount());
	}

	@Test
	public void testCompareTo() {
    Assert.assertEquals(-1, (long) SEK100.compareTo(EUR20));
        Assert.assertEquals(1, (long) SEK200.compareTo(SEK100));
	}
}
