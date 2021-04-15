package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean turnOn = true;
        String model = "X-52";
        int yearOfrelease = 2009;
        float capacity = 0.75f;
        double sizeOfMemory = 784.5;
        byte registr = 64;
        short supply = 1024;
        long quantity = 100500;
        LOG.debug(
                "Model info turnOn : {}, model : {}, yearOfrelease : {}, capacity : {}, sizeOfMemory : {}, registr : {}, supply : {}, quantity : {}",
                 turnOn, model, yearOfrelease, capacity, sizeOfMemory, registr, supply, quantity);

    }
}
