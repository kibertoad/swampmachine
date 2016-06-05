package net.kiberion.gui.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.gui.managers.GuiManager;

@Configuration
public class CommonGuiConfiguration {

    @Bean
    public GuiManager guiManager() {
        return new GuiManager();
    }
    
}
