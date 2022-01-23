package com.enterprise.attendance.controllers;

import com.enterprise.attendance.dto.input.VendorInputDTO;
import com.enterprise.attendance.dto.output.VendorOutputDTO;
import com.enterprise.attendance.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/vendors")
public class VendorController {

	@Autowired
	private VendorService vendorService;

	@PostMapping
	public VendorOutputDTO create(@RequestBody VendorInputDTO vendorInputDTO) {
		return vendorService.create(vendorInputDTO);
	}
}
