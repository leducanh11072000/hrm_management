package com.payment.admin.query;

import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;

public class DynamicSpecifications {
    public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters, final Class<T> entityClazz) {
        return new SimpleSpecification<T>(filters);
    }
}
