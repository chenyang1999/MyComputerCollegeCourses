package com.moment.comment.domain;

import java.io.Serializable;
import java.util.Date;

public class CommentVO implements Serializable {
    private Integer id;

    private Integer userid;

    private String username;
    
    private String userimg;
    
    private String content;

    private Integer picid;

    private Integer commentid;
    
    private String commentname;
    
    public String getUserimg() {
		return userimg;
	}

	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}

	public String getCommentname() {
		return commentname;
	}

	public void setCommentname(String commentname) {
		this.commentname = commentname;
	}

	private Date time;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getPicid() {
        return picid;
    }

    public void setPicid(Integer picid) {
        this.picid = picid;
    }

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
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
        sb.append(", userid=").append(userid);
        sb.append(", username=").append(username);
        sb.append(", userimg=").append(userimg);
        sb.append(", content=").append(content);
        sb.append(", picid=").append(picid);
        sb.append(", commentid=").append(commentid);
        sb.append(", commentname=").append(commentname);
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
        CommentVO other = (CommentVO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getUserimg() == null ? other.getUserimg() == null : this.getUserimg().equals(other.getUserimg()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getPicid() == null ? other.getPicid() == null : this.getPicid().equals(other.getPicid()))
            && (this.getCommentid() == null ? other.getCommentid() == null : this.getCommentid().equals(other.getCommentid()))
            && (this.getCommentname() == null ? other.getCommentname() == null : this.getCommentname().equals(other.getCommentname()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getUserimg() == null) ? 0 : getUserimg().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getPicid() == null) ? 0 : getPicid().hashCode());
        result = prime * result + ((getCommentid() == null) ? 0 : getCommentid().hashCode());
        result = prime * result + ((getCommentname() == null) ? 0 : getCommentname().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        return result;
    }
}