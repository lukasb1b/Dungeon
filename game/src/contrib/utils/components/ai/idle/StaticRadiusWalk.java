package contrib.utils.components.ai.idle;

import com.badlogic.gdx.ai.pfa.GraphPath;

import contrib.utils.components.ai.AIUtils;

import core.Entity;
import core.Game;
import core.components.PositionComponent;
import core.level.Tile;
import core.level.utils.Coordinate;
import core.level.utils.LevelUtils;
import core.utils.Point;
import core.utils.components.MissingComponentException;
import core.utils.position.Position;
import core.utils.position.Coordinate;

import java.util.function.Consumer;

public class StaticRadiusWalk implements Consumer<Entity> {
    private final float radius;
    private final int breakTime;
    private GraphPath<Tile> path;
    private int currentBreak = 0;
    private Position center;
    private Position currentPosition;
    private Position newEndTile;

    /**
     * Finds a point in the radius and then moves there. When the point has been reached, a new
     * point in the radius is searched for from the center.
     *
     * @param radius Radius in which a target point is to be searched for
     * @param breakTimeInSeconds how long to wait (in seconds) before searching a new goal
     */
    public StaticRadiusWalk(final float radius, final int breakTimeInSeconds) {
        this.radius = radius;
        this.breakTime = breakTimeInSeconds * Game.frameRate();
    }

    @Override
    public void accept(final Entity entity) {
        if (path == null || AIUtils.pathFinishedOrLeft(entity, path)) {
            if (center == null) {
                PositionComponent pc =
                        entity.fetch(PositionComponent.class)
                                .orElseThrow(
                                        () ->
                                                MissingComponentException.build(
                                                        entity, PositionComponent.class));
                center = pc.position();
            }

            if (currentBreak >= breakTime) {
                currentBreak = 0;
                PositionComponent pc2 =
                        entity.fetch(PositionComponent.class)
                                .orElseThrow(
                                        () ->
                                                MissingComponentException.build(
                                                        entity, PositionComponent.class));
                currentPosition = pc2.position();
                newEndTile =
                        LevelUtils.randomAccessibleTileCoordinateInRange(center, radius)
                                .map(Coordinate::point)
                                // center is the start position of the entity, so it must be
                                // accessible
                                .orElse(center.point());
                path = LevelUtils.calculatePath(currentPosition, newEndTile);
                accept(entity);
            }
            currentBreak++;

        } else AIUtils.move(entity, path);
    }
}
