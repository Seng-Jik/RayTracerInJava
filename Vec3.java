
public class Vec3 {
    private double x, y, z;
    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3 add(Vec3 a) {
        return new Vec3(x + a.x, y + a.y, z + a.z);
    }

    public Vec3 negative() {
        return new Vec3(-x, -y, -z);
    }

    public Vec3 sqrt() {
        return new Vec3(
            Math.sqrt(x),
            Math.sqrt(y),
            Math.sqrt(z));
    }

    public double dot(Vec3 a) {
        return x * a.x + y * a.y + z * a.z; 
    }

    public Vec3 sub(Vec3 a) {
        return add(a.negative());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vec3 mul(Vec3 a) {
        return new Vec3(x * a.x, y * a.y, z * a.z);
    }

    public Vec3 mul(double a) {
        return new Vec3(x * a, y * a, z * a);
    }

    public Vec3 div(double a) {
        return mul(1.0 / a);
    }

    public Vec3 normalize() {
        return div(length());
    }

    public Vec3 lerpTo(Vec3 target, double t) {
        return new Vec3(
            (target.getX() - x) * t + x,
            (target.getY() - y) * t + y,
            (target.getZ() - z) * t + z
        );
    }

    public Vec3 reflect(Vec3 panelNormal) {
        return this.sub(panelNormal.mul(2 * this.dot(panelNormal)));
    }

    public int asIntColor() {
        int r = (int)(x * 255);
        int g = (int)(y * 255);
        int b = (int)(z * 255);

        if(r > 255) r = 255;
        if(g > 255) r = 255;
        if(b > 255) r = 255;
        if(r < 0) r = 0;
        if(g < 0) g = 0;
        if(b < 0) b = 0;
        return (r << 16) | (g << 8) | b;
    }

    public static Vec3 zeros = new Vec3(0, 0, 0);
    public static Vec3 ones = new Vec3(1, 1, 1);
}
