package cat.mvm.modular.entities;

import cat.mvm.modular.entities.Sephora;

public class Data {
    private int code;
    private String name;
    private int family;
    private String type;
    private double price;
    private int quantity;

    public Data(int code, String name, int family, String type, double price, int quantity) {
        this.setCode(code);
        this.setName(name);
        this.setFamily(family);
        this.setType(type);
        this.setPrice(price);
        this.setQuantity(quantity);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        if ((code > 0) && (code < 1000)) {
        } else {
            throw new IllegalArgumentException("El codi té que ser més gran que 0 i més petit de 999");
        }
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFamily() {
        return family;
    }

    public void setFamily(int family) {
        this.family = family;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("El valor té que ser més gran o igual que 0");
        }
        this.quantity = quantity;
    }
}
