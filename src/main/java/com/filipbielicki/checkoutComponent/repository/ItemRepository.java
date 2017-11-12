package com.filipbielicki.checkoutComponent.repository;

import com.filipbielicki.checkoutComponent.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findItemById(Long id);
    List<Item> findAll();
}
