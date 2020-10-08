import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel; 

public class Program {
    private static void createFromColorBuffer(Vec3[] buf, BufferedImage output)
    {
        int width = output.getWidth();
        int height = output.getHeight();
        for(int y = 0; y < height; ++y)
        {
            for(int x = 0; x < width; ++x)
                output.setRGB(x, height - y - 1, 
                    buf[x + y * width].sqrt().asIntColor());
        }
    }
    public static void main(String[] args) {
        int width = 1440 / 2;
        int height = 810 / 2;

        JFrame window = new JFrame("Ray Tracer");
        window.setSize(width, height);
        window.setVisible(true);

        Vec3 ballCol = new Vec3(0.8, 0.8, 0.8);
        Vec3 earthCol = new Vec3(0.5, 0.5, 0.5);

        LinkedList<SceneObject>scene = new LinkedList<SceneObject>();
        scene.add(new DiffusedSphere(new Vec3(0, 0, -1), 0.5, ballCol));
        scene.add(new DiffusedSphere(new Vec3(0, -100.5, -1), 100.0, earthCol));

        Random rnd = new Random();
        Worker[] workers = new Worker[Runtime.getRuntime().availableProcessors()];
        for(int i = 0; i < workers.length; ++i) {
            workers[i] = new Worker(width, height, rnd.nextLong(), scene);
            workers[i].start();
        }

        JPanel panel = new JPanel();
        panel.setSize(width, height);
        window.add(panel);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int samples = 0;
        int selectedWorker = 0;
        Vec3[] masterBuffer = new Vec3[width * height];
        for(int i = 0; i < masterBuffer.length; ++i)
            masterBuffer[i] = Vec3.zeros;
        Vec3[] displayBuffer = new Vec3[width * height];

        BufferedImage image = 
            new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        while(true) {
            try {
                Thread.sleep(5000);
            }
            catch(Exception e) {}

            samples += workers[selectedWorker].applyToBuffer(masterBuffer);
            System.out.println("Samples:" + samples + "    GetBufferFromWorker:" + selectedWorker);
            selectedWorker = (selectedWorker + 1) % workers.length;
            if(samples != 0) {
                for(int i = 0; i < masterBuffer.length; ++i)
                    displayBuffer[i] = masterBuffer[i].div(samples);

                createFromColorBuffer(displayBuffer, image);
                
                panel.getGraphics().drawImage(image, 0, 0, null);
            }
        }
    }
}