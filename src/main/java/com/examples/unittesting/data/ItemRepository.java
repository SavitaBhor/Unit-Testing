package com.examples.unittesting.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examples.unittesting.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
