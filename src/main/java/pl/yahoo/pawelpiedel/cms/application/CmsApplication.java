package pl.yahoo.pawelpiedel.cms.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"pl.yahoo.pawelpiedel.cms"})
@EntityScan(basePackages = {"pl.yahoo.pawelpiedel.cms.model"})
@EnableJpaRepositories("pl.yahoo.pawelpiedel.cms.repositories")
public class CmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
