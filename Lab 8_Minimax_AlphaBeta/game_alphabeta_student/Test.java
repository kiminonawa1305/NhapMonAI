package game_alphabeta_student;

public class Test {
    public static void main(String[] args) throws Exception {
        Node e1 = new Node("e1", 3);
        Node e2 = new Node("e2", 1);
        Node f1 = new Node("f1", 6);
        Node f2 = new Node("f2", 1);
        Node f3 = new Node("f3", 5);
        Node g1 = new Node("g1", 2);
        Node g2 = new Node("g2", 1);
        Node h1 = new Node("h1", 1);
        Node h2 = new Node("h2", 1);
        Node i1 = new Node("i1", 9);
        Node i2 = new Node("i2", 3);
        Node j1 = new Node("j1", 7);
        Node j2 = new Node("j2", 5);
        Node e = new Node("e");
        Node f = new Node("f");
        Node g = new Node("g");
        Node h = new Node("h");
        Node i = new Node("i");
        Node j = new Node("j");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node a = new Node("a");
        a.addChild(b);
        a.addChild(c);
        a.addChild(d);
        b.addChild(e);
        b.addChild(f);
        c.addChild(g);
        c.addChild(h);
        c.addChild(i);
        d.addChild(j);
        e.addChild(e1);
        e.addChild(e2);
        f.addChild(f1);
        f.addChild(f2);
        f.addChild(f3);
        g.addChild(g1);
        g.addChild(g2);
        h.addChild(h1);
        h.addChild(h2);
        i.addChild(i1);
        i.addChild(i2);
        j.addChild(j1);
        j.addChild(j2);
//        MiniMaxSearchAlgo miniMaxSearchAlgo = new MiniMaxSearchAlgo();
//        miniMaxSearchAlgo.execute(a);
//        System.out.println(a.getValue());

        AlphaBetaSearchAlgo alphaBetaSearchAlgo = new AlphaBetaSearchAlgo();
        System.out.println(alphaBetaSearchAlgo.executeExtend().getValue());
    }
}
