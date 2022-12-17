package com.politecnicomalaga.madmaxroad.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.politecnicomalaga.madmaxroad.GdxMadMaxRoad;
import com.politecnicomalaga.madmaxroad.managers.AssetsManager;
import com.politecnicomalaga.madmaxroad.managers.SettingsManager;

public class WarCars extends ObjetoVolador {

    protected final String[] type = AssetsManager.IMGS_WARCARS;

    public WarCars() {
        this.posX = Gdx.graphics.getWidth();
        this.posY = (float) (Math.random() * (Gdx.graphics.getWidth() - SettingsManager.WARCARS_WIDTH)) + SettingsManager.WARCARS_WIDTH;
        this.velX = -4;
        this.velY = 0;
        this.img = new Texture(type[(int) (Math.random() * 3)]);
        this.anchoDiv2 = SettingsManager.WARCARS_WIDTH / 2.0f;
        this.altoDiv2 = SettingsManager.WARCARS_HEIGHT / 2.0f;
    }

    public void Spawn() {
        if (GdxMadMaxRoad.ReserveCWC.isEmpty()) {
            GdxMadMaxRoad.CrashWarCars.add(new WarCars());
        } else {
            WarCars aux = GdxMadMaxRoad.ReserveCWC.get(0);
            aux.setPosX();
            aux.setPosY();
            GdxMadMaxRoad.CrashWarCars.add(aux);
            GdxMadMaxRoad.ReserveCWC.remove(0);
        }
        if (GdxMadMaxRoad.CrashWarCars.get(0).getPosX() <= 0) {
            GdxMadMaxRoad.ReserveCWC.add(GdxMadMaxRoad.CrashWarCars.get(0));
            GdxMadMaxRoad.CrashWarCars.remove(0);
        }
    }

    public void wcMove() {
        for (WarCars ff : GdxMadMaxRoad.CrashWarCars) {
            ff.moverse();
        }
    }

    public boolean wcColisiona(MadMax aux) {
        boolean resultado = false;
        int posicion = 0;
        while (!resultado && posicion < GdxMadMaxRoad.CrashWarCars.size()) {
            resultado = GdxMadMaxRoad.CrashWarCars.get(posicion).colisiona(aux);
            if (resultado) {
                WarCars fruitCrashed = GdxMadMaxRoad.CrashWarCars.remove(posicion);
                fruitCrashed.dispose();
            }
            posicion++;
        }
        return resultado;
    }

    public void wcDraw(SpriteBatch miSB) {
        for (WarCars ff : GdxMadMaxRoad.CrashWarCars) {
            ff.pintarse(miSB, SettingsManager.WARCARS_WIDTH, SettingsManager.WARCARS_HEIGHT);
        }
    }

    public void wcDispose() {
        for (WarCars ff : GdxMadMaxRoad.CrashWarCars) {
            ff.dispose();
        }
        for (WarCars rf : GdxMadMaxRoad.ReserveCWC) {
            rf.dispose();
        }
    }

    public void setPosY() {
        this.posY = (float) (Math.random() * (Gdx.graphics.getHeight() - SettingsManager.SCREEN_HEIGHT)) + SettingsManager.WARCARS_HEIGHT;
    }

    public void setPosX() {
        this.posX = Gdx.graphics.getWidth();
    }

}

