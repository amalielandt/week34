package rest.service;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("employee")
public class EmployeeResource {

    Gson gson = new Gson();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EmployeeFacade facade = EmployeeFacade.getFacadeExample(emf);

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EmployeeResource
     */
    public EmployeeResource() {
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllEmployees() {
        //TODO return proper representation object
        List<Employee> employees = facade.getAllEmployees();
        List<EmployeeDTO> employeesDTO = new ArrayList();

        for (Employee employee : employees) {
            employeesDTO.add(new EmployeeDTO(employee));
        }
        return gson.toJson(employeesDTO);
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getId(@PathParam("id") long id) {
        //TODO return proper representation object

        Employee emp = facade.getEmployeeById(id);
        EmployeeDTO empDTO = new EmployeeDTO(emp);

        return gson.toJson(empDTO);
    }

    @GET
    @Path("/highestpaid")
    @Produces({MediaType.APPLICATION_JSON})
    public String getHighestPaid() {
        //TODO return proper representation object
        List<Employee> employees = facade.getEmployeesWithHighestSalary();
        List<EmployeeDTO> employeesDTO = new ArrayList();

        for (Employee employee : employees) {

            employeesDTO.add(new EmployeeDTO(employee));
        }

        return gson.toJson(employeesDTO);
    }

    @GET
    @Path("/name/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getName(@PathParam("name") String name) {
        //TODO return proper representation object
        List<Employee> employees = facade.getEmployeesByName(name);
        List<EmployeeDTO> employeesDTO = new ArrayList();

        for (Employee employee : employees) {

            employeesDTO.add(new EmployeeDTO(employee));
        }

        return gson.toJson(employeesDTO);
    }

}
