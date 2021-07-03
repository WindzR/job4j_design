package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SharesStorage {
    private List<Shares> shares = new ArrayList<>();

    public void purchase(int quantity) {
        Shares share = new Shares();
        boolean isAllowed = share.getCost() > 100;
        if (isAllowed) {
            for (int i = 0; i < quantity; i++) {
                shares.add(share);
            }
        }
    }

    public void sale(int quantity) {
        Shares share = new Shares();
        boolean isAllowed = share.getCost() < 100 && shares.size() >= quantity;
        if (isAllowed) {
            for (int i = 0; i < quantity; i++) {
                shares.remove(share);
            }
        }
    }

    public class Shares {
        private int cost;

        public Shares() {
        }

        public Shares(int cost) {
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
            Shares shares = (Shares) o;
            return cost == shares.cost;
        }

        @Override
        public int hashCode() {
            return Objects.hash(cost);
        }
    }
}
