package com.example.EcomApp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

//we don't want to create getter so we will use lombok directly here
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    //here we will write all the details that we would need for a product
    @Id
    //so while inserting the data in the database we want the autoincrement option
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//this will be the primary key for the product
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date releaseDate;
    //now the next thing that needs to be kept in mind is that if the product is available or not
    private boolean available;
    //next for every product we need to know about the quantity for that product
    private int quantity;

    //now instead of storing the image on the cloud we will store it in the database
    private String imageName;
    private String imageType;

    //we will store the image data in the form of largeobject so use the annotation @LOB
    @Lob
    private byte[] imageData;


}
