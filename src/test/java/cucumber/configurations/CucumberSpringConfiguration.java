package cucumber.configurations;

import io.cucumber.spring.CucumberContextConfiguration;
import org.metsetmerveilles.MetsetmerveillesApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = MetsetmerveillesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberSpringConfiguration {
}
