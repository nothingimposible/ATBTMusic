
package com.qst.atbtmusic.spider;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.qst.atbtmusic.entity.SongMsg;
import com.qst.atbtmusic.mapper.SingerMapper;
import com.qst.atbtmusic.mapper.SongMapper;
import com.qst.atbtmusic.mapper.UserMapper;
import com.qst.atbtmusic.pojo.Singer;
import com.qst.atbtmusic.pojo.SingerIdList;
import com.qst.atbtmusic.pojo.Song;
import com.qst.atbtmusic.pojo.User;
import com.qst.atbtmusic.util.HttpClientUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/qq")
public class NetMusicGrab {
    private final static Log logger = LogFactory.getLog(NetMusicGrab.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    SingerMapper singerMapper;

    @Autowired
    SongMapper songMapper;

    //获取歌手每首歌的id
    public static List<String> getSongIdList(String url,String charest) {
        List<String> songId = new ArrayList<String>();//存储歌曲id信息
        List<String> songWord = new ArrayList<String>();//存储歌曲歌词id的信息
        Document document = getDocument(url, charest);
        //  System.out.println("document:   "+document);
        if(document!=null){
            //Elements elements = document.select(".m-sgerlist");//所有的歌手信息

            //获取歌手的歌曲信息
            Elements elements = document.select(".f-cb").select("a");//获取歌曲的id
            for(int i=0;i<elements.size();i++) {
                String song_id = elements.get(i).attr("href");//歌曲id
                if(song_id.contains("song?id")) {
                    System.out.println("歌曲id："+song_id);

                    String songword_id = song_id.substring(9);//将链接切割
                    songWord.add("http://music.163.com/api/song/media?id="+songword_id);
                    songId.add(songword_id);
                }
            }
            return songId;
        }
        return null;
    }

    //获取歌手每首歌的歌词
    public static String getSongWordList(String url,String charest) {
        String musicWord = new String();
        Document document = getDocument(url, charest);
        if(document!=null){
            Elements elements = document.select("body");//所有的歌曲信息

            //获取歌曲信息
            System.out.println("json数据如下");
            String resJson = elements.text();
            logger.info(resJson);

            Gson gson = new Gson();
            JsonParser jsonParser = new JsonParser();//将获取的歌词转成字符串list
            JsonObject jsonElement = jsonParser.parse(resJson).getAsJsonObject();
            String word = gson.fromJson(jsonElement, Words.class).toString();
            //System.out.println(word);//输出歌词
            // musicWord.add(word);
            return word;
        }
        return null;
    }

    public List<Song> getSongList(String url, String charest) {
        Singer artist = new Singer();//存储歌手信息
        Document document = getDocument(url, charest);
        if (document != null) {
            Elements elements = document.select("#song-list-pre-data");//所有的歌曲信息

            //获取歌手信息
            Elements elements1 = document.select("h2");//获取歌手的名字，歌手所在标签
            String singer_name = elements1.text();//获取歌手的名字

            Elements elements2 = document.select("img");//获取歌手的图片地址
            String singer_img = elements2.attr("src");

            artist.setSingerName(singer_name);
            artist.setSingerImg(singer_img);//将歌手信息存储到对象中

            //获取歌曲信息
            System.out.println("json数据如下");
            String resJson = elements.text();
            logger.info(resJson);
            Gson gson = new Gson();
            Type listType = new TypeToken<List<SongMsg>>() {
            }.getType();

            if (resJson != null && !resJson.contains("html")) ;
            List<Song> songList = new ArrayList<Song>();
            List<SongMsg> msgList1 = gson.fromJson(resJson, listType);
            for (SongMsg sm : msgList1) {
                Song song = new Song();//封装歌曲信息
                song.setSongAddress("http://music.163.com/song/media/outer/url?id=" + sm.getId());
                song.setSongImg(sm.getAlbum().getPicUrl());
                song.setSongName(sm.getName());
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                //System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
                song.setSongTime(df.format(new Date()));
                //封装对象
                songList.add(song);
            }
            //uploadMP3(msgList);
            return songList;
        }
        return null;
    }

    public static Document getDocument(String url, String charest) {
        List<Header> headerList = new ArrayList<Header>();
        String[] ua = {"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36 OPR/37.0.2178.32",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586",
                "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko",
                "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)",
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0)",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 BIDUBrowser/8.3 Safari/537.36",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 Core/1.47.277.400 QQBrowser/9.4.7658.400",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 UBrowser/5.6.12150.8 Safari/537.36",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36 TheWorld 7",
                "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/60.0"};
        headerList.add(new BasicHeader("Host", "music.163.com"));
        headerList.add(new BasicHeader("Referer", "https://music.163.com/"));
        headerList.add(new BasicHeader("User-Agent", ua[1]));
        HttpHost proxy=new HttpHost("223.214.23.130",45158);
        HttpClient httpClient = new HttpClient();
//设置代理服务器的ip地址和端口
        httpClient.getHostConfiguration().setProxy("223.214.23.130", 45158);
//使用抢先认证
        httpClient.getParams().setAuthenticationPreemptive(true);
        String result = HttpClientUtil.doGet(url, headerList, charest);
        if (result != null && !result.contains("n-for404")) {
            return Jsoup.parse(result);
        }
        return null;
    }

    public List<String> getSingerList(String url, String charest) {

        List<Singer> singerList = new ArrayList<Singer>();//存储歌手信息
        List<String> singerId = new ArrayList<String>();//存储歌手id信息
        List<String> singerDesc = new ArrayList<String>();//存储歌手简介页面的链接
        List<String> singerdesc = new ArrayList<String>();//存储歌手简介
        Document document = getDocument(url, charest);
        if (document != null) {
            //Elements elements = document.select(".m-sgerlist");//所有的歌手信息
            logger.info("不为空");
            //获取歌手信息
            Elements elements4 = document.select(".msk");//获取歌手的id
            Elements elements1 = document.select(".s-fc0");//获取歌手的名字，歌手所在标签
            Elements elements2 = document.select(".u-cover-5").select("img");//歌手图片
            Elements elements3 = document.select("h3");//获取歌手的类型
            Elements e11 = document.select("a");
            String singer_type = elements3.text();
            logger.info(elements4.size());
            logger.info(elements4);
            logger.info(e11);
            for (int i = 0; i < elements4.size(); i++) {
                User user = new User();
                logger.info("循环");
                Singer artist = new Singer();
                String singer_name = elements1.get(i).text();//歌手名字
                String singer_id = elements4.get(i).attr("href");//歌手id

                String s1 = singer_id.substring(7);
                System.out.println("地址：" + s1);
                String Desc = getSingerDesc("http://music.163.com/artist/desc" + s1, "utf-8");//歌手描述
                System.out.println(Desc + "：描述");
                String singer_img = elements2.get(i).attr("src");//歌手图片
                artist.setSingerImg(singer_img);
                artist.setSingerName(singer_name);
                artist.setSingerType(singer_type);
                artist.setSingerDesc(Desc);
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                user.setUserVip(1);
                user.setUserImg(artist.getSingerImg());
                user.setUserPrivilege(2);
                user.setUserEmail(System.currentTimeMillis() + "@163.com");
                user.setUserTime(dateFormat.format(date));
                user.setUserPassword("123456");
                user.setUserName(artist.getSingerName());
                System.out.println(user.toString());
              //  userMapper.register(user);
                //logger.info(userMapper);
                artist.setSingerId(user.getUserId());
               // singerMapper.addSinger(artist);
                System.out.println(artist.toString());
                singerList.add(artist);
                SingerIdList s = new SingerIdList();
                s.setId("http://music.163.com" + singer_id);
                s.setSingerId(artist.getSingerId());
                singerId.add("http://music.163.com" + singer_id);
            }

            for (Singer s1 : singerList) {
                System.out.println(s1 + "歌手信息");
                System.out.println("\n");
            }
            return singerId;
        }
        return null;
    }

    //获取歌手的个人简介
    public String getSingerDesc(String url, String charest) {

        Document document = getDocument(url, charest);
        if (document != null) {
            //Elements elements = document.select(".m-sgerlist");//所有的歌手信息

            //获取歌手信息
            Elements elements4 = document.select(".n-artdesc").select("p");//获取歌手的简介

            String singer_desc1 = elements4.text();
            System.out.println("-=====歌手描述");
            System.out.println(singer_desc1);
            return singer_desc1;
        }
        return null;
    }

    @RequestMapping("/ww")
    @ResponseBody
    public String main() throws IOException {
        //List<Song> list=getSongList("https://music.163.com/#/artist?id=6452","utf-8");
        List<String> singerId = getSingerList("https://music.163.com/#/discover/artist/cat?id=1001", "utf-8");
        List<String> songWord = new ArrayList<String>();
        List<String> songId = new ArrayList<String>();
        List<String> musicWord = new ArrayList<String>();
        ArrayList<Song> songs=songMapper.allSongs();
        for (Song song: songs) {
            System.out.println(song+"===========");
           // List<String> list1 = getSongIdList(singer,"utf-8");//获取每个歌手页面的每首歌的id
           String ss=song.getSongAddress().substring(45,song.getSongAddress().length());
              ///  songId.add("http://music.163.com/song?id="+ss);  //保存每首歌的id
              //  songWord.add("http://music.163.com/api/song/media?id="+ss);
            String song1 = getSongWordList("http://music.163.com/api/song/media?id="+ss, "utf-8");
         //   System.out.println("song1 = " + song1);
            //songMapper.songWords(song1,song.getSingerId());

            if(song1!=null){
                long name=System.currentTimeMillis();
                String address="F:\\songfile\\songWords\\"+name+".txt";
                String [] list=song1.split("\\n");
                FileWriter fw=new FileWriter(address);
                for(String s:list){
                    fw.write(s+"\r\n");
                    System.out.println(s);
                }
                fw.flush();
                fw.close();
                String address2="/songWords/"+name+".txt";
                songMapper.songWords(address2,song.getSongId());
            }else {
                songMapper.songWords(null,song.getSongId());
            }

        }
        //for (String songid : songId) {//所有歌曲的评论
        // 	List<String> comment = new ArrayList<String>();
        //	List<String> string = getSongCommentList("http://music.163.com/song?id=1374051000", "utf-8");
        		/*for(String s:string) {
        			System.out.println(s+"评论");
        		}*/
        //System.out.println(word+"所有歌曲");
        //}
        for (String msg : songWord) {   //获取所有歌曲的歌词
            String song = getSongWordList(msg, "utf-8");
            musicWord.add(song);
        }

        for (String msg : musicWord) { //输出所有歌曲的歌词
            System.out.println("歌词: "+msg);
        }

        //List<Song> list1 = getSongList("https://music.163.com/#/artist?id=5781","utf-8");
    /*	List<Song> list = new ArrayList<Song>(); //每首歌的详细信息
    	for(String s :singerId) {
    		List<Song> list1 = getSongList(s,"utf-8");
    		for(Song ss :list1) {
    			list.add(ss);
    		}
    	}*/
    	/*for (Song songMsg : list) {
			System.out.println(songMsg);
		}*/

        return "冲个";
    }
}
