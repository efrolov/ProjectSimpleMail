import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class DataStore {
	private Configuration myConf;
	private ArrayList<Contact> myContacts;
	private static DataStore myInstance = null;
	private static String myFolder;
	
	private DataStore()
	{
		myContacts = new ArrayList<Contact>();
		File folderHandle = new File(myFolder);
		if(folderHandle.listFiles()!=null)
		{
			for(File f : folderHandle.listFiles())
			{
				try
				{
					if(f.getCanonicalPath().endsWith(".cont"))
					{
						FileInputStream fis = new FileInputStream(f);
						ObjectInputStream ois = new ObjectInputStream(fis);
						myContacts.add((Contact)ois.readObject());
					}
					else if(f.getCanonicalPath().endsWith(".conf"))
					{
						FileInputStream fis = new FileInputStream(f);
						ObjectInputStream ois = new ObjectInputStream(fis);
						myConf = (Configuration)ois.readObject();
					}
				}
				catch(IOException e)
				{
					System.err.println("An error occurred while loading saved data.");
				}
				catch(ClassNotFoundException e)
				{
					System.err.println("Attempted to read in a class that does not exist.");
				}
			}
		}
	}
	
	public static DataStore getInstance()
	{
		if(myInstance == null)
		{
			myInstance = new DataStore();
		}
		return myInstance;
	}
	
	public static void setFolderPath(String fp)
	{
		myFolder = fp;
	}

	public Configuration getMyConfiguration() {
		return myConf;
	}
	
	public void setConfiguration(Configuration c)
	{
		myConf = c;
	}

	public ArrayList<Contact> getMyContacts() {
		return myContacts;
	}
	
	public void addContact(Contact c)
	{
		myContacts.add(c);
	}
	
	public void save()
	{
		for(Contact c : myContacts)
		{
			try
			{
				File f = new File(myFolder+c.getMyEmail()+".cont");
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(c);
			}
			catch(IOException e)
			{
				System.err.println("An error occurred while saving contact data.\n"+e.getMessage());
			}
		}
		if(myConf!=null)
		{
			try {
				File f = new File(myFolder + myConf.getMyEmail() + ".conf");
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(myConf);
			} catch (IOException e) {
				System.err.println("An error occurred while saving configuration data.");
			}
		}
	}
}
