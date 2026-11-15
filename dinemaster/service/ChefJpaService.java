package com.example.dinemaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.dinemaster.model.*;
import com.example.dinemaster.repository.*;

@Service
public class ChefJpaService implements ChefRepository {
    @Autowired
    private ChefJpaRepository chefJpaRepository;

    @Autowired
    private RestaurantJpaRepository restaurantJpaRepository;

    @Override
    public ArrayList<Chef> getChefs() {
        return new ArrayList<>(chefJpaRepository.findAll());
    }

    @Override
    public Chef getChefById(int chefId) {
        try {
            return chefJpaRepository.findById(chefId).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Chef addChef(Chef chef) {
        Restaurant restaurant = chef.getRestaurant();
        int restaurantId = restaurant.getId();
        try {
            Restaurant completeRestaurant = restaurantJpaRepository.findById(restaurantId).get();
            chef.setRestaurant(completeRestaurant);
            return chefJpaRepository.save(chef);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Chef updateChef(int chefId, Chef chef) {
        try {
            Chef newChef = chefJpaRepository.findById(chefId).get();
            if (chef.getFirstName() != null) newChef.setFirstName(chef.getFirstName());
            if (chef.getLastName() != null) newChef.setLastName(chef.getLastName());
            if (chef.getExpertise() != null) newChef.setExpertise(chef.getExpertise());
            if (chef.getExperienceYears() != 0) newChef.setExperienceYears(chef.getExperienceYears());
            if (chef.getRestaurant() != null) {
                int restaurantId = chef.getRestaurant().getId();
                Restaurant completeRestaurant = restaurantJpaRepository.findById(restaurantId).get();
                newChef.setRestaurant(completeRestaurant);
            }
            return chefJpaRepository.save(newChef);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteChef(int chefId) {
        try {
            chefJpaRepository.deleteById(chefId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public Restaurant getChefRestaurant(int chefId) {
        try {
            Chef chef = chefJpaRepository.findById(chefId).get();
            return chef.getRestaurant();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
