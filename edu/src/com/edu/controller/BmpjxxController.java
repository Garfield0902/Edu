package com.edu.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.component.Log;
import com.edu.domain.Bmpjxx;
import com.edu.domain.Jsjbxx;
import com.edu.domain.Pxhd;
import com.edu.service.BmpjxxService;
import com.edu.service.JsjbxxService;
import com.edu.service.TrainingInfoServiceI;
import com.edu.vo.BmpjxxVo;
import com.edu.vo.EvaluationManagementVo;
import com.edu.vo.GenePageVo;
import com.edu.vo.JsjbxxVo;
import com.edu.vo.Pagination;
import com.edu.vo.PxhdVo;
import com.edu.vo.PxhddaVo;

@Controller
@RequestMapping("/bmpjxx")
public class BmpjxxController {
	Logger logger = LoggerFactory.getLogger(BmpjxxController.class);
	
	@Autowired
	private BmpjxxService service;
	@Autowired
	private TrainingInfoServiceI trainingInfoService;
	@Autowired
	private JsjbxxService jsjbxxService;
	
	@RequestMapping(value = "/archives.do")
	@Log(operationName="跳转培训档案界面",operationType="界面跳转操作")
	public String archives(HttpServletRequest request){
		return "trainarchives";
	}
	@RequestMapping(value = "/evaluationManagementPage.do")
	public String evaluationManagementPage(HttpServletRequest request){
		return "evaluationManagement";
	}
	
	@RequestMapping(value = "/allArchives.do",method={RequestMethod.GET,RequestMethod.POST},consumes="application/json")
	@ResponseBody
	@Log(operationName="查看培训档案",operationType="archives操作")
	public GenePageVo allArchives(@RequestBody BmpjxxVo bv){
		logger.debug("查询所有用户！");
		String searchType = bv.getSearchType();
		if(StringUtils.isEmpty(searchType)){
			logger.error("档案搜索条件类型为空，无法搜索！");
			return null;
		}
		if(bv.getHdnf()!=null&&bv.getHdnf().intValue()==-1){
			bv.setHdnf(null);
		}
		final GenePageVo<PxhddaVo> gv = new GenePageVo<PxhddaVo>();
		int count = service.getAllJsjbxxCount(bv);
		bv.setTotalCount(count);
		
		Pagination p = new Pagination();
		BeanUtils.copyProperties(bv, p);
		List<PxhddaVo> list= service.getAllJsjbxx(bv);
		gv.setList(list);
		gv.setPage(p);
		return gv;
	}
	@RequestMapping(value = "/getAllEvaluationManagement.do",method={RequestMethod.POST},consumes="application/json")
	@ResponseBody
	public GenePageVo getAllEvaluationManagement(@RequestBody PxhdVo pxhdVo){
		final GenePageVo<EvaluationManagementVo> gv = new GenePageVo<EvaluationManagementVo>();
		int count = service.getAllEvaluationManagementCount(pxhdVo);
		pxhdVo.setTotalCount(count);
		
		Pagination p = new Pagination();
		BeanUtils.copyProperties(pxhdVo,p);
		List<EvaluationManagementVo> list= service.getAllEvaluationManagement(pxhdVo);
		gv.setList(list);
		gv.setPage(p);
		return gv;
	}
	@RequestMapping(value = "/getBmListByHdid.do",method={RequestMethod.POST},consumes="application/json")
	@ResponseBody
	public GenePageVo getBmListByHdid(@RequestBody BmpjxxVo bv){
		final GenePageVo<Bmpjxx> gv = new GenePageVo<Bmpjxx>();
		int count = service.getAllBmByIdCount(bv);
		bv.setTotalCount(count);
		Pagination p = new Pagination();
		BeanUtils.copyProperties(bv,p);
		List<Bmpjxx> list= service.getAllBmpjxx(bv);
		gv.setList(list);
		gv.setPage(p);
		return gv;
	}
	@RequestMapping(value = "/getBmListByzgh.do",method={RequestMethod.GET,RequestMethod.POST},consumes="application/json")
	@ResponseBody
	public GenePageVo getBmListByzgh(@RequestBody BmpjxxVo bv){
		final GenePageVo<PxhddaVo> gv = new GenePageVo<PxhddaVo>();
		int count = service.getAllJsjbxxCount(bv);
		bv.setTotalCount(count);
		
		Pagination p = new Pagination();
		BeanUtils.copyProperties(bv, p);
		List<PxhddaVo> list= service.getAllJsjbxx(bv);
		gv.setList(list);
		gv.setPage(p);
		return gv;
	}
	@ResponseBody
	@RequestMapping(value="/add.do",method = RequestMethod.POST,consumes="application/json")
	private int add(@RequestBody Bmpjxx bmpjxx){
		String hdid = bmpjxx.getHdid();
		JsjbxxVo js = new JsjbxxVo();
		String zgh = "11";//(String)req.getSession().getAttribute("zgh");
		js.setZgh(zgh);
		Jsjbxx jsInfo = jsjbxxService.getJsjbxxInfo(js);
		bmpjxx.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		bmpjxx.setHdid(hdid);
		bmpjxx.setXydm(jsInfo.getXy());
		bmpjxx.setXymc(null);
		bmpjxx.setZgh(zgh);
		bmpjxx.setXm(jsInfo.getXm());
		bmpjxx.setBmbz(1);
		bmpjxx.setBmsj(new Date());
		bmpjxx.setPjbz(1);
		bmpjxx.setPjnf((new Date()).getYear());
		bmpjxx.setPjsj(new Date());
		bmpjxx.setPjfs(1);
		bmpjxx.setLrr("aaaaq");
		bmpjxx.setRecordStatus(1);
		bmpjxx.setRecordVersion(1);
		return service.insertSelective(bmpjxx);
	}
	@ResponseBody
	@RequestMapping(value="/delete.do",method = RequestMethod.POST,consumes="application/json")
	private int delete(@RequestBody String id){
		return service.deleteByPrimaryKey(id);
	}
}
