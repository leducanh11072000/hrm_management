package com.payment.admin.repository.base;

import org.hibernate.query.sql.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.nutz.mapl.Mapl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BaseRepositoryImpl <DOMAIN, ID> extends SimpleJpaRepository<DOMAIN, ID>
        implements BaseRepository<DOMAIN, ID> {

    private final EntityManager entityManager;

    private Class<DOMAIN> klass;


    BaseRepositoryImpl(JpaEntityInformation<DOMAIN, ID> entityInformation,
                       EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.klass = entityInformation.getJavaType();
    }

    @Override
    public List<Map> queryBySql(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.getResultList();
        return list;
    }
    @Override
    public List<Map> queryMapBySql(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.getResultList();
        return list;
    }
    @Override
    public List<?> queryBySql(String sql, Class<?> klass) {
        List<Map> list = queryBySql(sql);
        if(list.isEmpty()){
            return null;
        }
        List result = new ArrayList();
        for(Map map :list){
            try {
                Object bean = Mapl.maplistToObj(map,klass);
                result.add(bean);
            }catch (Exception e){
            }
        }
        return result;
    }
    @Override
    public List<?> queryObjBySql(String sql, Class<?> klass) {
        List<Map> list = queryMapBySql(sql);
        if(list.isEmpty()){
            return new ArrayList<>();
        }
        List result = new ArrayList();
        for(Map map:list){
            try {
                Object bean = Mapl.maplistToObj(map, klass);
                result.add(bean);
            } catch (Exception e) {
            }
        }
        return result ;

    }
    @Override
    public DOMAIN getOne(ID id){
        return findById(id).get();
    }
    @Override
    public DOMAIN get(String sql) {
        List<DOMAIN> list =  entityManager.createNativeQuery(sql,klass).getResultList();
        return list.get(0);
    }

    @Override
    public int execute(String sql) {
        return entityManager.createNativeQuery(sql).executeUpdate();
    }

    @Override
    public Class<DOMAIN> getDataClass() {
        return klass;
    }

    @Override
    public List<DOMAIN> query(String sql) {
        return entityManager.createNativeQuery(sql,klass).getResultList();
    }

    @Override
    public Object getBySql(String sql, Class<?> klass) {
        List list = queryBySql(sql,klass);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
}

