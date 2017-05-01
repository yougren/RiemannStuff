package com.ugen.zeta;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

/**
 * Created by WilsonCS30 on 4/26/2017.
 */

public class SumScreen extends ScreenAdapter{

    private ShapeRenderer renderer;
    private ArrayList<Rectangle> squares;
    private int squareNum = 10;
    
    private ArrayList<Color> colors;


    public SumScreen(){
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);

        squares = new ArrayList<Rectangle>();
        colors = new ArrayList<Color>();

        for(int i = 0; i < squareNum; i++){
            
            float red = (float) Math.sin((float)i * 6 / squareNum) * 0.5f + 0.5f;
            float green = (float) Math.sin((float)i * 6 / squareNum + 1) * 0.5f + 0.5f;
            float blue = (float) Math.sin((float)i * 6 /  squareNum + 3) * 0.5f + 0.5f;
        
            colors.add(new Color(red, green, blue, 1.0f));

            if(i == 0){
                squares.add(new Rectangle(0, Gdx.graphics.getHeight() - 14, 6*(i * i), 6*(i * i)));
            }
            else
                squares.add(new Rectangle(0, squares.get(i-1).getY() - 6*i*i, 6*(i * i), 6*(i * i)));
        }
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        for(float i = 0; i < 8; i+= .1){
            renderer.circle(i * Gdx.graphics.getWidth()/8, i * i + Gdx.graphics.getHeight()/2, 2, 20);
        }

        renderer.end();
    }

    public void reset(){

    }

    @Override
    public void show(){

    }

    @Override
    public void dispose(){
        super.dispose();
    }
}
