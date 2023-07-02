package org.example.original;

import lombok.AllArgsConstructor;
import org.example.aspectj.LogAspectJ;
import java.util.Arrays;
import java.util.stream.Collectors;

@AllArgsConstructor
public class StringUtilsImpl implements StringUtils {
    @LogAspectJ
    @Override
    public String concat(String string, int... ints) {
        return string + Arrays.stream(ints)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }

}
