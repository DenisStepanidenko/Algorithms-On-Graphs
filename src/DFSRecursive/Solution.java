package DFSRecursive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// алгоритм поиск в глубину
public class Solution {
    // будем реализовать через список смежности
    public static void main(String[] args) {
        // построим для примера граф
        Map<Integer , List<Edge>> graph = new HashMap<>();
        addEdge(graph , 0 , 1, 1);
        addEdge(graph , 0 , 2 , 1);
//        addEdge(graph , 0 , 1 , 6);
//        addEdge(graph , 1 , 2 , 7);
//        addEdge(graph , 2 , 3 , 10);
//        addEdge(graph , 3 , 1 , 4);
//        addEdge(graph , 1 , 4 , 6);
//        addEdge(graph , 4 , 5 , 12);
//        addEdge(graph , 0 , 7 , 9);
//        addEdge(graph , 0 , 6 , 10);
        boolean[] visited = new boolean[3];
        DFS(1 , visited , graph);
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
    public static void DFS(int at , boolean[] visited , Map<Integer , List<Edge>> graph){
        // так как алгоритм рекурсивны, то проверяем, были ли мы уже в этой вершине
        if(visited[at]) return;
        visited[at] = true;

        // мы находимся в данной вершине, значит нужно узнать список ребёр всех
        List<Edge> current = graph.get(at);
        if(current != null) {
            for (Edge edge : current) {
                if(!visited[edge.to]) {
                    // проходимся по всем ребрам далее
                    System.out.println("В данный момент мы находимся на ребре - " + edge.from + " " + edge.to + " " + edge.cost);
                    DFS(edge.to, visited, graph);
                }
            }
        }
    }

    // вспомогательный метод для создания самого графа
    public static void addEdge(Map<Integer , List<Edge>> graph , int from , int to , int cost){
        List<Edge> currentFrom = graph.computeIfAbsent(from, k -> new ArrayList<Edge>());
        currentFrom.add(new Edge(from , to , cost));

        List<Edge> currentTo = graph.computeIfAbsent(to, k -> new ArrayList<Edge>());
        currentTo.add(new Edge(to , from , cost));
    }
}
