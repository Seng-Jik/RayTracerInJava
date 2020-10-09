package scene;

import raytracer.*;
import material.Diffuse;

public class DiffusedBall extends Ball {

    private Diffuse diffuse;

    public DiffusedBall(Vec3 center, double radius, Vec3 col) {
        super(center, radius);
        diffuse = new Diffuse(col);
    }

    @Override
    public Material getMaterial() {
        return diffuse;
    }
    
}
