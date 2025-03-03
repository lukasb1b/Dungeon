package contrib.components;

import static org.junit.Assert.*;

import core.Entity;
import core.Game;

import org.junit.Test;

public class XPComponentTest {

    /** Test if the xp component ist initialized with zero xp and level zero. */
    @Test
    public void testStartXP() {
        /* Prepare */
        Game.removeAllEntities();
        Entity entity = new Entity();
        XPComponent xpComponent = new XPComponent(null);
        entity.addComponent(xpComponent);

        /* Test */
        assertEquals(0, xpComponent.currentXP());
        assertEquals(0, xpComponent.characterLevel());
    }

    /** Test if xp is added correctly. */
    @Test
    public void testAddXPSingle() {
        /* Prepare */
        Game.removeAllEntities();
        Entity entity = new Entity();
        XPComponent xpComponent = new XPComponent(null);
        entity.addComponent(xpComponent);

        /* Test */
        xpComponent.addXP(10);
        assertEquals(10, xpComponent.currentXP());
    }

    /** Test if xp is added correctly. */
    @Test
    public void testAddXPMultiple() {
        /* Prepare */
        Game.removeAllEntities();
        Entity entity = new Entity();
        XPComponent xpComponent = new XPComponent(null);
        entity.addComponent(xpComponent);

        /* Test */
        xpComponent.addXP(10);
        xpComponent.addXP(10);
        assertEquals(20, xpComponent.currentXP());
    }

    /** Tests if getXPToNextLevel() returns correct value if not enough xp is added. */
    @Test
    public void testXPToNextLevelNonZero() {
        /* Prepare */
        Game.removeAllEntities();
        Entity entity = new Entity();
        XPComponent xpComponent = new XPComponent(null);
        entity.addComponent(xpComponent);

        /* Test */
        xpComponent.addXP(10);
        assertEquals(90, xpComponent.xpToNextCharacterLevel());
    }

    /** Tests if getXPToNextLevel() returns correct value if enough xp is added. */
    @Test
    public void testXPToNextLevelExact() {
        /* Prepare */
        Game.removeAllEntities();
        Entity entity = new Entity();
        XPComponent xpComponent = new XPComponent(null);
        entity.addComponent(xpComponent);

        /* Test */
        xpComponent.addXP(100);
        assertEquals(0, xpComponent.xpToNextCharacterLevel());
    }

    /** Tests if getXPToNextLevel() returns correct value. If more xp is added than needed. */
    @Test
    public void testXPToNextLevelMore() {
        /* Prepare */
        Game.removeAllEntities();
        Entity entity = new Entity();
        XPComponent xpComponent = new XPComponent(null);
        entity.addComponent(xpComponent);

        /* Test */
        xpComponent.addXP(120);
        assertEquals(-20, xpComponent.xpToNextCharacterLevel());
    }
}
