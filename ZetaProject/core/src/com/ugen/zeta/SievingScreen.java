package com.ugen.zeta;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

/**
 * Created by WilsonCS30 on 4/28/2017.
 */

public class SievingScreen extends ScreenAdapter{

    private ShapeRenderer renderer;
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera cam;
    private ArrayList<Block> blocks;
    private Viewport viewport;
    private InputHandler handler;
    private ArrayList<Integer> primes;


    public SievingScreen() {
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2.0f);
        cam = new OrthographicCamera(1.0f, (float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth());
        blocks = new ArrayList<Block>();

        Gdx.input.setInputProcessor(handler);
        cam = new OrthographicCamera(1.0f, (float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth());
        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam);
        viewport.apply();
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);


        primes = new ArrayList<Integer>();

        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 10; j++) {
                blocks.add(new Block(10 * i + j + 1, new Vector2(j * 4 * Gdx.graphics.getWidth() / 41 + Gdx.graphics.getWidth() / 41,
                        Gdx.graphics.getHeight() - (i * 4 * Gdx.graphics.getWidth() / 41 + 4 * Gdx.graphics.getWidth() / 41))));
            }
        }
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(cam.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        for(Block b : blocks){
            if(primes.contains(b.getNumber()))
                renderer.setColor(Color.GREEN);
            else if(!b.isPrime() || b.getNumber() == 1)
                renderer.setColor(Color.RED);
            else if(b.isPrime())
                renderer.setColor(Color.ROYAL);


            b.draw(renderer);
        }


        renderer.end();
        ////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
/////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        batch.begin();

        for(Block b : blocks){
            b.drawText(batch, font);
        }

        batch.end();
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(handler);
    }

    public void Advance(){
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        for (Block b : blocks) {
            if (b.isPrime() && b.getNumber() != 1 && !primes.contains(b.getNumber())) {
                primes.add(b.getNumber());

                for (int i = blocks.indexOf(b) + b.getNumber(); i < blocks.size(); i += b.getNumber()) {
                    blocks.get(i).setPrime(false);
                    blocks.get(i).draw(renderer);
                }

                break;
            }
        }

        renderer.end();
    }

    public void reset(){
        primes.clear();

        for(Block b : blocks){
            if(b.getNumber() != 1)
                 b.setPrime(true);
        }
    }

    @Override
    public void dispose () {
        super.dispose();
    }

    public ArrayList<Integer> getPrimes() {
        return primes;
    }
}


