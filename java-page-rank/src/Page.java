import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Severin Kaderli
 */
public class Page {
    /**
     * The damping factor that is used in the calculation of the page rank.
     */
    private static final double DAMPING_FACTOR = 0.75;

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
     * The page rank of this page.
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
        this.pageRank = 0;
    }

    /**
     * Add a link to another page.
     *
     * @param page The page that will be linked to
     */
    public void addLink(Page page) {
        // Check for duplicate pages and itself.
        if(this.links.contains(page) || this.equals(page)) {
            return;
        }

        this.links.add(page);
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
     * Check if a specific link exists.
     *
     * @param page The page that will be checked
     * @return True if a link to the page exists, false otherwise
     */
    public boolean hasLink(Page page) {
        return this.links.contains(page);
    }

    public void calculatePageRank(List<Page> pages) {
        // Get all pages who link to this page
        List<Page> linkingPages = new ArrayList<>();
        for(Page page: pages) {
            if(page.hasLink(this)) {
                linkingPages.add(page);
            }
        }

        // Calculate the unnormalized page rank
        double unnormalizedPageRank = 0;
        for(Page page : linkingPages) {
            unnormalizedPageRank += page.pageRank / page.getNumberOflinks();
        }

        // Calculate the real page rank
        double pageRank = 1 - DAMPING_FACTOR;
        pageRank += DAMPING_FACTOR * unnormalizedPageRank;
        this.pageRank = pageRank;

        // Some fancy output
        System.out.printf("PR(%s) = (0.15) + 0.85 * %f\n", this.title, unnormalizedPageRank);
        System.out.printf("PR(%s) = %f\n\n", this.title, this.pageRank);
    }
}
