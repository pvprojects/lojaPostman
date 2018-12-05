package br.com.pvprojects.loja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pvprojects.loja.domain.History;

public interface HistoryRepository extends JpaRepository<History, Integer> {

    List<History> findByLoginIgnoreCase(String login);
}