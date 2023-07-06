class Pair{
    int first;
    int second;
    Pair(int first, int second){
        this.first = first;
        this.second = second;
    }
}

class Solution
{
    //Function to find the shortest distance of all the vertices
    //from the source vertex S.
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S)
    {
        PriorityQueue<Pair> pq = 
        new PriorityQueue<Pair>((x,y) -> (x.first -y.first));

        int[]dist = new int[V+1];
        int[]parent = new int[V+1];
        
        for(int i = 1; i <= V; i++){
            dist[i] = (int)(1e9);
            parent[i] = i;
        }
        dist[S] = 0;
        pq.add(new Pair(0,1));
        while(pq.size() !=0){
            Pair it = pq.peek();
            int node = it.second;
            int dis = it.first;
            pq.remove();
            
            for(Pair iter : adj.get(node)){
                int adjnode = iter.first;
                int ewt = iter.second;
                if(dis+ewt < dist[adjnode]){
                    dist[adjnode] = dis+ewt;
                    pq.add(new Pair(dis+ewt,adjnode));
                    parent[adjnode] = node;
                }
            }
            
        }
        List<Integer>path = new ArrayList<>();
        if(dist[V] == 1e9){
            path.add(-1);
            return path;
        }
        int node = V;
        while(parent[node] != node){
            path.add(node);
            node = parent[node];
        }
        path.add(1);
        
        Collections.reverse(path);
        // return path;
          return path.stream().mapToInt(i -> i).toArray();
    }
}
