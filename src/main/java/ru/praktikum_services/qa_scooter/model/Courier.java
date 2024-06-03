package ru.praktikum_services.qa_scooter.model;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Courier {
    private Integer id;
    private String login;
    private String password;
    private String firstName;

}
