/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sofieamalielandt
 */
public class EmployeeFacadeTest {

    static EntityManagerFactory emf;
    static EmployeeFacade ef;

    public EmployeeFacadeTest() {

    }

    @BeforeClass
    public static void setUpClass() {

        emf = Persistence.createEntityManagerFactory("pu");
        ef = EmployeeFacade.getFacadeExample(emf);
    }

    @AfterClass
    public static void tearDownClass() {
        //removes employee from createEmployee test
        ef.removeEmployee(ef.getEmployeesByName("Louise").get(0));
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    /**
     * Test of getFacadeExample method, of class EmployeeFacade.
     */
    @Test
    public void testGetFacadeExample() {

        EmployeeFacade result = EmployeeFacade.getFacadeExample(emf);
        assertNotNull(result);
    }

    /**
     * Test of getEmployeeById method, of class EmployeeFacade.
     */
    @Test
    public void testGetEmployeeById() {

        Employee employee = ef.getEmployeeById(1);

        assertNotNull(employee);
        assertEquals("Amanda", employee.getName());
        assertEquals("Hovedvejen 3", employee.getAdress());
        assertEquals(3000, employee.getSalary());
    }

    /**
     * Test of getEmployeesByName method, of class EmployeeFacade.
     */
    @Test
    public void testGetEmployeesByName() {

        List<Employee> employees = ef.getEmployeesByName("Laura");

        assertNotNull(employees);
        assertEquals(1, employees.size());
        assertEquals("Laura", employees.get(0).getName());
        assertEquals("Parallelvej 14", employees.get(0).getAdress());
        assertEquals(4200, employees.get(0).getSalary());
    }

    /**
     * Test of getAllEmployees method, of class EmployeeFacade.
     */
    @Test
    public void testGetAllEmployees() {

        List<Employee> employees = ef.getAllEmployees();
        assertNotNull(employees);
        assertEquals(6, employees.size());
    }

    /**
     * Test of getEmployeesWithHighestSalary method, of class EmployeeFacade.
     */
    @Test
    public void testGetEmployeesWithHighestSalary() {

        List<Employee> employees = ef.getEmployeesWithHighestSalary();

        assertNotNull(employees);
        assertEquals(2, employees.size());
        assertEquals("Laura", employees.get(0).getName());
        assertEquals("Parallelvej 14", employees.get(0).getAdress());
        assertEquals(4200, employees.get(0).getSalary());
    }

    /**
     * Test of createEmployee method, of class EmployeeFacade.
     */
    @Test
    public void testCreateEmployee() {
        
        int employeesBefore = ef.getAllEmployees().size();
        ef.createEmployee("Louise", "Prinsessevej 18", 4000);
        int employeesAfter = ef.getAllEmployees().size();
        assertTrue(employeesBefore < employeesAfter);
    }

}
