package com.syqu.shop.repository;

import com.syqu.shop.domain.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<ShopOrder, Long> {

    ShopOrder findById(long id);
    List<ShopOrder> findAllByOrderByIdAsc();
}
