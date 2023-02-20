package com.oes.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.oes.entity.Product;
import com.oes.entity.Profile;
import com.oes.entity.Review;
import com.oes.entity.User;


@Service
public interface ProductService {
	
public Product addProduct(Product product);
	
	public List<Product> getAllProductbyUser(String username)throws Exception;

	public List<Product> getAllProducts()throws Exception;

	public boolean deleteProductById(int productId)throws Exception;

	public User getProductByUserName(String username);

	public User addProduct(List<Product> allProducts, User savedUser);

	




}