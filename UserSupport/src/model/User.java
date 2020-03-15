package model;

import java.io.Serializable;

public class User implements Serializable{
	private String documentType = "";
	private String documentNumber = "";
	private String name = "";
	private String lastName = "";
	private String phone = "";
	private String adress = "";
	private boolean discontinued = false;

	public User(String documentType, String documentNumber, String name, String lastName, String phone, String adress) {

		this.discontinued = false;
		this.documentType = documentType;
		this.documentNumber = documentNumber;
		this.name = name;
		this.lastName = lastName;
		this.phone = phone;
		this.adress = adress;

	}

	public boolean isDiscontinued() {
		return discontinued;
	}
	
	public int compareTo ( User ot) {
		int comparation;
		if(documentNumber.compareTo(ot.documentNumber)<0) {
			comparation = -1;
		}else if(documentNumber.compareTo(ot.documentNumber)>0) {
			comparation = 1;
		}else {
			comparation = 0;
		}
		return comparation;
	}

	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * getDocumenType
	 * <p>
	 * desc:
	 * </p>
	 * This method allows to obtain private data
	 *
	 * @return this method returns a type data String whit the document type of the
	 *         user
	 */
	public String getDocumenType() {

		return documentType;
	}

	/**
	 * getDocumenNumber
	 * <p>
	 * desc:
	 * </p>
	 * This method allows to obtain private data
	 *
	 * @return this method returns a type data String whit the document number of
	 *         the user
	 */
	public String getDocumenNumber() {

		return documentNumber;
	}

	/**
	 * getName
	 * <p>
	 * desc:
	 * </p>
	 * This method allows to obtain private data
	 *
	 * @return this method returns a type data String whit the name of the user
	 */
	public String getName() {

		return name;
	}

	/**
	 * getLastName
	 * <p>
	 * desc:
	 * </p>
	 * This method allows to obtain private data
	 *
	 * @return this method returns a type data String whit the last name of the user
	 */
	public String getLastName() {

		return lastName;
	}

	/**
	 * getPhone
	 * <p>
	 * desc:
	 * </p>
	 * This method allows to obtain private data
	 *
	 * @return this method returns a type data String whit the phone number of the
	 *         user
	 */
	public String getPhone() {

		return phone;
	}

	/**
	 * getAdress
	 * <p>
	 * desc:
	 * </p>
	 * This method allows to obtain private data
	 *
	 * @return this method returns a type data String whit the adress of the user
	 */
	public String getAdress() {

		return adress;
	}

	/**
	 * toString
	 * <p>
	 * desc:
	 * </p>
	 * This method is responsible for concatenating the information that needs to be
	 * displayed to the user.
	 *
	 * @return The emtodo returns a String with the information concatenated
	 */
	public String toString() {
		return "Document: "+ documentNumber+ "// "+ "Name : " + name + " " + lastName + "//" + "Phone: " + phone  ;
	}

}
