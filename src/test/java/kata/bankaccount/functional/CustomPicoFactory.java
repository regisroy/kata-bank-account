package kata.bankaccount.functional;

import cucumber.runtime.java.picocontainer.PicoFactory;
import kata.bankaccount.BankAccountRepository;

public class CustomPicoFactory extends PicoFactory {

    public CustomPicoFactory() {
        addClass(BankAccountRepository.class);
        addClass(StepContext.class);
    }
}
