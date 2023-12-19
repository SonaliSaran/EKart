package com.example.eKart.serviceImpl;

import com.example.eKart.dto.ProductListDto;
import com.example.eKart.entity.ProductCart;
import com.example.eKart.entity.ProductList;
import com.example.eKart.repository.ProductCartRepository;
import com.example.eKart.repository.ProductListRepository;
import com.example.eKart.response.ResponseClass;
import com.example.eKart.response.StringConstants;
import com.example.eKart.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductListRepository productListRepository;
    @Autowired
    private ProductCartRepository productCartRepository;

    @Override
    public ResponseEntity<ResponseClass> addAllProductsList(ProductListDto productListDto, HttpServletRequest request) {

        ProductList list = new ProductList();
        list= productListRepository.findByProductId(productListDto.getProductId()).get();

        Optional<ProductList> productListValue = productListRepository.findByProductId(productListDto.getProductId());
        if (!productListValue.isPresent()) {
            ProductList productList1 = new ProductList();
            ProductList productList = productListRepository.save(productList1.convertDtoToEntity(productListDto));
            ResponseClass responseClass = new ResponseClass(HttpStatus.OK, StringConstants.ADD_PRODUCT_SUCCESS, productList);
            return new ResponseEntity<>(responseClass, HttpStatus.OK);
        } else {
            ResponseClass responseClass = new ResponseClass(HttpStatus.BAD_REQUEST, StringConstants.ADD_PRODUCT_ERROR);
            return new ResponseEntity<>(responseClass, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<ResponseClass> getAllProductList(HttpServletRequest request) {

        List<ProductList> productList = productListRepository.findAll();
        if (!productList.isEmpty()) {
            ResponseClass responseClass = new ResponseClass(HttpStatus.OK, StringConstants.GET_ALL_PRODUCTS, productList);
            return new ResponseEntity<>(responseClass, HttpStatus.OK);
        } else {
            ResponseClass responseClass = new ResponseClass(HttpStatus.BAD_REQUEST, StringConstants.GET_ALL_PRODUCTS_FAILURE);
            return new ResponseEntity<>(responseClass, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ResponseClass> getProductByCategory(String category,HttpServletRequest request) {

        List<ProductList> productList = productListRepository.findByCategories(category);
        List<ProductListDto> productListDtoList = new ArrayList<>();
        productList.forEach(productList1 ->{
            productListDtoList.add(ProductListDto.convertEntityToDto(productList1));

        });
        /*
        for(int i=0;i<productList.size();i++){
        Product productList1 = productList.get(i);
        productListDtoList.add(ProductListDto.convertEntityToDto(productList1));
        }
         */
        if(!productListDtoList.isEmpty()){
            ResponseClass responseClass = new ResponseClass(HttpStatus.OK, StringConstants.GET_ALL_PRODUCTS_BY_CATEGORY, productListDtoList);
            return new ResponseEntity<>(responseClass, HttpStatus.OK);
        }
        else {
            ResponseClass responseClass = new ResponseClass(HttpStatus.BAD_REQUEST, StringConstants.GET_ALL_PRODUCTS_BY_CATEGORY_FAILURE);
            return new ResponseEntity<>(responseClass, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<ResponseClass> getAllCategories(HttpServletRequest request) {
        List<String> productList = productListRepository.findAllCategories();
        ResponseClass responseClass = new ResponseClass(HttpStatus.OK,StringConstants.GET_ALL_CATEGORIES_SUCCESS,productList);
        return new ResponseEntity<>(responseClass,HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ResponseClass> addItemsToCart(ProductCart productCart,HttpServletRequest request) {
        ProductList productList = productListRepository.findByProductId(productCart.getProductId()).get();
        if(productList!=null){
            ProductCart productCart1 = productCartRepository.save(productCart);
            ResponseClass responseClass = new ResponseClass(HttpStatus.OK, StringConstants.ADD_PRODUCT_TO_CART_SUCCESS, productList);
            return new ResponseEntity<>(responseClass, HttpStatus.OK);
        } else {
            ResponseClass responseClass = new ResponseClass(HttpStatus.BAD_REQUEST, StringConstants.ADD_PRODUCT_TO_CART_ERROR);
            return new ResponseEntity<>(responseClass, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ResponseClass> cartProducts(Long cartId, HttpServletRequest request) {
        ProductCart productCart = productCartRepository.findByCartId(cartId);
        if(productCart!=null){
            ResponseClass responseClass = new ResponseClass(HttpStatus.OK, StringConstants.GET_PRODUCT_FROM_CART_SUCCESS, productCart);
            return new ResponseEntity<>(responseClass, HttpStatus.OK);

        }
        else {
            ResponseClass responseClass = new ResponseClass(HttpStatus.BAD_REQUEST, StringConstants.GET_PRODUCT_FROM_CART_ERROR);
            return new ResponseEntity<>(responseClass, HttpStatus.BAD_REQUEST);

        }
    }


}
