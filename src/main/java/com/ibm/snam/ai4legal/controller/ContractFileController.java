package com.ibm.snam.ai4legal.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.snam.ai4legal.model.ContractFile;
import com.ibm.snam.ai4legal.repositories.ContractFileRepository;

@RestController
@CrossOrigin(origins = "*")
public class ContractFileController {
	
	@Autowired
	ContractFileRepository contractFileRepository;
	
	@GetMapping("/getContractFile")
	public ContractFile getContractFile(@RequestParam("idContract") int idContract){
		Optional<ContractFile> contractFile = contractFileRepository.findById(idContract);
		return contractFile.isPresent() ? contractFile.get() : null;
	}
	
}
