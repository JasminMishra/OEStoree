package com.oes.controller;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oes.dto.ErrorDTO;
import com.oes.dto.MyDTO;
import com.oes.dto.UserDefaultResponseDTO;
import com.oes.dto.UserOrderCreatedResponseDTO;
import com.oes.dto.UserReviewCreatedResponseDTO;
import com.oes.entity.User;
import com.oes.entity.OnlineOrder;
import com.oes.entity.Product;
import com.oes.entity.Review;
import com.oes.service.UserService;
import com.oes.service.OnlineOrderService;
import com.oes.service.ReviewService;
import com.oes.util.UserDTOConvertor;

@RestController
@RequestMapping("/es/onlineorder")
public class OnlineOrderRestController {

	@Autowired
	OnlineOrderService orderService;
	
	@Autowired
	UserService userService;
	
	//http://localhost:8888/es/onlineorder/user/sachi/onlineorder/8000/pending
	@PostMapping("/user/{username}/onlineorder/{totalOrderCost}/{status}/{productName}")
	public ResponseEntity<MyDTO> addOrderByUser(@PathVariable String username,@PathVariable int totalOrderCost,@PathVariable String status ,@PathVariable String productName)
	{
		
		User savedUser = null; 
		try {
		    savedUser = userService.getUserByUserName(username);
			if(savedUser != null)
			{
				OnlineOrder onlineorder = new OnlineOrder(totalOrderCost, LocalDate.now().toString(),status,username, productName);
			//	Review post = new Review(description, LocalDate.now().toString(), 0, 0);
				OnlineOrder savedOrder = orderService.addOrder(onlineorder);
				
				if(savedOrder.getOnId() != 0)
				{
					// code to link post with user
					User updatedUserWithOrder = userService.addOrder(savedOrder, savedUser);
					
					UserOrderCreatedResponseDTO dto = UserDTOConvertor.getOrderCreatedDTO(updatedUserWithOrder,onlineorder);
					
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

	//http://localhost:8888/es/onlineorder/OnlineOrders
	
	@GetMapping("/OnlineOrders")
	public List<OnlineOrder> getAllOrders()
	{

		try {
			List<OnlineOrder>  allExtractedUsers = userService.getAllOrders();
			
			return allExtractedUsers;
			
		} catch (Exception e) {
		
			System.out.println(e);
			
		}
		
		return null;
	}
	
	//http://localhost:8888/es/onlineorder/OnlineOrder/delete/11
	@DeleteMapping("OnlineOrder/delete/{onId}")
	public List<OnlineOrder> deleteOnlineOrderById(@PathVariable int onId) throws Exception{
		

		boolean status = userService.deleteOnlineOrderById(onId);
		
		if(status == true) {
			List<OnlineOrder> users = userService.getAllOrders();
			return users;
		}
		else return null;
	}
		
	
	@GetMapping("/sort/OnlineOrders")
	public 
	List<OnlineOrder> sortOnlineOrderbyOrderDate()throws Exception
	{
		
		return orderService.sortOnlineOrderbyOrderDate();
	}
	
	
	
	
	
	
	
}