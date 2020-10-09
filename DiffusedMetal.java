import java.util.Random;

public class DiffusedMetal implements Material {

    private Metal metal;
    private Diffuse diffuse;
    private double diffuseFactor;

    public DiffusedMetal(Vec3 color, Vec3 light, double diffuseFactor) {
        metal = new Metal(color, light);
        diffuse = new Diffuse(color, light);
        this.diffuseFactor = diffuseFactor;
    }

    public DiffusedMetal(Vec3 color, double diffuseFactor) {
        this(color, Vec3.zeros, diffuseFactor);
    }

    public DiffusedMetal(double diffuseFactor) {
        this(Vec3.ones, diffuseFactor);
    }

    @Override
    public Vec3 updateColor(Vec3 col) {
        return metal.updateColor(col).lerpTo(
            diffuse.updateColor(col), diffuseFactor);
    }

    @Override
    public Ray nextRay(HitRecord hitRec, Random rnd) {
        Ray metalRay = metal.nextRay(hitRec, rnd);
        if(metalRay != null) {
            Ray diffuseRay = diffuse.nextRay(hitRec, rnd);
            Vec3 dir = metalRay.getDir().lerpTo(diffuseRay.getDir(), diffuseFactor);
            return new Ray(hitRec.getPoint(), dir);
        }

        return null;
    }
    
}
