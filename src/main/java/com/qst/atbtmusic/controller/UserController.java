package com.qst.atbtmusic.controller;

import com.qst.atbtmusic.email.Emailcheck;
import com.qst.atbtmusic.mapper.*;
import com.qst.atbtmusic.method.SaveFile;
import com.qst.atbtmusic.pojo.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public SongListMapper songListMapper;

    @Autowired
    public VideoMapper videoMapper;

    @Autowired
    public SongMapper songMapper;

    @Autowired
    public SingerMapper singerMapper;

    public User getUser(){
        HttpServletRequest servletRequest = null;
        HttpSession session = servletRequest.getSession();
       User user= (User) session.getAttribute("user");
       return user;
    }
     //登录页面
    @RequestMapping("/login")
    public String login(HttpSession session) {
        System.out.println(userMapper);
        session.setAttribute("user","123");
        return "login";
    }
    //登录判断
    @RequestMapping("/loginjudge")
    public void loginjudge(User user, HttpSession session, HttpServletResponse response) throws IOException {
        System.out.println("跳到登录后台"+user.toString());
        response.setContentType("text/html;chatset=utf-8");
        response.setCharacterEncoding("utf-8");
        User us1=new User();
        int us=userMapper.login(user);
        System.out.println(us);
        if(us>0){
            //登录成功
            us1=userMapper.getUserMsg(user);
            System.out.println("登录成功"+userMapper.getUserMsg(user));
            session.setAttribute("user",us1);
            JSONObject jbUser = JSONObject.fromObject(us1);
            System.out.println("jb_user = " + jbUser.toString());
            response.getWriter().write(jbUser.toString());
        }else{
            //登录失败

        }
    }

    /*用户喜欢的歌*/
    @RequestMapping("/likesongs")
    public void likesongs(@RequestParam("userId") int id,HttpServletResponse response) throws IOException {
        response.setContentType("text/html;chatset=utf-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("喜欢歌曲用户id="+id);
        if (id<1){
            id=29;
        }
       // User us=getUser();
        ArrayList<Song> afans=userMapper.likeSongList(id);
      //  ArrayList<Song> afans=songMapper.rangkinglist(10);
        System.out.println(afans.size());
        System.out.println("afans = " + afans);
        ArrayList<SongAndSinger> songAndSingers=new ArrayList<SongAndSinger>();
        for(Song e:afans){
            Singer singer=singerMapper.selectById(e.getSingerId());
            SongAndSinger songAndSinger=new SongAndSinger();
            songAndSinger.copy(e,singer);
          //  System.out.println("songAndSinger = " + songAndSinger.toString());
            songAndSingers.add(songAndSinger);
        }
        JSONArray jsonArray=JSONArray.fromObject(songAndSingers);
        System.out.println("用户喜欢的歌jsonArray = " + jsonArray);
       // System.out.println("jsonArray = " + jsonArray.toString());
        response.getWriter().write(jsonArray.toString());
    }
    //用户的粉丝
    @RequestMapping(value = "/fans")
    public void fans(@RequestParam("userId") int id,HttpServletResponse response) throws IOException {
//用户粉丝
        response.setContentType("text/html;chatset=utf-8");
        response.setCharacterEncoding("utf-8");
        User us=getUser();
        ArrayList<User> afans=userMapper.myfans(us);
        JSONArray fans= JSONArray.fromObject(afans);
        response.getWriter().write(fans.toString());
    }
    //用户的关注
    @RequestMapping(value = "/focus")
    public void attention(@RequestParam("userId") int id,HttpServletResponse response) throws IOException {
        User us=getUser();
        System.out.println("用户关注id = " + id);
        response.setContentType("text/html;chatset=utf-8");
        response.setCharacterEncoding("utf-8");
        ArrayList<User> afans=userMapper.myattentions(us);
        System.out.println("用户管住宿afans = " + afans);
        JSONArray focus= JSONArray.fromObject(afans);
        response.getWriter().write(focus.toString());
    }
    //用户创建的歌单
    @RequestMapping(value = "/songlist")
    public void songList(@RequestParam("userId") int id,HttpServletResponse response) throws IOException {
        User us=getUser();
        response.setContentType("text/html;chatset=utf-8");
        response.setCharacterEncoding("utf-8");
        ArrayList<SongList> afans=songListMapper.selectByUser(us);
        JSONArray songlist= JSONArray.fromObject(afans);
        response.getWriter().write(songlist.toString());
    }

    //上传的MV
    @RequestMapping(value = "/mvlist")
    public void mvList(@RequestParam("userId") int id,HttpServletResponse response) throws IOException {
        User us=getUser();
        response.setContentType("text/html;chatset=utf-8");
        response.setCharacterEncoding("utf-8");
        Singer singer=singerMapper.selectById(us.getUserId());
        ArrayList<Video> afans=videoMapper.selectBySinger(singer);
        JSONArray mvlist= JSONArray.fromObject(afans);
        response.getWriter().write(mvlist.toString());
    }

    //发送验证码
    @RequestMapping(value = "/email")
    public void email(@RequestParam("userEmail") String user_mail) throws Exception {
        new Emailcheck().sendMail(user_mail,"");
    }
    //注册
    @RequestMapping(value = "/registerput")
    public void registerput(User user, @RequestParam(value = "userImg") MultipartFile file){
        System.out.println("注册");
        User us=user;
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String time=dateFormat.format(date);
        System.out.println(dateFormat.format(date));
        //头像上传
        String imgname=new SaveFile().SaveFile(file,"userimg");
        us.setUserTime(time);
        us.setUserPrivilege(1);
        us.setUserVip(1);
        us.setUserImg(imgname);
        userMapper.register(us);
    }

    //关注
    public void attention(@RequestParam("id")int id,HttpSession session){
        User user= (User) session.getAttribute("user");
        Attention attention=new Attention();
        attention.setUserId(user.getUserId());
        attention.setAttentionId(id);
        userMapper.attention(attention);
    }
}
