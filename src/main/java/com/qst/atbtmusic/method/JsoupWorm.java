package com.qst.atbtmusic.method;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class JsoupWorm {

    public ArrayList<String> search() throws IOException {
        ArrayList<String> list = new ArrayList<String>();
        System.out.println("进入jsoup");
        ArrayList<String> link=new ArrayList<String>();
        Connection conn = Jsoup.connect("https://music.163.com/#/discover/artist/cat?id=1001").data("query", "Java")   // 请求参数
                .userAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)") // 设置 User-Agent
                .timeout(30000); // 设置连接超时时间
        Connection.Response resp = conn.execute();
        Document doc = conn.get();
        /*Elements elements=doc.getElementsByClass("list1");*/
        Elements links=doc.select("a"); // 获取所有超链接元素
        for(Element element:links){
            if(!element.attr("data").equals("")&&element.attr("data")!=null) {//获取的元素不为空
                String len = element.attr("data");//将data内的数据转换成字符串
                //截取字符串第一位到倒数第七位
                //获取歌曲资源地址
                String url = "https://www.kugou.com/song/#hash="+len.substring(0, element.attr("data").length()-7);
                System.out.println(url);
                String imgUrl = searchImg(url);//调用方法获取每个页面的图片地址
                System.out.println("图片地址"+imgUrl);
                //存入list并返回
                list.add(url);
            }
        }
        return list;
    }

    //获取每个链接的图片
    public String searchImg(String url) throws IOException {
        String imgUrl = null;
        System.out.println("进入jsoup");
        ArrayList<String> link=new ArrayList<String>();
        Connection conn = Jsoup.connect(url).data("query", "Java")   // 请求参数
                .userAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)") // 设置 User-Agent
                .timeout(30000); // 设置连接超时时间
        Connection.Response resp = conn.execute();
        Document doc = conn.get();
        /*Elements elements=doc.getElementsByClass("list1");*/
        Elements links=doc.select("img"); // 获取所有超链接元素
        System.out.println(links+"===");
        for(Element element:links){
            if(!element.attr("src").equals("")&&element.attr("src")!=null) {//获取的元素不为空
                imgUrl = element.attr("src");//将data内的数据转换成字符串
            }

        }
        return imgUrl;
    }
}
