import java.io.Serializable;
import java.net.Inet4Address;

public class Configuration implements Serializable {

	private static final long serialVersionUID = -4042341160358404576L;
	private Inet4Address mySMTP;
	private String myEmail;
	
	public Configuration(Inet4Address ip, String e)
	{
		mySMTP = ip;
		myEmail = e;
	}
	
	public Inet4Address getMySMTP() {
		return mySMTP;
	}
	public void setMySMTP(Inet4Address mySMTP) {
		this.mySMTP = mySMTP;
	}
	public String getMyEmail() {
		return myEmail;
	}
	public void setMyEmail(String myEmail) {
		this.myEmail = myEmail;
	}
}
