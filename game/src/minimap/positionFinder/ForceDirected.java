package minimap.positionFinder;

import graph.Edge;
import graph.Graph;
import graph.Node;
import tools.Point;

import java.util.Arrays;
import java.util.Iterator;

public abstract class ForceDirected<T> implements IPositionFinder<T> {
    int K;
    float sigma;

    @Override
    public Point[] findPositions(Graph<T> graph, Point[] init) {
        Point[] nextIt = new Point[init.length];
        for (int i = 0; i < nextIt.length; i++) {
            nextIt[i] = new Point(0,0);
        }
        int t = 1;
        while (t < K) {
            // iterate

            // prepare all repells and attracts
            for (Iterator<Node<T>> it = graph.getNodeIterator(); it.hasNext(); ) {
                Node<T> u = it.next();

                nextIt[u.getIdx()].x = 0;
                nextIt[u.getIdx()].y = 0;
                // all repell
                for (Iterator<Node<T>> it2 = graph.getNodeIterator(); it2.hasNext(); ) {
                    Node<T> v = it2.next();
                    Point repul = repulsion(init[u.getIdx()], init[v.getIdx()]);
                    nextIt[u.getIdx()].x += repul.x;
                    nextIt[u.getIdx()].y += repul.y;
                }
                // all edges attract
                for (Iterator<Edge> it2 = u.edgeIterator(); it2.hasNext(); ) {
                    Edge v = it2.next();
                    Point attr = attract(init[u.getIdx()],init[v.getEndNode().getIdx()]);
                    nextIt[u.getIdx()].x += attr.x;
                    nextIt[u.getIdx()].y += attr.y;

                }
            }
            // update all Positions
            for (Iterator<Node<T>> it = graph.getNodeIterator(); it.hasNext(); ) {
                Node<T> node = it.next();
                init[node.getIdx()].x += sigma * nextIt[node.getIdx()].x;
                init[node.getIdx()].y += sigma * nextIt[node.getIdx()].y;

            }
        }


        return init;
    }

    @Override
    public Point[] findPositions(Graph<T> graph, Point[] init, int[] fixed) {
        Point[] nextIt = new Point[init.length];
        for (int i = 0; i < nextIt.length; i++) {
            nextIt[i] = new Point(0,0);
        }
        int t = 1;
        while (t < K) {
            // iterate

            // prepare all repells and attracts
            for (Iterator<Node<T>> it = graph.getNodeIterator(); it.hasNext(); ) {
                Node<T> u = it.next();

                nextIt[u.getIdx()].x = 0;
                nextIt[u.getIdx()].y = 0;
                // all repell
                for (Iterator<Node<T>> it2 = graph.getNodeIterator(); it2.hasNext(); ) {
                    Node<T> v = it2.next();
                    Point repul = repulsion(init[u.getIdx()], init[v.getIdx()]);
                    nextIt[u.getIdx()].x += repul.x;
                    nextIt[u.getIdx()].y += repul.y;
                }
                // all edges attract
                for (Iterator<Edge> it2 = u.edgeIterator(); it2.hasNext(); ) {
                    Edge v = it2.next();
                    Point attr = attract(init[u.getIdx()],init[v.getEndNode().getIdx()]);
                    nextIt[u.getIdx()].x += attr.x;
                    nextIt[u.getIdx()].y += attr.y;

                }
            }
            // update all Positions
            Iterator<Node<T>> it = graph.getNodeIterator();

            while (it.hasNext()) {
                Node<T> node = it.next();
                if(Arrays.stream(fixed).anyMatch(x-> x == node.getIdx()))
                    continue;
                init[node.getIdx()].x += sigma * nextIt[node.getIdx()].x;
                init[node.getIdx()].y += sigma * nextIt[node.getIdx()].y;

            }
        }


        return init;
    }

    public abstract Point attract(Point u, Point v);

    public abstract Point repulsion(Point u, Point v);
}
