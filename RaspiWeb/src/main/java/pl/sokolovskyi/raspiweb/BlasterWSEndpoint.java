/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sokolovskyi.raspiweb;

import com.pi4j.component.servo.ServoDriver;
import com.pi4j.component.servo.ServoProvider;
import com.pi4j.component.servo.impl.RPIServoBlasterProvider;
import pl.sokolovskyi.raspijava.BlasterValueChanged;
import pl.sokolovskyi.raspijava.RPIServoBlasterExample;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


/**
 *
 * @author Jörg
 */
@ServerEndpoint("/blasterpoint")
public class BlasterWSEndpoint {

   Session session;
   
   ServoProvider servoProvider = new RPIServoBlasterProvider();
   ServoDriver servo7 = servoProvider.getServoDriver(servoProvider.getDefinedServoPins().get(7));

    public BlasterWSEndpoint() throws IOException {
        System.out.println("Object blaster will create");
        this.servoProvider = new RPIServoBlasterProvider();
        System.out.println("Object blaster is created");
    }
 
    @OnOpen
    public void onOpen(Session session) throws IOException {
        System.out.println("Dim Websocket blaster befor onOpen");
        this.session=session;
        //BlasterControl.addListener(this);
        System.out.println(session.getId() + " has opened a connection blaster");
        System.out.println("Dim Websocket blaster Value");
        session.getBasicRemote().sendText(Integer.toString(servo7.getServoPulseWidth()));
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Dim Websocket blaster befor onClose");
        //BlasterControl.removeListener(this);
        System.out.println("Session blaster " + session.getId() + " has ended");
        session=null;
    }

    @OnMessage
    public String onMessage(String message) throws IOException {
        System.out.println("Dim Websocket blaster receive:" + message);
        servo7.setServoPulseWidth(Integer.parseInt(message));
        System.out.println("Dim Websocket blaster Value");
        session.getBasicRemote().sendText(Integer.toString(servo7.getServoPulseWidth()));
        //BlasterControl.dim(Integer.parseInt(message));
        return null;
    }
/*
    private void sendDimValue() throws IOException {
            System.out.println("Dim Websocket blaster Value");
            session.getBasicRemote().sendText(Integer.toString(servo7.getServoPulseWidth()));

        /*
        try {
            System.out.println("Dim Websocket blaster Value");
            session.getBasicRemote().sendText(BlasterControl.getDimValue().toString());
        } catch (IOException ex) {
            Logger.getLogger(BlasterWSEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
    }

    @Override
    public void valueChanged(int v) {
        System.out.println("Dim Websocket blaster Change");
        this.sendDimValue();
    }
*/  
}
