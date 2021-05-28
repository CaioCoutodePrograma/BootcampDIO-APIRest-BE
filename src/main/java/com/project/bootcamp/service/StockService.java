package com.project.bootcamp.service;

import com.project.bootcamp.exceptions.BusinessException;
import com.project.bootcamp.exceptions.ExeptionResponse;
import com.project.bootcamp.exceptions.NotFoundException;
import com.project.bootcamp.mapper.StockMapper;
import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.repository.StockRepository;
import com.project.bootcamp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private StockRepository repository;
    @Autowired
    private StockMapper mapper;

    @Transactional
    public StockDTO save(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(), dto.getDate());
        if (optionalStock.isPresent()) {
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }
        Stock stock = mapper.toEntity(dto);
        repository.save(stock);

        return mapper.toDto(stock);
    }

    @Transactional
    public StockDTO update(StockDTO dto) {

        Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getName(), dto.getDate(),dto.getId());
        if (optionalStock.isPresent()) {
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }
        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDto(stock);
    }

    @Transactional(readOnly = true)
    public StockDTO findById(Long id) {
        Optional<Stock> optionalStock = repository.findById(id);
        if (!optionalStock.isPresent()) {
            throw new NotFoundException(MessageUtils.STOCK_NOT_EXISTS);
        }
        return mapper.toDto(optionalStock.get());
    }
    @Transactional(readOnly = true)
    public List<StockDTO> findAll(){
        return mapper.toDto(repository.findAll());
    }
    @Transactional
    public StockDTO deleteById(Long id) {
        StockDTO stockDTO = this.findById(id);
        repository.deleteById(stockDTO.getId());
        return stockDTO;
    }
    @Transactional
    public List<StockDTO> findToday() {
        List<StockDTO> stockDTOS = repository.findByDate(LocalDate.now()).map(mapper::toDto).orElseThrow(() -> new NotFoundException(""));
        return stockDTOS;
    }

}