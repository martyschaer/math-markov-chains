pub struct Page {
	name: String,
	page_rank: f32,
	linked_pages: Vec<Page>,
}

impl Page {
	pub fn new(name: String) -> Page {
		Page {
			name: name,
			page_rank: 1f32,
			linked_pages: Vec::new(),
		}
	}

	pub fn get_name(&self) -> &String {
		&self.name
	}

	pub fn get_page_rank(&self) -> f32 {
		self.page_rank
	}
}
