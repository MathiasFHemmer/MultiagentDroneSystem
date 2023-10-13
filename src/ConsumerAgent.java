import jade.core.Agent;
import jade.core.behaviours.Behaviour;

import java.awt.*;
import java.io.Console;
import java.util.Random;
import java.util.UUID;

public class ConsumerAgent extends Agent {
    public UUID uuid;
    public AppContext Context;
    protected void setup(){
        Object[] args = getArguments();
        Context = (AppContext)args[0];

        uuid = java.util.UUID.randomUUID();
        Random rand = new Random();

        var myTransform = new Transform();
        myTransform.PosX = rand.nextInt(200, 600);
        myTransform.PosY = rand.nextInt(200, 600);
        myTransform.Radius = 10;

        Context.ConsumersTransform.put(uuid, myTransform);

        addBehaviour(new Behav(this));

        System.out.println("The pig says: wee wee");

    }
}

class Behav extends Behaviour{
    long tStart = System.currentTimeMillis();
    ConsumerAgent Agent;
    Dimension target;

    public Behav(ConsumerAgent agent){
        super(agent);
        Agent = agent;

        Random rand = new Random();
        target = new Dimension(rand.nextInt(200, 600), rand.nextInt(200, 600));
    }
    @Override
    public void action() {
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        tStart = System.currentTimeMillis();

        var transform = Agent.Context.ConsumersTransform.get(Agent.uuid);

        var directionX = target.width - transform.PosX ;
        var directionY = target.height - transform.PosY;
        var magn = Math.sqrt(directionX * directionX + directionY * directionY);
        var normDirX = directionX / magn;
        var normDirY = directionY / magn;

        transform.PosX += normDirX * elapsedSeconds * 10.0;
        transform.PosY += normDirY * elapsedSeconds* 10.0;

        //Agent.Context.ConsumersTransform.put(Agent.uuid, transform);
        Agent.Context.UI.repaint();
    }
    @Override
    public boolean done() {
        var transform = Agent.Context.ConsumersTransform.get(Agent.uuid);

        var directionX = target.width - transform.PosX ;
        var directionY = target.height - transform.PosY;
        var magn = Math.sqrt(directionX * directionX + directionY * directionY);

        return(magn <= 0.5f);
    }
}
