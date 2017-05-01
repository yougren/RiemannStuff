package com.ugen.zeta;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by WilsonCS30 on 4/18/2017.
 */

public class Block {
    private int number;
    private Vector2 position;
    private float width, height;
    private GlyphLayout glyphLayout = new GlyphLayout();
    private boolean isPrime = true;


    public Block(int number, Vector2 position){
        this.number = number;
        this.position = position;
        width = height = 3 * Gdx.graphics.getWidth() / 41;
    }

    public void draw(ShapeRenderer renderer){
        renderer.rect(position.x, position.y, width, height);
    }

    public void drawText(SpriteBatch batch, BitmapFont font){
        String fontText = number + "";
        glyphLayout.setText(font, fontText);
        float w = glyphLayout.width;
        float h = glyphLayout.height;

        font.draw(batch, number + "", position.x + width / 2 - w/2, position.y + height/2 + h/2);

    }

    public int getNumber() {
        return number;
    }

    public void setPrime(boolean prime){
        this.isPrime = prime;
    }

    public boolean isPrime(){
        return isPrime;
    }
}
