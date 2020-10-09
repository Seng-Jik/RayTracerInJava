import javax.swing.JFrame;
import javax.swing.JPanel; 

import raytracer.*;
import runner.*;
import scene.*;
import sky.*;

public class Main {
    public static void main(String[] args) {
        int width = 1440 / 2;
        int height = 810 / 2;

        // Window
        JFrame window = new JFrame("Ray Tracer");
        window.setSize(width, height);
        window.setVisible(true);

        JPanel panel = new JPanel();
        panel.setSize(width, height);
        window.add(panel);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Scene
        Vec3 ballCol = new Vec3(0.8, 0.3, 0.3);
        Vec3 earthCol = new Vec3(0.85, 1.0, 0.9);

        Scene scene = new Scene(new BlueSky());
        scene.add(new DiffusedBall(new Vec3(-1, 0, -2), 0.5, ballCol));
        scene.add(new GlassBall(new Vec3(0, 0, -2), 0.5));
        scene.add(new MetalBall(new Vec3(1, 0, -2), 0.5, 0.4));
        scene.add(new MetalBall(new Vec3(0, -100.5, -2), 100.0, 0.3, earthCol));
        
        // Camera Frustums
        double aspect = ((double)width) / (double)height;
        Frustum frustum = new Frustum(
            new Vec3(-aspect, -1, -1), 
            new Vec3(2 * aspect, 0, 0), 
            new Vec3(0, 2, 0), 
            new Vec3(0, 0, 0));

        // Renderer
        RenderRunner renderer = new RenderRunner(width, height, frustum, scene);
        renderer.run(panel.getGraphics());
    }
}