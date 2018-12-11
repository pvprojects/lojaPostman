package br.com.pvprojects.loja.web.api;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;

import br.com.pvprojects.loja.base.DefaultDefaultBaseControllerTest;
import br.com.pvprojects.loja.controller.CustomerController;
import br.com.pvprojects.loja.domain.request.CustomerResquest;
import br.com.pvprojects.loja.domain.response.CustomerResponse;
import br.com.pvprojects.loja.service.CustomerService;
import br.com.pvprojects.loja.util.JsonUtil;
import br.com.pvprojects.loja.util.enums.Gender;
import br.com.pvprojects.loja.util.enums.PersonType;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest extends DefaultDefaultBaseControllerTest {

    @MockBean
    private CustomerService customerService;

    private CustomerResquest customerResquest;
    private CustomerResponse customerResponse;
    private String customerId;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        customerId = "dd450c8c-3297-4f0c-8bf0-a39c78f6ccc7";

        customerResquest = new CustomerResquest();
        customerResquest.setFullName("Paulo Vieira");
        customerResquest.setPersonType("F");
        customerResquest.setNickName("Mestre");
        customerResquest.setBirthDate("22041993");
        customerResquest.setCountry("BR");
        customerResquest.setGender("M");
        customerResquest.setMotherName("Maria das Dores");
        customerResquest.setFatherName("Augusto dos Santos");
        customerResquest.setLogin("paulo.junior@zup.com.br");
        customerResquest.setPassword("123456");
        customerResquest.setNumberOfChildren(2);
        customerResquest.setParentId(null);

        customerResponse = new CustomerResponse();
        customerResponse.setId(customerId);
        customerResponse.setFullName("Paulo Vieira");
        customerResponse.setPersonType(PersonType.F);
        customerResponse.setNickName("Mestre");
        customerResponse.setBirthDate("22041993");
        customerResponse.setCountry("BR");
        customerResponse.setGender(Gender.M);
        customerResponse.setMotherName("Maria das Dores");
        customerResponse.setFatherName("Augusto dos Santos");
        customerResponse.setLogin("paulo.junior@zup.com.br");
        customerResponse.setPassword("$2a$10$fZxua5IAPhVnYOgvVRZ9h.bslvdoiBgA1JeTQzKDd8Potx6sRBYXC");
        customerResponse.setNumberOfChildren(2);
        customerResponse.setParentId(null);
        customerResponse.setCreated(LocalDateTime.of(2018, 11, 21, 19, 33, 20));
        customerResponse.setUpdated(LocalDateTime.of(2018, 12, 6, 15, 1, 1));

    }

    /**
     * createCustomer
     */

    @Test
    public void createCustomer() throws Exception {
        when(customerService.create(customerResquest)).thenReturn(customerResponse);

        addHeader("Authorization",
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyQHNlcnZpY2UuY29tIiwiZXhwIjoxNTM1MzcwNTE0LCJqdGkiOiIxIn0.c9OeRN");

        post("/customer", status().isCreated(), customerResquest, new ResponseEntity<>(HttpStatus.CREATED))
                .andDo(MockMvcRestDocumentation.document("customer-create",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("birthDate").description("Data de anivesario do usuário"),
                                fieldWithPath("country").description("País do usuário"),
                                fieldWithPath("fatherName").description("Nome do pai do usuário"),
                                fieldWithPath("fullName").description("Nome do usuário"),
                                fieldWithPath("gender").description("Sexo do usuário"),
                                fieldWithPath("login").description("E-mail do usuário"),
                                fieldWithPath("motherName").description("Nome da mãe do usuário"),
                                fieldWithPath("nickName").description("Nickname do usuário"),
                                fieldWithPath("numberOfChildren").description("Quantidade de fihlos do usuário"),
                                fieldWithPath("password").description("Senha do usuário"),
                                fieldWithPath("personType").description("Tipo de pessoa (Fisica - Juridica"),
                                fieldWithPath("parentId").description("CustomerId do pai/mãe"))
                ));
    }

    /**
     * getCustomerByLogin
     */

    @Test
    public void getCustomerById() throws Exception {
        given(customerService.findByIdOrLogin(anyString())).willReturn(customerResponse);

        addHeader("login", "E-mail do usuário");
        addHeader("Authorization",
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyQHNlcnZpY2UuY29tIiwiZXhwIjoxNTM1MzcwNTE0LCJqdGkiOiIxIn0.c9OeRN");

        get("/customer", status().isOk(), new ResponseEntity<>(customerResponse,
                HttpStatus.OK))
                .andDo(MockMvcRestDocumentation.document("customer-find",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("id").description("Id do usuário"),
                                fieldWithPath("birthDate").description("Data de anivesario do usuário"),
                                fieldWithPath("country").description("País do usuário"),
                                fieldWithPath("created").description("Data de criação do customer"),
                                fieldWithPath("fatherName").description("Nome do pai do usuário"),
                                fieldWithPath("fullName").description("Nome do usuário"),
                                fieldWithPath("gender").description("Sexo do usuário"),
                                fieldWithPath("login").description("E-mail do usuário"),
                                fieldWithPath("motherName").description("Nome da mãe do usuário"),
                                fieldWithPath("nickName").description("Nickname do usuário"),
                                fieldWithPath("numberOfChildren").description("Quantidade de fihlos do usuário"),
                                fieldWithPath("password").description("Senha do usuário"),
                                fieldWithPath("personType").description("Tipo de pessoa (Fisica - Juridica)"),
                                fieldWithPath("updated").description("Data de atualização do customer"))
                ));
    }

    /**
     * edit Customer
     */

//    @Test
    public void editCustomer() throws Exception {
        when(customerService.updateCustomer(anyString(), any(CustomerResquest.class))).thenReturn(customerResponse);

        addHeader("Authorization",
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyQHNlcnZpY2UuY29tIiwiZXhwIjoxNTM1MzcwNTE0LCJqdGkiOiIxIn0.c9OeRN");

        put("/customer/{customerId}", status().isOk(),  JsonUtil.toJson(customerResquest),
                new ResponseEntity<>(customerResponse, HttpStatus.OK), customerId)
                .andDo(MockMvcRestDocumentation.document("customer-edit",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("birthDate").description("Data de anivesario do usuário"),
                                fieldWithPath("country").description("País do usuário"),
                                fieldWithPath("fatherName").description("Nome do pai do usuário"),
                                fieldWithPath("fullName").description("Nome do usuário"),
                                fieldWithPath("gender").description("Sexo do usuário"),
                                fieldWithPath("login").description("E-mail do usuário"),
                                fieldWithPath("motherName").description("Nome da mãe do usuário"),
                                fieldWithPath("nickName").description("Nickname do usuário"),
                                fieldWithPath("numberOfChildren").description("Quantidade de fihlos do usuário"),
                                fieldWithPath("password").description("Senha do usuário"),
                                fieldWithPath("personType").description("Tipo de pessoa (Fisica - Juridica"),
                                fieldWithPath("parentId").description("CustomerId do pai/mãe")),
                        pathParameters(
                                parameterWithName("customerId").description("Id do usuário no customer")),
                        responseFields(
                                fieldWithPath("id").description("Id do usuário"),
                                fieldWithPath("birthDate").description("Data de anivesario do usuário"),
                                fieldWithPath("country").description("País do usuário"),
                                fieldWithPath("created").description("Data de criação do customer"),
                                fieldWithPath("fatherName").description("Nome do pai do usuário"),
                                fieldWithPath("fullName").description("Nome do usuário"),
                                fieldWithPath("gender").description("Sexo do usuário"),
                                fieldWithPath("login").description("E-mail do usuário"),
                                fieldWithPath("motherName").description("Nome da mãe do usuário"),
                                fieldWithPath("nickName").description("Nickname do usuário"),
                                fieldWithPath("numberOfChildren").description("Quantidade de fihlos do usuário"),
                                fieldWithPath("password").description("Senha do usuário"),
                                fieldWithPath("personType").description("Tipo de pessoa (Fisica - Juridica)"),
                                fieldWithPath("updated").description("Data de atualização do customer"))
                ));
    }
}