package br.com.pvprojects.loja.integration.response;

public class MockRequestData {

    private String item;
    private String price;

    public MockRequestData() {
    }

    public MockRequestData(String item, String price) {
        this.item = item;
        this.price = price;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MockRequestData{" +
                "item='" + item + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}