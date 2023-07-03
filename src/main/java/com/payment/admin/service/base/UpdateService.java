package com.payment.admin.service.base;


public interface UpdateService <T, ID> {
    T update(T record);
    void update(Iterable<T> list);
}

