package ru.otus.original;

import lombok.AllArgsConstructor;
import ru.otus.annotations.Log;

import java.util.Arrays;
import java.util.stream.Collectors;

@AllArgsConstructor
public class StringUtilsImpl implements StringUtils {
    @Log
    @Override
    public String concat(String string, int... ints) {
        return string + Arrays.stream(ints)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }

}
