package com.qst.atbtmusic.util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.*;

public class HttpClientUtil {

/**
     * 模拟get请求
     * @param url
     * @param headerList
     * @param charest
     * @return
     */

    public static String doGet(String url,List<Header> headerList,String charest){

        HttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = null;
        try {
            httpClient = new DefaultHttpClient();
            httpGet = new HttpGet(url);
            //设置请求头
            for(Header header:headerList){
                httpGet.setHeader(header);
            }

            HttpResponse httpResponse = httpClient.execute(httpGet);
            if(httpResponse!=null){
                HttpEntity httpEntity = httpResponse.getEntity();
                if(httpEntity!=null){
                    result = EntityUtils.toString(httpEntity,charest);
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return result;

    }


}
