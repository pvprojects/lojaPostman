package br.com.pvprojects.loja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pvprojects.loja.domain.Document;
import br.com.pvprojects.loja.util.enums.Type;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

    Document findByTypeAndNumber(Type type, String number);

    List<Document> findByCustomerId(String customerId);

    Document findByCustomerIdAndTypeAndNumber(String customerId, Type type, String number);

}