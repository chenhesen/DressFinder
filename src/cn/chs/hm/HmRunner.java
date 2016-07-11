package cn.chs.hm;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class HmRunner {
	
	public static void main(String[] args) throws IOException {
//		String page = runner.ReadHtmlFile();
        
        HmSpider hmSpider = new HmSpider();
        HmParser hmParser = new HmParser();
               
        List<URL> clothesUrlList = null;
        clothesUrlList = hmSpider.searchClothesUrl();
        	 		
        for (URL url : clothesUrlList) {
    		
            List<URL> imgUrlList = null;
            String clothesId = hmParser.getSellerId(url);
            System.out.println(clothesId);
            imgUrlList = hmSpider.searchImgUrl(url);	
            hmParser.analyzeData(imgUrlList,clothesId);
				
	    }
		      				        
}
	
	public String ReadHtmlFile() {
		File file = new File("C:\\Users\\Administrator\\Desktop\\1.txt");
		try {
			String content = FileUtils.readFileToString(file,"utf-8");
            return content; 
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}	
	}

}
