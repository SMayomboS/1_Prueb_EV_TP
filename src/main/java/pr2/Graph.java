package pr2;

import org.testng.annotations.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Graph<V>{
    private final Map<V, Set<V>> adjacencyList = new HashMap<>();

    public boolean addVertex(V v){
        if (adjacencyList.containsKey(v)) {
            return false;
        }
        adjacencyList.put(v, new HashSet<>());
        return true;
    }

    public boolean addEdge(V v1, V v2){
        addVertex(v1);
        addVertex(v2);
        return adjacencyList.get(v1).add(v2) && adjacencyList.get(v2).add(v1);
    }

    public Set<V> obtainAdjacents(V v) throws Exception {
        if (!adjacencyList.containsKey(v)) {
            throw new Exception("Vértice no encontrado en el grafo");
        }
        return adjacencyList.get(v);
    }

    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var entry : adjacencyList.entrySet()) {
            sb.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    public List<V> onePath(V v1, V v2) {
        if (!adjacencyList.containsKey(v1) || !adjacencyList.containsKey(v2)) {
            return null;
        }

        Map<V, V> trace = new HashMap<>();
        Deque<V> open = new ArrayDeque<>();
        open.push(v1);
        trace.put(v1, null);
        boolean found = false;

        while (!open.isEmpty() && !found) {
            V v = open.pop();
            if (v.equals(v2)) {
                found = true;
            } else {
                for (V neighbor : adjacencyList.get(v)) {
                    if (!trace.containsKey(neighbor)) {
                        trace.put(neighbor, v);
                        open.push(neighbor);
                    }
                }
            }
        }

        if (found){
            List<V> path = new LinkedList<>();
            for (V at = v2; at != null; at = trace.get(at)){
                path.add(0, at);
            }
            return path;
        }

        return null;
    }

    @Test
    public void onePathFindsAPath(){
        System.out.println("\nTest onePathFindsAPath");
        System.out.println("----------------------");
        // Se construye el grafo.
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        g.addEdge(1, 5);
        g.addEdge(5, 6);
        g.addEdge(6, 4);
        // Se construye el camino esperado.
        List<Integer> expectedPath = new ArrayList<>();
        expectedPath.add(1);
        expectedPath.add(5);
        expectedPath.add(6);
        expectedPath.add(4);
        //Se comprueba si el camino devuelto es igual al esperado.
        assertEquals(expectedPath, g.onePath(1, 4));
    }
}
