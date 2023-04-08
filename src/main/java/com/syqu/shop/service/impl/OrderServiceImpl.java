package com.syqu.shop.service.impl;

import com.syqu.shop.domain.ShopOrder;
import com.syqu.shop.repository.OrderRepository;
import com.syqu.shop.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void save(ShopOrder shopOrder) {
        orderRepository.save(shopOrder);
    }

    @Override
    public void edit(long id, ShopOrder newShopOrder) {
        orderRepository.save(newShopOrder);
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public ShopOrder findById(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<ShopOrder> findAllByOrderByIdAsc() {
        return orderRepository.findAllByOrderByIdAsc();
    }


    @Override
    public long count() {
        return orderRepository.count();
    }
}
