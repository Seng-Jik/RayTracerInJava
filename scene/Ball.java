package scene;

import raytracer.*;

public abstract class Ball implements SceneObject {

    private Vec3 center;
    private double radius;

    public Ball(Vec3 center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public HitRecord hit(Ray ray, double maxT, double minT) {
        Vec3 oc = ray.getOrg().sub(center);
        double a = ray.getDir().dot(ray.getDir());
        double b = 2.0 * oc.dot(ray.getDir());
        double c = oc.dot(oc) - radius * radius;

        double discriminant = b * b - 4 * a * c;
        if (discriminant <= 0)
        {
            return new HitRecord();
        }
        else
        {
            double root = (-b - Math.sqrt(discriminant)) / a * 0.5;
            if(root <= maxT && root >= minT) {
                Vec3 normal = ray.getPoint(root).sub(center).normalize();
                return new HitRecord(ray, normal, root, this);
            }

            root = (-b + Math.sqrt(discriminant)) / a * 0.5;
            if(root <= maxT && root >= minT) {
                Vec3 normal = ray.getPoint(root).sub(center).normalize();
                return new HitRecord(ray, normal, root, this);
            }

            return new HitRecord();
        }
    }
}
