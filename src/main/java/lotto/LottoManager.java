package lotto;

public class LottoManager {

    private static int parsedReceivedMoney;

    public int validateReceivedMoney(String receivedMoney) {

        try {
            parsedReceivedMoney = Integer.parseInt(receivedMoney);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[숫자만 입력 가능합니다.");
        }
        if (parsedReceivedMoney <= 0) {
            throw  new IllegalArgumentException("[ERROR] 최소 금액은 1000원입니다.");
        }
        if (parsedReceivedMoney % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 1000원 단위여야 합니다.");
        }
        return parsedReceivedMoney / 1000;
    }
}
