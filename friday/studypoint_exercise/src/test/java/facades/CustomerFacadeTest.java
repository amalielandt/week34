/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
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
        cf = CustomerFacade.getFacadeExample(emf);
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
        //removes BankCustomer from addCustomer test to reset database
        BankCustomer c = cf.getAllBankCustomers().get(cf.getAllBankCustomers().size()-1);
        cf.removeEmployee(c);
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getFacadeExample method, of class CustomerFacade.
     */
    @Test
    public void testGetFacadeExample() {
        CustomerFacade result = CustomerFacade.getFacadeExample(emf);
        assertNotNull(result);
    }

    /**
     * Test of getCustomerByID method, of class CustomerFacade.
     */
    @Test
    public void testGetCustomerByID() {
        
        CustomerDTO customer = cf.getCustomerByID(1);

        assertNotNull(customer);
        assertEquals("Amanda Hansen", customer.getFullName());
        assertEquals("11134534554", customer.getAccountNumber());
        assertEquals(100.5, customer.getBalance(), 0.01);
    }

    /**
     * Test of getCustomerByName method, of class CustomerFacade.
     */
    @Test
    public void testGetCustomerByName() {
        
         List<CustomerDTO> customers = cf.getCustomerByName("Haslund");

        assertNotNull(customers);
        assertEquals("Jonas Haslund", customers.get(0).getFullName());
        
        customers = cf.getCustomerByName("Amalie");

        assertNotNull(customers);
        assertEquals("Amalie Landt", customers.get(0).getFullName());
    }

    /**
     * Test of addCustomer method, of class CustomerFacade.
     */
    @Test
    public void testAddCustomer() {
        
        int customersBefore = cf.getAllBankCustomers().size();
        cf.addCustomer(new BankCustomer("Lisa", "Landt", "34534534583", 6000.5, 1, "Unknown"));
        int customersAfter = cf.getAllBankCustomers().size();
        assertTrue(customersBefore < customersAfter);
       
    }

    /**
     * Test of getAllBankCustomers method, of class CustomerFacade.
     */
    @Test
    public void testGetAllBankCustomers() {
        List<BankCustomer> customers = cf.getAllBankCustomers();
        assertNotNull(customers);
        assertEquals(5, customers.size());
    }
    
}
