package com.example.projectsoftware.service.impl;

import com.example.projectsoftware.dto.DirectorDto;
import com.example.projectsoftware.mapper.DirectorMapper;
import com.example.projectsoftware.model.Director;
import com.example.projectsoftware.repository.DirectorRepository;
import com.example.projectsoftware.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    @Override
    public List<DirectorDto> getAll() {
        return directorMapper.toDtoList(directorRepository.findAll());
    }

    @Override
    public DirectorDto getById(Long id) {
        return directorRepository.findById(id)
                .map(directorMapper::toDto)
                .orElse(null);
    }

    @Override
    public DirectorDto addDirector(DirectorDto dto) {
        Director entity = directorMapper.toEntity(dto);
        Director saved = directorRepository.save(entity);
        return directorMapper.toDto(saved);
    }

    @Override
    public DirectorDto updateDirector(Long id, DirectorDto dto) {
        Director entity = directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Director not found with id: " + id));

        entity.setName(dto.getName());
        Director updated = directorRepository.save(entity);

        return directorMapper.toDto(updated);
    }

    @Override
    public boolean deleteDirector(Long id) {
        if (!directorRepository.existsById(id)) return false;
        directorRepository.deleteById(id);
        return true;
    }
}