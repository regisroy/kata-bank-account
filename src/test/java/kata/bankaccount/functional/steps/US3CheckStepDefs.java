package kata.bankaccount.functional.steps;

import cucumber.api.java8.En;
import kata.bankaccount.BankAccount;
import kata.bankaccount.BankAccountRepository;
import kata.bankaccount.functional.StepContext;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class US3CheckStepDefs implements En {

    public US3CheckStepDefs(BankAccountRepository bankAccountRepository, StepContext context) {
        Given("I make a withdrawal of {} the {}", (Double amount, String operationDateTime) -> {
            Date operationDate=new SimpleDateFormat("yyyy-MM-dd' at 'HH'h'mm").parse(operationDateTime);
            try {
                BankAccount bankAccount = bankAccountRepository.findByClientNo(context.currentClientNo);
                bankAccount.makeWithdrawal(amount, operationDate);
            } catch (Exception e) {
                context.actualErrorMessage = e.getMessage();
            }
        });
        Given("I make a deposit of {} the {}", (Double amount, String operationDateTime) -> {
            Date operationDate=new SimpleDateFormat("yyyy-MM-dd' at 'HH'h'mm").parse(operationDateTime);
            try {
                BankAccount bankAccount = bankAccountRepository.findByClientNo(context.currentClientNo);
                bankAccount.makeDeposit(amount, operationDate);
            } catch (Exception e) {
                context.actualErrorMessage = e.getMessage();
            }
        });
        When("I consult my operations", () -> {
            try {
                bankAccountRepository.findByClientNo(context.currentClientNo);
            } catch (Exception e) {
                context.actualErrorMessage = e.getMessage();
            }
        });
        Then("I should get the result", (String docString) -> {
            BankAccount bankAccount = bankAccountRepository.findByClientNo(context.currentClientNo);
            String consultOperations = bankAccount.consultOperations();
            assertThat(consultOperations).isEqualTo(docString);
        });
    }


}
