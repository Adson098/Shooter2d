package com.mygdx.game.hero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Shooter2d;

/**
 * Created by Adson on 2017-11-18.
 */

public class Player {

    private float x;
    private float y;
    private int width;
    private int height;
    private float speedX;
    private float speedY;
    private float maxSpeedX;
    private float maxSpeedY;
    private Rectangle player_box;
    /////BOX2d//////
    private BodyDef bodydef;
    private Body body;
    private CircleShape circle;
    private FixtureDef fixturedef;
    private Fixture fixture ;
    private float MAX_VELOCITY;


    public Player(World world) {
        x = 0;
        y = 0;
        width = 50;
        height = 50;
        speedX = 0;
        speedY = 0;
        maxSpeedX = 300;
        maxSpeedY = 200;
        player_box = new Rectangle(x, y, width, height);
        bodydef = new BodyDef();
        bodydef.type = BodyDef.BodyType.DynamicBody;
        bodydef.position.set(100,300);
        body = world.createBody(bodydef);
        circle = new CircleShape();
        circle.setRadius(6f);
        fixturedef = new FixtureDef();
        fixturedef.shape = circle;
        fixturedef.density = 0.5f;
        fixturedef.friction = 0.4f;
        fixturedef.restitution = 0.6f;
        fixture = body.createFixture(fixturedef);
        MAX_VELOCITY = 0.6f;
    }

    public void input(float dt) {
        Vector2 vel = this.body.getLinearVelocity();
        Vector2 pos = this.body.getPosition();

        if (Gdx.input.isKeyPressed(Input.Keys.A) && vel.x > -MAX_VELOCITY) {
            this.body.applyLinearImpulse(-0.80f, 0, pos.x, pos.y, true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D) && vel.x < MAX_VELOCITY) {
            this.body.applyLinearImpulse(0.80f, 0, pos.x, pos.y, true);
        }
        updateBox();

    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(player_box.x, player_box.y, player_box.width, player_box.height);
    }

    private void updateBox() {
        player_box.setPosition(body.getPosition().x, body.getPosition().y);
    }
    public void checkoutthescreen(){
        if(x >= Shooter2d.V_WIDTH - width){
            x = Shooter2d.V_WIDTH;
        }
        else if(x <= 0){
            x = 0;
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public CircleShape getCircle(){return circle;}


}
