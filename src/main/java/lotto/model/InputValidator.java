package lotto.model;

import java.util.function.Supplier;
import lotto.view.OutputView;

public class InputValidator<T> {

    private final Supplier<T> inputSupplier;
    private final OutputView outputView;

    public InputValidator(Supplier<T> inputSupplier, OutputView outputView) {
        this.inputSupplier = inputSupplier;
        this.outputView = outputView;
    }

    public T validate() {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (IllegalArgumentException | IllegalStateException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}