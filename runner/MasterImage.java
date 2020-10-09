package runner;

import java.awt.image.BufferedImage;
import raytracer.*;

public class MasterImage {
    private Vec3[] masterBuffer;
    private int masterBufferSamples;
    private BufferedImage outImage;

    private int width, height;

    public MasterImage(int width, int height) {
        masterBuffer = new Vec3[width * height];
        for(int i = 0; i < masterBuffer.length; ++i)
            masterBuffer[i] = Vec3.zeros;
        masterBufferSamples = 0;
        outImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        this.width = width;
        this.height = height;
    }

    public void grabImageFromWorker(RenderWorker w) {
        masterBufferSamples += w.applyToBuffer(masterBuffer);
    }

    public void updateOutputImage() {
        if(masterBufferSamples == 0) return;

        for(int y = 0; y < height; ++y)
        {
            for(int x = 0; x < width; ++x)
                outImage.setRGB(x, height - y - 1, 
                    masterBuffer[x + y * width]
                        .div(masterBufferSamples)
                        .sqrt().asIntColor());
        }
    }

    public int sampleCount() {
        return masterBufferSamples;
    }

    public BufferedImage getOutput() {
        return outImage;
    }

}
