package com.qst.atbtmusic.mapper;

import com.qst.atbtmusic.pojo.Satisfaction;
import com.qst.atbtmusic.pojo.Song;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;


public interface SongMapper {
    //上传歌曲
    @Options(useGeneratedKeys = true,keyProperty = "songId")
    @Insert("insert into tb_song (songId,singerId,songName,songImg,songAddress,songTime,songDesc,songType,songPlayernum) values (#{songId},#{singerId},#{songName},#{songImg},#{songAddress},#{songTime},#{songDesc},#{songType},#{songPlayernum})")
    public void addSong(Song song);
    //通过歌手查询歌曲
    @Select("select * from tb_song where singerId=#{sid}")
    public ArrayList<Song> selectBySinger(@Param("sid")int sid);
    //通过类型查询歌曲
    @Select("select * from tb_song where songType like '%${type}%'")
    public ArrayList<Song> selectByType(String type);
    //通过名字查询歌曲
    @Select("select * from tb_song where songName like '%${name}%'")
    public ArrayList<Song> selectByName(@Param("name") String name);
    //播放量设置0
    @Update("update tb_song set songPlayernum = 0")
    public void playernum();
    //通过歌单查询歌曲
    @Select("SELECT * FROM tb_song,tb_songlist,tb_songandsonglist WHERE tb_song.songId=tb_songandsonglist.songId AND tb_songlist.songlistId=tb_songandsonglist.singlistId AND tb_songlist.songlistId=#{id}")
    public ArrayList<Song> selectBySonglistId(int id);

    /*通过id查歌曲*/
    @Select("select * from tb_song where songId=#{id}")
    public Song selectById(int id);
    //播放量加1
    @Update("UPDATE tb_song SET songPlayernum=songPlayernum+1 WHERE songId = #{id}")
    public void playernumadd(int id);

    /*排行榜*/
    @Select("SELECT * FROM tb_song  WHERE lyric IS NOT NULL  ORDER BY songPlayernum DESC LIMIT 0,#{num}")
    public ArrayList<Song> rangkinglist(int num);

    /*类型排行榜*/
    @Select("SELECT * FROM tb_song,tb_singer WHERE tb_song.singerId=tb_singer.singerId AND tb_singer.singerType like '%${type}%' AND lyric IS NOT NULL ORDER BY songPlayernum DESC  LIMIT 0,#{num}")
    public ArrayList<Song> typerankinglist(String type,int num);

    /*获取所有歌*/
    @Select("select * from tb_song where songId>5506")
    public ArrayList<Song> allSongs();

    /*插入歌词*/
    @Update("update tb_song set lyric=#{lyric} where songId=#{id}")
    public void songWords(@Param("lyric") String lyric,@Param("id") int id);

    /*新歌首发按类型*/
    @Select("SELECT * FROM tb_song,tb_singer WHERE tb_song.singerId=tb_singer.singerId AND singerType LIKE '%${type}%' AND lyric IS NOT NULL ORDER BY songId ASC LIMIT 0,#{num}")
    public ArrayList<Song> newSongs(@Param("type") String type,@Param("num") int num);

    /*精彩推荐*/
    @Select("SELECT * FROM tb_song,tb_singer WHERE tb_song.singerId=tb_singer.singerId AND singerType LIKE '%${type}%' AND lyric IS NOT NULL ORDER BY  RAND() LIMIT #{num}")
    public ArrayList<Song> boutique(String type,int num);

    //添加我喜欢的歌
    @Insert("insert into tb_like (userId,songId) values (#{userid},#{songid})")
    public void addLikeSong(@Param("userid") int userid,@Param("songid") int songid);

    //移除我喜欢的歌
    @Insert("delete tb_like where userId=#{userid} and songId=#{songid}")
    public void deleteLikeSong(@Param("userid") int userid,@Param("songid") int songid);

    /*是否是我喜欢列表里的歌*/
    @Select("select count(*) from tb_like where userId=#{userId} and songId=#{songId}")
    public int judgelike(@Param("userId") int userId,@Param("songId") int songId);

    /*歌曲打分*/
    @Insert("insert into tb_satifaction (userId,songId,satifaction) values (#{userId},#{songId},#{satifaction})")
    public void grade(Satisfaction satisfaction);

    /*判断是否已经打分*/
    @Select("select count(*) from tb_satifaction where userId=#{userId} and songId=#{songId}")
    public int judgegrade(Satisfaction satisfaction);

    /*返回所有打分结果*/
    @Select("select * from tb_satifaction")
    public ArrayList<Satisfaction> allSatisfaction();
}
