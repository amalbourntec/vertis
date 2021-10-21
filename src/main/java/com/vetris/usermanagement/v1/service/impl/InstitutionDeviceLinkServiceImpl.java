package com.vetris.usermanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.entity.InstitutionDeviceLink;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.usermanagement.v1.dto.request.InstitutionDeviceLinkRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.usermanagement.v1.dto.response.InstitutionDeviceLinkResponseDTO;
import com.vetris.usermanagement.v1.exception.ResourceNotFoundException;
import com.vetris.usermanagement.v1.repository.InstitutionDeviceLinkRepository;
import com.vetris.usermanagement.v1.service.InstitutionDeviceLinkService;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Service Implementation for InstitutionDeviceLink
 * @author Aldrin
 *
 */
@Service
public class InstitutionDeviceLinkServiceImpl implements InstitutionDeviceLinkService{

	
	@Autowired
	private InstitutionDeviceLinkRepository institutionDeviceLinkRepository;
	
	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;
	
	@Autowired
	ObjectMapper objectMapper;
	/**
	 * Getting institution device link by id
	 */
	@Override
	public CommonResponseDTO getDeviceById(Integer id) throws Exception {
		
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionDeviceLink existingDevice=institutionDeviceLinkRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));
		
		    InstitutionDeviceLinkResponseDTO deviceRespDTO=objectMapper.convertValue(existingDevice, InstitutionDeviceLinkResponseDTO.class);
			resultDto.setStatus(StatusType.Success.toString());
			resultDto.setPayload(deviceRespDTO);
			resultDto.setMessage("Fetched device successfully");
		
		return resultDto;
	}
	/**
	 * Getting all institution device link
	 */
	@Override
	public CommonResponseDTO getAllDevice() throws Exception {
		
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionDeviceLink> institutionDeviceLinkList=institutionDeviceLinkRepository.findAll();
		List<InstitutionDeviceLinkResponseDTO> deviceRespDTO=new ArrayList<>();
		if(institutionDeviceLinkList.isEmpty()) {
			resultDto.setStatus(StatusType.Failure.toString());
			resultDto.setMessage("No device found");
		}else {
			institutionDeviceLinkList.stream()
			.forEach(device->{deviceRespDTO
				.add(objectMapper.convertValue(device, InstitutionDeviceLinkResponseDTO.class));
		});
		}
		resultDto.setStatus(StatusType.Success.toString());
		resultDto.setPayload(deviceRespDTO);
		resultDto.setMessage("Fetched list of device successfully");
		return resultDto;
	}
	/**
	 * Adding a institution device link using request DTO
	 */
	@Override
	public CommonResponseDTO addDevice(InstitutionDeviceLinkRequestDTO data) throws Exception {
		
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionDeviceLink device=objectMapper.convertValue(data, InstitutionDeviceLink.class);
		device.setCreatedBy(jwtSecurityContextUtil.getId());
		device=institutionDeviceLinkRepository.save(device);
		
		InstitutionDeviceLinkResponseDTO deviceRespDTO=objectMapper.convertValue(device, InstitutionDeviceLinkResponseDTO.class);
		resultDto.setStatus(StatusType.Success.toString());
		resultDto.setPayload(deviceRespDTO);
		resultDto.setMessage("Device added successfully");
		return resultDto;
	}
	/**
	 * updating the existing institution device link
	 */
	@Override
	public CommonResponseDTO updateDevice(InstitutionDeviceLinkRequestDTO data, Integer id) throws Exception {
		
		CommonResponseDTO resultDto = new CommonResponseDTO();
		institutionDeviceLinkRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));
			
				InstitutionDeviceLink device=objectMapper.convertValue(data, InstitutionDeviceLink.class);
				device.setDeviceId(id);
				device.setUpdateBy(jwtSecurityContextUtil.getId());
				device = institutionDeviceLinkRepository.save(device);
				
				InstitutionDeviceLinkResponseDTO deviceRespDTO=objectMapper.convertValue(device, InstitutionDeviceLinkResponseDTO.class);
				resultDto.setStatus(StatusType.Success.toString());
				resultDto.setPayload(deviceRespDTO);
				resultDto.setMessage("Device updated successfully");
			
		return resultDto;
	}
	/**
	 * Deleting the institution device link by device id
	 */
	@Override
	public CommonResponseDTO deleteDevice(Integer id) throws Exception {
		
		CommonResponseDTO resultDto = new CommonResponseDTO();
		institutionDeviceLinkRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));
		
			institutionDeviceLinkRepository.deleteById(id);
			resultDto.setStatus(StatusType.Success.toString());
			resultDto.setMessage("Device deleated successfully");
		
		return resultDto;
	}

}
