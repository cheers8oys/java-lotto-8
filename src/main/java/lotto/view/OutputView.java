package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.model.Lotto;
import lotto.model.LottoRank;

public class OutputView {

    public void printError(String errorMessage) {
        if (errorMessage.startsWith("[ERROR]")) {
            System.out.println(errorMessage);
        }
        System.out.println("[ERROR] " + errorMessage);
    }

    public void printPurchaseCount(int purchaseCount) {
        System.out.printf("%d개를 구매했습니다.%n", purchaseCount);
    }

    public void printLottoNumbers(List<Lotto> lottoList) {
        for (Lotto lotto : lottoList) {
            System.out.println(lotto.getNumbers().toString());
        }
    }

    public void printStatistics(Map<LottoRank, Integer> rankCount) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        printRank(rankCount, LottoRank.FIFTH, "3개 일치 (5,000원)");
        printRank(rankCount, LottoRank.FOURTH, "4개 일치 (50,000원)");
        printRank(rankCount, LottoRank.THIRD, "5개 일치 (1,500,000원)");
        printRank(rankCount, LottoRank.SECOND, "5개 일치, 보너스 볼 일치 (30,000,000원)");
        printRank(rankCount, LottoRank.FIRST, "6개 일치 (2,000,000,000원)");
    }

    private void printRank(Map<LottoRank, Integer> rankCount, LottoRank rank, String message) {
        int count = rankCount.getOrDefault(rank, 0);
        System.out.printf("%s - %d개%n", message, count);
    }

    public void printProfitRate(double profitRate) {
        System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
    }
}