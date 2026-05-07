package com.maiu.remnant_life.modules.auth.domain.repository;

import java.util.List;
import java.util.Optional;

import com.maiu.remnant_life.modules.auth.domain.model.Order;


public interface OrderRepository {
    List<Order> findAll();

    Optional<Order> findById(Long id);

    Order save(Order order);

}
