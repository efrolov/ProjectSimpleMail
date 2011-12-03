import java.io.Serializable;

/**
 * <p>A class that represents a Contact containing a first name, 
 * a middle initial, a last name, a street address, a city, a state, 
 * a ZIP code, a phone number, and an email address.</p>
 */
public class Contact implements Serializable {

	private static final long serialVersionUID = 4445549905840681824L;
	private String myFirst, myMI, myLast, myStreetAddress, myCity, myState, myZIP, myPhone, myEmail;
	private boolean isBlank;
	
	public Contact()
	{
		isBlank=true;
	}
	
	public String getMyFirst() {
		return myFirst;
	}
	public void setMyFirst(String myFirst) {
		this.myFirst = myFirst;
	}
	public String getMyMI() {
		return myMI;
	}
	public void setMyMI(String myMI) {
		this.myMI = myMI;
	}
	public String getMyLast() {
		return myLast;
	}
	public void setMyLast(String myLast) {
		this.myLast = myLast;
	}
	public String getMyStreetAddress() {
		return myStreetAddress;
	}
	public void setMyStreetAddress(String myStreetAddress) {
		this.myStreetAddress = myStreetAddress;
	}
	public String getMyCity() {
		return myCity;
	}
	public void setMyCity(String myCity) {
		this.myCity = myCity;
	}
	public String getMyState() {
		return myState;
	}
	public void setMyState(String myState) {
		this.myState = myState;
	}
	public String getMyZIP() {
		return myZIP;
	}
	public void setMyZIP(String myZIP) {
		this.myZIP = myZIP;
	}
	public String getMyPhone() {
		return myPhone;
	}
	public void setMyPhone(String myPhone) {
		this.myPhone = myPhone;
	}
	public String getMyEmail() {
		return myEmail;
	}
	public void setMyEmail(String myEmail) {
		this.myEmail = myEmail;
		isBlank=false;
	}
	
	/**
	 * <p>Determines whether this {@code Contact} is equal 
	 * to the {@code Contact} passed in the parameter.  
	 * The parameter must be of type {@code Contact}.</p>
	 * @param o an {@code Object} to be compared to this {@code Contact}
	 */
	@Override
	public boolean equals(Object o)
	{
		Contact rhs = (Contact)o;
//		if(myFirst.compareTo(rhs.getMyFirst())==0 &&
//				myMI.compareTo(rhs.getMyMI())==0 &&
//				myLast.compareTo(rhs.myLast)==0 &&
//				myStreetAddress.compareTo(rhs.getMyStreetAddress())==0 &&
//				myCity.compareTo(rhs.getMyCity())==0 &&
//				myState.compareTo(rhs.getMyState())==0 &&
//				myZIP.compareTo(rhs.getMyZIP())==0 &&
//				myPhone.compareTo(rhs.getMyPhone())==0 &&
				if(
						myEmail.compareTo(rhs.getMyEmail())==0
				)
		{
			return true;
		}
		return false;
	}
	
	public boolean isBlank()
	{
		return isBlank;
	}

}
