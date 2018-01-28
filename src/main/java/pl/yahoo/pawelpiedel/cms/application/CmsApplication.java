package pl.yahoo.pawelpiedel.cms.application;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.yahoo.pawelpiedel.cms.model.Post;
import pl.yahoo.pawelpiedel.cms.repositories.PostRepository;

import java.util.stream.Stream;

@SpringBootApplication(scanBasePackages = {"pl.yahoo.pawelpiedel.cms"})
@EntityScan(basePackages = {"pl.yahoo.pawelpiedel.cms.model"})
@EnableJpaRepositories("pl.yahoo.pawelpiedel.cms.repositories")
public class CmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }

    @Bean
    ApplicationRunner init(PostRepository repository) {
        return args -> {
            Stream.of("Android MVP", "Android MVVM", "Room - why and why not ?").forEach(name -> {
                Post car = new Post();
                car.setTitle(name);
                repository.save(car);
            });
            repository.findAll().forEach(System.out::println);
        };
    }
}
