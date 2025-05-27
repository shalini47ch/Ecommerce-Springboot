package com.example.EcomApp.controller;
import com.example.EcomApp.model.Product;
import com.example.EcomApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
    //instead of creating the object directly we will be using the autowired directly
    @Autowired
    ProductService service;

    @RequestMapping("/")
    public String greet(){
        return "Hello welcome to my homepage";
    }

    @GetMapping("/product")
    //here we will return the list of all the products
    //so inorder to return the status codes we will use ResponseEntity
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(),HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Product prod=service.getProductById(id);
        if(prod!=null){
            return new ResponseEntity<>(prod,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    //now here we will write for adding the product @RequestPart MultipartFile
    @PostMapping("/product")
    public ResponseEntity<?>addProduct(@RequestPart Product product,
                                       @RequestPart MultipartFile imageFile){
        try{
            Product prod=service.addProduct(product,imageFile);
            return new ResponseEntity<>(prod,HttpStatus.CREATED);

        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
    //here create a helper function to get the image by productId
    @GetMapping("product/{productid}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productid){
        //first get the product by the specific prodid
        Product prod=service.getProductById(productid);
        byte[] imageFile=prod.getImageData();
        return ResponseEntity.ok().contentType(MediaType.valueOf(prod.getImageType())).body(imageFile);


    }
    //here we need to create a helper function to perform the updation of products
    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart Product product,
                                                @RequestPart MultipartFile imageFile){
        Product prod1= null;
        try {
            prod1 = service.updateProduct(id,product,imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(prod1!=null){
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
        }
    }
    //now the last thing what we will do is to delete a product with a specific id
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        //here we will delete the product by id
        Product prod=service.getProductById(id);
        if(prod!=null){
            service.deleteProduct(id);
            return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Product not found ",HttpStatus.NOT_FOUND);
        }


    }
    @GetMapping("/product/search")
    public ResponseEntity<List<Product>>searchProducts(@RequestParam String keyword){
        List<Product>prod=service.searchProducts(keyword);
        //atlast return the product and the specific id
        return new ResponseEntity<>(prod,HttpStatus.OK);

    }


}
