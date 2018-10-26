package mid2prac;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.fail;

import javax.swing.JTable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test; 



class Tester {
	private ConcreteTableModel ctm1;
	private JTable jt1;
	@Test
	void test() {
		assertEquals(2, 1+1);
	}
	
	@Test
	void testAddRow() {
		assertThrows(Exception.class, () -> {
			ctm1.addRow(ctm1, jt1);
		});
	}
	
	@Test
	void testEditRow() {
		assertThrows(Exception.class, () -> {
			ctm1.editRow(ctm1, jt1);
		});
	}
	
	@Test
	void testRemoveRow() {
		assertThrows(Exception.class, () -> {
			ctm1.removeRow(jt1);
		});
	}

}
