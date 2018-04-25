use std::marker::PhantomData;

pub struct Page<'a> {
	name: String,
	page_rank: f32,
	linked_pages: Vec<Page<'static>>,
	phantom: PhantomData<&'a i32>,
}

impl<'a> Page<'a> {
	pub fn new(name: String) -> Page<'static> {
		Page {
			name: name,
			page_rank: 1f32,
			phantom: PhantomData,
			linked_pages: Vec::new(),
		}
	}

	pub fn add_linked_page(&mut self, page: Page<'static>) {
		self.linked_pages.push(page);
	}

	pub fn get_name(&self) -> &String {
		&self.name
	}

	pub fn get_page_rank(&self) -> f32 {
		self.page_rank
	}
}
