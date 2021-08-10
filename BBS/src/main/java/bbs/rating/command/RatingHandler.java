package bbs.rating.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.auth.model.User;
import bbs.mvc.command.CommandHandler;
import bbs.rating.model.Rating;
import bbs.rating.service.WriteRatingService;
import bbs.util.ErrorUtil;

public class RatingHandler extends CommandHandler {

	private WriteRatingService writeService = new WriteRatingService();

	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/rating.jsp";
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		//String ratingID = req.getParameter("ratingID");
		String userID = ((User)req.getSession().getAttribute("authUser")).getId();
		String movieName = req.getParameter("movieName");
		String directorName = req.getParameter("directorName");
		String movieYear = req.getParameter("movieYear");
		String agegroupDivide = req.getParameter("agegroupDivide");
		String genreDivide = req.getParameter("genreDivide");
		String ratingTitle = req.getParameter("ratingTitle");
		String ratingContent = req.getParameter("ratingContent");
		String totalScore = req.getParameter("totalScore");
		String immersionScore = req.getParameter("immersionScore");
		String visualbeautyScore = req.getParameter("visualbeautyScore");
		String messageScore = req.getParameter("messageScore");
		//String likeCount = req.getParameter("likeCount");

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		ErrorUtil.checkEmpty(errors, userID, "userID");

		if (errors.isEmpty() == false)
			return getFormViewName();

		int movieYearVale = Integer.parseInt(movieYear);

		// 데이터가 정상일 경우
		Rating rating = new Rating(
				-1,
				userID,
				movieName,
				directorName,
				movieYearVale,
				agegroupDivide,
				genreDivide,
				ratingTitle,
				ratingContent,
				totalScore,
				immersionScore,
				visualbeautyScore,
				messageScore,
				0);
		
		writeService.write(rating);

		
		
		return getFormViewName();
	}
}
