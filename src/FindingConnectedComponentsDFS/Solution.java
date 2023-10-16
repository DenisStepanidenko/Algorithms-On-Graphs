package FindingConnectedComponentsDFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        //а
        Map<Integer , List<Edge>> graph = new HashMap<>();
        Map<Integer , List<Integer>> connectivityComponents = new HashMap<>(); // здесь будут храниться найденные компоненты связности
        addEdge(graph , 0 , 1 , 20);
        addEdge(graph , 0 , 2 , 20);
        addEdge(graph , 1 , 2 , 20);

        addEdge(graph , 3 , 4 , 20);
        addEdge(graph , 4 , 5 , 20);
        addEdge(graph , 3 , 5 , 20);

        addEdge(graph , 6 , 7 , 20);
        addEdge(graph , 7 , 8 , 20);
        addEdge(graph , 6 , 8 , 20);

        addEdge(graph , 9 , 2 , 30);
        addEdge(graph , 2, 9 , 30);

        addEdge(graph , 10 , 5 , 20);
        addEdge(graph , 5 , 10 , 30);
        List<Boolean> visited = new ArrayList<>(11);
        for(int i =0; i < 11; i++){
            visited.add(false);
        }
        connectivityComponents = findConnectedComponents(graph , visited);
        for (Map.Entry<Integer, List<Integer>> entry : connectivityComponents.entrySet()) {
            System.out.println("------");
            System.out.println("Компонента связности - " + entry.getKey());
            List<Integer> allVertex = entry.getValue();
            for (Integer vertex : allVertex) {
                System.out.print(vertex + " ");
            }
            System.out.println("------");
        }
    }

    static public Map<Integer , List<Integer>> findConnectedComponents(Map<Integer , List<Edge>> graph , List<Boolean> visited){
        Map<Integer, List<Integer>> connectivityComponents = new HashMap<>();
        int count = 0; // компоненты связности будем итерировать с 1
        for(int i =0; i <visited.size(); i++){
            if(!visited.get(i)){
                count++;
                connectivityComponents.put(count , new ArrayList<>());
                dfs(graph , i , visited , count , connectivityComponents);
            }
        }
        return connectivityComponents;
    }

    private static void dfs(Map<Integer, List<Edge>> graph, int i , List<Boolean> visited , int count , Map<Integer, List<Integer>> connectivityComponents) {
        if(!visited.get(i)) {
            visited.set(i, true); // уже
            List<Integer> listVertex = connectivityComponents.get(count);
            listVertex.add(i);
            connectivityComponents.put(count, listVertex);
        }
        else{
            return;
        }
        // то есть добавили текущую вершину в текущую компоненту связности
        // нужно узнать вершины, которые соединены с текущей
        List<Edge> currentVertexEdges = graph.get(i);
        if(currentVertexEdges != null) {
            for (Edge x : currentVertexEdges){
                dfs(graph , x.to , visited , count , connectivityComponents);
            }
        }
    }

    // вспомогательный метод для графа
    static public void addEdge(Map<Integer , List<Edge>> graph , int from , int to , int cost){
        List<Edge> current = graph.computeIfAbsent(from , k-> new ArrayList<>());
        current.add(new Edge(from , to , cost));
    }
    static class Edge{
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
