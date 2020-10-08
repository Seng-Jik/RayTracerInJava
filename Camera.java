import java.util.List;
import java.util.Random;

public class Camera {

    private int width, height;
    private Random random;

    private Vec3 trace(Ray ray, List<SceneObject> scene, double maxT, double minT, Random rnd, int depth) {
        HitRecord hitRec = null;
        for(var i : scene) {
            var hitRecCur = i.hit(ray, maxT, minT);
            if(hitRecCur.isHit()) {
                if(hitRec == null) hitRec = hitRecCur;
                if(hitRecCur.getT() < hitRec.getT())
                    hitRec = hitRecCur;
            }
        }

        if(hitRec != null) {
            Material mat = hitRec.sceneObject.getMaterial();
            Ray nextRay = mat.nextRay(hitRec, rnd);
            if(depth < 5) {
                return mat.updateColor(trace(nextRay, scene, Double.MAX_VALUE, 0, rnd, depth + 1));
            }
        }

        double t = 0.5 * ray.getDir().normalize().getY() + 1.0;
        return (Vec3.ones.mul(1 - t)).add(new Vec3(0.5, 0.7, 1).mul(t));
    }

    public Camera(int width, int height, long seed) {
        this.width = width;
        this.height = height;
        this.random = new Random(seed);
    }

    public void createImage(List<SceneObject> scene, Vec3[] buffer) {
        Vec3 leftBottom = new Vec3(-2, -1, -1);
        Vec3 horizontal = new Vec3(4, 0, 0);
        Vec3 vertical = new Vec3(0, 2, 0);
        Vec3 org = new Vec3(0, 0, 0);

        for(int y = 0; y < height; ++y) {
            for(int x = 0; x < width; ++x) {
                Vec3 xDir = horizontal.mul(x / (double)width);
                Vec3 yDir = vertical.mul(y / (double)height);
                Vec3 dir = leftBottom.add(xDir.add(yDir));
                
                dir = dir.add(
                    new Vec3(
                        random.nextDouble() * (1.0 / width),
                        random.nextDouble() * (1.0 / height),
                        0));

                Ray r = new Ray(org, dir);
                Vec3 col = trace(r, scene, Double.MAX_VALUE, 0, random, 0);
                buffer[x + y * width] = 
                    buffer[x + y * width].add(col);
            }
        }
    }
}
