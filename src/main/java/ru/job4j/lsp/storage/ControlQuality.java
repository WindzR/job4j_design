package ru.job4j.lsp.storage;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * класс принимает наследников AbstractFood и выбирает куда направить продукт, в зависимости от условий
 */
public class ControlQuality {

    private Depository depository;
    private List<Depository> depositories = new ArrayList<>();
    private LocalDateTime currentDate;
    private ZoneOffset zone = ZoneOffset.UTC;
    private AbstractFood food;

    public ControlQuality() {
        depositories = List.of(new WareHouse(), new Shop(), new Trash());
        currentDate = LocalDateTime.now();
    }

    public ControlQuality(AbstractFood food) {
        depositories = List.of(new WareHouse(), new Shop(), new Trash());
        currentDate = LocalDateTime.now();
        this.food = food;
    }

    public ControlQuality(LocalDateTime date, AbstractFood food) {
        depositories = List.of(new WareHouse(), new Shop(), new Trash());
        currentDate = date;
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
        if (create.isAfter(currentDate)) {
            throw new IllegalArgumentException("Продукта еще не существует!");
        }
        if (expire.isBefore(currentDate)) {
            System.out.println("Срок годности истек! Пора выбрасывать!");
            result = 100;
        }
        if (create.isBefore(currentDate) && expire.isAfter(currentDate)) {
            long foodLife = expire.toEpochSecond(zone) - create.toEpochSecond(zone);
            long timePassed = currentDate.toEpochSecond(zone) - create.toEpochSecond(zone);
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
        for (Depository depository : depositories) {
            if (depository.accept(shelfLife)) {
                this.depository = depository;
                break;
            }
        }
        depository.storage(food);
    }
}
