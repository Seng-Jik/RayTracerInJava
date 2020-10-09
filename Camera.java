import java.util.Random;

public class Camera {

    private int width, height;
    private Random random;
    private Frustum frustum;

    private Vec3 trace(
        Ray ray, 
        Scene scene, 
        double maxT, 
        double minT, 
        Random rnd, 
        int depth) {

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
            Material mat = hitRec.getSceneObject().getMaterial();
            Ray nextRay = mat.nextRay(hitRec, rnd);
            if(depth < 5 && nextRay != null) 
                return mat.updateColor(
                    trace(
                        nextRay, 
                        scene, 
                        Double.MAX_VALUE, 
                        0, 
                        rnd, 
                        depth + 1));
            else return Vec3.zeros;
        }

        return scene.getSky().getColor(ray);
    }

    public Camera(int width, int height, Frustum frustum, Random random) {
        this.width = width;
        this.height = height;
        this.random = random;
        this.frustum = frustum;
    }

    public void shot(Scene scene, Vec3[] buffer) {
        Vec3 leftBottom = frustum.getLeftBottom();
        Vec3 horizontal = frustum.getHorizontal();
        Vec3 vertical = frustum.getVertical();
        Vec3 org = frustum.getOrginal();

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
