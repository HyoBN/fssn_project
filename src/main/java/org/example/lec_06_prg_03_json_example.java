package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class lec_06_prg_03_json_example {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File jsonFile = new File("src/main/java/org/example/lec-06-prg-03-json-example.json");
            JsonNode superHeroes = objectMapper.readTree(jsonFile);

            System.out.println(superHeroes.get("homeTown").asText());
            System.out.println(superHeroes.get("active").asBoolean());
            System.out.println(superHeroes.get("members").get(1).get("powers").get(2).asText());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}