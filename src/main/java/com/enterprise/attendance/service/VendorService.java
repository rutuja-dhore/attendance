package com.enterprise.attendance.service;



import com.enterprise.attendance.dto.input.VendorInputDTO;
import com.enterprise.attendance.dto.output.VanOutputDTO;
import com.enterprise.attendance.dto.output.VendorOutputDTO;

import java.util.List;

public interface VendorService {

	public VendorOutputDTO create(VendorInputDTO vendorInputDTO);

	public VendorOutputDTO retrieveById(Integer id);

	public List<VendorOutputDTO> retrieveAll();

}
