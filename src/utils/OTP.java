package utils;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;



public class OTP {
	
//	public static JSONObject  getOTP(String Mobile_Number) {
//
//
//        JSONObject response = new JSONObject();
//        try {
//            JSONObject apiRequest = new JSONObject();
//            
//            System.out.println("Ia maaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//
//            apiRequest.put("msg", "Dear Customer, the OTP to verify your number is 143143. Do not share this with anyone. Magma HDI");
//            apiRequest.put("mobNo", Mobile_Number);
//            apiRequest.put("otp", "true");
//            apiRequest.put("s", "0");
//            
//            JSONObject demo=new JSONObject();
//            demo.put("date", apiRequest);
//
//            
//            System.out.println("before api response");
//            
//            String apiResponse =(HttpMethod.post("https://uatpg.magma-hdi.co.in:444/Esales/api/Communication/SendSMS", demo));
////            System.out.println(apiResponse);
//            System.out.println("after api response");
//            if(apiResponse.has("meta")) {
//            	JSONObject meta_response = apiResponse.getJSONObject("meta");
//            	
//            	JSONObject status_response = meta_response.getJSONObject("status");
//            	
//       
//            	String code = status_response.getString("code");
//            	
//            	String msg = status_response.getString("msg");
//            	
//            	System.out.println(code +"  "+ msg);
//            }
//            
//            System.out.println("Heyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
//        }catch(Exception e) {
//            System.out.println("I M NOT WORKING");
//        }
//		return response;
//    }

	public static void main(String[] args) {
		//getOTP("6369221936");
	}
}
