public class BlueSky implements Sky {

    @Override
    public Vec3 getColor(Ray ray) {
        double t = 0.5 * ray.getDir().normalize().getY() + 1.0;
        return (Vec3.ones.mul(1 - t)).add(new Vec3(0.5, 0.7, 1).mul(t));
    }
}
