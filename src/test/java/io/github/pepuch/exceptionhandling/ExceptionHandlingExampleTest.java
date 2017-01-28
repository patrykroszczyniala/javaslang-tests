package io.github.pepuch.exceptionhandling;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(BlockJUnit4ClassRunner.class)
public class ExceptionHandlingExampleTest {

    private ExceptionHandlingExample withJavaslang = new ExceptionHandlingExampleWithJavaslang();
    private ExceptionHandlingExample withoutJavaslang = new ExceptionHandlingExampleWithoutJavaslang();

    @After
    public void after() {
        new File("test-file-with-javaslang").delete();
        new File("test-file-without-javaslang").delete();
    }

    @Test
    public void should_return_file_content_given_valid_path() {
        final String filePath = "src/test/resources/testfile.txt";

        assertThat(withJavaslang.getFileContent(filePath)).isEqualTo("line1\nline2");
        assertThat(withoutJavaslang.getFileContent(filePath)).isEqualTo("line1\nline2");
    }

    @Test
    public void should_return_empty_string_given_invalid_path() {
        final String filePath = "invalid-path";

        assertThat(withJavaslang.getFileContent(filePath)).isEqualTo("");
        assertThat(withoutJavaslang.getFileContent(filePath)).isEqualTo("");
    }

    @Test
    public void should_write_text_to_file() {
        final String fileContent = "file content";
        withJavaslang.writeFileContent("test-file-with-javaslang", fileContent);
        withoutJavaslang.writeFileContent("test-file-without-javaslang", fileContent);

        Assertions.assertThat(withJavaslang.getFileContent("test-file-with-javaslang")).isEqualTo(fileContent);
        Assertions.assertThat(withJavaslang.getFileContent("test-file-without-javaslang")).isEqualTo(fileContent);
    }

    @Test
    public void shold_return_numbers_given_list_with_nan() {
        final Collection<String> list = Arrays.asList("1", "a", "2", "b");

        assertThat(withJavaslang.getNumbers(list)).containsOnly(1, 2);
        assertThat(withoutJavaslang.getNumbers(list)).containsOnly(1, 2);
    }

}