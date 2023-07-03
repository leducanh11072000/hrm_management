package com.payment.admin.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Map;

@NoRepositoryBean
public  interface BaseRepository<DOMAIN,ID>  extends JpaRepository<DOMAIN,ID>, PagingAndSortingRepository<DOMAIN, ID>
        , JpaSpecificationExecutor<DOMAIN>
{

    List<Map> queryBySql(String sql);

    List<?> queryBySql(String sql,Class<?> klass);

    List<Map> queryMapBySql(String sql);

    List<?> queryObjBySql(String sql, Class<?> klass);

    List<DOMAIN> query(String sql);

    Object getBySql(String sql,Class<?> klass);

    DOMAIN get(String sql);

    DOMAIN getOne(ID id);

    int execute(String sql);

    Class<DOMAIN> getDataClass();
}
