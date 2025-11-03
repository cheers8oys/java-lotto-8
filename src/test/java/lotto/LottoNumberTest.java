package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import lotto.model.BonusNumber;
import lotto.model.LottoNumberGenerator;
import lotto.model.WinningNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @Test
    @DisplayName("6자리 무작위 로또 숫자 생성 테스트")
    public void testCreating6figureLottoNumber() {
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        assertEquals(6, lottoNumberGenerator.createLotto().size());
    }

    @Test
    public void 당첨번호_6자리_숫자_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningNumber("1,2,3,4,5,6,7"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 당첨번호_중복이_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningNumber("1,2,3,4,5,5"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 당첨번호_범위에_해당하지않는_수가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningNumber("1,2,3,4,5,46"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 유효하지_않은_입력값이_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningNumber("1,2,3,4,5,ㅁ"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 보너스번호와_당첨번호가_중복이_있으면_예외가_발생한다() {
        String intputBonusNumber = "6";
        WinningNumber winningNumber = new WinningNumber("1,2,3,4,5,6");
        BonusNumber bonusNumber = new BonusNumber(winningNumber);
        assertThatThrownBy(() -> bonusNumber.validate(intputBonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "ㅁ", " ", "", "46", "0", "-1", })
    public void 보너스번호에_유효하지_않은_입력이_있으면_예외가_발생한다(String inputValue) {
        WinningNumber winningNumber = new WinningNumber("1,2,3,4,5,6");
        BonusNumber bonusNumber = new BonusNumber(winningNumber);
        assertThatThrownBy(() -> bonusNumber.validate(inputValue))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
