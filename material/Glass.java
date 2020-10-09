package material;

import java.util.Random;
import raytracer.*;

public class Glass extends LightMaterial {

    double reflectionFactor;

    public Glass(Vec3 color, Vec3 light, double reflectionFactor) {
        super(color, light);
        this.reflectionFactor = reflectionFactor;
    }

    public Glass(Vec3 color, double reflectionFactor) {
        this(color, Vec3.zeros, reflectionFactor);
    }

    public Glass(double reflectionFactor) {
        this(Vec3.ones, reflectionFactor);
    }

    public Glass() {
        this(0.9);
    }

    private Vec3 refract(Vec3 in,Vec3 normal, double nino) {
        Vec3 inn = in.normalize();
        double dt = inn.dot(normal);
        double d = 1 - nino * nino * (1 - dt * dt);
        if(d >= 0) 
            return inn.sub(normal.mul(dt)).sub(normal.mul(Math.sqrt(d))).mul(nino);
        else return null;
    }

    @Override
    public Ray nextRay(HitRecord hitRec, Random rnd) {
        Vec3 reflection = hitRec.getRay().getDir().reflect(hitRec.getNormal());
        double nino = 1;

        Vec3 outNormal;

        if(hitRec.getRay().getDir().dot(hitRec.getNormal()) > 0) {
            outNormal = hitRec.getNormal().negative();
            nino = reflectionFactor;
        }
        else {
            outNormal = hitRec.getNormal();
            nino = 1.0 / reflectionFactor;
        }

        Vec3 refracted = refract(hitRec.getRay().getDir(), outNormal, nino);
        if(refracted != null) 
            return new Ray(hitRec.getPoint(), refracted);
        else return new Ray(hitRec.getPoint(), reflection);
    }
    
}
