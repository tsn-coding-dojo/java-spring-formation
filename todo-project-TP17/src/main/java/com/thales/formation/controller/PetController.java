
package com.thales.formation.controller;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thales.formation.api.layer.prive.v1.PetApi;

@Transactional
@RestController
@RequestMapping("/api/pets")
public class PetController implements PetApi{

	
}
