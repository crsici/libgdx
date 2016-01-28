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

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static java.lang.StrictMath.atan2;

/**
 * Created by pthanhtrung on 1/27/2016.
 */
public class Application implements ApplicationListener {


    private Float TIME_STEP = 1 / 60F;

    private OrthographicCamera orthographicCamera;
    private SpriteBatch batch;
    int i = 500;
    int bulletLocation = 500;
    float baseLight = 1;

    Stack<Vector2> positions = new Stack<>();


    @Override
    public void create() {

        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(false, 8, 4.8F);
        batch = new SpriteBatch();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        orthographicCamera.update();
        for (int i = 0;i< 100;i++) {
            positions.push(new Vector2(i*i, i*i));
        }


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
//        if(bulletLocation == 800)
//            bulletLocation = 500;
////        for(int smoke = bulletLocation-100;smoke < bulletLocation; smoke ++){
////            Pixmap pixmap = new Pixmap( 3, 10, Pixmap.Format.RGBA8888 );
////            System.out.println(0.000001f*smoke);
////            pixmap.setColor( 0, 1, 0, 0.01f*(smoke%100) );
////            pixmap.fillRectangle( 0, 0, 3,10 );
////            Texture pixmaptex = new Texture( pixmap );
////            pixmap.dispose();
////            System.out.println("Draw : " + 2*smoke +1F);
////            TextureRegion textureRegion = new TextureRegion(pixmaptex);
////            batch.draw(textureRegion,2*smoke +1F,0,1,5,2,10,1,1,75);
////        }
//
//        baseLight-= 0.1F;
//        if(baseLight<=0) baseLight= 1;
//            for (int j = 0; j <= i; j++) {
//                Pixmap pixmap = new Pixmap(3, 10, Pixmap.Format.RGBA8888);
//
//                pixmap.setColor(255, 255, 224, 0.002f * j*baseLight);
//                pixmap.fillRectangle(0, 0, 3, 10);
//                Texture pixmaptex = new Texture(pixmap);
//                pixmap.dispose();
//
//                TextureRegion textureRegion = new TextureRegion(pixmaptex);
//                batch.draw(textureRegion, 2 * j + 1F + bulletLocation - 500, 10, 1, 5, 2, 10, 1, 1, 90);
//                //batch.draw(textureRegion,2*j +1F + bulletLocation - 100,5,1,5,2,10,1,1,360-75);
//                Short f = 1;
//            }
//
//
////        }

        Vector2 first = positions.get(0);
        for (int i = 1; i < positions.size(); i++) {
            Vector2 second = positions.get(i);
            Vector2 sub = new Vector2(second.x - first.x, second.y - first.y);
            Vector2 base = new Vector2(0,-1);
            Double angle =  Math.toDegrees(atan2(sub.y,sub.x) - atan2(base.y,base.x));

            Pixmap pixmap = new Pixmap(3, 10, Pixmap.Format.RGBA8888);

            pixmap.setColor(255, 255, 224,1);
            pixmap.fillRectangle(0, 0, 3, 10);
            Texture pixmaptex = new Texture(pixmap);
            pixmap.dispose();

            TextureRegion textureRegion = new TextureRegion(pixmaptex);
            System.out.println(sub.x + ", " + sub.y);
            batch.draw(textureRegion, first.x,first.y, sub.len()/2, 5, sub.len(), 5, 1, 1, 360 -angle.floatValue());
            first = second;
        }

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
