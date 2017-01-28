package io.github.pepuch.exceptionhandling;

import javaslang.control.Option;
import javaslang.control.Try;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * CONS
 * 1. Auto closeable not impelmented yet (https://github.com/javaslang/javaslang/issues/1263).
 */
public class ExceptionHandlingExampleWithJavaslang implements ExceptionHandlingExample {

    @Override
    public String getFileContent(final String filePath) {
        return Try.of(() -> new BufferedReader(new FileReader(filePath)))
                .map(bufferedReader -> bufferedReader.lines().collect(Collectors.joining("\n")))
                .getOrElse("");
    }

    @Override
    public void writeFileContent(final String filePath, final String fileContent) {
        Try.of(() -> new FileWriter(filePath))
                .andThenTry(fileWriter -> fileWriter.write(fileContent))
                .andThenTry(FileWriter::close);
    }

    @Override
    public Collection<Integer> getNumbers(final Collection<String> list) {
        return list.stream()
                .map(listElement -> Try.of(() -> Integer.parseInt(listElement)).toOption())
                .flatMap(Option::toJavaStream)
                .collect(Collectors.toList());
    }

}
