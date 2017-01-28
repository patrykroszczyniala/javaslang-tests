package io.github.pepuch.exceptionhandling;

import java.util.Collection;

public interface ExceptionHandlingExample {

    String getFileContent(String filePath);

    void writeFileContent(String filePath, String fileContent);

    Collection<Integer> getNumbers(Collection<String> list);

}
