package app.dao;

import app.utils.Page;
import app.utils.PageRequest;

public interface DAO<T> {
    Page<T> getAll(PageRequest request);

    T getOne(String str); //// TODO: 18.04.2019 change to long

    void createOne(T item);

    void deleteOneById(String id);
}
