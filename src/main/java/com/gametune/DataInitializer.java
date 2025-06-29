package com.gametune;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gametune.model.Game;
import com.gametune.model.hardware.Cpu;
import com.gametune.model.hardware.Gpu;
import com.gametune.model.hardware.HardwareProfile;
import com.gametune.repository.CpuRepository;
import com.gametune.repository.GameRepository;
import com.gametune.repository.GpuRepository;
import com.gametune.repository.HardwareRepository;
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

    @Autowired
    private HardwareRepository hardwareRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void run(String... args) throws Exception{

        //Clear existing data
        System.out.println("Clearing existing data...");
        gameRepository.deleteAll();
        hardwareRepository.deleteAll();
        cpuRepository.deleteAll();
        gpuRepository.deleteAll();
        System.out.println("Existing data cleared.");

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

    private void loadGames() throws Exception{
        System.out.println("Checking for new games in games.json...");

        InputStream inputStream = getClass().getResourceAsStream("/games.json");
        List<GameDto> gameDtos = mapper.readValue(inputStream, new TypeReference<List<GameDto>>() {});

        //Fetch all existing game names
        List<String> existingGames = gameRepository.findAll()
                .stream()
                .map(Game::getName)
                .collect(Collectors.toList());

        //Filter out games that already exist in the database
        List<GameDto> newGameDtos = gameDtos.stream()
                .filter(dto -> !existingGames.contains(dto.name))
                .collect(Collectors.toList());

        //Special handling for games
        for (GameDto dto : newGameDtos){
            Game game = new Game();
            game.setName(dto.name);
            //game.setPublisher(dto.publisher.toUpperCase());
            game.setSupportsDLSS(dto.supportsDLSS);
            game.setSupportsFSR(dto.supportsFSR);

            Cpu minCpu = cpuRepository.findByModel(dto.minCpuModel).orElseThrow(() -> new RuntimeException("CPU not found: " + dto.minCpuModel));
            Gpu minGpu = gpuRepository.findByModel(dto.minGpuModel).orElseThrow(() -> new RuntimeException("GPU not found: " + dto.minGpuModel));

            Cpu recCpu = cpuRepository.findByModel(dto.recCpuModel).orElseThrow(() -> new RuntimeException("CPU not found: " + dto.recCpuModel));
            Gpu recGpu = gpuRepository.findByModel(dto.recGpuModel).orElseThrow(() -> new RuntimeException("GPU not found: " + dto.recGpuModel));

            //Create minimal hardware profile
            HardwareProfile minReq = new HardwareProfile();
            minReq.setCpu(minCpu);
            minReq.setGpu(minGpu);
            minReq.setRam(dto.minRam);
            game.setMinRequirements(minReq);

            //Create recommended hardware profile
            HardwareProfile recReq = new HardwareProfile();
            recReq.setCpu(recCpu);
            recReq.setGpu(recGpu);
            recReq.setRam(dto.recRam);
            game.setRecRequirements(recReq);

            gameRepository.save(game);
        }
    }
}

