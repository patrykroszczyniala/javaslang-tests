package io.github.pepuch.exceptionhandling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExceptionHandlingExampleWithoutJavaslang implements ExceptionHandlingExample {

    @Override
    public String getFileContent(final String filePath) {
        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (final IOException e) {
            return "";
        }
    }

    @Override
    public void writeFileContent(final String filePath, final String fileContent) {
        try (final FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(fileContent);
        } catch (final IOException e) {
            // hmmm
        }
    }

    @Override
    public Collection<Integer> getNumbers(Collection<String> list) {
        return list.stream()
                .flatMap(this::toNumber)
                .collect(Collectors.toList());
    }

    private Stream<Integer> toNumber(String possibleNumber) {
        try {
            return Stream.of(Integer.parseInt(possibleNumber));
        } catch (final NumberFormatException e) {
            return Stream.empty();
        }
    }

}
