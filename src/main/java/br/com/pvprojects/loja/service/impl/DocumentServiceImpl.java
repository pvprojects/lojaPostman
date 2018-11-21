package br.com.pvprojects.loja.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pvprojects.loja.domain.Customer;
import br.com.pvprojects.loja.domain.Document;
import br.com.pvprojects.loja.domain.data.DocumentsData;
import br.com.pvprojects.loja.repository.CustomerRepository;
import br.com.pvprojects.loja.repository.DocumentRepository;
import br.com.pvprojects.loja.service.DocumentService;
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
        documentRepository.save(doc);

        return doc;
    }

    @Override
    public List<Document> findAllDocuments(String login) {
        List<Document> list = documentRepository.findAll();
        return list;
    }

    @Override
    public DocumentsData find(Type type, String number) {
        Document document = documentRepository.findByTypeAndNumber(type, number);
        DocumentsData documentsData = this.findDto(document);
        return documentsData;
    }

    private DocumentsData findDto(Document document) {
        DocumentsData documentsData = new DocumentsData();
        documentsData.setId(document.getId());
        documentsData.setCustomerId(document.getCustomerId());
        documentsData.setType(document.getType());
        documentsData.setCreated(document.getCreated());
        return documentsData;
    }
}
