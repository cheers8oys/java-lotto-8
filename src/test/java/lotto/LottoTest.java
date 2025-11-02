package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
    void 로또_번호에_유효하지_않은_입력이_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 46, 5)))
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

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void 복권_발급_요청_숫자가_0이거나_0보다_작을경우_예외가_발생한다(int attemptCount) {
        LottoService lottoService = new LottoService();
        assertThrows(IllegalArgumentException.class, () -> {
            lottoService.provideLottoTickets(attemptCount);
        });
    }

    @Test
    void 로또번호리스트가_null일_경우_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(null);  // null 리스트로 생성 시도
        });
    }

    @Test
    void 로또목록이_비었을_경우_예외가_발생한다() {
        List<Lotto> lottoes = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            new LottoList(lottoes);
        });
    }

    @Test
    void 로또목록에_null로또를_추가하면_예외가_발생한다() {
        LottoList lottoList = new LottoList(List.of(new Lotto(List.of(1,2,3,4,5,6))));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            lottoList.add(null);
        });
    }

    @Test
    void 총상금과_구입_금액으로_수익률_계산_한다() {
        LottoService service = new LottoService();

        Map<LottoRank, Integer> rankCount = Map.of(
                LottoRank.THIRD, 1,
                LottoRank.FOURTH, 0,
                LottoRank.FIFTH, 0,
                LottoRank.SECOND, 0,
                LottoRank.FIRST, 0,
                LottoRank.NONE, 7
        );
        int purchaseCount = 8;
        int lottoPrice = 1000;

        double result = service.calculateProfitRate(rankCount, purchaseCount, lottoPrice);
        double expectedProfitRate = Math.round((1_500_000.0 / 8000.0 * 100) * 100) / 100.0;

        assertEquals(result, expectedProfitRate);
    }
}