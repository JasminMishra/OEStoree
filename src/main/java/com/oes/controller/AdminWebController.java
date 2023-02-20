package com.oes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oes.dto.MyDTO;
import com.oes.dto.UserDefaultResponseDTO;
import com.oes.entity.DeliveryAgent;
import com.oes.entity.Product;
import com.oes.entity.User;
import com.oes.service.UserService;
import com.oes.util.UserDTOConvertor;


@RestController
@RequestMapping("/admin")
public class AdminWebController {

	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers()
	{

		try {
			List<User>  allExtractedUsers = userService.getAllUsers();
			
			return allExtractedUsers;
			
		} catch (Exception e) {
		
			System.out.println(e);
			
		}
		
		return null;
	}
}