package com.example.util.dao.impl;

/**
 * Author DQ
 * Date 2020/6/9
 **/
import com.example.util.dao.BaseDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/*
 * BaseDao实现类，使用SqlSessionTemplate 模版
 * */
@Repository
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    private static Logger log = LoggerFactory.getLogger(BaseDaoImpl.class);

    private Class<T> entityClass;
    private String mapperNamespace;

    public BaseDaoImpl() {
        setEntityClass((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
        setMapperNamespace(entityClass.getName().substring(
                entityClass.getName().lastIndexOf(".") + 1));
    }

    public String getMapperNamespace() {
        return mapperNamespace;
    }

    public void setMapperNamespace(String className) {
        this.mapperNamespace = "mapper." + className + "Mapper";
    }

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    //增
    @Override
    public int save(String id, T entity ) throws Exception {
        return sqlSessionTemplate.insert(mapperNamespace + "." + id,entity);
    }

    //改
    @Override
    public int update(String mapper, Map<String, Object> parameter) throws Exception {
        return sqlSessionTemplate.update(mapper,parameter);
    }

    //删
    @Override
    public int delete(String mapper, Map<String, Object> parameter) throws Exception {
        return sqlSessionTemplate.delete(mapper,parameter);
    }

    //查
    @Override
    public T find(String mapper, Map<String, Object> parameter) throws Exception {
        return sqlSessionTemplate.selectOne(mapper,parameter);
    }

    @Override
    public List<T> findList(String mapper, Map<String, Object> parameter) throws Exception {
        return sqlSessionTemplate.selectList(mapper,parameter);
    }

    @Override
    public List<T> selectBy(String id, Map<String, Object> parameter) throws Exception {
        return sqlSessionTemplate.selectList(mapperNamespace + "." + id,parameter);
    }

    @Override
    public List selectsBy(String id, Map<String, Object> parameter) throws Exception {
        return sqlSessionTemplate.selectList(mapperNamespace + "." + id,parameter);
    }

    @Override
    public Object getObject(String id, Object object) {
        return sqlSessionTemplate.selectOne(mapperNamespace + "." + id, object);
    }

    @Override
    public boolean delete(String id, T entity) throws DataAccessException {
        boolean flag = false;
        try {
            flag = sqlSessionTemplate.delete(mapperNamespace + "." + id,
                    entity) > 0 ? true : false;
        } catch (DataAccessException e) {
            flag = false;
            throw e;
        }
        return flag;
    }

    @Override
    public boolean deletes(String id, Object object) throws DataAccessException {
        boolean flag = false;
        try {
            flag = sqlSessionTemplate.delete(mapperNamespace + "." + id,
                    object) > 0 ? true : false;
        } catch (DataAccessException e) {
            flag = false;
            throw e;
        }
        return flag;
    }

    @Override
    public List<T> select(String id, Object params) throws DataAccessException {
        List<T> result = new ArrayList<T>();
        try {
            result = sqlSessionTemplate.selectList(
                    mapperNamespace + "." + id, params);
        } catch (DataAccessException e) {
            throw e;
        }
        return result;
    }

    @Override
    public List selects(String id, Object params) throws DataAccessException {
        List result = new ArrayList();
        try {
            result = sqlSessionTemplate.selectList(
                    mapperNamespace + "." + id, params);
        } catch (DataAccessException e) {
            throw e;
        }
        return result;
    }

    @Override
    public T selectEntity(String id, Object params) throws DataAccessException {
        List<T> list = select(id, params, mapperNamespace);
        if (list != null && list.size() > 0) {
            return (T) list.get(0);
        }
        return null;
    }

    @Override
    public Integer getTotalCount(String id, Object params) throws DataAccessException {
        return sqlSessionTemplate.selectOne(
                mapperNamespace + "." + "getTotalCount", params);    }

    @Override
    public boolean insertSelective(T entity) throws DataAccessException {
        return insert("insertSelective", entity);
    }

    @Override
    public boolean insert(String id, T entity) throws DataAccessException {
        return insertObj(id, entity);
    }

    @Override
    public boolean insertObj(String id, Object params) throws DataAccessException {
        boolean flag = false;
        try {
            flag = sqlSessionTemplate.insert(mapperNamespace + "." + id,
                    params) > 0 ? true : false;
        } catch (DataAccessException e) {
            flag = false;
            throw e;
        }
        return flag;
    }

    @Override
    public boolean updateByPrimaryKeySelective(T entity) throws DataAccessException {
        return update("updateByPrimaryKeySelective", entity);
    }

    @Override
    public boolean update(String id, T entity) throws DataAccessException {
        boolean flag = false;
        try {
            flag = sqlSessionTemplate.update(mapperNamespace + "." + id,
                    entity) > 0 ? true : false;
        } catch (DataAccessException e) {
            flag = false;
            throw e;
        }
        return flag;
    }

    @Override
    public boolean updateByObject(String id, Object params) throws DataAccessException {
        boolean flag = false;
        try {
            flag = sqlSessionTemplate.update(mapperNamespace + "." + id,
                    params) > 0 ? true : false;
        } catch (DataAccessException e) {
            flag = false;
            throw e;
        }
        return flag;
    }

    @Override
    public boolean batchInsert(String id, T entity) throws DataAccessException {
        boolean flag = false;
        try {
            flag = sqlSessionTemplate.insert(mapperNamespace + "." + id,
                    entity) > 0 ? true : false;
        } catch (DataAccessException e) {
            flag = false;
            throw e;
        }
        return flag;
    }

    @Override
    public boolean deleteByPrimaryKey(Integer pk) throws DataAccessException {
        boolean flag = false;
        try {
            flag = sqlSessionTemplate.delete(
                    mapperNamespace + "." + "deleteByPrimaryKey", pk) > 0 ? true
                    : false;
        } catch (DataAccessException e) {
            flag = false;
            throw e;
        }
        return flag;
    }

    @Override
    public T selectByPrimaryKey(Integer pk) throws DataAccessException {
        T result = null;
        try {
            result = (T) sqlSessionTemplate.selectOne(
                    mapperNamespace + "." + "selectByPrimaryKey", pk);
        } catch (DataAccessException e) {
            throw e;
        }
        return result;
    }

    @Override
    public List<T> selectAll() throws DataAccessException {
        List<T> result = new ArrayList<T>();
        try {
            result = sqlSessionTemplate.selectList(
                    mapperNamespace + "." + "selectAll");
        } catch (DataAccessException e) {
            throw e;
        }
        return result;
    }

    @Override
    public List<T> selectByEntity(T entity) throws DataAccessException {
        return select("selectByEntity", entity, mapperNamespace);
    }

    @Override
    public boolean saveOrUpdate(T entity) throws DataAccessException {
        return saveOrUpdate("insertSelective", "updateByPrimaryKeySelective",
                entity);
    }

    @Override
    public boolean saveOrUpdate(String insertId, String updateId, T entity) throws DataAccessException {
        BeanWrapper beanWrapper = new BeanWrapperImpl(entity);
        Integer id = (Integer) beanWrapper.getPropertyValue("id");
        if (id == null || id <= 0) {
            return insert(insertId, entity);
        } else {
            return update(updateId, entity);
        }
    }

    @Override
    public boolean update(String sql, Integer status) {
        boolean flag = false;
        try {
            flag = sqlSessionTemplate.update(mapperNamespace + "." + sql,
                    status) > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public Object getObject(String id, Object object, String readOnly) {
        return sqlSessionTemplate.selectOne(mapperNamespace + "." + id, object);
    }

    @Override
    public List<T> select(String id, Object params, String readOnly) throws DataAccessException {
        List<T> result = new ArrayList<T>();
        try {
            result = sqlSessionTemplate.selectList(
                    mapperNamespace + "." + id, params);

        } catch (DataAccessException e) {
            throw e;
        }
        return result;
    }

    @Override
    public List selects(String id, Object params, String readOnly) throws DataAccessException {
        List result = new ArrayList();
        try {
            result = sqlSessionTemplate.selectList(
                    mapperNamespace + "." + id, params);
        } catch (DataAccessException e) {
            throw e;
        }
        return result;
    }

    @Override
    public T selectEntity(String id, Object params, String readOnly) throws DataAccessException {
        List<T> list = select(id, params, mapperNamespace);
        if (list != null && list.size() > 0) {
            return (T) list.get(0);
        }
        return null;
    }

    @Override
    public Integer getTotalCount(String id, Object params, String readOnly) throws DataAccessException {
        return sqlSessionTemplate.selectOne(mapperNamespace + "." + id,
                params);
    }

    @Override
    public T selectByPrimaryKey(Integer pk, String readOnly) throws DataAccessException {
        T result = null;
        try {
            result = (T) sqlSessionTemplate.selectOne(
                    mapperNamespace + "." + "selectByPrimaryKey", pk);
        } catch (DataAccessException e) {
            throw e;
        }
        return result;
    }

    @Override
    public List<T> selectAll(String readOnly) throws DataAccessException {
        List<T> result = new ArrayList<T>();
        try {
            result = sqlSessionTemplate.selectList(
                    mapperNamespace + "." + "selectAll");
        } catch (DataAccessException e) {
            throw e;
        }
        return result;
    }

    @Override
    public List<T> selectByEntity(T entity, String readOnly) throws DataAccessException {
        return select("selectByEntity", entity, mapperNamespace);
    }
}
