package br.com.pvprojects.loja.controller;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pvprojects.loja.domain.Document;
import br.com.pvprojects.loja.domain.data.DocumentsData;
import br.com.pvprojects.loja.domain.form.DocumentChange;
import br.com.pvprojects.loja.service.DocumentService;
import br.com.pvprojects.loja.util.enums.Type;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping(path = "/create")
    public ResponseEntity<DocumentsData> createDocument(@RequestBody @Valid Document document,
            @QueryParam("login") String login) {

        Document newDocument = this.documentService.create(document, login);
        DocumentsData documentsData = new DocumentsData(newDocument.getId());

        return new ResponseEntity<>(documentsData, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{email}")
    public ResponseEntity<List<Document>> getAllDocuments(@PathVariable(name = "email") String email) {

        List<Document> list = this.documentService.findAllDocuments(email);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = "/find/cpf/{number}")
    public ResponseEntity<DocumentsData> getDocument(@PathVariable(name = "number") String number) {

        DocumentsData documentsData = this.documentService.find(Type.CPF, number);

        return new ResponseEntity<>(documentsData, HttpStatus.OK);
    }

    @PutMapping(path = "/change/{type}/{number}")
    public ResponseEntity<Void> changeDocument(@PathVariable(name = "type") String type,
            @PathVariable(name = "number") String number,
            @QueryParam("login") String login,
            @RequestBody DocumentChange documentChange) {

        this.documentService.changeDocument(type, number, login, documentChange);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}