package cn.chs.moco;

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

public class MocoSpider {
	
	public List<URL> searchImgUrl(URL gUrl) throws IOException {
		
		String page = NetWork.sendGETRequest(gUrl, "gbk");

	    Document doc = Jsoup.parse(page);
	    Elements classname = doc.getElementsByClass("large");

//	    System.out.println(classname);
	    
	    List<URL> list = new ArrayList<>();
	    for (Element link : classname) {
			String imglink =link.attr("src");

			list.add(new URL(imglink));
//			System.out.println(imglink);
	    }
        return list;    
    }
	
	public List<URL> searchClothesUrl() throws IOException {
		
        String path = "http://www.mo-co.com/";
        String para = "product/listProduct.jhtml?brandId=18&promotionId=&productCategoryId=80&orderType=dateDesc&pageNumber=1&pageSize=69&requestType=&_=1442308960215";
        System.out.println(path + para);
        URL mocoURL = new URL(path + para);
        String html = NetWork.sendGETRequest(mocoURL, "utf-8");
	    final Pattern paurl = Pattern.compile("(/product/.{18,23}html)");
	    final Matcher maUrl = paurl.matcher(html);
	    
	    List<URL> list = new ArrayList<>();

	    while(maUrl.find()) {
            try {
	            list.add(new URL("http://www.mo-co.com" + maUrl.group(0)));
                System.out.println("http://www.mo-co.com" + maUrl.group(0));
				} catch (Exception e) {
					e.printStackTrace();
				}

	    }
        return list; 
    }
	
}
