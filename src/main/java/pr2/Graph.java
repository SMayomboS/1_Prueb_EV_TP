package pr2;

import java.util.*;

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

        while (!open.isEmpty()){
            V v = open.pop();
            if (v.equals(v2)) {
                return reconstructPath(trace, v1, v2);
            }
            for (V neighbor : adjacencyList.get(v)){
                if (!trace.containsKey(neighbor)){
                    trace.put(neighbor, v);
                    open.push(neighbor);
                }
            }
        }

        return null;
    }

    private List<V> reconstructPath(Map<V, V> trace, V start, V end){
        List<V> path = new LinkedList<>();
        for(V at = end; at != null; at = trace.get(at)){
            path.add(0, at);
        }
        return path.get(0).equals(start) ? path : null;
    }
}
