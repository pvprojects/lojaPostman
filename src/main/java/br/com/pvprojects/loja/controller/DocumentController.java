package br.com.pvprojects.loja.controller;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pvprojects.loja.domain.form.DocumentChange;
import br.com.pvprojects.loja.domain.request.DocumentRequest;
import br.com.pvprojects.loja.domain.response.DocumentsResponse;
import br.com.pvprojects.loja.service.DocumentService;
import br.com.pvprojects.loja.util.enums.Type;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping(path = "/create")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')  and #oauth2" +
            ".hasScope('write')")
    public ResponseEntity<DocumentsResponse> createDocument(@RequestBody DocumentRequest documentRequest,
            @QueryParam("login") String login) {

        DocumentsResponse documentsResponse = this.documentService.create(documentRequest, login);

        return new ResponseEntity<>(new DocumentsResponse(documentsResponse.getType(), documentsResponse.getNumber()),
                HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')  and #oauth2" +
            ".hasScope('read')")
    public ResponseEntity<List<DocumentsResponse>> getAllDocuments(@QueryParam("email") String email) {

        List<DocumentsResponse> list = this.documentService.findAllDocuments(email);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')  and #oauth2" +
            ".hasScope('read')")
    @GetMapping(path = "/find/cpf/{number}")
    public ResponseEntity<DocumentsResponse> getDocument(@PathVariable(name = "number") String number) {

        DocumentsResponse documentsResponse = this.documentService.find(Type.CPF, number);

        return new ResponseEntity<>(documentsResponse, HttpStatus.OK);
    }

    @PutMapping(path = "/change/{type}/{number}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')  and #oauth2" +
            ".hasScope('write')")
    public ResponseEntity<Void> changeDocument(@PathVariable(name = "type") String type,
            @PathVariable(name = "number") String number,
            @QueryParam("login") String login,
            @RequestBody DocumentChange documentChange) {

        this.documentService.changeDocument(type, number, login, documentChange);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}