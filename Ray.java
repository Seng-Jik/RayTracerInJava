public class Ray {
    private Vec3 org, dir, normalDir;
    
    public Ray(Vec3 org, Vec3 dir) {
        this.org = org;
        this.dir = dir;
        this.normalDir = dir.normalize();
    }

    public Vec3 getOrg() {
        return org;
    }

    public Vec3 getDir() {
        return dir;
    }

    public Vec3 getNormalDir() {
        return normalDir;
    }

    public Vec3 getPoint(double t) {
        return org.add(dir.mul(t));
    }
}
