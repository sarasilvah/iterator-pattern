package com.univassouras.iterator;

import java.util.NoSuchElementException;

public class IteradorReverso implements Iterador<Musica> {
    private final Playlist playlist;
    private int posicao;

    public IteradorReverso(Playlist playlist) {
        this.playlist = playlist;
        this.posicao = playlist.tamanho() - 1;
    }

    @Override
    public boolean temProximo() {
        return posicao >= 0;
    }

    @Override
    public Musica proximo() {
        if (!temProximo()) {
            throw new NoSuchElementException("Nao ha mais musicas na playlist");
        }
        Musica musica = playlist.obter(posicao);
        posicao--;
        return musica;
    }
}
