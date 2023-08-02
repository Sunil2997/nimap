package com.bountyregister.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bountyregister.dto.CityMasterDto;
import com.bountyregister.dto.DeleteId;
import com.bountyregister.entities.CityMasterEntity;
import com.bountyregister.exceptionHandling.ResourceNotFoundException;
import com.bountyregister.iListDto.IListCityMaster;
import com.bountyregister.repositories.CityMasterRepository;
import com.bountyregister.service.CityMasterInterface;
import com.bountyregister.utils.Constant;
import com.bountyregister.utils.ErrorMessageCode;
import com.bountyregister.utils.Pagination;

@Service
public class CityMasterServiceImpl implements CityMasterInterface {

	@Autowired
	private CityMasterRepository cityMasterRepository;

	@Override
	public CityMasterDto addCity(CityMasterDto cityMasterDto, Long userId) {

		CityMasterEntity city = this.cityMasterRepository.findByCityIgnoreCaseAndIsActiveTrue(cityMasterDto.getCity());

		if (city != null) {
			throw new ResourceNotFoundException("City already present");
		}

		CityMasterEntity cityMasterEntity = new CityMasterEntity();
		cityMasterEntity.setCity(cityMasterDto.getCity());
		cityMasterEntity.setCreatedBy(userId);
		cityMasterRepository.save(cityMasterEntity);
		return cityMasterDto;
	}

	@Override
	public CityMasterDto editCity(Long id, CityMasterDto cityMasterDto, Long userId) {

		CityMasterEntity cityMasterEntity = this.cityMasterRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessageCode.CITY_NOT_PRESENT));
		CityMasterEntity city = this.cityMasterRepository.findByCityIgnoreCaseAndIsActiveTrue(cityMasterDto.getCity());

		if (city != null) {
			if (city.getId() != cityMasterEntity.getId()) {
				throw new ResourceNotFoundException("City already present.");
			}
		}

		cityMasterEntity.setCity(cityMasterDto.getCity());
		cityMasterEntity.setUpdatedBy(userId);
		cityMasterRepository.save(cityMasterEntity);
		return cityMasterDto;
	}

	@Override
	public void deleteCity(Long id) {
		CityMasterEntity cityMasterEntity = this.cityMasterRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessageCode.CITY_NOT_PRESENT));
		cityMasterEntity.setIsActive(false);
		this.cityMasterRepository.save(cityMasterEntity);

	}

	@Override
	public Page<IListCityMaster> getAllCities(String search, String pageNo, String pageSize) throws Exception {

		Page<IListCityMaster> iListCities;

		String pageNumber = pageNo.isBlank() ? Constant.DEFAULT_PAGENUMBER : pageNo;
		String pages = pageSize.isBlank() ? Constant.DEFAULT_PAGESIZE : pageSize;
		Pageable pageable = new Pagination().getPagination(pageNumber, pages);
		if (pageNo.isBlank() && pageSize.isBlank()) {
			pageable = Pageable.unpaged();
		}

		iListCities = this.cityMasterRepository.findByCityContainingIgnoreCaseOrderByIdDesc(search, pageable,
				IListCityMaster.class);
		return iListCities;
	}

	@Override
	public void deleteMultipleCities(DeleteId id, Long userId) {
		List<CityMasterEntity> ids = this.cityMasterRepository.findAllById(id.getIds());
		if (ids.size() != id.getIds().size()) {
			throw new IllegalArgumentException("City ids not found");
		}

		for (int i = 0; i < ids.size(); i++) {
			ids.get(i).setIsActive(false);
			ids.get(i).setUpdatedBy(userId);
		}
		this.cityMasterRepository.saveAll(ids);
	}

}
