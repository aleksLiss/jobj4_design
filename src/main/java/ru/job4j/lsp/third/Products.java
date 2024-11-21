package ru.job4j.lsp.third;

public class Products {

    protected int shelfLife;

    public Products(int shelfLife) {
        this.shelfLife = shelfLife;
    }

    public void checkMinShelfLife(int shelfLife) {
        int minShelfLife = 7;
        int maxShelfLife = 14;
        if (shelfLife < minShelfLife) {
            throw new IllegalArgumentException("Products have shelf life equals 7 days");
        }
        if (shelfLife > maxShelfLife) {
            throw new IllegalArgumentException("Product can't be sold");
        }
        System.out.println("Product can be sold");
    }
}


class Apple extends Products {

    public Apple(int shelfLife) {
        super(shelfLife);
    }
}


class Milk extends Products {

    public Milk(int shelfLife) {
        super(shelfLife);
    }

    /**
     * Происходит нарушение 3 пункта принципа LSP, а именно:
     * нет соблюдения минимальных условий в методе родительского класса.
     *
     * @param shelfLife
     */
    @Override
    public void checkMinShelfLife(int shelfLife) {
        int minShelfLife = 7;
        if (shelfLife < minShelfLife) {
            throw new IllegalArgumentException("Products have shelf life equals 7 days");
        }
        System.out.println("Product can be sold");
    }
}