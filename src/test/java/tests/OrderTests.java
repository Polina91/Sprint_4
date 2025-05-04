package tests;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.pageobject.MainPage;
import org.pageobject.OrderPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

@RunWith(Parameterized.class)
public class OrderTests extends BaseTest{ ;

    private final String name;
    private final String lastName;
    private final String address;
    private final String phone;
    private final String placedOrderText = "Заказ оформлен";

    public OrderTests(String name, String lastName, String address, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Иван", "Иванов", "ул. Ленина, 1", "+79998887766"},
                {"Мария", "Петрова", "ул. Гагарина, 5", "+79995554433"}
        };
    }

    @Test
    public void orderScooterFromTopButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickTopOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderForm(name, lastName, address, phone);

        assertThat(orderPage.getModalWindowText(), containsString(placedOrderText));
    }

    @Test
    public void orderScooterFromMidButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickMidOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderForm(name, lastName, address, phone);

        assertThat(orderPage.getModalWindowText(), containsString(placedOrderText));
    }
}
