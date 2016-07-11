package cn.chs.women;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.chs.common.NetWork;

public class WomenSpider {
  
  public List<URL> searchImgUrl(String page) {
    Document doc = Jsoup.parse(page);
    Elements classname = doc.getElementsByClass("lazy");
           
    List<URL> list = new ArrayList<>(); 
    for (Element link : classname) {
		String imglink ="http:" + link.attr("data-original");
		String imglink3 = imglink.replace("_1_","_3_");
		String imglink4 = imglink.replace("_1_","_4_");
		String imglink5 = imglink.replace("_1_","_5_");
	    try {
			list.add(new URL(imglink3));
			list.add(new URL(imglink4));
			list.add(new URL(imglink5));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println(imglink3);
		System.out.println(imglink4);
		System.out.println(imglink5);
    }
    return list; 
  }
  
	public String searchPage(int pagepn) throws IOException {
        String path = "http://www.matchesfashion.com/intl/womens/shop";
        String para = "?page="+ pagepn;
        System.out.println(path + para);
        URL womenURL = new URL(path + para);
        String html = NetWork.sendGETRequest(womenURL, "gbk");
        return html;
	}
}
