package com.oes.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oes.entity.OnlineOrder;
import com.oes.entity.Review;
import com.oes.repository.OnlineOrderRepository;
import com.oes.repository.ReviewRepository;
import com.oes.repository.UserRepository;
import com.oes.sorting.sortOnlineOrderbyHightoLow;
@Service
public class OnlineOrderServiceImp implements OnlineOrderService{
	@Autowired
	OnlineOrderRepository orderRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public OnlineOrder addOrder(OnlineOrder order) {
		OnlineOrder savedOrder = orderRepository.save(order);
		return savedOrder;
	}

	@Override
	public List<OnlineOrder> getAllOrderbyUser(String username) throws Exception {
        User user = (User) userRepository.getUsersByUsername(username);
		
		
		return null;
	}

	

	@Override
	public List<OnlineOrder> sortOnlineOrderbyOrderDate() {
		List<OnlineOrder> list= orderRepository.findAll();
		List<OnlineOrder> OnlineOrder = new ArrayList<>();
		Collections.sort(list, new sortOnlineOrderbyHightoLow());
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public boolean deleteOnlineOrderById(int onId) throws Exception {
		userRepository.deleteById(onId);
		return true;
	}

	

	
	
	
}