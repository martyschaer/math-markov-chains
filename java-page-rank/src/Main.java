import java.util.ArrayList;
import java.util.List;


/**
 * PageRank implementation
 *
 * http://ilpubs.stanford.edu:8090/361/1/1998-8.pdf
 * http://www.cs.princeton.edu/~chazelle/courses/BIB/pagerank.htm
 */
public class Main {

    private static List<Page> pages;

    public static void main(String[] args) {
        pages = new ArrayList<>();
        Page a = new Page("A");
        Page b = new Page("B");
        Page c = new Page("C");
        Page d = new Page("D");

        a.addLink(b);
        a.addLink(c);
        b.addLink(c);
        c.addLink(a);
        d.addLink(c);

        pages.add(a);
        pages.add(b);
        pages.add(c);
        pages.add(d);


        for(int i = 0; i < 20; i++) {
            System.out.println("Iteration NR " + (i + 1));
            for(Page page: pages) {
                page.calculatePageRank(pages);
            }
        }
    }
}
