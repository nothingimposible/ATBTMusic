package com.qst.atbtmusic.controller;

import com.qst.atbtmusic.mapper.SingerMapper;
import com.qst.atbtmusic.mapper.SongListMapper;
import com.qst.atbtmusic.pojo.Singer;
import com.qst.atbtmusic.pojo.Song;
import com.qst.atbtmusic.pojo.SongAndSinger;
import com.qst.atbtmusic.pojo.SongList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/songlist")
public class SongListController {
    @Autowired
    SongListMapper songListMapper;

    @Autowired
    SingerMapper singerMapper;

    public void getSongList(HttpServletResponse response,ArrayList<Song> songs) throws IOException {
        ArrayList<SongAndSinger> songAndSinger= new ArrayList<SongAndSinger>();
        for(Song e:songs){
            Singer singer=singerMapper.selectById(e.getSingerId());
            SongAndSinger s1=new SongAndSinger();
            s1.copy(e,singer);
            songAndSinger.add(s1);
        }
        JSONArray jsonArray=JSONArray.fromObject(songAndSinger);
        response.getWriter().write(jsonArray.toString());
    }
    @RequestMapping("/getbyid")
    public void getbuid(@RequestParam("songListId")int id, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;chatset=utf-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("id = " + id);
        SongList songList=songListMapper.selectById(id);
        JSONObject jsonObject=JSONObject.fromObject(songList);
        response.getWriter().write(jsonObject.toString());
    }

    @RequestMapping("/getbytype")
    public void getbytype(@RequestParam("songListType")String type, HttpServletResponse response) throws IOException {
        System.out.println("type = " + type);
        response.setContentType("text/html;chatset=utf-8");
        response.setCharacterEncoding("utf-8");
        ArrayList<SongList> songList=songListMapper.selectByType(type);
        JSONArray jsonObject=JSONArray.fromObject(songList);
        System.out.println("jsonObject = " + jsonObject);
        response.getWriter().write(jsonObject.toString());
    }

    @RequestMapping("/getsongs")
    public void getsongs(@RequestParam("songListId")int id, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;chatset=utf-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("歌单id = " + id);
        ArrayList<Song> songs=songListMapper.getsongs(id);
        System.out.println("songs = " + songs);
        getSongList(response,songs);
    }
}
