package com.example.demo.repositories;

import com.example.demo.entities.Order;
import com.example.demo.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saves_and_loads_order(){
        //given
        LocalDate date = LocalDate.now();
        User user = new User();
        user.setEmailAddress("emailaddress");
        userRepository.save(user);
        Order order = new Order();

        order.setStartDate(date);
        order.setUser(user);



        List<Optional<Order>> foundOrdersByUser = orderRepository.findAllByUser(user);
        Assertions.assertThat(foundOrdersByUser.isEmpty()).isTrue();

        //when

        orderRepository.save(order);


        foundOrdersByUser = orderRepository.findAllByUser(user);

        //then
        Assertions.assertThat(foundOrdersByUser.stream().findFirst().isPresent()).isTrue();
        Order foundOrder = foundOrdersByUser.get(0).get();
        Assertions.assertThat(foundOrder.getUser()).isEqualTo(user);
        Assertions.assertThat(foundOrder.getStartDate()).isEqualTo(date);
    }
}