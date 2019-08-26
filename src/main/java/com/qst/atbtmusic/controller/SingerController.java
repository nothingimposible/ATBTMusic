package com.qst.atbtmusic.controller;

import com.qst.atbtmusic.mapper.SingerMapper;
import com.qst.atbtmusic.method.SaveFile;
import com.qst.atbtmusic.pojo.Singer;
import com.qst.atbtmusic.pojo.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/singer")
public class SingerController {

    @Autowired
    SingerMapper singerMapper;
    //歌手入驻
    @RequestMapping(value = "/enter")
    public void Enter(Singer singer, @RequestParam("singer_img")MultipartFile file, HttpSession session){
        String singerimg=new SaveFile().SaveFile(file,"singerimg");
        User user= (User) session.getAttribute("user");
        singer.setSingerId(user.getUserId());
        singerMapper.addSinger(singer);
    }

    //歌手信息
    @RequestMapping("/message")
    public void Message(@RequestParam("singerId") int singerid, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;chatset=utf-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("singerid = " + singerid);
        Singer singer=singerMapper.selectById(singerid);
        JSONObject singermsg=JSONObject.fromObject(singer);
        System.out.println("qqqqqqqqqqqqqsingermsg = " + singermsg);
        response.getWriter().write(singermsg.toString());
    }
    /*按歌手类型获取歌手列表*/
    @RequestMapping("/singertype")
    public void singertype(HttpServletResponse response,@RequestParam("singerType")String type) throws IOException {
        response.setContentType("text/html;chatset=utf-8");
        response.setCharacterEncoding("utf-8");
        if("全部".equals(type)){
            type="";
        }
        ArrayList<Singer> singers=singerMapper.selectByType(type);
        JSONArray jsonArray=JSONArray.fromObject(singers);
        response.getWriter().write(jsonArray.toString());
    }

}
