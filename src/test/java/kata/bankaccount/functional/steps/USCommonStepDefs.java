package kata.bankaccount.functional.steps;


import cucumber.api.java8.En;
import kata.bankaccount.BankAccount;
import kata.bankaccount.BankAccountRepository;
import kata.bankaccount.functional.StepContext;

import static org.assertj.core.api.Assertions.assertThat;

public class USCommonStepDefs implements En {

    public USCommonStepDefs(BankAccountRepository bankAccountRepository, StepContext context) {
        Given("Client {} exists", (String clientNo) -> {
            BankAccount bankAccount = new BankAccount(clientNo);
            bankAccountRepository.addBankAccount(bankAccount);
        });
        Given("I'm the client {}", (String clientNo) -> {
            context.currentClientNo = clientNo;
        });
        Given("Bank account has a (positiv )(negativ )balance of {} €", (Double balance) -> {
            BankAccount bankAccount = bankAccountRepository.findByClientNo(context.currentClientNo);
            bankAccount.setBalance(balance);
        });

        Then("I should get the new balance {} €", (Double balance) -> {
            BankAccount bankAccount = bankAccountRepository.findByClientNo(context.currentClientNo);
            assertThat(bankAccount.getBalance()).isEqualTo(balance);
        });

        Then("I should get the error message : {}", (String expectedErrorMessage) -> {
            assertThat(context.actualErrorMessage).isEqualTo(expectedErrorMessage);
        });

    }

}
