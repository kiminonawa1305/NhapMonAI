package game_alphabeta_student;

public class Test {
    public static void main(String[] args) {
        Node n = new Node("n", 4);
        Node w = new Node("w", -3);
        Node x = new Node("x", -5);
        Node g = new Node("g", -5);
        Node h = new Node("h", 3);
        Node i = new Node("i", 8);
        Node p = new Node("p", 9);
        Node q = new Node("q", -6);
        Node r = new Node("r", 0);
        Node d = new Node("d", 0);
        Node s = new Node("s", 3);
        Node t = new Node("t", 5);
        Node l = new Node("l", 2);
        Node u = new Node("u", -7);
        Node v = new Node("v", -9);
        Node o = new Node("o");
        Node f = new Node("f");
        Node b = new Node("b");
        Node c = new Node("c");
        Node j = new Node("j");
        Node e = new Node("e");
        Node k = new Node("k");
        Node m = new Node("m");
        Node a = new Node("a");
        a.addChild(b);
        a.addChild(c);
        a.addChild(d);
        a.addChild(e);
        b.addChild(f);
        b.addChild(g);
        f.addChild(n);
        f.addChild(o);
        o.addChild(w);
        o.addChild(x);
        c.addChild(h);
        c.addChild(i);
        c.addChild(j);
        j.addChild(p);
        j.addChild(q);
        j.addChild(r);
        e.addChild(k);
        e.addChild(l);
        e.addChild(m);
        k.addChild(s);
        k.addChild(t);
        m.addChild(u);
        m.addChild(v);

        MiniMaxSearchAlgo miniMaxSearchAlgo = new MiniMaxSearchAlgo();
        miniMaxSearchAlgo.execute(a);
        System.out.println(a.getValue());
    }
}
