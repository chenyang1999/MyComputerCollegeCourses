package com.moment.piclike.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moment.common.domain.CurrentUser;
import com.moment.common.domain.JsonResult;
import com.moment.piclike.domain.PiclikeVO;
import com.moment.piclike.service.PiclikeService;

@Controller
@RequestMapping("/PiclikeController")
public class PiclikeController {
	@Autowired
	private PiclikeService piclikeservice;

	@RequestMapping("/dolike")
	public @ResponseBody JsonResult dolike(Integer picid) {
		JsonResult result = new JsonResult();
		CurrentUser cuser = CurrentUser.getInstance();
		int userid = cuser.getUserId();
		PiclikeVO pic = new PiclikeVO();
		try {
			     System.out.println(piclikeservice.getifPiclikeById(picid, userid) );
			if (piclikeservice.getifPiclikeById(picid, userid) == true) {
				pic.setPicid(picid);
				pic.setUserid(userid);
				piclikeservice.addPiclike(pic);
				result.setStatus(1);
				result.setMsg("点赞成功");
			} else {
				pic.setPicid(picid);
				int i = piclikeservice.deletePiclike(pic);

				result.setStatus(2);
				result.setMsg("取消成功");
			}
		} catch (Throwable e) {
			result.setStatus(0);
			result.setMsg("点赞失败");
			return result;
		}
		System.out.println(result);
		return result;
	}

	@RequestMapping("/getlikenumber")
	public int getlikenumber(Integer picid) {
		int likenumber = 0;
		try {
			likenumber = piclikeservice.getlikeNumberByPicId(picid);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return likenumber;
	}

	@RequestMapping("/getlike")
	public @ResponseBody List<PiclikeVO> getPicidAndUserid() {
		List<PiclikeVO> piclikelist = new ArrayList<PiclikeVO>();
		CurrentUser cuser = CurrentUser.getInstance();
		int userid = cuser.getUserId();
		try {
			piclikelist = piclikeservice.getPiclikeByUserId(userid);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return piclikelist;

	}

}
