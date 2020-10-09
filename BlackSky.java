public class BlackSky implements Sky {

    @Override
    public Vec3 getColor(Ray ray) {
        return Vec3.zeros;
    }
    
}
