package br.com.pvprojects.loja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pvprojects.loja.domain.Historic;
import br.com.pvprojects.loja.domain.response.HistoricResponse;

public interface HistoricRepository extends JpaRepository<Historic, Integer> {

    List<HistoricResponse> findByCustomerId(String customerId);
}