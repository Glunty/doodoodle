package fr.doodoodle.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

@SpringBootApplication
@EnableOAuth2Sso
public class DoodoodleServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoodoodleServerApplication.class, args);
    }

    /*
    * Factory bean that creates the com.mongodb.Mongo instance
    */
    public
    @Bean
    MongoClientFactoryBean mongo() {
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        mongo.setHost("localhost");
        mongo.setPort(27017);
        return mongo;
    }
}
