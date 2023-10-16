package BreadthFirstSearch;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Map<Integer , List<Edge>> graph = new HashMap<>();
        addEdge(graph , 9 , 0 , 1);
        addEdge(graph , 0 , 11 , 1);
        addEdge(graph , 0 , 7 , 1);
        addEdge(graph , 10 , 9 , 1);
        addEdge(graph , 10 , 1 , 1);
        addEdge(graph , 1 , 8 , 1);
        addEdge(graph , 9 , 8 , 1);
        addEdge(graph , 12 , 8 , 1);
        addEdge(graph , 12 , 2 , 1);
        addEdge(graph , 2 , 3 , 1);
        addEdge(graph , 3 , 7 , 1);
        addEdge(graph , 3 , 4 , 1);
        addEdge(graph , 7 , 11 , 1);
        addEdge(graph , 7 , 6, 1);
        addEdge(graph , 6 , 5 , 1);
        bfs(graph , 0);
    }
    // входные параметры:
    // сам граф в виде списка смежности
    // начальная вершина, с которой делаем обход
    public static void bfs(Map<Integer, List<Edge>> graph , int start){
        boolean[] visited = new boolean[graph.size()]; // посещённые вершины
        Arrays.fill(visited , false);
        Queue<Integer> current = new LinkedList<>();
        current.offer(start); // добавили текущую вершину
        visited[start] = true; // уже посетили вершину start
        while(!current.isEmpty()){
            Integer vertex = current.poll(); // достали первую вершину в очереди
            System.out.println("На данный момент посещаем вершину - " + vertex);
            // теперь нужно узнать какие вершиныв соединены с данной
            List<Edge> currentVertex = graph.get(vertex);

            // пройдёмся по всем вершинам
            for (Edge edge : currentVertex) {
                if(!visited[edge.to]){
                    // значит вершина edge.to ещё не посещена
                    visited[edge.to] = true;
                    current.offer(edge.to);
                }
            }
        }
    }
    // описание ребра
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
    // метод добавления ребра в неориентированный граф
    public static void addEdge(Map<Integer , List<Edge>> graph , int from , int to , int cost){
        List<Edge> currentFrom = graph.computeIfAbsent(from, k -> new ArrayList<Edge>());
        currentFrom.add(new Edge(from , to , cost));

        List<Edge> currentTo = graph.computeIfAbsent(to, k -> new ArrayList<Edge>());
        currentTo.add(new Edge(to , from , cost));
    }
}
