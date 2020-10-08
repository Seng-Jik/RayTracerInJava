public interface SceneObject {
    HitRecord hit(Ray ray, double maxT, double minT);
    Material getMaterial();
}
