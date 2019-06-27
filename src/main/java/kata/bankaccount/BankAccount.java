package kata.bankaccount;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class BankAccount {
    private String clientNo;
    private BigDecimal balance = BigDecimal.ZERO;
    private List<BankAccountOperation> bankAccountOperations = new LinkedList<>();

    public BankAccount(String clientNo) {
        this.clientNo = clientNo;
    }

    public void makeDeposit(BigDecimal deposit, Date operationDate) {
        if (deposit.signum() == -1)
            throw new IllegalArgumentException("Deposit can't be negative");
        balance = balance.add(deposit);
        BankAccountOperation operation = BankAccountOperation.createDepositOperation(deposit, operationDate, balance);
        bankAccountOperations.add(operation);
    }

    public void makeDeposit(Double deposit, Date operationDate) {
        makeDeposit(BigDecimal.valueOf(deposit), operationDate);
    }

    public void makeDeposit(Double deposit) {
        makeDeposit(BigDecimal.valueOf(deposit), new Date());
    }

    public void makeWithdrawal(BigDecimal withdrawal, Date operationDate) {
        if (withdrawal.signum() == -1)
            throw new IllegalArgumentException("Withdrawal can't be negative");
        balance = balance.subtract(withdrawal);
        BankAccountOperation operation = BankAccountOperation.createWithdrawalOperation(withdrawal, operationDate, balance);
        bankAccountOperations.add(operation);
    }

    public void makeWithdrawal(Double withdrawal) {
        makeWithdrawal(BigDecimal.valueOf(withdrawal), new Date());
    }

    public void makeWithdrawal(Double withdrawal, Date operationDate) {
        makeWithdrawal(BigDecimal.valueOf(withdrawal), operationDate);
    }

    public Double getBalance() {
        return balance.doubleValue();
    }

    public void setBalance(Double balance) {
        this.balance = BigDecimal.valueOf(balance);
    }

    public String getClientNo() {
        return clientNo;
    }

    public String consultOperations() {
        StringBuilder consultation = new StringBuilder();
        for (BankAccountOperation bankAccountOperation : bankAccountOperations) {
            consultation.append(bankAccountOperation.toString());
        }
        return consultation.toString();
    }
}
