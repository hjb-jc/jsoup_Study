package test.java;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.redis.connection.RedisClusterConnection;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;


public class JsoupTest {

    public  void getTile(){
        String url = "http://www.zhsme.gov.cn/policy/getPolicyList?areaSreachValue=%E5%B8%82%E7%9B%B4%E5%B1%9E&areaSreachId=areaSreach_szs";

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements title = doc.select(".list-content ul li .list-txt h4 a");

        for (Element element : title) {
            System.out.println("标题>>>>" + element.text());
        }
    }


    public static void main(String[] args) throws IOException {
        String url = "http://www.zhsme.gov.cn";
        String url1 = "http://www.zhsme.gov.cn/policy/getPolicyList?pageNum=2&NameOrWords=&areaSreachValue=&areaSreachId=&scaleSreachValue=&scaleSreachId=&levelSreachValue=&levelSreachId=&isShuangChuang=";
        String url2 = "http://www.zhsme.gov.cn/policy/getTextPolicyByTextPolicyId?textPolicyId=";

        int num = 180 ;
        for (int i = 1; i < num; i++) {

            url1 = "http://www.zhsme.gov.cn/policy/getPolicyList?pageNum="+i+"&NameOrWords=&areaSreachValue=&areaSreachId=&scaleSreachValue=&scaleSreachId=&levelSreachValue=&levelSreachId=&isShuangChuang=";
            String href = Jsoup.connect(url1).get()
                    .select(".list-content ul li .list-txt h4 a").attr("href");
            String onclick = Jsoup.connect(url + href).get().select(".policy-txt a").attr("onclick").substring(18,54);

            String text = Jsoup.connect(url2 + onclick).get().select(".view").text();
            System.out.println("第"+ i+ "条：" +text);
            //System.out.println(onclick);
            //System.out.println("di" +i +">"+onclick);
            //System.out.println(href);

        }
    }
}
