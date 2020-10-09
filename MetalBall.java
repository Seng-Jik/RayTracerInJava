public class MetalBall extends Ball {

    private DiffusedMetal metal;

    public MetalBall(Vec3 center, double radius, double diffuseFactor, Vec3 col) {
        super(center, radius);
        metal = new DiffusedMetal(col, diffuseFactor);
    }

    public MetalBall(Vec3 center, double radius, double diffuseFactor) {
        this(center, radius, diffuseFactor, Vec3.ones);
    }

    @Override
    public Material getMaterial() {
        return metal;
    }
    
}
