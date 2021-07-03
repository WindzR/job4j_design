package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * так как в подклассе изменены предусловия, то нарушается принцип подстановки Лисков
 */
public class GazpromShareStorage extends SharesStorage {
    private List<GazShares> gazShares = new ArrayList<>();

    public void purchase(int quantity) {
        GazShares share = new GazShares();
        boolean isAllowed = share.getCost() > 20;
        if (isAllowed) {
            for (int i = 0; i < quantity; i++) {
                gazShares.add(share);
            }
        }
    }

    public void sale(int quantity) {
        GazShares share = new GazShares();
        boolean isAllowed = share.getCost() <= 20 && gazShares.size() >= quantity;
        if (isAllowed) {
            for (int i = 0; i < quantity; i++) {
                gazShares.remove(share);
            }
        }
    }

    public class GazShares {
        private int cost;

        public GazShares() {
        }

        public GazShares(int cost) {
            this.cost = cost;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            GazShares shares = (GazShares) o;
            return cost == shares.cost;
        }

        @Override
        public int hashCode() {
            return Objects.hash(cost);
        }
    }
}
