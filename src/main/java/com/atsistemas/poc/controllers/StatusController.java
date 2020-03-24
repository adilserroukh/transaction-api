package com.atsistemas.poc.controllers;

import com.atsistemas.generated.api.StatusApi;
import com.atsistemas.generated.model.StatusDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StatusController implements StatusApi {


    @Override
    public ResponseEntity<StatusDto> getTransactionsByIban(String accountIban) {
        StatusDto tra= new StatusDto();
        tra.setStatus(StatusDto.StatusEnum.INVALID);
        return ResponseEntity.ok(tra);
    }
}
