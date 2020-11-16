package kr.co.engcom.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.engcom.action.Action;
import kr.co.engcom.action.ActionForward;
import kr.co.engcom.service.TimeLineAddService;
import kr.co.engcom.service.TimeLineDeleteService;
import kr.co.engcom.service.TimeLineEditService;
import kr.co.engcom.service.TimeLineListMainService;
import kr.co.engcom.service.TimeLineListService;
import kr.co.engcom.service.TimeLineUpdateService;
import kr.co.engcom.service.papago;





@WebServlet("*.tc")
public class TimeLineController extends HttpServlet implements javax.servlet.Servlet{
	private static final long serialVersionUID = 1L;
       
   
    public TimeLineController() {
        super();
    
    }
    protected void doProcess(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	 String RequestURI=request.getRequestURI();
		 String contextPath=request.getContextPath();
		 String command=RequestURI.substring(contextPath.length());
		 ActionForward forward=null;
		 Action action=null;	
		 String viewpage="";
		 
		 
		 if(command.equals("/TimeLineWrite.tc")){
			   forward=new ActionForward();
			   forward.setRedirect(false);
			   forward.setPath("/WEB-INF/views/timeline/TimeLineWrite.jsp");
		   }else if (command.equals("/TimeLineAdd.tc")){
			   action = new TimeLineAddService();
			   try {
				forward=action.execute(request, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}			  
		   }else if (command.equals("/TimeLineListMain.tc")){
			   action = (Action) new TimeLineListMainService();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }		   
		   }else if(command.equals("/TimeLineList.tc")){
			   action = (Action) new TimeLineListService();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }	
		   }else if(command.equals("/TimeLineDelete.tc")){
			   action = (Action) new TimeLineDeleteService();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }	
		   }else if(command.equals("/TimeLineEdit.tc")){
			   action = (Action) new TimeLineEditService();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }	
		   }else if(command.equals("/TimeLineUpdate.tc")){
			   action = (Action) new TimeLineUpdateService();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }	
		   }else if(command.equals("/papago.tc")){
			   action = (Action) new papago();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }	
		   }
	
		   
		   
		   
		   
		   if(forward != null) {
        		if(forward.isRedirect()) { //true 
        			response.sendRedirect(forward.getPath());
        		}else {
        			RequestDispatcher dis  = request.getRequestDispatcher(forward.getPath());
        			dis.forward(request, response);
        		}
        	}
        	
    		
    	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("board");
		doProcess(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doProcess(request, response);
	}

}
