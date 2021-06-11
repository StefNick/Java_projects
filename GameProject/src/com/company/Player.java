package com.company;

import java.awt.*;

public class Player extends GameObject {

    public Player(float x, float y, ID id){
        super(x,y, id);

        velX = 5;
        velY = 1;
    }



    public void tick() {
        x += velX;
        y += velY;

        if (x > Game.WIDTH) x = 0;
        if (y > Game.HEIGHT) y = 0;
    }

    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect((int)x, (int)y, 32 ,32);
    }
}
