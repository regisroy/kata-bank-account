package kata.bankaccount.functional.steps;


import cucumber.api.java8.En;
import kata.bankaccount.BankAccount;
import kata.bankaccount.BankAccountRepository;
import kata.bankaccount.functional.StepContext;

//@SuppressWarnings("unused")
public class US1DepositStepDefs implements En {

    public US1DepositStepDefs(BankAccountRepository bankAccountRepository, StepContext context) {

        When("I make a deposit of {} €", (Double deposit) -> {
            try {
                BankAccount bankAccount = bankAccountRepository.findByClientNo(context.currentClientNo);
                bankAccount.makeDeposit(deposit);
            } catch (Exception e) {
                context.actualErrorMessage = e.getMessage();
            }
        });

    }

}
