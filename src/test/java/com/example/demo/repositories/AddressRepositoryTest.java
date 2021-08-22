package com.example.demo.repositories;

import com.example.demo.entities.Address;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.assertj.core.api.Assertions;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AddressRepositoryTest {

    @Autowired
    AddressRepository addressRepository;

    @Test
    void save_and_get_address() {

        //given
        Address address=new Address();
        address.setCity("Warszawa");
        address.setStreet("Marszalkowska 1");
        address.setFlatNumber(1);

        //When
        addressRepository.save(address);

        Optional<Address>foundAddressOptional=addressRepository.findById(address.getId());
        Address foundAddress=(Address) foundAddressOptional.get();

        //then
        Assertions.assertThat(foundAddress.getCity()).isEqualTo(address.getCity());




    }

}