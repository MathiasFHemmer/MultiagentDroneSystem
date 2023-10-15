import java.util.UUID;

public class Entity {
    public UUID Id;
    public Transform Transform;

    public Entity(Transform transform){
        Transform = transform;
        Id = java.util.UUID.randomUUID();
    }
}
