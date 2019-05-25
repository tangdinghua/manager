package com.trip.base.service;

import com.trip.base.entity.Resource;
import com.trip.base.entity.Role;
import com.trip.commons.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by yk on 2017/2/8.
 */
@Service
public class ResourceService extends BaseService<Resource> {

    @Autowired
    private RoleService roleService;

    public List<Resource> tree(Long roleid) throws Exception {
        //首先建立一个用来搜索的list，复制元素内容与allNodes一样，先进行排序
        List<Resource> tmpNodes = new ArrayList<Resource>();
        List<Resource> allNodes = super.findList(new Resource());
        Map<String,String> resourceMap = new HashMap<>();
        if(roleid!=null){
            List<Role> list =roleService.queryResourceById(roleid);
            for(Role role:list){
                resourceMap.put(role.getResourceId(),role.getResourceId());
            }
        }
        tmpNodes.addAll(allNodes);
        Collections.sort(tmpNodes);
        //声明装顶级节点的容器
        List<Resource> rootNodes = new ArrayList<Resource>();
        //迭代源节点
        for (Iterator<Resource> i = allNodes.iterator(); i.hasNext();) {
            //迭代树
            Resource resource = (Resource) i.next();
            if(resourceMap.containsKey(resource.getId()+"")){
                resource.setChecked(true);
            }
            resource.setExpand(false);
            //构建临时搜索对象 -> 为了进行二分查找父节点，所使用的临时对象
            Resource tmpSearch = new Resource();
            tmpSearch.setId(resource.getParentId());
            int searchIndex = Collections.binarySearch(tmpNodes, tmpSearch);
            if(searchIndex >= 0){
                //搜索到的节点，为当前节点的父节点
                Resource searchedObj = tmpNodes.get(searchIndex);
                //添加当前节点到其父节点的children下面
                searchedObj.addChild(resource);
            }else{
                //找不到父节点了，认为其为顶级节点
                rootNodes.add(resource);
            }
        }
        //返回获取的顶级节点
        return rootNodes;
    }
}
