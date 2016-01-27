package com.sic.org.libgdx;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * Created by pthanhtrung on 1/27/2016.
 */
public class DesktopLaucher {
    final static int WIDTH = 1000;
    final static int HEIGHT = 480;
    public static void main(String[] args){
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = WIDTH;
        config.height = HEIGHT;
        new LwjglApplication(new Application(), config);
    }
}
