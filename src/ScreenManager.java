import jade.wrapper.ContainerController;

import javax.swing.*;
import java.awt.*;

public class ScreenManager extends JFrame {
    private  AppContext Context;
    public ScreenManager(AppContext context){
        Context = context;
        context.UI = this;
        setTitle("Platform Manager");

        add(new ScreenDrawer(context));
        pack();
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setLayout(null);
    }
}

class ScreenDrawer extends JPanel{
    private  AppContext Context;
    public ScreenDrawer (AppContext context){
        Context = context;

        Dimension size = new Dimension(Constants.WIDTH, Constants.HEIGHT);
        setPreferredSize(size);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Context.ConsumersTransform.forEach(entity -> {
            g.setColor(Color.GREEN);
            g.fillOval((int)entity.Transform.Position.x, (int)entity.Transform.Position.y, entity.Transform.Radius, entity.Transform.Radius);
        });

        Context.DronesTranform.forEach(entity -> {
            g.setColor(Color.BLUE);
            g.fillOval((int)entity.Transform.Position.x, (int)entity.Transform.Position.y, entity.Transform.Radius, entity.Transform.Radius);
        });
    }
}
