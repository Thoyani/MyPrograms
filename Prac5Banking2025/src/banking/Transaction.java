package banking;

public class Transaction {
	public final static int WITHDRAW = 0;
	public final static int DEPOSIT = 1;
	public final static int TRANSFER = 2;

	// Date of Transaction
	int day;
	int month;
	int year;

	double amount;
	int transactionType;
	BankAccount toAccount;
	boolean cancelled = false;

	public Transaction(int day, int month, int year, int transactionType, BankAccount toAccount, double amount) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.transactionType = transactionType;
		this.toAccount = toAccount;
		this.amount = amount;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public String toString() {
		String transactionString = "";
		transactionString += day + " / " + month + " / " + year + " : ";
		switch (transactionType) {
		case WITHDRAW:
			transactionString += "withdrawal of : R" + amount + (isCancelled() ? "[CANCELLED]" : "");
			break;
		case DEPOSIT:
			transactionString += "deposit of : R " + amount;
			break;
		case TRANSFER:
			transactionString += "transfer to : " + "Account No.: " + toAccount.getAccountNumber() + " amount of  : R "
					+ amount;
			break;
		default:
			transactionString += "R " + amount;
		}
		return transactionString;
	}

}
