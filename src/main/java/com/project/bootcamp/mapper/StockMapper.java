package com.project.bootcamp.mapper;

import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockMapper {

    public Stock toEntity(StockDTO dto) {
        Stock stock = new Stock();
        stock.setId(dto.getId());
        stock.setDate(dto.getDate());
        stock.setName(dto.getName());
        stock.setVariation(dto.getVariation());
        stock.setPrice(dto.getPrice());
        return stock;
    }

    public StockDTO toDto(Stock stock) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(stock.getId());
        stockDTO.setDate(stock.getDate());
        stockDTO.setName(stock.getName());
        stockDTO.setVariation(stock.getVariation());
        stockDTO.setPrice(stock.getPrice());
        return stockDTO;

    }
    public List<StockDTO> toDto(List<Stock> stock) {
        List<StockDTO> listaStocksDTO = new ArrayList<>();
        for (int i =0;i<stock.size();i++){
            listaStocksDTO.add(toDto(stock.get(i)));
        }
        return listaStocksDTO;

    }


}
