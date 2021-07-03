package ru.job4j.lsp;

/**
 * класс нарушает принцип LSP(и заодно OCP), так как при добавлении новых enum SmartPhone нужно будет переписывать метод в классе.
 */
public class CheckRating {

    public void checkRating(User user) {
        if (user.getSmartPhone() == SmartPhone.HUAWEI || user.getSmartPhone() == SmartPhone.MEIZU
        || user.getSmartPhone() == SmartPhone.XIAOMI || user.getSmartPhone() == SmartPhone.OPPO) {
            user.setSocialRating(user.getSocialRating() + 100);
        }
        if (user.getSmartPhone() == SmartPhone.ASUS || user.getSmartPhone() == SmartPhone.LG
        || user.getSmartPhone() == SmartPhone.SAMSUNG) {
            user.setSocialRating(user.getSocialRating() - 10);
        }
        if (user.getSmartPhone() == SmartPhone.APPLE || user.getSmartPhone() == SmartPhone.SONY) {
            user.setSocialRating(user.getSocialRating() - 100);
        }
    }
}
