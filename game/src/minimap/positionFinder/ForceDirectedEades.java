package minimap.positionFinder;

import tools.Point;

public class ForceDirectedEades<T> extends ForceDirected<T>{
    float c_rep = 2;
    float c_spring = 1;
    float l = 1;

    @Override
    public Point attract(Point u, Point v) {
        return null;
    }

    @Override
    public Point repulsion(Point u, Point v) {
        return null;
    }
}
