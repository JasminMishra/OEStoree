package com.oes.service;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oes.entity.DeliveryAgent;
import com.oes.repository.DeliveryAgentRepository;
import com.oes.repository.UserRepository;
@Service
public class DeliveryAgentServiceImp implements DeliveryAgentService {
	@Autowired
	DeliveryAgentRepository deliveryAgentRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public DeliveryAgent addDeliveryAgent(DeliveryAgent deliveryAgent) {
		DeliveryAgent savedDeliveryAgent = deliveryAgentRepository.save(deliveryAgent);
		return savedDeliveryAgent;
	}
	

	@Override
	public List<DeliveryAgent> getAllDeliveryAgentbyUser(String username) throws Exception {
	User user = (User) userRepository.getUsersByUsername(username);
		
		
		return null;
	}


}