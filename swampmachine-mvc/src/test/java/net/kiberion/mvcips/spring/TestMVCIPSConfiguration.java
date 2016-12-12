package net.kiberion.mvcips.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.kiberion.swampmachine.api.scripting.ScriptEntityFactory;
import net.kiberion.swampmachine.api.scripting.SwampBinding;
import net.kiberion.swampmachine.api.view.StateView;
import net.kiberion.swampmachine.mvcips.spring.CommonMVCIPSConfiguration;
import net.kiberion.swampmachine.mvcips.states.GameState;
import net.kiberion.swampmachine.mvcips.states.annotations.LoadingState;
import net.kiberion.swampmachine.mvcips.states.annotations.NewGameState;
import net.kiberion.swampmachine.mvcips.states.annotations.StartingState;
import net.kiberion.swampmachine.mvcips.states.annotations.State;
import net.kiberion.swampmachine.scripting.common.AbstractScriptParser;

@Configuration
@Import({ CommonMVCIPSConfiguration.class })
public class TestMVCIPSConfiguration {

    public static final String LOADING_STATE_ID = "loading";
    public static final String STARTING_STATE_ID = "starting";
    public static final String NEWGAME_STATE_ID = "newgame";
    public static final String FOURTH_STATE_ID = "4";

    @Bean
    public GameState state1() {
        return new TestLoadingState();
    }

    @Bean
    public GameState state2() {
        return new TestStartingState();
    }

    @Bean
    public GameState state3() {
        return new TestNewGameState();
    }

    @Bean
    public GameState state4() {
        return new ThirdState();
    }

    @Bean
    public ScriptEntityFactory scriptEntityFactory() {
        return new ScriptEntityFactory() {
            @Override
            public SwampBinding getBindingInstance() {
                return null;
            }

            @Override
            public AbstractScriptParser getParserInstance() {
                return null;
            }
        };
    }

    ///////////////
    // Test states//
    ///////////////

    @State(id = FOURTH_STATE_ID)
    public static class ThirdState extends GameState {

        @Override
        public StateView getView() {
            return null;
        }

    }

    @LoadingState
    @State(id = LOADING_STATE_ID)
    public static class TestLoadingState extends GameState {

        @Override
        public StateView getView() {
            return null;
        }

    }

    @StartingState
    @State(id = STARTING_STATE_ID)
    public static class TestStartingState extends GameState {

        @Override
        public StateView getView() {
            return null;
        }

    }

    @NewGameState
    @State(id = NEWGAME_STATE_ID)
    public static class TestNewGameState extends GameState {

        @Override
        public StateView getView() {
            return null;
        }

    }

}
