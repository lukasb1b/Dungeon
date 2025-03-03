package core.level.generator.postGeneration;

import core.level.TileLevel;
import core.level.elements.ILevel;
import core.level.generator.IGenerator;
import core.level.utils.DesignLabel;
import core.level.utils.LevelElement;
import core.level.utils.LevelSize;

/**
 * Generator to generate Walls and Holes on a pre-generated level layout Works on every layout with
 * only SKIP and FLOOR LevelElements.
 *
 * <p>Adds walls at the outer border of a level and at places of at least 2x2 SKIP tiles to
 * guarantee "good-looking" walls.
 *
 * <p>Replaces SKIP tiles with holes when they are next to a FLOOR tile and there is no space for a
 * wall.
 */
public class WallGenerator implements IGenerator {
    private final IGenerator preGenerator;
    private LevelElement[][] layout;

    /**
     * Constructs a new WallGenerator using the layout from the passed IGenerator.
     *
     * @param precedingGenerator generator used to generate layout beforehand
     */
    public WallGenerator(IGenerator precedingGenerator) {
        this.preGenerator = precedingGenerator;
    }

    @Override
    public ILevel level(DesignLabel designLabel, LevelSize size) {
        runPreGeneration(size);
        placeWalls();
        return new TileLevel(layout, designLabel);
    }

    @Override
    public LevelElement[][] layout(LevelSize size) {
        runPreGeneration(size);
        placeWalls();
        return layout;
    }

    private void runPreGeneration(LevelSize size) {
        LevelElement[][] preLayout = preGenerator.layout(size);
        // Surround layout with 2 layers of LevelElement.SKIP
        this.layout = new LevelElement[preLayout.length + 4][preLayout[0].length + 4];
        for (int i = 0; i < this.layout.length; i++) {
            for (int j = 0; j < this.layout[0].length; j++) {
                this.layout[i][j] = LevelElement.SKIP;
            }
        }
        for (int i = 2; i < this.layout.length - 2; i++) {
            if (this.layout[0].length - 4 >= 0)
                System.arraycopy(preLayout[i - 2], 0, this.layout[i], 2, this.layout[0].length - 4);
        }
    }

    private void placeWalls() {
        for (int y = 1; y < layout.length - 1; y++) {
            for (int x = 1; x < layout[0].length - 1; x++) {
                if (layout[y][x] == LevelElement.SKIP && accessibleTileIsAdjacent(y, x)) {
                    if (bottomLeftCornerIsEmpty(y, x)) {
                        layout[y][x] = LevelElement.WALL;
                    } else if (upperLeftCornerIsEmpty(y, x)) {
                        layout[y][x] = LevelElement.WALL;
                    } else if (bottomRightCornerIsEmpty(y, x)) {
                        layout[y][x] = LevelElement.WALL;
                    } else if (upperRightCornerIsEmpty(y, x)) {
                        layout[y][x] = LevelElement.WALL;
                    } else {
                        layout[y][x] = LevelElement.HOLE;
                    }
                }
            }
        }
    }

    private boolean upperRightCornerIsEmpty(int y, int x) {
        return !layout[y + 1][x].value()
                && !layout[y][x + 1].value()
                && !layout[y + 1][x + 1].value();
    }

    private boolean bottomRightCornerIsEmpty(int y, int x) {
        return !layout[y - 1][x].value()
                && !layout[y - 1][x + 1].value()
                && !layout[y][x + 1].value();
    }

    private boolean upperLeftCornerIsEmpty(int y, int x) {
        return !layout[y][x - 1].value()
                && !layout[y + 1][x - 1].value()
                && !layout[y + 1][x].value();
    }

    private boolean bottomLeftCornerIsEmpty(int y, int x) {
        return !layout[y - 1][x - 1].value()
                && !layout[y][x - 1].value()
                && !layout[y - 1][x].value();
    }

    /**
     * Checks if at least one accessible tile is nearby.
     *
     * @return true if at least one tile (including corner tiles) surrounding this tile is
     *     accessible
     */
    private boolean accessibleTileIsAdjacent(int y, int x) {
        if (layout[y - 1][x - 1].value()) return true;
        if (layout[y - 1][x].value()) return true;
        if (layout[y - 1][x + 1].value()) return true;
        if (layout[y][x - 1].value()) return true;
        if (layout[y][x + 1].value()) return true;
        if (layout[y + 1][x - 1].value()) return true;
        if (layout[y + 1][x].value()) return true;
        return layout[y + 1][x + 1].value();
    }
}
