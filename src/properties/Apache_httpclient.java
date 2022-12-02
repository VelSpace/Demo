package properties;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class Apache_httpclient {

public static JSONObject post(String url, JSONObject body, JSONObject header, String botName) {
        JSONObject response = new JSONObject();
      //  logger.info("URL : " + url + "\nRequest body : " + body + "\nRequest header : " + header+ "\nFor Bot : " + botName);
        try {

            HttpClient httpClient = HttpClientBuilder.create().build();

            HttpPost post = new HttpPost(url);

            // post.addHeader("Content-type", "application/json");
            if (header != null && header.length() != 0) {
                header.names().forEach(keyStr -> { post.addHeader(keyStr.toString(), header.get(keyStr.toString()).toString());}
               );
            }
            // set entity if body not null.
            if (body != null && body.length() != 0) {
                if (header != null && header.has("Content-Type")
                        && header.getString("Content-Type").equals("application/x-www-form-urlencoded")) {
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                    for (String key : body.keySet()) {
                        nameValuePairs.add(new BasicNameValuePair(key, body.getString(key)));
                    }

                    post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                } else {
                    StringEntity entity = new StringEntity(body.toString(), "UTF-8");
                    post.setEntity(entity);
                }
            }
            HttpResponse responseHttp = httpClient.execute(post);
            HttpEntity entity2 = responseHttp.getEntity();
            String statusCode = Integer.toString(responseHttp.getStatusLine().getStatusCode());
            response.put("apiStatusCode", statusCode);

            if (!statusCode.startsWith("2")) {
                //logger.error("Error via API with status " + statusCode+"\nURL : " + url + "\nRequest body : " + body + "\nRequest header : " + header+ "\nFor Bot : " + botName);
            }
            if (entity2 != null) {
                String content = EntityUtils.toString(entity2);
              //  logger.info("Response from API ---------->>>>> " + content);
                JSONObject apiResponse = new JSONObject(content);
                response.put("apiResponse", apiResponse.toString());
            } else {
               // logger.error("Null response received from API"+ "\nURL : " + url + "\nRequest body : " + body + "\nRequest header : " + header+ "\nFor Bot : " + botName);
            }
        } catch (Exception ex) {
           // logger.error("Exception occured in post request "+ ex + " \nURL : " + url + "\nRequest body : " + body + "\nRequest header : " + header+ "\nFor Bot : " + botName);
        }
        return response;
    }

}
