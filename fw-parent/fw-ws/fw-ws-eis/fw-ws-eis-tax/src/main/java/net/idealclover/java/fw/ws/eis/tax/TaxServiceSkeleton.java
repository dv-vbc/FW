/**
 * TaxServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.3  Built on : May 30, 2016 (04:08:57 BST)
 */
package net.idealclover.java.fw.ws.eis.tax;

import net.idealclover.java.fw.svc.eis.tax.ITaxService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//import com.forsaven.eis.tax.service.TaxService;
/**
 * TaxServiceSkeleton java skeleton for the axisService
 */
@Controller("taxServiceSkeleton")
public class TaxServiceSkeleton {

    private static Log logger = LogFactory.getLog(TaxServiceSkeleton.class);

    @Autowired
    private ITaxService taxService;

    public void setTaxService(ITaxService taxService) {
        this.taxService = taxService;
    }

    /**
     * Auto generated method signature
     *
     * @param xml
     * @return resultXml
     */
    public String checkConnect(String xml) {
        String resultXml = "";
        logger.info("服务接口：TaxService#checkConnect");
        logger.info("接收信息：" + xml);
        try {
            resultXml = taxService.checkConnect(xml);
        } catch (Exception e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
        logger.info("发送信息：" + resultXml);
        return resultXml;
    }

    /*	*/
    /**
     * Auto generated method signature
     *
     * @param xml
     * @return resultXml
     *//*
	public String synchronizationOrganizationInfo(String xml) {
		String resultXml = "";
		logger.info("服务接口：TaxService#synchronizationOrganizationInfo");
		logger.info("接收信息：" + xml);
		try {
			resultXml = taxService.synchronizationOrganizationInfo(xml);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		logger.info("发送信息：" + resultXml);
		return resultXml;
	}

     */
    /**
     * Auto generated method signature
     *
     * @param xml
     * @return resultXml
     *//*
	public String synchronizationUserInfo(String xml) {
		String resultXml = "";
		logger.info("服务接口：TaxService#synchronizationUserInfo");
		logger.info("接收信息：" + xml);
		try {
			resultXml = taxService.synchronizationUserInfo(xml);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		logger.info("发送信息：" + resultXml);
		return resultXml;
	}

     */
    /**
     * Auto generated method signature
     *
     * @param xml
     * @return
     *//*
	public String refreshInvoiceInfo(String xml) {
		String resultXml = "";
		logger.info("服务接口：TaxService#refreshInvoiceInfo");
		logger.info("接收信息：" + xml);
		try {
			resultXml = taxService.refreshInvoiceInfo(xml);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		logger.info("发送信息：" + resultXml);
		return resultXml;
	}

     */
    /**
     * Auto generated method signature
     *
     * @param xml
     * @return resultXml
     *//*
	public String loginCheck(String xml) {
		String resultXml = "";
		logger.info("服务接口：TaxService#loginCheck");
		logger.info("接收信息：" + xml);
		try {
			resultXml = taxService.loginCheck(xml);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		logger.info("发送信息：" + resultXml);
		return resultXml;
	}

     */
    /**
     * Auto generated method signature
     *
     * @param xml
     * @return
     *//*
	public void invoiceForSingle(String xml) {
		logger.info("服务接口：TaxService#invoiceForSingle");
		logger.info("接收信息：" + xml);
		try {
			taxService.invoiceForSingle(xml);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		logger.info("发送信息：无反馈信息");
	}

     */
    /**
     * Auto generated method signature
     *
     * @param xml
     * @return
     *//*
	public void invoiceForMulti(String xml) {
		logger.info("服务接口：TaxService#invoiceForMulti");
		logger.info("接收信息：" + xml);
		try {
			taxService.invoiceForMulti(xml);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		logger.info("发送信息：无反馈信息");
	}

     */
    /**
     * Auto generated method signature
     *
     * @param xml
     * @return resultXml
     *//*
	public String invalidInvoice(String xml) {
		String resultXml = "";
		logger.info("服务接口：TaxService#invalidInvoice");
		logger.info("接收信息：" + xml);
		try {
			resultXml = taxService.invalidInvoice(xml);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		logger.info("发送信息：" + resultXml);
		return resultXml;
	}*/

}
