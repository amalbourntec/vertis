package com.vetris.usermanagement.v1.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vetris.usermanagement.v1.dto.request.InstitutionDeviceLinkRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.usermanagement.v1.service.InstitutionDeviceLinkService;

/**
 * Controller for InstitutionDeviceLink
 * @author Aldrin
 *
 */

@RestController(value="InstitutionDeviceLinkController")
@RequestMapping("/v1/device")
@CrossOrigin(origins="*")
public class InstitutionDeviceLinkController {
	
	@Autowired
	InstitutionDeviceLinkService institutionDeviceLinkService;
	
	/**
	 * @param id
	 * @return institution device link
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> fetchDeviceById(@PathVariable("id") Integer id) throws Exception{	
		CommonResponseDTO deviceRespDTO=institutionDeviceLinkService.getDeviceById(id);
		return ResponseEntity.ok(deviceRespDTO); 
	}
	
	/**
	 * @return all institution device link
	 * @throws Exception
	 */
	@GetMapping("")
	public CommonResponseDTO fetchAllDevice() throws Exception{
		return institutionDeviceLinkService.getAllDevice();
	}
	
	/**
	 * @param deviceRequest
	 * @return String institution device link
	 * @throws Exception
	 */
	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createDevice(@RequestBody InstitutionDeviceLinkRequestDTO deviceRequest) throws Exception{
		CommonResponseDTO deviceRespDTO=institutionDeviceLinkService.addDevice(deviceRequest);
		return ResponseEntity.ok(deviceRespDTO);
	}
	
	/**
	 * @param deviceRequest
	 * @param id
	 * @return institution device link
	 * @throws Exception
	 */
	@PutMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> updateDevice(@RequestBody InstitutionDeviceLinkRequestDTO deviceRequest, @PathVariable("id") Integer id) throws Exception{
		CommonResponseDTO deviceRespDTO=institutionDeviceLinkService.updateDevice(deviceRequest, id);
		return ResponseEntity.ok(deviceRespDTO);
	}
	
	/**
	 * @param id
	 * @return string 
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> deleteDevice(@PathVariable("id") Integer id)throws Exception {
		CommonResponseDTO deviceRespDTO=institutionDeviceLinkService.deleteDevice(id);
		return ResponseEntity.ok(deviceRespDTO);
	}

}
