/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.Controllers;

import com.google.gson.Gson;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import resources.DataAccess.AdminDBUtils;
import resources.DataAccess.LoginDBUtils;
import resources.DataAccess.PatientDBUtils;
import resources.DataAccess.TechnicianDBUtils;
import resources.Models.Admin;
import resources.Models.Login;
import resources.Models.Patient;
import resources.Models.Technician;
import resources.Models.UserInformation;

/**
 *
 * @author Madusanka(MadusankaB
 */
@Path("login")
public class UserInfoResource {
    Gson gson = new Gson(); 

    public UserInfoResource() {
        
    }
    
    @POST
    //@Path("{json}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkLogin(String json){ 
        try {
            var data="";
            Login login = gson.fromJson(json, Login.class); 
            //UserInformation user = gson.fromJson(json, UserInformation.class); 
            var user = new LoginDBUtils().checkLogin(login);
                if (user != null) {
                      return Response
                        .ok(gson.toJson(user))
                        .build();
                } else{
                    return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .build();
                }
            //String type = user.getUserType().replaceAll("\"", "");
            
//            if(user.getId()!=0 && user.getUserType()==3){
//                Patient patient =new PatientDBUtils().getPatientbyUserId(user.getId());
//                patient.setImage("");
//                if (patient != null) {
//                      return Response
//                        .ok(gson.toJson(patient))
//                        .build();
//                } 
//            }else if(user.getId()!=0 && user.getUserType()==2){
//                Technician technician = new TechnicianDBUtils().getTechnicianbyUserId(user.getId());
//                technician.setImage("");
//                if (technician != null) {
//                      return Response
//                        .ok(gson.toJson(technician))
//                        .build();
//                } 
//            }else if(user.getId()!=0 && user.getUserType()==1){
//                //patient.setImage("");
//                return Response
//                .status(Response.Status.INTERNAL_SERVER_ERROR)
//                .build();
//            }else{
//                return Response
//                    .status(Response.Status.NOT_FOUND)
//                    .build();
//            }
//            return Response
//                    .status(Response.Status.NOT_FOUND)
//                    .build();
            
        } catch(Exception e) {
             return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .build();
        }
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getImage(@PathParam("id") int id){ 
        try {
            UserInformation userInfo = new LoginDBUtils().getUserType(id);

            if (userInfo != null) {
            if(userInfo.getId()!=0 && userInfo.getUserType()==3){
                Patient patient =new PatientDBUtils().getPatientbyUserId(userInfo.getId());
                //patient.setImage("");
                if (patient != null) {
                      return Response
                        .ok(gson.toJson(patient))
                        .build();
                } 
            }else if(userInfo.getId()!=0 && userInfo.getUserType()==2){
                Technician technician = new TechnicianDBUtils().getTechnicianbyUserId(userInfo.getId());
                //technician.setImage("");
                if (technician != null) {
                      return Response
                        .ok(gson.toJson(technician))
                        .build();
                } 
            }else if(userInfo.getId()!=0 && userInfo.getUserType()==1){
                Admin admin = new AdminDBUtils().getAdminbyUserId(userInfo.getId());
                if (admin != null) {
                      return Response
                        .ok(gson.toJson(admin))
                        .build();
                } 
            }else{
                return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
            }
            } else{
                
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
            }
        } catch(Exception e) {
             return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .build();
        }
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .build();
    }
}
