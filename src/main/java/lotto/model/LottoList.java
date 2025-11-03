package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoList {
    private final List<Lotto> lottos;

    public LottoList(List<Lotto> lottos) {
        if (lottos == null || lottos.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 로또 수는 1개 이상이어야 합니다.");
        }
        this.lottos = new ArrayList<>(lottos);
    }

    public void add(Lotto lotto) {
        if (lotto == null) {
            throw new IllegalArgumentException("[ERROR] 로또는 NULL일 수 없습니다.");
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