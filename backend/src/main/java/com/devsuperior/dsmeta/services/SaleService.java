package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService
{
	@Autowired
	private SaleRepository repository;
	
	public List<Sale> findSales()
	{
		return repository.findAll();
	}
	
	public Page<Sale> findSalesPageable(Pageable pageable)
	{
		return repository.findAll(pageable);
	}
	
	public Page<Sale> findSalesPageableForDate( String minDate, String maxDate, Pageable pageable)
	{
		LocalDate today = LocalDate.now();
		
		LocalDate min = maxDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate);
		LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);
		
		return repository.findSales(min, max, pageable);
	}

}
