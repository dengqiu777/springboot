package com.example.util.dao;

/**
 * Author DQ
 * Date 2020/6/9
 **/

import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface BaseDao<T> {
    /**
     * 写接口方法，如果程序中先写，后读，使用这里的接口，即全部数据从主库读取
     */

    //增
    public  int save(String id,T entity)throws Exception;

    //改
    public int update(String mapper,Map<String ,Object> parameter)throws Exception;

    //删
    public int delete(String mapper,Map<String ,Object> parameter)throws Exception;

    //查
    public T find(String mapper,Map<String ,Object> parameter)throws Exception;
    public List<T> findList(String mapper,Map<String ,Object> parameter)throws Exception;
    public List<T> selectBy(String id,Map<String ,Object> parameter)throws Exception;

    public List selectsBy(String id,Map<String ,Object> parameter)throws Exception;
    /**
     * 查询该条记录
     * @param id
     * @param object
     * 开启主库，主库进行读写操作
     * @return
     */
    public Object getObject(String id, Object object);

    /**
     * 删除该条数据，
     * @param id xml的id
     * @param entity 实体
     * @return 是否
     * 开启主库，主库进行读写操作
     * @throws DataAccessException 数据库操作异常
     */
    public boolean delete(String id, T entity) throws DataAccessException;

    /**
     * 删除多条记录
     * @param id
     * @param object
     * @return
     * 开启主库，主库进行读写操作
     * @throws DataAccessException
     */
    public boolean deletes(String id, Object object) throws DataAccessException;

    /**
     * 查询一个集合实体 T 返回得实体
     * @param id
     * @param params
     * @return
     * 开启主库，主库进行读写操作
     * @throws DataAccessException
     */
    public List<T> select(String id, Object params) throws DataAccessException;

    /**
     *  查询出一个集合对象
     * @param id
     * @param params
     * @return
     * 开启主库，主库进行读写操作
     * @throws DataAccessException
     */
    public List selects(String id, Object params) throws DataAccessException;

    /**
     * 查询实体对象
     * @param id
     * @param params
     * @return
     * 开启主库，主库进行读写操作
     * @throws DataAccessException
     */
    public T selectEntity(String id, Object params) throws DataAccessException;

    /**
     * 统计总数
     * @param id
     * @param params
     * @return
     * 开启主库，主库进行读写操作
     * @throws DataAccessException
     */
    public Integer getTotalCount(String id, Object params) throws DataAccessException;



    /**
     * insert一个对象
     * @param entity
     * @return
     * 开启主库，主库进行读写操作
     * @throws DataAccessException
     */
    public boolean insertSelective(T entity) throws DataAccessException;

    /**
     * 插入一个对象
     * @param id xml id
     * @param entity
     * @return
     * 开启主库，主库进行读写操作
     * @throws DataAccessException
     */
    public boolean insert(String id, T entity) throws DataAccessException;

    /**
     * 插入一个对象
     * @param id
     * @param params
     * @return
     * 开启主库，主库进行读写操作
     * @throws DataAccessException
     */
    public boolean insertObj(String id, Object params) throws DataAccessException;

    /**
     * 更新实体，根据ID更新
     * @param entity
     * @return
     * 开启主库，主库进行读写操作
     * @throws DataAccessException
     */
    public boolean updateByPrimaryKeySelective(T entity) throws DataAccessException;

    /**
     * 更新实体 ID  sqlID
     * @param id
     * @param entity
     * @return
     * 开启主库，主库进行读写操作
     * @throws DataAccessException
     */
    public boolean update(String id, T entity) throws DataAccessException;

    /**
     * 更新实体
     * @param id
     * @param params
     * @return
     * 开启主库，主库进行读写操作
     * @throws DataAccessException
     */
    public boolean updateByObject(String id, Object params) throws DataAccessException;

    /**
     * insert
     * @param id
     * @param entity
     * @return
     * 开启主库，主库进行读写操作
     * @throws DataAccessException
     */
    public boolean batchInsert(String id, T entity) throws DataAccessException;

    /**
     * 删除
     * @param pk sqlID
     * @return
     * 开启主库，主库进行读写操作
     * @throws DataAccessException
     */
    public boolean deleteByPrimaryKey(Integer pk) throws DataAccessException;

    /**
     * 查询实体
     * @param pk 实体id
     * @return
     * 开启主库，主库进行读写操作
     * @throws DataAccessException
     */
    public T selectByPrimaryKey(Integer pk) throws DataAccessException;

    /**
     * 查询全部
     * @return
     * 开启主库，主库进行读写操作
     * @throws DataAccessException
     */
    public List<T> selectAll() throws DataAccessException;

    /**
     * 查询实体
     * @param entity
     * @return
     * 开启主库，主库进行读写操作
     * @throws DataAccessException
     */
    public List<T> selectByEntity(T entity) throws DataAccessException;

    /**
     * 保存或者更新
     * @param entity
     * @return
     * 开启主库，主库进行读写操作
     * @throws DataAccessException
     */
    public boolean saveOrUpdate(T entity) throws DataAccessException;

    /**
     * 保存或者更新
     * @param insertId
     * @param updateId
     * @param entity
     * @return
     * 开启主库，主库进行读写操作
     * @throws DataAccessException
     */
    public boolean saveOrUpdate(String insertId, String updateId, T entity) throws DataAccessException;

    /**
     * 更新
     * @param sql
     * @param status
     * @return
     * 开启主库，主库进行读写操作
     */
    public boolean update(String sql, Integer status);

    /**
     * 只读接口方法，只会开启从数据库进行读取
     */
    /**
     * 查询对象
     * @param id
     * @param object
     * @param readOnly
     * 开启从库，只读接口
     * @return
     */
    public Object getObject(String id, Object object, String readOnly);

    /**
     * 查询对象
     * @param id
     * @param params
     * @param readOnly
     * 开启从库，只读接口
     * @return
     * @throws DataAccessException
     */
    public List<T> select(String id, Object params, String readOnly) throws DataAccessException;

    /**
     * 查询集合对象
     * @param id
     * @param params
     * @param readOnly
     * @return
     * 开启从库，只读接口
     * @throws DataAccessException
     */
    public List selects(String id, Object params, String readOnly) throws DataAccessException;

    /**
     * 查询对象
     * @param id
     * @param params
     * @param readOnly
     * @return
     * 开启从库，只读接口
     * @throws DataAccessException
     */
    public T selectEntity(String id, Object params, String readOnly) throws DataAccessException;

    /**
     * 统计总数
     * @param id
     * @param params
     * @param readOnly
     * @return
     * 开启从库，只读接口
     * @throws DataAccessException
     */
    public Integer getTotalCount(String id, Object params, String readOnly) throws DataAccessException;

    /**
     * 查询实体
     * @param pk  id
     * @param readOnly
     * @return
     * 开启从库，只读接口
     * @throws DataAccessException
     */
    public T selectByPrimaryKey(Integer pk, String readOnly) throws DataAccessException;

    /**查询全部
     * @param readOnly
     * @return
     * 开启从库，只读接口
     * @throws DataAccessException
     */
    public List<T> selectAll(String readOnly) throws DataAccessException;

    /**
     * 查询
     * @param entity
     * @param readOnly
     * @return
     * 开启从库，只读接口
     * @throws DataAccessException
     */
    public List<T> selectByEntity(T entity, String readOnly) throws DataAccessException;

}
