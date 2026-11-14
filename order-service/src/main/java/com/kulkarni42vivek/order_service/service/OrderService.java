package com.kulkarni42vivek.order_service.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.kulkarni42vivek.order_service.model.InventoryResponse;
import com.kulkarni42vivek.order_service.model.Order;
import com.kulkarni42vivek.order_service.model.OrderDto;
import com.kulkarni42vivek.order_service.model.OrderLineItems;
import com.kulkarni42vivek.order_service.model.OrderLineItemsDto;
import com.kulkarni42vivek.order_service.repo.OrderRepo;

@Service
public class OrderService {
	
	private final OrderRepo orderRepo;
	private final WebClient.Builder webClient;
	
	OrderService(OrderRepo orderRepo ,  WebClient.Builder webClient){
		this.orderRepo = orderRepo;
		this.webClient = webClient;
	}
	

	public void createOrder(OrderDto orderDto) {
		
		List<OrderLineItems> list =  orderDto.getOrderLineItemsDto()
			.stream()
			.map(obj->this.mapToOrderLineItems(obj))
			.collect(Collectors.toList());
			
		
		
		Order order = Order.builder()
				.orderNumber(UUID.randomUUID().toString())
				.orderLineItems(list)
				.build();
		
		List<String> orderCodes = list.stream().map(obj-> obj.getOrderCode()).toList();
		
		InventoryResponse[] inventoryResponseList = this.webClient.build().get()
			.uri("http://inventory-service/api/inventory/test",
					uribuilder -> uribuilder.queryParam("orderCodes", orderCodes).build()
				)
			.retrieve()
			.bodyToMono(InventoryResponse[].class)
			.block();
		
		boolean result = Arrays.stream(inventoryResponseList).allMatch(InventoryResponse::getIsInStock);
		
		if(result) {
			orderRepo.save(order);
		}
		else {
			throw new IllegalArgumentException("Product is not available");
		}
		
	}
	
	private OrderLineItems mapToOrderLineItems(OrderLineItemsDto dto) {
		OrderLineItems order = new OrderLineItems();
		order.setOrderCode(dto.getOrderCode());
		order.setPrice(dto.getPrice());
		return order;
	}
}
