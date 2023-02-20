package com.oes.sorting;

import java.util.Comparator;

import com.oes.entity.OnlineOrder;

public class sortOnlineOrderbyHightoLow implements Comparator<OnlineOrder>{

	@Override
	public int compare(OnlineOrder o1, OnlineOrder o2) {
		// TODO Auto-generated method stub
		return o2.getOrderDate().compareTo(o1.getOrderDate());
	}
	
}