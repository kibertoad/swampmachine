package net.kiberion.swampmachine.gui.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.badlogic.gdx.Gdx;

import net.kiberion.swampmachine.api.elements.Label;
import net.kiberion.swampmachine.gui.composer.populators.GdxElementFactory;
import net.kiberion.swampmachine.gui.composer.populators.GdxPopulator;
import net.kiberion.swampmachine.gui.composer.transformers.BoundLabelTransformer;
import net.kiberion.swampmachine.gui.composer.transformers.ButtonTransformer;
import net.kiberion.swampmachine.gui.composer.transformers.ImageTransformer;
import net.kiberion.swampmachine.gui.composer.transformers.LabelTransformer;
import net.kiberion.swampmachine.gui.composer.transformers.ViewInfoTransformer;
import net.kiberion.swampmachine.gui.elements.SwampLabel;

@Configuration
public class MahlerGdxConfiguration {

    @Bean
    public GdxElementFactory elementFactory() {
        return new GdxElementFactory();
    }

    @Bean
    public GdxPopulator populator() {
        return new GdxPopulator();
    }

    @Bean
    public ImageTransformer imageTransformer() {
        return new ImageTransformer();
    }

    @Bean
    public ViewInfoTransformer viewInfoTransformer() {
        return new ViewInfoTransformer();
    }

    @Bean
    public ButtonTransformer buttonTransformer() {
        return new ButtonTransformer();
    }

    @Bean
    public BoundLabelTransformer boundLabelTransformer() {
        return new BoundLabelTransformer();
    }

    @Bean
    public LabelTransformer labelTransformer() {
        return new LabelTransformer();
    }

    //ToDo FixMe
    @Bean
    @Scope("prototype")
    public Label coordsLabel() {
        if (Gdx.app != null) {
            return new SwampLabel("Dummy");
        } else {
            return new Label() {

                @Override
                public void setText(CharSequence string) {
                }

                @Override
                public void setPosition(float x, float y) {
                }
            };
        }
    }

}
