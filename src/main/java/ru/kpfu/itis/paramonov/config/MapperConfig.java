package ru.kpfu.itis.paramonov.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.kpfu.itis.paramonov.mappers.UserModelMapper;

@Configuration
public class MapperConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public UserModelMapper userModelMapper() {
        return new UserModelMapper();
    }
}
