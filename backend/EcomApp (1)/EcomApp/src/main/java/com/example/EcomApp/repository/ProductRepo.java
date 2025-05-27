package com.example.EcomApp.repository;

import com.example.EcomApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository <Product,Integer>{
    //since  there is no function inbuilt in jpa to search by specific name,description,brand we need to create one
    @Query("SELECT p from Product p WHERE "+"LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            +"LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            +"LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            +"LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchProducts(String keyword);


}
