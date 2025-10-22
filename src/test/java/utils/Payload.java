package utils;

public class Payload {



	public String createUserPayload(String name, String email, String accountType)
	{
		return " \"name\": \""+name+"\",\n"
				+ "\n"
				+ "              \"email\": \""+email+"\"\n"
				+ "              \n"
				+ "              \"accountType\" : \""+accountType+"\"";
	}
	
	public String updateUserPayload(String email)
	{
		
		return " \"email\": \""+email+"\",\n";
				
	}

	public String createTransactionPayload(String userId, String amount, String type, String recipientId)
	{
		return "\"userId\": \""+userId+"\",\n"
				+ "     \"amount\": \""+amount+"\"\\\",\n"
				+ "     \"type\": \""+type+"\",\n"
				+ "     \"recipientId\": \""+recipientId+"\"";
	}
}
