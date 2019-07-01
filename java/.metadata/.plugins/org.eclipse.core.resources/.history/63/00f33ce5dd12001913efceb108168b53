package com.fsq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.fsq.beans.PhotoClass;
import com.fsq.util.JdbcUtil;
import com.fsq.util.Pager;

public class PhotoClassDAO {	

	public List<PhotoClass> getAllClass() {
		List<PhotoClass> photoClassList = new ArrayList<PhotoClass>();
		ResultSet rs = null;

		PreparedStatement ps = null;
		try {
			Connection conn = JdbcUtil.getConnection();
			String sql = "select * from leibie";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				PhotoClass photoClass = new PhotoClass();
				photoClass.setId(rs.getInt(1));
				photoClass.setName(rs.getString(2));
				photoClassList.add(photoClass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, ps);
			JdbcUtil.close();
		}
		return photoClassList;

	}

	public Pager getClassesByPage(int pageSize, int pageNo) {
		List<PhotoClass> photoClassList = new ArrayList<PhotoClass>();
		int rowNum = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlCount = "select count(*) from leibie";
		String sqlResult = "select *, count(photo.id) as num from leibie "
				+ "left join photo on leibie.id=photo.lid group by leibie.id limit ?,?";
		try {
			Connection conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sqlCount);
			rs = ps.executeQuery();
			if (rs.next()) {
				rowNum = rs.getInt(1);
			}
			ps = conn.prepareStatement(sqlResult);
			ps.setInt(1, (pageNo - 1) * pageSize);
			ps.setInt(2, pageNo * pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				PhotoClass photoClass = new PhotoClass();
				photoClass.setId(rs.getInt("id"));
				photoClass.setName(rs.getString("name"));
				photoClass.setShuoming(rs.getString("shuoming"));
				photoClass.setPhotoNum(rs.getInt("num"));
				photoClassList.add(photoClass);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, ps);
			JdbcUtil.close();
		}
		Pager pager = new Pager(pageSize, pageNo, rowNum, photoClassList);
		return pager;
	}

	public void insert(PhotoClass PhotoClass) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "insert into leibie (name,shuoming,contenttime) values(?,?,?)";
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, PhotoClass.getName());
			ps.setString(2, PhotoClass.getShuoming());
			ps.setString(3, PhotoClass.getContenttime());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcUtil.close(null, ps);
			JdbcUtil.close();
		}
	}

	public void update(PhotoClass photoClass) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "update leibie set name=?,shuoming=?,contenttime=? where id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, photoClass.getName());
			ps.setString(2, photoClass.getShuoming());
			ps.setString(3, photoClass.getContenttime());
			ps.setInt(4, photoClass.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcUtil.close(null, ps);
			JdbcUtil.close();
		}
	}

	public void delete(PhotoClass photoClass) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "delete  from leibie where id=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, photoClass.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcUtil.close(null, ps);
			JdbcUtil.close();
		}
	}

	public PhotoClass findClassById(int id) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select id,name,shuoming,contenttime from leibie where id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				PhotoClass photoClass = new PhotoClass();
				photoClass.setId(rs.getInt(1));
				photoClass.setName(rs.getString(2));
				photoClass.setShuoming(rs.getString(3));
				photoClass.setContenttime(rs.getString(4));
				return photoClass;
			}
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcUtil.close(rs, ps);
			JdbcUtil.close();
		}

	}

}