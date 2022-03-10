package com.uh635c.task22.service;

import com.uh635c.task22.model.Writer;
import com.uh635c.task22.repository.WriterRepository;
import com.uh635c.task22.repository.WriterRepositoryImpl;

import java.util.List;

public class WriterService {
    WriterRepository writerRepository = new WriterRepositoryImpl();

    public List<Writer> getAll(){
        return writerRepository.getAll();
    }

    public Writer getById(Long id){
        return writerRepository.getById(id);
    }

    public Writer save(Writer writer){
        return writerRepository.save(writer);
    }

    public Writer update(Writer writer){
        return writerRepository.update(writer);
    }

    public void remove(Long id){
        writerRepository.remove(id);
    }
}
