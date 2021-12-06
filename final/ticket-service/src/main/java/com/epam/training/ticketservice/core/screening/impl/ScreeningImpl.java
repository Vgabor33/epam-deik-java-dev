package com.epam.training.ticketservice.core.screening.impl;

import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import com.epam.training.ticketservice.core.screening.persistence.entity.Screening;
import com.epam.training.ticketservice.core.screening.persistence.repository.ScreeningRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ScreeningImpl implements ScreeningService {
    private final ScreeningRepository screeningRepository;

    @Override
    public List<ScreeningDto> getScreeningList() {
        return screeningRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<ScreeningDto> getScreeningById(int id) {
        return convertEntityToDto(screeningRepository.findById(id));
    }

    @Override
    public void createScreening(ScreeningDto screeningDto) {
        Objects.requireNonNull(screeningDto, "Screening cannot be null");
        Objects.requireNonNull(screeningDto.getMovie(), "Screening: Movie name cannot be null");
        Objects.requireNonNull(screeningDto.getRoom(), "Screening: Room cannot be null");
        Objects.requireNonNull(screeningDto.getDate(), "Screening: Date cannot be null");
        Screening screening = new Screening(screeningDto.getMovie(),
                screeningDto.getRoom(),
                screeningDto.getDate());
        screeningRepository.save(screening);
    }

    private ScreeningDto convertEntityToDto(Screening screening) {
        return ScreeningDto.builder().room(screening.getRoom()).movie(screening.getMovie()).date(screening.getDate()).build();
    }

    private Optional<ScreeningDto> convertEntityToDto(Optional<Screening> screening) {
        return screening.isEmpty() ? Optional.empty() : Optional.of(convertEntityToDto(screening.get()));
    }
}
