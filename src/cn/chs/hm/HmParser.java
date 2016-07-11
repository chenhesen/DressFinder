package cn.chs.hm;

import static java.io.File.separator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;

public class HmParser {
	
	public void analyzeData(List<URL> iUrlList,String clothesId) throws IOException {
		
        saveImg(iUrlList,clothesId);
//		String page = NetWork.sendGETRequest(url, "gbk");
//      String details = getDetails(page);
//		String sellerName = getSellerName(details);
//        System.out.println(sellerName);
//        saveData(details,sellerName);

	}
	
	public String getSellerId(URL url) {
		try {
	        String[] sp = url.getFile().split("[\\\\/..]");                
	        String clothesId = URLDecoder.decode(sp[sp.length - 2], "utf-8");
//			System.out.println(clothesId);
	        return clothesId;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
			// TODO: handle exception
		}
	}
	 
	    public void saveImg(List<URL> imgs,String clothesId) {

	        byte[] buffer = new byte[1024];
	        int len;
	        
	        for (URL img : imgs) {
		        File imgFolder = new File("D:\\Clothes\\H&M\\" + clothesId);
		        if (!imgFolder.exists())
		            imgFolder.mkdirs();
	            System.out.println("Downloading " + img.toString());
	            HttpURLConnection httpURLConnection = null;
	            try {
	                httpURLConnection = (HttpURLConnection) img.openConnection();
	                httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36");
	                InputStream inputStream = httpURLConnection.getInputStream();
	                String[] sp = img.getFile().split("[\\\\/]");                
	                String imgName = URLDecoder.decode(sp[sp.length - 1], "utf-8");			                 
	                FileOutputStream fileOutputStream = new FileOutputStream(new File(imgFolder + separator + imgName));

	                while ((len = inputStream.read(buffer)) > 0) {
	                    fileOutputStream.write(buffer, 0, len);
	                }

	                fileOutputStream.close();
	                inputStream.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }


}
