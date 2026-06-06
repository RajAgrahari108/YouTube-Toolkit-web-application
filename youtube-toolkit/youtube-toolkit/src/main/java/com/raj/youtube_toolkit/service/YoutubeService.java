package com.raj.youtube_toolkit.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class YoutubeService {

    public String runCommand(String command) {

        StringBuilder output = new StringBuilder();

        try {

            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", command
            );

            builder.redirectErrorStream(true);

            Process process = builder.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            process.waitFor();

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }

        return output.toString();
    }
}