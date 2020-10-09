package material;

import java.util.Random;
import raytracer.*;

public class Diffuse extends LightMaterial {

    public Diffuse(Vec3 col, Vec3 light) {
        super(col, light);
    }

    public Diffuse(Vec3 col) {
        super(col);
    }

    @Override
    public Ray nextRay(HitRecord hitRec, Random rnd) {
        Vec3 rndDir = new Vec3(
            rnd.nextDouble() * 2,
            rnd.nextDouble() * 2,
            rnd.nextDouble() * 2).sub(Vec3.ones).normalize().mul(rnd.nextDouble());
        Vec3 targetDir = hitRec.getPoint().add(hitRec.getNormal()).add(rndDir);
        return new Ray(hitRec.getPoint(), targetDir.sub(hitRec.getPoint()));
    }
    
}
