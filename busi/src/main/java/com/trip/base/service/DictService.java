package com.trip.base.service;

import com.trip.base.dao.DictDao;
import com.trip.base.entity.Dict;
import com.trip.commons.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
public class DictService extends BaseService<Dict>{

	@Autowired
	private DictDao dictDao;

	public List<Dict> findByCode(String code){
		return dictDao.findByCode(code);
	}

	@Transactional
	public void removeByEntity(Dict t) {
		dictDao.removeByEntity(t);
		dictDao.removeByParentid(t.getParentId());
	}

	public List<Dict> tree() throws Exception {
		//首先建立一个用来搜索的list，复制元素内容与allNodes一样，先进行排序
		List<Dict> tmpNodes = new ArrayList<Dict>();
		List<Dict> allNodes = super.findList(new Dict());
		tmpNodes.addAll(allNodes);
		Collections.sort(tmpNodes);
		//声明装顶级节点的容器
		List<Dict> rootNodes = new ArrayList<Dict>();
		//迭代源节点
		for (Iterator<Dict> i = allNodes.iterator(); i.hasNext();) {
			//迭代树
			Dict Dict = (Dict) i.next();
			Dict.setExpand(false);
			//构建临时搜索对象 -> 为了进行二分查找父节点，所使用的临时对象
			Dict tmpSearch = new Dict();
			tmpSearch.setId(Dict.getParentId());
			int searchIndex = Collections.binarySearch(tmpNodes, tmpSearch);
			if(searchIndex >= 0){
				//搜索到的节点，为当前节点的父节点
				Dict searchedObj = tmpNodes.get(searchIndex);
				//添加当前节点到其父节点的children下面
				searchedObj.addChild(Dict);
			}else{
				//找不到父节点了，认为其为顶级节点
				rootNodes.add(Dict);
			}
		}
		//返回获取的顶级节点
		return rootNodes;
	}
}
