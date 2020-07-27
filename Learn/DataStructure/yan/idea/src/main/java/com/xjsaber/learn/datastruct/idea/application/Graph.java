package com.xjsaber.learn.datastruct.idea.application;

import java.util.LinkedList;

/**
 * @author xjsaber
 */
public class Graph {

    /**
     * 顶点的个数
     */
    private final int v;
    /**
     * 邻接表
     */
    private final LinkedList<Integer>[] adj;

    public Graph(int v){
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++){
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) {
        adj[s].add(t);
    }

    public void topoSortByDFS(){
        // 先构建逆邻接表，边s->t表示，s依赖于t，t先于s
        LinkedList<Integer>[] inverseAdj = new LinkedList[v];
        for (int i = 0; i < v; i++){
            inverseAdj[i] = new LinkedList<>();
        }
        for (int i = 0; i < v; i++){
            for (int j = 0; j < adj[i].size(); j++){
                // i->w
                int w = adj[i].get(j);
                // w->i
                inverseAdj[w].add(i);
            }
        }
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++){
            visited[i] = true;
            dfs(i, inverseAdj, visited);
        }
    }

    private void dfs(int vertex, LinkedList<Integer>[] inverseAdj, boolean[] visited){
        for (int i = 0; i < inverseAdj[vertex].size(); i++){
            int w = inverseAdj[vertex].get(i);
            if (visited[w] == true) continue;
            visited[w] = true;
            dfs(w, inverseAdj, visited);
            System.out.print("->" + vertex);
        }
    }

    public void topoSortByKahn(){
        int[] inDegree = new int[v];
        for (int i = 0; i < v; i++){
            for (int j = 0; j < adj[i].size(); j++){
                // i -> j
                int w = adj[i].get(j);
                inDegree[w]++;
            }
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; i++){
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()){
            int i = queue.remove();
            System.out.print("->" + i);
            for (int j = 0; j < adj[i].size(); j++){
                int k = adj[i].get(j);
                inDegree[k]--;
                if (inDegree[k] == 0) {
                    queue.add(k);
                }
            }
        }
    }
}
