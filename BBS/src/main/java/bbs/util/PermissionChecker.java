package bbs.util;

import bbs.article.model.Article;

public class PermissionChecker {

	public static boolean canModify(String userId, Article article) {
		return article.getWriter().getId().equals(userId);
	}
}
