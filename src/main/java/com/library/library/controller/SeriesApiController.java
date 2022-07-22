package com.library.library.controller;

import com.library.library.dto.SeriesDto;
import com.library.library.dto.SeriesPutDto;
import com.library.library.dto.SeriesSlimDto;
import com.library.library.entity.Series;
import com.library.library.exception.SeriesNotFoundException;
import com.library.library.mapper.MapStructMapper;
import com.library.library.repository.SeriesRepository;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/series")
public class SeriesApiController {

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private MapStructMapper mapStructMapper;

    @PostMapping
    public ResponseEntity<SeriesDto> create(@Valid @RequestBody SeriesDto seriesDto) {
        return new ResponseEntity<>(
            mapStructMapper.seriesToSeriesDto(seriesRepository.save(mapStructMapper.seriesDtoToSeries(seriesDto))),
            HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<SeriesSlimDto>> index() {
        return new ResponseEntity<>(
            new ArrayList<>(mapStructMapper.seriesToSeriesSlimDtos(seriesRepository.findAll().stream().collect(Collectors.toSet()))),
            HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    ResponseEntity<SeriesDto> show(@PathVariable("id") String id) {
        return new ResponseEntity<>(
            mapStructMapper.seriesToSeriesDto(seriesRepository.findById(id).orElseThrow(() -> new SeriesNotFoundException(id))),
            HttpStatus.OK
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<SeriesDto> update(@Valid @RequestBody SeriesPutDto seriesDto, @PathVariable("id") String id) {
        Series series = seriesRepository.findById(id).orElseThrow(() -> new SeriesNotFoundException(id));
        if (seriesDto.getId() != null) {
            series.setId(seriesDto.getId());
        }
        if (seriesDto.getName() != null) {
            series.setName(seriesDto.getName());
        }
        if (seriesDto.getPlannedVolumes() != null) {
            series.setPlannedVolumes(seriesDto.getPlannedVolumes());
        }
        if (seriesDto.getBookTourEvents() != null) {
            series.setBookTourEvents(seriesDto.getBookTourEvents());
        }
        if (!seriesDto.getBooks().isEmpty()) {
            series.setBooks(mapStructMapper.mapBookIdsToBooks(seriesDto.getBooks()));
        }
        return new ResponseEntity<>(mapStructMapper.seriesToSeriesDto(seriesRepository.save(series)), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        Series series = seriesRepository.findById(id).orElseThrow(() -> new SeriesNotFoundException(id));
        seriesRepository.delete(series);
        return new ResponseEntity<>(true, HttpStatus.GONE);
    }
}
