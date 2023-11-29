package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class lec_06_prg_06_json_example {

    public static void main(String[] args) {
        // Creating the source map similar to the Python code
        Map<String, Object> superHeroesSource = Map.of(
                "squadName", "Super hero squad",
                "homeTown", "Metro City",
                "formed", 2016,
                "secretBase", "Super tower",
                "active", true,
                "members", List.of(
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
                )
        );

        // Converting the map to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            String superHeroesMid = objectMapper.writeValueAsString(superHeroesSource);

            // Converting the JSON string back to a map
            Map<String, Object> superHeroes = objectMapper.readValue(superHeroesMid, Map.class);

            // Accessing and printing values
            System.out.println(superHeroes.get("homeTown"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}