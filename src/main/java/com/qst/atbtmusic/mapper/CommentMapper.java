package com.qst.atbtmusic.mapper;

import com.qst.atbtmusic.pojo.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface CommentMapper {
    //添加评论
    @Options(useGeneratedKeys = true,keyProperty = "commentId")
    @Insert("insert into tb_comment (commentId,commentDesc,userId,songId,commentTime) values (#{commentId},#{commentDesc},#{userId},#{songId},#{commentTime})")
    public void comment(Comment comment);
    //一首歌的评论列表
    @Select("select * from tb_comment where songId=#{id}")
    public ArrayList<Comment> commentlist(int id);
}
