package com.mygdx.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Shooter2d;

/**
 * Created by Adson Work on 11.11.2017.
 */
public class LoadingState extends ScreenAdapter {
    private static final float WORLD_WIDTH = 1280;
    private static final float WORLD_HEIGHT = 720;
    private static final float PROGRESS_BAR_WIDTH = 100;
    private static final float PROGRESS_BAR_HEIGHT = 25;
    private ShapeRenderer shapeRenderer;
    private Viewport viewport;
    private OrthographicCamera camera;
    private float progress = 0;
    private final Shooter2d shooter2d;
    public LoadingState(Shooter2d peteGame) {
        this.shooter2d = peteGame;
    }
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        camera.update();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        shapeRenderer = new ShapeRenderer();
        shooter2d.getAssetManager().load("map/level2.tmx", TiledMap.class);
    }
    @Override
    public void render(float delta) {
        update();
        clearScreen();
        draw();
    }
    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
    private void update() {
        if (shooter2d.getAssetManager().update()) {
            shooter2d.setScreen(new GameState(shooter2d));
            System.out.println("switch to GameState ");
        } else {
            progress = shooter2d.getAssetManager().getProgress();
        }
    }
    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g,Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    private void draw() {
        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(
                (WORLD_WIDTH - PROGRESS_BAR_WIDTH) / 2, WORLD_HEIGHT / 2 -
                        PROGRESS_BAR_HEIGHT / 2,
                progress * PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT);
        shapeRenderer.end();
    }
}