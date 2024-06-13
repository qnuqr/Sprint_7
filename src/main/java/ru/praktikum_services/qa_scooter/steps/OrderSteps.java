package ru.praktikum_services.qa_scooter.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum_services.qa_scooter.model.Orders;

import static io.restassured.RestAssured.given;
import static ru.praktikum_services.qa_scooter.EndPoints.CANCEL_ORDER;
import static ru.praktikum_services.qa_scooter.EndPoints.ORDERS;

public class OrderSteps {

    @Step("отправка запроса создания заказа")
    public ValidatableResponse createOrder(Orders orders) {
        return given()
                .body(orders)
                .when()
                .post(ORDERS)
                .then();
    }

    @Step("получение списка заказа")
    public ValidatableResponse getOrder(Orders orders) {
        return given()
                .body(orders)
                .when()
                .get(ORDERS)
                .then();
    }


    @Step("отмена созданных заказов")
    public ValidatableResponse cancelOrder(Orders orders) {
        return given()
                .pathParam("track", orders.getTrack())
                .when()
                .put(CANCEL_ORDER)
                .then();
    }
}
