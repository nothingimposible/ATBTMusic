package com.qst.atbtmusic.method;

import com.qst.atbtmusic.pojo.Satisfaction;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于用户的协同过滤算法
 */
public class UserCFDemo {

    final static int NEIGHBORHOOD_NUM = 5;   //用户邻居数量
    final static int RECOMMENDER_NUM = 9;    //推荐结果个数

    public void inputStrem(ArrayList<Satisfaction> satisfactions) throws IOException {
        File file = new File("F:\\songfile\\data\\data.txt");
        // 下面这也会抛出异常，这次我们为了代码结构清晰起见，直接throw给main吧
        if(file.exists()){
            file.createNewFile();
        }
        Writer writer = new FileWriter(file);
        for(Satisfaction e:satisfactions){
            String string = e.getUserId()+"\t"+e.getSongId()+"\t"+e.getSatifaction()+"\n";
            writer.write(string);
        }
        // 在这一定要记得关闭流
        writer.close();
    }
    public  ArrayList<Integer> CFDemo(int userid) throws IOException, TasteException {
        DataModel model = new FileDataModel(new File("F:\\songfile\\data\\data.txt"));  //基于文件的model，通过文件形式来读入,且此类型所需要读入的数据的格式要求很低，只需要满足每一行是用户id，物品id，用户偏好，且之间用tab或者是逗号隔开即可
        System.out.println(model.toString());
        //基于用户的协同过滤算法，基于物品的协同过滤算法
        UserSimilarity user = new EuclideanDistanceSimilarity(model);  //计算欧式距离，欧式距离来定义相似性，用s=1/(1+d)来表示，范围在[0,1]之间，值越大，表明d越小，距离越近，则表示相似性越大
        NearestNUserNeighborhood  neighbor = new NearestNUserNeighborhood(NEIGHBORHOOD_NUM, user, model);
        //指定用户邻居数量

        //构建基于用户的推荐系统
        Recommender r = new GenericUserBasedRecommender(model, neighbor, user);

        //得到所有用户的id集合
        LongPrimitiveIterator iter = model.getUserIDs();
        ArrayList<Integer> s=new ArrayList<Integer>();
        while(iter.hasNext())
        {
            long uid = iter.nextLong();
            List<RecommendedItem> list = r.recommend(uid,RECOMMENDER_NUM);  //获取推荐结果
            System.out.printf("用户:%s",uid);
            if (userid == (int) uid){
                for(RecommendedItem ritem : list)
                {
                    System.out.println("ritem.getItemID() = " + ritem.getItemID());
                    s.add((int) ritem.getItemID());
                }
            }
            //遍历推荐结果
            System.out.print("|| 推荐商品：");
            for(RecommendedItem ritem : list)
            {
                System.out.print(ritem.getItemID()+" ");
            }
            System.out.println();
        }
        return s;
    }
}