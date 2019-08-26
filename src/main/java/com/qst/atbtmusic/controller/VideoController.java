package com.qst.atbtmusic.controller;

import com.qst.atbtmusic.mapper.VideoMapper;
import com.qst.atbtmusic.method.SaveFile;
import com.qst.atbtmusic.pojo.Video;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.DataFormatException;

@Controller
@RequestMapping(value = "/video")
public class VideoController {

    @Autowired
    VideoMapper videoMapper;

    @RequestMapping("/videoupload")
    public void addVideo(Video video,@RequestParam(value = "videoImg") MultipartFile videoimg,@RequestParam(value = "videoAddress") MultipartFile videoaddress){
        Video vd=video;
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String vdimg=new SaveFile().SaveFile(videoimg,"videoimg");
        String vdaddress=new SaveFile().SaveFile(videoaddress,"video");
        vd.setVideoAddress(vdaddress);
        vd.setVideoImg(vdimg);
        vd.setVideoTime(simpleDateFormat.format(date));
        videoMapper.addVideo(vd);
    }


    @RequestMapping("/selectvideo")
    public void selectvideo(@RequestParam("videoName")String videoname, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;chatset=utf-8");
        response.setCharacterEncoding("utf-8");
        ArrayList<Video> videos=videoMapper.selectByName(videoname);
        JSONArray jsonArray=JSONArray.fromObject(videos);
        response.getWriter().write(jsonArray.toString());
    }
}
