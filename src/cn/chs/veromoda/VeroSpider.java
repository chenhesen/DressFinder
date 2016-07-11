package cn.chs.veromoda;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cn.chs.common.NetWork;

public class VeroSpider {
	
	public List<URL> searchImgUrl(String page) {

	    Document doc = Jsoup.parse(page);
	    Elements classname = doc.getElementsByClass("proThumbImg");

//	    System.out.println(classname);
	    String informationText = classname.html();  //replace("¡ª","\n");
//	    System.out.println(informationText);
	    
	    final Pattern paImgurl = Pattern.compile("(http.*.jpg)");
	    final Matcher maImgUrl = paImgurl.matcher(informationText);
	    
	    List<URL> list = new ArrayList<>();

	    while(maImgUrl.find()) {
	            try {
//		            list.add(new URL(maImgUrl.group(1)));
		            list.add(new URL(maImgUrl.group(1).replace("/185296","")));
		            list.add(new URL(maImgUrl.group(1).replace("/185296","/313500")));
//	                System.out.println(maImgUrl.group(1));
	                System.out.println(maImgUrl.group(1).replace("/185296",""));
	                System.out.println(maImgUrl.group(1).replace("/185296","/313500"));
				} catch (Exception e) {
					e.printStackTrace();
				}

	    }
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
        String path = "http://www.veromoda.com.cn/";
        String para = "webapp/wcs/stores/servlet/CategoryNavigationResultsView?manufacturer=&searchType=&resultCatEntryType=&searchTerm=+&catalogId=10001&categoryId=-30100107&langId=-7&storeId=10151&sType=SimpleSearch&filterFacet=&metaData=&facet=&facet=&facet=&subCategoryId=&occasionFacet=&colorFacet=&sizeFacet=&sizeflag=&colorflag=&occasionflag=&pageSize=40&beginIndex="+ 40*(pagepn-1) + "&orderBy=5&categoryId=";
        System.out.println(path + para);
        URL veroURL = new URL(path + para);
        String html = NetWork.sendGETRequest(veroURL, "gbk");
        return html;
	}
	
}
