import java.util.Random;

public interface Material {
    public Vec3 updateColor(Vec3 col);
    public Ray nextRay(HitRecord hitRec, Random rnd);
}
