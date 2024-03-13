/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.Controllers;

import com.google.gson.Gson;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDate;
import static resources.Controllers.TechnicianResource.emailBody;
import static resources.Controllers.TechnicianResource.generateRandomPassword;
//import static resources.Controllers.TechnicianResource.userInfo;
import resources.DataAccess.PatientDBUtils;
import resources.DataAccess.TechnicianDBUtils;
import resources.DataAccess.UserInfoDBUtils;
import resources.Email.EmailSender;
import resources.Models.Doctor;
import resources.Models.Patient;
import resources.Models.Technician;
import resources.Models.UserInformation;

/**
 *
 * @author Madusanka(MadusankaB
 */
@Path("signup")
public class PatientResource {
    Gson gson = new Gson(); 

    public PatientResource() {
        
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registation(String json){
        try{
            String msg="";
            Patient patient = gson.fromJson(json, Patient.class); 
            String password = generateRandomPassword(6);
            var result = userInfo(patient, password);
            new UserInfoDBUtils().addUserInfo(result);
            String toEmail = patient.getEmail();
            var userInfo = new UserInfoDBUtils().getUserId( toEmail);
            String unqId = GenerateUniqId(userInfo.getId());
            new PatientDBUtils().addPatient(patient,unqId,userInfo.getId());
            EmailSender emailSender = new EmailSender();
            //new PatientDBUtils().addTechnician(patient);
            //toEmail = patient.getEmail();
            
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
    
    public static UserInformation userInfo(Patient tc, String password) {
        
        UserInformation uinf = new UserInformation();
        uinf.setId(0);
        uinf.setDisplayName(tc.getName());
        uinf.setEmail(tc.getEmail());
        uinf.setUserType(3);
        uinf.setPassword(password);
        uinf.setActive(1);
        uinf.setCreateDate(tc.getDob());
        uinf.setModifiedDate(tc.getDob());
        uinf.setCreatedBy("test 1");
        uinf.setModifiedBy("test 1");
        return uinf;
    }
    
    public String GenerateUniqId(int serId){
        int currentYear = LocalDate.now().getYear();
        String lastTwoDigits = String.valueOf(currentYear % 100);
        String formattedNumber = String.format("%05d", serId);
        return "P-"+lastTwoDigits+"/"+formattedNumber;
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatient(@PathParam("id") int id){ 
        try {
            Patient patient = new PatientDBUtils().getPatient(id);

            if (patient != null) {
                      return Response
                        .ok(gson.toJson(patient))
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
}
