package ru.job4j;

public class Trigger {
    public int someLogic() {
        return 1;
    }

    public static void main(String[] args) {
        String key = "-Xmx=";
        String[] temp = new String[2];
        temp = key.split("=");
        temp[0] = temp[0].substring(1);
        System.out.println(temp[0] + " " + temp[1]);
    }
}
