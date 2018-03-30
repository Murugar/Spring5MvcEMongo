package com.iqmsoft.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "pessoa")
public class User {

    @Id
    private long id;
    private String nome;
    private int idade;

    @DBRef(db = "endereco")
    private List<Address> enderecos = new ArrayList<Address>();

    /**
     *
     */
    public User() {
    }

    /**
     *
     * @param id
     * @param nome
     * @param idade
     */
    @PersistenceConstructor
    public User(Long id, String nome, int idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    /**
     *
     * @param nome
     * @param idade
     */
    public User(String nome, int idade) {
        this(null, nome, idade);
    }

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public User setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public int getIdade() {
        return idade;
    }

    public User setIdade(int idade) {
        this.idade = idade;
        return this;
    }

    public List<Address> getEnderecos() {
        return enderecos;
    }

    public User setEnderecos(List<Address> enderecos) {
        this.enderecos = enderecos;
        return this;
    }
}
