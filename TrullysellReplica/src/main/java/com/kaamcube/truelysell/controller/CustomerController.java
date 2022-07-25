package com.kaamcube.truelysell.controller;

import com.kaamcube.truelysell.config.TokenProvider;
import com.kaamcube.truelysell.model.request.*;
import com.kaamcube.truelysell.model.responce.Response;
import com.kaamcube.truelysell.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/registerCustomer")
    private Response registerCustomer(@Valid @RequestBody RegistrationRequest customerRequest) {
        return customerService.registerCustomer(customerRequest);
    }

    @PostMapping("/otpValidation")
    public Response otpValidation(@Valid@RequestBody OtpRequest otpRequest ){
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        otpRequest.getMobileNo(),
                        otpRequest.getOtp()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        Response response = new Response("SUCCESS", "200", "Customer logged in Successfully", token);
        return response;
//        return customerService.otpValidation(otpRequest);
    }
    @PostMapping("/customerLogin")
    public Response customerLogin(@Valid@RequestBody LoginRequest loginRequest ){
        return customerService.customerLogin(loginRequest);
    }
    @GetMapping("/getCustomer")
    public Response updateCustomer(@Valid@RequestParam Long customerId){
        return customerService.getCustomer(customerId);
    }

    @PostMapping("/updateCustomer")
    public Response updateCustomer(@Valid@RequestBody VendorRequest customerRequest){
        return customerService.updateCustomer(customerRequest);
    }

    @PostMapping("/bookService")
    public Response bookService(@Valid@RequestBody BookServiceRequest bookServiceRequest){
        return customerService.bookService(bookServiceRequest);
    }

    @GetMapping("/getCustomerBooking")
    public Response getCustomerBooking(@Valid@RequestParam Long customerId){
        return customerService.getCustomerBooking(customerId);
    }

    @GetMapping("/getWalletAmount")
    public Response getWalletAmount(@Valid@RequestParam Long customerId){
        return customerService.getWalletAmount(customerId);
    }

    @PostMapping("/addWalletAmount")
    public Response addWalletAmount(@Valid@RequestBody AddWalletAmountRequest addWalletAmountRequest){
        return customerService.addWalletAmount(addWalletAmountRequest);
    }

    @GetMapping("/getWalletTransaction")
    public Response getWalletTransaction(@Valid@RequestParam Long customerId){
        return customerService.getWalletTransaction(customerId);
    }

}
