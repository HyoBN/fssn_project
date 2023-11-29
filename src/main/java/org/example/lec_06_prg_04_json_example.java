package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class lec_06_prg_04_json_example {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            // Creating JSON structure similar to the Python code
            JsonNode superHeroes = objectMapper.createObjectNode()
                    .put("squadName", "Super hero squad")
                    .put("homeTown", "Metro City")
                    .put("formed", 2016)
                    .put("secretBase", "Super tower")
                    .put("active", true)
                    .set("members", objectMapper.createArrayNode()
                            .add(objectMapper.createObjectNode()
                                    .put("name", "Molecule Man")
                                    .put("age", 29)
                                    .put("secretIdentity", "Dan Jukes")
                                    .set("powers", objectMapper.createArrayNode()
                                            .add("Radiation resistance")
                                            .add("Turning tiny")
                                            .add("Radiation blast")))
                            .add(objectMapper.createObjectNode()
                                    .put("name", "Madame Uppercut")
                                    .put("age", 39)
                                    .put("secretIdentity", "Jane Wilson")
                                    .set("powers", objectMapper.createArrayNode()
                                            .add("Million tonne punch")
                                            .add("Damage resistance")
                                            .add("Superhuman reflexes")))
                            .add(objectMapper.createObjectNode()
                                    .put("name", "Eternal Flame")
                                    .put("age", 1000000)
                                    .put("secretIdentity", "Unknown")
                                    .set("powers", objectMapper.createArrayNode()
                                            .add("Immortality")
                                            .add("Heat Immunity")
                                            .add("Inferno")
                                            .add("Teleportation")
                                            .add("Interdimensional travel"))));

            // Accessing and printing values
            System.out.println(superHeroes.get("homeTown").asText());
            System.out.println(superHeroes.get("active").asBoolean());
            System.out.println(superHeroes.get("members").get(1).get("powers").get(2).asText());

            // Writing JSON to file
            objectMapper.writeValue(new File("src/main/java/org/example/lec-06-prg-04-json-example.json"), superHeroes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}