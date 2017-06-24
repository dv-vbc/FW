package net.idealclover.java.fw.svc.eis.tax.impl;

import javax.xml.namespace.QName;
import net.idealclover.java.fw.svc.eis.tax.ITaxService;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("taxService")
public class TaxServiceImpl implements ITaxService {

    private static Log logger = LogFactory.getLog(TaxServiceImpl.class);

//	@Autowired
//	private TaxConfig taxConfig;
    @Value("#{taxws[ws_smsStatus_address]}")
    private String smsStatusAddr;
    @Value("#{taxws[ws_smsStatus_ns]}")
    private String smsStatusNs;
    @Value("#{taxws[ws_smsStatus_operation]}")
    private String smsStatusOpt;

    @Override
    public String checkConnect(String xml) {
        // TODO Auto-generated method stub
        String svcStr = smsStatusAddr;
        String nsStr = smsStatusNs;
        String optStr = smsStatusOpt;
        logger.info("调用接口：" + svcStr);
        logger.info("调用方法：" + optStr);
        logger.info("调用参数：" + xml);
        if (!StringUtils.hasText(xml)) {
            throw new RuntimeException("在请求中获取不到XML");
        }
        try {
            RPCServiceClient client = new RPCServiceClient();
            Options options = client.getOptions();
            EndpointReference target = new EndpointReference(svcStr);
            options.setTo(target);
//                        options.setTimeOutInMilliSeconds(5000);
//                        options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer(3000));
            options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer(3000));
            Object[] args = new Object[]{xml};
            Class[] classes = new Class[]{String.class};
            QName qn = new QName(nsStr, optStr);
            client.invokeBlocking(qn, args, classes);
        } catch (Exception e) {
//			e.printStackTrace();
            throw new RuntimeException("调用第三方接口失败", e);
        }

        return "";
    }

    /*private OorgDao oorgDao;

	public void setOorgDao(OorgDao oorgDao) {
		this.oorgDao = oorgDao;
	}

	private IndyUserDao indyUserDao;

	public void setIndyUserDao(IndyUserDao indyUserDao) {
		this.indyUserDao = indyUserDao;
	}

	private AinvPrintTaxDao ainvPrintTaxDao;

	public void setAinvPrintTaxDao(AinvPrintTaxDao ainvPrintTaxDao) {
		this.ainvPrintTaxDao = ainvPrintTaxDao;
	}

	@Override
	public String synchronizationOrganizationInfo(String xml) {
		String resultXml = "";
		String orgNo;
		Oorg org;
		if (!StringUtils.hasText(xml)) {
			throw new RuntimeException("在请求中获取不到XML");
		}
		try {
			StringReader reader = new StringReader(xml);
			OMXMLParserWrapper builder = OMXMLBuilderFactory
					.createOMBuilder(reader);
			OMDocument doc = builder.getDocument();// INFO
			OMElement omInfo = doc.getFirstChildWithName(new QName("INFO"));
			OMElement omBmid = omInfo.getFirstChildWithName(new QName("BMID"));
			orgNo = omBmid.getText();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("解析XML失败", e);
		}
		try {
			org = oorgDao.readOorgByNo(orgNo);
		} catch (EmptyResultDataAccessException erdae) {
			erdae.printStackTrace();
			throw new RuntimeException("单位不存在", erdae);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("业务处理失败", e);
		}
		try {
			// create a factory
			OMFactory factory = OMAbstractFactory.getOMFactory();
			OMDocument doc = factory.createOMDocument();
			// use the factory to create three elements
			OMElement omOrgobj = factory.createOMElement(new QName("ORGOBJ"));
			OMElement omOrgno = factory.createOMElement(new QName("ORGNO"));
			omOrgno.setText(org.getOrgNo());
			OMElement omOrgname = factory.createOMElement(new QName("ORGNAME"));
			omOrgname.setText(org.getOrgName());
			OMElement omPorgno = factory.createOMElement(new QName("PORGNO"));
			omPorgno.setText(org.getPorgNo());
			OMElement omOrgtype = factory.createOMElement(new QName("ORGTYPE"));
			omOrgtype.setText(org.getOrgType());
			OMElement omSortno = factory.createOMElement(new QName("SORTNO"));
			omSortno.setText(org.getSortNo());
			omOrgobj.addChild(omOrgno);
			omOrgobj.addChild(omOrgname);
			omOrgobj.addChild(omPorgno);
			omOrgobj.addChild(omOrgtype);
			omOrgobj.addChild(omSortno);
			doc.addChild(omOrgobj);
			StringWriter writer = new StringWriter();
			doc.serialize(writer);
			resultXml = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("生成XML失败", e);
		}
		return resultXml;
	}

	@Override
	public String synchronizationUserInfo(String xml) {
		String resultXml = "";
		String userName;
		IndyUser indyUser;
		if (!StringUtils.hasText(xml)) {
			throw new RuntimeException("在请求中获取不到XML");
		}
		try {
			StringReader reader = new StringReader(xml);
			OMXMLParserWrapper builder = OMXMLBuilderFactory
					.createOMBuilder(reader);
			OMDocument doc = builder.getDocument();// INFO
			OMElement omInfo = doc.getFirstChildWithName(new QName("INFO"));
			OMElement omYhid = omInfo.getFirstChildWithName(new QName("YHID"));
			userName = omYhid.getText();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("解析XML失败", e);
		}
		try {
			indyUser = indyUserDao.readIndyUserByName(userName);
		} catch (EmptyResultDataAccessException erdae) {
			erdae.printStackTrace();
			throw new RuntimeException("用户不存在", erdae);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("业务处理失败", e);
		}
		try {
			// create a factory
			OMFactory factory = OMAbstractFactory.getOMFactory();
			OMDocument doc = factory.createOMDocument();
			// use the factory to create three elements
			OMElement omUserobj = factory.createOMElement(new QName("USEROBJ"));
			OMElement omUsername = factory
					.createOMElement(new QName("USERNAME"));
			omUsername.setText(indyUser.getUserName());
			OMElement omUsertitle = factory.createOMElement(new QName(
					"USERTITLE"));
			omUsertitle.setText(indyUser.getUserTitle());
			OMElement omPassword = factory
					.createOMElement(new QName("PASSWORD"));
			omPassword.setText(indyUser.getUserPassword());
			OMElement omOrgno = factory.createOMElement(new QName("ORGNO"));
			omOrgno.setText(indyUser.getUserBureauName());
			OMElement omDeptno = factory.createOMElement(new QName("DEPTNO"));
			omDeptno.setText(indyUser.getUserDepartmentName());
			omUserobj.addChild(omUsername);
			omUserobj.addChild(omUsertitle);
			omUserobj.addChild(omPassword);
			omUserobj.addChild(omOrgno);
			omUserobj.addChild(omDeptno);
			doc.addChild(omUserobj);
			StringWriter writer = new StringWriter();
			doc.serialize(writer);
			resultXml = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("生成XML失败", e);
		}
		return resultXml;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String refreshInvoiceInfo(String xml) {
		List<AinvPrintTax> list = new ArrayList<AinvPrintTax>();
		if (!StringUtils.hasText(xml)) {
			throw new RuntimeException("在请求中获取不到XML");
		}
		try {
			StringReader reader = new StringReader(xml);
			OMXMLParserWrapper builder = OMXMLBuilderFactory
					.createOMBuilder(reader);
			OMDocument doc = builder.getDocument();// DATA
			OMElement omData = doc.getFirstChildWithName(new QName("DATA"));
			Iterator<OMElement> itomInv = omData.getChildElements();
			while (itomInv.hasNext()) {
				OMElement omInv = itomInv.next();
				if ("INVOICE".equals(omInv.getLocalName())) {
					AinvPrintTax inv = new AinvPrintTax();
					OMElement omXsdjbh = omInv.getFirstChildWithName(new QName(
							"XSDJBH"));
					String strXsdjbh = omXsdjbh.getText();
					OMElement omInfo = omInv.getFirstChildWithName(new QName(
							"INFO"));
					String strInfo = omInfo.getText();
					String[] strFp = StringUtils.split(strInfo, "@");
					String[] strPp = StringUtils.split(strFp[0], "_");
					String[] strZp = StringUtils.split(strFp[1], "_");
					inv.setInvMainId(strXsdjbh);
					inv.setTaxOrdinaryCode(strPp[0]);
					inv.setTaxOrdinaryNo(strPp[1]);
					inv.setTaxSpecifyCode(strZp[0]);
					inv.setTaxSpecifyNo(strZp[1]);
					list.add(inv);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("解析XML失败", e);
		}
		try {
			ainvPrintTaxDao.batupdateAinvPrintTax4Refresh(list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("业务处理失败", e);
		}
		return "";
	}

	@Override
	public String loginCheck(String xml) {
		String resultXml = "";
		String username;
		String password;
		String login = "FALSE";
		IndyUser indyUser;
		if (!StringUtils.hasText(xml)) {
			throw new RuntimeException("在请求中获取不到XML");
		}
		try {
			StringReader reader = new StringReader(xml);
			OMXMLParserWrapper builder = OMXMLBuilderFactory
					.createOMBuilder(reader);
			OMDocument doc = builder.getDocument();// INFO
			OMElement omUser = doc.getFirstChildWithName(new QName("USER"));
			OMElement omAcc = omUser
					.getFirstChildWithName(new QName("ACCOUNTS"));
			username = omAcc.getText();
			OMElement omPwd = omUser
					.getFirstChildWithName(new QName("PASSWORD"));
			password = omPwd.getText();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("解析XML失败", e);
		}
		try {
			indyUser = indyUserDao.readIndyUserByName(username);
			String pwdMark = indyUser.getUserPassword();
			boolean checkTag = SecurityUtil.isEncryptPassword(username,
					password, pwdMark);// 密码验证
			if (checkTag) {
				login = "TRUE";
			} else {
				login = "FALSE";
			}
		} catch (EmptyResultDataAccessException erdae) {
			// erdae.printStackTrace();
			// 用户不存在
			login = "FALSE";
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("业务处理失败", e);
		}
		try {
			// create a factory
			OMFactory factory = OMAbstractFactory.getOMFactory();
			OMDocument doc = factory.createOMDocument();
			// use the factory to create three elements
			OMElement omUser = factory.createOMElement(new QName("USER"));
			OMElement omLogin = factory.createOMElement(new QName("LOGIN"));
			omLogin.setText(login);
			omUser.addChild(omLogin);
			doc.addChild(omUser);
			StringWriter writer = new StringWriter();
			doc.serialize(writer);
			resultXml = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("生成XML失败", e);
		}
		return resultXml;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void invoiceForSingle(String xml) {
		String svcStr = taxConfig.getInvoiceForSingleAddr();
		String nsStr = taxConfig.getInvoiceForSingleNs();
		String optStr = taxConfig.getInvoiceForSingleOpt();
		logger.info("调用接口：" + svcStr);
		logger.info("调用方法：" + optStr);
		logger.info("调用参数：" + xml);
		if (!StringUtils.hasText(xml)) {
			throw new RuntimeException("在请求中获取不到XML");
		}
		try {
			RPCServiceClient client = new RPCServiceClient();
			Options options = client.getOptions();
			EndpointReference target = new EndpointReference(svcStr);
			options.setTo(target);
			Object[] args = new Object[] { xml };
			Class[] classes = new Class[] { String.class };
			QName qn = new QName(nsStr, optStr);
			client.invokeBlocking(qn, args, classes);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("调用税控接口失败", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void invoiceForMulti(String xml) {
		String svcStr = taxConfig.getInvoiceForMultiAddr();
		String nsStr = taxConfig.getInvoiceForMultiNs();
		String optStr = taxConfig.getInvoiceForMultiOpt();
		logger.info("调用接口：" + svcStr);
		logger.info("调用方法：" + optStr);
		logger.info("调用参数：" + xml);
		if (!StringUtils.hasText(xml)) {
			throw new RuntimeException("在请求中获取不到XML");
		}
		try {
			RPCServiceClient client = new RPCServiceClient();
			Options options = client.getOptions();
			EndpointReference target = new EndpointReference(svcStr);
			options.setTo(target);
			Object[] args = new Object[] { xml };
			Class[] classes = new Class[] { String.class };
			QName qn = new QName(nsStr, optStr);
			client.invokeBlocking(qn, args, classes);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("调用税控接口失败", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String invalidInvoice(String xml) {
		String resultXml = "";
		String svcStr = taxConfig.getInvalidInvoiceAddr();
		String nsStr = taxConfig.getInvalidInvoiceNs();
		String optStr = taxConfig.getInvalidInvoiceOpt();
		logger.info("调用接口：" + svcStr);
		logger.info("调用方法：" + optStr);
		logger.info("调用参数：" + xml);
		if (!StringUtils.hasText(xml)) {
			throw new RuntimeException("在请求中获取不到XML");
		}
		try {
			RPCServiceClient client = new RPCServiceClient();
			Options options = client.getOptions();
			EndpointReference target = new EndpointReference(svcStr);
			options.setTo(target);
			Object[] args = new Object[] { xml };
			Class[] classes = new Class[] { String.class };
			QName qn = new QName(nsStr, optStr);
			resultXml = client.invokeBlocking(qn, args, classes)[0].toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("调用税控接口失败", e);
		}
		return resultXml;
	}
     */
}
