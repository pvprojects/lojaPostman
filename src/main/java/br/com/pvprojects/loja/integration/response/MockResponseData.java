package br.com.pvprojects.loja.integration.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MockResponseData implements Serializable {

    private String id;


    public MockResponseData() {
    }

    public MockResponseData(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MockResponseData{" +
                "id='" + id + '\'' +
                '}';
    }
}