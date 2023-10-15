import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;

import java.util.Random;
import java.util.UUID;

public class DroneAgent extends Agent {
    public UUID uuid;
    public AppContext Context;
    int DefaultRadius = 10;
    Vector2 target;
    protected void setup(){
        Context = (AppContext)getArguments()[0];

        Random rand = new Random();
        var myTransform = new Transform(rand.nextInt(200, 600),rand.nextInt(200, 600), DefaultRadius);
        var myEntity = new Entity(myTransform);
        uuid = myEntity.Id;

        Context.DronesTranform.add(myEntity);

        var brain = new FSMBehaviour();
        brain.registerState(new MoveToTarget(this), "Move");
        brain.registerFirstState(new GetTarget(this), "GetTarget");
        brain.registerTransition("Move","GetTarget", 1);
        brain.registerTransition("GetTarget","Move", 1);

        addBehaviour(brain);
    }
}

class MoveToTarget extends Behaviour {
    long tStart = System.currentTimeMillis();
    DroneAgent Agent;
    public MoveToTarget(DroneAgent agent){
        super(agent);
        Agent = agent;
    }
    @Override
    public void action() {
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        tStart = System.currentTimeMillis();

        if(Agent.target == null){
            return;
        }

        var transform = Agent.Context.DronesTranform.stream().filter(x -> x.Id == Agent.uuid).findFirst().get().Transform;

        var direction = Vector2.Distance(transform.Position, Agent.target).Normalized();

        transform.Position.x += (float) (direction.x * elapsedSeconds * 50.0);
        transform.Position.y += (float) (direction.y * elapsedSeconds * 50.0);
    }

    @Override
    public boolean done() {
        var transform = Agent.Context.DronesTranform.stream().filter(x -> x.Id == Agent.uuid).findFirst().get().Transform;
        return Vector2.Distance(Agent.target, transform.Position).Magnitude() <= 5;
    }

    @Override
    public int onEnd() {
        Agent.target = null;
        return 1;
    }
}

class GetTarget extends OneShotBehaviour{
    DroneAgent Agent;
    public GetTarget(DroneAgent agent){
        super(agent);
        Agent = agent;
    }
    @Override
    public void action() {
        var rand = new Random();

        Random generator = new Random();
        var maxIndex = Agent.Context.ConsumersTransform.size() - 1;
        var index = generator.nextInt(0, maxIndex);
        Transform randomValue = Agent.Context.ConsumersTransform.get(index).Transform;
        System.out.println("maxIndex: " + maxIndex + " - index: " +index);

        System.out.println("X: " + randomValue.Position.x + " - Y: " + randomValue.Position.y);

        Agent.target = randomValue.Position;
    }
    @Override
    public int onEnd() {
        return 1;
    }
}