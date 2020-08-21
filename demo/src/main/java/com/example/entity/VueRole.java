package com.example.entity;

import java.io.Serializable;
import java.util.Date;

public class VueRole implements Serializable {
    private Integer id;

    private String roleName;

    private String roleSequence;

    private Integer roleStatus;

    private Integer roleType;

    private String roleSummary;

    private Integer roleSuper;

    private String roleRemark;

    private Date roleAddtime;

    private String roleAddip;

    private String channel;

    private String rolePurview;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleSequence() {
        return roleSequence;
    }

    public void setRoleSequence(String roleSequence) {
        this.roleSequence = roleSequence == null ? null : roleSequence.trim();
    }

    public Integer getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public String getRoleSummary() {
        return roleSummary;
    }

    public void setRoleSummary(String roleSummary) {
        this.roleSummary = roleSummary == null ? null : roleSummary.trim();
    }

    public Integer getRoleSuper() {
        return roleSuper;
    }

    public void setRoleSuper(Integer roleSuper) {
        this.roleSuper = roleSuper;
    }

    public String getRoleRemark() {
        return roleRemark;
    }

    public void setRoleRemark(String roleRemark) {
        this.roleRemark = roleRemark == null ? null : roleRemark.trim();
    }

    public Date getRoleAddtime() {
        return roleAddtime;
    }

    public void setRoleAddtime(Date roleAddtime) {
        this.roleAddtime = roleAddtime;
    }

    public String getRoleAddip() {
        return roleAddip;
    }

    public void setRoleAddip(String roleAddip) {
        this.roleAddip = roleAddip == null ? null : roleAddip.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public String getRolePurview() {
        return rolePurview;
    }

    public void setRolePurview(String rolePurview) {
        this.rolePurview = rolePurview == null ? null : rolePurview.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleName=").append(roleName);
        sb.append(", roleSequence=").append(roleSequence);
        sb.append(", roleStatus=").append(roleStatus);
        sb.append(", roleType=").append(roleType);
        sb.append(", roleSummary=").append(roleSummary);
        sb.append(", roleSuper=").append(roleSuper);
        sb.append(", roleRemark=").append(roleRemark);
        sb.append(", roleAddtime=").append(roleAddtime);
        sb.append(", roleAddip=").append(roleAddip);
        sb.append(", channel=").append(channel);
        sb.append(", rolePurview=").append(rolePurview);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}