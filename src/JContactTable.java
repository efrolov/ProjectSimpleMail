import javax.swing.JTable;


public class JContactTable extends JTable {

	private static final long serialVersionUID = 118736475082270287L;
	
	public JContactTable(ContactTableModel myTableModel) {
		super(myTableModel);
		this.setDragEnabled(false);
	}
	
	public Contact getContactAtRow(int row)
	{
		Contact c = new Contact();
		for(int i = 0; i<9; i++)
		{
			Object v = this.getValueAt(row, i);
			switch(i)
			{
				case 0:
					c.setMyFirst((String)v);
					break;
				case 1:
					c.setMyMI((String)v);
					break;
				case 2:
					c.setMyLast((String)v);
					break;
				case 3:
					c.setMyEmail((String)v);
					break;
				case 4:
					c.setMyPhone((String)v);
					break;
				case 5:
					c.setMyStreetAddress((String)v);
					break;
				case 6:
					c.setMyCity((String)v);
					break;
				case 7:
					c.setMyState((String)v);
					break;
				case 8:
					c.setMyZIP((String)v);
					break;
				default:
					System.err.println("Column number requested does not exist.");
			}
		}
		return c;
	}

}
