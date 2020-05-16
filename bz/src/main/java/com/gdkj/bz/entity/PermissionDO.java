package com.gdkj.bz.entity;

public class PermissionDO {
    private Integer id;

    private Integer perId;

    private String perName;

    private String perContext;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPerId() {
        return perId;
    }

    public void setPerId(Integer perId) {
        this.perId = perId;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName == null ? null : perName.trim();
    }

    public String getPerContext() {
        return perContext;
    }

    public void setPerContext(String perContext) {
        this.perContext = perContext == null ? null : perContext.trim();
    }
}