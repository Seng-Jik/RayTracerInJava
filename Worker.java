import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Worker extends Thread {

    private Vec3[] imageBuffer;
    private AtomicBoolean keep = new AtomicBoolean(true);
    private int samples = 0;
    private Camera camera;
    private List<SceneObject> scene;

    public Worker(int width, int height, long seed, List<SceneObject> scene) {
        imageBuffer = new Vec3[width * height];
        for(int i = 0;i < imageBuffer.length;++i) 
            imageBuffer[i] = Vec3.zeros;

        this.camera = new Camera(width, height, seed);
        this.scene = scene;
    }

    public void run() {
        while(keep.get()) {
            synchronized(imageBuffer) {
                camera.createImage(scene, imageBuffer);
                samples ++;
            }
            try {
                Thread.sleep(1);
            }
            catch(Exception e) {}
        }
    }

    public void requestStop() {
        keep.set(false);
    }

    public int applyToBuffer(Vec3[] targetBuf) {
        int samples;
        synchronized(imageBuffer) {
            samples = this.samples;
            this.samples = 0;
            for(int i = 0; i < targetBuf.length; ++i) {
                targetBuf[i] = targetBuf[i].add(imageBuffer[i]);
                imageBuffer[i] = Vec3.zeros;
            }
        }
        return samples;
    }
}
