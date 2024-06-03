package tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum_services.qa_scooter.model.Courier;
import ru.praktikum_services.qa_scooter.steps.CourierSteps;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierTests extends AbstractTest {
    private CourierSteps courierSteps = new CourierSteps();
    private Courier courier;

    @Before
    public void setUp() {
        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(10));
    }

    @Test
    public void createCourier() {
        courierSteps
                .createCourier(courier)
                .statusCode(201)
                .body("ok", is(true));
    }

    @Test
    public void loginCourier() {
        courierSteps
                .createCourier(courier);
        courierSteps
                .login(courier)
                .statusCode(200)
                .body("id", notNullValue());
    }

    @After
    public void tearDown() {
        if (courier.getLogin() != null) {
            Integer id = courierSteps.login(courier)
                    .extract().body().path("id");
            courier.setId(id);
            courierSteps.delete(courier);
        }
    }

}