package com.example.eKart.dto;

import com.example.eKart.entity.ProductList;
import com.sun.istack.NotNull;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class ProductListDto {
/*
  @NotNull // not null is for dto if we didnot pass the param itself then it will come fr not null validation
 e.g) {if the product name itself is not there then it is @NotNull}
      {"productName" : ""} this is @NotEmpty
      {"productName: : "   " } this is @NotBlank
 */


    private long productId;
    @NotNull
    private String productName;
    @NotNull
    private String category;
    @NotNull
    private double price;
    @NotNull
    private String color;

    public  static ProductListDto convertEntityToDto(ProductList productList){
        ProductListDto productListDto = new ProductListDto();
        productListDto.setProductId(productList.getProductId());
        productListDto.setProductName(productList.getProductName());
        productListDto.setCategory(productList.getCategory());
        productListDto.setPrice(productList.getPrice());
        productListDto.setColor(productList.getColor());
        return productListDto;
    }
}

