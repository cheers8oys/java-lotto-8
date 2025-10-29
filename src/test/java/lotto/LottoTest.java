package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 입력받은_금액이_0보다_같거나_작으면_예외가_발생한다() {
        String money = "-1000";
        LottoService lottoService = new LottoService();

        assertThrows(IllegalArgumentException.class, () -> {
            lottoService.validateReceivedMoney(money);
        });
    }

    @Test
    void 입력받은_금액이_1000원단위가_아니면_예외가_발생한다() {
        String money = "1500";
        LottoService lottoService = new LottoService();

        assertThrows(IllegalArgumentException.class, () -> {
            lottoService.validateReceivedMoney(money);
        });
    }

    @Test
    void 입력받은_금액이_숫자가_아니면_예외가_발생한다() {
        String money = "abc";
        LottoService lottoService = new LottoService();

        assertThrows(IllegalArgumentException.class, () -> {
            lottoService.validateReceivedMoney(money);
        });
    }

    @Test
    void 금액만큼_해당하는_로또티켓수_발행한다() {
        LottoService lottoService = new LottoService();
        int attemptCount = lottoService.validateReceivedMoney("5000");
        assertEquals(5, lottoService.provideLottoTickets(attemptCount).size());
    }
}
