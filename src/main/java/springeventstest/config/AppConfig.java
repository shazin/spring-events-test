package springeventstest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ RepoConfig.class })
@ComponentScan(basePackages = { "springeventstest.listener", "springeventstest.handler",
		"springeventstest.publisher", "springeventstest.service" })
public class AppConfig {

}
