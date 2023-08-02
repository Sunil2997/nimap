package com.bountyregister.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bountyregister.dto.ForgotPasswordConfirmDto;
import com.bountyregister.entities.OtpEntity;
import com.bountyregister.entities.UserEntity;
import com.bountyregister.exceptionHandling.ResourceNotFoundException;
import com.bountyregister.iListDto.IUserPermissionDto;
import com.bountyregister.repositories.AuthRepository;
import com.bountyregister.repositories.ErrorLoggerRepository;
import com.bountyregister.repositories.MailRepository;
import com.bountyregister.repositories.OTPRepository;
import com.bountyregister.repositories.RoleRepository;
import com.bountyregister.repositories.UserRepository;
import com.bountyregister.service.AuthInterface;
import com.bountyregister.service.OtpInterface;
import com.bountyregister.service.RolePermissionInterface;
import com.bountyregister.service.UserRoleInterface;
import com.bountyregister.utils.ErrorMessageCode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AuthServiceImpl implements AuthInterface, UserDetailsService {

	@Autowired
	private AuthRepository authRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RolePermissionInterface rolePermissionInterface;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private UserRoleInterface userRoleInterface;

	@Autowired
	private OTPRepository otpRepository;

//	@Autowired
//	private EmailInterface emailInterface;
	
//	@Autowired
//	private CacheOperation cacheOperation;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MailRepository mailRepository;

	@Autowired
	private OtpInterface otpInterface;

//	@Value("${url}")
//	private String frontendURL;

	@Autowired
	private ErrorLoggerRepository loggerRepository;

	@Autowired
	private UserRepository userRepository;
	
	
	private static final Logger LOG = LoggerFactory.getLogger(AuthServiceImpl.class);

//	@Override
//	public UserDto registerUser(UserDto userDto) throws Exception {
//
//		UserEntity userEmail = userRepository.findByEmail(userDto.getEmail());
//		if (userEmail != null) {
//			throw new ResourceNotFoundException(ErrorMessageCode.ALREADY_REGISTER);
//		}
//		for (int i = 0; i < userDto.getRoleId().size(); i++) {
//			final Long roleId = userDto.getRoleId().get(i);
//			this.roleRepository.findById(roleId)
//					.orElseThrow(() -> new ResourceNotFoundException(ErrorMessageCode.ROLE_NOT_FOUND));
//		}
//
//		UserEntity userEntity = new UserEntity();
//		userEntity.setName(userDto.getName());
//		if (Validator.isValidforEmail(userDto.getEmail())) {
//			userEntity.setEmail(userDto.getEmail());
//		} else {
//			throw new IllegalArgumentException(ErrorMessageCode.INVALID_EMAIL);
//		}
//		userEntity.setGender(userDto.getGender());
//		userEntity.setPhoneNumber(userDto.getPhoneNumber());
//		authRepository.save(userEntity);
//
//		AssignRole role = new AssignRole(userEntity.getId(), userDto.getRoleId());
//		userRoleInterface.add(role);
//
//		UUID uuid = UUID.randomUUID();
//
//		String url = frontendURL + uuid;
//
//		MailTemplate mailtemplate = mailRepository.findBytemplatename(Constant.ONBOARD_TEMPLATE_NAME);
//		if (null != mailtemplate) {
//			String template = mailtemplate.getMailtemp();
//			String replaceString = template.replace("USER_NAME", userEntity.getName()).replace("ONBOARDING_URL", url);
//
//			LOG.info("AuthServiceImpl >> registerUser() >>  Onboarding >>  " + userEntity.getEmail());
//			emailInterface.sendSimpleMessage(userEntity.getEmail(), "Onboarding - Welcome to GPL !!", replaceString);
//			LOG.info("AuthServiceImpl >> registerUser() >>  Onboarding >>  " + userEntity.getEmail());
//		} else {
//			throw new ResourceNotFoundException(ErrorMessageCode.MAILTEMPLATE_NOT_FOUND);
//		}
//
//		return userDto;
//	}

//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//		UserEntity userEntity = new UserEntity();
//
//		if (cacheOperation.isRedisConnectionClose() == false) {
//			userEntity = this.authRepository.findByEmailContainingIgnoreCase(email);
//			ErrorLoggerEntity entity = new ErrorLoggerEntity();
//			entity.setMessage(ErrorMessageCode.REDIS_CONNECTION_FAILED);
//			Date date = new Date(System.currentTimeMillis());
//			// Timestamp t=new Timestamp(d)
//			entity.setCreatedAt(date);
//			this.loggerRepository.save(entity);
//
//			return new org.springframework.security.core.userdetails.User(userEntity.getEmail(),
//					userEntity.getPassword(), getAuthority(userEntity));
//
//		}
//
//		if (!cacheOperation.isKeyExist(email, email)) {
//
//			userEntity = this.authRepository.findByEmailContainingIgnoreCase(email);
//			cacheOperation.addInCache(email, email, userEntity.toString());
//		} else {
//			try {
//
//				String jsonString = (String) cacheOperation.getFromCache(email, email);
//
//				Map<String, Object> map = objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {
//				});
//				userEntity.setPassword((String) map.get("password"));
//				userEntity.setEmail((String) map.get("email"));
//				userEntity.setId(((Integer) map.get("id")).longValue());
//
//				if (userEntity.getEmail().isEmpty()) {
//					throw new ResourceNotFoundException(ErrorMessageCode.USER_PASSWORD_NOT_FOUND);
//				}
//
//			} catch (Exception e) {
//				throw new ResourceNotFoundException(ErrorMessageCode.ENTER_VALID_INFORMATION);
//			}
//		}
//		return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(),
//				getAuthority(userEntity));
//	}

	// for compare password
	@Override
	public Boolean comparePassword(String password, String hashPassword) {

		return passwordEncoder.matches(password, hashPassword);

	}

	private ArrayList<SimpleGrantedAuthority> getAuthority(UserEntity user) {
		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

		if ((user.getId() + "permission") != null) {
			ArrayList<SimpleGrantedAuthority> authorities1 = new ArrayList<>();

			// ArrayList<String> permissions =
			// this.rolePermissionInterface.getPermissionByUserId(user.getId());

			// ArrayList<String> permissions = this.getPermissionByUser(user.getId());

			List<IUserPermissionDto> permissions = this.rolePermissionInterface.getPermissionsByUserId(user.getId());

			permissions.forEach(e -> {
				authorities1.add(new SimpleGrantedAuthority("ROLE_" + e.getpermissionName()));

			});

			authorities = authorities1;

		}
		return authorities;

	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public ArrayList<String> getPermissionByUser(Long userId) {
//
//		ArrayList<String> list;
//		if (!cacheOperation.isRedisConnectionClose()) {
//			list = this.rolePermissionInterface.getPermissionByUserId(userId);
//			return list;
//		}
//
//		if (!cacheOperation.isKeyExist(userId + "permission", userId + "permission")) {
//
//			list = this.rolePermissionInterface.getPermissionByUserId(userId);
//			this.cacheOperation.addInCache(userId + "permission", userId + "permission", list);
//
//		} else {
//			list = (ArrayList<String>) cacheOperation.getFromCache(userId + "permission", userId + "permission");
//
//		}
//
//		return list;
//	}

//	@Override
//	public ArrayList<String> getUserPermission(Long userId) throws IOException {
//		ArrayList<String> permissions;
////		permissions = this.rolePermissionInterface.getPermissionByUserId(userId);
//		permissions = this.getPermissionByUser(userId);
//		return permissions;
//
//	}

	@Override
	public Boolean updateUserWithPassword(ForgotPasswordConfirmDto payload) throws Exception {
		UserEntity userEntity = this.authRepository.findByEmailContainingIgnoreCase(payload.getEmail());

		if (null == userEntity) {
			throw new ResourceNotFoundException(ErrorMessageCode.INVALID_EMAIL);
		}

		OtpEntity otpEntity = this.otpRepository.findByOtp(payload.getOtp());
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());

		if (null == otpEntity) {
			throw new ResourceNotFoundException(ErrorMessageCode.INVALID_OTP);
		} else {
			if (!otpEntity.getEmail().equals(payload.getEmail()) && ts.compareTo(otpEntity.getExpireAt()) == -1) {
				throw new ResourceNotFoundException(ErrorMessageCode.INVALID_OTP);
			}
		}

		userEntity.setPasscode(passwordEncoder.encode(payload.getPassword()));
		this.authRepository.save(userEntity);
		this.otpRepository.delete(otpEntity);
		return true;

	}

//	@Override
//	public void generateOtpAndSendEmail(OTPDto otpDto, Long userId, String emailTemplate) throws Exception {
//
//		UserEntity userEntity = this.authRepository.findByEmailIgnoreCase(otpDto.getEmail());
//
//		if (userEntity == null) {
//			throw new ResourceNotFoundException(ErrorMessageCode.USER_NOT_PRESENT);
//		}
//
//		final int otp = emailInterface.generateOTP();
//
//		MailTemplate mailtemplate = mailRepository.findBytemplatename(Constant.OTP_TEMPLATE_NAME);
//
//		String otp1 = Integer.toString(otp);
//
//		String template = mailtemplate.getMailtemp();
//		String replaceString = template.replace("otp-alchemy", otp1);// replaces all occurrences of a to e
//
//		final String url = replaceString;
//		Calendar calender = Calendar.getInstance();
//		calender.add(Calendar.MINUTE, 5);
//
//		this.otpInterface.saveOtp(otpDto.getEmail(), otp1, userId, calender.getTime());
//
//		LOG.info("AuthServiceImpl >> generateOtpAndSendEmail() >>  OtpSend >>  " + otpDto.getEmail());
//		this.emailInterface.sendSimpleMessage(otpDto.getEmail(), "Reset your GPL password", url);
//		LOG.info("AuthServiceImpl >> generateOtpAndSendEmail() >>  OtpSend >>  " + otpDto.getEmail());
//
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public void saveGplEmployeeDetail(UserEntity userEntity) throws HttpClientErrorException, Exception {
//		MyResponse myResponse = gplApiIntegrationServiceImpl.gplApiResponseDetail(userEntity.getEmail());
//
//		if (myResponse != null) {
//
//			for (int i = 0; i < myResponse.getResp_data().size(); i++) {
//
//				GplApiResponceEntity gplApiResponceEntity = myResponse.getResp_data().get(i);
//
//				if (userEntity != null) {
//					userEntity.setName(gplApiResponceEntity.getFirstName() + " " + gplApiResponceEntity.getLastName());
//					userEntity.setEmployeeId(gplApiResponceEntity.getEmployeeId());
//					userEntity.setEmployeeGrade(gplApiResponceEntity.getSalaryGradeSalaryGradeName());
//
////					String region = gplApiResponceEntity.getEmpJobInfoTCustomVchar13().split("-")[1];
////					userEntity.setRegion(region);
////					 userEntity.setProject(gplApiResponceEntity.getEmpJobInfoTCustomVchar15());
//
//					userEntity.setRegion(gplApiResponceEntity.getSubBusinessUnitName());
//					userEntity.setPositionTitle(gplApiResponceEntity.getJobTitle());
//					userEntity.setZone(gplApiResponceEntity.getSubBusinessUnitName());
//
//					userRepository.save(userEntity);
//				}
//
//			}
//		}
////		else {
////
////			throw new ResourceNotFoundException("Invalid godrej user");
////		}
//	}
//	
//	@Override
//	public UserEntity saveNewEnrollEmployee(String email) throws HttpClientErrorException, Exception {
//		MyResponse myResponse = gplApiIntegrationServiceImpl.gplApiResponseDetail(email);
//		UserEntity userEntity= new UserEntity();
//		
//		if (myResponse != null) {
//		
//			for (int i = 0; i < myResponse.getResp_data().size(); i++) {
//
//				GplApiResponceEntity gplApiResponceEntity = myResponse.getResp_data().get(i);
//					userEntity.setEmail(email);
//					userEntity.setName(gplApiResponceEntity.getFirstName() + " " + gplApiResponceEntity.getLastName());
//					userEntity.setEmployeeId(gplApiResponceEntity.getEmployeeId());
//					userEntity.setEmployeeGrade(gplApiResponceEntity.getSalaryGradeSalaryGradeName());
//
//					userEntity.setRegion(gplApiResponceEntity.getSubBusinessUnitName());
//					userEntity.setPositionTitle(gplApiResponceEntity.getJobTitle());
//					userEntity.setZone(gplApiResponceEntity.getSubBusinessUnitName());
//
//					userRepository.save(userEntity);
////				}
//
//			}
//		}
//		return userEntity;
//
//	}
	
}
