package com.demo.moviecatalogservice.Controller;
import com.demo.moviecatalogservice.models.CatalogItem;
import com.demo.moviecatalogservice.models.Movie;
import com.demo.moviecatalogservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

//    RestTemplate restTemplate;
    WebClient.Builder webClientBuilder;

    public MovieCatalogController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }
    //    public MovieCatalogController(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId){

        //get All rated movies ids
        List<Rating> ratings = Arrays.asList(
                new Rating("123",3),
                new Rating("657",4)
        );

        return ratings.stream().map(rating ->{
            //Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
            Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
            return new CatalogItem(movie.getName(),"Desc",rating.getRating());
        })
        .collect(Collectors.toList());
        ///For each movie Id, call movie info service and get details;
        //put all together
    }
}
