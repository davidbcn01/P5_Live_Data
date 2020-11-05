package com.example.p5_live_data;
import androidx.lifecycle.LiveData;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Pokemon {

    interface PokemonListener {
        void cuandoEvolucione(String evolucion);
    }


    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    ScheduledFuture<?> evolucionando;

    LiveData<String> evolucionLiveData = new LiveData<String>() {
        @Override
        protected void onActive() {
            super.onActive();

            iniciarEvolucion(new PokemonListener() {
                @Override
                public void cuandoEvolucione(String evolucion) {
                    postValue(evolucion);
                }
            });
        }

        @Override
        protected void onInactive() {
            super.onInactive();

            pararEvolucion();
        }
    };

    void iniciarEvolucion(PokemonListener pokemonListener) {
        if (evolucionando == null || evolucionando.isCancelled()) {
            evolucionando = scheduler.scheduleAtFixedRate(new Runnable() {
                int evolucion = 1;

                @Override
                public void run() {
                    pokemonListener.cuandoEvolucione("Evolucion" + evolucion);
                    evolucion++;
                    if(evolucion > 3){
                        evolucion = 1;
                    }
                }
            }, 0, 3, SECONDS);
        }
    }

    void pararEvolucion() {
        if (evolucionando != null) {
            evolucionando.cancel(true);
        }
    }


}


/*
Evolucion1
Evolucion2
Evolucion3


 */