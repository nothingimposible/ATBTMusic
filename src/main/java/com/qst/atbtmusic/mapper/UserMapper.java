package com.qst.atbtmusic.mapper;

import com.qst.atbtmusic.pojo.Attention;
import com.qst.atbtmusic.pojo.Song;
import com.qst.atbtmusic.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

public interface UserMapper {
    //注册
    @Options(useGeneratedKeys = true,keyProperty = "user_id")
    @Insert("insert into tb_user (userId,userName,userPassword,usereMail,userImg,userPrivilege,userVip,userTime) values (#{userId},#{userName},#{userPassword},#{userEmail},#{userImg},#{userPrivilege},#{userVip},#{userTime})")
    public int register(User user);

    //获取用户信息
    @Select("select * from tb_user where userEmail=#{userEmail} and userPassword=#{userPassword}")
    public User getUserMsg(User user);

    //登录
    @Select("select count(*) from tb_user where userEmail=#{userEmail} and userPassword=#{userPassword}")
    public int login(User user);

    //关注用户
    @Insert("insert into tb_attention (userId,attentionId) values(#{userId},#{attentionId})")
    public void attention(Attention attention);
    //我的粉丝
    @Select("SELECT * FROM tb_user HAVING user_id IN (SELECT userId from tb_attention WHERE attentionId=#{userId})")
    public ArrayList<User> myfans(User user);
    //我的关注
    @Select("SELECT * FROM tb_user HAVING user_id IN (SELECT attentionId from tb_attention WHERE userId=#{userId})")
    public ArrayList<User> myattentions(User user);

    //获取我喜欢的歌列表
    @Select("SELECT * FROM tb_like,tb_song WHERE tb_like.songId=tb_song.songId AND tb_like.userId=#{userid}")
    public ArrayList<Song> likeSongList(@Param("userid") int userid);

    //名字查询用户
    @Select("select * from tb_user where userName like '%${name}%'")
    public ArrayList<User> selectByName(@Param("name")String name);

    @Select("select * from tb_user")
    public ArrayList<User> userlist();
    /*设置为VIP用户*/
    @Update("update tb_user set userVip=2 where userId = #{id}")
    public void setVip(int id);

}
