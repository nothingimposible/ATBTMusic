package com.qst.atbtmusic.controller;

import com.qst.atbtmusic.mapper.SingerMapper;
import com.qst.atbtmusic.mapper.SongMapper;
import com.qst.atbtmusic.method.SaveFile;
import com.qst.atbtmusic.method.UserCFDemo;
import com.qst.atbtmusic.pojo.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/song")
public class SongController {

    @Autowired
    SongMapper songMapper;

    @Autowired
    SingerMapper singerMapper;

    public void getSongList(HttpServletResponse response,ArrayList<Song> songs) throws IOException {
        ArrayList<SongAndSinger> songAndSinger= new ArrayList<SongAndSinger>();
        for(Song e:songs){
            Singer singer=singerMapper.selectById(e.getSingerId());
            SongAndSinger s1=new SongAndSinger();
            s1.copy(e,singer);
            songAndSinger.add(s1);
            System.out.println("s1 = " + s1);
        }

        JSONArray jsonArray=JSONArray.fromObject(songAndSinger);
        response.getWriter().write(jsonArray.toString());
    }
//上传歌曲
    @RequestMapping("/upload")
    public void uploadsong(Song song, @RequestParam("song_img") MultipartFile file, HttpSession session){
        String address=new SaveFile().SaveFile(file,"songimg");
        //发布时间
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(dateFormat.format(date));
        User user= (User) session.getAttribute("user");
        Song song1=song;
        song1.setSongImg(address);
        song1.setSongTime(dateFormat.format(date));
        song1.setSingerId(user.getUserId());
        songMapper.addSong(song1);
    }
    @RequestMapping(value = "/getsong")
    public void getSong(@RequestParam("songId")int songId, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;chatset=utf-8");
        response.setCharacterEncoding("utf-8");
        SongAndSinger songAndSinger=new SongAndSinger();
        Song song=songMapper.selectById(songId);
        /*播放加一*/
        songMapper.playernum();
        Singer singer=singerMapper.selectById(song.getSingerId());
        songAndSinger.copy(song,singer);
        JSONObject jsonObject=JSONObject.fromObject(songAndSinger);
        response.getWriter().write(jsonObject.toString());
    }

    /*新歌推荐*/
    @RequestMapping(value = "/newsongs")
    public void newSongs(@RequestParam("songType")String type,HttpServletResponse response) throws IOException {
        response.setContentType("text/html;chatset=utf-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("type = " + type);
        ArrayList<Song> songs=songMapper.newSongs(type,9);
       getSongList(response,songs);
    }

    /*添加到我喜欢*/
    @RequestMapping("/addlike")
    public void addlike(@RequestParam("songId")int songId,@RequestParam("userId")int userId){
        songMapper.addLikeSong(userId,songId);
    }

    /*移除我喜欢的歌*/
    @RequestMapping("/deletelike")
    public void deletelike(@RequestParam("songId")int songId,@RequestParam("userId")int userId){
        songMapper.deleteLikeSong(userId,songId);
    }

    /*猜你喜欢歌曲*/
    @RequestMapping("/youlike")
     public void youlike(@RequestParam("userId")int id,HttpServletResponse response) throws IOException, TasteException {
        response.setContentType("text/html;chatset=utf-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("你喜欢的歌id = " + id);
       if (id<1){
           id=29;
       }
       UserCFDemo userCFDemo= new UserCFDemo();
       userCFDemo.inputStrem(songMapper.allSatisfaction());
       ArrayList<Integer> songid= userCFDemo.CFDemo(id);
       ArrayList<SongAndSinger> songAndSingers=new ArrayList<SongAndSinger>();
       for(Integer e:songid){
           Song song=songMapper.selectById(e);
           Singer singer=singerMapper.selectById(song.getSingerId());
           SongAndSinger s1=new SongAndSinger();
           s1.copy(song,singer);
           songAndSingers.add(s1);
           System.out.println("s1 = " + s1);
       }
       JSONArray jsonArray=JSONArray.fromObject(songAndSingers);
       response.getWriter().write(jsonArray.toString());
    }

    /*是否是我喜欢列表里的歌*/
    @RequestMapping("/judgelike")
    public void judgelike(@RequestParam("songId")int songId,@RequestParam("userId")int userId,HttpServletResponse response) throws IOException {
       int p=0;
       if (songMapper.judgelike(songId,userId)>0) {
           p=1;
       }
       response.getWriter().write(p);
    }

    /*搜索歌曲*/
    @RequestMapping("/search")
    public void search(@RequestParam("songName")String name,HttpServletResponse response) throws IOException {
        response.setContentType("text/html;chatset=utf-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("name = " + name);
        ArrayList<Song> songs=songMapper.selectByName(name);
       getSongList(response,songs);
    }

    /*通过歌手id查询歌曲*/
    @RequestMapping(value = "/selectbysinger")
    public void selectbysinger(@RequestParam("singerId")int id,HttpServletResponse response) throws IOException {
        response.setContentType("text/html;chatset=utf-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("通过歌手id = " + id);
        ArrayList<Song> songs=songMapper.selectBySinger(id);
        getSongList(response,songs);
    }

    /*歌曲打分*/
    @RequestMapping("/grade")
    public void grade(Satisfaction satisfaction){
        songMapper.grade(satisfaction);
    }

    @RequestMapping("/play")
    public String play(){
        return "tryScroll";
    }

}
