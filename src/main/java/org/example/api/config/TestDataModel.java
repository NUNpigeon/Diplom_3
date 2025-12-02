package org.example.api.config;
import com.github.javafaker.Faker;
import org.example.api.models.User;


public class TestDataModel {
    private static final Faker faker = new Faker();

    public static User generateUser() {
        return User.builder()
                .email(faker.internet().emailAddress())
                .password(faker.internet().password(6, 12))
                .name(faker.name().firstName())
                .build();
    }

    public static String generateInvalidPassword() {
        return faker.lorem().characters(1, 5); // Пароль менее 6 символов
    }
}
