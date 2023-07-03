package com.payment.admin.service.base;

public interface InsertService<T, ID> {
    T insertOrUpdate(T record);
}
