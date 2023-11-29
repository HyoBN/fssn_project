package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class lec_06_prg_05_json_example {

    public static void main(String[] args) {
        // Creating the source map similar to the Python code
        Map<String, Object> superHeroesSource = new HashMap<>();
        superHeroesSource.put("squadName", "Super hero squad");
        superHeroesSource.put("homeTown", "Metro City");
        superHeroesSource.put("formed", 2016);
        superHeroesSource.put("secretBase", "Super tower");
        superHeroesSource.put("active", true);

        List<Map<String, Object>> members = List.of(
                Map.of(
                        "name", "Molecule Man",
                        "age", 29,
                        "secretIdentity", "Dan Jukes",
                        "powers", List.of("Radiation resistance", "Turning tiny", "Radiation blast")
                ),
                Map.of(
                        "name", "Madame Uppercut",
                        "age", 39,
                        "secretIdentity", "Jane Wilson",
                        "powers", List.of("Million tonne punch", "Damage resistance", "Superhuman reflexes")
                ),
                Map.of(
                        "name", "Eternal Flame",
                        "age", 1000000,
                        "secretIdentity", "Unknown",
                        "powers", List.of("Immortality", "Heat Immunity", "Inferno", "Teleportation", "Interdimensional travel")
                )
        );

        superHeroesSource.put("members", members);

        // Converting the map to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            String superHeroes = objectMapper.writeValueAsString(superHeroesSource);
            System.out.println(superHeroes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
