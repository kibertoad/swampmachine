package net.kiberion.swampmachine.mvcips.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.swampmachine.mvcips.states.util.StateManager;
import net.kiberion.swampmachine.mvcips.states.util.StateRegistry;

@Configuration
public class CommonMVCIPSConfiguration {

    @Bean
    public StateRegistry stateRegistry() {
        return new StateRegistry();
    }
    
    @Bean
    public StateManager stateManager() {
        return new StateManager();
    }
    
}
