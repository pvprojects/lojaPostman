package br.com.pvprojects.loja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pvprojects.loja.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findByCustomerId(String customerId);

    Address findByCustomerIdAndName(String customerId, String nameAddress);
}
