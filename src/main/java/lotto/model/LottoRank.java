package lotto.model;

public enum LottoRank {

    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean bonusMatched;
    private final int prize;

    LottoRank(int matchCount, boolean bonusMatched, int prize) {
        this.matchCount = matchCount;
        this.bonusMatched = bonusMatched;
        this.prize = prize;
    }

    public int getPrize() {
        return prize;
    }

    public static LottoRank from(int matchedCount, boolean bonusMatched) {
        if (matchedCount == FIRST.matchCount) {
            return FIRST;
        }
        if (matchedCount == SECOND.matchCount && bonusMatched) {
            return SECOND;
        }
        if (matchedCount == THIRD.matchCount) {
            return THIRD;
        }
        if (matchedCount == FOURTH.matchCount) {
            return FOURTH;
        }
        if (matchedCount == FIFTH.matchCount) {
            return FIFTH;
        }
        return NONE;
    }
}