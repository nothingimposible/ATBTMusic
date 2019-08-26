package com.qst.atbtmusic.mapper;

import com.qst.atbtmusic.pojo.Song;
import com.qst.atbtmusic.pojo.SongList;
import com.qst.atbtmusic.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface SongListMapper {

    //创建歌单
    @Options(useGeneratedKeys = true,keyProperty = "songlistId")
    @Insert("insert into tb_songlist (songListId,songListName,userId,songListImg,songListType,songListDesc,songListPlayTime) values (#{songListId},#{songListName},#{userId},#{songListImg},#{songListType},#{songListDesc},#{songListPlayTime})")
    public void addSongList(SongList songList);

    /*添加歌曲到歌单*/
    @Insert("insert into tb_songandsonglist (songId,songListId) values (#{songId},#{songListId})")
    public void addsongs(@Param("songId") int songId,@Param("songListId") int songListId);
    //通过用户查询歌单
    @Select("select * from tb_songlist where userId=#{userId}")
    public ArrayList<SongList> selectByUser(User user);

    //通过歌单ID查询歌单
    @Select("select * from tb_songlist where songListId=#{id}")
    public SongList selectById(int id);

    /*通过类型查找*/
    @Select("select * from tb_songlist where songListType = #{type} limit 0,5")
    public ArrayList<SongList> selectByType(String type);

    /*获取歌单里面的歌曲*/
    @Select("SELECT * FROM tb_song,tb_songandsonglist WHERE tb_song.songId=tb_songandsonglist.songId AND tb_songandsonglist.songListId=#{id}")
    public ArrayList<Song> getsongs(@Param("id")int id);
}
