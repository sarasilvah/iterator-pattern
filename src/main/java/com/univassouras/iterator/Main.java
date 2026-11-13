package com.univassouras.iterator;

public class Main {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.adicionar(new Musica("Aquarela", "Toquinho", 240));
        playlist.adicionar(new Musica("Mas Que Nada", "Sergio Mendes", 195));
        playlist.adicionar(new Musica("Samba de Verao", "Marcos Valle", 180));
        playlist.adicionar(new Musica("Berimbau", "Toquinho", 210));
        playlist.adicionar(new Musica("Chega de Saudade", "Joao Gilberto", 125));

        System.out.println("Ordem normal:");
        imprimir(playlist.criarIterador());

        System.out.println();
        System.out.println("Ordem reversa:");
        imprimir(playlist.criarIteradorReverso());

        System.out.println();
        System.out.println("Somente Toquinho:");
        imprimir(playlist.criarIteradorPorArtista("Toquinho"));

        System.out.println();
        System.out.println("Duracao total: " + somarDuracao(playlist.criarIterador()) + " segundos");
    }

    private static void imprimir(Iterador<Musica> iterador) {
        int numero = 1;
        while (iterador.temProximo()) {
            System.out.println(numero + ". " + iterador.proximo());
            numero++;
        }
    }

    private static int somarDuracao(Iterador<Musica> iterador) {
        int total = 0;
        while (iterador.temProximo()) {
            total += iterador.proximo().getDuracaoSegundos();
        }
        return total;
    }
}
