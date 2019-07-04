package com.uco.pilae.pilae;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


@EnableAutoConfiguration
//@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories
public class PilaeApplication {


    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Locale.setDefault(new Locale("es", "CO"));
    }

    public static void main(String[] args) {
        SpringApplication.run(PilaeApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }

    @Bean
    public ObjectWriter objectWriter(ObjectMapper objectMapper) {
        return objectMapper.writerWithDefaultPrettyPrinter();
    }

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper mapper = new ModelMapper();
        mapper.addConverter(this.dateToLongConverter());
        mapper.addConverter(this.longToDateConverter());
        return mapper;
    }

    @Bean
    public ObjectMapper jsonObjectMapper() {
        return new ObjectMapper();
    }

    private Converter<Date, Long> dateToLongConverter() {
        return context -> context.getSource().getTime();
    }

    private Converter<Long, Date> longToDateConverter() {
        return context -> {
            final Calendar calendar = Calendar.getInstance(Locale.getDefault());
            calendar.setTimeZone(TimeZone.getDefault());
            calendar.setTimeInMillis(context.getSource());
            return calendar.getTime();
        };
    }
}
