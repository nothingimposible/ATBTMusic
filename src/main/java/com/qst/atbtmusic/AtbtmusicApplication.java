package com.qst.atbtmusic;

import com.qst.atbtmusic.method.UserCFDemo;
import com.qst.atbtmusic.pojo.Satisfaction;
import org.apache.mahout.cf.taste.common.TasteException;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

@SpringBootApplication
@MapperScan(value = "com.qst.atbtmusic.mapper")
public class AtbtmusicApplication {

    public static void main(String[] args) throws IOException, TasteException {
        SpringApplication.run(AtbtmusicApplication.class, args);
       /* ArrayList<Satisfaction> s1=new ArrayList<Satisfaction>();
        for(int i=0;i<15;i++){
            Satisfaction satisfaction=new Satisfaction();
            satisfaction.setSong_id(i);
            satisfaction.setUser_id(i+1);
            satisfaction.setSatifaction(3);
            s1.add(satisfaction);
        }
        new UserCFDemo().inputStrem(s1);
        new UserCFDemo().CFDemo();*/

    }

}
