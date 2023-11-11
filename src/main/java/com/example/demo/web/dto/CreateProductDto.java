package com.example.demo.web.dto;

public class CreateProductDto {
    private String name;
    private String price;
    private int rate;
    private int amount;

    public CreateProductDto() {};

    public CreateProductDto(String name, String price, int rate, int amount) {
        this.name = name;
        this.price = price;
        this.rate = rate;
        this.amount = amount;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getRate() {
        return this.rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
