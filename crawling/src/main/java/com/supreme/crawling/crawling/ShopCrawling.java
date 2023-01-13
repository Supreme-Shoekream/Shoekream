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

public class ShopCrawling {
    public static void main(String[] args) {
        int maximum = 50;
        String kreamurl = "https://kream.co.kr/search?category_id=44&per_page=40";
        String url = "";
        Document doc = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        int x = 0;
        String path = "/Users/oyun-yeong/Desktop/Shoekream/shoptestImg/"; // 파일저장위치


            try {
                doc = Jsoup.connect(kreamurl).get();
            } catch (Exception e) {
                e.printStackTrace();
            }

        Elements elements3 = doc.getElementsByAttributeValue("class", "product_card");
        int size = elements3.size();

        for (Element element : elements3) {
            System.out.println("🟡");
            Elements imgs = element.getElementsByAttributeValue("class", "item_inner");
            Elements p_img = imgs.select("img.image");

            Elements elements5 = element.select("div.product_info_area");
            Elements brands = elements5.first().children().first().children();

            Elements elements = element.getElementsByAttributeValue("class", "product_info_product_name");
            Elements names = elements.first().children(); // 상품명

            Elements elements2 = element.select("div.price_area");
            Elements p_price = elements2.first().children(); // 즉시구매가

            Elements elements4 = element.getElementsByAttributeValue("class", "action_wish_review");
            Elements p_wish = elements4.select("span.wish_figure>span.text"); // 관심상품수
            Elements p_style = elements4.select("span.review_figure>span.text"); // 스타일게시글수


//            while(p_brand.hasNext()) {
                String img_url = p_img.attr("src"); // 상품사진주소
                String brand = brands.get(0).text(); // 브랜드명
                String name = names.get(0).text(); // 상품명
                String name_kor = names.get(1).text(); // 상품명(kor)
                String now_price = p_price.get(0).text(); // 즉시구매가
                String wish_count = p_wish.get(0).text(); // 관심상품수
                String style_count= p_style.get(0).text(); // 스타일게시글수


                String filename = name.replaceAll(" ", "_");

                HttpURLConnection conn2 = null;
                URL imgUrl;
                try{
                    imgUrl = new URL(img_url);

                    conn2 = (HttpURLConnection) imgUrl.openConnection();
                    conn2.setRequestProperty("Referer", "https://kream.co.kr/products/");
//                    System.out.println("Img URL : " + img_url);

                    BufferedImage buffImg = ImageIO.read(conn2.getInputStream());
                    FileOutputStream file = new FileOutputStream(path + filename + ".png");
                    ImageIO.write(buffImg, "png", file);

                }catch (IOException e){
                    e.printStackTrace();
                }

                String img = "/img/shop/" + filename + ".png"; // 상품사진(원하는 형식으로 사진이름 만들었음)

                System.out.println(img + " - " + brand + " - " + name + " - " + name_kor + " - " + now_price + " - " + wish_count + " - " + style_count);

                try {
                    conn = Dbconn.getConnection();
                    sql = "insert into shop_test(img, brand, name, name_kor, now_price, wish_count, style_count) " +
                            "values(?, ?, ?, ?, ?, ?, ?)";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1,img);
                    pstmt.setString(2,brand);
                    pstmt.setString(3,name);
                    pstmt.setString(4,name_kor);
                    pstmt.setString(5,now_price);
                    pstmt.setString(6,wish_count);
                    pstmt.setString(7,style_count);
                    x = pstmt.executeUpdate();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }

        }
//        System.out.println("성공!!!!!!!!!!!!!");
    }
//}
