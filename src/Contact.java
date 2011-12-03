import java.io.Serializable;

/**
 * <p>
 * A class that represents a Contact containing a first name, a middle initial,
 * a last name, a street address, a city, a state, a ZIP code, a phone number,
 * and an email address.
 * </p>
 */
public class Contact implements Serializable {

	private static final long serialVersionUID = 4445549905840681824L;
	private boolean isBlank;
	private String myFirst, myMI, myLast, myStreetAddress, myCity, myState,
			myZIP, myPhone, myEmail;

	public Contact() {
		this.isBlank = true;
	}

	/**
	 * <p>
	 * Determines whether this {@code Contact} is equal to the {@code Contact}
	 * passed in the parameter. The parameter must be of type {@code Contact}.
	 * </p>
	 * 
	 * @param o
	 *            an {@code Object} to be compared to this {@code Contact}
	 */
	@Override
	public boolean equals(Object o) {
		Contact rhs = (Contact) o;
		// if(myFirst.compareTo(rhs.getMyFirst())==0 &&
		// myMI.compareTo(rhs.getMyMI())==0 &&
		// myLast.compareTo(rhs.myLast)==0 &&
		// myStreetAddress.compareTo(rhs.getMyStreetAddress())==0 &&
		// myCity.compareTo(rhs.getMyCity())==0 &&
		// myState.compareTo(rhs.getMyState())==0 &&
		// myZIP.compareTo(rhs.getMyZIP())==0 &&
		// myPhone.compareTo(rhs.getMyPhone())==0 &&
		if (this.myEmail.compareTo(rhs.getMyEmail()) == 0) {
			return true;
		}
		return false;
	}

	public String getMyCity() {
		return this.myCity;
	}

	public String getMyEmail() {
		return this.myEmail;
	}

	public String getMyFirst() {
		return this.myFirst;
	}

	public String getMyLast() {
		return this.myLast;
	}

	public String getMyMI() {
		return this.myMI;
	}

	public String getMyPhone() {
		return this.myPhone;
	}

	public String getMyState() {
		return this.myState;
	}

	public String getMyStreetAddress() {
		return this.myStreetAddress;
	}

	public String getMyZIP() {
		return this.myZIP;
	}

	public boolean isBlank() {
		return this.isBlank;
	}

	public void setMyCity(String myCity) {
		this.myCity = myCity;
	}

	public void setMyEmail(String myEmail) {
		this.myEmail = myEmail;
		this.isBlank = false;
	}

	public void setMyFirst(String myFirst) {
		this.myFirst = myFirst;
	}

	public void setMyLast(String myLast) {
		this.myLast = myLast;
	}

	public void setMyMI(String myMI) {
		this.myMI = myMI;
	}

	public void setMyPhone(String myPhone) {
		this.myPhone = myPhone;
	}

	public void setMyState(String myState) {
		this.myState = myState;
	}

	public void setMyStreetAddress(String myStreetAddress) {
		this.myStreetAddress = myStreetAddress;
	}

	public void setMyZIP(String myZIP) {
		this.myZIP = myZIP;
	}

}
