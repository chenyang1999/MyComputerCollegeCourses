package com.moment.concern.domain;

import java.io.Serializable;
import java.util.Date;

public class ConcernVO implements Serializable {
    private Integer id;

    private Integer watchuserid;

    private Integer bewatchuserid;

    private Date time;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWatchuserid() {
        return watchuserid;
    }

    public void setWatchuserid(Integer watchuserid) {
        this.watchuserid = watchuserid;
    }

    public Integer getBewatchuserid() {
        return bewatchuserid;
    }

    public void setBewatchuserid(Integer bewatchuserid) {
        this.bewatchuserid = bewatchuserid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", watchuserid=").append(watchuserid);
        sb.append(", bewatchuserid=").append(bewatchuserid);
        sb.append(", time=").append(time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ConcernVO other = (ConcernVO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getWatchuserid() == null ? other.getWatchuserid() == null : this.getWatchuserid().equals(other.getWatchuserid()))
            && (this.getBewatchuserid() == null ? other.getBewatchuserid() == null : this.getBewatchuserid().equals(other.getBewatchuserid()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getWatchuserid() == null) ? 0 : getWatchuserid().hashCode());
        result = prime * result + ((getBewatchuserid() == null) ? 0 : getBewatchuserid().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        return result;
    }
}