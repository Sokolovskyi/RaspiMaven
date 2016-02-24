/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sokolovskyi.restful;

import pl.sokolovskyi.raspijava.LED;
import pl.sokolovskyi.raspijava.LEDDimValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jörg
 */
@Path("led")
public class RestfulLED {
    
    
     LED ledControl = LED.getInstance(18);    
     @GET
     @Consumes(MediaType.APPLICATION_JSON)
     public LEDDimValue getDimValue() {
         return ledControl.getDimValue();
     }
     
     @POST
     @Consumes(MediaType.APPLICATION_JSON)
     public LEDDimValue setDimValue(LEDDimValue d) {
         ledControl.dim(d.getDim());
         return ledControl.getDimValue();
     }
}
