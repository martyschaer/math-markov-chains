import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is an example program to demonstrate the structure of the
 * PageRank algorithm by Larry Page.
 *
 * @author Severin Kaderli
 * @see <a href="https://ilpubs.stanford.edu:8090/361/1/1998-8.pdf">https://ilpubs.stanford.edu:8090/361/1/1998-8.pdf</a>
 * @see <a href="https://www.cs.princeton.edu/~chazelle/courses/BIB/pagerank.htm">https://www.cs.princeton.edu/~chazelle/courses/BIB/pagerank.htm</a>
 **/
public class Main {
    private static final int NUMBER_OF_ITERATIONS = 20;
    /**
     * This list keeps track of all the pages.
     */
    private static List<Page> pages;

    public static void main(String[] args) {
        // Add some pages
        pages = new ArrayList<>();
        Page a = new Page("A");
        Page b = new Page("B");
        Page c = new Page("C");
        Page d = new Page("D");
        pages.addAll(Arrays.asList(a, b, c, d));

        // Add links between the pages
        a.addLink(b);
        a.addLink(c);
        b.addLink(c);
        c.addLink(a);
        d.addLink(c);

        // Simulate the PageRank calculation
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            System.out.println("Iteration NR " + (i + 1));
            double pageRankSum = 0;
            for (Page page : pages) {
                page.calculatePageRank();
                pageRankSum += page.getPageRank();
            }

            System.out.printf("Sum of the PageRanks is: %.2f\n\n", pageRankSum);
        }
    }
}
