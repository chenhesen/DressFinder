package cn.chs.ochirly;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;


public class OchirlyRunner {
	
	public static void main(String[] args) throws IOException {
//		OnlyRunner runner = new OnlyRunner();
//		String page = runner.ReadHtmlFile();
        
        OchirlySpider ochirlySpider = new OchirlySpider();
        OchirlyParser ochirlyParser = new OchirlyParser();
              
        for (int pn = 1; pn <=1 ; ++pn) {
            List<URL> imgUrlList = null;
            String page = ochirlySpider.searchPage(pn); 
            
            imgUrlList = ochirlySpider.searchImgUrl(page);	
            ochirlyParser.analyzeData(imgUrlList);
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
