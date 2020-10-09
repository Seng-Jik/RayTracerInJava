
public abstract class LightMaterial implements Material {
    private Vec3 color;
    private Vec3 light;

    public LightMaterial(Vec3 color, Vec3 light) {
        this.color = color;
        this.light = light;
    }

    public LightMaterial(Vec3 color) {
        this(color, Vec3.zeros);
    }

    @Override
    public Vec3 updateColor(Vec3 col) {
        return col.mul(color).add(light);
    }
}
