package cn.chs.hm;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.chs.common.NetWork;

public class HmSpider {
	
	public List<URL> searchImgUrl(URL hUrl) throws IOException {
		
		String page = NetWork.sendGETRequest(hUrl, "gbk");
	    Document doc = Jsoup.parse(page);
	    Elements classname = doc.getElementsByClass("product-detail-thumbnail-image");

//	    System.out.println(classname);
	    
	    List<URL> list = new ArrayList<>();
	    for (Element link : classname) {
			String imglink =link.attr("src");

			list.add(new URL("http:" + imglink));
			System.out.println("http:" + imglink);
	    }
        return list;    
    }
	
	public List<URL> searchClothesUrl() throws IOException {
		
        String path = "http://www2.hm.com/";
        String para = "zh_cn/ladies/shop-by-product/dresses/_jcr_content/main/productlisting.display.html?offset=0&page-size=311";
        System.out.println(path + para);
        URL hmURL = new URL(path + para);
        String html = NetWork.sendGETRequest(hmURL, "utf-8");
        
	    Document doc = Jsoup.parse(html);
	    Elements classname = doc.select("h3[class=product-item-headline]>a");
//	    System.out.println(classname);
	    List<URL> list = new ArrayList<>();
	    
	    for (Element link : classname) {
			String imglink =link.attr("href");

			list.add(new URL("http://www2.hm.com" + imglink));
	    }
        return list;

    }
	
}
