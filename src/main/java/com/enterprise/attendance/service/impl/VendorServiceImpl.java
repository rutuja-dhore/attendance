package com.enterprise.attendance.service.impl;

import com.enterprise.attendance.dao.VendorDAO;
import com.enterprise.attendance.dto.input.VendorInputDTO;
import com.enterprise.attendance.dto.output.VendorOutputDTO;
import com.enterprise.attendance.model.Vendor;
import com.enterprise.attendance.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorDAO vendorDAO;

	@Override
	public VendorOutputDTO create(VendorInputDTO vendorInputDTO) {
		Vendor vendor = new Vendor();
		vendor.setName(vendorInputDTO.getName());
		vendor = vendorDAO.save(vendor);
		return createResponseDTO(vendor);
	}

	private VendorOutputDTO createResponseDTO(Vendor vendor) {
		VendorOutputDTO outputDTO = new VendorOutputDTO();
		outputDTO.setId(vendor.getId());
		outputDTO.setName(vendor.getName());
		return outputDTO;
	}

}
