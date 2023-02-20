package com.oes.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oes.dto.ErrorDTO;
import com.oes.dto.MyDTO;
import com.oes.dto.UserDefaultResponseDTO;
import com.oes.dto.UserProductCreatedResponseDTO;
import com.oes.dto.UserReviewCreatedResponseDTO;
import com.oes.entity.User;
import com.oes.entity.OnlineOrder;
import com.oes.entity.Product;
import com.oes.entity.Review;
import com.oes.service.UserService;
import com.oes.service.ProductService;
import com.oes.service.ReviewService;
import com.oes.util.UserDTOConvertor;

@RestController
@RequestMapping("/es/product")
public class ProductRestController {

	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	//http://localhost:8888/es/product/user/ross/product/mobile
	
	@PostMapping("/user/{username}/product/{productName}")
	public ResponseEntity<MyDTO> addProductByUser(@PathVariable String username,@PathVariable String productName)
	{
		
		User savedUser = null; 
		try {
		    savedUser = userService.getUserByUserName(username);
			if(savedUser != null)
			{
				Product product = new Product(0,productName,0,null,null, LocalDate.now().toString());
				Product savedProduct = productService.addProduct(product);
				
				if(savedProduct.getProductId() != 0)
				{
					// code to link post with user
					User updatedUserWithProduct = userService.addProduct(savedProduct, savedUser);
					
					UserProductCreatedResponseDTO dto = UserDTOConvertor.getProductCreatedDTO(updatedUserWithProduct,product);
					
					return new ResponseEntity<MyDTO>(dto, HttpStatus.OK);
				}
				
			}
			else
			{
				throw new Exception("User not found "+savedUser+" for "+username);
			}
			
		} catch (Exception e) {
			System.out.println(savedUser+" is not");
			
			ErrorDTO errorDto = new ErrorDTO(e.getMessage());
			return new ResponseEntity<MyDTO>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		
		
		return null;
		
	}
//http://localhost:8888/es/product/delete/5
	@DeleteMapping("/delete/{productId}")
	public List<Product> deleteProductById(@PathVariable int productId) throws Exception{
		

		boolean status = userService.deleteProductById(productId);
		
		if(status == true) {
			List<Product> users = userService.getAllProducts();
			return users;
		}
		else return null;
	}
		
	@PutMapping("update")
	public User updateProducts(@RequestBody List<Product> allProducts,@RequestParam String username)
	{
		
		System.out.println(" \n\n ======= Inside Web Controller update products =====\n\n");
		System.out.println("1.  allProducts :-  "+allProducts);
		System.out.println(" username :-  "+username);
		
		
		
		User savedUser = null; 
		try {
			 savedUser = productService.getProductByUserName(username);
			if(savedUser != null)
			{
				User updatedUser = productService.addProduct(allProducts, savedUser);
				return updatedUser;
			}
			else
			{
				throw new Exception("User not found "+savedUser+" for "+username);
			}
			
		} catch (Exception e) {
			System.out.println(savedUser+" is not");
		}
		
		
		return null;
	}

	@GetMapping("/Products")
	public List<Product> getAllProducts()
	{

		try {
			List<Product>  allExtractedUsers = userService.getAllProducts();
			
			return allExtractedUsers;
			
		} catch (Exception e) {
		
			System.out.println(e);
			
		}
		
		return null;
	}
	
	
	

	
	
}