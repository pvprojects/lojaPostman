package br.com.pvprojects.loja.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pvprojects.loja.domain.Customer;
import br.com.pvprojects.loja.domain.Document;
import br.com.pvprojects.loja.domain.data.DocumentsData;
import br.com.pvprojects.loja.domain.form.DocumentChange;
import br.com.pvprojects.loja.infra.handle.exceptions.DefaultException;
import br.com.pvprojects.loja.repository.CustomerRepository;
import br.com.pvprojects.loja.repository.DocumentRepository;
import br.com.pvprojects.loja.service.DocumentService;
import br.com.pvprojects.loja.util.Helper;
import br.com.pvprojects.loja.util.Validator;
import br.com.pvprojects.loja.util.enums.Type;

@Service
public class DocumentServiceImpl implements DocumentService {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public Document create(Document document, String login) {
        Helper.checkIfObjectIsNull(document, "Informações inválidas.");
        Helper.checkIfStringIsBlank(login, "customerId inválido.");

        Customer customer = customerRepository.findByLoginIgnoreCase(login);
        Helper.checkIfObjectIsNull(customer, "Usuário não encontrado.");

        this.validateDocumentType(document.getType());
        this.validateDocumentByPerson(document);

        Document doc = new Document();
        doc.setCustomerId(customer.getId());
        doc.setNumber(document.getNumber());
        doc.setType(document.getType());

        try {
            this.documentRepository.saveAndFlush(doc);
        } catch (Exception e) {
            log.error("Erro ao criar o documento.");
            throw new DefaultException("Erro ao criar o documento.");
        }

        return doc;
    }

    @Override
    public List<Document> findAllDocuments(String login) {
        return this.documentRepository.findAll();
    }

    @Override
    public DocumentsData find(Type type, String number) {
        this.validateDocumentType(type);
        Helper.checkIfStringIsBlank(number, "número inválido.");

        Document document = this.documentRepository.findByTypeAndNumber(type, number);
        return this.findDto(document);
    }

    @Override
    @Transactional
    public DocumentsData changeDocument(String type, String number, String login, DocumentChange documentChange) {
        Helper.checkIfStringIsBlank(type, "Tipo do documento inválido.");
        Helper.checkIfStringIsBlank(number, "Número do documento inválido.");
        Helper.checkIfStringIsBlank(login, "Login inválido.");
        Helper.checkIfObjectIsNull(documentChange, "Informações inválidas.");

        String value = Validator.clean(documentChange.getNumber());

        List<String> list = Helper.enumList();
        if (!list.contains(type.toUpperCase()))
            throw new DefaultException("O tipo não é válido.");

        Type type2 = Enum.valueOf(Type.class, type.toUpperCase());

        if (type2.equals(Type.CPF))
            Validator.verifyIfCpfIsValid(number);

        Customer customer = customerRepository.findByLoginIgnoreCase(login);
        Helper.checkIfObjectIsNull(customer, "Customer não encontrado.");

        Document document = documentRepository.findByCustomerIdAndTypeAndNumber(customer.getId(), type2, number);
        Helper.checkIfObjectIsNull(document, "Documents não encontrado.");

        document.setType(documentChange.getType());
        document.setNumber(value);
        document.setUpdated(LocalDateTime.now());

        try {
            documentRepository.saveAndFlush(document);
        } catch (Exception e) {
            log.error("Erro ao atualizar o documento.");
            throw new DefaultException("Erro ao atualizar o documento.");
        }
        return this.findDto(document);
    }

    private DocumentsData findDto(Document document) {
        DocumentsData documentsData = new DocumentsData();
        documentsData.setId(document.getId());
        documentsData.setCustomerId(document.getCustomerId());
        documentsData.setType(document.getType());
        documentsData.setCreated(document.getCreated());
        documentsData.setUpdated(document.getUpdated());
        return documentsData;
    }

    private boolean validateDocumentType(Type type) {
        Helper.checkIfObjectIsNull(type, "O campo tipo não pode ser null.");

        String tipo = type.name();

        if (tipo.isEmpty())
            throw new DefaultException("O campo tipo não pode ser vazio.");

        return true;
    }

    private boolean validateDocumentByPerson(Document document) {
        Document documentEntity = documentRepository.findByType(document.getType());

        if (null != documentEntity)
            throw new DefaultException(document.getType() + " já cadastrado para o usuário.");

        return true;
    }
}