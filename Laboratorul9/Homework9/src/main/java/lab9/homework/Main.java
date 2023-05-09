package lab9.homework;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lab9.homework.models.Album;
import lab9.homework.models.Artist;
import lab9.homework.models.Genre;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        // Creeaza repository-uri pentru fiecare tip de entitate
        EntityRepository<Genre> genreRepository = new EntityRepository<>(Genre.class);
        EntityRepository<Artist> artistRepository = new EntityRepository<>(Artist.class);
        EntityRepository<Album> albumRepository = new EntityRepository<>(Album.class);

        // Creeaza o noua entitate Genre si adaug-o in baza de date
        Genre genre = new Genre();
        genre.setName("Rock");
        genreRepository.create(genre);

        // Creeaza o noua entitate Artist si adaug-o in baza de date
        Artist artist = new Artist();
        artist.setName("Led Zeppelin");
        artistRepository.create(artist);

        // Creeaza o noua entitate Album si adaug-o in baza de date
        Album album = new Album();
        album.setTitle("Led Zeppelin IV");
        album.setReleaseYear(1971);
        album.setArtist(artist);
        album.addGenre(genre);
        albumRepository.create(album);

        // Gsseste o entitate dupa id
        System.out.println("Gaseste entitatea dupa id:");
        Genre foundGenre = genreRepository.findById(genre.getId());
        Artist foundArtist = artistRepository.findById(artist.getId());
        Album foundAlbum = albumRepository.findById(album.getId());

        System.out.println("Genul gasit: " + foundGenre.getName());
        System.out.println("Artistul gasit: " + foundArtist.getName());
        System.out.println("Albumul gasit: " + foundAlbum.getTitle());

        // Gaseste o entitate dupa nume
        System.out.println("\nGaseste entitatea dupa nume:");
        foundGenre = genreRepository.findByName("Rock");
        foundArtist = artistRepository.findByName("Led Zeppelin");

        System.out.println("Genul gasit: " + foundGenre.getName());
        System.out.println("Artistul gasit: " + foundArtist.getName());


        EntityManager entityManager = DatabaseEntity.getEntityManager();

        // insert fake artists and albums and log execution time
        insertFakeArtistsAndAlbums(entityManager);
    }

    public static void insertFakeArtistsAndAlbums(EntityManager entityManager) {
        long startTime = System.currentTimeMillis();

        // Create a list of fake artists and albums
        List<Artist> artists = new ArrayList<>();
        List<Album> albums = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            Artist artist = new Artist();
            artist.setName("Artist " + i);
            artists.add(artist);
            for (int j = 1; j <= 5; j++) {
                Album album = new Album();
                album.setTitle("Album " + j + " of Artist " + i);
                album.setReleaseYear(2000 + j);
                album.setArtist(artist);
                albums.add(album);
            }
        }

        // Insert the fake artists and albums in the database
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        for (Artist artist : artists) {
            entityManager.persist(artist);
        }
        for (Album album : albums) {
            entityManager.persist(album);
        }
        transaction.commit();

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("\nS-au inserat " + artists.size() + " artisti si " + albums.size() + " albume in " + elapsedTime + " ms.");
    }


}

