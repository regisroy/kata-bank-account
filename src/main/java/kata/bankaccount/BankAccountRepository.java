package kata.bankaccount;

import java.util.HashMap;
import java.util.Map;

public class BankAccountRepository {

    private Map<String, BankAccount> bankAccountsByClientNo = new HashMap<>();

    public void addBankAccount(BankAccount bankAccount) {
        if (bankAccount == null)
            throw new BankAccountException("The bank account is null.");
        if (isNullOrWhiteSpace(bankAccount.getClientNo()))
            throw new BankAccountException("The client number is not defined.");
        if(bankAccountsByClientNo.get(bankAccount.getClientNo()) != null)
            throw new BankAccountAlreadyExistsException();
        bankAccountsByClientNo.putIfAbsent(bankAccount.getClientNo(), bankAccount);
    }

    public int count() {
        return bankAccountsByClientNo.size();
    }

    public BankAccount findByClientNo(String clientNo) {
        if (isNullOrWhiteSpace(clientNo))
            throw new BankAccountException("The client number must be different from null, empty or blank.");
        BankAccount bankAccount = bankAccountsByClientNo.get(clientNo);

        if (bankAccount == null)
            throw new BankAccountException("Client " + clientNo + " unknown!");
        return bankAccount;
    }

    private boolean isNullOrWhiteSpace(String stringToCheck) {
        return stringToCheck == null || stringToCheck.isEmpty() || stringToCheck.isBlank();
    }
}
