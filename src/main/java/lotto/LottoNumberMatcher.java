package lotto;

import java.util.List;

public class LottoNumberMatcher {

    private final List<Integer> lottoNumbers;
    private final List<Integer> winningNumber;
    private final int bonusNumber;

    public LottoNumberMatcher(List<Integer> lottoNumbers, List<Integer> winningNumber, int bonusNumber) {
        this.lottoNumbers = lottoNumbers;
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    public int countMatchingNumbers() {
        int count = 0;
        for (int number : lottoNumbers) {
            if (winningNumber.contains(number)) {
                count++;
            }
        }
        return count;
    }

    public boolean isBonusNumberMatched() {
        return lottoNumbers.contains(bonusNumber);
    }
}
