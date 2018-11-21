package br.com.pvprojects.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pvprojects.loja.domain.Credential;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Integer> {

    Credential findByLoginIgnoreCase(String login);

    Credential findByCustomerId(String customerId);
}