package com.bountyregister.service;

import java.util.List;

import javax.validation.Valid;

public interface SystemConfigurationInterface {
//	public void updateCareerAspirationFlag(ConfigPatchDto dto);

//	public void addPdf(MultipartFile file, HttpServletRequest request, String key) throws Exception;

//	public List<IListFooterDocumentDto> getAllPdf();

	public void updateFlag(@Valid String key, Boolean flag);

//	public void addFooterText(@Valid FooterTextDto footerTextDto);

	public void showBannerValidation();

}
