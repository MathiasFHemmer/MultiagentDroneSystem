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

        var controller1 = cc.createNewAgent("Test1", "ConsumerAgent", new Object[]{(Object)context});
        var controller2 = cc.createNewAgent("Test2", "ConsumerAgent", new Object[]{(Object)context});
        var controller3 = cc.createNewAgent("Test3", "ConsumerAgent", new Object[]{(Object)context});
        var controller4 = cc.createNewAgent("Test4", "ConsumerAgent", new Object[]{(Object)context});
        controller1.start();
        controller2.start();
        controller3.start();
        controller4.start();

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    field.setVisible(true);
                } catch (Exception e) {

                }
            }
        });
    }
}

