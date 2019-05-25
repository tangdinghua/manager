package com.trip.base.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TreeNodeUtil {
	
	/**
	 * 获取tree的顶级节点
	 * @return
	 */
	public static List<TreeNode> getRootTreeNodes(List<TreeNode> allNodes){
		if(allNodes==null){
			return null;
		}
		//首先建立一个用来搜索的list，复制元素内容与allNodes一样，先进行排序
		List<TreeNode> tmpNodes = new ArrayList<TreeNode>();
		tmpNodes.addAll(allNodes);
		Collections.sort(tmpNodes);
		//声明装顶级节点的容器
		List<TreeNode> rootNodes = new ArrayList<TreeNode>();
		//迭代源节点
		for (Iterator i = allNodes.iterator(); i.hasNext();) {
			//迭代树
			TreeNode treeNode = (TreeNode) i
					.next();
			//构建临时搜索对象 -> 为了进行二分查找父节点，所使用的临时对象
			TreeNode tmpSearch = new TreeNode();
			tmpSearch.setId(treeNode.getParentId());
			int searchIndex = Collections.binarySearch(tmpNodes, tmpSearch);
			if(searchIndex >= 0){
				//搜索到的节点，为当前节点的父节点
				TreeNode searchedObj = tmpNodes.get(searchIndex);
				//添加当前节点到其父节点的children下面
				searchedObj.addChild(treeNode);
			}else{
				//找不到父节点了，认为其为顶级节点
				rootNodes.add(treeNode);
			}
		}
		
		//返回获取的顶级节点
		return rootNodes;
	}
}
