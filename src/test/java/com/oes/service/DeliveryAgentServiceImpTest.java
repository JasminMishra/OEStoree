package com.oes.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.oes.entity.DeliveryAgent;
import com.oes.repository.DeliveryAgentRepository;

@SpringBootTest
class DeliveryAgentServiceImpTest {
	
	@Mock
	DeliveryAgentRepository  deliveryAgentRepository;
	
	@InjectMocks
	DeliveryAgentServiceImp deliveryAgentService;
	
	@Test
	@DisplayName("Test - to verify the insert operation")
	//@Disabled
	void testAddDeliveryAgent() {
		
		
		
			// given
			DeliveryAgent sampleInput = new DeliveryAgent("pending","COD","Jasmin");
			DeliveryAgent expectedOutput = new DeliveryAgent( "shipped","DebitCard", "ram");
					//Object obj = new Object();
			
			BDDMockito.given(deliveryAgentService.addDeliveryAgent(sampleInput)).willReturn(expectedOutput);	
					// when 
					DeliveryAgent actualOutput = deliveryAgentService.addDeliveryAgent(sampleInput);
					
					// verify 
					assertEquals(expectedOutput, actualOutput);
				}
	

	@Test
	@Disabled
	void testGetAllDeliveryAgentbyUser() {
		fail("Not yet implemented");
	}

}
