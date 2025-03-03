package contrib.utils.components.ai;

import static org.junit.Assert.*;

import contrib.components.HealthComponent;
import contrib.utils.components.ai.transition.SelfDefendTransition;

import core.Entity;
import core.utils.components.MissingComponentException;

import org.junit.Test;

import java.util.function.Function;

public class SelfDefendTransitionTest {

    /**
     * tests if the isInFight method returns false when the current HealthPoints of an entity are
     * equal to its max HealthPoints
     */
    @Test
    public void isInFightModeHealtpointsAreMax() {
        Entity entity = new Entity();
        HealthComponent hc = new HealthComponent();
        entity.addComponent(hc);
        hc.maximalHealthpoints(10);
        hc.currentHealthpoints(10);
        Function<Entity, Boolean> defend = new SelfDefendTransition();

        assertFalse(defend.apply(entity));
    }
    /**
     * tests if the isInFight method returns true when the current HealthPoints of an entity are
     * lower than its max HealthPoints
     */
    @Test
    public void isInFightModeHealthpointsAreLowerThenMax() {
        Entity entity = new Entity();
        HealthComponent hc = new HealthComponent();
        entity.addComponent(hc);
        hc.maximalHealthpoints(10);
        hc.currentHealthpoints(10);
        Function<Entity, Boolean> defend = new SelfDefendTransition();
        assertFalse(defend.apply(entity));
        hc.currentHealthpoints(9);
        assertTrue(defend.apply(entity));
    }

    /**
     * checks the thrown Exception when the required HealthComponent is missing in the provided
     * Entity
     */
    @Test
    public void isInFightModeHealthComponentMissing() {
        Entity entity = new Entity();
        Function<Entity, Boolean> defend = new SelfDefendTransition();
        MissingComponentException exception =
                assertThrows(MissingComponentException.class, () -> defend.apply(entity));
        assertTrue(exception.getMessage().contains(HealthComponent.class.getName()));
    }
}
