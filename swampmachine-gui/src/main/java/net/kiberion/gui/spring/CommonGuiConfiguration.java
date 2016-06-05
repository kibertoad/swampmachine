package net.kiberion.gui.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.gui.managers.GuiManager;
import net.kiberion.swampmachine.factories.InvokablesFactory;

@Configuration
public class CommonGuiConfiguration {

    @Bean
    public GuiManager guiManager() {
        return new GuiManager();
    }
    
    @Bean
    public InvokablesFactory invokablesFactory (){
        return new InvokablesFactory();
    }
    
}
