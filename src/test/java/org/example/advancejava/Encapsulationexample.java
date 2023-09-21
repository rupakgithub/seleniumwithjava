package org.example.advancejava;

public class Encapsulationexample {
    public static void main(String[] args) {

        Vehical vehical = new Vehical();

        vehical.setCompany("Honda");
        vehical.setModel("XYZ");
        vehical.setRegistration("jhgjg67678t5");

        vehical.getCompany();

    }
}

class Vehical{

    final int id = 4755;
    private String company;
    private String model;
    private String registration;

    public int getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }
}
