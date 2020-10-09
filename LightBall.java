public class LightBall extends Ball{

    private Diffuse diffuse;

    public LightBall(Vec3 center, double radius) {
        super(center, radius);
        diffuse = new Diffuse(Vec3.zeros, Vec3.ones);
    }

    @Override
    public Material getMaterial() {
        return diffuse;
    }
}
