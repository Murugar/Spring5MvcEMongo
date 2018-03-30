package com.iqmsoft.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "endereco")
public class Address {

    @Id
    private long id;
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;

    /**
     *
     */
    public Address() {
    }

    /**
     *
     * @param id
     * @param rua
     * @param numero
     * @param bairro
     * @param cidade
     * @param estado
     */
    @PersistenceConstructor
    public Address(long id, String rua, int numero, String bairro, String cidade, String estado) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public Address setId(long id) {
        this.id = id;
        return this;
    }

    public String getRua() {
        return rua;
    }

    public Address setRua(String rua) {
        this.rua = rua;
        return this;
    }

    public int getNumero() {
        return numero;
    }

    public Address setNumero(int numero) {
        this.numero = numero;
        return this;
    }

    public String getBairro() {
        return bairro;
    }

    public Address setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public String getCidade() {
        return cidade;
    }

    public Address setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public String getEstado() {
        return estado;
    }

    public Address setEstado(String estado) {
        this.estado = estado;
        return this;
    }
}
