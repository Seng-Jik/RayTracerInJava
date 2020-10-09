package runner;

import java.util.Random;
import java.awt.Graphics;
import raytracer.*;

public class RenderRunner {
    private RenderWorker[] workers;
    private MasterImage masterImage;
    private int selectedWorker;

    public RenderRunner(int width, int height, Frustum frustum, Scene scene) {
        masterImage = new MasterImage(width, height);
        selectedWorker = 0;

        Random rnd = new Random();
        int cpus = Runtime.getRuntime().availableProcessors();
        workers = new RenderWorker[cpus];
        for(int i = 0; i < workers.length; ++i) 
            workers[i] = 
                new RenderWorker(
                    width, 
                    height,
                    frustum, 
                    new Random(rnd.nextLong()), 
                    scene);
    }

    public void run(Graphics target) {
        for(var i : workers) i.start();

        while(true) {
            try {
                Thread.sleep(5000);
            }
            catch(Exception e) {}

            masterImage.grabImageFromWorker(workers[selectedWorker]);
            System.out.println(
                "Samples:" 
                + masterImage.sampleCount() 
                + "    GetBufferFromWorker:" 
                + selectedWorker);
            selectedWorker = (selectedWorker + 1) % workers.length;

            masterImage.updateOutputImage();
            target.drawImage(masterImage.getOutput(), 0, 0, null);
        }
    }

}
