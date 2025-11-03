package lotto.model;

import java.util.List;

public class BonusNumber {

    private final WinningNumber winningNumber;

    public BonusNumber(WinningNumber winningNumber) {
        this.winningNumber = winningNumber;
    }

    public int validate(String inputValue) {
        int bonusNumber = ParseAndValidateBonusNumber(inputValue);
        comparisonWithWinningNumber(winningNumber.getNumbers(), bonusNumber);
        return bonusNumber;
    }

    private int ParseAndValidateBonusNumber(String intputValue) {
        if (intputValue == null || intputValue.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 반드시 입력해야 합니다.");
        }
        if (!intputValue.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
        }
        int bonusNumber = Integer.parseInt(intputValue);
        if (bonusNumber <= 0 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1 ~ 45 사이의 숫자여야 합니다.");
        }
        return bonusNumber;
    }

    private void comparisonWithWinningNumber(List<Integer> numbers, int bonusNum) {
        if (numbers.contains(bonusNum)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
