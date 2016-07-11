package cn.chs.baidu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class BaiduRunner {
	
	    public static void main(String[] args) throws Exception {
	        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	        System.out.print("word=");
	        String word = reader.readLine();
	        System.out.print("startPage=");
	        int stPn = Integer.parseInt(reader.readLine());
	        System.out.print("endPage=");
	        int edPn = Integer.parseInt(reader.readLine());

	        BaiduSpider baiduSpider = new BaiduSpider();
	        BaiduParser baiduParser = new BaiduParser();

	        try {
	            for (int pn = stPn; pn <= edPn; ++pn) {
	                List<URL> list = null;
	                list = baiduSpider.search(word, pn);
	                 System.out.println(list);
	                 baiduParser.analyzeData(list);	            
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
