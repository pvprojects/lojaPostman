package br.com.pvprojects.loja;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class LojaApplication {

    private static final Logger log = LoggerFactory.getLogger(LojaApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LojaApplication.class,
                args);

        ConfigurableEnvironment environment = context.getEnvironment();
        String port = environment.getProperty("server.port");
        String name = environment.getProperty("spring.application.name");

        log.info("\n" +
                "| \n" +
                "| ------------------------------------------------------------\n" +
                "| Application " + name + " is running! \n" +
                "| Access URLs \n" +
                "| Local >> http://127.0.0.1: " + port + "\n" +
                "| ------------------------------------------------------------\n");
    }
}
