
public class SimpleMail {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Contact test1 = new Contact();
		test1.setMyFirst("Mike");
		test1.setMyMI("");
		test1.setMyLast("Rotchburns");
		test1.setMyEmail("jewsforjesus@dabomb.com");
		test1.setMyStreetAddress("7000 Wisteria Ct.");
		test1.setMyCity("Po-Dunk");
		test1.setMyState("VA");
		test1.setMyZIP("66666");
		DataStore.getInstance().addContact(test1);
		MainFrame main = new MainFrame();
	}

}
