package cucumber.configurations;

import io.cucumber.spring.CucumberContextConfiguration;
import org.metsetmerveilles.MetsetmerveillesApplication;
import org.metsetmerveilles.config.FirebaseConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@CucumberContextConfiguration
@SpringBootTest(classes = MetsetmerveillesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class CucumberSpringConfiguration {
}
