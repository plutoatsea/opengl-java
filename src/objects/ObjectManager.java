package objects;
import java.util.ArrayList;
import java.util.List;

public class ObjectManager {
    private List<Entity> objects;
    public ObjectManager(){
        objects = new ArrayList<Entity>();
    }
    // Calls for each entity render()
    public void renderAll(){
        objects.forEach(entity -> entity.render());
    }
}