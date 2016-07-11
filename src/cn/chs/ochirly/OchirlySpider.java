package cn.chs.ochirly;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.chs.common.NetWork;

public class OchirlySpider {
	
	public List<URL> searchImgUrl(String page) {

	    Document doc = Jsoup.parse(page);
	    Elements tagname = doc.getElementsByTag("img");
	    List<URL> list = new ArrayList<>();
	    for (Element link : tagname) {
	    	String imglink = link.attr("datasrc");
	    	try {
	            list.add(new URL(imglink.replace("_list_","_m_")));
	            list.add(new URL(imglink.replace("_list_","_b_")));
				System.out.println(imglink.replace("_list_","_m_"));
				System.out.println(imglink.replace("_list_","_b_"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

//      System.out.println(classname);
//	    String informationText = tagname.html();  //replace("¡ª","\n");
//	    System.out.println(informationText);
	    

//	    while(maImgUrl.find()) {
//	            try {
//		            list.add(new URL(maImgUrl.group(1)));
//		            list.add(new URL(maImgUrl.group(1).replace("_list_","_m_")));
//		            list.add(new URL(maImgUrl.group(1).replace("_list","_b_")));
//	                System.out.println(maImgUrl.group(1));
//	                System.out.println(maImgUrl.group(1).replace("_list_","_m_"));
//	                System.out.println(maImgUrl.group(1).replace("_list_","_b_"));
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//	    }
        return list;     
    }
	
	public List<URL> searchUrl(String page) {

	    Document doc = Jsoup.parse(page);
	    Elements classname = doc.getElementsByClass("HfloorTwoListTitle");

//	    System.out.println(classname);
	    String informationText = classname.html();  //replace("¡ª","\n");
//	    System.out.println(informationText);
	    
	    final Pattern paurl = Pattern.compile("(http.*.html)");
	    final Matcher maUrl = paurl.matcher(informationText);
	    
	    List<URL> list = new ArrayList<>();

	    while(maUrl.find()) {
            try {
	            list.add(new URL(maUrl.group(1)));
                System.out.println(maUrl.group(1));
				} catch (Exception e) {
					e.printStackTrace();
				}

	    }
        return list;     
    }
	
	public String searchPage(int pagepn) throws IOException {
        String path = "http://www.ochirly.com.cn/";
        String para = "women/Clothing1/Sweaters/list-" + pagepn + "-40-0-0-0-0-0-0-ochirly-0-1.shtml";
        System.out.println(path + para);
        URL onlyURL = new URL(path + para);
        String html = NetWork.sendGETRequest(onlyURL, "gbk");
        return html;
	}

}
