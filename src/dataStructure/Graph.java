package dataStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<Vertex, List<Vertex>> adj;

    public Graph() {}

    public ArrayList<Integer> dfsArray = new ArrayList<>();

    public void addVertex(int value) {
        adj.putIfAbsent(new Vertex(value), new ArrayList<>());
    }

    public void addEdge(int v1, int v2) {
        adj.get(new Vertex(v1)).add(new Vertex(v2));
        adj.get(new Vertex(v2)).add(new Vertex(v1));
    }

    static class Vertex {
        public int value;
        public Vertex(int value) {
            this.value = value;
        }
    }
}