/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Customer;
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
public class CustomerFacadeTest {

    static EntityManagerFactory emf;
    static CustomerFacade cf;

    public CustomerFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {

        emf = Persistence.createEntityManagerFactory("pu");
        cf = CustomerFacade.getCustomerFacade(emf);
    }

    @AfterClass
    public static void tearDownClass() {
        
        //removes Customer from createCustomer test to reset database
        Customer c = cf.getAllCustomers().get(cf.getAllCustomers().size()-1);
        cf.removeCustomer(c);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCustomerFacade method, of class CustomerFacade.
     */
    @Test
    public void testGetCustomerFacade() {
        CustomerFacade result = CustomerFacade.getCustomerFacade(emf);
        assertNotNull(result);
    }

    /**
     * Test of findById method, of class CustomerFacade.
     */
    @Test
    public void testFindById() {
        
        Customer customer = cf.findById(1);
        assertNotNull(customer);
        assertEquals("Amalie", customer.getFirstname());
        assertEquals("Landt", customer.getLastname());
    }

    /**
     * Test of findByLastname method, of class CustomerFacade.
     */
    @Test
    public void testFindByLastname() {
        List<Customer> customers = cf.findByLastname("Hansen");

        assertEquals(1, customers.size());
        assertEquals("Amanda", customers.get(0).getFirstname());
    }

    /**
     * Test of getNumberOfCustomers method, of class CustomerFacade.
     */
    @Test
    public void testGetNumberOfCustomers() {
        
        long numberOfCustomers = cf.getNumberOfCustomers();
        assertEquals(5, numberOfCustomers);    
    }

    /**
     * Test of getAllCustomers method, of class CustomerFacade.
     */
    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = cf.getAllCustomers();
        assertNotNull(customers);
        assertEquals(5, customers.size());
    }

    /**
     * Test of addCustomer method, of class CustomerFacade.
     */
    @Test
    public void testAddCustomer() {
        int customersBefore = cf.getAllCustomers().size();
        cf.addCustomer("Jonas", "Haslund");
        int customersAfter = cf.getAllCustomers().size();
        assertTrue(customersBefore < customersAfter);
    }

}
