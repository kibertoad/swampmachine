package net.kiberion.blueprints.common.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.blueprints.common.state.CommonMainMenuState;
import net.kiberion.blueprints.common.state.CommonMainMenuView;

@Configuration
public class CommonStatesConfiguration {

    @Bean
    public CommonMainMenuState mainMenuState() {
        return new CommonMainMenuState();
    }

    @Bean
    public CommonMainMenuView mainMenuView() {
        return new CommonMainMenuView();
    }
    
}
