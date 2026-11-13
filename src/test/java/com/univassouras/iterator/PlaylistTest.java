package com.univassouras.iterator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlaylistTest {

    private Playlist playlistComCincoMusicas() {
        Playlist playlist = new Playlist();
        playlist.adicionar(new Musica("Aquarela", "Toquinho", 240));
        playlist.adicionar(new Musica("Mas Que Nada", "Sergio Mendes", 195));
        playlist.adicionar(new Musica("Samba de Verao", "Marcos Valle", 180));
        playlist.adicionar(new Musica("Berimbau", "Toquinho", 210));
        playlist.adicionar(new Musica("Chega de Saudade", "Joao Gilberto", 125));
        return playlist;
    }

    private List<String> titulos(Iterador<Musica> iterador) {
        List<String> titulos = new ArrayList<>();
        while (iterador.temProximo()) {
            titulos.add(iterador.proximo().getTitulo());
        }
        return titulos;
    }

    @Test
    void iteradorSequencialDevePercorrerNaOrdemDeInsercao() {
        Playlist playlist = playlistComCincoMusicas();

        List<String> resultado = titulos(playlist.criarIterador());

        assertEquals(List.of("Aquarela", "Mas Que Nada", "Samba de Verao", "Berimbau", "Chega de Saudade"), resultado);
    }

    @Test
    void iteradorReversoDevePercorrerDoFimParaOInicio() {
        Playlist playlist = playlistComCincoMusicas();

        List<String> resultado = titulos(playlist.criarIteradorReverso());

        assertEquals(List.of("Chega de Saudade", "Berimbau", "Samba de Verao", "Mas Que Nada", "Aquarela"), resultado);
    }

    @Test
    void iteradorPorArtistaDeveRetornarSomenteMusicasDoArtista() {
        Playlist playlist = playlistComCincoMusicas();

        List<String> resultado = titulos(playlist.criarIteradorPorArtista("Toquinho"));

        assertEquals(List.of("Aquarela", "Berimbau"), resultado);
    }

    @Test
    void iteradorPorArtistaDeveIgnorarDiferencaEntreMaiusculasEMinusculas() {
        Playlist playlist = playlistComCincoMusicas();

        List<String> resultado = titulos(playlist.criarIteradorPorArtista("toquinho"));

        assertEquals(2, resultado.size());
    }

    @Test
    void iteradorPorArtistaSemCorrespondenciaNaoDeveTerProximo() {
        Playlist playlist = playlistComCincoMusicas();

        Iterador<Musica> iterador = playlist.criarIteradorPorArtista("Artista Inexistente");

        assertFalse(iterador.temProximo());
    }

    @Test
    void chamarProximoAposOFimDeveLancarExcecao() {
        Playlist playlist = new Playlist();
        playlist.adicionar(new Musica("Aquarela", "Toquinho", 240));
        Iterador<Musica> iterador = playlist.criarIterador();
        iterador.proximo();

        assertFalse(iterador.temProximo());
        assertThrows(NoSuchElementException.class, iterador::proximo);
    }

    @Test
    void playlistVaziaNaoDeveTerProximoEmNenhumIterador() {
        Playlist vazia = new Playlist();

        assertFalse(vazia.criarIterador().temProximo());
        assertFalse(vazia.criarIteradorReverso().temProximo());
        assertFalse(vazia.criarIteradorPorArtista("Toquinho").temProximo());
    }

    @Test
    void doisIteradoresDevemAndarDeFormaIndependente() {
        Playlist playlist = playlistComCincoMusicas();
        Iterador<Musica> primeiro = playlist.criarIterador();
        Iterador<Musica> segundo = playlist.criarIterador();

        primeiro.proximo();
        primeiro.proximo();

        assertEquals("Aquarela", segundo.proximo().getTitulo());
        assertEquals("Samba de Verao", primeiro.proximo().getTitulo());
    }

    @Test
    void codigoClienteDeveFuncionarComQualquerIteradorSemConhecerSuaClasse() {
        Playlist playlist = playlistComCincoMusicas();

        int totalSequencial = somarDuracao(playlist.criarIterador());
        int totalReverso = somarDuracao(playlist.criarIteradorReverso());
        int totalToquinho = somarDuracao(playlist.criarIteradorPorArtista("Toquinho"));

        assertEquals(950, totalSequencial);
        assertEquals(950, totalReverso);
        assertEquals(450, totalToquinho);
    }

    @Test
    void playlistDeveSerUmaColecaoIteravel() {
        ColecaoIteravel<Musica> colecao = new Playlist();

        assertTrue(colecao.criarIterador() instanceof IteradorSequencial);
    }

    private int somarDuracao(Iterador<Musica> iterador) {
        int total = 0;
        while (iterador.temProximo()) {
            total += iterador.proximo().getDuracaoSegundos();
        }
        return total;
    }
}
