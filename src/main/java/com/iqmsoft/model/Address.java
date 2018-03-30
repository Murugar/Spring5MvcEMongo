package com.iqmsoft.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "endereco")
public class Address {

    @Id
    private long id;
    private String street;
    private int numero;
    private String neighborhood;
    private String city;
    private String state;

    /**
     *
     */
    public Address() {
    }

   
    @PersistenceConstructor
    public Address(long id, String street, int numero, String neighborhood, String city, String state) {
        this.id = id;
        this.street = street;
        this.numero = numero;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public Address setId(long id) {
        this.id = id;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Address setStreet(String rua) {
        this.street = rua;
        return this;
    }

    public int getNumero() {
        return numero;
    }

    public Address setNumero(int numero) {
        this.numero = numero;
        return this;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public Address setNeighborhood(String bairro) {
        this.neighborhood = bairro;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public Address setState(String state) {
        this.state = state;
        return this;
    }
}
