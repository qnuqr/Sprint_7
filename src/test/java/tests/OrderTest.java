package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import ru.praktikum_services.qa_scooter.model.Orders;
import ru.praktikum_services.qa_scooter.steps.OrderSteps;

import static org.hamcrest.CoreMatchers.notNullValue;
public class OrderTest extends AbstractTest {
    private OrderSteps orderSteps = new OrderSteps();
    private Orders orders;

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private Integer rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"Vvv", "Aa", "Address 5", "Metro 1", "+ 7 700 123 00 05", 4, "2024-06-01", "доставка 1", "GREY"},
                {"Uuu", "Bb", "Address 6", "Metro 2", "+ 7 700 123 00 06", 5, "2024-06-02", "доставка 2", new String[]{"BLACK", "GREY"}},
                {"Iii", "Cc", "Address 7", "Metro 3", "+ 7 700 123 00 07", 6, "2024-06-03", "доставка 3"}
        };
    }


    @Before
    public void setUp() {
        orders = new Orders();
        orders.setFirstName(firstName);
        orders.setLastName(lastName);
        orders.setAddress(address);
        orders.setMetroStation(metroStation);
        orders.setPhone(phone);
        orders.setRentTime(rentTime);
        orders.setDeliveryDate(deliveryDate);
        orders.setComment(comment);
        orders.setColor(color);
    }

    @Test
    public void createOrderTest() {
        orderSteps
                .createOrder(orders)
                .statusCode(201)
                .body("track", notNullValue());
    }

    @Test
    public void getOrderList() {
        orderSteps
                .getOrder(orders)
                .statusCode(200)
                .body("orders", notNullValue());
    }

    @After
    public void tearDown() {
        Integer track = orderSteps.createOrder(orders)
                .extract().body().path("track");
        orders.setTrack(track.toString());
        orderSteps.cancelOrder(orders);
    }

}