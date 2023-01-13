package com.supreme.crawling.crawling;

import com.supreme.crawling.db.Dbconn;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

public class ProductCrawling {
    public static void main(String[] args) {
        int maximum = 50;
        String kreamurl = "https://kream.co.kr/products/";
        String url = "";
        Document doc = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        int x = 0;
        String path = "/Users/oyun-yeong/Desktop/Shoekream/testImg/"; // 파일저장위치

        for (int i = 0; i <= maximum; i++) {
            url = kreamurl + (38530 + i);
            try {
                doc = Jsoup.connect(url).get();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Elements imgs = doc.getElementsByAttributeValue("class", "item_inner");
            Elements p_img = imgs.select("img.image");

            Elements elements = doc.select("div.main_title_box");
            Iterator<Element> p_brand = elements.select("a.brand").iterator();
            Iterator<Element> p_title = elements.select("p.title").iterator();
            Iterator<Element> p_subtitle = elements.select("p.sub_title").iterator();

            Elements p_size = doc.getElementsByAttributeValue("class", "btn_text");

            Elements p_wish = doc.getElementsByAttributeValue("class", "wish_count_num");

            Elements el = doc.getElementsByAttributeValue("class", "product_info");

            while(p_brand.hasNext()) {
                String img_url = p_img.attr("src"); // 상품사진주소
                String brand = p_brand.next().text(); // 브랜드명
                String name = p_title.next().text(); // 상품명
                String name_kor = p_subtitle.next().text(); // 상품명(kor)
                String size = p_size.next().text(); // 사이즈
                String wish_count = p_wish.get(0).text(); // 관심상품수
                String model_num = el.get(0).text(); // 모델번호
                String release_date = el.get(1).text(); // 출시일
                String color = el.get(2).text(); // 컬러
                String first_price = el.get(3).text(); // 발매가

                String filename = name.replaceAll(" ", "_");

                HttpURLConnection conn2 = null;
                URL imgUrl;
                try{
                    imgUrl = new URL(img_url);

                    conn2 = (HttpURLConnection) imgUrl.openConnection();
                    conn2.setRequestProperty("Referer", "https://kream.co.kr/products/");
                    System.out.println("Img URL : " + img_url);

                    BufferedImage buffImg = ImageIO.read(conn2.getInputStream());
                    FileOutputStream file = new FileOutputStream(path + filename + ".png");
                    ImageIO.write(buffImg, "png", file);

                }catch (IOException e){
                    e.printStackTrace();
                }

                String img = "/img/product/" + filename + ".png"; // 상품사진(원하는 형식으로 사진이름 만들었음)

                System.out.println(img + " - " + brand + " - " + name + " - " + name_kor + " - " + size + " - " + wish_count + " - " + model_num + " - " + release_date + " - " + color + " - " + first_price);

                try {
                    conn = Dbconn.getConnection();
                    sql = "insert into product_test(img, brand, name, name_kor, size, wish_count, model_num, release_date, color, first_price) " +
                            "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1,img);
                    pstmt.setString(2,brand);
                    pstmt.setString(3,name);
                    pstmt.setString(4,name_kor);
                    pstmt.setString(5,size);
                    pstmt.setString(6,wish_count);
                    pstmt.setString(7,model_num);
                    pstmt.setString(8,release_date);
                    pstmt.setString(9,color);
                    pstmt.setString(10,first_price);
                    x = pstmt.executeUpdate();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("성공!!!!!!!!!!!!!");
    }
}
