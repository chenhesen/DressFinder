package cn.chs.girdear;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.chs.common.NetWork;

public class GirdearSpider {
	
	public List<URL> searchImgUrl(URL gUrl) throws IOException {
		
		String page = NetWork.sendGETRequest(gUrl, "gbk");

	    Document doc = Jsoup.parse(page);
	    Elements classname = doc.select("a[attr_id = 211]>img");

//	    System.out.println(classname);
	    
	    List<URL> list = new ArrayList<>();
	    for (Element link : classname) {
			String mimglink ="http://www.girdear.cn/" + link.attr("jqimg_g");
			String limglink ="http://www.girdear.cn/" + link.attr("jqimg_o");
		    list.add(new URL(mimglink));
			list.add(new URL(limglink));
//			System.out.println(mimglink);
//			System.out.println(limglink);
	    }
//
        return list;     
    }
	
	public List<URL> searchClothesUrl(int pagepn) throws IOException {
		
        String path = "http://www.girdear.cn/";
        String para = "category.php?&id=87&brand=0&p=" + pagepn;
        System.out.println(path + para);
        URL girdearURL = new URL(path + para);
        String html = NetWork.sendGETRequest(girdearURL, "gbk");

	    Document doc = Jsoup.parse(html);
	    Elements aurl = doc.select("a.aHover2");

//	    System.out.println(aurl);
	    int i = 1;
	    List<URL> list = new ArrayList<>();
	    for (Element link : aurl) {
	    	if (i>0) {
		    	String imglink = "http://www.girdear.cn/" + link.attr("href");
		    	System.out.println(imglink);
		    	list.add(new URL(imglink));
			}   	
	    	i = -i;
	    } 
	    return list;
	}
}