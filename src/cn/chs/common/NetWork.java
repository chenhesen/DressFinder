package cn.chs.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class NetWork {
	public static String sendGETRequest(URL url, String charset) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.130 Safari/537.36");
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        while(conn.getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM || conn.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
            URL newURL = new URL(conn.getHeaderField("location"));
            conn = (HttpURLConnection)newURL.openConnection();
        }

        if (conn.getResponseCode() == 200) {
            InputStream in = conn.getInputStream();
            String result = IOUtils.toString(in, charset);
            return (new String(result.getBytes(charset), charset));
        }
        return "";
    }
}
