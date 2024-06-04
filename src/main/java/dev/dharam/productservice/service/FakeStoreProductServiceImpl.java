package dev.dharam.productservice.service;


import dev.dharam.productservice.client.FakeStoreClient;
import dev.dharam.productservice.dto.FakeStoreProductRequestDto;
import dev.dharam.productservice.dto.FakeStoreProductResponseDto;
import dev.dharam.productservice.dto.ProductRequestDto;
import dev.dharam.productservice.dto.ProductResponseDto;
import dev.dharam.productservice.entities.Product;
import dev.dharam.productservice.exceptions.NoProductPresentException;
import dev.dharam.productservice.exceptions.ProductNotFoundException;
import dev.dharam.productservice.mapper.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductServiceImpl {
    @Autowired
    private FakeStoreClient fakeStoreClient;


    public List<ProductResponseDto> getAllProducts() {
        List<FakeStoreProductResponseDto> fakeStoreProducts =fakeStoreClient.getAllProducts();
        if (fakeStoreProducts == null) {
            throw new NoProductPresentException("No products are found!");
        }

        List<ProductResponseDto> products = new ArrayList<>();
        for(FakeStoreProductResponseDto fakeStoreProduct: fakeStoreProducts){
            ProductResponseDto product = DtoMapper.convertFakeStoreResponseDtoToProductResponseDto(fakeStoreProduct);
            products.add(product);

        }
        return products;

    }



    public ProductResponseDto getProductById(int productId)throws ProductNotFoundException {
        FakeStoreProductResponseDto fakeStoreProduct =fakeStoreClient.getProductById(productId);
        if(fakeStoreProduct == null){
            throw new ProductNotFoundException("Product not found with id: "+productId);
        }

        ProductResponseDto product = DtoMapper.convertFakeStoreResponseDtoToProductResponseDto(fakeStoreProduct);
        return product;
    }



    public ProductResponseDto createProduct(ProductRequestDto product) {
        FakeStoreProductRequestDto fakeStoreProductRequestDto = DtoMapper.convertProductRequestDtoToFakeStoreProductRequestDto(product);

        FakeStoreProductResponseDto createdFakeStoreProduct = fakeStoreClient.createProduct(fakeStoreProductRequestDto);

        ProductResponseDto createdProduct = DtoMapper.convertFakeStoreResponseDtoToProductResponseDto(createdFakeStoreProduct);

        return createdProduct;
    }



    public Product updateProduct(Product updatedProduct, int productId) {
        return null;
    }



    public boolean deleteProduct(int productId) {
        return false;
    }
}
