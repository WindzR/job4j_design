package ru.job4j.lsp.storage;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Scanner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {
    private LocalDateTime date = LocalDateTime.of(2021, 6, 1, 6, 0);
    private Depository depository;

    @Test(expected = IllegalArgumentException.class)
    public void whenNotYetExist() {
        AbstractFood cheese = new Cheese("Gauda",
                LocalDateTime.of(2021, 8, 1, 4, 0),
                LocalDateTime.of(2021, 9, 1, 4, 0),
                100);
        ControlQuality quality = new ControlQuality(cheese);
        quality.getStorage(cheese);
    }

    @Test
    public void whenGoToWarehouse() {
        AbstractFood cheese = new Cheese("Gauda",
                LocalDateTime.of(2021, 12, 1, 4, 0),
                LocalDateTime.of(2021, 7, 1, 4, 0),
                100);
        ControlQuality quality = new ControlQuality(cheese);
        quality.getStorage(cheese);
        depository = quality.getStrategy();
        String result = depository.getClass().getName();
        assertThat(result, is("ru.job4j.lsp.storage.WareHouse"));
    }

    @Test
    public void whenGoToShopWithoutDiscount() {
        AbstractFood cheese = new Cheese("Gauda",
                LocalDateTime.of(2021, 7, 7, 4, 0),
                LocalDateTime.of(2021, 7, 1, 4, 0),
                100);
        ControlQuality quality = new ControlQuality(cheese);
        quality.getStorage(cheese);
        depository = quality.getStrategy();
        String result = depository.getClass().getName();
        assertThat(result, is("ru.job4j.lsp.storage.Shop"));
        assertFalse(cheese.isDiscount());
    }

    @Test
    public void whenGoToShopWithDiscount() {
        AbstractFood cheese = new Cheese("Gauda",
                LocalDateTime.of(2021, 7, 6, 4, 0),
                LocalDateTime.of(2021, 7, 1, 4, 0),
                100);
        ControlQuality quality = new ControlQuality(cheese);
        quality.getStorage(cheese);
        depository = quality.getStrategy();
        String result = depository.getClass().getName();
        assertThat(result, is("ru.job4j.lsp.storage.Shop"));
        assertTrue(cheese.isDiscount());
    }

    @Test
    public void whenGoToTrash() {
        Scanner sc = new Scanner(System.in);
        AbstractFood cheese = new Cheese("Gauda",
                LocalDateTime.of(2021, 7, 4, 4, 0),
                LocalDateTime.of(2021, 7, 1, 4, 0),
                100);
        ControlQuality quality = new ControlQuality(cheese);
        quality.getStorage(cheese);
        depository = quality.getStrategy();
        String result = depository.getClass().getName();
        assertThat(result, is("ru.job4j.lsp.storage.Trash"));
    }
}