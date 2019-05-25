package com.trip.admin.sys.action;

import com.trip.base.action.BaseAction;
import com.trip.base.aspect.ActionLog;
import com.trip.base.aspect.NoResp;
import com.trip.base.entity.Org;
import com.trip.base.service.OrgService;
import com.trip.base.service.UserService;
import com.trip.base.util.TreeNode;
import com.trip.base.util.TreeNodeUtil;
import com.trip.commons.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * 控制器
 *
 * @author fqh
 * @create 2017-01-30 19:05
 */
@Controller
@RequestMapping("org")
@ActionLog(module = "机构管理")
public class OrgAction extends BaseAction<Org> {
    @Autowired
    private OrgService orgService;

    @Autowired
    private UserService userService;


    @Override
    public BaseService getService() {
        return orgService;
    }
    @RequestMapping("tree")
    @NoResp
    @ResponseBody
    public Object tree() throws Exception {
        List<Org> list = orgService.tree();
        return list;
    }

    @RequestMapping("treeAll")
    @ResponseBody
    public Object treeAll() throws Exception {
        List<Org> list = orgService.findList(new Org());
        List<TreeNode> allNodes = new ArrayList<TreeNode>();
        if(list!=null&&list.size()>0){
            for (Org org : list) {
                TreeNode node = new TreeNode();
                node.setId(org.getId());
                node.setParentId(org.getParentId());
                node.setText(org.getOrgName());
                allNodes.add(node);
            }
        }
        return TreeNodeUtil.getRootTreeNodes(allNodes);
    }

    @RequestMapping("tree/async")
    @ResponseBody
    @NoResp
    public Object treeasync(Long id) throws Exception {
        Org org = new Org();
        List<Org> list = new ArrayList<Org>();
        org.setParentId(id);
        if(id!=null){
            list = orgService.findList(org);
        }else{
            org.setOrgName("组织机构");
            org.setId(0l);
            org.setParent(true);
            list.add(0,org);
        }
        return list;
    }
}
