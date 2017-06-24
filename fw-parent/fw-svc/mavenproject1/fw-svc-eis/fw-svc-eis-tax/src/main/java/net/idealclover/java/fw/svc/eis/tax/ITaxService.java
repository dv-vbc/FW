package net.idealclover.java.fw.svc.eis.tax;


public interface ITaxService {
	
	public String checkConnect(String xml);
	
/*	*//**-------------------------------------------------------------------------
	 * 营销系统发起调用 税控系统提供服务
	 *------------------------------------------------------------------------*//*
	*//**
	 * 单户电费单数据接口
	 * @param xml
	 *//*
	public void invoiceForSingle(String xml);
	*//**
	 * 批户电费单数据接口
	 * @param xml
	 *//*
	public void invoiceForMulti(String xml);
	*//**
	 * 发票作废接口
	 * @param xml
	 * @return
	 *//*
	public String invalidInvoice(String xml);
	
	*//**-------------------------------------------------------------------------
	 * 税控系统发起调用 营销系统提供服务
	 *------------------------------------------------------------------------*//*
	*//**
	 * 机构数据同步接口
	 * @param xml
	 * @return
	 *//*
	public String synchronizationOrganizationInfo(String xml);
	*//**
	 * 用户数据同步接口
	 * @param xml
	 * @return
	 *//*
	public String synchronizationUserInfo(String xml);
	*//**
	 * 开票信息返回
	 * @param xml
	 *//*
	public String refreshInvoiceInfo(String xml);
	*//**
	 * 登录校验
	 * @param xml
	 * @return
	 *//*
	public String loginCheck(String xml);*/
	
}
