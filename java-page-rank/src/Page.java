import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a single web page. It contains links to other pages
 * and also keeps track of backlinks to itself. Finally it can calculate its
 * own page rank.
 *
 * @author Severin Kaderli
 */
public class Page {
    /**
     * The initial PageRank of the pages.
     */
    private static final double INITIAL_PAGE_RANK = 0;

    /**
     * The damping factor that is used in the calculation of the PageRank.
     */
    private static final double DAMPING_FACTOR = 0.85;

    /**
     * The title of this page.
     */
    private String title;

    /**
     * This list contains all pages this page links to. It doesn't contain
     * links to itself or duplicate links.
     */
    private List<Page> links;

    /**
     * This list contains all pages which link to this page.
     */
    private List<Page> backlinks;

    /**
     * The current PageRank of this page.
     */
    double pageRank;

    /**
     * This creates a new page object with the given title.
     *
     * @param title The title of the page
     */
    public Page(String title) {
        this.title = title;
        this.links = new ArrayList<>();
        this.backlinks = new ArrayList<>();
        this.pageRank = INITIAL_PAGE_RANK;
    }

    /**
     * Add a link to another page. It also adds itself as a backlink
     * to the other page.
     *
     * @param page The page that will be linked to
     */
    public void addLink(Page page) {
        // Check for duplicate pages and itself.
        if(this.links.contains(page) || this.equals(page)) {
            return;
        }

        page.addBacklink(this);
        this.links.add(page);
    }

    /**
     * Add a backlink. This is only called in the addLink method to ensure
     * that we keep track of backlinks.
     *
     * @param page The page where the backlink comes from
     */
    private void addBacklink(Page page) {
        this.backlinks.add(page);
    }

    /**
     * Return the number of links.
     *
     * @return The number of links
     */
    public int getNumberOflinks() {
        return this.links.size();
    }

    /**
     * Return the current PageRank.
     *
     * @return The current PageRank
     */
    public double getPageRank() {
        return this.pageRank;
    }

    /**
     * Calculate the current page rank.
     */
    public void calculatePageRank() {
        // Calculate the unnormalized page rank
        double unnormalizedPageRank = 0;
        for(Page page : this.backlinks) {
            unnormalizedPageRank += page.pageRank / page.getNumberOflinks();
        }

        // Calculate the real page rank
        double pageRank = 1 - DAMPING_FACTOR;
        pageRank += DAMPING_FACTOR * unnormalizedPageRank;
        this.pageRank = pageRank;

        // Some fancy output
        System.out.printf("PR(%s) = (%.2f) + %.2f * %.2f\n", this.title, 1 - DAMPING_FACTOR, DAMPING_FACTOR, unnormalizedPageRank);
        System.out.printf("PR(%s) = %.2f\n\n", this.title, this.pageRank);
    }
}
