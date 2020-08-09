package guru.springframework.services;

import java.util.Set;

public interface BaseService <T,S> {
    T saveCommand(T command);
    T findCommandById(Long l);
    S findById(Long l);
    Set<S> getAll();
}
