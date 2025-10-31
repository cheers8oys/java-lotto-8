package lotto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class LottoNumberMatcherTest {

    @Test
    void 로또번호_당첨번호와_보너스번호_비교해서_1등_반환(){
        WinningNumber winningNumber = new WinningNumber("1,2,3,4,5,6");
        BonusNumber bonusNumber = new BonusNumber(winningNumber);
        int validatedBonusNumber = bonusNumber.validate("7");

        LottoNumberMatcher lottoNumberMatcher = new LottoNumberMatcher(List.of(1,2,3,4,5,6), winningNumber.getNumbers(), validatedBonusNumber);
        assertEquals(6, lottoNumberMatcher.countMatchingNumbers());
    }

    @Test
    void 로또번호_당첨번호와_보너스번호_비교해서_2등_반환(){
        WinningNumber winningNumber = new WinningNumber("1,2,3,4,5,6");
        BonusNumber bonusNumber = new BonusNumber(winningNumber);
        int validatedBonusNumber = bonusNumber.validate("7");

        LottoNumberMatcher lottoNumberMatcher = new LottoNumberMatcher(List.of(1,2,3,4,5,7), winningNumber.getNumbers(), validatedBonusNumber);
        assertEquals(5, lottoNumberMatcher.countMatchingNumbers());
        assertTrue(lottoNumberMatcher.isBonusNumberMatched());
    }

    @Test
    void 로또번호_당첨번호와_보너스번호_비교해서_3등_반환(){
        WinningNumber winningNumber = new WinningNumber("1,2,3,4,5,6");
        BonusNumber bonusNumber = new BonusNumber(winningNumber);
        int validatedBonusNumber = bonusNumber.validate("7");

        LottoNumberMatcher lottoNumberMatcher = new LottoNumberMatcher(List.of(1,2,3,4,5,8), winningNumber.getNumbers(), validatedBonusNumber);
        assertEquals(5, lottoNumberMatcher.countMatchingNumbers());
    }

    @Test
    void 로또번호_당첨번호와_보너스번호_비교해서_4등_반환(){
        WinningNumber winningNumber = new WinningNumber("1,2,3,4,5,6");
        BonusNumber bonusNumber = new BonusNumber(winningNumber);
        int validatedBonusNumber = bonusNumber.validate("7");

        LottoNumberMatcher lottoNumberMatcher = new LottoNumberMatcher(List.of(1,2,3,4,9,10), winningNumber.getNumbers(), validatedBonusNumber);
        assertEquals(4, lottoNumberMatcher.countMatchingNumbers());
    }

    @Test
    void 로또번호_당첨번호와_보너스번호_비교해서_5등_반환(){
        WinningNumber winningNumber = new WinningNumber("1,2,3,4,5,6");
        BonusNumber bonusNumber = new BonusNumber(winningNumber);
        int validatedBonusNumber = bonusNumber.validate("7");

        LottoNumberMatcher lottoNumberMatcher = new LottoNumberMatcher(List.of(1,2,3,8,9,10), winningNumber.getNumbers(), validatedBonusNumber);
        assertEquals(3, lottoNumberMatcher.countMatchingNumbers());
    }
}
