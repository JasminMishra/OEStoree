package com.oes.service;
import java.util.List;


import org.springframework.stereotype.Service;

import com.oes.entity.OnlineOrder;
import com.oes.entity.Review;


@Service
public interface OnlineOrderService {
	
public OnlineOrder addOrder(OnlineOrder order);
	
	public List<OnlineOrder> getAllOrderbyUser(String username)throws Exception;


	public List<OnlineOrder> sortOnlineOrderbyOrderDate()throws Exception;

	public boolean deleteOnlineOrderById(int onId)throws Exception;




}