package lotto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.model.BonusNumber;
import lotto.model.InputValidator;
import lotto.model.Lotto;
import lotto.model.LottoNumberMatcher;
import lotto.model.LottoRank;
import lotto.model.LottoService;
import lotto.model.WinningNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private static final int LOTTO_PRICE = 1000;

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService = new LottoService();

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {

        int purchaseCount = getPurchaseCount();
        List<Lotto> lottoList = getLottoList(purchaseCount);

        WinningNumber winningNumber = getWinningNumber();
        int bonusNumber = getBonusNumber(winningNumber);

        Map<LottoRank, Integer> rankCount = getLottoRankIntegerMap(lottoList, winningNumber, bonusNumber);
        outputView.printStatistics(rankCount);

        double profitRate = lottoService.calculateProfitRate(rankCount, purchaseCount, LOTTO_PRICE);
        outputView.printProfitRate(profitRate);
    }

    private int getBonusNumber(WinningNumber winningNumber) {
        int bonus = new InputValidator<>(
                () -> new BonusNumber(winningNumber).validate(inputView.readBonusNumber()),
                outputView
        ).validate();
        return bonus;
    }

    private WinningNumber getWinningNumber() {
        WinningNumber winningNumber = new InputValidator<>(
                () -> new WinningNumber(inputView.readLottoWinningNumber()),
                outputView
        ).validate();
        return winningNumber;
    }

    private List<Lotto> getLottoList(int purchaseCount) {
        List<Lotto> lottoList = new InputValidator<>(
                () -> lottoService.provideLottoTickets(purchaseCount),
                outputView
        ).validate();
        outputView.printLottoNumbers(lottoList);
        return lottoList;
    }

    private int getPurchaseCount() {
        int purchaseCount = new InputValidator<>(
                () -> lottoService.validateReceivedMoney(inputView.readLottoPurchasePrice()),
                outputView
        ).validate();
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
