package net.kiberion.swampmachine.gui.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import net.kiberion.swampmachine.factories.InvokablesFactory;
import net.kiberion.swampmachine.gui.managers.GuiManager;

@Configuration
public class CommonGuiConfiguration {

    @Bean
    @Scope("prototype")
    public GuiManager guiManager() {
        return new GuiManager();
    }
    
    @Bean
    public InvokablesFactory invokablesFactory (){
        return new InvokablesFactory();
    }
    
}
