package com.trip.commons.core.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trip.commons.core.bean.IDEntity;
import com.trip.commons.core.dao.BaseDao;
import com.trip.base.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务操作基本类
 *
 * @author Administrator
 * @create 2017-01-07 13:05
 */
public class BaseService<T extends IDEntity> {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private BaseDao<T> baseDao;

    /**
     * 保存数据
     * @param bean
     */
    @Transactional
    public void save(T bean) throws Exception {
        if(bean.isNew()) {
            String memberId = jwtUtil.getValue("memberId");
            if(StringUtils.isEmpty(memberId)){
                bean.setCreateUser(jwtUtil.getValue("userId"));
            }else{
                bean.setCreateUser(jwtUtil.getValue("memberId"));
            }
            insert(bean);
        } else {
            update(bean);
        }
    }
    /**
     * 添加数据
     * @param bean
     */
    @Transactional
    public void insert(T bean) throws Exception {
        baseDao.insert(bean);
    }
    /**
     * 更新数据
     * @param bean
     */
    @Transactional
    public void update(T bean) {
        baseDao.update(bean);
    }
    /**
     * 根据主键查询实体信息
     * @param id
     * @return
     */
    public T findById(T t) {
        return baseDao.findById(t);
    }
    /**
     * 根据实体条件查询实体信息
     * @param bean
     * @return
     */
    public List<T> findList(T bean){
        List<T> list= baseDao.findByExample(bean);
        return list;
    }
    /**
     * 根据实体条件查询实体信息
     * @param bean
     * @return
     */
    public T queryEntity(T bean) {
        List<T> list= baseDao.findByExample(bean);
        if(list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }
    /**
     * 根据实体条件分页查询实体信息
     * @param bean
     * @param page
     * @return
     */
    public PageInfo<T> findByExample(T bean, Page page) {
        setupPage(page);
        return new PageInfo<T>(baseDao.findByExample(bean));
    }
    /**
     * 设置页码信息
     * @param page
     */
    protected void setupPage(Page page) {
        if(page == null) {
            page = new Page();
        }
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
    }

    /**
     * 根据实体条件分页查询实体信息
     * @param bean
     * @param page
     * @param pagesize
     * @return
     */
    public Map<String,Object> findByExample(T bean, int page, int pagesize){
        PageInfo pageInfo = findByExample(bean, new Page(page, pagesize));
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total",pageInfo.getTotal());
        result.put("data",pageInfo.getList());
        return result;
    }

    /**
     * 根据实体条件分页查询实体信息
     * @param bean
     * @param page
     * @param pagesize
     * @return
     */
    public List<T> findListByPage(T bean, int page, int pagesize){
        PageInfo pageInfo = findByExample(bean, new Page(page, pagesize));
        return pageInfo.getList();
    }
    /**
     * 根据主键删除实体信息
     */
    @Transactional
    public void removeByEntity(T bean) {
        baseDao.removeByEntity(bean);
    }

}
