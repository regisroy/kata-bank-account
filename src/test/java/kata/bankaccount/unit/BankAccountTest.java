package kata.bankaccount.unit;

import kata.bankaccount.BankAccount;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class BankAccountTest {

    @Test
    public void makeDeposit_0() {
        double deposit = 0.0;
        double expected = 0.0;

        BankAccount bankAccount = new BankAccount("001");
        bankAccount.makeDeposit(deposit);
        assertThat(bankAccount.getBalance()).isEqualTo(expected);

        BankAccount bankAccountBig = new BankAccount("001");
        bankAccountBig.makeDeposit(BigDecimal.valueOf(deposit), new Date());
        assertThat(bankAccountBig.getBalance()).isEqualTo(expected);
    }

    @Test
    public void makeDeposit_positive() {
        double deposit = 1236.2365;
        double expected = 1236.2365;

        BankAccount bankAccount = new BankAccount("001");
        bankAccount.makeDeposit(deposit);
        assertThat(bankAccount.getBalance()).isEqualTo(expected);

        BankAccount bankAccountBig = new BankAccount("001");
        bankAccountBig.makeDeposit(BigDecimal.valueOf(deposit), new Date());
        assertThat(bankAccountBig.getBalance()).isEqualTo(expected);
    }

    @Test
    public void makeDeposit_negative_then_throw_error() {
        double deposit = -456.3;

        Throwable thrown = catchThrowable(() -> {
            BankAccount bankAccount = new BankAccount("001");
            bankAccount.makeDeposit(deposit);
        });
        makeDepositAndCheckError(thrown);

        Throwable thrownBig = catchThrowable(() -> {
            BankAccount bankAccount = new BankAccount("001");
            bankAccount.makeDeposit(BigDecimal.valueOf(deposit), new Date());
        });
        makeDepositAndCheckError(thrownBig);
    }

    private void makeDepositAndCheckError(Throwable thrown) {
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Deposit can't be negative")
        ;
    }
}