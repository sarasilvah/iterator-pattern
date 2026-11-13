package com.univassouras.iterator;

import java.util.ArrayList;
import java.util.List;

public class Playlist implements ColecaoIteravel<Musica> {
    private final List<Musica> musicas = new ArrayList<>();

    public void adicionar(Musica musica) {
        musicas.add(musica);
    }

    public int tamanho() {
        return musicas.size();
    }

    public Musica obter(int posicao) {
        return musicas.get(posicao);
    }

    @Override
    public Iterador<Musica> criarIterador() {
        return new IteradorSequencial(this);
    }

    public Iterador<Musica> criarIteradorReverso() {
        return new IteradorReverso(this);
    }

    public Iterador<Musica> criarIteradorPorArtista(String artista) {
        return new IteradorPorArtista(this, artista);
    }
}
