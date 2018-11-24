package br.com.pvprojects.loja.service;

import java.util.List;

import org.dom4j.io.DocumentResult;

import br.com.pvprojects.loja.domain.Document;
import br.com.pvprojects.loja.domain.request.DocumentRequest;
import br.com.pvprojects.loja.domain.response.DocumentsResponse;
import br.com.pvprojects.loja.domain.form.DocumentChange;
import br.com.pvprojects.loja.util.enums.Type;

public interface DocumentService {

    DocumentsResponse create(DocumentRequest documentRequest, String login);

    List<DocumentsResponse> findAllDocuments(String login);

    DocumentsResponse find(Type type, String number);

    DocumentsResponse changeDocument(String type, String number, String login,  DocumentChange documentChange);
}