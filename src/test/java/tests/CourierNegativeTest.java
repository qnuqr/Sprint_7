package tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum_services.qa_scooter.model.Courier;
import ru.praktikum_services.qa_scooter.steps.CourierSteps;

import static org.hamcrest.CoreMatchers.is;

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
        courierSteps
                .login(courier)
                .statusCode(404)
                .body("message", is("Учетная запись не найдена"));
    }

    @Test
    public void loginCourierWithoutLogPswrd() {
        courier.setLogin(null);
        courier.setPassword(null);
        courierSteps
                .login(courier)
                .statusCode(400)
                .body("message", is("Недостаточно данных для входа"));
    }

    @Test
    public void createCourierWithoutLogin() {
        courier.setLogin(null);
        courierSteps
                .createCourier(courier)
                .statusCode(400)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    public void createCourierWithoutPassword() {
        courier.setPassword(null);
        courierSteps
                .createCourier(courier)
                .statusCode(400)
                .body("message", is("Недостаточно данных для создания учетной записи"));
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
                .body("message", is("Этот логин уже используется"));
    }

}
