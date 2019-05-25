package com.trip.admin.sys.action;

import com.trip.base.action.BaseAction;
import com.trip.base.aspect.ActionLog;
import com.trip.base.aspect.NoResp;
import com.trip.base.entity.Resource;
import com.trip.base.service.ResourceService;
import com.trip.base.util.GsonUtil;
import com.trip.commons.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yk on 2017/2/8.
 */
@RestController
@RequestMapping("resource")
@ActionLog(module = "资源管理")
public class ResourceAction extends BaseAction<Resource> {
    @Autowired
    private ResourceService resourceService;

    @Override
    public BaseService getService() {
        return resourceService;
    }


    @RequestMapping(value = "tree/async",name = "资源树异步查询")
    @NoResp
    public Object treeasync(Long id,  HttpServletResponse response) throws Exception {
        List<Resource> list = new ArrayList<Resource>();
        Resource resource = new Resource();
        resource.setParentId(id);
        if(id!=null){
            list = resourceService.findList(resource);
        }else{
            resource.setResourceName("资源管理");
            resource.setId(0l);
            resource.setParent(true);
            list.add(resource);
        }
        return list;
    }

    @RequestMapping(value = "tree",name = "资源树查询")
    @NoResp
    public Object tree(Long roleid) throws Exception {
        List<Resource> list = resourceService.tree(roleid);
        return list;
    }
}
