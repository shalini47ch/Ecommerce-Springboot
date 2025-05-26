package com.example.EcomApp.repository;

import com.example.EcomApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository <Product,Integer>{
}
