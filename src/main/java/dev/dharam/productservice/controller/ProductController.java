package dev.dharam.productservice.controller;


import dev.dharam.productservice.client.authenticationClient.AuthenticationClient;
import dev.dharam.productservice.client.authenticationClient.dtos.Role;
import dev.dharam.productservice.client.authenticationClient.dtos.SessionStatus;
import dev.dharam.productservice.client.authenticationClient.dtos.ValidateTokenResponseDto;
import dev.dharam.productservice.dto.ProductRequestDto;
import dev.dharam.productservice.dto.ProductResponseDto;
import dev.dharam.productservice.exceptions.InvalidInputException;
import dev.dharam.productservice.service.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
//    @Qualifier("productService")
    @Qualifier("fakeStoreProductService")
    private ProductService productService;

    @Autowired
    private AuthenticationClient authenticationClient;

    //Make only admins to be able to access all products
    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(@Nullable @RequestHeader("AUTH_TOKEN") String token,
                                                                   @Nullable @RequestHeader("USER_ID") Long userId) {
        //@Nullable gives go-ahead even if parameter is null
        //Check if token exists
//        if(token == null || userId == null){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//
//        ValidateTokenResponseDto response = authenticationClient.validate(token, userId);
//        //Check if token is valid
//        if(!response.getSessionStatus().equals(SessionStatus.ACTIVE)){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//
//
//
//        //check if user has permissions
//        boolean isUserAdmin = false;
//
//        for(Role role: response.getUserDto().getRoles()){
//            if(role.getName().equals("admin")){
//                isUserAdmin = true;
//            }
//        }
//
//        if(!isUserAdmin){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }

        List<ProductResponseDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id") Long id){
        if(id == null){
            throw new InvalidInputException("Input is not correct!");
        }
        ProductResponseDto product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
    @PostMapping()
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto savedProduct = productService.createProduct(productRequestDto);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable("id") Long id,@RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto updatedProduct = productService.updateProduct(id,productRequestDto);
        return ResponseEntity.ok(updatedProduct);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id){
//        String response = productService.deleteProduct(id);
//        return ResponseEntity.ok(response);
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

}
