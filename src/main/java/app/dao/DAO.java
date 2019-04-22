package app.dao;

import app.entities.Page;
import app.entities.PageRequest;
import app.entities.Product;

public interface DAO<T> {
    Page<T> getAll(PageRequest request);

    T getOne(String str); //// TODO: 18.04.2019 change to long

    void createOne(Product product); //// TODO: 18.04.2019 change on parametrize type

    void deleteOneById(String id);
}
