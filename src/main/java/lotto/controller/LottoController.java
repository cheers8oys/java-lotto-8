package lotto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.BonusNumber;
import lotto.Lotto;
import lotto.LottoNumberMatcher;
import lotto.LottoRank;
import lotto.LottoService;
import lotto.WinningNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private static final int LOTTO_PRICE = 1000;
    private static final String ERROR_MESSAGE = "[ERROR]";

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService = new LottoService();

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {

        int purchaseCount = getPurchaseCount();

        List<Lotto> lottoList = lottoService.provideLottoTickets(purchaseCount);
        outputView.printLottoNumbers(lottoList);

        WinningNumber winningNumber = new WinningNumber(inputView.readLottoWinningNumber());

        BonusNumber bonusNumber = new BonusNumber(winningNumber);
        int bonus = bonusNumber.validate(inputView.readBonusNumber());

        Map<LottoRank, Integer> rankCount = getLottoRankIntegerMap(lottoList, winningNumber, bonus);

        outputView.printStatistics(rankCount);

        double profitRate = lottoService.calculateProfitRate(rankCount, purchaseCount, LOTTO_PRICE);
        outputView.printProfitRate(profitRate);
    }

    private int getPurchaseCount() {
        int purchaseCount;
        try {
            purchaseCount = lottoService.validateReceivedMoney(inputView.readLottoPurchasePrice());
        } catch (IllegalArgumentException e)  {
            System.out.println(ERROR_MESSAGE);
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        outputView.printPurchaseCount(purchaseCount);
        return purchaseCount;
    }

    private static Map<LottoRank, Integer> getLottoRankIntegerMap(List<Lotto> lottoList, WinningNumber winningNumber,
                                                                  int bonus) {
        Map<LottoRank, Integer> rankCount = new HashMap<>();
        for (Lotto lotto : lottoList) {
            LottoNumberMatcher matcher = new LottoNumberMatcher(lotto.getNumbers(), winningNumber.getNumbers(), bonus);
            int matchedCount = matcher.countMatchingNumbers();
            boolean bonusMatched = matcher.isBonusNumberMatched();
            LottoRank rank = LottoRank.from(matchedCount, bonusMatched);
            rankCount.put(rank, rankCount.getOrDefault(rank, 0) + 1);
        }
        return rankCount;
    }

}
