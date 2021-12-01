package com.ximedes.adventofcode.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;


@Configuration
@EnableScheduling
@ComponentScan("com.ximedes.adventofcode")
public class GeneralConfiguration {

    @Bean
    public Gson gson() {
        GsonBuilder builder = new GsonBuilder();

        com.fatboyindustrial.gsonjavatime.Converters.registerLocalDate(builder);
        com.fatboyindustrial.gsonjavatime.Converters.registerLocalDateTime(builder);

        return builder.create();
    }

    @Bean
    public TemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new Java8TimeDialect());
        engine.setTemplateResolver(templateResolver);
        return engine;
    }
}
