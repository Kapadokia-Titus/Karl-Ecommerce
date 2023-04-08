package com.syqu.shop.service;

import com.syqu.shop.domain.ShopOrder;

import java.util.List;

public interface OrderService {

    void save(ShopOrder shopOrder);
    void edit(long id, ShopOrder newShopOrder);
    void delete(long id);
    ShopOrder findById(long id);
    List<ShopOrder> findAllByOrderByIdAsc();
    long count();
}
