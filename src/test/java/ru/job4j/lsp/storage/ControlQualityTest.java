package ru.job4j.lsp.storage;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenNotYetExist() {
        LocalDateTime testDate = LocalDateTime.of(2021, 7, 1, 4, 0);
        AbstractFood cheese = new Cheese("Gauda",
                LocalDateTime.of(2021, 8, 1, 4, 0),
                LocalDateTime.of(2021, 9, 1, 4, 0),
                100);
        ControlQuality quality = new ControlQuality(testDate, cheese);
        quality.getStorage(cheese);
    }

    @Test
    public void whenGoToWarehouse() {
        LocalDateTime testDate = LocalDateTime.of(2021, 7, 10, 4, 0);
        AbstractFood cheese = new Cheese("Gauda",
                LocalDateTime.of(2021, 12, 1, 4, 0),
                LocalDateTime.of(2021, 7, 1, 4, 0),
                100);
        ControlQuality quality = new ControlQuality(testDate, cheese);
        quality.getStorage(cheese);
        List<AbstractFood> expected = quality.getStrategy().getAll();
        assertThat(expected, is(List.of(cheese)));
    }

    @Test
    public void whenGoToShopWithoutDiscount() {
        LocalDateTime testDate = LocalDateTime.of(2021, 7, 4, 4, 0);
        AbstractFood cheese = new Cheese("Gauda",
                LocalDateTime.of(2021, 7, 10, 4, 0),
                LocalDateTime.of(2021, 7, 1, 4, 0),
                100);
        ControlQuality quality = new ControlQuality(testDate, cheese);
        quality.getStorage(cheese);
        List<AbstractFood> expected = quality.getStrategy().getAll();
        assertThat(expected, is(List.of(cheese)));
        assertFalse(cheese.isDiscount());
    }

    @Test
    public void whenGoToShopWithDiscount() {
        LocalDateTime testDate = LocalDateTime.of(2021, 7, 9, 4, 0);
        AbstractFood cheese = new Cheese("Gauda",
                LocalDateTime.of(2021, 7, 10, 4, 0),
                LocalDateTime.of(2021, 7, 1, 4, 0),
                100);
        ControlQuality quality = new ControlQuality(testDate, cheese);
        quality.getStorage(cheese);
        List<AbstractFood> expected = quality.getStrategy().getAll();
        assertThat(expected, is(List.of(cheese)));
        assertTrue(cheese.isDiscount());
    }

    @Test
    public void whenGoToTrash() {
        LocalDateTime testDate = LocalDateTime.of(2021, 7, 10, 5, 0);
        AbstractFood cheese = new Cheese("Gauda",
                LocalDateTime.of(2021, 7, 10, 4, 0),
                LocalDateTime.of(2021, 7, 1, 4, 0),
                100);
        ControlQuality quality = new ControlQuality(testDate, cheese);
        quality.getStorage(cheese);
        List<AbstractFood> expected = quality.getStrategy().getAll();
        assertThat(expected, is(List.of(cheese)));
    }
}