package raytracer;
import java.util.Random;

public interface Material {
    public Vec3 updateColor(Vec3 col);

    // May returns null when has not next ray.
    public Ray nextRay(HitRecord hitRec, Random rnd);
}
