package com.kulkarni42vivek.order_service.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kulkarni42vivek.order_service.model.Order;
import com.kulkarni42vivek.order_service.model.OrderDto;
import com.kulkarni42vivek.order_service.model.OrderLineItems;
import com.kulkarni42vivek.order_service.model.OrderLineItemsDto;
import com.kulkarni42vivek.order_service.repo.OrderRepo;

@Service
public class OrderService {
	
	
	@Autowired
	OrderRepo orderRepo;

	public void createOrder(OrderDto orderDto) {
		
		List<OrderLineItems> list =  orderDto.getOrderLineItemsDto()
			.stream()
			.map(obj->this.mapToOrderLineItems(obj))
			.collect(Collectors.toList());
			
		
		
		Order order = Order.builder()
				.orderNumber(UUID.randomUUID().toString())
				.orderLineItems(list)
				.build();
		
		
		orderRepo.save(order);
	}
	
	private OrderLineItems mapToOrderLineItems(OrderLineItemsDto dto) {
		OrderLineItems order = new OrderLineItems();
		order.setOrderCode(dto.getOrderCode());
		order.setPrice(dto.getPrice());
		return order;
	}
}
