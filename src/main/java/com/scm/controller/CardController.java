package com.scm.controller;

import com.scm.entity.*;
import com.scm.helper.ResourceNotFoundException;
import com.scm.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;


@Controller
public class CardController {
    @Autowired
    private  GrainRepository grainRepository;
    @Autowired
    private FruitRepository fruitRepository;
    @Autowired
    private VegetableRepository vegetableRepository;
    @Autowired
    private FlowerRepository flowerRepository;
    @Autowired
    private GrainDetailRepository grainDetailRepository;
    @Autowired
    private VegetableDetailRepository vegetableDetailRepository;
    @Autowired
    private FlowerDetailRepository flowerDetailRepository;
    @Autowired
    private FruitDetailRepository fruitDetailRepository;



//    Category Route
    @GetMapping("/category")
    public String showCategory(){
        return "category";
    }


//    Fruit Route
    @GetMapping("/fruit")
    public String showFruits(Model model) {
        List<Fruit> fruit = fruitRepository.findAll();
        model.addAttribute("fruit", fruit);
        return "fruit"; // Ensure this corresponds to the Thymeleaf template for fruits list
    }
//  Fruit Detail Route
    @GetMapping("/fruit/{id}")
    public String showFruitDetails(@PathVariable("id") Integer id, Model model) {
        Fruit fruit = fruitRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fruit not found"));
        List<FruitDetail> fruitDetails = fruitDetailRepository.findByFruitId(id);

        model.addAttribute("fruit", fruit);
        model.addAttribute("fruitDetails", fruitDetails);
        return "fruit_detail"; // Ensure this corresponds to the Thymeleaf template for fruit details
    }


    @GetMapping("/flower")
    public String showFlowers(Model model) {
        List<Flower> flowers = flowerRepository.findAll();
        model.addAttribute("flowers", flowers);
        return "flower"; // Ensure this corresponds to the Thymeleaf template for flowers list
    }

    @GetMapping("/flower/{id}")
    public String showFlowerDetails(@PathVariable("id") Integer id, Model model) {
        Flower flower = flowerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Flower not found"));
        List<FlowerDetail> flowerDetails = flowerDetailRepository.findByFlowerId(id);

        model.addAttribute("flower", flower);
        model.addAttribute("flowerDetails", flowerDetails);
        return "flower_detail"; // Ensure this corresponds to the Thymeleaf template for flower details
    }



    @GetMapping("/vegetable")
    public String showVegetables(Model model) {
        List<Vegetable> vegetables = vegetableRepository.findAll();

        model.addAttribute("vegetables", vegetables);
        return "vegetable";
    }

    @GetMapping("/vegetable/{id}")
    public String showVegetableDetail(@PathVariable("id") Integer id, Model model) {
        Vegetable vegetable = vegetableRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Grain not found"));
        List<VegetableDetail> vegetableDetails = vegetableDetailRepository.findByVegetableId(id);


        model.addAttribute("vegetable", vegetable);
        model.addAttribute("vegetableDetails", vegetableDetails);  // Ensure this is a List<VegetableDetail>
        return "vegetable_detail";
    }
    @GetMapping("/grain")
    public String showGrains(Model model) {
        List<Grain> grains = grainRepository.findAll();
        model.addAttribute("grains", grains);
        return "grain";
    }

    @GetMapping("/grain/{id}")
    public String showGrainDetails(@PathVariable("id") Integer id, Model model) {
        Grain grain = grainRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Grain not found"));
        List<GrainDetail> grainDetails = grainDetailRepository.findByGrainId(id);

        model.addAttribute("grain", grain);
        model.addAttribute("grainDetails", grainDetails);
        return "grain_detail";
    }


}
