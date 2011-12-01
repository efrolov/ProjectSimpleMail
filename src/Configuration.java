import java.io.Serializable;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>Represents the configuration for the main user of this application.
 *   Holds an IP for the SMTP server and a string for the main user's 
 *   email address.</p>
 */
public class Configuration implements Serializable {

	private static final long serialVersionUID = -4042341160358404576L;
	private InetAddress mySMTP;
	private String myEmail;
	
	/**
	 * <p>Creates a new {@code Configuration} object with the specified 
	 * IP address for the SMTP server and the specified e-mail address.</p>
	 * @param ip an {@code InetAddress} representing the IP of the SMTP server
	 * @param e a {@code String} representing the e-mail address
	 */
	public Configuration(InetAddress ip, String e)
	{
		mySMTP = ip;
		myEmail = e;
	}
	
	public Configuration()
	{
		try {
			mySMTP = Inet4Address.getLocalHost();
		} catch (UnknownHostException e) {
			System.err.println("You have problems that are " +
					"far worse than the scope of this program.  " +
					"Kindly, turn off your computer and go home.");
		}
		myEmail = new String();
	}
	
	/**
	 * <p>Gets the IP address of the SMTP server for this 
	 * {@code Configuration} object.</p>
	 * @return an {@code Inet4Address} representing the IP of the SMTP server
	 */
	public InetAddress getMySMTP() {
		return mySMTP;
	}
	
	/**
	 * <p>Sets the IP address of the SMTP server for this 
	 * {@code Configuration} object.</p>
	 * @param mySMTP an {@code Inet4Address} representing the IP of the SMTP server
	 */
	public void setMySMTP(Inet4Address mySMTP) {
		this.mySMTP = mySMTP;
	}
	
	/**
	 * <p>Gets the email address for this {@code Configuration} object.</p>
	 * @return a {@code String} representing the email address
	 */
	public String getMyEmail() {
		return myEmail;
	}
	
	/**
	 * <p>Sets the email address of the main user for this 
	 * {@code Configuration} object.</p>
	 * @param myEmail a {@code String} representing the email address
	 */
	public void setMyEmail(String myEmail) {
		this.myEmail = myEmail;
	}
}
