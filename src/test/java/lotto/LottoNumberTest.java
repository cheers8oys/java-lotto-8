package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
