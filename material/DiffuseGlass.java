package material;

import java.util.Random;
import raytracer.*;

public class DiffuseGlass extends LightMaterial {
    private Glass glass;
    private Diffuse diffuse;

    double diffuseFactor;

    public DiffuseGlass(double diffuseFactor) {
        super(Vec3.ones, Vec3.zeros);
        this.diffuseFactor = diffuseFactor;

        glass = new Glass();
        diffuse = new Diffuse(Vec3.ones);
    }

    @Override
    public Ray nextRay(HitRecord hitRec, Random rnd) {
        Ray glassRay = glass.nextRay(hitRec, rnd);
        Ray diffuseRay = diffuse.nextRay(hitRec, rnd);
        return new Ray(hitRec.getPoint(), 
            glassRay.getDir().lerpTo(diffuseRay.getDir(), diffuseFactor));
    }    
}
