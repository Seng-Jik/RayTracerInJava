package material;

import java.util.Random;
import raytracer.*;

public class Metal extends LightMaterial {

    public Metal(Vec3 color) {
        super(color);
    }

    public Metal(Vec3 color, Vec3 light) {
        super(color, light);
    }

    @Override
    public Ray nextRay(HitRecord hitRec, Random rnd) {
        Vec3 inDir = hitRec.getRay().getNormalDir();
        Vec3 normal = hitRec.getNormal().normalize();
        Vec3 refelected = inDir.reflect(normal);
        if(refelected.dot(normal) > 0)
            return new Ray(hitRec.getPoint(), refelected);
        else return null;
    }
    
}
