package com.univassouras.iterator;

import java.util.NoSuchElementException;

public class IteradorSequencial implements Iterador<Musica> {
    private final Playlist playlist;
    private int posicao;

    public IteradorSequencial(Playlist playlist) {
        this.playlist = playlist;
        this.posicao = 0;
    }

    @Override
    public boolean temProximo() {
        return posicao < playlist.tamanho();
    }

    @Override
    public Musica proximo() {
        if (!temProximo()) {
            throw new NoSuchElementException("Nao ha mais musicas na playlist");
        }
        Musica musica = playlist.obter(posicao);
        posicao++;
        return musica;
    }
}
