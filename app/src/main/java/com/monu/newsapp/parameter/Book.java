package com.monu.newsapp.parameter;

import java.io.Serializable;

public class Book implements Serializable {

    private String Tittle;
    private String Authors;
    private String Image;
    private double Rating;
    private double Price;
    private Integer Pages;
    private String PreView;

    public Book(String tittle, String authors, String image, double rating,double price,Integer pages, String preView) {
        Tittle = tittle;
        Authors = authors;
        Image = image;
        Rating = rating;
        Price = price;
        Pages = pages;
        PreView = preView;
    }

    public String getTittle() {
        return Tittle;
    }

    public void setTittle(String tittle) {
        Tittle = tittle;
    }

    public String getAuthors() {
        return Authors;
    }

    public void setAuthors(String authors) {
        Authors = authors;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double rating) {
        Rating = rating;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public Integer getPages() {
        return Pages;
    }

    public void setPages(Integer pages) {
        Pages = pages;
    }

    public String getPreView() {
        return PreView;
    }

    public void setPreView(String preView) {
        PreView = preView;
    }
}

