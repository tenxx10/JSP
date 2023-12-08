package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DollCartAddService;
import vo.ActionForward;
import vo.Doll;

public class DollCartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		DollCartAddService dollCartAddService = new DollCartAddService();
		int id= Integer.parseInt(request.getParameter("id"));
		Doll cartDoll = dollCartAddService.getCartDoll(id);
		dollCartAddService.addCart(request, cartDoll);
		ActionForward forward = new ActionForward("dollCartList.doll", false);
		return forward;
		

	}

}
