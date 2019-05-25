package com.trip.base.service;

import com.trip.base.dao.OrgDao;
import com.trip.base.entity.Org;
import com.trip.commons.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 业务处理
 * @author fqh
 * @create 2017-02-19 12:00:27
 */

@Service
public class OrgService extends BaseService<Org> {

    @Autowired
    private OrgDao orgDao;

    public List<Org> tree() throws Exception {
        //首先建立一个用来搜索的list，复制元素内容与allNodes一样，先进行排序
        List<Org> tmpNodes = new ArrayList<Org>();
        List<Org> allNodes = super.findList(new Org());
        tmpNodes.addAll(allNodes);
        Collections.sort(tmpNodes);
        //声明装顶级节点的容器
        List<Org> rootNodes = new ArrayList<Org>();
        //迭代源节点
        for (Iterator<Org> i = allNodes.iterator(); i.hasNext();) {
            //迭代树
            Org org = (Org) i.next();
            org.setExpand(false);
            //构建临时搜索对象 -> 为了进行二分查找父节点，所使用的临时对象
            Org tmpSearch = new Org();
            tmpSearch.setId(org.getParentId());
            int searchIndex = Collections.binarySearch(tmpNodes, tmpSearch);
            if(searchIndex >= 0){
                //搜索到的节点，为当前节点的父节点
                Org searchedObj = tmpNodes.get(searchIndex);
                //添加当前节点到其父节点的children下面
                searchedObj.addChild(org);
            }else{
                //找不到父节点了，认为其为顶级节点
                rootNodes.add(org);
            }
        }
        //返回获取的顶级节点
        return rootNodes;
    }

    public List<Org> queryAllSonOrg(){
        return orgDao.queryAllSonOrg();
    }

}
