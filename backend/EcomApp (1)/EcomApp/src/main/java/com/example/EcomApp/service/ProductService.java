package com.example.EcomApp.service;

import com.example.EcomApp.model.Product;
import com.example.EcomApp.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo repo;

    public List<Product> getAllProducts(){
        return repo.findAll();
    }
    public Product getProductById(int id) {
        return repo.findById(id).orElse(new Product());

    }
    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        //since we have declared the fields like imageName,imageType and imageData
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {

        //here write the logic for updateProduct
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);

    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }
     public List<Product> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }
}

