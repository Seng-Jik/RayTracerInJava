import java.util.Iterator;
import java.util.LinkedList;

public class Scene implements Iterable<SceneObject> {

    private LinkedList<SceneObject> sceneObjects;
    private Sky sky;

    public Scene(Sky sky) {
        sceneObjects = new LinkedList<SceneObject>();
        this.sky = sky;
    }

    public void add(SceneObject sceneObject) {
        sceneObjects.add(sceneObject);
    }

    public Sky getSky() {
        return sky;
    }

    @Override
    public Iterator<SceneObject> iterator() {
        return sceneObjects.iterator();
    }
}
