package dev.dazevedo.home;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest()
class HomeApplicationTests {

    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> HomeApplication.main(new String[]{}), "Application context should load without throwing exceptions");
    }


}
