package com.ecommerce.model;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
	private int stock;

    public Product(int id, String name, String description, double price, int stock, String imageUrl) {
    	this.id = id;
    	this.name = name;
        this.description = description;
        this.price = price;
        this.setStock(stock);
        this.imageUrl = imageUrl;
	}

	public Product(String name, String description, double price, int stock, String imageUrl) {
		this.name = name;
        this.description = description;
        this.price = price;
        this.setStock(stock);
        this.imageUrl = imageUrl;
        
	}

	public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}
