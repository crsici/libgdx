package com.sic.org.libgdx;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.Random;

import static java.lang.Math.sqrt;

/**
 * Created by pthanhtrung on 1/26/2016.
 */
public class Main {
    final static float TIME_STEP = 1 / 60F;
    final static float HEIGHT = 10F;
    final static float WIDTH = 8F;

    public static void main(String args[]) {
        System.out.println("Hello libgdx");
        World world = new World(new Vector2(0, -9.8F), true);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(10, 10);

        Body body = world.createBody(bodyDef);
        CircleShape circle = new CircleShape();
        circle.setRadius(6f);

// Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f; // Make it bounce a little bit

// Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);
        circle.dispose();




        for (int i = 0; ; i++) {
            world.step(TIME_STEP, 8, 3);
            System.out.println(body.getPosition().x + " , " + body.getPosition().y);

            Random rand = new Random();
            int ratio = rand.nextInt(1000);

            if (body.getPosition().y <= 0) {



                fly(body, world);
            // TODO: must recalculate the probability
            } else if (ratio < 10 && body.getPosition().y < HEIGHT) {


                fly(body, world);
            }




            //System.out.println("Body.y : " + body.getPosition().y);

        }


    }

    public static void fly(Body body, World world) {
        System.out.println("Fly");
        if (isDown(body)) {
            Random rand = new Random();
            Float ratio = rand.nextFloat();
            Float desireHeight = ratio * HEIGHT - body.getPosition().y;
            float velocity = calculateVerticalVelocityForHeight(desireHeight, world);
            body.setLinearVelocity(new Vector2(0, velocity));
            System.out.println("Apply force");
        }
    }

    public static boolean isDown(Body body) {

        return body.getLinearVelocity().y < 0;
    }

    public static float calculateVerticalVelocityForHeight(float desiredHeight, World world) {
        if (desiredHeight <= 0)
            return 0; //wanna go down? just let it drop

        //gravity is given per second but we want time step values here
        float t = 1 / 60.0f;
        Vector2 stepGravity = new Vector2(world.getGravity().x * t * t, world.getGravity().y * t * t); // m/s/s

        //quadratic equation setup (ax² + bx + c = 0)
        float a = 0.5f / stepGravity.y;
        float b = 0.5f;
        float c = desiredHeight;

        //check both possible solutions
        double quadraticSolution1 = (-b - sqrt(b * b - 4 * a * c)) / (2 * a);
        double quadraticSolution2 = (-b + sqrt(b * b - 4 * a * c)) / (2 * a);

        //use the one which is positive
        double v = quadraticSolution1;
        if (v < 0)
            v = quadraticSolution2;

        //convert answer back to seconds
        return (float) v * 60.0f;
    }
}
