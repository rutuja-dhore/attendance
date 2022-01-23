package com.enterprise.attendance.service;



import com.enterprise.attendance.dto.input.VendorInputDTO;
import com.enterprise.attendance.dto.output.VendorOutputDTO;

public interface VendorService {

	public VendorOutputDTO create(VendorInputDTO vendorInputDTO);

}
