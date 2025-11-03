package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoService {

    public int validateReceivedMoney(String receivedMoney) {
        int parsedReceivedMoney;
        try {
            parsedReceivedMoney = Integer.parseInt(receivedMoney);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력 가능합니다.");
        }
        if (parsedReceivedMoney <= 0) {
            throw new IllegalArgumentException("[ERROR] 최소 금액은 1000원입니다.");
        }
        if (parsedReceivedMoney % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 1000원 단위여야 합니다.");
        }
        return parsedReceivedMoney / 1000;
    }

    public List<Lotto> provideLottoTickets(int attemptCount) {
        if (attemptCount <= 0) {
            throw new IllegalArgumentException("[ERROR] 로또는 1개 이상이어야 합니다.");
        }
        LottoNumberGenerator generator = new LottoNumberGenerator();
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < attemptCount; i++) {
            List<Integer> numbers = generator.createLotto();
            Lotto lotto = new Lotto(numbers);
            lottoList.add(lotto);
        }
        return lottoList;
    }

    public double calculateProfitRate(Map<LottoRank, Integer> rankCount, int purchaseCount, int lottoPrice) {
        long totalWinningPrize = rankCount.entrySet().stream()
                .mapToLong(e -> (long) e.getKey().getPrize() * e.getValue())
                .sum();

        long totalCost = (long) purchaseCount * lottoPrice;
        double profitRate = (double) totalWinningPrize / totalCost * 100;
        return Math.round(profitRate * 100) / 100.0;
    }
}
