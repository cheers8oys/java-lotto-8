package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoList {
    private final List<Lotto> lottos;

    public LottoList(List<Lotto> lottos) {
        if (lottos == null) {
            throw new IllegalArgumentException("[ERROR] 로또 목록은 null일 수 없습니다.");
        }
        this.lottos = new ArrayList<>(lottos);
    }

    public void add(Lotto lotto) {
        if (lotto == null) {
            throw new IllegalArgumentException("[ERROR] 로또는 null일 수 없습니다.");
        }
        lottos.add(lotto);
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }

    public Lotto get(int index) {
        return lottos.get(index);
    }
}