/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.Controllers;

import com.google.gson.Gson;
import jakarta.ws.rs.GET;
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
@Path("patientappointment")
public class PatientAppointmentResource {
    Gson gson = new Gson();
    
    public PatientAppointmentResource() {
        
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointments(@PathParam("id") int id){ 
        try{
            //Appointment technician = new AppointmentDBUtils().getAppointment(id);
            return Response
                .ok(gson.toJson(new AppointmentDBUtils().getPatientAppointments(id)))
                .build();
        }catch(Exception e) {
             return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .build();
        }
    }
}
