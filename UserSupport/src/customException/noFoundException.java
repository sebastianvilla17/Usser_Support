package customException;

public class noFoundException extends Exception {

	private String id = "";

	/**
	 * RepeatedUserException
	 * <p>
	 * desc:
	 * </p>
	 * This method is responsible for creating the exception and assigning a message
	 *
	 * @param id This parameter refers to the user's document number
	 *
	 * @return This method returns the message that will be released at home if the
	 *         exception is met.
	 */
	public noFoundException(String id) {
		super("the user " + id + " is not registered");

	}

	public String getId() {

		return id;
	}
}
