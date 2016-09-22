package fenfa;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class MyTest {
	public static void main(String[] args) {
		try {
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			org.apache.cxf.endpoint.Client client = dcf.createClient("http://saas.ccqm.cn:8800/codeService/codeService?wsdl");
			Object[] obj = client.invoke("provideCustomerId","广东省","江门市","蓬江区","检测单位","测试单位123");
			for (int i = 0; i < obj.length; i++) {
				System.out.println(obj[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
