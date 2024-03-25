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
import resources.Models.Appointment;

/**
 *
 * @author Madusanka(MadusankaB
 */
@Path("appointment")
public class AppointmentResource {
    Gson gson = new Gson(); 

    public AppointmentResource() {
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointments(){ 
        try{
            return Response
                .ok(gson.toJson(new AppointmentDBUtils().getAppointments()))
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
    public Response getAppointment(@PathParam("id") int id){ 
        try {
            Appointment technician = new AppointmentDBUtils().getAppointment(id);

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
    public Response addAppointment(String json){
        try{
            String msg="";
            Appointment appointment = gson.fromJson(json, Appointment.class); 
            
            boolean res =  new AppointmentDBUtils().addAppointment(appointment);
            if(!res){
                return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
            }
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
            Appointment appointment = gson.fromJson(json, Appointment.class);
            new AppointmentDBUtils().updateAppointment(appointment);
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
            new AppointmentDBUtils().deleteAppointment(id);
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
