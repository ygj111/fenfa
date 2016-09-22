package com.hhh.service.fenfa;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hhh.dao.entity.fenfa.SsApp;
import com.hhh.dao.entity.fenfa.SsAppVer;
import com.hhh.dao.entity.fenfa.SsService;
import com.hhh.dao.entity.fenfa.SsVersService;
import com.hhh.dao.fenfa.SsAppDao;
import com.hhh.dao.fenfa.SsAppVerDao;
import com.hhh.dao.fenfa.SsServiceDao;
import com.hhh.dao.fenfa.SsVersServiceDao;
import com.hhh.web.model.fenfa.SsAppBean;
import com.hhh.web.model.fenfa.SsServiceBean;

import jxl.Sheet;
import jxl.Workbook;

@Component
@Transactional
public class SsServiceService {
	private final static Logger logger = LoggerFactory.getLogger(SsServiceService.class);
	@Autowired
	private SsAppDao appDao;
	@Autowired
	private SsServiceDao serviceDao;
	@Autowired
	private SsVersServiceDao ssVersServiceDao;
	
	@Autowired
	private SsAppVerDao ssAppVerDao;
	
	/**
	 * 根据产品名称查询服务（分页查询）
	 * @param appname
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@Transactional(propagation =Propagation.NOT_SUPPORTED, readOnly=true)
	public List<SsServiceBean> findByAppname(String appname, int pageNum, int pageSize) throws IllegalAccessException, InvocationTargetException {
		PageRequest pageReq = new PageRequest(pageNum - 1, pageSize);
		Page<SsService> services = serviceDao.findBySsAppAppname(appname, pageReq);
		
		List<SsService> serviceList = services.getContent();
		List<SsServiceBean> serviceBeanList = new ArrayList<SsServiceBean>();
		
		SsService service = null;
		SsServiceBean serviceBean = null;
		
		for (int i = 0; i < serviceList.size(); i++) {
			service = serviceList.get(i);
			serviceBean = new SsServiceBean();
			
			BeanUtils.copyProperties(service, serviceBean);
			
			serviceBeanList.add(serviceBean);
		}
		
		return serviceBeanList;
	}
	
	/**
	 * 分页获取产品下的服务，并将分页信息传递到Controller
	 * @param appname
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Transactional(propagation =Propagation.NOT_SUPPORTED, readOnly=true)
	public Page<SsService> findServicesByAppname(String appname, int pageNum, int pageSize) {
		PageRequest pageReq = new PageRequest(pageNum - 1, pageSize);
		Page<SsService> services = serviceDao.findBySsAppAppname(appname, pageReq);
		return services;
	}
	
	/**
	 * 根据产品名称查询服务（不带分页）
	 * @param appname
	 * @return
	 */
	@Transactional(propagation =Propagation.NOT_SUPPORTED, readOnly=true)
	public List<SsServiceBean> findByappname(String appname) {
		SsApp app = appDao.findByappname(appname);
		List<SsServiceBean>ssServiceList=new ArrayList<SsServiceBean>();
		if (app != null) {
			
			List<SsService> list=app.getSsServices();
			
			for (int i =0; i < list.size(); i++) {
				SsService ssService=new SsService();
				ssService = list.get(i);
				SsServiceBean ssServiceBean = new SsServiceBean();
				ssServiceBean.setId(ssService.getId());
				ssServiceBean.setCustomerId(ssService.getCustomerId());
				ssServiceBean.setPrice(ssService.getPrice());
				ssServiceBean.setSeq(ssService.getSeq());
				ssServiceBean.setLabel(ssService.getLabel());
				ssServiceBean.setPid(ssService.getPid());
				ssServiceBean.setRealId(ssService.getRealId());
				ssServiceBean.setRemark(ssService.getRemark());
				SsAppBean ssAppBean = new SsAppBean();
				try {
					BeanUtils.copyProperties(ssAppBean, ssService.getSsApp());
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ssServiceBean.setSsAppBean(ssAppBean);
				ssServiceBean.setServiceType(ssService.getServiceType());
				
				String appname2 = ssService.getSsApp().getAppname();//获取到产品id
				String realId = ssService.getRealId();//获取服务真实Id
				//realId和appname2查找出来的有多个，不是一个，修改
				List<SsVersService>  ssVerServiceList = ssVersServiceDao.findByAppnameAndRealId(appname2, realId);//根据产品Id和服务reald查找版本服务实体
				for(int j=0;j<ssVerServiceList.size();j++){
					String appVersion = ssVerServiceList.get(j).getAppVersion();//获取版本
					ssServiceBean.setAppVersion(appVersion);
					ssServiceList.add(ssServiceBean);
				}
				
			}
			return ssServiceList;
			
		}
		
		return null;
	}

	/**
	 * 分页查询服务列表
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Transactional(propagation =Propagation.NOT_SUPPORTED, readOnly=true)
	public Page<SsService> getAllServices(int pageNum, int pageSize) {
	    PageRequest pageRequest = new PageRequest(pageNum-1, pageSize);
	    Page<SsService> page = serviceDao.findAll(pageRequest);
		return page;
	}
    
	/**
	 * 根据Id查询服务
	 * @param id
	 * @return
	 */
	public SsService findServiceById(String id) {
		return serviceDao.findOne(id);
		
		
	}
	/**
	 * 根据real_Id查询服务
	 * @param id
	 * @return
	 */
	public SsService findServiceByRealId(String realId) {
		return serviceDao.findByRealId(realId);
		
		
	}
	
	/**
	 * 保存
	 * @param ssAppBean
	 */
	@Transactional(propagation =Propagation.NOT_SUPPORTED, readOnly=true)
	public void saveAndUpdateApp(SsServiceBean ssServiceBean) {
		SsService ssService=toSsService(ssServiceBean);
		serviceDao.save(ssService);
	}
	/**
	 * 导入表格前，删除已有的产品服务，产品版本，服务版本。
	 * @param ssServiceBean
	 * */
	public void deleteImport(String appname){
		List<SsService> serviceList=serviceDao.findBySsAppAppname(appname);
    	if(serviceList.size()>0){
    		for(int i=0;i<serviceList.size();i++){
				//删除服务版本表原有数据
    			SsService ssService1 = serviceList.get(i);
    			serviceDao.delete(ssService1);
			}
    	}
    	List<SsVersService> list = ssVersServiceDao.findByAppname(appname);
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				//删除服务版本表原有数据
				SsVersService ssVersService1 = list.get(i);
				ssVersServiceDao.delete(ssVersService1);
			}
		}
		List<SsAppVer> appVerList=ssAppVerDao.findBySsAppAppname(appname);
    	if(appVerList.size()>0){
    		for(int i=0;i<appVerList.size();i++){
				//删除服务版本表原有数据
    			SsAppVer ssAppVer1 = appVerList.get(i);
    			ssAppVerDao.delete(ssAppVer1);
			}
    	}
	}
	/**
	 * 实体转换并且保存服务 byzb
	 * @param ssServiceBean
	 * @return
	 */
	@Transactional(propagation =Propagation.NOT_SUPPORTED, readOnly=true)
	public void SaveSsService(SsServiceBean ssServiceBean) {
		SsService ssService = new SsService();
    	
    	SsApp ssApp = new SsApp();
    	try {
			BeanUtils.copyProperties(ssApp, ssServiceBean.getSsAppBean());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		ssService.setCustomerId(ssServiceBean.getCustomerId());
		ssService.setId(ssServiceBean.getId());
		ssService.setLabel(ssServiceBean.getLabel());
		
		ssService.setPid(ssServiceBean.getPid());
		ssService.setPrice(ssServiceBean.getPrice());
		ssService.setRealId(ssServiceBean.getRealId());
		ssService.setRemark(ssServiceBean.getRemark());
		ssService.setSeq(ssServiceBean.getSeq());
		SsApp ssApp2 = new SsApp();
    	try {
			BeanUtils.copyProperties(ssApp2, ssServiceBean.getSsAppBean());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ssService.setSsApp(ssApp2);
		ssService.setServiceType(ssServiceBean.getServiceType());
		
		serviceDao.save(ssService);
	}
	/**
	 * 实体转换并且保存服务和产品的版本信息 byzb
	 * @param ssServiceBean
	 * @return
	 */
	@Transactional(propagation =Propagation.NOT_SUPPORTED, readOnly=true)
	public void saveVer(SsServiceBean ssServiceBean) {
		SsAppVer ssAppVer = new SsAppVer();
		SsVersService ssVersService = new SsVersService();
		String appname = ssServiceBean.getSsAppBean().getAppname();
		
		ssVersService.setAppname(appname);
    	ssVersService.setRealId(ssServiceBean.getRealId());
    	ssVersService.setAppVersion(ssServiceBean.getAppVersion());
    	
    	SsApp ssApp = new SsApp();
    	try {
			BeanUtils.copyProperties(ssApp, ssServiceBean.getSsAppBean());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	ssAppVer.setSsApp(ssApp);
    	String appversion = ssServiceBean.getAppVersion();
    	//查找现在的产品版本是否已经存在
    	SsAppVer ssAppVer1 = ssAppVerDao.findByAppVersionAndSsAppAppname(appversion,appname);
    	if(ssAppVer1 == null){
    		ssAppVer.setAppVersion(ssServiceBean.getAppVersion());
    	    ssAppVerDao.save(ssAppVer);
    	}
    	ssVersServiceDao.save(ssVersService);//在保存服务的时候，同时把realId、appversion、appname保存到服务版本表中
		
	}
	/**
	 * 实体转换
	 * @param ssServiceBean
	 * @return
	 */
	private SsService toSsService(SsServiceBean ssServiceBean) {
		SsService ssService = new SsService();
		SsAppVer ssAppVer = new SsAppVer();
    	SsVersService ssVersService = new SsVersService();
    	
    	
    	ssVersService.setAppname(ssServiceBean.getSsAppBean().getAppname());
    	ssVersService.setRealId(ssServiceBean.getRealId());
    	ssVersService.setAppVersion(ssServiceBean.getAppVersion());
    	ssVersServiceDao.save(ssVersService);//在保存服务的时候，同时把realId、appversion、appname保存到服务版本表中
		
    	SsApp ssApp = new SsApp();
    	try {
			BeanUtils.copyProperties(ssApp, ssServiceBean.getSsAppBean());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ssAppVer.setSsApp(ssApp);
    	ssAppVer.setAppVersion(ssServiceBean.getAppVersion());
    	ssAppVerDao.save(ssAppVer);//在保存服务的时候，同时把appname、appversion保存到产品版本表中
    	
		ssService.setCustomerId(ssServiceBean.getCustomerId());
		ssService.setId(ssServiceBean.getId());
		ssService.setLabel(ssServiceBean.getLabel());
		
		ssService.setPid(ssServiceBean.getPid());
		ssService.setPrice(ssServiceBean.getPrice());
		ssService.setRealId(ssServiceBean.getRealId());
		ssService.setRemark(ssServiceBean.getRemark());
		ssService.setSeq(ssServiceBean.getSeq());
		SsApp ssApp2 = new SsApp();
    	try {
			BeanUtils.copyProperties(ssApp2, ssServiceBean.getSsAppBean());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ssService.setSsApp(ssApp2);
		ssService.setServiceType(ssServiceBean.getServiceType());
		
		
		return ssService;
	}

	/**
	 * 服务删除
	 * @param ssServiceBean
	 */
	public void deleteByPks(String id) {
		//SsService ssService=toSsService(ssServiceBean);
		serviceDao.delete(id);
	}

   /**
    * 导入服务
    * @param uploadExcel
    * 
    */
	public void importExcel(String appName,CommonsMultipartFile uploadExcel) {
		    Workbook book = null;
		   
	        try {
	        	InputStream in = uploadExcel.getInputStream();
	            book = Workbook.getWorkbook(in);
	            // 获得第一个工作表对象
	            Sheet sheet = book.getSheet(0);
	            int rows=sheet.getRows();//行
	            int columns=sheet.getColumns();//列
	           
	            // 遍历每行每列的单元格（由第二行开始）
	            for(int i=1;i<rows;i++){
	            	SsServiceBean ssServiceBean = new SsServiceBean();
	            	SsAppVer ssAppVer = new SsAppVer();
	            	SsVersService ssVersService = new SsVersService();
	          
	            	String cell = sheet.getCell(0, i).getContents();//产品ID
	            	if(cell!=null && !("".equals(cell))){
	            		SsApp ssApp = appDao.findByappname(cell);//数据库产品
	            		SsAppBean ssAppBean = new SsAppBean();
	    				try {
	    					BeanUtils.copyProperties(ssAppBean, ssApp);
	    				} catch (IllegalAccessException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				} catch (InvocationTargetException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
		            	ssServiceBean.setSsAppBean(ssAppBean);
		            	ssAppVer.setSsApp(ssApp);
		            	

	            	}else{
	            		continue;
	            	}
	            	String cell2 = sheet.getCell(1, i).getContents();//真实服务ID
	            	if(cell2!=null && !("".equals(cell2))){
	            		ssServiceBean.setRealId(cell2);;  
	            	}
	            	
	            	String cell3 = sheet.getCell(2, i).getContents();//真实服务中文描述
	            	if(cell3!=null && !(""==cell3)){
	            		ssServiceBean.setLabel(cell3);
	            	}
	            	
	            	         	
	            	String cell4 = sheet.getCell(3, i).getContents();//标准单价         
	            	ssServiceBean.setPrice(Double.valueOf(cell4));  
	            	
	            	String cell5 = sheet.getCell(4, i).getContents();//上级真实服务ID
	            	ssServiceBean.setPid(cell5);  
	            	
	            	String cell6 = sheet.getCell(5, i).getContents();//服务序号
	            	ssServiceBean.setSeq(cell6);
	            
	            	String cell7 = sheet.getCell(6, i).getContents();//服务类型
	            	ssServiceBean.setServiceType(cell7);
	            	String cell9 = sheet.getCell(8, i).getContents();//备注	            	
	            	if(cell9!=null && !(""==cell9)){
	            		ssServiceBean.setRemark(cell9);
	            	} 
	            	String cell8 = sheet.getCell(7, i).getContents();//销售版本
	            	
	            	if(cell8.indexOf(";") != -1){
	            		String[] strs = cell8.split(";");
	            		for(int vs=0;vs<strs.length;vs++){
	            			String version = strs[vs];
	            			ssVersService.setAppVersion(version);
		            		ssVersService.setAppname(cell);
		            		ssVersService.setRealId(cell2);
		            		
		            		ssAppVer.setAppVersion(version);
		            		ssServiceBean.setAppVersion(version);
		            		
		            		ssVersServiceDao.save(ssVersService);
		            		ssAppVerDao.save(ssAppVer);
	            		}
	            	}else{
	            		ssVersService.setAppVersion(cell8);
	            		ssVersService.setAppname(cell);
	            		ssVersService.setRealId(cell2);
            		
	            		//ssAppVer.setAppVersion(cell8);
	            		ssVersServiceDao.save(ssVersService);
	            		ssServiceBean.setAppVersion(cell8);
	            		//ssAppVerDao.save(ssAppVer);
		            	
		            	//saveAndUpdateApp(ssServiceBean);
	            	}
	            	
	            }  
	        } catch (Exception e) {
	            System.out.println(e);
	        }finally{
	            if(book!=null){
	                book.close();
	            }
	        }
		
		
	}
	/**
	    * 导入服务 byzb
	    * @param uploadExcel，appName：当前产品
	    * @return 是否成功
	    */
		public String importExcel1(String appName,CommonsMultipartFile uploadExcel) {
			    Workbook book = null;
			   
		        try {
		        	InputStream in = uploadExcel.getInputStream();
		            book = Workbook.getWorkbook(in);
		            // 获得第一个工作表对象
		            Sheet sheet = book.getSheet(0);
		            int rows=sheet.getRows();//行
		            int columns=sheet.getColumns();//列
		            String cellToDelete = sheet.getCell(0, 1).getContents();//产品ID
		            //当前产品和数据表中产品相同时，进行删除
		            if(!cellToDelete.equals("") && cellToDelete != null){
		            	if(cellToDelete.equals(appName)){
		            		deleteImport(appName);
		            	}
		            }
		            // 遍历每行每列的单元格（由第二行开始）
		            for(int i=1;i<rows;i++){
		            	SsServiceBean ssServiceBean = new SsServiceBean();
		            	SsAppVer ssAppVer = new SsAppVer();
		            	String cell = sheet.getCell(0, i).getContents();//产品ID
		            	if(cell!=null && !("".equals(cell))){
		            		//导入数据和当前产品不相符
		            		if(!cell.equals(appName)){
		            			return "false";
		            		}
		            		SsApp ssApp = appDao.findByappname(cell);
		            		SsAppBean ssAppBean = new SsAppBean();
		    				try {
		    					BeanUtils.copyProperties(ssAppBean, ssApp);
		    				} catch (IllegalAccessException e) {
		    					// TODO Auto-generated catch block
		    					e.printStackTrace();
		    				} catch (InvocationTargetException e) {
		    					// TODO Auto-generated catch block
		    					e.printStackTrace();
		    				}
			            	ssServiceBean.setSsAppBean(ssAppBean);
			            	ssAppVer.setSsApp(ssApp);
			            	

		            	}else{
		            		continue;
		            	}
		            	String cell2 = sheet.getCell(1, i).getContents();//真实服务ID
		            	if(cell2!=null && !("".equals(cell2))){
		            		ssServiceBean.setRealId(cell2);;  
		            	}
		            	
		            	String cell3 = sheet.getCell(2, i).getContents();//真实服务中文描述
		            	if(cell3!=null && !(""==cell3)){
		            		ssServiceBean.setLabel(cell3);
		            	}
		            	
		            	         	
		            	String cell4 = sheet.getCell(3, i).getContents();//标准单价         
		            	ssServiceBean.setPrice(Double.valueOf(cell4));  
		            	
		            	String cell5 = sheet.getCell(4, i).getContents();//上级真实服务ID
		            	ssServiceBean.setPid(cell5);  
		            	
		            	String cell6 = sheet.getCell(5, i).getContents();//服务序号
		            	ssServiceBean.setSeq(cell6);
		            
		            	String cell7 = sheet.getCell(6, i).getContents();//服务类型
		            	ssServiceBean.setServiceType(cell7);
		            	String cell9 = sheet.getCell(8, i).getContents();//备注	            	
		            	if(cell9!=null && !(""==cell9)){
		            		ssServiceBean.setRemark(cell9);
		            	} 
		            	String cell8 = sheet.getCell(7, i).getContents();//销售版本
		            	
		            	if(cell8.indexOf(";") != -1){
		            		String[] strs = cell8.split(";");
		            		for(int vs=0;vs<strs.length;vs++){
		            			String version = strs[vs];
		            			ssServiceBean.setAppVersion(version);
			            		saveVer(ssServiceBean);//保存版本信息
		            		}
		            	}else{
		            		ssServiceBean.setAppVersion(cell8);
		            		saveVer(ssServiceBean);
		            	}
		            	//保存服务信息
		            	SaveSsService(ssServiceBean);
		            }  
		            return "true";
		        } catch (Exception e) {
		            System.out.println(e);
		            return "false";
		        }finally{
		            if(book!=null){
		                book.close();
		            }
		        }
			
			
		}
	   
	/**
	 * 根据产品id获取所对应的产品的所有服务的总价格
	 * @return
	 */
   public double getTotalPriceByAppname(String appname){
	   List<SsService> ssServiceList = serviceDao.findBySsAppAppname(appname);
	   double totalPrice = 0;
	   for(SsService ssService:ssServiceList){
		   totalPrice += ssService.getPrice();
	   }
	   return totalPrice;
   }
	
   /**
    * 根据realId获取服务
    * @return
    */
   public SsServiceBean getServiceByRealId(String realId){
	   SsServiceBean bean = null;
	   try {
	   SsService ssService = serviceDao.findByRealId(realId);
	   bean = new SsServiceBean();
		BeanUtils.copyProperties(bean, ssService);
	} catch (IllegalAccessException | InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return bean;
   }
   
   public List<SsServiceBean> findByAppName(String appName){
	   List<SsService> list = (List<SsService>)serviceDao.findBySsAppAppname(appName);
	   List<SsServiceBean> beanList = new ArrayList<SsServiceBean>();
	   SsServiceBean bean = null;
	   for(SsService ssService:list){
		   bean = new SsServiceBean();
		   bean.setId(ssService.getId());
			bean.setCustomerId(ssService.getCustomerId());
			bean.setPrice(ssService.getPrice());
			bean.setSeq(ssService.getSeq());
			bean.setLabel(ssService.getLabel());
			SsAppBean ssAppBean = new SsAppBean();
			try {
				BeanUtils.copyProperties(ssAppBean, ssService.getSsApp());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bean.setSsAppBean(ssAppBean);
			bean.setPid(ssService.getPid());
			bean.setRealId(ssService.getRealId());
			bean.setRemark(ssService.getRemark());
			bean.setServiceType(ssService.getServiceType());
			beanList.add(bean);
	   }
	   return beanList;
   }
}
