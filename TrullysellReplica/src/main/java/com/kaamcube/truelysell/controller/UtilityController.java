package com.kaamcube.truelysell.controller;

import com.kaamcube.truelysell.model.request.AddSubscriptionsRequest;
import com.kaamcube.truelysell.model.request.CategoryRequest;
import com.kaamcube.truelysell.model.request.SubCategoryRequest;
import com.kaamcube.truelysell.model.responce.Response;
import com.kaamcube.truelysell.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UtilityController {

    @Autowired
    private UtilityService utilityService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addSubscriptionPlans")
    public Response addSubscriptionPlans(@Valid @RequestBody AddSubscriptionsRequest addSubscriptionsRequest){
        return utilityService.addSubscriptionPlans(addSubscriptionsRequest);
    }

    @PreAuthorize("hasAnyRole('VENDOR','ADMIN')")
    @GetMapping("/getSubscriptionPlans")
    public Response getSubscriptionPlans(){
        return utilityService.getSubscriptionsPlan();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addCategory")
    public Response addCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        return utilityService.addCategory(categoryRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addSubCategory")
    public Response addSubCategory(@Valid @RequestBody SubCategoryRequest subCategoryRequest){
        return utilityService.addSubCategory(subCategoryRequest);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER', 'VENDOR')")
    @GetMapping ("/getAllCategory")
    public Response getAllCategory(){
        return utilityService.getAllCategory();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER', 'VENDOR')")
    @GetMapping ("/getSubCategoryByCategoryId")
    public Response getSubCategoryByCategoryId(@Valid @RequestParam Long id){
        return utilityService.getSubCategoryByCategoryId(id);
    }
}
