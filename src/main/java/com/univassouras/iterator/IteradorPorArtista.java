package com.univassouras.iterator;

import java.util.NoSuchElementException;

public class IteradorPorArtista implements Iterador<Musica> {
    private final Playlist playlist;
    private final String artista;
    private int posicao;

    public IteradorPorArtista(Playlist playlist, String artista) {
        this.playlist = playlist;
        this.artista = artista;
        this.posicao = proximaPosicaoDoArtista(0);
    }

    private int proximaPosicaoDoArtista(int aPartirDe) {
        int indice = aPartirDe;
        while (indice < playlist.tamanho() && !playlist.obter(indice).getArtista().equalsIgnoreCase(artista)) {
            indice++;
        }
        return indice;
    }

    @Override
    public boolean temProximo() {
        return posicao < playlist.tamanho();
    }

    @Override
    public Musica proximo() {
        if (!temProximo()) {
            throw new NoSuchElementException("Nao ha mais musicas deste artista na playlist");
        }
        Musica musica = playlist.obter(posicao);
        posicao = proximaPosicaoDoArtista(posicao + 1);
        return musica;
    }
}
