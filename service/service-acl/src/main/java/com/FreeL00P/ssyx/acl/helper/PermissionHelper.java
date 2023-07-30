package com.FreeL00P.ssyx.acl.helper;

/**
 * PermissionHelper
 *
 * @author fj
 * @since 2023/7/30 10:14
 */

import com.FreeL00P.ssyx.model.acl.Permission;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 根据权限构建菜单数据
 */
public class PermissionHelper {

    /**
     * 使用递归方法建菜单
     * @param treeNodes
     * @return
     */
    public static List<Permission> bulid(List<Permission> treeNodes) {
       //筛选出所有的父节点
        List<Permission> trees=new ArrayList<>();
        for (Permission treeNode : treeNodes) {
            if (treeNode.getPid()==0){
                treeNode.setLevel(1);
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static Permission findChildren(Permission treeNode, List<Permission> treeNodes) {
        treeNode.setChildren(new ArrayList<Permission>());
        for (Permission node : treeNodes) {
            if (node.getPid().longValue()==treeNode.getId().longValue()){
                node.setLevel(treeNode.getLevel()+1);
                if (treeNode.getChildren()==null){
                    treeNode.setChildren(new ArrayList<Permission>());
                }
                treeNode.getChildren().add(findChildren(node,treeNodes));
            }
        }
        return treeNode;
    }
}
