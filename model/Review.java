package webtest.model;

public class Review {
	private String name;
	private String description;
	private String rating;

	private int id;
	String[] categories = { "1/5", "2/5", "3/5", "4/5", "5/5" };

	public Review() {
	}

	public Review(String name, String description, String rating) {
		this.name = name;
		this.description = description;
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
