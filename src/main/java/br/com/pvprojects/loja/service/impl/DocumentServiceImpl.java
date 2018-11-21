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
import br.com.pvprojects.loja.repository.CustomerRepository;
import br.com.pvprojects.loja.repository.DocumentRepository;
import br.com.pvprojects.loja.service.DocumentService;
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
        Customer customer = customerRepository.findByLoginIgnoreCase(login);

        Document doc = new Document();
        doc.setCustomerId(customer.getId());
        doc.setNumber(document.getNumber());
        doc.setType(document.getType());
        this.documentRepository.saveAndFlush(doc);

        return doc;
    }

    @Override
    public List<Document> findAllDocuments(String login) {
        return this.documentRepository.findAll();
    }

    @Override
    public DocumentsData find(Type type, String number) {
        Document document = this.documentRepository.findByTypeAndNumber(type, number);
        return this.findDto(document);
    }

    @Override
    @Transactional
    public DocumentsData changeDocument(String type, String number, String login, DocumentChange documentChange) {
        Type type2;


        if (type.equalsIgnoreCase(Type.CPF.name())) {
            type2 = Type.CPF;
            Validator.verifyIfCpfIsValid(documentChange.getNumber());
        } else if (type.equalsIgnoreCase(Type.RG.name())) {
            type2 = Type.RG;
        } else if (type.equalsIgnoreCase(Type.PASSPORT.name())) {
            type2 = Type.PASSPORT;
        } else {
            return null;
        }
        String value = Validator.clean(documentChange.getNumber());

        Customer customer = customerRepository.findByLoginIgnoreCase(login);

        Document document = documentRepository.findByCustomerIdAndTypeAndNumber(customer.getId(), type2, number);

        document.setType(documentChange.getType());
        document.setNumber(value);
        document.setUpdated(LocalDateTime.now());
        documentRepository.saveAndFlush(document);

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
}
