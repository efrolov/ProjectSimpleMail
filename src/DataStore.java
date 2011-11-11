import java.util.ArrayList;


public class DataStore {
	private Configuration myConf;
	private ArrayList<Contact> myContacts;
	private static DataStore myInstance = null;
	
	private DataStore()
	{
		myContacts = new ArrayList<Contact>();
	}
	
	public static DataStore getInstance()
	{
		if(myInstance == null)
		{
			myInstance = new DataStore();
		}
		return myInstance;
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
	}
}
