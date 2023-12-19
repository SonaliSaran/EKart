package com.example.eKart.entity;


import com.example.eKart.dto.ProductListDto;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="products")
public class ProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;
    private String productName;
    private String category;
    private double price;
    private String color;


    public  ProductList convertDtoToEntity(ProductListDto productListDto){
        ProductList productList =new ProductList();
        productList.setProductId(productListDto.getProductId());
        productList.setProductName(productListDto.getProductName());
        productList.setCategory(productListDto.getCategory());
        productList.setPrice(productListDto.getPrice());
        productList.setColor(productListDto.getColor());
        return productList;
    }
}
