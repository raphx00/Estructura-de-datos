package TURF.DIA_2;

import java.util.*;

public class CycleDetectionBFS {

    public boolean hasCycle(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V];
        Queue<int[]> queue = new LinkedList<>();
        
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (bfs(i, adj, visited, V)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean bfs(int start, List<List<Integer>> adj, boolean[] visited, int V) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, -1}); // El segundo valor (-1) es el "padre" de ese nodo
        
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int currentNode = node[0];
            int parentNode = node[1];
            
            visited[currentNode] = true;
            
            for (int neighbor : adj.get(currentNode)) {
                if (!visited[neighbor]) {
                    queue.offer(new int[]{neighbor, currentNode}); // Agregar vecino a la cola con el nodo actual como su "padre"
                } else if (neighbor != parentNode) {
                    // Si el vecino ya está visitado y no es el padre del nodo actual, entonces encontramos un ciclo
                    return true;
                }
            }
        }
        
        return false;
    }

    public static void main(String[] args) {
        CycleDetectionBFS solution = new CycleDetectionBFS();

        int V1 = 6;
        List<List<Integer>> adj1 = new ArrayList<>();
        adj1.add(Arrays.asList(1, 3));
        adj1.add(Arrays.asList(0, 2, 4));
        adj1.add(Arrays.asList(1, 5));
        adj1.add(Arrays.asList(0, 4));
        adj1.add(Arrays.asList(1, 3, 5));
        adj1.add(Arrays.asList(2, 4));
        System.out.println(solution.hasCycle(V1, adj1));  // Output: true

        int V2 = 4;
        List<List<Integer>> adj2 = new ArrayList<>();
        adj2.add(Arrays.asList(1, 2));
        adj2.add(Arrays.asList(0, 2));
        adj2.add(Arrays.asList(0, 1, 3));
        adj2.add(Arrays.asList(2));
        System.out.println(solution.hasCycle(V2, adj2));  // Output: false
    }
}
