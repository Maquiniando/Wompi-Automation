package co.com.wompi.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "co.com.wompi.stepdefinitions",
        tags = "@exitoso_monto_valido",
        plugin = {"pretty", "html:target/cucumber-report.html"},
        monochrome = true
)
public class CucumberRunner {}
