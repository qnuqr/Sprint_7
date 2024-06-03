package tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum_services.qa_scooter.model.Courier;
import ru.praktikum_services.qa_scooter.steps.CourierSteps;

import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierNegativeTest extends AbstractTest {
    private CourierSteps courierSteps = new CourierSteps();
    private Courier courier;

    @Before
    public void setUp() {
        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(10));
    }

    @Test
    public void loginCourierWithNonExistentData() {
        courier.setLogin("MmMmM1");
        courier.setPassword("456qwer");
        courierSteps
                .login(courier)
                .statusCode(404)
                .body("message", notNullValue());
    }

    @Test
    public void loginCourierWithoutLogPswrd() {
        courier.setLogin(null);
        courier.setPassword(null);
        courierSteps
                .login(courier)
                .statusCode(400)
                .body("message", notNullValue());
    }

    @Test
    public void createCourierWithoutLogin() {
        courier.setLogin(null);
        courierSteps
                .createCourier(courier)
                .statusCode(400)
                .body("message", notNullValue());
    }

    @Test
    public void createCourierWithoutPassword() {
        courier.setPassword(null);
        courierSteps
                .createCourier(courier)
                .statusCode(400)
                .body("message", notNullValue());
    }

    @Test
    public void createCourierWithDuplicateUsername() {
        courier.setLogin("adm3");
        courierSteps
                .createCourier(courier);
        courier.setLogin("adm3");
        courierSteps
                .createCourier(courier)
                .statusCode(409)
                .body("message", notNullValue());
    }

}
