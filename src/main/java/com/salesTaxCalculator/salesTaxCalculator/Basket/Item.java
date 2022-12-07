package com.salesTaxCalculator.salesTaxCalculator.Basket;

public class Item {

    private Integer quantity;

    // defining two tax categories 1. being normal tax category with 10% and 2. being exemption of tax category
    private Integer taxCategory;

    private String name;

    private Double price;

    // defining boolean for import taxes of 5%
    private Boolean importing;

    public Item() {
    }

    public Item(Integer quantity,Integer taxCategory, String name, Double price, Boolean importing) {
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.taxCategory = taxCategory;
        this.importing = importing;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTaxCategory() {
        return taxCategory;
    }

    public void setTaxCategory(Integer taxCategory) {
        this.taxCategory = taxCategory;
    }

    public Boolean getImporting() {
        return importing;
    }

    public void setImporting(Boolean importing) {
        this.importing = importing;
    }
}
