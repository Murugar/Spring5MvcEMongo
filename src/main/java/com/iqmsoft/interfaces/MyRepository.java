package com.iqmsoft.interfaces;

import java.util.List;

import com.iqmsoft.model.User;

public interface MyRepository {

    public void save(Object object);
    public Object encontrarPorId(long id, Class aClass);
    public List<User> encontrarPorNome(String nome);

}
