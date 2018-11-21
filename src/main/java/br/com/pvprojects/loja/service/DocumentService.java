package br.com.pvprojects.loja.service;

import java.util.List;

import br.com.pvprojects.loja.domain.Document;
import br.com.pvprojects.loja.domain.data.DocumentsData;
import br.com.pvprojects.loja.util.enums.Type;

public interface DocumentService {

    Document create(Document document, String login);

    List<Document> findAllDocuments(String login);

    DocumentsData find(Type type, String number);
}