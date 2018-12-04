package br.com.pvprojects.loja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pvprojects.loja.domain.Historic;

public interface HistoricRepository extends JpaRepository<Historic, Integer> {

    List<Historic> findByLoginIgnoreCase(String login);
}