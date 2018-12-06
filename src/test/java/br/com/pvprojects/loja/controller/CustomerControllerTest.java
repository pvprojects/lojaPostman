package br.com.pvprojects.loja.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;

import br.com.pvprojects.loja.base.BaseControllerTests;
import br.com.pvprojects.loja.domain.request.CustomerResquest;
import br.com.pvprojects.loja.domain.response.CustomerResponse;
import br.com.pvprojects.loja.service.CustomerService;
import br.com.pvprojects.loja.util.enums.Gender;
import br.com.pvprojects.loja.util.enums.PersonType;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest extends BaseControllerTests {

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
        customerResquest.setMotherName("Maria José");
        customerResquest.setFatherName("João dos Santos");
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
        customerResponse.setMotherName("Maria José");
        customerResponse.setFatherName("João dos Santos");
        customerResponse.setLogin("paulo.junior@zup.com.br");
        customerResponse.setPassword("$2a$10$fZxua5IAPhVnYOgvVRZ9h.bslvdoiBgA1JeTQzKDd8Potx6sRBYXC");
        customerResponse.setNumberOfChildren(2);
        customerResponse.setParentId(null);
        customerResponse.setCreated(LocalDateTime.of(2018, 11, 21, 19, 33, 20));
        customerResponse.setUpdated(LocalDateTime.of(2018, 12, 6, 15, 1, 1));

    }

//    @Test
    public void createCustomer() throws Exception {
        given(customerService.create(any(CustomerResquest.class))).willReturn(customerResponse);

        post("/customer", status().isCreated(), customerResquest, new ResponseEntity<>(customerResponse,
                HttpStatus.CREATED))
                .andDo(MockMvcRestDocumentation.document("customer-create",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("id").description("Id do usuário"),
                                fieldWithPath("fullName").description("Nome do usuário")
                        )));
    }

//    @Test
    public void getCustomerById() throws Exception {
        given(customerService.findByIdOrLogin(anyString())).willReturn(customerResponse);

        get("/customer/{customerId}", status().isOk(), new ResponseEntity<>(customerResponse,
                HttpStatus.OK), customerId)
                .andDo(MockMvcRestDocumentation.document("customer-find",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("id").description("Id do usuário"),
                                fieldWithPath("fullName").description("Nome do usuário")
                        )));
    }

    //    @Test
    public void updateCustomer() {
    }
}