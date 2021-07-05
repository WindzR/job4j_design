package ru.job4j.lsp.storage;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * класс принимает наследников AbstractFood и выбирает куда направить продукт, в зависимости от условий
 */
public class ControlQuality {
    private Depository depository;
    private LocalDateTime now = LocalDateTime.now();
    private ZoneOffset zone = ZoneOffset.UTC;
    private AbstractFood food;

    public ControlQuality() {
    }

    public ControlQuality(AbstractFood food) {
        this.food = food;
    }

    /**
     * метод для проверки самой стратегии тестами
     * @return выбранная стратегия
     */
    public Depository getStrategy() {
        return depository;
    }

    /**
     * метод проверяет срок годности продукта(в %), допустимые значения от 0 до 100%,
     * если равен -1, то продукта еще не существует, если равен 100, то негоден
     * @param food проверяемый продукт
     */
    private double checkShelfLife(AbstractFood food) {
        double result = -1;
        LocalDateTime create = food.getCreateDate();
        LocalDateTime expire = food.getExpireDate();
        if (create.isAfter(now)) {
            throw new IllegalArgumentException("Продукта еще не существует!");
        }
        if (expire.isBefore(now)) {
            System.out.println("Срок годности истек! Пора выбрасывать!");
            result = 100;
        }
        if (create.isBefore(now) && expire.isAfter(now)) {
            long foodLife = expire.toEpochSecond(zone) - create.toEpochSecond(zone);
            long timePassed = now.toEpochSecond(zone) - create.toEpochSecond(zone);
            result = (double) timePassed * 100 / foodLife;
        }
        return result;
    }

    /**
     * метод для распределения продуктов, в зависимости от срока годности(паттерн стратегия)
     * @param food проверяемый продукт
     */
    public void getStorage(AbstractFood food) {
        double shelfLife = checkShelfLife(food);
        if (shelfLife >= 0 && shelfLife < 25) {
            depository = new WareHouse();
        }
        if (shelfLife >= 25 && shelfLife < 100) {
            depository = new Shop();
            if (shelfLife >= 75) {
                food.setUpDiscount(true);
            }
        }
        if (shelfLife == 100) {
            depository = new Trash();
        }
        depository.storage(food);
    }


}
