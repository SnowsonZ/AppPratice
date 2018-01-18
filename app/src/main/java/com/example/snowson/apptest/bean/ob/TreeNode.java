package com.example.snowson.apptest.bean.ob;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

/**
 * Created by snowson on 18-1-18.
 */
@Entity
public class TreeNode {
    @Id
    public long id;
    public ToOne<TreeNode> parent;
    @Backlink
    public ToMany<TreeNode> children;
}
