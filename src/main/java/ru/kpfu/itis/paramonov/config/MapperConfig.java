package ru.kpfu.itis.paramonov.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.kpfu.itis.paramonov.mappers.CommentModelMapper;
import ru.kpfu.itis.paramonov.mappers.PostModelMapper;
import ru.kpfu.itis.paramonov.mappers.UserModelMapper;

@Configuration
public class MapperConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public UserModelMapper userModelMapper() {
        return new UserModelMapper();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CommentModelMapper commentModelMapper(UserModelMapper userModelMapper) {
        return new CommentModelMapper(
                userModelMapper
        );
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PostModelMapper postModelMapper(CommentModelMapper commentModelMapper, UserModelMapper userModelMapper) {
        return new PostModelMapper(
                commentModelMapper,
                userModelMapper
        );
    }
}
