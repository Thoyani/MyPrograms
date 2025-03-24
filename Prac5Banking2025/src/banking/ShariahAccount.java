package banking;

public class ShariahAccount extends BankAccount {

	public ShariahAccount(int accountNumber, String accountHolder, double openingBalance) {
		super(accountNumber, accountHolder, openingBalance);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deductFees() {
		if (this.transactionCount > 5) {
			this.withdraw((transactionCount - 5) * 2.50);

		}
		this.transactionCount = 0;
	}

	@Override
	public boolean withdraw(double amount) {
		if (this.balance >= 0 && this.balance >= amount) {
			this.balance -= amount;
			this.transactionCount++;
			return true;
		}
		return false;
	}

}
