package com.gametune;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gametune.model.hardware.Cpu;
import com.gametune.model.hardware.Gpu;
import com.gametune.repository.CpuRepository;
import com.gametune.repository.GameRepository;
import com.gametune.repository.GpuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CpuRepository cpuRepository;

    @Autowired
    private GpuRepository gpuRepository;

    @Autowired
    private GameRepository gameRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void run(String... args) throws Exception{
        loadData("/cpus.json", new TypeReference<List<Cpu>>() {}, cpuRepository, Cpu::getModel, "CPUs");
        loadData("/gpus.json", new TypeReference<List<Gpu>>() {}, gpuRepository, Gpu::getModel, "GPUs");
        loadGames();
    }

    /**
     * @param jsonFile Path to the JSON file in resources
     * @param typeReference Type reference for the list of entities
     * @param repository Repository to save the entities
     * @param modelExtractor Function to extract the model name from the entity
     * @param logName Name for logging purposes
     * @param <T> Type of the entity to be loaded
     * @param <ID> Type of the entity ID
     * @throws Exception If there is an error reading the JSON file or saving to the database
     */
    private <T, ID> void loadData(String jsonFile, TypeReference<List<T>> typeReference,
                                  JpaRepository<T, ID> repository, Function<T, String> modelExtractor, String logName) throws Exception {

        System.out.println("Checking for new " + logName + " in " + jsonFile + "...");

        InputStream inputStream = getClass().getResourceAsStream(jsonFile);
        List<T> entitiesFromJson = mapper.readValue(inputStream, typeReference);

        List<String> existingModels = repository.findAll()
                .stream()
                .map(modelExtractor)
                .collect(Collectors.toList());

        List<T> newEntities = entitiesFromJson.stream()
                .filter(entity -> !existingModels.contains(modelExtractor.apply(entity)))
                .collect(Collectors.toList());

        if (!newEntities.isEmpty()) {
            repository.saveAll(newEntities);
            System.out.println(newEntities.size() + " new " + logName + " added to the database.");
        } else {
            System.out.println("No new " + logName + " found in " + jsonFile + ".");
        }
    }

    private void loadGames() {
        // Deine Logik für die Spiele kommt hier hin
        System.out.println("Game loading not implemented yet.");
    }
}

