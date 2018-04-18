/**
 * Don't ask why I've done this but I did.
 */
use std::collections::HashMap;

/**
 * The initial page rank that is used when no
 * existing is found.
 */
const INITIAL_PAGE_RANK: f32 = 1.0;

/**
 * The damping factor.
 */
const DAMPING_FACTOR: f32 = 0.85;

// PageRank implementation
// http://ilpubs.stanford.edu:8090/361/1/1998-8.pdf
// http://www.cs.princeton.edu/~chazelle/courses/BIB/pagerank.htm
fn main() {
    // Create a hash map with the "links"
    let mut links = HashMap::new();
    links.insert("A", vec!["B", "C"]);
    links.insert("B", vec!["C"]);
    links.insert("C", vec!["A"]);
    links.insert("D", vec!["C"]);

    // This hash map keeps tracks of the current values for the page ranks
    let mut page_ranks = HashMap::new();

    for n in 1..26 {
        println!("ITERATION N°{}", n);
        for source in links.keys() {
            calculate_page_rank(source, &links, &mut page_ranks);
        }
    }
}

fn calculate_page_rank<'a>(
    key: &'a str,
    links: &HashMap<&str, Vec<&str>>,
    page_ranks: &mut HashMap<&'a str, f32>,
) -> f32 {
    // Get the linked pages
    let mut linked_pages: HashMap<&str, f32> = HashMap::new();
    for (source, destinations) in links {
        for destination in destinations {
            // Check if this page links to our page
            if destination == &key {
                linked_pages.insert(source, destinations.len() as f32);
            }
        }
    }

    // Calculate the unnormalized page rank
    let mut unnormalized_page_rank: f32 = 0f32;
    for (page, count) in linked_pages {
        // if there's no page rank yet, we use 0
        let mut page_rank: f32 = INITIAL_PAGE_RANK;

        // Get the current page_rank if there's one
        if page_ranks.contains_key(page) {
            page_rank = match page_ranks.get(page) {
                Some(v) => *v,
                None => {
                    panic!("Key doesn't exist!");
                }
            };
        }

        unnormalized_page_rank += page_rank / count;
    }

    let mut page_rank = 1.0 - DAMPING_FACTOR;
    page_rank += DAMPING_FACTOR * (unnormalized_page_rank);

    // Update the page rank
    if page_ranks.contains_key(&key) {
        *page_ranks.get_mut(key).unwrap() = page_rank;
    } else {
        page_ranks.insert(key, page_rank);
    }

    println!(
        "PR({}) = (0.15) + 0.85 * {:.2}",
        key, unnormalized_page_rank
    );
    println!("PR({}) = {:.2}\n", key, page_rank);

    page_rank
}
