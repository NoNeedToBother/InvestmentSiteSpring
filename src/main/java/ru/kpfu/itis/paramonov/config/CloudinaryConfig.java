package ru.kpfu.itis.paramonov.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:cloudinary.properties") //see public_cloudinary.properties
public class CloudinaryConfig implements EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("cloud_name", environment.getProperty("cloudinary.cloud_name"));
        configMap.put("api_key", environment.getProperty("cloudinary.api_key"));
        configMap.put("api_secret", environment.getProperty("cloudinary.api_secret"));
        return new Cloudinary(configMap);
    }
}
