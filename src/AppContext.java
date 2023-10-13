import jade.wrapper.ContainerController;

import javax.swing.*;
import java.util.*;

public class AppContext {
    public JFrame UI;
    public ContainerController JadeContainer;
    public HashMap<UUID, Transform> ConsumersTransform;

    public AppContext(ContainerController jadeContainer){
        ConsumersTransform = new HashMap<>();
        JadeContainer = jadeContainer;
    }
}
