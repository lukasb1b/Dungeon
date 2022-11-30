package minimap.positionFinder;

import graph.Graph;
import tools.Point;

public interface IPositionFinder<T> {
    Point[] findPositions(Graph<T> graph, Point[] init, int[] fixed);

    Point[] findPositions(Graph<T> graph, Point[] init);
}
