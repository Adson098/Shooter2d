package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.State.LoadingState;


public class Shooter2d extends Game {
	public static final int V_WIDTH = 1280;
	public static final int V_HEIGHT = 720;
	private final AssetManager assetManager = new AssetManager();
	@Override
	public void create() {
		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		setScreen(new LoadingState(this));

	}
	public AssetManager getAssetManager() {
		return assetManager;
	}
}