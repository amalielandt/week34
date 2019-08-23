package rest.service;

import com.google.gson.Gson;
import entities.BankCustomer;
import facades.CustomerFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("bankcustomer")
public class BankCustomerResource {

    Gson gson = new Gson();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    CustomerFacade facade =  CustomerFacade.getFacadeExample(emf);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }
    
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllCustomers() {
        //TODO return proper representation object
        List<BankCustomer> customers = facade.getAllBankCustomers();
        return gson.toJson(customers);
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getByID(@PathParam("id") long id) {
        //TODO return proper representation object
        return gson.toJson(facade.getCustomerByID(id));
    }
//
//    @POST
//    @Consumes({MediaType.APPLICATION_JSON})
//    public void create(BankCustomer entity) {
//        throw new UnsupportedOperationException();
//    }
    
//    @PUT
//    @Path("/{id}")
//    @Consumes({MediaType.APPLICATION_JSON})
//    public void update(BankCustomer entity, @PathParam("id") int id) {
//        throw new UnsupportedOperationException();
//    }
}
