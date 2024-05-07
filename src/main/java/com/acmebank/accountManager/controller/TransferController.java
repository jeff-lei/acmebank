package com.acmebank.accountManager.controller;

import com.acmebank.accountManager.api.model.TransferRequest;
import com.acmebank.accountManager.domain.Transaction;
import com.acmebank.accountManager.service.TransferService;
import com.acmebank.accountManager.utility.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class TransferController {
    private final TransferService transferService;

    @PostMapping(value = "/api/transfers/", produces = "application/json")
    public ResponseEntity<Void> createTransfer(@RequestBody TransferRequest transferRequest) {
        //validate request
        if (!Validator.isValidAccountFormat(transferRequest.getFromAccount())) return ResponseEntity.badRequest().build();
        if (!Validator.isValidAccountFormat(transferRequest.getToAccount())) return ResponseEntity.badRequest().build();

        try {
            Transaction newTransaction = transferService.createTransfer(
                    transferRequest.getFromAccount(),
                    transferRequest.getToAccount(),
                    transferRequest.getAmount()
            );
            //It can change to return HTTP 200 with a body content of Transfer summary if needed.
            //For simplicity only a HTTP 201 with the URI is returned
            return ResponseEntity.created(URI.create("/api/transfers/" + newTransaction.getId())
                    ).build();
        } catch (Exception e) {
            //Log exception message
            //a better way could be:
            //To create TransferException class, in here, catching TransferException and
            //return TransferException.getMessage() to the HTTP error response.
            return ResponseEntity.badRequest().build();
        }
        //Catching other exception here, logging the exception detials and return a generic error response
    }
}
