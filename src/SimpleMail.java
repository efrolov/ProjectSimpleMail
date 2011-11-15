
public class SimpleMail {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Contact test1 = new Contact();
		test1.setMyFirst("Mike");
		test1.setMyMI("C");
		test1.setMyLast("Jones");
		test1.setMyEmail("jeronimo@dabomb.com");
		test1.setMyStreetAddress("7000 Wisteria Ct.");
		test1.setMyCity("Po-Dunk");
		test1.setMyState("VA");
		test1.setMyZIP("66667");
		DataStore.getInstance().addContact(test1);
		Contact test2 = new Contact();
		test2.setMyFirst("Oksana");
		test2.setMyMI("B");
		test2.setMyLast("Jedavis");
		test2.setMyEmail("giggitygoo@quagmire.com");
		test2.setMyStreetAddress("152 N. Quahog St.");
		test2.setMyCity("Quahog");
		test2.setMyState("MA");
		test2.setMyZIP("Ummmm...");
		DataStore.getInstance().addContact(test2);
		MainFrame main = new MainFrame();
	}

}
