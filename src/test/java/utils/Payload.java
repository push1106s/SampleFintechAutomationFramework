package utils;

/**
 * Utility class for generating JSON payload strings used in API requests.
 */

public class Payload {


	/**
	 * Constructs a JSON-formatted string for creating a new user.
	 *
	 * @param name the user's name
	 * @param email the user's email address
	 * @param accountType the type of account (e.g., "admin", "standard")
	 * @return a JSON string representing the user creation payload
	 */	
	public String createUserPayload(String name, String email, String accountType)
	{
		return " \"name\": \""+name+"\",\n"
				+ "\n"
				+ "              \"email\": \""+email+"\"\n"
				+ "              \n"
				+ "              \"accountType\" : \""+accountType+"\"";
	}

	/**
	 * Constructs a JSON-formatted string for updating a user's email.
	 *
	 * @param email the new email address
	 * @return a JSON string representing the email update payload
	 */
	public String updateUserPayload(String email)
	{

		return " \"email\": \""+email+"\",\n";

	}

	/**
	 * Constructs a JSON-formatted string for creating a transaction.
	 *
	 * @param userId the ID of the user initiating the transaction
	 * @param amount the transaction amount
	 * @param type the type of transaction (e.g., "transfer", "deposit")
	 * @param recipientId the ID of the recipient user
	 * @return a JSON string representing the transaction payload
	 */
	public String createTransactionPayload(String userId, String amount, String type, String recipientId)
	{
		return "\"userId\": \""+userId+"\",\n"
				+ "     \"amount\": \""+amount+"\"\\\",\n"
				+ "     \"type\": \""+type+"\",\n"
				+ "     \"recipientId\": \""+recipientId+"\"";
	}
}
