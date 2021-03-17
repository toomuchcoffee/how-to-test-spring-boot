package de.toomuchcoffee.httsb.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SomeServiceWithTransaction {
    private final SubServiceA subServiceA;
    private final SubServiceB subServiceB;

    @Transactional
    public void doStuff() {
        subServiceA.doA();
        subServiceB.doB();
    }
}
