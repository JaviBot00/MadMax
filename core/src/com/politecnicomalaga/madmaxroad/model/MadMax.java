package com.politecnicomalaga.madmaxroad.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.politecnicomalaga.madmaxroad.managers.AssetsManager;
import com.politecnicomalaga.madmaxroad.managers.SettingsManager;

public class MadMax extends ObjetoVolador {

    public MadMax() {
        this.posX = 30;
        this.posY = SettingsManager.SCREEN_HEIGHT / 2f - SettingsManager.MADMAX_MIDDLE;
        this.velX = 0;
        this.velY = 0;
        this.img = new Texture(AssetsManager.IMG_MADMAX);
        this.anchoDiv2 = SettingsManager.MADMAX_WIDTH / 2.0f;
        this.altoDiv2 = SettingsManager.MADMAX_HEIGHT / 2.0f;
    }


    public void mPintarse(SpriteBatch miSB) {
        miSB.begin();
        miSB.draw(img, posX, posY, SettingsManager.MADMAX_WIDTH, SettingsManager.MADMAX_HEIGHT);
        miSB.end();
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }
}
