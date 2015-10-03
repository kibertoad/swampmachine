package net.kiberion.jpa;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import net.kiberion.hibernate.EmbeddedDAO;
import net.kiberion.jpa.beans.Employee;

public class EmbeddedDAOTest {

    private EmbeddedDAO dao = new EmbeddedDAO();
    
    @Test
    public void simpleTest () {
        //dao.executeHQLQuery("");
        
        dao.init();
        
        Employee employee1 = new Employee(1, "Vardas", 27);
        Employee employee2 = new Employee(2, "Name", 55);
        
        dao.save(employee1);
        
        Employee loadedEmployee = dao.load(1, Employee.class);
        assertNotNull (loadedEmployee);

        loadedEmployee = dao.load(2, Employee.class);
        assertNull (loadedEmployee);
    }
}
