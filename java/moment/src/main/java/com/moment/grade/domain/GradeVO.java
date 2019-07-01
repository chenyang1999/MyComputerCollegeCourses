package com.moment.grade.domain;

import java.io.Serializable;

public class GradeVO implements Serializable {
    private Integer id;

    private String grade;

    private String description;

    private Integer picnum;

    private Integer uploadnum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getPicnum() {
        return picnum;
    }

    public void setPicnum(Integer picnum) {
        this.picnum = picnum;
    }

    public Integer getUploadnum() {
        return uploadnum;
    }

    public void setUploadnum(Integer uploadnum) {
        this.uploadnum = uploadnum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", grade=").append(grade);
        sb.append(", description=").append(description);
        sb.append(", picnum=").append(picnum);
        sb.append(", uploadnum=").append(uploadnum);
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
        GradeVO other = (GradeVO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGrade() == null ? other.getGrade() == null : this.getGrade().equals(other.getGrade()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getPicnum() == null ? other.getPicnum() == null : this.getPicnum().equals(other.getPicnum()))
            && (this.getUploadnum() == null ? other.getUploadnum() == null : this.getUploadnum().equals(other.getUploadnum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGrade() == null) ? 0 : getGrade().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getPicnum() == null) ? 0 : getPicnum().hashCode());
        result = prime * result + ((getUploadnum() == null) ? 0 : getUploadnum().hashCode());
        return result;
    }
}