package ru.praktikum_services.qa_scooter.steps;

import io.qameta.allure.Step;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import io.qameta.allure.internal.shadowed.jackson.databind.node.ObjectNode;
import io.restassured.response.ValidatableResponse;
import ru.praktikum_services.qa_scooter.model.Courier;

import static io.restassured.RestAssured.given;
import static ru.praktikum_services.qa_scooter.EndPoints.*;

public class CourierSteps {

    @Step("отправка запроса для создания курьера")
    public ValidatableResponse createCourier(Courier courier) {
        return given()
                .body(courier)
                .when()
                .post(COURIER)
                .then();
    }

    @Step("Авторизация курьера в системе")
    public ValidatableResponse login(Courier courier) {
        return given()
                .body(courier)
                .when()
                .post(LOGIN)
                .then();
    }

    @Step("Удаление курьера")
    public ValidatableResponse delete(Courier courier) {
        return given()
                .pathParam("id", courier.getId())
                .when()
                .post(COURIER_DELETE)
                .then();
    }
}