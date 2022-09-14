package com.nnk.springboot;

import com.nnk.springboot.service.PasswordValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;


@SpringBootTest
public class PasswordEncodeTest {
    @ParameterizedTest(name = "#{index} - Run test with password = {0}")
    @ValueSource(strings = {"Password$Test123456"})
    public void passwordRegex(String password) {
        Assertions.assertTrue(PasswordValidator.isValid(password));
    }

    @ParameterizedTest(name = "#{index} - Run test with password = {0}")
    @ValueSource(strings = {"PasswordTest1"})
    public void passwordRegexInvalid(String password) {
        Assertions.assertFalse(PasswordValidator.isValid(password));
    }

    @Test
    public static Stream<String> passwordProvider() {
        return Stream.of(
                "HHHgggaaa°123",
                "passworD&456",
                "J<?@-z97",
                "0987654321+mnopQAR"
        );
    }

    @Test
    public static Stream<String> passwordProvideInvalid() {
        return Stream.of(
                "test1234",
                "toto",
                "&@!$-;<",
                "Password34",
                " ",
                ""
        );
    }

}
