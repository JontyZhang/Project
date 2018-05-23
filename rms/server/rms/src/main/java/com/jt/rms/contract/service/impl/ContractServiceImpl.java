package com.jt.rms.contract.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.rms.common.constant.Code;
import com.jt.rms.common.util.Untils;
import com.jt.rms.common.util.exception.ServiceException;
import com.jt.rms.common.util.isnotnull.StringUtils;
import com.jt.rms.common.util.utils.IDCard;
import com.jt.rms.common.util.word.CreateWord;
import com.jt.rms.contract.dao.ContractMapper;
import com.jt.rms.contract.entity.ContractEntity;
import com.jt.rms.contract.service.ContractService;
import com.jt.rms.deposit.dao.DepositMapper;
import com.jt.rms.deposit.entity.DepositEntity;
import com.jt.rms.rent.dao.RentMapper;
import com.jt.rms.rent.entity.Rents;
import com.jt.rms.rentinghouse.entity.RentingHouse;
import com.jt.rms.rentinghouse.service.IRentingHouseService;
import com.jt.rms.tenant.dao.TenantMapper;
import com.jt.rms.tenant.entity.TenantEntity;
import com.jt.rms.user.dao.UserMapper;
import com.jt.rms.user.entity.User;

/**
 * 
 * @author :zjt
 * @time :2018年3月23日
 */
@Service("ContractService")
public class ContractServiceImpl implements ContractService {
	@Resource 
	ContractMapper contractMapper;
	@Resource
	DepositMapper depositMapper;
	@Resource
	UserMapper userMapper;
	@Resource	
	TenantMapper tenantMapper;
	@Resource 
	RentMapper rentMapepr;
	@Resource
	IRentingHouseService iRentingHouseService;
	/**
	 * 新增合同
	 */
	@Override
	public void addContract(ContractEntity params,User user) {
		//定义一些ID
		String rentId=Untils.getUUID("RENT_ID_");//设置租金详细表主键ID
		String tenantId = Untils.getUUID("TENANT_ID_");//生成客户主键ID
		String depositId = Untils.getUUID("DEPOSIT_ID_"); //生成压金单ID
		//新增押金单 如果是首签，则需要押金单，否则续签不需要押金单
		DepositEntity tempParams=params.getDeposit();
		if(StringUtils.isNotEmpty(params.getDeposit())){
			tempParams.setDepositId(depositId); //生成押金Id
			tempParams.setLandlordId(user.getUserId());//插入押金房东信息
			tempParams.setTenantId(params.getTenantName());
			tempParams.setCreateBy(user.getUserName());
			checkDepositEntity(tempParams);
			//押金单插入数据
			depositMapper.addDeposit(params.getDeposit());
		}
		//新增租金详细表内容		
		Rents tempRent = new Rents();
		tempRent = params.getRents();		
		tempRent.setRentId(rentId);
		tempRent.setHouseRent(params.getRent());
		tempRent.setTenantId(tenantId);
		tempRent.setHouseRent(params.getRent());
		checkRentDetail(tempRent);//检查租金详情 参数
		tempRent.setLandlordId(user.getUserId());	
		tempRent.setCreateBy(user.getUserName());
		rentMapepr.addRent(tempRent);
		checkContract(params);//校验合同数据
		//在合同表新增合同信息	
		String contractId = Untils.getUUID("CONTRACT_ID");//生成合同UUID		
		String contractNo= Untils.makeContractNO(params.getLandlordCID(),params.getTenantCID());//生成合同编号	
		params.setContractId(contractId);
		params.setContractNo(contractNo);
		params.setRentId(rentId);
		params.setCreateBy(user.getUserName());
		params.setDepositId(tempParams.getDepositId());
		contractMapper.addContract(params);//插入数据
		//新增客户
		TenantEntity tempTenant = new TenantEntity();		
		tempTenant.setTenantId(tenantId);//生成客户UUID		
		tempTenant.setLandlordId(user.getUserId());//房东ID
		tempTenant.setRentingHouseId(params.getRentingHouseId());//房子ID		
		tempTenant.setContractId(contractId);//合同ID		
		tempTenant.setTenantName(params.getTenantName());//客户姓名		
		tempTenant.setTenantCID(params.getTenantCID());//客户身份证		
		tempTenant.setTenantPhoneNum(params.getTenantTel());//客户电话号码
		tenantMapper.addTenant(tempTenant);
	}
	/**
	 * 批量查询合同
	 * 分页查询
	 */
	@Override
	public PageInfo<ContractEntity> queryContractBatch(Map<String, String> params) {
		PageHelper.startPage(Integer.parseInt(params.get("pageNum")),Integer.parseInt(params.get("pageSize")));
		List<ContractEntity> resutlList = contractMapper.queryContractBatch(params);
		PageInfo<ContractEntity> result = new PageInfo<ContractEntity>(resutlList);
		return result;
	}
	/**
	 * 根据UUID查询合同内容
	 */
	@Override
	public ContractEntity queryContract(ContractEntity params) {
		ContractEntity result = new ContractEntity();
		if(StringUtils.isNotEmpty(params.getContractId())){
			result = contractMapper.queryContract(params);
		}else{
			throw new ServiceException(Code.EMPTY_CONTRACT_ID.getErrorCode(), Code.EMPTY_CONTRACT_ID.getErrorMessage());
		}
		return result;
	}
	/**
	 * 批量删除合同
	 */
	@Override
	public void deleteContractBatch(List<ContractEntity> params) {
		if(StringUtils.isNotEmpty(params)){
			//不允许删除有效状态的合同
			for (ContractEntity contractEntity : params) {
				if(contractEntity.getContractState().equals("0")){
					throw new ServiceException(Code.EMPTY_PARAME.getErrorCode(), Code.EMPTY_DEPOSIT.getErrorMessage());
				}
			}
			contractMapper.deleteContractBatch(params);
		}else{
			throw new ServiceException(Code.EMPTY_PARAME.getErrorCode(), Code.EMPTY_DEPOSIT.getErrorMessage());
		}
	}
	/**
	 * 更新合同内容
	 */
	@Override
	public void updateContract(ContractEntity params,User user) {
		if(params.getContractState().equals("1")){
			throw new ServiceException(Code.ERROR_CONTRACT_STATE.getErrorCode(), Code.ERROR_CONTRACT_STATE.getErrorMessage());
		}

	}
	@Override
	public ContractEntity queryContractByRentingHouseId(String params) {
		ContractEntity result = null;
		result = contractMapper.queryContractByRentingHosueId(params);
		return result;
	}
	/**
	 * 检查押金数据问题
	 * @param params
	 */
	private  static void checkDepositEntity(DepositEntity params){
		if(StringUtils.isEmpty(params.getDeposit())){
			throw new ServiceException(Code.EMPTY_DEP_DEPOSIT.getErrorCode(),Code.EMPTY_DEP_DEPOSIT.getErrorMessage());
		}		
	}
	/**
	 * 检查合同数据问题
	 * @param params
	 */
	private static void checkContract(ContractEntity params){
		IDCard idCard =new IDCard();
		if(StringUtils.isEmpty(params.getLandlordName())){
			throw new ServiceException(Code.EMPTY_CONTRACT_L_NAME.getErrorCode(),Code.EMPTY_CONTRACT_L_NAME.getErrorMessage());
		}
		if(StringUtils.isEmpty(params.getLandlordCID())){
			throw new ServiceException(Code.EMPTY_CONTRACT_L_CID.getErrorCode(),Code.EMPTY_CONTRACT_L_CID.getErrorMessage());			
		}else{
			if(!idCard.verify(params.getLandlordCID())){
				throw new ServiceException(Code.ERROR_CONTRACT_L_CID.getErrorCode(), Code.ERROR_CONTRACT_L_CID.getErrorMessage());
			}
		}
		if(StringUtils.isEmpty(params.getTenantName())){
			throw new ServiceException(Code.EMPTY_CONTRACT_T_NAME.getErrorCode(),Code.EMPTY_CONTRACT_T_NAME.getErrorMessage());
		}	
		if(StringUtils.isEmpty(params.getTenantCID())){
			throw new ServiceException(Code.EMPTY_CONTRACT_T_CID.getErrorCode(),Code.EMPTY_CONTRACT_T_CID.getErrorMessage());
		}else{
			if(!idCard.verify(params.getTenantCID())){
				throw new ServiceException(Code.ERROR_CONTRACT_L_CID.getErrorCode(), Code.ERROR_CONTRACT_L_CID.getErrorMessage());
			}
		}
		if(StringUtils.isEmpty(params.getRent())){
			throw new ServiceException(Code.EMPTY_CONTRACT_RENT.getErrorCode(),Code.EMPTY_CONTRACT_RENT.getErrorMessage());
		}
		if(StringUtils.isEmpty(params.getRentFromDate())){
			throw new ServiceException(Code.EMPTY_CONTRACT_RENT_FROM_DATE.getErrorCode(),Code.EMPTY_CONTRACT_RENT_FROM_DATE.getErrorMessage());
		}	
		if(StringUtils.isEmpty(params.getRentToDate())){
			throw new ServiceException(Code.EMPTY_CONTRACT_RENT_TO_DATE.getErrorCode(),Code.EMPTY_CONTRACT_RENT_TO_DATE.getErrorMessage());
		}
	}
	//检查杂费详情
	private static void checkRentDetail(Rents params) {
		// 租金详情
		if(StringUtils.isEmpty(params.getHouseRent())){
			throw new ServiceException(Code.EMPTY_HOUSE_RENT.getErrorCode(), Code.EMPTY_HOUSE_RENT.getErrorMessage());
		}
	}
	@Override
	public void creatContractToWord(ContractEntity params, User user) {
		CreateWord utils = new CreateWord();
		RentingHouse rentingHouse = new RentingHouse();
		rentingHouse.setRentingHouseId(params.getRentingHouseId());
		RentingHouse temprentingHosue = iRentingHouseService.queryRentingHouse(rentingHouse);
		DepositEntity deposit = new DepositEntity();
		try {
			utils.createContract(params, rentingHouse, deposit, user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
