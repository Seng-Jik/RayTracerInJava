import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class RenderWorker extends Thread {

    private Vec3[] imageBuffer;
    private AtomicBoolean keep;
    private int samples = 0;
    private Camera camera;
    private Scene scene;

    public RenderWorker(
        int width,
        int height, 
        Frustum frustum, 
        Random random, 
        Scene scene) {

        keep = new AtomicBoolean(true);
        imageBuffer = new Vec3[width * height];
        for(int i = 0;i < imageBuffer.length;++i) 
            imageBuffer[i] = Vec3.zeros;

        this.camera = new Camera(width, height, frustum, random);
        this.scene = scene;
    }

    public void run() {
        while(keep.get()) {
            synchronized(imageBuffer) {
                camera.shot(scene, imageBuffer);
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
