package com.qst.atbtmusic.mapper;

import com.qst.atbtmusic.pojo.Singer;
import com.qst.atbtmusic.pojo.Video;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface VideoMapper {

    //上传MV
    @Options(useGeneratedKeys = true,keyProperty = "videoId")
    @Insert("insert into tb_user (videoId,videoDesc,videoTime,videoNum,videoAddress,songId) values (#{videoId},#{videoDesc},#{videoTime},#{videoNum},#{videoAddress},#{songId})")
    public void addVideo(Video video);

    //通过名字查找MV
    @Select("select * from tb_video where videoDesc like '%${name}%'")
    public ArrayList<Video> selectByName(String name);

    //通过歌手查询MV
    @Select("select * from tb_video where singerId=#{singerId}")
    public ArrayList<Video> selectBySinger(Singer singer);

    /*id获取MV*/
    @Select("select * from tb_video where videoId=#{id}")
    public ArrayList<Video> selectById(int id);
}
