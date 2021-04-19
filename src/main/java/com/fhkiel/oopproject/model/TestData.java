package com.fhkiel.oopproject.model;

import java.io.Serial;
import java.io.Serializable;

public class TestData implements Serializable {

    // The serialVersionUID is important for version control
    @Serial
    private static final long serialVersionUID = 1L;

    private String bezeichner;
    private int nummer;
    private int value;

    public TestData() {
        this.setBezeichner("Test Objekt");
        this.setNummer(9999);
        this.setValue(42);
    }

    public TestData(String bezeichner, int nummer, int value) {
        this.setBezeichner(bezeichner);
        this.setNummer(nummer);
        this.setValue(value);
    }

    public TestData(TestData cloneData) {
        this.setBezeichner(cloneData.getBezeichner() + "(Clone)");
        this.setNummer(cloneData.getNummer() * (-1));
        this.setValue(cloneData.getValue());
    }

    public String getBezeichner() {
        return bezeichner;
    }

    private void setBezeichner(String bezeichner) {
        this.bezeichner = bezeichner;
    }

    public int getNummer() {
        return nummer;
    }

    private void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
