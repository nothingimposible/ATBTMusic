package com.qst.atbtmusic.mapper;

import com.qst.atbtmusic.pojo.Singer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface SingerMapper {

    //申请入驻歌手
    @Insert("insert into tb_singer (singerId,singerName,singerType,singerImg,singerDesc) values (#{singerId},#{singerName},#{singerType},#{singerImg},#{singerDesc})")
    public void addSinger(Singer singer);

    //名字查询歌手
    @Select("select * from tb_singer where singerName like '%${name}%'")
    public ArrayList<Singer> selectIdByName(String name);

    //通过id查询歌手
    @Select("select * from tb_singer where singerId = #{id}")
    public Singer selectById(@Param("id") int id);

    /*按类型查询歌手*/
    @Select("select * from tb_singer where singerType like '%${type}%'")
    public ArrayList<Singer> selectByType(@Param("type")String type);
}
