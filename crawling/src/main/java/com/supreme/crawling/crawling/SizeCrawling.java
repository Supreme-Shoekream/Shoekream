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

public class SizeCrawling {
    public static void main(String[] args) {
        String kreamurl = "https://kream.co.kr/products/23632";
        Document doc = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        int x = 0;
        String path = "/Users/oyun-yeong/Desktop/Shoekream/"; // 파일저장위치

            try {
                doc = Jsoup.connect(kreamurl).get();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Elements imgs = doc.select("div.item_inner"); // 상품사진
            Elements p_img = imgs.select("img.image");

            Elements elements = doc.select("div.main_title_box");
            Iterator<Element> p_brand = elements.select("a.brand").iterator(); // 브랜드명
            Iterator<Element> p_title = elements.select("p.title").iterator(); // 상품명(eng)
            Iterator<Element> p_subtitle = elements.select("p.sub_title").iterator(); // 상품명(kor)

            Elements p_sizes = doc.select("div.body_list");  // 사이즈
//            Elements p_size = p_sizes.first().children();

//            Elements p_prices1 = doc.select("div.detail_price"); // 최근거래가, 가격변동
//            Elements p_price1 = p_prices1.last().children().last().children();
//
//            Elements p_prices2 = doc.select("div.division_btn_box"); // 즉시구매가, 즉시판매가
//            Elements p_buy = p_prices2.first().children();
//            Elements p_nowbuy = p_buy.first().children();
//            Elements p_sell = p_prices2.last().children();
//            Elements p_nowsell = p_sell.last().children();
//
//            Elements p_wish = doc.getElementsByAttributeValue("class", "wish_count_num"); // 관심상품수

            Elements el = doc.getElementsByAttributeValue("class", "product_info"); // 모델번호, 출시일, 컬러, 발매가

            while(p_brand.hasNext()) {
                String img_url = p_img.attr("src"); // 상품사진주소
                String brand = p_brand.next().text(); // 브랜드명
                String name = p_title.next().text(); // 상품명
                String name_kor = p_subtitle.next().text(); // 상품명(kor)
//                String size = p_size.get(0).text(); // 사이즈
//                String recent_price = p_price1.get(0).text(); // 최근거래가
//                String fluctuation = p_price1.get(1).text(); // 가격변동
//                String now_buy = p_nowbuy.get(1).text().replace("즉시 구매가", ""); // 즉시 구매가
//                String now_sell = p_nowsell.get(1).text().replace("즉시 판매가", ""); // 즉시 판매가
//                String wish_count = p_wish.get(0).text(); // 관심상품수
                String model_num = el.get(0).text(); // 모델번호
                String release_date = el.get(1).text(); // 출시일
                String color = el.get(2).text(); // 컬러
                String first_price = el.get(3).text(); // 발매가

                String filename = name.replaceAll(" ", "_");

//                HttpURLConnection conn2 = null;
//                URL imgUrl;
//                try{
//                    imgUrl = new URL(img_url);
//
//                    conn2 = (HttpURLConnection) imgUrl.openConnection();
//                    conn2.setRequestProperty("Referer", "https://kream.co.kr/products/");
//                    System.out.println("Img URL : " + img_url);
//
//                    BufferedImage buffImg = ImageIO.read(conn2.getInputStream());
//                    FileOutputStream file = new FileOutputStream(path + filename + ".png");
//                    ImageIO.write(buffImg, "png", file);
//
//                }catch (IOException e){
//                    e.printStackTrace();
//                }

                String img = "/img/product/" + filename + ".png"; // 상품사진(원하는 형식으로 사진이름 만들었음)

                System.out.println(img + " - " + brand + " - " + name + " - " + name_kor + " - " + model_num + " - " + release_date + " - " + color + " - " + first_price);

                try {
                    conn = Dbconn.getConnection();
                    sql = "insert into ex(img, brand, name, name_kor, model_num, release_date, color, first_price) " +
                            "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    System.out.println("🟡");
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1,img);
                    pstmt.setString(2,brand);
                    pstmt.setString(3,name);
                    pstmt.setString(4,name_kor);
//                    pstmt.setString(5,size);
//                    pstmt.setString(6,recent_price);
//                    pstmt.setString(7,fluctuation);
//                    pstmt.setString(8,now_buy);
//                    pstmt.setString(9,now_sell);
//                    pstmt.setString(10,wish_count);
                    pstmt.setString(6,model_num);
                    pstmt.setString(7,release_date);
                    pstmt.setString(8,color);
                    pstmt.setString(9,first_price);
                    x = pstmt.executeUpdate();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
