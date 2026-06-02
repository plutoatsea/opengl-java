package objects;
import java.util.ArrayList;
import java.util.List;

public class ObjectManager {
    private List<Entity> objects;
    public ObjectManager(){
        objects = new ArrayList<Entity>();
    }
    //Adds an Entity to the List
    public void add(Entity e){
        this.objects.add(e);
    }
    // Adds Instantly a List of Entities
    public void addAll(List<Entity> list){
        this.objects.addAll(list);
    }
    // Calls for each entity render()
    public void renderAll(){
        objects.forEach(entity -> entity.render());
    }
    // Get Specific Entity
    public Entity get(int index){
        return objects.get(index);
    }
}