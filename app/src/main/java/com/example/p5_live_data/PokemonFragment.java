package com.example.p5_live_data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.p5_live_data.databinding.FragmentPokemonBinding;

public class PokemonFragment extends Fragment {
    private FragmentPokemonBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentPokemonBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PokemonViewModel pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        pokemonViewModel.obtenerEvolucion().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer imagenEvolucion) {
                Glide.with(PokemonFragment.this).load(imagenEvolucion).into(binding.pokemon);
            }
        });
    }
}
