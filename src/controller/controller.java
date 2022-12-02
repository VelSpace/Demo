package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

@Path("/Controller")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class controller {
	private static final Logger logger = LogManager.getLogger(controller.class);

	@POST
	@Path("/validate_number")
	public Response phoneNumberValidate(String str) {
		JSONObject response = new JSONObject();
		System.out.println("Heyyyyyyyyyyy universe");
		try {
			Pattern phonePattern = Pattern.compile("\\d+");
			Matcher m = phonePattern.matcher(str);
			while (m.find()) {
				str = m.group();
				if (str.length() > 10) {
					if (m.group().substring(0, 3).equals("+91"))
						str = str.substring(3);
					else if (m.group().substring(0, 2).equals("91"))
						str = str.substring(str.length() - 10, str.length());
					else if (m.group().substring(0, 1).equals("0"))
						str = str.substring(1);
				}
				phonePattern = Pattern.compile("\\d{10}");
				m = phonePattern.matcher(str);
				if (m.matches())
					response.put("found", m.group());
				else {
					response.put("status", "Invalid");
				}
			}
			
			//response.put("otp", p);
			return successResponse(response);
		}

		catch (Exception e) {
			logger.error("Exception :: " + e);
			return improperJsonResponse(response, e.getMessage());
		}

	}

	@POST
	@Path("/validate_otp")
	public Response otpverify(String rs) {
		JSONObject response = new JSONObject();
		try {
			JSONObject request = new JSONObject(rs);
			String check = request.getString("generated_otp");
			if (check.equals(request.getString("text"))) {
				response.put("found", check);
			} else {
				response.put("status", "Invalid");
			}
			return successResponse(response);
		} catch (Exception e) {
			return improperJsonResponse(response, e.getMessage());
		}
	}
	
	@POST
	@Path("/Product_type")
	public Response Product_type(String rs) {
		JSONObject response = new JSONObject();
		try {
			JSONObject request = new JSONObject(rs);
			if(!request.has("text")) {
				return fieldMisssing(response);
			}
			String user_choice = request.getString("text").toLowerCase();
			
			if(user_choice.contains("1") || user_choice.contains("private car") ||
					user_choice.contains("2") || user_choice.contains("two wheeler") || 
					user_choice.contains("3") || user_choice.contains("commercial vehicle")) {
				response.put("endrosement_message"," Choose the type of endorsment from the below options:\r\n"
						+ " 1. Name Transfer\r\n"
						+ " 2. Vehicle Number Update\r\n"
						+ " 3. Inclusion of CNG kit\r\n"
						+ " 4. Address/Email/Mobile Number Update\r\n");
				
				if(user_choice.contains("1") || user_choice.contains("private car")) {
					response.put("found", "Private car");
				}
				else if(user_choice.contains("2") || user_choice.contains("two wheeler")){
					response.put("found", "Two wheeler");
				}else {
					response.put("found", "Commercial vehicle");
				}
			}else if(user_choice.contains("4") || user_choice.contains("health")) {
				response.put("endrosement_message"," Choose the type of endorsment from the below options:\r\n"
						+ " 1. Address Change\r\n"
						+ " 2. Nominee Detail Correction\r\n"
						+ " 3. Telephone/Email ID Change\r\n"
						+ " 4. Nominee Change\r\n");
				
				response.put("found", "Health");
			}else if(user_choice.contains("5") || user_choice.contains("others")){
				response.put("endrosement_message", "Thank you! Our executive will shortly connect with you. \r\n"
						+ "  Is there anything else that I can help you with?");
				response.put("found", "Others");
			}else {
				response.put("status", "Invalid");
			}
			return successResponse(response);
		} catch (Exception e) {
			return improperJsonResponse(response, e.getMessage());
		}
	}

	private static Response successResponse(JSONObject response) {
		logger.info("Response Form API - " + response.toString());
		return Response.status(200).entity(response.toString()).build();
	}

	private static Response fieldMisssing(JSONObject response) {
		response.put("status", "Invalid");
		response.put("Reason", "userMessage Field missing");
		logger.info("Response Form API - " + response.toString());
		return Response.status(204).entity(response.toString()).build();
	}

	private static Response improperJsonResponse(JSONObject response, String error) {
		logger.error("JSON format Exceoption - " + error);
		response.put("status", "Invalid");
		response.put("reason", "Improper JSON format");
		logger.info("response sent is - " + response.toString());
		return Response.status(500).entity(response.toString()).build();
	}
}
