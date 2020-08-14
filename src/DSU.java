import java.util.Arrays;

class Pair {
    int p, d;

    public Pair(int p, int d) {
        this.p = p;
        this.d = d;
    }

    public boolean equals(Pair b) {
        return this.p == b.p && this.d == b.d;
    }
}

public class DSU {
    Pair[] parent;
    int[] rank;

    public DSU() {
        parent = new Pair[100];
        rank = new int[100];
        Arrays.fill(rank, 0);
    }
    //makeSet

    public void makeSet(int n) {
        assert n < 100;
        assert this.parent[n] == null;
        parent[n] = new Pair(n, 0);
        rank[n] = 0;
    }

    //findSet
    public Pair findSet(int n) {
        if (n != parent[n].p) {
            int len = parent[n].d;
            parent[n] = findSet(parent[n].p);
            parent[n].d += len;
        }
        return parent[n];
    }

    //union
    public void union(int a, int b) {
        Pair p1 = findSet(a);
        Pair p2 = findSet(b);
        if (p1.equals(p2)) return;
        if (rank[p1.p] < rank[p1.p]) {
            union(p2.p, p1.p);
            return;
        }
        parent[p2.p] = new Pair(p1.p, 1);
        if (rank[p1.p] == rank[p2.p]) rank[p1.p]++;
    }
}
