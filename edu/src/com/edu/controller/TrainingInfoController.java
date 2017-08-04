package com.edu.controller;

import java.io.InputStream;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.edu.domain.Bmpjxx;
import com.edu.domain.Jsjbxx;
import com.edu.domain.Pxhd;
import com.edu.service.TrainingInfoServiceI;
import com.edu.service.impl.BmpjxxServiceImpl;
import com.edu.service.impl.JsjbxxServiceImpl;
import com.edu.util.DateUtils;
import com.edu.util.DateUtilsSafe;
import com.edu.util.excel.ExcelUtil;
import com.edu.util.excel.ImpEntity;
import com.edu.util.excel.ReflectionUtils;
import com.edu.util.excel.SheetContent;
import com.edu.vo.GenePageVo;
import com.edu.vo.JsjbxxVo;
import com.edu.vo.Pagination;
import com.edu.vo.PxhdVo;
import com.edu.vo.UserVo;

@Controller
@RequestMapping("/trainingInfo")
public class TrainingInfoController {
	
	@Autowired
	TrainingInfoServiceI trainingInfo;
	@Autowired
	JsjbxxServiceImpl jsjbxxServiceImp; 
	@Autowired
	BmpjxxServiceImpl bmpjxxServiceImp;
	@RequestMapping("/trainingInfoPage")
	private String trainingInfoPage(){
		return "trainingInfo";
	}
	@RequestMapping("/activityManagementPage.do")
	private String activityManagementPage(){
		return "activityManagement";
	}
	@ResponseBody
	@RequestMapping(value="/getAllPxhd.do",method = RequestMethod.POST,consumes="application/json")
	private GenePageVo getAllPxhd(@RequestBody PxhdVo pxhdVo,HttpServletRequest request){
		String zgh = "11";//(String)request.getSession().getAttribute("zgh");
		
		final GenePageVo<Pxhd> gv = new GenePageVo<Pxhd>();
		int count = trainingInfo.getAllPxhdCount(pxhdVo);
		pxhdVo.setTotalCount(count);
		Pagination p = new Pagination();
		BeanUtils.copyProperties(pxhdVo, p);
		List<Pxhd> list= trainingInfo.getAllPxhd(pxhdVo,zgh);
		gv.setList(list);
		gv.setPage(p);
		return gv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/exportTrainingInfo.do", method = { RequestMethod.GET,RequestMethod.POST },produces = "text/html;charset=UTF-8")
	public String exportTrainingInfo(HttpServletRequest request, HttpServletResponse response,PxhdVo pxhdVo){
		String msg = "";
		try {
			//创建表文件名
		    String fileName="培训信息导出"+DateUtils.currentDate();
		    List<Pxhd> list= trainingInfo.getAllPxhdNoPage(pxhdVo);
			
			//此对象用户各个封装并返回，单个sheet内容默认不能超过65533,多处部分,导出时候程序自行截断
	    	Workbook hssfWorkbook2=trainingInfo.getexcelData(list);
	    	ExcelUtil.writeExcelToResponse(request, response, fileName, hssfWorkbook2);
	 		msg = "导出成功！";
		} catch (Exception e) {
			e.printStackTrace();
			msg="ERROR:"+e.getMessage();
		}
	 	return msg;
	}
	
	@RequestMapping(value = "/importTrainingInfo.do", method = {RequestMethod.GET,RequestMethod.POST },produces = "text/html;charset=UTF-8")
	public String importTrainingInfo(HttpServletRequest request, HttpServletResponse response,UserVo userVo){
		String msg = "";
		try {
			
			ShiroHttpServletRequest shiroRequest = (ShiroHttpServletRequest) request;  
		    CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();  
		    MultipartHttpServletRequest multipartRequest = commonsMultipartResolver.resolveMultipart((HttpServletRequest) shiroRequest.getRequest());  
			
//			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
//	        System.out.println("通过传统方式form表单提交方式导入excel文件！");  
	        InputStream in =null;  
	        List<List<Object>> listob = null;  
	        MultipartFile file = multipartRequest.getFile("upfile");  
	        if(file.isEmpty()){
	            throw new Exception("文件不存在！");  
	        }
	        in = file.getInputStream();
	        List<SheetContent> wk = ExcelUtil.getExcelInfo(in, file.getOriginalFilename());
	        package1(wk);
	        
		} catch (Exception e) {
			e.printStackTrace();
			msg="ERROR:"+e.getMessage();
		}
	 	return msg;
	}
	
	private Object package1(List<SheetContent> wk){
		for(SheetContent sc : wk){
        	String sheetName = sc.getSheetName();
        	Map<Object, List<Object>> map = sc.getSheetContentMap();
        	Set<Object> keys = map.keySet();
        	Iterator<Object> it = keys.iterator();
        	
        	ImpEntity ie = new ImpEntity();
        	//用于封装数据
        	while(it.hasNext()){
        		Object key = it.next();
        		List<Object> list = map.get(key);
        		if(key.toString().equals("0")){
        			ie.setTableName(list.get(0).toString());
        		}else if(key.toString().equals("1")){
        			ie.setExpTime(list.get(0).toString());
        		}else if(key.toString().equals("2")){
        			System.out.println("字段："+list);
        			packageField(ie,list);
        		}else{
        			try {
						packageVal(ie,key.toString(),list);
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}
        		}
        	}
        	trainingInfo.insertTrainingInfoBach(ie.getVals());
        }
		return null;
	}
	
	private void packageVal(ImpEntity ie, String key,
			List<Object> list) throws NoSuchFieldException, SecurityException, ParseException {
		List<String> fList = ie.getFields();
		Pxhd p = new Pxhd();
		ie.setClazz(Pxhd.class);
		for(int i=0,j=fList.size();i<j;i++){
			String fName = fList.get(i);
			Object o = list.get(i);
			String typeName = p.getClass().getDeclaredField(fName).getType().getName();
			ReflectionUtils.setFieldValue(p,fName,getTo(o,typeName));
		}
		ie.getVals().add(p);
		System.out.println(p);
	}
	private Object getTo(Object o, String typeName) throws ParseException {
		if(o==null){
			return null;
		}
		if(StringUtils.isEmpty(o.toString())){
			return null;
		}
		if(typeName.equals("java.lang.Integer")){
			return Integer.parseInt(o.toString());
		}
		if(typeName.equals("java.lang.Long")){
			return Long.parseLong(o.toString());
		}
		if(typeName.equals("java.lang.Double")){
			return Double.parseDouble(o.toString());
		}
		if(typeName.equals("java.lang.Float")){
			return Float.parseFloat(o.toString());
		}
		if(typeName.equals("java.util.Date")){
			return DateUtilsSafe.parseDatetime(o.toString());
		}
		return o.toString();
	}
	/**
	 * 封装字段属性名
	 * @param ie
	 * @param list
	 */
	private void packageField(final ImpEntity ie, List<Object> list) {
		List<String> fN = ie.getFields();
		for(int i=0,j=list.size();i<j;i++){
			String ts = list.get(i).toString().split(":")[1];
			fN.add(ts);
		}
	}
	@ResponseBody
	@RequestMapping(value="/signUp.do",method = RequestMethod.POST,consumes="application/json")
	private int signUp(@RequestBody Pxhd record){
		record.setRecordStatus(1);
		Bmpjxx bmp = new Bmpjxx();
		Jsjbxx jsj = new Jsjbxx();
		JsjbxxVo jsjbxxVo = new JsjbxxVo(); 
		jsjbxxVo.setZgh("11");
		jsj = jsjbxxServiceImp.getJsjbxxInfo(jsjbxxVo);
		bmp.setHdid(record.getHdid());
		bmp.setXymc(jsj.getXy());
		bmp.setZgh(jsj.getZgh());
		bmp.setXm(jsj.getXm());
		bmp.setBmbz(0);
		bmp.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		return trainingInfo.signUp(bmp);
	}
	
/*	@ResponseBody
	@RequestMapping(value="/getAllBm.do",method = RequestMethod.POST,consumes="application/json")
	private GenePageVo getAllBm(@RequestBody PxhdVo pxhdVo){
		final GenePageVo<Bmpjxx> gv = new GenePageVo<Bmpjxx>();
		int count = trainingInfo.getAllPxhdCount(pxhdVo);
		pxhdVo.setTotalCount(count);
		Pagination p = new Pagination();
		BeanUtils.copyProperties(pxhdVo, p);
		List<Bmpjxx> list= trainingInfo.getAllBm(pxhdVo);
		gv.setList(list);
		gv.setPage(p);
		return gv;
	}*/
	
	@ResponseBody
	@RequestMapping(value="/cancelSignUp.do",method = RequestMethod.POST,consumes="application/json")
	private int cancelSignUp(@RequestBody String id){
		return trainingInfo.cancelSignUp(id);
	}
	@RequestMapping(value="/delete.do",method = RequestMethod.POST,consumes="application/json")
	private int delete(@RequestBody String ids){
		String[] array = ids.split(",");
		int result = 0;
		for(int i = 0;i < array.length; i++){
			result = trainingInfo.delete(array[i]);
		}
		return result;
	}
	@RequestMapping(value="/addPxhd.do",method = RequestMethod.POST)
	private ModelAndView addPxhd(Pxhd pxhd){
		ModelAndView mav = new ModelAndView();
		String hdid = pxhd.getHdid();
		int result = 0;
		if(hdid == ""){
			pxhd.setHdid(UUID.randomUUID().toString().replaceAll("-", ""));
			pxhd.setDqcyrs(0);
			result = trainingInfo.insert(pxhd);
		}else{
			result = trainingInfo.updateByPrimaryKeySelective(pxhd);
		}
		if(result==1){
			mav.addObject("msg", "操作成功！");
		}else{
			mav.addObject("msg", "操作失败！");
		}
		 mav.setViewName("redirect:/trainingInfo/activityManagementPage.do");
		return mav;
	}
	@RequestMapping(value="/selectTrainingInfoById.do",method = RequestMethod.POST)
	@ResponseBody
	private Pxhd selectTrainingInfoById(@RequestBody String hdid){
		return trainingInfo.selectByPrimaryKey(hdid);
	}
	@RequestMapping(value="/bmDelete.do",method = RequestMethod.POST,consumes="application/json")
	private int bmDelete(@RequestBody String ids){
		String[] array = ids.split(",");
		int result = 0;
		for(int i = 0;i < array.length; i++){
			result = bmpjxxServiceImp.deleteByPrimaryKey(array[i]);
		}
		return result;
	}
	
}
