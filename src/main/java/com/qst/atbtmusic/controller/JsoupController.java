package com.qst.atbtmusic.controller;

import com.qst.atbtmusic.mapper.SongListMapper;
import com.qst.atbtmusic.mapper.SongMapper;
import com.qst.atbtmusic.method.JsoupWorm;
import com.qst.atbtmusic.pojo.Satisfaction;
import com.qst.atbtmusic.pojo.Song;
import com.qst.atbtmusic.pojo.SongList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Random;

@Controller
@ResponseBody
public class JsoupController {

    @Autowired
    SongMapper songMapper;

    @Autowired
    SongListMapper songListMapper;
    @RequestMapping(value = "/j")
    public String jsoup() throws IOException {
     /*   for (int i=0;i<10;i++){
            SongList songList=new SongList();
            songList.setSongListName("网络歌曲"+(i+1));
            songList.setUserId(6);
            Random r1=new Random();
            Song song=songMapper.selectById(r1.nextInt(3000)+1);
            songList.setSongListImg(song.getSongImg());
            songList.setSongListType("网络歌曲");
            songList.setSongListDesc("歌曲描述");
            songListMapper.addSongList(songList);
            for(int u=0;u<20;u++){
                Random r=new Random();
                 songListMapper.addsongs(r.nextInt(6000)+1,u+5);
            }
        }*/
     for(int i=2;i<30;i++){
         for(int u=0;u<20;u++){
             Satisfaction satisfaction=new Satisfaction();
             while (1==1){
                 Random r1=new Random();
                 int songid=r1.nextInt(200)+1;
                 Random r2=new Random();
                 int grade=r2.nextInt(5)+1;
                 satisfaction.setSongId(songid);
                 satisfaction.setUserId(i);
                 satisfaction.setSatifaction(grade);
                 if(songMapper.judgegrade(satisfaction)<1){
                     songMapper.grade(satisfaction);
                     break;
                 }
             }
         }
     }

        for(int u=0;u<20;u++){
            Satisfaction satisfaction=new Satisfaction();
            while (1==1){
                Random r1=new Random();
                int songid=r1.nextInt(200)+1;
                Random r2=new Random();
                int grade=r2.nextInt(5)+1;
                satisfaction.setSongId(songid);
                satisfaction.setUserId(19);
                satisfaction.setSatifaction(grade);
                if(songMapper.judgegrade(satisfaction)<1){
                    songMapper.grade(satisfaction);
                    break;
                }
            }
        }
        return "查询成功";
    }
}
