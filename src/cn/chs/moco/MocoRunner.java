package cn.chs.moco;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class MocoRunner {
	
	
	public static void main(String[] args) throws IOException {
//		String page = runner.ReadHtmlFile();
        
        MocoSpider mocoSpider = new MocoSpider();
        MocoParser mocoParser = new MocoParser();
               
        List<URL> clothesUrlList = null;
        clothesUrlList = mocoSpider.searchClothesUrl();
        	
        for (URL url : clothesUrlList) {
    		
            List<URL> imgUrlList = null;
            String clothesId = mocoParser.getSellerId(url);
 //           System.out.println(clothesId);
            imgUrlList = mocoSpider.searchImgUrl(url);	
            mocoParser.analyzeData(imgUrlList,clothesId);
				
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
