package my;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import sun.misc.Regexp;
import java.awt.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class  InternetWorm{
    private static  String WORM_URL = null;
    private static  final  String IMG_RE = "https.*?.jpg";
    private static  final  String SRC_RE = "";
    public InternetWorm(String wormUrl){
        this.WORM_URL = wormUrl;
    }

    private String getHtmlFile() throws Exception {
        StringBuilder sb = new StringBuilder("");
        URLConnection connection = (new URL(WORM_URL)).openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
    /*
    public void getWormUrl_Test() throws Exception{
        URLConnection connection = (new URL(WORM_URL)).openConnection();
        InputStreamReader is = new InputStreamReader(connection.getInputStream());
        BufferedReader br = new BufferedReader(is);
        File f = new File("C:/Picture/index.html");
        FileWriter fw = new FileWriter(f);
        String line = null;
        while((line = br.readLine()) != null){
            fw.append(line);
            fw.append('\n');
        }
        is.close();
        br.close();
        fw.close();
    }*/
    private  String[] getImgSrc(String html)throws  Exception{
        String[] imgsrc = new String[10000]; int id  = 0;
        Pattern p = Pattern.compile(IMG_RE);
        Matcher m = p.matcher(html);
        while(id < 10000 && m.find()){
            int flag = 1;
            for(int i = 1;i < id; i++){
                if(m.group() == imgsrc[i]) {
                    flag = 0;
                }
            }
            if(flag == 0) continue;
            imgsrc[id++] = m.group();
        }
        return imgsrc;
    }

    private void Dow(String[] src)throws  Exception{
        for(int i = 1; i < 10000; i++){
            if(src[i] == null) return ;
            URLConnection con = (URLConnection) (new URL(src[i])).openConnection() ;
            InputStream is = con.getInputStream();
            FileOutputStream fos = new FileOutputStream("C:/Picture/" + i + ".jpg");
            byte[] buf = new byte[1024]; int len;
            while((len = is.read(buf)) != -1){
                fos.write(buf, 0, len);
            }
            is.close();
            fos.close();
        }

    }
    /*
    public void getImgSrc_Test()throws  Exception{
        BufferedReader br = new BufferedReader(new FileReader("C:/Picture/index.html"));
        StringBuilder sb = new StringBuilder("");
        String line = null;
        while((line = br.readLine()) != null) {
            sb.append(line);
        }

        Pattern p = Pattern.compile(IMG_RE);
        Matcher m = p.matcher(sb);

        while(m.find()){
            System.out.println(m.group());
        }

    }
    */

    public void solve()throws  Exception{
        //this.Dow(this.getImgSrc(this.getHtmlFile()));

        
        String[] s = this.getImgSrc(this.getHtmlFile());
        for(int i = 0;  i < 100; i++){
            if(s[i] == null) return ;
            System.out.println(s[i]);
        }
        

    }
}
public class  App{
    public static  void  main  (String[] args) throws  Exception{
        InternetWorm iw = new InternetWorm("https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&sf=1&fmq=&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&fm=index&pos=history&word=1080x1920%E9%AB%98%E6%B8%85%E5%8A%A8%E6%BC%AB%E5%A3%81%E7%BA%B8");
        iw.solve();
    }
}
