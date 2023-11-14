package webtest.model;

import java.io.IOException;

public class MenuItem {
	
	private int id;
	private String name;
	private String description;
	private double price;
	private String imagePath;
	private String foodType;
	private boolean featured;

	public MenuItem() {
	}
	
	public MenuItem(int id, String name, String description, String foodType, double price, boolean featured, String imagePath) { // constructor if featured is present																							
		this.id = id;																				
		this.name = name;
		this.description = description;
		this.foodType = foodType;
		this.price = price;
		this.imagePath = imagePath;
		this.featured = featured;
	}

	
	public MenuItem(int id, String name, String description, String foodType, double price, String imagePath) { // constructor if featured is not present																							
		this.id = id;																				
		this.name = name;
		this.description = description;
		this.foodType = foodType;
		this.price = price;
		this.imagePath = imagePath;
		this.featured = false;
	}

	

	// <--------------------SETTERS AND
	// GETTERS---------------------------------------------------------------------------------------------->

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public boolean getFeatured() {
		return featured;
	}

	public void setFeatured(boolean featured) {
		this.featured = featured;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}

// Could potentially turn into an abstract class and model for FoodItem, BeverageItem, and SpecialItem depending on if future problems arise
