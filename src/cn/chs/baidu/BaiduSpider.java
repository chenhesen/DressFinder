package cn.chs.baidu;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.chs.common.NetWork;

public class BaiduSpider {

    private int rn = 60;

    public List<URL> search(String word, int pn) throws IOException, JSONException {
        String path = "http://image.baidu.com/search/acjson?";
        String para = "tn=resultjson_com&ipn=rj&ie=utf-8&oe=utf-8&word=" + URLEncoder.encode(word, "UTF-8") + "&nc=1&pn=" + pn * rn + "&rn=60";
        System.out.println(path + para);

        URL baiduURL = new URL(path + para);
        String json = NetWork.sendGETRequest(baiduURL, "utf-8");

        JSONObject object = new JSONObject(json);
        JSONArray data = object.getJSONArray("data");
        List<URL> list = new ArrayList<>();
        
        for (int i = 0; i < data.length() - 1; i++) {
            JSONObject tmp = data.getJSONObject(i);
            String imgurl = decode(tmp.getString("objURL"));          
            list.add(new URL(imgurl));
        }
        return list;
    }
    
    private String decode(String url) {
        HashMap<String, String> table = new HashMap<String, String>() {
            {
                put("w", "a");
                put("k", "b");
                put("v", "c");
                put("1", "d");
                put("j", "e");
                put("u", "f");
                put("2", "g");
                put("i", "h");
                put("t", "i");
                put("3", "j");
                put("h", "k");
                put("s", "l");
                put("4", "m");
                put("g", "n");
                put("5", "o");
                put("r", "p");
                put("q", "q");
                put("6", "r");
                put("f", "s");
                put("p", "t");
                put("7", "u");
                put("e", "v");
                put("o", "w");
                put("8", "1");
                put("d", "2");
                put("n", "3");
                put("9", "4");
                put("c", "5");
                put("m", "6");
                put("0", "7");
                put("b", "8");
                put("l", "9");
                put("a", "0");
                put("_z2C$q", ":");
                put("_z&e3B", ".");
                put("AzdH3F", "/");
            }
        };
        String dest = "", sub = "";
        for (int i = 0; i < url.length(); ) {
            sub = (i + 6 <= url.length()) ? url.substring(i, i + 6) : "";
            switch (sub) {
                case "_z2C$q":
                    dest += table.get(sub);
                    i += sub.length();
                    break;
                case "_z&e3B":
                    dest += table.get(sub);
                    i += sub.length();
                    break;
                case "AzdH3F":
                    dest += table.get(sub);
                    i += sub.length();
                    break;
                default:
                    String letter = String.valueOf(url.charAt(i));
                    if (table.containsKey(letter))
                        dest += table.get(letter);
                    else
                        dest += url.charAt(i);
                    ++i;
                    break;
            }
        }
        return dest;
    }
}
