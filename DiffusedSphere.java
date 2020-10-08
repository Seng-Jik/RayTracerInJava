public class DiffusedSphere extends Sphere {

    Diffuse diffuse;

    public DiffusedSphere(Vec3 center, double radius, Vec3 col) {
        super(center, radius);
        diffuse = new Diffuse(col);
    }

    @Override
    public Material getMaterial() {
        return diffuse;
    }
    
}
