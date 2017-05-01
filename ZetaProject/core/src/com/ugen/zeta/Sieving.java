package com.ugen.zeta;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class Sieving extends Game {
	private InputHandler handler;
	SievingScreen sievingScreen;
	SumScreen sumScreen;
	MainMenu mainMenu;

	@Override
	public void create() {
		AssetManager.load();
		sievingScreen = new SievingScreen();
		sumScreen = new SumScreen();
		mainMenu = new MainMenu(this);
		Gdx.input.setCatchBackKey(true);
		handler = new InputHandler(this);

		setScreen(mainMenu);
	}

	public void Advance(){
		sievingScreen.Advance();
	}

	public SievingScreen getSievingScreen(){
		return sievingScreen;
	}

	public InputProcessor getHandler(){
		return handler;
	}

	@Override
	public void render(){
		super.render();
	}

	@Override
	public void dispose(){
		super.dispose();
	}

	public void changeScreen(int i){
		switch (i) {
			case 0:
				setScreen(sievingScreen);
				Gdx.input.setInputProcessor(handler);
				break;
			case 1:
				Gdx.input.setInputProcessor(null);
				setScreen(sumScreen);
				break;
			case 2:
				sievingScreen.reset();
				sumScreen.reset();
				setScreen(mainMenu);

				break;
		}
	}
}

