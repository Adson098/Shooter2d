package com.mygdx.game.hero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
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


    public Player() {
        x = 0;
        y = 0;
        width = 50;
        height = 50;
        speedX = 0;
        speedY = 0;
        maxSpeedX = 300;
        maxSpeedY = 200;
        player_box = new Rectangle(x, y, width, height);
    }

    public void input(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            speedX = maxSpeedX;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            speedX = -maxSpeedX;
        }
         else {
            speedX = 0;
            speedY = 0;
        }

        x += speedX*dt;
        y += speedY*dt;
        updateBox();

    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(player_box.x, player_box.y, player_box.width, player_box.height);
    }

    private void updateBox() {
        player_box.setPosition(x, y);
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


}
