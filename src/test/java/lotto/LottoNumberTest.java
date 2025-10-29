package lotto;

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
}
