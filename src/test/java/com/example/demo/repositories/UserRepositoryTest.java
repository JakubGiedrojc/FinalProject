package com.example.demo.repositories;

import com.example.demo.entities.Address;
import com.example.demo.entities.Order;
import com.example.demo.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void clear() {
        userRepository.deleteAll();
    }

    @Test
    public void saves_and_get_users() {
        //given
        String login = "test";
        String password = "test";
        String email = "test@test.com";


        User user = new User();

        user.setLogin(login);
        user.setPassword(password);
        user.setEmailAddress(email);

        Address address1 = new Address();
        Address address2 = new Address();

        address1.setCity("Warszawa");
        address1.setStreet("Marszalkowska 1");
        address1.setFlatNumber(1);

        address2.setCity("Warszawa");
        address2.setStreet("Pulawska 120");
        address2.setFlatNumber(10);

        List<Address> userAddresses = new ArrayList<>();
        userAddresses.add(address1);
        userAddresses.add(address2);

        user.setAddresses(userAddresses);

        Order order=new Order();

        List<Order> orders=new ArrayList<>();
        user.setOrders(orders);

        //when
        userRepository.save(user);

        Optional foundUserOptional = userRepository.findById(user.getId());
        User foundUser= (User) foundUserOptional.get();



        //then
        Assertions.assertThat(foundUser.getLogin()).isEqualTo(user.getLogin());
        Assertions.assertThat(foundUser.getAddresses().size()).isEqualTo(user.getAddresses().size());
        Assertions.assertThat(foundUser.getOrders().size()).isEqualTo(user.getOrders().size());

    }



}