package com.simplilearn.sportyShoe;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

public class Item {

    private String category;
    private String name;
    private Double price;
    private Double discount;
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Adding this format the date 
    private Date date;
    private String id;


   /*  public Item(String category, String name, Double price, Double discount, Date date) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.date = date;
    } */

    public Item() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return this.discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    // to handle date formatting
    
    public String getFormatDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);   
    }
    
}
