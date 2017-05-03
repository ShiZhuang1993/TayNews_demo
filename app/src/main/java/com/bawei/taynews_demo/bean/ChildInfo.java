package com.bawei.taynews_demo.bean;

/**
 * 类用途:
 * 作者:崔涵淞
 * 时间: 2017/4/11 15:55.
 */

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * onCreated = "sql"：当第一次创建表需要插入数据时候在此写sql语句
 */
@Table(name = "child_info")
public class ChildInfo {
    /**
     * name = "id"：数据库表中的一个字段
     * isId = true：是否是主键
     * autoGen = true：是否自动增长
     * property = "NOT NULL"：添加约束
     */
    @Column(name = "id",isId = true,autoGen = true,property = "NOT NULL")
    private int id;
    @Column(name = "nid")
    private int nid;
    @Column(name = "title")
    private String title;
    @Column(name = "uri")
    private String uri;
    @Column(name = "state")
    private int state ;

    public ChildInfo(int nid, String title, String uri,int state) {
        this.nid = nid;
        this.title = title;
        this.uri = uri;
        this.state =state;

    }
    //默认的构造方法必须写出，如果没有，这张表是创建不成功的
    public ChildInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "ChildInfo{" +
                "id=" + id +
                ", nid=" + nid +
                ", title='" + title + '\'' +
                ", state=" + state +
                ", uri='" + uri + '\'' +
                '}';
    }
}
