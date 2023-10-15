import jade.wrapper.ContainerController;

import javax.swing.*;
import java.util.*;

public class AppContext {
    public JFrame UI;
    public ContainerController JadeContainer;
    public List<Entity> ConsumersTransform;
    public List<Entity> DronesTranform;

    public AppContext(ContainerController jadeContainer){
        ConsumersTransform = new ArrayList<>();
        DronesTranform = new ArrayList<>();
        JadeContainer = jadeContainer;
    }
}

