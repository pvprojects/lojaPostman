package br.com.pvprojects.loja.service.impl;

import static br.com.pvprojects.loja.util.ConventionsHelper.INVALID_REQUEST;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pvprojects.loja.domain.Customer;
import br.com.pvprojects.loja.domain.Document;
import br.com.pvprojects.loja.domain.form.DocumentChange;
import br.com.pvprojects.loja.domain.request.DocumentRequest;
import br.com.pvprojects.loja.domain.response.DocumentsResponse;
import br.com.pvprojects.loja.infra.handle.exceptions.DefaultException;
import br.com.pvprojects.loja.repository.CustomerRepository;
import br.com.pvprojects.loja.repository.DocumentRepository;
import br.com.pvprojects.loja.service.DocumentService;
import br.com.pvprojects.loja.util.Helper;
import br.com.pvprojects.loja.util.Validator;
import br.com.pvprojects.loja.util.enums.Type;

@Service
public class DocumentServiceImpl implements DocumentService {

    private static final Logger log = LoggerFactory.getLogger(DocumentServiceImpl.class);

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public DocumentsResponse create(DocumentRequest documentRequest, String login) {
        Helper.checkIfObjectIsNull(documentRequest, INVALID_REQUEST);
        Helper.checkIfStringIsBlank(login, "customerId inválido.");

        Customer customer = customerRepository.findByLoginIgnoreCase(login);
        Helper.checkIfObjectIsNull(customer, "Usuário não encontrado.");

        this.validateDocumentByPerson(documentRequest);

        Document document = new Document();
        DocumentsResponse documentsResponse;

        try {
            document.setCustomerId(customer.getId());
            document.setNumber(documentRequest.getNumber());
            document.setType(Type.valueOf(documentRequest.getType()));
            this.documentRepository.saveAndFlush(document);
        } catch (Exception e) {
            log.error("Erro ao criar o documento.");
            throw new DefaultException("Erro ao criar o documento.");
        }

        documentsResponse = this.documentToDocumentResponse(document);

        return documentsResponse;
    }

    @Override
    public List<DocumentsResponse> findAllDocuments(String login) {
        List<Document> list = this.documentRepository.findAll();

        List<DocumentsResponse> docs = new ArrayList<>();

        list.forEach(p -> {
            for (Document doc : list) {
                DocumentsResponse documentsResponse = this.documentToDocumentResponse(doc);

                if (docs.isEmpty() || docs.size() < list.size())
                    docs.add(documentsResponse);

            }
        });

        return docs;
    }

    @Override
    public DocumentsResponse find(Type type, String number) {
        Helper.checkIfStringIsBlank(number, "número inválido.");

        Document document = this.documentRepository.findByTypeAndNumber(type, number);
        return this.documentToDocumentResponse(document);
    }

    @Override
    @Transactional
    public DocumentsResponse changeDocument(String type, String number, String login, DocumentChange documentChange) {
        Helper.checkIfStringIsBlank(type, "Tipo do documento inválido.");
        Helper.checkIfStringIsBlank(number, "Número do documento inválido.");
        Helper.checkIfStringIsBlank(login, "Login inválido.");
        Helper.checkIfObjectIsNull(documentChange, INVALID_REQUEST);

        validateType(type);

        Type type2 = Type.valueOf(type.toUpperCase());

        if (type2.equals(Type.CPF))
            Validator.verifyIfCpfIsValid(number);

        Customer customer = customerRepository.findByLoginIgnoreCase(login);
        Helper.checkIfObjectIsNull(customer, "Customer não encontrado.");

        Document document = documentRepository.findByCustomerIdAndTypeAndNumber(customer.getId(), type2, number);
        Helper.checkIfObjectIsNull(document, "Documents não encontrado.");

        try {
            document.setType(documentChange.getType());
            document.setNumber(Validator.clean(documentChange.getNumber()));
            document.setUpdated(LocalDateTime.now());
            documentRepository.saveAndFlush(document);
        } catch (Exception e) {
            log.error("Erro ao atualizar o documento.");
            throw new DefaultException("Erro ao atualizar o documento.");
        }
        return this.documentToDocumentResponse(document);
    }

    private DocumentsResponse documentToDocumentResponse(Document document) {
        DocumentsResponse documentsResponse = new DocumentsResponse();

        documentsResponse.setId(document.getId());
        documentsResponse.setCustomerId(document.getCustomerId());
        documentsResponse.setType(document.getType());
        documentsResponse.setNumber(document.getNumber());
        documentsResponse.setCreated(document.getCreated());
        documentsResponse.setUpdated(document.getUpdated());
        return documentsResponse;
    }

    private boolean validateDocumentByPerson(DocumentRequest documentRequest) {
        Document documentEntity;

        try {
            documentEntity = documentRepository.findByType(Type.valueOf(documentRequest.getType()));
        } catch (IllegalArgumentException e) {
            throw new DefaultException("Tipo de documento inválido.");
        } catch (Exception ex) {
            throw new DefaultException("Erro.");
        }

        if (null != documentEntity)
            throw new DefaultException(documentRequest.getType() + " já cadastrado para o usuário.");

        return true;
    }

    private boolean validateType(String type) {
        try {
            Type.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DefaultException("Tipo de documento inválido.");
        } catch (Exception ex) {
            throw new DefaultException("Erro.");
        }
        return true;
    }
}