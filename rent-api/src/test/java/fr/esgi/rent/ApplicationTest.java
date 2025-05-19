package fr.esgi.rent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@ImportAutoConfiguration(InfoEndpoint.class)
@SpringBootTest
class ApplicationTest {

    @Test
    void contextLoads() {
    }

}


