package ru.otus.serialization.model.converters;

import ru.otus.serialization.model.input.Member;
import ru.otus.serialization.model.output.MemberOutput;

import java.util.function.Function;

public class MemberConverter implements Function<Member, MemberOutput> {

    @Override
    public MemberOutput apply(Member member) {
        return new MemberOutput(member.last());
    }
}
