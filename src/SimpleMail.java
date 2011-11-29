
public class SimpleMail {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataStore.setFolderPath("data/");
		MainFrame main = new MainFrame();
		DataStore.getInstance().save();
	}

}
