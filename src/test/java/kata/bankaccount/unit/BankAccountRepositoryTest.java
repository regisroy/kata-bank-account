package kata.bankaccount.unit;

import kata.bankaccount.BankAccount;
import kata.bankaccount.BankAccountAlreadyExistsException;
import kata.bankaccount.BankAccountException;
import kata.bankaccount.BankAccountRepository;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class BankAccountRepositoryTest {

    private BankAccountRepository bankAccountRepository;

    @Before
    public void setUp() {
        bankAccountRepository = new BankAccountRepository();
    }

    @Test(expected = BankAccountException.class)
    public void addBankAccount_return_error_when_bankAccount_is_null_or_blank() {
        bankAccountRepository.addBankAccount(null);
    }

    @Test
    public void addBankAccount_return_error_when_clientNo_is_null_or_blank() {
        addBankAccountAndCheckError(null, "The client number is not defined.");
        addBankAccountAndCheckError("", "The client number is not defined.");
        addBankAccountAndCheckError("  ", "The client number is not defined.");
    }

    @Test
    public void addBankAccount_nominal_check_count_when_add() {
        bankAccountRepository.addBankAccount(new BankAccount("001"));
        bankAccountRepository.addBankAccount(new BankAccount("002"));
        bankAccountRepository.addBankAccount(new BankAccount("003"));
        assertThat(bankAccountRepository.count()).isEqualTo(3);
    }

    @Test
    public void findByClientNo_return_error_when_null_or_blank() {
        findByClientNoAndCheckError(null, "The client number must be different from null, empty or blank.");
        findByClientNoAndCheckError("", "The client number must be different from null, empty or blank.");
        findByClientNoAndCheckError("  ", "The client number must be different from null, empty or blank.");
    }

    @Test(expected = BankAccountAlreadyExistsException.class)
    public void addBankAccount_return_error_when_alread_exist() {
        bankAccountRepository.addBankAccount(new BankAccount("001"));
        bankAccountRepository.addBankAccount(new BankAccount("001"));
    }


    private void addBankAccountAndCheckError(String clientNo, String expectedMessage) {
        Throwable thrown = catchThrowable(() -> {
            bankAccountRepository.addBankAccount(new BankAccount(clientNo));
        });
        assertThat(thrown)
                .isInstanceOf(BankAccountException.class)
                .hasMessage(expectedMessage)
        ;
    }

    private void findByClientNoAndCheckError(String clientNo, String expectedMessage) {
        Throwable thrown = catchThrowable(() -> {
            bankAccountRepository.findByClientNo(clientNo);
        });
        assertThat(thrown)
                .isInstanceOf(BankAccountException.class)
                .hasMessage(expectedMessage)
        ;
    }

}