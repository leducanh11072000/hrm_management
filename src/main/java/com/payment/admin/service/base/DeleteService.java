package com.payment.admin.service.base;


public interface DeleteService<T,ID> {
    void  delete(T record);
    void deleteById(ID id);
    void delete(Iterable<ID> ids);
    void deleteAll(Iterable<T> list);
    void clear();
}

