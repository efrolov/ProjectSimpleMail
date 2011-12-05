import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * <p>
 * A singleton representing the {@code Configuration} and all of the
 * {@code Contact}s for this application environment. This class handles the
 * loading and storing of all tracked classes to and from disk.
 * </p>
 */
public class DataStore {
	private static String myFolder;
	private static DataStore myInstance = null;
	/**
	 * <p>
	 * Returns an instance of this class if one exists and creates one if there
	 * is no such pre-existing instance. A call to setFolderPath must have been
	 * made prior to any calls to this function.
	 * </p>
	 * 
	 * @return a {@code DataStore} instance
	 */
	public static DataStore getInstance() {
		if (myInstance == null) {
			try {
				myInstance = new DataStore();
			} catch (IOException e) {
				AlertDialog a = new AlertDialog(
						"An error occurred while trying to read in objects."
						);
			} catch (Exception e) {
				AlertDialog a = new AlertDialog(
						"A generic error occurred: "+e.getMessage()
						);
			}
		}
		return myInstance;
	}
	public static String getMyFolderPath() {
		return myFolder;
	}

	/**
	 * <p>
	 * Statically sets the folderpath for this application runtime environment.
	 * Must be called before any calls to getInstance.
	 * </p>
	 * 
	 * @param fp
	 *            a {@code String} representing the canonical path to this
	 *            application's data folder
	 */
	public static void setFolderPath(String fp) {
		myFolder = fp;
	}

	private Configuration myConf;

	private ArrayList<Contact> myContacts;

	/**
	 * <p>
	 * Creates a new DataStore object by checking for any stored application
	 * data and loading it if it does exist. {@code DataStore}'s filepath must
	 * have been initialized prior to the call to this function.
	 * </p>
	 */
	private DataStore() throws IOException, Exception {
		this.myContacts = new ArrayList<Contact>();
		File folderHandle = new File(myFolder);
		if (folderHandle.listFiles() != null) {
			for (File f : folderHandle.listFiles()) {
				if (f.getCanonicalPath().endsWith(".cont")) {
					FileInputStream fis = new FileInputStream(f);
					ObjectInputStream ois = new ObjectInputStream(fis);
					this.myContacts.add((Contact) ois.readObject());
				} else if (f.getCanonicalPath().endsWith(".conf")) {
					FileInputStream fis = new FileInputStream(f);
					ObjectInputStream ois = new ObjectInputStream(fis);
					this.myConf = (Configuration) ois.readObject();
				}
			}
		}
		if (this.myConf == null) {
			this.myConf = new Configuration();
		}
	}

	public void addContact(Contact c) {
		this.myContacts.add(c);
	}

	private boolean cleanDirectory(File directory) {
		String[] list = directory.list();

		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				File entry = new File(directory, list[i]);
				if (entry.isDirectory()) {
					if (!this.cleanDirectory(entry)) {
						return false;
					}
				} else {
					if (!(entry.getName().compareTo("icon.png") == 0)) {
						if (!entry.delete()) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public boolean containsContact(Contact c) {
		return this.myContacts.contains(c);
	}

	public Configuration getMyConfiguration() {
		return this.myConf;
	}

	public ArrayList<Contact> getMyContacts() {
		return this.myContacts;
	}

	public void removeContact(Contact c) {
		this.myContacts.remove(c);
	}

	/**
	 * <p>
	 * Saves all classes held by this {@code DataStore} to disk.
	 * </p>
	 */
	public void save() throws IOException {
		this.cleanDirectory(new File(myFolder));
		for (Contact c : this.myContacts) {
			File f = new File(myFolder + c.getMyEmail() + ".cont");
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(c);
		}
		if (this.myConf != null) {
			File f = new File(myFolder + this.myConf.getMyEmail() + ".conf");
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.myConf);
		}
	}

	public void setConfiguration(Configuration c) {
		this.myConf = c;
	}
}
