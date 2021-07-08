package ru.job4j.isp;

public class SiemensC60 implements SmartPhone {

    @Override
    public void calling() {
       //some code;
    }

    @Override
    public void useInternet() {
        //some code;
    }

    @Override
    public void takePhoto() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void takeVideo() {
        throw new UnsupportedOperationException();
    }
}
