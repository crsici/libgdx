package com.sic.org.libgdx;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by pthanhtrung on 1/27/2016.
 */
public class Application implements ApplicationListener {


    private Float TIME_STEP = 1/60F;

    private OrthographicCamera orthographicCamera;
    private SpriteBatch batch;
    int i = 500;
    int bulletLocation = 500;
    float baseLight = 1;

    @Override
    public void create() {

        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(false,8,4.8F);
        batch = new SpriteBatch();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        orthographicCamera.update();


//

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

               Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        orthographicCamera.update();


//
//        batch.enableBlending();
        batch.begin();
        //bulletLocation +=15;
        if(bulletLocation == 800)
            bulletLocation = 500;
//        for(int smoke = bulletLocation-100;smoke < bulletLocation; smoke ++){
//            Pixmap pixmap = new Pixmap( 3, 10, Pixmap.Format.RGBA8888 );
//            System.out.println(0.000001f*smoke);
//            pixmap.setColor( 0, 1, 0, 0.01f*(smoke%100) );
//            pixmap.fillRectangle( 0, 0, 3,10 );
//            Texture pixmaptex = new Texture( pixmap );
//            pixmap.dispose();
//            System.out.println("Draw : " + 2*smoke +1F);
//            TextureRegion textureRegion = new TextureRegion(pixmaptex);
//            batch.draw(textureRegion,2*smoke +1F,0,1,5,2,10,1,1,75);
//        }

        baseLight-= 0.1F;
        if(baseLight<=0) baseLight= 1;
            for (int j = 0; j <= i; j++) {
                Pixmap pixmap = new Pixmap(3, 10, Pixmap.Format.RGBA8888);

                pixmap.setColor(255, 255, 224, 0.002f * j*baseLight);
                pixmap.fillRectangle(0, 0, 3, 10);
                Texture pixmaptex = new Texture(pixmap);
                pixmap.dispose();

                TextureRegion textureRegion = new TextureRegion(pixmaptex);
                batch.draw(textureRegion, 2 * j + 1F + bulletLocation - 500, 10, 1, 5, 2, 10, 1, 1, 90);
                //batch.draw(textureRegion,2*j +1F + bulletLocation - 100,5,1,5,2,10,1,1,360-75);
                Short f = 1;
            }


//        }

        batch.end();



    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
