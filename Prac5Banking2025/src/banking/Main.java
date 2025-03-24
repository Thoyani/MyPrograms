package banking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static ArrayList<BankAccount> getAccounts(ArrayList<BankAccount> accounts, Scanner fileScanner)
			throws IOException {
		fileScanner.hasNextLine();// removes the first line(The heading)
		while (fileScanner.hasNext()) {
			String[] lineAsArray = fileScanner.nextLine().split("\t");
			if (lineAsArray[1].equalsIgnoreCase("Traditional")) {
				accounts.add(new TraditionalAccount(Integer.parseInt(lineAsArray[0]), lineAsArray[2],
						Double.parseDouble(lineAsArray[3])));

			} else if (lineAsArray[1].equalsIgnoreCase("Shariah"))
				accounts.add(new ShariahAccount(Integer.parseInt(lineAsArray[0]), lineAsArray[2],
						Double.parseDouble(lineAsArray[3])));
		}
		return accounts;
	}

//	 for(BankAccount acount : accounts ) {
//	 if()
//}

	private static BankAccount getAccount(ArrayList<BankAccount> accounts, String AccNum, int n) {

		return !(accounts.get(n).getAccountNumber() + "").equals(AccNum) ? getAccount(accounts, AccNum, ++n)
				: accounts.get(n);
	}

	private static void proccessTransactions(ArrayList<BankAccount> accounts, Scanner fileScanner) {
		String[] transactionTypes = { "withdraw", "deposit", "transfer" };

		while (fileScanner.hasNext()) {
			String[] lineAsArray = fileScanner.nextLine().split("\t");

			switch (lineAsArray[4]) {

			case "transfer" -> {
				double amount = Double.parseDouble(lineAsArray[5]);
				BankAccount receiver, sender = getAccount(accounts, (lineAsArray[3]), 0);// Recursive method for finding
																							// the account
				// Starting at the beginning(Third Argument in method, value == 0) of the List.
				receiver = getAccount(accounts, (lineAsArray[6]), 0);

				sender.processTransaction(new Transaction(Integer.parseInt(lineAsArray[0]),
						Integer.parseInt(lineAsArray[1]), Integer.parseInt(lineAsArray[2]), 2, receiver, amount));

			}

			case "withdraw" -> {

				double amount = Double.parseDouble(lineAsArray[5]);
				BankAccount account = getAccount(accounts, (lineAsArray[3]), 0);// Recursive method for finding the
																				// account // the account
				// Starting at the beginning(Third Argument in method, value == 0) of the List.

				account.processTransaction(new Transaction(Integer.parseInt(lineAsArray[0]),
						Integer.parseInt(lineAsArray[1]), Integer.parseInt(lineAsArray[2]), 0, null, amount));

				break;
			}

			case "deposit" -> {

				double amount = Double.parseDouble(lineAsArray[5]);
				BankAccount account = getAccount(accounts, (lineAsArray[3]), 0);// Recursive method for finding the
																				// account // the account
				// Starting at the beginning(Third Argument in method, value == 0) of the List.

				account.processTransaction(new Transaction(Integer.parseInt(lineAsArray[0]),
						Integer.parseInt(lineAsArray[1]), Integer.parseInt(lineAsArray[2]), 1, null, amount));

				break;
			}

			}
		}

	}

	private static void feePayments(ArrayList<BankAccount> accounts) {
		for (BankAccount account : accounts) {
			// if(account instanceof TraditionalAccount)
			account.deductFees();
		}
	}

	private static void printAccountStatements(ArrayList<BankAccount> accounts) {
		for (BankAccount account : accounts) {
			System.out.println("Statement for account Number : " + account.getAccountNumber() + account.toString());

			account.printStatement();
			System.out.println("\n");
		}
	}

	public static void main(String[] args) {
		// write your code here

		try (BufferedReader TransactReader = new BufferedReader(new FileReader("transact.txt"));
				Scanner TransactScanner = new Scanner(TransactReader);
				BufferedReader AccountsReader = new BufferedReader(new FileReader("accounts.txt"));
				Scanner AccountsScanner = new Scanner(AccountsReader);) {

			ArrayList<BankAccount> Accounts = new ArrayList<>();

			Accounts = getAccounts(Accounts, AccountsScanner);

			proccessTransactions(Accounts, TransactScanner);

			printAccountStatements(Accounts);
			feePayments(Accounts);
			printAccountStatements(Accounts);

		} catch (IOException e) {
			e.getStackTrace();
		}
	}
}
