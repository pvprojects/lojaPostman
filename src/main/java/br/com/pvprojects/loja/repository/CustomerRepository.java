package br.com.pvprojects.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pvprojects.loja.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    Customer findByFullNameIgnoreCase(String fullName);

    Customer findByLoginIgnoreCase (String login);

    Customer findByParentId(String customerIdFromFatherOrMother);
}