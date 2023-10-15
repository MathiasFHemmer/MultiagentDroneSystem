import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.Behaviour;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main  {
    public static void main(String args[]) throws StaleProxyException {
        Profile p = new ProfileImpl();
        p.setParameter(Profile.MAIN_HOST, "localhost");
        p.setParameter(Profile.MAIN_PORT, "3250");

        Runtime r = Runtime.instance();
        final ContainerController cc = r.createMainContainer(p);

        AppContext context = new AppContext(cc);
        JFrame field = new ScreenManager(context);

        System.out.println("Field Agent initialized");


        for(int i = 0; i < 10; i++){
            var controller = cc.createNewAgent("ConsumerAgent_"+i, "ConsumerAgent", new Object[]{(Object)context});
            controller.start();
        }

        for(int i = 0; i < 2; i++){
            var controller = cc.createNewAgent("DroneAgent_"+i, "DroneAgent", new Object[]{(Object)context});
            controller.start();
        }

        field.setVisible(true);
        new Timer("Drawer", true).scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                field.repaint();
            }
        }, 100, (int)1000/60);
    }
}

