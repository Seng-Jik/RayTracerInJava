public class HitRecord {
    Ray ray = null;
    Vec3 normal;
    SceneObject sceneObject;
    double t;

    public HitRecord() {}
    
    public HitRecord(Ray ray, Vec3 normal, double t, SceneObject sceneObj) {
        this.ray = ray;
        this.normal = normal;
        this.sceneObject = sceneObj;
        this.t = t;
    }

    public boolean isHit() {
        return ray != null;
    }

    public SceneObject getSceneObject() {
        return sceneObject;
    }

    public double getT() {
        return t;
    }

    public Ray getRay() {
        return ray;
    }

    public Vec3 getNormal() {
        return normal;
    }

    public Vec3 getPoint() {
        return ray.getPoint(t);
    }
}
