package com.oes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oes.dto.ErrorDTO;
import com.oes.dto.MyDTO;
import com.oes.dto.UserDeliveryAgentCreatedResponseDTO;
import com.oes.entity.User;
import com.oes.entity.DeliveryAgent;
import com.oes.entity.OnlineOrder;
import com.oes.entity.Product;
import com.oes.service.UserService;
import com.oes.service.DeliveryAgentService;
import com.oes.service.OnlineOrderService;
import com.oes.service.ProfileService;
import com.oes.util.UserDTOConvertor;

@RestController
@RequestMapping("/es/deliveryAgent")
public class DeliveryAgentRestController {
	@Autowired
	DeliveryAgentService deliveryAgentService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OnlineOrderService onlineOrderService;
	
	@Autowired
	ProfileService profileService;
	

	//http://localhost:8888/es/deliveryAgent/user/jasmin/deliveryAgent/shipped/cash on delivery
	@PostMapping("/user/{username}/deliveryAgent/{deliveryStatus}/{payment}")
	public ResponseEntity<MyDTO> addDeliveryAgentByUser(@PathVariable String username,@PathVariable String deliveryStatus,@PathVariable String payment)
	{
		
		User savedUser = null; 
		try {
		    savedUser = userService.getUserByUserName(username);
			if(savedUser != null)
			{
				DeliveryAgent deliveryAgent = new DeliveryAgent(deliveryStatus, payment,username);
			//	Review post = new Review(description, LocalDate.now().toString(), 0, 0);
				DeliveryAgent savedDeliveryAgent = deliveryAgentService.addDeliveryAgent(deliveryAgent);
				
				if(savedDeliveryAgent.getDeliveryAgentId() != 0)
				{
					// code to link post with user
					User updatedUserWithDeliveryAgent = userService.addDeliveryAgent(savedDeliveryAgent, savedUser);
					
					UserDeliveryAgentCreatedResponseDTO dto = UserDTOConvertor.getDeliveryAgentCreatedDTO(updatedUserWithDeliveryAgent,deliveryAgent);
					
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
	
//	http://localhost:8888/es/deliveryAgent/OnlineOrders
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
	
	@GetMapping("/sort/OnlineOrders")
	public 
	List<OnlineOrder> sortOnlineOrderbyOrderDate()throws Exception
	{
		
		return onlineOrderService.sortOnlineOrderbyOrderDate();
	}

	
}