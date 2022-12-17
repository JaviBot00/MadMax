package com.politecnicomalaga.madmaxroad;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.politecnicomalaga.madmaxroad.managers.SettingsManager;
import com.politecnicomalaga.madmaxroad.model.Fondo;
import com.politecnicomalaga.madmaxroad.model.MadMax;
import com.politecnicomalaga.madmaxroad.model.WarCars;
import com.politecnicomalaga.madmaxroad.view.PanelNumeros;

import java.util.ArrayList;

public class GdxMadMaxRoad extends ApplicationAdapter {

    SpriteBatch myBatch;
    public static final ArrayList<WarCars> CrashWarCars = new ArrayList<>();
    public static final ArrayList<WarCars> ReserveCWC = new ArrayList<>();
    MadMax GhostRider;
    WarCars WarCars;
    PanelNumeros numberPanel;
    Fondo wallPaper;

    @Override
    public void create() {
        myBatch = new SpriteBatch();
        GhostRider = new MadMax();
        WarCars = new WarCars();
        CrashWarCars.add(WarCars);
        numberPanel = new PanelNumeros(0, SettingsManager.SCREEN_HEIGHT - 32, 50);
        numberPanel.setData(3);
        wallPaper = new Fondo("road.png", 5, 0, 0, (SettingsManager.SCREEN_HEIGHT * 2) - (SettingsManager.SCREEN_HEIGHT / 2), SettingsManager.SCREEN_WIDTH, SettingsManager.SCREEN_HEIGHT);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        pantallaJuego();
    }


    public void pantallaJuego() {
        if (numberPanel.getiValorAlmacenado() != 0) {
            // Movement MadMax
            if (Gdx.input.justTouched()) {
                int pixelY = Gdx.input.getY();
                float pantalladivididaEntre3 = Gdx.graphics.getHeight() / 3f;
                if (pixelY > (pantalladivididaEntre3 * 2)) {
                    GhostRider.setVelY(-2);
                } else if (pixelY < (pantalladivididaEntre3)) {
                    GhostRider.setVelY(2);
                } else {
                    GhostRider.setVelY(0);
                }
            }

            // Crashes
            if (WarCars.wcColisiona(GhostRider)) {
                numberPanel.incrementa(-1);
            }


            // Spawn WarCars
            if (Math.random() > 0.98f) {
                WarCars.Spawn();
            }

//            if (Math.random() > 0.99f) {
//                warCarsPosY = (int) (Math.random() * (SIZE_HEIGTH - 32) + 32);
//                CrashWarCars.add(new ObjetoVolador(SIZE_WIDTH, warCarsPosY, -1f, 0f, "broken.png"));
//
//            }

            // Movement
            wallPaper.avanzar();
            GhostRider.moverse();
            WarCars.wcMove();

        } else {
            if (Gdx.input.justTouched()) {
                numberPanel.setData(3);
            }
        }
        wallPaper.pintate(myBatch);
        GhostRider.mPintarse(myBatch);
        WarCars.wcDraw(myBatch);
        numberPanel.pintarse(myBatch);
    }

    public void dispose() {
        myBatch.dispose();
        GhostRider.dispose();
        WarCars.wcDispose();
        numberPanel.dispose();
        wallPaper.dispose();
    }
}