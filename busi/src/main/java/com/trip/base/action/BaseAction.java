package com.trip.base.action;

import com.trip.base.util.JwtUtil;
import com.trip.commons.core.bean.IDEntity;
import com.trip.commons.core.service.BaseService;
import com.trip.base.constants.Constants;
import com.trip.base.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yk on 2016/8/16.
 */

public abstract class BaseAction<T extends IDEntity> {

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping("query")
    @ResponseBody
    public Object query(T t, Integer curPage, Integer pageSize) throws Exception {
        return getService().findByExample(t, curPage, pageSize);
    }

    @RequestMapping("query/list")
    @ResponseBody
    public Object queryList(T t) throws Exception {
        return getService().findList(t);
    }
    @RequestMapping("query/entity")
    @ResponseBody
    public Object queryEntity(T t) throws Exception {
        return getService().findById(t);
    }


    @RequestMapping("save")
    @ResponseBody
    public Object save(T t) throws BusinessException {
        try {
            getService().save(t);
        } catch (Exception e) {
            e.printStackTrace();
            if(e instanceof BusinessException){
                throw (BusinessException) e;
            }else{
                throw new BusinessException(Constants.SYS_FAIL_CODE, Constants.SYS_FAIL_MSG, e);
            }

        }
        return null;
    }


    @RequestMapping("delete")
    @ResponseBody
    public Object delete(T t) throws BusinessException {
        try {
            getService().removeByEntity(t);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(Constants.SYS_FAIL_CODE, Constants.SYS_FAIL_MSG, e);
        }
        return null;
    }
    public abstract BaseService getService();
}
