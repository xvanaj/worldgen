package com.mygdx.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.vana.MapScreen;


public class DesktopLauncher {

	public static void main(String[] args) {
		Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();

		final Lwjgl3Application lwjglApplication = new Lwjgl3Application(new MapScreen(), configuration);
		configuration.setWindowedMode(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


		Gdx.app = lwjglApplication;
	}
}
