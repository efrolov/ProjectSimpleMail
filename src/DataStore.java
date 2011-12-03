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
	private Configuration myConf;
	private ArrayList<Contact> myContacts;
	private static DataStore myInstance = null;
	private static String myFolder;

	/**
	 * <p>
	 * Creates a new DataStore object by checking for any stored application
	 * data and loading it if it does exist. {@code DataStore}'s filepath must
	 * have been initialized prior to the call to this function.
	 * </p>
	 */
	private DataStore() {
		myContacts = new ArrayList<Contact>();
		File folderHandle = new File(myFolder);
		if (folderHandle.listFiles() != null) {
			for (File f : folderHandle.listFiles()) {
				try {
					if (f.getCanonicalPath().endsWith(".cont")) {
						FileInputStream fis = new FileInputStream(f);
						ObjectInputStream ois = new ObjectInputStream(fis);
						myContacts.add((Contact) ois.readObject());
					} else if (f.getCanonicalPath().endsWith(".conf")) {
						FileInputStream fis = new FileInputStream(f);
						ObjectInputStream ois = new ObjectInputStream(fis);
						myConf = (Configuration) ois.readObject();
					}
				} catch (IOException e) {
					System.err
							.println("An error occurred while loading saved data.");
				} catch (ClassNotFoundException e) {
					System.err
							.println("Attempted to read in a class that does not exist.");
				}
			}
		}
		if(myConf==null)
		{
			myConf = new Configuration();
		}
	}

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
			myInstance = new DataStore();
		}
		return myInstance;
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

	public Configuration getMyConfiguration() {
		return myConf;
	}

	public void setConfiguration(Configuration c) {
		myConf = c;
	}

	public ArrayList<Contact> getMyContacts() {
		return myContacts;
	}

	public void addContact(Contact c) {
		myContacts.add(c);
	}

	public boolean containsContact(Contact c) {
		return myContacts.contains(c);
	}

	public void removeContact(Contact c) {
		myContacts.remove(c);
	}

	/**
	 * <p>
	 * Saves all classes held by this {@code DataStore} to disk.
	 * </p>
	 */
	public void save() {
		cleanDirectory(new File(myFolder));
		for (Contact c : myContacts) {
			try {
				File f = new File(myFolder + c.getMyEmail() + ".cont");
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(c);
			} catch (IOException e) {
				System.err
						.println("An error occurred while saving contact data.\n"
								+ e.getMessage());
			}
		}
		if (myConf != null) {
			try {
				File f = new File(myFolder + myConf.getMyEmail() + ".conf");
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(myConf);
			} catch (IOException e) {
				System.err
						.println("An error occurred while saving configuration data.");
			}
		}
	}
	
	private boolean cleanDirectory(File directory)
	{
		String[] list = directory.list();

		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				File entry = new File(directory, list[i]);
				if (entry.isDirectory()) {
					if (!cleanDirectory(entry))
						return false;
				} else {
					if (!entry.delete())
						return false;
				}
			}
		}
		return true;
	}
}
