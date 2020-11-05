package com.example.p5_live_data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class PokemonViewModel extends AndroidViewModel {
    Pokemon pokemon;

    LiveData<Integer> evolucionLiveData;

    public PokemonViewModel(@NonNull Application application) {
        super(application);

        pokemon = new Pokemon();

        evolucionLiveData = Transformations.switchMap(pokemon.evolucionLiveData, new Function<String, LiveData<Integer>>() {

            @Override
            public LiveData<Integer> apply(String evolucion) {
                int imagen;
                switch (evolucion) {
                    case "Evolucion1":
                    default:
                        imagen = R.drawable.totodile;
                        break;
                    case "Evolucion2":
                        imagen = R.drawable.croconaw;
                        break;
                    case "Evolucion3":
                        imagen = R.drawable.feraligatr;
                        break;
                }

                return new MutableLiveData<>(imagen);
            }
        });
    }

    LiveData<Integer> obtenerEvolucion(){
        return evolucionLiveData;
    }
}