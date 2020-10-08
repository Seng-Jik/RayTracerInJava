import java.util.Random;

public class Diffuse implements Material {

    private Vec3 color;

    public Diffuse(Vec3 col) {
        this.color = col;
    }

    @Override
    public Vec3 updateColor(Vec3 col) {
        return col.mul(color);
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
