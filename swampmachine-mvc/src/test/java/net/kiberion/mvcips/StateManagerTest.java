package net.kiberion.mvcips;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ContextConfiguration;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import net.kiberion.mvcips.spring.TestMVCIPSConfiguration;
import net.kiberion.swampmachine.mvcips.states.GameState;
import net.kiberion.swampmachine.mvcips.states.annotations.State;
import net.kiberion.swampmachine.mvcips.states.events.ChangeStateEvent;
import net.kiberion.swampmachine.mvcips.states.util.StateManager;
import net.kiberion.swampmachine.mvcips.states.util.StateRegistry;
import net.kiberion.swampmachine.mvcips.utils.StateSpringLoader;
import net.kiberion.swampmachine.spring.ContextBasedTest;

@ContextConfiguration(classes = TestMVCIPSConfiguration.class)
public class StateManagerTest extends ContextBasedTest {

    @Autowired
    private StateManager stateManager;

    @Autowired
    private StateRegistry stateRegistry;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private ApplicationContext context;

    private Game game = new DummyGame();

    @Test
    public void testStateTransition() {
        stateManager.setGame(game);
        assertStateId(null);
        StateSpringLoader.registerStatesFromContext(context, stateRegistry);
        assertEquals(3, stateRegistry.getStates().size());

        eventPublisher.publishEvent(new ChangeStateEvent(this, TestMVCIPSConfiguration.STARTING_STATE_ID));
        assertStateId(TestMVCIPSConfiguration.STARTING_STATE_ID);
    }

    private static class DummyGame extends Game {

        private Screen screen;

        @Override
        public void create() {
        }

        @Override
        public void setScreen(Screen screen) {
            this.screen = screen;
        }

        @Override
        public Screen getScreen() {
            return screen;
        }

    }

    private void assertStateId(String expectedId) {
        if (expectedId == null) {
            assertEquals(null, game.getScreen());
        } else {
            assertEquals(expectedId, ((GameState) game.getScreen()).getClass().getAnnotation(State.class).id());
        }
    }
}
