package scene;

import raytracer.*;
import material.DiffuseGlass;

public class GlassBall extends Ball {

    DiffuseGlass glass;

    public GlassBall(Vec3 center, double radius) {
        super(center, radius);
        glass = new DiffuseGlass(0.05);
    }

    @Override
    public Material getMaterial() {
        return glass;
    }
    
}
