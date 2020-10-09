package raytracer;

public class Frustum {
    private Vec3 leftBottom, horizontal, vertical, org;

    public Frustum(Vec3 leftBottom, Vec3 horizontal, Vec3 vertical, Vec3 org) {
        this.leftBottom = leftBottom;
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.org = org;
    }

    public Vec3 getLeftBottom() {
        return leftBottom;
    }

    public Vec3 getHorizontal() {
        return horizontal;
    }

    public Vec3 getVertical() {
        return vertical;
    }

    public Vec3 getOrginal() {
        return org;
    }
}
