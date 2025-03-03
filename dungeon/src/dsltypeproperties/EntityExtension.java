package dsltypeproperties;

import core.Entity;
import core.components.DrawComponent;
import core.components.PositionComponent;
import core.components.VelocityComponent;

import semanticanalysis.types.DSLTypeProperty;
import semanticanalysis.types.IDSLTypeProperty;

/**
 * This class implements {@link IDSLTypeProperty} for the {@link Entity} class, in order to access
 * the Components of an entity from a DSL-program.
 */
public class EntityExtension {
    // private ctor, because this class should not be instantiated
    private EntityExtension() {}

    @DSLTypeProperty(name = "velocity_component", extendedType = Entity.class)
    public static class VelocityComponentProperty
            implements IDSLTypeProperty<Entity, VelocityComponent> {
        public static EntityExtension.VelocityComponentProperty instance =
                new EntityExtension.VelocityComponentProperty();

        private VelocityComponentProperty() {}

        @Override
        public void set(Entity instance, VelocityComponent valueToSet) {
            instance.removeComponent(VelocityComponent.class);
            instance.addComponent(valueToSet);
        }

        @Override
        public VelocityComponent get(Entity instance) {
            var optionalComponent = instance.fetch(VelocityComponent.class);
            return optionalComponent.orElse(null);
        }
    }

    @DSLTypeProperty(name = "position_component", extendedType = Entity.class)
    public static class PositionComponentProperty
            implements IDSLTypeProperty<Entity, PositionComponent> {
        public static PositionComponentProperty instance = new PositionComponentProperty();

        private PositionComponentProperty() {}

        @Override
        public void set(Entity instance, PositionComponent valueToSet) {
            instance.removeComponent(PositionComponent.class);
            instance.addComponent(valueToSet);
        }

        @Override
        public PositionComponent get(Entity instance) {
            var optionalComponent = instance.fetch(PositionComponent.class);
            return optionalComponent.orElse(null);
        }
    }

    @DSLTypeProperty(name = "draw_component", extendedType = Entity.class)
    public static class DrawComponentProperty implements IDSLTypeProperty<Entity, DrawComponent> {
        public static DrawComponentProperty instance = new DrawComponentProperty();

        private DrawComponentProperty() {}

        @Override
        public void set(Entity instance, DrawComponent valueToSet) {
            instance.removeComponent(DrawComponent.class);
            instance.addComponent(valueToSet);
        }

        @Override
        public DrawComponent get(Entity instance) {
            var optionalComponent = instance.fetch(DrawComponent.class);
            return optionalComponent.orElse(null);
        }
    }
}
