/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.Controllers;

import com.google.gson.Gson;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import resources.DataAccess.AppointmentReportDBUtils;

/**
 *
 * @author Madusanka(MadusankaB
 */
@Path("appointmentReport")
public class AppointmentReportResource {
    Gson gson = new Gson(); 

    public AppointmentReportResource() {
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctors(){ 
        try{
            return Response
                .ok(gson.toJson(new AppointmentReportDBUtils().getAppointmentReports()))
                .build();
        }catch(Exception e) {
             return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .build();
        }
    }
}
