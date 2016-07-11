package cn.chs.girdear;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;


public class GirdearRunner {
	
	public static void main(String[] args) throws IOException {
//		OnlyRunner runner = new OnlyRunner();
//		String page = runner.ReadHtmlFile();
        
        GirdearSpider girdearSpider = new GirdearSpider();
        GirdearParser girdearParser = new GirdearParser();
        
//        List<URL> imgUrlList = onlySpider.searchImgUrl(page);	
//        onlyParser.analyzeData(imgUrlList);
        
        for (int pn = 1; pn <=2 ; ++pn) {
        	List<URL> clothesUrlList = null;
        	clothesUrlList = girdearSpider.searchClothesUrl(pn);
        	
        	for (URL url : clothesUrlList) {
        		
                List<URL> imgUrlList = null;
                String clothesId = girdearParser.getSellerId(url);
//              System.out.println(clothesId);
                imgUrlList = girdearSpider.searchImgUrl(url);	
                girdearParser.analyzeData(imgUrlList,clothesId);
				
			}
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
	
	
	
//	  public void getStyleDetails(String page) {
//			
//		    Document doc = Jsoup.parse(page);
//		    Elements classname = doc.getElementsByClass("bIi-brand-logo");
//		    Elements logoStyle = doc.getElementsByClass("bIi-style");
//		    
//		    //System.out.println(classname);
//		    //System.out.println(logoStyle);
//		    String informationText = classname.text() ;  //replace("��","\n");
//		    String styleText = logoStyle.text() ;
//		    
//		    String[] logoNameArray = informationText.split(" ");
//		    String[] styleArray = styleText.split("Ʒ");
//		    for (int i = 0; i < logoNameArray.length; i++) {
//		        System.out.println(logoNameArray[i]);
//		        System.out.println(styleArray[i+1]);
//		        File file = new File(logoNameArray[i].replace("/"," ") + ".txt");
//		        File parent = file.getParentFile();
//		        if (parent != null && !parent.exists()) {
//		            parent.mkdirs();
//		        }
//		        
//		        try {
//		            file.createNewFile();
//		            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
//		            bufferedWriter.write("Ʒ"+styleArray[i+1]);
//		            bufferedWriter.close();
//		        } catch (IOException e) {
//		            e.printStackTrace();
//		        }
//		  }
//		  }

}
