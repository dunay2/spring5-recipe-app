package guru.springframework.services;

import guru.springframework.commands.BaseCommand;
import guru.springframework.domain.BaseDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;


public class BaseServiceImpl implements BaseService<BaseCommand, BaseDomain> {

    @Autowired
    private CrudRepository repository;

    @Override
    public BaseCommand saveCommand(BaseCommand command) {
        return null;
    }

    @Override
    public BaseCommand findCommandById(Long l) {
        return null;
    }

    @Override
    public BaseDomain findById(Long l) {
        Optional<BaseDomain> object= repository.findById(l);
        if(!object.isPresent()) {
            throw new RuntimeException("Recipe not found!");
        }
        return object.get();
    }


    @Override
    public Set<BaseDomain> getAll() {
        return null;
    }

}
