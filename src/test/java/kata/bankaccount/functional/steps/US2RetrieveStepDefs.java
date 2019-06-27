package kata.bankaccount.functional.steps;

import cucumber.api.java8.En;
import kata.bankaccount.BankAccount;
import kata.bankaccount.BankAccountRepository;
import kata.bankaccount.functional.StepContext;

public class US2RetrieveStepDefs implements En {
    private BankAccountRepository bankAccountRepository = new BankAccountRepository();

    public US2RetrieveStepDefs(BankAccountRepository bankAccountRepository, StepContext context) {

        When("I make a withdrawal of {} €", (Double withdrawal) -> {
            try {
                BankAccount bankAccount = bankAccountRepository.findByClientNo(context.currentClientNo);
                bankAccount.makeWithdrawal(withdrawal);
            } catch (Exception e) {
                context.actualErrorMessage = e.getMessage();
            }
        });

    }}
