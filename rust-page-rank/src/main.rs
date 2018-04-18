use std::collections::HashMap;

const INITIAL_PAGE_RANK: f32 = 40.0;

// PageRank implementation
// http://ilpubs.stanford.edu:8090/361/1/1998-8.pdf
// http://www.cs.princeton.edu/~chazelle/courses/BIB/pagerank.htm
fn main() {
    // The damping factor
    let damping_factor: f32 = 0.85;

    // Create a hash map with the "links"
    let mut links = HashMap::new();
    links.insert("A", vec!["B", "C"]);
    links.insert("B", vec!["C"]);
    links.insert("C", vec!["A"]);
    links.insert("D", vec!["C"]);
    //links.insert("C", vec![]);

    let mut page_ranks = HashMap::new();

    for n in 1..21 {
        println!("ITERATION NR {}", n);
        for source in links.keys() {
            calculate_page_rank(source, damping_factor, &links, &mut page_ranks);
        }
        println!("\n");
    }
}

fn calculate_page_rank<'a>(
    key: &'a str,
    damping_factor: f32,
    links: &HashMap<&str, Vec<&str>>,
    page_ranks: &mut HashMap<&'a str, f32>,
) -> f32 {
    // Get the linked pages
    let mut linked_pages = HashMap::new();
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

    let mut page_rank = 1.0 - damping_factor;
    page_rank += damping_factor * (unnormalized_page_rank);

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
