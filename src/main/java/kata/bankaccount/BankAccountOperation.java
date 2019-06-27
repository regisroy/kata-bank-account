package kata.bankaccount;

import java.math.BigDecimal;
import java.util.Date;

public class BankAccountOperation {

    private enum Type {DEPOSIT, WITHDRAWAL}

    private final Type type;
    private final BigDecimal amount;
    private final Date operationDate;
    private final BigDecimal balance;

    public static BankAccountOperation createWithdrawalOperation(BigDecimal amount, Date operationDate, BigDecimal balance) {
        return new BankAccountOperation(Type.WITHDRAWAL, amount, operationDate, balance);
    }

    public static BankAccountOperation createDepositOperation(BigDecimal amount, Date operationDate, BigDecimal balance) {
        return new BankAccountOperation(Type.DEPOSIT, amount, operationDate, balance);
    }

    private BankAccountOperation(Type type, BigDecimal amount, Date operationDate, BigDecimal balance) {
        this.type = type;
        this.amount = amount;
        this.operationDate = operationDate;
        this.balance = balance;
    }

    @Override
    public String toString() {
        //ex. withdrawal    100.00 € the 2019-05-22 at 12h54 (new balance: 2460.00 €)
        return String.format("%-11s %10.2f € the %tF at %tHh%tM (new balance: %10.2f €)\n",
                type, amount.doubleValue(), operationDate, operationDate, operationDate, balance.doubleValue());
    }
}
