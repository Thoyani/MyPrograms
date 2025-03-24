package banking;

public class TraditionalAccount extends BankAccount implements InterestBearingAccount {

	public TraditionalAccount(int accountNumber, String accountHolder, double openingBalance) {
		super(accountNumber, accountHolder, openingBalance);
	}

	@Override
	public void addInterest(double interestRate) {
		if (this.balance > 1000.0)
			this.deposit(balance * (0.0125));

	}

	@Override
	public void deductFees() {

		this.withdraw(100.0);
		this.transactionCount = 0;
	}

	@Override
	public boolean withdraw(double amount) {
		if (this.balance >= 250.0 && this.balance >= amount) {
			this.balance -= amount;
			this.transactionCount++;

			return true;
		}
		return false;
	}

}
