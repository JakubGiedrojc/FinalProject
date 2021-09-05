package com.example.demo.repositories;

import com.example.demo.entities.Order;
import com.example.demo.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Optional<Order>> findAllByUser(User user);

    Order getOrderById(int orderId);
}