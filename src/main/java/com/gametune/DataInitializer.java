package com.gametune;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gametune.model.hardware.Cpu;
import com.gametune.repository.CpuRepository;
import com.gametune.repository.GameRepository;
import com.gametune.repository.GpuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CpuRepository cpuRepository;

    @Autowired
    private GpuRepository gpuRepository;

    @Autowired
    private GameRepository gameRepository;


    @Override
    public void run(String... args) throws Exception{
        System.out.println("Checking for new CPUs in cpus.json...");

        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/cpus.json");
        List<Cpu> cpusFromJson = mapper.readValue(inputStream, new TypeReference<List<Cpu>>() {});

        //Fetch existing CPUs from the database
        List<String> existingCpuModels = cpuRepository.findAll()
                .stream()
                .map(Cpu::getModel)
                .collect(Collectors.toList());

        //Filter out CPUs that are already in the database
        List<Cpu> newCpus = cpusFromJson.stream()
                .filter(cpu -> !existingCpuModels.contains(cpu.getModel()))
                .collect(Collectors.toList());

        // Save new CPUs to the database
        if(!newCpus.isEmpty()) {
            cpuRepository.saveAll(newCpus);
            System.out.println(newCpus.size() + " new CPUs added to the database.");
        }else{
            System.out.println("No new CPUs found in cpus.json.");
        }
    }
}
