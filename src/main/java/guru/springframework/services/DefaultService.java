package guru.springframework.services;

import java.util.Set;

public interface DefaultService<T,S> {
    T saveCommand(T command);
    T findCommandById(Long l);
    S findById(Long l);
    Set<S> getAll();
}
