package cn.chs.women;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import cn.chs.women.WomenParser;
import cn.chs.women.WomenSpider;

public class WomenRunner {
	
	public static void main(String[] args) throws IOException {
//		String page = runner.ReadHtmlFile();
        
        WomenSpider womenSpider = new WomenSpider();
        WomenParser womenParser = new WomenParser();
                      	 		
        for (int pn = 1; pn <=3; ++pn) {
            List<URL> imgUrlList = null;
            
            System.out.println("正在爬取第"+pn+"页商品");
            String page = womenSpider.searchPage(pn); 
            
            imgUrlList = womenSpider.searchImgUrl(page);	
            womenParser.analyzeData(imgUrlList);
        }
        
	}

}
