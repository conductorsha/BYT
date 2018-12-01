package b_Money;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		SweBank.addTimedPayment("Alice", "kasha", 2, 3, new Money(200, SweBank.getCurrency()), SweBank, "Hans");
		SweBank.getAccount("Alice").getTimedpayments().size();
		SweBank.removeTimedPayment("Alice", "kasha");
		Assert.assertEquals(0, SweBank.getAccount("Alice").getTimedpayments().size());
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		fail("Write test case here");
	}

	@Test
	public void testAddWithdraw() {
		SweBank.getAccount("Alice").deposit(new Money(500, SweBank.getCurrency()));
		SweBank.getAccount("Alice").withdraw(new Money(200, SweBank.getCurrency()));
		Assert.assertEquals(1000300, (long) SweBank.getAccount("Alice").getBalance().getAmount());
	}
	
	@Test
	public void testGetBalance() {
		SweBank.getAccount("Alice").deposit(new Money(500, SweBank.getCurrency()));
		Assert.assertEquals(1000500, (long)SweBank.getAccount("Alice").getBalance().getAmount());
	}
}
