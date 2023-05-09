package lab9.compulsory;

import lab9.compulsory.models.Genres;


public class Main {
    public static void main(String[] args){
         Genres genre = new Genres();
         String gen = "rock";
         genre.setName(gen);

        EntityRepository<Genres> genresEntityRepository = new EntityRepository<>();
         genresEntityRepository.create(genre);

        Genres genre2 = genresEntityRepository.findById(genre.getId());
        System.out.println(genre2.getName());

        Genres genre1 = genresEntityRepository.findByName(gen);
        System.out.println(genre1.getId());
    }
}
