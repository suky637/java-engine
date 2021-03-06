package ca.easyengine.engine;

import java.awt.event.KeyEvent;

public class GameContainer implements Runnable
{
    public static final double Version = 1.0;

    private Thread thread;
    private Window window;
    private Renderer renderer;
    private Input input;
    private AbstractGame game;

    private boolean running = false;
    private final double UPDATE_CAP = 1.0/60.0;
    private int width = 320, height = 240;
    private float scale = 3f;
    private String title = "EasyEngine v" + Version;
    private int fps = 0;

    public GameContainer(AbstractGame game)
    {
        this.game = game;
    }

    public void start()
    {
        window = new Window(this);
        renderer = new Renderer(this);
        input = new Input(this);

        thread = new Thread(this);
        thread.run();
    }

    public void stop()
    {

    }

    public void run()
    {
        running = true;

        boolean render = false;
        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;

        while (running)
        {
            render = false;

            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while (unprocessedTime >= UPDATE_CAP)
            {
                unprocessedTime -= UPDATE_CAP;
                render = true;
                //TODO: Update Game

                game.update(this, (float)UPDATE_CAP);

                input.update();

                if (frameTime >= 1.0)
                {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                    System.out.println("fps: " + fps);
                }
            }

            if (render)
            {
                renderer.Clear(0);
                game.render(this, renderer);
                renderer.process();
                window.update();
                frames++;

            }else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        dispose();
    }

    public void dispose()
    {

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getScale() {
        return scale;
    }

    public String getTitle() {
        return title;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Window getWindow() {
        return window;
    }

    public Input getInput() {
        return input;
    }

    public int getFps() {
        return fps;
    }
}
