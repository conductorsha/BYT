package b_Money;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		Assert.assertEquals("Nordea", Nordea.getName());
	}

	@Test
	public void testGetCurrency() {
		Assert.assertEquals("SEK", Nordea.getCurrency().getName());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
	Assert.assertEquals("Ulrika", SweBank.getAccount("Ulrika").getAccountName());
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		Nordea.deposit("Bob", new Money(500, Nordea.getCurrency()));
		Assert.assertEquals(500, (long)Nordea.getAccount("Bob").getBalance().getAmount());
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		Nordea.deposit("Bob", new Money(500, Nordea.getCurrency()));
		Nordea.withdraw("Bob",new Money(500, Nordea.getCurrency()));
		Assert.assertEquals(0, (long)Nordea.getAccount("Bob").getBalance().getAmount());
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		Assert.assertEquals(0,(long)Nordea.getAccount("Bob").getBalance().getAmount());
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		//transfers between the same account
		SweBank.deposit("Bob", new Money(500, Nordea.getCurrency()));
		SweBank.transfer("Bob", "Ulrika", new Money(500, Nordea.getCurrency()));
		Assert.assertEquals(500, (long)SweBank.getAccount("Ulrika").getBalance().getAmount());

		//transfers between different banks
		Nordea.deposit("Bob", new Money(500, Nordea.getCurrency()));
		Nordea.transfer("Bob", SweBank, "Ulrika", Nordea.getAccount("Bob").getBalance());
		Assert.assertEquals(0,(long)Nordea.getAccount("Bob").getBalance().getAmount());
		Assert.assertEquals(1000, (long) SweBank.getAccount("Ulrika").getBalance().getAmount());
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		Nordea.getAccount("Bob").deposit(new Money(500, Nordea.getCurrency()));
		Nordea.getAccount("Bob").addTimedPayment("One", 2, 3 , new Money(200, Nordea.getCurrency()), SweBank, "Ulrika");
		for(int i =0; i< 5; i++){
			Nordea.tick();
		}
		System.out.println(SweBank.getAccount("Ulrika").getBalance().getAmount());
		Assert.assertEquals(200, (long)SweBank.getAccount("Ulrika").getBalance().getAmount());
		Assert.assertEquals(300, (long)Nordea.getAccount("Bob").getBalance().getAmount());
	}
}
