package net.kiberion.jpa;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import net.kiberion.hibernate.EmbeddedDAO;
import net.kiberion.jpa.beans.Employee;
import net.kiberion.utils.StopWatch;

public class EmbeddedDAOTest {

    private static final Logger log = LogManager.getLogger();
    private EmbeddedDAO dao = new EmbeddedDAO();
    
    @Test
    public void simpleTest () {
        //dao.executeHQLQuery("");
        
        dao.init();
        
        Employee employee1 = new Employee(1, "Vardas", 27);
        Employee employee2 = new Employee(2, "Name", 55);

        StopWatch watch = new StopWatch();
        watch.start();
        dao.save(employee1);
        watch.endAndLog("Saving");
        
        watch.start();
        Employee loadedEmployee = dao.load(1, Employee.class);
        watch.endAndLog("Loading 1st time");
        assertNotNull (loadedEmployee);

        watch.start();
        loadedEmployee = dao.load(1, Employee.class);
        watch.endAndLog("Loading 2nd time");
        assertNotNull (loadedEmployee);
        
        loadedEmployee = dao.load(2, Employee.class);
        assertNull (loadedEmployee);
    }
}
