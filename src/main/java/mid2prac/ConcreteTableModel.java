package mid2prac;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ConcreteTableModel extends AbstractTableModel{
	
	private JTextField field1 = new JTextField();
	private JTextField field2 = new JTextField();
	private JTextField field3 = new JTextField();
	private JTextField field4 = new JTextField();
	private JTextField field5 = new JTextField();
	
	private Object[] message = {
			"Name", field1, 
			"Age", field2,
			"Gender", field3,
			"Salary", field4,
			"Email", field5,
	};

	private Worker worker1 = new Worker();
	private static final long serialVersionUID = 1L;
	private List<String[]> list1 = new ArrayList<String[]>();

	private WorkerList workers = new WorkerList();
	private List<Worker> workerList1 = new ArrayList<>();
	//private String[] title = {"Name","Age","Gender","Salary", "Email"};
	private String[] title = {Attributes.name.name(),
			Attributes.age.name(),
			Attributes.gender.name(),
			Attributes.salary.name(),
			Attributes.email.name()};

	ConcreteTableModel(String filename) throws FileNotFoundException {
		Scanner inputStream;
		String data;
		inputStream = new Scanner(new File(filename));
		data = inputStream.nextLine();
		while(inputStream.hasNext()) {
			data = inputStream.nextLine();

			String[] str1 = data.split(",");

			String[] str2 = {str1[0], str1[1], str1[2] , str1[3] , str1[4]};

			if (isValidEmailAddress(str1[4])) {
				System.out.println(str1[0] + "'s email: " +  str1[4] + " is valid");
			}
			else {
				System.out.println(str1[0] + "'s email: " +  str1[4] + " is not valid");
			}

			list1.add(str2);
		}
		inputStream.close();
	}

	public String getColumnName(int index) {
		return title[index];
	}

	public int getColumnCount() {
		return list1.get(0).length;
	}

	public int getRowCount() {
		return list1.size();
	}

	public Object getValueAt(int row, int col) {
		return list1.get(row)[col];
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public void editValueAtRow(int row, int col) {
		isCellEditable(row, col);
	}

	public void setValueAt(Object value, int row, int col) {
		list1.get(row)[col] = value.toString();
	}

	public void saveAsCSV(File file) throws IOException {
		String name = new String(file.getAbsolutePath()+".csv");
		File file2 = new File(name);
		FileWriter f1 = new FileWriter(file2);

		PrintWriter p1 = new PrintWriter(f1);

		for (int i = 0; i < list1.size(); i ++) {
			for (int j = 0; j < list1.get(0).length; j ++) {
				p1.print(list1.get(i)[j]);
				System.out.print(list1.get(i)[j]);
				if (j+1 < list1.get(0).length) {
					p1.print(",");
					System.out.print(",");
				}
			}
			p1.print("\r\n");
			System.out.print("\r\n");
		}
		p1.close();
		f1.close();
	}
	
	public void addRow(ConcreteTableModel ctm1, JTable jt1) {
		String newRow [] = new String[5];

		for (int i = 0; i < newRow.length; i ++) {
			newRow[i] = new String();
		}

		list1.add(newRow);
		fireTableRowsInserted(list1.size()-1, list1.size()-1);
		
		int option = JOptionPane.showConfirmDialog(null, message, "Add New Row", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION)
		{
			ctm1.setValueAt(field1.getText(), list1.size()-1, 0);
			ctm1.setValueAt(field2.getText(), list1.size()-1, 1);
			ctm1.setValueAt(field3.getText(), list1.size()-1, 2);
			ctm1.setValueAt(field4.getText(), list1.size()-1, 3);
			ctm1.setValueAt(field5.getText(), list1.size()-1, 4);
		}
		jt1.repaint();
	}
	
	public void editRow(ConcreteTableModel ctm1, JTable jt1) {
		
		if (jt1.isRowSelected(jt1.getSelectedRow())) {
			field1.setText(list1.get(jt1.getSelectedRow())[0]);
			field2.setText(list1.get(jt1.getSelectedRow())[1]);
			field3.setText(list1.get(jt1.getSelectedRow())[2]);
			field4.setText(list1.get(jt1.getSelectedRow())[3]);
			field5.setText(list1.get(jt1.getSelectedRow())[4]);
			
			int option = JOptionPane.showConfirmDialog(null, message, "Edit Selected Row", JOptionPane.OK_CANCEL_OPTION);

			if (option == JOptionPane.OK_OPTION)
			{
				ctm1.setValueAt(field1.getText(), jt1.getSelectedRow(), 0);
				ctm1.setValueAt(field2.getText(), jt1.getSelectedRow(), 1);
				ctm1.setValueAt(field3.getText(), jt1.getSelectedRow(), 2);
				ctm1.setValueAt(field4.getText(), jt1.getSelectedRow(), 3);
				ctm1.setValueAt(field5.getText(), jt1.getSelectedRow(), 4);
			}
			jt1.repaint();
		}
		else {
			JOptionPane.showMessageDialog(null, "Plese Select a Row to Edit","Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void removeRow(JTable jt1) {
		int row = 0;
		if (jt1.isRowSelected(jt1.getSelectedRow())) {
			row = jt1.getSelectedRow();
			list1.remove(row);
		}
		else {
			JOptionPane.showMessageDialog(null, "Plese Select a Row to Delete","Error",
					JOptionPane.ERROR_MESSAGE);
			System.out.println("Please Select a Row");
		}
		fireTableRowsDeleted(row, row);
	}

	public void saveAsXML(File file) throws IOException {
		String name = new String(file.getAbsolutePath()+".xml");

		for (int i = 0; i < list1.size(); i ++) {
			worker1 = new Worker();
			worker1.setName(list1.get(i)[0].toString());
			worker1.setAge(list1.get(i)[1].toString());
			worker1.setGender(list1.get(i)[2].toString());
			worker1.setSalary(list1.get(i)[3].toString());
			worker1.setEmail(list1.get(i)[4].toString());

			workerList1.add(worker1);
		}
		workers.setWorkerList(workerList1);

		try {
			File file2 = new File(name);
			JAXBContext jaxbContext = JAXBContext.newInstance(WorkerList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(workers, file2);
			jaxbMarshaller.marshal(workers, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}
}
