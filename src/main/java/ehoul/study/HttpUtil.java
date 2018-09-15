package ehoul.study;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;  
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.http.NameValuePair; 
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;  


public class HttpUtil {

	public static final String ADD_URL = "http://apitest.ybren.com/Api.ashx";  
	public static String send(String url, Map<String,String> map,String encoding) throws ParseException, IOException{  
        String body = "";  
  
        //创建httpclient对象  
        CloseableHttpClient client = HttpClients.createDefault();  
        //创建post方式请求对象  
        HttpPost httpPost = new HttpPost(url);  
          
        //装填参数  
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        if(map!=null){  
            for (Entry<String, String> entry : map.entrySet()) {  
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));  
            }  
        }
        //设置参数到请求对象中  
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));  
  
        System.out.println("请求地址："+url);  
        System.out.println("请求参数："+nvps.toString());  
        
        //设置header信息  
        //指定报文头【Content-type】、【User-Agent】  
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");  
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
          
        //执行请求操作，并拿到结果（同步阻塞）  
        CloseableHttpResponse response = client.execute(httpPost);  
        //获取结果实体  
        HttpEntity entity = response.getEntity();  
        if (entity != null) {
            //按指定编码转换结果实体为String类型  
            body = EntityUtils.toString(entity, encoding);  
        }  
        EntityUtils.consume(entity);  
        //释放链接  
        response.close();  
        return body;  
    }
    public static void main(String [] args) throws ParseException, IOException{
       
        Map<String, String> map = new HashMap<String, String>();  
        map.put("DoType", "Login");
        map.put("Account", "test");
        map.put("Password", "123456");
        String body = send(ADD_URL, map,"utf-8");
        JSONObject jsonArray = JSONObject.fromObject(body);
        JSONObject data = JSONObject.fromObject(jsonArray.getString("Data"));
        System.out.println("交易响应结果：");
        System.out.println(data.getString("LoginToken"));
        Map<String, String> order = new HashMap<String, String>();  
        order.put("DoType", "GetOrders");
        order.put("Account", data.getString("Account"));
        order.put("LoginToken", data.getString("LoginToken"));
        order.put("StatusType", "1");
        order.put("Page", "1");
        String orderMessage = send(ADD_URL, order,"utf-8");
        System.out.println("-----------------------------------");
        System.out.println(orderMessage);
        JSONArray Orderdata = JSONArray.fromObject(JSONObject.fromObject(orderMessage).getString("Data"));
        Map<String, String> order2 = new HashMap<String, String>();  
        order2.put("DoType", "GetOrderDetailV2");
        order2.put("Account", data.getString("Account"));
        order2.put("LoginToken", data.getString("LoginToken"));
        order2.put("ProductId", Orderdata.getJSONObject(0).getString("ProductId"));
        String orderMessage2 = send(ADD_URL, order2,"utf-8");
        System.out.println("-----------------------------------");
        System.out.println(orderMessage2);
    
    }
   
}
