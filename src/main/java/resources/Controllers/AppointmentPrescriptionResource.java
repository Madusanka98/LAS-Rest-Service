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
import resources.DataAccess.AppointmentDBUtils;
import resources.DataAccess.AppointmentPrescriptionDBUtils;
import resources.Models.Appointment;
import resources.Models.AppointmentPrescription;

/**
 *
 * @author Madusanka(MadusankaB
 */
@Path("appointmentPrescription")
public class AppointmentPrescriptionResource {
    Gson gson = new Gson(); 

    public AppointmentPrescriptionResource() {
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentPrescription(){ 
        try{
            return Response
                .ok(gson.toJson(new AppointmentPrescriptionDBUtils().getAppointmentPrescriptions()))
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
    public Response getAppointmentPrescription(@PathParam("id") int id){ 
        try {
            AppointmentPrescription pres = new AppointmentPrescriptionDBUtils().getAppointmentPrescription(id);

            if (pres != null) {
                      return Response
                        .ok(gson.toJson(pres))
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
    public Response addAppointmentPrescription(String json){
        try{
            String msg="";
            AppointmentPrescription pres = gson.fromJson(json, AppointmentPrescription.class); 
            
            new AppointmentPrescriptionDBUtils().addAppointmentPrescription(pres);
            
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
    public Response updateAppointmentPrescription(String json, @PathParam("id") int id){ 
        try{
            AppointmentPrescription pres = gson.fromJson(json, AppointmentPrescription.class);
            new AppointmentPrescriptionDBUtils().updateAppointmentPrescription(pres,id);
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
            new AppointmentPrescriptionDBUtils().deleteAppointmentPrescription(id);
        return Response
            .ok()
            .build();
        }catch(Exception e) {
             return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .build();
        }
    }
}
