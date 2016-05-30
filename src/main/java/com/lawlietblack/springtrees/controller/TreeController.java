package com.lawlietblack.springtrees.controller;

import com.lawlietblack.springtrees.model.Tree;
import com.lawlietblack.springtrees.repository.PersonRepository;
import com.lawlietblack.springtrees.repository.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TreeController {
    private static List<Tree> allTrees = new ArrayList<>();

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TreeRepository treeRepository;

    @RequestMapping("/")
    public String index(ModelMap modelMap) {
        allTrees = (List<Tree>) treeRepository.findAll();
        modelMap.put("trees", allTrees);
        return "home";
    }

    @RequestMapping("/display/{id}")
    public String treeDetail(@PathVariable int id, ModelMap modelMap) {
        Tree tree = treeRepository.findOne(id);
        modelMap.put("tree", tree);
        return "tree";
    }

    @RequestMapping(value = "/data/trees/{id}", method = RequestMethod.GET, headers = {"Accept=application/json", "Access-Control-Allow-Origin:*"})
    public @ResponseBody Tree treeJson(@PathVariable int id, ModelMap modelMap) {
        return treeRepository.findOne(id);
    }
}
