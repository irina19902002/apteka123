package ru.apteka.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
public class WebTest {
    @BeforeAll
    static void setUp() {
        Configuration.remote = "http://localhost:4444/wd/hub";
    }
}
