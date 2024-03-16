/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.Controllers;

import com.google.gson.Gson;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import resources.DataAccess.TechnicianDBUtils;
import resources.Models.Technician;
import resources.Email.EmailSender;
import java.util.Random;
import static resources.Controllers.PatientResource.hashPassword;
import resources.Models.UserInformation;
import resources.DataAccess.UserInfoDBUtils;
/**
 *
 * @author Madusanka(MadusankaB
 */
@Path("technicians")
public class TechnicianResource {
    Gson gson = new Gson(); 

    public TechnicianResource() {
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTechnicians(){ 
        try{
            return Response
                .ok(gson.toJson(new TechnicianDBUtils().getTechnicians()))
                .build();
        }catch(Exception e) {
             return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .build();
        }
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTechnician(@PathParam("id") int id){ 
        try {
            Technician technician = new TechnicianDBUtils().getTechnician(id);

            if (technician != null) {
                      return Response
                        .ok(gson.toJson(technician))
                        .build();
            } 
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        } catch(Exception e) {
             return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTechnician(String json){
        try{
            String msg="";
            Technician technician = gson.fromJson(json, Technician.class); 
            String password = generateRandomPassword(6);
            String pass = hashPassword(password);
            var result = userInfo(technician, pass);
            new UserInfoDBUtils().addUserInfo(result);
            String toEmail = technician.getEmail();
            var userInfo = new UserInfoDBUtils().getUserId( toEmail);
            
            new TechnicianDBUtils().addTechnician(technician,userInfo.getId());
            EmailSender emailSender = new EmailSender();
            //new TechnicianDBUtils().addTechnician(technician);
            
            String body = emailBody(msg,toEmail, password);
            //send email
            emailSender.sendEmail(toEmail, "Login Information", body);
            
            
            return Response
                    .status(Response.Status.CREATED)
                    .build();
        }
        catch(Exception e) {
             return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .build();
        }
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTechnician(String json, @PathParam("id") int id){ 
        try{
            Technician technician = gson.fromJson(json, Technician.class);
            new TechnicianDBUtils().updateTechnician(technician);
            return Response
                        .ok()
                        .build();
        }catch(Exception e) {
             return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .build();
        }
        
    }
    
    
    @DELETE
    @Path("{id}")
    public Response deleteTechnician(@PathParam("id") int id){ 
        try{
            new TechnicianDBUtils().deleteTechnician(id);
        return Response
            .ok()
            .build();
        }catch(Exception e) {
             return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .build();
        }
        
    }
    
    public static String generateRandomPassword(int length) {
        
        if (length <= 0) {
            throw new IllegalArgumentException("Password length must be greater than 0");
        }
        // Define the characters allowed in the password
        String characters = "0123456789";
        // Initialize the random number generator
        Random random = new Random();
        // Create a StringBuilder to hold the generated password
        StringBuilder password = new StringBuilder(length);
        // Generate the password by appending random characters
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }
        return password.toString();
    }
    
    public static String emailBody(String msg,String userEmail, String passowrd) {
        return msg = "Hello,\n\n"
                    + "Your login credentials are as follows:\n"
                    + "Username: " + userEmail + "\n"
                    + "Password: " + passowrd + "\n\n"
                    + "Please keep this information secure.";
        
    }
    
    public static UserInformation userInfo(Technician tc, String password) {
        
        UserInformation uinf = new UserInformation();
        uinf.setId(0);
        uinf.setDisplayName(tc.getName());
        uinf.setEmail(tc.getEmail());
        uinf.setUserType(2);
        uinf.setPassword(password);
        uinf.setActive(1);
        uinf.setCreateDate(tc.getStartDate());
        uinf.setModifiedDate(tc.getStartDate());
        uinf.setCreatedBy("test 1");
        uinf.setModifiedBy("test 1");
        return uinf;
    }
      
}
