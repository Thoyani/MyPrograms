package banking;

import java.util.ArrayList;

/**
 * The BankAccount class represents a bank account and is meant mainly to be
 * sub-classed
 */
public abstract class BankAccount {
	private int accountNumber;
	private String accountHolder;
	protected double balance = 0;
	protected ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	protected int transactionCount = 0;

	public BankAccount(int accountNumber, String accountHolder, double openingBalance) {
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		this.balance = openingBalance;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public int getTransactionCount() {
		return transactionCount;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {
		this.balance += amount;
		this.transactionCount++;
	}

	public void transferTo(BankAccount b, double amount) {
		if (this.balance >= amount) {
			this.withdraw(amount);
			b.deposit(amount);
		} else {
			System.err.println("Unsufficiant funds!");
		}
	}

	public abstract void deductFees();

	public abstract boolean withdraw(double amount);

	public void processTransaction(Transaction t) {
		this.transactions.add(t);
		switch (t.transactionType) {
		case Transaction.WITHDRAW:
			if (!withdraw(t.amount)) {
				t.cancelled = true;
			}
			break;
		case Transaction.DEPOSIT:
			deposit(t.amount);
			break;
		case Transaction.TRANSFER:
			transferTo(t.toAccount, t.amount);

			t.toAccount.transactions.add(t);
			t.toAccount.transactionCount++;
			break;
		default:
			System.err.println("Wrong transaction type");
		}
	}

	public String toString() {
		return "\nAccount Holder : " + this.getAccountHolder() + "\nClosing Balance : R "
				+ String.format("%,.2f", this.balance) + "\n" + this.transactionCount + " transactions this month.";
	}

	public void printStatement() {

		for (Transaction t : this.transactions)
			System.out.println(t.toString());
	}
}
